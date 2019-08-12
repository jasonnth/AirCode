package com.airbnb.android.managelisting.settings;

import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.utils.listing.CheckinDisplay;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.epoxy.Typed2AirEpoxyController;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManageListingAllCheckInMethodsController extends Typed2AirEpoxyController<List<CheckInInformation>, CheckInInformation> {
    DocumentMarqueeEpoxyModel_ headerRow;
    private final Listener listener;

    public interface Listener {
        void editMethodNoteClicked(CheckInInformation checkInInformation);

        void methodClicked(CheckInInformation checkInInformation);
    }

    public ManageListingAllCheckInMethodsController(Listener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<CheckInInformation> checkInInformation, CheckInInformation selectedMethod) {
        this.headerRow.titleRes(C7368R.string.manage_listing_check_in_entry_method_title).addTo(this);
        int currGroupId = -1;
        Set<Integer> usedGroupIds = new HashSet<>();
        for (CheckInInformation option : CheckInInformation.sortedAndGroupedMethods(checkInInformation)) {
            if (currGroupId != option.getGroupId()) {
                currGroupId = option.getGroupId();
                addNewGroupHeader(Integer.valueOf(currGroupId), option, usedGroupIds);
            }
            boolean selected = selectedMethod != null && selectedMethod.getAmenityNumber() == option.getAmenityNumber();
            boolean shouldShowLinkRow = selected && !TextUtils.isEmpty(option.getLocalizedInstructionButtonLabel());
            ToggleActionRowEpoxyModel_ methodRow = new ToggleActionRowEpoxyModel_().title(option.getAmenity().getName()).subtitle(CheckinDisplay.getSelectTypeInformationalString(option)).checked(selected).readOnly(true).clickListener(ManageListingAllCheckInMethodsController$$Lambda$1.lambdaFactory$(this, option)).showDivider(!shouldShowLinkRow).m5701id((CharSequence) "checkin_method", (long) option.getAmenity().getAmenityId());
            methodRow.addTo(this);
            if (shouldShowLinkRow) {
                addLinkRow(methodRow, option);
            }
        }
    }

    private void addNewGroupHeader(Integer currGroupId, CheckInInformation option, Set<Integer> usedGroupIds) {
        if (usedGroupIds.contains(currGroupId)) {
            BugsnagWrapper.throwOrNotify(new RuntimeException("CheckInMethod grouping failed. Saw groupId " + currGroupId + " multiple times"));
        }
        usedGroupIds.add(currGroupId);
        new SectionHeaderEpoxyModel_().title(option.getLocalizedGroupName()).m5557id((CharSequence) "checkin_method_group", (long) currGroupId.intValue()).addTo(this);
    }

    private void addLinkRow(ToggleActionRowEpoxyModel_ methodRow, CheckInInformation option) {
        new LinkActionRowEpoxyModel_().text(option.getLocalizedInstructionButtonLabel()).clickListener(ManageListingAllCheckInMethodsController$$Lambda$2.lambdaFactory$(this, option)).m5041id((CharSequence) "edit_method_note", (long) option.getAmenity().getAmenityId()).addTo(this);
    }
}
