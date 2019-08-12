package com.appboy.p028ui.inappmessage.listeners;

import android.os.Bundle;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageHtml;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.p028ui.actions.ActionFactory;
import com.appboy.p028ui.actions.IAction;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageManager;
import com.appboy.push.AppboyNotificationUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.BundleUtils;
import com.appboy.support.StringUtils;

/* renamed from: com.appboy.ui.inappmessage.listeners.AppboyInAppMessageWebViewClientListener */
public class AppboyInAppMessageWebViewClientListener implements IInAppMessageWebViewClientListener {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyInAppMessageWebViewClientListener.class.getName()});

    public void onCloseAction(IInAppMessage inAppMessage, String url, Bundle queryBundle) {
        AppboyLogger.m1733d(TAG, "IInAppMessageWebViewClientListener.onCloseAction called.");
        logHtmlInAppMessageClick(inAppMessage, queryBundle);
        getInAppMessageManager().hideCurrentlyDisplayingInAppMessage(false);
        getInAppMessageManager().getHtmlInAppMessageActionListener().onCloseClicked(inAppMessage, url, queryBundle);
    }

    public void onNewsfeedAction(IInAppMessage inAppMessage, String url, Bundle queryBundle) {
        AppboyLogger.m1733d(TAG, "IInAppMessageWebViewClientListener.onNewsfeedAction called.");
        if (getInAppMessageManager().getActivity() == null) {
            AppboyLogger.m1739w(TAG, "Can't perform news feed action because the cached activity is null.");
            return;
        }
        logHtmlInAppMessageClick(inAppMessage, queryBundle);
        if (!getInAppMessageManager().getHtmlInAppMessageActionListener().onNewsfeedClicked(inAppMessage, url, queryBundle)) {
            inAppMessage.setAnimateOut(false);
            getInAppMessageManager().hideCurrentlyDisplayingInAppMessage(false);
            Bundle inAppMessageBundle = BundleUtils.mapToBundle(inAppMessage.getExtras());
            inAppMessageBundle.putAll(queryBundle);
            getInAppMessageManager().getAppboyNavigator().gotoNewsFeed(getInAppMessageManager().getActivity(), inAppMessageBundle);
        }
    }

    public void onCustomEventAction(IInAppMessage inAppMessage, String url, Bundle queryBundle) {
        AppboyLogger.m1733d(TAG, "IInAppMessageWebViewClientListener.onCustomEventAction called.");
        if (getInAppMessageManager().getActivity() == null) {
            AppboyLogger.m1739w(TAG, "Can't perform custom event action because the activity is null.");
        } else if (!getInAppMessageManager().getHtmlInAppMessageActionListener().onCustomEventFired(inAppMessage, url, queryBundle)) {
            String customEventName = parseCustomEventNameFromQueryBundle(queryBundle);
            if (!StringUtils.isNullOrBlank(customEventName)) {
                Appboy.getInstance(getInAppMessageManager().getActivity()).logCustomEvent(customEventName, parsePropertiesFromQueryBundle(queryBundle));
            }
        }
    }

    public void onOtherUrlAction(IInAppMessage inAppMessage, String url, Bundle queryBundle) {
        IAction urlAction;
        AppboyLogger.m1733d(TAG, "IInAppMessageWebViewClientListener.onOtherUrlAction called.");
        if (getInAppMessageManager().getActivity() == null) {
            AppboyLogger.m1739w(TAG, "Can't perform other url action because the cached activity is null.");
            return;
        }
        logHtmlInAppMessageClick(inAppMessage, queryBundle);
        if (!getInAppMessageManager().getHtmlInAppMessageActionListener().onOtherUrlAction(inAppMessage, url, queryBundle)) {
            inAppMessage.setAnimateOut(false);
            getInAppMessageManager().hideCurrentlyDisplayingInAppMessage(false);
            boolean doExternalOpen = false;
            if (queryBundle.containsKey("abExternalOpen")) {
                doExternalOpen = Boolean.parseBoolean(queryBundle.getString("abExternalOpen"));
            }
            if (doExternalOpen) {
                Bundle inAppMessageBundle = BundleUtils.mapToBundle(inAppMessage.getExtras());
                inAppMessageBundle.putAll(queryBundle);
                urlAction = ActionFactory.createViewUriAction(url, inAppMessageBundle);
            } else {
                urlAction = ActionFactory.createUriAction(getInAppMessageManager().getActivity(), url);
            }
            if (urlAction != null) {
                urlAction.execute(getInAppMessageManager().getActivity());
            }
        }
    }

    private AppboyInAppMessageManager getInAppMessageManager() {
        return AppboyInAppMessageManager.getInstance();
    }

    private void logHtmlInAppMessageClick(IInAppMessage inAppMessage, Bundle queryBundle) {
        if (queryBundle == null || !queryBundle.containsKey("abButtonId")) {
            inAppMessage.logClick();
        } else {
            ((IInAppMessageHtml) inAppMessage).logButtonClick(queryBundle.getString("abButtonId"));
        }
    }

    static String parseCustomEventNameFromQueryBundle(Bundle queryBundle) {
        return queryBundle.getString("name");
    }

    static AppboyProperties parsePropertiesFromQueryBundle(Bundle queryBundle) {
        AppboyProperties customEventProperties = new AppboyProperties();
        for (String key : queryBundle.keySet()) {
            if (!key.equals("name")) {
                String propertyValue = AppboyNotificationUtils.bundleOptString(queryBundle, key, null);
                if (!StringUtils.isNullOrBlank(propertyValue)) {
                    customEventProperties.addProperty(key, propertyValue);
                }
            }
        }
        return customEventProperties;
    }
}
