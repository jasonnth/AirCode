package com.airbnb.jitney.event.logging.p167P3.p168v2;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.PageNavigationActionType */
public enum PageNavigationActionType {
    NAVIGATION_TAB_CLICK(1),
    WAYPOINT_SCROLLED(2),
    SECTION_SCROLLED(3),
    LEAVE_PAGE(4),
    DURATION_CHECKPOINT(5),
    USER_SCROLLED(6);
    
    public final int value;

    private PageNavigationActionType(int value2) {
        this.value = value2;
    }
}
