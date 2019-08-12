package com.airbnb.android.hostcalendar.adapters;

import android.content.Context;
import android.graphics.drawable.InsetDrawable;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarDayPriceInfo;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ScratchMicroRowWithRightTextEpoxyModel_;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import com.airbnb.p027n2.primitives.fonts.CustomFontSpan;
import com.airbnb.p027n2.primitives.fonts.Font;
import java.text.DateFormat;
import java.util.List;

public class MultiDayPriceTipsEpoxyController extends TypedAirEpoxyController<List<CalendarDay>> {
    private final InsetDrawable checkmarkDrawable;
    private final ImageSpan checkmarkImageSpan;
    private final OnPriceTipsDisclaimerClickedListener clickListener;
    private final Context context;
    private final DateFormat dateFormat = DateFormat.getDateInstance();
    DocumentMarqueeEpoxyModel_ documentMarquee;
    private final CustomFontSpan lightFontSpan;
    private final RelativeSizeSpan smallerSizeSpan = new RelativeSizeSpan(0.8f);
    private final StrikethroughSpan strikethroughSpan = new StrikethroughSpan();

    public interface OnPriceTipsDisclaimerClickedListener {
        void onDisclaimerClicked();
    }

    public MultiDayPriceTipsEpoxyController(Context context2, OnPriceTipsDisclaimerClickedListener clickListener2) {
        this.context = context2;
        this.clickListener = clickListener2;
        this.lightFontSpan = new CustomFontSpan(context2, Font.CircularLight);
        int checkmarkSize = (int) context2.getResources().getDimension(C6418R.dimen.n2_vertical_padding_small);
        int checkmarkPadding = (int) context2.getResources().getDimension(C6418R.dimen.n2_vertical_padding_tiny_half);
        this.checkmarkDrawable = new InsetDrawable(ContextCompat.getDrawable(context2, C6418R.C6419drawable.n2_ic_check_hof), 0, 0, checkmarkPadding, checkmarkPadding);
        this.checkmarkDrawable.setBounds(0, 0, checkmarkSize, checkmarkSize);
        this.checkmarkImageSpan = new ImageSpan(this.checkmarkDrawable);
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<CalendarDay> data) {
        this.documentMarquee.titleText((CharSequence) this.context.getResources().getQuantityString(C6418R.plurals.price_tips_for_x_dates, data.size(), new Object[]{Integer.valueOf(data.size())})).captionText((CharSequence) TextUtil.fromHtmlSafe(this.context.getString(C6418R.string.host_calendar_multi_day_price_tips_subtitle))).clickListener(MultiDayPriceTipsEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        for (CalendarDay day : data) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (day.getPriceInfo().getNativeSuggestedPrice() != day.getPriceInfo().getNativePrice()) {
                spannableStringBuilder.append(day.getFormattedNativePrice());
                com.airbnb.p027n2.utils.TextUtil.addSpan(this.strikethroughSpan, spannableStringBuilder);
                com.airbnb.p027n2.utils.TextUtil.addSpan(this.smallerSizeSpan, spannableStringBuilder);
                com.airbnb.p027n2.utils.TextUtil.addSpan(this.lightFontSpan, spannableStringBuilder);
                spannableStringBuilder.append(" ");
            } else {
                spannableStringBuilder.append("placeholder text for image");
                com.airbnb.p027n2.utils.TextUtil.addSpan(this.checkmarkImageSpan, spannableStringBuilder);
            }
            CalendarDayPriceInfo priceInfo = day.getPriceInfo();
            spannableStringBuilder.append(CurrencyUtils.formatCurrencyAmount((double) priceInfo.getNativeSuggestedPrice(), priceInfo.getNativeCurrency()));
            String dateString = day.getDate().formatDate(this.dateFormat);
            new ScratchMicroRowWithRightTextEpoxyModel_().title(dateString).text(spannableStringBuilder).m5484id((CharSequence) dateString).addTo(this);
        }
    }
}
