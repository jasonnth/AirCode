package com.airbnb.p027n2.collections;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;

/* renamed from: com.airbnb.n2.collections.BannerContainer$BannerAdapter$BannerViewHolder$$Lambda$1 */
final /* synthetic */ class BannerContainer$BannerAdapter$BannerViewHolder$$Lambda$1 implements OnPreparedListener {
    private final BannerViewHolder arg$1;

    private BannerContainer$BannerAdapter$BannerViewHolder$$Lambda$1(BannerViewHolder bannerViewHolder) {
        this.arg$1 = bannerViewHolder;
    }

    public static OnPreparedListener lambdaFactory$(BannerViewHolder bannerViewHolder) {
        return new BannerContainer$BannerAdapter$BannerViewHolder$$Lambda$1(bannerViewHolder);
    }

    public void onPrepared() {
        BannerViewHolder.lambda$bind$0(this.arg$1);
    }
}
