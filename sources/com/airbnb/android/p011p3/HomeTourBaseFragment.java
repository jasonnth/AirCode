package com.airbnb.android.p011p3;

import android.content.Context;
import com.airbnb.android.core.fragments.AirFragment;

/* renamed from: com.airbnb.android.p3.HomeTourBaseFragment */
public class HomeTourBaseFragment extends AirFragment {
    protected HomeTourController controller;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.controller = (HomeTourController) context;
    }

    public void onDetach() {
        this.controller = null;
        super.onDetach();
    }
}
