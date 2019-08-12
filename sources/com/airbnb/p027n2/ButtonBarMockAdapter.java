package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ButtonBar;

/* renamed from: com.airbnb.n2.ButtonBarMockAdapter */
public final class ButtonBarMockAdapter implements DLSMockAdapter<ButtonBar> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "ButtonBar » 2";
            case 1:
                return "ButtonBar » 3";
            case 2:
                return "ButtonBar » 4";
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

    public void bindView(ButtonBar view, int position) {
        switch (position) {
            case 0:
                ButtonBar.mock2(view);
                return;
            case 1:
                ButtonBar.mock3(view);
                return;
            case 2:
                ButtonBar.mock4(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 2;
    }
}
