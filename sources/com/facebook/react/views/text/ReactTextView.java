package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;

public class ReactTextView extends TextView implements ReactCompoundView {
    private static final LayoutParams EMPTY_LAYOUT_PARAMS = new LayoutParams(0, 0);
    private boolean mContainsImages;
    private int mDefaultGravityHorizontal = (getGravity() & 8388615);
    private int mDefaultGravityVertical = (getGravity() & 112);
    private TruncateAt mEllipsizeLocation = TruncateAt.END;
    private float mLetterSpacing = Float.NaN;
    private float mLineHeight = Float.NaN;
    private int mNumberOfLines = Integer.MAX_VALUE;
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private int mTextAlign = 0;
    private boolean mTextIsSelectable;

    public ReactTextView(Context context) {
        super(context);
    }

    public void setText(ReactTextUpdate update) {
        this.mContainsImages = update.containsImages();
        if (getLayoutParams() == null) {
            setLayoutParams(EMPTY_LAYOUT_PARAMS);
        }
        setText(update.getText());
        setPadding((int) Math.floor((double) update.getPaddingLeft()), (int) Math.floor((double) update.getPaddingTop()), (int) Math.floor((double) update.getPaddingRight()), (int) Math.floor((double) update.getPaddingBottom()));
        if (VERSION.SDK_INT >= 21) {
            float nextLetterSpacing = update.getLetterSpacing();
            int fontSize = update.getFontSize();
            if (!FloatUtil.floatsEqual(this.mLetterSpacing, nextLetterSpacing)) {
                this.mLetterSpacing = nextLetterSpacing;
                if (Float.isNaN(this.mLetterSpacing)) {
                    setLetterSpacing(0.0f);
                } else {
                    setLetterSpacing(1.0f + ((this.mLetterSpacing - PixelUtil.toDIPFromPixel((float) fontSize)) / PixelUtil.toDIPFromPixel((float) fontSize)));
                }
            }
        }
        int nextTextAlign = update.getTextAlign();
        if (this.mTextAlign != nextTextAlign) {
            this.mTextAlign = nextTextAlign;
        }
        setGravityHorizontal(this.mTextAlign);
        if (VERSION.SDK_INT >= 23 && getBreakStrategy() != update.getTextBreakStrategy()) {
            setBreakStrategy(update.getTextBreakStrategy());
        }
    }

    public int reactTagForTouch(float touchX, float touchY) {
        Spanned text = (Spanned) getText();
        int target = getId();
        int x = (int) touchX;
        int y = (int) touchY;
        Layout layout = getLayout();
        if (layout == null) {
            return target;
        }
        int line = layout.getLineForVertical(y);
        int lineEndX = (int) layout.getLineRight(line);
        if (x >= ((int) layout.getLineLeft(line)) && x <= lineEndX) {
            int index = layout.getOffsetForHorizontal(line, (float) x);
            ReactTagSpan[] spans = (ReactTagSpan[]) text.getSpans(index, index, ReactTagSpan.class);
            if (spans != null) {
                int targetSpanTextLength = text.length();
                for (int i = 0; i < spans.length; i++) {
                    int spanStart = text.getSpanStart(spans[i]);
                    int spanEnd = text.getSpanEnd(spans[i]);
                    if (spanEnd > index && spanEnd - spanStart <= targetSpanTextLength) {
                        target = spans[i].getReactTag();
                        targetSpanTextLength = spanEnd - spanStart;
                    }
                }
            }
        }
        return target;
    }

    public void setTextIsSelectable(boolean selectable) {
        this.mTextIsSelectable = selectable;
        super.setTextIsSelectable(selectable);
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (span.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (span.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                span.onDetachedFromWindow();
            }
        }
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                span.onStartTemporaryDetach();
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                span.onAttachedToWindow();
            }
        }
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                span.onFinishTemporaryDetach();
            }
        }
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.mReactBackgroundDrawable != null) {
            getOrCreateReactViewBackground().setColor(color);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setGravityHorizontal(int gravityHorizontal) {
        if (gravityHorizontal == 0) {
            gravityHorizontal = this.mDefaultGravityHorizontal;
        }
        setGravity((getGravity() & -8 & -8388616) | gravityHorizontal);
    }

    /* access modifiers changed from: 0000 */
    public void setGravityVertical(int gravityVertical) {
        if (gravityVertical == 0) {
            gravityVertical = this.mDefaultGravityVertical;
        }
        setGravity((getGravity() & -113) | gravityVertical);
    }

    public void setNumberOfLines(int numberOfLines) {
        if (numberOfLines == 0) {
            numberOfLines = Integer.MAX_VALUE;
        }
        this.mNumberOfLines = numberOfLines;
        setMaxLines(this.mNumberOfLines);
    }

    public void setEllipsizeLocation(TruncateAt ellipsizeLocation) {
        this.mEllipsizeLocation = ellipsizeLocation;
    }

    public void updateView() {
        setEllipsize(this.mNumberOfLines == Integer.MAX_VALUE ? null : this.mEllipsizeLocation);
    }

    public void setBorderWidth(int position, float width) {
        getOrCreateReactViewBackground().setBorderWidth(position, width);
    }

    public void setBorderColor(int position, float color, float alpha) {
        getOrCreateReactViewBackground().setBorderColor(position, color, alpha);
    }

    public void setBorderRadius(float borderRadius) {
        getOrCreateReactViewBackground().setRadius(borderRadius);
    }

    public void setBorderRadius(float borderRadius, int position) {
        getOrCreateReactViewBackground().setRadius(borderRadius, position);
    }

    public void setBorderStyle(String style) {
        getOrCreateReactViewBackground().setBorderStyle(style);
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.mReactBackgroundDrawable);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, backgroundDrawable}));
            }
        }
        return this.mReactBackgroundDrawable;
    }
}
