package com.airbnb.jitney.event.logging.Wishlists.p298v1;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.ListingCardAction */
public enum ListingCardAction {
    ACTIVATED(1),
    DEACTIVATED(2),
    REMOVED_BY_BUTTON(3),
    PREVIOUS_IMAGE_CLICKED(4),
    NEXT_IMAGE_CLICKED(5),
    PAGE_LINK_CLICKED(6),
    CHECK_PRICE_AVAILABILITY_BUTTON_CLICKED(7),
    BOOKING_BUTTON_CLICKED(8),
    UP_VOTE_ADDED(9),
    UP_VOTE_REMOVED(10),
    DOWN_VOTE_ADDED(11),
    DOWN_VOTE_REMOVED(12),
    IMAGE_CHANGED(13),
    HEART_BUTTON_CLICKED(14),
    REMOVE_LISTING_MODAL_CLOSED(15),
    REMOVE_LISTING_CANCEL_CLICKED(16),
    REMOVE_LISTING_CONFIRM_CLICKED(17);
    
    public final int value;

    private ListingCardAction(int value2) {
        this.value = value2;
    }
}
