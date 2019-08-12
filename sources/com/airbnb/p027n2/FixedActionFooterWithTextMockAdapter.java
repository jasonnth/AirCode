package com.airbnb.p027n2;

import com.airbnb.p027n2.components.FixedActionFooterWithText;

/* renamed from: com.airbnb.n2.FixedActionFooterWithTextMockAdapter */
public final class FixedActionFooterWithTextMockAdapter implements DLSMockAdapter<FixedActionFooterWithText> {
    public int getItemCount() {
        return 4;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Waiting State";
            case 1:
                return "Normal State";
            case 2:
                return "Long text";
            case 3:
                return "Missing text";
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
            case 3:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(FixedActionFooterWithText view, int position) {
        switch (position) {
            case 0:
                FixedActionFooterWithText.mockWaiting(view);
                return;
            case 1:
                FixedActionFooterWithText.mock(view);
                return;
            case 2:
                FixedActionFooterWithText.mockLongButtonText(view);
                return;
            case 3:
                FixedActionFooterWithText.mockMissingText(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 1;
    }
}
