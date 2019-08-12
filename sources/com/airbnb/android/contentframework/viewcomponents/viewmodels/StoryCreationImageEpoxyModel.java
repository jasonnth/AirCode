package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnCreateContextMenuListener;
import com.airbnb.android.contentframework.data.StoryCreationImage;
import com.airbnb.android.contentframework.views.StoryCreationImageViewWrapper;
import com.airbnb.android.contentframework.views.StoryCreationImageViewWrapper.OnOptionsSelectedListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class StoryCreationImageEpoxyModel extends AirEpoxyModel<StoryCreationImageViewWrapper> {
    StoryCreationImage image;
    OnCreateContextMenuListener onCreateContextMenuListener;
    OnOptionsSelectedListener onOptionsSelectedListener;

    public void bind(StoryCreationImageViewWrapper view) {
        super.bind(view);
        view.loadImage(this.image);
        view.setOnOptionsSelectedListener(this.onOptionsSelectedListener);
        view.setOnCreateContextMenuListener(this.onCreateContextMenuListener);
    }
}
