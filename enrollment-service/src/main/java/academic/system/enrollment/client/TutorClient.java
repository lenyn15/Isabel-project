package academic.system.enrollment.client;

import academic.system.enrollment.model.Tutor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient( contextId = "tutorClient",
              name = "service-person" )
@RequestMapping( path = "/tutor",
                 produces = "application/json" )
public interface TutorClient {

    @GetMapping( path = "/{id}" )
    ResponseEntity<Tutor> findTutor( @PathVariable( "id" ) int idTutor );
}
