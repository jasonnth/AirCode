package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;

public class QuickPayGiftCardLandingFragment_ViewBinding implements Unbinder {
    private QuickPayGiftCardLandingFragment target;
    private View view2131755474;

    public QuickPayGiftCardLandingFragment_ViewBinding(final QuickPayGiftCardLandingFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0704R.C0706id.next_button, "method 'onClickNextButton'");
        this.view2131755474 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNextButton();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755474.setOnClickListener(null);
        this.view2131755474 = null;
    }
}
