package com.airbnb.android.core.viewcomponents.models;

import android.content.res.Resources;
import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.RangeDisplay;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class RangeDisplayEpoxyModel extends AirEpoxyModel<RangeDisplay> {
    private static final boolean DEFAULT_ENABLED = true;
    private static final boolean DEFAULT_FORMAT_WITH_YEAR = false;
    OnClickListener clickListener;
    boolean enabled = DEFAULT_ENABLED;
    AirDate endDate;
    int endSubTitleHintRes;
    int endSubTitleRes;
    CharSequence endSubtitle;
    CharSequence endSubtitleHint;
    CharSequence endTitle;
    CharSequence endTitleHint;
    int endTitleHintRes;
    int endTitleRes;
    boolean formatWithYear = false;
    AirDate startDate;
    int startSubTitleHintRes;
    int startSubTitleRes;
    CharSequence startSubtitle;
    CharSequence startSubtitleHint;
    CharSequence startTitle;
    CharSequence startTitleHint;
    int startTitleHintRes;
    int startTitleRes;

    public void bind(RangeDisplay view) {
        CharSequence actualStartTitle;
        CharSequence actualStartSubtitle;
        CharSequence actualEndTitle;
        CharSequence actualEndSubtitle;
        super.bind(view);
        Resources resources = view.getResources();
        String upperRowFormat = this.formatWithYear ? resources.getString(C0716R.string.date_name_format_trailing_comma) : resources.getString(C0716R.string.day_of_week_format);
        String lowerRowFormat = this.formatWithYear ? resources.getString(C0716R.string.full_year_format) : resources.getString(C0716R.string.date_name_format);
        if (this.startDate != null) {
            actualStartTitle = this.startDate.formatDate(upperRowFormat);
            actualStartSubtitle = this.startDate.formatDate(lowerRowFormat);
        } else {
            actualStartTitle = this.startTitleRes != 0 ? resources.getString(this.startTitleRes) : this.startTitle;
            actualStartSubtitle = this.startSubTitleRes != 0 ? resources.getString(this.startSubTitleRes) : this.startSubtitle;
        }
        CharSequence actualStartTitleHint = this.startTitleHintRes != 0 ? resources.getString(this.startTitleHintRes) : this.startTitleHint;
        CharSequence actualStartSubtitleHint = this.startSubTitleHintRes != 0 ? resources.getString(this.startSubTitleHintRes) : this.startSubtitleHint;
        if (this.endDate != null) {
            actualEndTitle = this.endDate.formatDate(upperRowFormat);
            actualEndSubtitle = this.endDate.formatDate(lowerRowFormat);
        } else {
            actualEndTitle = this.endTitleRes != 0 ? resources.getString(this.endTitleRes) : this.endTitle;
            actualEndSubtitle = this.endSubTitleRes != 0 ? resources.getString(this.endSubTitleRes) : this.endSubtitle;
        }
        CharSequence actualEndTitleHint = this.endTitleHintRes != 0 ? resources.getString(this.endTitleHintRes) : this.endTitleHint;
        CharSequence actualEndSubtitleHint = this.endSubTitleHintRes != 0 ? resources.getString(this.endSubTitleHintRes) : this.endSubtitleHint;
        view.setStartTitle(actualStartTitle);
        view.setStartSubtitle(actualStartSubtitle);
        view.setStartTitleHint(actualStartTitleHint);
        view.setStartSubtitleHint(actualStartSubtitleHint);
        view.setEndTitle(actualEndTitle);
        view.setEndSubtitle(actualEndSubtitle);
        view.setEndTitleHint(actualEndTitleHint);
        view.setEndSubtitleHint(actualEndSubtitleHint);
        view.setOnClickListener(this.clickListener);
        view.setEnabled(this.enabled);
    }

    public void unbind(RangeDisplay view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public AirEpoxyModel<RangeDisplay> reset() {
        this.enabled = DEFAULT_ENABLED;
        this.formatWithYear = false;
        return super.reset();
    }

    public int getDividerViewType() {
        return 0;
    }
}
