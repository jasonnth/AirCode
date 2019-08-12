package com.airbnb.android.cityregistration.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class CityRegistrationNextStepsFragment extends CityRegistrationBaseFragment {
    @BindView
    AirButton doneButton;
    @BindView
    SheetMarquee sheetMarquee;

    public static CityRegistrationNextStepsFragment create() {
        return new CityRegistrationNextStepsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5630R.layout.fragment_city_registration_next_steps, container, false);
        bindViews(view);
        ListingRegistrationContent content = this.listingRegistrationProcess.getContent();
        this.sheetMarquee.setTitle(content.getNextTitle());
        this.sheetMarquee.setSubtitle(TextUtils.join("\n\n", content.getNextSubtitles()));
        this.doneButton.setText(content.getNextCallToAction());
        return view;
    }

    @OnClick
    public void onDone() {
        this.controller.finishOk();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationNextSteps;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onSaveActionPressed() {
        return true;
    }
}
