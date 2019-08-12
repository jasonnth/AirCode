package com.airbnb.p027n2;

import com.airbnb.p027n2.components.AddToPlanButton;

/* renamed from: com.airbnb.n2.AddToPlanButtonMockAdapter */
public final class AddToPlanButtonMockAdapter implements DLSMockAdapter<AddToPlanButton> {
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
                return "";
            case 3:
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
            default:
                return null;
        }
    }

    public void bindView(AddToPlanButton view, int position) {
        switch (position) {
            case 0:
                AddToPlanButton.mock(view);
                return;
            case 1:
                AddToPlanButton.mock2(view);
                return;
            case 2:
                AddToPlanButton.mock3(view);
                return;
            case 3:
                AddToPlanButton.mock4(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
