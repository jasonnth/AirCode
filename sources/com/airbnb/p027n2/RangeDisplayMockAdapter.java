package com.airbnb.p027n2;

import com.airbnb.p027n2.components.RangeDisplay;

/* renamed from: com.airbnb.n2.RangeDisplayMockAdapter */
public final class RangeDisplayMockAdapter implements DLSMockAdapter<RangeDisplay> {
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

    public void bindView(RangeDisplay view, int position) {
        switch (position) {
            case 0:
                RangeDisplay.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
