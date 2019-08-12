package com.paypal.android.sdk.onetouch.core.config;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.braintreepayments.api.internal.AppHelper;
import com.braintreepayments.browserswitch.ChromeCustomTabs;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.Protocol;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import com.paypal.android.sdk.onetouch.core.sdk.AppSwitchHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public abstract class Recipe<T extends Recipe<T>> {
    private Protocol mProtocol;
    private Collection<String> mSupportedLocales = new HashSet();
    private RequestTarget mTarget;
    private String mTargetIntentAction;
    private List<String> mTargetPackagesInReversePriorityOrder = new ArrayList();

    /* access modifiers changed from: protected */
    public abstract T getThis();

    public T target(RequestTarget target) {
        this.mTarget = target;
        return getThis();
    }

    public T protocol(String protocol) {
        this.mProtocol = Protocol.getProtocol(protocol);
        return getThis();
    }

    public T targetPackage(String singleTargetPackage) {
        this.mTargetPackagesInReversePriorityOrder.add(singleTargetPackage);
        return getThis();
    }

    public List<String> getTargetPackagesInReversePriorityOrder() {
        return new ArrayList(this.mTargetPackagesInReversePriorityOrder);
    }

    public T supportedLocale(String supportedLocale) {
        this.mSupportedLocales.add(supportedLocale);
        return getThis();
    }

    public T targetIntentAction(String targetIntentAction) {
        this.mTargetIntentAction = targetIntentAction;
        return getThis();
    }

    public String getTargetIntentAction() {
        return this.mTargetIntentAction;
    }

    public RequestTarget getTarget() {
        return this.mTarget;
    }

    public boolean isValidAppTarget(Context context) {
        boolean isLocaleAllowed;
        for (String allowedWalletTarget : getTargetPackagesInReversePriorityOrder()) {
            boolean isIntentAvailable = AppHelper.isIntentAvailable(context, AppSwitchHelper.createBaseIntent(getTargetIntentAction(), allowedWalletTarget));
            String locale = Locale.getDefault().toString();
            if (this.mSupportedLocales.isEmpty() || this.mSupportedLocales.contains(locale)) {
                isLocaleAllowed = true;
            } else {
                isLocaleAllowed = false;
            }
            boolean isSignatureValid = AppSwitchHelper.isSignatureValid(context, allowedWalletTarget);
            if (isIntentAvailable && isLocaleAllowed && isSignatureValid) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidBrowserTarget(Context context, String browserSwitchUrl) {
        for (String allowedBrowserPackage : getTargetPackagesInReversePriorityOrder()) {
            if (isValidBrowserTarget(context, browserSwitchUrl, allowedBrowserPackage)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidBrowserTarget(Context context, String browserSwitchUrl, String allowedBrowserPackage) {
        return getBrowserIntent(context, browserSwitchUrl, allowedBrowserPackage).resolveActivity(context.getPackageManager()) != null;
    }

    public static Intent getBrowserIntent(Context context, String browserSwitchUrl, String allowedBrowserPackage) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(browserSwitchUrl)).addFlags(268435456);
        if (!"*".equals(allowedBrowserPackage)) {
            intent.setPackage(allowedBrowserPackage);
        }
        return ChromeCustomTabs.addChromeCustomTabsExtras(context, intent);
    }

    public Protocol getProtocol() {
        return this.mProtocol;
    }
}
