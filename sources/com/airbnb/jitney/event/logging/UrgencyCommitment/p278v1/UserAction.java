package com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1;

/* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.UserAction */
public enum UserAction {
    BROWSER_BACK_BUTTON_CLICK(0),
    BROWSER_QUIT_BUTTON(1),
    SAVE_TO_WISHLIST_CLICK(2),
    BOOK_IT_BUTTON_CLICK(3),
    NAVIGATE_TO_P3(4),
    CONTACT_HOST_CLICK(5),
    NAVIGATE_TO_P4(6),
    BOOKING_STATUS(7),
    P4_TIME_SPENT(8),
    U_C_MESSAGE_IMPRESSION(9),
    U_C_MESSAGE_COLLISION(10);
    
    public final int value;

    private UserAction(int value2) {
        this.value = value2;
    }
}
