package com.airbnb.android.core.requests.photos;

import com.airbnb.airrequest.MultipartRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.responses.PhotoUploadResponse;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.utils.IOUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.Part;

public class PhotoUploadRequest extends MultipartRequestV2<PhotoUploadResponse> {
    private static final String KEY_ATTACHMENT = "attachment";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_JSON_ROOT_BODY = "json_root_body";
    private static final String VALUE_BINARY = "binary";
    public final long offlineId;
    public final PhotoUpload photoUpload;

    private static final class CheckInGuidePhotoBody {
        @JsonProperty("content_type")
        String contentType;
        @JsonProperty("filename")
        String fileName;

        CheckInGuidePhotoBody(String contentType2, String fileName2) {
            this.contentType = contentType2;
            this.fileName = fileName2;
        }
    }

    private static final class ListingPhotoBody {
        @JsonProperty("listing_id")
        long listingId;

        ListingPhotoBody(long listingId2) {
            this.listingId = listingId2;
        }
    }

    public static PhotoUploadRequest createRequest(long offlineId2, PhotoUpload photoUpload2) {
        return new PhotoUploadRequest(offlineId2, photoUpload2);
    }

    private PhotoUploadRequest(long offlineId2, PhotoUpload photoUpload2) {
        this.offlineId = offlineId2;
        this.photoUpload = photoUpload2;
    }

    public String getPath() {
        switch (this.photoUpload.uploadTarget()) {
            case ListingPhoto:
                return "listing_photos/";
            case CheckInGuide:
                return "check_in_guide_steps/" + this.photoUpload.uploadRequestId();
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Invalid upload target in PhotoUploadRequest: " + this.photoUpload.uploadTarget()));
                return null;
        }
    }

    public RequestMethod getMethod() {
        switch (this.photoUpload.uploadTarget()) {
            case ListingPhoto:
                return RequestMethod.POST;
            case CheckInGuide:
                return RequestMethod.PUT;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Invalid request method PhotoUploadRequest: " + this.photoUpload.uploadTarget()));
                return RequestMethod.POST;
        }
    }

    public List<Part> getParts() {
        List<Part> parts = new ArrayList<>();
        File file = new File(this.photoUpload.path());
        switch (this.photoUpload.uploadTarget()) {
            case ListingPhoto:
                parts.add(createContentTypePart(file, "image"));
                parts.add(new Part(KEY_JSON_ROOT_BODY, new ListingPhotoBody(this.photoUpload.uploadRequestId())));
                break;
            case CheckInGuide:
                parts.add(createContentTypePart(file, KEY_ATTACHMENT));
                parts.add(new Part(KEY_JSON_ROOT_BODY, new CheckInGuidePhotoBody(ImageUtils.getContentTypeFromFilePath(file.getPath()), file.getName())));
                break;
        }
        return parts;
    }

    public Type successResponseType() {
        return PhotoUploadResponse.class;
    }

    private Part createContentTypePart(File file, String partName) {
        return new Part(partName, RequestBody.create(IOUtils.getContentType(file.getName()), file), VALUE_BINARY, file.getPath());
    }
}
