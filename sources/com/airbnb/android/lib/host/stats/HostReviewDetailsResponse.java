package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.HostRatingDistributionStatistic;
import com.airbnb.android.core.models.ListingRatingAggregation;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HostReviewDetailsResponse extends BaseResponse {
    @JsonProperty("review_rating_aggregations")
    List<ListingRatingAggregation> listingRatingAggregations;
    @JsonProperty("metadata")
    public MetaData metaData;

    public static class AggregationWrapper {
        @JsonProperty("_attributes")
        public HostRatingDistributionStatistic statistic;
    }

    public static class MetaData {
        @JsonProperty("ratings_aggregations_all_listings")
        List<AggregationWrapper> ratingDistributionStatisticsAllListings;
    }

    public ArrayList<HostRatingDistributionStatistic> getRatingDistributionStatistics() {
        if (this.metaData == null || ListUtils.isEmpty((Collection<?>) this.metaData.ratingDistributionStatisticsAllListings)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(FluentIterable.from((Iterable<E>) this.metaData.ratingDistributionStatisticsAllListings).transform(new Function<AggregationWrapper, HostRatingDistributionStatistic>() {
            public HostRatingDistributionStatistic apply(AggregationWrapper input) {
                if (input == null) {
                    return null;
                }
                return input.statistic;
            }
        }).toList());
    }

    public ArrayList<ListingRatingAggregation> getListingRatingAggregations() {
        return new ArrayList<>(this.listingRatingAggregations);
    }
}
