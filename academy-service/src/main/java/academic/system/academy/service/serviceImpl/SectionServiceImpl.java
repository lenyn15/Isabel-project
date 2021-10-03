package academic.system.academy.service.serviceImpl;

import academic.system.academy.entity.Grade;
import academic.system.academy.entity.Section;
import academic.system.academy.exception.ApiRequestException;
import academic.system.academy.repository.SectionRepository;
import academic.system.academy.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Override
    public List<Section> listAll() {
        List<Section> sectionList = sectionRepository.findAll();
        if ( sectionList.isEmpty() ) {
            throw new ApiRequestException( "No hay registro de secciones" );
        }
        return sectionList;
    }

    @Override
    public List<Section> filterByGrade( Grade grade ) {
        List<Section> sectionList = sectionRepository.findByGrade( grade );
        if ( sectionList.isEmpty() ) {
            throw new ApiRequestException( "No hay ninguna seccion asignada para este Grado" );
        }
        return sectionList;
    }

    @Override
    public Section getSection( int id ) {
        return sectionRepository
                .findById( id )
                .orElse( null );
    }

    @Override
    public Section newSection( Section section ) {
        return sectionRepository.save( section );
    }

    @Transactional
    public Section updateSection( int id,
                                  Section section,
                                  Grade grade ) {
        Section sectionDB = getSection( id );
        sectionDB.setLetter( section.getLetter() );
        sectionDB.setCapacity( section.getCapacity() );
        sectionDB.setGrade( grade );
        return sectionDB;
    }
}
