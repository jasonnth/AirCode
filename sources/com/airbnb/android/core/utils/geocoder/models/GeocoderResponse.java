package com.airbnb.android.core.utils.geocoder.models;

import android.content.Context;
import android.location.Address;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.utils.geocoder.AddressComponentType;
import com.airbnb.android.core.utils.geocoder.models.generated.GenGeocoderResponse;

public class GeocoderResponse extends GenGeocoderResponse {
    public static final Creator<GeocoderResponse> CREATOR = new Creator<GeocoderResponse>() {
        public GeocoderResponse[] newArray(int size) {
            return new GeocoderResponse[size];
        }

        public GeocoderResponse createFromParcel(Parcel source) {
            GeocoderResponse object = new GeocoderResponse();
            object.readFromParcel(source);
            return object;
        }
    };

    private ResponseStatus getStatusCode() {
        return ResponseStatus.getCodeFromKey(this.mStatus);
    }

    public Address toAddress() {
        GeocoderResult result = getFirstResult();
        if (result == null) {
            return null;
        }
        return result.toAddress();
    }

    public AirAddress toAirAddress(Context context) {
        GeocoderResult result = getFirstResult();
        if (result == null) {
            return null;
        }
        return result.toAirAddress(context);
    }

    public GeocoderResult getFirstResult() {
        if (getStatusCode() == ResponseStatus.Ok && getResults() != null && !getResults().isEmpty()) {
            return (GeocoderResult) getResults().get(0);
        }
        return null;
    }

    public String getCityStateCountry() {
        GeocoderResult result = getFirstResult();
        if (result == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(20);
        appendIfPresent(sb, result.getLongName(AddressComponentType.Locality));
        appendIfPresent(sb, result.getLongName(AddressComponentType.Admin1));
        appendIfPresent(sb, result.getShortName(AddressComponentType.Country));
        if (sb.length() != 0) {
            return sb.toString();
        }
        return null;
    }

    public String getCityName() {
        GeocoderResult result = getFirstResult();
        if (result == null) {
            return null;
        }
        return result.getLongName(AddressComponentType.Locality);
    }

    private void appendIfPresent(StringBuilder sb, String term) {
        if (!TextUtils.isEmpty(term)) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(term);
        }
    }
}
