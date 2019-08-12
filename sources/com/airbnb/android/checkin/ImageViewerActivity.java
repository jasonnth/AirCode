package com.airbnb.android.checkin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.checkin.data.CheckInComponent.Builder;
import com.airbnb.android.checkin.data.CheckInDataBindings;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.image_viewer.ImageViewer;
import com.airbnb.p027n2.components.image_viewer.ImageViewer.OnViewDragCallback;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageViewerActivity extends AirActivity implements OnViewDragCallback {
    private static final String ARG_IMAGES = "arg_images";
    private static final String ARG_LISTING_ID = "arg_listing_id";
    public static final String ARG_RETURN_CURRENT_URL = "arg_image_last_position_url";
    private static final String ARG_SELECTION_INDEX = "arg_selection_index";
    public static final int REQUEST_CODE = 1115;
    @BindView
    View background;
    /* access modifiers changed from: private */
    public String currentUrl;
    @BindView
    ImageViewer imageViewer;
    GuestCheckInJitneyLogger jitneyLogger;
    private long listingId;
    @BindView
    AirToolbar toolbar;

    public static Intent newIntent(Context context, List<String> imageUris, int initialIndex, long listingId2) {
        Check.argument(initialIndex < imageUris.size() && initialIndex >= 0);
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra(ARG_SELECTION_INDEX, initialIndex);
        intent.putStringArrayListExtra(ARG_IMAGES, new ArrayList(imageUris));
        intent.putExtra("arg_listing_id", listingId2);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n2_activity_image_viewer);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        ((Builder) ((CheckInDataBindings) CoreApplication.instance().componentProvider()).checkInComponentProvider().get()).build().inject(this);
        Bundle extras = getIntent().getExtras();
        this.listingId = extras.getLong("arg_listing_id");
        List<String> urls = extras.getStringArrayList(ARG_IMAGES);
        if (ListUtils.isEmpty((Collection<?>) urls)) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("The list of images for ImageViewer is empty!"));
            finish();
            return;
        }
        this.imageViewer.setData("", 0, FluentIterable.from((Iterable<E>) urls).transform(ImageViewerActivity$$Lambda$1.lambdaFactory$()).toList());
        this.imageViewer.scrollToPosition(extras.getInt(ARG_SELECTION_INDEX));
        this.imageViewer.setSnapToPositionListener(ImageViewerActivity$$Lambda$4.lambdaFactory$(this, urls));
        this.imageViewer.setViewDragCallback(this);
    }

    public void onViewDragged(float dragPercentage) {
        this.background.setAlpha(1.0f - dragPercentage);
    }

    public void onViewCaptured() {
        this.toolbar.animate().alpha(0.0f).setDuration(150);
    }

    public void onViewReleased(boolean isSettling) {
        if (isSettling) {
            this.toolbar.animate().alpha(1.0f).setDuration(150);
        } else {
            supportFinishAfterTransition();
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    public void finish() {
        this.jitneyLogger.logCheckinGuideGuestClickPhotoOutEvent(this.listingId, (long) this.imageViewer.getClosestPosition());
        Intent intent = new Intent();
        intent.putExtra(ARG_RETURN_CURRENT_URL, this.currentUrl);
        setResult(-1, intent);
        super.finish();
    }
}
