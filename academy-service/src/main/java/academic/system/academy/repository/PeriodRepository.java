package academic.system.academy.repository;

import academic.system.academy.entity.AcademicPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends JpaRepository<AcademicPeriod, Integer> {
    AcademicPeriod findByYear( String year );
}
