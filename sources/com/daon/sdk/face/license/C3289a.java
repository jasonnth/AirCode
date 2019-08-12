package com.daon.sdk.face.license;

import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.daon.sdk.face.license.a */
public final class C3289a {

    /* renamed from: a */
    private String f3036a;

    /* renamed from: b */
    private byte[] f3037b;

    /* renamed from: c */
    private byte[] f3038c;

    /* renamed from: d */
    private String[] f3039d;

    /* renamed from: e */
    private String f3040e;

    /* renamed from: f */
    private String f3041f;

    /* renamed from: g */
    private String f3042g;

    /* renamed from: h */
    private boolean f3043h = false;

    /* renamed from: i */
    private SimpleDateFormat f3044i = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public C3289a(InputStream inputStream) {
        if (inputStream != null) {
            byte[] a = m1752a(inputStream);
            if (a != null) {
                this.f3043h = m1751a(a);
            }
        }
    }

    /* renamed from: a */
    private static byte[] m1752a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            } catch (Exception e) {
                Log.d("DAON", "Unable to read license data");
                return null;
            }
        }
    }

    /* renamed from: a */
    private boolean m1751a(byte[] bArr) {
        try {
            if (m1750a(new String(bArr))) {
                X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode("MIIDwjCCAqoCCQC4QPdtJ4W3pzANBgkqhkiG9w0BAQUFADCBojELMAkGA1UEBhMCVVMxETAPBgNVBAgTCFZpcmdpbmlhMQ8wDQYDVQQHEwZSZXN0b24xEjAQBgNVBAoTCUlkZW50aXR5WDEbMBkGA1UECxMSUHJvZHVjdCBNYW5hZ2VtZW50MRwwGgYDVQQDExNMaWNlbnNlIEtleSBNYW5hZ2VyMSAwHgYJKoZIhvcNAQkBFhFsaWNlbnNlc0BkYW9uLmNvbTAeFw0xMzExMjExNTU1MTBaFw0yNzA3MzExNTU1MTBaMIGiMQswCQYDVQQGEwJVUzERMA8GA1UECBMIVmlyZ2luaWExDzANBgNVBAcTBlJlc3RvbjESMBAGA1UEChMJSWRlbnRpdHlYMRswGQYDVQQLExJQcm9kdWN0IE1hbmFnZW1lbnQxHDAaBgNVBAMTE0xpY2Vuc2UgS2V5IE1hbmFnZXIxIDAeBgkqhkiG9w0BCQEWEWxpY2Vuc2VzQGRhb24uY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxnwJ6AoT17JRcGTpMX6oySy+K09/EUNOIDVEasSkWhXlJTLzBf1PqWHhxFU9ry0mswRbjEW+cgsG4xQpu9SUt8+LEQ21vVJ30aEboxtE5YMYooFWfojnkM9wBfhNcubOM7xIXsb6nyCrGKn6gdS97cerXv4tILid4zg6fchqAhDIlkoDx8yYc/K15+W+oWdoqu23xJ2gqwpHBZAOlr+vbOwqxfmDXGo3UbIgfAedgR0QJjAwm8nKpZZijOU+m1YS6kriz9zms9iiVP+YGETXYmnVdwuOYw7SFhjCzXypzC/BsnOJVeB7R2TKBWjba/tTZdmflR2QlLxGYNpAH9pqzwIDAQABMA0GCSqGSIb3DQEBBQUAA4IBAQAoyBTqQaHYp7ey20q3GRpPmeoLnqp5AcRfHXJMqGFa+TYmFGemcpgmUWxGYcISUJ1iaBTbRFoLza6pB5mD/I1Uj4Ix5Ri0P2jFPUeeHphrh3dBy3RiLB0bgwEh8nD4k61K4+hPSjJ29Wh1sFF8quMTCvDE0hCe8yR/ZUHnWSDZxKP7BWw3MO4hUblgFZc5bDOWL/yKdG47e9+ihpqR4t6iZiEIOeSk3YpCNye4HqPXhFSUmywghU0/GfoU765GjhJQmCW/XNy47sDV5zMNy1WuVTs8Z9WJ/LJZPGzUdo2mnWWu8KOSqwbGEQGDCePDLUENDHeQHJyvBs9fCe0liLZT", 0)));
                Signature instance = Signature.getInstance("SHA1withRSA");
                instance.initVerify(x509Certificate.getPublicKey());
                instance.update(this.f3038c);
                return instance.verify(this.f3037b);
            }
        } catch (Exception e) {
            Log.d("DAON", "An exception occured while attempting to verify the signature.  Exception: " + e.getMessage());
        }
        return false;
    }

    /* renamed from: a */
    public final boolean mo28817a() {
        try {
            return new Date(System.currentTimeMillis() + 86400000).after(this.f3044i.parse(this.f3041f));
        } catch (ParseException e) {
            return true;
        }
    }

    /* renamed from: b */
    public final boolean mo28818b() {
        return this.f3043h;
    }

    /* renamed from: c */
    public final String mo28819c() {
        int indexOf = this.f3040e.indexOf(42);
        if (indexOf < 0) {
            return this.f3040e;
        }
        return this.f3040e.substring(0, indexOf - 1);
    }

    /* renamed from: a */
    private boolean m1750a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f3036a = jSONObject.getString("organization");
            this.f3037b = Base64.decode(jSONObject.getString("signature"), 0);
            this.f3042g = jSONObject.getString("version");
            JSONObject jSONObject2 = jSONObject.getJSONObject("signed");
            this.f3040e = jSONObject2.getString("applicationIdentifier");
            this.f3041f = jSONObject2.getString("expiry");
            JSONArray jSONArray = jSONObject2.getJSONArray("features");
            if (jSONArray != null) {
                this.f3039d = new String[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.f3039d[i] = (String) jSONArray.get(i);
                }
            }
            this.f3038c = m1753d();
            Log.d("DAON", "License: Organization  : " + this.f3036a);
            Log.d("DAON", "License: Version       : " + this.f3042g);
            Log.d("DAON", "License: App Identifier: " + this.f3040e);
            Log.d("DAON", "License: Expiry        : " + this.f3041f);
            return true;
        } catch (Exception e) {
            Log.d("DAON", "Unable to read license information.");
            return false;
        }
    }

    /* renamed from: d */
    private final byte[] m1753d() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f3040e).append(" ").append(this.f3041f).append(" ");
        List asList = Arrays.asList(this.f3039d);
        Collections.sort(asList);
        if (asList.size() != 0) {
            stringBuffer.append((String) asList.get(0));
            int i = 1;
            while (true) {
                int i2 = i;
                if (i2 >= asList.size()) {
                    break;
                }
                stringBuffer.append(", ").append((String) asList.get(i2));
                i = i2 + 1;
            }
        }
        return stringBuffer.toString().getBytes();
    }
}
