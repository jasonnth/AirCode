package com.airbnb.android.contentframework.adapters;

import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCreationPickTripRowEpoxyModel_;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.ArrayList;
import java.util.List;

public class StoryCreationPickTripController extends AirEpoxyController {
    private final Delegate delegate;
    private boolean hasMore;
    private boolean isLoading;
    EpoxyControllerLoadingModel_ loaderModel;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    private final List<Reservation> reservations = new ArrayList();
    LinkActionRowEpoxyModel_ showMoreModel;

    public interface Delegate {
        void onReservationSelected(Reservation reservation);

        void onShowMoreReservationsClicked();
    }

    public StoryCreationPickTripController(Delegate delegate2) {
        this.delegate = delegate2;
    }

    public void appendReservations(List<Reservation> reservations2, boolean hasMore2) {
        this.reservations.addAll(reservations2);
        this.hasMore = hasMore2;
        this.isLoading = false;
        requestModelBuild();
    }

    public int getReservationCount() {
        return this.reservations.size();
    }

    public void setLoading(boolean loading) {
        this.isLoading = loading;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeModel.titleRes(C5709R.string.story_creation_pick_trip_title).captionRes(C5709R.string.story_creation_pick_trip_subtitle).addTo(this);
        if (!this.reservations.isEmpty()) {
            for (Reservation reservation : this.reservations) {
                new StoryCreationPickTripRowEpoxyModel_().m4178id((CharSequence) reservation.getConfirmationCode()).reservation(reservation).clickListener(StoryCreationPickTripController$$Lambda$1.lambdaFactory$(this, reservation)).addTo(this);
            }
            if (this.isLoading) {
                this.loaderModel.addTo(this);
            } else if (this.hasMore) {
                this.showMoreModel.textRes(C5709R.string.story_creation_pick_trip_show_more).clickListener(StoryCreationPickTripController$$Lambda$2.lambdaFactory$(this)).addTo(this);
            }
        } else {
            this.loaderModel.addTo(this);
        }
    }
}
