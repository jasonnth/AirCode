package com.airbnb.p027n2;

import com.airbnb.p027n2.components.NumberedSimpleTextRow;

/* renamed from: com.airbnb.n2.NumberedSimpleTextRowMockAdapter */
public final class NumberedSimpleTextRowMockAdapter implements DLSMockAdapter<NumberedSimpleTextRow> {
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

    public void bindView(NumberedSimpleTextRow view, int position) {
        switch (position) {
            case 0:
                NumberedSimpleTextRow.mock(view);
                return;
            case 1:
                NumberedSimpleTextRow.mockWithLongText(view);
                return;
            case 2:
                NumberedSimpleTextRow.mockWithNoPadding(view);
                return;
            case 3:
                NumberedSimpleTextRow.mockWithColoredStep(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
