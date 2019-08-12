package com.appboy.models;

import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0375bd;
import p004bo.app.C0386bo;
import p004bo.app.C0397bz;
import p004bo.app.C0495ex;
import p004bo.app.C0497ez;

public abstract class InAppMessageHtmlBase extends InAppMessageBase implements IInAppMessageHtml {

    /* renamed from: i */
    private String f2818i;

    /* renamed from: j */
    private String f2819j;

    /* renamed from: k */
    private boolean f2820k = false;

    /* renamed from: l */
    private String f2821l = null;

    protected InAppMessageHtmlBase() {
    }

    public InAppMessageHtmlBase(JSONObject object, C0375bd appboyManager) {
        super(object, appboyManager);
        if (!StringUtils.isNullOrBlank(object.optString("zipped_assets_url"))) {
            this.f2818i = object.optString("zipped_assets_url");
        }
    }

    public String getLocalAssetsDirectoryUrl() {
        return this.f2819j;
    }

    public String getAssetsZipRemoteUrl() {
        return this.f2818i;
    }

    public String getRemoteAssetPathForPrefetch() {
        return getAssetsZipRemoteUrl();
    }

    public void setLocalAssetsDirectoryUrl(String assetsLocalDirectoryUrl) {
        this.f2819j = assetsLocalDirectoryUrl;
    }

    public void setLocalAssetPathForPrefetch(String localUri) {
        setLocalAssetsDirectoryUrl(localUri);
    }

    public boolean logButtonClick(String buttonId) {
        if (StringUtils.isNullOrEmpty(this.f2793b) && StringUtils.isNullOrEmpty(this.f2794c) && StringUtils.isNullOrEmpty(this.f2795d)) {
            AppboyLogger.m1733d(f2789a, "Campaign, card, and trigger Ids not found. Not logging html in-app message click.");
            return false;
        } else if (StringUtils.isNullOrBlank(buttonId)) {
            AppboyLogger.m1737i(f2789a, "Button Id was null or blank for this html in-app message. Ignoring.");
            return false;
        } else if (this.f2820k) {
            AppboyLogger.m1737i(f2789a, "Button click already logged for this html in-app message. Ignoring.");
            return false;
        } else if (this.f2799h == null) {
            AppboyLogger.m1735e(f2789a, "Cannot log an html in-app message button click because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.f2799h.mo6769a((C0386bo) C0397bz.m286a(this.f2793b, this.f2794c, this.f2795d, buttonId));
                this.f2821l = buttonId;
                this.f2820k = true;
                return true;
            } catch (JSONException e) {
                this.f2799h.mo6767a((Throwable) e);
                return false;
            }
        }
    }

    public void onAfterClosed() {
        super.onAfterClosed();
        if (this.f2820k && !StringUtils.isNullOrBlank(this.f2795d) && !StringUtils.isNullOrBlank(this.f2821l)) {
            this.f2799h.mo6765a((C0495ex) new C0497ez(this.f2795d, this.f2821l));
        }
    }

    public JSONObject forJsonPut() {
        if (this.f2798g != null) {
            return this.f2798g;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.putOpt("zipped_assets_url", this.f2818i);
            return forJsonPut;
        } catch (JSONException e) {
            return null;
        }
    }
}
