package com.airbnb.jitney.event.logging.Wishlists.p298v1;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.NewListModalAction */
public enum NewListModalAction {
    NAME_INPUT_FOCUSED(1),
    PRIVACY_SETTING_SELECTED(2),
    SAVE_BUTTON_CLICKED(3),
    CANCEL_BUTTON_CLICKED(4),
    CLOSE_BUTTON_CLICKED(5),
    OUTSIDE_MODAL_CLICKED(6),
    MODAL_CLOSED(7);
    
    public final int value;

    private NewListModalAction(int value2) {
        this.value = value2;
    }
}
