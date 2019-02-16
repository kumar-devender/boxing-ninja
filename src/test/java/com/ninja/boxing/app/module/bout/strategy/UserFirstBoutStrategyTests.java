package com.ninja.boxing.app.module.bout.strategy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ninja.boxing.app.module.bout.BoutActionView;
import com.ninja.boxing.app.module.constant.CommonEnum.BoutAction;
import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;
import com.ninja.boxing.app.module.helper.BuilderHelper;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.Menu;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserFirstBoutStrategy.class)
public class UserFirstBoutStrategyTests {
    
    private Menu<BoutAction> menu;
    private Playable user;
    private  Playable enemy;
    @Before
    public void setUp() {
        menu = mock(Menu.class);
        this.user = BuilderHelper.createUserPlayer();
        this.enemy = BuilderHelper.createEnemyPlayer().get(0);
    }

    @Test
    public void testStart() throws Exception {
        when(menu.chooseItem()).thenReturn(BoutAction.ATTACK);
        BoutActionView boutActionView = mock(BoutActionView.class);
        
        //doNothing().when(boutActionView).draw();
        //doNothing().when(boutActionView).drawAttack(user, enemy, user.getMinPunchPower());
        
        PowerMockito.whenNew(BoutActionView.class).withArguments(menu).thenReturn(boutActionView);
        
        UserFirstBoutStrategy spy = PowerMockito.spy(new UserFirstBoutStrategy(boutActionView));
        spy.start(user, enemy);
        FightStatus fightStatus = spy.getBoutStatus();
        assertThat(fightStatus, is(FightStatus.COMPLETED));
        PowerMockito.verifyPrivate(spy, times(1)).invoke("startAndUpdateStatus");
        PowerMockito.verifyPrivate(spy, times(1)).invoke("nextIteration");
        
    }

    @Test
    public void testOnUserAttack() throws Exception {

        when(menu.chooseItem()).thenReturn(BoutAction.ATTACK);
        BoutActionView boutActionView = mock(BoutActionView.class);
        PowerMockito.whenNew(BoutActionView.class).withArguments(menu).thenReturn(boutActionView);
        UserFirstBoutStrategy spy = PowerMockito.spy(new UserFirstBoutStrategy(boutActionView));
        spy.start(user, enemy);
        spy.onUserAttack();
        FightStatus fightStatus = spy.getBoutStatus();
        assertThat(fightStatus, is(FightStatus.COMPLETED));
        PowerMockito.verifyPrivate(spy, times(1)).invoke("startAndUpdateStatus");
        PowerMockito.verifyPrivate(spy, times(2)).invoke("nextIteration");
        PowerMockito.verifyPrivate(spy, times(1)).invoke("enemyBoutAction");
        
        
    
    }

    @Test
    public void testOnUserDefend() throws Exception {
        when(menu.chooseItem()).thenReturn(BoutAction.ATTACK);
        BoutActionView boutActionView = mock(BoutActionView.class);
        PowerMockito.whenNew(BoutActionView.class).withArguments(menu).thenReturn(boutActionView);
        UserFirstBoutStrategy spy = PowerMockito.spy(new UserFirstBoutStrategy(boutActionView));
        spy.start(user, enemy);
        spy.onUserDefend();
        FightStatus fightStatus = spy.getBoutStatus();
        assertThat(fightStatus, is(FightStatus.COMPLETED));
        PowerMockito.verifyPrivate(spy, times(1)).invoke("startAndUpdateStatus");
        PowerMockito.verifyPrivate(spy, times(2)).invoke("nextIteration");
        PowerMockito.verifyPrivate(spy, times(1)).invoke("enemyBoutAction");
    }

   @Test
    public void testOnDoNothing() throws Exception {
        when(menu.chooseItem()).thenReturn(BoutAction.DO_NOTHING);
        BoutActionView boutActionView = mock(BoutActionView.class);
        PowerMockito.whenNew(BoutActionView.class).withArguments(menu).thenReturn(boutActionView);
        UserFirstBoutStrategy spy = PowerMockito.spy(new UserFirstBoutStrategy(boutActionView));
        spy.start(user, enemy);
        spy.onDoNothing();
        FightStatus fightStatus = spy.getBoutStatus();
        assertThat(fightStatus, is(FightStatus.COMPLETED));
        PowerMockito.verifyPrivate(spy, times(1)).invoke("startAndUpdateStatus");
        PowerMockito.verifyPrivate(spy, times(2)).invoke("nextIteration");
        PowerMockito.verifyPrivate(spy, times(1)).invoke("enemyBoutAction");
    
    }

    @Test
    public void testOnsaveAndExit() throws Exception {
        when(menu.chooseItem()).thenReturn(BoutAction.DO_NOTHING);
        BoutActionView boutActionView = mock(BoutActionView.class);
        PowerMockito.whenNew(BoutActionView.class).withArguments(menu).thenReturn(boutActionView);
        UserFirstBoutStrategy spy = PowerMockito.spy(new UserFirstBoutStrategy(boutActionView));
        spy.start(user, enemy);
        spy.onsaveAndExit();
        FightStatus fightStatus = spy.getBoutStatus();
        assertThat(fightStatus, is(FightStatus.SAVED));
        PowerMockito.verifyPrivate(spy, times(1)).invoke("startAndUpdateStatus");
        PowerMockito.verifyPrivate(spy, times(1)).invoke("nextIteration");
    
    }

    
    @After
    public void validate() {
        validateMockitoUsage();
    }

}
