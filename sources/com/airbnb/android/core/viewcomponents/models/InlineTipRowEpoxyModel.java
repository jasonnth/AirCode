package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.p027n2.components.InlineTipRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InlineTipRowEpoxyModel extends AirEpoxyModel<InlineTipRow> {
    OnClickListener clickListener;
    OnClickListener closeListener;
    CharSequence link;
    int linkRes;
    CharSequence text;
    int textRes;
    CharSequence title;
    int titleRes;

    public void bind(InlineTipRow view) {
        super.bind(view);
        view.setText(setUpStringBuilder(view.getContext()));
        view.setDismissible(this.closeListener != null);
        view.setHintClickListener(this.clickListener);
        view.setCloseClickListener(this.closeListener);
    }

    public void unbind(InlineTipRow view) {
        super.unbind(view);
        view.setHintClickListener(null);
        view.setCloseClickListener(null);
    }

    public int getDividerViewType() {
        return 4;
    }

    private SpannableStringBuilder setUpStringBuilder(Context context) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        CharSequence titleText = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        if (!TextUtils.isEmpty(titleText)) {
            builder.append(SpannableUtils.makeBoldedString(titleText, context)).append("  ");
        }
        CharSequence bodyText = this.textRes != 0 ? context.getString(this.textRes) : this.text;
        if (!TextUtils.isEmpty(bodyText)) {
            builder.append(bodyText).append("  ");
        }
        CharSequence linkText = this.linkRes != 0 ? context.getString(this.linkRes) : this.link;
        if (!TextUtils.isEmpty(linkText)) {
            builder.append(SpannableUtils.makeColoredString(linkText, ContextCompat.getColor(context, C0716R.color.n2_babu)));
        }
        return builder;
    }
}
