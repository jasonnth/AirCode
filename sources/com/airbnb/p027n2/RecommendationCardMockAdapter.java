package com.airbnb.p027n2;

import com.airbnb.p027n2.components.RecommendationCard;

/* renamed from: com.airbnb.n2.RecommendationCardMockAdapter */
public final class RecommendationCardMockAdapter implements DLSMockAdapter<RecommendationCard> {
    public int getItemCount() {
        return 4;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Medium";
            case 1:
                return "";
            case 2:
                return "Small";
            case 3:
                return "Large";
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

    public void bindView(RecommendationCard view, int position) {
        switch (position) {
            case 0:
                RecommendationCard.mockMedium(view);
                return;
            case 1:
                RecommendationCard.mockMediumLongSubtext(view);
                return;
            case 2:
                RecommendationCard.mockSmall(view);
                return;
            case 3:
                RecommendationCard.mockLarge(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
