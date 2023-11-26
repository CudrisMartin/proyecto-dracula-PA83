/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tarjetas;

/**
 *
 * @author Marti
 * 
 * Las cartas DeFrente atacan unicamente a la unidad o casilla que tienen frente suyo en el campo de batalla.
 * 
 */
public class DeFrente extends Tarjeta{
    
    /* Establece las caracteristicas de las cartas dependiendo del indice de esta*/
    public DeFrente(char i){
        super(i);
        
        this.tipoAtaque = 1;
    }
}
