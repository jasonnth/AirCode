package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SheetInputText;

/* renamed from: com.airbnb.n2.SheetInputTextMockAdapter */
public final class SheetInputTextMockAdapter implements DLSMockAdapter<SheetInputText> {
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

    public void bindView(SheetInputText view, int position) {
        switch (position) {
            case 0:
                SheetInputText.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
