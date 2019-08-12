package com.airbnb.android.contentframework.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.view.menu.ActionMenuItemView;
import android.support.p002v7.widget.LinearSnapHelper;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindInt;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.ContentFrameworkBindings;
import com.airbnb.android.contentframework.ContentFrameworkComponent;
import com.airbnb.android.contentframework.adapters.StoryCreationImageCarouselController;
import com.airbnb.android.contentframework.adapters.StoryCreationImageCarouselController.Delegate;
import com.airbnb.android.contentframework.controller.StoryPublishController;
import com.airbnb.android.contentframework.data.StoryCreationImage;
import com.airbnb.android.contentframework.data.StoryCreationListingAppendix;
import com.airbnb.android.contentframework.data.StoryPublishArguments;
import com.airbnb.android.contentframework.imagepicker.PhotoMetadataUtils;
import com.airbnb.android.contentframework.imagepicker.StoryCreationImagePickerFragment;
import com.airbnb.android.contentframework.requests.StoryCreationSearchPlaceRequest;
import com.airbnb.android.contentframework.responses.StoryCreationSearchPlaceResponse;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.StoryCreationPlaceTag;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.android.gms.maps.model.LatLng;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.Subscription;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.schedulers.Schedulers;

public class StoryCreationComposerFragment extends AirFragment implements Delegate {
    private static final String ARG_CREATION_APPENDIX = "ARG_CREATION_APPENDIX";
    private static final String ARG_RECOVERY_ERROR_MESSAGE = "ARG_RECOVERY_ERROR_MESSAGE";
    private static final String ARG_RECOVER_ARGUMENTS = "ARG_RECOVER_ARGUMENTS";
    private static final int RC_ADD_PHOTOS = 141;
    private static final int RC_EXIT_CONFIRMATION = 143;
    private static final int RC_TAG_PLACE = 142;
    @State
    StoryCreationListingAppendix appendix;
    @BindView
    AirEditTextView bodyText;
    @BindView
    TextView composerInfoText;
    private StoryCreationImageCarouselController imageCarouselController;
    private View imageViewWithContextMenu;
    private StoryCreationImage imageWithContextMenu;
    @State
    ArrayList<StoryCreationImage> images = new ArrayList<>();
    @BindView
    Carousel imagesCarousel;
    private boolean infoButtonActive;
    @BindView
    AirImageView listingAppendixImage;
    @BindView
    AirImageView listingAppendixInfoButton;
    @BindView
    RatingBar listingAppendixRatingBar;
    @BindView
    TextView listingAppendixSubtitle;
    @BindView
    TextView listingAppendixTitle;
    private ActionMenuItemView menuItemView;
    @State
    LatLng photoLocationLatLng;
    @State
    StoryCreationPlaceTag placeTag;
    @BindView
    View placeTagPill;
    @BindView
    View placeTagRemove;
    @BindView
    AirTextView placeTagText;
    @State
    boolean recoveryErrorMessageShown;
    @BindView
    VerboseScrollView scrollView;
    StoryPublishController storyPublishController;
    private Subscription subscription;
    /* access modifiers changed from: 0000 */
    @State
    public ArrayList<StoryCreationPlaceTag> suggestedPlaceTags = new ArrayList<>();
    final RequestListener<StoryCreationSearchPlaceResponse> suggestedPlaceTagsListener = new C0699RL().onResponse(StoryCreationComposerFragment$$Lambda$2.lambdaFactory$(this)).build();
    private final TextWatcher textWatcher = TextWatcherUtils.create(StoryCreationComposerFragment$$Lambda$1.lambdaFactory$(this));
    @BindInt
    int titleCharLimit;
    @BindView
    AirEditTextView titleText;
    @BindView
    AirTextView titleTextCounter;
    private final TextWatcher titleTextWatcher = TextWatcherUtils.create(new StringTextWatcher() {
        public void textUpdated(String s) {
            if (TextUtils.isEmpty(s)) {
                StoryCreationComposerFragment.this.titleTextCounter.setVisibility(8);
                return;
            }
            int charLeft = Math.max(0, StoryCreationComposerFragment.this.titleCharLimit - s.length());
            StoryCreationComposerFragment.this.titleTextCounter.setText(String.valueOf(charLeft));
            StoryCreationComposerFragment.this.titleTextCounter.setTextColor(ContextCompat.getColor(StoryCreationComposerFragment.this.getContext(), charLeft == 0 ? C5709R.color.n2_rausch : C5709R.color.n2_text_color_muted));
        }
    });
    @BindView
    AirToolbar toolbar;

    public static Intent newIntent(Context context, StoryCreationListingAppendix appendix2) {
        return ((Builder) AutoFragmentActivity.create(context, StoryCreationComposerFragment.class).putParcelable(ARG_CREATION_APPENDIX, appendix2)).build();
    }

    public static Intent newIntentToContinueEdit(Context context, StoryPublishArguments arguments, String message) {
        return ((Builder) ((Builder) AutoFragmentActivity.create(context, StoryCreationComposerFragment.class).putParcelable(ARG_RECOVER_ARGUMENTS, arguments)).putString(ARG_RECOVERY_ERROR_MESSAGE, message)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ContentFrameworkComponent.Builder) ((ContentFrameworkBindings) CoreApplication.instance(getContext()).componentProvider()).contentFrameworkComponentProvider().get()).build().inject(this);
        if (savedInstanceState == null) {
            this.appendix = (StoryCreationListingAppendix) getArguments().getParcelable(ARG_CREATION_APPENDIX);
            if (this.appendix == null) {
                StoryPublishArguments arguments = (StoryPublishArguments) getArguments().getParcelable(ARG_RECOVER_ARGUMENTS);
                this.appendix = arguments.appendix();
                this.images.addAll(arguments.imageList());
                this.placeTag = arguments.placeTag();
            }
        }
        if (this.images.isEmpty()) {
            openPhotoPickerWithPermissionCheck();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5709R.layout.fragment_story_creation_composer_fragment, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        setupViews();
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recoverStateIfNeeded();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getAirActivity().setOnBackPressedListener(StoryCreationComposerFragment$$Lambda$3.lambdaFactory$(this));
        getAirActivity().setOnHomePressedListener(StoryCreationComposerFragment$$Lambda$4.lambdaFactory$(this));
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.subscription != null) {
            this.subscription.unsubscribe();
        }
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard((Activity) getActivity());
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C5709R.C5712menu.composer_menu, menu);
        this.toolbar.post(StoryCreationComposerFragment$$Lambda$5.lambdaFactory$(this));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C5709R.C5711id.publish) {
            return super.onOptionsItemSelected(item);
        }
        KeyboardUtils.dismissSoftKeyboard(getContext(), getView());
        try {
            StoryPublishArguments arguments = StoryPublishArguments.newInstance(TextUtil.getFieldTextTrimmed(this.titleText.getText()), TextUtil.getFieldTextTrimmed(this.bodyText.getText()), this.images, this.appendix, this.placeTag);
            this.storyPublishController.publish(arguments);
            ContentFrameworkAnalytics.trackPublishButtonClicked(arguments);
            Toast.makeText(getContext(), C5709R.string.story_creation_composer_publish_in_background, 1).show();
            getActivity().setResult(-1);
            getActivity().finish();
            return true;
        } catch (IllegalArgumentException e) {
            Toast.makeText(getContext(), C5709R.string.story_creation_composer_publish_incomplete, 1).show();
            return true;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 141:
                    this.subscription = Observable.fromCallable(StoryCreationComposerFragment$$Lambda$6.lambdaFactory$(this, data)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(StoryCreationComposerFragment$$Lambda$7.lambdaFactory$(this));
                    return;
                case 142:
                    this.placeTag = (StoryCreationPlaceTag) data.getParcelableExtra(StoryCreationPlaceTaggingFragment.EXTRA_RESULT_PLACE_TAG);
                    updatePlaceTagPill();
                    return;
                case 143:
                    getActivity().finish();
                    return;
                default:
                    return;
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        StoryCreationComposerFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /* access modifiers changed from: 0000 */
    public void openPhotoPicker() {
        ArrayList<Uri> selectedImageUris = new ArrayList<>(this.images.size());
        Iterator it = this.images.iterator();
        while (it.hasNext()) {
            selectedImageUris.add(((StoryCreationImage) it.next()).uri());
        }
        startActivityForResult(StoryCreationImagePickerFragment.intent(getContext(), selectedImageUris), 141);
    }

    /* access modifiers changed from: 0000 */
    public void onPermissionDenied() {
    }

    /* access modifiers changed from: 0000 */
    public void onPermissionNeverAskAgain() {
        Toast.makeText(getContext(), C5709R.string.story_creation_composer_permission_needed, 1).show();
        getActivity().finish();
    }

    public void onAddPhotoClicked() {
        ContentFrameworkAnalytics.trackAddImage(this.images.size());
        openPhotoPickerWithPermissionCheck();
    }

    private void setupViews() {
        boolean z = true;
        this.imageCarouselController = new StoryCreationImageCarouselController(this);
        this.imagesCarousel.setAdapter(this.imageCarouselController.getAdapter());
        new LinearSnapHelper().attachToRecyclerView(this.imagesCarousel);
        updateImageCarousel(-1);
        this.placeTagPill.setOnClickListener(StoryCreationComposerFragment$$Lambda$8.lambdaFactory$(this));
        this.placeTagRemove.setOnClickListener(StoryCreationComposerFragment$$Lambda$9.lambdaFactory$(this));
        updatePlaceTagPill();
        this.listingAppendixImage.setImageUrl(this.appendix.thumbnailUrl());
        this.listingAppendixTitle.setText(this.appendix.title());
        ViewLibUtils.setVisibleIf(this.listingAppendixSubtitle, !TextUtils.isEmpty(this.appendix.subtitle()));
        this.listingAppendixSubtitle.setText(this.appendix.subtitle());
        RatingBar ratingBar = this.listingAppendixRatingBar;
        if (this.appendix.rating() <= 0.0f) {
            z = false;
        }
        ViewLibUtils.setVisibleIf(ratingBar, z);
        this.listingAppendixRatingBar.setRating(this.appendix.rating());
        this.listingAppendixInfoButton.setOnClickListener(StoryCreationComposerFragment$$Lambda$10.lambdaFactory$(this));
        this.titleText.setOnEditorActionListener(StoryCreationComposerFragment$$Lambda$11.lambdaFactory$(this));
        this.titleText.addTextChangedListener(this.textWatcher);
        this.titleText.addTextChangedListener(this.titleTextWatcher);
        this.titleTextCounter.setText(String.valueOf(this.titleCharLimit));
        this.bodyText.addTextChangedListener(this.textWatcher);
    }

    static /* synthetic */ void lambda$setupViews$3(StoryCreationComposerFragment storyCreationComposerFragment, View v) {
        ContentFrameworkAnalytics.trackPlageTagPillClicked();
        storyCreationComposerFragment.startActivityForResult(StoryCreationPlaceTaggingFragment.newIntent(storyCreationComposerFragment.getContext(), storyCreationComposerFragment.suggestedPlaceTags, storyCreationComposerFragment.photoLocationLatLng), 142);
    }

    static /* synthetic */ void lambda$setupViews$4(StoryCreationComposerFragment storyCreationComposerFragment, View v) {
        ContentFrameworkAnalytics.trackPlageTagCleared(storyCreationComposerFragment.placeTag.getGooglePlaceId());
        storyCreationComposerFragment.placeTag = null;
        storyCreationComposerFragment.updatePlaceTagPill();
    }

    static /* synthetic */ void lambda$setupViews$5(StoryCreationComposerFragment storyCreationComposerFragment, View v) {
        boolean z;
        boolean z2 = true;
        ContentFrameworkAnalytics.trackComposerInfoButtonClicked();
        if (!storyCreationComposerFragment.infoButtonActive) {
            z = true;
        } else {
            z = false;
        }
        storyCreationComposerFragment.infoButtonActive = z;
        storyCreationComposerFragment.listingAppendixInfoButton.setImageDrawableCompat(storyCreationComposerFragment.infoButtonActive ? C5709R.C5710drawable.ic_questioncircle_active : C5709R.C5710drawable.ic_questioncircle_inactive);
        TextView textView = storyCreationComposerFragment.composerInfoText;
        if (ViewLibUtils.isVisible(storyCreationComposerFragment.composerInfoText)) {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(textView, z2);
    }

    static /* synthetic */ boolean lambda$setupViews$6(StoryCreationComposerFragment storyCreationComposerFragment, TextView v, int actionId, KeyEvent event) {
        if (!KeyboardUtils.isEnterOrDone(actionId, event)) {
            return false;
        }
        storyCreationComposerFragment.bodyText.requestFocus();
        return true;
    }

    private void recoverStateIfNeeded() {
        StoryPublishArguments arguments = (StoryPublishArguments) getArguments().getParcelable(ARG_RECOVER_ARGUMENTS);
        if (arguments != null) {
            this.titleText.setText(arguments.title());
            this.bodyText.setText(arguments.body());
        }
        String message = getArguments().getString(ARG_RECOVERY_ERROR_MESSAGE);
        if (!this.recoveryErrorMessageShown && !TextUtils.isEmpty(message)) {
            this.recoveryErrorMessageShown = true;
            new SnackbarWrapper().view(getView()).title(C5709R.string.story_creation_composer_publish_error_title, true).body(message).duration(0).buildAndShow();
        }
    }

    private void updatePlaceTagPill() {
        if (this.placeTag == null) {
            this.placeTagPill.setSelected(false);
            this.placeTagText.setText(C5709R.string.story_creation_plage_tagging_text);
            this.placeTagRemove.setVisibility(8);
            return;
        }
        this.placeTagPill.setSelected(true);
        this.placeTagText.setText(this.placeTag.getMainText());
        this.placeTagRemove.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void onSelectedPhotosProcessed(List<StoryCreationImage> imageList) {
        int scrollToPosition = this.images.size();
        this.images.clear();
        this.images.addAll(imageList);
        updateImageCarousel(scrollToPosition);
    }

    private void updateImageCarousel(int scrollToPosition) {
        this.imageCarouselController.setData(this.images);
        this.imagesCarousel.post(StoryCreationComposerFragment$$Lambda$12.lambdaFactory$(this, scrollToPosition));
        updateActionMenuState();
    }

    static /* synthetic */ void lambda$updateImageCarousel$7(StoryCreationComposerFragment storyCreationComposerFragment, int scrollToPosition) {
        if (scrollToPosition >= 0 && scrollToPosition < storyCreationComposerFragment.imagesCarousel.getAdapter().getItemCount()) {
            storyCreationComposerFragment.imagesCarousel.scrollToPosition(scrollToPosition);
        }
    }

    /* access modifiers changed from: private */
    public List<StoryCreationImage> processSelectedImages(List<Uri> selectedPhotos) {
        List<StoryCreationImage> result = new ArrayList<>();
        if (selectedPhotos != null && !selectedPhotos.isEmpty()) {
            this.photoLocationLatLng = null;
            for (Uri uri : selectedPhotos) {
                StoryCreationImage image = StoryCreationImage.fromUri(getContext(), uri);
                result.add(image);
                if (this.photoLocationLatLng == null) {
                    this.photoLocationLatLng = PhotoMetadataUtils.getExifLatLng(image.filePath());
                    if (Trebuchet.launch(TrebuchetKeys.ENABLE_PLACE_TAGGING_SUGGESTIONS_IN_COMPOSER)) {
                        StoryCreationSearchPlaceRequest.forLatLng(this.photoLocationLatLng).withListener((Observer) this.suggestedPlaceTagsListener).execute(this.requestManager);
                    }
                }
            }
        }
        return result;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (this.imageWithContextMenu == null || this.imageViewWithContextMenu != v) {
            this.imageWithContextMenu = null;
            this.imageViewWithContextMenu = null;
            return;
        }
        getActivity().getMenuInflater().inflate(C5709R.C5712menu.creation_image_menu, menu);
        ContentFrameworkAnalytics.trackImageOptionClicked();
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() != C5709R.C5711id.delete) {
            return false;
        }
        ContentFrameworkAnalytics.trackImageDeleted();
        int scrollToPosition = this.images.indexOf(this.imageWithContextMenu);
        this.images.remove(scrollToPosition);
        this.imageWithContextMenu = null;
        this.imageViewWithContextMenu = null;
        updateImageCarousel(scrollToPosition);
        return true;
    }

    public void onImageSelected(StoryCreationImage image, View v) {
        this.imageWithContextMenu = image;
        this.imageViewWithContextMenu = v;
        v.showContextMenu();
    }

    /* access modifiers changed from: private */
    public boolean confirmExit() {
        ZenDialog.builder().withBodyText(C5709R.string.story_creation_composer_exit_confirmation).withDualButton(C5709R.string.cancel, 0, C5709R.string.confirm, 143).create().show(getFragmentManager(), (String) null);
        return true;
    }

    private void openPhotoPickerWithPermissionCheck() {
        StoryCreationComposerFragmentPermissionsDispatcher.openPhotoPickerWithCheck(this);
    }

    private boolean readyToPublish() {
        return !this.images.isEmpty() && !TextUtils.isEmpty(TextUtil.getFieldTextTrimmed(this.titleText.getText())) && !TextUtils.isEmpty(TextUtil.getFieldTextTrimmed(this.bodyText.getText()));
    }

    /* access modifiers changed from: private */
    public void updateActionMenuState() {
        boolean enableMenuItem = readyToPublish();
        if (this.menuItemView == null) {
            this.menuItemView = MiscUtils.getActionMenuItemView(this.toolbar);
        }
        if (this.menuItemView != null) {
            this.menuItemView.setTextColor(ContextCompat.getColor(getContext(), enableMenuItem ? C5709R.color.n2_babu : C5709R.color.n2_babu_30));
            this.menuItemView.setEnabled(enableMenuItem);
        }
    }
}
