package com.airbnb.p027n2;

import com.airbnb.p027n2.components.EditorialMarquee;

/* renamed from: com.airbnb.n2.EditorialMarqueeMockAdapter */
public final class EditorialMarqueeMockAdapter implements DLSMockAdapter<EditorialMarquee> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "With Description";
            case 2:
                return "With Description and Kicker";
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

    public void bindView(EditorialMarquee view, int position) {
        switch (position) {
            case 0:
                EditorialMarquee.mock(view);
                return;
            case 1:
                EditorialMarquee.mockPlusDescription(view);
                return;
            case 2:
                EditorialMarquee.mockPlusDescriptionPlusKicker(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 2;
    }
}
