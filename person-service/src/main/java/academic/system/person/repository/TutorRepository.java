package academic.system.person.repository;

import academic.system.person.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    Tutor findByDni( String dni );

    List<Tutor> findByName( String name );
}
