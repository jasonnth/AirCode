package p315io.branch.indexing;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: io.branch.indexing.ContentDiscoveryManifest */
public class ContentDiscoveryManifest {
    private static ContentDiscoveryManifest thisInstance_;
    private final String PREF_KEY = "BNC_CD_MANIFEST";
    private JSONObject cdManifestObject_;
    private JSONArray contentPaths_;
    private boolean isCDEnabled_ = false;
    private String manifestVersion_;
    private int maxPacketSize_ = 0;
    private int maxTextLen_ = 0;
    private int maxViewHistoryLength_ = 1;
    private SharedPreferences sharedPref;

    /* renamed from: io.branch.indexing.ContentDiscoveryManifest$CDPathProperties */
    class CDPathProperties {
        private int discoveryRepeatInterval_;
        private boolean isClearText_;
        private int maxDiscoveryRepeat_ = 15;
        final JSONObject pathInfo_;

        /* access modifiers changed from: 0000 */
        public int getDiscoveryRepeatInterval() {
            return this.discoveryRepeatInterval_;
        }

        /* access modifiers changed from: 0000 */
        public int getMaxDiscoveryRepeatNumber() {
            return this.maxDiscoveryRepeat_;
        }

        CDPathProperties(JSONObject pathInfo) {
            this.pathInfo_ = pathInfo;
            if (pathInfo.has("h")) {
                try {
                    this.isClearText_ = !pathInfo.getBoolean("h");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (pathInfo.has("dri")) {
                    this.discoveryRepeatInterval_ = pathInfo.getInt("dri");
                }
                if (pathInfo.has("mdr")) {
                    this.maxDiscoveryRepeat_ = pathInfo.getInt("mdr");
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }

        /* access modifiers changed from: 0000 */
        public JSONArray getFilteredElements() {
            JSONArray elementArray = null;
            if (!this.pathInfo_.has("ck")) {
                return elementArray;
            }
            try {
                return this.pathInfo_.getJSONArray("ck");
            } catch (JSONException e) {
                e.printStackTrace();
                return elementArray;
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean isClearTextRequested() {
            return this.isClearText_;
        }

        /* access modifiers changed from: 0000 */
        public boolean isSkipContentDiscovery() {
            JSONArray filteredElements = getFilteredElements();
            return filteredElements != null && filteredElements.length() == 0;
        }
    }

    private ContentDiscoveryManifest(Context context) {
        this.sharedPref = context.getSharedPreferences("bnc_content_discovery_manifest_storage", 0);
        retrieve(context);
    }

    public static ContentDiscoveryManifest getInstance(Context context) {
        if (thisInstance_ == null) {
            thisInstance_ = new ContentDiscoveryManifest(context);
        }
        return thisInstance_;
    }

    private void persist() {
        this.sharedPref.edit().putString("BNC_CD_MANIFEST", this.cdManifestObject_.toString()).apply();
    }

    private void retrieve(Context context) {
        String jsonStr = this.sharedPref.getString("BNC_CD_MANIFEST", null);
        if (jsonStr != null) {
            try {
                this.cdManifestObject_ = new JSONObject(jsonStr);
                if (this.cdManifestObject_.has("mv")) {
                    this.manifestVersion_ = this.cdManifestObject_.getString("mv");
                }
                if (this.cdManifestObject_.has("m")) {
                    this.contentPaths_ = this.cdManifestObject_.getJSONArray("m");
                }
            } catch (JSONException e) {
                this.cdManifestObject_ = new JSONObject();
            }
        } else {
            this.cdManifestObject_ = new JSONObject();
        }
    }

    public void onBranchInitialised(JSONObject branchInitResp) {
        if (branchInitResp.has("cd")) {
            this.isCDEnabled_ = true;
            try {
                JSONObject cdObj = branchInitResp.getJSONObject("cd");
                if (cdObj.has("mv")) {
                    this.manifestVersion_ = cdObj.getString("mv");
                }
                if (cdObj.has("mhl")) {
                    this.maxViewHistoryLength_ = cdObj.getInt("mhl");
                }
                if (cdObj.has("m")) {
                    this.contentPaths_ = cdObj.getJSONArray("m");
                }
                if (cdObj.has("mtl")) {
                    int maxTextLength = cdObj.getInt("mtl");
                    if (maxTextLength > 0) {
                        this.maxTextLen_ = maxTextLength;
                    }
                }
                if (cdObj.has("mps")) {
                    this.maxPacketSize_ = cdObj.getInt("mps");
                }
                this.cdManifestObject_.put("mv", this.manifestVersion_);
                this.cdManifestObject_.put("m", this.contentPaths_);
                persist();
            } catch (JSONException e) {
            }
        } else {
            this.isCDEnabled_ = false;
        }
    }

    /* access modifiers changed from: 0000 */
    public CDPathProperties getCDPathProperties(Activity activity) {
        if (this.contentPaths_ == null) {
            return null;
        }
        String viewPath = "/" + activity.getClass().getSimpleName();
        int i = 0;
        while (i < this.contentPaths_.length()) {
            try {
                JSONObject pathObj = this.contentPaths_.getJSONObject(i);
                if (pathObj.has("p") && pathObj.getString("p").equals(viewPath)) {
                    return new CDPathProperties(pathObj);
                }
                i++;
            } catch (JSONException e) {
                return null;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean isCDEnabled() {
        return this.isCDEnabled_;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxTextLen() {
        return this.maxTextLen_;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxPacketSize() {
        return this.maxPacketSize_;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxViewHistorySize() {
        return this.maxViewHistoryLength_;
    }

    public String getManifestVersion() {
        if (TextUtils.isEmpty(this.manifestVersion_)) {
            return "-1";
        }
        return this.manifestVersion_;
    }
}
