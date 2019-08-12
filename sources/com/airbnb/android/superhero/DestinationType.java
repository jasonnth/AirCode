package com.airbnb.android.superhero;

public enum DestinationType {
    MAP(0),
    DEEPLINK(1),
    CALLBACK(2),
    POST(3),
    DISMISS(4),
    HERO(5),
    UNKNOWN(-1);
    
    public final Long value;

    private DestinationType(int value2) {
        this.value = Long.valueOf((long) value2);
    }

    static DestinationType fromLong(long value2) {
        switch ((int) value2) {
            case 0:
                return MAP;
            case 1:
                return DEEPLINK;
            case 2:
                return CALLBACK;
            case 3:
                return POST;
            case 4:
                return DISMISS;
            case 5:
                return HERO;
            default:
                return UNKNOWN;
        }
    }
}
