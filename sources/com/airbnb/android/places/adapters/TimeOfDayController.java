package com.airbnb.android.places.adapters;

import android.content.Context;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.viewmodels.AddToPlanButtonEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class TimeOfDayController extends AirEpoxyController {
    private final Context context;
    private final OnTimeSelectedListener onTimeSelectedListener;
    private TimeOfDay selectedTime;

    public interface OnTimeSelectedListener {
        void onTimeTapped(TimeOfDay timeOfDay, int i);
    }

    public enum TimeOfDay {
        Morning(C7627R.string.explore_places_morning, 8),
        Afternoon(C7627R.string.explore_places_afternoon, 13),
        Evening(C7627R.string.explore_places_evening, 18);
        
        public final int hour;
        final int titleRes;

        private TimeOfDay(int titleRes2, int hour2) {
            this.titleRes = titleRes2;
            this.hour = hour2;
        }
    }

    public TimeOfDayController(Context context2, OnTimeSelectedListener onTimeSelectedListener2) {
        this.context = context2;
        this.onTimeSelectedListener = onTimeSelectedListener2;
        requestModelBuild();
    }

    public void setSelectedTime(TimeOfDay selectedTime2) {
        this.selectedTime = selectedTime2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        DisplayOptions displayOptions = DisplayOptions.forAddToPlanButton(this.context, DisplayType.Horizontal);
        TimeOfDay[] timeOfDayValues = TimeOfDay.values();
        for (int i = 0; i < timeOfDayValues.length; i++) {
            TimeOfDay timeOfDay = timeOfDayValues[i];
            AddToPlanButtonEpoxyModel_ model = new AddToPlanButtonEpoxyModel_().titleRes(timeOfDay.titleRes).clickListener(TimeOfDayController$$Lambda$1.lambdaFactory$(this, timeOfDay, i)).m6379id((long) timeOfDay.hour).displayOptions(displayOptions);
            if (timeOfDay == this.selectedTime) {
                model.selected(true);
            }
            add((EpoxyModel<?>) model);
        }
    }
}
