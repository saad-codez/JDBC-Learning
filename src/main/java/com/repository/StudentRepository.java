package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepository {
    String url = "jdbc:mysql://localhost:3306/studentdb";
    String username = "saad";
    String password = "Saadhero@26";

    public void createUser() {
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
            } else {
                System.out.println("Failed to Update the table!");
            }


            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed: " + e.getMessage());
        }

    }

    public void updateUser() {

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql  = "update student set age = 24 where id = 1";
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            if (result != 0) {
                System.out.println("Successfully Updated");
            } else {
                System.out.println("Failed to Update");
            }
        } catch (SQLException e) {
            System.out.println("Failed: " + e.getMessage());
        }

    }

    public void deleteUser() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "delete from student where id = 2";
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            if (result != 0) {
                System.out.println("Successfully Deleted");
            } else {
                System.out.println("Deletion failed!");
            }
        } catch (SQLException e) {
            System.out.println("FAILED: "+ e.getMessage());
        }
}


    }





