package com.airbnb.android.cohosting;

import com.airbnb.android.cohosting.activities.AcceptCohostInvitationActivity;
import com.airbnb.android.cohosting.activities.CohostManagementActivity;
import com.airbnb.android.cohosting.activities.CohostReasonSelectionActivity;
import com.airbnb.android.cohosting.activities.CohostUpsellActivity;
import com.airbnb.android.cohosting.adapters.CohostingInviteFriendAdapter;
import com.airbnb.android.cohosting.adapters.CohostingListingPickerAdapter;
import com.airbnb.android.cohosting.adapters.CohostingShareEarningsAdapter;
import com.airbnb.android.cohosting.adapters.ListingManagerDetailsAdapter;
import com.airbnb.android.cohosting.adapters.ListingManagersPickerAdapter;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.cohosting.epoxycontrollers.CohostUpsellEpoxyController;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingInviteFriendEpoxyController;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingShareEarningsEpoxyController;
import com.airbnb.android.cohosting.fragments.AcceptCohostInvitationFragment;
import com.airbnb.android.cohosting.fragments.CohostReasonMessageTextInputFragment;
import com.airbnb.android.cohosting.fragments.CohostReasonPrivateFeedbackTextInputFragment;
import com.airbnb.android.cohosting.fragments.CohostReasonSelectionFragment;
import com.airbnb.android.cohosting.fragments.CohostUpsellFragment;
import com.airbnb.android.cohosting.fragments.CohostingContractFragment;
import com.airbnb.android.cohosting.fragments.CohostingInvitationErrorFragment;
import com.airbnb.android.cohosting.fragments.CohostingInvitationExpiredFragment;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendConfirmationFragment;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendWithFeeOptionFragment;
import com.airbnb.android.cohosting.fragments.CohostingListingLevelNotificationSettingFragment;
import com.airbnb.android.cohosting.fragments.CohostingMakePrimaryHostFragment;
import com.airbnb.android.cohosting.fragments.CohostingServicesIntroFragment;
import com.airbnb.android.cohosting.fragments.CohostingShareEarningsWithFeeOptionFragment;
import com.airbnb.android.cohosting.fragments.CohostingStopShareEarningsFragment;
import com.airbnb.android.cohosting.fragments.ConfirmInvitationAcceptedFragment;
import com.airbnb.android.cohosting.fragments.ListingManagersPickerFragment;
import com.airbnb.android.cohosting.fragments.PendingCohostDetailsFragment;
import com.airbnb.android.cohosting.fragments.RemoveCohostFragment;
import com.airbnb.android.core.BaseGraph;

public interface CohostingGraph extends BaseGraph {
    void inject(AcceptCohostInvitationActivity acceptCohostInvitationActivity);

    void inject(CohostManagementActivity cohostManagementActivity);

    void inject(CohostReasonSelectionActivity cohostReasonSelectionActivity);

    void inject(CohostUpsellActivity cohostUpsellActivity);

    void inject(CohostingInviteFriendAdapter cohostingInviteFriendAdapter);

    void inject(CohostingListingPickerAdapter cohostingListingPickerAdapter);

    void inject(CohostingShareEarningsAdapter cohostingShareEarningsAdapter);

    void inject(ListingManagerDetailsAdapter listingManagerDetailsAdapter);

    void inject(ListingManagersPickerAdapter listingManagersPickerAdapter);

    void inject(CohostManagementDataController cohostManagementDataController);

    void inject(CohostUpsellEpoxyController cohostUpsellEpoxyController);

    void inject(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController);

    void inject(CohostingShareEarningsEpoxyController cohostingShareEarningsEpoxyController);

    void inject(AcceptCohostInvitationFragment acceptCohostInvitationFragment);

    void inject(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment);

    void inject(CohostReasonPrivateFeedbackTextInputFragment cohostReasonPrivateFeedbackTextInputFragment);

    void inject(CohostReasonSelectionFragment cohostReasonSelectionFragment);

    void inject(CohostUpsellFragment cohostUpsellFragment);

    void inject(CohostingContractFragment cohostingContractFragment);

    void inject(CohostingInvitationErrorFragment cohostingInvitationErrorFragment);

    void inject(CohostingInvitationExpiredFragment cohostingInvitationExpiredFragment);

    void inject(CohostingInviteFriendConfirmationFragment cohostingInviteFriendConfirmationFragment);

    void inject(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment);

    void inject(CohostingListingLevelNotificationSettingFragment cohostingListingLevelNotificationSettingFragment);

    void inject(CohostingMakePrimaryHostFragment cohostingMakePrimaryHostFragment);

    void inject(CohostingServicesIntroFragment cohostingServicesIntroFragment);

    void inject(CohostingShareEarningsWithFeeOptionFragment cohostingShareEarningsWithFeeOptionFragment);

    void inject(CohostingStopShareEarningsFragment cohostingStopShareEarningsFragment);

    void inject(ConfirmInvitationAcceptedFragment confirmInvitationAcceptedFragment);

    void inject(ListingManagersPickerFragment listingManagersPickerFragment);

    void inject(PendingCohostDetailsFragment pendingCohostDetailsFragment);

    void inject(RemoveCohostFragment removeCohostFragment);
}
