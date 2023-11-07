    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarjetas;

/**
 *
 * @author Marti
 */
public abstract class BaseTarjeta implements Interfaces.MetodosCartas {
    
    protected int id;
    protected String nombre;
    protected int valorSalud;
    protected int valorAtaque;
    protected int valorDefensa;
    protected int valorMagia;
    protected int tipoAtaque;
    
    
    @Override
    public void recibirDaño(int at){
        valorSalud -= (int)at/valorDefensa;
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
