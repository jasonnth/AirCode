package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.facebook.common.util.UriUtil;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class AccountKitCookieStore implements CookieStore {
    private static final List<String> ALLOW_PERSIST_COOKIE_NAMES = new ArrayList();
    private static final List<String> ALLOW_PERSIST_DOMAINS = new ArrayList();
    private static final String SP_COOKIE_STORE = "cookieStore";
    private static final String SP_KEY_DELIMITER = "|";
    private static final String SP_KEY_DELIMITER_REGEX = "\\|";
    private final Map<URI, List<HttpCookie>> map = new HashMap();
    private final SharedPreferences sharedPreferences;

    static {
        ALLOW_PERSIST_DOMAINS.add(".accountkit.com");
        ALLOW_PERSIST_COOKIE_NAMES.add("aksb");
    }

    public AccountKitCookieStore(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SP_COOKIE_STORE, 0);
        loadFromSharedPreferences();
    }

    private void loadFromSharedPreferences() {
        for (Entry<String, ?> entry : this.sharedPreferences.getAll().entrySet()) {
            try {
                URI uri = new URI(((String) entry.getKey()).split(SP_KEY_DELIMITER_REGEX, 2)[0]);
                HttpCookie cookie = new SerializableHttpCookie().decode((String) entry.getValue());
                if (cookie != null) {
                    List<HttpCookie> targetCookies = (List) this.map.get(uri);
                    if (targetCookies == null) {
                        targetCookies = new ArrayList<>();
                        this.map.put(uri, targetCookies);
                    }
                    targetCookies.add(cookie);
                }
            } catch (URISyntaxException e) {
            }
        }
    }

    private void saveToSharedPreferences(URI uri, HttpCookie cookie) {
        if (ALLOW_PERSIST_DOMAINS.contains(cookie.getDomain()) && ALLOW_PERSIST_COOKIE_NAMES.contains(cookie.getName())) {
            Editor editor = this.sharedPreferences.edit();
            editor.putString(uri.toString() + SP_KEY_DELIMITER + cookie.getName(), new SerializableHttpCookie().encode(cookie));
            editor.apply();
        }
    }

    public synchronized void add(URI uri, HttpCookie cookie) {
        if (cookie == null) {
            throw new NullPointerException("cookie == null");
        }
        URI uri2 = cookiesUri(uri);
        List<HttpCookie> cookies = (List) this.map.get(uri2);
        if (cookies == null) {
            cookies = new ArrayList<>();
            this.map.put(uri2, cookies);
        } else {
            cookies.remove(cookie);
        }
        cookies.add(cookie);
        saveToSharedPreferences(uri2, cookie);
    }

    private URI cookiesUri(URI uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URI(UriUtil.HTTP_SCHEME, uri.getHost(), null, null);
        } catch (URISyntaxException e) {
            return uri;
        }
    }

    public synchronized List<HttpCookie> get(URI uri) {
        List<HttpCookie> result;
        if (uri == null) {
            throw new NullPointerException("uri == null");
        }
        result = new ArrayList<>();
        List<HttpCookie> cookiesForUri = (List) this.map.get(uri);
        if (cookiesForUri != null) {
            Iterator<HttpCookie> i = cookiesForUri.iterator();
            while (i.hasNext()) {
                HttpCookie cookie = (HttpCookie) i.next();
                if (cookie.hasExpired()) {
                    i.remove();
                } else {
                    result.add(cookie);
                }
            }
        }
        for (Entry<URI, List<HttpCookie>> entry : this.map.entrySet()) {
            if (!uri.equals(entry.getKey())) {
                Iterator<HttpCookie> i2 = ((List) entry.getValue()).iterator();
                while (i2.hasNext()) {
                    HttpCookie cookie2 = (HttpCookie) i2.next();
                    if (HttpCookie.domainMatches(cookie2.getDomain(), uri.getHost())) {
                        if (cookie2.hasExpired()) {
                            i2.remove();
                        } else if (!result.contains(cookie2)) {
                            result.add(cookie2);
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(result);
    }

    public synchronized List<HttpCookie> getCookies() {
        List<HttpCookie> result;
        result = new ArrayList<>();
        for (List<HttpCookie> list : this.map.values()) {
            Iterator<HttpCookie> i = list.iterator();
            while (i.hasNext()) {
                HttpCookie cookie = (HttpCookie) i.next();
                if (cookie.hasExpired()) {
                    i.remove();
                } else if (!result.contains(cookie)) {
                    result.add(cookie);
                }
            }
        }
        return Collections.unmodifiableList(result);
    }

    public synchronized List<URI> getURIs() {
        List<URI> result;
        result = new ArrayList<>(this.map.keySet());
        result.remove(null);
        return Collections.unmodifiableList(result);
    }

    public synchronized boolean remove(URI uri, HttpCookie cookie) {
        boolean z;
        if (cookie == null) {
            throw new NullPointerException("cookie == null");
        }
        List<HttpCookie> cookies = (List) this.map.get(cookiesUri(uri));
        if (cookies != null) {
            z = cookies.remove(cookie);
        } else {
            z = false;
        }
        return z;
    }

    public synchronized boolean removeAll() {
        boolean result;
        result = !this.map.isEmpty();
        this.map.clear();
        return result;
    }
}
