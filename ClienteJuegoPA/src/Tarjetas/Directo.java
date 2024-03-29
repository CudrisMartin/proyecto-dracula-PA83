/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarjetas;

/**
 *
 * @author Marti
 * 
 * Las cartas tipo Directo atacan directamente al jugador, ignorando las cartas que tienen
 * justo al frente.
 */
public class Directo extends Tarjeta{
    
    /* Establece las caracteristicas de las cartas dependiendo del indice de esta*/
    public Directo(int i){
        this.tipoAtaque = 4;
        
        switch (i){
            case 13 ->{
                this.id = 13;
                this.nombre = "Invisible";
                this.valorAtaque = 5;
                this.valorDefensa = 25;
                this.valorMagia = 40;
                this.valorSalud = 30;
                break;
            }
            case 18 ->{
                this.id = 18;
                this.nombre = "Fantasmario";
                this.valorAtaque = 10;
                this.valorDefensa = 35;
                this.valorMagia = 20;
                this.valorSalud = 35;
                break;
            }
        }
    }
}
