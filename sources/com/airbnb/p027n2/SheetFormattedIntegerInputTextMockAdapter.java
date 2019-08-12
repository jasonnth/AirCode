package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SheetFormattedIntegerInputText;

/* renamed from: com.airbnb.n2.SheetFormattedIntegerInputTextMockAdapter */
public final class SheetFormattedIntegerInputTextMockAdapter implements DLSMockAdapter<SheetFormattedIntegerInputText> {
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

    public void bindView(SheetFormattedIntegerInputText view, int position) {
        switch (position) {
            case 0:
                SheetFormattedIntegerInputText.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
