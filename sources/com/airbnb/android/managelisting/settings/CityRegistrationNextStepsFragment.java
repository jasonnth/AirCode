package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class CityRegistrationNextStepsFragment extends ManageListingBaseFragment {
    @BindView
    AirButton doneButton;
    @BindView
    SheetMarquee sheetMarquee;

    public static CityRegistrationNextStepsFragment create() {
        return new CityRegistrationNextStepsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_city_registration_next_steps, container, false);
        bindViews(view);
        ListingRegistrationContent content = this.controller.listingRegistrationProcess.getContent();
        this.sheetMarquee.setTitle(content.getNextTitle());
        this.sheetMarquee.setSubtitle(TextUtils.join("\n\n", content.getNextSubtitles()));
        this.doneButton.setText(content.getNextCallToAction());
        return view;
    }

    @OnClick
    public void onDone() {
        this.controller.actionExecutor.popToHome();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationNextSteps;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
