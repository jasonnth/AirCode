package com.jumio.p311nv.models;

import android.app.Activity;
import com.jumio.commons.PersistWith;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import java.util.ArrayList;

@PersistWith("MerchantSettingsModel")
/* renamed from: com.jumio.nv.models.MerchantSettingsModel */
public class MerchantSettingsModel implements StaticModel {

    /* renamed from: a */
    private NVDocumentVariant f3390a;

    /* renamed from: b */
    private String f3391b = "";

    /* renamed from: c */
    private String f3392c = "";

    /* renamed from: d */
    private boolean f3393d;

    /* renamed from: e */
    private String f3394e = "";

    /* renamed from: f */
    private String f3395f = "";

    /* renamed from: g */
    private String f3396g = "";

    /* renamed from: h */
    private String f3397h = "";

    /* renamed from: i */
    private boolean f3398i = false;

    /* renamed from: j */
    private transient Activity f3399j;

    /* renamed from: k */
    private boolean f3400k;

    /* renamed from: l */
    private NVDocumentType f3401l;

    /* renamed from: m */
    private ArrayList<NVDocumentType> f3402m;

    /* renamed from: n */
    private boolean f3403n;

    /* renamed from: o */
    private boolean f3404o;

    /* renamed from: p */
    private boolean f3405p;

    /* renamed from: q */
    private boolean f3406q;

    /* renamed from: r */
    private boolean f3407r;

    /* renamed from: s */
    private boolean f3408s;

    /* renamed from: t */
    private boolean f3409t;

    public boolean isFaceMatchEnabled() {
        return this.f3404o;
    }

    public void setFaceMatchEnabled(boolean z) {
        this.f3404o = z;
    }

    public boolean isFaceMatchSet() {
        return this.f3405p;
    }

    public void setFaceMatchSet(boolean z) {
        this.f3405p = z;
    }

    public String getMerchantScanReference() {
        return this.f3391b;
    }

    public void setMerchantScanReference(String str) {
        this.f3391b = str;
    }

    public String getMerchantReportingCriteria() {
        return this.f3392c;
    }

    public void setMerchantReportingCriteria(String str) {
        this.f3392c = str;
    }

    public boolean requireVerification() {
        return this.f3393d;
    }

    public String getCustomerId() {
        return this.f3394e;
    }

    public void setCustomerId(String str) {
        this.f3394e = str;
    }

    public String getCallbackUrl() {
        return this.f3395f;
    }

    public void setCallbackUrl(String str) {
        this.f3395f = str;
    }

    public String getIsoCode() {
        return this.f3396g;
    }

    public void setCountryIsoCode(String str) {
        this.f3396g = str;
    }

    public NVDocumentVariant getDocumentVariant() {
        return this.f3390a;
    }

    public void setDocumentVariant(NVDocumentVariant nVDocumentVariant) {
        this.f3390a = nVDocumentVariant;
    }

    public void setRequireVerification(boolean z) {
        this.f3393d = z;
    }

    public boolean isCameraFrontfacing() {
        return this.f3398i;
    }

    public void setCameraFacingFront(boolean z) {
        this.f3398i = z;
    }

    public Activity getContext() {
        return this.f3399j;
    }

    public void setContext(Activity activity) {
        this.f3399j = activity;
    }

    public void setCountryPreSelected(boolean z) {
        this.f3400k = z;
    }

    public boolean isCountryPreSelected() {
        return this.f3400k;
    }

    public NVDocumentType getDocumentTypeId() {
        return this.f3401l;
    }

    public void setPreSelectedDocumentType(NVDocumentType nVDocumentType) {
        this.f3401l = nVDocumentType;
    }

    public ArrayList<NVDocumentType> getSupportedDocumentTypes() {
        return this.f3402m;
    }

    public void setSupportedDocumentTypes(ArrayList<NVDocumentType> arrayList) {
        this.f3402m = arrayList;
    }

    public void setDocumentVariantPreSelected(boolean z) {
        this.f3403n = z;
    }

    public boolean isDocumentVariantPreSelected() {
        return this.f3403n;
    }

    public void setDataExtractionOnMobileOnly(boolean z) {
        this.f3406q = z;
    }

    public boolean isDataExtractionOnMobileOnly() {
        return this.f3406q;
    }

    public void setEnableEpassport(boolean z) {
        this.f3407r = z;
    }

    public boolean isEnableEpassport() {
        return this.f3407r;
    }

    public void setSendDebugInfo(boolean z) {
        this.f3408s = z;
    }

    public boolean isSendDebugInfo() {
        return this.f3408s;
    }

    public boolean isShowHelpBeforeScan() {
        return this.f3409t;
    }

    public void setShowHelpBeforeScan(boolean z) {
        this.f3409t = z;
    }

    public String getAdditionalInformation() {
        return this.f3397h;
    }

    public void setAdditionalInformation(String str) {
        this.f3397h = str;
    }
}
