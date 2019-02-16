package com.ninja.boxing.app.module.bout;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.ninja.boxing.app.module.bout.referee.RefereeInCharge;
import com.ninja.boxing.app.module.bout.strategy.UserFirstBoutStrategy;
import com.ninja.boxing.app.module.constant.CommonEnum.BoutAction;
import com.ninja.boxing.app.module.helper.BuilderHelper;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.Menu;

public class BoutOrganizerFactoryImplTests {

    private RefereeInCharge refereeInCharge;

    private BoutInitializerImpl boutInitializer;
    
    private Playable enemy;
    
    private Playable user;

    @Before
    public void setUp() {
        Menu<BoutAction> menu = new Menu<>("Choose your action:", BoutAction.values());
        enemy = BuilderHelper.createEnemyPlayer().get(0);
        user = BuilderHelper.createUserPlayer();
        UserFirstBoutStrategy boutStrategy = new UserFirstBoutStrategy(new BoutActionView(menu));
        refereeInCharge = new RefereeInCharge(enemy, user, boutStrategy);
        boutInitializer =mock(BoutInitializerImpl.class);
    }

    @Test
    public void testCreate() {
        when(boutInitializer.create()).thenReturn(Optional.of(refereeInCharge));
        BoutOrganizerFactoryImpl organizerFactoryImpl = new BoutOrganizerFactoryImpl(boutInitializer, new BoutNotificationView());
        Optional<BoutOrganizer> boutOrganizer = organizerFactoryImpl.create();
        assertThat(boutOrganizer.isPresent(), is(true));
    }

    @Test
    public void testLoad() {
        when(boutInitializer.load()).thenReturn(Optional.of(refereeInCharge));
        BoutOrganizerFactoryImpl organizerFactoryImpl = new BoutOrganizerFactoryImpl(boutInitializer, new BoutNotificationView());
        Optional<BoutOrganizer> boutOrganizer = organizerFactoryImpl.load();
        assertThat(boutOrganizer.isPresent(), is(true));
    }

    @Test
    public void testGetUserPlayer() {
        when(boutInitializer.getUser()).thenReturn(Optional.of(user));
        BoutOrganizerFactoryImpl organizerFactoryImpl = new BoutOrganizerFactoryImpl(boutInitializer, new BoutNotificationView());
        Optional<Playable> user = organizerFactoryImpl.getUserPlayer();
        assertThat(user.isPresent(), is(true));
    }

    @Test
    public void testOrganizeBoutForProfile() {
        when(boutInitializer.createNewEnemy()).thenReturn(Optional.of(refereeInCharge));
        BoutOrganizerFactoryImpl organizerFactoryImpl = new BoutOrganizerFactoryImpl(boutInitializer, new BoutNotificationView());
        Optional<BoutOrganizer> boutOrganizer = organizerFactoryImpl.organizeBoutForProfile();
        assertThat(boutOrganizer.isPresent(), is(true));
    }
    
    @Test
    public void testOrganizeBoutForProfileShouldReturnNullWhenNoReferee() {
        when(boutInitializer.createNewEnemy()).thenReturn(Optional.empty());
        BoutOrganizerFactoryImpl organizerFactoryImpl = new BoutOrganizerFactoryImpl(boutInitializer, new BoutNotificationView());
        Optional<BoutOrganizer> boutOrganizer = organizerFactoryImpl.organizeBoutForProfile();
        assertThat(boutOrganizer.isPresent(), is(false));
    }

}
