package com.airbnb.android.lib.payments.quickpay.adapters;

import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.viewcomponents.viewmodels.ExpandableCollectionRowEpoxyModel_;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.ExpandableCollectionRow.RowItem;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class HomesQuickPayInstallmentsHelper {
    private ExpandableCollectionRowEpoxyModel_ installmentsRowAboveModel;
    private ExpandableCollectionRowEpoxyModel_ installmentsRowBelowModel;
    private boolean shouldShowInstallmentsAbove;
    private boolean shouldShowInstallmentsBelow;

    public void setInstallmentsRowAboveModel(ExpandableCollectionRowEpoxyModel_ model) {
        this.installmentsRowAboveModel = model;
    }

    public void setInstallmentsRowBelowModel(ExpandableCollectionRowEpoxyModel_ model) {
        this.installmentsRowBelowModel = model;
    }

    public ExpandableCollectionRowEpoxyModel_ updateInstallmentsModel(BillPriceQuote priceQuote) {
        ExpandableCollectionRowEpoxyModel_ installmentsRowModel;
        List<RowItem> installmentRowItems = FluentIterable.from((Iterable<E>) priceQuote.getInstallments()).transform(HomesQuickPayInstallmentsHelper$$Lambda$1.lambdaFactory$()).toList();
        if (installmentRowItems.size() <= 1 || !PricingFeatureToggles.showInstallmentsRow()) {
            return null;
        }
        if (Experiments.showInstallmentsAbove()) {
            installmentsRowModel = this.installmentsRowAboveModel;
            this.shouldShowInstallmentsAbove = true;
        } else {
            installmentsRowModel = this.installmentsRowBelowModel;
            this.shouldShowInstallmentsBelow = true;
        }
        installmentsRowModel.rowItems(installmentRowItems).expandTextRes(C0880R.string.installment_expandable_text).bottomTextRes(C0880R.string.installment_disclaimer_text).show();
        return installmentsRowModel;
    }

    static /* synthetic */ RowItem lambda$updateInstallmentsModel$0(Price installment) {
        return new RowItem(installment.getLocalizedTitle(), installment.getTotal().formattedForDisplay());
    }

    public ExpandableCollectionRowEpoxyModel_ getVisibleRow() {
        if (this.shouldShowInstallmentsAbove) {
            return this.installmentsRowAboveModel;
        }
        if (this.shouldShowInstallmentsBelow) {
            return this.installmentsRowBelowModel;
        }
        return null;
    }
}
