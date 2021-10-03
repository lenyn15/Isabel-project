package academic.system.enrollment.repository;

import academic.system.enrollment.entity.UserPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserPerson, Integer> {
    List<UserPerson> findByName( String name );

    UserPerson findByDni( String dni );

    UserPerson findByUsername( String user );
}
