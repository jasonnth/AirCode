package com.airbnb.android.lib.fragments;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class SearchSettingsFragment$$Lambda$1 implements OnCheckedChangeListener {
    private final SearchSettingsFragment arg$1;

    private SearchSettingsFragment$$Lambda$1(SearchSettingsFragment searchSettingsFragment) {
        this.arg$1 = searchSettingsFragment;
    }

    public static OnCheckedChangeListener lambdaFactory$(SearchSettingsFragment searchSettingsFragment) {
        return new SearchSettingsFragment$$Lambda$1(searchSettingsFragment);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.sharedPrefsHelper.setShowTotalPriceEnabled(z);
    }
}
