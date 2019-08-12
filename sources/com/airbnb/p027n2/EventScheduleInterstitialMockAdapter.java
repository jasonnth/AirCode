package com.airbnb.p027n2;

import com.airbnb.p027n2.components.EventScheduleInterstitial;

/* renamed from: com.airbnb.n2.EventScheduleInterstitialMockAdapter */
public final class EventScheduleInterstitialMockAdapter implements DLSMockAdapter<EventScheduleInterstitial> {
    public int getItemCount() {
        return 1;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Default";
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

    public void bindView(EventScheduleInterstitial view, int position) {
        switch (position) {
            case 0:
                EventScheduleInterstitial.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
