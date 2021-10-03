package academic.system.academy.repository;

import academic.system.academy.entity.Grade;
import academic.system.academy.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    List<Section> findByGrade( Grade grade );
}
