package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InlineInputRow;

/* renamed from: com.airbnb.n2.InlineInputRowMockAdapter */
public final class InlineInputRowMockAdapter implements DLSMockAdapter<InlineInputRow> {
    public int getItemCount() {
        return 5;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "With inputted text";
            case 2:
                return "With inputted text that wraps";
            case 3:
                return "Error";
            case 4:
                return "Subtitle";
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
            case 4:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(InlineInputRow view, int position) {
        switch (position) {
            case 0:
                InlineInputRow.mock(view);
                return;
            case 1:
                InlineInputRow.mockInputtedText(view);
                return;
            case 2:
                InlineInputRow.mockInputtedTextWraps(view);
                return;
            case 3:
                InlineInputRow.mockError(view);
                return;
            case 4:
                InlineInputRow.mockSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
