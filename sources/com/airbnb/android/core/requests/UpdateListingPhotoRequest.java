package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class UpdateListingPhotoRequest extends BaseRequestV2<BaseResponse> {
    public static final String KEY_CAPTION = "caption";
    public static final String KEY_SORT_ORDER = "sort_order";
    public final String caption;
    public final long listingId;
    public final boolean makeCoverPhoto;
    public final long photoId;

    public static UpdateListingPhotoRequest forCaption(long listingId2, long photoId2, String caption2) {
        return new UpdateListingPhotoRequest(listingId2, photoId2, caption2, false);
    }

    public static UpdateListingPhotoRequest forCaption(long listingId2, long photoId2, String caption2, boolean makeCoverPhoto2) {
        return new UpdateListingPhotoRequest(listingId2, photoId2, caption2, makeCoverPhoto2);
    }

    private UpdateListingPhotoRequest(long listingId2, long photoId2, String caption2, boolean makeCoverPhoto2) {
        this.listingId = listingId2;
        this.photoId = photoId2;
        this.caption = caption2;
        this.makeCoverPhoto = makeCoverPhoto2;
    }

    public String getPath() {
        return "listing_photos/" + this.listingId + "_" + this.photoId;
    }

    public Object getBody() {
        Strap strap = Strap.make().mo11639kv("caption", this.caption);
        if (this.makeCoverPhoto) {
            strap.mo11637kv(KEY_SORT_ORDER, 1);
        }
        return strap;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }
}
