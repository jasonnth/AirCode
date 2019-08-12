package com.airbnb.p027n2;

import com.airbnb.p027n2.components.HomeCardChina;

/* renamed from: com.airbnb.n2.HomeCardChinaMockAdapter */
public final class HomeCardChinaMockAdapter implements DLSMockAdapter<HomeCardChina> {
    public int getItemCount() {
        return 6;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Singleline";
            case 1:
                return "Instant Book + Superhost";
            case 2:
                return "Instant Book Only";
            case 3:
                return "Superhost Only";
            case 4:
                return "No Reviews + New Home";
            case 5:
                return "No Reviews";
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

    public void bindView(HomeCardChina view, int position) {
        switch (position) {
            case 0:
                HomeCardChina.mockDefault(view);
                return;
            case 1:
                HomeCardChina.mockInstantBookSuperhost(view);
                return;
            case 2:
                HomeCardChina.mockInstantBook(view);
                return;
            case 3:
                HomeCardChina.mockSuperhost(view);
                return;
            case 4:
                HomeCardChina.mockNoReviewsNewHome(view);
                return;
            case 5:
                HomeCardChina.mockNoReviews(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
