/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Hacer_Mazo.componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Andro
 */
public class casilla extends javax.swing.JFrame {

    private int tipoCasilla; // 0 para activa para seleccionar, 1 para casilla seleccionada
    private int idTarjeta;
    private ImageIcon sprite;

     int contador_seleccion;
    /**
     * Creates new form casilla
     * sin seleccionar
     */
    public casilla() {
        initComponents();
         this.tipoCasilla = 0;
    }
    
    public casilla(int i) {
        this.tipoCasilla = 1;
        this.idTarjeta = i;
        initComponents();
        actualizarSprite(idTarjeta,1);
        addMouseListener(new MouseAdapter() {
               private int clics;
            @Override
            public void mouseClicked(MouseEvent e) {
                clics++; 

                if(clics == 2){
                    mostrarInfoEnNuevoFrame(i);
                    clics = 0; // Reiniciar el contador de clics
                }// Incrementar el contador de clics
                if (clics == 3) {
                   
                    contador_seleccion++;
                    actualizarSprite(i,0); // Cambiar el color del sprite después de 3 clics
                    
                    clics = 0; // Reiniciar el contador de clics
                }
            }

              

              
        });
    }
    
    
    /*
        Casilla seleccionada
    */
    
      /* Actualiza el sprite de cada casilla dependiendo de si tiene carta o no*/
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        if (idTarjeta == 0){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 100, 156);
        }else{
            g.drawImage(sprite.getImage(), 10 ,10, 100, 156, this);
        }
    }
    
    
    
     /* Actualiza el sprite de cada casilla si esta seleccionada o no*/
   public void actualizarSprite(int id,int tipoCasilla){
        this.idTarjeta = id;
        if (id != 0){            
           this.sprite = new ImageIcon(getClass().getResource("/Recursos/Sprites/JPD_PA"+id+".png"));
        }else{
           this.sprite = new ImageIcon(getClass().getResource("/Recursos/Sprites/JPD_PA"+id+"s.png"));
        }
      
    }
   
   private Properties cargarDatosDesdeProperties() {
        Properties propiedades = new Properties();
        try (FileInputStream entrada = new FileInputStream("./src/Recursos/Tarjetas.properties")) {
            propiedades.load(entrada);
        } catch (IOException ex) {
            ex.printStackTrace();
            // Manejar la excepción apropiadamente, por ejemplo, mostrar un mensaje de error
        }
        return propiedades;
    }
   
    private void mostrarInfoEnNuevoFrame(int id) {
        
        Properties propiedades = cargarDatosDesdeProperties();
        
        /* Obtener datos asociados al ID*/
        String nombre = propiedades.getProperty("tarjeta." + id + ".nombre", "Nombre no encontrado");
        int valorAtaque = Integer.parseInt(propiedades.getProperty("tarjeta." + id + ".valorAtaque", "0"));
        int valorDefensa = Integer.parseInt(propiedades.getProperty("tarjeta." + id + ".valorDefensa", "0"));
        int valorMagia = Integer.parseInt(propiedades.getProperty("tarjeta." + id + ".valorMagia", "0"));
        int valorSalud = Integer.parseInt(propiedades.getProperty("tarjeta." + id + ".valorSalud", "0"));

        JFrame infoFrame = new JFrame("Información de Tarjeta");
        JLabel labelTitulo = new JLabel("Información de la Tarjeta " + id);
        JLabel labelNombre = new JLabel("Nombre: " + nombre);
        JLabel labelAtaque = new JLabel("Valor de Ataque: " + valorAtaque);
        JLabel labelDefensa = new JLabel("Valor de Defensa: " + valorDefensa);
        JLabel labelMagia = new JLabel("Valor de Magia: " + valorMagia);
        JLabel labelSalud = new JLabel("Valor de Salud: " + valorSalud);
        
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(6, 1));
        panelInfo.add(labelTitulo);
        panelInfo.add(labelNombre);
        panelInfo.add(labelAtaque);
        panelInfo.add(labelDefensa);
        panelInfo.add(labelMagia);
        panelInfo.add(labelSalud);
        
        infoFrame.getContentPane().add(panelInfo);
        
        
        
        
        infoFrame.setSize(300, 200);
        infoFrame.setLocationRelativeTo(null);
        infoFrame.setVisible(true);
        
        
    }
    
    
    
   
    public int getTipoCasilla() {
        return tipoCasilla;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(casilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(casilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(casilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(casilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new casilla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
