package com.airbnb.android.lib.fragments;

import android.app.Activity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.EditProfileInterface;
import com.airbnb.android.core.interfaces.OnBackListener;

public class BaseEditProfileSectionFragment extends AirFragment implements OnBackListener {
    protected EditProfileInterface mCallback;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof EditProfileInterface)) {
            throw new IllegalArgumentException(activity.toString() + " must implement " + EditProfileInterface.class.getSimpleName());
        }
        this.mCallback = (EditProfileInterface) activity;
    }

    public void onResume() {
        super.onResume();
        updateViews();
    }

    /* access modifiers changed from: protected */
    public void updateViews() {
    }

    public boolean onBackPressed() {
        return false;
    }
}
