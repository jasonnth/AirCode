package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.annotations.ReactProp;

class RCTVirtualText extends FlatTextShadowNode {
    private static final String BOLD = "bold";
    private static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String ITALIC = "italic";
    private static final String NORMAL = "normal";
    private static final String PROP_SHADOW_COLOR = "textShadowColor";
    private static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    private static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    private FontStylingSpan mFontStylingSpan = FontStylingSpan.INSTANCE;
    private ShadowStyleSpan mShadowStyleSpan = ShadowStyleSpan.INSTANCE;

    RCTVirtualText() {
    }

    public void addChildAt(ReactShadowNode child, int i) {
        super.addChildAt(child, i);
        notifyChanged(true);
    }

    /* access modifiers changed from: protected */
    public void performCollectText(SpannableStringBuilder builder) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((FlatTextShadowNode) getChildAt(i)).collectText(builder);
        }
    }

    /* access modifiers changed from: protected */
    public void performApplySpans(SpannableStringBuilder builder, int begin, int end, boolean isEditable) {
        this.mFontStylingSpan.freeze();
        int flag = isEditable ? 33 : begin == 0 ? 18 : 34;
        builder.setSpan(this.mFontStylingSpan, begin, end, flag);
        if (!(this.mShadowStyleSpan.getColor() == 0 || this.mShadowStyleSpan.getRadius() == 0.0f)) {
            this.mShadowStyleSpan.freeze();
            builder.setSpan(this.mShadowStyleSpan, begin, end, flag);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((FlatTextShadowNode) getChildAt(i)).applySpans(builder, isEditable);
        }
    }

    /* access modifiers changed from: protected */
    public void performCollectAttachDetachListeners(StateBuilder stateBuilder) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((FlatTextShadowNode) getChildAt(i)).performCollectAttachDetachListeners(stateBuilder);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "fontSize")
    public void setFontSize(float fontSizeSp) {
        int fontSize;
        if (Float.isNaN(fontSizeSp)) {
            fontSize = getDefaultFontSize();
        } else {
            fontSize = fontSizeFromSp(fontSizeSp);
        }
        if (this.mFontStylingSpan.getFontSize() != fontSize) {
            getSpan().setFontSize(fontSize);
            notifyChanged(true);
        }
    }

    @ReactProp(defaultDouble = Double.NaN, name = "color")
    public void setColor(double textColor) {
        if (this.mFontStylingSpan.getTextColor() != textColor) {
            getSpan().setTextColor(textColor);
            notifyChanged(false);
        }
    }

    public void setBackgroundColor(int backgroundColor) {
        if (!isVirtual()) {
            super.setBackgroundColor(backgroundColor);
        } else if (this.mFontStylingSpan.getBackgroundColor() != backgroundColor) {
            getSpan().setBackgroundColor(backgroundColor);
            notifyChanged(false);
        }
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(String fontFamily) {
        if (!TextUtils.equals(this.mFontStylingSpan.getFontFamily(), fontFamily)) {
            getSpan().setFontFamily(fontFamily);
            notifyChanged(true);
        }
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(String fontWeightString) {
        int fontWeight;
        if (fontWeightString == null) {
            fontWeight = -1;
        } else if (BOLD.equals(fontWeightString)) {
            fontWeight = 1;
        } else if (NORMAL.equals(fontWeightString)) {
            fontWeight = 0;
        } else {
            int fontWeightNumeric = parseNumericFontWeight(fontWeightString);
            if (fontWeightNumeric == -1) {
                throw new RuntimeException("invalid font weight " + fontWeightString);
            }
            fontWeight = fontWeightNumeric >= 500 ? 1 : 0;
        }
        if (this.mFontStylingSpan.getFontWeight() != fontWeight) {
            getSpan().setFontWeight(fontWeight);
            notifyChanged(true);
        }
    }

    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(String textDecorationLineString) {
        String[] split;
        boolean isUnderlineTextDecorationSet = false;
        boolean isLineThroughTextDecorationSet = false;
        if (textDecorationLineString != null) {
            for (String textDecorationLineSubString : textDecorationLineString.split(" ")) {
                if ("underline".equals(textDecorationLineSubString)) {
                    isUnderlineTextDecorationSet = true;
                } else if ("line-through".equals(textDecorationLineSubString)) {
                    isLineThroughTextDecorationSet = true;
                }
            }
        }
        if (isUnderlineTextDecorationSet != this.mFontStylingSpan.hasUnderline() || isLineThroughTextDecorationSet != this.mFontStylingSpan.hasStrikeThrough()) {
            FontStylingSpan span = getSpan();
            span.setHasUnderline(isUnderlineTextDecorationSet);
            span.setHasStrikeThrough(isLineThroughTextDecorationSet);
            notifyChanged(true);
        }
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(String fontStyleString) {
        int fontStyle;
        if (fontStyleString == null) {
            fontStyle = -1;
        } else if (ITALIC.equals(fontStyleString)) {
            fontStyle = 2;
        } else if (NORMAL.equals(fontStyleString)) {
            fontStyle = 0;
        } else {
            throw new RuntimeException("invalid font style " + fontStyleString);
        }
        if (this.mFontStylingSpan.getFontStyle() != fontStyle) {
            getSpan().setFontStyle(fontStyle);
            notifyChanged(true);
        }
    }

    @ReactProp(name = "textShadowOffset")
    public void setTextShadowOffset(ReadableMap offsetMap) {
        float dx = 0.0f;
        float dy = 0.0f;
        if (offsetMap != null) {
            if (offsetMap.hasKey("width")) {
                dx = PixelUtil.toPixelFromDIP(offsetMap.getDouble("width"));
            }
            if (offsetMap.hasKey("height")) {
                dy = PixelUtil.toPixelFromDIP(offsetMap.getDouble("height"));
            }
        }
        if (!this.mShadowStyleSpan.offsetMatches(dx, dy)) {
            getShadowSpan().setOffset(dx, dy);
            notifyChanged(false);
        }
    }

    @ReactProp(name = "textShadowRadius")
    public void setTextShadowRadius(float textShadowRadius) {
        float textShadowRadius2 = PixelUtil.toPixelFromDIP(textShadowRadius);
        if (this.mShadowStyleSpan.getRadius() != textShadowRadius2) {
            getShadowSpan().setRadius(textShadowRadius2);
            notifyChanged(false);
        }
    }

    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public void setTextShadowColor(int textShadowColor) {
        if (this.mShadowStyleSpan.getColor() != textShadowColor) {
            getShadowSpan().setColor(textShadowColor);
            notifyChanged(false);
        }
    }

    /* access modifiers changed from: protected */
    public final int getFontSize() {
        return this.mFontStylingSpan.getFontSize();
    }

    /* access modifiers changed from: protected */
    public final int getFontStyle() {
        int style = this.mFontStylingSpan.getFontStyle();
        if (style >= 0) {
            return style;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getDefaultFontSize() {
        return -1;
    }

    static int fontSizeFromSp(float sp) {
        return (int) Math.ceil((double) PixelUtil.toPixelFromSP(sp));
    }

    /* access modifiers changed from: protected */
    public final FontStylingSpan getSpan() {
        if (this.mFontStylingSpan.isFrozen()) {
            this.mFontStylingSpan = this.mFontStylingSpan.mutableCopy();
        }
        return this.mFontStylingSpan;
    }

    /* access modifiers changed from: 0000 */
    public final SpannableStringBuilder getText() {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        collectText(sb);
        applySpans(sb, isEditable());
        return sb;
    }

    private final ShadowStyleSpan getShadowSpan() {
        if (this.mShadowStyleSpan.isFrozen()) {
            this.mShadowStyleSpan = this.mShadowStyleSpan.mutableCopy();
        }
        return this.mShadowStyleSpan;
    }

    private static int parseNumericFontWeight(String fontWeightString) {
        if (fontWeightString.length() != 3 || !fontWeightString.endsWith("00") || fontWeightString.charAt(0) > '9' || fontWeightString.charAt(0) < '1') {
            return -1;
        }
        return (fontWeightString.charAt(0) - '0') * 100;
    }
}
