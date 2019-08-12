package com.airbnb.android.places.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindDimen;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.RestaurantAvailability;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.PlaceJitneyLogger;
import com.airbnb.android.places.ResyController;
import com.airbnb.android.places.ResyController.ResyControllerProvider;
import com.airbnb.android.places.ResyController.ResyTimeSlotClickListener;
import com.airbnb.android.places.ResyState;
import com.airbnb.android.places.adapters.ResyFragmentController;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooter;
import com.airbnb.p027n2.epoxy.EpoxyItemAnimator;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class ResyFragment extends AirFragment {
    private ResyFragmentController controller;
    @BindView
    CoordinatorLayout coordinatorLayout;
    private final SnackbarWrapper doubleConfirmationSnackbar = new SnackbarWrapper();
    private PlaceJitneyLogger placeJitneyLogger;
    @BindView
    FixedFlowActionFooter primaryButton;
    @BindDimen
    float primaryButtonHeight;
    @BindView
    RecyclerView recyclerView;
    private ResyController resyController;
    private final ResyTimeSlotClickListener resyTimeSlotClickListener = ResyFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    AirToolbar toolbar;

    public static ResyFragment newInstance() {
        return new ResyFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7627R.layout.fragment_resy, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.primaryButton.setTranslationY(this.primaryButtonHeight);
        this.doubleConfirmationSnackbar.view(this.coordinatorLayout);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.resyController = ((ResyControllerProvider) getActivity()).getResyController();
        this.controller = new ResyFragmentController(getContext(), this.resyController, this.resyTimeSlotClickListener);
        this.recyclerView.setItemAnimator(new EpoxyItemAnimator());
        this.recyclerView.setAdapter(this.controller.getAdapter());
        this.resyController.attachResySticky(ResyFragment$$Lambda$2.lambdaFactory$(this));
        this.placeJitneyLogger = new PlaceJitneyLogger(this.loggingContextFactory, this.resyController);
        this.placeJitneyLogger.resyPageView();
    }

    public void onStop() {
        super.onStop();
        this.resyController.unbindListener();
    }

    /* access modifiers changed from: private */
    public void setResyState(ResyState resyState) {
        this.controller.setData(resyState);
        RestaurantAvailability selectedTime = resyState.selectedTime();
        showPrimaryButtonAnimate(selectedTime != null);
        if (selectedTime != null && selectedTime.hasConfirmationMessages()) {
            this.doubleConfirmationSnackbar.body(TextUtils.join(getString(C7627R.string.newline_separator), selectedTime.getConfirmationMessages())).buildAndShow();
        } else if (this.doubleConfirmationSnackbar.isShown()) {
            this.doubleConfirmationSnackbar.dismiss();
        }
        if (selectedTime != null) {
            StringBuilder sb = new StringBuilder(resyState.getDateForDisplay(getContext(), true)).append(getString(C7627R.string.bullet_with_space)).append(selectedTime.getTimeString(getContext()));
            int guests = resyState.guests();
            String guestsString = getResources().getQuantityString(C7627R.plurals.x_guests, guests, new Object[]{Integer.valueOf(guests)});
            this.primaryButton.setTitle((CharSequence) sb);
            this.primaryButton.setSubtitle((CharSequence) guestsString);
            this.primaryButton.setButtonOnClickListener(ResyFragment$$Lambda$3.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$new$1(ResyFragment resyFragment, RestaurantAvailability timeSlot) {
        resyFragment.resyController.setSelectedTimeSlot(timeSlot);
        resyFragment.placeJitneyLogger.resyPageClickTimeSlot();
    }

    private void showPrimaryButtonAnimate(boolean show) {
        float translationY = this.primaryButton.getTranslationY();
        if (translationY != 0.0f || translationY == this.primaryButtonHeight) {
            if (translationY == this.primaryButtonHeight && translationY != 0.0f && show) {
                this.primaryButton.animate().translationY(0.0f);
                ViewLibUtils.setPaddingBottom(this.recyclerView, (int) this.primaryButtonHeight);
                ViewLibUtils.setPaddingBottom(this.coordinatorLayout, (int) this.primaryButtonHeight);
            }
        } else if (!show) {
            this.primaryButton.animate().translationY(this.primaryButtonHeight);
            ViewLibUtils.setPaddingBottom(this.recyclerView, 0);
            ViewLibUtils.setPaddingBottom(this.coordinatorLayout, 0);
        }
    }
}
