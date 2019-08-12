package com.airbnb.android.p011p3;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.core.requests.P3ListingRequest;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.image_viewer.ImageViewer;
import com.airbnb.p027n2.components.image_viewer.ImageViewer.ImageViewerData;
import com.airbnb.p027n2.components.image_viewer.ImageViewer.OnFirstImageLoadedListener;
import com.airbnb.p027n2.components.image_viewer.ImageViewer.OnViewDragCallback;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback.AutoSharedElementCallbackDelegate;
import com.airbnb.p027n2.transitions.TransitionName;
import com.facebook.internal.AnalyticsEvents;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import p032rx.Observer;

/* renamed from: com.airbnb.android.p3.P3PicturesActivity */
public class P3PicturesActivity extends SolitAirActivity implements OnSnapToPositionListener, OnFirstImageLoadedListener, OnViewDragCallback {
    private static final String EXTRA_CAROUSEL_POSITION_MESSENGER = "carousel_position_binder";
    private static final String EXTRA_POSITION = "position";
    private static final String EXTRA_STATE = "extra_p3_state";
    public static final int MSG_CAROUSEL_POSITION = 1;
    @BindView
    View background;
    private boolean failedToNotifyP3;
    @BindView
    ImageViewer imageViewer;
    private Listing listing;
    final RequestListener<ListingResponse> listingResponseRequestListener = new C0699RL().onResponse(P3PicturesActivity$$Lambda$1.lambdaFactory$(this)).build();
    private Messenger messenger;
    private P3State state;
    @BindView
    AirToolbar toolbar;

    public static Intent intent(Context context, P3State state2, int position, Messenger messenger2) {
        return new Intent(context, P3PicturesActivity.class).putExtra(EXTRA_STATE, state2).putExtra("position", position).putExtra(EXTRA_CAROUSEL_POSITION_MESSENGER, messenger2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        List<Photo> photos;
        super.onCreate(savedInstanceState);
        setContentView(C7532R.layout.activity_p3_pictures);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        if (AndroidVersion.isAtLeastLollipop()) {
            setupTransition();
        }
        this.imageViewer.setSnapToPositionListener(this);
        this.messenger = (Messenger) getIntent().getParcelableExtra(EXTRA_CAROUSEL_POSITION_MESSENGER);
        this.state = (P3State) getIntent().getParcelableExtra(EXTRA_STATE);
        this.listing = this.state.listing();
        if (this.listing == null) {
            loadImages(this.state);
            photos = Collections.emptyList();
        } else if (this.listing.getPhotos() == null) {
            loadImages(this.state);
            photos = Collections.singletonList(this.listing.getPhoto());
        } else {
            photos = new ArrayList<>(this.listing.getPhotos());
        }
        setPhotos(this.state.listingId(), photos);
        this.imageViewer.scrollToPosition(getIntent().getIntExtra("position", 0));
    }

    @TargetApi(21)
    private void setupTransition() {
        this.imageViewer.setViewDragCallback(this);
        this.imageViewer.setOnFirstImageLoadedListener(this);
        supportPostponeEnterTransition();
        this.imageViewer.postDelayed(P3PicturesActivity$$Lambda$2.lambdaFactory$(this), 1000);
        setEnterSharedElementCallback(new AutoSharedElementCallback(this).setDelegate(new AutoSharedElementCallbackDelegate() {
            public boolean onPreMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                P3PicturesActivity.this.onMapSharedElements(names, sharedElements);
                return true;
            }
        }));
        Transition transition = TransitionInflater.from(this).inflateTransition(17760257);
        transition.setInterpolator(new LinearOutSlowInInterpolator());
        transition.setDuration(200);
        getWindow().setSharedElementReturnTransition(transition);
    }

    /* access modifiers changed from: protected */
    public boolean shouldLockToPortrait() {
        return false;
    }

    /* access modifiers changed from: private */
    @TargetApi(21)
    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
        if (!sharedElements.keySet().containsAll(names)) {
            if (this.failedToNotifyP3) {
                sharedElements.clear();
                getWindow().setReturnTransition(new Slide(5));
                return;
            }
            int firstVisiblePosition = this.imageViewer.getFirstVisibleItemPosition();
            if (firstVisiblePosition != -1) {
                View firstView = this.imageViewer.getLayoutManager().findViewByPosition(firstVisiblePosition);
                String transitionName = TransitionName.toString("listing", this.state.listingId(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, (long) firstVisiblePosition);
                View transitionView = ViewUtils.findTransitionView(firstView, transitionName);
                sharedElements.clear();
                sharedElements.put(transitionName, transitionView);
                names.clear();
                names.add(transitionName);
            }
        }
    }

    private void loadImages(P3State state2) {
        P3ListingRequest.forP3(state2).withListener((Observer) this.listingResponseRequestListener).execute(this.requestManager);
    }

    private void setPhotos(long listingId, List<Photo> photos) {
        ImmutableList<ImageViewerData> data;
        if (Experiments.useDynamicImageSizeOnP3()) {
            data = FluentIterable.from((Iterable<E>) photos).transform(P3PicturesActivity$$Lambda$3.lambdaFactory$()).toList();
        } else {
            data = FluentIterable.from((Iterable<E>) photos).transform(P3PicturesActivity$$Lambda$4.lambdaFactory$()).toList();
        }
        this.imageViewer.setData("listing", listingId, data);
    }

    static /* synthetic */ ImageViewerData lambda$setPhotos$0(Photo photo) {
        return new ImageViewerData(photo.getCaption(), photo);
    }

    static /* synthetic */ ImageViewerData lambda$setPhotos$1(Photo photo) {
        return new ImageViewerData(photo.getCaption(), photo.getLargeUrl(), photo.getBase64Preview());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return this.toolbar.onCreateOptionsMenu(menu, getMenuInflater());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7532R.C7534id.share) {
            return super.onOptionsItemSelected(item);
        }
        if (this.listing != null) {
            startActivity(ShareActivityIntents.newIntentForListingPhoto(this, this.listing, this.imageViewer.getFirstVisibleItemPosition()));
        }
        return true;
    }

    public void onFirstImageLoaded() {
        scheduleStartPostponedTransition();
    }

    public void onSnappedToPosition(int position, boolean userSwipedLeft, boolean autoScroll) {
        if (!this.failedToNotifyP3) {
            Message message = new Message();
            message.what = 1;
            message.arg1 = position;
            try {
                this.messenger.send(message);
            } catch (RemoteException e) {
                this.failedToNotifyP3 = true;
                BugsnagWrapper.notify((Throwable) e);
            }
        }
    }

    static /* synthetic */ void lambda$new$2(P3PicturesActivity p3PicturesActivity, ListingResponse response) {
        p3PicturesActivity.listing = response.listing;
        p3PicturesActivity.setPhotos(p3PicturesActivity.listing.getId(), p3PicturesActivity.listing.getPhotos());
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
}
