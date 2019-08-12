package com.airbnb.jitney.event.logging.Wishlists.p298v1;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.OutsideAppAction */
public enum OutsideAppAction {
    MODAL_OPENED_BY_BUTTON(1),
    LISTING_ADDED_BY_BUTTON(2),
    LISTING_ADDED_BY_MODAL(3),
    LISTING_REMOVED_BY_BUTTON(4),
    LISTING_REMOVED_BY_MODAL(5),
    NEW_WISH_LIST_BUTTON_CLICKED(6),
    NEW_WISH_LIST_BUTTON_CONFIRM_CLICKED(7);
    
    public final int value;

    private OutsideAppAction(int value2) {
        this.value = value2;
    }
}
