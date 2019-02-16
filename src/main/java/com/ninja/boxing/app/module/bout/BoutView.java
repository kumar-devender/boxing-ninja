package com.ninja.boxing.app.module.bout;

import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.View;

public interface BoutView extends View<BoutView.ActionDelegate> {

    void drawUser(Playable user);

    void drawEnemy(Playable enemy);

    void drawdefence(Playable enemy);

    void drawDoNothing(Playable enemy);

    void drawAttack(Playable attacker, Playable defender, int damage);

    interface ActionDelegate {
        void onUserAttack();

        void onUserDefend();

        void onDoNothing();

        void onsaveAndExit();
    }
}
