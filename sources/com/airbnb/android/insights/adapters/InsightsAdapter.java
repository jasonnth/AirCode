package com.airbnb.android.insights.adapters;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.insights.InsightEpoxyModel.LoadingState;
import com.airbnb.android.insights.InsightEpoxyModel_;
import com.airbnb.android.insights.LastInsightEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import java.util.LinkedHashMap;

public class InsightsAdapter extends AirEpoxyAdapter {
    private final InsightEventListener listener;

    public interface InsightEventListener {
        void leaveInsights(LastInsightEpoxyModel_ lastInsightEpoxyModel_);

        void onDismissButtonClicked(InsightEpoxyModel_ insightEpoxyModel_);

        void onMoreListingsButtonClicked(LastInsightEpoxyModel_ lastInsightEpoxyModel_);

        void onPrimaryButtonClicked(InsightEpoxyModel_ insightEpoxyModel_);

        void onSecondaryButtonClicked(InsightEpoxyModel_ insightEpoxyModel_);
    }

    public InsightsAdapter(InsightEventListener listener2) {
        this.listener = listener2;
    }

    public void addInsights(LinkedHashMap<Insight, LoadingState> insightToLoadingStateMap) {
        for (Insight insight : insightToLoadingStateMap.keySet()) {
            if (TextUtils.isEmpty(insight.getStoryId())) {
                LastInsightEpoxyModel_ lastCardEpoxyModel = new LastInsightEpoxyModel_().nextListing(insight.getListing()).dummyInsight(insight);
                this.models.add(lastCardEpoxyModel.primaryButtonClickListener(InsightsAdapter$$Lambda$1.lambdaFactory$(this, insight, lastCardEpoxyModel)));
            } else {
                InsightEpoxyModel_ insightModel = new InsightEpoxyModel_().insight(insight);
                this.models.add(insightModel.primaryButtonClickListener(InsightsAdapter$$Lambda$2.lambdaFactory$(this, insightModel)).dismissButtonClickListener(InsightsAdapter$$Lambda$3.lambdaFactory$(this, insightModel)).secondaryButtonClickListener(InsightsAdapter$$Lambda$4.lambdaFactory$(this, insightModel)).m6001id((CharSequence) insight.getStoryId()).loadingState((LoadingState) insightToLoadingStateMap.get(insight)));
            }
        }
        notifyDataSetChanged();
    }

    static /* synthetic */ void lambda$addInsights$0(InsightsAdapter insightsAdapter, Insight insight, LastInsightEpoxyModel_ lastCardEpoxyModel, View v) {
        if (insight.getListing() == null) {
            insightsAdapter.listener.leaveInsights(lastCardEpoxyModel);
        } else {
            insightsAdapter.listener.onMoreListingsButtonClicked(lastCardEpoxyModel);
        }
    }

    public void updateStoryLoadingState(Insight insight, LoadingState loadingState) {
        getModel(insight).loadingState(loadingState);
        notifyDataSetChanged();
    }

    public void updateLastCardLoadingState(LastInsightEpoxyModel_ model, boolean isLoading) {
        model.isLoading(isLoading);
        notifyDataSetChanged();
    }

    private InsightEpoxyModel_ getModel(Insight insight) {
        for (EpoxyModel<?> epoxyModel : this.models) {
            if (epoxyModel instanceof InsightEpoxyModel_) {
                InsightEpoxyModel_ insightModel = (InsightEpoxyModel_) epoxyModel;
                if (insightModel.insight().equals(insight)) {
                    return insightModel;
                }
            }
        }
        return null;
    }

    public EpoxyModel<?> getModelAtPosition(int pos) {
        if (pos < 0 || pos >= this.models.size()) {
            return null;
        }
        return (EpoxyModel) this.models.get(pos);
    }

    public int getModelPosition(EpoxyModel<?> model) {
        return super.getModelPosition(model);
    }

    public void removeModel(EpoxyModel<?> model) {
        super.removeModel(model);
    }

    public void clearModels() {
        this.models.clear();
        notifyDataSetChanged();
    }

    public void onViewAttachedToWindow(EpoxyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        View itemView = holder.itemView;
        int rootWidth = itemView.getRootView().getWidth();
        LayoutParams lp = itemView.getLayoutParams();
        lp.width = (int) (((float) rootWidth) * 0.9f);
        itemView.setLayoutParams(lp);
    }
}
