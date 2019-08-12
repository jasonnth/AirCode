package com.airbnb.android.itinerary.views;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.p027n2.components.RecommendationRow;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class ItineraryItemView_ViewBinding implements Unbinder {
    private ItineraryItemView target;

    public ItineraryItemView_ViewBinding(ItineraryItemView target2) {
        this(target2, target2);
    }

    public ItineraryItemView_ViewBinding(ItineraryItemView target2, View source) {
        this.target = target2;
        target2.timelineLineTop = Utils.findRequiredView(source, C5755R.C5757id.timeline_line_top, "field 'timelineLineTop'");
        target2.timelineLineBottom = Utils.findRequiredView(source, C5755R.C5757id.timeline_line_bottom, "field 'timelineLineBottom'");
        target2.timelineLineFaded = Utils.findRequiredView(source, C5755R.C5757id.timeline_line_faded, "field 'timelineLineFaded'");
        target2.header = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.header, "field 'header'", AirTextView.class);
        target2.subheader = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.subheader, "field 'subheader'", AirTextView.class);
        target2.timelineIndicator = (AirImageView) Utils.findRequiredViewAsType(source, C5755R.C5757id.timeline_indicator, "field 'timelineIndicator'", AirImageView.class);
        target2.nowIndicator = (LottieAnimationView) Utils.findRequiredViewAsType(source, C5755R.C5757id.now_indicator, "field 'nowIndicator'", LottieAnimationView.class);
        target2.cardsContainer = (FrameLayout) Utils.findRequiredViewAsType(source, C5755R.C5757id.itinerary_card_container, "field 'cardsContainer'", FrameLayout.class);
        target2.suggestionsContainer = (FrameLayout) Utils.findRequiredViewAsType(source, C5755R.C5757id.suggestions_container, "field 'suggestionsContainer'", FrameLayout.class);
        target2.topRecommendationRow = (RecommendationRow) Utils.findRequiredViewAsType(source, C5755R.C5757id.top_recommendation_row, "field 'topRecommendationRow'", RecommendationRow.class);
        target2.bottomRecommendationRow = (RecommendationRow) Utils.findRequiredViewAsType(source, C5755R.C5757id.bottom_recommendation_row, "field 'bottomRecommendationRow'", RecommendationRow.class);
        target2.contentTextContainer = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.content_text, "field 'contentTextContainer'", AirTextView.class);
        target2.headerContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C5755R.C5757id.header_container, "field 'headerContainer'", LinearLayout.class);
        target2.flightEntryPointContainer = (FlightEntryPointCardView) Utils.findRequiredViewAsType(source, C5755R.C5757id.flight_entry_point_container, "field 'flightEntryPointContainer'", FlightEntryPointCardView.class);
        target2.cards = Utils.listOf((TripCardView) Utils.findRequiredViewAsType(source, C5755R.C5757id.card1, "field 'cards'", TripCardView.class), (TripCardView) Utils.findRequiredViewAsType(source, C5755R.C5757id.card2, "field 'cards'", TripCardView.class), (TripCardView) Utils.findRequiredViewAsType(source, C5755R.C5757id.card3, "field 'cards'", TripCardView.class));
        target2.cardMargin = source.getContext().getResources().getDimensionPixelSize(C5755R.dimen.trip_card_stacked_top_margin);
    }

    public void unbind() {
        ItineraryItemView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.timelineLineTop = null;
        target2.timelineLineBottom = null;
        target2.timelineLineFaded = null;
        target2.header = null;
        target2.subheader = null;
        target2.timelineIndicator = null;
        target2.nowIndicator = null;
        target2.cardsContainer = null;
        target2.suggestionsContainer = null;
        target2.topRecommendationRow = null;
        target2.bottomRecommendationRow = null;
        target2.contentTextContainer = null;
        target2.headerContainer = null;
        target2.flightEntryPointContainer = null;
        target2.cards = null;
    }
}
