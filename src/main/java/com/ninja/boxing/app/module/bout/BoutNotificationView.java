package com.ninja.boxing.app.module.bout;

import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.AbstractConsoleView;

public class BoutNotificationView extends AbstractConsoleView implements NotificationView {


    @Override
    public void showWinnerNotification(final Playable palyer) {
        System.out.println(palyer.getName() +" won this bout.");
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("This method is not supported");
    }


    @Override
    public void showPausedNotification() {
        System.out.println("This fight has been paused for now.");

    }
}
