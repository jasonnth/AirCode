package com.airbnb.android.misnap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.p027n2.components.AirToolbar;

public class TakeSelfieHelpFragment extends AirFragment {
    @BindView
    AirToolbar toolbar;

    public static TakeSelfieHelpFragment newInstance() {
        return new TakeSelfieHelpFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7471R.layout.fragment_take_selfie_help, container, false);
        bindViews(view);
        return view;
    }

    public void onResume() {
        super.onResume();
        AirActivity airActivity = getAirActivity();
        airActivity.setSupportActionBar(this.toolbar);
        airActivity.setupActionBar("");
        airActivity.getSupportActionBar().setHomeAsUpIndicator(C7471R.C7472drawable.n2_icon_close);
        this.toolbar.setTheme(3);
    }
}
