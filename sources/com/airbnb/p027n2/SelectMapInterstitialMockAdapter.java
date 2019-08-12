package com.airbnb.p027n2;

import com.airbnb.p027n2.components.decide.select.SelectMapInterstitial;

/* renamed from: com.airbnb.n2.SelectMapInterstitialMockAdapter */
public final class SelectMapInterstitialMockAdapter implements DLSMockAdapter<SelectMapInterstitial> {
    public int getItemCount() {
        return 1;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(SelectMapInterstitial view, int position) {
        switch (position) {
            case 0:
                SelectMapInterstitial.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
