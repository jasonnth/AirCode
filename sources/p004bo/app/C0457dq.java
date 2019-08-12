package p004bo.app;

import android.net.Uri;
import com.appboy.support.AppboyLogger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/* renamed from: bo.app.dq */
public final class C0457dq {

    /* renamed from: a */
    private static final String f384a = AppboyLogger.getAppboyLogTag(C0457dq.class);

    /* renamed from: a */
    public static URI m524a(Uri uri) {
        try {
            return new URI(uri.toString());
        } catch (URISyntaxException e) {
            AppboyLogger.m1735e(f384a, String.format("Could not create URI from uri [%s]", new Object[]{uri.toString()}));
            return null;
        }
    }

    /* renamed from: a */
    public static URL m525a(URI uri) {
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            AppboyLogger.m1735e(f384a, String.format("Unable to parse URI [%s]", new Object[]{e.getMessage()}));
            return null;
        }
    }
}
