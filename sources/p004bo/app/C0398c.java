package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

/* renamed from: bo.app.c */
public final class C0398c implements C0431d {

    /* renamed from: a */
    private static final String f231a = AppboyLogger.getAppboyLogTag(C0398c.class);

    /* renamed from: b */
    private final int f232b;

    public C0398c(int i) {
        this.f232b = i;
    }

    /* renamed from: a */
    public JSONObject mo6883a(URI uri, Map<String, String> map) {
        return m314a(uri, (JSONObject) null, map, C0632u.GET);
    }

    /* renamed from: a */
    public JSONObject mo6884a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        return m314a(uri, jSONObject, map, C0632u.POST);
    }

    /* renamed from: a */
    private JSONObject m314a(URI uri, JSONObject jSONObject, Map<String, String> map, C0632u uVar) {
        URL a = C0457dq.m525a(uri);
        if (a == null) {
            return null;
        }
        try {
            return m315a(a, jSONObject, map, uVar);
        } catch (IOException e) {
            try {
                return m315a(a, jSONObject, map, uVar);
            } catch (IOException e2) {
                throw new C0357aq(String.format("Experienced IOException twice during request to [%s], failing: [%s]", new Object[]{a.toString(), e2.getMessage()}), e2);
            }
        }
    }

    /* renamed from: a */
    private JSONObject m315a(URL url, JSONObject jSONObject, Map<String, String> map, C0632u uVar) {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (url != null) {
            try {
                httpURLConnection = m317b(url, jSONObject, map, uVar);
            } catch (Throwable th) {
                th = th;
                httpURLConnection2 = null;
            }
        } else {
            httpURLConnection = null;
        }
        if (httpURLConnection != null) {
            try {
                inputStream = m312a(httpURLConnection);
                try {
                    JSONObject jSONObject2 = new JSONObject(m313a(new BufferedReader(new InputStreamReader(inputStream, JPushConstants.ENCODING_UTF_8))));
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream == null) {
                        return jSONObject2;
                    }
                    try {
                        inputStream.close();
                        return jSONObject2;
                    } catch (Exception e) {
                        AppboyLogger.m1736e(f231a, "Caught an error trying to close the inputStream in getResult", e);
                        return jSONObject2;
                    }
                } catch (IOException e2) {
                    AppboyLogger.m1735e(f231a, String.format("Could not read from response stream [%s]", new Object[]{e2.getMessage()}));
                } catch (JSONException e3) {
                    AppboyLogger.m1735e(f231a, String.format("Unable to parse response [%s]", new Object[]{e3}));
                } catch (Throwable th2) {
                    th = th2;
                    inputStream2 = inputStream;
                    httpURLConnection2 = httpURLConnection;
                }
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection2 = httpURLConnection;
            }
        } else {
            inputStream = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e4) {
                    AppboyLogger.m1736e(f231a, "Caught an error trying to close the inputStream in getResult", e4);
                }
            }
            AppboyLogger.m1739w(f231a, String.format("Failed to get result from [%s]. Returning null.", new Object[]{String.valueOf(url)}));
            return null;
        }
        throw th;
        if (httpURLConnection2 != null) {
            httpURLConnection2.disconnect();
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (Exception e5) {
                AppboyLogger.m1736e(f231a, "Caught an error trying to close the inputStream in getResult", e5);
            }
        }
        throw th;
    }

    /* renamed from: a */
    private InputStream m312a(HttpURLConnection httpURLConnection) {
        httpURLConnection.connect();
        if (httpURLConnection.getResponseCode() / 100 != 2) {
            throw new C0357aq(String.format("Bad Http response code from Appboy: [%d]", new Object[]{Integer.valueOf(httpURLConnection.getResponseCode())}));
        } else if ("gzip".equalsIgnoreCase(httpURLConnection.getContentEncoding())) {
            return new GZIPInputStream(httpURLConnection.getInputStream());
        } else {
            return new BufferedInputStream(httpURLConnection.getInputStream());
        }
    }

    /* renamed from: b */
    private HttpURLConnection m317b(URL url, JSONObject jSONObject, Map<String, String> map, C0632u uVar) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(this.f232b);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod(uVar.toString());
            mo6885a(httpURLConnection, map);
            if (uVar == C0632u.POST) {
                m316a(httpURLConnection, jSONObject);
            }
            return httpURLConnection;
        } catch (IOException e) {
            throw new C0357aq(String.format("Could not setup connection [%s] [%s].  Appboy will try to reconnect periodically.", new Object[]{url.toString(), e.getMessage()}), e);
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo6885a(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (Entry entry : map.entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
    }

    /* renamed from: a */
    private void m316a(HttpURLConnection httpURLConnection, JSONObject jSONObject) {
        httpURLConnection.setDoOutput(true);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        bufferedOutputStream.write(jSONObject.toString().getBytes(JPushConstants.ENCODING_UTF_8));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    /* renamed from: a */
    private String m313a(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return sb.toString();
            }
            sb.append(readLine);
        }
    }
}
