package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.InlineInputRowStyleApplier */
public final class InlineInputRowStyleApplier extends StyleApplier<InlineInputRowStyleApplier, InlineInputRow> {
    public InlineInputRowStyleApplier(InlineInputRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_InlineInputRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((InlineInputRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_titleStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_InlineInputRow_n2_titleStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((InlineInputRow) getView()).titleText).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_subtitleStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_InlineInputRow_n2_subtitleStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((InlineInputRow) getView()).subTitleText).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_inputStyle)) {
            Style subStyle3 = new Style(a.getResourceId(R.styleable.n2_InlineInputRow_n2_inputStyle, -1));
            subStyle3.setDebugListener(style.getDebugListener());
            Paris.style(((InlineInputRow) getView()).editText).apply(subStyle3);
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_dividerStyle)) {
            Style subStyle4 = new Style(a.getResourceId(R.styleable.n2_InlineInputRow_n2_dividerStyle, -1));
            subStyle4.setDebugListener(style.getDebugListener());
            Paris.style(((InlineInputRow) getView()).divider).apply(subStyle4);
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_errorTextStyle)) {
            Style subStyle5 = new Style(a.getResourceId(R.styleable.n2_InlineInputRow_n2_errorTextStyle, -1));
            subStyle5.setDebugListener(style.getDebugListener());
            Paris.style(((InlineInputRow) getView()).error).apply(subStyle5);
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_titleText)) {
            ((InlineInputRow) getView()).setTitle(a.getText(R.styleable.n2_InlineInputRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_subtitleText)) {
            ((InlineInputRow) getView()).setSubTitleText(a.getText(R.styleable.n2_InlineInputRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_hintText)) {
            ((InlineInputRow) getView()).setHint(a.getText(R.styleable.n2_InlineInputRow_n2_hintText));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_inputText)) {
            ((InlineInputRow) getView()).setInputText(a.getText(R.styleable.n2_InlineInputRow_n2_inputText));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_maxLines)) {
            ((InlineInputRow) getView()).setMaxLines(a.getInt(R.styleable.n2_InlineInputRow_n2_maxLines, -1));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_errorDismissal)) {
            ((InlineInputRow) getView()).setErrorDismissal(a.getInt(R.styleable.n2_InlineInputRow_n2_errorDismissal, -1));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_normalStyle)) {
            ((InlineInputRow) getView()).setNormalStyle(a.getResourceId(R.styleable.n2_InlineInputRow_n2_normalStyle, -1));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_errorStyle)) {
            ((InlineInputRow) getView()).setErrorStyle(a.getResourceId(R.styleable.n2_InlineInputRow_n2_errorStyle, -1));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_defaultDrawable)) {
            ((InlineInputRow) getView()).setDefaultDrawable(a.getResourceId(R.styleable.n2_InlineInputRow_n2_defaultDrawable, -1));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_eraseDrawable)) {
            ((InlineInputRow) getView()).setEraseDrawable(a.getResourceId(R.styleable.n2_InlineInputRow_n2_eraseDrawable, -1));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_errorDrawable)) {
            ((InlineInputRow) getView()).setErrorDrawable(a.getResourceId(R.styleable.n2_InlineInputRow_n2_errorDrawable, -1));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_removeHintOnFocus)) {
            ((InlineInputRow) getView()).setRemoveHintOnFocus(a.getBoolean(R.styleable.n2_InlineInputRow_n2_removeHintOnFocus, false));
        }
        if (a.hasValue(R.styleable.n2_InlineInputRow_n2_autoHideTipOnInputChange)) {
            ((InlineInputRow) getView()).setAutoHideTipOnInputChange(a.getBoolean(R.styleable.n2_InlineInputRow_n2_autoHideTipOnInputChange, false));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseComponentStyleApplier((BaseComponent) getView()).apply(style);
    }
}
