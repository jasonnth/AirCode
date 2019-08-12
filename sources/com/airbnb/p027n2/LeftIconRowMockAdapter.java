package com.airbnb.p027n2;

import com.airbnb.p027n2.components.LeftIconRow;

/* renamed from: com.airbnb.n2.LeftIconRowMockAdapter */
public final class LeftIconRowMockAdapter implements DLSMockAdapter<LeftIconRow> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
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
            default:
                return null;
        }
    }

    public void bindView(LeftIconRow view, int position) {
        switch (position) {
            case 0:
                LeftIconRow.mockDefault(view);
                return;
            case 1:
                LeftIconRow.mockNoSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
