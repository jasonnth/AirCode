package com.airbnb.epoxy;

import com.airbnb.epoxy.EpoxyController;
import java.util.List;

public abstract class ControllerHelper<T extends EpoxyController> {
    public abstract void resetAutoModels();

    /* access modifiers changed from: protected */
    public void validateModelHashCodesHaveNotChanged(T controller) {
        List<EpoxyModel<?>> currentModels = controller.getAdapter().getCopyOfModels();
        for (int i = 0; i < currentModels.size(); i++) {
            ((EpoxyModel) currentModels.get(i)).validateStateHasNotChangedSinceAdded("Model has changed since it was added to the controller.", i);
        }
    }

    /* access modifiers changed from: protected */
    public void setControllerToStageTo(EpoxyModel<?> model, T controller) {
        model.controllerToStageTo = controller;
    }
}
