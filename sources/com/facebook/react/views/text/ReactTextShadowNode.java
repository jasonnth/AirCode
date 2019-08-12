package com.facebook.react.views.text;

import android.os.Build.VERSION;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaMeasureFunction;
import java.util.ArrayList;
import java.util.List;

public class ReactTextShadowNode extends LayoutShadowNode {
    public static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String INLINE_IMAGE_PLACEHOLDER = "I";
    public static final String PROP_SHADOW_COLOR = "textShadowColor";
    public static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    public static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    public static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    public static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    @VisibleForTesting
    public static final String PROP_TEXT = "text";
    public static final int UNSET = -1;
    /* access modifiers changed from: private */
    public static final TextPaint sTextPaintInstance = new TextPaint();
    private boolean mAllowFontScaling = true;
    private int mBackgroundColor;
    private int mColor;
    protected boolean mContainsImages;
    private String mFontFamily;
    protected int mFontSize = -1;
    protected float mFontSizeInput = -1.0f;
    private int mFontStyle;
    private int mFontWeight;
    private float mHeightOfTallestInlineImage;
    private boolean mIsBackgroundColorSet = false;
    private boolean mIsColorSet = false;
    private boolean mIsLineThroughTextDecorationSet;
    private boolean mIsUnderlineTextDecorationSet;
    private float mLetterSpacing = Float.NaN;
    private float mLineHeight = Float.NaN;
    protected int mLineHeightInput = -1;
    protected int mNumberOfLines = -1;
    /* access modifiers changed from: private */
    public Spannable mPreparedSpannableText;
    private String mText;
    protected int mTextAlign = 0;
    protected int mTextBreakStrategy;
    private final YogaMeasureFunction mTextMeasureFunction = new YogaMeasureFunction() {
        /* JADX WARNING: type inference failed for: r2v2, types: [android.text.BoringLayout] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long measure(com.facebook.yoga.YogaNodeAPI r24, float r25, com.facebook.yoga.YogaMeasureMode r26, float r27, com.facebook.yoga.YogaMeasureMode r28) {
            /*
                r23 = this;
                android.text.TextPaint r4 = com.facebook.react.views.text.ReactTextShadowNode.sTextPaintInstance
                r0 = r23
                com.facebook.react.views.text.ReactTextShadowNode r6 = com.facebook.react.views.text.ReactTextShadowNode.this
                android.text.Spannable r6 = r6.mPreparedSpannableText
                java.lang.String r7 = "Spannable element has not been prepared in onBeforeLayout"
                java.lang.Object r3 = com.facebook.infer.annotation.Assertions.assertNotNull(r6, r7)
                android.text.Spanned r3 = (android.text.Spanned) r3
                android.text.BoringLayout$Metrics r12 = android.text.BoringLayout.isBoring(r3, r4)
                if (r12 != 0) goto L_0x007d
                float r21 = android.text.Layout.getDesiredWidth(r3, r4)
            L_0x001f:
                com.facebook.yoga.YogaMeasureMode r6 = com.facebook.yoga.YogaMeasureMode.UNDEFINED
                r0 = r26
                if (r0 == r6) goto L_0x002a
                r6 = 0
                int r6 = (r25 > r6 ? 1 : (r25 == r6 ? 0 : -1))
                if (r6 >= 0) goto L_0x0080
            L_0x002a:
                r22 = 1
            L_0x002c:
                if (r12 != 0) goto L_0x00b2
                if (r22 != 0) goto L_0x003a
                boolean r6 = com.facebook.yoga.YogaConstants.isUndefined(r21)
                if (r6 != 0) goto L_0x00b2
                int r6 = (r21 > r25 ? 1 : (r21 == r25 ? 0 : -1))
                if (r6 > 0) goto L_0x00b2
            L_0x003a:
                r0 = r21
                double r6 = (double) r0
                double r6 = java.lang.Math.ceil(r6)
                int r5 = (int) r6
                int r6 = android.os.Build.VERSION.SDK_INT
                r7 = 23
                if (r6 >= r7) goto L_0x0083
                android.text.StaticLayout r2 = new android.text.StaticLayout
                android.text.Layout$Alignment r6 = android.text.Layout.Alignment.ALIGN_NORMAL
                r7 = 1065353216(0x3f800000, float:1.0)
                r8 = 0
                r9 = 1
                r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            L_0x0053:
                r0 = r23
                com.facebook.react.views.text.ReactTextShadowNode r6 = com.facebook.react.views.text.ReactTextShadowNode.this
                int r6 = r6.mNumberOfLines
                r7 = -1
                if (r6 == r7) goto L_0x011c
                r0 = r23
                com.facebook.react.views.text.ReactTextShadowNode r6 = com.facebook.react.views.text.ReactTextShadowNode.this
                int r6 = r6.mNumberOfLines
                int r7 = r2.getLineCount()
                if (r6 >= r7) goto L_0x011c
                int r6 = r2.getWidth()
                r0 = r23
                com.facebook.react.views.text.ReactTextShadowNode r7 = com.facebook.react.views.text.ReactTextShadowNode.this
                int r7 = r7.mNumberOfLines
                int r7 = r7 + -1
                int r7 = r2.getLineBottom(r7)
                long r6 = com.facebook.yoga.YogaMeasureOutput.make(r6, r7)
            L_0x007c:
                return r6
            L_0x007d:
                r21 = 2143289344(0x7fc00000, float:NaN)
                goto L_0x001f
            L_0x0080:
                r22 = 0
                goto L_0x002c
            L_0x0083:
                r6 = 0
                int r7 = r3.length()
                android.text.StaticLayout$Builder r6 = android.text.StaticLayout.Builder.obtain(r3, r6, r7, r4, r5)
                android.text.Layout$Alignment r7 = android.text.Layout.Alignment.ALIGN_NORMAL
                android.text.StaticLayout$Builder r6 = r6.setAlignment(r7)
                r7 = 0
                r8 = 1065353216(0x3f800000, float:1.0)
                android.text.StaticLayout$Builder r6 = r6.setLineSpacing(r7, r8)
                r7 = 1
                android.text.StaticLayout$Builder r6 = r6.setIncludePad(r7)
                r0 = r23
                com.facebook.react.views.text.ReactTextShadowNode r7 = com.facebook.react.views.text.ReactTextShadowNode.this
                int r7 = r7.mTextBreakStrategy
                android.text.StaticLayout$Builder r6 = r6.setBreakStrategy(r7)
                r7 = 1
                android.text.StaticLayout$Builder r6 = r6.setHyphenationFrequency(r7)
                android.text.StaticLayout r2 = r6.build()
                goto L_0x0053
            L_0x00b2:
                if (r12 == 0) goto L_0x00cc
                if (r22 != 0) goto L_0x00bd
                int r6 = r12.width
                float r6 = (float) r6
                int r6 = (r6 > r25 ? 1 : (r6 == r25 ? 0 : -1))
                if (r6 > 0) goto L_0x00cc
            L_0x00bd:
                int r8 = r12.width
                android.text.Layout$Alignment r9 = android.text.Layout.Alignment.ALIGN_NORMAL
                r10 = 1065353216(0x3f800000, float:1.0)
                r11 = 0
                r13 = 1
                r6 = r3
                r7 = r4
                android.text.BoringLayout r2 = android.text.BoringLayout.make(r6, r7, r8, r9, r10, r11, r12, r13)
                goto L_0x0053
            L_0x00cc:
                int r6 = android.os.Build.VERSION.SDK_INT
                r7 = 23
                if (r6 >= r7) goto L_0x00e9
                android.text.StaticLayout r2 = new android.text.StaticLayout
                r0 = r25
                int r0 = (int) r0
                r16 = r0
                android.text.Layout$Alignment r17 = android.text.Layout.Alignment.ALIGN_NORMAL
                r18 = 1065353216(0x3f800000, float:1.0)
                r19 = 0
                r20 = 1
                r13 = r2
                r14 = r3
                r15 = r4
                r13.<init>(r14, r15, r16, r17, r18, r19, r20)
                goto L_0x0053
            L_0x00e9:
                r6 = 0
                int r7 = r3.length()
                r0 = r25
                int r8 = (int) r0
                android.text.StaticLayout$Builder r6 = android.text.StaticLayout.Builder.obtain(r3, r6, r7, r4, r8)
                android.text.Layout$Alignment r7 = android.text.Layout.Alignment.ALIGN_NORMAL
                android.text.StaticLayout$Builder r6 = r6.setAlignment(r7)
                r7 = 0
                r8 = 1065353216(0x3f800000, float:1.0)
                android.text.StaticLayout$Builder r6 = r6.setLineSpacing(r7, r8)
                r7 = 1
                android.text.StaticLayout$Builder r6 = r6.setIncludePad(r7)
                r0 = r23
                com.facebook.react.views.text.ReactTextShadowNode r7 = com.facebook.react.views.text.ReactTextShadowNode.this
                int r7 = r7.mTextBreakStrategy
                android.text.StaticLayout$Builder r6 = r6.setBreakStrategy(r7)
                r7 = 1
                android.text.StaticLayout$Builder r6 = r6.setHyphenationFrequency(r7)
                android.text.StaticLayout r2 = r6.build()
                goto L_0x0053
            L_0x011c:
                int r6 = r2.getWidth()
                int r7 = r2.getHeight()
                long r6 = com.facebook.yoga.YogaMeasureOutput.make(r6, r7)
                goto L_0x007c
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextShadowNode.C38781.measure(com.facebook.yoga.YogaNodeAPI, float, com.facebook.yoga.YogaMeasureMode, float, com.facebook.yoga.YogaMeasureMode):long");
        }
    };
    private int mTextShadowColor;
    private float mTextShadowOffsetDx;
    private float mTextShadowOffsetDy;
    private float mTextShadowRadius;

    private static class SetSpanOperation {
        protected int end;
        protected int start;
        protected Object what;

        SetSpanOperation(int start2, int end2, Object what2) {
            this.start = start2;
            this.end = end2;
            this.what = what2;
        }

        public void execute(SpannableStringBuilder sb) {
            int spanFlags = 34;
            if (this.start == 0) {
                spanFlags = 18;
            }
            sb.setSpan(this.what, this.start, this.end, spanFlags);
        }
    }

    static {
        sTextPaintInstance.setFlags(1);
    }

    private static void buildSpannedFromTextCSSNode(ReactTextShadowNode textShadowNode, SpannableStringBuilder sb, List<SetSpanOperation> ops) {
        int start = sb.length();
        if (textShadowNode.mText != null) {
            sb.append(textShadowNode.mText);
        }
        int length = textShadowNode.getChildCount();
        for (int i = 0; i < length; i++) {
            ReactShadowNode child = textShadowNode.getChildAt(i);
            if (child instanceof ReactTextShadowNode) {
                buildSpannedFromTextCSSNode((ReactTextShadowNode) child, sb, ops);
            } else if (child instanceof ReactTextInlineImageShadowNode) {
                sb.append(INLINE_IMAGE_PLACEHOLDER);
                ops.add(new SetSpanOperation(sb.length() - INLINE_IMAGE_PLACEHOLDER.length(), sb.length(), ((ReactTextInlineImageShadowNode) child).buildInlineImageSpan()));
            } else {
                throw new IllegalViewOperationException("Unexpected view type nested under text node: " + child.getClass());
            }
            child.markUpdateSeen();
        }
        int end = sb.length();
        if (end >= start) {
            if (textShadowNode.mIsColorSet) {
                ops.add(new SetSpanOperation(start, end, new ForegroundColorSpan(textShadowNode.mColor)));
            }
            if (textShadowNode.mIsBackgroundColorSet) {
                ops.add(new SetSpanOperation(start, end, new BackgroundColorSpan(textShadowNode.mBackgroundColor)));
            }
            if (textShadowNode.mFontSize != -1) {
                ops.add(new SetSpanOperation(start, end, new AbsoluteSizeSpan(textShadowNode.mFontSize)));
            }
            if (!(textShadowNode.mFontStyle == -1 && textShadowNode.mFontWeight == -1 && textShadowNode.mFontFamily == null)) {
                ops.add(new SetSpanOperation(start, end, new CustomStyleSpan(textShadowNode.mFontStyle, textShadowNode.mFontWeight, textShadowNode.mFontFamily, textShadowNode.getThemedContext().getAssets())));
            }
            if (textShadowNode.mIsUnderlineTextDecorationSet) {
                ops.add(new SetSpanOperation(start, end, new UnderlineSpan()));
            }
            if (textShadowNode.mIsLineThroughTextDecorationSet) {
                ops.add(new SetSpanOperation(start, end, new StrikethroughSpan()));
            }
            if (!(textShadowNode.mTextShadowOffsetDx == 0.0f && textShadowNode.mTextShadowOffsetDy == 0.0f)) {
                ops.add(new SetSpanOperation(start, end, new ShadowStyleSpan(textShadowNode.mTextShadowOffsetDx, textShadowNode.mTextShadowOffsetDy, textShadowNode.mTextShadowRadius, textShadowNode.mTextShadowColor)));
            }
            if (!Float.isNaN(textShadowNode.getEffectiveLineHeight())) {
                ops.add(new SetSpanOperation(start, end, new CustomLineHeightSpan(textShadowNode.getEffectiveLineHeight())));
            }
            ops.add(new SetSpanOperation(start, end, new ReactTagSpan(textShadowNode.getReactTag())));
        }
    }

    protected static Spannable fromTextCSSNode(ReactTextShadowNode textCSSNode) {
        int ceil;
        SpannableStringBuilder sb = new SpannableStringBuilder();
        List<SetSpanOperation> ops = new ArrayList<>();
        buildSpannedFromTextCSSNode(textCSSNode, sb, ops);
        if (textCSSNode.mFontSize == -1) {
            if (textCSSNode.mAllowFontScaling) {
                ceil = (int) Math.ceil((double) PixelUtil.toPixelFromSP(14.0f));
            } else {
                ceil = (int) Math.ceil((double) PixelUtil.toPixelFromDIP(14.0f));
            }
            sb.setSpan(new AbsoluteSizeSpan(ceil), 0, sb.length(), 17);
        }
        textCSSNode.mContainsImages = false;
        textCSSNode.mHeightOfTallestInlineImage = Float.NaN;
        for (int i = ops.size() - 1; i >= 0; i--) {
            SetSpanOperation op = (SetSpanOperation) ops.get(i);
            if (op.what instanceof TextInlineImageSpan) {
                int height = ((TextInlineImageSpan) op.what).getHeight();
                textCSSNode.mContainsImages = true;
                if (Float.isNaN(textCSSNode.mHeightOfTallestInlineImage) || ((float) height) > textCSSNode.mHeightOfTallestInlineImage) {
                    textCSSNode.mHeightOfTallestInlineImage = (float) height;
                }
            }
            op.execute(sb);
        }
        return sb;
    }

    private static int parseNumericFontWeight(String fontWeightString) {
        if (fontWeightString.length() != 3 || !fontWeightString.endsWith("00") || fontWeightString.charAt(0) > '9' || fontWeightString.charAt(0) < '1') {
            return -1;
        }
        return (fontWeightString.charAt(0) - '0') * 100;
    }

    public ReactTextShadowNode() {
        int i = 1;
        if (VERSION.SDK_INT < 23) {
            i = 0;
        }
        this.mTextBreakStrategy = i;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        this.mTextShadowRadius = 1.0f;
        this.mTextShadowColor = DEFAULT_TEXT_SHADOW_COLOR;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        this.mFontStyle = -1;
        this.mFontWeight = -1;
        this.mFontFamily = null;
        this.mText = null;
        this.mContainsImages = false;
        this.mHeightOfTallestInlineImage = Float.NaN;
        if (!isVirtual()) {
            setMeasureFunction(this.mTextMeasureFunction);
        }
    }

    public float getLetterSpacing() {
        return this.mLetterSpacing;
    }

    public int getFontSize() {
        return this.mFontSize;
    }

    public float getEffectiveLineHeight() {
        return !Float.isNaN(this.mLineHeight) && !Float.isNaN(this.mHeightOfTallestInlineImage) && (this.mHeightOfTallestInlineImage > this.mLineHeight ? 1 : (this.mHeightOfTallestInlineImage == this.mLineHeight ? 0 : -1)) > 0 ? this.mHeightOfTallestInlineImage : this.mLineHeight;
    }

    private int getTextAlign() {
        int textAlign = this.mTextAlign;
        if (getLayoutDirection() != YogaDirection.RTL) {
            return textAlign;
        }
        if (textAlign == 5) {
            return 3;
        }
        if (textAlign == 3) {
            return 5;
        }
        return textAlign;
    }

    public void onBeforeLayout() {
        if (!isVirtual()) {
            this.mPreparedSpannableText = fromTextCSSNode(this);
            markUpdated();
        }
    }

    public void markUpdated() {
        super.markUpdated();
        if (!isVirtual()) {
            super.dirty();
        }
    }

    @ReactProp(name = "text")
    public void setText(String text) {
        this.mText = text;
        markUpdated();
    }

    @ReactProp(defaultInt = -1, name = "numberOfLines")
    public void setNumberOfLines(int numberOfLines) {
        if (numberOfLines == 0) {
            numberOfLines = -1;
        }
        this.mNumberOfLines = numberOfLines;
        markUpdated();
    }

    @ReactProp(defaultInt = -1, name = "lineHeight")
    public void setLineHeight(int lineHeight) {
        this.mLineHeightInput = lineHeight;
        if (lineHeight == -1) {
            this.mLineHeight = Float.NaN;
        } else {
            this.mLineHeight = this.mAllowFontScaling ? PixelUtil.toPixelFromSP((float) lineHeight) : PixelUtil.toPixelFromDIP((float) lineHeight);
        }
        markUpdated();
    }

    @ReactProp(defaultBoolean = true, name = "allowFontScaling")
    public void setAllowFontScaling(boolean allowFontScaling) {
        if (allowFontScaling != this.mAllowFontScaling) {
            this.mAllowFontScaling = allowFontScaling;
            setFontSize(this.mFontSizeInput);
            setLineHeight(this.mLineHeightInput);
            markUpdated();
        }
    }

    @ReactProp(defaultFloat = -1.0f, name = "letterSpacing")
    public void setLetterSpacing(float letterSpacing) {
        if (letterSpacing == -1.0f) {
            letterSpacing = Float.NaN;
        }
        this.mLetterSpacing = letterSpacing;
        markUpdated();
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(String textAlign) {
        if (textAlign == null || "auto".equals(textAlign)) {
            this.mTextAlign = 0;
        } else if (ViewProps.LEFT.equals(textAlign)) {
            this.mTextAlign = 3;
        } else if (ViewProps.RIGHT.equals(textAlign)) {
            this.mTextAlign = 5;
        } else if ("center".equals(textAlign)) {
            this.mTextAlign = 1;
        } else if ("justify".equals(textAlign)) {
            this.mTextAlign = 3;
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + textAlign);
        }
        markUpdated();
    }

    @ReactProp(defaultFloat = -1.0f, name = "fontSize")
    public void setFontSize(float fontSize) {
        this.mFontSizeInput = fontSize;
        if (fontSize != -1.0f) {
            if (this.mAllowFontScaling) {
                fontSize = (float) Math.ceil((double) PixelUtil.toPixelFromSP(fontSize));
            } else {
                fontSize = (float) Math.ceil((double) PixelUtil.toPixelFromDIP(fontSize));
            }
        }
        this.mFontSize = (int) fontSize;
        markUpdated();
    }

    @ReactProp(name = "color")
    public void setColor(Integer color) {
        this.mIsColorSet = color != null;
        if (this.mIsColorSet) {
            this.mColor = color.intValue();
        }
        markUpdated();
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(Integer color) {
        if (!isVirtualAnchor()) {
            this.mIsBackgroundColorSet = color != null;
            if (this.mIsBackgroundColorSet) {
                this.mBackgroundColor = color.intValue();
            }
            markUpdated();
        }
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(String fontFamily) {
        this.mFontFamily = fontFamily;
        markUpdated();
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(String fontWeightString) {
        int fontWeightNumeric;
        if (fontWeightString != null) {
            fontWeightNumeric = parseNumericFontWeight(fontWeightString);
        } else {
            fontWeightNumeric = -1;
        }
        int fontWeight = -1;
        if (fontWeightNumeric >= 500 || "bold".equals(fontWeightString)) {
            fontWeight = 1;
        } else if ("normal".equals(fontWeightString) || (fontWeightNumeric != -1 && fontWeightNumeric < 500)) {
            fontWeight = 0;
        }
        if (fontWeight != this.mFontWeight) {
            this.mFontWeight = fontWeight;
            markUpdated();
        }
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(String fontStyleString) {
        int fontStyle = -1;
        if ("italic".equals(fontStyleString)) {
            fontStyle = 2;
        } else if ("normal".equals(fontStyleString)) {
            fontStyle = 0;
        }
        if (fontStyle != this.mFontStyle) {
            this.mFontStyle = fontStyle;
            markUpdated();
        }
    }

    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(String textDecorationLineString) {
        String[] split;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (textDecorationLineString != null) {
            for (String textDecorationLineSubString : textDecorationLineString.split(" ")) {
                if ("underline".equals(textDecorationLineSubString)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("line-through".equals(textDecorationLineSubString)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
        markUpdated();
    }

    @ReactProp(name = "textBreakStrategy")
    public void setTextBreakStrategy(String textBreakStrategy) {
        if (VERSION.SDK_INT >= 23) {
            if (textBreakStrategy == null || "highQuality".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 1;
            } else if ("simple".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 0;
            } else if ("balanced".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 2;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + textBreakStrategy);
            }
            markUpdated();
        }
    }

    @ReactProp(name = "textShadowOffset")
    public void setTextShadowOffset(ReadableMap offsetMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (offsetMap != null) {
            if (offsetMap.hasKey("width") && !offsetMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(offsetMap.getDouble("width"));
            }
            if (offsetMap.hasKey("height") && !offsetMap.isNull("height")) {
                this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(offsetMap.getDouble("height"));
            }
        }
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "textShadowRadius")
    public void setTextShadowRadius(float textShadowRadius) {
        if (textShadowRadius != this.mTextShadowRadius) {
            this.mTextShadowRadius = textShadowRadius;
            markUpdated();
        }
    }

    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public void setTextShadowColor(int textShadowColor) {
        if (textShadowColor != this.mTextShadowColor) {
            this.mTextShadowColor = textShadowColor;
            markUpdated();
        }
    }

    public boolean isVirtualAnchor() {
        return !isVirtual();
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        if (!isVirtual()) {
            super.onCollectExtraUpdates(uiViewOperationQueue);
            if (this.mPreparedSpannableText != null) {
                uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(this.mPreparedSpannableText, -1, this.mContainsImages, getPadding(4), getPadding(1), getPadding(5), getPadding(3), getLetterSpacing(), getFontSize(), getTextAlign(), this.mTextBreakStrategy));
            }
        }
    }
}
