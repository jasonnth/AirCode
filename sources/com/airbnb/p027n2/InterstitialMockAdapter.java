package com.airbnb.p027n2;

import com.airbnb.p027n2.components.Interstitial;

/* renamed from: com.airbnb.n2.InterstitialMockAdapter */
public final class InterstitialMockAdapter implements DLSMockAdapter<Interstitial> {
    public int getItemCount() {
        return 4;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Default Babu";
            case 1:
                return "Background Raush";
            case 2:
                return "Background Hackberry";
            case 3:
                return "Babu no padding";
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
            case 2:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 3:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(Interstitial view, int position) {
        switch (position) {
            case 0:
                Interstitial.mock(view);
                return;
            case 1:
                Interstitial.mockRaush(view);
                return;
            case 2:
                Interstitial.mockHackberry(view);
                return;
            case 3:
                Interstitial.mockNoPadding(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
