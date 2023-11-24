/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

/**
 *
 * @author Marti
 * 
 * Interfaz que permite obtener todos los metodos de las tarjetas de forma facil.
 */
public interface MetodosCartas {
    public void recibirDaño(int at);

    public String getNombre();

    public int getValorSalud();

    public int getValorAtaque();

    public int getValorDefensa();

    public int getValorMagia();

    public int getTipoAtaque();
}
