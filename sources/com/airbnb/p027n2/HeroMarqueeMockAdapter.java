package com.airbnb.p027n2;

import com.airbnb.p027n2.components.HeroMarquee;

/* renamed from: com.airbnb.n2.HeroMarqueeMockAdapter */
public final class HeroMarqueeMockAdapter implements DLSMockAdapter<HeroMarquee> {
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
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(HeroMarquee view, int position) {
        switch (position) {
            case 0:
                HeroMarquee.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
