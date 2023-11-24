/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tarjetas;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Marti
 * 
 * El Mazo contiene todas las referencias al mazo del jugador, permite tomar
 * cartas así como tener un registro de
 * cuantas queda.
 */
public class Mazo {
    
    private ArrayList<Tarjeta> contenidoMazo;
    private int tamanoMazo;
    
    private Random rng = new Random();
    
    /* Toma una tarjeta del mazo pseudoaleatoriamente */
    public Tarjeta getTarjeta(){
        Tarjeta t = null;
        if (tamanoMazo > 0){
            int tarjetaSel = rng.nextInt(0, contenidoMazo.size());
            t = contenidoMazo.get(tarjetaSel);
            this.contenidoMazo.remove(tarjetaSel);
            this.tamanoMazo = contenidoMazo.size();
        }
        
        return t;
    }
}
