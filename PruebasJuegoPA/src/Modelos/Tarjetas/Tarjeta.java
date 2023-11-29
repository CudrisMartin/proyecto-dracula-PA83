    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tarjetas;

import java.io.Serializable;

/**
 *
 * @author Marti
 * 
 * Clase abstracta de la que derivan todos los tipós de cartas
 */
public abstract class Tarjeta implements Interfaces.MetodosCartas, Serializable{
    private static final long serialVersionUID = 12L;
        
    protected int id;
    protected String nombre;
    protected int valorSalud;
    protected int valorAtaque;
    protected int valorDefensa;
    protected int valorMagia;
    protected int tipoAtaque;
    
    /* Reduce la vida de la carta en momento de juego*/
    @Override
    public void recibirDaño(int at){
        valorSalud -= (int)2*at/valorDefensa;
        if (valorSalud < 0){
            valorSalud = 0;
        }
        //System.out.println(nombre+" recibe "+at+" puntos de ataque y queda con "+valorSalud+" puntos de salud.");
    }
    
    public int getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getValorSalud() {
        return valorSalud;
    }

    @Override
    public int getValorAtaque() {
        return valorAtaque;
    }

    @Override
    public int getValorDefensa() {
        return valorDefensa;
    }

    @Override
    public int getValorMagia() {
        return valorMagia;
    }

    @Override
    public int getTipoAtaque() {
        return tipoAtaque;
    }
    
    
    
    
}
