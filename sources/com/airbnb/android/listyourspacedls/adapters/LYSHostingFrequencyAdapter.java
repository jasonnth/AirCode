package com.airbnb.android.listyourspacedls.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.listing.adapters.InputAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import icepick.State;

public class LYSHostingFrequencyAdapter extends AirEpoxyAdapter implements InputAdapter {
    @State
    ListingPersonaAnswer hostingFrequency;
    /* access modifiers changed from: private */
    public final Listener listener;
    private final RadioRowModelManager<ListingPersonaAnswer> radioRowManager = new RadioRowModelManager<>(new com.airbnb.android.core.utils.RadioRowModelManager.Listener<ListingPersonaAnswer>() {
        public void onValueSelected(ListingPersonaAnswer value) {
            LYSHostingFrequencyAdapter.this.hostingFrequency = value;
            LYSHostingFrequencyAdapter.this.listener.onValueSelected(value);
        }

        public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
            LYSHostingFrequencyAdapter.this.notifyModelChanged(model);
        }
    });

    public interface Listener {
        void onValueSelected(ListingPersonaAnswer listingPersonaAnswer);
    }

    public LYSHostingFrequencyAdapter(Listener listener2, Listing listing, Bundle savedInstanceState) {
        super(true);
        this.listener = listener2;
        if (savedInstanceState == null) {
            this.hostingFrequency = listing.getOccupancyPersonaAnswer();
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C7251R.string.lys_dls_hosting_frequency_title));
        for (ListingPersonaAnswer answer : ListingPersonaAnswer.getOccupanyAnswers()) {
            this.radioRowManager.addRow(new ToggleActionRowEpoxyModel_().titleRes(answer.getTitleRes()), answer);
        }
        this.radioRowManager.setSelectedValue(this.hostingFrequency);
        addModels(this.radioRowManager.getModels());
    }

    public void setInputEnabled(boolean enabled) {
        this.radioRowManager.setRowsEnabled(enabled);
    }

    public ListingPersonaAnswer getSelectedValue() {
        return (ListingPersonaAnswer) this.radioRowManager.getSelectedValue();
    }
}
