package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;
import com.airbnb.p027n2.components.InlineMultilineInputRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InlineMultilineInputRowEpoxyModel extends AirEpoxyModel<InlineMultilineInputRow> {
    CharSequence hint;
    int hintRes;
    CharSequence input;
    OnInputChangedListener inputChangedListener;
    int inputRes;
    CharSequence title;
    int titleRes;

    public void bind(InlineMultilineInputRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualHint = this.hintRes != 0 ? context.getString(this.hintRes) : this.hint;
        CharSequence actualInput = this.inputRes != 0 ? context.getString(this.inputRes) : this.input;
        view.setTitle(actualTitle);
        view.setHint(actualHint);
        view.setInputText(actualInput);
        view.setOnInputChangedListener(this.inputChangedListener);
    }
}
