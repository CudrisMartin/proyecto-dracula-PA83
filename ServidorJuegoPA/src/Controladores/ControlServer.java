/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controladores;

import GUI.Consola;
import Modelos.Jugador;
import Modelos.Turno;
import conecciones.BD.DAO.DAOJugadores;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
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
    private DAOJugadores dao;
    private Consola consola;
    
    InetAddress direccion;
    
    public ControlServer(int puerto) throws IOException {
        
        this.direccion = InetAddress.getLocalHost();
        this.puerto = puerto;
        this.consola = new Consola();
        this.dao = new DAOJugadores();
        iniciar();
    }
    
    public void iniciar() throws IOException {
        consola.mostrarMensaje("Iniciando servidor en el puerto " + puerto+" Dirección IP: "+direccion.toString());

        // Validar la existencia de la base de datos
        
        if (dao.validarBD("PROYECTO_PAA_DRACULA")) {
            consola.mostrarMensaje("La base de datos 'PROYECTO_PAA_DRACULA' ya existe.");
        } else {
            consola.mostrarMensaje("La base de datos 'PROYECTO_PAA_DRACULA' no existe. Creándola...");
            dao.BDcrear();
            consola.mostrarMensaje("Base de datos 'PROYECTO_PAA_DRACULA' creada con éxito.");
        }
        

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
                        if (jugador.getIdJugador() == 1){
                        // Envío de mensajes de acuerdo con la información recibida
                            try (DataOutputStream dataOutputStream = new DataOutputStream(cliente.getOutputStream())) {
                                
                                boolean existeJugador = this.dao.comprobarJugador(jugador.getNombre());
                                
                                if(existeJugador){
                                    
                                    boolean ingresoCorrecto = this.dao.comprobarContraseña(jugador.getNombre(), jugador.getContraseña());
                                    
                                    if (ingresoCorrecto) {
                                        dataOutputStream.writeUTF("IngresoCorrecto");
                                        /*Se utiliza una expresión lambda para crear un hilo debido a la necesidad de manejar múltiples conexiones simultáneamente.
                                        *La expresión lambda proporciona una forma concisa de crear una implementación para una interfaz funcional, 
                                        que en este caso es la interfaz Runnable.*/
                                        accederServidor(cliente,jugador);

                                    }else{
                                        dataOutputStream.writeUTF("ContraseñaIncorrecta");
                                    }
                                }else{
                                    dataOutputStream.writeUTF("RegistrarUsuario");
                                }
                            } catch (Exception ex) {
                                consola.mostrarMensaje(ex.getMessage());
                            }
                        }else if (jugador.getIdJugador() == 0){
                            // Envío de mensajes de acuerdo con la información recibida
                            try (DataOutputStream dataOutputStream = new DataOutputStream(cliente.getOutputStream())) {

                                this.dao.inscribirJugador(jugador.getNombre(), jugador.getContraseña(), "");
                                dataOutputStream.writeUTF("RegistroCorrecto");
                                
                            } catch (Exception ex) {
                                consola.mostrarMensaje(ex.getMessage());
                            }
                        }
                    }
                } catch (Exception ex) {
                    consola.mostrarMensaje(ex.getMessage());
                }
            }
        } catch (Exception e) {
            consola.mostrarMensaje("Error en el servidor: " + e.getMessage());
        }
    }
    
    private void accederServidor(Socket cliente, Jugador jugador){
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
                            ctrlJuego = new ControlJuego(jugador.getMazo());
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
