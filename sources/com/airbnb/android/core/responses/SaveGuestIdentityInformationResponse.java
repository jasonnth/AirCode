package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.models.ChineseResidentIdentity;
import com.airbnb.android.core.models.PassportInformation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveGuestIdentityInformationResponse extends BaseResponse {
    @JsonProperty("china_resident_identity_card")
    ChineseResidentIdentity chineseIdentity;
    @JsonProperty("passport")
    PassportInformation passport;

    public GuestIdentity getIdentity() {
        if (this.passport != null) {
            return this.passport;
        }
        if (this.chineseIdentity != null) {
            return this.chineseIdentity;
        }
        return null;
    }
}
