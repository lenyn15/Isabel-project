package academic.system.academy.repository;

import academic.system.academy.entity.Course;
import academic.system.academy.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByLevel( Level level );

    List<Course> findByName( String name );
}
