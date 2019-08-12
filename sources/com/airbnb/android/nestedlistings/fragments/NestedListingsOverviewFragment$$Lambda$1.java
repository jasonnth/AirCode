package com.airbnb.android.nestedlistings.fragments;

final /* synthetic */ class NestedListingsOverviewFragment$$Lambda$1 implements Runnable {
    private final NestedListingsOverviewFragment arg$1;

    private NestedListingsOverviewFragment$$Lambda$1(NestedListingsOverviewFragment nestedListingsOverviewFragment) {
        this.arg$1 = nestedListingsOverviewFragment;
    }

    public static Runnable lambdaFactory$(NestedListingsOverviewFragment nestedListingsOverviewFragment) {
        return new NestedListingsOverviewFragment$$Lambda$1(nestedListingsOverviewFragment);
    }

    public void run() {
        this.arg$1.updateAdapter(false);
    }
}
