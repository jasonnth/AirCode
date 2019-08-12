package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SimpleTitleContentRow;

/* renamed from: com.airbnb.n2.SimpleTitleContentRowMockAdapter */
public final class SimpleTitleContentRowMockAdapter implements DLSMockAdapter<SimpleTitleContentRow> {
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

    public void bindView(SimpleTitleContentRow view, int position) {
        switch (position) {
            case 0:
                SimpleTitleContentRow.mockTitle(view);
                return;
            case 1:
                SimpleTitleContentRow.mockLongTitle(view);
                return;
            case 2:
                SimpleTitleContentRow.mockWithSubtitle(view);
                return;
            case 3:
                SimpleTitleContentRow.mockWithRichSubtitle(view);
                return;
            case 4:
                SimpleTitleContentRow.mockWithLongSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 2;
    }
}
