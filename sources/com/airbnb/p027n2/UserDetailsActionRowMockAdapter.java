package com.airbnb.p027n2;

import com.airbnb.p027n2.components.UserDetailsActionRow;

/* renamed from: com.airbnb.n2.UserDetailsActionRowMockAdapter */
public final class UserDetailsActionRowMockAdapter implements DLSMockAdapter<UserDetailsActionRow> {
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

    public void bindView(UserDetailsActionRow view, int position) {
        switch (position) {
            case 0:
                UserDetailsActionRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
