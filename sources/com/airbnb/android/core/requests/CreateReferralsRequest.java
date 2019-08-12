package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.GrayUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreateReferralsRequest extends BaseRequestV2<Object> {
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("contacts")
        List<C0831Contact> contacts;

        /* renamed from: com.airbnb.android.core.requests.CreateReferralsRequest$RequestBody$Contact */
        private static class C0831Contact {
            private static final int ACTION_EMAIL = 2;
            private static final int ACTION_SMS = 1;
            @JsonProperty("action")
            final int action;
            @JsonProperty("external_contact_id")
            final int contactId;
            @JsonProperty("referred_email")
            final String email;
            @JsonProperty("name")
            final String name;
            @JsonProperty("referred_phone")
            final String phone;

            public C0831Contact(GrayUser grayUser) {
                this(grayUser.getEmail().hashCode(), grayUser.getName(), grayUser.getEmail());
            }

            private C0831Contact(int id, String name2, String email2) {
                this.name = name2;
                this.contactId = id;
                this.phone = null;
                this.action = 2;
                this.email = email2;
            }
        }

        private RequestBody(List<GrayUser> grayUsers) {
            this.contacts = new ArrayList(grayUsers.size());
            for (GrayUser grayUser : grayUsers) {
                this.contacts.add(new C0831Contact(grayUser));
            }
        }
    }

    public CreateReferralsRequest(List<GrayUser> grayUsers) {
        this.requestBody = new RequestBody(grayUsers);
    }

    public String getPath() {
        return "referral_jobs";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
