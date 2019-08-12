package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.managelisting.C7368R;

final /* synthetic */ class LocationAdapter$$Lambda$4 implements OnClickListener {
    private static final LocationAdapter$$Lambda$4 instance = new LocationAdapter$$Lambda$4();

    private LocationAdapter$$Lambda$4() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        ErrorUtils.showErrorUsingSnackbar(view, C7368R.string.location_not_editable, C7368R.string.location_new_listing);
    }
}
