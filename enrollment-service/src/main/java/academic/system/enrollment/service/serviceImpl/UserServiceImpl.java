package academic.system.enrollment.service.serviceImpl;

import academic.system.enrollment.entity.UserPerson;
import academic.system.enrollment.exception.ApiRequestException;
import academic.system.enrollment.repository.UserRepository;
import academic.system.enrollment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserPerson> listAll() {
        List<UserPerson> list = userRepository.findAll();
        if ( list.isEmpty() ) {
            throw new ApiRequestException( "No existen usuarios registrados, aun" );
        }
        return list;
    }

    @Override
    public List<UserPerson> listByState( String state ) {
        return userRepository
                .findAll()
                .stream()
                .filter( userPerson -> Objects.equals( userPerson.getState(), state ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<UserPerson> searchUsersByName( String name ) {
        List<UserPerson> userList = userRepository.findByName( name );
        if ( userList.isEmpty() ) {
            throw new ApiRequestException( "No existen registros con ese nombre" );
        }
        return userList;
    }

    @Override
    public UserPerson getUser( int id ) {
        return userRepository
                .findById( id )
                .orElse( null );
    }

    @Override
    public UserPerson addNewUser( UserPerson userPerson ) {
        UserPerson userByDni = userRepository.findByDni( userPerson.getDni() );
        if ( !Objects.equals( userByDni, null ) ) {
            throw new ApiRequestException( "El DNI " + userPerson.getDni() + " ya existe, ingrese otro" );
        }

        UserPerson userByUsername = userRepository.findByUsername( userPerson.getUsername() );
        if ( !Objects.equals( userByUsername, null ) ) {
            throw new ApiRequestException( "El nombre de usuario " + userPerson.getUsername() + " ya existe, ingrese otro" );
        }

        userPerson.setState( "ACTIVO" );
        return null;
    }

    @Transactional
    public UserPerson updateUser( int id,
                                  UserPerson userPerson ) {
        UserPerson userDB = getUser( id );
        if ( !Objects.equals( userDB.getDni(), userPerson.getDni() ) ) {
            UserPerson userByDni = userRepository.findByDni( userPerson.getDni() );
            if ( !Objects.equals( userByDni, null ) ) {
                throw new ApiRequestException( "El DNI " + userPerson.getDni() + " ya existe, ingrese otro" );
            }
            userDB.setDni( userPerson.getDni() );
        }

        if ( !Objects.equals( userDB.getUsername(), userPerson.getUsername() ) ) {
            UserPerson userByUsername = userRepository.findByUsername( userPerson.getUsername() );
            if ( !Objects.equals( userByUsername, null ) ) {
                throw new ApiRequestException( "El nombre de usuario " + userPerson.getUsername() + " ya existe, ingrese otro" );
            }
            userDB.setUsername( userPerson.getUsername() );
        }

        userDB.setName( userPerson.getName() );
        userDB.setSurname( userPerson.getSurname() );
        userDB.setRole( userPerson.getRole() );
        userDB.setPassword( userPerson.getPassword() );
        return userDB;
    }

    @Transactional
    public UserPerson deleteUser( int id ) {
        UserPerson userPerson = getUser( id );
        userPerson.setState( "INACTIVO" );
        return userPerson;
    }
}
