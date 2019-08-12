package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GovernmentIdResultsResponse extends BaseResponse {
    @JsonProperty("government_id_results")
    public List<GovernmentIdResult> governmentIdResults;

    private static class GovernmentIdResultComparator implements Comparator<GovernmentIdResult> {
        private GovernmentIdResultComparator() {
        }

        public int compare(GovernmentIdResult result1, GovernmentIdResult result2) {
            if (result1 == null) {
                return result2 == null ? 0 : -1;
            }
            if (result2 == null) {
                return 1;
            }
            return new Long(result2.getId()).compareTo(Long.valueOf(result1.getId()));
        }
    }

    public GovernmentIdResult getLatestResult() {
        if (ListUtils.isEmpty((Collection<?>) this.governmentIdResults)) {
            return null;
        }
        Collections.sort(this.governmentIdResults, new GovernmentIdResultComparator());
        return (GovernmentIdResult) this.governmentIdResults.get(0);
    }
}
