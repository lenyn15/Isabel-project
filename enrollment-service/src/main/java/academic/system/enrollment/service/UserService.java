package academic.system.enrollment.service;

import academic.system.enrollment.entity.UserPerson;

import java.util.List;

public interface UserService {

    List<UserPerson> listAll();

    List<UserPerson> listByState( String state );

    List<UserPerson> searchUsersByName( String name );

    UserPerson getUser( int id );

    UserPerson addNewUser( UserPerson userPerson );

    UserPerson updateUser( int id,
                           UserPerson userPerson );

    UserPerson deleteUser( int id );
}
