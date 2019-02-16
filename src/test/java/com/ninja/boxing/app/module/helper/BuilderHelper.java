package com.ninja.boxing.app.module.helper;

import java.util.ArrayList;
import java.util.List;

import com.ninja.boxing.app.module.bout.EnemyAttributes;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.model.Player.PlayerBuilder;

public class BuilderHelper {

    public static Playable createUserPlayer() {
        PlayerBuilder playerBuilder = new PlayerBuilder("devender", 20);
        return playerBuilder.build();
    }

    public static List<Playable> createEnemyPlayer() {
        PlayerBuilder playerBuilder = new PlayerBuilder(EnemyAttributes.Names.LOMACHENKO.toString(), 35);
        final List<Playable> list = new ArrayList<>();
        list.add(playerBuilder.build());
        return list;
    }
}
