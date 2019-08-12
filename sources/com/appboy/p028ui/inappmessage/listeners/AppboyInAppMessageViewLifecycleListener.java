package com.appboy.p028ui.inappmessage.listeners;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import com.appboy.Constants;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageHtml;
import com.appboy.models.IInAppMessageImmersive;
import com.appboy.models.MessageButton;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageManager;
import com.appboy.p028ui.inappmessage.InAppMessageCloser;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.BundleUtils;
import com.appboy.support.WebContentUtils;

/* renamed from: com.appboy.ui.inappmessage.listeners.AppboyInAppMessageViewLifecycleListener */
public class AppboyInAppMessageViewLifecycleListener implements IInAppMessageViewLifecycleListener {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyInAppMessageViewLifecycleListener.class.getName()});

    public void beforeOpened(View inAppMessageView, IInAppMessage inAppMessage) {
        AppboyLogger.m1733d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.beforeOpened called.");
        inAppMessage.logImpression();
    }

    public void afterOpened(View inAppMessageView, IInAppMessage inAppMessage) {
        AppboyLogger.m1733d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.afterOpened called.");
    }

    public void beforeClosed(View inAppMessageView, IInAppMessage inAppMessage) {
        AppboyLogger.m1733d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.beforeClosed called.");
    }

    public void afterClosed(IInAppMessage inAppMessage) {
        AppboyLogger.m1733d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.afterClosed called.");
        getInAppMessageManager().resetAfterInAppMessageClose();
        if (inAppMessage instanceof IInAppMessageHtml) {
            startClearHtmlInAppMessageAssetsThread();
        }
        inAppMessage.onAfterClosed();
    }

    public void onClicked(InAppMessageCloser inAppMessageCloser, View inAppMessageView, IInAppMessage inAppMessage) {
        AppboyLogger.m1733d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onClicked called.");
        inAppMessage.logClick();
        if (!getInAppMessageManager().getInAppMessageManagerListener().onInAppMessageClicked(inAppMessage, inAppMessageCloser)) {
            performInAppMessageClicked(inAppMessage, inAppMessageCloser);
        }
    }

    public void onButtonClicked(InAppMessageCloser inAppMessageCloser, MessageButton messageButton, IInAppMessageImmersive inAppMessageImmersive) {
        AppboyLogger.m1733d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onButtonClicked called.");
        inAppMessageImmersive.logButtonClick(messageButton);
        if (!getInAppMessageManager().getInAppMessageManagerListener().onInAppMessageButtonClicked(messageButton, inAppMessageCloser)) {
            performInAppMessageButtonClicked(messageButton, inAppMessageImmersive, inAppMessageCloser);
        }
    }

    public void onDismissed(View inAppMessageView, IInAppMessage inAppMessage) {
        AppboyLogger.m1733d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onDismissed called.");
        getInAppMessageManager().getInAppMessageManagerListener().onInAppMessageDismissed(inAppMessage);
    }

    private void performInAppMessageButtonClicked(MessageButton messageButton, IInAppMessage inAppMessage, InAppMessageCloser inAppMessageCloser) {
        performClickAction(messageButton.getClickAction(), inAppMessage, inAppMessageCloser, messageButton.getUri());
    }

    private void performInAppMessageClicked(IInAppMessage inAppMessage, InAppMessageCloser inAppMessageCloser) {
        performClickAction(inAppMessage.getClickAction(), inAppMessage, inAppMessageCloser, inAppMessage.getUri());
    }

    private void performClickAction(ClickAction clickAction, IInAppMessage inAppMessage, InAppMessageCloser inAppMessageCloser, Uri clickUri) {
        if (getInAppMessageManager().getActivity() == null) {
            AppboyLogger.m1739w(TAG, "Can't perform click action because the cached activity is null.");
            return;
        }
        switch (clickAction) {
            case NEWS_FEED:
                inAppMessageCloser.close(false);
                getInAppMessageManager().getAppboyNavigator().gotoNewsFeed(getInAppMessageManager().getActivity(), BundleUtils.mapToBundle(inAppMessage.getExtras()));
                return;
            case URI:
                inAppMessageCloser.close(false);
                getInAppMessageManager().getAppboyNavigator().gotoURI(getInAppMessageManager().getActivity(), clickUri, BundleUtils.mapToBundle(inAppMessage.getExtras()));
                return;
            case NONE:
                inAppMessageCloser.close(inAppMessage.getAnimateOut());
                return;
            default:
                inAppMessageCloser.close(false);
                return;
        }
    }

    private AppboyInAppMessageManager getInAppMessageManager() {
        return AppboyInAppMessageManager.getInstance();
    }

    private void startClearHtmlInAppMessageAssetsThread() {
        new Thread(new Runnable() {
            public void run() {
                Activity inAppMessageActivity = AppboyInAppMessageManager.getInstance().getActivity();
                if (inAppMessageActivity != null) {
                    AppboyFileUtils.deleteFileOrDirectory(WebContentUtils.getHtmlInAppMessageAssetCacheDirectory(inAppMessageActivity));
                }
            }
        }).start();
    }
}
