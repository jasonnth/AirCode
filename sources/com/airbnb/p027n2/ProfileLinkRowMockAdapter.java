package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ProfileLinkRow;

/* renamed from: com.airbnb.n2.ProfileLinkRowMockAdapter */
public final class ProfileLinkRowMockAdapter implements DLSMockAdapter<ProfileLinkRow> {
    public int getItemCount() {
        return 1;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(ProfileLinkRow view, int position) {
        switch (position) {
            case 0:
                ProfileLinkRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
