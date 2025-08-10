/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco.modelos;

import java.util.ArrayList;

public class CuentaBancaria {
    private String numeroCuenta; 
    private double saldo;        
    private Usuario propietario; 
    private ArrayList<String> historialTransacciones;
    
public CuentaBancaria(String numeroCuenta, double saldoInicial, Usuario propietario) {
    this.numeroCuenta = numeroCuenta;
    this.saldo = saldoInicial;
    this.propietario = propietario;

    this.historialTransacciones = new ArrayList<>();
    }
public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public ArrayList<String> getHistorialTransacciones() {
        return historialTransacciones;
    }
    public boolean consignar(double monto) {
        if (monto > 0) { 
            this.saldo += monto; 
            
            historialTransacciones.add("Consignación: +" + monto + " (Saldo actual: " + saldo + ")");
            return true; 
        } else {
            return false; 
        }
    }
     public boolean retirar(double monto) {
        if (monto > 0 && monto <= this.saldo) { 
            this.saldo -= monto; 
            
            historialTransacciones.add("Retiro:      -" + monto + " (Saldo actual: " + saldo + ")");
            return true; 
        } else {
            return false; 
        }
    }
}
