package com.appboy.p028ui.feed;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageSwitcher;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.feed.AppboyImageSwitcher */
public class AppboyImageSwitcher extends ImageSwitcher {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyImageSwitcher.class.getName()});
    private Drawable mReadIcon;
    private Drawable mUnReadIcon;

    public AppboyImageSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.com_appboy_ui_feed_AppboyImageSwitcher);
            for (int i = 0; i < typedArray.getIndexCount(); i++) {
                int offset = typedArray.getIndex(i);
                if (offset == R.styleable.com_appboy_ui_feed_AppboyImageSwitcher_appboyFeedCustomReadIcon) {
                    Drawable drawable = typedArray.getDrawable(offset);
                    if (drawable != null) {
                        this.mReadIcon = drawable;
                    }
                } else if (typedArray.getIndex(i) == R.styleable.com_appboy_ui_feed_AppboyImageSwitcher_appboyFeedCustomUnReadIcon) {
                    Drawable drawable2 = typedArray.getDrawable(offset);
                    if (drawable2 != null) {
                        this.mUnReadIcon = drawable2;
                    }
                }
            }
            typedArray.recycle();
        } catch (Exception e) {
            AppboyLogger.m1740w(TAG, "Error while checking for custom drawable.", e);
        }
    }

    public Drawable getUnReadIcon() {
        return this.mUnReadIcon;
    }

    public Drawable getReadIcon() {
        return this.mReadIcon;
    }

    public void setUnReadIcon(Drawable unReadIcon) {
        this.mUnReadIcon = unReadIcon;
    }

    public void setReadIcon(Drawable readIcon) {
        this.mReadIcon = readIcon;
    }
}
