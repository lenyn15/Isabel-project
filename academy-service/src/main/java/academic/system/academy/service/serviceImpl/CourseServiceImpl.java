package academic.system.academy.service.serviceImpl;

import academic.system.academy.client.TeacherClient;
import academic.system.academy.entity.Course;
import academic.system.academy.entity.Level;
import academic.system.academy.exception.ApiRequestException;
import academic.system.academy.model.Teacher;
import academic.system.academy.repository.CourseRepository;
import academic.system.academy.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    private final TeacherClient teacherClient;

    @Override
    public List<Course> listAll() {
        List<Course> courseList = courseRepository.findAll();
        if ( courseList.isEmpty() ) {
            throw new ApiRequestException( "No existen cursos registrados, aun" );
        }
        return courseList;
    }

    @Override
    public List<Course> filterByName( String name ) {
        List<Course> courseList = courseRepository.findByName( name );
        if ( courseList.isEmpty() ) {
            throw new ApiRequestException( "No hay cursos con ese nombre" );
        }
        return courseList;
    }

    @Override
    public List<Course> filterByLevel( Level level ) {
        List<Course> courseList = courseRepository.findByLevel( level );
        if ( courseList.isEmpty() ) {
            throw new ApiRequestException( "No hay cursos asignados a este nivel" );
        }
        return courseList;
    }

    @Override
    public List<Course> filterByState( String state ) {
        return courseRepository
                .findAll()
                .stream()
                .filter( course -> Objects.equals( course.getState(), state ) )
                .collect( Collectors.toList() );
    }

    @Override
    public Course getCourse( int id ) {
        Course course = courseRepository
                .findById( id )
                .orElse( null );
        if ( !Objects.equals( course, null ) ) {
            Teacher teacher = teacherClient
                    .findTeacher( course.getTeacherId() )
                    .getBody();
            course.setTeacher( teacher );
        }
        return course;
    }

    @Override
    public Course newCourse( Course course ) {
        Teacher teacher = teacherClient
                .findTeacher( course.getTeacherId() )
                .getBody();
        course.setTeacher( teacher );
        course.setState( "ACTIVO" );
        return courseRepository.save( course );
    }

    @Transactional
    public Course updateCourse( int id,
                                Course course,
                                Level level ) {
        Teacher teacher = teacherClient
                .findTeacher( course.getTeacherId() )
                .getBody();
        Course courseDB = getCourse( id );
        courseDB.setName( course.getName() );
        courseDB.setTeacher( teacher );
        courseDB.setLevel( level );
        return courseDB;
    }

    @Transactional
    public Course deleteCourse( int id ) {
        Course course = getCourse( id );
        course.setState( "INACTIVO" );
        return course;
    }
}
