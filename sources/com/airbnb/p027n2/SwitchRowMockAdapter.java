package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SwitchRow;

/* renamed from: com.airbnb.n2.SwitchRowMockAdapter */
public final class SwitchRowMockAdapter implements DLSMockAdapter<SwitchRow> {
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
                return "Outline Style (Deprecated)";
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
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 3:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 4:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(SwitchRow view, int position) {
        switch (position) {
            case 0:
                SwitchRow.mock(view);
                return;
            case 1:
                SwitchRow.mockPlus(view);
                return;
            case 2:
                SwitchRow.mockSheet(view);
                return;
            case 3:
                SwitchRow.mockPlusSheet(view);
                return;
            case 4:
                SwitchRow.mockPlusOutline(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 1;
    }
}
