package p315io.branch.referral;

import android.content.Context;
import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.indexing.BranchUniversalObject;
import p315io.branch.indexing.BranchUniversalObject.RegisterViewStatusListener;
import p315io.branch.referral.Defines.Jsonkey;
import p315io.branch.referral.Defines.RequestPath;

/* renamed from: io.branch.referral.ServerRequestRegisterView */
class ServerRequestRegisterView extends ServerRequest {
    RegisterViewStatusListener callback_;

    public ServerRequestRegisterView(Context context, BranchUniversalObject branchUniversalObject, SystemObserver sysObserver, RegisterViewStatusListener callback) {
        super(context, RequestPath.RegisterView.getPath());
        this.callback_ = callback;
        try {
            setPost(createContentViewJson(branchUniversalObject, sysObserver));
        } catch (JSONException ex) {
            ex.printStackTrace();
            this.constructError_ = true;
        }
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        if (this.callback_ != null) {
            this.callback_.onRegisterViewFinished(true, null);
        }
    }

    public void handleFailure(int statusCode, String causeMsg) {
        if (this.callback_ != null) {
            this.callback_.onRegisterViewFinished(false, new BranchError("Unable to register content view. " + causeMsg, statusCode));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        if (this.callback_ != null) {
            this.callback_.onRegisterViewFinished(false, new BranchError("Unable to register content view", -102));
        }
        return true;
    }

    public boolean isGetRequest() {
        return false;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }

    private JSONObject createContentViewJson(BranchUniversalObject universalObject, SystemObserver sysObserver) throws JSONException {
        String uniqueId;
        JSONObject contentObject = new JSONObject();
        String str = "Android " + VERSION.SDK_INT;
        contentObject.put(Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
        contentObject.put(Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
        if (DeviceInfo.getInstance() != null) {
            uniqueId = DeviceInfo.getInstance().getHardwareID();
        } else {
            uniqueId = sysObserver.getUniqueID(this.prefHelper_.getExternDebug());
        }
        if (!uniqueId.equals("bnc_no_value") && sysObserver.hasRealHardwareId()) {
            contentObject.put(Jsonkey.HardwareID.getKey(), uniqueId);
        }
        String appVersion = sysObserver.getAppVersion();
        if (!appVersion.equals("bnc_no_value")) {
            contentObject.put(Jsonkey.AppVersion.getKey(), appVersion);
        }
        JSONObject paramsObj = new JSONObject();
        paramsObj.put(Jsonkey.ContentKeyWords.getKey(), universalObject.getKeywordsJsonArray());
        paramsObj.put(Jsonkey.PublicallyIndexable.getKey(), universalObject.isPublicallyIndexable());
        if (universalObject.getPrice() > 0.0d) {
            paramsObj.put("$amount", universalObject.getPrice());
            paramsObj.put("$currency", universalObject.getCurrencyType());
        }
        String canonicalId = universalObject.getCanonicalIdentifier();
        if (canonicalId != null && canonicalId.trim().length() > 0) {
            paramsObj.put(Jsonkey.CanonicalIdentifier.getKey(), canonicalId);
        }
        String canonicalUrl = universalObject.getCanonicalUrl();
        if (canonicalUrl != null && canonicalUrl.trim().length() > 0) {
            paramsObj.put(Jsonkey.CanonicalUrl.getKey(), canonicalUrl);
        }
        String title = universalObject.getTitle();
        if (title != null && title.trim().length() > 0) {
            paramsObj.put(Jsonkey.ContentTitle.getKey(), universalObject.getTitle());
        }
        String desc = universalObject.getDescription();
        if (desc != null && desc.trim().length() > 0) {
            paramsObj.put(Jsonkey.ContentDesc.getKey(), desc);
        }
        String imageUrl = universalObject.getImageUrl();
        if (imageUrl != null && imageUrl.trim().length() > 0) {
            paramsObj.put(Jsonkey.ContentImgUrl.getKey(), imageUrl);
        }
        String contentType = universalObject.getType();
        if (contentType != null && contentType.trim().length() > 0) {
            paramsObj.put(Jsonkey.ContentType.getKey(), contentType);
        }
        if (universalObject.getExpirationTime() > 0) {
            paramsObj.put(Jsonkey.ContentExpiryTime.getKey(), universalObject.getExpirationTime());
        }
        contentObject.put(Jsonkey.Params.getKey(), paramsObj);
        HashMap metaData = universalObject.getMetadata();
        Set extraKeys = metaData.keySet();
        JSONObject metaDataObject = new JSONObject();
        for (Object key : extraKeys) {
            metaDataObject.put((String) key, metaData.get(key));
        }
        contentObject.put(Jsonkey.Metadata.getKey(), metaDataObject);
        return contentObject;
    }

    public boolean isGAdsParamsRequired() {
        return true;
    }
}
