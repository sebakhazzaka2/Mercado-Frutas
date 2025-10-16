
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;

public class Movimiento implements Serializable{
    private int numeroMovimiento;
    private static int contadorMovimiento = 1;
    
    
    public Movimiento() {
        numeroMovimiento = contadorMovimiento++;
    }
    
    public int getNumeroMovimiento() {
        return numeroMovimiento;
    }
}
