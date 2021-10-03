package academic.system.academy.client;

import academic.system.academy.model.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient( name = "service-person" )
@RequestMapping( path = "/teacher",
                 produces = "application/json" )
public interface TeacherClient {

    @GetMapping( path = "/{id}" )
    ResponseEntity<Teacher> findTeacher( @PathVariable( "id" ) int idTeacher );
}
