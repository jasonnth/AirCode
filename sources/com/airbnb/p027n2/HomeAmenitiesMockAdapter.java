package com.airbnb.p027n2;

import com.airbnb.p027n2.components.HomeAmenities;

/* renamed from: com.airbnb.n2.HomeAmenitiesMockAdapter */
public final class HomeAmenitiesMockAdapter implements DLSMockAdapter<HomeAmenities> {
    public int getItemCount() {
        return 6;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "";
            case 4:
                return "";
            case 5:
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

    public void bindView(HomeAmenities view, int position) {
        switch (position) {
            case 0:
                HomeAmenities.mock(view);
                return;
            case 1:
                HomeAmenities.mockTwo(view);
                return;
            case 2:
                HomeAmenities.mockFive(view);
                return;
            case 3:
                HomeAmenities.mockTen(view);
                return;
            case 4:
                HomeAmenities.mockHundred(view);
                return;
            case 5:
                HomeAmenities.mockThousand(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
