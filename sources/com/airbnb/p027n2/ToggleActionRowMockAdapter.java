package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ToggleActionRow;

/* renamed from: com.airbnb.n2.ToggleActionRowMockAdapter */
public final class ToggleActionRowMockAdapter implements DLSMockAdapter<ToggleActionRow> {
    public int getItemCount() {
        return 4;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "Radio";
            case 3:
                return "Read Only";
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
            default:
                return null;
        }
    }

    public void bindView(ToggleActionRow view, int position) {
        switch (position) {
            case 0:
                ToggleActionRow.mock(view);
                return;
            case 1:
                ToggleActionRow.mockSubtitleChecked(view);
                return;
            case 2:
                ToggleActionRow.mockRadio(view);
                return;
            case 3:
                ToggleActionRow.mockReadOnly(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 1;
    }
}
