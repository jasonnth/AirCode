package com.airbnb.jitney.event.logging.Wishlists.p298v1;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingPanelAction */
public enum SettingPanelAction {
    CANCEL_BUTTON_CLICKED(1),
    SAVE_BUTTON_CLICKED(2),
    DELETE_BUTTON_CLICKED(3),
    NAME_CHANGED(4),
    DATES_CHANGED(5),
    GUESTS_CHANGED(6),
    PRIVACY_CHANGED(7),
    MEMBERSHIP_CHANGED(8),
    DELETE_LIST_MODAL_CLOSED(9),
    DELETE_LIST_CANCEL_CLICKED(10),
    DELETE_LIST_CONFIRM_CLICKED(11),
    INVITE_BUTTON_CLICKED(12),
    APPLY_BUTTON_CLICKED(13);
    
    public final int value;

    private SettingPanelAction(int value2) {
        this.value = value2;
    }
}
