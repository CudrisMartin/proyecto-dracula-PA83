/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controladores;

import GUI.Consola;
import Modelos.Jugador;
import Modelos.Turno;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marti
 */
public class ControlServer {

    private int puerto;
    private ControlJuego enfrentamiento;
    private boolean bandera = false;
    private Consola consola;
    
    public ControlServer(int puerto) {
        this.puerto = puerto;
        this.enfrentamiento = new ControlJuego();
    }
    
    public void iniciar() throws IOException {
        consola.mostrarMensaje("Iniciando servidor en el puerto " + puerto);

        // Validar la existencia de la base de datos
        /*
        if (Dao.validarBD("Jurados")) {
            consola.mostrarMensaje("La base de datos 'Jurados' ya existe.");
        } else {
            consola.mostrarMensaje("La base de datos 'Jurados' no existe. Creándola...");
            Dao.BDcrear();
            consola.mostrarMensaje("Base de datos 'Jurados' creada con éxito.");
        }
        */

        // Configuración del ExecutorService para gestionar hilos
        ExecutorService executor = Executors.newFixedThreadPool(100); // 10 hilos para manejar conexiones simultáneas

        // Creación del socket del servidor
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            consola.mostrarMensaje("Servidor listo para aceptar conexiones en el puerto " + puerto);

            while (true) {
                Socket cliente = serverSocket.accept();
                consola.mostrarMensaje("Jugador conectado desde " + cliente.getInetAddress());

                // Manejo del flujo de entrada de objetos
                try (ObjectInputStream objectInputStream = new ObjectInputStream(cliente.getInputStream())) {
                    Object obj = objectInputStream.readObject();
                    if (obj instanceof Jugador) {
                        Jugador jugador = (Jugador) obj;
                        consola.mostrarMensaje("Jugador ingresa en el servidor: " + jugador);
                        //consola.mostrarMensaje(this.Dao.buscarEstadoCedula(persona.getCedula()));

                        // Envío de mensajes de acuerdo con la información recibida
                        try (DataOutputStream dataOutputStream = new DataOutputStream(cliente.getOutputStream())) {
                            String mensaje = this.Dao.buscarEstadoCedula(persona.getCedula());
                            dataOutputStream.writeUTF(mensaje);
                            if (mensaje.equals("Asignado")) {
                                Cedula = Jugador.getCedula();
                                
                                /*Se utiliza una expresión lambda para crear un hilo debido a la necesidad de manejar múltiples conexiones simultáneamente.
                                *La expresión lambda proporciona una forma concisa de crear una implementación para una interfaz funcional, 
                                que en este caso es la interfaz Runnable.*/
                                
                                new Thread(() -> {
                                    try (ServerSocket soc = new ServerSocket(3333)) {
                                        consola.mostrarMensaje("Nuevo enfrentamiento ubicado en el puerto 3333");
                                        
                                        ControlJuego ctrlJuego = null;
                                        
                                        while (true) {
                                            Socket jugEnPart = soc.accept();
                                            ObjectInputStream ObjectInputStream = new ObjectInputStream(jugEnPart.getInputStream());
                                            Object paq = ObjectInputStream.readObject();
                                            Turno t = null;
                                            
                                            if (paq instanceof Turno){
                                                Turno turno = (Turno) paq;
                                                if (ctrlJuego == null){
                                                    ctrlJuego = new ControlJuego(turno.getMazoJugador());
                                                    t = ctrlJuego.primerTurno();
                                                }
                                            }
                                            
                                            boolean turEnv = false;
                                            
                                            do{
                                                try(ObjectOutputStream ObOuSt = new ObjectOutputStream(cliente.getOutputStream())){
                                                    ObOuSt.writeObject(t);
                                                    consola.mostrarMensaje("Turno enviado exitosamente.");
                                                    turEnv = true;
                                                }catch (IOException ex) {
                                                    Logger.getLogger(ControlServer.class.getName()).log(Level.SEVERE, null, ex);
                                                    consola.mostrarMensaje("Fallo al enviar el turno, reintentando...");
                                                }
                                            }while(!turEnv);
                                            
                                            if (ctrlJuego.getVidaJugador() == 0 || ctrlJuego.getVidaMaquina() == 0){
                                                
                                            }
                                            
                                        }
                                    } catch (IOException e) {
                                        consola.mostrarMensaje("Error en el servidor 3333: " + e.getMessage());
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(ControlServer.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }).start();
                            }
                        } catch (IOException ex) {
                            consola.mostrarMensaje("Error al enviar el mensaje.");
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    consola.mostrarMensaje("Error al recibir el objeto Persona.");
                }
            }
        } catch (IOException e) {
            consola.mostrarMensaje("Error en el servidor: " + e.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
