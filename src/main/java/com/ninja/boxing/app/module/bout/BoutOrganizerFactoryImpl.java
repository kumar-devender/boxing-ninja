package com.ninja.boxing.app.module.bout;

import java.util.Optional;

import com.ninja.boxing.app.module.bout.referee.Referee;
import com.ninja.boxing.app.module.model.Playable;

public class BoutOrganizerFactoryImpl implements BoutOrganizerFactory{

    final private BoutInitializer boutInitializer;
    final  private NotificationView view;
    public BoutOrganizerFactoryImpl(final BoutInitializer boutInitializer, final NotificationView notificationView) {
        this.boutInitializer = boutInitializer;
        this.view = notificationView;
    }

    @Override
    public Optional<BoutOrganizer> create() {
        return create(boutInitializer.create());
    }

    @Override
    public Optional<BoutOrganizer> load() {
        return create(boutInitializer.load());
    }

    private Optional<BoutOrganizer> create(final Optional<Referee> optional) {
        if(optional.isPresent()) {
            return Optional.of(new BoutOrganizerImpl(optional.get(), view));
        }else {
            return Optional.empty();
        }
    }


    @Override
    public Optional<Playable> getUserPlayer() {
        return this.boutInitializer.getUser();
    }


    @Override
    public Optional<BoutOrganizer> organizeBoutForProfile() {
        return create(boutInitializer.createNewEnemy());

    }

}
