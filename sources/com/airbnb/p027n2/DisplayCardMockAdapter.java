package com.airbnb.p027n2;

import com.airbnb.p027n2.components.DisplayCard;

/* renamed from: com.airbnb.n2.DisplayCardMockAdapter */
public final class DisplayCardMockAdapter implements DLSMockAdapter<DisplayCard> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "DisplayCard";
            case 1:
                return "DisplayCard + Title";
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
            default:
                return null;
        }
    }

    public void bindView(DisplayCard view, int position) {
        switch (position) {
            case 0:
                DisplayCard.mock(view);
                return;
            case 1:
                DisplayCard.mockWithText(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 1;
    }
}
