package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSCityRegistrationNextStepsFragment extends LYSBaseFragment {
    @BindView
    AirButton doneButton;
    @BindView
    SheetMarquee sheetMarquee;
    @BindView
    AirToolbar toolbar;

    public static LYSCityRegistrationNextStepsFragment newInstance() {
        return new LYSCityRegistrationNextStepsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.fragment_city_registration_next_steps, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        ListingRegistrationContent content = this.controller.getListingRegistrationProcess().getContent();
        this.sheetMarquee.setTitle(content.getNextTitle());
        this.sheetMarquee.setSubtitle(TextUtils.join("\n\n", content.getNextSubtitles()));
        this.doneButton.setText(content.getNextCallToAction());
        return view;
    }

    @OnClick
    public void onDone() {
        this.controller.nextStep(LYSStep.CityRegistration);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationNextSteps;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
    }
}
