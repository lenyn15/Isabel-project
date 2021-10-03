package academic.system.academy.service;

import academic.system.academy.entity.Grade;
import academic.system.academy.entity.Level;

import java.util.List;

public interface GradeService {
    List<Grade> listAll();

    List<Grade> filterByLevel( Level level );

    List<Grade> filterByState( String state );

    Grade getGrade( int id );

    Grade newGrade( Grade grade );

    Grade updateGrade( int id,
                       Grade grade,
                       Level level );

    Grade deleteGrade( int id );
}
