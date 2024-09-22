package com.example.quiz.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseSingleton {

    private static DataBaseSingleton instance;
    private Connection connection;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // name database
    private static final String DATABASE = "practica_contenedores";

    // Host
    private static final String HOSTNAME = "localhost";

    // Port
    private static final String PORT = "3306";

    // username
    private static final String USER = "root";

    // password user
    private static final String PASSWORD = "root";

    // Rute of our datebase (we desactive the use of SSL with "?useSSL=false")
    private static final String DATABASE_URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE + "?useSSL=false";

    private DataBaseSingleton() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseSingleton getInstance() {
        if (instance == null){
            instance = new DataBaseSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo establecer la conexión con la base de datos");
        }
    }
}