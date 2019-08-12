package android.support.p000v4.text;

import android.annotation.TargetApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

@TargetApi(23)
/* renamed from: android.support.v4.text.ICUCompatApi23 */
class ICUCompatApi23 {
    private static Method sAddLikelySubtagsMethod;

    static {
        try {
            sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[]{Locale.class});
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static String maximizeAndGetScript(Locale locale) {
        try {
            return ((Locale) sAddLikelySubtagsMethod.invoke(null, new Object[]{locale})).getScript();
        } catch (InvocationTargetException e) {
            Log.w("ICUCompatIcs", e);
        } catch (IllegalAccessException e2) {
            Log.w("ICUCompatIcs", e2);
        }
        return locale.getScript();
    }
}
