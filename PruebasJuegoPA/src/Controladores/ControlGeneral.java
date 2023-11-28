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
    private DataOutputStream dataOutputStream;
    private ControlLogin ctrlLogin;
    
    public ControlGeneral(String serverIP, int serverPort){
        this.ctrlLogin = new ControlLogin(this);
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.ctrlLogin.mostrarVista();
    }
    
    public void iniciarConeccion(Jugador j){
    // Bloque de código que maneja la conexión inicial con el servidor
        try {
            socket = new Socket(serverIP, serverPort);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Enviando objeto.");
            objectOutputStream.writeObject(j);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String mensaje = dataInputStream.readUTF();
            if (mensaje.equals("IngresoCorrecto")) {
                System.out.println("IngresoCorrecto");
            }
        } catch (IOException e) {
            System.exit(0);
        }
    } 

    
//    public void enviarTurno(Turno t){
//        if (socket != null) {
//                if (vista.jTextArea.getText().isEmpty()) {
//                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese una justificación para continuar.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
//                } else {
//                    try {
//                        dataOutputStream = new DataOutputStream(socket.getOutputStream());
//                        String justificacion = vista.jComboBox.getSelectedItem() + " " + vista.jTextArea.getText();
//                        dataOutputStream.writeUTF(justificacion);
//                        vista.showMessage("Esperámos que pase un buen día");
//                        vista.dispose();
//                    } catch (IOException e) {
//                        startPage.showMessage("Error al enviar el mensaje al servidor: " + e.getMessage());
//                    }
//                }
//            }
//    }
}



//public class ClientFacade implements ActionListener {
//
//
//    // Método que maneja los eventos de acción
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        
//    }
//
//    // Método para inicializar la página de inicio
//    private void startView() {
//        startPage.setLocationRelativeTo(null);
//        startPage.setVisible(true);
//        startPage.setResizable(false);
//        startPage.setTitle("Jurados de Votación 2023");
//    }
//
//    // Método para inicializar la segunda vista
//    private void inicioMenu() {
//        vista.setVisible(true);
//        vista.setLocationRelativeTo(null);
//        vista.setResizable(false);
//        vista.setTitle("Jurados de Votación 2023");
//
//        // Establecer la conexión en el puerto 3333 (segunda interfaz)
//        int secondPort = 3333;
//        try {
//            socket = new Socket(serverIP, secondPort);
//        } catch (IOException e) {
//            startPage.showMessage("Error en el usuario: " + e.getMessage());
//        }
//    }
//}
//
