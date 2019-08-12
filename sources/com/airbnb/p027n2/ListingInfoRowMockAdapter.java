package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ListingInfoRow;

/* renamed from: com.airbnb.n2.ListingInfoRowMockAdapter */
public final class ListingInfoRowMockAdapter implements DLSMockAdapter<ListingInfoRow> {
    public int getItemCount() {
        return 4;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Default Babu Accent";
            case 1:
                return "Hackberry Accent";
            case 2:
                return "Basic Row with Label";
            case 3:
                return "Disabled";
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
            case 3:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(ListingInfoRow view, int position) {
        switch (position) {
            case 0:
                ListingInfoRow.mock(view);
                return;
            case 1:
                ListingInfoRow.mockHackBerry(view);
                return;
            case 2:
                ListingInfoRow.mockLabel(view);
                return;
            case 3:
                ListingInfoRow.mockDisabled(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
