package com.ninja.boxing.app.module.bout.factory;

import com.ninja.boxing.app.module.constant.CommonEnum.PunchingPower;
import com.ninja.boxing.app.module.constant.CommonEnum.Stances;
import com.ninja.boxing.app.module.constant.CommonEnum.WeightCategory;
import com.ninja.boxing.app.module.ui.component.AbstractConsoleView;
import com.ninja.boxing.app.module.ui.component.Menu;
import com.ninja.boxing.app.module.ui.component.TextInput;

public class BoxerConsoleView extends AbstractConsoleView<BoxerView.ActionDelegate> implements BoxerView {

    private final TextInput playerName;

    private final Menu<WeightCategory> weightCategory;

    private final Menu<Stances> stance;

    private final Menu<PunchingPower> punchingPower;

    public BoxerConsoleView(final TextInput playerName, final Menu<WeightCategory> weightCategory,
            final Menu<Stances> stance, final Menu<PunchingPower> punchingPower) {
        this.playerName = playerName;
        this.punchingPower = punchingPower;
        this.weightCategory = weightCategory;
        this.stance = stance;
    }

    @Override
    public void draw() {
        System.out.println("New Boxer Menu");

        playerName.draw();
        delegate.onChosen(playerName.getValue());

        weightCategory.draw();
        delegate.onChosen(weightCategory.chooseItem());

        stance.draw();
        delegate.onChosen(stance.chooseItem());

        punchingPower.draw();
        delegate.onChosen(punchingPower.chooseItem());

        delegate.onCompleted();
    }
}
