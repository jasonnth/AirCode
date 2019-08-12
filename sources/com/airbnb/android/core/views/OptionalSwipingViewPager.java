package com.airbnb.android.core.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.view.PagerAdapter;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.adapters.BaseTabFragmentPager;

public class OptionalSwipingViewPager extends ViewPager {
    private boolean isInNestedScroll;
    private OnPageChangeListener onPageChangeListener;
    private boolean swipingEnabled;
    private ViewPagerScrollInterface viewPagerScrollInterface;

    public interface ViewPagerScrollInterface {
        void onGestureComplete();

        boolean onNestedPreFling(View view, float f, float f2);

        void onNestedPreScroll(View view, int i, int i2, int[] iArr);
    }

    public OptionalSwipingViewPager(Context context) {
        super(context);
        init(context, null);
    }

    public OptionalSwipingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0716R.styleable.OptionalSwipingViewPager, 0, 0);
        this.swipingEnabled = a.getBoolean(C0716R.styleable.OptionalSwipingViewPager_swipingEnabled, true);
        a.recycle();
    }

    public void setViewPagerScrollInterface(ViewPagerScrollInterface viewPagerScrollInterface2) {
        this.viewPagerScrollInterface = viewPagerScrollInterface2;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (isSwipingDisabled()) {
            return false;
        }
        return super.onTouchEvent(event);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean z = false;
        if ((event.getAction() == 1 || event.getAction() == 3) && this.viewPagerScrollInterface != null) {
            this.viewPagerScrollInterface.onGestureComplete();
        }
        if (isSwipingDisabled()) {
            return z;
        }
        try {
            return super.onInterceptTouchEvent(event);
        } catch (IllegalArgumentException e) {
            BugsnagWrapper.notify((Throwable) e);
            return z;
        }
    }

    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        if (this.onPageChangeListener != null) {
            this.onPageChangeListener.onPageSelected(getCurrentItem());
        }
    }

    public void addOnPageChangeListener(OnPageChangeListener listener) {
        super.addOnPageChangeListener(listener);
        this.onPageChangeListener = listener;
    }

    public void removeOnPageChangeListener(OnPageChangeListener listener) {
        super.removeOnPageChangeListener(listener);
        this.onPageChangeListener = null;
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (this.viewPagerScrollInterface != null && !this.isInNestedScroll) {
            this.viewPagerScrollInterface.onNestedPreScroll(target, dx, dy, consumed);
        }
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (this.viewPagerScrollInterface == null || !this.viewPagerScrollInterface.onNestedPreFling(target, velocityX, velocityY)) {
            return super.onNestedPreFling(target, velocityX, velocityY);
        }
        return true;
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        if (nestedScrollAxes == 1) {
            this.isInNestedScroll = true;
        }
        return true;
    }

    public void onStopNestedScroll(View child) {
        this.isInNestedScroll = false;
    }

    private boolean isSwipingDisabled() {
        return !this.swipingEnabled || this.isInNestedScroll || ((getAdapter() instanceof BaseTabFragmentPager) && !((BaseTabFragmentPager) getAdapter()).isSwipePagingEnabled(getCurrentItem()));
    }

    public void setSwipingEnabled(boolean enabled) {
        this.swipingEnabled = enabled;
    }
}
