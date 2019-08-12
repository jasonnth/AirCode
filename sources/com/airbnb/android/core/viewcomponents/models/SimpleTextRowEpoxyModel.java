package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.text.method.MovementMethod;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

public abstract class SimpleTextRowEpoxyModel extends AirEpoxyModel<SimpleTextRow> {
    OnClickListener clickListener;
    int color;
    CharSequence coloredText;
    int coloredTextRes;
    CharSequence description;
    int descriptionRes;
    boolean hasColoredText;
    boolean hasLinkedText;
    LinkOnClickListener linkListener;
    int linkifyMask;
    MovementMethod movementMethod;
    CharSequence text;
    boolean textIsSelectable;
    int textRes;

    public void bind(SimpleTextRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualDescription = this.descriptionRes != 0 ? context.getString(this.descriptionRes) : this.description;
        CharSequence actualColoredText = this.coloredTextRes != 0 ? context.getString(this.coloredTextRes) : this.coloredText;
        if (this.hasLinkedText) {
            view.setupLinkedText(actualDescription, actualColoredText, this.color, this.linkListener);
        } else if (this.hasColoredText) {
            view.setupColoredTextWithRegularDescription(actualColoredText, actualDescription, this.color);
        } else if (this.textRes != 0) {
            view.setText(this.textRes);
            view.setMovementMethod(this.movementMethod);
        } else {
            view.setText(this.text);
            view.setMovementMethod(this.movementMethod);
        }
        view.linkifyText(this.linkifyMask);
        view.setOnClickListener(this.clickListener);
        view.setTextIsSelectable(this.textIsSelectable);
    }

    public void unbind(SimpleTextRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public SimpleTextRowEpoxyModel plus() {
        layout(C0716R.layout.view_holder_simple_text_row_plus);
        return this;
    }

    public SimpleTextRowEpoxyModel large() {
        layout(C0716R.layout.view_holder_simple_text_row_large);
        return this;
    }

    public SimpleTextRowEpoxyModel largeAndInverse() {
        layout(C0716R.layout.view_holder_simple_text_row_large_inverse);
        return this;
    }

    public SimpleTextRowEpoxyModel small() {
        layout(C0716R.layout.view_holder_simple_text_row_small);
        return this;
    }

    public SimpleTextRowEpoxyModel smallAndMuted() {
        layout(C0716R.layout.view_holder_simple_text_row_small_muted);
        return this;
    }

    public SimpleTextRowEpoxyModel plusAndTightPadding() {
        layout(C0716R.layout.view_holder_simple_text_row_plus_tiny_half_padding);
        return this;
    }

    public SimpleTextRowEpoxyModel plusAndTopPadding() {
        layout(C0716R.layout.view_holder_simple_text_row_plus_top_padding);
        return this;
    }

    public SimpleTextRowEpoxyModel smallPadding() {
        layout(C0716R.layout.view_holder_simple_text_row_small_padding);
        return this;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    public int getDividerViewType() {
        return 0;
    }
}
