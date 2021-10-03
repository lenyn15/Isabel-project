package academic.system.person.service.serviceImpl;

import academic.system.person.entity.Person;
import academic.system.person.entity.Tutor;
import academic.system.person.exception.ApiRequestException;
import academic.system.person.repository.PersonRepository;
import academic.system.person.repository.TutorRepository;
import academic.system.person.service.TutorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;
    private final PersonRepository personRepository;

    @Override
    public List<Tutor> listAll() {
        List<Tutor> tutorList = tutorRepository.findAll();
        if ( tutorList.isEmpty() ) {
            throw new ApiRequestException( "No existe ningun registro" );
        }
        return tutorList;
    }

    @Override
    public List<Tutor> listByState( String state ) {
        return tutorRepository
                .findAll()
                .stream()
                .filter( tutor -> Objects.equals( tutor.getState(), state ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<Tutor> searchTutorsByName( String name ) {
        List<Tutor> tutorList = tutorRepository.findByName( name );
        if ( tutorList.isEmpty() ) {
            throw new ApiRequestException( "No existen registros con el nombre " + name );
        }
        return tutorList;
    }

    @Override
    public Tutor getTutor( int id ) {
        return tutorRepository
                .findById( id )
                .orElse( null );
    }

    @Override
    public Tutor searchTutor( String dni ) {
        Tutor tutor = tutorRepository.findByDni( dni );
        if ( Objects.equals( tutor, null ) ) {
            throw new ApiRequestException( "No existe registro con DNI " + dni );
        }
        return tutor;
    }

    @Override
    public Tutor addNewTutor( Tutor tutor ) {
        Person personByDni = personRepository.findByDni( tutor.getDni() );
        if ( !Objects.equals( personByDni, null ) ) {
            throw new ApiRequestException( "El DNI " + tutor.getDni() + " ya existe, ingrese otro" );
        }

        Person personByEmail = personRepository.findByEmail( tutor.getEmail() );
        if ( !Objects.equals( personByEmail, null ) ) {
            throw new ApiRequestException( "El DNI " + tutor.getEmail() + " ya existe, ingrese otro" );
        }

        tutor.setState( "ACTIVO" );
        return tutorRepository.save( tutor );
    }

    @Transactional
    public Tutor updateTutor( int id,
                              Tutor tutor ) {
        Tutor tutorRegistered = getTutor( id );
        if ( !Objects.equals( tutorRegistered.getDni(), tutor.getDni() ) ) {
            Person personByDni = personRepository.findByDni( tutor.getDni() );
            if ( !Objects.equals( personByDni, null ) ) {
                throw new ApiRequestException( "El DNI " + tutor.getDni() + " ya existe, ingrese otro" );
            }
            tutorRegistered.setDni( tutor.getDni() );
        }

        if ( !Objects.equals( tutorRegistered.getEmail(), tutor.getEmail() ) ) {
            Person personByEmail = personRepository.findByEmail( tutor.getEmail() );
            if ( !Objects.equals( personByEmail, null ) ) {
                throw new ApiRequestException( "El correo " + tutor.getEmail() + " ya existe, ingrese otro" );
            }
            tutorRegistered.setEmail( tutor.getEmail() );
        }

        tutorRegistered.setName( tutor.getName() );
        tutorRegistered.setSurname( tutor.getSurname() );
        tutorRegistered.setGender( tutor.getGender() );
        tutorRegistered.setPhone( tutor.getPhone() );
        tutorRegistered.setAddress( tutor.getAddress() );
        tutorRegistered.setMarital_status( tutor.getMarital_status() );
        tutorRegistered.setCareer( tutor.getCareer() );
        tutorRegistered.setWorkspace( tutor.getWorkspace() );
        return tutorRegistered;
    }

    @Transactional
    public Tutor activateTutor( int id ) {
        Tutor tutor = getTutor( id );
        tutor.setState( "ACTIVO" );
        return tutor;
    }


    @Transactional
    public Tutor deleteTutor( int id ) {
        Tutor tutor = getTutor( id );
        tutor.setState( "INACTIVO" );
        return tutor;
    }
}
