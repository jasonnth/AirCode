package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import icepick.State;

public class CohostingListingLevelNotificationEpoxyController extends AirEpoxyController {
    SimpleTextRowEpoxyModel_ descriptionRow;
    ToggleActionRowEpoxyModel_ detailedNotificationToggle;
    private final boolean isCurrentUserListingAdmin;
    private final Listing listing;
    SwitchRowEpoxyModel_ listingNotificationSwitchRow;
    ToggleActionRowEpoxyModel_ monthlyNotificationToggle;
    @State
    MuteType muteType = this.oldMuteType;
    private final MuteType oldMuteType;
    DocumentMarqueeEpoxyModel_ titleRow;

    public CohostingListingLevelNotificationEpoxyController(CohostManagementDataController controller) {
        this.listing = controller.getListing();
        this.isCurrentUserListingAdmin = controller.isCurrentUserListingAdmin();
        this.oldMuteType = controller.getMuteType();
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        this.titleRow.titleRes(C5658R.string.cohosting_notification_setting_title).captionText((CharSequence) this.listing.getName());
        ToggleActionRowEpoxyModel_ checkedChangedlistener = this.detailedNotificationToggle.titleRes(C5658R.string.cohosting_notification_setting_all_activity_toggle).checked(this.muteType == MuteType.UNMUTED).checkedChangedlistener(CohostingListingLevelNotificationEpoxyController$$Lambda$1.lambdaFactory$(this));
        if (this.muteType == MuteType.MUTED) {
            z = true;
        } else {
            z = false;
        }
        checkedChangedlistener.enabled(z).addIf(this.isCurrentUserListingAdmin, (EpoxyController) this);
        ToggleActionRowEpoxyModel_ titleRes = this.monthlyNotificationToggle.titleRes(C5658R.string.cohosting_notification_setting_monthly_report_toggle);
        if (this.muteType == MuteType.MUTED) {
            z2 = true;
        } else {
            z2 = false;
        }
        ToggleActionRowEpoxyModel_ checkedChangedlistener2 = titleRes.checked(z2).checkedChangedlistener(CohostingListingLevelNotificationEpoxyController$$Lambda$2.lambdaFactory$(this));
        if (this.muteType == MuteType.UNMUTED) {
            z3 = true;
        } else {
            z3 = false;
        }
        checkedChangedlistener2.enabled(z3).addIf(this.isCurrentUserListingAdmin, (EpoxyController) this);
        SwitchRowEpoxyModel_ titleRes2 = this.listingNotificationSwitchRow.style(SwitchStyle.Filled).updateModelWithCheckedState(false).titleRes(C5658R.string.cohosting_notification_setting_listing_notifications_toggle);
        if (this.muteType == MuteType.UNMUTED) {
            z4 = true;
        } else {
            z4 = false;
        }
        SwitchRowEpoxyModel_ checkedChangeListener = titleRes2.checked(z4).checkedChangeListener(CohostingListingLevelNotificationEpoxyController$$Lambda$3.lambdaFactory$(this));
        if (this.isCurrentUserListingAdmin) {
            z5 = false;
        }
        checkedChangeListener.addIf(z5, (EpoxyController) this);
        this.descriptionRow.textRes(this.isCurrentUserListingAdmin ? C5658R.string.cohosting_notification_setting_description_for_listing_admin : C5658R.string.cohosting_notification_setting_description_for_cohost);
    }

    public MuteType getMuteType() {
        return this.muteType;
    }

    public boolean hasChanged() {
        return this.muteType != this.oldMuteType;
    }

    /* access modifiers changed from: private */
    public void updateMuteType(MuteType muteType2) {
        this.muteType = muteType2;
        requestModelBuild();
    }

    /* access modifiers changed from: private */
    public void updateMuteTypeWhenSwitch(boolean checked) {
        this.muteType = checked ? MuteType.UNMUTED : MuteType.MUTED;
        requestModelBuild();
    }
}
