package com.appboy.p028ui.feed;

import com.appboy.p028ui.feed.listeners.AppboyDefaultFeedClickActionListener;
import com.appboy.p028ui.feed.listeners.IFeedClickActionListener;

/* renamed from: com.appboy.ui.feed.AppboyFeedManager */
public final class AppboyFeedManager {
    private static volatile AppboyFeedManager sInstance = null;
    private IFeedClickActionListener mCustomFeedCardClickActionListener;
    private IFeedClickActionListener mDefaultFeedCardClickActionListener = new AppboyDefaultFeedClickActionListener();

    public static AppboyFeedManager getInstance() {
        if (sInstance == null) {
            synchronized (AppboyFeedManager.class) {
                if (sInstance == null) {
                    sInstance = new AppboyFeedManager();
                }
            }
        }
        return sInstance;
    }

    public IFeedClickActionListener getFeedCardClickActionListener() {
        return this.mCustomFeedCardClickActionListener != null ? this.mCustomFeedCardClickActionListener : this.mDefaultFeedCardClickActionListener;
    }
}
