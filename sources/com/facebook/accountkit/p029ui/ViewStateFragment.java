package com.facebook.accountkit.p029ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

/* renamed from: com.facebook.accountkit.ui.ViewStateFragment */
abstract class ViewStateFragment extends Fragment {
    public static final String TAG = ViewStateFragment.class.getSimpleName();
    protected static final String UI_MANAGER_KEY = (TAG + ".UI_MANAGER_KEY");
    private static final String VIEW_STATE_KEY = (TAG + ".VIEW_STATE_KEY");
    private final Bundle viewState = new Bundle();

    ViewStateFragment() {
    }

    /* access modifiers changed from: protected */
    public UIManager getUIManager() {
        return (UIManager) this.viewState.get(UI_MANAGER_KEY);
    }

    /* access modifiers changed from: protected */
    public Bundle getViewState() {
        return this.viewState;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.viewState.putAll(savedInstanceState.getBundle(VIEW_STATE_KEY));
        }
        if (!this.viewState.containsKey(UI_MANAGER_KEY)) {
            throw new RuntimeException("You must supply a UIManager to " + TAG);
        }
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (view != null) {
            onViewReadyWithState(view, this.viewState);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putBundle(VIEW_STATE_KEY, this.viewState);
        super.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void onViewReadyWithState(View view, Bundle viewState2) {
    }
}
