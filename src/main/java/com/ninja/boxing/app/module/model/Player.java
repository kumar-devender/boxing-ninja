package com.ninja.boxing.app.module.model;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;

import com.ninja.boxing.app.module.constant.CommonEnum.Defence;
import com.ninja.boxing.app.module.constant.CommonEnum.Level;
import com.ninja.boxing.app.module.constant.CommonEnum.Stances;
import com.ninja.boxing.app.module.constant.CommonEnum.WeightCategory;
import com.ninja.boxing.app.module.utils.RandomSelectedEnumUtil;

/**
 * @author DEVENDER
 * Class represent player in the system. This class represent both system player and user player.
 * Instance of this class is being created with Builder. There can be other optional fields that can added later with the builder without breaking any client code.
 * There are some fields that are required they need to pass with builder constructor.
 *
 */
public class Player implements Playable,Serializable{
    /**
     * Serial Version ID
     */
    private static final long serialVersionUID = -6259684525671102916L;

    private final String name;

    private final Stances stance; 

    private int energyLevel;

    private final int minPunchPower;

    private final List<WeightCategory> weightCategories;

    private final Experience experience;

    private Level level;

    private Defence defence;

    private Player(final String name, final Stances stance, final int minPunchPower,
            final List<WeightCategory> weightCategories) {
        this.name = name;
        this.stance = stance;
        this.minPunchPower = minPunchPower;
        this.weightCategories = weightCategories;
        this.level = Level.Level1;
        this.experience = new Experience();
        this.energyLevel = 100;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(final Level level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public Stances getStance() {
        return stance;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public int getMinPunchPower() {
        return minPunchPower;
    }

    public List<WeightCategory> getWeightCategories() {
        return weightCategories;
    }

    public Experience getExperience() {
        return experience;
    }

    public static class PlayerBuilder{
        private String name;

        private Stances stance; 

        private int minPunchPower;

        private List<WeightCategory> weightCategories;

        public PlayerBuilder(final String name, final int minPunchPower) {
            this.name = name;
            this.minPunchPower = minPunchPower;
        }

        public void setStance(final Stances stance) {
            this.stance = stance;
        }

        public void setWeightCategories(final List<WeightCategory> weightCategories) {
            this.weightCategories = weightCategories;
        }


        public Playable build() {
            Player player = new Player(name, stance, minPunchPower, weightCategories);
            if(name == null || "".equals(name.trim())) {
                throw new IllegalArgumentException("Name can not be null or empty.");
            }
            return player;
        }
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", stance=" + stance + ", energyLevel=" + energyLevel + ""
                + ", minPunchPower=" + minPunchPower + ", "
                + "weightCategories=" + weightCategories + ", experience=" + experience
                + ", level=" + level + "]";
    }


    @Override
    public boolean canPlay() {
        return energyLevel >0;
    }

    @Override
    public int isBeatenBy(final Playable playable) {
        int hittingForce = playable.getMinPunchPower();
        if(this.defence != null) {
            hittingForce = (int)(hittingForce*this.defence.getValue());
            this.defence = null;
        }

        if(hittingForce <this.energyLevel) {
            this.energyLevel -=hittingForce;
            return hittingForce;
        }

        this.energyLevel = 0;

        return hittingForce;
    }

    @Override
    public void defense() {
        this.defence = RandomSelectedEnumUtil.menu(EnumSet.allOf(Defence.class));
    }

}
