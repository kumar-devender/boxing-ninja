package com.ninja.boxing.app.module.config;

import com.ninja.boxing.app.module.bout.BoutActionView;
import com.ninja.boxing.app.module.bout.BoutInitializer;
import com.ninja.boxing.app.module.bout.BoutInitializerImpl;
import com.ninja.boxing.app.module.bout.BoutNotificationView;
import com.ninja.boxing.app.module.bout.BoutOrganizerFactory;
import com.ninja.boxing.app.module.bout.BoutOrganizerFactoryImpl;
import com.ninja.boxing.app.module.bout.NotificationView;
import com.ninja.boxing.app.module.bout.factory.BoxerConsoleView;
import com.ninja.boxing.app.module.bout.factory.BoxerPresenter;
import com.ninja.boxing.app.module.bout.strategy.BoutStrategy;
import com.ninja.boxing.app.module.bout.strategy.UserFirstBoutStrategy;
import com.ninja.boxing.app.module.constant.CommonEnum.BoutAction;
import com.ninja.boxing.app.module.constant.CommonEnum.PunchingPower;
import com.ninja.boxing.app.module.constant.CommonEnum.Stances;
import com.ninja.boxing.app.module.constant.CommonEnum.WeightCategory;
import com.ninja.boxing.app.module.ui.component.Menu;
import com.ninja.boxing.app.module.ui.component.TextInput;
import com.ninja.boxing.app.module.ui.home.WelcomeMenuConsoleView;
import com.ninja.boxing.app.module.ui.home.WelcomeMenuPresenter;
import com.ninja.boxing.app.module.ui.home.WelcomeMenuConsoleView.MainMenuItem;

/**
 * @author DEVENDER
 * Heart of application. All the configuration can be managed from here. If we want to change strategy just inject from here. 
 */
public final class AppConfiguration {

    private AppConfiguration() {

    }

    public static void welcome() {
        final Menu<MainMenuItem> menu = new Menu<>("Welcome", MainMenuItem.values());
        new WelcomeMenuPresenter(new WelcomeMenuConsoleView(menu), boutOrganizerFactory()).show();
    }

    private static BoutOrganizerFactory boutOrganizerFactory() {
        return new BoutOrganizerFactoryImpl(boutInitializer(),boutView());
    }

    private static BoutInitializer boutInitializer() {
        return new BoutInitializerImpl(boxerPresenter(), boutStrategy());
    }

    private static NotificationView boutView() {
        return new BoutNotificationView();
    }

    private static BoutStrategy boutStrategy() {
        Menu<BoutAction> menu = new Menu<>("Choose your action:", BoutAction.values());
        return new UserFirstBoutStrategy(new BoutActionView(menu));
    }

    private static BoxerPresenter boxerPresenter() {
        final TextInput playerName = new TextInput("Boxer name:");
        final Menu<WeightCategory> weightCategory = new Menu<>("Choose Weight Category for Player :", WeightCategory.values());
        final Menu<Stances> stance = new Menu<>("Choose Stance :", Stances.values());
        final Menu<PunchingPower> punchingPower = new Menu<>("Choose Max punching power of player :", PunchingPower.values());
        return new BoxerPresenter(new BoxerConsoleView(playerName,weightCategory,stance,punchingPower));
    }
}
