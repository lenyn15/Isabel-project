package academic.system.academy.controller;

import academic.system.academy.entity.Level;
import academic.system.academy.service.LevelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping( path = "/level",
                 produces = "application/json" )
@CrossOrigin( origins = "*" )
public class LevelAPI {

    private final LevelService levelService;

    @GetMapping( path = "/list" )
    public ResponseEntity<List<Level>> listAllLevels() {
        return ResponseEntity.ok( levelService.listAll() );
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<Level> findLevel( @PathVariable( "id" ) int idLevel ) {
        return ResponseEntity.ok( levelService.getLevel( idLevel ) );
    }

    @PostMapping( path = "/new" )
    public ResponseEntity<Level> addLevel( @Valid @RequestBody Level newLevel ) {
        return ResponseEntity
                .status( CREATED )
                .body( levelService.addNewLevel( newLevel ) );
    }

    @PutMapping( path = "/update/{id}" )
    public ResponseEntity<Level> modifyLevel( @PathVariable( "id" ) int idLevel,
                                              @Valid @RequestBody Level level ) {
        return ResponseEntity.ok( levelService.updateLevel( idLevel, level ) );
    }
}
