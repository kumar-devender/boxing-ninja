package com.ninja.boxing.app.module.bout.strategy;

import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;
import com.ninja.boxing.app.module.model.Playable;

/**
 * @author DEVENDER
 * Bout strategy implementation need to implement this interface. 
 */
public interface BoutStrategy {
    /**
     * @param user
     * @param enemy
     */
    void start(Playable user, Playable enemy);
    /**
     * @return
     * Return bout status
     */
    FightStatus getBoutStatus();
}
