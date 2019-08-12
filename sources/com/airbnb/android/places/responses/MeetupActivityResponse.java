package com.airbnb.android.places.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.PlaceActivity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MeetupActivityResponse extends BaseResponse {
    @JsonProperty("meetup_activity")
    private PlaceActivity meetupActivity;

    public PlaceActivity getMeetupActivity() {
        return this.meetupActivity;
    }
}
