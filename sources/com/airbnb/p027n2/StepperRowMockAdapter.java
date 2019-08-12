package com.airbnb.p027n2;

import com.airbnb.p027n2.components.StepperRow;

/* renamed from: com.airbnb.n2.StepperRowMockAdapter */
public final class StepperRowMockAdapter implements DLSMockAdapter<StepperRow> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "with description";
            case 2:
                return "useOldDesign";
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

    public void bindView(StepperRow view, int position) {
        switch (position) {
            case 0:
                StepperRow.mock(view);
                return;
            case 1:
                StepperRow.mockDescription(view);
                return;
            case 2:
                StepperRow.mockOld(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
