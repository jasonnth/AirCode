package com.airbnb.android.core.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthorizedAccount implements Serializable {
    private static final String KEY_AUTH_TOKEN = "authtoken";
    private static final String KEY_ID = "id";
    private static final String KEY_PICTURE_URL = "pictureurl";
    private static final String KEY_USERNAME = "username";
    private static final long serialVersionUID = -1402022756672810095L;
    private String mAuthToken;
    private final long mId;
    private String mPictureUrl;
    private String mUsername;

    public AuthorizedAccount(JSONObject json) {
        try {
            this.mId = json.getLong("id");
            this.mUsername = json.getString(KEY_USERNAME);
            this.mAuthToken = json.getString("authtoken");
            this.mPictureUrl = json.getString("pictureurl");
            if (this.mId <= 0) {
                throw new IllegalArgumentException("creating authorized user with id 0");
            }
        } catch (JSONException e) {
            throw new RuntimeException("invalid json");
        }
    }

    public AuthorizedAccount(long id, String username, String authToken, String pictureUrl) {
        this.mId = id;
        this.mUsername = username;
        this.mAuthToken = authToken;
        this.mPictureUrl = pictureUrl;
        if (this.mId <= 0) {
            throw new IllegalArgumentException("creating authorized user with id 0");
        } else if (username == null || authToken == null || pictureUrl == null) {
            throw new IllegalArgumentException("cannot create authorized account with null name/token/pic");
        }
    }

    public long getId() {
        return this.mId;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public String getAuthToken() {
        return this.mAuthToken;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    public JSONObject toJson() {
        JSONObject jso = new JSONObject();
        try {
            jso.put("id", this.mId);
            jso.put(KEY_USERNAME, this.mUsername);
            jso.put("authtoken", this.mAuthToken);
            jso.put("pictureurl", this.mPictureUrl);
            return jso;
        } catch (JSONException e) {
            throw new RuntimeException("error json-ifying account " + this.mUsername);
        }
    }

    public static ArrayList<AuthorizedAccount> getAccounts(String json) {
        ArrayList<AuthorizedAccount> accounts = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                accounts.add(new AuthorizedAccount(array.getJSONObject(i)));
            }
            Collections.sort(accounts, AuthorizedAccount$$Lambda$1.lambdaFactory$());
        } catch (JSONException e) {
        }
        return accounts;
    }

    public boolean updateIfNeeded(String authToken, String pictureUrl, String userName) {
        boolean isUpdated = false;
        if (!this.mAuthToken.equals(authToken)) {
            this.mAuthToken = authToken;
            isUpdated = true;
        }
        if (!this.mPictureUrl.equals(pictureUrl)) {
            this.mPictureUrl = pictureUrl;
            isUpdated = true;
        }
        if (this.mUsername.equals(userName)) {
            return isUpdated;
        }
        this.mUsername = userName;
        return true;
    }

    public int hashCode() {
        return ((int) (this.mId ^ (this.mId >>> 32))) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.mId != ((AuthorizedAccount) obj).mId) {
            return false;
        }
        return true;
    }
}
