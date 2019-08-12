package com.airbnb.p027n2;

import com.airbnb.p027n2.components.FullScreenImageMarquee;

/* renamed from: com.airbnb.n2.FullScreenImageMarqueeMockAdapter */
public final class FullScreenImageMarqueeMockAdapter implements DLSMockAdapter<FullScreenImageMarquee> {
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

    public void bindView(FullScreenImageMarquee view, int position) {
        switch (position) {
            case 0:
                FullScreenImageMarquee.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
