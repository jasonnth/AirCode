package com.airbnb.android.contentframework.adapters;

import android.view.View.OnCreateContextMenuListener;
import com.airbnb.android.contentframework.data.StoryCreationImage;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCreationAddPhotoEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCreationImageEpoxyModel_;
import com.airbnb.android.contentframework.views.StoryCreationImageViewWrapper.OnOptionsSelectedListener;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.util.List;

public class StoryCreationImageCarouselController extends TypedAirEpoxyController<List<StoryCreationImage>> {
    private static final int MAX_PHOTO_COUNT = 4;
    StoryCreationAddPhotoEpoxyModel_ addPhotoEpoxyModel;
    private final Delegate delegate;

    public interface Delegate extends OnCreateContextMenuListener, OnOptionsSelectedListener {
        void onAddPhotoClicked();
    }

    public StoryCreationImageCarouselController(Delegate delegate2) {
        this.delegate = delegate2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<StoryCreationImage> data) {
        for (StoryCreationImage image : data) {
            new StoryCreationImageEpoxyModel_().m4166id((CharSequence) image.uri().toString()).image(image).onCreateContextMenuListener(this.delegate).onOptionsSelectedListener(this.delegate).addTo(this);
        }
        this.addPhotoEpoxyModel.onClickListener(StoryCreationImageCarouselController$$Lambda$1.lambdaFactory$(this)).addIf(data.size() < 4, (EpoxyController) this);
    }
}
