package com.airbnb.android.cohosting.adapters;

import android.content.Context;
import android.view.View;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.cohosting.utils.CohostingUtil;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ImageWithButtonRowExpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.UserDetailsActionRowEpoxyModel_;
import java.util.List;

public class ListingManagersPickerAdapter extends AirEpoxyAdapter {
    CohostingManagementJitneyLogger cohostingManagementJitneyLogger;
    private final Context context;
    private CohostManagementDataController controller;
    private final LinkActionRowEpoxyModel learnMoreLinkModel = new LinkActionRowEpoxyModel_().textRes(C5658R.string.cohosting_cohost_duty_explanation).clickListener(ListingManagersPickerAdapter$$Lambda$1.lambdaFactory$(this));
    private final DocumentMarqueeEpoxyModel_ marqueeModel = new DocumentMarqueeEpoxyModel_();

    static /* synthetic */ void lambda$new$0(ListingManagersPickerAdapter listingManagersPickerAdapter, View v) {
        listingManagersPickerAdapter.cohostingManagementJitneyLogger.logWhatCanCohostsDoLinkClicked(listingManagersPickerAdapter.controller.getCohostingContext(), listingManagersPickerAdapter.controller.getSourceFlow());
        listingManagersPickerAdapter.controller.actionExecutor.cohostingServiceIntro();
    }

    public ListingManagersPickerAdapter(CohostManagementDataController controller2, Context context2) {
        super(true);
        this.controller = controller2;
        this.context = context2;
        ((CohostingGraph) CoreApplication.instance(context2).component()).inject(this);
    }

    public void update() {
        removeAllModels();
        display();
    }

    public void display() {
        if (this.controller.hasCohostsOrInvitations()) {
            displayListingManagersAndInvitations();
        } else {
            displayEmptyState();
        }
    }

    private void displayEmptyState() {
        this.marqueeModel.titleRes(C5658R.string.cohosting_cohosts_title).captionRes(C5658R.string.cohosting_cohost_explanation);
        addModel(this.marqueeModel);
        addModel(new ImageWithButtonRowExpoxyModel_().imageDrawableRes(C5658R.C5659drawable.cohosting_friends).buttonTextRes(C5658R.string.cohosting_invite_friend).buttonClickListener(ListingManagersPickerAdapter$$Lambda$2.lambdaFactory$(this)));
        addModel(this.learnMoreLinkModel);
    }

    static /* synthetic */ void lambda$displayEmptyState$1(ListingManagersPickerAdapter listingManagersPickerAdapter, View v) {
        listingManagersPickerAdapter.cohostingManagementJitneyLogger.logInviteButtonFromIntroPageClicked(listingManagersPickerAdapter.controller.getCohostingContext(), listingManagersPickerAdapter.controller.getSourceFlow());
        listingManagersPickerAdapter.controller.actionExecutor.inviteFriend();
    }

    private void displayListingManagersAndInvitations() {
        this.marqueeModel.titleRes(C5658R.string.cohosting_cohosts_title).captionRes(this.controller.isCurrentUserListingAdmin() ? C5658R.string.cohosting_cohosts_description_for_listing_admin : C5658R.string.cohosting_cohosts_description_for_cohost);
        addModel(this.marqueeModel);
        addInvitationModels();
        addListingManagerModels();
        addModel(this.learnMoreLinkModel);
    }

    private void addInvitationModels() {
        List<CohostInvitation> invitations = this.controller.getCohostInvitations();
        if (!invitations.isEmpty()) {
            for (CohostInvitation invitation : invitations) {
                UserDetailsActionRowEpoxyModel_ model = new UserDetailsActionRowEpoxyModel_();
                model.title(invitation.getInviteeEmail());
                String status = CohostingUtil.getInvitationStatusStr(this.context, invitation.getStatus());
                model.subTitle(status + " " + CohostingUtil.getInvitationExpirationTimeStr(this.context, invitation.getExpirationTime()));
                model.imageDrawableRes(C5658R.C5659drawable.placeholder_profile);
                model.clickListener(ListingManagersPickerAdapter$$Lambda$3.lambdaFactory$(this, invitation));
                addModel(model);
            }
        }
    }

    static /* synthetic */ void lambda$addInvitationModels$2(ListingManagersPickerAdapter listingManagersPickerAdapter, CohostInvitation invitation, View v) {
        listingManagersPickerAdapter.cohostingManagementJitneyLogger.logPendingInvitationRowClicked(listingManagersPickerAdapter.controller.getCohostingContext());
        listingManagersPickerAdapter.controller.actionExecutor.pendingCohostDetails(invitation.getId());
    }

    private void addListingManagerModels() {
        String cohostCreationTimeStr;
        for (ListingManager manager : this.controller.getListingManagers()) {
            UserDetailsActionRowEpoxyModel_ model = new UserDetailsActionRowEpoxyModel_();
            UserDetailsActionRowEpoxyModel_ clickListener = model.title(manager.getUser().getName()).imageUrl(manager.getUser().getPictureUrl()).clickListener(ListingManagersPickerAdapter$$Lambda$4.lambdaFactory$(this, manager));
            if (manager.isIsListingAdmin().booleanValue()) {
                cohostCreationTimeStr = this.context.getString(C5658R.string.cohosting_listing_admin_text);
            } else {
                cohostCreationTimeStr = CohostingUtil.getCohostCreationTimeStr(this.context, manager.getCreatedAt(), this.controller);
            }
            clickListener.subTitle(cohostCreationTimeStr).showDivider(true);
            if (manager.isIsPrimaryHost().booleanValue()) {
                model.label(this.context.getString(C5658R.string.primary_host_badge));
            }
            addModel(model);
        }
    }

    static /* synthetic */ void lambda$addListingManagerModels$3(ListingManagersPickerAdapter listingManagersPickerAdapter, ListingManager manager, View v) {
        listingManagersPickerAdapter.cohostingManagementJitneyLogger.logListingManagerRowClicked(listingManagersPickerAdapter.controller.getCohostingContext(), manager);
        listingManagersPickerAdapter.controller.actionExecutor.listingManagerDetails(manager.getId());
    }
}
