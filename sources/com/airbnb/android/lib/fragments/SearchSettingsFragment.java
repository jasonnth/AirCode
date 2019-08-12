package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SwitchRow;

public class SearchSettingsFragment extends AirFragment {
    protected SharedPrefsHelper sharedPrefsHelper;
    @BindView
    SwitchRow showTotalPriceSetting;
    @BindView
    AirToolbar toolbar;

    public static Fragment newInstance() {
        return new SearchSettingsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_search_settings, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        setToolbar(this.toolbar);
        this.toolbar.setTitle(C0880R.string.search_settings);
        setupShowTotalPriceSetting();
        return view;
    }

    private void setupShowTotalPriceSetting() {
        this.showTotalPriceSetting.setChecked(this.sharedPrefsHelper.isShowTotalPriceEnabled());
        this.showTotalPriceSetting.setOnCheckedChangeListener(SearchSettingsFragment$$Lambda$1.lambdaFactory$(this));
    }
}
