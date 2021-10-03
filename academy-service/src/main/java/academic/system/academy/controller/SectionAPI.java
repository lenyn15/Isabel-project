package academic.system.academy.controller;

import academic.system.academy.entity.Grade;
import academic.system.academy.entity.Section;
import academic.system.academy.service.GradeService;
import academic.system.academy.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping( path = "/section",
                 produces = "application/json" )
@CrossOrigin( origins = "*" )
public class SectionAPI {

    private final SectionService sectionService;
    private final GradeService gradeService;

    @GetMapping( path = "/list" )
    public ResponseEntity<List<Section>> listAllSections() {
        return ResponseEntity.ok( sectionService.listAll() );
    }

    @GetMapping( path = "/list/grade/{id}" )
    public ResponseEntity<List<Section>> listByGrade( @PathVariable( "id" ) int idGrade ) {
        List<Section> sectionList = sectionService.filterByGrade( gradeService.getGrade( idGrade ) );
        return ResponseEntity.ok( sectionList );
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<Section> findSection( @PathVariable( "id" ) int idSection ) {
        return ResponseEntity.ok( sectionService.getSection( idSection ) );
    }

    @PostMapping( path = "/new" )
    public ResponseEntity<Section> addSection( @Valid @RequestBody Section newSection ) {
        return ResponseEntity
                .status( CREATED )
                .body( sectionService.newSection( newSection ) );
    }

    @PutMapping( path = "/update/{idG}/{idS}" )
    public ResponseEntity<Section> modifySection( @PathVariable( "idG" ) int idGrade,
                                                  @PathVariable( "idS" ) int idSection,
                                                  @Valid @RequestBody Section section ) {
        Grade grade = gradeService.getGrade( idGrade );
        return ResponseEntity.ok( sectionService.updateSection( idSection, section, grade ) );
    }
}
