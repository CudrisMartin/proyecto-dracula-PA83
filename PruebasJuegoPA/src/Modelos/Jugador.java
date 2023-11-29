/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.io.Serializable;

/**
 *
 * @author Marti
 */
public class Jugador implements Serializable{
    private static final long serialVersionUID = 12L;
    
    private int idJugador, partidasGanadas;
    private String nombre, contraseña, mazo;

    public Jugador(int idJugador, int partidasGanadas, String nombre, String contraseña, String mazo) {
        this.idJugador = idJugador;
        this.partidasGanadas = partidasGanadas;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.mazo = mazo;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getMazo() {
        return mazo;
    }
    
    
    
}
