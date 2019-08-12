package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.BottomButtonBar_ViewBinding */
public class BottomButtonBar_ViewBinding implements Unbinder {
    private BottomButtonBar target;
    private View view2131493115;
    private View view2131493116;

    public BottomButtonBar_ViewBinding(final BottomButtonBar target2, View source) {
        this.target = target2;
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.positiveButtonText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.positive_button_text, "field 'positiveButtonText'", AirTextView.class);
        View view = Utils.findRequiredView(source, R.id.negative_button_text, "field 'negativeButtonText' and method 'negativeButtonClicked'");
        target2.negativeButtonText = (AirTextView) Utils.castView(view, R.id.negative_button_text, "field 'negativeButtonText'", AirTextView.class);
        this.view2131493115 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.negativeButtonClicked(p0);
            }
        });
        View view2 = Utils.findRequiredView(source, R.id.positive_action_button, "method 'positiveButtonClicked'");
        this.view2131493116 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.positiveButtonClicked(p0);
            }
        });
    }

    public void unbind() {
        BottomButtonBar target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loadingView = null;
        target2.positiveButtonText = null;
        target2.negativeButtonText = null;
        this.view2131493115.setOnClickListener(null);
        this.view2131493115 = null;
        this.view2131493116.setOnClickListener(null);
        this.view2131493116 = null;
    }
}
