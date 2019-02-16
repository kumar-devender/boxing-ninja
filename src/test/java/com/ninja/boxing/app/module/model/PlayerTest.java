package com.ninja.boxing.app.module.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ninja.boxing.app.module.constant.CommonEnum.Level;
import com.ninja.boxing.app.module.constant.CommonEnum.WeightCategory;
import com.ninja.boxing.app.module.helper.BuilderHelper;
import com.ninja.boxing.app.module.model.Player.PlayerBuilder;

public class PlayerTest {

    @Test(expected=IllegalArgumentException.class)
    public void testBuildShouldThrowExceptionWhenNameIsNull() {
        PlayerBuilder builder = new PlayerBuilder(null, 10);
        builder.build();
    }

    @Test
    public void testGetLevel() {
        Playable user = BuilderHelper.createUserPlayer();
        assertThat(user.getLevel(), is(Level.Level1));
        user.setLevel(Level.Level5);
        assertThat(user.getLevel(), is(Level.Level5));
    }

    @Test
    public void testGetWeightCategories() {
        PlayerBuilder builder = new PlayerBuilder("devender", 10);
        List<WeightCategory> weightCategory = new ArrayList<>();
        weightCategory.add(WeightCategory.FLYWEIGHT);
        builder.setWeightCategories(weightCategory);
        Playable user = builder.build();
        assertThat(user.getWeightCategories().get(0), is(WeightCategory.FLYWEIGHT));
    }

    @Test
    public void testCanPlay() {
        PlayerBuilder builder = new PlayerBuilder("devender", 10);
        Playable user = builder.build();
        assertThat(user.canPlay(), is(true));
    }

    @Test
    public void testIsBeatenBy() {
        PlayerBuilder builder = new PlayerBuilder("devender", 40);
        Playable user = builder.build();

        PlayerBuilder enemyBuilder = new PlayerBuilder("devender", 50);
        Playable enemy = enemyBuilder.build();
        
        int staminaLeft = enemy.isBeatenBy(user);
        
        assertThat(staminaLeft, is(40));
        enemy.isBeatenBy(user);
        staminaLeft = enemy.isBeatenBy(user);
        assertThat(staminaLeft, is(40));
        

    }

}
