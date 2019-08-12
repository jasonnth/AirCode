package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import android.view.View;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ImageWithButtonRowExpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CohostUpsellEpoxyController extends AirEpoxyController {
    private C1951CohostingContext cohostingContext;
    CohostingManagementJitneyLogger cohostingManagementJitneyLogger;
    private final Context context;
    ImageWithButtonRowExpoxyModel_ imageWithButtonRowExpoxyModel;
    LinkActionRowEpoxyModel_ learnMoreLinkModel;
    private Listener listener;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    MicroSectionHeaderEpoxyModel_ sectionHeaderEpoxyModel;
    TextRowEpoxyModel_ textRowEpoxyModel;

    public interface Listener {
        void inviteFriend();

        void showCohostingServiceIntro();
    }

    public CohostUpsellEpoxyController(Context context2, Listener listener2, C1951CohostingContext cohostingContext2) {
        this.context = context2;
        this.listener = listener2;
        this.cohostingContext = cohostingContext2;
        requestModelBuild();
        ((CohostingGraph) CoreApplication.instance(context2).component()).inject(this);
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeModel.titleRes(C5658R.string.cohosting_upsell_title).addTo(this);
        this.sectionHeaderEpoxyModel.title(this.context.getString(C5658R.string.cohosting_upsell_subtitle)).addTo(this);
        this.textRowEpoxyModel.textRes(C5658R.string.cohosting_upsell_explanation).addTo(this);
        this.imageWithButtonRowExpoxyModel.imageDrawableRes(C5658R.C5659drawable.cohosting_friends).buttonTextRes(C5658R.string.cohosting_invite_friend).buttonClickListener(CohostUpsellEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        this.learnMoreLinkModel.textRes(C5658R.string.cohosting_cohost_duty_explanation).clickListener(CohostUpsellEpoxyController$$Lambda$2.lambdaFactory$(this)).addTo(this);
    }

    static /* synthetic */ void lambda$buildModels$0(CohostUpsellEpoxyController cohostUpsellEpoxyController, View v) {
        cohostUpsellEpoxyController.cohostingManagementJitneyLogger.logInviteButtonFromIntroPageClicked(cohostUpsellEpoxyController.cohostingContext, C1958CohostingSourceFlow.PostListYourSpace);
        cohostUpsellEpoxyController.listener.inviteFriend();
    }

    static /* synthetic */ void lambda$buildModels$1(CohostUpsellEpoxyController cohostUpsellEpoxyController, View v) {
        cohostUpsellEpoxyController.cohostingManagementJitneyLogger.logWhatCanCohostsDoLinkClicked(cohostUpsellEpoxyController.cohostingContext, C1958CohostingSourceFlow.PostListYourSpace);
        cohostUpsellEpoxyController.listener.showCohostingServiceIntro();
    }
}
