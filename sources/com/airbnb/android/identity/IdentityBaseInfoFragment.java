package com.airbnb.android.identity;

import android.os.Bundle;
import android.view.View;
import com.airbnb.android.core.identity.IdentityReactNativeInfoType;
import com.airbnb.android.core.intents.ReactNativeIntents;

public abstract class IdentityBaseInfoFragment extends BaseAccountVerificationFragment implements IdentityLoaderFragment {
    protected static final int RC_RN_INFO = 800;

    public abstract IdentityReactNativeInfoType getType();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        show();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        show();
    }

    public void show() {
        startActivityForResult(ReactNativeIntents.intentForVerificationInfo(getContext(), getType()), 800);
    }
}
