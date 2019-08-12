package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.views.StoryCreationAddPhotoView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class StoryCreationAddPhotoEpoxyModel extends AirEpoxyModel<StoryCreationAddPhotoView> {
    OnClickListener onClickListener;

    public void bind(StoryCreationAddPhotoView view) {
        super.bind(view);
        view.setOnClickListener(this.onClickListener);
    }
}
