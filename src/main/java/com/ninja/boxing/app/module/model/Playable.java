package com.ninja.boxing.app.module.model;

import java.util.List;

import com.ninja.boxing.app.module.constant.CommonEnum.Level;
import com.ninja.boxing.app.module.constant.CommonEnum.Stances;
import com.ninja.boxing.app.module.constant.CommonEnum.WeightCategory;


/**
 * @author DEVENDER
 * Interface represent operation of Boxer
 */
public interface Playable{

    Level getLevel();

    void setLevel(Level level);

    String getName();

    Stances getStance();

    int getEnergyLevel();

    int getMinPunchPower();

    List<WeightCategory> getWeightCategories() ;

    Experience getExperience() ;

    boolean canPlay();

    int isBeatenBy(Playable anotherEntity);

    void defense();

}
