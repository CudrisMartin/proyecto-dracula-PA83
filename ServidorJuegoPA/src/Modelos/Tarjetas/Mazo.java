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
    
    private char[] guiaTarjetas= {'A','B','C','D','E',
                                  'F','G','H','I','J',
                                  'K','L','M','N','O',
                                  'P','Q','R','S','T'};
    
    private Random rng = new Random();
    
    public Mazo(String mazoJugador){
        for (int i = 0; i < mazoJugador.length(); i++){
            contenidoMazo.add(crearTarjeta(mazoJugador.charAt(i)));
        }
       tamanoMazo = contenidoMazo.size();
    }
    
    public Mazo(){
        
        tamanoMazo = rng.nextInt(10, 20);
        
        do{
            int idCarta = rng.nextInt(1,20);
            switch (idCarta){
                case 2, 17 -> {
                    contenidoMazo.add(new CampoCompleto(guiaTarjetas[idCarta-1]));
                    break;
                }
                case 7, 8 -> {
                    contenidoMazo.add(new TripleLinea(guiaTarjetas[idCarta-1]));
                    break;
                }
                case 13,18 -> {
                    contenidoMazo.add(new Directo(guiaTarjetas[idCarta-1]));
                    break;
                }
                default -> {
                    contenidoMazo.add(new DeFrente(guiaTarjetas[idCarta-1]));
                    break;
                }
            }
        }while (contenidoMazo.size() < tamanoMazo);
    }
    
    private Tarjeta crearTarjeta(char i){
        Tarjeta t = null;
        
        if (i == 'G' || i == 'H'){
            t = new TripleLinea(i);
        }else if (i == 'B' || i == 'Q'){
            t = new CampoCompleto(i);
        }else if (i == 'M' || i == 'R'){
            t = new Directo(i);
        }else{
            t = new DeFrente(i);
        }
        
        return t;
    }
    
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
