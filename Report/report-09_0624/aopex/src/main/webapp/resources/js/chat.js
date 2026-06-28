(function () {
    const contextPath = window.APP_CONTEXT || '';
    const roomListEl = document.getElementById('roomList');
    const chatSectionEl = document.getElementById('chatSection');
    const currentRoomEl = document.getElementById('currentRoom');
    const statusEl = document.getElementById('status');
    const roomNameInput = document.getElementById('roomName');
    const createRoomBtn = document.getElementById('createRoomBtn');
    const senderInput = document.getElementById('sender');
    const messageInput = document.getElementById('messageInput');
    const sendBtn = document.getElementById('sendBtn');
    const leaveBtn = document.getElementById('leaveBtn');

    let stompSocket = null;
    let roomSubscription = null;
    let roomListSubscription = null;
    let currentRoom = null;
    let roomCache = [];
    let pendingEnterTimer = null;

    function setStatus(message) {
        statusEl.textContent = message || '';
    }

    function parseFrame(rawFrame) {
        const frame = { command: '', headers: {}, body: '' };
        const parts = rawFrame.split('\n\n');
        const headerLines = parts[0].split('\n');
        frame.command = headerLines.shift();
        for (const line of headerLines) {
            const idx = line.indexOf(':');
            if (idx > -1) {
                frame.headers[line.slice(0, idx)] = line.slice(idx + 1);
            }
        }
        frame.body = parts.slice(1).join('\n\n').replace(/\u0000$/, '');
        return frame;
    }

    function buildFrame(command, headers, body) {
        let frame = command + '\n';
        Object.entries(headers || {}).forEach(([key, value]) => {
            frame += key + ':' + value + '\n';
        });
        frame += '\n';
        frame += body || '';
        frame += '\u0000';
        return frame;
    }

    function connect() {
        if (stompSocket && (stompSocket.readyState === WebSocket.OPEN || stompSocket.readyState === WebSocket.CONNECTING)) {
            return;
        }

        const protocol = location.protocol === 'https:' ? 'wss:' : 'ws:';
        const socketUrl = protocol + '//' + location.host + contextPath + '/ws-stomp';
        stompSocket = new WebSocket(socketUrl);
        stompSocket.onopen = () => {
            stompSocket.send(buildFrame('CONNECT', {
                'accept-version': '1.2',
                'host': location.host
            }));
        };
        stompSocket.onmessage = (event) => {
            const frame = parseFrame(event.data);
            if (frame.command === 'CONNECTED') {
                subscribeRooms();
                setStatus('STOMP 연결 완료');
                if (currentRoom) {
                    subscribeRoom(currentRoom.roomId);
                }
                return;
            }
            if (frame.command === 'MESSAGE') {
                const body = frame.body ? JSON.parse(frame.body) : null;
                if (!body) {
                    return;
                }
                if (frame.headers.destination === '/topic/rooms') {
                    roomCache = body;
                    renderRooms();
                    return;
                }
                appendMessage(body);
            }
        };
        stompSocket.onclose = () => {
            roomListSubscription = null;
            roomSubscription = null;
            setStatus('연결이 종료되었습니다.');
        };
        stompSocket.onerror = () => {
            setStatus('연결 오류가 발생했습니다.');
        };
    }

    function subscribeRooms() {
        if (!stompSocket || stompSocket.readyState !== WebSocket.OPEN) {
            return;
        }
        if (roomListSubscription) {
            return;
        }
        roomListSubscription = 'rooms-sub';
        stompSocket.send(buildFrame('SUBSCRIBE', {
            id: roomListSubscription,
            destination: '/topic/rooms'
        }));
    }

    function subscribeRoom(roomId) {
        if (!stompSocket || stompSocket.readyState !== WebSocket.OPEN) {
            return;
        }
        if (roomSubscription) {
            stompSocket.send(buildFrame('UNSUBSCRIBE', {
                id: roomSubscription
            }));
        }
        roomSubscription = 'room-sub-' + Date.now();
        stompSocket.send(buildFrame('SUBSCRIBE', {
            id: roomSubscription,
            destination: '/topic/chat/' + roomId
        }));
    }

    function refreshRooms() {
        fetch(contextPath + '/api/rooms')
            .then((response) => response.json())
            .then((data) => {
                roomCache = data;
                renderRooms();
            });
    }

    function renderRooms() {
        if (!roomCache.length) {
            roomListEl.innerHTML = '<div class="meta">등록된 대화방이 없습니다.</div>';
            return;
        }

        roomListEl.innerHTML = roomCache.map((room) => {
            const activeClass = currentRoom && currentRoom.roomId === room.roomId ? 'active' : '';
            return [
                '<div class="room-item ' + activeClass + '">',
                '<div>',
                '<div class="room-name">' + escapeHtml(room.roomName) + '</div>',
                '<div class="meta">참여 ' + room.memberCount + '명</div>',
                '</div>',
                '<button data-room-id="' + room.roomId + '">입장</button>',
                '</div>'
            ].join('');
        }).join('');

        roomListEl.querySelectorAll('button[data-room-id]').forEach((button) => {
            button.addEventListener('click', () => enterRoom(button.dataset.roomId));
        });
    }

    function createRoom() {
        const roomName = roomNameInput.value.trim();
        if (!roomName) {
            setStatus('대화방 이름을 입력해 주세요.');
            return;
        }

        const body = new URLSearchParams();
        body.append('roomName', roomName);

        fetch(contextPath + '/api/rooms', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            body: body.toString()
        })
            .then(() => {
                roomNameInput.value = '';
                refreshRooms();
                setStatus('대화방을 만들었습니다.');
            });
    }

    function enterRoom(roomId) {
        const room = roomCache.find((item) => item.roomId === roomId);
        if (!room) {
            return;
        }
        if (currentRoom && currentRoom.roomId === roomId && stompSocket && stompSocket.readyState === WebSocket.OPEN) {
            setStatus('이미 입장한 대화방입니다.');
            return;
        }

        if (pendingEnterTimer) {
            clearInterval(pendingEnterTimer);
            pendingEnterTimer = null;
        }

        const sender = senderInput.value.trim() || '익명';
        senderInput.value = sender;
        currentRoom = room;
        currentRoomEl.textContent = room.roomName + ' (' + room.roomId + ')';
        chatSectionEl.innerHTML = '';
        connect();

        const sendEnter = () => {
            subscribeRoom(room.roomId);
            stompSocket.send(buildFrame('SEND', {
                destination: '/app/chat.enter',
                'content-type': 'application/json'
            }, JSON.stringify({
                type: 'ENTER',
                roomId: room.roomId,
                roomName: room.roomName,
                sender: sender
            })));
        };

        if (stompSocket && stompSocket.readyState === WebSocket.OPEN) {
            sendEnter();
        } else {
            pendingEnterTimer = setInterval(() => {
                if (currentRoom && currentRoom.roomId === room.roomId && stompSocket && stompSocket.readyState === WebSocket.OPEN) {
                    clearInterval(pendingEnterTimer);
                    pendingEnterTimer = null;
                    sendEnter();
                }
            }, 50);
        }
        renderRooms();
        setStatus(room.roomName + '에 입장했습니다.');
    }

    function sendMessage() {
        if (!currentRoom) {
            setStatus('먼저 대화방에 입장해 주세요.');
            return;
        }
        if (!stompSocket || stompSocket.readyState !== WebSocket.OPEN) {
            setStatus('연결이 아직 준비되지 않았습니다.');
            return;
        }
        const text = messageInput.value.trim();
        if (!text) {
            return;
        }
        const sender = senderInput.value.trim() || '익명';
        stompSocket.send(buildFrame('SEND', {
            destination: '/app/chat.send',
            'content-type': 'application/json'
        }, JSON.stringify({
            type: 'CHAT',
            roomId: currentRoom.roomId,
            sender: sender,
            content: text
        })));
        messageInput.value = '';
    }

    function leaveRoom() {
        if (!currentRoom) {
            return;
        }
        const room = currentRoom;
        const sender = senderInput.value.trim() || '익명';
        if (pendingEnterTimer) {
            clearInterval(pendingEnterTimer);
            pendingEnterTimer = null;
        }
        if (stompSocket && stompSocket.readyState === WebSocket.OPEN) {
            stompSocket.send(buildFrame('SEND', {
                destination: '/app/chat.leave',
                'content-type': 'application/json'
            }, JSON.stringify({
                type: 'LEAVE',
                roomId: room.roomId,
                sender: sender
            })));
            stompSocket.send(buildFrame('DISCONNECT', {}));
            setTimeout(() => stompSocket.close(), 50);
        } else if (stompSocket) {
            stompSocket.close();
        }
        currentRoom = null;
        roomSubscription = null;
        currentRoomEl.textContent = '선택된 대화방이 없습니다.';
        chatSectionEl.innerHTML = '';
        refreshRooms();
        setStatus(room.roomName + '에서 나왔습니다.');
    }

    function appendMessage(message) {
        if (!currentRoom || message.roomId !== currentRoom.roomId) {
            return;
        }
        const element = document.createElement('div');
        element.className = 'message' + (message.type === 'SYSTEM' ? ' system' : '');
        element.innerHTML = [
            '<strong>' + escapeHtml(message.sender || 'system') + '</strong>',
            '<div>' + escapeHtml(message.content || '') + '</div>'
        ].join('');
        chatSectionEl.appendChild(element);
        chatSectionEl.scrollTop = chatSectionEl.scrollHeight;
    }

    function escapeHtml(value) {
        return String(value)
            .replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;')
            .replace(/'/g, '&#39;');
    }

    createRoomBtn.addEventListener('click', createRoom);
    sendBtn.addEventListener('click', sendMessage);
    leaveBtn.addEventListener('click', leaveRoom);
    messageInput.addEventListener('keydown', (event) => {
        if (event.key === 'Enter') {
            sendMessage();
        }
    });

    refreshRooms();
})();
