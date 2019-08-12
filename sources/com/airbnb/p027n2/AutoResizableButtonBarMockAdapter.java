package com.airbnb.p027n2;

import com.airbnb.p027n2.components.AutoResizableButtonBar;

/* renamed from: com.airbnb.n2.AutoResizableButtonBarMockAdapter */
public final class AutoResizableButtonBarMockAdapter implements DLSMockAdapter<AutoResizableButtonBar> {
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

    public void bindView(AutoResizableButtonBar view, int position) {
        switch (position) {
            case 0:
                AutoResizableButtonBar.mock(view);
                return;
            case 1:
                AutoResizableButtonBar.mockLoadingView(view);
                return;
            case 2:
                AutoResizableButtonBar.mockLongText(view);
                return;
            case 3:
                AutoResizableButtonBar.mockLoadingViewLong(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
