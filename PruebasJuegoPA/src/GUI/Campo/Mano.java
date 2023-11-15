/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Campo;

/**
 *
 * @author Marti
 */
public class Mano extends javax.swing.JPanel{
    
    private int cartaSeleccionada;

    /**
     * Creates new form Mano
     */
    public Mano() {
        initComponents();
    }
    
    public void anadirTarjeta(int i){
        Casilla c = new Casilla(i);
        c.setVisible(true);
        c.addMouseListener(this.getMouseListeners()[0]);
        this.add(c);
        this.updateUI();
         System.out.println("Id carta en Mano: "+i);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 255));
        setMaximumSize(new java.awt.Dimension(1000, 200));
        setMinimumSize(new java.awt.Dimension(1000, 200));
        setPreferredSize(new java.awt.Dimension(1000, 200));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));
    }// </editor-fold>//GEN-END:initComponents


    public int getCartaSeleccionada() {
        return cartaSeleccionada;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
