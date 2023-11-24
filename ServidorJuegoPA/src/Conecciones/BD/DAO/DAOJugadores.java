package DAO;

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
 

    public String buscarEstadoCedula(String cedula) {
        String estado = null;

        // Establecer la conexión a la base de datos
        String jdbcURL = "jdbc:mysql://localhost:3306/Jurados"; // Asegúrate de que sea la misma URL de la base de datos
        String usuario = "root"; // Nombre de usuario de MySQL
        String contraseña = ""; // Contraseña de MySQL
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, usuario, contraseña);
            if (connection != null) {
                // Consulta SQL para buscar el estado de la cédula en la tabla Usuarios
                String consulta = "SELECT Estado FROM Usuarios WHERE Cedula = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(consulta);
                preparedStatement.setString(1, cedula);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    estado = resultSet.getString("Estado");
                }

            } else {
                System.out.println("Error al conectar a la base de datos.");
            }
        } catch (SQLException e) {

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }

        return estado;
    }
    
    //En este metodo se crean las dos tablas que se utilizaran
    //la tabla de usuarios y la tabla de exonerados
    public static void crearTablas() {
        String jdbcURL = "jdbc:mysql://localhost:3306/Jurados"; // Asegúrate de que sea la misma URL de la base de datos
        String usuario = "root"; // Nombre de usuario de MySQL
        String contraseña = ""; // Contraseña de MySQL
        Connection conexion = null;
        Statement declaración = null;

        try {
            // Conectar a la base de datos
            conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);
            declaración = conexion.createStatement();

            // Crear la tabla Usuarios con las columnas necesarias para cada propiedad 
            String sql = "CREATE TABLE Usuarios ("
                    + "Cedula INT PRIMARY KEY,"
                    + "Contraseña VARCHAR(50) NOT NULL,"
                    + "Direccion VARCHAR(50) NOT NULL,"
                    + "Estado VARCHAR(50) NOT NULL,"
                    + "Nombre VARCHAR(50) NOT NULL,"
                    + "Edad INT NOT NULL,"
                    + "Asistencia VARCHAR(2) DEFAULT 'No',"
                    + "Justificacion VARCHAR(200)"
                    + ")"; 
            String sql2 = "CREATE TABLE Exonerados ("
                    + "Cedula INT PRIMARY KEY,"
                    + "Contraseña VARCHAR(50) NOT NULL,"
                    + "Direccion VARCHAR(50) NOT NULL,"
                    + "Estado VARCHAR(50) NOT NULL,"
                    + "Nombre VARCHAR(50) NOT NULL,"
                    + "Edad INT NOT NULL,"
                    + "Asistencia VARCHAR(2) DEFAULT 'No',"
                    + "Justificacion VARCHAR(200)"
                    + ")";
            declaración.executeUpdate(sql);
            declaración.executeUpdate(sql2);
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

    public static void BDcrear() {
        boolean existe = validarBD("Jurados");
        if (existe == false) {
            String url = "jdbc:mysql://localhost:3306/"; // URL de conexión a MySQL
            String usuario = "root"; // Nombre de usuario de MySQL
            String contraseña = ""; // Contraseña de MySQL
            String nombreBaseDatos = "Jurados"; // Nombre de la nueva base de datos

            Connection conexion = null;
            Statement statement = null;

            try {
                
                conexion = DriverManager.getConnection(url, usuario, contraseña);

                // Crear la base de datos
                statement = conexion.createStatement();
                String sql = "CREATE DATABASE " + nombreBaseDatos;
                statement.executeUpdate(sql);

                crearTablas();
                cargar();

            } catch (SQLException e) {

            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException e) {

                }
            }
        }

    }
    
    //Este método valida la existencia de la base de datos.

    public static boolean validarBD(String nombreBD) {
        boolean existeBD = false;
        Connection conn = null;
        try {
            // Conexión a la base de datos "mysql" en XAMPP
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql?useSSL=false", "root", "");
            Statement stmt = conn.createStatement();
            // Buscar si la base de datos existe
            ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE '" + nombreBD + "'");
            // Si encuentra la base de datos, entonces existe
            if (rs.next()) {
                existeBD = true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {

            }
        }
        return existeBD;
    }
    
    //Este metodo carga el archivo properties para tener usuarios en la tabla para consultar

    public static void cargar() {
        String archivoPropiedades = "./src/Docs/JudgesProperties.properties";
        Properties propiedades = new Properties();
        Connection conn = null;
        Statement stmt = null;

        try {
            FileInputStream in = new FileInputStream(archivoPropiedades);
            propiedades.load(in);
            in.close();
        } catch (IOException e) {

        }

        String url = "jdbc:mysql://localhost:3306/Jurados";
        String usuario = "root";
        String contrasena = ""; // Sin contraseña en este caso

        try {
            conn = DriverManager.getConnection(url, usuario, contrasena);
            stmt = conn.createStatement();

            int juezCount = 1; // Comienza con el primer juez
            while (true) {
                String juezKey = "juez" + juezCount;
                String cedula = propiedades.getProperty(juezKey + ".cedula");
                if (cedula == null) {
                    // Si no hay más usuarios en el archivo de propiedades, sal del bucle
                    break;
                }

                // Verificar si la cedula ya existe en la tabla
                String verificarCedulaSql = "SELECT Cedula FROM Usuarios WHERE Cedula = '" + cedula + "'";
                boolean cedulaExistente = false;

                // Ejecutar la consulta
                ResultSet resultSet = stmt.executeQuery(verificarCedulaSql);
                if (resultSet.next()) {
                    cedulaExistente = true;
                    break;
                }
                resultSet.close();

                if (!cedulaExistente) {
                    // Realizar la inserción solo si la cedula no existe
                    String contras = propiedades.getProperty(juezKey + ".contraseña");
                    String direccion = propiedades.getProperty(juezKey + ".direccion");
                    String estado = propiedades.getProperty(juezKey + ".estado");
                    String nombre = propiedades.getProperty(juezKey + ".nombre");
                    String edad = propiedades.getProperty(juezKey + ".edad");

                    String sql = "INSERT INTO Usuarios (Cedula, Contraseña, Direccion, Estado, Nombre, Edad) VALUES "
                            + "('" + cedula + "', '" + contras + "', '" + direccion + "', '" + estado + "', '" + nombre + "', " + edad + ")";

                    stmt.executeUpdate(sql);

                }
                juezCount++;
                if (juezCount == 16) {
                    break;
                }
            }
        } catch (SQLException e) {

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
    }
    
    //Esste metodo ingresa los jueces que son exonerados a la tabla de exonerados
    
    public void actualizarExonerados(String cedula, String justificacion) {
        String url = "jdbc:mysql://localhost:3306/Jurados";
        String usuario = "root"; // Nombre de usuario de MySQL
        String contras = ""; // Contraseña de MySQL
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            // Establecer la conexión a la base de datos
            conexion = DriverManager.getConnection(url, usuario, contras);

            // Definir la consulta SQL parametrizada
            String sql = "INSERT INTO Exonerados (Cedula, Contraseña, Direccion, Estado, Nombre, Edad, Asistencia, Justificacion) " +
                         "SELECT Cedula, Contraseña, Direccion, Estado, Nombre, Edad, 'No', ? FROM Usuarios WHERE Cedula = ?";

            // Preparar la declaración SQL
            statement = conexion.prepareStatement(sql);

            // Establecer los parámetros en la consulta
            statement.setString(1, justificacion);
            statement.setString(2, cedula);

            // Ejecutar la consulta para insertar en la tabla Exonerados
            statement.executeUpdate();
            
            eliminarUsuarios(cedula);
            
        } catch (SQLException e) {
            // Manejo de excepciones
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                // Manejo de excepciones
            }
        }
    }

   public void actualizarAsistenciaYJustificacion(String cedula, String asistencia, String justificacion) {
        String url = "jdbc:mysql://localhost:3306/Jurados";
        String usuario = "root";
        String contras = ""; 
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            // Establecer la conexión a la base de datos
            conexion = DriverManager.getConnection(url, usuario, contras);

            // Definir la consulta SQL parametrizada para actualizar la tabla de Usuarios
            String sql = "UPDATE Usuarios SET Asistencia = ?, Justificacion = ? WHERE Cedula = ?";

            // Preparar la declaración SQL
            statement = conexion.prepareStatement(sql);

            // Convertir el valor de asistencia a "Sí" si es "1" y configurar la justificación correspondiente
            if ("1".equals(asistencia)) {
                asistencia = "Sí";
            } else if ("No".equals(asistencia) && justificacion != null) {
                // Si la asistencia es "No" y la justificación no es nula, entonces el usuario se exoneró
                actualizarExonerados(cedula, justificacion);
            }

            // Establecer los parámetros en la consulta preparada
            statement.setString(1, asistencia);
            statement.setString(2, justificacion);
            statement.setString(3, cedula);

            // Ejecutar la consulta para actualizar el registro en la tabla Usuarios
            statement.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al ejecutar la consulta
        } finally {
            try {
                // Cerrar la declaración y la conexión en el bloque finally
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                // Manejo de excepciones en caso de error al cerrar la declaración o la conexión
            }
        }
    }

   //Este metodo permite eliminar usuarios de la tabla de Jurados a partir de consultar su cedula 
    public void eliminarUsuarios(String cedula) {
        String url = "jdbc:mysql://localhost:3306/Jurados"; // Asegúrate de que sea la misma URL de la base de datos
        String usuario = "root"; // Nombre de usuario de MySQL
        String contras = ""; // Contraseña de MySQL
        Connection conexion = null;
        PreparedStatement eliminarStatement = null;

        try {
            // Establecer la conexión a la base de datos
            conexion = DriverManager.getConnection(url, usuario, contras);

            // Definir la consulta SQL parametrizada para eliminar al usuario de la tabla Usuarios
            String eliminarUsuarioSql = "DELETE FROM Usuarios WHERE Cedula = ?";
            eliminarStatement = conexion.prepareStatement(eliminarUsuarioSql);
            eliminarStatement.setString(1, cedula);
            eliminarStatement.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones
        } finally {
            try {
                if (eliminarStatement != null) {
                    eliminarStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
            }
        }
    }

}
