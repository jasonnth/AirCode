package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.utils.geocoder.models.AutocompletePrediction;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.StandardRow;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;

public class AddressAutoCompleteAdapter extends AirEpoxyAdapter {
    private final Listener listener;

    public interface Listener {
        void onAutocompletePredictionSelected(AutocompletePrediction autocompletePrediction);
    }

    public AddressAutoCompleteAdapter(Listener listener2) {
        this.listener = listener2;
    }

    public void setAutoCompleteItems(List<AutocompletePrediction> autoCompleteItems) {
        this.models.clear();
        addModels((Collection<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) autoCompleteItems).transform(AddressAutoCompleteAdapter$$Lambda$1.lambdaFactory$(this)).toList());
        notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public EpoxyModel<StandardRow> buildAutoCompleteEpoxyModel(AutocompletePrediction item) {
        return new StandardRowEpoxyModel_().title((CharSequence) item.getDescription()).titleMaxLine(2).clickListener(AddressAutoCompleteAdapter$$Lambda$2.lambdaFactory$(this, item)).m5604id((CharSequence) "autocomplete" + item.getDescription());
    }

    public void clearModels() {
        this.models.clear();
        notifyDataSetChanged();
    }
}
