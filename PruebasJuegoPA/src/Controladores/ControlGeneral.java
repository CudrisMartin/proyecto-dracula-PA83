/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelos.Jugador;
import Modelos.Turno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Marti
 */
public class ControlGeneral {
    
    private final String serverIP;
    private final int serverPort;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ControlLogin ctrlLogin;
    private ControlRegistro ctrlRegistro;
    private ControlJuego ctrlJuego;
    
    public ControlGeneral(String serverIP, int serverPort){
        this.ctrlLogin = new ControlLogin(this);
        this.ctrlRegistro = new ControlRegistro(this);
        this.ctrlJuego = new ControlJuego(this);
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.ctrlLogin.mostrarVista();
    }
    
    public void iniciarConeccion(Jugador j){
    // Bloque de código que maneja la conexión inicial con el servidor
        try {
            
            socket = new Socket(serverIP, serverPort);
            
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            
            System.out.println("Enviando objeto.");
            objectOutputStream.writeObject(j);
            
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String mensaje = dataInputStream.readUTF();
            
            if (mensaje.equals("RegistrarUsuario")){
                ctrlLogin.ocultarVista();
                ctrlRegistro.mostrarVista();
            }else if (mensaje.equals("IngresoCorrecto")){
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Object paq = objectInputStream.readObject();
                if (paq instanceof Turno){
                    Turno t = (Turno) paq;
                    ctrlJuego.actualizarTablero(t);
                    ctrlJuego.mostrarVista();
                    ctrlLogin.ocultarVista();
                }
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void enviarNuevoUsuario(Jugador j){
        try {
            socket = new Socket(serverIP, serverPort);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Enviando objeto.");
            objectOutputStream.writeObject(j);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String mensaje = dataInputStream.readUTF();
            if (mensaje.equals("RegistroCorrecto")) {
                System.out.println("RegistroCorrecto");
                ctrlRegistro.ocultarVista();
                ctrlLogin.mostrarVista();
            }else{
                System.out.println("RegistroFallido");
            }
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getClass()+" "+e.getCause()+""+e.getMessage());
        }
    }
    
    public void enviarTurno(Turno t){
        try {
            socket = new Socket(serverIP, 3333);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Enviando objeto.");
            objectOutputStream.writeObject(t);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object paq = objectInputStream.readObject();
            if (paq instanceof Turno){
                Turno turnoRecibido = (Turno) paq;
                ctrlJuego.actualizarTablero(turnoRecibido);
            }
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void reiniciarJuego(){
        ctrlJuego.ocultarVista();
        ctrlLogin.mostrarVista();
    }
}