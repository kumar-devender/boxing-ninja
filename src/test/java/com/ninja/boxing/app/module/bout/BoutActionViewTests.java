package com.ninja.boxing.app.module.bout;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.ninja.boxing.app.module.constant.CommonEnum.BoutAction;
import com.ninja.boxing.app.module.helper.BuilderHelper;
import com.ninja.boxing.app.module.helper.PrintStreamHelper;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.Menu;

public class BoutActionViewTests extends PrintStreamHelper{
    
    private Menu<BoutAction> menu; 
    @Before
    public void setUp() {
        menu = mock(Menu.class); 
    }
    
    @Test
    public void testDrawUser() {
        Playable user = BuilderHelper.createUserPlayer();
        BoutActionView boutActionView = new BoutActionView(menu);
        boutActionView.drawUser(user);
        String actual = outContent.toString();
        assertThat(actual , containsString(user.getName()));
    }
    
    @Test
    public void testDrawEnemy() {
        Playable enemy = BuilderHelper.createEnemyPlayer().get(0);
        BoutActionView boutActionView = new BoutActionView(menu);
        boutActionView.drawEnemy(enemy);
        String actual = outContent.toString();
        assertThat(actual , containsString(enemy.getName()));
    }
    
    @Test
    public void testDrawefence() {
        Playable enemy = BuilderHelper.createEnemyPlayer().get(0);
        BoutActionView boutActionView = new BoutActionView(menu);
        boutActionView.drawdefence(enemy);
        String actual = outContent.toString();
        assertThat(actual , containsString(enemy.getName()));
        assertThat(actual , containsString("choose to defence"));
    }
    
    @Test
    public void testDrawDoNothing() {
        Playable enemy = BuilderHelper.createEnemyPlayer().get(0);
        BoutActionView boutActionView = new BoutActionView(menu);
        boutActionView.drawDoNothing(enemy);
        String actual = outContent.toString();
        assertThat(actual , containsString(enemy.getName()));
        assertThat(actual , containsString("choose to see opponent action"));
        
    }
    
    @Test
    public void testDrawAttack() {
        Playable enemy = BuilderHelper.createEnemyPlayer().get(0);
        Playable user = BuilderHelper.createUserPlayer();
        BoutActionView boutActionView = new BoutActionView(menu);
        boutActionView.drawAttack(user, enemy, user.getMinPunchPower());
        String actual = outContent.toString();
        assertThat(actual , containsString(enemy.getName()));
        assertThat(actual , containsString("got a damage"));
    }
    
}
