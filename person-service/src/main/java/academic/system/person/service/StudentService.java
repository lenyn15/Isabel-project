package academic.system.person.service;

import academic.system.person.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> listAll();

    List<Student> listByState( String state );

    List<Student> searchStudentsByName( String name );

    Student getStudent( int id );

    Student searchStudent( String dni );

    Student addNewStudent( Student student );

    Student updateStudent( int id,
                           Student student );

    Student enrollStudent(int id);

    Student deleteStudent( int id );
}
