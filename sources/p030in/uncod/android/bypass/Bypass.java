package p030in.uncod.android.bypass;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.LeadingMarginSpan.Standard;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.util.TypedValue;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p030in.uncod.android.bypass.Element.Type;
import p030in.uncod.android.bypass.style.HorizontalLineSpan;

/* renamed from: in.uncod.android.bypass.Bypass */
public class Bypass {
    private final int mBlockQuoteIndent;
    private final int mCodeBlockIndent;
    private final int mHruleSize;
    private final int mHruleTopBottomPadding;
    private final int mListItemIndent;
    private final Options mOptions;
    private final Map<Element, Integer> mOrderedListNumber;

    /* renamed from: in.uncod.android.bypass.Bypass$ImageGetter */
    public interface ImageGetter {
        Drawable getDrawable(String str);
    }

    /* renamed from: in.uncod.android.bypass.Bypass$Options */
    public static final class Options {
        /* access modifiers changed from: private */
        public int mBlockQuoteColor = -16776961;
        /* access modifiers changed from: private */
        public float mBlockQuoteIndentSize = 10.0f;
        /* access modifiers changed from: private */
        public int mBlockQuoteIndentUnit = 1;
        /* access modifiers changed from: private */
        public float mCodeBlockIndentSize = 10.0f;
        /* access modifiers changed from: private */
        public int mCodeBlockIndentUnit = 1;
        /* access modifiers changed from: private */
        public float[] mHeaderSizes = {1.5f, 1.4f, 1.3f, 1.2f, 1.1f, 1.0f};
        /* access modifiers changed from: private */
        public int mHruleColor = -7829368;
        /* access modifiers changed from: private */
        public float mHruleSize = 1.0f;
        /* access modifiers changed from: private */
        public int mHruleUnit = 1;
        /* access modifiers changed from: private */
        public float mListItemIndentSize = 10.0f;
        /* access modifiers changed from: private */
        public int mListItemIndentUnit = 1;
        /* access modifiers changed from: private */
        public String mUnorderedListItem = "•";

        public Options setHeaderSizes(float[] headerSizes) {
            if (headerSizes == null) {
                throw new IllegalArgumentException("headerSizes must not be null");
            } else if (headerSizes.length != 6) {
                throw new IllegalArgumentException("headerSizes must have 6 elements (h1 through h6)");
            } else {
                this.mHeaderSizes = headerSizes;
                return this;
            }
        }

        public Options setUnorderedListItem(String unorderedListItem) {
            this.mUnorderedListItem = unorderedListItem;
            return this;
        }

        public Options setListItemIndentSize(int unit, float size) {
            this.mListItemIndentUnit = unit;
            this.mListItemIndentSize = size;
            return this;
        }

        public Options setBlockQuoteColor(int color) {
            this.mBlockQuoteColor = color;
            return this;
        }

        public Options setBlockQuoteIndentSize(int unit, float size) {
            this.mBlockQuoteIndentUnit = unit;
            this.mBlockQuoteIndentSize = size;
            return this;
        }

        public Options setCodeBlockIndentSize(int unit, float size) {
            this.mCodeBlockIndentUnit = unit;
            this.mCodeBlockIndentSize = size;
            return this;
        }

        public Options setHruleColor(int color) {
            this.mHruleColor = color;
            return this;
        }

        public Options setHruleSize(int unit, float size) {
            this.mHruleUnit = unit;
            this.mHruleSize = size;
            return this;
        }
    }

    private native Document processMarkdown(String str);

    static {
        System.loadLibrary("bypass");
    }

    @Deprecated
    public Bypass() {
        this.mOrderedListNumber = new ConcurrentHashMap();
        this.mOptions = new Options();
        this.mListItemIndent = 20;
        this.mBlockQuoteIndent = 10;
        this.mCodeBlockIndent = 10;
        this.mHruleSize = 2;
        this.mHruleTopBottomPadding = 20;
    }

    public Bypass(Context context) {
        this(context, new Options());
    }

    public Bypass(Context context, Options options) {
        this.mOrderedListNumber = new ConcurrentHashMap();
        this.mOptions = options;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        this.mListItemIndent = (int) TypedValue.applyDimension(this.mOptions.mListItemIndentUnit, this.mOptions.mListItemIndentSize, dm);
        this.mBlockQuoteIndent = (int) TypedValue.applyDimension(this.mOptions.mBlockQuoteIndentUnit, this.mOptions.mBlockQuoteIndentSize, dm);
        this.mCodeBlockIndent = (int) TypedValue.applyDimension(this.mOptions.mCodeBlockIndentUnit, this.mOptions.mCodeBlockIndentSize, dm);
        this.mHruleSize = (int) TypedValue.applyDimension(this.mOptions.mHruleUnit, this.mOptions.mHruleSize, dm);
        this.mHruleTopBottomPadding = ((int) dm.density) * 10;
    }

    public CharSequence markdownToSpannable(String markdown) {
        return markdownToSpannable(markdown, null);
    }

    public CharSequence markdownToSpannable(String markdown, ImageGetter imageGetter) {
        Document document = processMarkdown(markdown);
        int size = document.getElementCount();
        CharSequence[] spans = new CharSequence[size];
        for (int i = 0; i < size; i++) {
            spans[i] = recurseElement(document.getElement(i), i, size, imageGetter);
        }
        return TextUtils.concat(spans);
    }

    private CharSequence recurseElement(Element element, int indexWithinParent, int numberOfSiblings, ImageGetter imageGetter) {
        Type type = element.getType();
        boolean isOrderedList = false;
        if (type == Type.LIST) {
            String flagsStr = element.getAttribute("flags");
            if (flagsStr != null) {
                isOrderedList = (Integer.parseInt(flagsStr) & 1) != 0;
                if (isOrderedList) {
                    this.mOrderedListNumber.put(element, Integer.valueOf(1));
                }
            }
        }
        int size = element.size();
        CharSequence[] spans = new CharSequence[size];
        for (int i = 0; i < size; i++) {
            spans[i] = recurseElement(element.children[i], i, size, imageGetter);
        }
        if (isOrderedList) {
            this.mOrderedListNumber.remove(this);
        }
        CharSequence concat = TextUtils.concat(spans);
        SpannableStringBuilder builder = new ReverseSpannableStringBuilder();
        String text = element.getText();
        if (!(element.size() != 0 || element.getParent() == null || element.getParent().getType() == Type.BLOCK_CODE)) {
            text = text.replace(10, ' ');
        }
        Drawable imageDrawable = null;
        if (type == Type.IMAGE && imageGetter != null && !TextUtils.isEmpty(element.getAttribute("link"))) {
            imageDrawable = imageGetter.getDrawable(element.getAttribute("link"));
        }
        switch (type) {
            case LIST:
                if (element.getParent() != null && element.getParent().getType() == Type.LIST_ITEM) {
                    builder.append("\n");
                    break;
                }
            case LINEBREAK:
                builder.append("\n");
                break;
            case LIST_ITEM:
                builder.append(" ");
                if (this.mOrderedListNumber.containsKey(element.getParent())) {
                    int number = ((Integer) this.mOrderedListNumber.get(element.getParent())).intValue();
                    builder.append(Integer.toString(number) + ".");
                    this.mOrderedListNumber.put(element.getParent(), Integer.valueOf(number + 1));
                } else {
                    builder.append(this.mOptions.mUnorderedListItem);
                }
                builder.append("  ");
                break;
            case AUTOLINK:
                builder.append(element.getAttribute("link"));
                break;
            case HRULE:
                builder.append("-");
                break;
            case IMAGE:
                if (imageDrawable != null) {
                    builder.append("￼");
                    break;
                } else {
                    String show = element.getAttribute("alt");
                    if (TextUtils.isEmpty(show)) {
                        show = element.getAttribute("title");
                    }
                    if (!TextUtils.isEmpty(show)) {
                        builder.append("[" + show + "]");
                        break;
                    }
                }
                break;
        }
        builder.append(text);
        builder.append(concat);
        if (element.getParent() != null || indexWithinParent < numberOfSiblings - 1) {
            if (type == Type.LIST_ITEM) {
                if (element.size() == 0 || !element.children[element.size() - 1].isBlockElement()) {
                    builder.append("\n");
                }
            } else if (element.isBlockElement() && type != Type.BLOCK_QUOTE) {
                if (type == Type.LIST) {
                    if (element.getParent() == null || element.getParent().getType() != Type.LIST_ITEM) {
                        builder.append("\n");
                    }
                } else if (element.getParent() == null || element.getParent().getType() != Type.LIST_ITEM) {
                    builder.append("\n\n");
                } else {
                    builder.append("\n");
                }
            }
        }
        switch (type) {
            case LIST:
                setBlockSpan(builder, new Standard(this.mListItemIndent));
                break;
            case AUTOLINK:
            case LINK:
                String link = element.getAttribute("link");
                if (!TextUtils.isEmpty(link) && Patterns.EMAIL_ADDRESS.matcher(link).matches()) {
                    link = "mailto:" + link;
                }
                URLSpan uRLSpan = new URLSpan(link);
                setSpan(builder, uRLSpan);
                break;
            case HRULE:
                setSpan(builder, new HorizontalLineSpan(this.mOptions.mHruleColor, this.mHruleSize, this.mHruleTopBottomPadding));
                break;
            case IMAGE:
                if (imageDrawable != null) {
                    ImageSpan imageSpan = new ImageSpan(imageDrawable);
                    setSpan(builder, imageSpan);
                    break;
                }
                break;
            case HEADER:
                setSpan(builder, new RelativeSizeSpan(this.mOptions.mHeaderSizes[Integer.parseInt(element.getAttribute("level")) - 1]));
                setSpan(builder, new StyleSpan(1));
                break;
            case EMPHASIS:
                setSpan(builder, new StyleSpan(2));
                break;
            case DOUBLE_EMPHASIS:
                setSpan(builder, new StyleSpan(1));
                break;
            case TRIPLE_EMPHASIS:
                setSpan(builder, new StyleSpan(3));
                break;
            case BLOCK_CODE:
                setSpan(builder, new Standard(this.mCodeBlockIndent));
                setSpan(builder, new TypefaceSpan("monospace"));
                break;
            case CODE_SPAN:
                setSpan(builder, new TypefaceSpan("monospace"));
                break;
            case BLOCK_QUOTE:
                setBlockSpan(builder, new Standard(this.mBlockQuoteIndent));
                setBlockSpan(builder, new QuoteSpan(this.mOptions.mBlockQuoteColor));
                setBlockSpan(builder, new Standard(this.mBlockQuoteIndent));
                setBlockSpan(builder, new StyleSpan(2));
                break;
            case STRIKETHROUGH:
                setSpan(builder, new StrikethroughSpan());
                break;
        }
        return builder;
    }

    private static void setSpan(SpannableStringBuilder builder, Object what) {
        builder.setSpan(what, 0, builder.length(), 33);
    }

    private static void setBlockSpan(SpannableStringBuilder builder, Object what) {
        builder.setSpan(what, 0, Math.max(0, builder.length() - 1), 33);
    }
}
