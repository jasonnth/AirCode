package com.airbnb.p027n2.primitives;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.primitives.AirSwitch$$Lambda$4 */
final /* synthetic */ class AirSwitch$$Lambda$4 implements OnClickListener {
    private final AirSwitch arg$1;

    private AirSwitch$$Lambda$4(AirSwitch airSwitch) {
        this.arg$1 = airSwitch;
    }

    public static OnClickListener lambdaFactory$(AirSwitch airSwitch) {
        return new AirSwitch$$Lambda$4(airSwitch);
    }

    public void onClick(View view) {
        this.arg$1.toggle();
    }
}
