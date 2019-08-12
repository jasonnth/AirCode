package com.airbnb.android.core.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.SimpleTextRow;

public class DLSCancellationPolicyFragment_ViewBinding implements Unbinder {
    private DLSCancellationPolicyFragment target;
    private View view2131755199;

    public DLSCancellationPolicyFragment_ViewBinding(final DLSCancellationPolicyFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0716R.C0718id.marquee, "field 'marquee'", DocumentMarquee.class);
        target2.cancellationPolicyTextView = (SimpleTextRow) Utils.findRequiredViewAsType(source, C0716R.C0718id.cancellation_policy, "field 'cancellationPolicyTextView'", SimpleTextRow.class);
        View view = Utils.findRequiredView(source, C0716R.C0718id.button, "field 'cancelButton' and method 'initiateCancellation'");
        target2.cancelButton = (PrimaryButton) Utils.castView(view, C0716R.C0718id.button, "field 'cancelButton'", PrimaryButton.class);
        this.view2131755199 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.initiateCancellation();
            }
        });
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C0716R.C0718id.loading_view, "field 'loadingView'", LoadingView.class);
    }

    public void unbind() {
        DLSCancellationPolicyFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.marquee = null;
        target2.cancellationPolicyTextView = null;
        target2.cancelButton = null;
        target2.loadingView = null;
        this.view2131755199.setOnClickListener(null);
        this.view2131755199 = null;
    }
}
