    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tarjetas;

/**
 *
 * @author Marti
 * 
 * Clase abstracta de la que derivan todos los tipós de cartas
 */
public abstract class Tarjeta implements Interfaces.MetodosCartas {
    
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
        valorSalud -= (int)at/valorDefensa;
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
