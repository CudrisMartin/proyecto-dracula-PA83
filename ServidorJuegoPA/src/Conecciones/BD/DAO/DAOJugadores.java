package conecciones.BD.DAO;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAOJugadores {
 
public String buscarEstadoK_IdJugador(String k_idJugador) {
    String estado = null;
    Connection connection = null;

    try {
        // Establecer la conexión a la base de datos
        String jdbcURL = "jdbc:mysql://localhost:3306/PROYECTO_PAA_DRACULA";
        String usuario = "root";
        String contraseña = "";
        connection = DriverManager.getConnection(jdbcURL, usuario, contraseña);

        if (connection != null) {
            // Consulta SQL para buscar el estado del k_idJugador en la tabla Jugador
            String consulta = "SELECT Estado FROM Jugador WHERE k_idJugador = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, k_idJugador);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                estado = resultSet.getString("Estado");
            }
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    } catch (SQLException e) {
        // Manejo de excepciones
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // Manejo de excepciones
        }
    }

    return estado;
}

    
    //En este metodo se crean las dos tablas que se utilizaran
    //la tabla de usuarios y la tabla de exonerados
   public static void crearTablas() {
    String jdbcURL = "jdbc:mysql://localhost:3306/PROYECTO_PAA_DRACULA";
    String usuario = "root";
    String contraseña = "";
    Connection conexion = null;
    Statement declaración = null;

    try {
        // Conectar a la base de datos
        conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);
        declaración = conexion.createStatement();

        // Crear la tabla Jugador con las columnas necesarias
        String sql = "CREATE TABLE Jugador ("
                + "k_idJugador INT NOT NULL,"
                + "k_nombre VARCHAR(15) NOT NULL,"
                + "n_contrasena VARCHAR(8) NOT NULL,"
                + "n_mazo VARCHAR(30) NOT NULL,"
                + "q_partidasGan SMALLINT NOT NULL,"
                + "CONSTRAINT PK_Jugador PRIMARY KEY (k_idJugador, k_nombre),"
                + "CONSTRAINT UK_n_contrasena UNIQUE (n_contrasena)"
                + ")";
        declaración.executeUpdate(sql);
    } catch (SQLException e) {
        // Manejo de excepciones
    } finally {
        try {
            if (declaración != null) {
                declaración.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            // Manejo de excepciones
        }
    }
}

    //Este metodo crea la base de datos en caso de que no exista 

public static boolean validarBD(String nombreBD) {
    boolean existeBD = false;
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql?useSSL=false", "root", "");
        if (conn != null) {
            String sql = "SHOW DATABASES LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreBD);

            rs = stmt.executeQuery();

            if (rs.next()) {
                // Si encuentra una coincidencia con el nombre de la base de datos, establece existeBD a true
                existeBD = true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos en el bloque finally
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return existeBD;
}


//Este metodo nos ayuda a agregar un jugador nuevo
public void inscribirJugador(String nombre, String contrasena, String mazo) {
    String jdbcURL = "jdbc:mysql://localhost:3306/PROYECTO_PAA_DRACULA";
    String usuario = "root";
    String contraseña = "";
    Connection conexion = null;
    PreparedStatement preparedStatement = null;

    try {
        conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);

        if (conexion != null) {
            // Consulta para obtener el siguiente ID de jugador
            String obtenerSiguienteID = "SELECT MAX(k_idJugador) FROM Jugador";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(obtenerSiguienteID);

            int siguienteID = 1; // Valor por defecto si no hay jugadores aún
            if (resultSet.next()) {
                siguienteID = resultSet.getInt(1) + 1;
            }

            // Consulta SQL para insertar un nuevo jugador en la tabla Jugador
            String consulta = "INSERT INTO Jugador (k_idJugador, k_nombre, n_contrasena, n_mazo) VALUES (?, ?, ?, ?)";
            preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, siguienteID);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, contrasena);
            preparedStatement.setString(4, mazo);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("¡Te has inscrito correctamente!");
            } else {
                System.out.println("Error al inscribir al jugador.");
            }
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    } catch (SQLException e) {
        // Manejo de excepciones
        e.printStackTrace();
    } finally {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }
}

//Este metodo nos ayuda a obtener la informacion de un jugador
public void obtenerInformacionJugador(int idJugador) {
    String jdbcURL = "jdbc:mysql://localhost:3306/PROYECTO_PAA_DRACULA";
    String usuario = "root";
    String contraseña = "";
    Connection conexion = null;
    PreparedStatement preparedStatement = null;

    try {
        conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);

        if (conexion != null) {
            // Consulta para obtener la información del jugador con el ID proporcionado
            String consulta = "SELECT * FROM Jugador WHERE k_idJugador = ?";
            preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, idJugador);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("k_idJugador");
                String nombre = resultSet.getString("k_nombre");
                String contrasena = resultSet.getString("n_contrasena");
                String mazo = resultSet.getString("n_mazo");
                int partidasGanadas = resultSet.getInt("q_partidasGan");

                // Aquí puedes imprimir o utilizar la información como desees
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Contraseña: " + contrasena);
                System.out.println("Mazo: " + mazo);
                System.out.println("Partidas Ganadas: " + partidasGanadas);
            } else {
                System.out.println("No se encontró al jugador con ID " + idJugador);
            }
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    } catch (SQLException e) {
        // Manejo de excepciones
        e.printStackTrace();
    } finally {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }
}
}
