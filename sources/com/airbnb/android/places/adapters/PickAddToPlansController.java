package com.airbnb.android.places.adapters;

import android.content.Context;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.viewmodels.AddToPlanButtonEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class PickAddToPlansController extends AirEpoxyController {
    private static final int DAYS_TO_SHOW = 5;
    private final Context context;
    private final String customString;
    private final String dateFormat;
    private final String getDirectionsString;
    private final String goNowString;
    private final OnPlanSelectedListener onPlanSelectedListener;
    private final String pickDateString;
    private AirDate selectedDate;

    public interface OnPlanSelectedListener {
        void onCustomDateTime(int i);

        void onDateTapped(AirDate airDate, int i);

        void onGoNow(int i);
    }

    public PickAddToPlansController(Context context2, OnPlanSelectedListener onPlanSelectedListener2) {
        this.context = context2;
        this.onPlanSelectedListener = onPlanSelectedListener2;
        this.dateFormat = context2.getString(C7627R.string.date_name_format);
        this.goNowString = context2.getString(C7627R.string.places_add_to_plans_go_now);
        this.getDirectionsString = context2.getString(C7627R.string.places_add_to_plans_get_directions);
        this.customString = context2.getString(C7627R.string.places_add_to_plans_custom);
        this.pickDateString = context2.getString(C7627R.string.places_add_to_plans_pick_date);
        requestModelBuild();
    }

    public void setSelectedDate(AirDate selectedDate2) {
        this.selectedDate = selectedDate2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        DisplayOptions displayOptions = DisplayOptions.forAddToPlanButton(this.context, DisplayType.Horizontal);
        new AddToPlanButtonEpoxyModel_().titleText(this.goNowString).subtitleText(this.getDirectionsString).clickListener(PickAddToPlansController$$Lambda$1.lambdaFactory$(this)).m6381id((CharSequence) this.goNowString).displayOptions(DisplayOptions.forAddToPlanButton(this.context, DisplayType.Horizontal)).addTo(this);
        AirDate today = AirDate.today();
        for (int i = 0; i < 5; i++) {
            AirDate date = today.plusDays(i);
            new AddToPlanButtonEpoxyModel_().titleText(date.getRelativeDateStringFromNow(this.context)).subtitleText(date.formatDate(this.dateFormat)).clickListener(PickAddToPlansController$$Lambda$2.lambdaFactory$(this, date, i + 1)).m6379id(date.getTimeInMillisAtStartOfDay()).selected(this.selectedDate != null && date.isSameDay(this.selectedDate)).displayOptions(displayOptions).addTo(this);
        }
        new AddToPlanButtonEpoxyModel_().titleText(this.customString).subtitleText(this.pickDateString).clickListener(PickAddToPlansController$$Lambda$3.lambdaFactory$(this)).m6381id((CharSequence) this.customString).displayOptions(DisplayOptions.forAddToPlanButton(this.context, DisplayType.Horizontal)).addTo(this);
    }
}
