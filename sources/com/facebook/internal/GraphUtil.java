package com.facebook.internal;

import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.facebook.FacebookException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphUtil {
    private static final String[] dateFormats = {"yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd"};

    public static JSONObject createOpenGraphActionForPost(String type) {
        JSONObject action = new JSONObject();
        if (type != null) {
            try {
                action.put("type", type);
            } catch (JSONException e) {
                throw new FacebookException("An error occurred while setting up the open graph action", (Throwable) e);
            }
        }
        return action;
    }

    public static JSONObject createOpenGraphObjectForPost(String type) {
        return createOpenGraphObjectForPost(type, null, null, null, null, null, null);
    }

    public static JSONObject createOpenGraphObjectForPost(String type, String title, String imageUrl, String url, String description, JSONObject objectProperties, String id) {
        JSONObject openGraphObject = new JSONObject();
        if (type != null) {
            try {
                openGraphObject.put("type", type);
            } catch (JSONException e) {
                throw new FacebookException("An error occurred while setting up the graph object", (Throwable) e);
            }
        }
        openGraphObject.put("title", title);
        if (imageUrl != null) {
            JSONObject imageUrlObject = new JSONObject();
            imageUrlObject.put("url", imageUrl);
            JSONArray imageUrls = new JSONArray();
            imageUrls.put(imageUrlObject);
            openGraphObject.put(ContentFrameworkAnalytics.IMAGE, imageUrls);
        }
        openGraphObject.put("url", url);
        openGraphObject.put("description", description);
        openGraphObject.put(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY, true);
        if (objectProperties != null) {
            openGraphObject.put("data", objectProperties);
        }
        if (id != null) {
            openGraphObject.put("id", id);
        }
        return openGraphObject;
    }

    public static boolean isOpenGraphObjectForPost(JSONObject object) {
        if (object != null) {
            return object.optBoolean(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY);
        }
        return false;
    }
}
