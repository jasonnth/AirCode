package com.jumio.sdk.view.fragment;

import android.app.Fragment;
import com.jumio.sdk.models.CredentialsModel;
import com.jumio.sdk.view.fragment.IBaseActivityCallback;

public interface IBaseFragmentCallback<AC extends IBaseActivityCallback> {
    void closeFragment(int i, int i2);

    CredentialsModel getCredentials();

    void registerActivityCallback(AC ac);

    void startFragment(Fragment fragment, boolean z, int i, int i2);
}
