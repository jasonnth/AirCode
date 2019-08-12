package com.airbnb.android.core.payments.models.clientparameters;

import android.os.Parcelable;
import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.C1092As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.C1093Id;

@JsonTypeInfo(include = C1092As.PROPERTY, property = "product_type", use = C1093Id.NAME)
@JsonSubTypes({@Type(name = "MtTrip", value = MagicalTripsClientParameters.class), @Type(name = "PaidAmenityOrder", value = PaidAmenityClientParameters.class), @Type(name = "GiftCredit", value = GiftCardClientParameters.class)})
public abstract class QuickPayParameters implements Parcelable {
    public abstract BillProductType productType();
}
