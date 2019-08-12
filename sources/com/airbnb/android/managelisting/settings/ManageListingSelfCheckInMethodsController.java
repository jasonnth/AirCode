package com.airbnb.android.managelisting.settings;

import android.content.Context;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.utils.listing.CheckinDisplay;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.util.List;

public class ManageListingSelfCheckInMethodsController extends TypedAirEpoxyController<List<CheckInInformation>> {
    private final Context context;
    DocumentMarqueeEpoxyModel_ headerRow;
    private final Listener listener;

    public interface Listener {
        void methodSelected(CheckInInformation checkInInformation);
    }

    public ManageListingSelfCheckInMethodsController(Context context2, Listener listener2) {
        this.context = context2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<CheckInInformation> checkInInformation) {
        this.headerRow.titleRes(C7368R.string.manage_listing_check_in_entry_method_title).addTo(this);
        for (CheckInInformation option : checkInInformation) {
            new StandardRowEpoxyModel_().title((CharSequence) option.getAmenity().getName()).subtitle((CharSequence) CheckinDisplay.getSelectTypeInformationalString(option)).actionText(option.isIsOptionAvailable().booleanValue() ? C7368R.string.edit : C7368R.string.add).clickListener(ManageListingSelfCheckInMethodsController$$Lambda$1.lambdaFactory$(this, option)).m5605id((CharSequence) "checkin_option", (long) option.getAmenity().getAmenityId()).addTo(this);
        }
    }
}
