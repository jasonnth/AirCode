package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryCreationPickTripController$$Lambda$2 implements OnClickListener {
    private final StoryCreationPickTripController arg$1;

    private StoryCreationPickTripController$$Lambda$2(StoryCreationPickTripController storyCreationPickTripController) {
        this.arg$1 = storyCreationPickTripController;
    }

    public static OnClickListener lambdaFactory$(StoryCreationPickTripController storyCreationPickTripController) {
        return new StoryCreationPickTripController$$Lambda$2(storyCreationPickTripController);
    }

    public void onClick(View view) {
        this.arg$1.delegate.onShowMoreReservationsClicked();
    }
}
