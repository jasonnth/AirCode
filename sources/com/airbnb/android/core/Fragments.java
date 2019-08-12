package com.airbnb.android.core;

import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.fragments.ZenDialog;

public final class Fragments extends ClassRegistry {
    private Fragments() {
    }

    private static Fragment maybeLoadFragmentClass(String className) {
        try {
            return (Fragment) maybeLoadClass(className).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Fragment Class is not public or is missing public no-args constructor: " + className, e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Fragment Class is not public or is missing public no-args constructor: " + className, e2);
        }
    }

    public static Fragment reactNative() {
        return maybeLoadFragmentClass("com.airbnb.android.react.ReactNativeFragment");
    }

    public static Fragment konaReservationMessageThread() {
        return maybeLoadFragmentClass("com.airbnb.android.lib.fragments.ThreadFragment");
    }

    public static ZenDialog shakeFeedback() {
        return (ZenDialog) maybeLoadFragmentClass("com.airbnb.android.lib.fragments.ShakeFeedbackDialog");
    }

    public static Fragment hostCalendar() {
        return maybeLoadFragmentClass("com.airbnb.android.hostcalendar.fragments.CalendarFragment");
    }

    public static Fragment editPrice() {
        return maybeLoadFragmentClass("com.airbnb.android.lib.fragments.managelisting.EditPriceFragment");
    }

    public static Fragment communityCommitment() {
        return maybeLoadFragmentClass("com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentFragment");
    }

    public static Fragment whatsMyPlaceWorthFragment() {
        return maybeLoadFragmentClass("com.airbnb.android.listing.fragments.WhatsMyPlaceWorthFragment");
    }
}
