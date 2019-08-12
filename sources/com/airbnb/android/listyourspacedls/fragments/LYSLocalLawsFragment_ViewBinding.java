package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSLocalLawsFragment_ViewBinding extends LYSBaseFragment_ViewBinding {
    private LYSLocalLawsFragment target;
    private View view2131755495;

    public LYSLocalLawsFragment_ViewBinding(final LYSLocalLawsFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.webView = (AirWebView) Utils.findRequiredViewAsType(source, C7251R.C7253id.web_view, "field 'webView'", AirWebView.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.next_btn, "field 'nextButton' and method 'onNext'");
        target2.nextButton = (AirButton) Utils.castView(view, C7251R.C7253id.next_btn, "field 'nextButton'", AirButton.class);
        this.view2131755495 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNext();
            }
        });
    }

    public void unbind() {
        LYSLocalLawsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.webView = null;
        target2.nextButton = null;
        this.view2131755495.setOnClickListener(null);
        this.view2131755495 = null;
        super.unbind();
    }
}
