package com.airbnb.android.lib.tripassistant.amenities;

import com.airbnb.android.core.models.Attachment;
import com.airbnb.android.core.requests.DeleteAttachmentRequest;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.tripassistant.HelpThreadPhoto;

public class HTDeletePhotoRequest extends DeleteAttachmentRequest {
    private final HelpThreadPhoto photo;

    public HTDeletePhotoRequest(HelpThreadPhoto photo2) {
        super((Attachment) Check.notNull(photo2.attachment(), "Can't delete photo with no attachment"));
        this.photo = photo2;
    }

    public HelpThreadPhoto getPhoto() {
        return this.photo;
    }
}
