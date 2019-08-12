package com.airbnb.android.cohosting.controllers;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.EmptyStateCardModel_;
import com.airbnb.p027n2.components.ListingInfoRowModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;

public class CohostLeadsCenterConfirmedLeadsEpoxyController extends AirEpoxyController {
    @State
    ArrayList<CohostingContract> acceptedContracts;
    private final Context context;
    private final CohostLeadsCenterDataController controller;
    EmptyStateCardModel_ emptyCardModel;

    public CohostLeadsCenterConfirmedLeadsEpoxyController(Context context2, CohostLeadsCenterDataController controller2, Bundle savedInstanceState) {
        this.controller = controller2;
        this.context = context2;
        onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState == null) {
            this.acceptedContracts = new ArrayList<>(controller2.getAcceptedContracts());
        }
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        if (ListUtils.isNullOrEmpty(this.acceptedContracts)) {
            displayEmptyState();
        } else {
            addConfirmedContractModels();
        }
    }

    private void displayEmptyState() {
        this.emptyCardModel.text(C5658R.string.cohost_leads_center_accepted_contracts).image(C5658R.C5659drawable.n2_need_assets_from_design);
    }

    private void addConfirmedContractModels() {
        Iterator it = this.acceptedContracts.iterator();
        while (it.hasNext()) {
            CohostingContract contract = (CohostingContract) it.next();
            new ListingInfoRowModel_().mo11716id(contract.getId()).title(contract.getListingName()).subtitleText(getSubtitle(contract)).image(contract.getListingThumbnailUrl()).addTo(this);
        }
    }

    private String getSubtitle(CohostingContract contract) {
        String dateStr;
        String ownerStr = this.context.getString(C5658R.string.cohost_leads_center_listing_owner, new Object[]{contract.getOwner().getName()});
        if (contract.getEndDate() != null) {
            dateStr = this.context.getString(C5658R.string.separator_with_values, new Object[]{contract.getStartDate().getDateString(this.context), contract.getEndDate().getDateString(this.context)});
        } else {
            dateStr = this.context.getString(C5658R.string.cohosting_contract_date_end_date_ongoing);
        }
        return this.context.getString(C5658R.string.two_line_strings, new Object[]{ownerStr, dateStr});
    }
}
