package com.airbnb.p027n2;

import com.airbnb.p027n2.components.CheckInGuideAddStepButton;

/* renamed from: com.airbnb.n2.CheckInGuideAddStepButtonMockAdapter */
public final class CheckInGuideAddStepButtonMockAdapter implements DLSMockAdapter<CheckInGuideAddStepButton> {
    public int getItemCount() {
        return 1;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "CheckInGuideAddStepCard";
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

    public void bindView(CheckInGuideAddStepButton view, int position) {
        switch (position) {
            case 0:
                CheckInGuideAddStepButton.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
