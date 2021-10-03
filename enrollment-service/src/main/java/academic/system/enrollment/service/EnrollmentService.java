package academic.system.enrollment.service;

import academic.system.enrollment.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {

    List<Enrollment> listAll();

    Enrollment getEnrollment( int id );

    Enrollment newEnrollment( Enrollment enrollment );

    Enrollment updateEnrollment( int id,
                                 Enrollment enrollment );
}
