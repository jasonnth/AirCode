package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.viewcomponents.models.RequirementChecklistRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationEmergencyPolicyAdapter extends ReasonPickerAdapter {
    public ReservationCancellationEmergencyPolicyAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo) {
        super(callback, reservationCancellationInfo);
        addTitleRes(C0880R.string.emergency_cancellation_title);
        addModel(new StandardRowEpoxyModel_().title(C0880R.string.emergency_cancellation_description).titleMaxLine(10));
        for (int stringRes : new int[]{C0880R.string.maintenance_issue_emergency, C0880R.string.family_death_emergency, C0880R.string.illness_emergency, C0880R.string.property_damage_emergency, C0880R.string.disaster_emergency, C0880R.string.political_unreset_emergency}) {
            addModel(new RequirementChecklistRowEpoxyModel_().title(stringRes).rowDrawableRes(C0880R.C0881drawable.n2_ic_bullet));
        }
        addModel(new StandardRowEpoxyModel_().title(C0880R.string.emergency_cancellation_contact_us).titleMaxLine(10));
        addKeepReservationLink();
    }
}
