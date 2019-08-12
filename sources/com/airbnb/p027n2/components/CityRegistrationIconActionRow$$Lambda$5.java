package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.Toast;
import com.airbnb.p027n2.utils.AirTextBuilder.OnLinkClickListener;

/* renamed from: com.airbnb.n2.components.CityRegistrationIconActionRow$$Lambda$5 */
final /* synthetic */ class CityRegistrationIconActionRow$$Lambda$5 implements OnLinkClickListener {
    private static final CityRegistrationIconActionRow$$Lambda$5 instance = new CityRegistrationIconActionRow$$Lambda$5();

    private CityRegistrationIconActionRow$$Lambda$5() {
    }

    public static OnLinkClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view, CharSequence charSequence) {
        Toast.makeText(view.getContext(), "Link clicked", 1).show();
    }
}
