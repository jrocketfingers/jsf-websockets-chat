var chatsocket = new WebSocket("ws://localhost:8080/jsf-websockets-chat/chat");

function sendMessage() {
    message = document.getElementById("form:message").value;
    chatsocket.send(message);
}

chatsocket.onmessage = function(event) {
    var split = event.data.indexOf(":");
    var command = event.data.slice(0, split);
    var message = event.data.slice(split + 1);
    
    if(command == "message") {
        document.getElementById("form:chat").value += message + '\n';
    } else if(command == "users") {
        document.getElementById("users").innerHTML = message;
    }
};

window.onbeforeunload = function() {
    chatsocket.close();
};