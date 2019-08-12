package com.airbnb.p027n2;

import com.airbnb.p027n2.components.HaloImageTextRow;

/* renamed from: com.airbnb.n2.HaloImageTextRowMockAdapter */
public final class HaloImageTextRowMockAdapter implements DLSMockAdapter<HaloImageTextRow> {
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
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(HaloImageTextRow view, int position) {
        switch (position) {
            case 0:
                HaloImageTextRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
