package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SmallTextRow;

/* renamed from: com.airbnb.n2.SmallTextRowMockAdapter */
public final class SmallTextRowMockAdapter implements DLSMockAdapter<SmallTextRow> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "No max lines";
            case 2:
                return "With rich text";
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

    public void bindView(SmallTextRow view, int position) {
        switch (position) {
            case 0:
                SmallTextRow.mockCollapsed(view);
                return;
            case 1:
                SmallTextRow.mockExpanded(view);
                return;
            case 2:
                SmallTextRow.mockRichText(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
