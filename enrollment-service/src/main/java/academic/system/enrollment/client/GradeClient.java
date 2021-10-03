package academic.system.enrollment.client;

import academic.system.enrollment.model.Grade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient( contextId = "gradeClient",
              name = "service-academy" )
@RequestMapping( path = "/grade",
                 produces = "application/json" )
public interface GradeClient {

    @GetMapping( path = "/{id}" )
    ResponseEntity<Grade> findGrade( @PathVariable( "id" ) int idGrade );
}
