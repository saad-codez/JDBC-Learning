import com.repository.StudentRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    static void main() throws SQLException {
        //creating the studentRepo object to call teh createUser()
        StudentRepository studentRepository = new StudentRepository();
        //studentRepository.createUser();
       // studentRepository.updateUser();
       // studentRepository.deleteUser();
      //  studentRepository.getUserById();
        studentRepository.getAllUsers();

    }
}

// to connect with db
// url
// username
//password

// jdbc:mysql://localhost:3306/