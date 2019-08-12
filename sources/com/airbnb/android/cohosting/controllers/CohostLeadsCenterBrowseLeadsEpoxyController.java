package com.airbnb.android.cohosting.controllers;

import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.models.CohostInquiry;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.EmptyStateCardModel_;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.util.List;

public class CohostLeadsCenterBrowseLeadsEpoxyController extends TypedAirEpoxyController<List<CohostInquiry>> {
    EmptyStateCardModel_ emptyCardModel;

    /* access modifiers changed from: protected */
    public void buildModels(List<CohostInquiry> data) {
        if (ListUtils.isNullOrEmpty(data)) {
            displayEmptyState();
        }
    }

    private void displayEmptyState() {
        this.emptyCardModel.text(C5658R.string.cohost_leads_center_empty_inquries).image(C5658R.C5659drawable.n2_need_assets_from_design);
    }
}
