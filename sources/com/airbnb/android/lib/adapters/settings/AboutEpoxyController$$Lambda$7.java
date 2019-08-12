package com.airbnb.android.lib.adapters.settings;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Toast;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.BuildHelper;

final /* synthetic */ class AboutEpoxyController$$Lambda$7 implements OnLongClickListener {
    private static final AboutEpoxyController$$Lambda$7 instance = new AboutEpoxyController$$Lambda$7();

    private AboutEpoxyController$$Lambda$7() {
    }

    public static OnLongClickListener lambdaFactory$() {
        return instance;
    }

    public boolean onLongClick(View view) {
        return Toast.makeText(CoreApplication.appContext(), BuildHelper.chinaInstallTag(), 1).show();
    }
}
