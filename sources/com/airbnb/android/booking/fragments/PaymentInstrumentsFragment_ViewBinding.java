package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.p336n2.PaymentInstrumentSelectionView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class PaymentInstrumentsFragment_ViewBinding implements Unbinder {
    private PaymentInstrumentsFragment target;
    private View view2131755509;
    private View view2131755565;

    public PaymentInstrumentsFragment_ViewBinding(final PaymentInstrumentsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0704R.C0706id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.selectionSheetPresenter = (PaymentInstrumentSelectionView) Utils.findRequiredViewAsType(source, C0704R.C0706id.selection_view, "field 'selectionSheetPresenter'", PaymentInstrumentSelectionView.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.add_payment_button, "field 'addPaymentButton' and method 'onAddPaymentButtonClicked'");
        target2.addPaymentButton = (AirButton) Utils.castView(view, C0704R.C0706id.add_payment_button, "field 'addPaymentButton'", AirButton.class);
        this.view2131755565 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onAddPaymentButtonClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C0704R.C0706id.continue_button, "field 'continueButton' and method 'onSelectPaymentInstrument'");
        target2.continueButton = (AirButton) Utils.castView(view2, C0704R.C0706id.continue_button, "field 'continueButton'", AirButton.class);
        this.view2131755509 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSelectPaymentInstrument();
            }
        });
    }

    public void unbind() {
        PaymentInstrumentsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.jellyfishView = null;
        target2.selectionSheetPresenter = null;
        target2.addPaymentButton = null;
        target2.continueButton = null;
        this.view2131755565.setOnClickListener(null);
        this.view2131755565 = null;
        this.view2131755509.setOnClickListener(null);
        this.view2131755509 = null;
    }
}
