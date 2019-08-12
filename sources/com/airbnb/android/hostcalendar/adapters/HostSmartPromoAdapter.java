package com.airbnb.android.hostcalendar.adapters;

import android.content.Context;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.CalendarDayPromotion;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

public class HostSmartPromoAdapter extends TypedAirEpoxyController<Set<CalendarDayPromotion>> {
    private final Context context;
    private final RequestManager requestManager;

    public HostSmartPromoAdapter(Context context2, RequestManager requestManager2) {
        this.context = context2;
        this.requestManager = requestManager2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(Set<CalendarDayPromotion> promotions) {
        for (CalendarDayPromotion promotion : promotions) {
            long smartPromoId = promotion.getPromotionId();
            new SimpleTextRowEpoxyModel_().text(getOfferText(promotion)).m5578id(smartPromoId).clickListener(HostSmartPromoAdapter$$Lambda$1.lambdaFactory$(this, smartPromoId)).small().addTo(this);
        }
    }

    private CharSequence getOfferText(CalendarDayPromotion promotion) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d");
        String discountPercentage = PercentageUtils.localizePercentage(promotion.getDiscountPercentage());
        String endDate = promotion.getEndDate().formatDate((DateFormat) dateFormat);
        switch (promotion.getPromotionType()) {
            case SmartPromotion:
                return TextUtil.fromHtmlSafe(this.context.getResources().getString(C6418R.string.host_calendar_smart_promotion_edit_sheet, new Object[]{discountPercentage, promotion.getStartDate().formatDate((DateFormat) dateFormat), endDate}));
            case NewHostingPromotion:
                return TextUtil.fromHtmlSafe(this.context.getResources().getString(C6418R.string.host_calendar_new_host_promotion_edit_sheet, new Object[]{discountPercentage, endDate, promotion.getExpiredAt().format(dateFormat)}));
            default:
                return "";
        }
    }
}
