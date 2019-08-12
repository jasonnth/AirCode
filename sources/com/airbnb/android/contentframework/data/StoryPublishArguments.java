package com.airbnb.android.contentframework.data;

import android.os.Parcelable;
import com.airbnb.android.contentframework.utils.StoryUtils;
import com.airbnb.android.core.models.StoryCreationPlaceTag;
import com.airbnb.android.utils.Strap;
import java.util.List;

public abstract class StoryPublishArguments implements Parcelable {
    public abstract StoryCreationListingAppendix appendix();

    public abstract String body();

    public abstract List<StoryCreationImage> imageList();

    public abstract StoryCreationPlaceTag placeTag();

    public abstract String title();

    public static StoryPublishArguments newInstance(String title, String body, List<StoryCreationImage> imageList, StoryCreationListingAppendix appendix, StoryCreationPlaceTag placeTag) {
        return new AutoValue_StoryPublishArguments(title, StoryUtils.removeExtraNewLinesFromBodyTextForPosting(body), imageList, appendix, placeTag);
    }

    public Strap getLoggingStrap() {
        String googlePlaceId;
        Strap kv = Strap.make().mo11637kv("image_count", imageList().size()).mo11639kv("title", title()).mo11637kv("body_length", body().length()).mo11638kv("listing_id", appendix().listingId());
        String str = "google_place_id";
        if (placeTag() == null) {
            googlePlaceId = null;
        } else {
            googlePlaceId = placeTag().getGooglePlaceId();
        }
        return kv.mo11639kv(str, googlePlaceId);
    }
}
