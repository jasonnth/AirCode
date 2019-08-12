package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SheetStepperRow;

/* renamed from: com.airbnb.n2.SheetStepperRowMockAdapter */
public final class SheetStepperRowMockAdapter implements DLSMockAdapter<SheetStepperRow> {
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

    public void bindView(SheetStepperRow view, int position) {
        switch (position) {
            case 0:
                SheetStepperRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}