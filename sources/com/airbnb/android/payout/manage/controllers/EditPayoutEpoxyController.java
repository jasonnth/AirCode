package com.airbnb.android.payout.manage.controllers;

import android.content.Context;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LabelRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.payout.C7552R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class EditPayoutEpoxyController extends AirEpoxyController {
    private final Context context;
    DocumentMarqueeEpoxyModel_ documentMarqueeModel;
    LinkActionRowEpoxyModel_ linkActionRowModel;
    private final Listener listener;
    EpoxyControllerLoadingModel_ loaderModel;
    private List<PaymentInstrument> payoutInstruments;

    public interface Listener {
        void onClickAddNewPayoutMethod();
    }

    public EditPayoutEpoxyController(Context context2, Listener listener2) {
        this.context = context2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.documentMarqueeModel.titleRes(C7552R.string.edit_payout_method_marquee_title);
        if (this.payoutInstruments == null) {
            add((EpoxyModel<?>) this.loaderModel);
            return;
        }
        addDefaultPayoutInstrument();
        addOtherPayoutInstruments();
        addLinkActionRow();
    }

    private void addDefaultPayoutInstrument() {
        PaymentInstrument defaultInstrument = getDefaultInstrument(this.payoutInstruments);
        if (defaultInstrument != null) {
            new LabelRowEpoxyModel_().m4990id((long) defaultInstrument.hashCode()).titleText(defaultInstrument.getTitleText()).subtitleText(getSubtitleText(defaultInstrument)).labelRes(C7552R.string.default_payout_method).addTo(this);
        }
    }

    private String getSubtitleText(PaymentInstrument payoutinstrument) {
        StringBuilder stringBuilder = new StringBuilder();
        String bullet = this.context.getResources().getString(C7552R.string.bullet_with_space);
        if (payoutinstrument.isPendingPayout()) {
            stringBuilder.append(this.context.getResources().getString(C7552R.string.pending));
            stringBuilder.append(bullet);
        } else if (payoutinstrument.hasError()) {
            stringBuilder.append(this.context.getResources().getString(C7552R.string.error));
            stringBuilder.append(bullet);
        }
        stringBuilder.append(payoutinstrument.getShortDescription());
        return stringBuilder.toString();
    }

    private void addLinkActionRow() {
        if (!Trebuchet.launch("payments", "add_payout_disabled", false)) {
            this.linkActionRowModel.textRes(C7552R.string.add_payout_method_row_title).clickListener(EditPayoutEpoxyController$$Lambda$1.lambdaFactory$(this));
        }
    }

    private void addOtherPayoutInstruments() {
        for (PaymentInstrument instrument : this.payoutInstruments) {
            if (!instrument.isDefaultPayout()) {
                new LabelRowEpoxyModel_().m4990id((long) instrument.hashCode()).titleText(instrument.getTitleText()).subtitleText(getSubtitleText(instrument)).addTo(this);
            }
        }
    }

    private PaymentInstrument getDefaultInstrument(List<PaymentInstrument> payoutInstruments2) {
        return (PaymentInstrument) FluentIterable.from((Iterable<E>) payoutInstruments2).firstMatch(EditPayoutEpoxyController$$Lambda$2.lambdaFactory$()).orNull();
    }

    public void setPayoutInstruments(List<PaymentInstrument> payoutInstruments2) {
        this.payoutInstruments = payoutInstruments2;
        requestModelBuild();
    }
}
