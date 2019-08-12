package com.airbnb.android.core.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.Locale;

public final class TranslationUtils {
    private TranslationUtils() {
    }

    public static Spannable getTranslateLink(Context context, String originalLanguage, boolean isTranslated, int color, final OnClickListener clickListener) {
        String string;
        if (originalLanguage != null && originalLanguage.toLowerCase().equals("zh-tw")) {
            originalLanguage = AirbnbConstants.LANGUAGE_CODE_CHINESE;
        }
        Locale reviewLocale = new Locale(originalLanguage);
        StringBuilder sb = new StringBuilder();
        if (isTranslated) {
            string = context.getString(C0716R.string.see_original_language, new Object[]{reviewLocale.getDisplayLanguage(reviewLocale)});
        } else {
            string = context.getString(C0716R.string.translate);
        }
        String translateText = sb.append(string).append(" ").toString();
        Spannable translateSpan = TextUtil.makeColored(context, color, translateText, translateText);
        translateSpan.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                clickListener.onClick(view);
            }

            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, 0, translateSpan.length(), 33);
        return translateSpan;
    }
}
