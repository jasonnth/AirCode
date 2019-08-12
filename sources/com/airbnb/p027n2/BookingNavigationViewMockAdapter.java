package com.airbnb.p027n2;

import com.airbnb.p027n2.components.BookingNavigationView;

/* renamed from: com.airbnb.n2.BookingNavigationViewMockAdapter */
public final class BookingNavigationViewMockAdapter implements DLSMockAdapter<BookingNavigationView> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
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
            default:
                return null;
        }
    }

    public void bindView(BookingNavigationView view, int position) {
        switch (position) {
            case 0:
                BookingNavigationView.mock(view);
                return;
            case 1:
                BookingNavigationView.mockLoading(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
