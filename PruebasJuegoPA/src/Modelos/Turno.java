/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Modelos.Tarjetas.Mazo;
import Modelos.Tarjetas.Tarjeta;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Marti
 */
public class Turno implements Serializable {
    private static final long serialVersionUID = 12L;
    
    private Tarjeta[] campoMaquina, campoJugador;
    private Mazo mazoJugador;
    private ArrayList<Tarjeta> mano;
    private int vidaJugador, vidaMaquina;

    public Turno(Tarjeta[] campoMaquina, Tarjeta[] campoJugador,
                 Mazo mazoJugador,
                 ArrayList<Tarjeta> mano, int vidaJugador, int vidaMaquina) {
        this.campoMaquina = campoMaquina;
        this.campoJugador = campoJugador;
        this.mazoJugador = mazoJugador;
        this.mano = mano;
        this.vidaJugador = vidaJugador;
        this.vidaMaquina = vidaMaquina;
    }

    public Tarjeta[] getCampoMaquina() {
        return campoMaquina;
    }

    public Tarjeta[] getCampoJugador() {
        return campoJugador;
    }

    public Mazo getMazoJugador() {
        return mazoJugador;
    }

    public ArrayList<Tarjeta> getMano() {
        return mano;
    }

    public int getVidaJugador() {
        return vidaJugador;
    }

    public int getVidaMaquina() {
        return vidaMaquina;
    }
    
    
}
