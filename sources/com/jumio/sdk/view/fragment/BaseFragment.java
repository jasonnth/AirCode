package com.jumio.sdk.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.core.mvp.presenter.Presenter;
import com.jumio.core.mvp.view.MvpFragment;
import com.jumio.sdk.view.InteractibleView;
import com.jumio.sdk.view.fragment.IBaseFragmentCallback;

public abstract class BaseFragment<P extends Presenter, FragmentCallback extends IBaseFragmentCallback> extends MvpFragment<P> implements InteractibleView, IBaseActivityCallback {
    /* access modifiers changed from: protected */
    public FragmentCallback callback;
    private String fragmentName;

    public void onAttach(Context context) {
        super.onAttach(context);
        attach(context);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attach(activity);
    }

    /* access modifiers changed from: protected */
    public void attach(Context context) {
        try {
            this.callback = (IBaseFragmentCallback) context;
        } catch (ClassCastException e) {
            ClassCastException cce = new ClassCastException(context.toString() + " must implement IBaseActivityCallback ");
            cce.setStackTrace(e.getStackTrace());
            throw cce;
        }
    }

    public boolean onBackButtonPressed() {
        return false;
    }

    public boolean onHomeButtonPressed() {
        return false;
    }

    public Context getContext() {
        if (VERSION.SDK_INT >= 23) {
            return super.getContext();
        }
        return getActivity();
    }

    /* access modifiers changed from: protected */
    public void setStatusBarColor(int color) {
        Activity activity = getActivity();
        if (activity != null && VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(color);
        }
    }

    /* access modifiers changed from: protected */
    public void showActionbar(boolean show) {
        Activity activity = getActivity();
        if (activity != null) {
            if (activity instanceof AppCompatActivity) {
                ActionBar bar = ((AppCompatActivity) activity).getSupportActionBar();
                if (bar == null) {
                    return;
                }
                if (show) {
                    bar.show();
                } else {
                    bar.hide();
                }
            } else {
                android.app.ActionBar bar2 = activity.getActionBar();
                if (bar2 == null) {
                    return;
                }
                if (show) {
                    bar2.show();
                } else {
                    bar2.hide();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showHomeIndicator(boolean show) {
        Activity activity = getActivity();
        if (activity != null) {
            if (activity instanceof AppCompatActivity) {
                ActionBar bar = ((AppCompatActivity) activity).getSupportActionBar();
                if (bar != null) {
                    bar.setDisplayHomeAsUpEnabled(show);
                    return;
                }
                return;
            }
            android.app.ActionBar bar2 = activity.getActionBar();
            if (bar2 != null) {
                bar2.setDisplayHomeAsUpEnabled(show);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setActionbarTitle(String title) {
        Activity activity = getActivity();
        if (activity != null) {
            SpannableString spannableString = null;
            if (!(title == null || title.length() == 0)) {
                spannableString = new SpannableString(title);
                spannableString.setSpan(new AbsoluteSizeSpan(20, true), 0, spannableString.length(), 33);
            }
            if (activity instanceof AppCompatActivity) {
                ActionBar bar = ((AppCompatActivity) activity).getSupportActionBar();
                if (bar != null) {
                    bar.setTitle((CharSequence) spannableString);
                    return;
                }
                return;
            }
            android.app.ActionBar bar2 = activity.getActionBar();
            if (bar2 != null) {
                bar2.setTitle(spannableString);
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragmentName = getClass().getSimpleName();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), this.fragmentName, "onCreate()"));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), this.fragmentName, "onCreateView()"));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), this.fragmentName, "onViewCreated()"));
    }

    public void onStart() {
        super.onStart();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), this.fragmentName, "onStart()"));
    }

    public void onResume() {
        super.onResume();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), this.fragmentName, "onResume()"));
    }

    public void onPause() {
        super.onPause();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), this.fragmentName, "onPause()"));
    }

    public void onStop() {
        super.onStop();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), this.fragmentName, "onStop()"));
    }

    public void onDestroyView() {
        super.onDestroyView();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), this.fragmentName, "onDestroyView()"));
    }

    public void onDestroy() {
        super.onDestroy();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), this.fragmentName, "onDestroy()"));
    }
}
