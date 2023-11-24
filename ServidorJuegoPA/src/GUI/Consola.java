/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

public class Consola{

    private JFrame frame;
    private JTextArea textArea;
    private PrintStream standardOut;

    public Consola() {
        frame = new JFrame("Consola del Servidor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false); // Evita que el usuario pueda editar el texto
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fuente monoespaciada para formato de consola

        // Redirige la salida estándar al JTextArea
        standardOut = System.out;
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Ajuste automático de desplazamiento
        frame.add(scrollPane);
        frame.setVisible(true);
        frame.setResizable(false);

    }

    // Clase para redirigir la salida estándar a JTextArea
    private static class CustomOutputStream extends PrintStream {

        private JTextArea textArea;

        CustomOutputStream(JTextArea textArea) {
            super(System.out);
            this.textArea = textArea;
        }

        @Override
        public void print(String s) {
            textArea.append(s);
        }
    }

    public void mostrarMensaje(String mensaje) {
        textArea.append(mensaje + "\n");
    }

}
