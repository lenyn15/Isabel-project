package academic.system.academy.service;

import academic.system.academy.entity.AcademicPeriod;

import java.util.List;

public interface PeriodService {
    List<AcademicPeriod> getAll();

    List<AcademicPeriod> listByState( String state );

    AcademicPeriod searchPeriod( String year );

    AcademicPeriod getPeriod( int idPeriod );

    AcademicPeriod addPeriod( AcademicPeriod period );

    AcademicPeriod updatePeriod( int idPeriod,
                                 AcademicPeriod period );

    AcademicPeriod deletePeriod( int idPeriod );
}
