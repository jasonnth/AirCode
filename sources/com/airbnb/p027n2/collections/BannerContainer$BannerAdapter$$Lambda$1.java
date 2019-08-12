package com.airbnb.p027n2.collections;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.n2.R;
import com.devbrackets.android.exomedia.p306ui.widget.EMVideoView;

/* renamed from: com.airbnb.n2.collections.BannerContainer$BannerAdapter$$Lambda$1 */
final /* synthetic */ class BannerContainer$BannerAdapter$$Lambda$1 implements OnClickListener {
    private final BannerAdapter arg$1;
    private final int arg$2;

    private BannerContainer$BannerAdapter$$Lambda$1(BannerAdapter bannerAdapter, int i) {
        this.arg$1 = bannerAdapter;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(BannerAdapter bannerAdapter, int i) {
        return new BannerContainer$BannerAdapter$$Lambda$1(bannerAdapter, i);
    }

    public void onClick(View view) {
        this.arg$1.listener.onBannerClicked(this.arg$2, view, ((EMVideoView) view.findViewById(R.id.video_view)).getCurrentPosition());
    }
}
