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
    
    //Objeto que permite hallar la dirección ip del servidor
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
                 
                // Entrada primer objeto para ingreso o registro
                try (ObjectInputStream objectInputStream = new ObjectInputStream(cliente.getInputStream())) {
                    
                    Object obj = objectInputStream.readObject();
                    
                    if (obj instanceof Jugador) {
                        
                        Jugador jugador = (Jugador) obj;
                        
                        // Envío de mensajes de acuerdo con la información recibida
                        if (jugador.getIdJugador() == 1){
                            
                            try (DataOutputStream dataOutputStream = new DataOutputStream(cliente.getOutputStream())) {
                                
                                boolean existeJugador = this.dao.comprobarJugador(jugador.getNombre());
                                
                                if(existeJugador){
                                    
                                    boolean ingresoCorrecto = this.dao.comprobarContraseña(jugador.getNombre(), jugador.getContraseña());
                                    
                                    if (ingresoCorrecto) {
                                        dataOutputStream.writeUTF("IngresoCorrecto");
                                        /*Se utiliza una expresión lambda para crear un hilo debido a la necesidad de manejar múltiples conexiones simultáneamente.
                                        *La expresión lambda proporciona una forma concisa de crear una implementación para una interfaz funcional, 
                                        que en este caso es la interfaz Runnable.*/
                                        ControlJuego ctrlJuego = new ControlJuego(dao.obtenerInformacionJugador(jugador.getNombre()));
                                        Turno primTurno = ctrlJuego.primerTurno();
                                        try(ObjectOutputStream ObOuSt = new ObjectOutputStream(cliente.getOutputStream())){
                                            ObOuSt.writeObject(primTurno);
                                            consola.mostrarMensaje("Turno enviado exitosamente.");
                                        }catch (IOException ex) {
                                            Logger.getLogger(ControlServer.class.getName()).log(Level.SEVERE, null, ex);
                                            consola.mostrarMensaje("Fallo al enviar el turno, reintentando...");
                                        }
                                        
                                        new Thread(() -> {
                                            try (ServerSocket soc = new ServerSocket(3333)) {
                                                consola.mostrarMensaje("Nuevo enfrentamiento ubicado en el puerto 3333");

                                                while (true) {
                                                    Socket jugEnPart = soc.accept();

                                                    ObjectInputStream ObjectInputStream = new ObjectInputStream(jugEnPart.getInputStream());

                                                    Object paq = ObjectInputStream.readObject();
                                                    if (paq instanceof Turno){
                                                        Turno t = ((Turno)paq);
                                                        Turno respuesta = ctrlJuego.generarTurno(t);
                                                        ObjectOutputStream ObOutStream = new ObjectOutputStream(jugEnPart.getOutputStream());
                                                        ObOutStream.writeObject(respuesta);
                                                        if (respuesta.getGanador() != 0){
                                                            if (respuesta.getGanador() == 1){
                                                                consola.mostrarMensaje("Gana jugador");
                                                            }else if(respuesta.getGanador() == 2){
                                                                consola.mostrarMensaje("Gana maquina");
                                                            }
                                                            soc.close();
                                                            break;
                                                        }
                                                    }

                                                }
                                            } catch (IOException e) {
                                                consola.mostrarMensaje("Error en el servidor 3333: " + e.getMessage());
                                            } catch (ClassNotFoundException ex) {
                                                Logger.getLogger(ControlServer.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }).start();

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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
