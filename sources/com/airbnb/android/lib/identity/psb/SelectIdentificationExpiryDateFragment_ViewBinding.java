package com.airbnb.android.lib.identity.psb;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class SelectIdentificationExpiryDateFragment_ViewBinding implements Unbinder {
    private SelectIdentificationExpiryDateFragment target;
    private View view2131756035;
    private View view2131756036;
    private View view2131756731;

    public SelectIdentificationExpiryDateFragment_ViewBinding(final SelectIdentificationExpiryDateFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0880R.C0882id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.expiry_date_text, "field 'dateOfExpiryInput' and method 'showExpiryDatePicker'");
        target2.dateOfExpiryInput = (SheetInputText) Utils.castView(view, C0880R.C0882id.expiry_date_text, "field 'dateOfExpiryInput'", SheetInputText.class);
        this.view2131756731 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showExpiryDatePicker();
            }
        });
        target2.sheetHeader = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.sheet_header, "field 'sheetHeader'", SheetMarquee.class);
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'onNextClick'");
        target2.nextButton = view2;
        this.view2131756035 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextClick();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.booking_next_button, "field 'bookingNextButton' and method 'onBookingNextClick'");
        target2.bookingNextButton = view3;
        this.view2131756036 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onBookingNextClick();
            }
        });
    }

    public void unbind() {
        SelectIdentificationExpiryDateFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.dateOfExpiryInput = null;
        target2.sheetHeader = null;
        target2.nextButton = null;
        target2.bookingNextButton = null;
        this.view2131756731.setOnClickListener(null);
        this.view2131756731 = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
        this.view2131756036.setOnClickListener(null);
        this.view2131756036 = null;
    }
}
