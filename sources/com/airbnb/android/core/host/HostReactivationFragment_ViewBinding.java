package com.airbnb.android.core.host;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.primitives.AirTextView;

public class HostReactivationFragment_ViewBinding implements Unbinder {
    private HostReactivationFragment target;
    private View view2131755480;
    private View view2131755483;

    public HostReactivationFragment_ViewBinding(final HostReactivationFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.refreshLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C0716R.C0718id.loader_view, "field 'refreshLoader'", RefreshLoader.class);
        target2.messageText = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.reactivation_text, "field 'messageText'", AirTextView.class);
        View view = Utils.findRequiredView(source, C0716R.C0718id.reactivate_link, "field 'helpLinkText' and method 'onHelpLinkClick'");
        target2.helpLinkText = (LinkActionRow) Utils.castView(view, C0716R.C0718id.reactivate_link, "field 'helpLinkText'", LinkActionRow.class);
        this.view2131755483 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onHelpLinkClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C0716R.C0718id.footer_button, "field 'buttonFooter' and method 'onReactivateButtonClick'");
        target2.buttonFooter = (FixedActionFooter) Utils.castView(view2, C0716R.C0718id.footer_button, "field 'buttonFooter'", FixedActionFooter.class);
        this.view2131755480 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onReactivateButtonClick();
            }
        });
    }

    public void unbind() {
        HostReactivationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.refreshLoader = null;
        target2.messageText = null;
        target2.helpLinkText = null;
        target2.buttonFooter = null;
        this.view2131755483.setOnClickListener(null);
        this.view2131755483 = null;
        this.view2131755480.setOnClickListener(null);
        this.view2131755480 = null;
    }
}
