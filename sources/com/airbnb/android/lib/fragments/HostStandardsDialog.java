package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;

public class HostStandardsDialog extends ViewPagerFtueDialog {
    private static final String BODY = "body_arg";
    private static final int NUM_FTUE_PAGES = 1;
    private static final String TITLE = "title_arg";

    public static HostStandardsDialog newInstance(Fragment targetFragment, String title, String body) {
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(BODY, body);
        HostStandardsDialog f = new HostStandardsDialog();
        f.setArguments(args);
        f.setTargetFragment(targetFragment, 0);
        return f;
    }

    public Fragment forStep(int step) {
        FragmentManager fragMgr = getFragmentManager();
        switch (step) {
            case 0:
                return DialogViewPagerFtueFragment.findFragment(fragMgr, C0880R.C0881drawable.hs_hidden, getArguments().getString(TITLE), getArguments().getString(BODY));
            default:
                return null;
        }
    }

    public int getNumPages() {
        return 1;
    }

    public OnClickListener getStickyButtonClickListener() {
        return HostStandardsDialog$$Lambda$1.lambdaFactory$(this);
    }

    public String getStickyButtonTitle() {
        return AirbnbApplication.appContext(getActivity()).getString(C0880R.string.okay);
    }
}
