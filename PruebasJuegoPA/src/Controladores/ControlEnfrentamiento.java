/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controladores;

import GUI.Campo.Casilla;
import GUI.MaquetaTableroJuego;
import Tarjetas.Mazo;
import Tarjetas.Tarjeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import java.awt.Component;

/**
 *
 * @author Marti
 */
public class ControlEnfrentamiento implements ActionListener, MouseListener{
    
    private Mazo mazoJugador;
    private ArrayList<Tarjeta> mano;
    
    private Tarjeta[] campoEnemigo, campoAliado;
    
    private static MaquetaTableroJuego mtj;
    
    private int cartaSeleccionada;

    public ControlEnfrentamiento() {
        
        this.mazoJugador = new Mazo();
        this.campoEnemigo = new Tarjeta[5];
        this.campoAliado = new Tarjeta[5];
        this.mano = new ArrayList<Tarjeta>();
        this.mtj = new MaquetaTableroJuego();
        this.mtj.setVisible(true);
        this.mtj.pasarTurno.addActionListener(this);
        this.mtj.getMano1().addMouseListener(this);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mtj.pasarTurno){
            if (mano.size() < 10){
                Tarjeta t = mazoJugador.getTarjeta();
                if (t != null){
                    mtj.anadirCarta(t.getId());
                    mano.add(t);
                     System.out.println("Id carta en Control: "+t.getId());
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().getClass() == Casilla.class){
            Component[] componentes = mtj.getMano1().getComponents();
            for (int i = 0; i < componentes.length; i++){
                if(componentes[i] == e.getSource()){
                    cartaSeleccionada = i;
                }
            }
            System.out.println("Carta seleccionada: "+mano.get(cartaSeleccionada).getNombre());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }
    
}
