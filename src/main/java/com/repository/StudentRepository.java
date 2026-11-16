package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepository {
    String url = "jdbc:mysql://localhost:3306/studentdb";
    String username = "saad";
    String password = "Saadhero@26";

    public void createUser(){
        try {
            //driver manager helps us to get connected to mysql and bring the path to connection object
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to MySQL DataBase SUCCESSFUL!");

            //creating sql query
            String sql = "insert INTO student(name,age,email)" +
                    "values('Alamgir',22,'alamgir26@gmail.com')";

            // to pass the sql query from java to mysql
            Statement statement = connection.createStatement(); //Statement is used to wrap the sql string and pass it to mysql db
            int result = statement.executeUpdate(sql); //execute();general purpose, executeQuery() used to (Select)
            if (result == 1) {
                System.out.println("Update Successful");
            }
            else{
                System.out.println("Failed to Update the table!");
            }



            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed: "+e.getMessage());
        }

    }
}
