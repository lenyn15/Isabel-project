package academic.system.academy.service;

import academic.system.academy.entity.Course;
import academic.system.academy.entity.Level;

import java.util.List;

public interface CourseService {
    List<Course> listAll();

    List<Course> filterByName( String name );

    List<Course> filterByLevel( Level level );

    List<Course> filterByState( String state );

    Course getCourse( int id );

    Course newCourse( Course course );

    Course updateCourse( int id,
                         Course course,
                         Level level );

    Course deleteCourse( int id );
}
