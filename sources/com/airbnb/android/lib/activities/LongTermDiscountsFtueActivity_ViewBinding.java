package com.airbnb.android.lib.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class LongTermDiscountsFtueActivity_ViewBinding implements Unbinder {
    private LongTermDiscountsFtueActivity target;
    private View view2131755408;
    private View view2131755431;

    public LongTermDiscountsFtueActivity_ViewBinding(LongTermDiscountsFtueActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public LongTermDiscountsFtueActivity_ViewBinding(final LongTermDiscountsFtueActivity target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.set_long_term_discounts, "method 'onLearnMoreClicked'");
        this.view2131755431 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onLearnMoreClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btn_close, "method 'close'");
        this.view2131755408 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.close();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755431.setOnClickListener(null);
        this.view2131755431 = null;
        this.view2131755408.setOnClickListener(null);
        this.view2131755408 = null;
    }
}
