package com.airbnb.p027n2;

import com.airbnb.p027n2.components.LabeledSectionRow;

/* renamed from: com.airbnb.n2.LabeledSectionRowMockAdapter */
public final class LabeledSectionRowMockAdapter implements DLSMockAdapter<LabeledSectionRow> {
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

    public void bindView(LabeledSectionRow view, int position) {
        switch (position) {
            case 0:
                LabeledSectionRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
