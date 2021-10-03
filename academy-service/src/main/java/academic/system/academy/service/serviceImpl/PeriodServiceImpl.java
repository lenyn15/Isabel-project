package academic.system.academy.service.serviceImpl;

import academic.system.academy.entity.AcademicPeriod;
import academic.system.academy.exception.ApiRequestException;
import academic.system.academy.repository.PeriodRepository;
import academic.system.academy.service.PeriodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;

    @Override
    public List<AcademicPeriod> getAll() {
        List<AcademicPeriod> periodList = periodRepository.findAll();
        if ( periodList.isEmpty() ) {
            throw new ApiRequestException( "No existe periodos registrados" );
        }
        return periodList;
    }

    @Override
    public List<AcademicPeriod> listByState( String state ) {
        return periodRepository
                .findAll()
                .stream()
                .filter( period -> Objects.equals( period.getState(), state ) )
                .collect( Collectors.toList() );
    }

    @Override
    public AcademicPeriod searchPeriod( String year ) {
        AcademicPeriod period = periodRepository.findByYear( year );
        if ( Objects.equals( period, null ) ) {
            throw new ApiRequestException( "No existe registro de ese periodo" );
        }
        return period;
    }

    @Override
    public AcademicPeriod getPeriod( int idPeriod ) {
        return periodRepository
                .findById( idPeriod )
                .orElse( null );
    }

    @Override
    public AcademicPeriod addPeriod( AcademicPeriod period ) {
        AcademicPeriod periodByYear = periodRepository.findByYear( period.getYear() );
        if ( !Objects.equals( periodByYear, null ) ) {
            throw new ApiRequestException( "Ya existe ese periodo, ingrese otro" );
        }
        period.setState( "ACTIVO" );
        return periodRepository.save( period );
    }

    @Transactional
    public AcademicPeriod updatePeriod( int idPeriod,
                                        AcademicPeriod period ) {
        AcademicPeriod periodDB = getPeriod( idPeriod );
        if ( !Objects.equals( periodDB.getYear(), period.getYear() ) ) {
            AcademicPeriod periodByYear = periodRepository.findByYear( period.getYear() );
            if ( !Objects.equals( periodByYear, null ) ) {
                throw new ApiRequestException( "Ya existe ese periodo, ingrese otro" );
            }
            periodDB.setYear( period.getYear() );
        }

        periodDB.setDate_begin( period.getDate_begin() );
        periodDB.setDate_end( period.getDate_end() );
        return periodDB;
    }

    @Transactional
    public AcademicPeriod deletePeriod( int idPeriod ) {
        AcademicPeriod period = getPeriod( idPeriod );
        period.setState( "INACTIVO" );
        return period;
    }
}
