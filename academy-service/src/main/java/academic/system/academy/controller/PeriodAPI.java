package academic.system.academy.controller;

import academic.system.academy.entity.AcademicPeriod;
import academic.system.academy.service.PeriodService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping( path = "/period",
                 produces = "application/json" )
@CrossOrigin( origins = "*" )
public class PeriodAPI {

    private final PeriodService periodService;

    @GetMapping( path = "/list" )
    public ResponseEntity<List<AcademicPeriod>> listAllPeriods() {
        return ResponseEntity.ok( periodService.getAll() );
    }

    @GetMapping( path = "/filter/{state}" )
    public ResponseEntity<List<AcademicPeriod>> filterByState( @PathVariable( "state" ) String statePeriod ) {
        return ResponseEntity.ok( periodService.listByState( statePeriod ) );
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<AcademicPeriod> findPeriod( @PathVariable( "id" ) int idPeriod ) {
        return ResponseEntity.ok( periodService.getPeriod( idPeriod ) );
    }

    @GetMapping( path = "/search/{year}" )
    public ResponseEntity<AcademicPeriod> searchByYear( @PathVariable( "year" ) String year ) {
        return ResponseEntity.ok( periodService.searchPeriod( year ) );
    }

    @PostMapping( path = "/new" )
    public ResponseEntity<AcademicPeriod> registerPeriod( @Valid @RequestBody AcademicPeriod period ) {
        return ResponseEntity
                .status( CREATED )
                .body( periodService.addPeriod( period ) );
    }

    @PutMapping( path = "/update/{id}" )
    public ResponseEntity<AcademicPeriod> modifyPeriod( @PathVariable( "id" ) int idPeriod,
                                                        @Valid @RequestBody AcademicPeriod period ) {
        return ResponseEntity.ok( periodService.updatePeriod( idPeriod, period ) );
    }

    @DeleteMapping( path = "/delete/{id}" )
    public ResponseEntity<AcademicPeriod> deletePeriod( @PathVariable( "id" ) int idPeriod ) {
        return ResponseEntity.ok( periodService.deletePeriod( idPeriod ) );
    }
}