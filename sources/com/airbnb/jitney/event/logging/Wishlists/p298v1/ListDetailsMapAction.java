package com.airbnb.jitney.event.logging.Wishlists.p298v1;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.ListDetailsMapAction */
public enum ListDetailsMapAction {
    LISTING_MARKER_CLICKED(1),
    LISTING_CARD_POPUP_INTERACTED(2),
    MAP_ZOOMED_IN(3),
    MAP_ZOOMED_OUT(4),
    MAP_DRAGGED(5),
    TRANSIT_LAYER_ENABLED(6),
    TRANSIT_LAYER_DISABLED(7);
    
    public final int value;

    private ListDetailsMapAction(int value2) {
        this.value = value2;
    }
}
