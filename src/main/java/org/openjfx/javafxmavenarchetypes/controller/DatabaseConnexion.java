package org.openjfx.javafxmavenarchetypes.controller;

import java.sql.*;

/**
 * Connexion à la base de donnée
 */
public class DatabaseConnexion {
    public Connection databaseLink;
    public Connection getConnection(){
        if(databaseLink == null){

            String dbName ="biblioDB";
            String dbUser ="root";
            String dbPassword="";
            String url = "jdbc:mysql://localhost:3306/"+dbName;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(url,dbUser,dbPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        return databaseLink;
    }

    /**
     *
     * @param req
     * @return
     */
    public ResultSet selectBook(String req){
        try {
            Statement statement = databaseLink.createStatement();
            ResultSet queryOutput = statement.executeQuery(req);
            return queryOutput;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
