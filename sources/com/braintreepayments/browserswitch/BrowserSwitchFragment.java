package com.braintreepayments.browserswitch;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;
import p005cn.jpush.android.JPushConstants.PushActivity;

public abstract class BrowserSwitchFragment extends Fragment {
    protected Context mContext;
    protected int mRequestCode;

    public enum BrowserSwitchResult {
        OK,
        CANCELED,
        ERROR;
        
        private String mErrorMessage;

        public String getErrorMessage() {
            return this.mErrorMessage;
        }

        /* access modifiers changed from: private */
        public BrowserSwitchResult setErrorMessage(String errorMessage) {
            this.mErrorMessage = errorMessage;
            return this;
        }

        public String toString() {
            return name() + " " + getErrorMessage();
        }
    }

    public abstract void onBrowserSwitchResult(int i, BrowserSwitchResult browserSwitchResult, Uri uri);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mContext == null) {
            this.mContext = getActivity().getApplicationContext();
        }
        if (savedInstanceState != null) {
            this.mRequestCode = savedInstanceState.getInt("com.braintreepayments.browserswitch.EXTRA_REQUEST_CODE");
        } else {
            this.mRequestCode = Integer.MIN_VALUE;
        }
    }

    public void onResume() {
        super.onResume();
        if (isBrowserSwitching()) {
            Uri returnUri = BrowserSwitchActivity.getReturnUri();
            int requestCode = this.mRequestCode;
            this.mRequestCode = Integer.MIN_VALUE;
            BrowserSwitchActivity.clearReturnUri();
            if (returnUri != null) {
                onBrowserSwitchResult(requestCode, BrowserSwitchResult.OK, returnUri);
            } else {
                onBrowserSwitchResult(requestCode, BrowserSwitchResult.CANCELED, null);
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("com.braintreepayments.browserswitch.EXTRA_REQUEST_CODE", this.mRequestCode);
    }

    public String getReturnUrlScheme() {
        return this.mContext.getPackageName().toLowerCase().replace("_", "") + ".browserswitch";
    }

    public void browserSwitch(int requestCode, Intent intent) {
        if (requestCode == Integer.MIN_VALUE) {
            onBrowserSwitchResult(requestCode, BrowserSwitchResult.ERROR.setErrorMessage("Request code cannot be Integer.MIN_VALUE"), null);
        } else if (!isReturnUrlSetup()) {
            onBrowserSwitchResult(requestCode, BrowserSwitchResult.ERROR.setErrorMessage("The return url scheme was not set up, incorrectly set up, or more than one Activity on this device defines the same url scheme in it's Android Manifest. See https://github.com/braintree/browser-switch-android for more information on setting up a return url scheme."), null);
        } else {
            this.mRequestCode = requestCode;
            this.mContext.startActivity(intent);
        }
    }

    private boolean isBrowserSwitching() {
        return this.mRequestCode != Integer.MIN_VALUE;
    }

    private boolean isReturnUrlSetup() {
        List<ResolveInfo> activities = this.mContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW").setData(Uri.parse(getReturnUrlScheme() + "://")).addCategory(PushActivity.CATEGORY_1).addCategory("android.intent.category.BROWSABLE"), 0);
        if (activities == null || activities.size() != 1) {
            return false;
        }
        return true;
    }
}
