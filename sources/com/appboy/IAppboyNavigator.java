package com.appboy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

public interface IAppboyNavigator {
    void gotoNewsFeed(Context context, Bundle bundle);

    void gotoURI(Context context, Uri uri, Bundle bundle);
}
