package com.airbnb.android.insights;

import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.SimpleOnScrollListener;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Insight.ConversionType;
import com.airbnb.android.core.models.PriceFactor;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.insights.adapters.InsightsAdapter;
import com.airbnb.android.utils.ParcelableUtils;
import com.airbnb.epoxy.EpoxyModel;
import java.util.ArrayList;
import java.util.List;

public class InsightsAnalytics {
    /* access modifiers changed from: private */
    public InsightsAdapter adapter;
    int currentVisiblePosition = -1;
    /* access modifiers changed from: private */
    public LinearLayoutManager layoutManager;
    private final SimpleOnScrollListener loggingScrollListener = new SimpleOnScrollListener() {
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int newCurrentVisiblePosition = InsightsAnalytics.this.layoutManager.findFirstCompletelyVisibleItemPosition();
            EpoxyModel<?> model = InsightsAnalytics.this.adapter.getModelAtPosition(newCurrentVisiblePosition);
            EpoxyModel<?> oldInsightModel = InsightsAnalytics.this.adapter.getModelAtPosition(InsightsAnalytics.this.currentVisiblePosition);
            if (newCurrentVisiblePosition != -1 && newCurrentVisiblePosition != InsightsAnalytics.this.currentVisiblePosition) {
                InsightsAnalytics.this.trackingRequests.clear();
                if (model instanceof InsightEpoxyModel_) {
                    InsightsAnalytics.this.trackingRequests.add(InsightsAnalytics.this.getImpressionRequest(((InsightEpoxyModel_) model).insight()));
                } else if (model instanceof LastInsightEpoxyModel_) {
                    LastInsightEpoxyModel_ lastInsightModel = (LastInsightEpoxyModel_) model;
                    InsightsAnalytics.this.trackingRequests.add(InsightsAnalytics.this.getLastCardImpressionRequest(lastInsightModel.dummyInsight(), lastInsightModel.nextListing() != null));
                }
                if (oldInsightModel instanceof InsightEpoxyModel_) {
                    InsightsAnalytics.this.trackingRequests.add(InsightsAnalytics.this.getSkipRequest(((InsightEpoxyModel_) oldInsightModel).insight()));
                } else if (oldInsightModel instanceof LastInsightEpoxyModel_) {
                    InsightsAnalytics.this.trackingRequests.add(InsightsAnalytics.this.getSkipRequest(((LastInsightEpoxyModel_) oldInsightModel).dummyInsight()));
                }
                new AirBatchRequest(InsightsAnalytics.this.trackingRequests, null).execute(NetworkUtil.singleFireExecutor());
                InsightsAnalytics.this.currentVisiblePosition = newCurrentVisiblePosition;
            }
        }
    };
    /* access modifiers changed from: private */
    public final List<BaseRequestV2<?>> trackingRequests = new ArrayList();
    private final long userId;

    public InsightsAnalytics(long userId2) {
        this.userId = userId2;
    }

    public void setUpForInsightsFragment(RecyclerView recyclerView, LinearLayoutManager layoutManager2, InsightsAdapter adapter2) {
        this.layoutManager = layoutManager2;
        this.adapter = adapter2;
        recyclerView.addOnScrollListener(this.loggingScrollListener);
    }

    private void track(Insight story, int actionType) {
        track(story, actionType, false);
    }

    private void track(Insight story, int actionType, boolean isLastCard) {
        getTrackingRequest(story, actionType, isLastCard).execute(NetworkUtil.singleFireExecutor());
    }

    private InsightsEventRequest getTrackingRequest(Insight story, int actionType, boolean isLastCard) {
        return InsightsEventRequest.forTracking(story, actionType, isLastCard, this.userId);
    }

    private InsightsEventRequest getTrackingRequest(Insight story, int actionType) {
        return getTrackingRequest(story, actionType, false);
    }

    public InsightsEventRequest getImpressionRequest(Insight story) {
        return getTrackingRequest(story, 1);
    }

    public InsightsEventRequest getSkipRequest(Insight story) {
        return getTrackingRequest(story, 4);
    }

    public InsightsEventRequest getLastCardImpressionRequest(Insight dummyStory, boolean isNextListing) {
        Insight story = (Insight) ParcelableUtils.cloneParcelable(dummyStory);
        story.setStoryConversionType(isNextListing ? ConversionType.CompletionWithNextListing : ConversionType.Completion);
        return getTrackingRequest(story, 1, true);
    }

    public void trackConversion(Insight story) {
        track(story, 2);
    }

    public void trackDismiss(Insight story) {
        track(story, 3);
    }

    public void trackUndo(Insight story) {
        track(story, 5);
    }

    public void trackPreview(Insight story) {
        track(story, 6);
    }

    public void trackImplicitConversion(Insight story, int minPrice) {
        InsightsEventRequest.forMinPriceImplicit(story, minPrice, this.userId).execute(NetworkUtil.singleFireExecutor());
    }

    public void trackImplicitConversion(Insight story, PriceFactor weeklyDiscount) {
        InsightsEventRequest.forWeeklyDiscountImplicit(story, weeklyDiscount, this.userId).execute(NetworkUtil.singleFireExecutor());
    }

    public void trackLastCardConversion(Insight dummyStory, boolean isNextListing) {
        Insight story = (Insight) ParcelableUtils.cloneParcelable(dummyStory);
        story.setStoryConversionType(isNextListing ? ConversionType.CompletionWithNextListing : ConversionType.Completion);
        track(story, 2, true);
    }
}
