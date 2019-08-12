package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SheetMarquee;

/* renamed from: com.airbnb.n2.SheetMarqueeMockAdapter */
public final class SheetMarqueeMockAdapter implements DLSMockAdapter<SheetMarquee> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "SheetMarquee";
            case 1:
                return "SheetMarquee + Subtitle";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 1:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(SheetMarquee view, int position) {
        switch (position) {
            case 0:
                SheetMarquee.mock(view);
                return;
            case 1:
                SheetMarquee.mockWithSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 1;
    }
}
