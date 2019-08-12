package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ReviewBulletRow;

/* renamed from: com.airbnb.n2.ReviewBulletRowMockAdapter */
public final class ReviewBulletRowMockAdapter implements DLSMockAdapter<ReviewBulletRow> {
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

    public void bindView(ReviewBulletRow view, int position) {
        switch (position) {
            case 0:
                ReviewBulletRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
