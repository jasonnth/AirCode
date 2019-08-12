package com.airbnb.android.mythbusters;

import com.airbnb.android.core.viewcomponents.models.KickerMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class MythbustersQuestionEpoxyController_EpoxyHelper extends ControllerHelper<MythbustersQuestionEpoxyController> {
    private final MythbustersQuestionEpoxyController controller;

    public MythbustersQuestionEpoxyController_EpoxyHelper(MythbustersQuestionEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.textRow = new TextRowEpoxyModel_();
        this.controller.textRow.m5686id(-1);
        setControllerToStageTo(this.controller.textRow, this.controller);
        this.controller.trueFalseButtonRow = new TrueFalseButtonRowEpoxyModel_();
        this.controller.trueFalseButtonRow.m6299id(-2);
        setControllerToStageTo(this.controller.trueFalseButtonRow, this.controller);
        this.controller.kickerMarquee = new KickerMarqueeEpoxyModel_();
        this.controller.kickerMarquee.m4978id(-3);
        setControllerToStageTo(this.controller.kickerMarquee, this.controller);
    }
}
