package com.ninja.boxing.app.module.bout;

import com.ninja.boxing.app.module.bout.referee.Referee;
import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;
import com.ninja.boxing.app.module.model.Playable;

public class BoutOrganizerImpl implements BoutOrganizer {

    private final Referee referee;

    private final NotificationView view;

    public BoutOrganizerImpl(final Referee referee, final NotificationView view) {
        this.referee = referee;
        this.view = view;
    }

    @Override
    public void start() {
        referee.start();
        showNotification();
    }

    private void showNotification() {
        final FightStatus fightStatus = this.referee.getBoutStatus();
        switch (fightStatus) {
        case COMPLETED:
            showWinnerNotification();
            break;
        case SAVED:
            view.showPausedNotification();
            break;
        default:
            break;
        }
    }
    
    private void showWinnerNotification() {
        final Playable enemy = this.referee.getEnemy();
        final Playable user = this.referee.getUser();
        Playable winner = null;
        referee.declareResult();
        if(user!= null && enemy!=null) {
            if(user.getEnergyLevel() > enemy.getEnergyLevel()) {
                winner = user;
            }else {
                winner = enemy;
            }
            view.showWinnerNotification(winner);
        }
    }
}
