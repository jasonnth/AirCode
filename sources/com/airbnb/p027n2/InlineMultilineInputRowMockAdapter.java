package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InlineMultilineInputRow;

/* renamed from: com.airbnb.n2.InlineMultilineInputRowMockAdapter */
public final class InlineMultilineInputRowMockAdapter implements DLSMockAdapter<InlineMultilineInputRow> {
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

    public void bindView(InlineMultilineInputRow view, int position) {
        switch (position) {
            case 0:
                InlineMultilineInputRow.mock(view);
                return;
            case 1:
                InlineMultilineInputRow.mockInputtedText(view);
                return;
            case 2:
                InlineMultilineInputRow.mockInputtedTextWraps(view);
                return;
            case 3:
                InlineMultilineInputRow.mockError(view);
                return;
            case 4:
                InlineMultilineInputRow.mockSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
