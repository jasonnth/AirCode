package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.util.LongSparseArray;
import android.util.Pair;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.utils.listing.CheckinDisplay;
import com.airbnb.android.core.viewcomponents.models.CarouselModel_;
import com.airbnb.android.core.viewcomponents.models.CheckInGuideAddStepButtonEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.CheckInStepCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.p027n2.components.CheckInGuideStepCard.LoadingState;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;
import java.util.List;

public class ManageListingCheckInGuideController extends AirEpoxyController {
    private final Context context;
    /* access modifiers changed from: private */
    public int currentCarouselPosition;
    DocumentMarqueeEpoxyModel_ headerRow;
    private boolean isLoading;
    private final Listener listener;
    EpoxyControllerLoadingModel_ loader;
    private final LongSparseArray<Pair<String, LoadingState>> stepIdToImageLoadingState = new LongSparseArray<>();
    private List<CheckInStep> steps;
    CarouselModel_ stepsCarousel;

    interface Listener {
        void addEmptyStepCard();

        void addNote(int i, long j);

        void addPhoto(int i, long j);

        void editPhoto(int i, long j);

        void editStep(int i, long j);
    }

    public ManageListingCheckInGuideController(Context context2, Listener listener2) {
        this.context = context2;
        this.listener = listener2;
        this.currentCarouselPosition = 0;
    }

    public void setStepCards(List<CheckInStep> steps2) {
        this.steps = steps2;
        requestModelBuild();
    }

    public void setLoading(boolean isLoading2) {
        this.isLoading = isLoading2;
        requestModelBuild();
    }

    public void setImageLoadingForStepId(long stepId, LoadingState state) {
        this.stepIdToImageLoadingState.put(stepId, Pair.create(null, state));
        requestModelBuild();
    }

    public void setImageLoadingForStepId(long stepId, String path, LoadingState state) {
        this.stepIdToImageLoadingState.put(stepId, Pair.create(path, state));
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        if (this.isLoading) {
            this.headerRow.addTo(this);
            this.loader.addTo(this);
            return;
        }
        setupHeader();
        setupStepsCarousel();
    }

    private void setupHeader() {
        boolean firstStepExists = false;
        if (this.steps != null && !this.steps.isEmpty() && ((CheckInStep) this.steps.get(0)).getId() > 0) {
            firstStepExists = true;
        }
        this.headerRow.titleRes(firstStepExists ? C7368R.string.manage_listing_check_in_guide_edit_guide_title : C7368R.string.manage_listing_check_in_guide_create_guide_title).captionRes(C7368R.string.manage_listing_check_in_guide_subtitle).addTo(this);
    }

    private void setupStepsCarousel() {
        this.stepsCarousel.onScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 0 && (recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
                    ManageListingCheckInGuideController.this.currentCarouselPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                }
            }
        }).onBind(ManageListingCheckInGuideController$$Lambda$1.lambdaFactory$(this)).models(getCheckinStepCards()).forCheckInCards(true).addTo(this);
    }

    public int getCurrentStepIndex() {
        return this.currentCarouselPosition;
    }

    private List<AirEpoxyModel<?>> getCheckinStepCards() {
        List<AirEpoxyModel<?>> cards = new ArrayList<>();
        int stepNum = 0;
        for (CheckInStep step : ListUtils.ensureNotNull(this.steps)) {
            Pair<String, LoadingState> imageUpload = (Pair) this.stepIdToImageLoadingState.get(step.getId(), Pair.create(null, LoadingState.None));
            String imageUrl = imageUpload.second == LoadingState.None ? step.getPictureUrl() : (String) imageUpload.first;
            CheckInStepCardEpoxyModel_ stepCard = emptyCardForStepNumber(stepNum, step.getId());
            stepCard.imageUrl(imageUrl).note(TextUtil.compressWhitespace(step.getNote())).imageLoadingState((LoadingState) imageUpload.second);
            cards.add(stepCard);
            stepNum++;
        }
        cards.add(new CheckInGuideAddStepButtonEpoxyModel_().m4437id((CharSequence) "add_step_button").textRes(C7368R.string.manage_listing_check_in_guide_add_another_step_button).clickListener(ManageListingCheckInGuideController$$Lambda$2.lambdaFactory$(this)));
        return cards;
    }

    private CheckInStepCardEpoxyModel_ emptyCardForStepNumber(int stepNum, long stepId) {
        return new CheckInStepCardEpoxyModel_().m4450id((CharSequence) "step_card", stepId != 0 ? stepId : (long) stepNum).displayOptions(DisplayOptions.forCheckInStepCard(this.context)).title(Integer.toString(stepNum + 1)).subtitleRes(CheckinDisplay.getCheckInStepInstructions(stepNum)).buttonTextRes(C7368R.string.manage_listing_check_in_guide_add_photo_button).errorStateTextRes(C7368R.string.manage_listing_check_in_guide_failed_photo_retry_caption).buttonClickListener(ManageListingCheckInGuideController$$Lambda$3.lambdaFactory$(this, stepNum, stepId)).noteClickListener(ManageListingCheckInGuideController$$Lambda$4.lambdaFactory$(this, stepNum, stepId)).imageClickListener(ManageListingCheckInGuideController$$Lambda$5.lambdaFactory$(this, stepNum, stepId)).errorStateClickListener(ManageListingCheckInGuideController$$Lambda$6.lambdaFactory$(this, stepNum, stepId)).notePromptRes(C7368R.string.manage_listing_check_in_guide_add_note_instructions);
    }

    public boolean hasPendingImageUpload(long stepId) {
        Pair<String, LoadingState> imageUpload = (Pair) this.stepIdToImageLoadingState.get(stepId);
        return imageUpload != null && imageUpload.second == LoadingState.Loading;
    }

    public boolean hasFailedImageUpload(long stepId) {
        Pair<String, LoadingState> imageUpload = (Pair) this.stepIdToImageLoadingState.get(stepId);
        return imageUpload != null && imageUpload.second == LoadingState.Failed;
    }
}
