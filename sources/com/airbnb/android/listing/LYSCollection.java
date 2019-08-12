package com.airbnb.android.listing;

import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.ImmutableList;
import java.util.List;

public enum LYSCollection {
    Basics,
    Marketing,
    Booking,
    Completion;
    
    private static final List<LYSCollection> ORDER = null;

    static {
        ORDER = ImmutableList.m1288of(Basics, Marketing, Booking, Completion);
    }

    public boolean isAfter(LYSCollection comparison) {
        return ORDER.indexOf(this) > ORDER.indexOf(comparison);
    }

    public static LYSCollection first() {
        return (LYSCollection) ORDER.get(0);
    }

    public static LYSCollection last() {
        return (LYSCollection) ListUtils.getLast(ORDER);
    }
}
