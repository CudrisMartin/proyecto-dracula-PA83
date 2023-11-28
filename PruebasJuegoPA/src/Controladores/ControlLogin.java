/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import GUI.VistaLogin;
import Modelos.Jugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Marti
 */
public class ControlLogin implements ActionListener{
    VistaLogin vista;
    ControlGeneral ctrlGen;
    
    public ControlLogin(ControlGeneral ctrlGen){
        this.ctrlGen =  ctrlGen;
        this.vista = new VistaLogin();
        this.vista.ingresa.addActionListener(this);
    }
    
    public void mostrarVista(){
        this.vista.setVisible(true);
    }
    
    public void ocultarVista(){
        this.vista.setVisible(false);
    }
    
    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(vista, mensaje);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.ingresa){
            String contString = "";
            char[] contChars = vista.TxtLog_contrasena.getPassword();
            for (int i  = 0; i < contChars.length; i++){
                contString += contChars[i];
            }
            Jugador j = new Jugador(0,0,vista.TxtLog_nombre.getText(),contString,"");
            ctrlGen.iniciarConeccion(j);
        }
    }
    
}
