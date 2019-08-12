package com.airbnb.android.places.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.ResyController.ResyTimeSlotClickListener;
import com.airbnb.android.places.ResyState;
import com.airbnb.android.places.views.ResyRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ResyRowEpoxyModel extends AirEpoxyModel<ResyRow> {
    OnClickListener changeClickListener;
    ResyState resyState;
    int selectedTimeId = -1;
    boolean slotsOnly;
    ResyTimeSlotClickListener timeSlotClickListener;

    public void bind(ResyRow view) {
        super.bind(view);
        if (this.slotsOnly) {
            view.setTitle(null, null);
        } else {
            view.setTitle(new StringBuilder(this.resyState.getTableForGuestsString(view.getContext())).append(view.getResources().getString(C7627R.string.bullet_with_space)).append(this.resyState.getDateForDisplay(view.getContext())), this.changeClickListener);
        }
        view.setupTimeSlots(this.resyState.timeSlots(), this.resyState.selectedTime(), this.resyState.isLoading());
        view.setTimeSlotClickListener(this.timeSlotClickListener);
    }

    public int getDividerViewType() {
        return 0;
    }

    public boolean shouldSaveViewState() {
        return true;
    }
}
