package com.airbnb.p027n2;

import com.airbnb.p027n2.components.HomeCard;

/* renamed from: com.airbnb.n2.HomeCardMockAdapter */
public final class HomeCardMockAdapter implements DLSMockAdapter<HomeCard> {
    public int getItemCount() {
        return 12;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Singleline";
            case 1:
                return "2 lines";
            case 2:
                return "Instant Book + Superhost";
            case 3:
                return "Instant Book Only";
            case 4:
                return "Superhost Only";
            case 5:
                return "No Reviews + New Home";
            case 6:
                return "No Reviews";
            case 7:
                return "Mini Style for Carousels";
            case 8:
                return "Micro Style for Map Carousel";
            case 9:
                return "Listing Title Experiment";
            case 10:
                return "Listing Title Experiment, Mini Style";
            case 11:
                return "Listing Title Experiment, Micro Style";
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
            case 6:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 7:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 8:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 9:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 10:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 11:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(HomeCard view, int position) {
        switch (position) {
            case 0:
                HomeCard.mockDefault(view);
                return;
            case 1:
                HomeCard.mock2Lines(view);
                return;
            case 2:
                HomeCard.mockInstantBookSuperhost(view);
                return;
            case 3:
                HomeCard.mockInstantBook(view);
                return;
            case 4:
                HomeCard.mockSuperhost(view);
                return;
            case 5:
                HomeCard.mockNoReviewsNewHome(view);
                return;
            case 6:
                HomeCard.mockNoReviews(view);
                return;
            case 7:
                HomeCard.mockMini(view);
                return;
            case 8:
                HomeCard.mockMicro(view);
                return;
            case 9:
                HomeCard.mockListingTitleFull(view);
                return;
            case 10:
                HomeCard.mockListingTitleMini(view);
                return;
            case 11:
                HomeCard.mockListingTitleMicro(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
