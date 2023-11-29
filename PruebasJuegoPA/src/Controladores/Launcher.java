package Controladores;

import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Marti
 * 
 * Seleciona cual es el objeto que toma el control del Main principal
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ControlGeneral(JOptionPane.showInputDialog(null, "Ingrese la dirección IP del servidor."), 4500);
    }
    
}
