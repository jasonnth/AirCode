package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InquiryCard;

/* renamed from: com.airbnb.n2.InquiryCardMockAdapter */
public final class InquiryCardMockAdapter implements DLSMockAdapter<InquiryCard> {
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

    public void bindView(InquiryCard view, int position) {
        switch (position) {
            case 0:
                InquiryCard.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
