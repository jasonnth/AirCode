package com.facebook.accountkit.p029ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: com.facebook.accountkit.ui.LoginFragment */
abstract class LoginFragment extends ViewStateFragment {
    /* access modifiers changed from: protected */
    public abstract View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    LoginFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = createView(inflater, container, savedInstanceState);
        }
        ViewUtility.applyThemeAttributes(getActivity(), getUIManager(), view);
        return view;
    }
}
