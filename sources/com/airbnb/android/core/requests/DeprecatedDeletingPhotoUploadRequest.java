package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.MultipartRequest;
import com.airbnb.android.core.responses.DeprecatedPhotoUploadResponse;
import com.airbnb.android.utils.IOUtils;
import com.google.common.collect.FluentIterable;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.Part;

@Deprecated
public class DeprecatedDeletingPhotoUploadRequest extends MultipartRequest<DeprecatedPhotoUploadResponse> {
    private static final String KEY_IMAGE = "image";
    private static final String VALUE_BINARY = "binary";
    private final List<File> files;
    public final long listingId;
    private final List<Part> parts = FluentIterable.from((Iterable<E>) this.files).transform(DeprecatedDeletingPhotoUploadRequest$$Lambda$2.lambdaFactory$(this)).toList();

    public static DeprecatedDeletingPhotoUploadRequest createRequest(long listingId2, List<String> filePaths) {
        return new DeprecatedDeletingPhotoUploadRequest(listingId2, filePaths);
    }

    private DeprecatedDeletingPhotoUploadRequest(long listingId2, List<String> filePaths) {
        this.listingId = listingId2;
        this.files = FluentIterable.from((Iterable<E>) filePaths).transform(DeprecatedDeletingPhotoUploadRequest$$Lambda$1.lambdaFactory$()).toList();
    }

    /* access modifiers changed from: private */
    public Part createPhotoPart(File file) {
        return new Part("image", RequestBody.create(IOUtils.getContentType(file.getName()), file), VALUE_BINARY, file.getPath());
    }

    public AirResponse<DeprecatedPhotoUploadResponse> transformResponse(AirResponse<DeprecatedPhotoUploadResponse> response) {
        for (File file : this.files) {
            if (file.exists()) {
                file.delete();
            }
        }
        return response;
    }

    public String getPath() {
        return "listings/" + this.listingId + "/update";
    }

    public List<Part> getParts() {
        return this.parts;
    }

    public Type successResponseType() {
        return DeprecatedPhotoUploadResponse.class;
    }
}
