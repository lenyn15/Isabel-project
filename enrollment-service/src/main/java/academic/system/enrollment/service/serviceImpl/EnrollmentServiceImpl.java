package academic.system.enrollment.service.serviceImpl;

import academic.system.enrollment.client.*;
import academic.system.enrollment.entity.Enrollment;
import academic.system.enrollment.exception.ApiRequestException;
import academic.system.enrollment.model.*;
import academic.system.enrollment.repository.EnrollmentRepository;
import academic.system.enrollment.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    private final StudentClient studentClient;

    @Autowired
    private final TutorClient tutorClient;

    @Autowired
    private final PeriodClient periodClient;

    @Autowired
    private final LevelClient levelClient;

    @Autowired
    private final GradeClient gradeClient;

    @Autowired
    private final SectionClient sectionClient;

    @Override
    public List<Enrollment> listAll() {
        List<Enrollment> enrollmentList = enrollmentRepository.findAll();
        if ( enrollmentList.isEmpty() ) {
            throw new ApiRequestException( "No hay matriculas registradas, aun" );
        }
        for (Enrollment enrollment : enrollmentList) {
            Student student = studentClient
                    .findStudent( enrollment.getStudentId() )
                    .getBody();
            Tutor tutor = tutorClient
                    .findTutor( enrollment.getTutorId() )
                    .getBody();
            AcademicPeriod period = periodClient
                    .findPeriod( enrollment.getPeriodId() )
                    .getBody();
            Level level = levelClient
                    .findLevel( enrollment.getLevelId() )
                    .getBody();
            Grade grade = gradeClient
                    .findGrade( enrollment.getGradeId() )
                    .getBody();
            Section section = sectionClient
                    .findSection( enrollment.getSectionId() )
                    .getBody();

            enrollment.setStudent( student );
            enrollment.setTutor( tutor );
            enrollment.setPeriod( period );
            enrollment.setLevel( level );
            enrollment.setGrade( grade );
            enrollment.setSection( section );
        }
        return enrollmentList;
    }

    @Override
    public Enrollment getEnrollment( int id ) {
        Enrollment enrollment = enrollmentRepository
                .findById( id )
                .orElse( null );
        if ( !Objects.equals( enrollment, null ) ) {
            Student student = studentClient
                    .findStudent( enrollment.getStudentId() )
                    .getBody();
            Tutor tutor = tutorClient
                    .findTutor( enrollment.getTutorId() )
                    .getBody();
            AcademicPeriod period = periodClient
                    .findPeriod( enrollment.getPeriodId() )
                    .getBody();
            Level level = levelClient
                    .findLevel( enrollment.getLevelId() )
                    .getBody();
            Grade grade = gradeClient
                    .findGrade( enrollment.getGradeId() )
                    .getBody();
            Section section = sectionClient
                    .findSection( enrollment.getSectionId() )
                    .getBody();

            enrollment.setStudent( student );
            enrollment.setTutor( tutor );
            enrollment.setPeriod( period );
            enrollment.setLevel( level );
            enrollment.setGrade( grade );
            enrollment.setSection( section );
        }
        return enrollment;
    }

    @Override
    public Enrollment newEnrollment( Enrollment enrollment ) {
        Student student = studentClient
                .findStudent( enrollment.getStudentId() )
                .getBody();
        Tutor tutor = tutorClient
                .findTutor( enrollment.getTutorId() )
                .getBody();
        AcademicPeriod period = periodClient
                .findPeriod( enrollment.getPeriodId() )
                .getBody();
        Level level = levelClient
                .findLevel( enrollment.getLevelId() )
                .getBody();
        Grade grade = gradeClient
                .findGrade( enrollment.getGradeId() )
                .getBody();
        Section section = sectionClient
                .findSection( enrollment.getSectionId() )
                .getBody();
        studentClient.registerStudent( enrollment.getStudentId() );

        enrollment.setStudent( student );
        enrollment.setTutor( tutor );
        enrollment.setPeriod( period );
        enrollment.setLevel( level );
        enrollment.setGrade( grade );
        enrollment.setSection( section );
        enrollment.setFees_number( 8 );
        return enrollmentRepository.save( enrollment );
    }

    @Transactional
    public Enrollment updateEnrollment( int id,
                                        Enrollment enrollment ) {
        Student student = studentClient
                .findStudent( enrollment.getStudentId() )
                .getBody();
        Tutor tutor = tutorClient
                .findTutor( enrollment.getTutorId() )
                .getBody();
        AcademicPeriod period = periodClient
                .findPeriod( enrollment.getPeriodId() )
                .getBody();
        Level level = levelClient
                .findLevel( enrollment.getLevelId() )
                .getBody();
        Grade grade = gradeClient
                .findGrade( enrollment.getGradeId() )
                .getBody();
        Section section = sectionClient
                .findSection( enrollment.getSectionId() )
                .getBody();

        Enrollment enrollmentDB = getEnrollment( id );
        enrollmentDB.setRepeating( enrollment.getRepeating() );
        enrollmentDB.setShift( enrollment.getShift() );
        enrollmentDB.setFees_number( enrollment.getFees_number() );
        enrollmentDB.setDetail( enrollment.getDetail() );
        enrollmentDB.setStudent( student );
        enrollmentDB.setTutor( tutor );
        enrollmentDB.setPeriod( period );
        enrollmentDB.setLevel( level );
        enrollmentDB.setGrade( grade );
        enrollmentDB.setSection( section );
        return enrollmentDB;
    }
}
