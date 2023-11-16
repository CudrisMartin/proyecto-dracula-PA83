/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Campo;

import java.awt.Component;

/**
 *
 * @author Marti
 * 
 * Muestra las cartas de cada bando que se presentan en combate en el tablero.
 */
public class Campo extends javax.swing.JPanel {

    /**
     * Creates new form Tablero
     */
    public Campo() {
        initComponents();
    }
    
    /* 
        Ubica el controlador del combate como el detector de clicks de cada
        casilla
    */
    public void setListeners(){
        Component[] comp = this.getComponents();
        for (Component comp1 : comp) {
            comp1.addMouseListener(this.getMouseListeners()[0]);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        casilla1 = new GUI.Campo.Casilla();
        casilla2 = new GUI.Campo.Casilla();
        casilla3 = new GUI.Campo.Casilla();
        casilla4 = new GUI.Campo.Casilla();
        casilla5 = new GUI.Campo.Casilla();

        setBackground(new java.awt.Color(255, 0, 0));
        setMaximumSize(new java.awt.Dimension(540, 156));
        setMinimumSize(new java.awt.Dimension(540, 156));
        setPreferredSize(new java.awt.Dimension(540, 156));

        javax.swing.GroupLayout casilla1Layout = new javax.swing.GroupLayout(casilla1);
        casilla1.setLayout(casilla1Layout);
        casilla1Layout.setHorizontalGroup(
            casilla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        casilla1Layout.setVerticalGroup(
            casilla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout casilla2Layout = new javax.swing.GroupLayout(casilla2);
        casilla2.setLayout(casilla2Layout);
        casilla2Layout.setHorizontalGroup(
            casilla2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        casilla2Layout.setVerticalGroup(
            casilla2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout casilla3Layout = new javax.swing.GroupLayout(casilla3);
        casilla3.setLayout(casilla3Layout);
        casilla3Layout.setHorizontalGroup(
            casilla3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        casilla3Layout.setVerticalGroup(
            casilla3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout casilla4Layout = new javax.swing.GroupLayout(casilla4);
        casilla4.setLayout(casilla4Layout);
        casilla4Layout.setHorizontalGroup(
            casilla4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        casilla4Layout.setVerticalGroup(
            casilla4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout casilla5Layout = new javax.swing.GroupLayout(casilla5);
        casilla5.setLayout(casilla5Layout);
        casilla5Layout.setHorizontalGroup(
            casilla5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        casilla5Layout.setVerticalGroup(
            casilla5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(casilla1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(casilla4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(casilla3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(casilla5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(casilla2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(casilla1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(casilla2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(casilla3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(casilla4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(casilla5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Campo.Casilla casilla1;
    private GUI.Campo.Casilla casilla2;
    private GUI.Campo.Casilla casilla3;
    private GUI.Campo.Casilla casilla4;
    private GUI.Campo.Casilla casilla5;
    // End of variables declaration//GEN-END:variables
}
