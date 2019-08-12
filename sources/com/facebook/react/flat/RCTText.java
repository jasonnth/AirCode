package com.facebook.react.flat;

import android.support.p000v4.text.TextDirectionHeuristicsCompat;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import com.facebook.fbui.textlayoutbuilder.TextLayoutBuilder;
import com.facebook.fbui.textlayoutbuilder.glyphwarmer.GlyphWarmerImpl;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNodeAPI;

final class RCTText extends RCTVirtualText implements YogaMeasureFunction {
    private static final int ALIGNMENT_LEFT = 3;
    private static final int ALIGNMENT_RIGHT = 4;
    private static final TextLayoutBuilder sTextLayoutBuilder = new TextLayoutBuilder().setShouldCacheLayout(false).setShouldWarmText(true).setGlyphWarmer(new GlyphWarmerImpl());
    private int mAlignment = 0;
    private DrawTextLayout mDrawCommand;
    private int mNumberOfLines = Integer.MAX_VALUE;
    private float mSpacingAdd = 0.0f;
    private float mSpacingMult = 1.0f;
    private CharSequence mText;

    public RCTText() {
        setMeasureFunction(this);
        getSpan().setFontSize(getDefaultFontSize());
    }

    public boolean isVirtual() {
        return false;
    }

    public boolean isVirtualAnchor() {
        return true;
    }

    public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        CharSequence text = getText();
        if (TextUtils.isEmpty(text)) {
            this.mText = null;
            return YogaMeasureOutput.make(0, 0);
        }
        this.mText = text;
        Layout layout = createTextLayout((int) Math.ceil((double) width), widthMode, TruncateAt.END, true, this.mNumberOfLines, this.mNumberOfLines == 1, text, getFontSize(), this.mSpacingAdd, this.mSpacingMult, getFontStyle(), getAlignment());
        if (this.mDrawCommand == null || this.mDrawCommand.isFrozen()) {
            this.mDrawCommand = new DrawTextLayout(layout);
        } else {
            this.mDrawCommand.setLayout(layout);
        }
        return YogaMeasureOutput.make(this.mDrawCommand.getLayoutWidth(), this.mDrawCommand.getLayoutHeight());
    }

    /* access modifiers changed from: protected */
    public void collectState(StateBuilder stateBuilder, float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom) {
        super.collectState(stateBuilder, left, top, right, bottom, clipLeft, clipTop, clipRight, clipBottom);
        if (this.mText == null) {
            if (bottom - top > 0.0f && right - left > 0.0f) {
                CharSequence text = getText();
                if (!TextUtils.isEmpty(text)) {
                    this.mText = text;
                }
            }
            if (this.mText == null) {
                return;
            }
        }
        boolean updateNodeRegion = false;
        if (this.mDrawCommand == null) {
            DrawTextLayout drawTextLayout = new DrawTextLayout(createTextLayout((int) Math.ceil((double) (right - left)), YogaMeasureMode.EXACTLY, TruncateAt.END, true, this.mNumberOfLines, this.mNumberOfLines == 1, this.mText, getFontSize(), this.mSpacingAdd, this.mSpacingMult, getFontStyle(), getAlignment()));
            this.mDrawCommand = drawTextLayout;
            updateNodeRegion = true;
        }
        float left2 = left + getPadding(0);
        float top2 = top + getPadding(1);
        this.mDrawCommand = (DrawTextLayout) this.mDrawCommand.updateBoundsAndFreeze(left2, top2, left2 + this.mDrawCommand.getLayoutWidth(), top2 + this.mDrawCommand.getLayoutHeight(), clipLeft, clipTop, clipRight, clipBottom);
        stateBuilder.addDrawCommand(this.mDrawCommand);
        if (updateNodeRegion) {
            NodeRegion nodeRegion = getNodeRegion();
            if (nodeRegion instanceof TextNodeRegion) {
                ((TextNodeRegion) nodeRegion).setLayout(this.mDrawCommand.getLayout());
            }
        }
        performCollectAttachDetachListeners(stateBuilder);
    }

    /* access modifiers changed from: 0000 */
    public boolean doesDraw() {
        return true;
    }

    @ReactProp(defaultDouble = Double.NaN, name = "lineHeight")
    public void setLineHeight(double lineHeight) {
        if (Double.isNaN(lineHeight)) {
            this.mSpacingMult = 1.0f;
            this.mSpacingAdd = 0.0f;
        } else {
            this.mSpacingMult = 0.0f;
            this.mSpacingAdd = PixelUtil.toPixelFromSP((float) lineHeight);
        }
        notifyChanged(true);
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(int numberOfLines) {
        this.mNumberOfLines = numberOfLines;
        notifyChanged(true);
    }

    /* access modifiers changed from: 0000 */
    public void updateNodeRegion(float left, float top, float right, float bottom, boolean isVirtual) {
        NodeRegion nodeRegion = getNodeRegion();
        if (this.mDrawCommand != null) {
            Layout layout = null;
            if (nodeRegion instanceof TextNodeRegion) {
                layout = ((TextNodeRegion) nodeRegion).getLayout();
            }
            Layout newLayout = this.mDrawCommand.getLayout();
            if (!nodeRegion.matches(left, top, right, bottom, isVirtual) || layout != newLayout) {
                setNodeRegion(new TextNodeRegion(left, top, right, bottom, getReactTag(), isVirtual, newLayout));
            }
        } else if (!nodeRegion.matches(left, top, right, bottom, isVirtual)) {
            setNodeRegion(new TextNodeRegion(left, top, right, bottom, getReactTag(), isVirtual, null));
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultFontSize() {
        return fontSizeFromSp(14.0f);
    }

    /* access modifiers changed from: protected */
    public void notifyChanged(boolean shouldRemeasure) {
        dirty();
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(String textAlign) {
        if (textAlign == null || "auto".equals(textAlign)) {
            this.mAlignment = 0;
        } else if (ViewProps.LEFT.equals(textAlign)) {
            this.mAlignment = 3;
        } else if (ViewProps.RIGHT.equals(textAlign)) {
            this.mAlignment = 5;
        } else if ("center".equals(textAlign)) {
            this.mAlignment = 17;
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + textAlign);
        }
        notifyChanged(false);
    }

    public Alignment getAlignment() {
        char c = 4;
        boolean isRtl = getLayoutDirection() == YogaDirection.RTL;
        switch (this.mAlignment) {
            case 3:
                if (!isRtl) {
                    c = 3;
                }
                return Alignment.values()[c];
            case 5:
                if (isRtl) {
                    c = 3;
                }
                return Alignment.values()[c];
            case 17:
                return Alignment.ALIGN_CENTER;
            default:
                return Alignment.ALIGN_NORMAL;
        }
    }

    private static Layout createTextLayout(int width, YogaMeasureMode widthMode, TruncateAt ellipsize, boolean shouldIncludeFontPadding, int maxLines, boolean isSingleLine, CharSequence text, int textSize, float extraSpacing, float spacingMultiplier, int textStyle, Alignment textAlignment) {
        int textMeasureMode;
        switch (widthMode) {
            case UNDEFINED:
                textMeasureMode = 0;
                break;
            case EXACTLY:
                textMeasureMode = 1;
                break;
            case AT_MOST:
                textMeasureMode = 2;
                break;
            default:
                throw new IllegalStateException("Unexpected size mode: " + widthMode);
        }
        sTextLayoutBuilder.setEllipsize(ellipsize).setMaxLines(maxLines).setSingleLine(isSingleLine).setText(text).setTextSize(textSize).setWidth(width, textMeasureMode);
        sTextLayoutBuilder.setTextStyle(textStyle);
        sTextLayoutBuilder.setTextDirection(TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR);
        sTextLayoutBuilder.setIncludeFontPadding(shouldIncludeFontPadding);
        sTextLayoutBuilder.setTextSpacingExtra(extraSpacing);
        sTextLayoutBuilder.setTextSpacingMultiplier(spacingMultiplier);
        sTextLayoutBuilder.setAlignment(textAlignment);
        Layout newLayout = sTextLayoutBuilder.build();
        sTextLayoutBuilder.setText(null);
        return newLayout;
    }
}
