package academic.system.person.repository;

import academic.system.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByDni( String dni );

    Person findByEmail( String email );
}
