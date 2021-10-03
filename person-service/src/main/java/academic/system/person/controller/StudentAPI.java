package academic.system.person.controller;

import academic.system.person.entity.Student;
import academic.system.person.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping( path = "/student",
                 produces = "application/json" )
@CrossOrigin( origins = "*" )
public class StudentAPI {

    private final StudentService studentService;

    @GetMapping( path = "/list" )
    public ResponseEntity<List<Student>> listAllStudents() {
        return ResponseEntity.ok( studentService.listAll() );
    }

    @GetMapping( path = "/filter/{state}" )
    public ResponseEntity<List<Student>> filterByState( @PathVariable( "state" ) String stateStudent ) {
        return ResponseEntity.ok( studentService.listByState( stateStudent ) );
    }

    @GetMapping( path = "/search/name/{name}" )
    public ResponseEntity<List<Student>> searchStudents( @PathVariable( "name" ) String nameStudent ) {
        return ResponseEntity.ok( studentService.searchStudentsByName( nameStudent ) );
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<Student> findStudent( @PathVariable( "id" ) int idStudent ) {
        return ResponseEntity.ok( studentService.getStudent( idStudent ) );
    }

    @GetMapping( path = "/search/dni/{dni}" )
    public ResponseEntity<Student> searchStudent( @PathVariable( "dni" ) String dni ) {
        return ResponseEntity.ok( studentService.searchStudent( dni ) );
    }

    @PostMapping( path = "/new" )
    public ResponseEntity<Student> addStudent( @Valid @RequestBody Student newStudent ) {
        return ResponseEntity
                .status( CREATED )
                .body( studentService.addNewStudent( newStudent ) );
    }

    @PutMapping( path = "/update/{id}" )
    public ResponseEntity<Student> modifyStudent( @PathVariable( "id" ) int idStudent,
                                                  @Valid @RequestBody Student student ) {
        return ResponseEntity.ok( studentService.updateStudent( idStudent, student ) );
    }

    @PutMapping( path = "/register/{id}" )
    public ResponseEntity<Student> registerStudent( @PathVariable( "id" ) int idStudent ) {
        return ResponseEntity.ok( studentService.enrollStudent( idStudent ) );
    }

    @DeleteMapping( path = "/delete/{id}" )
    public ResponseEntity<Student> deleteStudent( @PathVariable( "id" ) int idStudent ) {
        return ResponseEntity.ok( studentService.deleteStudent( idStudent ) );
    }
}
