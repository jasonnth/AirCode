package com.roughike.bottombar;

import android.os.Build.VERSION;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Interpolator;

class BottomNavigationBehavior<V extends View> extends VerticalScrollingBehavior<V> {
    private static final Interpolator INTERPOLATOR = new LinearOutSlowInInterpolator();
    /* access modifiers changed from: private */
    public final int bottomNavHeight;
    /* access modifiers changed from: private */
    public final int defaultOffset;
    private boolean hidden = false;
    /* access modifiers changed from: private */
    public boolean isTablet = false;
    private boolean mScrollingEnabled;
    /* access modifiers changed from: private */
    public int mSnackbarHeight = -1;
    private ViewPropertyAnimatorCompat mTranslationAnimator;
    private final BottomNavigationWithSnackbar mWithSnackBarImpl;

    private interface BottomNavigationWithSnackbar {
        void updateSnackbar(CoordinatorLayout coordinatorLayout, View view, View view2);
    }

    private class LollipopBottomNavWithSnackBarImpl implements BottomNavigationWithSnackbar {
        private LollipopBottomNavWithSnackBarImpl() {
        }

        public void updateSnackbar(CoordinatorLayout parent, View dependency, View child) {
            if (!BottomNavigationBehavior.this.isTablet && (dependency instanceof SnackbarLayout)) {
                if (BottomNavigationBehavior.this.mSnackbarHeight == -1) {
                    BottomNavigationBehavior.this.mSnackbarHeight = dependency.getHeight();
                }
                if (ViewCompat.getTranslationY(child) == 0.0f) {
                    dependency.setPadding(dependency.getPaddingLeft(), dependency.getPaddingTop(), dependency.getPaddingRight(), (BottomNavigationBehavior.this.mSnackbarHeight + BottomNavigationBehavior.this.bottomNavHeight) - BottomNavigationBehavior.this.defaultOffset);
                }
            }
        }
    }

    private class PreLollipopBottomNavWithSnackBarImpl implements BottomNavigationWithSnackbar {
        private PreLollipopBottomNavWithSnackBarImpl() {
        }

        public void updateSnackbar(CoordinatorLayout parent, View dependency, View child) {
            if (!BottomNavigationBehavior.this.isTablet && (dependency instanceof SnackbarLayout)) {
                if (BottomNavigationBehavior.this.mSnackbarHeight == -1) {
                    BottomNavigationBehavior.this.mSnackbarHeight = dependency.getHeight();
                }
                if (ViewCompat.getTranslationY(child) == 0.0f) {
                    ((MarginLayoutParams) dependency.getLayoutParams()).bottomMargin = (BottomNavigationBehavior.this.bottomNavHeight + BottomNavigationBehavior.this.mSnackbarHeight) - BottomNavigationBehavior.this.defaultOffset;
                    child.bringToFront();
                    child.getParent().requestLayout();
                    if (VERSION.SDK_INT < 19) {
                        ((View) child.getParent()).invalidate();
                    }
                }
            }
        }
    }

    BottomNavigationBehavior(int bottomNavHeight2, int defaultOffset2, boolean tablet) {
        this.mWithSnackBarImpl = VERSION.SDK_INT >= 21 ? new LollipopBottomNavWithSnackBarImpl() : new PreLollipopBottomNavWithSnackBarImpl();
        this.mScrollingEnabled = true;
        this.bottomNavHeight = bottomNavHeight2;
        this.defaultOffset = defaultOffset2;
        this.isTablet = tablet;
    }

    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
        this.mWithSnackBarImpl.updateSnackbar(parent, dependency, child);
        return dependency instanceof SnackbarLayout;
    }

    public void onNestedVerticalOverScroll(CoordinatorLayout coordinatorLayout, V v, int direction, int currentOverScroll, int totalOverScroll) {
    }

    public void onDependentViewRemoved(CoordinatorLayout parent, V child, View dependency) {
        updateScrollingForSnackbar(dependency, true);
        super.onDependentViewRemoved(parent, child, dependency);
    }

    private void updateScrollingForSnackbar(View dependency, boolean enabled) {
        if (!this.isTablet && (dependency instanceof SnackbarLayout)) {
            this.mScrollingEnabled = enabled;
        }
    }

    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
        updateScrollingForSnackbar(dependency, false);
        return super.onDependentViewChanged(parent, child, dependency);
    }

    public void onDirectionNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed, int scrollDirection) {
        handleDirection(child, scrollDirection);
    }

    private void handleDirection(V child, int scrollDirection) {
        if (this.mScrollingEnabled) {
            if (scrollDirection == -1 && this.hidden) {
                this.hidden = false;
                animateOffset(child, this.defaultOffset);
            } else if (scrollDirection == 1 && !this.hidden) {
                this.hidden = true;
                animateOffset(child, this.bottomNavHeight + this.defaultOffset);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onNestedDirectionFling(CoordinatorLayout coordinatorLayout, V child, View target, float velocityX, float velocityY, int scrollDirection) {
        handleDirection(child, scrollDirection);
        return true;
    }

    private void animateOffset(V child, int offset) {
        ensureOrCancelAnimator(child);
        this.mTranslationAnimator.translationY((float) offset).start();
    }

    private void ensureOrCancelAnimator(V child) {
        if (this.mTranslationAnimator == null) {
            this.mTranslationAnimator = ViewCompat.animate(child);
            this.mTranslationAnimator.setDuration(300);
            this.mTranslationAnimator.setInterpolator(INTERPOLATOR);
            return;
        }
        this.mTranslationAnimator.cancel();
    }
}
