package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.n2.components.AutoResizableButtonBar_ViewBinding */
public class AutoResizableButtonBar_ViewBinding implements Unbinder {
    private AutoResizableButtonBar target;
    private View view2131493098;
    private View view2131493099;

    public AutoResizableButtonBar_ViewBinding(final AutoResizableButtonBar target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, R.id.right_button, "field 'rightButton' and method 'rightButtonClicked'");
        target2.rightButton = (AirButton) Utils.castView(view, R.id.right_button, "field 'rightButton'", AirButton.class);
        this.view2131493099 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.rightButtonClicked(p0);
            }
        });
        View view2 = Utils.findRequiredView(source, R.id.left_button, "field 'leftButton' and method 'leftButtonClicked'");
        target2.leftButton = (AirButton) Utils.castView(view2, R.id.left_button, "field 'leftButton'", AirButton.class);
        this.view2131493098 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.leftButtonClicked(p0);
            }
        });
        target2.container = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.container, "field 'container'", LinearLayout.class);
        target2.loader = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loader, "field 'loader'", LoadingView.class);
    }

    public void unbind() {
        AutoResizableButtonBar target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.rightButton = null;
        target2.leftButton = null;
        target2.container = null;
        target2.loader = null;
        this.view2131493099.setOnClickListener(null);
        this.view2131493099 = null;
        this.view2131493098.setOnClickListener(null);
        this.view2131493098 = null;
    }
}
