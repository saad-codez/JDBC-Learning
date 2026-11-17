package com.repository;

import com.model.Student;
import com.mysql.cj.protocol.Resultset;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;

public class StudentRepository {
    String url = "jdbc:mysql://localhost:3306/studentdb";
    String username = "saad";
    String password = "Saadhero@26";

//    public StudentRepository() throws SQLException {
//    }


    // creating a student using prepared statement
    public void createStudent(Student student) {
        String sql = """
                insert into student(name,age,email) 
                values(?,?,?)
                """;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected !=0 ) {
                System.out.println("Student Created");
            } else {
                System.out.println("Failed to Create a Student");
            }


        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    // creating student using only statement
//    public void createStudent() {
//        try {
//            //driver manager helps us to get connected to mysql and bring the path to connection object
//            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Connection to MySQL DataBase SUCCESSFUL!");
//
//            //creating sql query
//            String sql = "insert INTO student(name,age,email)" +
//                    "values('Muwahhid',24,'muwahhid26@gmail.com')";
//
//            // to pass the sql query from java to mysql
//            Statement statement = connection.createStatement(); //Statement is used to wrap the sql string and pass it to mysql db
//            int result = statement.executeUpdate(sql); //execute();general purpose, executeQuery() used to (Select)
//            if (result == 1) {
//                System.out.println("Update Successful");
//            } else {
//                System.out.println("Failed to Update the table!");
//            }
//
//
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Failed: " + e.getMessage());
//        }
//
//    }


    // updating student using prepared statements
    public void updateStudent(Student student, int id) {
        String sql = """
                Update student
                set name = ?,
                age = ?,
                email = ?
                where id = ?
                """;

        try(
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.setString(3,student.getEmail());
            preparedStatement.setInt(4,id);


          int rowsAffected =   preparedStatement.executeUpdate();

            if (rowsAffected != 0) {
                System.out.println("Update Successful");
            } else {
                System.out.println("Update Fail");
            }


        } catch (SQLException e) {
            System.out.println("Failed: "+ e.getMessage());
        }
    }

// updating a student using only statement
//    public void updateStudent() {
//
//        try {
//            Connection connection = DriverManager.getConnection(url, username, password);
//            String sql = "update student set age = 24 where id = 1";
//            Statement statement = connection.createStatement();
//            int result = statement.executeUpdate(sql);
//            if (result != 0) {
//                System.out.println("Successfully Updated");
//            } else {
//                System.out.println("Failed to Update");
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Failed: " + e.getMessage());
//        }
//
//    }

    // deleting student using prepared statement
    public void deleteStudent(int id) {
        String sql = """
                delete from student where id = ?
                """;

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, id);
            int rowsAffected =  preparedStatement.executeUpdate();

            if (rowsAffected != 0) {
                System.out.println("Sucessfull");
            }
            else {
                System.out.println("Failed");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }


    //deleting student only using statement
//    public void deleteStudent() {
//        try {
//            Connection connection = DriverManager.getConnection(url, username, password);
//            String sql = "delete from student where id = 2";
//            Statement statement = connection.createStatement();
//            int result = statement.executeUpdate(sql);
//            if (result != 0) {
//                System.out.println("Successfully Deleted");
//            } else {
//                System.out.println("Deletion failed!");
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("FAILED: " + e.getMessage());
//        }
//    }

    public void getStudentById(int id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = """
                    select id, name, age, email from student
                    where id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Student student = mapRow(resultSet);
            System.out.println(student);
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllStudents() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        String sql = "select id, name, age, email from student";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Student student = mapRow(resultSet);
            System.out.println(student);
        }
        connection.close();

    }

    public void completeCRUD() throws SQLException {
        // execute() --> can perform anything; returns boolean, true(fetch data), if false(insert, update, delete)
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String sql = "update student set age = 23 where id = 4"; // this is update; returns false

        boolean execute = statement.execute(sql);

        if (execute) {
            ResultSet resultSet = statement.getResultSet();
            System.out.println("Fetched Data");
        } else {
            int rowsAffected = statement.getUpdateCount();
            System.out.println("Performed Insert, Update, Delete");
        }
    }

    // fetches all the students from the db
    private Student mapRow(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setAge(resultSet.getInt("age"));
        student.setEmail(resultSet.getString("email"));

        return student;
    }


}





