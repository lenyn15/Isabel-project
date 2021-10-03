package academic.system.enrollment.repository;

import academic.system.enrollment.entity.CommitmentPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitmentRepository extends JpaRepository<CommitmentPayments, Integer> {
}
