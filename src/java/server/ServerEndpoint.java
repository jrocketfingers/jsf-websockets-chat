/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import server.SocketConfigurator;
import user.User;
/**
 *
 * @author johnny
 */
@javax.websocket.server.ServerEndpoint(value = "/chat",
                                       configurator = SocketConfigurator.class)
public class ServerEndpoint {
    private RemoteEndpoint.Basic client;
    
    private HttpSession httpSession;
    private User user;
    private ServerData server;
    
    @OnOpen
    public String onOpen(Session s, EndpointConfig config) {
        client = s.getBasicRemote();
        
        httpSession = (HttpSession) config.getUserProperties().get("session");
        user = (User) httpSession.getAttribute("user");
        user.setSocket(client);
        
	// Add the user - password combination to the server
        server = (ServerData) httpSession.getServletContext().getAttribute("server_data");
        server.getUsers().put(user, user.getPassword());
        
        sendMessageToAll(user.getNickname() + " has joined the channel");
        
        sendUserListToAll();
        
        return "Connected!";
    }
    
    @OnClose
    public void onClose(Session s) {        
	server.getUsers().remove(user);
        sendMessageToAll(user.getNickname() + " has left");
    }
    
    @OnMessage
    public void onMessage(String message) {
        sendMessageToAll(user.getNickname() + ": " + message);
    }
    
    private void sendUserListToAll() {
        String userlist = "users:";
        for(User user : server.getUsers().keySet()) {
            userlist += user.getNickname() + "\n";
        }

        sendToAll(userlist);
    }
    
    private void sendToAll(String message) {
        for(User user : server.getUsers().keySet()) {
            try {
                user.getSocket().sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(ServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void sendMessageToAll(String message) {
        sendToAll("message:" + message);
    }
    
}
