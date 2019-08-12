package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InviteRow;

/* renamed from: com.airbnb.n2.InviteRowMockAdapter */
public final class InviteRowMockAdapter implements DLSMockAdapter<InviteRow> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 1:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(InviteRow view, int position) {
        switch (position) {
            case 0:
                InviteRow.mockWithTitle(view);
                return;
            case 1:
                InviteRow.mockWithNoTitleAndNoImage(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
