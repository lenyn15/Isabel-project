package academic.system.person.controller;

import academic.system.person.entity.Tutor;
import academic.system.person.service.TutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping( path = "/tutor",
                 produces = "application/json" )
@CrossOrigin( origins = "*" )
public class TutorAPI {

    private final TutorService tutorService;

    @GetMapping( path = "/list" )
    public ResponseEntity<List<Tutor>> listAllTutors() {
        return ResponseEntity.ok( tutorService.listAll() );
    }

    @GetMapping( path = "/filter/{state}" )
    public ResponseEntity<List<Tutor>> filterByState( @PathVariable( "state" ) String stateTutor ) {
        return ResponseEntity.ok( tutorService.listByState( stateTutor ) );
    }

    @GetMapping( path = "/search/name/{name}" )
    public ResponseEntity<List<Tutor>> searchTutors( @PathVariable( "name" ) String nameTutor ) {
        return ResponseEntity.ok( tutorService.searchTutorsByName( nameTutor ) );
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<Tutor> findTutor( @PathVariable( "id" ) int idTutor ) {
        return ResponseEntity.ok( tutorService.getTutor( idTutor ) );
    }

    @GetMapping( path = "/search/dni/{dni}" )
    public ResponseEntity<Tutor> searchTutor( @PathVariable( "dni" ) String dniTutor ) {
        return ResponseEntity.ok( tutorService.searchTutor( dniTutor ) );
    }

    @PostMapping( path = "/new" )
    public ResponseEntity<Tutor> registerTutor( @Valid @RequestBody Tutor tutor ) {
        return ResponseEntity
                .status( CREATED )
                .body( tutorService.addNewTutor( tutor ) );
    }

    @PutMapping( path = "/update/{id}" )
    public ResponseEntity<Tutor> modifyTutor( @PathVariable( "id" ) int idTutor,
                                              @Valid @RequestBody Tutor tutor ) {
        return ResponseEntity.ok( tutorService.updateTutor( idTutor, tutor ) );
    }

    @PutMapping( path = "/activate/{id}" )
    public ResponseEntity<Tutor> integrateTutor( @PathVariable( "id" ) int idTutor ) {
        return ResponseEntity.ok( tutorService.activateTutor( idTutor ) );
    }

    @DeleteMapping( path = "/delete/{id}" )
    public ResponseEntity<Tutor> deleteTutor( @PathVariable( "id" ) int idTutor ) {
        return ResponseEntity.ok( tutorService.deleteTutor( idTutor ) );
    }
}
