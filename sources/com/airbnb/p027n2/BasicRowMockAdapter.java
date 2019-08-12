package com.airbnb.p027n2;

import com.airbnb.p027n2.components.BasicRow;

/* renamed from: com.airbnb.n2.BasicRowMockAdapter */
public final class BasicRowMockAdapter implements DLSMockAdapter<BasicRow> {
    public int getItemCount() {
        return 5;
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
            default:
                return null;
        }
    }

    public void bindView(BasicRow view, int position) {
        switch (position) {
            case 0:
                BasicRow.mockTitle(view);
                return;
            case 1:
                BasicRow.mockLongTitle(view);
                return;
            case 2:
                BasicRow.mockWithSubtitle(view);
                return;
            case 3:
                BasicRow.mockWithRichSubtitle(view);
                return;
            case 4:
                BasicRow.mockWithLongSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 2;
    }
}
