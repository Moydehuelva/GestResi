package com.mycompany.sqlite;

import java.sql.*;

/**
 *
 * @author moyde
 */
public class Conexion {

    Connection conectar = null;

    public Connection getConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            conectar = DriverManager.getConnection("jdbc:sqlite:gestresi.db");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conectar;

    }

    public void cerrarConnection() throws SQLException {
        // Cierre de la conexión
        conectar.close();
    }
}
