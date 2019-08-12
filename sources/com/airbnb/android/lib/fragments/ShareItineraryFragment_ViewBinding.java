package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.LoaderRecyclerView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.android.p305ex.chips.RecipientEditTextView;

public class ShareItineraryFragment_ViewBinding implements Unbinder {
    private ShareItineraryFragment target;
    private View view2131756762;
    private View view2131756764;
    private View view2131756765;

    public ShareItineraryFragment_ViewBinding(final ShareItineraryFragment target2, View source) {
        this.target = target2;
        target2.infoHeaderText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_itin_info_text, "field 'infoHeaderText'", TextView.class);
        target2.infoHeaderSubtext = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_itin_info_subtext, "field 'infoHeaderSubtext'", TextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.share_itin_recipient_edit_text, "field 'recipEditText' and method 'onFocusChange'");
        target2.recipEditText = (RecipientEditTextView) Utils.castView(view, C0880R.C0882id.share_itin_recipient_edit_text, "field 'recipEditText'", RecipientEditTextView.class);
        this.view2131756762 = view;
        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View p0, boolean p1) {
                target2.onFocusChange(p1);
            }
        });
        target2.footer = Utils.findRequiredView(source, C0880R.C0882id.share_itin_button_footer, "field 'footer'");
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.share_itin_send_button, "field 'sendButton' and method 'promptConfirmSendInvites'");
        target2.sendButton = (AirButton) Utils.castView(view2, C0880R.C0882id.share_itin_send_button, "field 'sendButton'", AirButton.class);
        this.view2131756765 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.promptConfirmSendInvites();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.btn_skip, "field 'skipButton' and method 'finish'");
        target2.skipButton = (AirTextView) Utils.castView(view3, C0880R.C0882id.btn_skip, "field 'skipButton'", AirTextView.class);
        this.view2131756764 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.finish();
            }
        });
        target2.loaderRecyclerView = (LoaderRecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.loader_recycler_view, "field 'loaderRecyclerView'", LoaderRecyclerView.class);
    }

    public void unbind() {
        ShareItineraryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.infoHeaderText = null;
        target2.infoHeaderSubtext = null;
        target2.recipEditText = null;
        target2.footer = null;
        target2.sendButton = null;
        target2.skipButton = null;
        target2.loaderRecyclerView = null;
        this.view2131756762.setOnFocusChangeListener(null);
        this.view2131756762 = null;
        this.view2131756765.setOnClickListener(null);
        this.view2131756765 = null;
        this.view2131756764.setOnClickListener(null);
        this.view2131756764 = null;
    }
}
