package p004bo.app;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Video.Thumbnails;
import android.webkit.MimeTypeMap;
import com.miteksystems.misnap.params.SDKConstants;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import p004bo.app.C0586hd.C0587a;

/* renamed from: bo.app.hc */
public class C0584hc implements C0586hd {

    /* renamed from: a */
    protected final Context f787a;

    /* renamed from: b */
    protected final int f788b;

    /* renamed from: c */
    protected final int f789c;

    public C0584hc(Context context) {
        this(context, 5000, SDKConstants.CAM_INIT_CAMERA);
    }

    public C0584hc(Context context, int i, int i2) {
        this.f787a = context.getApplicationContext();
        this.f788b = i;
        this.f789c = i2;
    }

    /* renamed from: a */
    public InputStream mo7152a(String str, Object obj) {
        switch (C0587a.m1009a(str)) {
            case HTTP:
            case HTTPS:
                return mo7250b(str, obj);
            case FILE:
                return mo7252d(str, obj);
            case CONTENT:
                return mo7253e(str, obj);
            case ASSETS:
                return mo7254f(str, obj);
            case DRAWABLE:
                return mo7255g(str, obj);
            default:
                return mo7256h(str, obj);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public InputStream mo7250b(String str, Object obj) {
        HttpURLConnection c = mo7251c(str, obj);
        int i = 0;
        while (c.getResponseCode() / 100 == 3 && i < 5) {
            c = mo7251c(c.getHeaderField("Location"), obj);
            i++;
        }
        try {
            InputStream inputStream = c.getInputStream();
            if (mo7249a(c)) {
                return new C0558gl(new BufferedInputStream(inputStream, 32768), c.getContentLength());
            }
            C0597hm.m1054a((Closeable) inputStream);
            throw new IOException("Image request failed with response code " + c.getResponseCode());
        } catch (IOException e) {
            C0597hm.m1055a(c.getErrorStream());
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo7249a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getResponseCode() == 200;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public HttpURLConnection mo7251c(String str, Object obj) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        httpURLConnection.setConnectTimeout(this.f788b);
        httpURLConnection.setReadTimeout(this.f789c);
        return httpURLConnection;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public InputStream mo7252d(String str, Object obj) {
        String c = C0587a.FILE.mo7258c(str);
        if (m997b(str)) {
            return m995a(c);
        }
        return new C0558gl(new BufferedInputStream(new FileInputStream(c), 32768), (int) new File(c).length());
    }

    @TargetApi(8)
    /* renamed from: a */
    private InputStream m995a(String str) {
        if (VERSION.SDK_INT >= 8) {
            Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 2);
            if (createVideoThumbnail != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createVideoThumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public InputStream mo7253e(String str, Object obj) {
        ContentResolver contentResolver = this.f787a.getContentResolver();
        Uri parse = Uri.parse(str);
        if (m996b(parse)) {
            Bitmap thumbnail = Thumbnails.getThumbnail(contentResolver, Long.valueOf(parse.getLastPathSegment()).longValue(), 1, null);
            if (thumbnail != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        } else if (str.startsWith("content://com.android.contacts/")) {
            return mo7248a(parse);
        }
        return contentResolver.openInputStream(parse);
    }

    /* access modifiers changed from: protected */
    @TargetApi(14)
    /* renamed from: a */
    public InputStream mo7248a(Uri uri) {
        ContentResolver contentResolver = this.f787a.getContentResolver();
        if (VERSION.SDK_INT >= 14) {
            return Contacts.openContactPhotoInputStream(contentResolver, uri, true);
        }
        return Contacts.openContactPhotoInputStream(contentResolver, uri);
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public InputStream mo7254f(String str, Object obj) {
        return this.f787a.getAssets().open(C0587a.ASSETS.mo7258c(str));
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public InputStream mo7255g(String str, Object obj) {
        return this.f787a.getResources().openRawResource(Integer.parseInt(C0587a.DRAWABLE.mo7258c(str)));
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public InputStream mo7256h(String str, Object obj) {
        throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", new Object[]{str}));
    }

    /* renamed from: b */
    private boolean m996b(Uri uri) {
        String type = this.f787a.getContentResolver().getType(uri);
        return type != null && type.startsWith("video/");
    }

    /* renamed from: b */
    private boolean m997b(String str) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
        return mimeTypeFromExtension != null && mimeTypeFromExtension.startsWith("video/");
    }
}
