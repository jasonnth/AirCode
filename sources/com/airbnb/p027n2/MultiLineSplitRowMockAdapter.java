package com.airbnb.p027n2;

import com.airbnb.p027n2.components.MultiLineSplitRow;

/* renamed from: com.airbnb.n2.MultiLineSplitRowMockAdapter */
public final class MultiLineSplitRowMockAdapter implements DLSMockAdapter<MultiLineSplitRow> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "default";
            case 1:
                return "no top padding";
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
            default:
                return null;
        }
    }

    public void bindView(MultiLineSplitRow view, int position) {
        switch (position) {
            case 0:
                MultiLineSplitRow.mock(view);
                return;
            case 1:
                MultiLineSplitRow.mockNoTopPadding(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
