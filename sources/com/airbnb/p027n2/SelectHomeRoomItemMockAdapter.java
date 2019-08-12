package com.airbnb.p027n2;

import com.airbnb.p027n2.components.decide.select.SelectHomeRoomItem;

/* renamed from: com.airbnb.n2.SelectHomeRoomItemMockAdapter */
public final class SelectHomeRoomItemMockAdapter implements DLSMockAdapter<SelectHomeRoomItem> {
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

    public void bindView(SelectHomeRoomItem view, int position) {
        switch (position) {
            case 0:
                SelectHomeRoomItem.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
