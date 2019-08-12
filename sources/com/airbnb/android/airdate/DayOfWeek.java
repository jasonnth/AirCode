package com.airbnb.android.airdate;

public enum DayOfWeek {
    Monday(1, 1),
    Tuesday(2, 2),
    Wednesday(3, 3),
    Thursday(4, 4),
    Friday(5, 5),
    Saturday(6, 6),
    Sunday(7, 0);
    
    private final int indexFromSunday;
    private final int standardIndex;

    private DayOfWeek(int standardIndex2, int indexFromSunday2) {
        this.standardIndex = standardIndex2;
        this.indexFromSunday = indexFromSunday2;
    }

    public int getDayIndex() {
        return this.standardIndex;
    }

    public int getDayIndexFromSunday() {
        return this.indexFromSunday;
    }

    public static DayOfWeek getDayOfWeek(int constant) {
        DayOfWeek[] values;
        for (DayOfWeek dayOfWeek : values()) {
            if (dayOfWeek.getDayIndex() == constant || dayOfWeek.getDayIndexFromSunday() == constant) {
                return dayOfWeek;
            }
        }
        throw new IllegalArgumentException("Illegal DayOfWeek constant specified");
    }

    public int getLocalizedDayOfWeek() {
        switch (this) {
            case Monday:
                return C1647R.string.monday;
            case Tuesday:
                return C1647R.string.tuesday;
            case Wednesday:
                return C1647R.string.wednesday;
            case Thursday:
                return C1647R.string.thursday;
            case Friday:
                return C1647R.string.friday;
            case Saturday:
                return C1647R.string.saturday;
            default:
                return C1647R.string.sunday;
        }
    }
}
