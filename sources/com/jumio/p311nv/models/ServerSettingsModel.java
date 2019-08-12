package com.jumio.p311nv.models;

import android.content.Context;
import com.jumio.commons.PersistWith;
import com.jumio.commons.log.Log;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.Publisher;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.plugins.PluginRegistry;
import com.jumio.core.plugins.PluginRegistry.PluginMode;
import com.jumio.p311nv.IsoCountryConverter;
import com.jumio.p311nv.api.calls.NVBackend;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import jumio.p317nv.core.C4930j;
import jumio.p317nv.core.C4931k;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@PersistWith("ServerSettingsModel")
/* renamed from: com.jumio.nv.models.ServerSettingsModel */
public class ServerSettingsModel extends Publisher<ServerSettingsModel> implements StaticModel, Subscriber<String> {
    public static final String FRANCE_ISO3 = "FRA";
    public static final String GERMAN_ISO3 = "DEU";

    /* renamed from: a */
    private String f3418a;

    /* renamed from: b */
    private boolean f3419b = true;

    /* renamed from: c */
    private String f3420c;

    /* renamed from: d */
    private boolean f3421d = true;

    /* renamed from: e */
    private CountryDocumentModel f3422e = new CountryDocumentModel();

    /* renamed from: f */
    private boolean f3423f = false;

    /* renamed from: g */
    private boolean f3424g = false;

    /* renamed from: h */
    private String f3425h = null;

    /* renamed from: i */
    private int f3426i = 0;

    public String getBarcodeScannerKey() {
        return this.f3418a;
    }

    public void setBarcodeScannerKey(String str) {
        this.f3418a = str;
    }

    public boolean isBrandingEnabled() {
        return this.f3419b;
    }

    public void setBrandingEnabled(boolean z) {
        this.f3419b = z;
    }

    public String getCountryForIp() {
        return this.f3420c;
    }

    public void setCountryForIp(String str) {
        this.f3420c = str;
    }

    public void parseJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            setBarcodeScannerKey(jSONObject.getString("barcodeScannerKey"));
            setBrandingEnabled(jSONObject.optBoolean("brandingEnabled", true));
            setCountryForIp(jSONObject.optString("countryForIp"));
            setAnalyticsEnabled(jSONObject.optBoolean("analyticsEnabled", true));
            setEnabledFields(jSONObject.optString("enabledFields"));
            setVerificationAllowed(jSONObject.optBoolean("verificationAllowed", true));
            setMaxLivenessImages(jSONObject.optInt("maxLivenessImages", 0));
            JSONArray jSONArray = jSONObject.getJSONArray("supportedCountries");
            Log.m1909d("NetverifySDK", "Available plugins: " + Arrays.toString(PluginRegistry.getAvailablePlugins().toArray()));
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                Country country = new Country(jSONObject2.getString("country"));
                if (!country.getName().equalsIgnoreCase(IsoCountryConverter.convertToAlpha2(jSONObject2.getString("country")))) {
                    JSONArray optJSONArray = jSONObject2.optJSONArray("idTypes");
                    ArrayList arrayList = new ArrayList(jSONArray.length());
                    if (optJSONArray != null) {
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            DocumentType a = m1989a(country, optJSONArray.getJSONObject(i2));
                            if (PluginRegistry.getPlugin(a.getDocumentScanMode()) != null) {
                                arrayList.add(a);
                            } else {
                                Log.m1919i("ServerSettingsModel", "Skipping scan mode " + a.getDocumentScanMode() + " because no plugin was found!");
                            }
                        }
                    } else {
                        DocumentType a2 = m1989a(country, jSONObject2.getJSONObject("idTypes"));
                        if (PluginRegistry.getPlugin(a2.getDocumentScanMode()) != null) {
                            arrayList.add(a2);
                        } else {
                            Log.m1919i("ServerSettingsModel", "Skipping scan mode " + a2.getDocumentScanMode() + " because no plugin was found!");
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        country.setAddressFormat(C4931k.valueOf(jSONObject2.optString("addressFormat", "EU")));
                        Collections.sort(arrayList);
                        this.f3422e.add(country, (DocumentType[]) arrayList.toArray(new DocumentType[arrayList.size()]));
                    }
                }
            }
        } catch (JSONException e) {
            Log.m1930w("ServerSettingsModel", "JSONException ", (Throwable) e);
        }
    }

    /* renamed from: a */
    private DocumentType m1989a(Country country, JSONObject jSONObject) throws JSONException {
        int i;
        DocumentType documentType;
        boolean optBoolean = jSONObject.optBoolean("paperVariant", false);
        JSONArray optJSONArray = jSONObject.optJSONArray("paperVariantParts");
        if (optBoolean) {
            if (optJSONArray != null) {
                i = optJSONArray.length();
            } else if (jSONObject.optString("paperVariantParts", null) != null) {
                i = 1;
            }
            documentType = new DocumentType(jSONObject.getString("idType"), jSONObject.optInt("parts"), jSONObject.optString("mrzFormat", ""), jSONObject.optString("mrzSide", ""), jSONObject.optString("barcodeFormat", ""), jSONObject.optString("barcodeSide", ""), jSONObject.optString("thirdPartyOcr", ""), optBoolean, i);
            if (!FRANCE_ISO3.equals(country.getIsoCode()) && documentType.getDocumentScanMode() == DocumentScanMode.TD2) {
                documentType.setDocumentScanMode(DocumentScanMode.CNIS);
            } else if (GERMAN_ISO3.equals(country.getIsoCode()) && documentType.getId() == NVDocumentType.IDENTITY_CARD) {
                documentType.setFallbackScan(false);
                documentType.setDocumentScanMode(DocumentScanMode.CSSN);
                documentType.setDocumentScanSide(ScanSide.BACK);
                documentType.setPaperVariant(PluginRegistry.hasPlugin(PluginMode.MRZ));
                documentType.setPaperScanMode(DocumentScanMode.TD2);
                documentType.setPaperScanSide(ScanSide.FRONT);
                documentType.setPaperParts(2);
            }
            return documentType;
        }
        i = 0;
        documentType = new DocumentType(jSONObject.getString("idType"), jSONObject.optInt("parts"), jSONObject.optString("mrzFormat", ""), jSONObject.optString("mrzSide", ""), jSONObject.optString("barcodeFormat", ""), jSONObject.optString("barcodeSide", ""), jSONObject.optString("thirdPartyOcr", ""), optBoolean, i);
        if (!FRANCE_ISO3.equals(country.getIsoCode())) {
        }
        documentType.setFallbackScan(false);
        documentType.setDocumentScanMode(DocumentScanMode.CSSN);
        documentType.setDocumentScanSide(ScanSide.BACK);
        documentType.setPaperVariant(PluginRegistry.hasPlugin(PluginMode.MRZ));
        documentType.setPaperScanMode(DocumentScanMode.TD2);
        documentType.setPaperScanSide(ScanSide.FRONT);
        documentType.setPaperParts(2);
        return documentType;
    }

    @Deprecated
    public void loadData(Context context) {
        NVBackend.registerForUpdates(context, C4930j.class, this);
    }

    public void onResult(String str) {
        parseJson(str);
        this.f3423f = true;
        publishResult(this);
    }

    public void onError(Throwable th) {
        this.f3423f = false;
        publishError(th);
    }

    public boolean isLoaded() {
        return this.f3423f;
    }

    public CountryDocumentModel getCountryModel() {
        return this.f3422e;
    }

    public boolean isVerificationAllowed() {
        return this.f3421d;
    }

    public void setVerificationAllowed(boolean z) {
        this.f3421d = z;
    }

    public boolean isAnalyticsEnabled() {
        return this.f3424g;
    }

    public void setAnalyticsEnabled(boolean z) {
        this.f3424g = z;
    }

    public String getEnabledFields() {
        return this.f3425h;
    }

    public void setEnabledFields(String str) {
        this.f3425h = str;
    }

    public int getMaxLivenessImages() {
        return this.f3426i;
    }

    public void setMaxLivenessImages(int i) {
        if (i < 0 || i > 10) {
            i = 10;
        }
        this.f3426i = i;
    }
}
