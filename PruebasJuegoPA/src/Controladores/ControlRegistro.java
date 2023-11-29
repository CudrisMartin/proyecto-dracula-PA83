/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import GUI.VistaRegistro;
import Modelos.Jugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Marti
 */
public class ControlRegistro implements ActionListener{
    VistaRegistro vista;
    ControlGeneral ctrlGen;
    
    public ControlRegistro(ControlGeneral ctrlGen){
        this.ctrlGen =  ctrlGen;
        this.vista = new VistaRegistro();
        this.vista.bt_registrar.addActionListener(this);
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
        if (e.getSource() == vista.bt_registrar){
            String contConString = "";
            char[] contConChars = vista.Jfield_contrasenaconfirm.getPassword();
            for (int i  = 0; i < contConChars.length; i++){
                contConString += contConChars[i];
            }
            
            String contString = "";
            char[] contChars = vista.Jfield_contrasena.getPassword();
            for (int i  = 0; i < contChars.length; i++){
                contString += contChars[i];
            }
            
            if (contConString.equals(contString)){
                Jugador j = new Jugador(0,0,vista.Txtfield_nombre.getText(),contString,"");
                ctrlGen.enviarNuevoUsuario(j);
            }else{
                JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden", "Revisar contraseñas", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
