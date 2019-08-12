package com.airbnb.p027n2;

import com.airbnb.p027n2.components.LeftAlignedImageRow;

/* renamed from: com.airbnb.n2.LeftAlignedImageRowMockAdapter */
public final class LeftAlignedImageRowMockAdapter implements DLSMockAdapter<LeftAlignedImageRow> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
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
            default:
                return null;
        }
    }

    public void bindView(LeftAlignedImageRow view, int position) {
        switch (position) {
            case 0:
                LeftAlignedImageRow.mock(view);
                return;
            case 1:
                LeftAlignedImageRow.mockTitleOnly(view);
                return;
            case 2:
                LeftAlignedImageRow.mockLongTitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
