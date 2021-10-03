package academic.system.person.repository;

import academic.system.person.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByDni(String dni);

    List<Teacher> findByName( String name );
}
