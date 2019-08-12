package com.airbnb.android.utils;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.p000v4.util.Pair;
import android.support.p000v4.view.ViewCompat;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.airbnb.android.utils.animation.SimpleAnimatorListener;
import com.airbnb.p027n2.utils.ThemeUtils;

public final class ViewUtils {
    private static final double MAX_SCROLL_DURATION = 500.0d;
    private static final float SCROLL_SPEED_PX_PER_MS = 1.8f;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_OFF_AXIS = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private ViewUtils() {
    }

    public static void setDrawableLeft(TextView textView, Drawable drawable) {
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    public static void setDrawableTop(TextView textView, Drawable drawable) {
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    public static void setDrawableRight(TextView textView, Drawable drawable) {
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    public static void setDrawableBottom(TextView textView, Drawable drawable) {
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
    }

    public static void setMarginLeft(View view, int left) {
        MarginLayoutParams layoutParams = getMarginLayoutParams(view);
        layoutParams.leftMargin = left;
        view.setLayoutParams(layoutParams);
    }

    public static void setMarginRight(View view, int right) {
        MarginLayoutParams layoutParams = getMarginLayoutParams(view);
        layoutParams.rightMargin = right;
        view.setLayoutParams(layoutParams);
    }

    public static void setMarginTop(View view, int top) {
        MarginLayoutParams layoutParams = getMarginLayoutParams(view);
        layoutParams.topMargin = top;
        view.setLayoutParams(layoutParams);
    }

    public static void setMarginTopDimen(View view, int dimenId) {
        setMarginTop(view, view.getContext().getResources().getDimensionPixelOffset(dimenId));
    }

    public static void setMarginBottom(View view, int bottom) {
        MarginLayoutParams layoutParams = getMarginLayoutParams(view);
        layoutParams.bottomMargin = bottom;
        view.setLayoutParams(layoutParams);
    }

    public static void setMarginBottomDimen(View view, int dimenId) {
        setMarginBottom(view, view.getContext().getResources().getDimensionPixelOffset(dimenId));
    }

    private static MarginLayoutParams getMarginLayoutParams(View view) {
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            return (MarginLayoutParams) view.getLayoutParams();
        }
        throw new IllegalArgumentException("View LayoutParams is not an instance of MarginLayoutParams");
    }

    public static boolean isVerticalSwipe(MotionEvent e1, MotionEvent e2) {
        return Math.abs(e1.getY() - e2.getY()) > 250.0f;
    }

    public static boolean isLeftSwipe(MotionEvent e1, MotionEvent e2, float velocityX) {
        return e1.getX() - e2.getX() > 120.0f && Math.abs(velocityX) > 200.0f;
    }

    public static boolean isRightSwipe(MotionEvent e1, MotionEvent e2, float velocityX) {
        return e2.getX() - e1.getX() > 120.0f && Math.abs(velocityX) > 200.0f;
    }

    public static void scrollToView(ScrollView scrollView, View view) {
        scrollToView(scrollView, view, null);
    }

    public static void scrollToView(ScrollView scrollView, View view, final Runnable onScrollEndCallback) {
        int scrollBy = getPixelsToScrollToGetViewFullyInView(view, scrollView);
        long duration = (long) Math.max((double) Math.abs((long) (((float) scrollBy) / SCROLL_SPEED_PX_PER_MS)), MAX_SCROLL_DURATION);
        Animator animator = ObjectAnimator.ofInt(scrollView, "scrollY", new int[]{scrollView.getScrollY() + scrollBy});
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        if (onScrollEndCallback != null) {
            animator.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    onScrollEndCallback.run();
                }
            });
        }
        animator.start();
    }

    public static int getPixelsToScrollToGetViewFullyInView(View view, ScrollView scrollView) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int viewTop = location[1];
        int viewBottom = viewTop + view.getHeight();
        scrollView.getLocationOnScreen(location);
        int scrollViewTop = location[1];
        int scrollViewBottom = scrollViewTop + scrollView.getHeight();
        if (viewTop < scrollViewTop) {
            return -(scrollViewTop - viewTop);
        }
        if (viewBottom > scrollViewBottom) {
            return viewBottom - scrollViewBottom;
        }
        return 0;
    }

    public static int getAndroidDimension(Context context, String resourceIdName) {
        Resources resources = context.getResources();
        try {
            int id = resources.getIdentifier(resourceIdName, "dimen", "android");
            if (id > 0) {
                return resources.getDimensionPixelSize(id);
            }
            return 0;
        } catch (NotFoundException e) {
            return 0;
        }
    }

    public static int getActionBarHeight(Context context) {
        return ThemeUtils.resolveDimension(context, 16843499, 0);
    }

    public static Point getScreenSize(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return new Point(metrics.widthPixels, metrics.heightPixels);
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenWidthMinusPadding(Context context, int paddingRes) {
        return getScreenSize(context).x - (context.getResources().getDimensionPixelSize(paddingRes) * 2);
    }

    public static Bitmap getBitmapFromUnmeasuredView(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return bitmap;
    }

    public static float getDensityScale(Resources res, int id) {
        int density;
        TypedValue value = new TypedValue();
        res.getValue(id, value, true);
        int resourceDensity = value.density;
        if (resourceDensity == 0) {
            density = 160;
        } else if (resourceDensity != 65535) {
            density = resourceDensity;
        } else {
            density = 0;
        }
        int targetDensity = res.getDisplayMetrics().densityDpi;
        if (density <= 0 || targetDensity <= 0) {
            return 1.0f;
        }
        return ((float) targetDensity) / ((float) density);
    }

    public static View findTransitionView(View view, String transitionName) {
        if (transitionName.equals(ViewCompat.getTransitionName(view))) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = vg.getChildCount() - 1; i >= 0; i--) {
                View transitionView = findTransitionView(vg.getChildAt(i), transitionName);
                if (transitionView != null) {
                    return transitionView;
                }
            }
        }
        return null;
    }

    public static Pair<View, String> transitionPair(View view) {
        return Pair.create(view, ViewCompat.getTransitionName(view));
    }

    public static void setNoDelayLayoutTransition(ViewGroup viewGroup) {
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setStartDelay(1, 0);
        layoutTransition.setStartDelay(0, 0);
        layoutTransition.setStartDelay(4, 0);
        layoutTransition.setStartDelay(2, 0);
        layoutTransition.setStartDelay(3, 0);
        viewGroup.setLayoutTransition(layoutTransition);
    }

    public static Rect getScreenLocationMinusStatusBar(Activity activity, View view) {
        Rect loc = new Rect();
        int[] coords = new int[2];
        view.getLocationOnScreen(coords);
        int statusHeight = ActivityUtils.getStatusBarHeight(activity);
        loc.set(coords[0], coords[1] - statusHeight, coords[0] + view.getWidth(), (coords[1] + view.getHeight()) - statusHeight);
        return loc;
    }

    public static Rect getViewBounds(View view) {
        Rect loc = new Rect();
        int[] coords = new int[2];
        view.getLocationOnScreen(coords);
        loc.set(coords[0], coords[1], coords[0] + view.getWidth(), coords[1] + view.getHeight());
        return loc;
    }

    public static void flashVerticalScrollBar(ListView listView) {
        listView.scrollBy(0, 1);
        listView.scrollBy(0, -1);
    }

    public static void fadeInView(View view, int duration) {
        view.setAlpha(0.0f);
        view.setVisibility(0);
        view.animate().alpha(1.0f).setDuration((long) duration);
    }

    public static void setVisibleIf(View view, boolean visibleIfTrue) {
        if (view != null) {
            view.setVisibility(visibleIfTrue ? 0 : 8);
        }
    }

    public static void setGoneIf(View view, boolean goneIfTrue) {
        if (view != null) {
            view.setVisibility(goneIfTrue ? 8 : 0);
        }
    }

    public static void setInvisibleIf(View view, boolean invisibleIfTrue) {
        if (view != null) {
            view.setVisibility(invisibleIfTrue ? 4 : 0);
        }
    }

    public static void setVisibleIf(FloatingActionButton view, boolean visibleIfTrue) {
        if (view != null) {
            if (visibleIfTrue) {
                view.show();
            } else {
                view.hide();
            }
        }
    }

    public static void setEnableIf(View view, boolean enableIfTrue) {
        view.setEnabled(enableIfTrue);
    }

    public static void setTextAndColorSubstring(TextView view, String fulltext, String subtext, int color) {
        view.setText(fulltext, BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        int i = fulltext.indexOf(subtext);
        if (i == -1) {
            throw new RuntimeException("substring not found");
        }
        str.setSpan(new ForegroundColorSpan(color), i, subtext.length() + i, 33);
    }

    public static void setBoldIf(TextView textView, boolean bold) {
        textView.setTypeface(textView.getTypeface(), bold ? 1 : 0);
    }
}
