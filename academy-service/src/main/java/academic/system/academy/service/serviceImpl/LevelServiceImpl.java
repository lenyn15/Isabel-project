package academic.system.academy.service.serviceImpl;

import academic.system.academy.entity.Level;
import academic.system.academy.exception.ApiRequestException;
import academic.system.academy.repository.LevelRepository;
import academic.system.academy.service.LevelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Override
    public List<Level> listAll() {
        List<Level> levelList = levelRepository.findAll();
        if ( levelList.isEmpty() ) {
            throw new ApiRequestException( "No hay ningun nivel registrado, aun" );
        }
        return levelList;
    }

    @Override
    public Level getLevel( int id ) {
        return levelRepository
                .findById( id )
                .orElse( null );
    }

    @Override
    public Level addNewLevel( Level level ) {
        Level levelByName = levelRepository.findByName( level.getName() );
        if ( !Objects.equals( levelByName, null ) ) {
            throw new ApiRequestException( "Ya existe un nivel con ese nombre, ingrese otro" );
        }
        return levelRepository.save( level );
    }

    @Transactional
    public Level updateLevel( int id,
                              Level level ) {
        Level levelDB = getLevel( id );
        if ( !Objects.equals( levelDB.getName(), level.getName() ) ) {
            Level levelByName = levelRepository.findByName( level.getName() );
            if ( !Objects.equals( levelByName, null ) ) {
                throw new ApiRequestException( "Ya existe un nivel con ese nombre, ingrese otro" );
            }
            levelDB.setName( level.getName() );
        }
        return levelDB;
    }
}
