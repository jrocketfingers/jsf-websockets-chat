var chatsocket = new WebSocket("ws://localhost:8080/ChatApplication/chat");

function sendMessage() {
    message = document.getElementById("form:message").value
    nickname = document.getElementById("form:nickname").value
    chatsocket.send(nickname + ": " + message);
    console.log("Socket state: " + chatsocket.readyState);
    console.log("Message: " + message);
}

chatsocket.onmessage = function(event) {
    console.log("Server responded with: " + event.data);
    document.getElementById("form:chat").value += event.data + '\n';
}

window.onbeforeunload = function() {
    console.log("onbeforeunload");
    chatsocket.close();
}