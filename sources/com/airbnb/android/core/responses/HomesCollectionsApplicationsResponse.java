package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class HomesCollectionsApplicationsResponse extends BaseResponse {
    @JsonProperty("homes_collections_applications")
    public List<HomeCollectionApplication> applications;

    public HomeCollectionApplication getSingleResponse() {
        if (this.applications.isEmpty()) {
            return null;
        }
        return (HomeCollectionApplication) this.applications.get(0);
    }
}
