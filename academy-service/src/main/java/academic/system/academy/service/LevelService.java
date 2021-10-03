package academic.system.academy.service;

import academic.system.academy.entity.Level;

import java.util.List;

public interface LevelService {
    List<Level> listAll();

    Level getLevel( int id );

    Level addNewLevel( Level level );

    Level updateLevel( int id,
                       Level level );
}
