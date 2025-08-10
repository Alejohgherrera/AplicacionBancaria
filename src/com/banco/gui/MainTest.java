/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco.gui;

import com.banco.modelos.Banco; 
import com.banco.modelos.CuentaBancaria; 
import com.banco.modelos.Usuario; 
import java.util.ArrayList; 

public class MainTest {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA LOGICA DEL BANCO ---");

       
        Banco miBanco = new Banco();
        System.out.println("\nBanco inicializado. Intentando cargar datos...");

        
        if (miBanco.getUsuarios().isEmpty()) {
            System.out.println("No se encontraron usuarios existentes. Creando usuarios de prueba...");
            Usuario admin = new Usuario("admin", "1234");
            Usuario juan = new Usuario("juanperez", "passjuan");
            miBanco.agregarUsuario(admin);
            miBanco.agregarUsuario(juan);
            System.out.println("Usuarios de prueba creados y guardados.");
        } else {
            System.out.println("Usuarios existentes cargados:");
            for (Usuario u : miBanco.getUsuarios()) {
                System.out.println(" - Login: " + u.getLogin() + ", Password: " + u.getPassword());
            }
        }

       
        if (miBanco.getCuentas().isEmpty()) {
            System.out.println("\nNo se encontraron cuentas existentes. Creando cuentas de prueba...");
            Usuario adminUser = miBanco.buscarUsuario("admin", "1234");
            Usuario juanUser = miBanco.buscarUsuario("juanperez", "passjuan");

            if (adminUser != null) {
                miBanco.agregarCuenta(new CuentaBancaria("C001", 1000.0, adminUser));
                System.out.println("Cuenta C001 para admin creada.");
            }
            if (juanUser != null) {
                miBanco.agregarCuenta(new CuentaBancaria("C002", 500.0, juanUser));
                System.out.println("Cuenta C002 para juanperez creada.");
            }
            System.out.println("Cuentas de prueba creadas y guardadas.");
        } else {
            System.out.println("\nCuentas existentes cargadas:");
            for (CuentaBancaria c : miBanco.getCuentas()) {
                System.out.println(" - No. Cuenta: " + c.getNumeroCuenta() + ", Saldo: " + c.getSaldo() +
                                   ", Propietario: " + c.getPropietario().getLogin());
                System.out.println("   Historial: " + c.getHistorialTransacciones());
            }
        }

        
        System.out.println("\n--- Probando Login ---");
        Usuario usuarioValido = miBanco.buscarUsuario("admin", "1234");
        if (usuarioValido != null) {
            System.out.println("Login exitoso para: " + usuarioValido.getLogin());
            
            CuentaBancaria cuentaAdmin = miBanco.buscarCuentaPorPropietario(usuarioValido);
            if (cuentaAdmin != null) {
                System.out.println("Cuenta de " + usuarioValido.getLogin() + ": " + cuentaAdmin.getNumeroCuenta() + ", Saldo: " + cuentaAdmin.getSaldo());
                
                System.out.println("\n--- Probando Transacciones ---");
                System.out.println("Saldo antes de consignar: " + cuentaAdmin.getSaldo());
                cuentaAdmin.consignar(200.0);
                System.out.println("Saldo después de consignar $200: " + cuentaAdmin.getSaldo());
                cuentaAdmin.retirar(50.0);
                System.out.println("Saldo después de retirar $50: " + cuentaAdmin.getSaldo());
                cuentaAdmin.retirar(2000.0); // Intento de retiro inválido
                System.out.println("Saldo después de intentar retirar $2000: " + cuentaAdmin.getSaldo() + " (debería ser el mismo)");

                System.out.println("Historial de Transacciones de " + cuentaAdmin.getNumeroCuenta() + ":");
                for(String transaccion : cuentaAdmin.getHistorialTransacciones()){
                    System.out.println(" - " + transaccion);
                }

                
                miBanco.guardarCuentas();
                System.out.println("Cuentas guardadas después de las transacciones.");

            } else {
                System.out.println("No se encontró cuenta para el usuario: " + usuarioValido.getLogin());
            }
        } else {
            System.out.println("Login fallido para 'admin', '1234'.");
        }

        Usuario usuarioInvalido = miBanco.buscarUsuario("usuarioinexistente", "pass");
        if (usuarioInvalido == null) {
            System.out.println("Login fallido para 'usuarioinexistente' (esperado).");
        }

        System.out.println("\n--- PRUEBAS COMPLETADAS ---");
    }
}
