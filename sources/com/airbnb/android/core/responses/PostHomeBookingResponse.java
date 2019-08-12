package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PostHomeBookingResponse extends BaseResponse {
    @JsonProperty("post_home_booking")
    public PHB phb;

    public static class PHB {
        @JsonProperty("promo_picture")
        public Photo backgroundImage;
        @JsonProperty("sections")
        public List<ExploreSection> sections;
        @JsonProperty("subtitle")
        public String subtitle;
        @JsonProperty("title")
        public String title;

        public String getImageUrl() {
            if (this.backgroundImage != null) {
                return this.backgroundImage.getPromoPicture();
            }
            return null;
        }
    }
}
