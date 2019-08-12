package com.airbnb.android.lib.fragments.managelisting;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.PriceEditText;

public class EditPriceFragment_ViewBinding implements Unbinder {
    private EditPriceFragment target;
    private View view2131755854;

    public EditPriceFragment_ViewBinding(final EditPriceFragment target2, View source) {
        this.target = target2;
        target2.textViewBelowPriceField = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_price_sub_text, "field 'textViewBelowPriceField'", TextView.class);
        target2.mPriceText = (PriceEditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.price, "field 'mPriceText'", PriceEditText.class);
        target2.editPriceHeader = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_price_header, "field 'editPriceHeader'", TextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.tooltip_image, "method 'onTooltipClick'");
        this.view2131755854 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onTooltipClick();
            }
        });
    }

    public void unbind() {
        EditPriceFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textViewBelowPriceField = null;
        target2.mPriceText = null;
        target2.editPriceHeader = null;
        this.view2131755854.setOnClickListener(null);
        this.view2131755854 = null;
    }
}
