package com.airbnb.android.sharedcalendar.models;

import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateInterval;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.sharedcalendar.enums.CalendarReservationColor;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import java.util.ArrayList;
import java.util.List;

public class CalendarGridReservationModel {
    private final CalendarReservationColor color;
    private final int daysUntilReservationEnds;
    private final int daysUntilReservationStarts;
    private final String guestPhotoUrl;
    private final boolean hasEnded;
    private final boolean hideGuestProfilePhoto;
    private HaloImageView imageView;
    private AirTextView placeholderTextView;
    private final CharSequence profilePlaceholderText;

    public static List<CalendarGridReservationModel> getReservationModels(int positionStart, int positionEnd, CalendarDays calendarDays, AirDate positionStartDate) {
        List<CalendarGridReservationModel> reservationModels = new ArrayList<>();
        int i = -1;
        while (i < positionEnd - positionStart) {
            CalendarDay calendarDay = calendarDays.get(positionStartDate.plusDays(i));
            Reservation reservation = calendarDay != null ? calendarDay.getReservation() : null;
            if (reservation == null || reservation.isExpired()) {
                i++;
            } else {
                int daysUntilStart = positionStartDate.getDaysUntil(reservation.getStartDate());
                int daysUntilEnd = positionStartDate.getDaysUntil(reservation.getEndDate());
                if (daysUntilStart >= positionEnd - positionStart || daysUntilEnd < 0) {
                    i++;
                } else {
                    reservationModels.add(new CalendarGridReservationModel(daysUntilStart, daysUntilEnd, reservation));
                    i = daysUntilEnd;
                }
            }
        }
        return reservationModels;
    }

    public static CalendarGridReservationModel getCalendarGridRequestModel(int positionStart, int positionEnd, CalendarDays calendarDays, AirDate positionStartDate, AirDateInterval dateInterval, String guestPhotoUrl2, boolean hideGuestProfilePhoto2, CharSequence profilePlaceholderText2) {
        int i = -1;
        while (i < positionEnd - positionStart) {
            CalendarDay calendarDay = calendarDays.get(positionStartDate.plusDays(i));
            if (calendarDay == null || !dateInterval.contains(calendarDay.getDate())) {
                i++;
            } else {
                int daysUntilStart = positionStartDate.getDaysUntil(dateInterval.getStart());
                int daysUntilEnd = positionStartDate.getDaysUntil(dateInterval.getEnd());
                if (daysUntilStart < positionEnd - positionStart && daysUntilEnd >= 0) {
                    return new CalendarGridReservationModel(daysUntilStart, daysUntilEnd, guestPhotoUrl2, hideGuestProfilePhoto2, profilePlaceholderText2);
                }
                i++;
            }
        }
        return null;
    }

    public CalendarGridReservationModel(int daysUntilStart, int daysUntilEnd, String guestPhotoUrl2, boolean hideGuestProfilePhoto2, CharSequence profilePlaceholderText2) {
        this.daysUntilReservationStarts = daysUntilStart;
        this.daysUntilReservationEnds = daysUntilEnd;
        this.color = CalendarReservationColor.Arches;
        this.hasEnded = false;
        this.guestPhotoUrl = guestPhotoUrl2;
        this.hideGuestProfilePhoto = hideGuestProfilePhoto2;
        this.profilePlaceholderText = profilePlaceholderText2;
    }

    public CalendarGridReservationModel(int daysUntilStart, int daysUntilEnd, Reservation reservation) {
        this.daysUntilReservationStarts = daysUntilStart;
        this.daysUntilReservationEnds = daysUntilEnd;
        this.guestPhotoUrl = reservation.getGuest().getThumbnailUrl();
        this.color = CalendarReservationColor.fromReservation(reservation);
        this.hasEnded = reservation.hasEnded();
        this.hideGuestProfilePhoto = FeatureToggles.hideGuestProfilePhoto(reservation.getStatus());
        this.profilePlaceholderText = reservation.getGuest().getHiddenProfileName();
        if (TextUtils.isEmpty(this.profilePlaceholderText)) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("User first name is null"));
        }
    }

    public int getDaysUntilReservationStarts() {
        return this.daysUntilReservationStarts;
    }

    public int getDaysUntilReservationEnds() {
        return this.daysUntilReservationEnds;
    }

    public boolean showGuestProfilePhoto() {
        return this.daysUntilReservationStarts >= 0;
    }

    public boolean hasEnded() {
        return this.hasEnded;
    }

    public void setImageView(HaloImageView imageView2) {
        this.imageView = imageView2;
    }

    public void setPlaceholderTextView(AirTextView view) {
        this.placeholderTextView = view;
    }

    public HaloImageView getImageView() {
        return this.imageView;
    }

    public AirTextView getPlaceholderTextView() {
        return this.placeholderTextView;
    }

    public String getGuestPhotoUrl() {
        return this.guestPhotoUrl;
    }

    public CharSequence getProfilePlaceholderText() {
        return this.profilePlaceholderText;
    }

    public boolean shouldHideGuestProfilePhoto() {
        return this.hideGuestProfilePhoto;
    }

    public int getColor() {
        return this.color.getFillColor();
    }
}
