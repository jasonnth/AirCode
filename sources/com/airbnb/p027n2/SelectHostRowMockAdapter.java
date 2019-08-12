package com.airbnb.p027n2;

import com.airbnb.p027n2.components.decide.select.SelectHostRow;

/* renamed from: com.airbnb.n2.SelectHostRowMockAdapter */
public final class SelectHostRowMockAdapter implements DLSMockAdapter<SelectHostRow> {
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

    public void bindView(SelectHostRow view, int position) {
        switch (position) {
            case 0:
                SelectHostRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
