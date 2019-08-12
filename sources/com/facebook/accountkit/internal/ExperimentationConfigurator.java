package com.facebook.accountkit.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ExperimentationConfigurator {
    private static final String FEATURE_PARAMETER_KEY = "key";
    private static final String FEATURE_PARAMETER_VALUE = "value";
    private static final String GRAPH_PATH_GET_CONFIGURATION = "experimentation_configuration";
    private static final String PARAMETER_UNIT_IDENTIFIER = "unit_id";
    private static final String RESPONSE_PARAMETER_CREATE_TIME = "create_time";
    private static final String RESPONSE_PARAMETER_DATA = "data";
    private static final String RESPONSE_PARAMETER_FEATURE_SET = "feature_set";
    private static final String RESPONSE_PARAMETER_TTL = "ttl";
    private static final String RESPONSE_PARAMETER_UNIT_ID = "unit_id";
    /* access modifiers changed from: private */
    public Context applicationContext;

    ExperimentationConfigurator() {
    }

    /* access modifiers changed from: 0000 */
    public void initialize(Context context) {
        Validate.checkInternetPermissionAndThrow(context);
        this.applicationContext = context.getApplicationContext();
        Utility.getThreadPoolExecutor().execute(new Runnable() {
            public void run() {
                ExperimentationConfiguration ec = ExperimentationConfigurator.this.getExperimentationConfiguration();
                if (!ec.exists() || ec.isStale()) {
                    ExperimentationConfigurator.this.downloadExperimentationConfiguration(ec.getUnitID());
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public ExperimentationConfiguration getExperimentationConfiguration() {
        return new ExperimentationConfiguration(this.applicationContext);
    }

    /* access modifiers changed from: private */
    public void downloadExperimentationConfiguration(final String unitID) {
        Utility.getThreadPoolExecutor().execute(new Runnable() {
            public void run() {
                AccountKitGraphRequest graphRequest = ExperimentationConfigurator.this.buildGraphRequest(ExperimentationConfigurator.GRAPH_PATH_GET_CONFIGURATION, unitID);
                AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
                AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(graphRequest, new Callback() {
                    public void onCompleted(AccountKitGraphResponse response) {
                        if (response.getError() == null) {
                            try {
                                JSONObject responseObject = response.getResponseObject().getJSONArray("data").getJSONObject(0);
                                Long createTime = null;
                                Long ttl = null;
                                String unitID = null;
                                if (responseObject.has(ExperimentationConfigurator.RESPONSE_PARAMETER_CREATE_TIME)) {
                                    createTime = Long.valueOf(responseObject.getLong(ExperimentationConfigurator.RESPONSE_PARAMETER_CREATE_TIME));
                                }
                                if (responseObject.has("unit_id")) {
                                    unitID = responseObject.getString("unit_id");
                                }
                                if (responseObject.has(ExperimentationConfigurator.RESPONSE_PARAMETER_TTL)) {
                                    ttl = Long.valueOf(responseObject.getLong(ExperimentationConfigurator.RESPONSE_PARAMETER_TTL));
                                }
                                JSONArray payload = responseObject.getJSONArray(ExperimentationConfigurator.RESPONSE_PARAMETER_FEATURE_SET);
                                Map<Integer, Integer> featureSet = new HashMap<>(payload.length());
                                for (int i = 0; i < payload.length(); i++) {
                                    JSONObject elem = payload.getJSONObject(i);
                                    featureSet.put(Integer.valueOf(elem.getInt(ExperimentationConfigurator.FEATURE_PARAMETER_KEY)), Integer.valueOf(elem.getInt("value")));
                                }
                                ExperimentationConfiguration.load(ExperimentationConfigurator.this.applicationContext, unitID, createTime, ttl, featureSet);
                            } catch (JSONException e) {
                            }
                        }
                    }
                }));
            }
        });
    }

    /* access modifiers changed from: private */
    public AccountKitGraphRequest buildGraphRequest(String graphPath, String unitID) {
        Bundle parameters = new Bundle();
        Utility.putNonNullString(parameters, "unit_id", unitID);
        return new AccountKitGraphRequest(null, graphPath, parameters, false, HttpMethod.GET);
    }
}
