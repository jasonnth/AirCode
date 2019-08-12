package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.ChineseResidentIdentity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetSavedChinaIdentitesResponse {
    @JsonProperty("china_resident_identity_cards")
    public List<ChineseResidentIdentity> chinaIdentites;
}
