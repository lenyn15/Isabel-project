package academic.system.person.controller;

import academic.system.person.entity.Teacher;
import academic.system.person.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping( path = "/teacher",
                 produces = "application/json" )
@CrossOrigin( origins = "*" )
public class TeacherAPI {

    private final TeacherService teacherService;

    @GetMapping( path = "/list" )
    public ResponseEntity<List<Teacher>> listAllTeachers() {
        return ResponseEntity.ok( teacherService.listAll() );
    }

    @GetMapping( path = "/filter/{state}" )
    public ResponseEntity<List<Teacher>> filterByState( @PathVariable( "state" ) String stateTeacher ) {
        return ResponseEntity.ok( teacherService.listByState( stateTeacher ) );
    }

    @GetMapping( path = "/search/name/{name}" )
    public ResponseEntity<List<Teacher>> searchTeachers( @PathVariable( "name" ) String nameTeacher ) {
        return ResponseEntity.ok( teacherService.searchTeachersByName( nameTeacher ) );
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<Teacher> findTeacher( @PathVariable( "id" ) int idTeacher ) {
        return ResponseEntity.ok( teacherService.getTeacher( idTeacher ) );
    }

    @GetMapping( path = "/search/dni/{dni}" )
    public ResponseEntity<Teacher> searchTeacher( @PathVariable( "dni" ) String dniTeacher ) {
        return ResponseEntity.ok( teacherService.searchTeacher( dniTeacher ) );
    }

    @PostMapping( path = "/new" )
    public ResponseEntity<Teacher> registerTeacher( @Valid @RequestBody Teacher newTeacher ) {
        return ResponseEntity
                .status( CREATED )
                .body( teacherService.addNewTeacher( newTeacher ) );
    }

    @PutMapping( path = "/update/{id}" )
    public ResponseEntity<Teacher> modifyTeacher( @PathVariable( "id" ) int idTeacher,
                                                  @Valid @RequestBody Teacher teacher ) {
        return ResponseEntity.ok( teacherService.updateTeacher( idTeacher, teacher ) );
    }

    @PutMapping(path = "/activate/{id}")
    public ResponseEntity<Teacher> integrateTeacher(@PathVariable("id") int idTeacher) {
        return ResponseEntity.ok(teacherService.activateTeacher( idTeacher ));
    }

    @DeleteMapping( path = "/delete/{id}" )
    public ResponseEntity<Teacher> deleteTeacher( @PathVariable( "id" ) int idTeacher ) {
        return ResponseEntity.ok( teacherService.deleteTeacher( idTeacher ) );
    }
}
