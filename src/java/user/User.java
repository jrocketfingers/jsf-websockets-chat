/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author johnny
 */
@ManagedBean(name="user")
@SessionScoped
public class User {

    private String password;

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    private String nickname;

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setNickname(String username) {
        this.nickname = username;
    }

    /**
     * Creates a new instance of User
     */
    public User() {
    }
    
}