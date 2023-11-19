/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controladores;

import GUI.Campo.Casilla;
import GUI.TableroJuego;
import Tarjetas.Mazo;
import Tarjetas.Tarjeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import java.awt.Component;
import java.util.Random;

/**
 *
 * @author Marti
 * 
 * Clase que controla todo el flujo del enfrentamiento, donde se deben tomar
 * cartas del mazo, ubicarlas en el tablero y avisar para cambio de turno.
 */
public class ControlEnfrentamiento implements ActionListener, MouseListener{
    
    private Mazo mazoJugador;
    private Mazo mazoEnemigo;
    private ArrayList<Tarjeta> mano;
    
    private Tarjeta[] campoEnemigo, campoAliado;
    
    private static TableroJuego mtj;
    
    private boolean hayCarta;
    private int cartaSeleccionada;

    public ControlEnfrentamiento() {
        
        //Genera objetos necesarios para el transcurso de la partida
        this.mazoJugador = new Mazo();
        this.mazoEnemigo = new Mazo();
        this.campoEnemigo = new Tarjeta[5];
        this.campoAliado = new Tarjeta[5];
        this.mano = new ArrayList<Tarjeta>();
        this.mtj = new TableroJuego();
        
        //Permite la visualización de la GUI
        this.mtj.setVisible(true);
        
        /*
            Añade ActionListener y MouseListener a los objetos de los que se
            necesita obtener información sobre clicks y acciones.
        */
        this.mtj.getBotonPasar().addActionListener(this);
        this.mtj.getMano().addMouseListener(this);
        this.mtj.getCampoAliado().addMouseListener(this);
        this.mtj.getCampoAliado().setListeners();
        this.mtj.getCampoEnemigo().addMouseListener(this);
        this.mtj.getCampoEnemigo().setListeners();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
    
    /* Detecta acciones, principalmente botones presionados*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mtj.getBotonPasar()){
            
            generarDanoAliados();
            
            colocarCartaEnemiga();
            
            if (mano.size() < 10){
                Tarjeta t = mazoJugador.getTarjeta();
                if (t != null){
                    mtj.anadirCarta(t.getId());
                    mano.add(t);
                }
            }
        }
    }
    
    public void generarDanoAliados(){
        for (int i = 0; i < campoAliado.length; i++){
            if (campoAliado[i] != null){
                int danoReal = 0;
                switch (campoAliado[i].getTipoAtaque()){
                    case 1 -> {
                        if(campoEnemigo[i] != null){
                            danoReal += 1;
                        }
                        else{
                            campoEnemigo[i].recibirDaño(campoAliado[i].getValorAtaque());
                        }
                        break;
                    }
                    case 2 -> {
                        switch (i){
                            case 0 -> {
                                for (int j = 0; j < 2; j++){
                                    if(campoEnemigo[j] != null){
                                        danoReal += 1;
                                    }
                                    else{
                                        campoEnemigo[i].recibirDaño(campoAliado[i].getValorAtaque());
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case 3 -> {
                        danoReal = 5;
                        break;
                    }
                    case 4 -> {
                        danoReal = 1;
                        break;
                    }
                }
            }
        }
    }
    
    private void colocarCartaEnemiga(){
        Tarjeta t = mazoEnemigo.getTarjeta();
        
        int posTar;
        
        do{
            Random rng = new Random();
            posTar = rng.nextInt(0, 5);
        }while(campoAliado[posTar] != null);
        
        campoAliado[posTar] = t;
        
        ((Casilla)mtj.getCampoEnemigo().getComponents()[posTar]).actualizarSprite(t.getId());
    }

    /* Detecta Clicks del mouse dentro de diferentes elementos de la GUI*/
    @Override
    public void mouseClicked(MouseEvent e) {
        
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
                        
                        Component[] comps = mtj.getCampoAliado().getComponents();
                        
                        for (int i = 0; i < comps.length; i++) {
                            if (comps[i].equals(e.getSource())){
                                ubCarta = i;
                            }
                        }
                        
                        if (campoAliado[ubCarta] == null){
                            casOrigen.actualizarSprite(t.getId());
                            campoAliado[ubCarta] = t;
                            mano.remove(cartaSeleccionada);
                            mtj.eliminarCarta(cartaSeleccionada);
                            hayCarta = false;
                        }
                        break;
                    }
                }
                case 1 -> { //Casilla de Mano
                    
                    //Guarda referencia a la ultima carta en mano clickeada
                    Component[] componentes = mtj.getMano().getComponents();
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
