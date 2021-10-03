package academic.system.person.repository;

import academic.system.person.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByDni( String dni );

    List<Student> findByName( String name );
}
