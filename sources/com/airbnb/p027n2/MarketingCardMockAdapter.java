package com.airbnb.p027n2;

import com.airbnb.p027n2.components.MarketingCard;

/* renamed from: com.airbnb.n2.MarketingCardMockAdapter */
public final class MarketingCardMockAdapter implements DLSMockAdapter<MarketingCard> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Short Title";
            case 1:
                return "Long Title";
            case 2:
                return "Long CTA";
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

    public void bindView(MarketingCard view, int position) {
        switch (position) {
            case 0:
                MarketingCard.mock(view);
                return;
            case 1:
                MarketingCard.mockWithLongTitle(view);
                return;
            case 2:
                MarketingCard.mockWithLongCTA(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
