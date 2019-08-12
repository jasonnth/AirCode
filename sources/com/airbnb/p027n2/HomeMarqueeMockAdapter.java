package com.airbnb.p027n2;

import com.airbnb.p027n2.components.HomeMarquee;

/* renamed from: com.airbnb.n2.HomeMarqueeMockAdapter */
public final class HomeMarqueeMockAdapter implements DLSMockAdapter<HomeMarquee> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "HomeMarquee";
            case 1:
                return "HomeMarquee + Reviews";
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

    public void bindView(HomeMarquee view, int position) {
        switch (position) {
            case 0:
                HomeMarquee.mock(view);
                return;
            case 1:
                HomeMarquee.mockWithRating(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 1;
    }
}
