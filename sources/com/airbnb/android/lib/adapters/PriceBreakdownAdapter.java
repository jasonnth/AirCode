package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PriceSummaryEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ScratchStandardBoldableRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.Collection;
import java.util.List;

public class PriceBreakdownAdapter extends AirEpoxyAdapter {
    public PriceBreakdownAdapter(Context context, int titleRes, String caption, boolean isFromBooking, Price price) {
        super(true);
        DocumentMarqueeEpoxyModel marqueeEpoxyModel = new DocumentMarqueeEpoxyModel_().titleRes(titleRes);
        if (isFromBooking) {
            marqueeEpoxyModel.captionText(caption);
        }
        this.models.add(marqueeEpoxyModel);
        List<Price> priceItems = price.getPriceItems();
        if (!ListUtils.isEmpty((Collection<?>) priceItems)) {
            for (Price priceItem : priceItems) {
                this.models.add(buildRowModel(context, priceItem, isFromBooking));
            }
        }
        this.models.add(new PriceSummaryEpoxyModel_().currencyAmount(price.getTotal()).hideCaption(true));
        notifyDataSetChanged();
    }

    private EpoxyModel<?> buildRowModel(Context context, Price priceItem, boolean isFromBooking) {
        String priceItemTitle = priceItem.getLocalizedTitle();
        ScratchStandardBoldableRowEpoxyModel_ viewModel = new ScratchStandardBoldableRowEpoxyModel_().title((CharSequence) priceItemTitle).infoText((CharSequence) priceItem.getTotal().formattedForDisplay()).m5494id((long) priceItemTitle.hashCode()).bold(isBold(priceItem, isFromBooking)).titleMaxLine(2).subtitle(getSubtitle(context, priceItem)).subtitleMaxLine(Integer.MAX_VALUE);
        if (!TextUtils.isEmpty(priceItem.getUrl())) {
            viewModel.clickListener(PriceBreakdownAdapter$$Lambda$1.lambdaFactory$(context, priceItem));
        }
        return viewModel;
    }

    private CharSequence getSubtitle(Context context, Price priceItem) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(priceItem.getLocalizedExplanation())) {
            builder.append(priceItem.getLocalizedExplanation());
        }
        if (TextUtils.isEmpty(priceItem.getLocalizedUrlText())) {
            return builder.toString();
        }
        String urlText = priceItem.getLocalizedUrlText();
        builder.append(urlText);
        return TextUtil.makeColored(context, C0880R.color.n2_text_color_actionable, builder.toString(), urlText);
    }

    private boolean isBold(Price priceItem, boolean isFromBooking) {
        if (priceItem.getType() == null) {
            return false;
        }
        switch (priceItem.getType()) {
            case AirbnbCredit:
            case Coupon:
            case Discount:
            case GiftCredit:
                if (Experiments.showBoldP4PriceBreakdownCredits() || isFromBooking) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}
