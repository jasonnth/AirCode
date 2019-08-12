package com.airbnb.p027n2.epoxy;

/* renamed from: com.airbnb.n2.epoxy.EpoxyUtils */
final class EpoxyUtils {
    static int getDefaultSpanForDividerType(int marqueeType, int totalSpanCount, int position, int itemCount) {
        switch (marqueeType) {
            case -1:
            case 0:
                return 1;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return totalSpanCount;
            default:
                throw new IllegalStateException("Unknown type: " + marqueeType);
        }
    }
}
