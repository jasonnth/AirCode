package com.appboy.p028ui.inappmessage;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.IAppboyNavigator;
import com.appboy.enums.inappmessage.Orientation;
import com.appboy.events.IEventSubscriber;
import com.appboy.events.InAppMessageEvent;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageFull;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.models.InAppMessageModal;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.p028ui.AppboyNavigator;
import com.appboy.p028ui.inappmessage.factories.AppboyFullViewFactory;
import com.appboy.p028ui.inappmessage.factories.AppboyHtmlFullViewFactory;
import com.appboy.p028ui.inappmessage.factories.AppboyInAppMessageAnimationFactory;
import com.appboy.p028ui.inappmessage.factories.AppboyModalViewFactory;
import com.appboy.p028ui.inappmessage.factories.AppboySlideupViewFactory;
import com.appboy.p028ui.inappmessage.listeners.AppboyDefaultHtmlInAppMessageActionListener;
import com.appboy.p028ui.inappmessage.listeners.AppboyDefaultInAppMessageManagerListener;
import com.appboy.p028ui.inappmessage.listeners.AppboyInAppMessageViewLifecycleListener;
import com.appboy.p028ui.inappmessage.listeners.AppboyInAppMessageWebViewClientListener;
import com.appboy.p028ui.inappmessage.listeners.IHtmlInAppMessageActionListener;
import com.appboy.p028ui.inappmessage.listeners.IInAppMessageManagerListener;
import com.appboy.p028ui.inappmessage.listeners.IInAppMessageViewLifecycleListener;
import com.appboy.p028ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.p028ui.support.ViewUtils;
import com.appboy.support.AppboyLogger;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager */
public final class AppboyInAppMessageManager {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyInAppMessageManager.class.getName()});
    private static volatile AppboyInAppMessageManager sInstance = null;
    private Activity mActivity;
    private Context mApplicationContext;
    private IInAppMessage mCarryoverInAppMessage;
    private IHtmlInAppMessageActionListener mCustomHtmlInAppMessageActionListener;
    private IInAppMessageAnimationFactory mCustomInAppMessageAnimationFactory;
    private IInAppMessageManagerListener mCustomInAppMessageManagerListener;
    private IInAppMessageViewFactory mCustomInAppMessageViewFactory;
    private final IAppboyNavigator mDefaultAppboyNavigator = new AppboyNavigator();
    private IHtmlInAppMessageActionListener mDefaultHtmlInAppMessageActionListener = new AppboyDefaultHtmlInAppMessageActionListener();
    private IInAppMessageManagerListener mDefaultInAppMessageManagerListener = new AppboyDefaultInAppMessageManagerListener();
    private AtomicBoolean mDisplayingInAppMessage = new AtomicBoolean(false);
    private IInAppMessageAnimationFactory mInAppMessageAnimationFactory = new AppboyInAppMessageAnimationFactory();
    private IEventSubscriber<InAppMessageEvent> mInAppMessageEventSubscriber;
    private IInAppMessageViewFactory mInAppMessageFullViewFactory = new AppboyFullViewFactory();
    private IInAppMessageViewFactory mInAppMessageHtmlFullViewFactory = new AppboyHtmlFullViewFactory(this.mInAppMessageWebViewClientListener);
    private IInAppMessageViewFactory mInAppMessageModalViewFactory = new AppboyModalViewFactory();
    private IInAppMessageViewFactory mInAppMessageSlideupViewFactory = new AppboySlideupViewFactory();
    private final Stack<IInAppMessage> mInAppMessageStack = new Stack<>();
    private final IInAppMessageViewLifecycleListener mInAppMessageViewLifecycleListener = new AppboyInAppMessageViewLifecycleListener();
    private IInAppMessageViewWrapper mInAppMessageViewWrapper;
    private final IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener = new AppboyInAppMessageWebViewClientListener();
    private Integer mOriginalOrientation;
    private IInAppMessage mUnRegisteredInAppMessage;

    public static AppboyInAppMessageManager getInstance() {
        if (sInstance == null) {
            synchronized (AppboyInAppMessageManager.class) {
                if (sInstance == null) {
                    sInstance = new AppboyInAppMessageManager();
                }
            }
        }
        return sInstance;
    }

    public void ensureSubscribedToInAppMessageEvents(Context context) {
        if (this.mInAppMessageEventSubscriber == null) {
            AppboyLogger.m1733d(TAG, "Subscribing in-app message event subscriber");
            this.mInAppMessageEventSubscriber = createInAppMessageEventSubscriber();
            Appboy.getInstance(context).subscribeToNewInAppMessages(this.mInAppMessageEventSubscriber);
        }
    }

    public void registerInAppMessageManager(Activity activity) {
        AppboyLogger.m1733d(TAG, "registerInAppMessageManager called");
        this.mActivity = activity;
        if (this.mActivity != null && this.mApplicationContext == null) {
            this.mApplicationContext = this.mActivity.getApplicationContext();
        }
        if (this.mCarryoverInAppMessage != null) {
            AppboyLogger.m1733d(TAG, "Requesting display of carryover in-app message.");
            this.mCarryoverInAppMessage.setAnimateIn(false);
            displayInAppMessage(this.mCarryoverInAppMessage, true);
            this.mCarryoverInAppMessage = null;
        } else if (this.mUnRegisteredInAppMessage != null) {
            AppboyLogger.m1733d(TAG, "Adding previously unregistered in-app message.");
            addInAppMessage(this.mUnRegisteredInAppMessage);
            this.mUnRegisteredInAppMessage = null;
        }
        ensureSubscribedToInAppMessageEvents(this.mApplicationContext);
    }

    public void unregisterInAppMessageManager(Activity activity) {
        AppboyLogger.m1733d(TAG, "unregisterInAppMessageManager called");
        if (this.mInAppMessageViewWrapper != null) {
            ViewUtils.removeViewFromParent(this.mInAppMessageViewWrapper.getInAppMessageView());
            if (this.mInAppMessageViewWrapper.getIsAnimatingClose()) {
                this.mInAppMessageViewLifecycleListener.afterClosed(this.mInAppMessageViewWrapper.getInAppMessage());
                this.mCarryoverInAppMessage = null;
            } else {
                this.mCarryoverInAppMessage = this.mInAppMessageViewWrapper.getInAppMessage();
            }
            this.mInAppMessageViewWrapper = null;
        } else {
            this.mCarryoverInAppMessage = null;
        }
        this.mActivity = null;
        this.mDisplayingInAppMessage.set(false);
    }

    public void addInAppMessage(IInAppMessage inAppMessage) {
        this.mInAppMessageStack.push(inAppMessage);
        requestDisplayInAppMessage();
    }

    public boolean requestDisplayInAppMessage() {
        try {
            if (this.mActivity == null) {
                if (!this.mInAppMessageStack.empty()) {
                    AppboyLogger.m1739w(TAG, "No activity is currently registered to receive in-app messages. Saving in-app message as unregistered in-app message. It will automatically be displayed when the next activity registers to receive in-app messages.");
                    this.mUnRegisteredInAppMessage = (IInAppMessage) this.mInAppMessageStack.pop();
                } else {
                    AppboyLogger.m1733d(TAG, "No activity is currently registered to receive in-app messages and the in-app message stack is empty. Doing nothing.");
                }
                return false;
            } else if (this.mDisplayingInAppMessage.get()) {
                AppboyLogger.m1733d(TAG, "A in-app message is currently being displayed. Ignoring request to display in-app message.");
                return false;
            } else if (this.mInAppMessageStack.isEmpty()) {
                AppboyLogger.m1733d(TAG, "The in-app message stack is empty. No in-app message will be displayed.");
                return false;
            } else {
                final IInAppMessage inAppMessage = (IInAppMessage) this.mInAppMessageStack.pop();
                switch (getInAppMessageManagerListener().beforeInAppMessageDisplayed(inAppMessage)) {
                    case DISPLAY_NOW:
                        AppboyLogger.m1733d(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISPLAY_NOW. The in-app message will be displayed.");
                        new Handler(this.mApplicationContext.getMainLooper()).post(new Runnable() {
                            public void run() {
                                new AppboyAsyncInAppMessageDisplayer().execute(new IInAppMessage[]{inAppMessage});
                            }
                        });
                        return true;
                    case DISPLAY_LATER:
                        AppboyLogger.m1733d(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISPLAY_LATER. The in-app message will be pushed back onto the stack.");
                        this.mInAppMessageStack.push(inAppMessage);
                        return false;
                    case DISCARD:
                        AppboyLogger.m1733d(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISCARD. The in-app message will not be displayed and will not be put back on the stack.");
                        return false;
                    default:
                        AppboyLogger.m1735e(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned null instead of a InAppMessageOperation. Ignoring the in-app message. Please check the IInAppMessageStackBehaviour implementation.");
                        return false;
                }
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(TAG, "Error running requestDisplayInAppMessage", e);
            return false;
        }
    }

    public void hideCurrentlyDisplayingInAppMessage(boolean dismissed) {
        IInAppMessageViewWrapper inAppMessageWrapperView = this.mInAppMessageViewWrapper;
        if (inAppMessageWrapperView != null) {
            if (dismissed) {
                this.mInAppMessageViewLifecycleListener.onDismissed(inAppMessageWrapperView.getInAppMessageView(), inAppMessageWrapperView.getInAppMessage());
            }
            inAppMessageWrapperView.close();
        }
    }

    public IInAppMessageManagerListener getInAppMessageManagerListener() {
        return this.mCustomInAppMessageManagerListener != null ? this.mCustomInAppMessageManagerListener : this.mDefaultInAppMessageManagerListener;
    }

    public IHtmlInAppMessageActionListener getHtmlInAppMessageActionListener() {
        return this.mCustomHtmlInAppMessageActionListener != null ? this.mCustomHtmlInAppMessageActionListener : this.mDefaultHtmlInAppMessageActionListener;
    }

    public IAppboyNavigator getAppboyNavigator() {
        IAppboyNavigator customAppboyNavigator = Appboy.getInstance(this.mActivity).getAppboyNavigator();
        return customAppboyNavigator != null ? customAppboyNavigator : this.mDefaultAppboyNavigator;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public void resetAfterInAppMessageClose() {
        this.mInAppMessageViewWrapper = null;
        this.mDisplayingInAppMessage.set(false);
        if (this.mActivity != null && this.mOriginalOrientation != null) {
            this.mActivity.setRequestedOrientation(this.mOriginalOrientation.intValue());
            AppboyLogger.m1733d(TAG, "Setting requested orientation to original orientation " + this.mOriginalOrientation);
            this.mOriginalOrientation = null;
        }
    }

    private IInAppMessageAnimationFactory getInAppMessageAnimationFactory() {
        return this.mCustomInAppMessageAnimationFactory != null ? this.mCustomInAppMessageAnimationFactory : this.mInAppMessageAnimationFactory;
    }

    private IInAppMessageViewFactory getInAppMessageViewFactory(IInAppMessage inAppMessage) {
        if (this.mCustomInAppMessageViewFactory != null) {
            return this.mCustomInAppMessageViewFactory;
        }
        if (inAppMessage instanceof InAppMessageSlideup) {
            return this.mInAppMessageSlideupViewFactory;
        }
        if (inAppMessage instanceof InAppMessageModal) {
            return this.mInAppMessageModalViewFactory;
        }
        if (inAppMessage instanceof InAppMessageFull) {
            return this.mInAppMessageFullViewFactory;
        }
        if (inAppMessage instanceof InAppMessageHtmlFull) {
            return this.mInAppMessageHtmlFullViewFactory;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean displayInAppMessage(IInAppMessage inAppMessage, boolean isCarryOver) {
        if (!this.mDisplayingInAppMessage.compareAndSet(false, true)) {
            AppboyLogger.m1733d(TAG, "A in-app message is currently being displayed. Adding in-app message back on the stack.");
            this.mInAppMessageStack.push(inAppMessage);
            return false;
        }
        try {
            if (this.mActivity == null) {
                this.mCarryoverInAppMessage = inAppMessage;
                throw new Exception("No activity is currently registered to receive in-app messages. Registering in-app message as carry-over in-app message. It will automatically be displayed when the next activity registers to receive in-app messages.");
            }
            if (!isCarryOver) {
                long inAppMessageExpirationTimestamp = inAppMessage.getExpirationTimestamp();
                if (inAppMessageExpirationTimestamp > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis > inAppMessageExpirationTimestamp) {
                        throw new Exception(String.format("In-app message is expired. Doing nothing. Expiration: $%d. Current time: %d", new Object[]{Long.valueOf(inAppMessageExpirationTimestamp), Long.valueOf(currentTimeMillis)}));
                    }
                } else {
                    AppboyLogger.m1733d(TAG, "Expiration timestamp not defined. Continuing.");
                }
            } else {
                AppboyLogger.m1733d(TAG, "Not checking expiration status for carry-over in-app message.");
            }
            if (!verifyOrientationStatus(inAppMessage)) {
                throw new Exception("Current orientation did not match specified orientation for in-app message. Doing nothing.");
            }
            IInAppMessageViewFactory inAppMessageViewFactory = getInAppMessageViewFactory(inAppMessage);
            if (inAppMessageViewFactory == null) {
                throw new Exception("ViewFactory from getInAppMessageViewFactory was null.");
            }
            View inAppMessageView = inAppMessageViewFactory.createInAppMessageView(this.mActivity, inAppMessage);
            if (inAppMessageView == null) {
                throw new Exception("The in-app message view returned from the IInAppMessageViewFactory was null. The in-app message will not be displayed and will not be put back on the stack.");
            } else if (inAppMessageView.getParent() != null) {
                throw new Exception("The in-app message view returned from the IInAppMessageViewFactory already has a parent. This is a sign that the view is being reused. The IInAppMessageViewFactory method createInAppMessageViewmust return a new view without a parent. The in-app message will not be displayed and will not be put back on the stack.");
            } else {
                Animation openingAnimation = getInAppMessageAnimationFactory().getOpeningAnimation(inAppMessage);
                Animation closingAnimation = getInAppMessageAnimationFactory().getClosingAnimation(inAppMessage);
                if (inAppMessageView instanceof IInAppMessageImmersiveView) {
                    AppboyLogger.m1733d(TAG, "Creating view wrapper for immersive in-app message.");
                    IInAppMessageImmersiveView inAppMessageViewImmersive = (IInAppMessageImmersiveView) inAppMessageView;
                    this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(inAppMessageView, inAppMessage, this.mInAppMessageViewLifecycleListener, openingAnimation, closingAnimation, inAppMessageViewImmersive.getMessageClickableView(), inAppMessageViewImmersive.getMessageButtonViews(), inAppMessageViewImmersive.getMessageCloseButtonView());
                } else if (inAppMessageView instanceof IInAppMessageView) {
                    AppboyLogger.m1733d(TAG, "Creating view wrapper for base in-app message.");
                    IInAppMessage iInAppMessage = inAppMessage;
                    this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(inAppMessageView, iInAppMessage, this.mInAppMessageViewLifecycleListener, openingAnimation, closingAnimation, ((IInAppMessageView) inAppMessageView).getMessageClickableView());
                } else {
                    AppboyLogger.m1733d(TAG, "Creating view wrapper for in-app message.");
                    this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(inAppMessageView, inAppMessage, this.mInAppMessageViewLifecycleListener, openingAnimation, closingAnimation, inAppMessageView);
                }
                this.mInAppMessageViewWrapper.open(this.mActivity);
                return true;
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(TAG, "Could not display in-app message", e);
            resetAfterInAppMessageClose();
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean verifyOrientationStatus(IInAppMessage inAppMessage) {
        if (ViewUtils.isRunningOnTablet(this.mActivity)) {
            AppboyLogger.m1733d(TAG, "Running on tablet. In-app message can be displayed in any orientation.");
            return true;
        }
        Orientation preferredOrientation = inAppMessage.getOrientation();
        if (preferredOrientation == null) {
            AppboyLogger.m1733d(TAG, "No orientation specified. In-app message can be displayed in any orientation.");
            return true;
        } else if (preferredOrientation == Orientation.ANY) {
            AppboyLogger.m1733d(TAG, "Any orientation specified. In-app message can be displayed in any orientation.");
            return true;
        } else if (!currentOrientationIsValid(this.mActivity.getResources().getConfiguration().orientation, preferredOrientation)) {
            return false;
        } else {
            if (this.mOriginalOrientation != null) {
                return true;
            }
            AppboyLogger.m1733d(TAG, "Requesting orientation lock.");
            this.mOriginalOrientation = Integer.valueOf(this.mActivity.getRequestedOrientation());
            this.mActivity.setRequestedOrientation(14);
            return true;
        }
    }

    private boolean currentOrientationIsValid(int currentScreenOrientation, Orientation preferredOrientation) {
        if (currentScreenOrientation == 2 && preferredOrientation == Orientation.LANDSCAPE) {
            AppboyLogger.m1733d(TAG, "Current and preferred orientation are landscape.");
            return true;
        } else if (currentScreenOrientation == 1 && preferredOrientation == Orientation.PORTRAIT) {
            AppboyLogger.m1733d(TAG, "Current and preferred orientation are portrait.");
            return true;
        } else {
            AppboyLogger.m1733d(TAG, String.format("Current orientation %d and preferred orientation %s don't match", new Object[]{Integer.valueOf(currentScreenOrientation), preferredOrientation}));
            return false;
        }
    }

    private IEventSubscriber<InAppMessageEvent> createInAppMessageEventSubscriber() {
        return new IEventSubscriber<InAppMessageEvent>() {
            public void trigger(InAppMessageEvent event) {
                if (!AppboyInAppMessageManager.this.getInAppMessageManagerListener().onInAppMessageReceived(event.getInAppMessage())) {
                    AppboyInAppMessageManager.this.addInAppMessage(event.getInAppMessage());
                }
            }
        };
    }
}
