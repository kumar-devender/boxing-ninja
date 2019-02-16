package com.ninja.boxing.app.module.bout.factory;

import com.ninja.boxing.app.module.constant.CommonEnum.PunchingPower;
import com.ninja.boxing.app.module.constant.CommonEnum.Stances;
import com.ninja.boxing.app.module.constant.CommonEnum.WeightCategory;
import com.ninja.boxing.app.module.ui.component.View;

public interface BoxerView extends View<BoxerView.ActionDelegate> {

    interface ActionDelegate {

        void onChosen(Stances stances);

        void onChosen(WeightCategory weightCategory);

        void onChosen(String name);

        void onChosen(PunchingPower punchingPower);

        void onCompleted();
    }
}
