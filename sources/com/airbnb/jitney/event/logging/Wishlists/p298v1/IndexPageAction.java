package com.airbnb.jitney.event.logging.Wishlists.p298v1;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.IndexPageAction */
public enum IndexPageAction {
    NEW_WISH_LIST_BUTTON_CLICKED(1),
    WISH_LIST_CARD_CLICKED(2),
    YOUR_LISTS_SEE_ALL_BUTTON_CLICKED(3),
    POPULAR_SEE_ALL_BUTTON_CLICKED(4),
    AIRBNB_PICKS_SEE_ALL_BUTTON_CLICKED(5),
    HOME_BUTTON_CLICKED(6),
    LOAD_MORE_BUTTON_CLICKED(7);
    
    public final int value;

    private IndexPageAction(int value2) {
        this.value = value2;
    }
}
