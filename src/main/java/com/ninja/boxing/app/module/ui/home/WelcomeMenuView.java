package com.ninja.boxing.app.module.ui.home;

import com.ninja.boxing.app.module.ui.component.View;

/**
 * @author DEVENDER
 *Welcome view 
 */
public interface WelcomeMenuView extends View<WelcomeMenuView.ActionDelegate> {
    interface ActionDelegate {
        
        /**
         * When user choose to create new game with new profile.
         */
        void onStartChosen();

        /**
         * When user choose to play new game with same profile
         */
        void onContiWithExistingProfileChosen();

        /**
         * When user choose to resume the game that was saved
         */
        void onResumeChosen();

        /**
         * When user choose to see profile
         */
        void onviewProfileChosen();
        
        void onExitChosen();
    }
}
