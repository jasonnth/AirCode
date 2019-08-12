package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InlineInputWithContactPickerRow;

/* renamed from: com.airbnb.n2.InlineInputWithContactPickerRowMockAdapter */
public final class InlineInputWithContactPickerRowMockAdapter implements DLSMockAdapter<InlineInputWithContactPickerRow> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "InlineInputWithContactPickerRow";
            case 1:
                return "InlineInputWithContactPickerRow + Inputted Text";
            case 2:
                return "InlineInputWithContactPickerRow + Inputted Text [Wraps]";
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

    public void bindView(InlineInputWithContactPickerRow view, int position) {
        switch (position) {
            case 0:
                InlineInputWithContactPickerRow.mock(view);
                return;
            case 1:
                InlineInputWithContactPickerRow.mockPlusInputtedText(view);
                return;
            case 2:
                InlineInputWithContactPickerRow.mockPlusInputtedTextWraps(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
