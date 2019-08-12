package p005cn.jpush.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

/* renamed from: cn.jpush.android.util.HttpHelper */
public class HttpHelper {
    public static final int DEFAULT_CONN_TIME_OUT = 30000;
    public static final int DEFAULT_SOCKET_TIME_OUT = 30000;
    public static final String ERROR = "<<error>>";
    public static final String FAILED_WITH_RETRIES = "<<failed_with_retries>>";
    public static final String HTTP_DEFUALT_PROXY = "10.0.0.172";
    public static final String NETWORKERROR = "<<networkerror>>";
    public static final String SERVER_FAILED = "<<failed>>";
    private static final String TAG = "HttpHelper";
    public static boolean shouldShutdownConnection = false;

    /* renamed from: cn.jpush.android.util.HttpHelper$DownloadListener */
    public interface DownloadListener {
        void onFinish(boolean z, String str);
    }

    public static boolean isResponseOk(String response) {
        if (SERVER_FAILED.equals(response) || ERROR.equals(response) || FAILED_WITH_RETRIES.equals(response)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String httpSimpleGet(java.lang.String r16, int r17, long r18) {
        /*
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "action:httpSimpleGet - "
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            p005cn.jpush.android.util.Logger.m1416d(r13, r14)
            r13 = 1
            r0 = r17
            if (r0 < r13) goto L_0x0027
            r13 = 10
            r0 = r17
            if (r0 <= r13) goto L_0x0029
        L_0x0027:
            r17 = 1
        L_0x0029:
            r14 = 200(0xc8, double:9.9E-322)
            int r13 = (r18 > r14 ? 1 : (r18 == r14 ? 0 : -1))
            if (r13 < 0) goto L_0x0036
            r14 = 60000(0xea60, double:2.9644E-319)
            int r13 = (r18 > r14 ? 1 : (r18 == r14 ? 0 : -1))
            if (r13 <= 0) goto L_0x0038
        L_0x0036:
            r18 = 2000(0x7d0, double:9.88E-321)
        L_0x0038:
            r12 = 0
            r4 = 0
            r5 = 0
            r8 = 0
            r3 = 0
            r10 = -1
            r7 = 0
            r6 = r5
        L_0x0040:
            java.net.URL r5 = new java.net.URL     // Catch:{ SSLPeerUnverifiedException -> 0x00ab, Exception -> 0x00bc, AssertionError -> 0x00cd, all -> 0x00f3 }
            r0 = r16
            r5.<init>(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x00ab, Exception -> 0x00bc, AssertionError -> 0x00cd, all -> 0x00f3 }
            java.net.URLConnection r13 = r5.openConnection()     // Catch:{ SSLPeerUnverifiedException -> 0x025c, Exception -> 0x0259, AssertionError -> 0x0256 }
            r0 = r13
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ SSLPeerUnverifiedException -> 0x025c, Exception -> 0x0259, AssertionError -> 0x0256 }
            r12 = r0
            java.lang.String r13 = "Connection"
            java.lang.String r14 = "Close"
            r12.addRequestProperty(r13, r14)     // Catch:{ SSLPeerUnverifiedException -> 0x025c, Exception -> 0x0259, AssertionError -> 0x0256 }
            java.io.InputStream r4 = r12.getInputStream()     // Catch:{ SSLPeerUnverifiedException -> 0x025c, Exception -> 0x0259, AssertionError -> 0x0256 }
            int r10 = r12.getResponseCode()     // Catch:{ SSLPeerUnverifiedException -> 0x025c, Exception -> 0x0259, AssertionError -> 0x0256 }
            r13 = 200(0xc8, float:2.8E-43)
            if (r10 != r13) goto L_0x0092
            if (r4 == 0) goto L_0x0092
            java.lang.String r9 = new java.lang.String     // Catch:{ SSLPeerUnverifiedException -> 0x025c, Exception -> 0x0259, AssertionError -> 0x0256 }
            byte[] r13 = p005cn.jpush.android.util.StreamTool.readInputStream(r4)     // Catch:{ SSLPeerUnverifiedException -> 0x025c, Exception -> 0x0259, AssertionError -> 0x0256 }
            java.lang.String r14 = "UTF-8"
            r9.<init>(r13, r14)     // Catch:{ SSLPeerUnverifiedException -> 0x025c, Exception -> 0x0259, AssertionError -> 0x0256 }
            if (r12 == 0) goto L_0x0077
            r12.disconnect()
        L_0x0077:
            r13 = 200(0xc8, float:2.8E-43)
            if (r10 < r13) goto L_0x010d
            r13 = 300(0x12c, float:4.2E-43)
            if (r10 >= r13) goto L_0x010d
            r7 = r9
            if (r7 != 0) goto L_0x008f
            java.lang.String r13 = "HttpHelper"
            java.lang.String r14 = "Unexpected: server responsed NULL"
            r15 = 0
            p005cn.jpush.android.util.Logger.m1417d(r13, r14, r15)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r7 = "<<error>>"
        L_0x008f:
            r8 = r9
            r13 = r7
        L_0x0091:
            return r13
        L_0x0092:
            int r3 = r3 + 1
            r0 = r17
            if (r3 < r0) goto L_0x00a1
            java.lang.String r13 = "<<failed_with_retries>>"
            if (r12 == 0) goto L_0x0091
            r12.disconnect()
            goto L_0x0091
        L_0x00a1:
            if (r12 == 0) goto L_0x00a6
            r12.disconnect()
        L_0x00a6:
            java.lang.Thread.sleep(r18)     // Catch:{ InterruptedException -> 0x00fb }
            r6 = r5
            goto L_0x0040
        L_0x00ab:
            r2 = move-exception
            r5 = r6
        L_0x00ad:
            java.lang.String r13 = "HttpHelper"
            java.lang.String r14 = "Catch SSLPeerUnverifiedException, http client execute error!"
            p005cn.jpush.android.util.Logger.m1422ee(r13, r14)     // Catch:{ all -> 0x0253 }
            if (r12 == 0) goto L_0x00a6
            r12.disconnect()
            goto L_0x00a6
        L_0x00bc:
            r2 = move-exception
            r5 = r6
        L_0x00be:
            java.lang.String r13 = "HttpHelper"
            java.lang.String r14 = "http client execute error"
            p005cn.jpush.android.util.Logger.m1417d(r13, r14, r2)     // Catch:{ all -> 0x0253 }
            if (r12 == 0) goto L_0x00a6
            r12.disconnect()
            goto L_0x00a6
        L_0x00cd:
            r11 = move-exception
            r5 = r6
        L_0x00cf:
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0253 }
            r14.<init>()     // Catch:{ all -> 0x0253 }
            java.lang.String r15 = "Catch AssertionError to avoid http close crash - "
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x0253 }
            java.lang.String r15 = r11.toString()     // Catch:{ all -> 0x0253 }
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x0253 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0253 }
            p005cn.jpush.android.util.Logger.m1420e(r13, r14)     // Catch:{ all -> 0x0253 }
            if (r12 == 0) goto L_0x00a6
            r12.disconnect()
            goto L_0x00a6
        L_0x00f3:
            r13 = move-exception
            r5 = r6
        L_0x00f5:
            if (r12 == 0) goto L_0x00fa
            r12.disconnect()
        L_0x00fa:
            throw r13
        L_0x00fb:
            r13 = move-exception
            r6 = r5
            goto L_0x0040
        L_0x00ff:
            r2 = move-exception
            java.lang.String r13 = "HttpHelper"
            java.lang.String r14 = "parse entity error"
            p005cn.jpush.android.util.Logger.m1429v(r13, r14, r2)
            java.lang.String r7 = "<<error>>"
            goto L_0x008f
        L_0x010d:
            r13 = 400(0x190, float:5.6E-43)
            if (r10 < r13) goto L_0x01f3
            r13 = 500(0x1f4, float:7.0E-43)
            if (r10 >= r13) goto L_0x01f3
            r13 = 400(0x190, float:5.6E-43)
            if (r13 != r10) goto L_0x013a
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Server response failure:400 - "
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            p005cn.jpush.android.util.Logger.m1416d(r13, r14)
            java.lang.String r7 = "<<failed>>"
            goto L_0x008f
        L_0x013a:
            r13 = 401(0x191, float:5.62E-43)
            if (r13 != r10) goto L_0x015f
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Request not authorized:401 - "
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            p005cn.jpush.android.util.Logger.m1416d(r13, r14)
            java.lang.String r7 = "<<error>>"
            goto L_0x008f
        L_0x015f:
            r13 = 404(0x194, float:5.66E-43)
            if (r13 != r10) goto L_0x0184
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Request path does not exist: 404 - "
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            p005cn.jpush.android.util.Logger.m1416d(r13, r14)
            java.lang.String r7 = "<<error>>"
            goto L_0x008f
        L_0x0184:
            r13 = 406(0x196, float:5.69E-43)
            if (r13 != r10) goto L_0x01a9
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "not acceptable:406 - "
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            p005cn.jpush.android.util.Logger.m1416d(r13, r14)
            java.lang.String r7 = "<<error>>"
            goto L_0x008f
        L_0x01a9:
            r13 = 408(0x198, float:5.72E-43)
            if (r13 != r10) goto L_0x01ce
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "request timeout:408 - "
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            p005cn.jpush.android.util.Logger.m1416d(r13, r14)
            java.lang.String r7 = "<<error>>"
            goto L_0x008f
        L_0x01ce:
            r13 = 409(0x199, float:5.73E-43)
            if (r13 != r10) goto L_0x008f
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "conflict:409 - "
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            p005cn.jpush.android.util.Logger.m1416d(r13, r14)
            java.lang.String r7 = "<<error>>"
            goto L_0x008f
        L_0x01f3:
            r13 = 500(0x1f4, float:7.0E-43)
            if (r10 < r13) goto L_0x0227
            r13 = 600(0x258, float:8.41E-43)
            if (r10 >= r13) goto L_0x0227
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Server error - "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r10)
            java.lang.String r15 = ", url:"
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            p005cn.jpush.android.util.Logger.m1416d(r13, r14)
            java.lang.String r7 = "<<error>>"
            goto L_0x008f
        L_0x0227:
            java.lang.String r13 = "HttpHelper"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Other wrong response status - "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r10)
            java.lang.String r15 = ", url:"
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            p005cn.jpush.android.util.Logger.m1416d(r13, r14)
            java.lang.String r7 = "<<error>>"
            goto L_0x008f
        L_0x0253:
            r13 = move-exception
            goto L_0x00f5
        L_0x0256:
            r11 = move-exception
            goto L_0x00cf
        L_0x0259:
            r2 = move-exception
            goto L_0x00be
        L_0x025c:
            r2 = move-exception
            goto L_0x00ad
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.HttpHelper.httpSimpleGet(java.lang.String, int, long):java.lang.String");
    }

    public static byte[] httpGet(String url, int retries, long interval, int moreRetries) {
        byte[] bytz = null;
        for (int j = 0; j < moreRetries; j++) {
            bytz = httpGet(url, retries, interval);
            if (bytz != null) {
                break;
            }
        }
        return bytz;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a9 A[SYNTHETIC, Splitter:B:44:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00bf A[SYNTHETIC, Splitter:B:54:0x00bf] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00cc A[SYNTHETIC, Splitter:B:61:0x00cc] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0097 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] httpGet(java.lang.String r14, int r15, long r16) {
        /*
            r11 = 1
            if (r15 < r11) goto L_0x0007
            r11 = 10
            if (r15 <= r11) goto L_0x0008
        L_0x0007:
            r15 = 1
        L_0x0008:
            r12 = 200(0xc8, double:9.9E-322)
            int r11 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1))
            if (r11 < 0) goto L_0x0015
            r12 = 60000(0xea60, double:2.9644E-319)
            int r11 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1))
            if (r11 <= 0) goto L_0x0017
        L_0x0015:
            r16 = 2000(0x7d0, double:9.88E-321)
        L_0x0017:
            java.lang.String r11 = "HttpHelper"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "action:httpGet - "
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r14)
            java.lang.String r12 = r12.toString()
            p005cn.jpush.android.util.Logger.m1416d(r11, r12)
            r10 = 0
            r5 = 0
            r6 = 0
            r8 = 0
            r4 = 0
            r9 = -1
            r2 = 0
            r7 = r6
        L_0x0039:
            java.net.URL r6 = new java.net.URL     // Catch:{ SSLPeerUnverifiedException -> 0x009c, Exception -> 0x00b2, all -> 0x00c8 }
            r6.<init>(r14)     // Catch:{ SSLPeerUnverifiedException -> 0x009c, Exception -> 0x00b2, all -> 0x00c8 }
            java.net.URLConnection r11 = r6.openConnection()     // Catch:{ SSLPeerUnverifiedException -> 0x017d, Exception -> 0x017a }
            r0 = r11
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ SSLPeerUnverifiedException -> 0x017d, Exception -> 0x017a }
            r10 = r0
            java.lang.String r11 = "Accept-Encoding"
            java.lang.String r12 = "identity"
            r10.setRequestProperty(r11, r12)     // Catch:{ SSLPeerUnverifiedException -> 0x017d, Exception -> 0x017a }
            java.lang.String r11 = "Connection"
            java.lang.String r12 = "Close"
            r10.addRequestProperty(r11, r12)     // Catch:{ SSLPeerUnverifiedException -> 0x017d, Exception -> 0x017a }
            java.io.InputStream r5 = r10.getInputStream()     // Catch:{ SSLPeerUnverifiedException -> 0x017d, Exception -> 0x017a }
            byte[] r8 = p005cn.jpush.android.util.StreamTool.readInputStream(r5)     // Catch:{ SSLPeerUnverifiedException -> 0x017d, Exception -> 0x017a }
            int r9 = r10.getResponseCode()     // Catch:{ SSLPeerUnverifiedException -> 0x017d, Exception -> 0x017a }
            r11 = 200(0xc8, float:2.8E-43)
            if (r9 != r11) goto L_0x0089
            if (r5 == 0) goto L_0x0089
            int r2 = r10.getContentLength()     // Catch:{ SSLPeerUnverifiedException -> 0x017d, Exception -> 0x017a }
            if (r5 == 0) goto L_0x0078
            r5.close()     // Catch:{ IOException -> 0x0168 }
        L_0x0073:
            if (r10 == 0) goto L_0x0078
            r10.disconnect()
        L_0x0078:
            r11 = 200(0xc8, float:2.8E-43)
            if (r11 != r9) goto L_0x00fe
            if (r2 != 0) goto L_0x00e2
            java.lang.String r11 = "HttpHelper"
            java.lang.String r12 = "Unexpected: downloaded bytes content length is 0"
            p005cn.jpush.android.util.Logger.m1416d(r11, r12)     // Catch:{ Exception -> 0x00f2 }
            r11 = 0
        L_0x0088:
            return r11
        L_0x0089:
            if (r5 == 0) goto L_0x0093
            r5.close()     // Catch:{ IOException -> 0x016b }
        L_0x008e:
            if (r10 == 0) goto L_0x0093
            r10.disconnect()
        L_0x0093:
            int r4 = r4 + 1
            if (r4 < r15) goto L_0x00d5
            r10.disconnect()
            r11 = 0
            goto L_0x0088
        L_0x009c:
            r3 = move-exception
            r6 = r7
        L_0x009e:
            java.lang.String r11 = "HttpHelper"
            java.lang.String r12 = "Catch SSLPeerUnverifiedException, http client execute error!"
            p005cn.jpush.android.util.Logger.m1422ee(r11, r12)     // Catch:{ all -> 0x0177 }
            if (r5 == 0) goto L_0x0093
            r5.close()     // Catch:{ IOException -> 0x016e }
        L_0x00ac:
            if (r10 == 0) goto L_0x0093
            r10.disconnect()
            goto L_0x0093
        L_0x00b2:
            r3 = move-exception
            r6 = r7
        L_0x00b4:
            java.lang.String r11 = "HttpHelper"
            java.lang.String r12 = "http client execute error"
            p005cn.jpush.android.util.Logger.m1417d(r11, r12, r3)     // Catch:{ all -> 0x0177 }
            if (r5 == 0) goto L_0x0093
            r5.close()     // Catch:{ IOException -> 0x0171 }
        L_0x00c2:
            if (r10 == 0) goto L_0x0093
            r10.disconnect()
            goto L_0x0093
        L_0x00c8:
            r11 = move-exception
            r6 = r7
        L_0x00ca:
            if (r5 == 0) goto L_0x00d4
            r5.close()     // Catch:{ IOException -> 0x0174 }
        L_0x00cf:
            if (r10 == 0) goto L_0x00d4
            r10.disconnect()
        L_0x00d4:
            throw r11
        L_0x00d5:
            long r12 = (long) r4
            long r12 = r12 * r16
            java.lang.Thread.sleep(r12)     // Catch:{ InterruptedException -> 0x00de }
            r7 = r6
            goto L_0x0039
        L_0x00de:
            r11 = move-exception
            r7 = r6
            goto L_0x0039
        L_0x00e2:
            int r11 = r8.length     // Catch:{ Exception -> 0x00f2 }
            if (r11 >= r2) goto L_0x00f0
            java.lang.String r11 = "HttpHelper"
            java.lang.String r12 = "Download bytes failed. Got bytes len < header content length."
            p005cn.jpush.android.util.Logger.m1416d(r11, r12)     // Catch:{ Exception -> 0x00f2 }
            r11 = 0
            goto L_0x0088
        L_0x00f0:
            r11 = r8
            goto L_0x0088
        L_0x00f2:
            r3 = move-exception
            java.lang.String r11 = "HttpHelper"
            java.lang.String r12 = "parse response error"
            p005cn.jpush.android.util.Logger.m1417d(r11, r12, r3)
            r11 = 0
            goto L_0x0088
        L_0x00fe:
            r11 = 400(0x190, float:5.6E-43)
            if (r11 != r9) goto L_0x011f
            java.lang.String r11 = "HttpHelper"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "server response failure - "
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r14)
            java.lang.String r12 = r12.toString()
            p005cn.jpush.android.util.Logger.m1416d(r11, r12)
            r11 = 0
            goto L_0x0088
        L_0x011f:
            r11 = 404(0x194, float:5.66E-43)
            if (r11 != r9) goto L_0x0140
            java.lang.String r11 = "HttpHelper"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Request path does not exist: 404 - "
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r14)
            java.lang.String r12 = r12.toString()
            p005cn.jpush.android.util.Logger.m1416d(r11, r12)
            r11 = 0
            goto L_0x0088
        L_0x0140:
            java.lang.String r11 = "HttpHelper"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Other wrong response status - "
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r9)
            java.lang.String r13 = ", url:"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r14)
            java.lang.String r12 = r12.toString()
            p005cn.jpush.android.util.Logger.m1416d(r11, r12)
            r11 = 0
            goto L_0x0088
        L_0x0168:
            r11 = move-exception
            goto L_0x0073
        L_0x016b:
            r11 = move-exception
            goto L_0x008e
        L_0x016e:
            r11 = move-exception
            goto L_0x00ac
        L_0x0171:
            r11 = move-exception
            goto L_0x00c2
        L_0x0174:
            r12 = move-exception
            goto L_0x00cf
        L_0x0177:
            r11 = move-exception
            goto L_0x00ca
        L_0x017a:
            r3 = move-exception
            goto L_0x00b4
        L_0x017d:
            r3 = move-exception
            goto L_0x009e
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.HttpHelper.httpGet(java.lang.String, int, long):byte[]");
    }

    public static synchronized void downloadImage(String url, final String iconSaveFolder, final DownloadListener downloadListener) {
        synchronized (HttpHelper.class) {
            Logger.m1428v(TAG, "action:downloadImage - url:" + url);
            if (TextUtils.isEmpty(url) || iconSaveFolder == null) {
                downloadListener.onFinish(false, "");
            } else {
                final String downloadUrl = url.trim();
                new Thread(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
                    /* JADX WARNING: Removed duplicated region for block: B:39:0x012c A[SYNTHETIC, Splitter:B:39:0x012c] */
                    /* JADX WARNING: Removed duplicated region for block: B:42:0x0131 A[SYNTHETIC, Splitter:B:42:0x0131] */
                    /* JADX WARNING: Removed duplicated region for block: B:45:0x0136 A[SYNTHETIC, Splitter:B:45:0x0136] */
                    /* JADX WARNING: Removed duplicated region for block: B:48:0x013b  */
                    /* JADX WARNING: Removed duplicated region for block: B:85:0x01ac A[SYNTHETIC, Splitter:B:85:0x01ac] */
                    /* JADX WARNING: Removed duplicated region for block: B:88:0x01b1 A[SYNTHETIC, Splitter:B:88:0x01b1] */
                    /* JADX WARNING: Removed duplicated region for block: B:91:0x01b6 A[SYNTHETIC, Splitter:B:91:0x01b6] */
                    /* JADX WARNING: Removed duplicated region for block: B:94:0x01bb  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r22 = this;
                            r15 = 0
                            r3 = 0
                            r9 = 0
                            r11 = 0
                            java.net.URL r18 = new java.net.URL     // Catch:{ Exception -> 0x0159 }
                            r0 = r22
                            java.lang.String r0 = r0     // Catch:{ Exception -> 0x0159 }
                            r19 = r0
                            r18.<init>(r19)     // Catch:{ Exception -> 0x0159 }
                            java.net.URLConnection r19 = r18.openConnection()     // Catch:{ Exception -> 0x0159 }
                            r0 = r19
                            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x0159 }
                            r11 = r0
                            r19 = 0
                            r0 = r19
                            p005cn.jpush.android.util.HttpHelper.addHttpURLConnectRequestProperty(r11, r0)     // Catch:{ Exception -> 0x0159 }
                            java.lang.String r19 = "Accept-Encoding"
                            java.lang.String r20 = "identity"
                            r0 = r19
                            r1 = r20
                            r11.setRequestProperty(r0, r1)     // Catch:{ Exception -> 0x0159 }
                            r11.connect()     // Catch:{ Exception -> 0x0159 }
                            int r17 = r11.getResponseCode()     // Catch:{ Exception -> 0x0159 }
                            java.lang.String r19 = "HttpHelper"
                            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0159 }
                            r20.<init>()     // Catch:{ Exception -> 0x0159 }
                            java.lang.String r21 = "downloadImage statusCode:"
                            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ Exception -> 0x0159 }
                            r0 = r20
                            r1 = r17
                            java.lang.StringBuilder r20 = r0.append(r1)     // Catch:{ Exception -> 0x0159 }
                            java.lang.String r20 = r20.toString()     // Catch:{ Exception -> 0x0159 }
                            p005cn.jpush.android.util.Logger.m1428v(r19, r20)     // Catch:{ Exception -> 0x0159 }
                            r19 = 200(0xc8, float:2.8E-43)
                            r0 = r17
                            r1 = r19
                            if (r0 != r1) goto L_0x0185
                            int r19 = r11.getContentLength()     // Catch:{ Exception -> 0x0159 }
                            r0 = r19
                            long r12 = (long) r0     // Catch:{ Exception -> 0x0159 }
                            java.lang.String r19 = "HttpHelper"
                            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0159 }
                            r20.<init>()     // Catch:{ Exception -> 0x0159 }
                            java.lang.String r21 = "downloadImage imageFileLength:"
                            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ Exception -> 0x0159 }
                            r0 = r20
                            java.lang.StringBuilder r20 = r0.append(r12)     // Catch:{ Exception -> 0x0159 }
                            java.lang.String r20 = r20.toString()     // Catch:{ Exception -> 0x0159 }
                            p005cn.jpush.android.util.Logger.m1428v(r19, r20)     // Catch:{ Exception -> 0x0159 }
                            r0 = r22
                            java.lang.String r0 = r0     // Catch:{ Exception -> 0x0159 }
                            r19 = r0
                            java.lang.String r20 = "/"
                            int r14 = r19.lastIndexOf(r20)     // Catch:{ Exception -> 0x0159 }
                            r0 = r22
                            java.lang.String r0 = r0     // Catch:{ Exception -> 0x0159 }
                            r19 = r0
                            int r20 = r14 + 1
                            java.lang.String r8 = r19.substring(r20)     // Catch:{ Exception -> 0x0159 }
                            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x0159 }
                            r0 = r22
                            java.lang.String r0 = r6     // Catch:{ Exception -> 0x0159 }
                            r19 = r0
                            r0 = r19
                            r7.<init>(r0, r8)     // Catch:{ Exception -> 0x0159 }
                            boolean r19 = r7.exists()     // Catch:{ Exception -> 0x0159 }
                            if (r19 == 0) goto L_0x013f
                            long r20 = r7.length()     // Catch:{ Exception -> 0x0159 }
                            int r19 = (r20 > r12 ? 1 : (r20 == r12 ? 0 : -1))
                            if (r19 != 0) goto L_0x00da
                            r20 = 0
                            int r19 = (r12 > r20 ? 1 : (r12 == r20 ? 0 : -1))
                            if (r19 == 0) goto L_0x00da
                            r0 = r22
                            cn.jpush.android.util.HttpHelper$DownloadListener r0 = r7     // Catch:{ Exception -> 0x0159 }
                            r19 = r0
                            r20 = 1
                            java.lang.String r21 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x0159 }
                            r19.onFinish(r20, r21)     // Catch:{ Exception -> 0x0159 }
                            if (r9 == 0) goto L_0x00ca
                            r9.close()     // Catch:{ IOException -> 0x01bf }
                        L_0x00ca:
                            if (r3 == 0) goto L_0x00cf
                            r3.close()     // Catch:{ IOException -> 0x01c2 }
                        L_0x00cf:
                            if (r15 == 0) goto L_0x00d4
                            r15.close()     // Catch:{ IOException -> 0x01c5 }
                        L_0x00d4:
                            if (r11 == 0) goto L_0x00d9
                            r11.disconnect()
                        L_0x00d9:
                            return
                        L_0x00da:
                            r7.delete()     // Catch:{ Exception -> 0x0159 }
                            r7.createNewFile()     // Catch:{ Exception -> 0x0159 }
                        L_0x00e0:
                            java.io.InputStream r15 = r11.getInputStream()     // Catch:{ Exception -> 0x0159 }
                            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0159 }
                            r4.<init>(r15)     // Catch:{ Exception -> 0x0159 }
                            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x01ea, all -> 0x01e3 }
                            r10.<init>(r7)     // Catch:{ Exception -> 0x01ea, all -> 0x01e3 }
                            r19 = 1024(0x400, float:1.435E-42)
                            r0 = r19
                            byte[] r5 = new byte[r0]     // Catch:{ Exception -> 0x010c, all -> 0x01e6 }
                            r16 = 0
                        L_0x00f6:
                            int r16 = r4.read(r5)     // Catch:{ Exception -> 0x010c, all -> 0x01e6 }
                            r19 = -1
                            r0 = r16
                            r1 = r19
                            if (r0 == r1) goto L_0x015b
                            r19 = 0
                            r0 = r19
                            r1 = r16
                            r10.write(r5, r0, r1)     // Catch:{ Exception -> 0x010c, all -> 0x01e6 }
                            goto L_0x00f6
                        L_0x010c:
                            r6 = move-exception
                            r9 = r10
                            r3 = r4
                        L_0x010f:
                            java.lang.String r19 = "HttpHelper"
                            java.lang.String r20 = ""
                            r0 = r19
                            r1 = r20
                            p005cn.jpush.android.util.Logger.m1417d(r0, r1, r6)     // Catch:{ all -> 0x01a9 }
                            r0 = r22
                            cn.jpush.android.util.HttpHelper$DownloadListener r0 = r7     // Catch:{ all -> 0x01a9 }
                            r19 = r0
                            r20 = 0
                            java.lang.String r21 = ""
                            r19.onFinish(r20, r21)     // Catch:{ all -> 0x01a9 }
                            if (r9 == 0) goto L_0x012f
                            r9.close()     // Catch:{ IOException -> 0x01d4 }
                        L_0x012f:
                            if (r3 == 0) goto L_0x0134
                            r3.close()     // Catch:{ IOException -> 0x01d7 }
                        L_0x0134:
                            if (r15 == 0) goto L_0x0139
                            r15.close()     // Catch:{ IOException -> 0x01da }
                        L_0x0139:
                            if (r11 == 0) goto L_0x00d9
                            r11.disconnect()
                            goto L_0x00d9
                        L_0x013f:
                            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0159 }
                            r0 = r22
                            java.lang.String r0 = r6     // Catch:{ Exception -> 0x0159 }
                            r19 = r0
                            r0 = r19
                            r2.<init>(r0)     // Catch:{ Exception -> 0x0159 }
                            boolean r19 = r2.exists()     // Catch:{ Exception -> 0x0159 }
                            if (r19 != 0) goto L_0x0155
                            r2.mkdirs()     // Catch:{ Exception -> 0x0159 }
                        L_0x0155:
                            r7.createNewFile()     // Catch:{ Exception -> 0x0159 }
                            goto L_0x00e0
                        L_0x0159:
                            r6 = move-exception
                            goto L_0x010f
                        L_0x015b:
                            r10.flush()     // Catch:{ Exception -> 0x010c, all -> 0x01e6 }
                            r0 = r22
                            cn.jpush.android.util.HttpHelper$DownloadListener r0 = r7     // Catch:{ Exception -> 0x010c, all -> 0x01e6 }
                            r19 = r0
                            r20 = 1
                            java.lang.String r21 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x010c, all -> 0x01e6 }
                            r19.onFinish(r20, r21)     // Catch:{ Exception -> 0x010c, all -> 0x01e6 }
                            if (r10 == 0) goto L_0x0172
                            r10.close()     // Catch:{ IOException -> 0x01c8 }
                        L_0x0172:
                            if (r4 == 0) goto L_0x0177
                            r4.close()     // Catch:{ IOException -> 0x01ca }
                        L_0x0177:
                            if (r15 == 0) goto L_0x017c
                            r15.close()     // Catch:{ IOException -> 0x01cc }
                        L_0x017c:
                            if (r11 == 0) goto L_0x0181
                            r11.disconnect()
                        L_0x0181:
                            r9 = r10
                            r3 = r4
                            goto L_0x00d9
                        L_0x0185:
                            r0 = r22
                            cn.jpush.android.util.HttpHelper$DownloadListener r0 = r7     // Catch:{ Exception -> 0x0159 }
                            r19 = r0
                            r20 = 0
                            java.lang.String r21 = ""
                            r19.onFinish(r20, r21)     // Catch:{ Exception -> 0x0159 }
                            if (r9 == 0) goto L_0x0198
                            r9.close()     // Catch:{ IOException -> 0x01ce }
                        L_0x0198:
                            if (r3 == 0) goto L_0x019d
                            r3.close()     // Catch:{ IOException -> 0x01d0 }
                        L_0x019d:
                            if (r15 == 0) goto L_0x01a2
                            r15.close()     // Catch:{ IOException -> 0x01d2 }
                        L_0x01a2:
                            if (r11 == 0) goto L_0x00d9
                            r11.disconnect()
                            goto L_0x00d9
                        L_0x01a9:
                            r19 = move-exception
                        L_0x01aa:
                            if (r9 == 0) goto L_0x01af
                            r9.close()     // Catch:{ IOException -> 0x01dd }
                        L_0x01af:
                            if (r3 == 0) goto L_0x01b4
                            r3.close()     // Catch:{ IOException -> 0x01df }
                        L_0x01b4:
                            if (r15 == 0) goto L_0x01b9
                            r15.close()     // Catch:{ IOException -> 0x01e1 }
                        L_0x01b9:
                            if (r11 == 0) goto L_0x01be
                            r11.disconnect()
                        L_0x01be:
                            throw r19
                        L_0x01bf:
                            r19 = move-exception
                            goto L_0x00ca
                        L_0x01c2:
                            r19 = move-exception
                            goto L_0x00cf
                        L_0x01c5:
                            r19 = move-exception
                            goto L_0x00d4
                        L_0x01c8:
                            r19 = move-exception
                            goto L_0x0172
                        L_0x01ca:
                            r19 = move-exception
                            goto L_0x0177
                        L_0x01cc:
                            r19 = move-exception
                            goto L_0x017c
                        L_0x01ce:
                            r19 = move-exception
                            goto L_0x0198
                        L_0x01d0:
                            r19 = move-exception
                            goto L_0x019d
                        L_0x01d2:
                            r19 = move-exception
                            goto L_0x01a2
                        L_0x01d4:
                            r19 = move-exception
                            goto L_0x012f
                        L_0x01d7:
                            r19 = move-exception
                            goto L_0x0134
                        L_0x01da:
                            r19 = move-exception
                            goto L_0x0139
                        L_0x01dd:
                            r20 = move-exception
                            goto L_0x01af
                        L_0x01df:
                            r20 = move-exception
                            goto L_0x01b4
                        L_0x01e1:
                            r20 = move-exception
                            goto L_0x01b9
                        L_0x01e3:
                            r19 = move-exception
                            r3 = r4
                            goto L_0x01aa
                        L_0x01e6:
                            r19 = move-exception
                            r9 = r10
                            r3 = r4
                            goto L_0x01aa
                        L_0x01ea:
                            r6 = move-exception
                            r3 = r4
                            goto L_0x010f
                        */
                        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.HttpHelper.C16141.run():void");
                    }
                }).start();
            }
        }
    }

    public static void addHttpURLConnectRequestProperty(HttpURLConnection httpURLConnection, boolean isPost) {
        if (httpURLConnection == null) {
            Logger.m1420e(TAG, "httpURLConnection is null");
        }
        if (isPost) {
            try {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("User-Agent", JPushConstants.JPUSH_USER_AGENT);
        httpURLConnection.setRequestProperty("Accept-Charset", JPushConstants.ENCODING_UTF_8);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
    }

    public static boolean checkHttpIsError(String content) {
        return TextUtils.isEmpty(content) || content.equals(ERROR) || content.equals(SERVER_FAILED) || content.equals(FAILED_WITH_RETRIES) || content.equals(NETWORKERROR);
    }

    public static int sendLogs(Context ctx, JSONObject json, boolean deflate) {
        return -1;
    }

    private static boolean updateHeader(HttpURLConnection httpURLConnection, Context context, String body) {
        String token;
        if (httpURLConnection == null) {
            Logger.m1420e(TAG, "updateHeader httpURLConnection is null");
            return false;
        }
        httpURLConnection.setRequestProperty("Accept", "application/jason");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("X-App-Key", AndroidUtil.getAppKey(context));
        if (body == null) {
            token = ReportUtils.tokenGenerator();
        } else {
            token = ReportUtils.tokenGenerator(body, 2);
        }
        if (StringUtils.isEmpty(token)) {
            Logger.m1416d(TAG, "generate report token failed");
            return false;
        }
        String authorization = ReportUtils.getBasicAuthorization(token);
        if (StringUtils.isEmpty(authorization)) {
            Logger.m1416d(TAG, "generate basic authorization failed");
            return false;
        }
        httpURLConnection.setRequestProperty("Authorization", "Basic " + authorization);
        httpURLConnection.setRequestProperty("Charset", JPushConstants.ENCODING_UTF_8);
        return true;
    }

    private static HttpURLConnection getHttpURLConnectionWithProxy(Context ctx, String url) {
        try {
            URL mUrl = new URL(url);
            if (ctx.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", ctx.getPackageName()) == 0) {
                NetworkInfo netInfo = ((ConnectivityManager) ctx.getSystemService("connectivity")).getActiveNetworkInfo();
                if (!(netInfo == null || netInfo.getType() == 1)) {
                    String netType = netInfo.getExtraInfo();
                    if (netType != null && (netType.equals("cmwap") || netType.equals("3gwap") || netType.equals("uniwap"))) {
                        return (HttpURLConnection) mUrl.openConnection(new Proxy(Type.HTTP, new InetSocketAddress(HTTP_DEFUALT_PROXY, 80)));
                    }
                }
            }
            return (HttpURLConnection) mUrl.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStream zipper = new GZIPOutputStream(bos);
        zipper.write(data);
        zipper.close();
        byte[] zip = bos.toByteArray();
        bos.close();
        return zip;
    }

    public static String getConfig(Context context, String url) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String json = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            addHttpURLConnectRequestProperty(httpURLConnection, false);
            if (!updateHeader(httpURLConnection, context, null)) {
                Logger.m1424i(TAG, "updateheader fail");
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            }
            httpURLConnection.connect();
            int statusCode = httpURLConnection.getResponseCode();
            if (statusCode == 200) {
                inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sbd = new StringBuilder();
                    while (true) {
                        String buf = bReader.readLine();
                        if (buf == null) {
                            break;
                        }
                        sbd.append(buf + "\n");
                    }
                    json = sbd.toString();
                    if (json == null || json.equals("")) {
                        Logger.m1424i(TAG, "response content is empty or null while update config");
                    } else {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                            }
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return json;
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return json;
            }
            Logger.m1424i(TAG, "response code:" + statusCode);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (ProtocolException e5) {
            Logger.m1424i(TAG, "ProtocolException:" + e5);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (IOException e7) {
            Logger.m1424i(TAG, "IOException:" + e7);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e8) {
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e9) {
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
