package com.facebook.react.modules.network;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.GuardedResultAsyncTask;
import com.facebook.react.bridge.ReactContext;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ForwardingCookieHandler extends CookieHandler {
    private static final String COOKIE_HEADER = "Cookie";
    /* access modifiers changed from: private */
    public static final boolean USES_LEGACY_STORE = (VERSION.SDK_INT < 21);
    private static final String VERSION_ONE_HEADER = "Set-cookie2";
    private static final String VERSION_ZERO_HEADER = "Set-cookie";
    private final ReactContext mContext;
    private CookieManager mCookieManager;
    /* access modifiers changed from: private */
    public final CookieSaver mCookieSaver = new CookieSaver();

    private class CookieSaver {
        private static final int MSG_PERSIST_COOKIES = 1;
        private static final int TIMEOUT = 30000;
        private final Handler mHandler;

        public CookieSaver() {
            this.mHandler = new Handler(Looper.getMainLooper(), new Callback(ForwardingCookieHandler.this) {
                public boolean handleMessage(Message msg) {
                    if (msg.what != 1) {
                        return false;
                    }
                    CookieSaver.this.persistCookies();
                    return true;
                }
            });
        }

        public void onCookiesModified() {
            if (ForwardingCookieHandler.USES_LEGACY_STORE) {
                this.mHandler.sendEmptyMessageDelayed(1, 30000);
            }
        }

        public void persistCookies() {
            this.mHandler.removeMessages(1);
            ForwardingCookieHandler.this.runInBackground(new Runnable() {
                public void run() {
                    if (ForwardingCookieHandler.USES_LEGACY_STORE) {
                        CookieSyncManager.getInstance().sync();
                    } else {
                        CookieSaver.this.flush();
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        @TargetApi(21)
        public void flush() {
            ForwardingCookieHandler.this.getCookieManager().flush();
        }
    }

    public ForwardingCookieHandler(ReactContext context) {
        this.mContext = context;
    }

    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        String cookies = getCookieManager().getCookie(uri.toString());
        if (TextUtils.isEmpty(cookies)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap(COOKIE_HEADER, Collections.singletonList(cookies));
    }

    public void put(URI uri, Map<String, List<String>> headers) throws IOException {
        String url = uri.toString();
        for (Entry<String, List<String>> entry : headers.entrySet()) {
            String key = (String) entry.getKey();
            if (key != null && isCookieHeader(key)) {
                addCookies(url, (List) entry.getValue());
            }
        }
    }

    public void clearCookies(final com.facebook.react.bridge.Callback callback) {
        if (USES_LEGACY_STORE) {
            new GuardedResultAsyncTask<Boolean>(this.mContext) {
                /* access modifiers changed from: protected */
                public Boolean doInBackgroundGuarded() {
                    ForwardingCookieHandler.this.getCookieManager().removeAllCookie();
                    ForwardingCookieHandler.this.mCookieSaver.onCookiesModified();
                    return Boolean.valueOf(true);
                }

                /* access modifiers changed from: protected */
                public void onPostExecuteGuarded(Boolean result) {
                    callback.invoke(result);
                }
            }.execute(new Void[0]);
        } else {
            clearCookiesAsync(callback);
        }
    }

    private void clearCookiesAsync(final com.facebook.react.bridge.Callback callback) {
        getCookieManager().removeAllCookies(new ValueCallback<Boolean>() {
            public void onReceiveValue(Boolean value) {
                ForwardingCookieHandler.this.mCookieSaver.onCookiesModified();
                callback.invoke(value);
            }
        });
    }

    public void destroy() {
        if (USES_LEGACY_STORE) {
            getCookieManager().removeExpiredCookie();
            this.mCookieSaver.persistCookies();
        }
    }

    private void addCookies(final String url, final List<String> cookies) {
        if (USES_LEGACY_STORE) {
            runInBackground(new Runnable() {
                public void run() {
                    for (String cookie : cookies) {
                        ForwardingCookieHandler.this.getCookieManager().setCookie(url, cookie);
                    }
                    ForwardingCookieHandler.this.mCookieSaver.onCookiesModified();
                }
            });
            return;
        }
        for (String cookie : cookies) {
            addCookieAsync(url, cookie);
        }
        this.mCookieSaver.onCookiesModified();
    }

    @TargetApi(21)
    private void addCookieAsync(String url, String cookie) {
        getCookieManager().setCookie(url, cookie, null);
    }

    private static boolean isCookieHeader(String name) {
        return name.equalsIgnoreCase(VERSION_ZERO_HEADER) || name.equalsIgnoreCase(VERSION_ONE_HEADER);
    }

    /* access modifiers changed from: private */
    public void runInBackground(final Runnable runnable) {
        new GuardedAsyncTask<Void, Void>(this.mContext) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... params) {
                runnable.run();
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public CookieManager getCookieManager() {
        if (this.mCookieManager == null) {
            possiblyWorkaroundSyncManager(this.mContext);
            this.mCookieManager = CookieManager.getInstance();
            if (USES_LEGACY_STORE) {
                this.mCookieManager.removeExpiredCookie();
            }
        }
        return this.mCookieManager;
    }

    private static void possiblyWorkaroundSyncManager(Context context) {
        if (USES_LEGACY_STORE) {
            CookieSyncManager.createInstance(context).sync();
        }
    }
}
