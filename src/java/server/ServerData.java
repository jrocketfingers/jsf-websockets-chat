/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import user.User;

/**
 *
 * @author johnny
 */
@ManagedBean(name="server_data")
@ApplicationScoped
public class ServerData {
    private HashMap<String,String> nicknames = new HashMap();
    /**
     * Creates a new instance of ServerData
     */
    public ServerData() {

    }
    
    public String reserve() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        User user = (User) session.getAttribute("user");
        
        nicknames.put(user.getNickname(), user.getPassword());
        
        return "login";
    }
    
    public String login() {
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        User user = (User) sessionMap.get("user");
        
        String stored_password = nicknames.get(user.getNickname());
        
        if(user.getPassword() != null && stored_password.equals(user.getPassword())) {
            sessionMap.put("logged_in", "true");
            return "chat";
        }
        
        // TODO: Add the error text
        return "login";
    }
}
