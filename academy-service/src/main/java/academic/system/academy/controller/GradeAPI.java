package academic.system.academy.controller;

import academic.system.academy.entity.Grade;
import academic.system.academy.entity.Level;
import academic.system.academy.service.GradeService;
import academic.system.academy.service.LevelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping( path = "/grade",
                 produces = "application/json" )
@CrossOrigin( origins = "*" )
public class GradeAPI {

    private final GradeService gradeService;
    private final LevelService levelService;

    @GetMapping( path = "/list" )
    public ResponseEntity<List<Grade>> listAllGrades() {
        return ResponseEntity.ok( gradeService.listAll() );
    }

    @GetMapping( path = "/list/level/{idL}" )
    public ResponseEntity<List<Grade>> listByLevel( @PathVariable( "idL" ) int idLevel ) {
        Level level = levelService.getLevel( idLevel );
        return ResponseEntity.ok( gradeService.filterByLevel( level ) );
    }

    @GetMapping( path = "/filter/{state}" )
    public ResponseEntity<List<Grade>> filterByState( @PathVariable( "state" ) String stateGrade ) {
        return ResponseEntity.ok( gradeService.filterByState( stateGrade ) );
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<Grade> findGrade( @PathVariable( "id" ) int idGrade ) {
        return ResponseEntity.ok( gradeService.getGrade( idGrade ) );
    }

    @PostMapping( path = "/new" )
    public ResponseEntity<Grade> addGrade( @Valid @RequestBody Grade grade ) {
        return ResponseEntity
                .status( CREATED )
                .body( gradeService.newGrade( grade ) );
    }

    @PutMapping( path = "/update/{idL}/{idG}" )
    public ResponseEntity<Grade> modifyGrade( @PathVariable( "idL" ) int idLevel,
                                              @PathVariable( "idG" ) int idGrade,
                                              @Valid @RequestBody Grade grade ) {
        Level level = levelService.getLevel( idLevel );
        return ResponseEntity.ok( gradeService.updateGrade( idGrade, grade, level ) );
    }

    @DeleteMapping( path = "/delete/{id}" )
    public ResponseEntity<Grade> deleteGrade( @PathVariable( "id" ) int idGrade ) {
        return ResponseEntity.ok( gradeService.deleteGrade( idGrade ) );
    }
}
