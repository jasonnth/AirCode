package com.facebook.accountkit.p029ui;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* renamed from: com.facebook.accountkit.ui.KeyboardObserver */
final class KeyboardObserver {
    private static final int MINIMUM_KEYBOARD_HEIGHT_DP = ((VERSION.SDK_INT >= 21 ? 48 : 0) + 100);
    private boolean didCalculateVisibleFrame = false;
    private final Rect lastRootViewVisibleFrame = new Rect();
    private final Rect lastViewVisibleFrame = new Rect();
    private OnVisibleFrameChangedListener onVisibleFrameChangedListener;
    private final Rect rootViewVisibleFrame = new Rect();

    /* renamed from: com.facebook.accountkit.ui.KeyboardObserver$OnVisibleFrameChangedListener */
    public interface OnVisibleFrameChangedListener {
        void onVisibleFrameChanged(Rect rect);
    }

    public KeyboardObserver(View view) {
        configureGlobalObserver(view);
    }

    public void setOnVisibleFrameChangedListener(OnVisibleFrameChangedListener onVisibleFrameChangedListener2) {
        this.onVisibleFrameChangedListener = onVisibleFrameChangedListener2;
        if (this.didCalculateVisibleFrame && onVisibleFrameChangedListener2 != null) {
            onVisibleFrameChangedListener2.onVisibleFrameChanged(this.lastViewVisibleFrame);
        }
    }

    private void configureGlobalObserver(final View view) {
        if (view != null) {
            final View rootView = view.getRootView();
            if (rootView != null) {
                rootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        KeyboardObserver.this.checkVisibleFrame(view, rootView);
                    }
                });
                checkVisibleFrame(view, rootView);
            }
        }
    }

    /* access modifiers changed from: private */
    public void checkVisibleFrame(View view, View rootView) {
        int minimumKeyboardHeight = ViewUtility.getDimensionPixelSize(rootView.getContext(), MINIMUM_KEYBOARD_HEIGHT_DP);
        rootView.getWindowVisibleDisplayFrame(this.rootViewVisibleFrame);
        if ((rootView.getHeight() - (this.rootViewVisibleFrame.bottom - this.rootViewVisibleFrame.top) >= minimumKeyboardHeight) && !this.rootViewVisibleFrame.equals(this.lastRootViewVisibleFrame)) {
            this.lastRootViewVisibleFrame.set(this.rootViewVisibleFrame);
            view.getGlobalVisibleRect(this.lastViewVisibleFrame);
            this.didCalculateVisibleFrame = true;
            if (this.onVisibleFrameChangedListener != null) {
                this.onVisibleFrameChangedListener.onVisibleFrameChanged(this.lastViewVisibleFrame);
            }
        }
    }
}
