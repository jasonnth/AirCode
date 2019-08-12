package com.airbnb.p027n2;

import com.airbnb.p027n2.components.decide.select.SelectBulletList;

/* renamed from: com.airbnb.n2.SelectBulletListMockAdapter */
public final class SelectBulletListMockAdapter implements DLSMockAdapter<SelectBulletList> {
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

    public void bindView(SelectBulletList view, int position) {
        switch (position) {
            case 0:
                SelectBulletList.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
