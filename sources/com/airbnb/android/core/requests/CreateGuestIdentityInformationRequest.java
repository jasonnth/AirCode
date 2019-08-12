package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.core.responses.SaveGuestIdentityInformationResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateGuestIdentityInformationRequest extends BaseRequestV2<SaveGuestIdentityInformationResponse> {
    private final Type identityType;
    private final Object requestBody;

    static class PassportRequestBody extends RequestBody {
        @JsonProperty("nationality")
        final String countryCode;
        @JsonProperty("date_of_expiry")
        final AirDate dateOfExpiry;

        PassportRequestBody(String surname, String givenName, String countryCode2, String identificationNumber, AirDate dateOfExpiry2) {
            super(surname, givenName, identificationNumber);
            this.countryCode = countryCode2;
            this.dateOfExpiry = dateOfExpiry2;
        }
    }

    static abstract class RequestBody {
        @JsonProperty("given_names")
        final String givenName;
        @JsonProperty("id_number")
        final String identificationNumber;
        @JsonProperty("surname")
        final String surname;

        public RequestBody(String surname2, String givenName2, String identificationNumber2) {
            this.surname = surname2.trim();
            this.givenName = givenName2.trim();
            this.identificationNumber = identificationNumber2.trim();
        }
    }

    static class ChineseIdentityRequestBody extends RequestBody {
        ChineseIdentityRequestBody(String surname, String givenName, String identificationNumber) {
            super(surname, givenName, identificationNumber);
        }
    }

    public CreateGuestIdentityInformationRequest(String surnameName, String givenName, String countryCode, Type identityType2, String identificationNumber, AirDate dateOfExpiry) {
        this.identityType = identityType2;
        this.requestBody = getRequestBody(identityType2, surnameName, givenName, identificationNumber, countryCode, dateOfExpiry);
    }

    public String getPath() {
        switch (this.identityType) {
            case ChineseNationalID:
                return "china_resident_identity_cards";
            case Passport:
                return "passports";
            default:
                throw new IllegalStateException("unknown identity type: " + this.identityType.name());
        }
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return this.requestBody;
    }

    private Object getRequestBody(Type identityType2, String surname, String givenName, String identificationNumber, String countryCode, AirDate dateOfExpiry) {
        switch (identityType2) {
            case ChineseNationalID:
                return new ChineseIdentityRequestBody(surname, givenName, identificationNumber);
            case Passport:
                return new PassportRequestBody(surname, givenName, countryCode, identificationNumber, dateOfExpiry);
            default:
                throw new IllegalStateException("unknown identity type: " + identityType2.name());
        }
    }

    public java.lang.reflect.Type successResponseType() {
        return SaveGuestIdentityInformationResponse.class;
    }
}
