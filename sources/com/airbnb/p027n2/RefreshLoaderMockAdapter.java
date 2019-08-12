package com.airbnb.p027n2;

import com.airbnb.p027n2.components.RefreshLoader;

/* renamed from: com.airbnb.n2.RefreshLoaderMockAdapter */
public final class RefreshLoaderMockAdapter implements DLSMockAdapter<RefreshLoader> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "For carousels";
            case 2:
                return "Inverse";
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
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(RefreshLoader view, int position) {
        switch (position) {
            case 0:
                RefreshLoader.mock(view);
                return;
            case 1:
                RefreshLoader.mockCarousel(view);
                return;
            case 2:
                RefreshLoader.mockInverse(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
