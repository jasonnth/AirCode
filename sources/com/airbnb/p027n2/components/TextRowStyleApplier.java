package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.TextRowStyleApplier */
public final class TextRowStyleApplier extends StyleApplier<TextRowStyleApplier, TextRow> {
    public TextRowStyleApplier(TextRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_TextRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((TextRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_TextRow_n2_textStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_TextRow_n2_textStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((TextRow) getView()).textView).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_TextRow_n2_expandable)) {
            ((TextRow) getView()).setExpandable(a.getBoolean(R.styleable.n2_TextRow_n2_expandable, false));
        }
        if (a.hasValue(R.styleable.n2_TextRow_n2_maxLines)) {
            ((TextRow) getView()).setMaxLines(a.getInt(R.styleable.n2_TextRow_n2_maxLines, -1));
        }
        if (a.hasValue(R.styleable.n2_TextRow_n2_readMoreText)) {
            ((TextRow) getView()).setReadMoreText(a.getText(R.styleable.n2_TextRow_n2_readMoreText));
        }
        if (a.hasValue(R.styleable.n2_TextRow_n2_text)) {
            ((TextRow) getView()).setText(a.getText(R.styleable.n2_TextRow_n2_text));
        }
        if (a.hasValue(R.styleable.n2_TextRow_n2_contentText)) {
            ((TextRow) getView()).setContentText(a.getText(R.styleable.n2_TextRow_n2_contentText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
