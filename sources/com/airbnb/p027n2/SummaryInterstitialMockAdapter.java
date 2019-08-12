package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SummaryInterstitial;

/* renamed from: com.airbnb.n2.SummaryInterstitialMockAdapter */
public final class SummaryInterstitialMockAdapter implements DLSMockAdapter<SummaryInterstitial> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "with subtitle";
            case 1:
                return "no subtitle";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 1:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(SummaryInterstitial view, int position) {
        switch (position) {
            case 0:
                SummaryInterstitial.mock(view);
                return;
            case 1:
                SummaryInterstitial.mockNoSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
