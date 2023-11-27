/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conecciones.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Marti
 */
public class ConectorBD {
    static String bd = "PROYECTO_PAA_DRACULA";
    static String login = "root";
    static String password = "usuario";
    static String url = "jdbc:mysql://localhost/"+bd;
    static String mensaje = "";
    
    Connection conexion = null;
    
    /**
     * Constructor de la clase
     */
    public ConectorBD() {
        try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url,login,password);

                if (conexion!=null){
                        System.out.println("Conexión a base de datos "+bd+" OK");
                }
        }catch(SQLException e){
                mensaje = e.getMessage();
        }catch(ClassNotFoundException e){
                mensaje = e.getMessage();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Metodo para retornar mensajes de control
     * @return 
     */
    public static String getMensaje() {
        return mensaje;
    }

    /**
     * Metodo para setear mensajes de control
     * @param mensaje 
     */
    public static void setMensaje(String mensaje) {
        ConectorBD.mensaje = mensaje;
    }
     
    /**
     * metodo que retorna la conexion a la bd
     * @return 
     */
    public Connection getConexion(){
        return conexion;
    }

    /**
     * metodo que desconecta la base de datos
     */
    public void desconectar(){
        conexion = null;
    }
}
