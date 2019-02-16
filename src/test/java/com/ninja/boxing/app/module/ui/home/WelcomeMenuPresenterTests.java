package com.ninja.boxing.app.module.ui.home;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.ninja.boxing.app.module.bout.BoutOrganizerFactoryImpl;
import com.ninja.boxing.app.module.bout.BoutOrganizerImpl;
import com.ninja.boxing.app.module.helper.BuilderHelper;
import com.ninja.boxing.app.module.helper.PrintStreamHelper;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.Menu;
import com.ninja.boxing.app.module.ui.home.WelcomeMenuConsoleView.MainMenuItem;

public class WelcomeMenuPresenterTests extends PrintStreamHelper{
    private Menu<MainMenuItem> menu;
    private WelcomeMenuConsoleView welcomeMenuConsoleView;
    private BoutOrganizerImpl boutOrganizerImpl;
    private BoutOrganizerFactoryImpl boutOrganizerFactoryImpl;
    private WelcomeMenuPresenter menuPresenter;

    @Before
    public void setUp() {
        this.menu = mock(Menu.class);
        this.welcomeMenuConsoleView = new WelcomeMenuConsoleView(menu);
        this.boutOrganizerFactoryImpl = mock(BoutOrganizerFactoryImpl.class);
        this.boutOrganizerImpl = mock(BoutOrganizerImpl.class);
        this.menuPresenter = new WelcomeMenuPresenter(welcomeMenuConsoleView, boutOrganizerFactoryImpl);
    }

    @Test
    public void testOnStartChosenShouldStartGameWhenBoutOrganizerFactoryReturnNonNull() {
        when(this.menu.chooseItem()).thenReturn(MainMenuItem.START);
        when(boutOrganizerFactoryImpl.create()).thenReturn(Optional.of(boutOrganizerImpl));
        doNothing().when(boutOrganizerImpl).start();
        menuPresenter = spy(menuPresenter);
        doNothing().when(menuPresenter).show();
        menuPresenter.onStartChosen();
    }

    @Test
    public void testOnStartChosenShouldNotStartGameWhenBoutOrganizerFactoryReturnNull() {
        when(this.menu.chooseItem()).thenReturn(MainMenuItem.START);
        when(boutOrganizerFactoryImpl.create()).thenReturn(Optional.empty());
        doNothing().when(boutOrganizerImpl).start();
        menuPresenter = spy(menuPresenter);
        doNothing().when(menuPresenter).show();
        menuPresenter.onStartChosen();
        String actual = outContent.toString();
        assertThat(actual , containsString("Sorry could not create game."));
    }

    @Test
    public void testOnResumeChosenShouldStartGameWhenBoutOrganizerFactoryReturnNonNull() {
        when(this.menu.chooseItem()).thenReturn(MainMenuItem.START);
        when(boutOrganizerFactoryImpl.load()).thenReturn(Optional.of(boutOrganizerImpl));
        doNothing().when(boutOrganizerImpl).start();
        menuPresenter = spy(menuPresenter);
        doNothing().when(menuPresenter).show();
        menuPresenter.onResumeChosen();
    }

    @Test
    public void testOnResumeChosenShouldNotStartGameWhenBoutOrganizerFactoryReturnNull() {
        when(this.menu.chooseItem()).thenReturn(MainMenuItem.START);
        when(boutOrganizerFactoryImpl.load()).thenReturn(Optional.empty());
        doNothing().when(boutOrganizerImpl).start();
        menuPresenter = spy(menuPresenter);
        doNothing().when(menuPresenter).show();
        menuPresenter.onResumeChosen();
        String actual = outContent.toString();
        assertThat(actual , containsString("Sorry could not load game this time."));
    }

    @Test
    public void testOnviewProfileChosenShouldStartGameWhenBoutOrganizerFactoryReturnNonNull() {
        Playable user = BuilderHelper.createUserPlayer();
        when(this.menu.chooseItem()).thenReturn(MainMenuItem.EXISTING_PROFILE);
        when(boutOrganizerFactoryImpl.getUserPlayer()).thenReturn(Optional.of(user));
        doNothing().when(boutOrganizerImpl).start();
        menuPresenter = spy(menuPresenter);
        doNothing().when(menuPresenter).show();
        menuPresenter.onviewProfileChosen();
    }

    @Test
    public void testOnviewProfileChosenShouldNotStartGameWhenBoutOrganizerFactoryReturnNull() {
        when(this.menu.chooseItem()).thenReturn(MainMenuItem.EXISTING_PROFILE);
        when(boutOrganizerFactoryImpl.getUserPlayer()).thenReturn(Optional.empty());
        doNothing().when(boutOrganizerImpl).start();
        menuPresenter = spy(menuPresenter);
        doNothing().when(menuPresenter).show();
        menuPresenter.onviewProfileChosen();
        String actual = outContent.toString();
        assertThat(actual , containsString("Profile does not exist. Please create your profile."));
    }

    @Test
    public void testOnContiWithExistingProfileChosenShouldStartGameWhenBoutOrganizerFactoryReturnNonNull() {
        when(this.menu.chooseItem()).thenReturn(MainMenuItem.EXISTING_PROFILE);
        when(boutOrganizerFactoryImpl.organizeBoutForProfile()).thenReturn(Optional.of(boutOrganizerImpl));
        doNothing().when(boutOrganizerImpl).start();
        menuPresenter = spy(menuPresenter);
        doNothing().when(menuPresenter).show();
        menuPresenter.onContiWithExistingProfileChosen();
    }

    @Test
    public void testOnContiWithExistingProfileChosenShouldNotStartGameWhenBoutOrganizerFactoryReturnNull() {
        when(this.menu.chooseItem()).thenReturn(MainMenuItem.EXISTING_PROFILE);
        when(boutOrganizerFactoryImpl.organizeBoutForProfile()).thenReturn(Optional.empty());
        doNothing().when(boutOrganizerImpl).start();
        menuPresenter = spy(menuPresenter);
        doNothing().when(menuPresenter).show();
        menuPresenter.onContiWithExistingProfileChosen();
        String actual = outContent.toString();
        assertThat(actual , containsString("Sorry this profile seems does not exist. Try with new one."));
    }

}
