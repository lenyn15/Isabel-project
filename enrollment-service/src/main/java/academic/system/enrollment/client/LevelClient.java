package academic.system.enrollment.client;

import academic.system.enrollment.model.Level;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient( contextId = "levelClient",
              name = "service-academy" )
@RequestMapping( path = "/level",
                 produces = "application/json" )
public interface LevelClient {

    @GetMapping( path = "/{id}" )
    ResponseEntity<Level> findLevel( @PathVariable( "id" ) int idLevel );
}
