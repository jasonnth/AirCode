package com.airbnb.android.core.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.PatchBuilder;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.analytics.EditProfileAnalytics;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class EditProfileRequest extends BaseRequestV2<UserResponse> {
    private final Strap extras;
    private String newFirstName;
    private String newLastName;

    public EditProfileRequest(ProfileSection section, String newValue, BaseRequestListener<UserResponse> listener) {
        this(Strap.make().mo11639kv(section.getJsonKey(), newValue), listener);
    }

    public EditProfileRequest(Strap newValuePairs, BaseRequestListener<UserResponse> listener) {
        withListener((Observer) listener);
        this.extras = newValuePairs;
    }

    public EditProfileRequest(String newFirstName2, String newLastName2, BaseRequestListener<UserResponse> listener) {
        this(newFirstName2, newLastName2, null, listener);
    }

    public EditProfileRequest(String newFirstName2, String newLastName2, Strap extras2, BaseRequestListener<UserResponse> listener) {
        withListener((Observer) listener);
        this.newFirstName = newFirstName2;
        this.newLastName = newLastName2;
        this.extras = extras2;
    }

    public String getBody() {
        Strap strap = this.extras != null ? this.extras : Strap.make();
        if (!TextUtils.isEmpty(this.newFirstName)) {
            strap.mo11639kv(CohostingConstants.FIRST_NAME_FIELD, this.newFirstName);
        }
        if (!TextUtils.isEmpty(this.newLastName)) {
            strap.mo11639kv("last_name", this.newLastName);
        }
        return new PatchBuilder().replace(strap).toString();
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, EditProfileAnalytics.EDIT_PROFILE);
    }

    public String getPath() {
        return "users/" + AirbnbAccountManager.currentUserId();
    }

    public RequestMethod getMethod() {
        return RequestMethod.PATCH;
    }

    public Strap getHeaders() {
        return Strap.make().mo11639kv(ApiRequestHeadersInterceptor.HEADER_METHOD_OVERRIDE, "PATCH");
    }

    public static Strap makeStrap(ProfileSection section, String newValue) {
        return Strap.make().mo11639kv(section.getJsonKey(), newValue);
    }

    public static String formatBirthdate(AirDate updatedBirthDate) {
        return updatedBirthDate.formatDate((DateFormat) new SimpleDateFormat("MM/d/yyyy"));
    }

    public Type successResponseType() {
        return UserResponse.class;
    }
}
