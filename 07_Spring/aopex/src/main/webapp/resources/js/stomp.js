// STOMP 클라이언트 생성 : WebSocket 서버 엔드포인트
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat-app'
});

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

// 연결 성공했을 시 동작할 콜백 함수
stompClient.onConnect = (frame) => {
    console.log(frame);
    setConnected(true);

    // /topic/greetings : 구독
    stompClient.subscribe('/topic/greetings', (greeting) => {
        console.log('/topic/greetings', greeting.body);
        showMessage(JSON.parse(greeting.body).name + '님이 입장했습니다.');
    });

    stompClient.subscribe('/topic/chat', (chat) => {
        console.log('/topic/chat', chat.body);
        const message = JSON.parse(chat.body);
        showMessage(`${message.name}: ${message.content}`);
    });

    const name = document.getElementById('name').value;

    // 연결한 다음 /app/hello로 입장메시지 발행
    stompClient.publish({
        destination: '/app/hello',
        body: JSON.stringify({ name })
    });
};

// 상태값
function setConnected(connected) {
    const connectBtn = document.getElementById('connect');
    const disconnectBtn = document.getElementById('disconnect');
    const messages = document.getElementById('chat-messages');

    connectBtn.disabled = connected;
    disconnectBtn.disabled = !connected;
    messages.innerHTML = '';
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log('Disconnected');
}

function sendMessage() {
    const name = document.getElementById('name').value;
    const content = document.getElementById('content').value;
    console.log({ name, content });

    stompClient.publish({
        destination: '/app/chat',
        body: JSON.stringify({ name, content })
    });
}

function showMessage(message) {
    const messages = document.getElementById('chat-messages');
    messages.innerHTML += '<tr><td>' + message + '</td></tr>';
}

window.addEventListener("DOMContentLoaded", (event) => {
    const forms = document.querySelectorAll('.form-inline');
    const connectBtn = document.getElementById('connect');
    const disconnectBtn = document.getElementById('disconnect');
    const sendBtn = document.getElementById('send');

    connectBtn.addEventListener('click', () => connect());
    disconnectBtn.addEventListener('click', () => disconnect());
    sendBtn.addEventListener('click', () => sendMessage());

    for (const form of forms) {
        console.log(form);
        form.addEventListener('submit', (e) => e.preventDefault());
    }
});