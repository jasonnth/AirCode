package com.airbnb.android.booking.utils;

import com.airbnb.android.core.enums.FragmentTransitionType;

public class BookingUtil {
    public static FragmentTransitionType getTransitionType(boolean backButtonPressed) {
        return backButtonPressed ? FragmentTransitionType.SlideInFromSidePop : FragmentTransitionType.SlideInFromSide;
    }
}
