/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco.modelos;

import java.io.BufferedReader; 
import java.io.FileReader;    
import java.io.FileWriter;    
import java.io.IOException;   
import java.io.PrintWriter;   
import java.util.ArrayList;

public class Banco {
    private ArrayList<Usuario> usuarios;      
    private ArrayList<CuentaBancaria> cuentas; 

   
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    private static final String ARCHIVO_CUENTAS = "cuentas.txt";

    /**
     * 
     */
    public Banco() {
        usuarios = new ArrayList<>();
        cuentas = new ArrayList<>();
        cargarUsuarios(); 
        cargarCuentas();  
    }
    
     private void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) { 
                String[] partes = linea.split(","); 
                if (partes.length == 2) { 
                    String login = partes[0].trim(); 
                    String password = partes[1].trim();
                    usuarios.add(new Usuario(login, password)); 
                }
            }
        } catch (IOException e) {
            
            System.out.println("No se pudo cargar el archivo de usuarios o no existe: " + e.getMessage());
            
        }
    }
     private void cargarCuentas() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CUENTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                
                String[] partesPrincipales = linea.split(",", 4); 

                if (partesPrincipales.length >= 3) { 
                    String numeroCuenta = partesPrincipales[0].trim();
                    double saldo = Double.parseDouble(partesPrincipales[1].trim()); 
                    String loginPropietario = partesPrincipales[2].trim();

                    
                    Usuario propietarioEncontrado = null;
                    for (Usuario u : usuarios) { 
                        if (u.getLogin().equals(loginPropietario)) { 
                            propietarioEncontrado = u; 
                            break; 
                        }
                    }

                    if (propietarioEncontrado != null) { 
                        CuentaBancaria cuenta = new CuentaBancaria(numeroCuenta, saldo, propietarioEncontrado);

                        
                        if (partesPrincipales.length == 4) { 
                            String historialStr = partesPrincipales[3].trim();
                            if (!historialStr.isEmpty()) { 
                              
                                String[] transaccionesArray = historialStr.split(";");
                                
                                for (String transaccion : transaccionesArray) {
                                    cuenta.getHistorialTransacciones().add(transaccion.trim());
                                }
                            }
                        }
                        cuentas.add(cuenta); 
                    } else {
                        System.out.println("Advertencia: No se encontró el propietario para la cuenta " + numeroCuenta);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de cuentas o no existe: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al leer el saldo en el archivo de cuentas: " + e.getMessage());
        }
    }
     public void guardarUsuarios() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_USUARIOS))) {
            for (Usuario u : usuarios) { 
                pw.println(u.getLogin() + "," + u.getPassword()); 
            }
            System.out.println("Usuarios guardados correctamente en " + ARCHIVO_USUARIOS);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios en el archivo: " + e.getMessage());
            e.printStackTrace(); /
        }
    }
     public void guardarCuentas() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_CUENTAS))) {
            for (CuentaBancaria c : cuentas) { 
                // 
                String historialStr = String.join(";", c.getHistorialTransacciones());

                // 
                pw.println(c.getNumeroCuenta() + "," +
                           c.getSaldo() + "," +
                           c.getPropietario().getLogin() + "," + 
                           historialStr);
            }
            System.out.println("Cuentas guardadas correctamente en " + ARCHIVO_CUENTAS);
        } catch (IOException e) {
            System.err.println("Error al guardar cuentas en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
 public Usuario buscarUsuario(String login, String password) {
        for (Usuario u : usuarios) { 
            
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                return u; 
            }
        }
        return null; 
    }

    /*
     * 
     */
    public CuentaBancaria buscarCuentaPorPropietario(Usuario usuario) {
        for (CuentaBancaria c : cuentas) { 
            
            if (c.getPropietario().equals(usuario)) {
                return c; 
            }
        }
        return null; 
    }

    //

    /**
     */
    public void agregarUsuario(Usuario nuevoUsuario) {
        
        usuarios.add(nuevoUsuario);
        guardarUsuarios(); 
    }

    /**
     
     */
    public void agregarCuenta(CuentaBancaria nuevaCuenta) {
        
        cuentas.add(nuevaCuenta);
        guardarCuentas(); 
    }
    
    public ArrayList<Usuario> getUsuarios() {
    return usuarios;
}

public ArrayList<CuentaBancaria> getCuentas() {
    return cuentas;
}
}
