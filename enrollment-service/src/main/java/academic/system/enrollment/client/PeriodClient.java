package academic.system.enrollment.client;

import academic.system.enrollment.model.AcademicPeriod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient( contextId = "periodClient",
              name = "service-academy" )
@RequestMapping( path = "/period",
                 produces = "application/json" )
public interface PeriodClient {

    @GetMapping( path = "/{id}" )
    ResponseEntity<AcademicPeriod> findPeriod( @PathVariable( "id" ) int idPeriod );
}
