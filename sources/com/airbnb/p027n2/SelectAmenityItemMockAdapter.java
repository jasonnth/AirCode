package com.airbnb.p027n2;

import com.airbnb.p027n2.components.decide.select.SelectAmenityItem;

/* renamed from: com.airbnb.n2.SelectAmenityItemMockAdapter */
public final class SelectAmenityItemMockAdapter implements DLSMockAdapter<SelectAmenityItem> {
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

    public void bindView(SelectAmenityItem view, int position) {
        switch (position) {
            case 0:
                SelectAmenityItem.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
