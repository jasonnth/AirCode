package com.airbnb.android.lib.host.stats;

import android.content.Context;
import com.airbnb.android.core.models.CohostingStandard;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.core.viewcomponents.models.BarRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CohostingStandardEpoxyController extends AirEpoxyController {
    private final Context context;
    DocumentMarqueeEpoxyModel_ headerRow;
    LinkActionRowEpoxyModel_ learnMoreRow;
    private final Listener listener;
    SimpleTextRowEpoxyModel_ noStatsDescriptionRow;
    BarRowEpoxyModel_ responseRateRow;
    private final CohostingStandard standard;
    SimpleTextRowEpoxyModel_ statsExplanationRow;

    interface Listener {
        void goToCohostingStandardPage(Context context);
    }

    CohostingStandardEpoxyController(Context context2, CohostingStandard standard2, Listener listener2) {
        disableAutoDividers();
        this.context = context2;
        this.standard = standard2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        setupHeader();
        setupNoStatsDescriptionRow();
        setupResponseRateRow();
        setupLearnMoreRow();
        setupStatsExplanationRow();
    }

    private void setupHeader() {
        this.headerRow.titleRes(C0880R.string.cohosting_stats_detail_page_title).captionText((CharSequence) this.context.getString(C0880R.string.cohosting_stats_detail_page_subtitle, new Object[]{this.standard.getLastUpdated().getDateString(this.context)})).addTo(this);
    }

    private void setupNoStatsDescriptionRow() {
        this.noStatsDescriptionRow.textRes(C0880R.string.cohosting_stats_detail_page_no_stats_description).showDivider(true).addIf(this.standard.noStats(), (EpoxyController) this);
    }

    private void setupResponseRateRow() {
        String percentageString;
        String string;
        BarRowEpoxyModel_ thresholdIndicatorVisible = this.responseRateRow.titleRes(C0880R.string.cohosting_stats_response_rate_title).filledSectionColor(this.standard.getColorResByResponseRate()).value(this.standard.getCurrentRatioOfResponseRate()).threshold(this.standard.getThresholdRatioOfResponseRate()).thresholdIndicatorVisible(true);
        if (this.standard.noStats()) {
            percentageString = this.context.getString(C0880R.string.host_stats_hosting_standard_metric_not_applicable);
        } else {
            percentageString = getPercentageString((int) (this.standard.getCurrentRatioOfResponseRate() * 100.0f));
        }
        thresholdIndicatorVisible.progressLabel((CharSequence) percentageString).progressLabelVisible(true).showDivider(true);
        if (!this.standard.noStats()) {
            BarRowEpoxyModel_ barRowEpoxyModel_ = this.responseRateRow;
            if (this.standard.meetStandardOfResponseRate()) {
                string = this.context.getString(C0880R.string.cohosting_stats_meet_response_rate_standard_subtitle, new Object[]{Long.valueOf(this.standard.getResponseNumCurrent()), Long.valueOf(this.standard.getInquiryNumTotal())});
            } else {
                string = this.context.getString(C0880R.string.cohosting_stats_not_meet_response_rate_standard_subtitle);
            }
            barRowEpoxyModel_.subtitle((CharSequence) string);
        }
        add((EpoxyModel<?>) this.responseRateRow);
    }

    private void setupLearnMoreRow() {
        this.learnMoreRow.textRes(C0880R.string.cohosting_stats_learn_more_link).clickListener(CohostingStandardEpoxyController$$Lambda$1.lambdaFactory$(this)).showDivider(true).clickListener();
        add((EpoxyModel<?>) this.learnMoreRow);
    }

    private void setupStatsExplanationRow() {
        this.statsExplanationRow.textRes(C0880R.string.cohosting_stats_source_explanation).smallAndMuted().addTo(this);
    }

    private String getPercentageString(int percentage) {
        return PercentageUtils.localizePercentage(percentage);
    }
}
