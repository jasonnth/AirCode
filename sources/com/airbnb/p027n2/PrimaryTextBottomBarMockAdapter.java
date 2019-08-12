package com.airbnb.p027n2;

import com.airbnb.p027n2.components.PrimaryTextBottomBar;

/* renamed from: com.airbnb.n2.PrimaryTextBottomBarMockAdapter */
public final class PrimaryTextBottomBarMockAdapter implements DLSMockAdapter<PrimaryTextBottomBar> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Style Babu";
            case 1:
                return "Style Babu Disabled";
            case 2:
                return "Style Transparent";
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
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(PrimaryTextBottomBar view, int position) {
        switch (position) {
            case 0:
                PrimaryTextBottomBar.mockBabu(view);
                return;
            case 1:
                PrimaryTextBottomBar.mockBabuDisabled(view);
                return;
            case 2:
                PrimaryTextBottomBar.mockTransparent(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
