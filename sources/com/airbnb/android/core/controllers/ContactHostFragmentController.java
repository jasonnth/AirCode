package com.airbnb.android.core.controllers;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.p009p3.interfaces.ContactHostDataChangeListener;

public interface ContactHostFragmentController {
    AirDate getCheckInDate();

    AirDate getCheckOutDate();

    GuestDetails getGuestDetails();

    String getInquiryMessage();

    void onComposeMessageRequested();

    void onDatesUpdateRequested();

    void onGuestsUpdateRequested();

    void onSubmitToHost();

    void registerListener(ContactHostDataChangeListener contactHostDataChangeListener);

    void unregisterListener(ContactHostDataChangeListener contactHostDataChangeListener);
}
