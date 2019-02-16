package com.ninja.boxing.app.module.ui.home;

import java.util.Optional;

import com.ninja.boxing.app.module.bout.BoutOrganizer;
import com.ninja.boxing.app.module.bout.BoutOrganizerFactory;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.AbstractPresenter;

public class WelcomeMenuPresenter extends AbstractPresenter<WelcomeMenuView> implements WelcomeMenuView.ActionDelegate {

    /**
     * BoutOrganizerFactory represent creation of bout. 
     * It should be injected from constructor.
     */
    private final BoutOrganizerFactory boutOrganizerFactory;

    public WelcomeMenuPresenter(final WelcomeMenuView view, final BoutOrganizerFactory boutOrganizerFactory) {
        super(view);
        this.view.setDelegate(this);
        this.boutOrganizerFactory = boutOrganizerFactory;
    }

    /* When user choose to create new game with new profile
     * 
     */
    @Override
    public void onStartChosen() {
        final Optional<BoutOrganizer> optional = boutOrganizerFactory.create();
        if(optional.isPresent()) {
            optional.get().start();
        }else {
            System.out.println("Sorry could not create game.");
        }
        show();
    }

    /* 
     * When user choose to resume previously saved game
     */
    @Override
    public void onResumeChosen() {
        final  Optional<BoutOrganizer> optional = boutOrganizerFactory.load();
        if(optional.isPresent()) {
            optional.get().start();
        }else {
            System.out.println("Sorry could not load game this time.");
        }
        show();
    }

    /* When user choose to view profile
     */
    @Override
    public void onviewProfileChosen() {
        final Optional<Playable> optional = boutOrganizerFactory.getUserPlayer();
        if(optional.isPresent()) {
            System.out.println(optional.get());
        }else {
            System.out.println("Profile does not exist. Please create your profile.");
        }
        show();
    }

    /* When user choose to create new game with existing profile
     */
    @Override
    public void onContiWithExistingProfileChosen() {
        final Optional<BoutOrganizer> optional = boutOrganizerFactory.organizeBoutForProfile();
        if(optional.isPresent()) {
            optional.get().start();
        }else {
            System.out.println("Sorry this profile seems does not exist. Try with new one.");
        }
        show();
    }

    @Override
    public void onExitChosen() {
        System.out.println("Thanks Please come again.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
