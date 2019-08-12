package com.airbnb.android.core.viewcomponents.models;

import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.TextView.OnEditorActionListener;
import com.airbnb.p027n2.components.InputMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;
import java.util.List;

public abstract class InputMarqueeEpoxyModel extends AirEpoxyModel<InputMarquee> {
    Integer backgroundColor;
    OnEditorActionListener editorActionListener;
    boolean forSearch;
    int hint;
    int inputEditViewDrawable;
    boolean invertColors;
    OnClickListener marqueeOnClickListener;
    boolean requestFocus;
    boolean showKeyboardOnFocus;
    String text;
    final List<TextWatcher> textWatchers = new ArrayList();

    public void bind(InputMarquee view) {
        super.bind(view);
        for (TextWatcher textWatcher : this.textWatchers) {
            view.removeTextWatcher(textWatcher);
        }
        if (this.hint != 0) {
            view.setHint(this.hint);
        } else {
            view.setHint((CharSequence) null);
        }
        view.setLeftDrawable(this.inputEditViewDrawable);
        if (this.text != null) {
            view.setText((CharSequence) this.text);
        }
        view.setForSearch(this.forSearch);
        view.setOnEditorActionListener(this.editorActionListener);
        for (TextWatcher textWatcher2 : this.textWatchers) {
            view.addTextWatcher(textWatcher2);
        }
        view.setMarqueeOnClickListener(this.marqueeOnClickListener);
        if (this.requestFocus) {
            view.requestFocus();
        }
        view.setShowKeyboardOnFocus(this.showKeyboardOnFocus);
        view.invertColors(this.invertColors);
        view.setBackgroundColor(this.backgroundColor == null ? -1 : this.backgroundColor.intValue());
    }

    public void unbind(InputMarquee view) {
        super.unbind(view);
        for (TextWatcher textWatcher : this.textWatchers) {
            view.removeTextWatcher(textWatcher);
        }
    }

    public InputMarqueeEpoxyModel addTextWatcher(TextWatcher textWatcher) {
        this.textWatchers.add(textWatcher);
        return this;
    }

    public int getDividerViewType() {
        return 2;
    }
}
