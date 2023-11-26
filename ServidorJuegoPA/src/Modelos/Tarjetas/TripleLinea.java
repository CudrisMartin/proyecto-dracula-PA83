/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tarjetas;

/**
 *
 * @author Marti
 * 
 * Las tarjetas TripleLinea atacan no solo a la tarjeta al frente suyo, sino a las que tenga a sus costados
 */
public class TripleLinea extends Tarjeta {
    
    /* Establece las caracteristicas de las cartas dependiendo del indice de esta*/
    public TripleLinea(char i){
        super(i);
        
        this.tipoAtaque = 2;
    }
}
