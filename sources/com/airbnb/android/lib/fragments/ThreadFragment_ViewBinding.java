package com.airbnb.android.lib.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.messages.MessageThreadInputView;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.StatusBanner;
import com.airbnb.p027n2.components.ThreadBottomActionButton;

public class ThreadFragment_ViewBinding implements Unbinder {
    private ThreadFragment target;
    private View view2131756829;

    public ThreadFragment_ViewBinding(final ThreadFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.status_banner, "field 'banner' and method 'statusBannerClicked'");
        target2.banner = (StatusBanner) Utils.castView(view, C0880R.C0882id.status_banner, "field 'banner'", StatusBanner.class);
        this.view2131756829 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.statusBannerClicked();
            }
        });
        target2.layout = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.messaging_components, "field 'layout'", LinearLayout.class);
        target2.actionButton = (PrimaryButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.action_button, "field 'actionButton'", PrimaryButton.class);
        target2.fullLoader = Utils.findRequiredView(source, C0880R.C0882id.full_loader, "field 'fullLoader'");
        target2.inputLoader = Utils.findRequiredView(source, C0880R.C0882id.input_loader, "field 'inputLoader'");
        target2.input = (MessageThreadInputView) Utils.findRequiredViewAsType(source, C0880R.C0882id.input, "field 'input'", MessageThreadInputView.class);
        target2.threadBottomActionButton = (ThreadBottomActionButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.thread_bottom_action_button, "field 'threadBottomActionButton'", ThreadBottomActionButton.class);
        target2.hostRespondExtraServiceButton = (PrimaryButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.host_respond_extra_service_button, "field 'hostRespondExtraServiceButton'", PrimaryButton.class);
    }

    public void unbind() {
        ThreadFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.banner = null;
        target2.layout = null;
        target2.actionButton = null;
        target2.fullLoader = null;
        target2.inputLoader = null;
        target2.input = null;
        target2.threadBottomActionButton = null;
        target2.hostRespondExtraServiceButton = null;
        this.view2131756829.setOnClickListener(null);
        this.view2131756829 = null;
    }
}
