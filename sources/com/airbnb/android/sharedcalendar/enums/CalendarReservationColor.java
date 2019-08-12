package com.airbnb.android.sharedcalendar.enums;

import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.sharedcalendar.C1576R;

public enum CalendarReservationColor {
    Grey(C1576R.color.n2_background_gray, C1576R.color.n2_hof_40, C1576R.color.n2_hof_40, C1576R.color.n2_babu_50, 0.5f),
    Arches(C1576R.color.n2_arches, C1576R.color.white, C1576R.color.n2_white_60, C1576R.color.white, 1.0f),
    Foggy(C1576R.color.n2_foggy, C1576R.color.white, C1576R.color.n2_white_60, C1576R.color.white, 1.0f),
    Babu(C1576R.color.n2_babu, C1576R.color.white, C1576R.color.n2_white_60, C1576R.color.n2_babu_dark, 1.0f);
    
    private final int actionTextColor;
    private final int detailTextColor;
    private final int fillColor;
    private final float guestPhotoAlpha;
    private final int nameTextColor;

    private CalendarReservationColor(int fillColor2, int nameTextColor2, int detailTextColor2, int actionTextColor2, float guestPhotoAlpha2) {
        this.fillColor = fillColor2;
        this.nameTextColor = nameTextColor2;
        this.detailTextColor = detailTextColor2;
        this.actionTextColor = actionTextColor2;
        this.guestPhotoAlpha = guestPhotoAlpha2;
    }

    public int getFillColor() {
        return this.fillColor;
    }

    public int getNameTextColor() {
        return this.nameTextColor;
    }

    public int getDetailTextColor() {
        return this.detailTextColor;
    }

    public int getActionTextColor() {
        return this.actionTextColor;
    }

    public float getGuestPhotoAlpha() {
        return this.guestPhotoAlpha;
    }

    public static CalendarReservationColor fromReservation(Reservation reservation) {
        if (reservation.hasEnded()) {
            return Grey;
        }
        switch (reservation.getReservationStatus()) {
            case Cancelled:
            case Denied:
            case Timedout:
            case NotPossible:
                return Foggy;
            case Pending:
                return Arches;
            default:
                return Babu;
        }
    }
}
