package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.UpdateNestedListingsResponse;
import java.lang.reflect.Type;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateNestedListingsRequest extends BaseRequestV2<UpdateNestedListingsResponse> {
    private final long listingId;
    private final Object requestBody;

    public static UpdateNestedListingsRequest forReplaceChildren(long listingId2, Set<Long> childListingIds) {
        return new UpdateNestedListingsRequest(listingId2, createReplaceChildrenRequestBody(childListingIds));
    }

    public static UpdateNestedListingsRequest forSetParent(long listingId2, Long parentListingId) {
        return new UpdateNestedListingsRequest(listingId2, createUpdateParentRequestBody(parentListingId));
    }

    private UpdateNestedListingsRequest(long listingId2, Object requestBody2) {
        this.listingId = listingId2;
        this.requestBody = requestBody2;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return UpdateNestedListingsResponse.class;
    }

    public String getPath() {
        return "nested_listings/" + this.listingId;
    }

    public Object getBody() {
        return this.requestBody;
    }

    private static String createReplaceChildrenRequestBody(Set<Long> childrenListingIds) {
        JSONObject replaceChildrenJson = new JSONObject();
        JSONArray childListingIdsArrayJson = new JSONArray();
        if (childrenListingIds != null) {
            try {
                for (Long childListingId : childrenListingIds) {
                    childListingIdsArrayJson.put(childListingId);
                }
            } catch (JSONException e) {
                throw new IllegalStateException("Error building replaceChildrenJson json");
            }
        }
        replaceChildrenJson.put("child_listing_ids", childListingIdsArrayJson);
        return replaceChildrenJson.toString();
    }

    private static String createUpdateParentRequestBody(Long parentListingId) {
        Object valueOf;
        JSONObject updateParentJson = new JSONObject();
        String str = "parent_listing_id";
        if (parentListingId == null) {
            try {
                valueOf = JSONObject.NULL;
            } catch (JSONException e) {
                throw new IllegalStateException("Error building updateParentJson");
            }
        } else {
            valueOf = String.valueOf(parentListingId);
        }
        updateParentJson.put(str, valueOf);
        return updateParentJson.toString();
    }
}
