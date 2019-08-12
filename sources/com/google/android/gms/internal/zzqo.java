package com.google.android.gms.internal;

import com.facebook.common.util.UriUtil;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@zzme
public class zzqo {
    public HttpURLConnection zzb(String str, int i) throws IOException {
        URL url = new URL(str);
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (i3 <= 20) {
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(i);
                openConnection.setReadTimeout(i);
                if (!(openConnection instanceof HttpURLConnection)) {
                    throw new IOException("Invalid protocol.");
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                httpURLConnection.setInstanceFollowRedirects(false);
                if (httpURLConnection.getResponseCode() / 100 != 3) {
                    return httpURLConnection;
                }
                String headerField = httpURLConnection.getHeaderField("Location");
                if (headerField == null) {
                    throw new IOException("Missing Location header in redirect");
                }
                URL url2 = new URL(url, headerField);
                String protocol = url2.getProtocol();
                if (protocol == null) {
                    throw new IOException("Protocol is null");
                } else if (protocol.equals(UriUtil.HTTP_SCHEME) || protocol.equals(UriUtil.HTTPS_SCHEME)) {
                    String str2 = "Redirecting to ";
                    String valueOf = String.valueOf(headerField);
                    zzpk.zzbf(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    httpURLConnection.disconnect();
                    i2 = i3;
                    url = url2;
                } else {
                    String str3 = "Unsupported scheme: ";
                    String valueOf2 = String.valueOf(protocol);
                    throw new IOException(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
                }
            } else {
                throw new IOException("Too many redirects (20)");
            }
        }
    }
}
