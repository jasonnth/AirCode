package com.airbnb.android.lib.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.facebook.react.uimanager.ViewProps;

public class TextResize extends Transition {
    private static final String DATA = "TextResize:data";
    private static final String FONT = "TextResize:font";
    private static final String FONT_SIZE = "TextResize:fontSize";
    private static final String[] PROPERTIES = {FONT_SIZE};

    private static class SwitchBitmapDrawable extends Drawable {
        private float bottom;
        private final Bitmap endBitmap;
        private final float endFontSize;
        private final float endWidth;
        private float fontSize;
        private final int horizontalGravity;
        private float left;
        private final Paint paint = new Paint();
        private float right;
        private final Bitmap startBitmap;
        private final float startFontSize;
        private final float startWidth;
        private int textColor;
        private float top;
        private final int verticalGravity;
        private final TextView view;

        public SwitchBitmapDrawable(TextView view2, int gravity, Bitmap startBitmap2, float startFontSize2, float startWidth2, Bitmap endBitmap2, float endFontSize2, float endWidth2) {
            this.view = view2;
            this.horizontalGravity = gravity & 7;
            this.verticalGravity = gravity & 112;
            this.startBitmap = startBitmap2;
            this.endBitmap = endBitmap2;
            this.startFontSize = startFontSize2;
            this.endFontSize = endFontSize2;
            this.startWidth = startWidth2;
            this.endWidth = endWidth2;
        }

        public void invalidateSelf() {
            super.invalidateSelf();
            this.view.invalidate();
        }

        public void setFontSize(float fontSize2) {
            this.fontSize = fontSize2;
            invalidateSelf();
        }

        public void setTextColor(int textColor2) {
            this.textColor = textColor2;
            setColorFilter(textColor2, Mode.SRC_IN);
            invalidateSelf();
        }

        public void setLeft(float left2) {
            this.left = left2;
            invalidateSelf();
        }

        public void setTop(float top2) {
            this.top = top2;
            invalidateSelf();
        }

        public void setRight(float right2) {
            this.right = right2;
            invalidateSelf();
        }

        public void setBottom(float bottom2) {
            this.bottom = bottom2;
            invalidateSelf();
        }

        public float getLeft() {
            return this.left;
        }

        public float getTop() {
            return this.top;
        }

        public float getRight() {
            return this.right;
        }

        public float getBottom() {
            return this.bottom;
        }

        public float getFontSize() {
            return this.fontSize;
        }

        public int getTextColor() {
            return this.textColor;
        }

        public void draw(Canvas canvas) {
            int saveCount = canvas.save();
            float threshold = this.startFontSize / (this.startFontSize + this.endFontSize);
            float progress = (getFontSize() - this.startFontSize) / (this.endFontSize - this.startFontSize);
            float expectedWidth = TextResize.interpolate(this.startWidth, this.endWidth, progress);
            if (progress < threshold) {
                float scale = expectedWidth / this.startWidth;
                canvas.translate(getTranslationPoint(this.horizontalGravity, this.left, this.right, (float) this.startBitmap.getWidth(), scale), getTranslationPoint(this.verticalGravity, this.top, this.bottom, (float) this.startBitmap.getHeight(), scale));
                canvas.scale(scale, scale);
                canvas.drawBitmap(this.startBitmap, 0.0f, 0.0f, this.paint);
            } else {
                float scale2 = expectedWidth / this.endWidth;
                canvas.translate(getTranslationPoint(this.horizontalGravity, this.left, this.right, (float) this.endBitmap.getWidth(), scale2), getTranslationPoint(this.verticalGravity, this.top, this.bottom, (float) this.endBitmap.getHeight(), scale2));
                canvas.scale(scale2, scale2);
                canvas.drawBitmap(this.endBitmap, 0.0f, 0.0f, this.paint);
            }
            canvas.restoreToCount(saveCount);
        }

        public void setAlpha(int alpha) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.paint.setColorFilter(colorFilter);
        }

        public int getOpacity() {
            return -3;
        }

        private float getTranslationPoint(int gravity, float start, float end, float dim, float scale) {
            switch (gravity) {
                case 1:
                case 16:
                    return ((start + end) - (dim * scale)) / 2.0f;
                case 5:
                case 80:
                    return end - (dim * scale);
                default:
                    return start;
            }
        }
    }

    public static class TextResizeData {
        public final int gravity;
        public final int height;
        public final int paddingBottom;
        public final int paddingLeft;
        public final int paddingRight;
        public final int paddingTop;
        public final int textColor;
        public final int width;

        public TextResizeData(TextView textView) {
            this.paddingLeft = textView.getPaddingLeft();
            this.paddingTop = textView.getPaddingTop();
            this.paddingRight = textView.getPaddingRight();
            this.paddingBottom = textView.getPaddingBottom();
            this.width = textView.getWidth();
            this.height = textView.getHeight();
            this.gravity = textView.getGravity();
            this.textColor = textView.getCurrentTextColor();
        }
    }

    public TextResize() {
        addTarget(TextView.class);
    }

    public TextResize(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTarget(TextView.class);
    }

    public String[] getTransitionProperties() {
        return PROPERTIES;
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof AirTextView) {
            AirTextView view = (AirTextView) transitionValues.view;
            transitionValues.values.put(FONT_SIZE, Float.valueOf(view.getTextSize()));
            transitionValues.values.put(FONT, view.getFont());
            transitionValues.values.put(DATA, new TextResizeData(view));
        }
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        final ObjectAnimator animator;
        if (startValues == null || endValues == null) {
            return null;
        }
        final TextResizeData startData = (TextResizeData) startValues.values.get(DATA);
        final TextResizeData endData = (TextResizeData) endValues.values.get(DATA);
        if (startData.gravity != endData.gravity) {
            return null;
        }
        AirTextView textView = (AirTextView) endValues.view;
        float startFontSize = ((Float) startValues.values.get(FONT_SIZE)).floatValue();
        setTextViewData(textView, startData, startFontSize, (Font) startValues.values.get(FONT));
        float startWidth = textView.getPaint().measureText(textView.getText().toString());
        Bitmap startBitmap = captureTextBitmap(textView);
        if (startBitmap == null) {
            startFontSize = 0.0f;
        }
        float endFontSize = ((Float) endValues.values.get(FONT_SIZE)).floatValue();
        setTextViewData(textView, endData, endFontSize, (Font) endValues.values.get(FONT));
        float endWidth = textView.getPaint().measureText(textView.getText().toString());
        Bitmap endBitmap = captureTextBitmap(textView);
        if (endBitmap == null) {
            endFontSize = 0.0f;
        }
        if (startFontSize == 0.0f && endFontSize == 0.0f) {
            return null;
        }
        final ColorStateList textColors = textView.getTextColors();
        final ColorStateList hintColors = textView.getHintTextColors();
        final int highlightColor = textView.getHighlightColor();
        final ColorStateList linkColors = textView.getLinkTextColors();
        textView.setTextColor(0);
        textView.setHintTextColor(0);
        textView.setHighlightColor(0);
        textView.setLinkTextColor(0);
        SwitchBitmapDrawable drawable = new SwitchBitmapDrawable(textView, startData.gravity, startBitmap, startFontSize, startWidth, endBitmap, endFontSize, endWidth);
        textView.getOverlay().add(drawable);
        PropertyValuesHolder leftProp = PropertyValuesHolder.ofFloat(ViewProps.LEFT, new float[]{(float) startData.paddingLeft, (float) endData.paddingLeft});
        PropertyValuesHolder topProp = PropertyValuesHolder.ofFloat(ViewProps.TOP, new float[]{(float) startData.paddingTop, (float) endData.paddingTop});
        PropertyValuesHolder rightProp = PropertyValuesHolder.ofFloat(ViewProps.RIGHT, new float[]{(float) (startData.width - startData.paddingRight), (float) (endData.width - endData.paddingRight)});
        PropertyValuesHolder bottomProp = PropertyValuesHolder.ofFloat(ViewProps.BOTTOM, new float[]{(float) (startData.height - startData.paddingBottom), (float) (endData.height - endData.paddingBottom)});
        PropertyValuesHolder fontSizeProp = PropertyValuesHolder.ofFloat(ViewProps.FONT_SIZE, new float[]{startFontSize, endFontSize});
        if (startData.textColor != endData.textColor) {
            animator = ObjectAnimator.ofPropertyValuesHolder(drawable, new PropertyValuesHolder[]{leftProp, topProp, rightProp, bottomProp, fontSizeProp, PropertyValuesHolder.ofObject("textColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(startData.textColor), Integer.valueOf(endData.textColor)})});
        } else {
            animator = ObjectAnimator.ofPropertyValuesHolder(drawable, new PropertyValuesHolder[]{leftProp, topProp, rightProp, bottomProp, fontSizeProp});
        }
        final float finalFontSize = endFontSize;
        final AirTextView airTextView = textView;
        final SwitchBitmapDrawable switchBitmapDrawable = drawable;
        AnimatorListenerAdapter listener = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                airTextView.getOverlay().remove(switchBitmapDrawable);
                airTextView.setTextColor(textColors);
                airTextView.setHintTextColor(hintColors);
                airTextView.setHighlightColor(highlightColor);
                airTextView.setLinkTextColor(linkColors);
            }

            public void onAnimationCancel(Animator animation) {
                airTextView.getOverlay().remove(switchBitmapDrawable);
                airTextView.setTextColor(textColors);
                airTextView.setHintTextColor(hintColors);
                airTextView.setHighlightColor(highlightColor);
                airTextView.setLinkTextColor(linkColors);
            }

            public void onAnimationPause(Animator animation) {
                airTextView.setTextSize(0, switchBitmapDrawable.getFontSize());
                int paddingLeft = Math.round(switchBitmapDrawable.getLeft());
                int paddingTop = Math.round(switchBitmapDrawable.getTop());
                float fraction = animator.getAnimatedFraction();
                airTextView.setPadding(paddingLeft, paddingTop, Math.round(TextResize.interpolate((float) startData.paddingRight, (float) endData.paddingRight, fraction)), Math.round(TextResize.interpolate((float) startData.paddingBottom, (float) endData.paddingBottom, fraction)));
                airTextView.setTextColor(switchBitmapDrawable.getTextColor());
            }

            public void onAnimationResume(Animator animation) {
                airTextView.setTextSize(0, finalFontSize);
                airTextView.setPadding(endData.paddingLeft, endData.paddingTop, endData.paddingRight, endData.paddingBottom);
                airTextView.setTextColor(endData.textColor);
            }
        };
        animator.addListener(listener);
        animator.addPauseListener(listener);
        return animator;
    }

    public static void setTextViewData(AirTextView view, TextResizeData data, float fontSize, Font font) {
        view.setTextSize(0, fontSize);
        view.setTypeface(null);
        view.setFont(font);
        view.setPadding(data.paddingLeft, data.paddingTop, data.paddingRight, data.paddingBottom);
        view.setRight(view.getLeft() + data.width);
        view.setBottom(view.getTop() + data.height);
        view.setTextColor(data.textColor);
        view.measure(MeasureSpec.makeMeasureSpec(view.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(view.getHeight(), 1073741824));
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    private static Bitmap captureTextBitmap(TextView textView) {
        Drawable background = textView.getBackground();
        textView.setBackground(null);
        int width = (textView.getWidth() - textView.getPaddingLeft()) - textView.getPaddingRight();
        int height = (textView.getHeight() - textView.getPaddingTop()) - textView.getPaddingBottom();
        if (width == 0 || height == 0) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate((float) (-textView.getPaddingLeft()), (float) (-textView.getPaddingTop()));
        textView.draw(canvas);
        textView.setBackground(background);
        return bitmap;
    }

    /* access modifiers changed from: private */
    public static float interpolate(float start, float end, float fraction) {
        return ((end - start) * fraction) + start;
    }
}
