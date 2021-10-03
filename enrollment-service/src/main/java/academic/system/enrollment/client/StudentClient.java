package academic.system.enrollment.client;

import academic.system.enrollment.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient( contextId = "studentCLient",
              name = "service-person" )
@RequestMapping( path = "/student",
                 produces = "application/json" )
public interface StudentClient {

    @GetMapping( path = "/{id}" )
    ResponseEntity<Student> findStudent( @PathVariable( "id" ) int idStudent );

    @PutMapping( path = "/register/{id}" )
    ResponseEntity<Student> registerStudent( @PathVariable( "id" ) int idStudent );
}
