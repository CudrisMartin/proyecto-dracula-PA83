package logica;

/**
 *
 * @author Andro
 */
public class Usuario {
    /* el estado es la actividad ( online o offline)*/
    protected String nombre;
    protected String contraseña;
    protected int estado; 
    

    public Usuario(String nombre, String contraseña, int estado) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.estado = estado;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
            
    
}
