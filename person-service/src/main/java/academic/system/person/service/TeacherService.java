package academic.system.person.service;

import academic.system.person.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> listAll();

    List<Teacher> listByState( String state );

    List<Teacher> searchTeachersByName( String name );

    Teacher getTeacher( int id );

    Teacher searchTeacher( String dni );

    Teacher addNewTeacher( Teacher teacher );

    Teacher updateTeacher( int id,
                           Teacher teacher );

    Teacher activateTeacher(int id);

    Teacher deleteTeacher( int id );
}
