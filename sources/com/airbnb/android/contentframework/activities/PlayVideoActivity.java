package com.airbnb.android.contentframework.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.fragments.PlayVideoFragment;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.MiscUtils;

public final class PlayVideoActivity extends SolitAirActivity {
    public static Intent intentForVideoUrl(Context context, String videoUrl) {
        return intentForBundle(context, PlayVideoActivity.class, PlayVideoFragment.bundleWithVideoUrl(videoUrl));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle;
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(MiscUtils.getImmersiveModeFlags());
        this.mLoaderFrame.setBackgroundColor(getResources().getColor(C5709R.color.black));
        showLoader(true);
        if (savedInstanceState == null) {
            if (DeepLinkUtils.isDeepLink(getIntent())) {
                bundle = PlayVideoFragment.bundleWithVideoUrl(DeepLinkUtils.getParamAsString(getIntent(), "url_mp4", "url"));
            } else {
                bundle = getBundleFromIntent();
            }
            showFragment(PlayVideoFragment.newInstance(bundle), false);
        }
    }
}
