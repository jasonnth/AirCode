package com.airbnb.android.airmapview;

public class GoogleChinaWebViewMapFragment extends GoogleWebViewMapFragment {
    public static GoogleChinaWebViewMapFragment newInstance(AirMapType mapType) {
        return (GoogleChinaWebViewMapFragment) new GoogleChinaWebViewMapFragment().setArguments(mapType);
    }

    /* access modifiers changed from: protected */
    public boolean isChinaMode() {
        return true;
    }
}
