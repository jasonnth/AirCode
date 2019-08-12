package com.airbnb.p027n2;

import com.airbnb.p027n2.components.BarRow;

/* renamed from: com.airbnb.n2.BarRowMockAdapter */
public final class BarRowMockAdapter implements DLSMockAdapter<BarRow> {
    public int getItemCount() {
        return 6;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "BarRow [Above Standard]";
            case 1:
                return "BarRow [Standard]";
            case 2:
                return "BarRow [Below Standard]";
            case 3:
                return "BarRow + Subtitle [Above Standard]";
            case 4:
                return "Bar Row + Subtitle [Standard]";
            case 5:
                return "Bar Row + Subtitle [Below Standard]";
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
            default:
                return null;
        }
    }

    public void bindView(BarRow view, int position) {
        switch (position) {
            case 0:
                BarRow.mockAboveStandard(view);
                return;
            case 1:
                BarRow.mockStandard(view);
                return;
            case 2:
                BarRow.mockBelowStandard(view);
                return;
            case 3:
                BarRow.mockSubtitleAboveStandard(view);
                return;
            case 4:
                BarRow.mockSubtitleStandard(view);
                return;
            case 5:
                BarRow.mockSubtitleBelowStandard(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
