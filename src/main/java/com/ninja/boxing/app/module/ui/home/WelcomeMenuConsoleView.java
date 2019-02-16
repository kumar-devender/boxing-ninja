package com.ninja.boxing.app.module.ui.home;

import com.ninja.boxing.app.module.ui.component.AbstractConsoleView;
import com.ninja.boxing.app.module.ui.component.Menu;
import com.ninja.boxing.app.module.ui.home.WelcomeMenuView.ActionDelegate;;

/**
 * @author DEVENDER
 * reperesent Welcome console for user
 */
public class WelcomeMenuConsoleView extends AbstractConsoleView<ActionDelegate> implements WelcomeMenuView {

    /**
     * Main Menu Items
     */
    private Menu<MainMenuItem> menu ;

    public WelcomeMenuConsoleView(final Menu<MainMenuItem> menu) {
        super();
        this.menu = menu;
    }

    @Override
    public void draw() {
        menu.draw();
        switch (menu.chooseItem()) {
        case START:
            delegate.onStartChosen();
            break;
        case EXISTING_PROFILE:
            delegate.onContiWithExistingProfileChosen();
            break;
        case RESUME:
            delegate.onResumeChosen();
            break;
        case PROFILE:
            delegate.onviewProfileChosen();
            break;
        case EXIT:
            delegate.onExitChosen();
            break;
        default:
        }
    }

    /**
     * @author DEVENDER
     * Represent main menu items
     */
    public enum MainMenuItem {
        START("Start with new profile"),
        EXISTING_PROFILE("Conti with existing profile"),
        RESUME("Resume previous game"),
        PROFILE("View Profile"),
        EXIT("Exit");

        private final String title;

        MainMenuItem(final String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
