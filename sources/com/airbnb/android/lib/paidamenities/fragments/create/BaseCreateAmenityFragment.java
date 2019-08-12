package com.airbnb.android.lib.paidamenities.fragments.create;

import android.os.Bundle;
import com.airbnb.android.core.analytics.PaidAmenityJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.paidamenities.activities.CreateAmenityActivity;
import com.airbnb.android.lib.paidamenities.activities.CreateAmenityActivity.Flow;
import com.airbnb.android.lib.paidamenities.controllers.CreateAmenityNavigationController;
import com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails;
import com.airbnb.android.lib.paidamenities.requests.CreatePaidAmenityRequest;
import com.airbnb.android.lib.paidamenities.requests.bodies.CreatePaidAmenityRequestBody;

public abstract class BaseCreateAmenityFragment extends AirFragment {
    protected Flow flow;
    protected CreateAmenityNavigationController navigationController;
    protected PaidAmenityDetails paidAmenityDetails;
    PaidAmenityJitneyLogger paidAmenityJitneyLogger;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        syncDataWithActivity();
    }

    public CreateAmenityActivity getCreateAmenityActivity() {
        return (CreateAmenityActivity) getActivity();
    }

    public CreatePaidAmenityRequest getCreatePaidAmenityRequest() {
        return CreatePaidAmenityRequest.forBody(CreatePaidAmenityRequestBody.create(this.paidAmenityDetails));
    }

    private void syncDataWithActivity() {
        this.navigationController = getCreateAmenityActivity().getNavigationController();
        this.paidAmenityDetails = getCreateAmenityActivity().getPaidAmenityDetails();
        this.flow = getCreateAmenityActivity().getFlow();
    }
}
