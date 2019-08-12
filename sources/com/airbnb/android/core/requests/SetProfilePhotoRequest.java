package com.airbnb.android.core.requests;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.MultipartRequest;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.responses.UserWrapperResponse;
import com.airbnb.android.utils.IOUtils;
import com.squareup.otto.Bus;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Part;

public class SetProfilePhotoRequest extends MultipartRequest<UserWrapperResponse> {
    AirbnbAccountManager accountManager;
    Bus bus;
    private final boolean enableFaceDetection;
    private final File file;
    private final Handler handler;
    private final boolean overrideFaceDetection;

    public SetProfilePhotoRequest(Context context, File file2) {
        this(context, file2, true);
    }

    public SetProfilePhotoRequest(Context context, File file2, boolean overrideFaceDetection2) {
        this.handler = new Handler(Looper.getMainLooper());
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
        this.file = file2;
        this.enableFaceDetection = true;
        this.overrideFaceDetection = overrideFaceDetection2;
    }

    public static SetProfilePhotoRequest newRequestWithFaceDetection(Context context, File file2) {
        return new SetProfilePhotoRequest(context, file2, false);
    }

    public static SetProfilePhotoRequest newRequestWithoutFaceDetection(Context context, File file2) {
        return new SetProfilePhotoRequest(context, file2, true);
    }

    public List<Part> getParts() {
        Part photoPart = new Part("user[photo]", RequestBody.create(IOUtils.getContentType(this.file.getName()), this.file), "binary", this.file.getPath());
        ArrayList<Part> parts = new ArrayList<>();
        if (this.enableFaceDetection) {
            Collections.addAll(parts, new Part[]{photoPart, new Part("do_face_detection", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(true))), new Part("override_face_detection", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(this.overrideFaceDetection)))});
        } else {
            Collections.addAll(parts, new Part[]{photoPart});
        }
        return parts;
    }

    public String getPath() {
        return "account/update";
    }

    public AirResponse<UserWrapperResponse> transformResponse(AirResponse<UserWrapperResponse> response) {
        UserWrapperResponse data = (UserWrapperResponse) response.body();
        if (this.file.exists()) {
            this.file.delete();
        }
        User responseUser = data.user;
        data.user = this.accountManager.getCurrentUser();
        data.user.setPictureUrl(responseUser.getPictureUrl());
        data.user.setThumbnailUrl(responseUser.getThumbnailUrl());
        data.user.setPictureUrlLarge(responseUser.getPictureUrlLarge());
        this.accountManager.setCurrentUser(data.user);
        this.accountManager.storeCurrentUser();
        this.handler.post(SetProfilePhotoRequest$$Lambda$1.lambdaFactory$(this));
        return response;
    }

    public Type successResponseType() {
        return UserWrapperResponse.class;
    }
}
