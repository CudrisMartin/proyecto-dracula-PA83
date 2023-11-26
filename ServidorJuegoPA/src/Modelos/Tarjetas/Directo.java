/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tarjetas;

/**
 *
 * @author Marti
 * 
 * Las cartas tipo Directo atacan directamente al jugador, ignorando las cartas que tienen
 * justo al frente.
 */
public class Directo extends Tarjeta{
    
    /* Establece las caracteristicas de las cartas dependiendo del indice de esta*/
    public Directo(char i){
        super(i);
        
        this.tipoAtaque = 4;
    }
}
