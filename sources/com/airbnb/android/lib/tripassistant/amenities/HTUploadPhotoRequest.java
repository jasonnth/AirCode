package com.airbnb.android.lib.tripassistant.amenities;

import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.requests.CreateAttachmentRequest;
import java.io.File;

public class HTUploadPhotoRequest extends CreateAttachmentRequest {
    private final String photoPath;

    HTUploadPhotoRequest(HelpThreadIssue issue, String photoPath2) {
        super(issue.getId(), new File(photoPath2), "help_threads");
        this.photoPath = photoPath2;
    }

    /* access modifiers changed from: 0000 */
    public String getPhotoPath() {
        return this.photoPath;
    }
}
