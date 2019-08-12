package com.airbnb.p027n2;

import com.airbnb.p027n2.components.GuideImageMarquee;

/* renamed from: com.airbnb.n2.GuideImageMarqueeMockAdapter */
public final class GuideImageMarqueeMockAdapter implements DLSMockAdapter<GuideImageMarquee> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Sample Image";
            case 1:
                return "Drawable";
            case 2:
                return "No Image";
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
            default:
                return null;
        }
    }

    public void bindView(GuideImageMarquee view, int position) {
        switch (position) {
            case 0:
                GuideImageMarquee.mock(view);
                return;
            case 1:
                GuideImageMarquee.mockDrawable(view);
                return;
            case 2:
                GuideImageMarquee.mockNoImage(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
