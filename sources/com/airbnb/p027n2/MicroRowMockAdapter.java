package com.airbnb.p027n2;

import com.airbnb.p027n2.components.MicroRow;

/* renamed from: com.airbnb.n2.MicroRowMockAdapter */
public final class MicroRowMockAdapter implements DLSMockAdapter<MicroRow> {
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

    public void bindView(MicroRow view, int position) {
        switch (position) {
            case 0:
                MicroRow.mock(view);
                return;
            case 1:
                MicroRow.mockWrap(view);
                return;
            case 2:
                MicroRow.mockRichText(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
