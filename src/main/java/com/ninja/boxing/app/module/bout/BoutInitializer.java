package com.ninja.boxing.app.module.bout;

import java.util.Optional;

import com.ninja.boxing.app.module.bout.referee.Referee;
import com.ninja.boxing.app.module.model.Playable;

/**
 * @author DEVENDER
 * This class is responsible for initializing bout participant.
 */
public interface BoutInitializer {
    
    /**
     * @return create referee instance for the first user bout.
     */
    Optional<Referee> create();

    /**
     * @return load saved game from file then create and return Referee instance  
     */
    Optional<Referee> load();

    /**
     * @return create referee instance when user choose to play next Bout 
     * with same profile and return than instance. 
     * It uses choose random enemy from existing enemy list.  
     */
    Optional<Referee> createNewEnemy();

    /**
     * @return user
     */
    Optional<Playable> getUser();
}
