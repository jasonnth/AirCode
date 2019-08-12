package com.airbnb.p027n2.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.constraint.ConstraintLayout;
import android.support.p000v4.util.Pair;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.airbnb.n2.R;
import java.util.List;
import java.util.Stack;

/* renamed from: com.airbnb.n2.utils.ViewLibUtils */
public final class ViewLibUtils {
    private static Rect screenRect = null;

    public static Rect getViewBounds(View view) {
        Rect loc = new Rect();
        int[] coords = new int[2];
        view.getLocationOnScreen(coords);
        loc.set(coords[0], coords[1], coords[0] + view.getWidth(), coords[1] + view.getHeight());
        return loc;
    }

    public static Rect getViewRect(View view) {
        return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    public static Rect getViewRectWithMargins(View view) {
        return new Rect(view.getLeft() - getLeftMargin(view), view.getTop() - getTopMargin(view), view.getRight() + getRightMargin(view), view.getBottom() + getBottomMargin(view));
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

    public static boolean isVisible(View view) {
        return view.getVisibility() == 0;
    }

    public static void bindOptionalTextView(TextView textView, CharSequence text) {
        bindOptionalTextView(textView, text, false);
    }

    public static void bindOptionalTextView(TextView textView, CharSequence text, boolean enableLinks) {
        setVisibleIf(textView, !TextUtils.isEmpty(text));
        setText(textView, text, enableLinks);
    }

    public static void bindOptionalTextView(TextView textView, int textResId) {
        bindOptionalTextView(textView, textResId, false);
    }

    public static void bindOptionalTextView(TextView textView, int textResId, boolean enableLinks) {
        setVisibleIf(textView, textResId > 0);
        if (textResId > 0) {
            setText(textView, textResId, enableLinks);
        }
    }

    public static void setText(TextView textView, int stringRes) {
        setText(textView, stringRes, false);
    }

    public static void setText(TextView textView, int stringRes, boolean enableLinks) {
        setText(textView, (CharSequence) textView.getContext().getString(stringRes), enableLinks);
    }

    public static void setText(TextView textView, CharSequence text) {
        setText(textView, text, false);
    }

    public static void setText(TextView textView, CharSequence text, boolean enableLinks) {
        boolean clickableSpans = false;
        if (text instanceof Spanned) {
            if (enableLinks && ((ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class)).length > 0) {
                clickableSpans = true;
            }
            if (clickableSpans && !textView.isClickable()) {
                textView.setClickable(true);
            }
            textView.setMovementMethod(clickableSpans ? LinkMovementMethod.getInstance() : null);
        }
        textView.setText(text);
        if (textView.getParent() instanceof ConstraintLayout) {
            LayoutParams params = textView.getLayoutParams();
            if (params == null) {
                return;
            }
            if (params.width == 0 || params.height == 0) {
                textView.requestLayout();
            }
        }
    }

    public static void setEnabledIf(View view, boolean enabledIfTrue) {
        view.setEnabled(enabledIfTrue);
    }

    public static Point getScreenSize(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return new Point(metrics.widthPixels, metrics.heightPixels);
    }

    public static int getScreenWidth(Context context) {
        return getScreenSize(context).x;
    }

    public static boolean isOnScreen(View view) {
        return isOnScreen(view, 0.05f);
    }

    public static boolean isOnScreen(View view, float onScreenPercentage) {
        return getViewPercentageOnScreen(view) >= onScreenPercentage;
    }

    public static float getViewPercentageOnScreen(View view) {
        if (screenRect == null) {
            Point screenSize = getScreenSize(view.getContext());
            screenRect = new Rect(0, 0, screenSize.x, screenSize.y);
        }
        int[] locs = new int[2];
        view.getLocationInWindow(locs);
        return getRectOverlapPercentage(new Rect(locs[0], locs[1], locs[0] + view.getWidth(), locs[1] + view.getHeight()), screenRect);
    }

    public static float getRectOverlapPercentage(Rect rect1, Rect rect2) {
        return ((float) (Math.max(0, Math.min(rect1.right, rect2.right) - Math.max(rect1.left, rect2.left)) * Math.max(0, Math.min(rect1.bottom, rect2.bottom) - Math.max(rect1.top, rect2.top)))) / ((float) (rect1.width() * rect1.height()));
    }

    public static View getMostVisibleView(List<View> views) {
        View bestMatch = null;
        float bestMatchPercentageOnScreen = 0.0f;
        if (!views.isEmpty()) {
            for (View pm : views) {
                float percentageOnScreen = getViewPercentageOnScreen(pm);
                if (percentageOnScreen > bestMatchPercentageOnScreen) {
                    bestMatchPercentageOnScreen = percentageOnScreen;
                    bestMatch = pm;
                }
            }
            if (bestMatchPercentageOnScreen > 0.05f) {
                return bestMatch;
            }
        }
        return null;
    }

    public static boolean isTabletScreen(Context context) {
        return context.getResources().getBoolean(R.bool.n2_is_tablet);
    }

    public static boolean isWideTabletScreen(Context context) {
        return context.getResources().getBoolean(R.bool.n2_is_wide_tablet);
    }

    public static int clamp(int number, int min, int max) {
        return Math.max(Math.min(number, max), min);
    }

    public static float clamp(float number, float min, float max) {
        return Math.max(Math.min(number, max), min);
    }

    public static CharSequence trim(CharSequence input) {
        int start = 0;
        int end = input.length() - 1;
        while (start <= end && input.charAt(start) <= ' ') {
            start++;
        }
        while (end >= start && input.charAt(end) <= ' ') {
            end--;
        }
        if (start > end) {
            return "";
        }
        return input.subSequence(start, end + 1);
    }

    public static <T extends View> T findById(View view, int id) {
        return view.findViewById(id);
    }

    public static void setPaddingVertical(View view, int px) {
        view.setPadding(view.getPaddingLeft(), px, view.getPaddingRight(), px);
    }

    public static void setPaddingBottom(View view, int px) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), px);
    }

    public static void setPadding(View view, int px) {
        view.setPadding(px, px, px, px);
    }

    public static void setPaddingTop(View view, int px) {
        view.setPadding(view.getPaddingLeft(), px, view.getPaddingRight(), view.getPaddingBottom());
    }

    public static int getLeftMargin(View view) {
        return ((MarginLayoutParams) view.getLayoutParams()).leftMargin;
    }

    public static int getTopMargin(View view) {
        return ((MarginLayoutParams) view.getLayoutParams()).topMargin;
    }

    public static int getRightMargin(View view) {
        return ((MarginLayoutParams) view.getLayoutParams()).rightMargin;
    }

    public static int getBottomMargin(View view) {
        return ((MarginLayoutParams) view.getLayoutParams()).bottomMargin;
    }

    public static int getTotalMeasuredWidth(View view) {
        return getLeftMargin(view) + view.getMeasuredWidth() + getRightMargin(view);
    }

    public static int getTotalHeight(View view) {
        return getTopMargin(view) + view.getHeight() + getBottomMargin(view);
    }

    public static int dpToPx(Context context, float dp) {
        return (int) ((dp * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getSelectableItemBackgroundBorderlessResource(Context context) {
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackgroundBorderless, outValue, true);
        return outValue.resourceId;
    }

    public static void findTransitionViews(View view, List<Pair<View, String>> transitionViews) {
        if (view.getVisibility() == 0) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (!TextUtils.isEmpty(transitionName)) {
                if (isOnScreen(view)) {
                    transitionViews.add(Pair.create(view, transitionName));
                } else {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = viewGroup.getChildCount() - 1; i >= 0; i--) {
                    findTransitionViews(viewGroup.getChildAt(i), transitionViews);
                }
            }
        }
    }

    public static ViewGroup findViewGroupWithTag(ViewGroup root, int key, Object object) {
        Stack<ViewGroup> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            ViewGroup view = (ViewGroup) stack.pop();
            if (object.equals(view.getTag(key))) {
                return view;
            }
            int childCount = view.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = view.getChildAt(i);
                if (child instanceof ViewGroup) {
                    stack.push((ViewGroup) child);
                }
            }
        }
        return null;
    }

    public static float getBatteryLevel(Context context) {
        Intent batteryIntent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (batteryIntent == null) {
            return 50.0f;
        }
        int level = batteryIntent.getIntExtra("level", -1);
        int scale = batteryIntent.getIntExtra("scale", -1);
        if (level == -1 || scale == -1 || ((float) scale) == 0.0f) {
            return 50.0f;
        }
        return (((float) level) / ((float) scale)) * 100.0f;
    }

    public static Drawable getDrawableCompat(Context context, TypedArray array, int resId) {
        if (VERSION.SDK_INT >= 21) {
            return array.getDrawable(resId);
        }
        int resourceId = array.getResourceId(resId, -1);
        if (resourceId != -1) {
            return AppCompatResources.getDrawable(context, resourceId);
        }
        return null;
    }

    public static int getBatteryState(Context context) {
        Intent batteryStatus = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (batteryStatus == null) {
            return 1;
        }
        return batteryStatus.getIntExtra("status", -1);
    }

    public static void underline(TextView view, boolean underline) {
        int paintFlags;
        if (underline) {
            paintFlags = view.getPaintFlags() | 8;
        } else {
            paintFlags = view.getPaintFlags() & -9;
        }
        view.setPaintFlags(paintFlags);
    }

    public static boolean isAtLeastLollipop() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean isAtLeastKitKat() {
        return VERSION.SDK_INT >= 19;
    }
}
