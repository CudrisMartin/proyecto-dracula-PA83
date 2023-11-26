package Modelos.Tarjetas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Marti
 * 
 * las tarjetas CampoCompleto atacan a todas las casillas del campo enemigo.
 */
public class CampoCompleto extends Tarjeta {
    
    /* Establece las caracteristicas de las cartas dependiendo del indice de esta*/
    public CampoCompleto(char i){
        super(i);
        
        this.tipoAtaque = 3;
    }
}
