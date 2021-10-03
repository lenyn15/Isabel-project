package academic.system.academy.controller;

import academic.system.academy.entity.Course;
import academic.system.academy.entity.Level;
import academic.system.academy.service.CourseService;
import academic.system.academy.service.LevelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping( path = "/course",
                 produces = "application/json" )
@CrossOrigin( origins = "*" )
public class CourseAPI {

    private final CourseService courseService;
    private final LevelService levelService;

    @GetMapping( path = "/list" )
    public ResponseEntity<List<Course>> listAllCourses() {
        return ResponseEntity.ok( courseService.listAll() );
    }

    @GetMapping( path = "/search/{name}" )
    public ResponseEntity<List<Course>> searchCourses( @PathVariable( "name" ) String nameCourse ) {
        return ResponseEntity.ok( courseService.filterByName( nameCourse ) );
    }

    @GetMapping( path = "/list/level/{id}" )
    public ResponseEntity<List<Course>> listByLevel( @PathVariable( "idL" ) int idLevel ) {
        Level level = levelService.getLevel( idLevel );
        return ResponseEntity.ok( courseService.filterByLevel( level ) );
    }

    @GetMapping( path = "/filter/{state}" )
    public ResponseEntity<List<Course>> searchByState( @PathVariable( "state" ) String stateCourse ) {
        return ResponseEntity.ok( courseService.filterByState( stateCourse ) );
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<Course> findCourse( @PathVariable( "id" ) int idCourse ) {
        return ResponseEntity.ok( courseService.getCourse( idCourse ) );
    }

    @PostMapping( path = "/new" )
    public ResponseEntity<Course> addCourse( @Valid @RequestBody Course newCourse ) {
        return ResponseEntity
                .status( CREATED )
                .body( courseService.newCourse( newCourse ) );
    }

    @PutMapping( path = "/update/{idL}/{idC}" )
    public ResponseEntity<Course> modifyCourse( @PathVariable( "idL" ) int idLevel,
                                                @PathVariable( "idC" ) int idCourse,
                                                @Valid @RequestBody Course course ) {
        Level level = levelService.getLevel( idLevel );
        return ResponseEntity.ok( courseService.updateCourse( idCourse, course, level ) );
    }

    @DeleteMapping( path = "/delete/{id}" )
    public ResponseEntity<Course> deleteCourse( @PathVariable( "id" ) int idCourse ) {
        return ResponseEntity.ok( courseService.deleteCourse( idCourse ) );
    }
}
