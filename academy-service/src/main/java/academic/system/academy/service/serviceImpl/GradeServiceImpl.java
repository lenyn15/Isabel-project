package academic.system.academy.service.serviceImpl;

import academic.system.academy.entity.Grade;
import academic.system.academy.entity.Level;
import academic.system.academy.exception.ApiRequestException;
import academic.system.academy.repository.GradeRepository;
import academic.system.academy.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    @Override
    public List<Grade> listAll() {
        List<Grade> gradeList = gradeRepository.findAll();
        if ( gradeList.isEmpty() ) {
            throw new ApiRequestException( "No hay ningun grado registrado, aun" );
        }
        return gradeList;
    }

    @Override
    public List<Grade> filterByLevel( Level level ) {
        List<Grade> gradeList = gradeRepository.findByLevel( level );
        if ( gradeList.isEmpty() ) {
            throw new ApiRequestException( "No hay ningun grado asignado para este nivel" );
        }
        return gradeList;
    }

    @Override
    public List<Grade> filterByState( String state ) {
        return gradeRepository
                .findAll()
                .stream()
                .filter( grade -> Objects.equals( grade.getState(), state ) )
                .collect( Collectors.toList() );
    }

    @Override
    public Grade getGrade( int id ) {
        return gradeRepository
                .findById( id )
                .orElse( null );
    }

    @Override
    public Grade newGrade( Grade grade ) {
        Grade gradeByName = gradeRepository.findByName( grade.getName() );
        if ( !Objects.equals( gradeByName, null ) ) {
            throw new ApiRequestException( "Ya existe un grado con ese nombre, ingrese otro" );
        }
        grade.setState( "DISPONIBLE" );
        return gradeRepository.save( grade );
    }

    @Transactional
    public Grade updateGrade( int id,
                              Grade grade,
                              Level level ) {
        Grade gradeDB = getGrade( id );
        if ( !Objects.equals( gradeDB.getName(), grade.getName() ) ) {
            Grade gradeByName = gradeRepository.findByName( grade.getName() );
            if ( !Objects.equals( gradeByName, null ) ) {
                throw new ApiRequestException( "Ya existe un grado con ese nombre, ingrese otro" );
            }
            gradeDB.setName( grade.getName() );
        }
        gradeDB.setLevel( level );
        return gradeDB;
    }

    @Transactional
    public Grade deleteGrade( int id ) {
        Grade grade = getGrade( id );
        grade.setState( "INACTIVO" );
        return grade;
    }
}
