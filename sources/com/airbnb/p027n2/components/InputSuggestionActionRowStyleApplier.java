package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.InputSuggestionActionRowStyleApplier */
public final class InputSuggestionActionRowStyleApplier extends StyleApplier<InputSuggestionActionRowStyleApplier, InputSuggestionActionRow> {
    public InputSuggestionActionRowStyleApplier(InputSuggestionActionRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_InputSuggestionActionRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((InputSuggestionActionRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_InputSuggestionActionRow_n2_titleStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_InputSuggestionActionRow_n2_titleStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((InputSuggestionActionRow) getView()).title).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_InputSuggestionActionRow_n2_subtitleStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_InputSuggestionActionRow_n2_subtitleStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((InputSuggestionActionRow) getView()).subtitle).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_InputSuggestionActionRow_n2_labelStyle)) {
            Style subStyle3 = new Style(a.getResourceId(R.styleable.n2_InputSuggestionActionRow_n2_labelStyle, -1));
            subStyle3.setDebugListener(style.getDebugListener());
            Paris.style(((InputSuggestionActionRow) getView()).label).apply(subStyle3);
        }
        if (a.hasValue(R.styleable.n2_InputSuggestionActionRow_n2_titleText)) {
            ((InputSuggestionActionRow) getView()).setTitle(a.getText(R.styleable.n2_InputSuggestionActionRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_InputSuggestionActionRow_n2_subtitleText)) {
            ((InputSuggestionActionRow) getView()).setSubtitle(a.getText(R.styleable.n2_InputSuggestionActionRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_InputSuggestionActionRow_n2_labelText)) {
            ((InputSuggestionActionRow) getView()).setLabel(a.getText(R.styleable.n2_InputSuggestionActionRow_n2_labelText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }

    public InputSuggestionActionRowStyleApplier applyInverse() {
        return (InputSuggestionActionRowStyleApplier) apply(R.style.n2_InputSuggestionActionRow_Inverse);
    }
}
