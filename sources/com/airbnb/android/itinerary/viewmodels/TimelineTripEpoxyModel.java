package com.airbnb.android.itinerary.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.itinerary.views.ItineraryItemView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class TimelineTripEpoxyModel extends AirEpoxyModel<ItineraryItemView> {
    OnClickListener clickListener;
    boolean isBottomItem;
    boolean isUpcomingItem;
    boolean loading;
    boolean showExtraHeaderPadding;
    boolean showHeaderPadding;
    TimelineTrip timelineTrip;

    public void bind(ItineraryItemView view) {
        super.bind(view);
        if (this.loading) {
            view.setSubheader(null);
            view.setHeader(null);
            view.clear();
            return;
        }
        view.setHeaderContainerPadding(ItineraryUtils.getHeaderPaddingType(this.showHeaderPadding, this.showExtraHeaderPadding));
        view.setHeader(ItineraryUtils.getHeader(this.timelineTrip, true));
        view.setSubheader(ItineraryUtils.getSubheader(view.getContext(), this.timelineTrip, true));
        view.setImageUrls(this.timelineTrip.bundle_photo_urls());
        view.setCardTitle(ItineraryUtils.getCardTitle(this.timelineTrip));
        view.setCardSubtitle(ItineraryUtils.getCardSubtitle(this.timelineTrip));
        view.setTimelineLineColorAndIndicator(ItineraryUtils.isDuringOrUpcoming(this.timelineTrip, true), this.isUpcomingItem);
        view.setTimelineLineFadedColor(this.isBottomItem ? ItineraryUtils.getTimelineColor(view.getContext(), this.timelineTrip, true) : -1);
        view.setCardClickListener(this.clickListener);
    }

    public void unbind(ItineraryItemView view) {
        super.unbind(view);
        view.clear();
        view.setOnClickListener(null);
    }
}
