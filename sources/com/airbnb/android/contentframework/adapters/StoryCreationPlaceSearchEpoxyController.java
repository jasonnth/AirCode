package com.airbnb.android.contentframework.adapters;

import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.core.models.StoryCreationPlaceTag;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.util.ArrayList;
import java.util.List;

public class StoryCreationPlaceSearchEpoxyController extends TypedAirEpoxyController<List<StoryCreationPlaceTag>> {
    private final Delegate delegate;
    SimpleTextRowEpoxyModel_ emptyResultEpoxyModel;
    private boolean isLoading;
    EpoxyControllerLoadingModel_ loadingRowEpoxyModel;

    public interface Delegate {
        void onPlaceClicked(StoryCreationPlaceTag storyCreationPlaceTag);
    }

    public StoryCreationPlaceSearchEpoxyController(Delegate delegate2) {
        this.delegate = delegate2;
    }

    public void startLoading() {
        this.isLoading = true;
        setData(new ArrayList());
    }

    public void setLoadingResults(List<StoryCreationPlaceTag> placeTags) {
        this.isLoading = false;
        setData(placeTags);
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<StoryCreationPlaceTag> data) {
        if (this.isLoading) {
            this.loadingRowEpoxyModel.addTo(this);
        } else if (data == null || data.isEmpty()) {
            this.emptyResultEpoxyModel.textRes(C5709R.string.story_creation_plage_search_no_result).addTo(this);
        } else {
            for (StoryCreationPlaceTag placeTag : data) {
                new StandardRowEpoxyModel_().m5604id((CharSequence) placeTag.getGooglePlaceId()).title((CharSequence) placeTag.getMainText()).subtitle((CharSequence) placeTag.getSecondaryText()).clickListener(StoryCreationPlaceSearchEpoxyController$$Lambda$1.lambdaFactory$(this, placeTag)).addTo(this);
            }
        }
    }
}
