package com.ninja.boxing.app.module.bout;

import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.View;

public interface NotificationView extends View {

    void showWinnerNotification(Playable palyer);

    void showPausedNotification();
}
