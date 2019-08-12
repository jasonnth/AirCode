package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.Currency;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.utils.RadioRowModelManager.Listener;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.ListUtils;
import com.google.common.base.Objects;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListingCurrencyAdapter extends AirEpoxyAdapter {
    private final Context context;
    @State
    ArrayList<Currency> currencies = null;
    private final Listener<String> currencySelectListener = new Listener<String>() {
        public void onValueSelected(String value) {
            ListingCurrencyAdapter.this.currentCurrencyCode = value;
        }

        public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
            ListingCurrencyAdapter.this.notifyModelChanged(model);
        }
    };
    @State
    String currentCurrencyCode;
    private final DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.listing_currency_title);
    private final RadioRowModelManager<String> radioManager;

    public ListingCurrencyAdapter(Context context2, String currentCurrencyCode2, boolean showDisclaimer, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        onRestoreInstanceState(savedInstanceState);
        this.context = context2;
        this.radioManager = new RadioRowModelManager<>(this.currencySelectListener);
        addModel(this.header);
        if (savedInstanceState == null) {
            this.currentCurrencyCode = currentCurrencyCode2;
        }
        this.header.captionRes(showDisclaimer ? C7213R.string.lys_dls_listing_currency_conversion : 0);
        if (this.currencies == null) {
            addModel(new LoadingRowEpoxyModel_());
        } else {
            createRadioRows();
        }
    }

    public void setCurrencyOptions(List<Currency> currencies2) {
        removeAllAfterModel(this.header);
        this.currencies = new ArrayList<>(currencies2);
        Currency currentCurrency = (Currency) ListUtils.removeFirstWhere(this.currencies, ListingCurrencyAdapter$$Lambda$1.lambdaFactory$(this));
        if (currentCurrency != null) {
            this.currencies.add(0, currentCurrency);
        }
        createRadioRows();
    }

    public void setRowsEnabled(boolean enabled) {
        this.radioManager.setRowsEnabled(enabled);
    }

    public String getCurrentCurrencyCode() {
        return this.currentCurrencyCode;
    }

    public boolean hasChanged(String currencyCode) {
        return !Objects.equal(this.currentCurrencyCode, currencyCode);
    }

    private void createRadioRows() {
        Iterator it = this.currencies.iterator();
        while (it.hasNext()) {
            Currency currency = (Currency) it.next();
            this.radioManager.addRow((CharSequence) currency.getCode() + " (" + CurrencyUtils.getSanitizedSymbol(this.context, currency.getCode(), currency.getUnicodeSymbol()) + ")", currency.getCode());
        }
        this.radioManager.setSelectedValue(this.currentCurrencyCode);
        addModels(this.radioManager.getModels());
        notifyModelsChanged();
    }
}
