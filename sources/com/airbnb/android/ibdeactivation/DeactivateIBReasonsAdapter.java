package com.airbnb.android.ibdeactivation;

import com.airbnb.android.core.enums.DeactivateIBReason;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RequirementChecklistRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.RequirementChecklistRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.ibdeactivation.DeactivateIBLandingFragment.DeactivateIBNavigator;
import com.airbnb.epoxy.EpoxyModel;

public class DeactivateIBReasonsAdapter extends AirEpoxyAdapter {

    private enum RowType {
        ClickableRow,
        TextRow,
        ChecklistRow
    }

    public DeactivateIBReasonsAdapter(DeactivateIBNavigator deactivateIBNavigator, DeactivateIBReason reason) {
        super(true);
        addTitle(reason);
        addItems(deactivateIBNavigator, reason);
    }

    private void addTitle(DeactivateIBReason reason) {
        DocumentMarqueeEpoxyModel_ title;
        if (reason == null) {
            title = new DocumentMarqueeEpoxyModel_().titleRes(C6454R.string.deactivate_ib_title);
        } else {
            title = new DocumentMarqueeEpoxyModel_().titleRes(reason.getReasonStrTitleId()).captionRes(0);
        }
        addModel(title);
    }

    private void addItems(DeactivateIBNavigator deactivateIBNavigator, DeactivateIBReason reason) {
        if (reason == DeactivateIBReason.AirbnbRequirements) {
            addAirbnbRequirementsItems();
            return;
        }
        for (DeactivateIBReason deactivateIBReason : reason == null ? DeactivateIBReason.getMainReasons() : DeactivateIBReason.getSubReasons(reason)) {
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{createRow(deactivateIBNavigator, deactivateIBReason)});
        }
    }

    private StandardRowEpoxyModel_ createRow(int title) {
        return new StandardRowEpoxyModel_().title(title).titleMaxLine(2);
    }

    private StandardRowEpoxyModel_ createRow(DeactivateIBNavigator deactivateIBNavigator, DeactivateIBReason deactivateIBReason) {
        return createRow(deactivateIBReason.getReasonStrId()).subtitle(deactivateIBReason.getReasonStrSubtitleId()).clickListener(DeactivateIBReasonsAdapter$$Lambda$1.lambdaFactory$(deactivateIBNavigator, deactivateIBReason)).rowDrawableRes(C6454R.C6455drawable.n2_icon_chevron_right_babu);
    }

    private RequirementChecklistRowEpoxyModel createChecklistRow(int title) {
        return new RequirementChecklistRowEpoxyModel_().title(title).rowDrawableRes(C6454R.C6455drawable.n2_ic_check_babu);
    }

    private void addAirbnbRequirementsItems() {
        addModel(createRow(C6454R.string.deactivate_ib_guest_control_airbnb_requirements_book_list).showDivider(false));
        addModel(createChecklistRow(C6454R.string.f9069x23ed6c71));
        addModel(createChecklistRow(C6454R.string.f9066x20d716a6));
        addModel(createChecklistRow(C6454R.string.f9068x41230045));
        addModel(createChecklistRow(C6454R.string.f9067x9aa1bd08));
        addModel(createRow(C6454R.string.deactivate_ib_guest_control_airbnb_requirements_rules_list).showDivider(false));
        addModel(createChecklistRow(C6454R.string.f9071xdd04ea1f));
        addModel(createChecklistRow(C6454R.string.f9072xbf589bcb));
        addModel(createChecklistRow(C6454R.string.f9073xde08308d));
        addModel(createChecklistRow(C6454R.string.f9070xbe171135));
    }
}
