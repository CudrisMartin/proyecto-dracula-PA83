/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Campo;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author a
 * 
 * Las casillas muestran por la interfaz grafica la información de las cartas
 */
public class Casilla extends javax.swing.JPanel{
    
    private int tipoCasilla; // 0 para casilla de Campo, 1 para casilla de Mano
    
    private int idTarjeta;
    private ImageIcon sprite;

    /**
     * Casilla dentro de los Campos
     */
    public Casilla() {
        initComponents();
        this.tipoCasilla = 0;
    }
    
    /*
        Casilla dentro de la Mano
    */
    public Casilla(int i) {
        this.tipoCasilla = 1;
        this.idTarjeta = i;
        initComponents();
        actualizarSprite(idTarjeta);
    }
    
    
    
    /* Actualiza el sprite de cada casilla dependiendo de si tiene carta o no*/
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        if (idTarjeta == 0){
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, 100, 156);
        }else{
            g.drawImage(sprite.getImage(), 0,0, 100, 156, this);
        }
    }
    
    /* Ubica cual es el sprite al quue se debe actualizar la casilla*/
    public void actualizarSprite(int id){
        this.idTarjeta = id;
        if (id != 0){
            this.sprite = new ImageIcon(getClass().getResource("/Recursos/Sprites/JPD_PA"+id+".png"));
        }
        this.updateUI();
    }
    
    /* Obtiene el tipo de casilla*/
    public int getTipoCasilla() {
        return tipoCasilla;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 0));
        setMaximumSize(new java.awt.Dimension(100, 156));
        setMinimumSize(new java.awt.Dimension(100, 156));
        setPreferredSize(new java.awt.Dimension(100, 156));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
