package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextViewStyleApplier;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.EditorialMarqueeStyleApplier */
public final class EditorialMarqueeStyleApplier extends StyleApplier<EditorialMarqueeStyleApplier, EditorialMarquee> {
    public EditorialMarqueeStyleApplier(EditorialMarquee view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_EditorialMarquee;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((EditorialMarquee) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_EditorialMarquee_n2_kickerStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_EditorialMarquee_n2_kickerStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((EditorialMarquee) getView()).kickerTextView).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_EditorialMarquee_n2_titleStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_EditorialMarquee_n2_titleStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((EditorialMarquee) getView()).titleTextView).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_EditorialMarquee_n2_descriptionStyle)) {
            Style subStyle3 = new Style(a.getResourceId(R.styleable.n2_EditorialMarquee_n2_descriptionStyle, -1));
            subStyle3.setDebugListener(style.getDebugListener());
            Paris.style(((EditorialMarquee) getView()).descriptionTextView).apply(subStyle3);
        }
        if (a.hasValue(R.styleable.n2_EditorialMarquee_n2_image)) {
            ((EditorialMarquee) getView()).setImage(a.getDrawable(R.styleable.n2_EditorialMarquee_n2_image));
        }
        if (a.hasValue(R.styleable.n2_EditorialMarquee_n2_kickerText)) {
            ((EditorialMarquee) getView()).setKicker(a.getText(R.styleable.n2_EditorialMarquee_n2_kickerText));
        }
        if (a.hasValue(R.styleable.n2_EditorialMarquee_n2_titleText)) {
            ((EditorialMarquee) getView()).setTitle(a.getText(R.styleable.n2_EditorialMarquee_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_EditorialMarquee_n2_descriptionText)) {
            ((EditorialMarquee) getView()).setDescription(a.getText(R.styleable.n2_EditorialMarquee_n2_descriptionText));
        }
        if (a.hasValue(R.styleable.n2_EditorialMarquee_n2_scrimImage)) {
            ((EditorialMarquee) getView()).setScrimEnabled(a.getBoolean(R.styleable.n2_EditorialMarquee_n2_scrimImage, false));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }

    public AirTextViewStyleApplier kickerTextView() {
        return new AirTextViewStyleApplier(((EditorialMarquee) getView()).kickerTextView);
    }

    public AirTextViewStyleApplier titleTextView() {
        return new AirTextViewStyleApplier(((EditorialMarquee) getView()).titleTextView);
    }

    public AirTextViewStyleApplier descriptionTextView() {
        return new AirTextViewStyleApplier(((EditorialMarquee) getView()).descriptionTextView);
    }
}
