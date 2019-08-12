package com.airbnb.android.core;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.fragments.NavigationLoggingElement;

public interface AirFragmentFacade extends NavigationLoggingElement {
    Context getActivity();

    Context getContext();

    void startActivityForResult(Intent intent, int i);
}
