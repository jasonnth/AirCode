package com.airbnb.android.lib.host.stats.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.models.HostRatingDistributionStatistic;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.StarBar;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.Collection;
import java.util.List;

public class ReviewStarBreakdownView extends LinearLayout implements DividerView {
    private Callback callback;
    @BindView
    View divider;
    @BindView
    StarBar fiveStarbar;
    @BindView
    StarBar fourStarbar;
    @BindView
    StarBar oneStarbar;
    private StarBar selectedStarbar;
    @BindView
    StarBar threeStarbar;
    @BindView
    StarBar twoStarbar;

    public interface Callback {
        void onAllSectionsSelected();

        void onSectionSelected(int i);
    }

    public ReviewStarBreakdownView(Context context) {
        super(context);
        init();
    }

    public ReviewStarBreakdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReviewStarBreakdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        LayoutInflater.from(getContext()).inflate(C0880R.layout.review_breakdown_view, this);
        ButterKnife.bind((View) this);
    }

    public void setCallback(Callback callback2) {
        this.callback = callback2;
    }

    @OnClick
    public void onStarSectionClicked(StarBar clickedStarbar) {
        if (this.selectedStarbar == null || this.selectedStarbar != clickedStarbar) {
            this.selectedStarbar = clickedStarbar;
            this.oneStarbar.setSelected(false);
            this.twoStarbar.setSelected(false);
            this.threeStarbar.setSelected(false);
            this.fourStarbar.setSelected(false);
            this.fiveStarbar.setSelected(false);
            this.selectedStarbar.setSelected(true);
            if (this.callback != null) {
                int id = this.selectedStarbar.getId();
                if (id == C0880R.C0882id.one_stars_row) {
                    this.callback.onSectionSelected(1);
                } else if (id == C0880R.C0882id.two_stars_row) {
                    this.callback.onSectionSelected(2);
                } else if (id == C0880R.C0882id.three_stars_row) {
                    this.callback.onSectionSelected(3);
                } else if (id == C0880R.C0882id.four_stars_row) {
                    this.callback.onSectionSelected(4);
                } else if (id == C0880R.C0882id.five_stars_row) {
                    this.callback.onSectionSelected(5);
                }
            }
        } else {
            this.oneStarbar.setSelected(true);
            this.twoStarbar.setSelected(true);
            this.threeStarbar.setSelected(true);
            this.fourStarbar.setSelected(true);
            this.fiveStarbar.setSelected(true);
            this.selectedStarbar = null;
            this.callback.onAllSectionsSelected();
        }
    }

    public void setReviewData(List<HostRatingDistributionStatistic> statistics) {
        if (!ListUtils.isEmpty((Collection<?>) statistics)) {
            for (HostRatingDistributionStatistic statistic : statistics) {
                int percentage = statistic.getPercentage();
                switch (statistic.getRating()) {
                    case 1:
                        this.oneStarbar.setPercentage((float) percentage);
                        break;
                    case 2:
                        this.twoStarbar.setPercentage((float) percentage);
                        break;
                    case 3:
                        this.threeStarbar.setPercentage((float) percentage);
                        break;
                    case 4:
                        this.fourStarbar.setPercentage((float) percentage);
                        break;
                    case 5:
                        this.fiveStarbar.setPercentage((float) percentage);
                        break;
                }
            }
        }
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }
}
