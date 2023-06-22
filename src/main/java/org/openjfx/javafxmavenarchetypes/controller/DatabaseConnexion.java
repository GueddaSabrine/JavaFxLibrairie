package org.openjfx.javafxmavenarchetypes.controller;

import java.sql.*;

public class DatabaseConnexion {
    public Connection databaseLink;
    public Connection getConnection(){
        if(databaseLink == null){

            String dbName ="biblioDB";
            String dbUser ="root";
            String dbPassword="root";
            String url = "jdbc:mysql://localhost:8889/"+dbName;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(url,dbUser,dbPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return databaseLink;
    }

    public ResultSet selectBook(String req){
        try {
            Statement statement = databaseLink.createStatement();
            ResultSet queryOutput = statement.executeQuery(req);
            return queryOutput;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement insert(String req){
        try {
            PreparedStatement preparedStmt = databaseLink.prepareStatement(req);
            return preparedStmt;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
