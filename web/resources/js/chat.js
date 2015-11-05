var chatsocket = new WebSocket("ws://localhost:8080/ChatApplication/chat");

function sendMessage() {
    message = document.getElementById("form:message").value
    chatsocket.send(message);
}

chatsocket.onmessage = function(event) {
    document.getElementById("form:chat").value += event.data + '\n';
}

window.onbeforeunload = function() {
    chatsocket.close();
}