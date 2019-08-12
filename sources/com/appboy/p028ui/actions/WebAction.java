package com.appboy.p028ui.actions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.appboy.p028ui.AppboyWebViewActivity;
import com.appboy.support.AppboyFileUtils;
import java.util.List;

/* renamed from: com.appboy.ui.actions.WebAction */
public final class WebAction implements IAction {
    private final Bundle mExtras;
    private final String mTargetUrl;

    public WebAction(String targetUrl, Bundle extras) {
        this.mTargetUrl = targetUrl;
        this.mExtras = extras;
    }

    public void execute(Context context) {
        Intent intent = new Intent(context, AppboyWebViewActivity.class);
        if (this.mExtras != null) {
            intent.putExtras(this.mExtras);
        }
        intent.putExtra("url", this.mTargetUrl);
        context.startActivity(intent);
    }

    public static List<String> getSupportedSchemes() {
        return AppboyFileUtils.REMOTE_SCHEMES;
    }
}
