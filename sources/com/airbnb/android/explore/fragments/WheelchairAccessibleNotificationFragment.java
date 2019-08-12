package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.components.HeroMarquee;

public final class WheelchairAccessibleNotificationFragment extends AirFragment {
    @BindView
    HeroMarquee heroMarquee;

    public static WheelchairAccessibleNotificationFragment newInstance() {
        return new WheelchairAccessibleNotificationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0857R.layout.fragment_wheelchair_accessible_notification, container, false);
        bindViews(view);
        this.heroMarquee.setFirstButtonClickListener(WheelchairAccessibleNotificationFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }
}
