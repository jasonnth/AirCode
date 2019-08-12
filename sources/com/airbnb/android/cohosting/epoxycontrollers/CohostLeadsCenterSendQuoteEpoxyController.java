package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CohostLeadsCenterSendQuoteEpoxyController extends AirEpoxyController {
    private final Context context;
    StandardRowEpoxyModel_ earningsRow;
    private final String firstName;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    StandardRowEpoxyModel_ notesRow;
    StandardRowEpoxyModel_ packageRow;

    public CohostLeadsCenterSendQuoteEpoxyController(Context context2, String firstName2) {
        this.context = context2;
        this.firstName = firstName2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeModel.titleRes(C5658R.string.cohosting_leads_center_send_quote_title).captionText((CharSequence) this.context.getString(C5658R.string.cohosting_leads_center_send_quote_subtitle, new Object[]{this.firstName}));
        this.packageRow.titleRes(C5658R.string.cohosting_leads_center_quote_type_name).subtitleRes(C5658R.string.not_implemented).actionText(C5658R.string.edit);
        this.earningsRow.titleRes(C5658R.string.share_of_earnings_title).subtitleRes(C5658R.string.not_implemented).actionText(C5658R.string.edit);
        this.notesRow.titleRes(C5658R.string.cohosting_leads_center_quote_notes_title).subtitleRes(C5658R.string.not_implemented).actionText(C5658R.string.add);
    }
}
