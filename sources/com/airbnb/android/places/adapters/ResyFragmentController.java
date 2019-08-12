package com.airbnb.android.places.adapters;

import android.content.Context;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StepperRowEpoxyModel_;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.ResyController;
import com.airbnb.android.places.ResyController.ResyTimeSlotClickListener;
import com.airbnb.android.places.ResyState;
import com.airbnb.android.places.viewmodels.ResyRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;

public class ResyFragmentController extends TypedAirEpoxyController<ResyState> {
    private final Context context;
    StandardRowEpoxyModel_ dateRow;
    StepperRowEpoxyModel_ guestsRow;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    private final ResyController resyController;
    ResyRowEpoxyModel_ resyRow;
    private final ResyTimeSlotClickListener timeSlotClickListener;

    public ResyFragmentController(Context context2, ResyController resyController2, ResyTimeSlotClickListener timeSlotClickListener2) {
        this.context = context2;
        this.resyController = resyController2;
        this.timeSlotClickListener = timeSlotClickListener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(ResyState resyState) {
        this.marqueeModel.titleRes(C7627R.string.resy_make_reservation).addTo(this);
        this.dateRow.titleRes(C7627R.string.resy_date_title).subtitle(resyState.getDateForDisplay(this.context)).textRes(C7627R.string.change).clickListener(ResyFragmentController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        this.guestsRow.textRes(C7627R.string.resy_guests_title).value(resyState.guests()).valueChangedListener(ResyFragmentController$$Lambda$2.lambdaFactory$(this)).descriptionText(resyState.getGuestsString(this.context)).useOldDesign(true).minValue(1).addTo(this);
        this.resyRow.slotsOnly(true).timeSlotClickListener(this.timeSlotClickListener).resyState(resyState).addTo(this);
    }
}
