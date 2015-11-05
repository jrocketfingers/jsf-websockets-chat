/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author johnny
 */
@ManagedBean
@ApplicationScoped
public class ServerData {
    private String messages;
    /**
     * Creates a new instance of ServerData
     */
    public ServerData() {
        messages = new String();
    }
    
}
