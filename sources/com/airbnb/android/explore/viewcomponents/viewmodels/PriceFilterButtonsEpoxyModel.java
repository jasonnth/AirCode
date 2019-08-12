package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.p027n2.components.PriceFilterButtons;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class PriceFilterButtonsEpoxyModel extends AirEpoxyModel<PriceFilterButtons> {
    OnClickListener button1ClickListener;
    OnClickListener button2ClickListener;
    OnClickListener button3ClickListener;
    int selection;

    public void bind(PriceFilterButtons priceFilterButtons) {
        super.bind(priceFilterButtons);
        CurrencyFormatter currencyFormatter = CoreApplication.instance(priceFilterButtons.getContext()).component().currencyHelper();
        priceFilterButtons.setButton1Text(currencyFormatter.getSymbolicPrice(1));
        priceFilterButtons.setButton2Text(currencyFormatter.getSymbolicPrice(2));
        priceFilterButtons.setButton3Text(currencyFormatter.getSymbolicPrice(3));
        priceFilterButtons.setButton1ClickListener(this.button1ClickListener);
        priceFilterButtons.setButton2ClickListener(this.button2ClickListener);
        priceFilterButtons.setButton3ClickListener(this.button3ClickListener);
        priceFilterButtons.setSelection(this.selection);
    }

    public void unbind(PriceFilterButtons priceFilterButtons) {
        super.unbind(priceFilterButtons);
        priceFilterButtons.setButton1ClickListener(null);
        priceFilterButtons.setButton2ClickListener(null);
        priceFilterButtons.setButton3ClickListener(null);
    }
}
