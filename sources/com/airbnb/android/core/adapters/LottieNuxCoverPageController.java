package com.airbnb.android.core.adapters;

import android.content.Context;
import com.airbnb.android.core.enums.LottieNuxViewPagerArguments;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.p027n2.components.NuxCoverCardModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class LottieNuxCoverPageController extends AirEpoxyController {
    private LottieNuxViewPagerArguments arguments;
    private Context context;
    NuxCoverCardModel_ coverCardModel;
    ToolbarSpacerEpoxyModel_ spacer;

    public LottieNuxCoverPageController(Context context2, LottieNuxViewPagerArguments arguments2) {
        this.context = context2;
        this.arguments = arguments2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.spacer.addTo(this);
        this.coverCardModel.image(((Integer) this.arguments.coverPageImageRes().get()).intValue()).title(((Integer) this.arguments.coverPageTitleRes().get()).intValue()).subtitle(((Integer) this.arguments.coverPageDescriptionRes().get()).intValue()).button(((Integer) this.arguments.coverPageButtonTextRes().get()).intValue()).buttonClickListener(LottieNuxCoverPageController$$Lambda$1.lambdaFactory$(this));
    }
}
