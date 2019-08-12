package com.airbnb.android.core.models;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.generated.GenListingExpectation;
import com.airbnb.android.utils.JacksonUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListingExpectation extends GenListingExpectation {
    public static final Creator<ListingExpectation> CREATOR = new Creator<ListingExpectation>() {
        public ListingExpectation[] newArray(int size) {
            return new ListingExpectation[size];
        }

        public ListingExpectation createFromParcel(Parcel source) {
            ListingExpectation object = new ListingExpectation();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String EXPECTATIONS_KEY = "expectations";
    private static final String PAYLOAD_KEY = "payload";

    public static List<ListingExpectation> getExpectationListFromRNResponse(Intent data) {
        if (data == null || data.getExtras() == null || !data.getExtras().containsKey(PAYLOAD_KEY)) {
            return new ArrayList();
        }
        Map payload = (Map) data.getExtras().get(PAYLOAD_KEY);
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ObjectReader reader = JacksonUtils.readerForType(objectMapper, ListingExpectation.class);
        try {
            if (!payload.containsKey(EXPECTATIONS_KEY)) {
                throw new IOException();
            }
            JsonNode arrNode = (JsonNode) objectMapper.convertValue(payload.get(EXPECTATIONS_KEY), JsonNode.class);
            if (!arrNode.isArray()) {
                throw new IOException();
            }
            List<ListingExpectation> expectations = new ArrayList<>();
            Iterator it = arrNode.iterator();
            while (it.hasNext()) {
                expectations.add(reader.readValue((JsonNode) it.next()));
            }
            return expectations;
        } catch (IOException e) {
            notifyInvalidReactPayload(payload);
            return new ArrayList();
        } catch (NullPointerException e2) {
            notifyInvalidReactPayload(payload);
            return new ArrayList();
        }
    }

    private static void notifyInvalidReactPayload(Map payload) {
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("unable to generate native Android object from listing expectations React Native Json with payload " + payload));
    }
}
