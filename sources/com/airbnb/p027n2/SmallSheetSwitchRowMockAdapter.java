package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SmallSheetSwitchRow;

/* renamed from: com.airbnb.n2.SmallSheetSwitchRowMockAdapter */
public final class SmallSheetSwitchRowMockAdapter implements DLSMockAdapter<SmallSheetSwitchRow> {
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

    public void bindView(SmallSheetSwitchRow view, int position) {
        switch (position) {
            case 0:
                SmallSheetSwitchRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
