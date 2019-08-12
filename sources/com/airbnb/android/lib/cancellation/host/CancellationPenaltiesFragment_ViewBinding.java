package com.airbnb.android.lib.cancellation.host;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.primitives.AirButton;

public class CancellationPenaltiesFragment_ViewBinding implements Unbinder {
    private CancellationPenaltiesFragment target;
    private View view2131756104;

    public CancellationPenaltiesFragment_ViewBinding(final CancellationPenaltiesFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.title = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.section_title, "field 'title'", DocumentMarquee.class);
        target2.feeRow = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.fee_row, "field 'feeRow'", StandardRow.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.continue_button, "field 'continueButton' and method 'onClickContinue'");
        target2.continueButton = (AirButton) Utils.castView(view, C0880R.C0882id.continue_button, "field 'continueButton'", AirButton.class);
        this.view2131756104 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickContinue();
            }
        });
    }

    public void unbind() {
        CancellationPenaltiesFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.title = null;
        target2.feeRow = null;
        target2.continueButton = null;
        this.view2131756104.setOnClickListener(null);
        this.view2131756104 = null;
    }
}
