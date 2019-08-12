package com.airbnb.android.places.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.PlaceJitneyLogger;
import com.airbnb.android.places.adapters.PickAddToPlansController;
import com.airbnb.android.places.adapters.PickAddToPlansController.OnPlanSelectedListener;
import com.airbnb.android.places.adapters.TimeOfDayController;
import com.airbnb.android.places.adapters.TimeOfDayController.OnTimeSelectedListener;
import com.airbnb.android.places.adapters.TimeOfDayController.TimeOfDay;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;
import icepick.State;

public class PickAddToPlansFragment extends AirFragment {
    private static final int DEFAULT_OFFSET_TIME = 12;
    public static final String EXTRA_PLANS_CUSTOM = "plans_custom";
    public static final String EXTRA_PLANS_DATE_TIME = "plans_date_time";
    public static final String EXTRA_PLANS_GO_NOW = "plans_go_now";
    private static final String KEY_PLACE_ACTIVITY_MODEL = "key_place_activity_model";
    private static final int SLIDE_OUT_ANIMATION_TIME_MILLIS = 300;
    /* access modifiers changed from: private */
    public PlaceActivity activityModel;
    @BindView
    FixedActionFooter fixedActionFooter;
    private PickAddToPlansController mainController;
    @BindView
    Carousel mainRecyclerView;
    private final OnPlanSelectedListener onPlanSelectedListener = new OnPlanSelectedListener() {
        public void onDateTapped(AirDate airDate, int loggingPositionInCarousel) {
            if (PickAddToPlansFragment.this.selectedDate == null || !airDate.isSameDay(PickAddToPlansFragment.this.selectedDate)) {
                PickAddToPlansFragment.this.selectedDate = airDate;
            } else {
                PickAddToPlansFragment.this.selectedDate = null;
                PickAddToPlansFragment.this.selectedTimeOfDay = null;
            }
            PickAddToPlansFragment.this.setScreenState();
            PickAddToPlansFragment.this.placeJitneyLogger.addToPlansDateClick((long) PickAddToPlansFragment.this.activityModel.getId(), loggingPositionInCarousel, airDate);
        }

        public void onCustomDateTime(int loggingPositionInCarousel) {
            Intent intent = new Intent();
            intent.putExtra(PickAddToPlansFragment.EXTRA_PLANS_CUSTOM, true);
            PickAddToPlansFragment.this.getAirActivity().setResult(-1, intent);
            PickAddToPlansFragment.this.placeJitneyLogger.addToPlansCustomDateClick((long) PickAddToPlansFragment.this.activityModel.getId(), loggingPositionInCarousel);
            PickAddToPlansFragment.this.slideOut();
        }

        public void onGoNow(int loggingPositionInCarousel) {
            Intent intent = new Intent();
            intent.putExtra(PickAddToPlansFragment.EXTRA_PLANS_GO_NOW, true);
            PickAddToPlansFragment.this.getAirActivity().setResult(-1, intent);
            PickAddToPlansFragment.this.placeJitneyLogger.addToPlansGoNowClick((long) PickAddToPlansFragment.this.activityModel.getId(), loggingPositionInCarousel);
            PickAddToPlansFragment.this.slideOut();
        }
    };
    private final OnTimeSelectedListener onTimeSelectedListener = PickAddToPlansFragment$$Lambda$1.lambdaFactory$(this);
    /* access modifiers changed from: private */
    public PlaceJitneyLogger placeJitneyLogger;
    @State
    AirDate selectedDate;
    @State
    TimeOfDay selectedTimeOfDay;
    @BindView
    Carousel timeOfDayButtons;
    private TimeOfDayController timeOfDayController;

    public static AirFragment newInstance(PlaceActivity activityModel2) {
        return (AirFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PickAddToPlansFragment()).putParcelable(KEY_PLACE_ACTIVITY_MODEL, activityModel2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7627R.layout.fragment_pick_add_to_plans, container, false);
        bindViews(view);
        this.activityModel = (PlaceActivity) getArguments().getParcelable(KEY_PLACE_ACTIVITY_MODEL);
        this.placeJitneyLogger = new PlaceJitneyLogger(this.loggingContextFactory);
        this.mainController = new PickAddToPlansController(getContext(), this.onPlanSelectedListener);
        this.mainRecyclerView.setHasFixedSize(true);
        this.mainRecyclerView.setAdapter(this.mainController.getAdapter());
        this.timeOfDayController = new TimeOfDayController(getContext(), this.onTimeSelectedListener);
        this.timeOfDayButtons.setHasFixedSize(true);
        this.timeOfDayButtons.setAdapter(this.timeOfDayController.getAdapter());
        setScreenState();
        return view;
    }

    static /* synthetic */ void lambda$new$0(PickAddToPlansFragment pickAddToPlansFragment, TimeOfDay timeOfDay, int loggingPositionInCarousel) {
        pickAddToPlansFragment.selectedTimeOfDay = timeOfDay;
        pickAddToPlansFragment.setScreenState();
        pickAddToPlansFragment.placeJitneyLogger.addToPlansTimeClick((long) pickAddToPlansFragment.activityModel.getId(), loggingPositionInCarousel, timeOfDay);
    }

    /* access modifiers changed from: private */
    public void setScreenState() {
        this.mainController.setSelectedDate(this.selectedDate);
        this.timeOfDayController.setSelectedTime(this.selectedTimeOfDay);
        if (this.selectedDate != null) {
            Paris.style(this.fixedActionFooter).applyBabu();
        } else {
            Paris.style(this.fixedActionFooter).apply(C7627R.C7632style.n2_FixedActionFooter_GrayOutline);
        }
        this.fixedActionFooter.setButtonText(this.selectedDate != null ? C7627R.string.places_cta_add_to_plans : C7627R.string.cancel);
        ViewLibUtils.setVisibleIf(this.timeOfDayButtons, this.selectedDate != null);
    }

    /* access modifiers changed from: private */
    public void slideOut() {
        View view = getView();
        view.animate().setDuration(300).translationY((float) view.getHeight());
        this.mainRecyclerView.postDelayed(PickAddToPlansFragment$$Lambda$2.lambdaFactory$(this), 300);
    }

    static /* synthetic */ void lambda$slideOut$1(PickAddToPlansFragment pickAddToPlansFragment) {
        if (pickAddToPlansFragment.isResumed()) {
            pickAddToPlansFragment.getActivity().finish();
        }
    }

    @OnClick
    public void clickButton() {
        if (this.selectedDate != null) {
            getAirActivity().setResult(-1, new Intent().putExtra(EXTRA_PLANS_DATE_TIME, getAirDateTime()));
        } else {
            getAirActivity().setResult(0);
        }
        slideOut();
    }

    private AirDateTime getAirDateTime() {
        Check.notNull(this.selectedDate);
        return new AirDateTime(this.selectedDate.getYear(), this.selectedDate.getMonthOfYear(), this.selectedDate.getDayOfMonth(), this.selectedTimeOfDay != null ? this.selectedTimeOfDay.hour : 12, 0, 0);
    }
}
