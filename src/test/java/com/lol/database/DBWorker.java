package com.lol.database;

import java.sql.*;

/**
 * Created by Денис on 11.11.2016.
 */

public class DBWorker {

    String query = "select * from users where login = ? and password = ?";
    private static final String URL = "jdbc:mysql://localhost:3306/bank_roll";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    private Connection connection = null;

    User user;

    public DBWorker(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (SQLException e )
        {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public User chechUser(String login, String password){
        PreparedStatement statement;

        DBWorker dbworker = new DBWorker();

        try {
            dbworker.getConnection();
            statement = dbworker.getConnection().prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLogin(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                System.out.print(user.getName() + " " + user.getId());
            }
            resultSet.close();
            statement.close();
            dbworker.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
