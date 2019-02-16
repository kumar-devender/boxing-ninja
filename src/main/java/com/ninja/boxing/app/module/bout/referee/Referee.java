package com.ninja.boxing.app.module.bout.referee;

import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;
import com.ninja.boxing.app.module.model.Playable;

public interface Referee {

    void start();

    Playable getEnemy();

    Playable getUser();

    FightStatus getBoutStatus();

    void declareResult();

}
