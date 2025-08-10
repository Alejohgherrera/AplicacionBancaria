/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco.modelos;

/**
 *
 * @author ad
 */
public class Usuario {
    private String login; 
    private String password;
    
     public Usuario(String login, String password) {
        this.login = login;      
        this.password = password; 
        
    }
     
      public String getLogin() {
        return login; 
    }

    /**
     
     */
    public String getPassword() {
        return password; 
    }
    
}

