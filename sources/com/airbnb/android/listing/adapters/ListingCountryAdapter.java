package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.Country;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.utils.RadioRowModelManager.Listener;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.utils.ListUtils;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ListingCountryAdapter extends AirEpoxyAdapter {
    @State
    ArrayList<Country> countries;
    private final Listener<Country> countrySelectListener = new Listener<Country>() {
        public void onValueSelected(Country value) {
            ListingCountryAdapter.this.currentCountry = value;
        }

        public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
            ListingCountryAdapter.this.notifyModelChanged(model);
        }
    };
    @State
    Country currentCountry;
    private final DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.country);
    private final RadioRowModelManager<Country> radioManager = new RadioRowModelManager<>(this.countrySelectListener);

    public ListingCountryAdapter(Bundle savedInstanceState) {
        super(true);
        onRestoreInstanceState(savedInstanceState);
        addModel(this.header);
        if (this.countries == null) {
            addModel(new LoadingRowEpoxyModel_());
        } else {
            addRadioRows();
        }
    }

    public void setCurrentCountry(Country country) {
        this.currentCountry = country;
    }

    public void setCountryOptions(Collection<Country> countries2) {
        removeAllAfterModel(this.header);
        this.countries = new ArrayList<>(countries2);
        Country selectedCountry = (Country) ListUtils.removeFirstWhere(this.countries, ListingCountryAdapter$$Lambda$1.lambdaFactory$(this));
        if (selectedCountry != null) {
            this.countries.add(0, selectedCountry);
        }
        addRadioRows();
    }

    public Country getCurrentCountry() {
        return this.currentCountry;
    }

    private void addRadioRows() {
        Iterator it = this.countries.iterator();
        while (it.hasNext()) {
            Country country = (Country) it.next();
            this.radioManager.addRow((CharSequence) country.getLocalizedName(), country);
        }
        this.radioManager.setSelectedValue(this.currentCountry);
        addModels(this.radioManager.getModels());
    }
}
