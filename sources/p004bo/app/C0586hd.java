package p004bo.app;

import com.facebook.common.util.UriUtil;
import java.io.InputStream;
import java.util.Locale;

/* renamed from: bo.app.hd */
public interface C0586hd {

    /* renamed from: bo.app.hd$a */
    public enum C0587a {
        HTTP(UriUtil.HTTP_SCHEME),
        HTTPS(UriUtil.HTTPS_SCHEME),
        FILE(UriUtil.LOCAL_FILE_SCHEME),
        CONTENT("content"),
        ASSETS("assets"),
        DRAWABLE("drawable"),
        UNKNOWN("");
        

        /* renamed from: h */
        private String f799h;

        /* renamed from: i */
        private String f800i;

        private C0587a(String str) {
            this.f799h = str;
            this.f800i = str + "://";
        }

        /* renamed from: a */
        public static C0587a m1009a(String str) {
            C0587a[] values;
            if (str != null) {
                for (C0587a aVar : values()) {
                    if (aVar.m1010d(str)) {
                        return aVar;
                    }
                }
            }
            return UNKNOWN;
        }

        /* renamed from: d */
        private boolean m1010d(String str) {
            return str.toLowerCase(Locale.US).startsWith(this.f800i);
        }

        /* renamed from: b */
        public String mo7257b(String str) {
            return this.f800i + str;
        }

        /* renamed from: c */
        public String mo7258c(String str) {
            if (m1010d(str)) {
                return str.substring(this.f800i.length());
            }
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{str, this.f799h}));
        }
    }

    /* renamed from: a */
    InputStream mo7152a(String str, Object obj);
}
