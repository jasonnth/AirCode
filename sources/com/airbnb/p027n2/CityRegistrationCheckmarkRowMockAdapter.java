package com.airbnb.p027n2;

import com.airbnb.p027n2.components.CityRegistrationCheckmarkRow;

/* renamed from: com.airbnb.n2.CityRegistrationCheckmarkRowMockAdapter */
public final class CityRegistrationCheckmarkRowMockAdapter implements DLSMockAdapter<CityRegistrationCheckmarkRow> {
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

    public void bindView(CityRegistrationCheckmarkRow view, int position) {
        switch (position) {
            case 0:
                CityRegistrationCheckmarkRow.mock(view);
                return;
            case 1:
                CityRegistrationCheckmarkRow.mockWithRichSubtitle(view);
                return;
            case 2:
                CityRegistrationCheckmarkRow.mockWithLongTitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
