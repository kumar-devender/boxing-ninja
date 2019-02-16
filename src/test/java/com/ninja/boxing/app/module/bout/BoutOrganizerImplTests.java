package com.ninja.boxing.app.module.bout;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import com.ninja.boxing.app.module.bout.referee.Referee;
import com.ninja.boxing.app.module.bout.referee.RefereeInCharge;
import com.ninja.boxing.app.module.bout.strategy.UserFirstBoutStrategy;
import com.ninja.boxing.app.module.constant.CommonEnum.BoutAction;
import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;
import com.ninja.boxing.app.module.helper.BuilderHelper;
import com.ninja.boxing.app.module.helper.PrintStreamHelper;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.Menu;

public class BoutOrganizerImplTests extends PrintStreamHelper{
    private Menu<BoutAction> menu = new Menu<>("Choose your action:", BoutAction.values()); 

    @Test
    public void testStartShouldShowGamePausedNotification() throws Exception {
        Playable enemy = BuilderHelper.createEnemyPlayer().get(0);
        Playable user = BuilderHelper.createUserPlayer();
        RefereeInCharge refereeInCharge = mock(RefereeInCharge.class);
        doNothing().when(refereeInCharge).start();
        when(refereeInCharge.getBoutStatus()).thenReturn(FightStatus.SAVED);
        when(refereeInCharge.getUser()).thenReturn(user);
        when(refereeInCharge.getEnemy()).thenReturn(enemy);
        PowerMockito.whenNew(RefereeInCharge.class).withArguments(enemy,user,new UserFirstBoutStrategy(new BoutActionView(menu))).thenReturn(refereeInCharge);
        BoutOrganizerImpl organizerImpl = new BoutOrganizerImpl(refereeInCharge, new BoutNotificationView());
        organizerImpl.start();
        String actual = outContent.toString();
        assertThat(actual , containsString("This fight has been paused for now."));
    }

}
