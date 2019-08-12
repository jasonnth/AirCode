package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.HostReferralsInvite;
import com.airbnb.android.core.models.ReferralContact.Email;
import com.airbnb.android.core.models.ReferralContact.Phone;
import java.lang.reflect.Type;
import java.util.List;

public class SendHostReferralsInviteRequest extends BaseRequestV2<Object> {
    private final HostReferralsInvite invites;

    public SendHostReferralsInviteRequest(List<Email> emails, List<Phone> phoneNumbers) {
        this.invites = new HostReferralsInvite(emails, phoneNumbers);
    }

    public String getPath() {
        return "host_referral_batches";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return this.invites;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
