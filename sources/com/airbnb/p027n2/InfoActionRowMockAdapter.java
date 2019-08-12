package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InfoActionRow;

/* renamed from: com.airbnb.n2.InfoActionRowMockAdapter */
public final class InfoActionRowMockAdapter implements DLSMockAdapter<InfoActionRow> {
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
                return "Long Subtitle";
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

    public void bindView(InfoActionRow view, int position) {
        switch (position) {
            case 0:
                InfoActionRow.mock(view);
                return;
            case 1:
                InfoActionRow.mockLongTitle(view);
                return;
            case 2:
                InfoActionRow.mockLongInfo(view);
                return;
            case 3:
                InfoActionRow.mockWithSubTitle(view);
                return;
            case 4:
                InfoActionRow.mockWithRichSubTitle(view);
                return;
            case 5:
                InfoActionRow.mockWithLongSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
