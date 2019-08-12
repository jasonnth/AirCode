package com.airbnb.p027n2.browser;

/* renamed from: com.airbnb.n2.browser.DLSComponentListFragment$ViewHolder$$Lambda$3 */
final /* synthetic */ class DLSComponentListFragment$ViewHolder$$Lambda$3 implements Runnable {
    private final ViewHolder arg$1;

    private DLSComponentListFragment$ViewHolder$$Lambda$3(ViewHolder viewHolder) {
        this.arg$1 = viewHolder;
    }

    public static Runnable lambdaFactory$(ViewHolder viewHolder) {
        return new DLSComponentListFragment$ViewHolder$$Lambda$3(viewHolder);
    }

    public void run() {
        this.arg$1.frame.requestLayout();
    }
}
