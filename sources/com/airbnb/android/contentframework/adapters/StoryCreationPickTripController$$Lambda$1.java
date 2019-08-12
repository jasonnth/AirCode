package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Reservation;

final /* synthetic */ class StoryCreationPickTripController$$Lambda$1 implements OnClickListener {
    private final StoryCreationPickTripController arg$1;
    private final Reservation arg$2;

    private StoryCreationPickTripController$$Lambda$1(StoryCreationPickTripController storyCreationPickTripController, Reservation reservation) {
        this.arg$1 = storyCreationPickTripController;
        this.arg$2 = reservation;
    }

    public static OnClickListener lambdaFactory$(StoryCreationPickTripController storyCreationPickTripController, Reservation reservation) {
        return new StoryCreationPickTripController$$Lambda$1(storyCreationPickTripController, reservation);
    }

    public void onClick(View view) {
        this.arg$1.delegate.onReservationSelected(this.arg$2);
    }
}
