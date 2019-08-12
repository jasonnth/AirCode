package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.SummaryInterstitial$$Lambda$4 */
final /* synthetic */ class SummaryInterstitial$$Lambda$4 implements OnClickListener {
    private static final SummaryInterstitial$$Lambda$4 instance = new SummaryInterstitial$$Lambda$4();

    private SummaryInterstitial$$Lambda$4() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Right button clicked!", 0).show();
    }
}
