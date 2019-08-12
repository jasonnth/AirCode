package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.RefreshLoader_ViewBinding */
public final class RefreshLoader_ViewBinding implements Unbinder {
    private RefreshLoader target;

    public RefreshLoader_ViewBinding(RefreshLoader target2, View source) {
        this.target = target2;
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loading_view, "field 'loadingView'", LoadingView.class);
    }

    public void unbind() {
        RefreshLoader target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loadingView = null;
    }
}
