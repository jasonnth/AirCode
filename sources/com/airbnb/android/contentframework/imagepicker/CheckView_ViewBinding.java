package com.airbnb.android.contentframework.imagepicker;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.android.contentframework.C5709R;

public class CheckView_ViewBinding implements Unbinder {
    public CheckView_ViewBinding(CheckView target) {
        this(target, target.getContext());
    }

    @Deprecated
    public CheckView_ViewBinding(CheckView target, View source) {
        this(target, source.getContext());
    }

    public CheckView_ViewBinding(CheckView target, Context context) {
        Resources res = context.getResources();
        target.textSize = res.getDimensionPixelSize(C5709R.dimen.story_creation_media_grid_check_view_text_size);
        target.checkViewSizePx = res.getDimension(C5709R.dimen.story_creation_media_grid_check_view_size);
        target.strokeWidthPx = res.getDimension(C5709R.dimen.story_creation_media_grid_check_view_border_width);
    }

    public void unbind() {
    }
}
