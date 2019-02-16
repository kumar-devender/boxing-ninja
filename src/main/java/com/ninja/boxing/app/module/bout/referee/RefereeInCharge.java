package com.ninja.boxing.app.module.bout.referee;

import java.time.LocalDate;
import java.util.EnumSet;

import com.ninja.boxing.app.module.bout.strategy.BoutStrategy;
import com.ninja.boxing.app.module.constant.CommonEnum.BoxingTitle;
import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;
import com.ninja.boxing.app.module.model.Bout;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.utils.RandomSelectedEnumUtil;

/**
 * @author DEVENDER
 *
 */
public class RefereeInCharge implements Referee {


    /**
     * Bout strategy should be inject.
     */
    private final BoutStrategy boutStrategy;

    private final Playable user;

    private final Playable enemy;

    private Bout bout;

    public RefereeInCharge(final Playable enemy, final Playable user,
            final BoutStrategy boutStrategy) {
        this.boutStrategy = boutStrategy;
        this.user = user;
        this.enemy = enemy;
    }

    @Override
    public void start() {
        final BoxingTitle title = RandomSelectedEnumUtil.menu(EnumSet.allOf(BoxingTitle.class));
        this.bout = new Bout(LocalDate.now(), title);
        boutStrategy.start(this.user,this.enemy);
        this.user.getExperience().getBouts().add(bout);
        this.enemy.getExperience().getBouts().add(bout);
    }

    @Override
    public Playable getEnemy() {
        return this.enemy;
    }

    @Override
    public Playable getUser() {
        return user;
    }

    @Override
    public FightStatus getBoutStatus() {
        return this.boutStrategy.getBoutStatus();
    }

    @Override
    public void declareResult() {
        this.bout.setResult(user,enemy);
    }
}

