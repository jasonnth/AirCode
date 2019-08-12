package com.airbnb.p027n2;

import com.airbnb.p027n2.components.CityRegistrationIconActionRow;

/* renamed from: com.airbnb.n2.CityRegistrationIconActionRowMockAdapter */
public final class CityRegistrationIconActionRowMockAdapter implements DLSMockAdapter<CityRegistrationIconActionRow> {
    public int getItemCount() {
        return 8;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "";
            case 4:
                return "";
            case 5:
                return "";
            case 6:
                return "";
            case 7:
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
            case 3:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 4:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 5:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 6:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 7:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(CityRegistrationIconActionRow view, int position) {
        switch (position) {
            case 0:
                CityRegistrationIconActionRow.mock(view);
                return;
            case 1:
                CityRegistrationIconActionRow.mockWithNoSubtitle(view);
                return;
            case 2:
                CityRegistrationIconActionRow.mockWithLongSubtitle(view);
                return;
            case 3:
                CityRegistrationIconActionRow.mockWithLongTitle(view);
                return;
            case 4:
                CityRegistrationIconActionRow.mockWithRichSubtitle(view);
                return;
            case 5:
                CityRegistrationIconActionRow.mockWithLongSubtitleAndNoAction(view);
                return;
            case 6:
                CityRegistrationIconActionRow.mockWithNoIcon(view);
                return;
            case 7:
                CityRegistrationIconActionRow.mockWithNoActionTextOrIcon(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
