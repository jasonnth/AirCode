package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ListingInfoCardRow;

/* renamed from: com.airbnb.n2.ListingInfoCardRowMockAdapter */
public final class ListingInfoCardRowMockAdapter implements DLSMockAdapter<ListingInfoCardRow> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Default";
            case 1:
                return "Default with image";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 1:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(ListingInfoCardRow view, int position) {
        switch (position) {
            case 0:
                ListingInfoCardRow.mock(view);
                return;
            case 1:
                ListingInfoCardRow.mockImage(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
