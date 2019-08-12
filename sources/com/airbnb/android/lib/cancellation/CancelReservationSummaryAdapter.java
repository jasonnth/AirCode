package com.airbnb.android.lib.cancellation;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Price.Type;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationCancellationRefundBreakdown;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PriceSummaryEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;

public class CancelReservationSummaryAdapter extends AirEpoxyAdapter {
    private final Context context;
    private final Listener listener;

    public interface Listener {
        void onClickPolicyLink();

        void onNonRefundableItemLink(Type type);
    }

    public CancelReservationSummaryAdapter(Context context2, Listener listener2) {
        super(true);
        this.context = context2;
        this.listener = listener2;
        this.models.add(new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.cancel_refund_summary));
        this.models.add(new LoadingRowEpoxyModel_());
    }

    public void updateBreakdown(Reservation reservation) {
        removeAllAfterModel((EpoxyModel) this.models.get(0));
        this.models.add(new StandardRowEpoxyModel_().title(C0880R.string.cancellation_policy).actionText((CharSequence) reservation.getCancellationPolicy()).subtitle((CharSequence) reservation.getCancellationPolicyShortDescription()).clickListener(CancelReservationSummaryAdapter$$Lambda$1.lambdaFactory$(this)));
        ReservationCancellationRefundBreakdown breakdown = reservation.getGuestCancellationRefundBreakdown();
        addRefundableRows(breakdown.getRefundablePrice());
        addNonRefundableRows(breakdown.getNonRefundablePrice());
        addSummaryLine(reservation);
        notifyDataSetChanged();
    }

    private void addRefundableRows(Price refundablePrice) {
        for (Price p : refundablePrice.getPriceItems()) {
            CurrencyAmount amount = p.getTotal();
            if (amount.getAmount().intValue() != 0) {
                this.models.add(new StandardRowEpoxyModel_().title((CharSequence) p.getLocalizedTitle()).placeholderText((CharSequence) amount.formattedForDisplay()));
            }
        }
    }

    private void addNonRefundableRows(Price nonRefundablePrice) {
        for (Price p : nonRefundablePrice.getPriceItems()) {
            CurrencyAmount amount = p.getTotal();
            if (amount.getAmount().intValue() != 0) {
                this.models.add(new StandardRowEpoxyModel_().title((CharSequence) p.getLocalizedTitle()).actionText((CharSequence) "(" + amount.formattedForDisplay() + ")").subtitle(C0880R.string.non_refundable).clickListener(CancelReservationSummaryAdapter$$Lambda$2.lambdaFactory$(this, p)));
            }
        }
    }

    private void addSummaryLine(Reservation reservation) {
        this.models.add(new MicroSectionHeaderEpoxyModel_().titleRes(C0880R.string.your_total_refund));
        this.models.add(new PriceSummaryEpoxyModel_().currencyAmount(reservation.getGuestCancellationRefundBreakdown().getRefundablePrice().getTotal()).hideCaption(true).showDivider(false));
        String providerName = null;
        String lastFour = null;
        if (reservation.getLastVaultablePaymentOption() != null) {
            providerName = reservation.getLastVaultablePaymentOption().getProviderName();
            lastFour = reservation.getLastVaultablePaymentOption().getCreditCardLastFour();
        }
        this.models.add(new SimpleTextRowEpoxyModel_().text(getRefundHint(providerName, lastFour)).small());
    }

    private String getRefundHint(String providerName, String lastFour) {
        if (TextUtils.isEmpty(providerName) && TextUtils.isEmpty(providerName)) {
            return this.context.getString(C0880R.string.cancellation_is_effective_immediately);
        }
        if (TextUtils.isEmpty(providerName)) {
            return this.context.getString(C0880R.string.cancellation_is_effective_immediately_with_payment_card_info, new Object[]{lastFour});
        } else if (TextUtils.isEmpty(lastFour)) {
            return this.context.getString(C0880R.string.cancellation_is_effective_immediately_with_payment_account_info, new Object[]{providerName});
        } else {
            return this.context.getString(C0880R.string.f1171x3ce36b16, new Object[]{providerName, lastFour});
        }
    }
}
