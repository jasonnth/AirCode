package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class WifiZenDialogFragment_ViewBinding implements Unbinder {
    private WifiZenDialogFragment target;
    private View view2131755862;
    private View view2131755864;

    public WifiZenDialogFragment_ViewBinding(final WifiZenDialogFragment target2, View source) {
        this.target = target2;
        target2.tvNetworkName = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.tv_network_name, "field 'tvNetworkName'", TextView.class);
        target2.tvNetworkPassword = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.tv_network_password, "field 'tvNetworkPassword'", TextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.layout_network_name, "method 'copyNetworkName'");
        this.view2131755862 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.copyNetworkName();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.layout_network_password, "method 'copyNetworkPassword'");
        this.view2131755864 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.copyNetworkPassword();
            }
        });
    }

    public void unbind() {
        WifiZenDialogFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.tvNetworkName = null;
        target2.tvNetworkPassword = null;
        this.view2131755862.setOnClickListener(null);
        this.view2131755862 = null;
        this.view2131755864.setOnClickListener(null);
        this.view2131755864 = null;
    }
}
