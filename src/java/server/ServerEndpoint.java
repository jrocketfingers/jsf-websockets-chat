/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.ArrayList;
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
    private static ArrayList<RemoteEndpoint.Basic> clients = new ArrayList();
    
    private RemoteEndpoint.Basic client;
    
    private HttpSession httpSession;
    private String nickname;
    
    @OnOpen
    public String onOpen(Session s, EndpointConfig config) {
        client = s.getBasicRemote();
        clients.add(client);
        
        httpSession = (HttpSession) config.getUserProperties().get("session");
        nickname = ((User) httpSession.getAttribute("user")).getNickname();
        
        return "Connected!";
    }
    
    @OnClose
    public void onClose(Session s) {
        clients.remove(client);
    }
    
    @OnMessage
    public void onMessage(String message) {
        for(RemoteEndpoint.Basic client: clients) {
            try {
                client.sendText(nickname + ": " + message);
            } catch (IOException ex) {
                Logger.getLogger(ServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
