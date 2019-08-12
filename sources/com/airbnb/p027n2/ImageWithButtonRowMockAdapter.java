package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ImageWithButtonRow;

/* renamed from: com.airbnb.n2.ImageWithButtonRowMockAdapter */
public final class ImageWithButtonRowMockAdapter implements DLSMockAdapter<ImageWithButtonRow> {
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

    public void bindView(ImageWithButtonRow view, int position) {
        switch (position) {
            case 0:
                ImageWithButtonRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
