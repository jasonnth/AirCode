package com.facebook.accountkit.internal;

import android.os.Bundle;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountPreferences;
import com.facebook.accountkit.AccountPreferences.OnDeletePreferenceListener;
import com.facebook.accountkit.AccountPreferences.OnLoadPreferenceListener;
import com.facebook.accountkit.AccountPreferences.OnLoadPreferencesListener;
import com.facebook.accountkit.AccountPreferences.OnSetPreferenceListener;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class AccountPreferencesImpl implements AccountPreferences {
    private static final String PREFERENCES_API_PATH = "me/preferences";
    private static final String REQUEST_PARAMETER_NAME = "name";
    private static final String REQUEST_PARAMETER_VALUE = "value";
    private static final String RESPONSE_PARAMETER_DATA = "data";
    private static final String RESPONSE_PARAMETER_NAME = "name";
    private static final String RESPONSE_PARAMETER_SUCCESS = "success";
    private static final String RESPONSE_PARAMETER_VALUE = "value";
    private final AccessToken accessToken;

    AccountPreferencesImpl(AccessToken accessToken2) {
        this.accessToken = accessToken2;
    }

    public void deletePreference(final String key, final OnDeletePreferenceListener listener) {
        Callback callback;
        Bundle parameters = new Bundle();
        Utility.putNonNullString(parameters, "name", key);
        AccountKitGraphRequest request = new AccountKitGraphRequest(this.accessToken, PREFERENCES_API_PATH, parameters, false, HttpMethod.DELETE);
        if (listener == null) {
            callback = null;
        } else {
            callback = new Callback() {
                public void onCompleted(AccountKitGraphResponse response) {
                    AccountKitRequestError serverError = response.getError();
                    if (serverError != null) {
                        listener.onDeletePreference(key, (AccountKitError) Utility.createErrorFromServerError(serverError).first);
                        return;
                    }
                    try {
                        if (!response.getResponseObject().getBoolean("success")) {
                            listener.onDeletePreference(key, new AccountKitError(Type.INTERNAL_ERROR, InternalAccountKitError.OPERATION_NOT_SUCCESSFUL));
                            return;
                        }
                        listener.onDeletePreference(key, null);
                    } catch (JSONException e) {
                        listener.onDeletePreference(key, new AccountKitError(Type.SERVER_ERROR, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                    }
                }
            };
        }
        AccountKitGraphRequest.executeAsync(request, callback);
    }

    public void loadPreference(final String key, final OnLoadPreferenceListener listener) {
        Callback callback;
        Bundle parameters = new Bundle();
        Utility.putNonNullString(parameters, "name", key);
        AccountKitGraphRequest request = new AccountKitGraphRequest(this.accessToken, PREFERENCES_API_PATH, parameters, false, HttpMethod.GET);
        if (listener == null) {
            callback = null;
        } else {
            callback = new Callback() {
                public void onCompleted(AccountKitGraphResponse response) {
                    AccountKitRequestError serverError = response.getError();
                    if (serverError != null) {
                        listener.onLoadPreference(key, null, (AccountKitError) Utility.createErrorFromServerError(serverError).first);
                        return;
                    }
                    String value = null;
                    try {
                        JSONArray data = response.getResponseObject().getJSONArray("data");
                        int length = data.length();
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            JSONObject item = data.getJSONObject(i);
                            if (item != null) {
                                String itemKey = item.getString("name");
                                if (itemKey != null && itemKey.equals(key)) {
                                    value = item.getString("value");
                                    break;
                                }
                            }
                            i++;
                        }
                        listener.onLoadPreference(key, value, null);
                    } catch (JSONException e) {
                        listener.onLoadPreference(key, null, new AccountKitError(Type.SERVER_ERROR, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                    }
                }
            };
        }
        AccountKitGraphRequest.executeAsync(request, callback);
    }

    public void loadPreferences(final OnLoadPreferencesListener listener) {
        Callback callback;
        AccountKitGraphRequest request = new AccountKitGraphRequest(this.accessToken, PREFERENCES_API_PATH, new Bundle(), false, HttpMethod.GET);
        if (listener == null) {
            callback = null;
        } else {
            callback = new Callback() {
                public void onCompleted(AccountKitGraphResponse response) {
                    AccountKitRequestError serverError = response.getError();
                    if (serverError != null) {
                        listener.onLoadPreferences(null, (AccountKitError) Utility.createErrorFromServerError(serverError).first);
                        return;
                    }
                    JSONObject responseObject = response.getResponseObject();
                    Map<String, String> preferences = new HashMap<>();
                    try {
                        JSONArray data = responseObject.getJSONArray("data");
                        int length = data.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject item = data.getJSONObject(i);
                            if (item != null) {
                                String key = item.getString("name");
                                if (key != null) {
                                    String value = item.getString("value");
                                    if (value != null) {
                                        preferences.put(key, value);
                                    }
                                }
                            }
                        }
                        listener.onLoadPreferences(preferences, null);
                    } catch (JSONException e) {
                        listener.onLoadPreferences(null, new AccountKitError(Type.SERVER_ERROR, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                    }
                }
            };
        }
        AccountKitGraphRequest.executeAsync(request, callback);
    }

    public void setPreference(final String key, final String value, final OnSetPreferenceListener listener) {
        Callback callback;
        Bundle parameters = new Bundle();
        Utility.putNonNullString(parameters, "name", key);
        Utility.putNonNullString(parameters, "value", value);
        AccountKitGraphRequest request = new AccountKitGraphRequest(this.accessToken, PREFERENCES_API_PATH, parameters, false, HttpMethod.POST);
        if (listener == null) {
            callback = null;
        } else {
            callback = new Callback() {
                public void onCompleted(AccountKitGraphResponse response) {
                    AccountKitRequestError serverError = response.getError();
                    if (serverError != null) {
                        listener.onSetPreference(key, value, (AccountKitError) Utility.createErrorFromServerError(serverError).first);
                        return;
                    }
                    try {
                        if (!response.getResponseObject().getBoolean("success")) {
                            listener.onSetPreference(key, value, new AccountKitError(Type.INTERNAL_ERROR, InternalAccountKitError.OPERATION_NOT_SUCCESSFUL));
                            return;
                        }
                        listener.onSetPreference(key, value, null);
                    } catch (JSONException e) {
                        listener.onSetPreference(key, value, new AccountKitError(Type.SERVER_ERROR, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                    }
                }
            };
        }
        AccountKitGraphRequest.executeAsync(request, callback);
    }
}
