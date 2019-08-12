package com.airbnb.android.lib.paidamenities.fragments.create;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import icepick.State;

public class AddAmenityPriceFragment extends BaseCreateAmenityFragment {
    @BindView
    SwitchRow complimentarySwitchRow;
    @State
    boolean isComplimentary;
    private Listener listener;
    @BindView
    InlineInputRow priceInputRow;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        void onPriceEntered(boolean z, int i);
    }

    public static AddAmenityPriceFragment newInstance() {
        return new AddAmenityPriceFragment();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface.");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_add_paid_amenity_price, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.complimentarySwitchRow.setOnCheckedChangeListener(AddAmenityPriceFragment$$Lambda$1.lambdaFactory$(this));
        this.priceInputRow.setHint(this.mCurrencyHelper.getLocalCurrencySymbol());
        this.priceInputRow.setInputType(2);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(AddAmenityPriceFragment addAmenityPriceFragment, SwitchRowInterface switchRow, boolean checked) {
        addAmenityPriceFragment.isComplimentary = checked;
        ViewUtils.setGoneIf(addAmenityPriceFragment.priceInputRow, checked);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        if (this.isComplimentary) {
            this.listener.onPriceEntered(true, 0);
            return;
        }
        try {
            int price = Integer.parseInt(this.priceInputRow.getInputText());
            if (price == 0) {
                this.listener.onPriceEntered(true, 0);
            } else {
                this.listener.onPriceEntered(false, price);
            }
            this.paidAmenityJitneyLogger.logHostAddClickSetPrice();
        } catch (NumberFormatException e) {
            ErrorUtils.showErrorUsingSnackbar(getView(), C0880R.string.paid_amenities_add_price_error);
        }
    }
}
