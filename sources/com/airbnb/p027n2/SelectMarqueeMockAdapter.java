package com.airbnb.p027n2;

import com.airbnb.p027n2.components.decide.select.SelectMarquee;

/* renamed from: com.airbnb.n2.SelectMarqueeMockAdapter */
public final class SelectMarqueeMockAdapter implements DLSMockAdapter<SelectMarquee> {
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

    public void bindView(SelectMarquee view, int position) {
        switch (position) {
            case 0:
                SelectMarquee.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
