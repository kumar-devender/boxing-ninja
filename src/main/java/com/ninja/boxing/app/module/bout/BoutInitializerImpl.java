package com.ninja.boxing.app.module.bout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import com.ninja.boxing.app.module.bout.EnemyAttributes.Names;
import com.ninja.boxing.app.module.bout.factory.BoxerFactory;
import com.ninja.boxing.app.module.bout.referee.Referee;
import com.ninja.boxing.app.module.bout.referee.RefereeInCharge;
import com.ninja.boxing.app.module.bout.strategy.BoutStrategy;
import com.ninja.boxing.app.module.constant.CommonConstant;
import com.ninja.boxing.app.module.constant.CommonEnum.PunchingPower;
import com.ninja.boxing.app.module.constant.CommonEnum.Stances;
import com.ninja.boxing.app.module.constant.CommonEnum.WeightCategory;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.model.Player;
import com.ninja.boxing.app.module.model.Player.PlayerBuilder;
import com.ninja.boxing.app.module.serializer.SerializationManager;
import com.ninja.boxing.app.module.utils.RandomIntInRangeUtil;
import com.ninja.boxing.app.module.utils.RandomSelectedEnumUtil;

/**
 * @author DEVENDER
 * This class is responsible for initializing bout participant.
 */
public class BoutInitializerImpl implements BoutInitializer{

    /**
     * Represent factory of boxer.
     */
    private final BoxerFactory boxerFactory;

    /**
     * Represent bout strategy. This must be injected in constructor. 
     */
    private final BoutStrategy boutStrategy;

    /**
     * Represent enemy boxer list.
     */
    private List<Playable> enemies;

    /**
     * Represent user boxer.
     */
    private Playable user;

    /**
     * Represent enemy boxer.
     */
    private Playable enemy;

    public BoutInitializerImpl(final BoxerFactory newPlayerFactory, final BoutStrategy boutStrategy){
        this.boxerFactory = newPlayerFactory;
        this.boutStrategy = boutStrategy;
    }

    /* 
     * To create new bout participant  
     */
    @Override
    public Optional<Referee> create() {
        this.user = boxerFactory.getPlayer();
        this.enemies = createEnemyPlayer();
        this.enemy = this.enemies.get(RandomIntInRangeUtil.get(0, this.enemies.size()));
        return Optional.of(new RefereeInCharge(this.enemy,this.user, boutStrategy));
    }

    /* 
     * To load bout which was being paused and saved to play later.
     */
    @Override
    public Optional<Referee> load() {
        try {
            this.user = (Player)SerializationManager.deSerialize(CommonConstant.USER_PLAYER);
            this.enemy = (Player) SerializationManager.deSerialize(CommonConstant.ENEMY_PLAYER);
            if(this.enemies == null) {
                this.enemies = createEnemyPlayer();
            }
            return Optional.of(new RefereeInCharge(enemy,this.user, boutStrategy));
        } catch (ClassNotFoundException | IOException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }

    /* 
     * Create New Enemy for new bout
     */
    @Override
    public Optional<Referee> createNewEnemy() {
        if(this.enemies != null) {
            this.enemy = this.enemies.get(RandomIntInRangeUtil.get(0, this.enemies.size()));
            return Optional.of(new RefereeInCharge(this.enemy,this.user, boutStrategy));
        }
            return Optional.empty();
    }

    /**
     * @return list
     * 
     * It create enemy boxer. Only once when game is initialized. After that the same enemy list will be used for different games. 
     */
    private List<Playable> createEnemyPlayer() {
        final List<Playable> list = new ArrayList<>();
        PlayerBuilder builder = null;
        for(final Names name :Names.values()) {
            final PunchingPower punchingPower = RandomSelectedEnumUtil.menu(EnumSet.allOf(PunchingPower.class));
            builder = new PlayerBuilder(name.toString(),punchingPower.getValue());
            builder.setStance(getRandomStance());
            builder.setWeightCategories(getWeightCategory());
            list.add(builder.build());
        }
        return list;
    }

    private List<WeightCategory> getWeightCategory() {
        final int maxIndex = WeightCategory.values().length;
        final List<WeightCategory> list = new ArrayList<>();
        list.add(WeightCategory.values()[RandomIntInRangeUtil.get(0, maxIndex)]);
        return list;
    }

    private Stances getRandomStance() {
        final int maxIndex = Stances.values().length;
        return Stances.values()[RandomIntInRangeUtil.get(0, maxIndex)];
    }

    /* 
     * Return user 
     */
    @Override
    public Optional<Playable> getUser() {
        if(this.user != null) {
            return Optional.of(this.user);
        }
        return Optional.empty();
    }

}
