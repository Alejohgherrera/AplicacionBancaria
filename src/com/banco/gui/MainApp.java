/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco.gui;

import com.banco.modelos.Banco; 
import com.banco.modelos.CuentaBancaria;
import com.banco.modelos.Usuario;

public class MainApp {
    public static void main(String[] args) {
        
        Banco miBanco = new Banco();

        
        
        if (miBanco.getUsuarios().isEmpty()) {
            System.out.println("Creando usuarios y cuentas de prueba para la primera ejecución de la GUI...");
            Usuario admin = new Usuario("admin", "1234");
            Usuario juan = new Usuario("juanperez", "passjuan");
            miBanco.agregarUsuario(admin);
            miBanco.agregarUsuario(juan);
            miBanco.agregarCuenta(new CuentaBancaria("C001", 1000.0, admin));
            miBanco.agregarCuenta(new CuentaBancaria("C002", 500.0, juan));
            miBanco.guardarUsuarios(); 
            miBanco.guardarCuentas();  
            System.out.println("Usuarios y cuentas de prueba creados y guardados.");
        }


        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                LoginFrame login = new LoginFrame(miBanco);
                login.setVisible(true);
            }
        });
    }
    
}
