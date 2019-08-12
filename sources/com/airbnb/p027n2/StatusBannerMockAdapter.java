package com.airbnb.p027n2;

import com.airbnb.p027n2.components.StatusBanner;

/* renamed from: com.airbnb.n2.StatusBannerMockAdapter */
public final class StatusBannerMockAdapter implements DLSMockAdapter<StatusBanner> {
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

    public void bindView(StatusBanner view, int position) {
        switch (position) {
            case 0:
                StatusBanner.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
