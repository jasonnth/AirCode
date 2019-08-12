package com.airbnb.android.thread.controllers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ThreadBlockReasonEpoxyController_EpoxyHelper extends ControllerHelper<ThreadBlockReasonEpoxyController> {
    private final ThreadBlockReasonEpoxyController controller;

    public ThreadBlockReasonEpoxyController_EpoxyHelper(ThreadBlockReasonEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.docMarquee = new DocumentMarqueeEpoxyModel_();
        this.controller.docMarquee.m4534id(-1);
        setControllerToStageTo(this.controller.docMarquee, this.controller);
    }
}
