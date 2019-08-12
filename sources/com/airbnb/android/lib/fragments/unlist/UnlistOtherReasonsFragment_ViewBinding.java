package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class UnlistOtherReasonsFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private UnlistOtherReasonsFragment target;
    private View view2131756851;

    public UnlistOtherReasonsFragment_ViewBinding(final UnlistOtherReasonsFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.unlistReasonTextView = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.unlist_feedback_edit_text, "field 'unlistReasonTextView'", EditText.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.submit_and_unlist_button, "method 'unlistListing'");
        this.view2131756851 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.unlistListing();
            }
        });
    }

    public void unbind() {
        UnlistOtherReasonsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.unlistReasonTextView = null;
        this.view2131756851.setOnClickListener(null);
        this.view2131756851 = null;
        super.unbind();
    }
}
