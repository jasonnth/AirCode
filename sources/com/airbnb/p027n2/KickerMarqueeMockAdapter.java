package com.airbnb.p027n2;

import com.airbnb.p027n2.components.KickerMarquee;

/* renamed from: com.airbnb.n2.KickerMarqueeMockAdapter */
public final class KickerMarqueeMockAdapter implements DLSMockAdapter<KickerMarquee> {
    public int getItemCount() {
        return 5;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "";
            case 4:
                return "";
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
            case 2:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 3:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 4:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(KickerMarquee view, int position) {
        switch (position) {
            case 0:
                KickerMarquee.mock(view);
                return;
            case 1:
                KickerMarquee.mockOnlyTitle(view);
                return;
            case 2:
                KickerMarquee.mockTitleAndCaption(view);
                return;
            case 3:
                KickerMarquee.mockEverything(view);
                return;
            case 4:
                KickerMarquee.mockBabu(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
