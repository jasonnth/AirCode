package com.airbnb.android.profile_completion.models;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.profile_completion.C7646R;
import com.airbnb.p027n2.components.ProfileCompletionBarRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ProfileCompletionBarRowEpoxyModel extends AirEpoxyModel<ProfileCompletionBarRow> {
    private static final int DEFAULT_FILLED_SECTION_COLOR = C7646R.color.n2_babu;
    OnClickListener clickListener;
    int filledSectionColor = DEFAULT_FILLED_SECTION_COLOR;
    CharSequence progressLabel;
    int progressLabelRes;
    boolean progressLabelVisible;
    CharSequence subtitle;
    int subtitleRes;
    float threshold;
    boolean thresholdIndicatorVisible;
    CharSequence title;
    int titleRes;
    float value;

    public void bind(ProfileCompletionBarRow view) {
        super.bind(view);
        Context context = view.getContext();
        view.setTitle(this.titleRes != 0 ? context.getString(this.titleRes) : this.title);
        view.setSubtitle(this.subtitleRes != 0 ? context.getString(this.subtitleRes) : this.subtitle);
        view.setProgressLabel(this.progressLabelRes != 0 ? context.getString(this.progressLabelRes) : this.progressLabel);
        view.setProgressLabelVisible(this.progressLabelVisible);
        view.setValue(this.value);
        view.setThreshold(this.threshold);
        view.setThresholdIndicatorVisible(this.thresholdIndicatorVisible);
        view.setFilledSectionColor(this.filledSectionColor);
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(ProfileCompletionBarRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    public ProfileCompletionBarRowEpoxyModel title(CharSequence title2) {
        this.titleRes = 0;
        this.title = title2;
        return this;
    }

    public ProfileCompletionBarRowEpoxyModel title(int titleRes2) {
        this.titleRes = titleRes2;
        return this;
    }

    public ProfileCompletionBarRowEpoxyModel subtitle(CharSequence subtitle2) {
        this.subtitleRes = 0;
        this.subtitle = subtitle2;
        return this;
    }

    public ProfileCompletionBarRowEpoxyModel subtitle(int subtitleRes2) {
        this.subtitleRes = subtitleRes2;
        return this;
    }

    public ProfileCompletionBarRowEpoxyModel progressLabel(CharSequence progressLabel2) {
        this.progressLabelRes = 0;
        this.progressLabel = progressLabel2;
        return this;
    }

    public ProfileCompletionBarRowEpoxyModel progressLabel(int progressLabelRes2) {
        this.progressLabelRes = progressLabelRes2;
        return this;
    }

    public ProfileCompletionBarRowEpoxyModel value(float value2) {
        this.value = value2;
        return this;
    }

    public ProfileCompletionBarRowEpoxyModel threshold(float threshold2) {
        this.threshold = threshold2;
        return this;
    }
}