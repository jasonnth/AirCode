package com.airbnb.p027n2;

import com.airbnb.p027n2.components.NestedListingEditRow;

/* renamed from: com.airbnb.n2.NestedListingEditRowMockAdapter */
public final class NestedListingEditRowMockAdapter implements DLSMockAdapter<NestedListingEditRow> {
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

    public void bindView(NestedListingEditRow view, int position) {
        switch (position) {
            case 0:
                NestedListingEditRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
