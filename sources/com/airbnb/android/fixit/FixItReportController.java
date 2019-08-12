package com.airbnb.android.fixit;

import com.airbnb.android.core.models.FixItItem;
import com.airbnb.android.core.models.FixItReport;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.components.IconRowModel_;
import com.airbnb.p027n2.epoxy.Typed2AirEpoxyController;
import java.util.List;

public class FixItReportController extends Typed2AirEpoxyController<FixItReport, Boolean> {
    DocumentMarqueeEpoxyModel_ header;
    private final Listener listener;
    EpoxyControllerLoadingModel_ loader;

    public interface Listener {
        void fixItItem(FixItItem fixItItem);
    }

    public FixItReportController(Listener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(FixItReport report, Boolean isLoading) {
        this.loader.addIf(isLoading.booleanValue(), (EpoxyController) this);
        if (report != null) {
            buildHeaderRow(report);
            buildItemsSection(C6380R.string.report_title_required_next_steps, null, report.getRequiredToDoItems());
            buildItemsSection(C6380R.string.report_title_nice_to_haves, Integer.valueOf(C6380R.string.report_description_nice_to_haves), report.getNotRequiredToDoItems());
            buildItemsSection(C6380R.string.report_title_awaiting_review, Integer.valueOf(C6380R.string.report_description_awaiting_review), report.getAwaitingReviewItems());
            buildItemsSection(C6380R.string.report_title_completed, null, report.getApprovedItems());
        }
    }

    private void buildHeaderRow(FixItReport report) {
        this.header.titleRes(report.isComplete() ? C6380R.string.report_header_title_complete : C6380R.string.report_header_title).captionRes(report.isComplete() ? C6380R.string.report_header_subtitle_complete : C6380R.string.report_header_subtitle);
    }

    private void buildItemsSection(int titleRes, Integer descriptionRes, List<FixItItem> requiredItems) {
        if (requiredItems.size() != 0) {
            SectionHeaderEpoxyModel_ sectionHeader = new SectionHeaderEpoxyModel_().m5554id((long) titleRes).titleRes(titleRes);
            if (descriptionRes != null) {
                sectionHeader.descriptionRes(descriptionRes.intValue());
            }
            sectionHeader.addTo(this);
            for (FixItItem item : requiredItems) {
                buildItemModel(item).addTo(this);
            }
        }
    }

    private IconRowModel_ buildItemModel(FixItItem item) {
        IconRowModel_ iconRow = new IconRowModel_().mo11716id(item.getId()).title(item.getCategory()).subtitleText(item.getName()).onClickListener(FixItReportController$$Lambda$1.lambdaFactory$(this, item));
        switch (item.getStatus()) {
            case 1:
                iconRow.icon(C6380R.C6381drawable.n2_ic_clock_babu);
                break;
            case 2:
                iconRow.icon(C6380R.C6381drawable.n2_ic_check_babu);
                break;
        }
        return iconRow;
    }
}
