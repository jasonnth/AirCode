package com.airbnb.p027n2;

import com.airbnb.p027n2.components.IconRow;

/* renamed from: com.airbnb.n2.IconRowMockAdapter */
public final class IconRowMockAdapter implements DLSMockAdapter<IconRow> {
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
                return "Badged";
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

    public void bindView(IconRow view, int position) {
        switch (position) {
            case 0:
                IconRow.mockTitle(view);
                return;
            case 1:
                IconRow.mockLongTitle(view);
                return;
            case 2:
                IconRow.mockWithSubtitle(view);
                return;
            case 3:
                IconRow.mockBadged(view);
                return;
            case 4:
                IconRow.mockWithRichSubtitle(view);
                return;
            case 5:
                IconRow.mockWithLongSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 2;
    }
}
