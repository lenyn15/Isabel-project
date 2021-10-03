package academic.system.person.service.serviceImpl;

import academic.system.person.entity.Person;
import academic.system.person.entity.Student;
import academic.system.person.exception.ApiRequestException;
import academic.system.person.repository.PersonRepository;
import academic.system.person.repository.StudentRepository;
import academic.system.person.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PersonRepository personRepository;

    @Override
    public List<Student> listAll() {
        List<Student> studentList = studentRepository.findAll();
        if ( studentList.isEmpty() ) {
            throw new ApiRequestException( "No existen alumnos registrados, aun" );
        }
        return studentList;
    }

    @Override
    public List<Student> listByState( String state ) {
        return studentRepository
                .findAll()
                .stream()
                .filter( student -> Objects.equals( student.getState(), state ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<Student> searchStudentsByName( String name ) {
        List<Student> studentList = studentRepository.findByName( name );
        if ( studentList.isEmpty() ) {
            throw new ApiRequestException( "No existen registros con ese nombre" );
        }
        return studentList;
    }

    @Override
    public Student getStudent( int id ) {
        return studentRepository
                .findById( id )
                .orElse( null );
    }

    @Override
    public Student searchStudent( String dni ) {
        Student student = studentRepository.findByDni( dni );
        if ( Objects.equals( student, null ) ) {
            throw new ApiRequestException( "No existe registro del estudiante con DNI " + dni );
        }
        return student;
    }

    @Override
    public Student addNewStudent( Student student ) {
        Person personByDni = personRepository.findByDni( student.getDni() );
        if ( !Objects.equals( personByDni, null ) ) {
            throw new ApiRequestException( "El DNI " + student.getDni() + " ya existe, ingrese otro" );
        }

        Person personByEmail = personRepository.findByEmail( student.getEmail() );
        if ( !Objects.equals( personByEmail, null ) ) {
            throw new ApiRequestException( "El correo " + student.getEmail() + " ya existe, ingrese otro" );
        }

        student.setState( "PENDIENTE" );
        return studentRepository.save( student );
    }

    @Transactional
    public Student updateStudent( int id,
                                  Student student ) {
        Student studentDB = getStudent( id );
        if ( !Objects.equals( studentDB.getDni(), student.getDni() ) ) {
            Person personByDni = personRepository.findByDni( student.getDni() );
            if ( !Objects.equals( personByDni, null ) ) {
                throw new ApiRequestException( "El DNI " + student.getDni() + " ya existe, ingrese otro" );
            }
            studentDB.setDni( student.getDni() );
        }

        if ( !Objects.equals( studentDB.getEmail(), student.getEmail() ) ) {
            Person personByEmail = personRepository.findByEmail( student.getEmail() );
            if ( !Objects.equals( personByEmail, null ) ) {
                throw new ApiRequestException( "El correo " + student.getEmail() + " ya existe, ingrese otro" );
            }
            studentDB.setEmail( student.getEmail() );
        }

        studentDB.setName( student.getName() );
        studentDB.setSurname( student.getSurname() );
        studentDB.setGender( student.getGender() );
        studentDB.setPhone( student.getPhone() );
        studentDB.setAddress( student.getAddress() );
        studentDB.setDate_birth( student.getDate_birth() );
        return studentDB;
    }

    @Transactional
    public Student enrollStudent( int id ) {
        Student student = getStudent( id );
        student.setState( "MATRICULADO" );
        return student;
    }

    @Transactional
    public Student deleteStudent( int id ) {
        Student student = getStudent( id );
        student.setState( "INACTIVO" );
        return student;
    }
}
