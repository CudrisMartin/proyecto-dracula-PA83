/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarjetas;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Marti
 */
public class Mazo {
    
    private ArrayList<Tarjeta> contenidoMazo;
    private int tamanoMazo;
    
    private Random rng = new Random();
    
    public Mazo(){
        
        this.contenidoMazo = new ArrayList<Tarjeta>();
        this.tamanoMazo = rng.nextInt(5, 10);
        
        for (int i = 1; i <= tamanoMazo; i++){
            Tarjeta t;
            int nTarjeta = rng.nextInt(1, 20);
            switch(nTarjeta){
                case 2:{
                    t = new CampoCompleto(nTarjeta);
                    break;
                }
                case 7:{
                    t = new TripleLinea(nTarjeta);
                    break;
                }
                case 8:{
                    t = new TripleLinea(nTarjeta);
                    break;
                }
                case 13:{
                    t = new Directo(nTarjeta);
                    break;
                }
                case 17:{
                    t = new CampoCompleto(nTarjeta);
                    break;
                }
                case 18:{
                    t = new Directo(nTarjeta);
                    break;
                }
                default:
                    t = new DeFrente(nTarjeta);
                    break;
            }
            contenidoMazo.add(t);
        }
    }
    
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
