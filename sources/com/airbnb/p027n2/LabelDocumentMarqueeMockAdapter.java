package com.airbnb.p027n2;

import com.airbnb.p027n2.components.LabelDocumentMarquee;

/* renamed from: com.airbnb.n2.LabelDocumentMarqueeMockAdapter */
public final class LabelDocumentMarqueeMockAdapter implements DLSMockAdapter<LabelDocumentMarquee> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "LabelDocumentMarquee";
            case 1:
                return "";
            case 2:
                return "";
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

    public void bindView(LabelDocumentMarquee view, int position) {
        switch (position) {
            case 0:
                LabelDocumentMarquee.mock(view);
                return;
            case 1:
                LabelDocumentMarquee.mockWithoutLabel(view);
                return;
            case 2:
                LabelDocumentMarquee.mockWithoutCaption(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
