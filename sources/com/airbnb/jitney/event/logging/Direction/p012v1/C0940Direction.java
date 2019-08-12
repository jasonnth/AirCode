package com.airbnb.jitney.event.logging.Direction.p012v1;

/* renamed from: com.airbnb.jitney.event.logging.Direction.v1.Direction */
public enum C0940Direction {
    Up(1),
    Down(2),
    Left(3),
    Right(4);
    
    public final int value;

    private C0940Direction(int value2) {
        this.value = value2;
    }

    public static C0940Direction findByValue(int value2) {
        switch (value2) {
            case 1:
                return Up;
            case 2:
                return Down;
            case 3:
                return Left;
            case 4:
                return Right;
            default:
                return null;
        }
    }
}
