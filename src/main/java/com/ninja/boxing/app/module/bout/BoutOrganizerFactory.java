package com.ninja.boxing.app.module.bout;

import java.util.Optional;

import com.ninja.boxing.app.module.model.Playable;

public interface BoutOrganizerFactory {
    Optional<BoutOrganizer> create();

    Optional<BoutOrganizer> load();

    Optional<BoutOrganizer> organizeBoutForProfile();

    Optional<Playable> getUserPlayer();
}
