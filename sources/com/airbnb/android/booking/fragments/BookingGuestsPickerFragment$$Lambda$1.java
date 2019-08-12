package com.airbnb.android.booking.fragments;

import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import com.airbnb.jitney.event.logging.P4FlowGuestSheetSection.p171v1.C2465P4FlowGuestSheetSection;
import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;

final /* synthetic */ class BookingGuestsPickerFragment$$Lambda$1 implements OnValueChangedListener {
    private final BookingJitneyLogger arg$1;
    private final BookingController arg$2;
    private final C2465P4FlowGuestSheetSection arg$3;

    private BookingGuestsPickerFragment$$Lambda$1(BookingJitneyLogger bookingJitneyLogger, BookingController bookingController, C2465P4FlowGuestSheetSection p4FlowGuestSheetSection) {
        this.arg$1 = bookingJitneyLogger;
        this.arg$2 = bookingController;
        this.arg$3 = p4FlowGuestSheetSection;
    }

    public static OnValueChangedListener lambdaFactory$(BookingJitneyLogger bookingJitneyLogger, BookingController bookingController, C2465P4FlowGuestSheetSection p4FlowGuestSheetSection) {
        return new BookingGuestsPickerFragment$$Lambda$1(bookingJitneyLogger, bookingController, p4FlowGuestSheetSection);
    }

    public void onValueChanged(int i, int i2) {
        BookingGuestsPickerFragment.lambda$getChangedListener$0(this.arg$1, this.arg$2, this.arg$3, i, i2);
    }
}
