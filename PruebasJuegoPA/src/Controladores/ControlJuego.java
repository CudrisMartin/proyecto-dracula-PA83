/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controladores;

import GUI.Campo.Casilla;
import GUI.TableroJuego;
import Modelos.Tarjetas.Mazo;
import Modelos.Tarjetas.Tarjeta;
import Modelos.Turno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import java.awt.Component;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Marti
 * 
 * Clase que controla todo el flujo del enfrentamiento, donde se deben tomar
 * cartas del mazo, ubicarlas en el tablero y avisar para cambio de turno.
 */
public class ControlJuego implements ActionListener, MouseListener{
    
    ControlGeneral ctrlGen;
    
    private Mazo mazoJugador;
    private ArrayList<Tarjeta> mano;
    private Tarjeta[] campoMaquina, campoJugador;
    
    private static TableroJuego vista;
    
    private boolean hayCarta;
    private boolean permitirClickear;
    private int cartaSeleccionada;
    
    private int vidaJugador, vidaMaquina;

    public ControlJuego(ControlGeneral ctrlGen) {
        
        this.ctrlGen = ctrlGen;
        
        //Genera objetos necesarios para el transcurso de la partida
        this.mazoJugador = new Mazo();
        this.campoMaquina = new Tarjeta[5];
        this.campoJugador = new Tarjeta[5];
        this.mano = new ArrayList<Tarjeta>();
        this.vista = new TableroJuego();
        /*
            Añade ActionListener y MouseListener a los objetos de los que se
            necesita obtener información sobre clicks y acciones.
        */
        this.vista.getBotonPasar().addActionListener(this);
        this.vista.getMano().addMouseListener(this);
        this.vista.getCampoAliado().addMouseListener(this);
        this.vista.getCampoAliado().setListeners();
        this.vista.getCampoEnemigo().addMouseListener(this);
        this.vista.getCampoEnemigo().setListeners();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
    
    public void actualizarTablero(Turno t){
        this.campoJugador = t.getCampoJugador();
        this.campoMaquina = t.getCampoMaquina();
        this.mano = t.getMano();
        this.vidaJugador = t.getVidaJugador();
        this.vidaMaquina = t.getVidaMaquina();
        
        vista.lbVidaJugador.setText(Integer.toString(this.vidaJugador));
        vista.lbVidaMaquina.setText(Integer.toString(this.vidaMaquina));
        
        for (int i = 0; i < campoJugador.length; i++){
            if (campoJugador[i] != null){
                ((Casilla)vista.getCampoAliado().getComponents()[i]).actualizarSprite(campoJugador[i].getId());
            }else{
                ((Casilla)vista.getCampoAliado().getComponents()[i]).actualizarSprite(0);
            }
        }
        
        for (int i = 0; i < campoMaquina.length; i++){
            if (campoMaquina[i] != null){
                ((Casilla)vista.getCampoEnemigo().getComponents()[i]).actualizarSprite(campoMaquina[i].getId());
            }else{
                ((Casilla)vista.getCampoEnemigo().getComponents()[i]).actualizarSprite(0);
            }
        }
        vista.getMano().removeAll();
        
        for (Tarjeta mano1 : mano) {
            vista.getMano().anadirTarjeta(mano1.getId());
        }
        if (t.getGanador() == 0){
            permitirClickear = true;
            vista.getBotonPasar().setEnabled(true);
        }else{
            if (t.getGanador() == 1){
                JOptionPane.showMessageDialog(vista, "Ganaste");
            } else{
                JOptionPane.showMessageDialog(vista, "Gana la maquina");
            }
        }
    }
    
    public void enviarTurno(){
        Turno t = new Turno(campoMaquina,
                            campoJugador,
                            mano,
                            vidaJugador,
                            vidaMaquina,
                            0
                            );
        permitirClickear = false;
        ctrlGen.enviarTurno(t);
    }
    
    /* Detecta acciones, principalmente botones presionados*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBotonPasar()){
            
            vista.getBotonPasar().setEnabled(false);
            enviarTurno();
        }
    }

    /* Detecta Clicks del mouse dentro de diferentes elementos de la GUI*/
    @Override
    public void mouseClicked(MouseEvent e) {
        if(permitirClickear){
            //¿Es la fuente del evento una Casilla?
            if(e.getSource().getClass() == Casilla.class){

                //¿Que tipo de casilla es la que detecta el evento?
                switch(((Casilla)e.getSource()).getTipoCasilla()) {

                    case 0 -> { //Casilla de Campo

                        //Ubica la carta seleccionada en la casilla
                        //correspondiente del mazo
                        if (hayCarta){
                            Tarjeta t = mano.get(cartaSeleccionada);
                            Casilla casOrigen = (Casilla)e.getSource();
                            int ubCarta = 0;

                            Component[] comps = vista.getCampoAliado().getComponents();

                            for (int i = 0; i < comps.length; i++) {
                                if (comps[i].equals(e.getSource())){
                                    ubCarta = i;
                                }
                            }

                            if (campoJugador[ubCarta] == null){
                                casOrigen.actualizarSprite(t.getId());
                                campoJugador[ubCarta] = t;
                                mano.remove(cartaSeleccionada);
                                vista.eliminarCarta(cartaSeleccionada);
                                hayCarta = false;
                            }
                            break;
                        }
                    }
                    case 1 -> { //Casilla de Mano

                        //Guarda referencia a la ultima carta en mano clickeada
                        Component[] componentes = vista.getMano().getComponents();
                        for (int i = 0; i < componentes.length; i++){
                            hayCarta = true;
                            if(componentes[i] == e.getSource()){
                                cartaSeleccionada = i;
                            }
                        }
                        break;
                    }

                }
            }
        }
    }
    
    public void mostrarVista(){
        this.vista.setVisible(true);
    }
    
    public void ocultarVista(){
        this.vista.setVisible(false);
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
