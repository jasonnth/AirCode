package p315io.branch.referral.network;

import android.content.Context;
import com.airbnb.android.utils.AirbnbConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Branch;
import p315io.branch.referral.Defines.Jsonkey;
import p315io.branch.referral.PrefHelper;
import p315io.branch.referral.ServerResponse;

/* renamed from: io.branch.referral.network.BranchRemoteInterface */
public abstract class BranchRemoteInterface {

    /* renamed from: io.branch.referral.network.BranchRemoteInterface$BranchRemoteException */
    public static class BranchRemoteException extends Exception {
        /* access modifiers changed from: private */
        public int branchErrorCode = -113;

        public BranchRemoteException(int errorCode) {
            this.branchErrorCode = errorCode;
        }
    }

    /* renamed from: io.branch.referral.network.BranchRemoteInterface$BranchResponse */
    public static class BranchResponse {
        /* access modifiers changed from: private */
        public final int responseCode;
        /* access modifiers changed from: private */
        public final String responseData;

        public BranchResponse(String responseData2, int responseCode2) {
            this.responseData = responseData2;
            this.responseCode = responseCode2;
        }
    }

    public abstract BranchResponse doRestfulGet(String str) throws BranchRemoteException;

    public abstract BranchResponse doRestfulPost(String str, JSONObject jSONObject) throws BranchRemoteException;

    public final ServerResponse make_restful_get(String url, JSONObject params, String tag, String branchKey) {
        String modifiedUrl = url;
        if (params == null) {
            params = new JSONObject();
        }
        if (!addCommonParams(params, branchKey)) {
            return new ServerResponse(tag, -114);
        }
        String modifiedUrl2 = modifiedUrl + convertJSONtoString(params);
        long reqStartTime = System.currentTimeMillis();
        PrefHelper.Debug("BranchSDK", "getting " + modifiedUrl2);
        try {
            BranchResponse response = doRestfulGet(modifiedUrl2);
            ServerResponse processEntityForJSON = processEntityForJSON(response.responseData, response.responseCode, tag);
            if (Branch.getInstance() == null) {
                return processEntityForJSON;
            }
            Branch.getInstance().addExtraInstrumentationData(tag + "-" + Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - reqStartTime)));
            return processEntityForJSON;
        } catch (BranchRemoteException branchError) {
            if (branchError.branchErrorCode == -111) {
                ServerResponse serverResponse = new ServerResponse(tag, AirbnbConstants.SUPERHERO_TEST_ID);
                if (Branch.getInstance() == null) {
                    return serverResponse;
                }
                Branch.getInstance().addExtraInstrumentationData(tag + "-" + Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - reqStartTime)));
                return serverResponse;
            }
            ServerResponse serverResponse2 = new ServerResponse(tag, -113);
            if (Branch.getInstance() == null) {
                return serverResponse2;
            }
            Branch.getInstance().addExtraInstrumentationData(tag + "-" + Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - reqStartTime)));
            return serverResponse2;
        } catch (Throwable th) {
            if (Branch.getInstance() != null) {
                Branch.getInstance().addExtraInstrumentationData(tag + "-" + Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - reqStartTime)));
            }
            throw th;
        }
    }

    public final ServerResponse make_restful_post(JSONObject body, String url, String tag, String branchKey) {
        long reqStartTime = System.currentTimeMillis();
        if (body == null) {
            body = new JSONObject();
        }
        if (!addCommonParams(body, branchKey)) {
            return new ServerResponse(tag, -114);
        }
        PrefHelper.Debug("BranchSDK", "posting to " + url);
        PrefHelper.Debug("BranchSDK", "Post value = " + body.toString());
        try {
            BranchResponse response = doRestfulPost(url, body);
            ServerResponse processEntityForJSON = processEntityForJSON(response.responseData, response.responseCode, tag);
            if (Branch.getInstance() == null) {
                return processEntityForJSON;
            }
            Branch.getInstance().addExtraInstrumentationData(tag + "-" + Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - reqStartTime)));
            return processEntityForJSON;
        } catch (BranchRemoteException branchError) {
            if (branchError.branchErrorCode == -111) {
                ServerResponse serverResponse = new ServerResponse(tag, AirbnbConstants.SUPERHERO_TEST_ID);
                if (Branch.getInstance() == null) {
                    return serverResponse;
                }
                Branch.getInstance().addExtraInstrumentationData(tag + "-" + Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - reqStartTime)));
                return serverResponse;
            }
            ServerResponse serverResponse2 = new ServerResponse(tag, -113);
            if (Branch.getInstance() == null) {
                return serverResponse2;
            }
            Branch.getInstance().addExtraInstrumentationData(tag + "-" + Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - reqStartTime)));
            return serverResponse2;
        } catch (Throwable th) {
            if (Branch.getInstance() != null) {
                Branch.getInstance().addExtraInstrumentationData(tag + "-" + Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - reqStartTime)));
            }
            throw th;
        }
    }

    public static final BranchRemoteInterface getDefaultBranchRemoteInterface(Context context) {
        if (0 != 0) {
            return null;
        }
        return new BranchRemoteInterfaceUrlConnection(context);
    }

    private ServerResponse processEntityForJSON(String responseString, int statusCode, String tag) {
        ServerResponse result = new ServerResponse(tag, statusCode);
        PrefHelper.Debug("BranchSDK", "returned " + responseString);
        if (responseString != null) {
            try {
                result.setPost(new JSONObject(responseString));
            } catch (JSONException e) {
                try {
                    result.setPost(new JSONArray(responseString));
                } catch (JSONException ex2) {
                    PrefHelper.Debug(getClass().getSimpleName(), "JSON exception: " + ex2.getMessage());
                }
            }
        }
        return result;
    }

    private boolean addCommonParams(JSONObject post, String branch_key) {
        try {
            post.put("sdk", "android2.12.0");
            if (!branch_key.equals("bnc_no_value")) {
                post.put(Jsonkey.BranchKey.getKey(), branch_key);
                return true;
            }
        } catch (JSONException e) {
        }
        return false;
    }

    private String convertJSONtoString(JSONObject json) {
        StringBuilder result = new StringBuilder();
        if (json != null) {
            JSONArray names = json.names();
            if (names != null) {
                boolean first = true;
                int size = names.length();
                int i = 0;
                while (i < size) {
                    try {
                        String key = names.getString(i);
                        if (first) {
                            result.append("?");
                            first = false;
                        } else {
                            result.append("&");
                        }
                        result.append(key).append("=").append(json.getString(key));
                        i++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return result.toString();
    }
}
