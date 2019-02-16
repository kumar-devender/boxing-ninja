package com.ninja.boxing.app.module.bout.referee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.ninja.boxing.app.module.bout.strategy.UserFirstBoutStrategy;
import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;
import com.ninja.boxing.app.module.helper.BuilderHelper;
import com.ninja.boxing.app.module.model.Playable;

public class RefereeInChargeTests {

    @Test
    public void testStart() {
        UserFirstBoutStrategy boutStrategy = mock(UserFirstBoutStrategy.class);
        RefereeInCharge refereeInCharge = new RefereeInCharge(BuilderHelper.createEnemyPlayer().get(0), 
                BuilderHelper.createUserPlayer(), boutStrategy);
        Mockito.doNothing().when(boutStrategy).start(BuilderHelper.createUserPlayer(), BuilderHelper.createEnemyPlayer().get(0));
        refereeInCharge.start();
        assertThat(refereeInCharge.getUser().getExperience().getBouts().size(),is(1));
    }

    @Test
    public void testGetUser() {
        Playable user = BuilderHelper.createUserPlayer();
        UserFirstBoutStrategy boutStrategy = mock(UserFirstBoutStrategy.class);
        RefereeInCharge refereeInCharge = new RefereeInCharge(BuilderHelper.createEnemyPlayer().get(0), 
                user, boutStrategy);
        assertThat(user, is(refereeInCharge.getUser()));
    }

    @Test
    public void testGetEnemy() {
        Playable user = BuilderHelper.createUserPlayer();
        Playable enemy = BuilderHelper.createEnemyPlayer().get(0);
        UserFirstBoutStrategy boutStrategy = mock(UserFirstBoutStrategy.class);
        RefereeInCharge refereeInCharge = new RefereeInCharge(enemy, 
                user, boutStrategy);
        assertThat(enemy, is(refereeInCharge.getEnemy()));
    }
    
    @Test
    public void testGetBoutStatus() {
        FightStatus fightStatus = FightStatus.values()[0];
        Playable user = BuilderHelper.createUserPlayer();
        Playable enemy = BuilderHelper.createEnemyPlayer().get(0);
        UserFirstBoutStrategy boutStrategy = mock(UserFirstBoutStrategy.class);
        when(boutStrategy.getBoutStatus()).thenReturn(fightStatus);
        RefereeInCharge refereeInCharge = new RefereeInCharge(enemy, 
                user, boutStrategy);
        assertThat(refereeInCharge.getBoutStatus(), is(fightStatus));
    }
}
