<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>STOMP 채팅방</title>
    <style>
        :root {
            color-scheme: light;
            --border: #d6dbe5;
            --bg: #f7f8fb;
            --panel: #ffffff;
            --text: #18212f;
            --muted: #667085;
            --accent: #1f6feb;
        }
        * { box-sizing: border-box; }
        body {
            margin: 0;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
            color: var(--text);
            background: var(--bg);
        }
        .app {
            display: grid;
            grid-template-columns: 280px 1fr;
            min-height: 100vh;
        }
        .sidebar, .main {
            padding: 16px;
        }
        .sidebar {
            border-right: 1px solid var(--border);
            background: var(--panel);
        }
        .main {
            display: grid;
            grid-template-rows: auto 1fr auto;
            gap: 12px;
        }
        .row {
            display: flex;
            gap: 8px;
        }
        input, button, textarea {
            font: inherit;
        }
        input, textarea {
            width: 100%;
            border: 1px solid var(--border);
            border-radius: 6px;
            padding: 10px 12px;
            background: #fff;
        }
        button {
            border: 1px solid var(--border);
            border-radius: 6px;
            padding: 10px 12px;
            background: #fff;
            cursor: pointer;
        }
        button.primary {
            background: var(--accent);
            color: #fff;
            border-color: var(--accent);
        }
        button.block {
            width: 100%;
            text-align: left;
        }
        .panel {
            border: 1px solid var(--border);
            border-radius: 8px;
            background: var(--panel);
        }
        .panel-header {
            padding: 12px 14px;
            border-bottom: 1px solid var(--border);
            font-weight: 600;
        }
        .panel-body {
            padding: 12px 14px;
        }
        .room-list {
            display: grid;
            gap: 8px;
            max-height: calc(100vh - 180px);
            overflow: auto;
        }
        .room-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 8px;
            padding: 10px 12px;
            border: 1px solid var(--border);
            border-radius: 6px;
            background: #fff;
        }
        .room-item.active {
            border-color: var(--accent);
            background: #eef4ff;
        }
        .room-name {
            font-weight: 600;
        }
        .meta, .hint {
            color: var(--muted);
            font-size: 13px;
        }
        .chat-log {
            padding: 12px;
            border: 1px solid var(--border);
            border-radius: 8px;
            background: #fff;
            overflow: auto;
            min-height: 360px;
            display: grid;
            gap: 8px;
        }
        .message {
            padding: 8px 10px;
            border-radius: 6px;
            background: #f4f6fa;
        }
        .message.system {
            background: #eef4ff;
        }
        .message strong {
            display: block;
            margin-bottom: 2px;
        }
        .chat-tools {
            display: grid;
            gap: 8px;
        }
        .status {
            min-height: 20px;
            color: var(--muted);
            font-size: 13px;
        }
        @media (max-width: 900px) {
            .app {
                grid-template-columns: 1fr;
            }
            .sidebar {
                border-right: 0;
                border-bottom: 1px solid var(--border);
            }
        }
    </style>
</head>
<body>
<script>
    window.APP_CONTEXT = '${pageContext.request.contextPath}';
</script>
<div class="app">
    <aside class="sidebar">
        <div class="panel">
            <div class="panel-header">대화방 개설</div>
            <div class="panel-body">
                <div class="row">
                    <input id="roomName" type="text" placeholder="대화방 이름">
                    <button id="createRoomBtn" class="primary">생성</button>
                </div>
            </div>
        </div>
        <div style="height:12px"></div>
        <div class="panel">
            <div class="panel-header">대화방 목록</div>
            <div class="panel-body">
                <div id="roomList" class="room-list"></div>
            </div>
        </div>
    </aside>
    <main class="main">
        <div class="panel">
            <div class="panel-header">현재 대화방</div>
            <div class="panel-body">
                <div id="currentRoom" class="meta">선택된 대화방이 없습니다.</div>
                <div class="status" id="status"></div>
            </div>
        </div>
        <section id="chatSection" class="chat-log" aria-live="polite"></section>
        <div class="chat-tools">
            <div class="row">
                <input id="sender" type="text" placeholder="닉네임">
                <button id="leaveBtn">나가기</button>
            </div>
            <div class="row">
                <input id="messageInput" type="text" placeholder="메시지를 입력하고 Enter">
                <button id="sendBtn" class="primary">전송</button>
            </div>
        </div>
    </main>
</div>

<script src="<c:url value='/resources/js/chat.js'/>"></script>
</body>
</html>
