package academic.system.person.service;

import academic.system.person.entity.Tutor;

import java.util.List;

public interface TutorService {
    List<Tutor> listAll();

    List<Tutor> listByState(String state);

    List<Tutor> searchTutorsByName( String name );

    Tutor getTutor( int id );

    Tutor searchTutor( String dni );

    Tutor addNewTutor( Tutor tutor );

    Tutor updateTutor( int id,
                       Tutor tutor );

    Tutor activateTutor(int id);

    Tutor deleteTutor( int id );
}
