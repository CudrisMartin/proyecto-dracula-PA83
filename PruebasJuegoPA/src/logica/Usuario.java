package logica;

/**
 *
 * @author Andro
 */
public class Usuario {
    /* el estado es la actividad ( online o offline)*/
    /*mazo es conformado por un string ya que cada caracter es una tarjeta del mazo*/
    private String nombre;
    private String contrasena;
    private int estado;
    private int partidas_ganadas;
    private String mazo;
    

   public Usuario(String nombre, String contrasena, int estado, int partidas_ganadas, String mazo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.estado = estado;
        this.partidas_ganadas = partidas_ganadas;
        this.mazo = mazo;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPartidas_ganadas() {
        return partidas_ganadas;
    }

    public void setPartidas_ganadas(int partidas_ganadas) {
        this.partidas_ganadas = partidas_ganadas;
    }

    public String getMazo() {
        return mazo;
    }

    public void setMazo(String mazo) {
        this.mazo = mazo;
    }
    
    
            
    
}
