import com.model.Student;
import com.repository.StudentRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    static void main() throws SQLException {
        //creating the studentRepo object to call teh createUser()
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.createStudent(new Student("Ahmad", 21, "ahd@gmail.com"));
       // studentRepository.updateStudent();
       // studentRepository.deleteStudent();
      //  studentRepository.getStudentById();
       // studentRepository.getAllStudents();
       // studentRepository.completeCRUD();

    }
}

// to connect with db
// url
// username
//password

// jdbc:mysql://localhost:3306/