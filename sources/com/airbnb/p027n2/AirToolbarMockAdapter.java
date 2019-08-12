package com.airbnb.p027n2;

import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.n2.AirToolbarMockAdapter */
public final class AirToolbarMockAdapter implements DLSMockAdapter<AirToolbar> {
    public int getItemCount() {
        return 6;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "With up arrow";
            case 1:
                return "With X";
            case 2:
                return "With a menu";
            case 3:
                return "With a subtitle";
            case 4:
                return "No title";
            case 5:
                return "With caret";
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
            case 5:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(AirToolbar view, int position) {
        switch (position) {
            case 0:
                AirToolbar.mockBack(view);
                return;
            case 1:
                AirToolbar.mockX(view);
                return;
            case 2:
                AirToolbar.mockMenu(view);
                return;
            case 3:
                AirToolbar.mockSubtitle(view);
                return;
            case 4:
                AirToolbar.mockNoTitle(view);
                return;
            case 5:
                AirToolbar.mockCaret(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
