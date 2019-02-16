package com.ninja.boxing.app.module.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.ninja.boxing.app.module.constant.CommonEnum.BoxingTitle;
import com.ninja.boxing.app.module.helper.BuilderHelper;

public class BoutTests {

    @Test
    public void testGetBoutDate() {
        LocalDate localDate = LocalDate.now();
        Bout bout = new Bout(localDate, BoxingTitle.WBA);
        assertThat(bout.getBoutDate(), is(localDate));
    }

    @Test
    public void testGetWinner() {
        LocalDate localDate = LocalDate.now();
        Bout bout = new Bout(localDate, BoxingTitle.WBA);
        Playable user = BuilderHelper.createUserPlayer();
        Playable enemy = BuilderHelper.createEnemyPlayer().get(0);
        user.isBeatenBy(enemy);
        bout.setResult(user, enemy);
        assertThat(bout.getWinner(), is(enemy.getName()));
        assertThat(bout.getRunnerUp(), is(user.getName()));
    }

    @Test
    public void testGetRunnerUp() {
        LocalDate localDate = LocalDate.now();
        Bout bout = new Bout(localDate, BoxingTitle.WBA);
        Playable user = BuilderHelper.createUserPlayer();
        Playable enemy = BuilderHelper.createEnemyPlayer().get(0);
        user.isBeatenBy(enemy);
        bout.setResult(enemy, user);
        assertThat(bout.getRunnerUp(), is(user.getName()));
    }

    @Test
    public void testGetTitle() {
        LocalDate localDate = LocalDate.now();
        Bout bout = new Bout(localDate, BoxingTitle.WBA);
        assertThat(bout.getTitle(), is(BoxingTitle.WBA));
    }

}
