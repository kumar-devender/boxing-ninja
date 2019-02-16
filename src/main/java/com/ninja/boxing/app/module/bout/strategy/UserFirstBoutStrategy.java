package com.ninja.boxing.app.module.bout.strategy;

import java.io.IOException;
import java.util.EnumSet;

import com.ninja.boxing.app.module.bout.BoutView;
import com.ninja.boxing.app.module.constant.CommonConstant;
import com.ninja.boxing.app.module.constant.CommonEnum.BoutAction;
import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.serializer.SerializationManager;
import com.ninja.boxing.app.module.ui.component.AbstractPresenter;
import com.ninja.boxing.app.module.utils.RandomSelectedEnumUtil;

/**
 * @author DEVENDER
 * UserFirstBoutStrategy indicate that first action preference will be given to user. We can replace it by different strategy. 
 * Strategy need to be injected.
 */
public class UserFirstBoutStrategy extends AbstractPresenter<BoutView> implements BoutStrategy, BoutView.ActionDelegate {

    private Playable user;

    private Playable enemy;

    private FightStatus fightStatus;

    public UserFirstBoutStrategy(final  BoutView view) {
        super(view);
        this.view.setDelegate(this);
    }

    @Override
    public void start(final Playable user, final Playable enemy) {
        this.user = user;
        this.enemy = enemy;
        startAndUpdateStatus();
    }

    private void startAndUpdateStatus() {
        this.fightStatus = FightStatus.IN_PROGRESS;
        nextIteration();
        if(this.fightStatus != FightStatus.SAVED) {
            this.fightStatus = FightStatus.COMPLETED;
        }
    }

    private void nextIteration() {
        if (user.canPlay() && enemy.canPlay()) {
            view.drawUser(user);
            view.drawEnemy(enemy);
            show();
        }
    }

    /**
     * Random enemy action.
     */
    private void enemyBoutAction() {
        final  EnumSet<BoutAction> boutAction = EnumSet.allOf(BoutAction.class);
        boutAction.remove(BoutAction.SAVE_GAME);
        //Enemy do not have option to stop game 
        final  BoutAction action = RandomSelectedEnumUtil.menu(boutAction);
        switch (action) {
        case ATTACK:
            view.drawAttack(enemy, user, user.isBeatenBy(enemy));
            break;
        case DEFENSE:
            enemy.defense();
            view.drawdefence(enemy);
            break;
        case DO_NOTHING:
            view.drawDoNothing(enemy);
            break;
        default:
            throw new IllegalArgumentException("No Action specified for input" + action);
        }
    }

    @Override
    public void onUserAttack() {
        view.drawAttack(user, enemy, enemy.isBeatenBy(user));
        if(!enemy.canPlay()) {
            return;
        }
        enemyBoutAction();
        nextIteration();
    }

    @Override
    public void onUserDefend() {
        user.defense();
        view.drawdefence(user);
        enemyBoutAction();
        nextIteration();
    }

    @Override
    public void onDoNothing() {
        view.drawDoNothing(user);
        enemyBoutAction();
        nextIteration();
    }

    /* 
     */
    @Override
    public void onsaveAndExit() {
        try {
            SerializationManager.serialize(CommonConstant.USER_PLAYER, user);
            SerializationManager.serialize(CommonConstant.ENEMY_PLAYER, enemy);
            this.fightStatus = FightStatus.SAVED;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 
     */
    @Override
    public FightStatus getBoutStatus() {
        return fightStatus;
    }

}
