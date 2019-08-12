package com.airbnb.android.itinerary.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.airbnb.android.itinerary.data.models.TripEventSecondaryAction;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.itinerary.views.ItineraryItemView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class TripEventEpoxyModel extends AirEpoxyModel<ItineraryItemView> {
    OnClickListener actionClickListener;
    OnClickListener cardClickListener;
    boolean isBottomItem;
    boolean isTimeline;
    boolean isUpcomingItem;
    boolean showExtraHeaderPadding;
    boolean showHeaderPadding;
    TripEvent tripEvent;

    public void bind(ItineraryItemView view) {
        super.bind(view);
        view.setHeader(ItineraryUtils.getHeader(this.tripEvent, this.isTimeline));
        view.setSubheader(ItineraryUtils.getSubheader(view.getContext(), this.tripEvent, this.isTimeline));
        view.setCardTitle(ItineraryUtils.getCardTitle(this.tripEvent));
        view.setCardSubtitle(ItineraryUtils.getCardSubtitle(this.tripEvent));
        view.setCardSubtitleCopyToast(ItineraryUtils.getCardSubtitleCopyToast(this.tripEvent));
        view.setImageUrl(this.tripEvent.picture(), this.tripEvent.isCheckoutCard());
        view.setTimelineLineColorAndIndicator(ItineraryUtils.isDuringOrUpcoming(this.tripEvent, this.isTimeline), this.isUpcomingItem);
        view.setTimelineLineFadedColor(this.isBottomItem ? ItineraryUtils.getTimelineColor(view.getContext(), this.tripEvent, this.isTimeline) : -1);
        view.setCardClickListener(this.cardClickListener);
        TripEventSecondaryAction secondaryAction = this.tripEvent.getMainSecondaryAction();
        view.setSecondaryActionTitle(ItineraryUtils.getSecondaryActionTitle(secondaryAction));
        view.setSecondaryActionButtonText(secondaryAction == null ? null : secondaryAction.buttonText(), secondaryAction == null ? null : secondaryAction.buttonType());
        view.setSecondaryActionButtonClickListener(this.actionClickListener);
        if (this.tripEvent.isCheckoutCard()) {
            view.setContentText(view.getContext().getString(C5755R.string.itinerary_view_details));
            view.setContentTextClickListener(this.cardClickListener);
        }
        if (this.isTimeline) {
            view.setHeaderContainerPadding(ItineraryUtils.getHeaderPaddingType(this.showHeaderPadding, this.showExtraHeaderPadding));
        }
    }

    public void unbind(ItineraryItemView view) {
        super.unbind(view);
        view.clear();
        view.setCardClickListener(null);
        view.setSecondaryActionButtonClickListener(null);
    }
}
