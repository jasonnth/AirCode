package p315io.branch.referral;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Defines.Jsonkey;
import p315io.branch.referral.Defines.RequestPath;

/* renamed from: io.branch.referral.ServerRequest */
public abstract class ServerRequest {
    public boolean constructError_ = false;
    private boolean disableAndroidIDFetch_;
    final Set<PROCESS_WAIT_LOCK> locks_;
    private JSONObject params_;
    protected PrefHelper prefHelper_;
    long queueWaitTime_ = 0;
    protected String requestPath_;
    boolean skipOnTimeOut = false;
    private final SystemObserver systemObserver_;
    private int waitLockCnt = 0;

    /* renamed from: io.branch.referral.ServerRequest$PROCESS_WAIT_LOCK */
    enum PROCESS_WAIT_LOCK {
        FB_APP_LINK_WAIT_LOCK,
        GAID_FETCH_WAIT_LOCK,
        INTENT_PENDING_WAIT_LOCK,
        STRONG_MATCH_PENDING_WAIT_LOCK,
        INSTALL_REFERRER_FETCH_WAIT_LOCK
    }

    public abstract void clearCallbacks();

    public abstract boolean handleErrors(Context context);

    public abstract void handleFailure(int i, String str);

    public abstract boolean isGetRequest();

    public abstract void onRequestSucceeded(ServerResponse serverResponse, Branch branch);

    public ServerRequest(Context context, String requestPath) {
        this.requestPath_ = requestPath;
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.systemObserver_ = new SystemObserver(context);
        this.params_ = new JSONObject();
        this.disableAndroidIDFetch_ = Branch.isDeviceIDFetchDisabled();
        this.locks_ = new HashSet();
    }

    protected ServerRequest(String requestPath, JSONObject post, Context context) {
        this.requestPath_ = requestPath;
        this.params_ = post;
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.systemObserver_ = new SystemObserver(context);
        this.disableAndroidIDFetch_ = Branch.isDeviceIDFetchDisabled();
        this.locks_ = new HashSet();
    }

    public boolean shouldRetryOnFail() {
        return false;
    }

    public final String getRequestPath() {
        return this.requestPath_;
    }

    public String getRequestUrl() {
        return this.prefHelper_.getAPIBaseUrl() + this.requestPath_;
    }

    /* access modifiers changed from: protected */
    public void setPost(JSONObject post) {
        try {
            JSONObject metadata = new JSONObject();
            Iterator<String> i = this.prefHelper_.getRequestMetadata().keys();
            while (i.hasNext()) {
                String k = (String) i.next();
                metadata.put(k, this.prefHelper_.getRequestMetadata().get(k));
            }
            if (post.has(Jsonkey.Metadata.getKey())) {
                Iterator<String> postIter = post.getJSONObject(Jsonkey.Metadata.getKey()).keys();
                while (postIter.hasNext()) {
                    String key = (String) postIter.next();
                    metadata.put(key, post.getJSONObject(Jsonkey.Metadata.getKey()).get(key));
                }
            }
            post.put(Jsonkey.Metadata.getKey(), metadata);
        } catch (JSONException e) {
            Log.e("BranchSDK", "Could not merge metadata, ignoring user metadata.");
        }
        this.params_ = post;
        DeviceInfo.getInstance(this.prefHelper_.getExternDebug(), this.systemObserver_, this.disableAndroidIDFetch_).updateRequestWithDeviceParams(this.params_);
    }

    public JSONObject getPost() {
        return this.params_;
    }

    public boolean isGAdsParamsRequired() {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return r9.params_;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0064 A[ExcHandler: ConcurrentModificationException (e java.util.ConcurrentModificationException), Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject getPostWithInstrumentationValues(java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r10) {
        /*
            r9 = this;
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            org.json.JSONObject r7 = r9.params_     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            if (r7 == 0) goto L_0x002e
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            org.json.JSONObject r7 = r9.params_     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            java.lang.String r7 = r7.toString()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            r6.<init>(r7)     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            java.util.Iterator r4 = r6.keys()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
        L_0x0018:
            boolean r7 = r4.hasNext()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            if (r7 == 0) goto L_0x002e
            java.lang.Object r3 = r4.next()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            java.lang.Object r7 = r6.get(r3)     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            r1.put(r3, r7)     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            goto L_0x0018
        L_0x002c:
            r7 = move-exception
        L_0x002d:
            return r1
        L_0x002e:
            int r7 = r10.size()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            if (r7 <= 0) goto L_0x002d
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            r2.<init>()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            java.util.Set r5 = r10.keySet()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x0064 }
            java.util.Iterator r7 = r5.iterator()     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
        L_0x0041:
            boolean r8 = r7.hasNext()     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
            if (r8 == 0) goto L_0x005a
            java.lang.Object r3 = r7.next()     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
            java.lang.Object r8 = r10.get(r3)     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
            r2.put(r3, r8)     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
            r10.remove(r3)     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
            goto L_0x0041
        L_0x0058:
            r7 = move-exception
            goto L_0x002d
        L_0x005a:
            io.branch.referral.Defines$Jsonkey r7 = p315io.branch.referral.Defines.Jsonkey.Branch_Instrumentation     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
            java.lang.String r7 = r7.getKey()     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
            r1.put(r7, r2)     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x0064 }
            goto L_0x002d
        L_0x0064:
            r0 = move-exception
            org.json.JSONObject r1 = r9.params_
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: p315io.branch.referral.ServerRequest.getPostWithInstrumentationValues(java.util.concurrent.ConcurrentHashMap):org.json.JSONObject");
    }

    public JSONObject getGetParams() {
        return this.params_;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("REQ_POST", this.params_);
            json.put("REQ_POST_PATH", this.requestPath_);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }

    public static ServerRequest fromJSON(JSONObject json, Context context) {
        JSONObject post = null;
        String requestPath = "";
        try {
            if (json.has("REQ_POST")) {
                post = json.getJSONObject("REQ_POST");
            }
        } catch (JSONException e) {
        }
        try {
            if (json.has("REQ_POST_PATH")) {
                requestPath = json.getString("REQ_POST_PATH");
            }
        } catch (JSONException e2) {
        }
        if (requestPath == null || requestPath.length() <= 0) {
            return null;
        }
        return getExtendedServerRequest(requestPath, post, context);
    }

    private static ServerRequest getExtendedServerRequest(String requestPath, JSONObject post, Context context) {
        if (requestPath.equalsIgnoreCase(RequestPath.CompletedAction.getPath())) {
            return new ServerRequestActionCompleted(requestPath, post, context);
        }
        if (requestPath.equalsIgnoreCase(RequestPath.GetURL.getPath())) {
            return new ServerRequestCreateUrl(requestPath, post, context);
        }
        if (requestPath.equalsIgnoreCase(RequestPath.GetCreditHistory.getPath())) {
            return new ServerRequestGetRewardHistory(requestPath, post, context);
        }
        if (requestPath.equalsIgnoreCase(RequestPath.GetCredits.getPath())) {
            return new ServerRequestGetRewards(requestPath, post, context);
        }
        if (requestPath.equalsIgnoreCase(RequestPath.IdentifyUser.getPath())) {
            return new ServerRequestIdentifyUserRequest(requestPath, post, context);
        }
        if (requestPath.equalsIgnoreCase(RequestPath.Logout.getPath())) {
            return new ServerRequestLogout(requestPath, post, context);
        }
        if (requestPath.equalsIgnoreCase(RequestPath.RedeemRewards.getPath())) {
            return new ServerRequestRedeemRewards(requestPath, post, context);
        }
        if (requestPath.equalsIgnoreCase(RequestPath.RegisterClose.getPath())) {
            return new ServerRequestRegisterClose(requestPath, post, context);
        }
        if (requestPath.equalsIgnoreCase(RequestPath.RegisterInstall.getPath())) {
            return new ServerRequestRegisterInstall(requestPath, post, context);
        }
        if (requestPath.equalsIgnoreCase(RequestPath.RegisterOpen.getPath())) {
            return new ServerRequestRegisterOpen(requestPath, post, context);
        }
        return null;
    }

    public void updateGAdsParams(SystemObserver sysObserver) {
        if (!TextUtils.isEmpty(sysObserver.GAIDString_)) {
            try {
                this.params_.put(Jsonkey.GoogleAdvertisingID.getKey(), sysObserver.GAIDString_);
                this.params_.put(Jsonkey.LATVal.getKey(), sysObserver.LATVal_);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean doesAppHasInternetPermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0;
    }

    public void onRequestQueued() {
        this.queueWaitTime_ = System.currentTimeMillis();
    }

    public long getQueueWaitTime() {
        if (this.queueWaitTime_ > 0) {
            return System.currentTimeMillis() - this.queueWaitTime_;
        }
        return 0;
    }

    public void addProcessWaitLock(PROCESS_WAIT_LOCK lock) {
        if (lock != null) {
            this.locks_.add(lock);
        }
    }

    public void removeProcessWaitLock(PROCESS_WAIT_LOCK lock) {
        this.locks_.remove(lock);
    }

    public boolean isWaitingOnProcessToFinish() {
        return this.locks_.size() > 0;
    }

    public void onPreExecute() {
    }

    /* access modifiers changed from: protected */
    public void updateEnvironment(Context context, JSONObject post) {
        try {
            post.put(Jsonkey.Environment.getKey(), isPackageInstalled(context) ? Jsonkey.NativeApp.getKey() : Jsonkey.InstantApp.getKey());
        } catch (Exception e) {
        }
    }

    private static boolean isPackageInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        if (intent == null) {
            return false;
        }
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 65536);
        if (list == null || list.size() <= 0) {
            return false;
        }
        return true;
    }
}
