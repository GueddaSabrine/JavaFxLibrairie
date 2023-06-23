package org.openjfx.javafxmavenarchetypes.controller;

import javafx.util.Pair;

import java.sql.*;

/**
 * Classe pour gérer la connexion à la base de données.
 */
public class DatabaseConnexion {
    /**
     * Lien vers la base de données.
     */
    public Connection databaseLink;

    /**
     * Obtient la connexion à la base de données.
     *
     * @return La connexion à la base de données.
     */
    public Connection getConnection() {
        if (databaseLink == null) {

            String dbName = "biblioDB";
            String dbUser = "root";
            String dbPassword = "";
            //MARION CHANGE LE LOCALHOST
            String url = "jdbc:mysql://localhost:3306/" + dbName;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(url, dbUser, dbPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return databaseLink;
    }

    /**
     * Exécute une requête de sélection sur la base de données.
     *
     * @param req La requête de sélection à exécuter.
     * @return Le résultat de la requête de sélection sous forme de ResultSet.
     * @throws RuntimeException Si une exception SQL se produit lors de l'exécution de la requête.
     */
    public ResultSet selectBook(String req) {
        try {
            Statement statement = databaseLink.createStatement();
            ResultSet queryOutput = statement.executeQuery(req);
            return queryOutput;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement insert(String req) {
        try {
            PreparedStatement preparedStmt = databaseLink.prepareStatement(req);
            return preparedStmt;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String req, Pair<Object, Integer>[] arg){
        try {
            PreparedStatement preparedStmt = databaseLink.prepareStatement(req);
            for(int i =0 ; i< arg.length ; i++){
                preparedStmt.setObject(i +1 ,arg[i].getKey(),arg[i].getValue());
            }
            preparedStmt.executeUpdate();
            System.out.println("Ajout des éléments : ok");
        } catch (SQLException e) {
            System.out.println("Ajout impossible à effectuer.\nErreur :" + e);
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() throws SQLException {

        if(databaseLink != null){
            databaseLink.close();
            databaseLink= null;
        }

    }
}
