package com.airbnb.android.core;

import android.content.Context;

public interface AirDialogFragmentFacade {
    String getTag();

    boolean shouldShowAsDialog(Context context);
}
