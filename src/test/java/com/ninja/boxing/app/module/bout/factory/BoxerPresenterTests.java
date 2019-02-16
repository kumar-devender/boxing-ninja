package com.ninja.boxing.app.module.bout.factory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ninja.boxing.app.module.constant.CommonEnum.PunchingPower;
import com.ninja.boxing.app.module.constant.CommonEnum.Stances;
import com.ninja.boxing.app.module.constant.CommonEnum.WeightCategory;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.ui.component.Menu;
import com.ninja.boxing.app.module.ui.component.TextInput;

public class BoxerPresenterTests {
    @Test
    public void testOnCompleteShouldCreatePlayer() {
        TextInput playerName = mock(TextInput.class);;
        Menu<WeightCategory> weightCategory = mock(Menu.class);
        Menu<Stances> stance =  mock(Menu.class);
        Menu<PunchingPower> punchingPower =  mock(Menu.class);
        when(playerName.getValue()).thenReturn("Devender Kumar");
        when(weightCategory.chooseItem()).thenReturn(WeightCategory.MIDDLE_WEIGHT);
        when(stance.chooseItem()).thenReturn(Stances.UPRIGHT);
        when(punchingPower.chooseItem()).thenReturn(PunchingPower.TWENTY);
        BoxerPresenter boxerPresenter = new BoxerPresenter(new BoxerConsoleView(playerName,weightCategory,stance,punchingPower));
        Playable user = boxerPresenter.getPlayer();
        assertThat(user.getName(), is("Devender Kumar"));
    }
}
