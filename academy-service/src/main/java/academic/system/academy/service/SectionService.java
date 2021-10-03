package academic.system.academy.service;

import academic.system.academy.entity.Grade;
import academic.system.academy.entity.Section;

import java.util.List;

public interface SectionService {

    List<Section> listAll();

    List<Section> filterByGrade( Grade grade );

    Section getSection( int id );

    Section newSection( Section section );

    Section updateSection( int id,
                           Section section,
                           Grade grade );
}
