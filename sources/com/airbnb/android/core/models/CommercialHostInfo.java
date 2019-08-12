package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.generated.GenCommercialHostInfo;
import java.util.ArrayList;
import java.util.List;

public class CommercialHostInfo extends GenCommercialHostInfo {
    public static final Creator<CommercialHostInfo> CREATOR = new Creator<CommercialHostInfo>() {
        public CommercialHostInfo[] newArray(int size) {
            return new CommercialHostInfo[size];
        }

        public CommercialHostInfo createFromParcel(Parcel source) {
            CommercialHostInfo object = new CommercialHostInfo();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getFormattedInfo(Context context) {
        List<String> info = new ArrayList<>();
        if (!TextUtils.isEmpty(getFormattedBusiness(context))) {
            info.add(getFormattedBusiness(context));
        }
        if (!TextUtils.isEmpty(getFormattedAddress(context))) {
            info.add(getFormattedAddress(context));
        }
        if (!TextUtils.isEmpty(getFormattedPhoneEmail(context))) {
            info.add(getFormattedPhoneEmail(context));
        }
        if (!TextUtils.isEmpty(getLegalRepresentatives())) {
            info.add(context.getString(C0716R.string.business_details_legal_representative) + getLegalRepresentatives());
        }
        if (!TextUtils.isEmpty(getAdditionalInfo())) {
            info.add(context.getString(C0716R.string.business_details_additional_information) + "\n" + getAdditionalInfo());
        }
        return TextUtils.join("\n\n", info);
    }

    public String getFormattedAddress(Context context) {
        List<String> address = new ArrayList<>();
        if (!TextUtils.isEmpty(getLegalRepresentatives())) {
            address.add(getLegalRepresentatives());
        }
        if (!TextUtils.isEmpty(getStreet())) {
            address.add(getStreet());
        }
        if (!TextUtils.isEmpty(getApt())) {
            address.add(getApt());
        }
        if (!TextUtils.isEmpty(getCity())) {
            String cityStateText = getCity();
            if (!TextUtils.isEmpty(getState())) {
                cityStateText = context.getString(C0716R.string.business_details_city_state, new Object[]{getCity(), getState()});
            }
            address.add(cityStateText);
        }
        if (!TextUtils.isEmpty(getZipcode())) {
            address.add(getZipcode());
        }
        if (!TextUtils.isEmpty(getCountry())) {
            address.add(getCountry());
        }
        if (address.isEmpty()) {
            return "";
        }
        address.add(0, context.getString(C0716R.string.business_details_address));
        return TextUtils.join("\n", address);
    }

    public String getFormattedBusiness(Context context) {
        List<String> business = new ArrayList<>();
        if (!TextUtils.isEmpty(getBusinessName())) {
            business.add(context.getString(C0716R.string.business_details_business_name) + getBusinessName());
        }
        if (!TextUtils.isEmpty(getRegistrationNumbers())) {
            business.add(context.getString(C0716R.string.business_details_trade_register_number) + getRegistrationNumbers());
        }
        if (!TextUtils.isEmpty(getVatNumber())) {
            business.add(context.getString(C0716R.string.business_details_vat_number) + getVatNumber());
        }
        return TextUtils.join("\n", business);
    }

    public String getFormattedPhoneEmail(Context context) {
        List<String> phoneEmail = new ArrayList<>();
        if (!TextUtils.isEmpty(getEmail())) {
            phoneEmail.add(context.getString(C0716R.string.business_details_email) + getEmail());
        }
        if (!TextUtils.isEmpty(getPhoneNumber())) {
            phoneEmail.add(context.getString(C0716R.string.business_details_phone) + getPhoneNumber());
        }
        return TextUtils.join("\n", phoneEmail);
    }
}
