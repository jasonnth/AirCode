package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ImagePreviewRow;

/* renamed from: com.airbnb.n2.ImagePreviewRowMockAdapter */
public final class ImagePreviewRowMockAdapter implements DLSMockAdapter<ImagePreviewRow> {
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

    public void bindView(ImagePreviewRow view, int position) {
        switch (position) {
            case 0:
                ImagePreviewRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
