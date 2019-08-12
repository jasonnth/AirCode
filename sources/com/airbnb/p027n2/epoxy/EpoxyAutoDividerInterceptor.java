package com.airbnb.p027n2.epoxy;

import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.EpoxyController.Interceptor;
import com.airbnb.epoxy.EpoxyModel;
import java.util.List;

/* renamed from: com.airbnb.n2.epoxy.EpoxyAutoDividerInterceptor */
public class EpoxyAutoDividerInterceptor implements Interceptor {
    private final EpoxyController controller;
    private boolean hasIntercepted;

    public EpoxyAutoDividerInterceptor(EpoxyController controller2) {
        this.controller = controller2;
    }

    public boolean hasIntercepted() {
        return this.hasIntercepted;
    }

    public void intercept(List<EpoxyModel<?>> models) {
        boolean z;
        boolean hideDivider;
        boolean z2;
        this.hasIntercepted = true;
        boolean firstSectionHeader = true;
        int modelCount = models.size();
        for (int i = 0; i < modelCount; i++) {
            EpoxyModel<?> epoxyModel = (EpoxyModel) models.get(i);
            if (!(epoxyModel instanceof AirModel)) {
                throw new IllegalStateException("Model at position " + i + " is not a AirModel: " + epoxyModel);
            }
            AirModel airModel = (AirModel) epoxyModel;
            if (airModel.supportsDividers() && airModel.isShowingDivider() == null) {
                switch (airModel.getDividerViewType()) {
                    case -1:
                        throw new IllegalStateException("Should not try and auto draw dividers if dividers are unsupported by EpoxyModel");
                    case 0:
                        firstSectionHeader = false;
                        if (i != modelCount - 1) {
                            AirModel nextEpoxyModel = (AirModel) models.get(i + 1);
                            if (nextEpoxyModel.supportsDividers()) {
                                if (nextEpoxyModel.getDividerViewType() == 4) {
                                    hideDivider = true;
                                } else {
                                    hideDivider = false;
                                }
                                if (!hideDivider) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                setDivider(models, airModel, i, z2);
                                break;
                            } else {
                                setDivider(models, airModel, i, true);
                                break;
                            }
                        } else {
                            setDivider(models, airModel, i, false);
                            break;
                        }
                    case 1:
                        if (!firstSectionHeader) {
                            z = true;
                        } else {
                            z = false;
                        }
                        setDivider(models, airModel, i, z);
                        firstSectionHeader = false;
                        break;
                    case 2:
                        setDivider(models, airModel, i, true);
                        firstSectionHeader = true;
                        break;
                    case 4:
                        setDivider(models, airModel, i, true);
                        firstSectionHeader = true;
                        break;
                    case 5:
                        setDivider(models, airModel, i, true);
                        if (i == 0) {
                            break;
                        } else {
                            AirModel previousEpoxyModel = (AirModel) models.get(i - 1);
                            if (previousEpoxyModel.supportsDividers() && previousEpoxyModel.isShowingDivider() == null) {
                                setDivider(models, previousEpoxyModel, i, false);
                                break;
                            }
                        }
                }
            }
        }
    }

    private void setDivider(List<EpoxyModel<?>> models, AirModel model, int position, boolean showDivider) {
        boolean fullWidth;
        boolean z = true;
        int totalSpanCount = this.controller.getSpanCount();
        if (model.getSpanSize(totalSpanCount, position, models.size()) == totalSpanCount) {
            fullWidth = true;
        } else {
            fullWidth = false;
        }
        if (!fullWidth || !showDivider) {
            z = false;
        }
        model.showDivider(z);
    }
}
