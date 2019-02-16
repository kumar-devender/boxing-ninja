package com.ninja.boxing.app.module.bout.factory;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import com.ninja.boxing.app.module.bout.factory.BoxerView.ActionDelegate;
import com.ninja.boxing.app.module.constant.CommonEnum.PunchingPower;
import com.ninja.boxing.app.module.constant.CommonEnum.Stances;
import com.ninja.boxing.app.module.constant.CommonEnum.WeightCategory;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.model.Player.PlayerBuilder;
import com.ninja.boxing.app.module.ui.component.AbstractPresenter;

public class BoxerPresenter extends AbstractPresenter<BoxerView> implements BoxerFactory, ActionDelegate {

    private WeightCategory weightCategory;

    private Stances stance;

    private String name;

    private PunchingPower punchingPower;

    public BoxerPresenter(final BoxerView view) {
        super(view);
        view.setDelegate(this);
    }

    @Override
    public void onChosen(final WeightCategory weightCategory) {
        this.weightCategory = weightCategory;
    }

    @Override
    public void onChosen(final Stances stances) {
        this.stance = stances;
    }

    @Override
    public void onChosen(final String name) {
        this.name = name;
    }

    @Override
    public void onChosen(final PunchingPower punchingPower) {
        this.punchingPower = punchingPower;
    }

    @Override
    public void onCompleted() {
        requireNonNull(name, "It is impossible to create an instance of character without name parameter");
        requireNonNull(weightCategory, "It is impossible to create an instance of character without race parameter");
        requireNonNull(stance, "It is impossible to create an instance of character without sex parameter");
        requireNonNull(punchingPower, "It is impossible to create an instance of character without Punching Power parameter");
    }

    public Playable getPlayer() {
        show();
        final PlayerBuilder playerBuilder = new PlayerBuilder(name,punchingPower.getValue());
        playerBuilder.setStance(stance);
        final List<WeightCategory> weightCategoryList = new ArrayList<>();
        weightCategoryList.add(this.weightCategory);
        playerBuilder.setWeightCategories(weightCategoryList);
        return playerBuilder.build();
    }

}
