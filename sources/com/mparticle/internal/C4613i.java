package com.mparticle.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.common.util.UriUtil;
import com.mparticle.BuildConfig;
import com.mparticle.MParticle;
import com.mparticle.MParticle.LogLevel;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.mparticle.internal.i */
public class C4613i implements C4612h {

    /* renamed from: b */
    private static final String f3761b = (MPUtility.isEmpty("") ? UriUtil.HTTPS_SCHEME : UriUtil.HTTP_SCHEME);

    /* renamed from: c */
    private static final String f3762c = (MPUtility.isEmpty("") ? "nativesdks.mparticle.com" : "");

    /* renamed from: d */
    private static final String f3763d = (MPUtility.isEmpty("") ? "config2.mparticle.com" : "");

    /* renamed from: m */
    private static String f3764m;

    /* renamed from: a */
    Integer f3765a = null;

    /* renamed from: e */
    private final ConfigManager f3766e;

    /* renamed from: f */
    private final String f3767f;

    /* renamed from: g */
    private URL f3768g;

    /* renamed from: h */
    private URL f3769h;

    /* renamed from: i */
    private final String f3770i;

    /* renamed from: j */
    private final SharedPreferences f3771j;

    /* renamed from: k */
    private final String f3772k;

    /* renamed from: l */
    private final Context f3773l;

    /* renamed from: n */
    private SSLSocketFactory f3774n;

    /* renamed from: o */
    private JSONObject f3775o;

    /* renamed from: p */
    private boolean f3776p;

    /* renamed from: com.mparticle.internal.i$a */
    public final class C4614a extends Exception {
        public C4614a() {
            super("mP configuration request failed, deferring next batch.");
        }
    }

    /* renamed from: com.mparticle.internal.i$b */
    public static final class C4615b extends Exception {
        public C4615b() {
            super("This device is being sampled.");
        }
    }

    /* renamed from: com.mparticle.internal.i$c */
    public final class C4616c extends Exception {
        public C4616c() {
            super("mP servers are busy, API connections have been throttled.");
        }
    }

    public C4613i(ConfigManager configManager, SharedPreferences sharedPreferences, Context context) throws MalformedURLException {
        this.f3773l = context;
        this.f3766e = configManager;
        this.f3767f = configManager.getApiSecret();
        this.f3771j = sharedPreferences;
        this.f3772k = configManager.getApiKey();
        this.f3770i = "mParticle Android SDK/4.9.6";
    }

    /* renamed from: a */
    public void mo44885a() throws IOException, C4614a {
        try {
            if (this.f3768g == null) {
                this.f3768g = new URL(new Builder().scheme(f3761b).authority(f3763d).path("/v4/" + this.f3772k + "/config").appendQueryParameter("av", MPUtility.getAppVersionName(this.f3773l)).appendQueryParameter("sv", BuildConfig.VERSION_NAME).build().toString());
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) this.f3768g.openConnection();
            httpURLConnection.setConnectTimeout(OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE);
            httpURLConnection.setReadTimeout(10000);
            ConfigManager configManager = this.f3766e;
            httpURLConnection.setRequestProperty("x-mp-env", Integer.toString(ConfigManager.getEnvironment().getValue()));
            String j = m2270j();
            if (!MPUtility.isEmpty(j)) {
                httpURLConnection.setRequestProperty("x-mp-kits", j);
            }
            httpURLConnection.setRequestProperty("User-Agent", this.f3770i);
            String string = this.f3771j.getString("mp::etag", null);
            if (string != null) {
                httpURLConnection.setRequestProperty("If-None-Match", string);
            }
            String string2 = this.f3771j.getString("mp::ifmodified", null);
            if (string2 != null) {
                httpURLConnection.setRequestProperty("If-Modified-Since", string2);
            }
            mo44892a(httpURLConnection, (String) null);
            mo44889a(httpURLConnection, true);
            if (httpURLConnection.getResponseCode() >= 200 && httpURLConnection.getResponseCode() < 300) {
                JSONObject jsonResponse = MPUtility.getJsonResponse(httpURLConnection);
                mo44893a(jsonResponse);
                this.f3766e.updateConfig(jsonResponse);
                String headerField = httpURLConnection.getHeaderField("ETag");
                String headerField2 = httpURLConnection.getHeaderField("Last-Modified");
                Editor edit = this.f3771j.edit();
                if (!MPUtility.isEmpty(headerField)) {
                    edit.putString("mp::etag", headerField);
                }
                if (!MPUtility.isEmpty(headerField2)) {
                    edit.putString("mp::ifmodified", headerField2);
                }
                edit.apply();
            } else if (httpURLConnection.getResponseCode() >= 400) {
                throw new C4614a();
            }
        } catch (MalformedURLException e) {
            ConfigManager.log(LogLevel.ERROR, "Error constructing config service URL");
        } catch (JSONException e2) {
            ConfigManager.log(LogLevel.ERROR, "Config request failed to process response message JSON");
        }
    }

    /* renamed from: g */
    private URL m2267g() throws MalformedURLException {
        return new URL(f3761b, f3762c, "/v1/" + this.f3772k + "/audience?mpID=" + this.f3766e.getMpid());
    }

    /* renamed from: b */
    public JSONObject mo44886b() {
        Exception e;
        JSONObject jSONObject;
        try {
            ConfigManager.log(LogLevel.DEBUG, "Starting Segment Network request");
            HttpURLConnection httpURLConnection = (HttpURLConnection) m2267g().openConnection();
            httpURLConnection.setConnectTimeout(OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            httpURLConnection.setRequestProperty("User-Agent", this.f3770i);
            mo44892a(httpURLConnection, (String) null);
            mo44889a(httpURLConnection, true);
            if (httpURLConnection.getResponseCode() == 403) {
                ConfigManager.log(LogLevel.ERROR, "Segment call forbidden: is Segmentation enabled for your account?");
            }
            jSONObject = MPUtility.getJsonResponse(httpURLConnection);
            try {
                mo44893a(jSONObject);
            } catch (Exception e2) {
                e = e2;
                ConfigManager.log(LogLevel.ERROR, "Segment call failed: " + e.getMessage());
                return jSONObject;
            }
        } catch (Exception e3) {
            Exception exc = e3;
            jSONObject = null;
            e = exc;
            ConfigManager.log(LogLevel.ERROR, "Segment call failed: " + e.getMessage());
            return jSONObject;
        }
        return jSONObject;
    }

    /* renamed from: c */
    public boolean mo44887c() {
        try {
            mo44896f();
            return false;
        } catch (C4616c e) {
            return true;
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public int mo44884a(String str) throws IOException, C4616c, C4615b {
        mo44896f();
        m2269i();
        if (this.f3769h == null) {
            this.f3769h = new URL(f3761b, f3762c, "/v1/" + this.f3772k + "/events");
        }
        byte[] bytes = str.getBytes();
        HttpURLConnection httpURLConnection = (HttpURLConnection) this.f3769h.openConnection();
        httpURLConnection.setConnectTimeout(OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, "application/json");
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("User-Agent", this.f3770i);
        String activeModuleIds = this.f3766e.getActiveModuleIds();
        if (!MPUtility.isEmpty(activeModuleIds)) {
            httpURLConnection.setRequestProperty("x-mp-kits", activeModuleIds);
        }
        String j = m2270j();
        if (!MPUtility.isEmpty(j)) {
            httpURLConnection.setRequestProperty("x-mp-bundled-kits", j);
        }
        mo44892a(httpURLConnection, str);
        if (BuildConfig.MP_DEBUG.booleanValue()) {
            m2266b(str);
        }
        if (!BuildConfig.MP_DEBUG.booleanValue() && VERSION.SDK_INT >= 14 && (httpURLConnection instanceof HttpsURLConnection)) {
            try {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(m2268h());
            } catch (Exception e) {
            }
        }
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream()));
        try {
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
            mo44889a(httpURLConnection, true);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                mo44893a(MPUtility.getJsonResponse(httpURLConnection));
            }
            return httpURLConnection.getResponseCode();
        } catch (Throwable th) {
            gZIPOutputStream.close();
            throw th;
        }
    }

    /* renamed from: b */
    private void m2266b(String str) {
        int i = 0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("msgs")) {
                JSONArray jSONArray = jSONObject.getJSONArray("msgs");
                ConfigManager.log(LogLevel.DEBUG, "Uploading message batch...");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    ConfigManager.log(LogLevel.DEBUG, "Message type: " + ((JSONObject) jSONArray.get(i2)).getString("dt"));
                }
            } else if (jSONObject.has("sh")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("sh");
                ConfigManager.log(LogLevel.DEBUG, "Uploading session history batch...");
                while (true) {
                    int i3 = i;
                    if (i3 < jSONArray2.length()) {
                        ConfigManager.log(LogLevel.DEBUG, "Message type: " + ((JSONObject) jSONArray2.get(i3)).getString("dt") + " SID: " + ((JSONObject) jSONArray2.get(i3)).optString("sid"));
                        i = i3 + 1;
                    } else {
                        return;
                    }
                }
            }
        } catch (JSONException e) {
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo44892a(HttpURLConnection httpURLConnection, String str) {
        try {
            String requestMethod = httpURLConnection.getRequestMethod();
            String format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US).format(new Date());
            StringBuilder append = new StringBuilder().append(requestMethod).append("\n").append(format).append("\n").append(httpURLConnection.getURL().getFile());
            if (str != null) {
                append.append(str);
            }
            httpURLConnection.setRequestProperty("Date", format);
            httpURLConnection.setRequestProperty("x-mp-signature", MPUtility.hmacSha256Encode(this.f3767f, append.toString()));
        } catch (InvalidKeyException e) {
            ConfigManager.log(LogLevel.ERROR, "Error signing message.");
        } catch (NoSuchAlgorithmException e2) {
            ConfigManager.log(LogLevel.ERROR, "Error signing message.");
        } catch (UnsupportedEncodingException e3) {
            ConfigManager.log(LogLevel.ERROR, "Error signing message.");
        }
    }

    /* renamed from: a */
    private static Certificate m2265a(CertificateFactory certificateFactory, String str) throws IOException, CertificateException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        try {
            return certificateFactory.generateCertificate(byteArrayInputStream);
        } finally {
            byteArrayInputStream.close();
        }
    }

    /* renamed from: h */
    private SSLSocketFactory m2268h() throws Exception {
        if (this.f3774n == null) {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            CertificateFactory instance2 = CertificateFactory.getInstance("X.509");
            instance.setCertificateEntry("intca", m2265a(instance2, "-----BEGIN CERTIFICATE-----\nMIIE0DCCA7igAwIBAgIBBzANBgkqhkiG9w0BAQsFADCBgzELMAkGA1UEBhMCVVMx\nEDAOBgNVBAgTB0FyaXpvbmExEzARBgNVBAcTClNjb3R0c2RhbGUxGjAYBgNVBAoT\nEUdvRGFkZHkuY29tLCBJbmMuMTEwLwYDVQQDEyhHbyBEYWRkeSBSb290IENlcnRp\nZmljYXRlIEF1dGhvcml0eSAtIEcyMB4XDTExMDUwMzA3MDAwMFoXDTMxMDUwMzA3\nMDAwMFowgbQxCzAJBgNVBAYTAlVTMRAwDgYDVQQIEwdBcml6b25hMRMwEQYDVQQH\nEwpTY290dHNkYWxlMRowGAYDVQQKExFHb0RhZGR5LmNvbSwgSW5jLjEtMCsGA1UE\nCxMkaHR0cDovL2NlcnRzLmdvZGFkZHkuY29tL3JlcG9zaXRvcnkvMTMwMQYDVQQD\nEypHbyBEYWRkeSBTZWN1cmUgQ2VydGlmaWNhdGUgQXV0aG9yaXR5IC0gRzIwggEi\nMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC54MsQ1K92vdSTYuswZLiBCGzD\nBNliF44v/z5lz4/OYuY8UhzaFkVLVat4a2ODYpDOD2lsmcgaFItMzEUz6ojcnqOv\nK/6AYZ15V8TPLvQ/MDxdR/yaFrzDN5ZBUY4RS1T4KL7QjL7wMDge87Am+GZHY23e\ncSZHjzhHU9FGHbTj3ADqRay9vHHZqm8A29vNMDp5T19MR/gd71vCxJ1gO7GyQ5HY\npDNO6rPWJ0+tJYqlxvTV0KaudAVkV4i1RFXULSo6Pvi4vekyCgKUZMQWOlDxSq7n\neTOvDCAHf+jfBDnCaQJsY1L6d8EbyHSHyLmTGFBUNUtpTrw700kuH9zB0lL7AgMB\nAAGjggEaMIIBFjAPBgNVHRMBAf8EBTADAQH/MA4GA1UdDwEB/wQEAwIBBjAdBgNV\nHQ4EFgQUQMK9J47MNIMwojPX+2yz8LQsgM4wHwYDVR0jBBgwFoAUOpqFBxBnKLbv\n9r0FQW4gwZTaD94wNAYIKwYBBQUHAQEEKDAmMCQGCCsGAQUFBzABhhhodHRwOi8v\nb2NzcC5nb2RhZGR5LmNvbS8wNQYDVR0fBC4wLDAqoCigJoYkaHR0cDovL2NybC5n\nb2RhZGR5LmNvbS9nZHJvb3QtZzIuY3JsMEYGA1UdIAQ/MD0wOwYEVR0gADAzMDEG\nCCsGAQUFBwIBFiVodHRwczovL2NlcnRzLmdvZGFkZHkuY29tL3JlcG9zaXRvcnkv\nMA0GCSqGSIb3DQEBCwUAA4IBAQAIfmyTEMg4uJapkEv/oV9PBO9sPpyIBslQj6Zz\n91cxG7685C/b+LrTW+C05+Z5Yg4MotdqY3MxtfWoSKQ7CC2iXZDXtHwlTxFWMMS2\nRJ17LJ3lXubvDGGqv+QqG+6EnriDfcFDzkSnE3ANkR/0yBOtg2DZ2HKocyQetawi\nDsoXiWJYRBuriSUBAA/NxBti21G00w9RKpv0vHP8ds42pM3Z2Czqrpv1KrKQ0U11\nGIo/ikGQI31bS/6kA1ibRrLDYGCD+H1QQc7CoZDDu+8CL9IVVO5EFdkKrqeKM+2x\nLXY2JtwE65/3YR8V3Idv7kaWKK2hJn0KCacuBKONvPi8BDAB\n-----END CERTIFICATE-----\n"));
            instance.setCertificateEntry("rootca", m2265a(instance2, "-----BEGIN CERTIFICATE-----\nMIIE0DCCA7igAwIBAgIBBzANBgkqhkiG9w0BAQsFADCBgzELMAkGA1UEBhMCVVMx\nEDAOBgNVBAgTB0FyaXpvbmExEzARBgNVBAcTClNjb3R0c2RhbGUxGjAYBgNVBAoT\nEUdvRGFkZHkuY29tLCBJbmMuMTEwLwYDVQQDEyhHbyBEYWRkeSBSb290IENlcnRp\nZmljYXRlIEF1dGhvcml0eSAtIEcyMB4XDTExMDUwMzA3MDAwMFoXDTMxMDUwMzA3\nMDAwMFowgbQxCzAJBgNVBAYTAlVTMRAwDgYDVQQIEwdBcml6b25hMRMwEQYDVQQH\nEwpTY290dHNkYWxlMRowGAYDVQQKExFHb0RhZGR5LmNvbSwgSW5jLjEtMCsGA1UE\nCxMkaHR0cDovL2NlcnRzLmdvZGFkZHkuY29tL3JlcG9zaXRvcnkvMTMwMQYDVQQD\nEypHbyBEYWRkeSBTZWN1cmUgQ2VydGlmaWNhdGUgQXV0aG9yaXR5IC0gRzIwggEi\nMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC54MsQ1K92vdSTYuswZLiBCGzD\nBNliF44v/z5lz4/OYuY8UhzaFkVLVat4a2ODYpDOD2lsmcgaFItMzEUz6ojcnqOv\nK/6AYZ15V8TPLvQ/MDxdR/yaFrzDN5ZBUY4RS1T4KL7QjL7wMDge87Am+GZHY23e\ncSZHjzhHU9FGHbTj3ADqRay9vHHZqm8A29vNMDp5T19MR/gd71vCxJ1gO7GyQ5HY\npDNO6rPWJ0+tJYqlxvTV0KaudAVkV4i1RFXULSo6Pvi4vekyCgKUZMQWOlDxSq7n\neTOvDCAHf+jfBDnCaQJsY1L6d8EbyHSHyLmTGFBUNUtpTrw700kuH9zB0lL7AgMB\nAAGjggEaMIIBFjAPBgNVHRMBAf8EBTADAQH/MA4GA1UdDwEB/wQEAwIBBjAdBgNV\nHQ4EFgQUQMK9J47MNIMwojPX+2yz8LQsgM4wHwYDVR0jBBgwFoAUOpqFBxBnKLbv\n9r0FQW4gwZTaD94wNAYIKwYBBQUHAQEEKDAmMCQGCCsGAQUFBzABhhhodHRwOi8v\nb2NzcC5nb2RhZGR5LmNvbS8wNQYDVR0fBC4wLDAqoCigJoYkaHR0cDovL2NybC5n\nb2RhZGR5LmNvbS9nZHJvb3QtZzIuY3JsMEYGA1UdIAQ/MD0wOwYEVR0gADAzMDEG\nCCsGAQUFBwIBFiVodHRwczovL2NlcnRzLmdvZGFkZHkuY29tL3JlcG9zaXRvcnkv\nMA0GCSqGSIb3DQEBCwUAA4IBAQAIfmyTEMg4uJapkEv/oV9PBO9sPpyIBslQj6Zz\n91cxG7685C/b+LrTW+C05+Z5Yg4MotdqY3MxtfWoSKQ7CC2iXZDXtHwlTxFWMMS2\nRJ17LJ3lXubvDGGqv+QqG+6EnriDfcFDzkSnE3ANkR/0yBOtg2DZ2HKocyQetawi\nDsoXiWJYRBuriSUBAA/NxBti21G00w9RKpv0vHP8ds42pM3Z2Czqrpv1KrKQ0U11\nGIo/ikGQI31bS/6kA1ibRrLDYGCD+H1QQc7CoZDDu+8CL9IVVO5EFdkKrqeKM+2x\nLXY2JtwE65/3YR8V3Idv7kaWKK2hJn0KCacuBKONvPi8BDAB\n-----END CERTIFICATE-----"));
            instance.setCertificateEntry("fiddlerroot", m2265a(instance2, "-----BEGIN CERTIFICATE-----\nMIICnjCCAgegAwIBAgIQAOlcuB4VA5KNHpx2RQcMrzANBgkqhkiG9w0BAQUFADBq\nMSswKQYDVQQLDCJDcmVhdGVkIGJ5IGh0dHA6Ly93d3cuZmlkZGxlcjIuY29tMRgw\nFgYDVQQKDA9ET19OT1RfVFJVU1RfQkMxITAfBgNVBAMMGERPX05PVF9UUlVTVF9G\naWRkbGVyUm9vdDAeFw0xMzEyMTIwMDAwMDBaFw0yMzEyMTkxMTI1NTRaMGoxKzAp\nBgNVBAsMIkNyZWF0ZWQgYnkgaHR0cDovL3d3dy5maWRkbGVyMi5jb20xGDAWBgNV\nBAoMD0RPX05PVF9UUlVTVF9CQzEhMB8GA1UEAwwYRE9fTk9UX1RSVVNUX0ZpZGRs\nZXJSb290MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6P47ffxB2xJFlYVEZ\nL4KSTORmxI21pUIb6jqkAEGYOeO+In5egCmroZuXbem1YYzTmgkmCelt6OTr0OLa\nePCkdnxteUDMBs0DpcWutdJW9/9MNE90BfJ2WX1CA4zQx4zFZ9FRpYHntaIE8kf4\nbcts1+CE+VnI1fOPo0PsF6yudQIDAQABo0UwQzASBgNVHRMBAf8ECDAGAQH/AgEB\nMA4GA1UdDwEB/wQEAwICBDAdBgNVHQ4EFgQUouuoWsFXoOzyyW94lTD/apHuos8w\nDQYJKoZIhvcNAQEFBQADgYEAjOW9psxS4AeYgUcIhvNR5pd1BkuEwbdtgd8S0zgf\njOmkkQNKHPikfOeJurA3jityX3+z9d2zSvtbLU7MYArb7hs5cibAyxalI6NlWSsg\nQGKwfeATxe0gReGYACTf2WIBa3ceQFhAYhyEUYJpDiZsJi8mZkeQMWH/ZanBnL/Q\ngZ4=\n-----END CERTIFICATE-----"));
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init(instance);
            SSLContext instance4 = SSLContext.getInstance("TLS");
            instance4.init(null, instance3.getTrustManagers(), null);
            this.f3774n = instance4.getSocketFactory();
        }
        return this.f3774n;
    }

    /* renamed from: a */
    public HttpURLConnection mo44889a(HttpURLConnection httpURLConnection, boolean z) throws IOException {
        if (!BuildConfig.MP_DEBUG.booleanValue() && z && VERSION.SDK_INT >= 14 && (httpURLConnection instanceof HttpsURLConnection)) {
            try {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(m2268h());
            } catch (Exception e) {
            }
        }
        if (z) {
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 400 && !this.f3776p) {
                this.f3776p = true;
                ConfigManager.log(LogLevel.ERROR, "Bad API request - is the correct API key and secret configured?");
            }
            if ((responseCode == 503 || responseCode == 429) && !BuildConfig.MP_DEBUG.booleanValue()) {
                mo44891a(httpURLConnection);
            }
        }
        return httpURLConnection;
    }

    /* renamed from: a */
    public void mo44893a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ci")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("ci");
                if (jSONObject2.has("mpid")) {
                    this.f3766e.setMpid(jSONObject2.getLong("mpid"));
                }
                mo44894b(jSONObject2.optJSONObject("ck"));
            }
            if (jSONObject.has("iltv")) {
                this.f3771j.edit().putString("mp::ltv", new BigDecimal(jSONObject.getString("iltv")).add(new BigDecimal(this.f3771j.getString("mp::ltv", AppEventsConstants.EVENT_PARAM_VALUE_NO))).toPlainString()).apply();
            }
        } catch (JSONException e) {
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo44891a(HttpURLConnection httpURLConnection) {
        long j = 7200000;
        if (httpURLConnection != null) {
            String headerField = httpURLConnection.getHeaderField("Retry-After");
            if (MPUtility.isEmpty(headerField)) {
                headerField = httpURLConnection.getHeaderField("retry-after");
            }
            try {
                long parseLong = Long.parseLong(headerField) * 1000;
                if (parseLong > 0) {
                    j = Math.min(parseLong, 86400000);
                }
            } catch (NumberFormatException e) {
                ConfigManager.log(LogLevel.DEBUG, "Unable to parse retry-after header, using default.");
            }
        }
        mo44890a(j + System.currentTimeMillis());
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public long mo44895e() {
        return this.f3771j.getLong("mp::next_valid_request_time", 0);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo44890a(long j) {
        this.f3771j.edit().putLong("mp::next_valid_request_time", j).apply();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public void mo44896f() throws C4616c {
        if (System.currentTimeMillis() < mo44895e()) {
            throw new C4616c();
        }
    }

    /* renamed from: i */
    private void m2269i() throws C4615b {
        if (this.f3765a == null) {
            this.f3765a = Integer.valueOf(MPUtility.hashFnv1A(MPUtility.getRampUdid(this.f3773l).getBytes()).mod(BigInteger.valueOf(100)).intValue());
        }
        int currentRampValue = this.f3766e.getCurrentRampValue();
        if (currentRampValue > 0 && currentRampValue < 100 && this.f3765a.intValue() > this.f3766e.getCurrentRampValue()) {
            throw new C4615b();
        }
    }

    /* renamed from: j */
    private String m2270j() {
        if (f3764m == null) {
            Set supportedKits = MParticle.getInstance().getKitManager().getSupportedKits();
            if (supportedKits == null || supportedKits.size() <= 0) {
                f3764m = "";
            } else {
                StringBuilder sb = new StringBuilder(supportedKits.size() * 3);
                Iterator it = supportedKits.iterator();
                while (it.hasNext()) {
                    sb.append((Integer) it.next());
                    if (it.hasNext()) {
                        sb.append(",");
                    }
                }
                f3764m = sb.toString();
            }
        }
        return f3764m;
    }

    /* renamed from: b */
    public void mo44894b(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                JSONObject d = mo44888d();
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    d.put(str, jSONObject.getJSONObject(str));
                }
                this.f3775o = d;
                this.f3771j.edit().putString("mp::cookies", this.f3775o.toString()).apply();
            } catch (JSONException e) {
            }
        }
    }

    /* renamed from: d */
    public JSONObject mo44888d() {
        if (this.f3775o != null) {
            return this.f3775o;
        }
        String string = this.f3771j.getString("mp::cookies", null);
        if (MPUtility.isEmpty(string)) {
            this.f3775o = new JSONObject();
            this.f3771j.edit().putString("mp::cookies", this.f3775o.toString()).apply();
            return this.f3775o;
        }
        try {
            this.f3775o = new JSONObject(string);
        } catch (JSONException e) {
            this.f3775o = new JSONObject();
        }
        Calendar instance = Calendar.getInstance();
        instance.set(1, 1990);
        Date time = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Iterator keys = this.f3775o.keys();
        ArrayList arrayList = new ArrayList();
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                if (this.f3775o.get(str) instanceof JSONObject) {
                    try {
                        if (simpleDateFormat.parse(((JSONObject) this.f3775o.get(str)).getString("e")).before(time)) {
                            arrayList.add(str);
                        }
                    } catch (ParseException e2) {
                    }
                }
            } catch (JSONException e3) {
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.f3775o.remove((String) it.next());
        }
        if (arrayList.size() > 0) {
            this.f3771j.edit().putString("mp::cookies", this.f3775o.toString()).apply();
        }
        return this.f3775o;
    }
}
