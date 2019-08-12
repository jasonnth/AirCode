package com.miteksystems.misnap.events;

public class ShutdownEvent {
    public static final int CANCELLED = 4;
    public static final int CAPTURED = 0;
    public static final int ERROR = 5;
    public static final String EXT_HELP_BUTTON = "help_button";
    public static final String EXT_HELP_TAPS = "help_taps";
    public static final int FAILOVER = 3;
    public static final int HELP = 1;
    public static final int TIMEOUT = 2;
    public final String extendedReason;
    public final int reason;

    public ShutdownEvent(int reason2) {
        this(reason2, "");
    }

    public ShutdownEvent(int reason2, String extendedReason2) {
        this.reason = reason2;
        this.extendedReason = extendedReason2;
    }
}
