package academic.system.enrollment.client;

import academic.system.enrollment.model.Section;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient( contextId = "sectionClient",
              name = "service-academy" )
@RequestMapping( path = "/section",
                 produces = "application/json" )
public interface SectionClient {

    @GetMapping( path = "/{id}" )
    ResponseEntity<Section> findSection( @PathVariable( "id" ) int idSection );
}
