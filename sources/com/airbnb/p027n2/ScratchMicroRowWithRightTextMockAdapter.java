package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ScratchMicroRowWithRightText;

/* renamed from: com.airbnb.n2.ScratchMicroRowWithRightTextMockAdapter */
public final class ScratchMicroRowWithRightTextMockAdapter implements DLSMockAdapter<ScratchMicroRowWithRightText> {
    public int getItemCount() {
        return 4;
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

    public void bindView(ScratchMicroRowWithRightText view, int position) {
        switch (position) {
            case 0:
                ScratchMicroRowWithRightText.mockLong(view);
                return;
            case 1:
                ScratchMicroRowWithRightText.mockLongAndShort(view);
                return;
            case 2:
                ScratchMicroRowWithRightText.mockShortAndLong(view);
                return;
            case 3:
                ScratchMicroRowWithRightText.mockShort(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
