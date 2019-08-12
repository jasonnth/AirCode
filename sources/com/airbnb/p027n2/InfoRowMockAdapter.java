package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InfoRow;

/* renamed from: com.airbnb.n2.InfoRowMockAdapter */
public final class InfoRowMockAdapter implements DLSMockAdapter<InfoRow> {
    public int getItemCount() {
        return 6;
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

    public void bindView(InfoRow view, int position) {
        switch (position) {
            case 0:
                InfoRow.mock(view);
                return;
            case 1:
                InfoRow.mockLongTitle(view);
                return;
            case 2:
                InfoRow.mockLongInfo(view);
                return;
            case 3:
                InfoRow.mockWithSubTitle(view);
                return;
            case 4:
                InfoRow.mockWithRichSubTitle(view);
                return;
            case 5:
                InfoRow.mockWithLongSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 3;
    }
}
