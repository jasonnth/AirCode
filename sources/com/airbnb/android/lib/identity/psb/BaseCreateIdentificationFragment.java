package com.airbnb.android.lib.identity.psb;

import android.content.Context;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.Check;

public abstract class BaseCreateIdentificationFragment extends AirFragment {
    protected CreateIdentificationActivity callback;

    public void onAttach(Context context) {
        super.onAttach(context);
        Check.state(context instanceof CreateIdentificationActivity, "context must be instance of CreateIdentificationActivity");
        this.callback = (CreateIdentificationActivity) context;
    }

    public void onDetach() {
        this.callback = null;
        super.onDetach();
    }
}
