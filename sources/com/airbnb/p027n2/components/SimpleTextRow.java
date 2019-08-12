package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.text.util.LinkifyCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.MovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontStyleApplier;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.paris.Style;

/* renamed from: com.airbnb.n2.components.SimpleTextRow */
public class SimpleTextRow extends BaseDividerComponent {
    @BindView
    AirTextView textView;

    public SimpleTextRow(Context context) {
        super(context);
    }

    public SimpleTextRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleTextRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_simple_text_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_SimpleTextRow);
        String text = a.getString(R.styleable.n2_SimpleTextRow_n2_text);
        int textAppearanceRes = a.getResourceId(R.styleable.n2_SimpleTextRow_n2_textAppearance, 0);
        setText((CharSequence) text);
        setTextAppearance(textAppearanceRes);
        a.recycle();
        new FontStyleApplier(this.textView).apply(new Style(attrs));
    }

    public void setText(CharSequence body) {
        this.textView.setText(body);
    }

    public void setText(int strRes) {
        this.textView.setText(strRes);
    }

    public void setTextIsSelectable(boolean selectable) {
        this.textView.setTextIsSelectable(selectable);
    }

    public void setMovementMethod(MovementMethod method) {
        if (method != null) {
            this.textView.setMovementMethod(method);
        }
    }

    public void setFont(Font font) {
        this.textView.setFont(font);
    }

    public void setupLinkedText(CharSequence fullText, CharSequence linkedText, int color, LinkOnClickListener listener) {
        ClickableLinkUtils.setupClickableTextView(this.textView, new SpannableString(TextUtil.fromHtmlSafe(fullText.toString())).toString(), linkedText.toString(), ContextCompat.getColor(getContext(), color), R.color.n2_canonical_press_darken, listener, true);
    }

    public void setupColoredTextWithRegularDescription(CharSequence FirstParagraphToColor, CharSequence secondParagraph, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString spannable = new SpannableString(FirstParagraphToColor);
        spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), color)), 0, FirstParagraphToColor.length(), 17);
        builder.append(spannable);
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        builder.append(secondParagraph);
        this.textView.setText(builder);
    }

    private void setTextAppearance(int styleId) {
        if (styleId != 0) {
            this.textView.setTextAppearance(getContext(), styleId);
        }
    }

    public void linkifyText(int linkMask) {
        LinkifyCompat.addLinks((TextView) this.textView, linkMask);
    }

    public static void mock(SimpleTextRow view) {
        view.setText((CharSequence) "Text");
    }
}
