package com.airbnb.p027n2;

import com.airbnb.p027n2.components.decide.select.SelectIconBulletRow;

/* renamed from: com.airbnb.n2.SelectIconBulletRowMockAdapter */
public final class SelectIconBulletRowMockAdapter implements DLSMockAdapter<SelectIconBulletRow> {
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

    public void bindView(SelectIconBulletRow view, int position) {
        switch (position) {
            case 0:
                SelectIconBulletRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
