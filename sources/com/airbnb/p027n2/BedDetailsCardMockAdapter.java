package com.airbnb.p027n2;

import com.airbnb.p027n2.components.BedDetailsCard;

/* renamed from: com.airbnb.n2.BedDetailsCardMockAdapter */
public final class BedDetailsCardMockAdapter implements DLSMockAdapter<BedDetailsCard> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "BedDetailsCard";
            case 1:
                return "BedDetailsCard + Short";
            case 2:
                return "BedDetailsCard + Long text";
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

    public void bindView(BedDetailsCard view, int position) {
        switch (position) {
            case 0:
                BedDetailsCard.mock(view);
                return;
            case 1:
                BedDetailsCard.mockWithShortText(view);
                return;
            case 2:
                BedDetailsCard.mockWithText(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 2;
    }
}
