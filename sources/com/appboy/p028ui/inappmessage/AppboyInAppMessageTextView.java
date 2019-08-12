package com.appboy.p028ui.inappmessage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageTextView */
public class AppboyInAppMessageTextView extends TextView {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyInAppMessageTextView.class.getName()});

    public AppboyInAppMessageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.com_appboy_ui_inappmessage_AppboyInAppMessageTextView);
            for (int i = 0; i < typedArray.getIndexCount(); i++) {
                int offset = typedArray.getIndex(i);
                if (offset == R.styleable.com_appboy_ui_inappmessage_AppboyInAppMessageTextView_appboyInAppMessageCustomFontFile) {
                    String fontFile = typedArray.getString(offset);
                    try {
                        setTypeface(Typeface.createFromAsset(context.getAssets(), fontFile));
                    } catch (Exception e) {
                        AppboyLogger.m1740w(TAG, "Error loading custom typeface from: " + fontFile, e);
                    }
                }
            }
            typedArray.recycle();
        } catch (Exception e2) {
            AppboyLogger.m1740w(TAG, "Error while checking for custom typeface.", e2);
        }
    }
}
