package com.airbnb.p027n2;

import com.airbnb.p027n2.components.HomeReviewRow;

/* renamed from: com.airbnb.n2.HomeReviewRowMockAdapter */
public final class HomeReviewRowMockAdapter implements DLSMockAdapter<HomeReviewRow> {
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

    public void bindView(HomeReviewRow view, int position) {
        switch (position) {
            case 0:
                HomeReviewRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
