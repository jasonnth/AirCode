package com.airbnb.paris;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils.TruncateAt;
import android.widget.TextView;
import com.facebook.internal.AnalyticsEvents;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextViewStyleApplier.kt */
public final class TextViewStyleApplier extends StyleApplier<TextViewStyleApplier, TextView> {
    public TextViewStyleApplier(TextView view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        int[] iArr = R.styleable.Paris_TextView;
        Intrinsics.checkExpressionValueIsNotNull(iArr, "R.styleable.Paris_TextView");
        return iArr;
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        new ViewStyleApplier(getView()).apply(style);
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        Intrinsics.checkParameterIsNotNull(a, "a");
        Drawable drawableLeft = null;
        Drawable drawableTop = null;
        Drawable drawableRight = null;
        Drawable drawableBottom = null;
        if (a.hasValue(R.styleable.Paris_TextView_android_drawableLeft)) {
            drawableLeft = StyleUtils.getDrawable(((TextView) getView()).getContext(), a, R.styleable.Paris_TextView_android_drawableLeft);
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_drawableTop)) {
            drawableTop = StyleUtils.getDrawable(((TextView) getView()).getContext(), a, R.styleable.Paris_TextView_android_drawableTop);
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_drawableRight)) {
            drawableRight = StyleUtils.getDrawable(((TextView) getView()).getContext(), a, R.styleable.Paris_TextView_android_drawableRight);
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_drawableBottom)) {
            drawableBottom = StyleUtils.getDrawable(((TextView) getView()).getContext(), a, R.styleable.Paris_TextView_android_drawableBottom);
        }
        if (!(drawableLeft == null && drawableTop == null && drawableRight == null && drawableBottom == null)) {
            Drawable[] drawables = ((TextView) getView()).getCompoundDrawables();
            TextView textView = (TextView) getView();
            if (drawableLeft == null) {
                drawableLeft = drawables[0];
            }
            if (drawableTop == null) {
                drawableTop = drawables[1];
            }
            if (drawableRight == null) {
                drawableRight = drawables[2];
            }
            if (drawableBottom == null) {
                drawableBottom = drawables[3];
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_ellipsize)) {
            setEllipsize((TextView) getView(), a.getInt(R.styleable.Paris_TextView_android_ellipsize, -1));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_gravity)) {
            ((TextView) getView()).setGravity(a.getInt(R.styleable.Paris_TextView_android_gravity, -1));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_letterSpacing) && VERSION.SDK_INT >= 21) {
            ((TextView) getView()).setLetterSpacing(a.getFloat(R.styleable.Paris_TextView_android_letterSpacing, 0.0f));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_lines)) {
            ((TextView) getView()).setLines(a.getInt(R.styleable.Paris_TextView_android_lines, -1));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_lineSpacingExtra)) {
            ((TextView) getView()).setLineSpacing((float) a.getDimensionPixelSize(R.styleable.Paris_TextView_android_lineSpacingExtra, 0), ((TextView) getView()).getLineSpacingMultiplier());
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_lineSpacingMultiplier)) {
            ((TextView) getView()).setLineSpacing(((TextView) getView()).getLineSpacingExtra(), a.getFloat(R.styleable.Paris_TextView_android_lineSpacingMultiplier, 1.0f));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_maxLines)) {
            ((TextView) getView()).setMaxLines(a.getInt(R.styleable.Paris_TextView_android_maxLines, -1));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_minLines)) {
            ((TextView) getView()).setMinLines(a.getInt(R.styleable.Paris_TextView_android_minLines, -1));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_minWidth)) {
            ((TextView) getView()).setMinWidth(a.getDimensionPixelSize(R.styleable.Paris_TextView_android_minWidth, -1));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_singleLine)) {
            ((TextView) getView()).setSingleLine(a.getBoolean(R.styleable.Paris_TextView_android_singleLine, false));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_textAllCaps)) {
            ((TextView) getView()).setAllCaps(a.getBoolean(R.styleable.Paris_TextView_android_textAllCaps, false));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_textColor)) {
            ((TextView) getView()).setTextColor(a.getColorStateList(R.styleable.Paris_TextView_android_textColor));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_textColorHint)) {
            ((TextView) getView()).setHintTextColor(a.getColorStateList(R.styleable.Paris_TextView_android_textColorHint));
        }
        if (a.hasValue(R.styleable.Paris_TextView_android_textSize)) {
            ((TextView) getView()).setTextSize(0, (float) a.getDimensionPixelSize(R.styleable.Paris_TextView_android_textSize, 0));
        }
    }

    private final void setEllipsize(TextView view, int value) {
        TruncateAt truncateAt;
        switch (value) {
            case 1:
                truncateAt = TruncateAt.START;
                break;
            case 2:
                truncateAt = TruncateAt.MIDDLE;
                break;
            case 3:
                truncateAt = TruncateAt.END;
                break;
            case 4:
                truncateAt = TruncateAt.MARQUEE;
                break;
            default:
                throw new IllegalStateException("Wrong value for ellipsize");
        }
        view.setEllipsize(truncateAt);
    }
}
