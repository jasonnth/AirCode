package com.appboy.p028ui.inappmessage;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.appboy.Constants;
import com.appboy.enums.inappmessage.DismissType;
import com.appboy.enums.inappmessage.SlideFrom;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageImmersive;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.models.MessageButton;
import com.appboy.p028ui.inappmessage.listeners.IInAppMessageViewLifecycleListener;
import com.appboy.p028ui.inappmessage.listeners.SimpleSwipeDismissTouchListener;
import com.appboy.p028ui.inappmessage.listeners.SwipeDismissTouchListener.DismissCallbacks;
import com.appboy.p028ui.inappmessage.listeners.TouchAwareSwipeDismissTouchListener;
import com.appboy.p028ui.inappmessage.listeners.TouchAwareSwipeDismissTouchListener.ITouchListener;
import com.appboy.p028ui.inappmessage.views.AppboyInAppMessageHtmlBaseView;
import com.appboy.p028ui.support.AnimationUtils;
import com.appboy.p028ui.support.ViewUtils;
import com.appboy.support.AppboyLogger;
import java.util.List;

/* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper */
public class InAppMessageViewWrapper implements IInAppMessageViewWrapper {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, InAppMessageViewWrapper.class.getName()});
    /* access modifiers changed from: private */
    public List<View> mButtons;
    private View mClickableInAppMessageView;
    private View mCloseButton;
    private final Animation mClosingAnimation;
    /* access modifiers changed from: private */
    public Runnable mDismissRunnable;
    /* access modifiers changed from: private */
    public final IInAppMessage mInAppMessage;
    /* access modifiers changed from: private */
    public final View mInAppMessageView;
    /* access modifiers changed from: private */
    public final IInAppMessageViewLifecycleListener mInAppMessageViewLifecycleListener;
    private boolean mIsAnimatingClose;
    private final Animation mOpeningAnimation;

    public InAppMessageViewWrapper(View inAppMessageView, IInAppMessage inAppMessage, IInAppMessageViewLifecycleListener inAppMessageViewLifecycleListener, Animation openingAnimation, Animation closingAnimation, View clickableInAppMessageView) {
        this.mInAppMessageView = inAppMessageView;
        this.mInAppMessage = inAppMessage;
        this.mInAppMessageViewLifecycleListener = inAppMessageViewLifecycleListener;
        this.mIsAnimatingClose = false;
        if (clickableInAppMessageView != null) {
            this.mClickableInAppMessageView = clickableInAppMessageView;
        } else {
            this.mClickableInAppMessageView = this.mInAppMessageView;
        }
        if (VERSION.SDK_INT >= 12 && (this.mInAppMessage instanceof InAppMessageSlideup)) {
            TouchAwareSwipeDismissTouchListener touchAwareSwipeListener = new TouchAwareSwipeDismissTouchListener(inAppMessageView, null, createDismissCallbacks());
            touchAwareSwipeListener.setTouchListener(createTouchAwareListener());
            this.mClickableInAppMessageView.setOnTouchListener(touchAwareSwipeListener);
        } else if (this.mInAppMessage instanceof InAppMessageSlideup) {
            this.mClickableInAppMessageView.setOnTouchListener(getSimpleSwipeListener());
        }
        this.mOpeningAnimation = openingAnimation;
        this.mClosingAnimation = closingAnimation;
        this.mClickableInAppMessageView.setOnClickListener(createClickListener());
    }

    public InAppMessageViewWrapper(View inAppMessageView, IInAppMessage inAppMessage, IInAppMessageViewLifecycleListener inAppMessageViewLifecycleListener, Animation openingAnimation, Animation closingAnimation, View clickableInAppMessageView, List<View> buttons, View closeButton) {
        this(inAppMessageView, inAppMessage, inAppMessageViewLifecycleListener, openingAnimation, closingAnimation, clickableInAppMessageView);
        if (closeButton != null) {
            this.mCloseButton = closeButton;
            this.mCloseButton.setOnClickListener(createCloseInAppMessageClickListener());
        }
        if (buttons != null) {
            this.mButtons = buttons;
            for (View button : this.mButtons) {
                button.setOnClickListener(createButtonClickListener());
            }
        }
    }

    public void open(Activity activity) {
        final FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView().findViewById(16908290);
        int frameLayoutHeight = frameLayout.getHeight();
        final int displayHeight = ViewUtils.getDisplayHeight(activity);
        if (frameLayoutHeight == 0) {
            ViewTreeObserver viewTreeObserver = frameLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        AppboyLogger.m1733d(InAppMessageViewWrapper.TAG, String.format("Detected root view height of %d, display height of %d in onGlobalLayout", new Object[]{Integer.valueOf(frameLayout.getHeight()), Integer.valueOf(displayHeight)}));
                        frameLayout.removeView(InAppMessageViewWrapper.this.mInAppMessageView);
                        InAppMessageViewWrapper.this.open(frameLayout, displayHeight);
                        ViewUtils.removeOnGlobalLayoutListenerSafe(frameLayout.getViewTreeObserver(), this);
                    }
                });
                return;
            }
            return;
        }
        AppboyLogger.m1733d(TAG, String.format("Detected root view height of %d, display height of %d", new Object[]{Integer.valueOf(frameLayoutHeight), Integer.valueOf(displayHeight)}));
        open(frameLayout, displayHeight);
    }

    /* access modifiers changed from: private */
    public void open(FrameLayout frameLayout, int displayHeight) {
        this.mInAppMessageViewLifecycleListener.beforeOpened(this.mInAppMessageView, this.mInAppMessage);
        AppboyLogger.m1733d(TAG, "Adding In-app message view to root FrameLayout.");
        frameLayout.addView(this.mInAppMessageView, getLayoutParams(frameLayout, displayHeight));
        if (this.mInAppMessage.getAnimateIn()) {
            AppboyLogger.m1733d(TAG, "In-app message view will animate into the visible area.");
            setAndStartAnimation(true);
            return;
        }
        AppboyLogger.m1733d(TAG, "In-app message view will be placed instantly into the visible area.");
        if (this.mInAppMessage.getDismissType() == DismissType.AUTO_DISMISS) {
            addDismissRunnable();
        }
        this.mInAppMessageView.setFocusableInTouchMode(true);
        this.mInAppMessageView.requestFocus();
        announceForAccessibilityIfNecessary();
        this.mInAppMessageViewLifecycleListener.afterOpened(this.mInAppMessageView, this.mInAppMessage);
    }

    /* access modifiers changed from: private */
    public void announceForAccessibilityIfNecessary() {
        if (VERSION.SDK_INT <= 16) {
            return;
        }
        if (this.mInAppMessageView instanceof IInAppMessageImmersiveView) {
            this.mInAppMessageView.announceForAccessibility(this.mInAppMessage.getMessage());
        } else if (this.mInAppMessageView instanceof AppboyInAppMessageHtmlBaseView) {
            this.mInAppMessageView.announceForAccessibility("In-app message displayed.");
        }
    }

    private LayoutParams getLayoutParams(FrameLayout frameLayout, int displayHeight) {
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        if (this.mInAppMessage instanceof InAppMessageSlideup) {
            layoutParams.gravity = ((InAppMessageSlideup) this.mInAppMessage).getSlideFrom() == SlideFrom.TOP ? 48 : 80;
        }
        if (displayHeight > 0 && displayHeight == frameLayout.getHeight()) {
            int topVisibleCoordinate = ViewUtils.getTopVisibleCoordinate(frameLayout);
            AppboyLogger.m1733d(TAG, String.format("Detected status bar height of %d.", new Object[]{Integer.valueOf(topVisibleCoordinate)}));
            layoutParams.setMargins(0, topVisibleCoordinate, 0, 0);
        }
        return layoutParams;
    }

    public void close() {
        this.mInAppMessageView.removeCallbacks(this.mDismissRunnable);
        this.mInAppMessageViewLifecycleListener.beforeClosed(this.mInAppMessageView, this.mInAppMessage);
        if (this.mInAppMessage.getAnimateOut()) {
            this.mIsAnimatingClose = true;
            setAndStartAnimation(false);
            return;
        }
        ViewUtils.removeViewFromParent(this.mInAppMessageView);
        this.mInAppMessageViewLifecycleListener.afterClosed(this.mInAppMessage);
    }

    public View getInAppMessageView() {
        return this.mInAppMessageView;
    }

    public IInAppMessage getInAppMessage() {
        return this.mInAppMessage;
    }

    public boolean getIsAnimatingClose() {
        return this.mIsAnimatingClose;
    }

    private OnClickListener createClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                if (InAppMessageViewWrapper.this.mInAppMessage instanceof IInAppMessageImmersive) {
                    IInAppMessageImmersive inAppMessageImmersive = (IInAppMessageImmersive) InAppMessageViewWrapper.this.mInAppMessage;
                    if (inAppMessageImmersive.getMessageButtons() == null || inAppMessageImmersive.getMessageButtons().size() == 0) {
                        InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
                        return;
                    }
                    return;
                }
                InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            }
        };
    }

    private OnClickListener createButtonClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                IInAppMessageImmersive inAppMessageImmersive = (IInAppMessageImmersive) InAppMessageViewWrapper.this.mInAppMessage;
                for (int i = 0; i < InAppMessageViewWrapper.this.mButtons.size(); i++) {
                    if (view.getId() == ((View) InAppMessageViewWrapper.this.mButtons.get(i)).getId()) {
                        InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onButtonClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), (MessageButton) inAppMessageImmersive.getMessageButtons().get(i), inAppMessageImmersive);
                        return;
                    }
                }
            }
        };
    }

    private OnClickListener createCloseInAppMessageClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
            }
        };
    }

    /* access modifiers changed from: private */
    public void addDismissRunnable() {
        if (this.mDismissRunnable == null) {
            this.mDismissRunnable = new Runnable() {
                public void run() {
                    AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
                }
            };
            this.mInAppMessageView.postDelayed(this.mDismissRunnable, (long) this.mInAppMessage.getDurationInMilliseconds());
        }
    }

    private DismissCallbacks createDismissCallbacks() {
        return new DismissCallbacks() {
            public boolean canDismiss(Object token) {
                return true;
            }

            public void onDismiss(View view, Object token) {
                InAppMessageViewWrapper.this.mInAppMessage.setAnimateOut(false);
                AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
            }
        };
    }

    private ITouchListener createTouchAwareListener() {
        return new ITouchListener() {
            public void onTouchStartedOrContinued() {
                InAppMessageViewWrapper.this.mInAppMessageView.removeCallbacks(InAppMessageViewWrapper.this.mDismissRunnable);
            }

            public void onTouchEnded() {
                if (InAppMessageViewWrapper.this.mInAppMessage.getDismissType() == DismissType.AUTO_DISMISS) {
                    InAppMessageViewWrapper.this.addDismissRunnable();
                }
            }
        };
    }

    private void setAndStartAnimation(boolean opening) {
        Animation animation;
        if (opening) {
            animation = this.mOpeningAnimation;
        } else {
            animation = this.mClosingAnimation;
        }
        animation.setAnimationListener(createAnimationListener(opening));
        this.mInAppMessageView.clearAnimation();
        this.mInAppMessageView.setAnimation(animation);
        animation.startNow();
        this.mInAppMessageView.invalidate();
    }

    private AnimationListener createAnimationListener(boolean opening) {
        return opening ? new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (InAppMessageViewWrapper.this.mInAppMessage.getDismissType() == DismissType.AUTO_DISMISS) {
                    InAppMessageViewWrapper.this.addDismissRunnable();
                }
                AppboyLogger.m1733d(InAppMessageViewWrapper.TAG, "In-app message animated into view.");
                InAppMessageViewWrapper.this.mInAppMessageView.setFocusableInTouchMode(true);
                InAppMessageViewWrapper.this.mInAppMessageView.requestFocus();
                InAppMessageViewWrapper.this.announceForAccessibilityIfNecessary();
                InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.afterOpened(InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        } : new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                InAppMessageViewWrapper.this.mInAppMessageView.clearAnimation();
                InAppMessageViewWrapper.this.mInAppMessageView.setVisibility(8);
                ViewUtils.removeViewFromParent(InAppMessageViewWrapper.this.mInAppMessageView);
                InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.afterClosed(InAppMessageViewWrapper.this.mInAppMessage);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        };
    }

    private SimpleSwipeDismissTouchListener getSimpleSwipeListener() {
        return new SimpleSwipeDismissTouchListener(this.mInAppMessageView.getContext()) {
            private final long sSwipeAnimationDurationMillis = 400;

            public void onSwipeLeft() {
                animateAndClose(AnimationUtils.createHorizontalAnimation(0.0f, -1.0f, 400, false));
            }

            public void onSwipeRight() {
                animateAndClose(AnimationUtils.createHorizontalAnimation(0.0f, 1.0f, 400, false));
            }

            private void animateAndClose(Animation animation) {
                InAppMessageViewWrapper.this.mInAppMessageView.clearAnimation();
                InAppMessageViewWrapper.this.mInAppMessageView.setAnimation(animation);
                animation.startNow();
                InAppMessageViewWrapper.this.mInAppMessageView.invalidate();
                InAppMessageViewWrapper.this.mInAppMessage.setAnimateOut(false);
                AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
            }
        };
    }
}
