package academic.system.person.service.serviceImpl;

import academic.system.person.entity.Person;
import academic.system.person.entity.Teacher;
import academic.system.person.exception.ApiRequestException;
import academic.system.person.repository.PersonRepository;
import academic.system.person.repository.TeacherRepository;
import academic.system.person.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final PersonRepository personRepository;

    @Override
    public List<Teacher> listAll() {
        List<Teacher> teacherList = teacherRepository.findAll();
        if ( teacherList.isEmpty() ) {
            throw new ApiRequestException( "No existen docentes registrados" );
        }
        return teacherList;
    }

    @Override
    public List<Teacher> listByState( String state ) {
        return teacherRepository
                .findAll()
                .stream()
                .filter( teacher -> Objects.equals( teacher.getState(), state ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<Teacher> searchTeachersByName( String name ) {
        List<Teacher> teacherList = teacherRepository.findByName( name );
        if ( teacherList.isEmpty() ) {
            throw new ApiRequestException( "No existen registros con ese nombre" );
        }
        return teacherList;
    }

    @Override
    public Teacher getTeacher( int id ) {
        return teacherRepository
                .findById( id )
                .orElse( null );
    }

    @Override
    public Teacher searchTeacher( String dni ) {
        Teacher teacher = teacherRepository.findByDni( dni );
        if ( Objects.equals( teacher, null ) ) {
            throw new ApiRequestException( "No existe registro del docente con DNI " + dni );
        }
        return teacher;
    }

    @Override
    public Teacher addNewTeacher( Teacher teacher ) {
        Person personByDni = personRepository.findByDni( teacher.getDni() );
        if ( !Objects.equals( personByDni, null ) ) {
            throw new ApiRequestException( "El DNI " + teacher.getDni() + " ya existe, ingrese otro" );
        }

        Person personByEmail = personRepository.findByEmail( teacher.getEmail() );
        if ( !Objects.equals( personByEmail, null ) ) {
            throw new ApiRequestException( "El correo " + teacher.getEmail() + " ya existe, ingrese otro" );
        }

        teacher.setState( "ACTIVO" );
        return teacherRepository.save( teacher );
    }

    @Transactional
    public Teacher updateTeacher( int id,
                                  Teacher teacher ) {
        Teacher teacherRegistered = getTeacher( id );
        if ( !Objects.equals( teacherRegistered.getDni(), teacher.getDni() ) ) {
            Person personByDni = personRepository.findByDni( teacher.getDni() );
            if ( !Objects.equals( personByDni, null ) ) {
                throw new ApiRequestException( "El DNI " + teacher.getDni() + " ya existe, ingrese otro" );
            }
            teacherRegistered.setDni( teacher.getDni() );
        }

        if ( !Objects.equals( teacherRegistered.getEmail(), teacher.getEmail() ) ) {
            Person personByEmail = personRepository.findByEmail( teacher.getEmail() );
            if ( !Objects.equals( personByEmail, null ) ) {
                throw new ApiRequestException( "El correo " + teacher.getEmail() + " ya existe, ingrese otro" );
            }
            teacherRegistered.setEmail( teacher.getEmail() );
        }

        teacherRegistered.setName( teacher.getName() );
        teacherRegistered.setSurname( teacher.getSurname() );
        teacherRegistered.setGender( teacher.getGender() );
        teacherRegistered.setPhone( teacher.getPhone() );
        teacherRegistered.setAddress( teacher.getAddress() );
        teacherRegistered.setAge( teacher.getAge() );
        teacherRegistered.setOccupation( teacher.getOccupation() );
        return teacherRegistered;
    }

    @Transactional
    public Teacher activateTeacher( int id ) {
        Teacher teacher = getTeacher( id );
        teacher.setState( "ACTIVO" );
        return teacher;
    }

    @Transactional
    public Teacher deleteTeacher( int id ) {
        Teacher teacher = getTeacher( id );
        teacher.setState( "INACTIVO" );
        return teacher;
    }
}
