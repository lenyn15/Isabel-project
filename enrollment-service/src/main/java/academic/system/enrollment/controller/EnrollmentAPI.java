package academic.system.enrollment.controller;

import academic.system.enrollment.entity.Enrollment;
import academic.system.enrollment.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping( path = "/enrollment",
                 produces = "application/json" )
@CrossOrigin( origins = "*" )
public class EnrollmentAPI {

    private final EnrollmentService enrollmentService;

    @GetMapping( path = "/list" )
    public ResponseEntity<List<Enrollment>> listAllEnrollments() {
        return ResponseEntity.ok( enrollmentService.listAll() );
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<Enrollment> findEnrollment( @PathVariable( "id" ) int idEnrollment ) {
        return ResponseEntity.ok( enrollmentService.getEnrollment( idEnrollment ) );
    }

    @PostMapping( path = "/new" )
    public ResponseEntity<Enrollment> addEnrollment( @Valid @RequestBody Enrollment enrollment ) {
        return ResponseEntity
                .status( CREATED )
                .body( enrollmentService.newEnrollment( enrollment ) );
    }

    @PutMapping( path = "/update/{id}" )
    public ResponseEntity<Enrollment> modifyEnrollment( @PathVariable( "id" ) int idEnrollment,
                                                        @Valid @RequestBody Enrollment enrollment ) {
        return ResponseEntity.ok( enrollmentService.updateEnrollment( idEnrollment, enrollment ) );
    }
}
