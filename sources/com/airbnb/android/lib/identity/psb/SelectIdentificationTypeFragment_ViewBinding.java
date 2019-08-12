package com.airbnb.android.lib.identity.psb;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class SelectIdentificationTypeFragment_ViewBinding implements Unbinder {
    private SelectIdentificationTypeFragment target;
    private View view2131756035;
    private View view2131756036;

    public SelectIdentificationTypeFragment_ViewBinding(final SelectIdentificationTypeFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0880R.C0882id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'onNextClick'");
        target2.nextButton = view;
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.booking_next_button, "field 'bookingNextButton' and method 'onBookingNextClick'");
        target2.bookingNextButton = view2;
        this.view2131756036 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onBookingNextClick();
            }
        });
        target2.selectionView = (IdentificationTypeSelectionView) Utils.findRequiredViewAsType(source, C0880R.C0882id.selection_view, "field 'selectionView'", IdentificationTypeSelectionView.class);
    }

    public void unbind() {
        SelectIdentificationTypeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.nextButton = null;
        target2.bookingNextButton = null;
        target2.selectionView = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
        this.view2131756036.setOnClickListener(null);
        this.view2131756036 = null;
    }
}
