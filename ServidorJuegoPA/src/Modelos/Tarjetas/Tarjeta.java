    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tarjetas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author Marti
 * 
 * Clase abstracta de la que derivan todos los tipós de cartas
 */
public abstract class Tarjeta implements Interfaces.MetodosCartas, Serializable{
    private static final long serialVersionUID = 12L;
    
    protected int id;
    protected String nombre;
    protected int valorSalud;
    protected int valorAtaque;
    protected int valorDefensa;
    protected int valorMagia;
    protected int tipoAtaque;
    
    public Tarjeta(char id) {
        String archivoPropiedades = "./src/Recursos/Tarjetas.properties";
        Properties propiedades = new Properties();
        
        try{
            FileInputStream in = new FileInputStream(archivoPropiedades);
            propiedades.load(in);
            in.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        this.id = Integer.parseInt(propiedades.getProperty("tarjeta"+id+".id"));
        this.nombre = propiedades.getProperty("tarjeta"+id+".nombre");
        this.valorSalud = Integer.parseInt(propiedades.getProperty("tarjeta"+id+".valorSalud"));
        this.valorAtaque = Integer.parseInt(propiedades.getProperty("tarjeta"+id+".valorAtaque"));
        this.valorDefensa = Integer.parseInt(propiedades.getProperty("tarjeta"+id+".valorDefensa"));
        this.valorMagia = Integer.parseInt(propiedades.getProperty("tarjeta"+id+".valorMagia"));
    }
    
    /* Reduce la vida de la carta en momento de juego*/
    @Override
    public void recibirDaño(int at){
        valorSalud -= (int)4*at/valorDefensa;
        if (valorSalud < 0){
            valorSalud = 0;
        }
        //System.out.println(nombre+" recibe "+at+" puntos de ataque y queda con "+valorSalud+" puntos de salud.");
    }
    
    public int getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getValorSalud() {
        return valorSalud;
    }

    @Override
    public int getValorAtaque() {
        return valorAtaque;
    }

    @Override
    public int getValorDefensa() {
        return valorDefensa;
    }

    @Override
    public int getValorMagia() {
        return valorMagia;
    }

    @Override
    public int getTipoAtaque() {
        return tipoAtaque;
    }
    
    
    
    
}
