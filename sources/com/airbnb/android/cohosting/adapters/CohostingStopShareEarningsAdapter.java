package com.airbnb.android.cohosting.adapters;

import android.content.Context;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;

public class CohostingStopShareEarningsAdapter extends AirEpoxyAdapter {
    private final Context context;
    private final DocumentMarqueeEpoxyModel_ headerRow = new DocumentMarqueeEpoxyModel_();
    private final ListingManager listingManager;
    private final SimpleTextRowEpoxyModel_ stopShareEarningsIntro = new SimpleTextRowEpoxyModel_();
    private final SimpleTextRowEpoxyModel_ warningRow = new SimpleTextRowEpoxyModel_();

    public CohostingStopShareEarningsAdapter(Context context2, ListingManager listingManager2) {
        this.context = context2;
        this.listingManager = listingManager2;
        setupRows();
    }

    private void setupRows() {
        setupHeader();
        setupStopShareEarningsIntro();
        setupWarningRow();
    }

    private void setupHeader() {
        this.headerRow.titleText((CharSequence) this.context.getString(C5658R.string.cohosting_stop_sharing_earnings_title, new Object[]{this.listingManager.getUser().getFirstName()})).withNoTopPaddingLayout();
        addModel(this.headerRow);
    }

    private void setupStopShareEarningsIntro() {
        this.stopShareEarningsIntro.text(this.context.getString(C5658R.string.cohosting_stop_sharing_earnings_description, new Object[]{this.listingManager.getUser().getFirstName()})).showDivider(true);
        addModel(this.stopShareEarningsIntro);
    }

    private void setupWarningRow() {
        this.warningRow.text(this.context.getString(C5658R.string.cohosting_stop_sharing_earnings_explanation_if_currently_hosting, new Object[]{this.listingManager.getUser().getFirstName()})).smallAndMuted().showDivider(false);
        addModel(this.warningRow);
    }
}
