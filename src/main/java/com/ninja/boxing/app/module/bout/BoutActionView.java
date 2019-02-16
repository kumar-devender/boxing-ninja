package com.ninja.boxing.app.module.bout;

import static java.lang.String.format;

import com.ninja.boxing.app.module.constant.CommonEnum.BoutAction;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.AbstractConsoleView;
import com.ninja.boxing.app.module.ui.component.Menu;
import com.ninja.boxing.app.module.bout.BoutView.ActionDelegate;;

/**
 * @author DEVENDER
 * View for bout action. When user choose an option then will 
 * be delegated to implemented strategy
 */
public class BoutActionView extends AbstractConsoleView<ActionDelegate> implements BoutView {

    
    /**
     * User Bout action choice Menu.
     */
    private final Menu<BoutAction> menu;
    
    public BoutActionView(final Menu<BoutAction> menu) {
        super();
        this.menu = menu;
    }

    /* 
     * Bout Action draw. Based on user choice specific handler method will be called. 
     */
    @Override
    public void draw() {
        menu.draw();
        switch (menu.chooseItem()) {
        case ATTACK:
            delegate.onUserAttack();
            break;
        case DEFENSE:
            delegate.onUserDefend();
            break;
        case DO_NOTHING:
            delegate.onDoNothing();
            break;
        case SAVE_GAME:
            delegate.onsaveAndExit();
            break;
        default:
        }
    }

    /* 
     * Print User entity
     */
    @Override
    public void drawUser(final Playable user) {
        drawEntity(user);
    }

    /* 
     * Print enemy entity
     */
    @Override
    public void drawEnemy(final Playable enemy) {
        drawEntity(enemy);
    }

    /*
     * When Any boxer choose to attack.
     */
    @Override
    public void drawAttack(final Playable attacker,final  Playable defender, final int damage) {
        System.out.println(format(
                "%s attacks %s. %s got a damage - %d",
                attacker.getName(), defender.getName(), defender.getName(), damage
                ));
    }

    /**
     * @param entity
     * Print Boxer attributes
     */
    private void drawEntity(final Playable entity) {
        System.out.println(format("Name: %s;  Stance: %s;  Punching Power: %d;  Level: %s;  energy left: %d;", entity.getName(), entity.getStance(),
                entity.getMinPunchPower(), entity.getLevel(), entity.getEnergyLevel()));

    }


    /* 
     * When user choose to defence
     */
    @Override
    public void drawdefence(final Playable player) {
        System.out.println(format(" %s;  choose to defence", player.getName()));

    }

    /*
     * When user choose to do nothing and just watch the opponent action
     */
    @Override
    public void drawDoNothing(final Playable player) {
        System.out.println(format(" %s; choose to see opponent action.", player.getName()));

    }
}
