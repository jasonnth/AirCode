package com.airbnb.jitney.event.logging.Operation.p165v1;

/* renamed from: com.airbnb.jitney.event.logging.Operation.v1.Operation */
public enum C2451Operation {
    Impression(1),
    Click(2),
    Show(3),
    Select(4),
    Focus(5),
    Toggle(6),
    Cache(7),
    Schedule(8),
    Dismiss(9),
    Scroll(10),
    Swipe(11),
    Update(12),
    Save(13),
    Delete(14),
    Move(15),
    Enter(16),
    Submit(17),
    Blur(18),
    ReduxAction(19),
    Pixel(20);
    
    public final int value;

    private C2451Operation(int value2) {
        this.value = value2;
    }
}
