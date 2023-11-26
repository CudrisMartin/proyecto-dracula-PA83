/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controladores;

import Modelos.Tarjetas.Mazo;
import Modelos.Tarjetas.Tarjeta;
import Modelos.Turno;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Marti
 * 
 * Clase que controla todo el flujo del enfrentamiento, donde se deben tomar
 * cartas del mazo, ubicarlas en el tablero y avisar para cambio de turno.
 */
public class ControlJuego{
    
    private Mazo mazoJugador, mazoEnemigo;
    private ArrayList<Tarjeta> mano;
    private Tarjeta[] campoMaquina, campoJugador;
    private int vidaJugador, vidaMaquina;
    
    
    /*
        Constructor llamado para el primer turno del juego.
    */
    public ControlJuego(String mazoJugador) {
        
        
        
        //Genera objetos necesarios para el transcurso de la partida
        this.mazoJugador = mazoJugador;
        this.mazoEnemigo = new Mazo();
        this.campoMaquina = new Tarjeta[5];
        this.campoJugador = new Tarjeta[5];
        this.mano = new ArrayList<Tarjeta>();
        
        this.vidaJugador = 50;
        this.vidaMaquina = 50;
    }
    
    public Turno generarTurno(Tarjeta[] campoMaquina, Tarjeta[] campoJugador, ArrayList<Tarjeta> mano, Mazo mazoJugador){
        this.campoMaquina = campoMaquina;
        this.campoJugador = campoJugador;
        this.mano = mano;
        this.mazoJugador = mazoJugador;
        
        generarDano(this.campoJugador, this.campoMaquina);
        generarDano(this.campoMaquina, this.campoJugador);

        colocarCartaMaquina();

        darCartaAJugador();
        
        Turno t = new Turno(this.campoMaquina, this.campoJugador, this.mazoJugador, this.mano, this.vidaJugador, this.vidaMaquina);
        return t;
    }
    
    public Turno primerTurno(){
        colocarCartaMaquina();
        Turno t = new Turno(this.campoMaquina, this.campoJugador, this.mazoJugador, this.mano, this.vidaJugador, this.vidaMaquina);
        return t;
    }
    
    public void generarDano(Tarjeta[] atacantes, Tarjeta[] objetivos){
        int danoTotal = 0;
        for (int i = 0; i < atacantes.length; i++){
            if (atacantes[i] != null){
                switch (atacantes[i].getTipoAtaque()){
                    case 1 -> {
                        if(objetivos[i] == null){
                            danoTotal += 1;
                        }
                        else{
                            objetivos[i].recibirDaño(atacantes[i].getValorAtaque());
                            if (objetivos[i].getValorSalud() == 0){
                                eliminarCarta(atacantes, objetivos, i);
                            }
                        }
                        break;
                    }
                    case 2 -> {
                        switch (i){
                            case 0 -> {
                                for (int j = 0; j < 2; j++){
                                    if(objetivos[j] == null){
                                        danoTotal += 1;
                                    }
                                    else{
                                        objetivos[j].recibirDaño(atacantes[i].getValorAtaque());
                                        if (objetivos[j].getValorSalud() == 0){
                                            eliminarCarta(atacantes, objetivos, j);
                                        }
                                    }
                                }
                                break;
                            }
                            case 4 -> {
                                for (int j = 3; j < 5; j++){
                                    if(objetivos[j] == null){
                                        danoTotal += 1;
                                    }
                                    else{
                                        objetivos[j].recibirDaño(atacantes[i].getValorAtaque());
                                        if (objetivos[j].getValorSalud() == 0){
                                            eliminarCarta(atacantes, objetivos, j);
                                        }
                                    }
                                }
                            }
                            default -> {
                                for (int j = i-1; j < i+2; j++){
                                    if(objetivos[j] == null){
                                        danoTotal += 1;
                                    }
                                    else{
                                        objetivos[j].recibirDaño(atacantes[i].getValorAtaque());
                                        if (objetivos[j].getValorSalud() == 0){
                                            eliminarCarta(atacantes, objetivos, j);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 3 -> {
                        for (int j = 0; j < 5; j++){
                            if(objetivos[j] == null){
                                danoTotal += 1;
                            }
                            else{
                                objetivos[j].recibirDaño(atacantes[i].getValorAtaque());
                                if (objetivos[j].getValorSalud() == 0){
                                    eliminarCarta(atacantes, objetivos, j);
                                }
                            }
                        }
                        break;
                    }
                    case 4 -> {
                        danoTotal += 2;
                        break;
                    }
                }
            }
        }
        if (atacantes == campoJugador){
            vidaMaquina -= danoTotal;
            if (vidaMaquina < 0){
                vidaMaquina = 0;
            }
            System.out.println("Enemigo sufre "+danoTotal+" puntos de daño, queda con "+vidaMaquina+" puntos de vida.");
        }else{
            vidaJugador -= danoTotal;
            if (vidaJugador < 0){
                vidaJugador = 0;
            }
            System.out.println("Jugador sufre "+danoTotal+" puntos de daño, queda con "+vidaJugador+" puntos de vida.");
        }
    }
    
    private void eliminarCarta(Tarjeta[] atacantes, Tarjeta[] objetivos, int i){
        System.out.println(objetivos[i].getNombre()+" muere.");
        objetivos[i] = null;
    }
    
    private void colocarCartaMaquina(){
        int tarEnCampo = 0;
        
        for (Tarjeta campoEnemigo1 : campoMaquina){
            if (campoEnemigo1 != null){
                tarEnCampo++;
            }
        }
        
        if (tarEnCampo < 5){
            Tarjeta t = mazoEnemigo.getTarjeta();
            int posTar;

            do{
                Random rng = new Random();
                posTar = rng.nextInt(0, 5);
            }while(campoMaquina[posTar] != null);

            if (t != null){
                campoMaquina[posTar] = t;
            }
        }
    }
    
    private void darCartaAJugador(){
        if (mano.size() < 10){
            Tarjeta t = mazoJugador.getTarjeta();
            if (t != null){
                mano.add(t);
            }
        }
    }

    public int getVidaJugador() {
        return vidaJugador;
    }

    public int getVidaMaquina() {
        return vidaMaquina;
    }
    
    
}
