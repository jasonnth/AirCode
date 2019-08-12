package com.airbnb.android.lib.share;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.events.WechatShareTripFinishedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.share.ShareYourTripAnalytics.EntryPoint;
import com.airbnb.android.lib.utils.DialogUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.interfaces.Scrollable;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.common.collect.ImmutableList;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.squareup.otto.Subscribe;
import icepick.State;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class ShareYourTripToWechatFragment extends AirFragment {
    private static final int CHANNEL_TRACKING_CODE = 44;
    private static final String EXTRA_CONFIRMATION_CODE = "confirmation_code";
    private static final String EXTRA_ENTRY_POINT = "extra_entrypoint";
    private static final String EXTRA_RESERVATION = "extra_reservation";
    private static final String EXTRA_RESERVATION_ID = "extra_reservation_id";
    private static final String EXTRA_TRIP_TITLE_OVERRIDE = "extra_trip_title_override";
    private static final int MAX_USER_PHOTOS_ALLOWED = 5;
    private static final int RC_ADD_PHOTO = 246;
    private static final int RC_EDIT_MESSAGE = 245;
    @BindView
    View addMorePhotos;
    @BindView
    View addPhotoSection;
    @BindView
    AirToolbar airToolbar;
    private ShareYourTripAnalytics analytics;
    private final List<View> deletePhotoIcons = new ArrayList();
    @BindView
    AirTextView editMessage;
    @State
    EntryPoint entryPoint;
    private List<View> excludedComponents;
    @BindView
    View headerDivider;
    @BindView
    AirTextView homeTitle;
    @BindView
    AirImageView hostInfoSeparator;
    @BindView
    AirTextView hostInfoText;
    @BindView
    HaloImageView hostPhoto;
    @BindView
    AirTextView hostReviewCount;
    @BindView
    AirTextView hostedByText;
    private LayoutInflater inflater;
    @BindView
    AirImageView listingImageView;
    @BindView
    LoaderFrame loaderFrame;
    /* access modifiers changed from: private */
    public Snackbar maxPhotoReachedSnackBar;
    @BindView
    View messageSectionDivider;
    @State
    ArrayList<Uri> photoMemories = new ArrayList<>();
    @BindView
    ViewGroup photoMemoriesContainer;
    @BindView
    AirTextView photoMemoriesTitle;
    private int photoRowWidth;
    @BindView
    View photoSectionDivider;
    @BindView
    ViewGroup previewContainer;
    @State
    Reservation reservation;
    final RequestListener<ReservationResponse> reservationListener = new C0699RL().onResponse(ShareYourTripToWechatFragment$$Lambda$1.lambdaFactory$(this)).onError(ShareYourTripToWechatFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    VerboseScrollView scrollView;
    @BindView
    View submitButton;
    @BindView
    AirImageView superHostIcon;
    @BindView
    AirTextView tripMessage;
    @BindView
    AirTextView tripMessageQuote;
    @BindView
    AirTextView tripTitle;
    @State
    String tripTitleOverride;
    @BindView
    HaloImageView userPhoto;
    @BindView
    AirTextView userTitle;

    public static Intent newIntentFromRO(Context context, Reservation reservation2) {
        return new Intent(context, ShareYourTripActivity.class).putExtra("extra_reservation", reservation2).putExtra(EXTRA_ENTRY_POINT, EntryPoint.Itinerary);
    }

    public static Intent newIntentFromPush(Context context, long reservationId, String tripTitleOverride2) {
        return new Intent(context, ShareYourTripActivity.class).putExtra(EXTRA_RESERVATION_ID, reservationId).putExtra(EXTRA_TRIP_TITLE_OVERRIDE, tripTitleOverride2).putExtra(EXTRA_ENTRY_POINT, EntryPoint.PushNotification);
    }

    static /* synthetic */ void lambda$new$0(ShareYourTripToWechatFragment shareYourTripToWechatFragment, ReservationResponse response) {
        shareYourTripToWechatFragment.loaderFrame.finishImmediate();
        shareYourTripToWechatFragment.reservation = response.reservation;
        shareYourTripToWechatFragment.bindReservationData();
    }

    static /* synthetic */ void lambda$new$1(ShareYourTripToWechatFragment shareYourTripToWechatFragment, AirRequestNetworkException exception) {
        NetworkUtil.toastNetworkError((Context) shareYourTripToWechatFragment.getActivity(), (NetworkException) exception);
        shareYourTripToWechatFragment.getActivity().finish();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject((AirFragment) this);
        this.mBus.register(this);
        Intent intent = getActivity().getIntent();
        this.tripTitleOverride = intent.getStringExtra(EXTRA_TRIP_TITLE_OVERRIDE);
        this.reservation = (Reservation) intent.getParcelableExtra("extra_reservation");
        this.entryPoint = EntryPoint.valueOf(intent.getSerializableExtra(EXTRA_ENTRY_POINT).toString());
        this.analytics = new ShareYourTripAnalytics(this.entryPoint, getEntityIdToLog());
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBus.unregister(this);
    }

    @Subscribe
    public void onWechatShareFinished(WechatShareTripFinishedEvent event) {
        DialogUtils.hideProgressDialog(getFragmentManager());
        this.analytics.trackShareResult(event);
        if (event.success) {
            Toast.makeText(getContext(), C0880R.string.share_your_trip_share_success_toast, 0).show();
            getActivity().finish();
            return;
        }
        onShareFailed();
    }

    public View onCreateView(LayoutInflater inflater2, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater2;
        View view = inflater2.inflate(C0880R.layout.fragment_share_your_trip, container, false);
        bindViews(view);
        this.airToolbar.scrollWith((Scrollable<?>) this.scrollView);
        getAirActivity().setToolbar(this.airToolbar);
        this.excludedComponents = ImmutableList.m1287of(this.addPhotoSection, this.addMorePhotos, this.editMessage);
        if (TextUtils.isEmpty(this.tripTitleOverride)) {
            this.tripTitle.setText(this.tripTitleOverride);
        }
        if (this.reservation == null) {
            getReservationRequest().withListener((Observer) this.reservationListener).execute(this.requestManager);
            this.loaderFrame.startAnimation();
        } else {
            bindReservationData();
        }
        this.photoRowWidth = ViewLibUtils.getScreenWidth(getContext()) - (getResources().getDimensionPixelSize(C0880R.dimen.n2_horizontal_padding_medium) * 2);
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case RC_EDIT_MESSAGE /*245*/:
                    updateMessageSection(data.getStringExtra(ShareYourTripEditMessageFragment.RESULT_EXTRA_MESSAGE));
                    return;
                case RC_ADD_PHOTO /*246*/:
                    ClipData clipData = data.getClipData();
                    boolean photoChanged = false;
                    if (clipData != null) {
                        this.analytics.trackPhotoSelected(clipData.getItemCount());
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            photoChanged |= addPhotoToMemories(clipData.getItemAt(i).getUri());
                        }
                    } else if (data.getData() != null) {
                        this.analytics.trackPhotoSelected(1);
                        photoChanged = addPhotoToMemories(data.getData());
                    }
                    if (photoChanged) {
                        updatePhotoMemorySection();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ShareTrip;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv("reservation_id", getEntityIdToLog()).mo11639kv(ShareActivityIntents.ARG_ENTRY_POINT, this.entryPoint.name());
    }

    private String getEntityIdToLog() {
        if (this.reservation != null) {
            return String.valueOf(this.reservation.getId());
        }
        long reservationId = getActivity().getIntent().getLongExtra(EXTRA_RESERVATION_ID, -1);
        if (reservationId != -1) {
            return String.valueOf(reservationId);
        }
        return getActivity().getIntent().getStringExtra("confirmation_code");
    }

    private ReservationRequest getReservationRequest() {
        long reservationId = getActivity().getIntent().getLongExtra(EXTRA_RESERVATION_ID, -1);
        if (reservationId != -1) {
            return ReservationRequest.forReservationId(reservationId, Format.Guest);
        }
        return ReservationRequest.forConfirmationCode(getActivity().getIntent().getStringExtra("confirmation_code"), Format.Guest);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void editMessage() {
        this.analytics.trackClickEvent("add_message");
        startActivityForResult(ShareYourTripEditMessageFragment.newIntent(getContext(), this.tripMessage.getText().toString()), RC_EDIT_MESSAGE);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void addPhotoToMemories() {
        this.analytics.trackClickEvent("add_photo");
        if (!maybeToastUserForMaxPhotosReached()) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            if (VERSION.SDK_INT > 18) {
                intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            }
            startActivityForResult(intent, RC_ADD_PHOTO);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void shareToWechatWithPermissionCheck() {
        this.analytics.trackShareSubmit(this.tripMessage.getText().toString());
        ShareYourTripToWechatFragmentPermissionsDispatcher.shareToWechatWithCheck(this);
    }

    /* access modifiers changed from: 0000 */
    public void shareToWechat() {
        prepViewsForSharing();
        new Handler().post(ShareYourTripToWechatFragment$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$null$2(ShareYourTripToWechatFragment shareYourTripToWechatFragment) {
        if (shareYourTripToWechatFragment.isResumed()) {
            try {
                long startTime = System.currentTimeMillis();
                String filePath = shareYourTripToWechatFragment.generatePhoto();
                shareYourTripToWechatFragment.analytics.trackOperationDuration("generate_photo_duration", System.currentTimeMillis() - startTime);
                long startTime2 = System.currentTimeMillis();
                WeChatHelper.sharePhotoToWechatMoments(shareYourTripToWechatFragment.getContext(), filePath);
                shareYourTripToWechatFragment.analytics.trackOperationDuration("share_to_wechat_duration", System.currentTimeMillis() - startTime2);
            } catch (IOException e) {
                shareYourTripToWechatFragment.analytics.trackGeneratePhotoFailed(e);
                shareYourTripToWechatFragment.onShareFailed();
            }
        }
    }

    private void updateMessageSection(String message) {
        this.tripMessage.setText(message);
        this.analytics.setHasMessage(!TextUtils.isEmpty(message));
        if (TextUtils.isEmpty(message)) {
            this.tripMessageQuote.setVisibility(8);
            this.tripMessage.setVisibility(8);
            this.editMessage.setText(C0880R.string.share_your_trip_add_message);
            return;
        }
        this.tripMessageQuote.setVisibility(0);
        this.tripMessage.setVisibility(0);
        this.editMessage.setText(C0880R.string.share_your_trip_edit_message);
    }

    private void prepViewsForSharing() {
        this.loaderFrame.startAnimation();
        Toast.makeText(getContext(), C0880R.string.share_your_trip_generating_image_message, 1).show();
        for (View deleteIcon : this.deletePhotoIcons) {
            deleteIcon.setVisibility(8);
        }
        int sectionVerticalPadding = getResources().getDimensionPixelSize(C0880R.dimen.share_your_trip_section_vertical_padding);
        MarginLayoutParams params = (MarginLayoutParams) this.photoSectionDivider.getLayoutParams();
        params.topMargin = sectionVerticalPadding;
        this.photoSectionDivider.setLayoutParams(params);
        MarginLayoutParams params2 = (MarginLayoutParams) this.messageSectionDivider.getLayoutParams();
        params2.topMargin = sectionVerticalPadding;
        this.messageSectionDivider.setLayoutParams(params2);
    }

    private void resetViewsForEditing() {
        this.loaderFrame.finishImmediate();
        for (View deleteIcon : this.deletePhotoIcons) {
            deleteIcon.setVisibility(0);
        }
        MarginLayoutParams params = (MarginLayoutParams) this.photoSectionDivider.getLayoutParams();
        params.topMargin = 0;
        this.photoSectionDivider.setLayoutParams(params);
        MarginLayoutParams params2 = (MarginLayoutParams) this.messageSectionDivider.getLayoutParams();
        params2.topMargin = 0;
        this.messageSectionDivider.setLayoutParams(params2);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        ShareYourTripToWechatFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private String generatePhoto() throws IOException {
        View headerView = this.inflater.inflate(C0880R.layout.share_your_trip_header, this.previewContainer, false);
        headerView.measure(MeasureSpec.makeMeasureSpec(this.previewContainer.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
        headerView.layout(0, 0, headerView.getMeasuredWidth(), headerView.getMeasuredHeight());
        View footerView = this.inflater.inflate(C0880R.layout.share_your_trip_footer, this.previewContainer, false);
        AirImageView imageView = (AirImageView) footerView.findViewById(C0880R.C0882id.share_your_trip_qr_code);
        Bitmap qrCode = generateQrCode();
        if (qrCode != null) {
            footerView.measure(MeasureSpec.makeMeasureSpec(this.previewContainer.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
            footerView.layout(0, 0, footerView.getMeasuredWidth(), footerView.getMeasuredHeight());
            imageView.setImageBitmap(qrCode);
        }
        int height = headerView.getHeight() + this.previewContainer.getHeight() + footerView.getHeight();
        for (int i = 0; i < this.previewContainer.getChildCount(); i++) {
            View v = this.previewContainer.getChildAt(i);
            if (v.getVisibility() == 0 && this.excludedComponents.contains(v)) {
                height -= v.getHeight();
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(this.previewContainer.getWidth(), height, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(ContextCompat.getColor(getContext(), C0880R.color.white));
        drawViewToCanvas(headerView, canvas);
        canvas.translate(0.0f, (float) headerView.getHeight());
        for (int i2 = 0; i2 < this.previewContainer.getChildCount(); i2++) {
            View v2 = this.previewContainer.getChildAt(i2);
            if (!this.excludedComponents.contains(v2) && v2.getVisibility() == 0) {
                drawViewToCanvas(v2, canvas);
            } else if (v2.getVisibility() == 0) {
                canvas.translate(0.0f, (float) (0 - v2.getMeasuredHeight()));
            }
        }
        canvas.setMatrix(null);
        canvas.translate(0.0f, (float) (height - footerView.getHeight()));
        drawViewToCanvas(footerView, canvas);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/airbnb_trip.png");
        FileOutputStream outputStream = new FileOutputStream(file);
        bitmap.compress(CompressFormat.PNG, 100, outputStream);
        outputStream.flush();
        outputStream.close();
        return file.getPath();
    }

    private Bitmap generateQrCode() {
        int qrCodeSize = getResources().getDimensionPixelSize(C0880R.dimen.share_your_trip_qr_code_size);
        try {
            BitMatrix matrix = new QRCodeWriter().encode(getListingUrlForSharing(), BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize);
            Bitmap bmp = Bitmap.createBitmap(qrCodeSize, qrCodeSize, Config.ARGB_8888);
            int color = ContextCompat.getColor(getContext(), C0880R.color.n2_text_color_main);
            for (int x = 0; x < qrCodeSize; x++) {
                for (int y = 0; y < qrCodeSize; y++) {
                    bmp.setPixel(x, y, matrix.get(x, y) ? color : 0);
                }
            }
            return bmp;
        } catch (WriterException e) {
            return null;
        }
    }

    private void drawViewToCanvas(View v, Canvas canvas) {
        canvas.translate(v.getX(), v.getY());
        v.draw(canvas);
        canvas.translate(0.0f - v.getX(), 0.0f - v.getY());
    }

    private void bindReservationData() {
        updatePhotoMemorySection();
        User currentUser = this.mAccountManager.getCurrentUser();
        Listing listing = this.reservation.getListing();
        User host = this.reservation.getHost();
        this.userPhoto.setImageUrl(currentUser.getPictureUrl());
        this.userTitle.setText(currentUser.getFirstName());
        if (TextUtils.isEmpty(this.tripTitleOverride)) {
            this.tripTitle.setText(getResources().getQuantityString(C0880R.plurals.share_your_trip_x_nights_in_city, this.reservation.getReservedNightsCount(), new Object[]{Integer.valueOf(this.reservation.getReservedNightsCount()), listing.getCity()}));
        }
        this.homeTitle.setText(getString(C0880R.string.share_your_trip_home_title, listing.getCity()));
        this.listingImageView.setImageUrl(listing.getPictureUrl());
        if (this.reservation.getReview() != null && this.reservation.getReview().getReviewRole() == ReviewRole.Guest && !TextUtils.isEmpty(this.reservation.getReview().getPublicFeedback())) {
            updateMessageSection(this.reservation.getReview().getPublicFeedback());
        }
        this.hostedByText.setText(getString(C0880R.string.hosted_by_name, host.getName()));
        if (host.getRevieweeCount() > 0) {
            this.hostReviewCount.setText(getResources().getQuantityString(C0880R.plurals.reviews, host.getRevieweeCount(), new Object[]{Integer.valueOf(host.getRevieweeCount())}));
            this.hostReviewCount.setVisibility(0);
            this.hostInfoSeparator.setVisibility(0);
        } else {
            this.hostReviewCount.setVisibility(8);
            this.hostInfoSeparator.setVisibility(8);
        }
        this.hostInfoText.setText(getString(C0880R.string.share_your_trip_host_info, host.getCreatedAt().format(new SimpleDateFormat("yyyy"))));
        this.hostPhoto.setImageUrl(host.getPictureUrl());
    }

    private void updatePhotoMemorySection() {
        this.analytics.setPhotoCount(this.photoMemories.size());
        if (this.photoMemories.isEmpty()) {
            this.addPhotoSection.setVisibility(0);
            this.photoMemoriesTitle.setVisibility(8);
            this.photoMemoriesContainer.setVisibility(8);
            this.addMorePhotos.setVisibility(8);
            this.photoSectionDivider.setVisibility(8);
            return;
        }
        this.addPhotoSection.setVisibility(8);
        this.photoMemoriesTitle.setVisibility(0);
        this.photoMemoriesContainer.setVisibility(0);
        this.addMorePhotos.setVisibility(0);
        this.photoSectionDivider.setVisibility(0);
        this.photoMemoriesContainer.removeAllViews();
        int photoIndex = 0;
        if (this.photoMemories.size() % 2 != 0) {
            int photoIndex2 = 0 + 1;
            this.photoMemoriesContainer.addView(buildSingleImageRow((Uri) this.photoMemories.get(0)), this.photoMemoriesContainer.getChildCount());
            photoIndex = photoIndex2;
        }
        while (photoIndex < this.photoMemories.size()) {
            this.photoMemoriesContainer.addView(buildDoubleImageRow((Uri) this.photoMemories.get(photoIndex), (Uri) this.photoMemories.get(photoIndex + 1)));
            photoIndex += 2;
        }
    }

    private boolean maybeToastUserForMaxPhotosReached() {
        if (this.photoMemories.size() < 5) {
            return false;
        }
        if (this.maxPhotoReachedSnackBar == null) {
            this.analytics.trackMaxPhotoToastImpression();
            this.maxPhotoReachedSnackBar = new SnackbarWrapper().view(getView()).title(getString(C0880R.string.share_your_trip_max_photos_reached, Integer.valueOf(5)), false).action(C0880R.string.share_your_trip_max_photos_reached_action, ShareYourTripToWechatFragment$$Lambda$4.lambdaFactory$(this)).buildAndShow();
            this.maxPhotoReachedSnackBar.setCallback(new Callback() {
                public void onDismissed(Snackbar snackbar, int event) {
                    ShareYourTripToWechatFragment.this.maxPhotoReachedSnackBar = null;
                }
            });
        }
        return true;
    }

    private boolean addPhotoToMemories(Uri uri) {
        if (this.photoMemories.contains(uri) || maybeToastUserForMaxPhotosReached()) {
            return false;
        }
        this.photoMemories.add(uri);
        return true;
    }

    private void loadImageFromUri(final AirImageView imageView, Uri uri, int size) {
        Glide.with(getContext()).load(uri).asBitmap().override(size, size).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(new SimpleTarget<Bitmap>() {
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation, boolean fromMemoryCache) {
                imageView.setImageBitmap(resource);
            }
        });
    }

    private View buildSingleImageRow(Uri uri) {
        View view = this.inflater.inflate(C0880R.layout.share_your_trip_image_view, this.photoMemoriesContainer, false);
        AirImageView airImageView = (AirImageView) view.findViewById(C0880R.C0882id.share_your_trip_image_view_photo);
        airImageView.setAdjustViewBounds(true);
        loadImageFromUri(airImageView, uri, this.photoRowWidth);
        AirImageView deleteButton = (AirImageView) view.findViewById(C0880R.C0882id.share_your_trip_delete_photo);
        this.deletePhotoIcons.add(deleteButton);
        deleteButton.setOnClickListener(ShareYourTripToWechatFragment$$Lambda$5.lambdaFactory$(this, uri, deleteButton));
        return view;
    }

    static /* synthetic */ void lambda$buildSingleImageRow$5(ShareYourTripToWechatFragment shareYourTripToWechatFragment, Uri uri, AirImageView deleteButton, View v) {
        shareYourTripToWechatFragment.analytics.trackClickEvent("delete_photo");
        shareYourTripToWechatFragment.photoMemories.remove(uri);
        shareYourTripToWechatFragment.updatePhotoMemorySection();
        shareYourTripToWechatFragment.deletePhotoIcons.remove(deleteButton);
    }

    private View buildDoubleImageRow(Uri firstPhoto, Uri secondPhoto) {
        View view = this.inflater.inflate(C0880R.layout.share_your_trip_photo_row, this.photoMemoriesContainer, false);
        loadImageFromUri((AirImageView) view.findViewById(C0880R.C0882id.share_your_trip_photo_row_item_1), firstPhoto, this.photoRowWidth / 2);
        AirImageView deleteButton = (AirImageView) view.findViewById(C0880R.C0882id.share_your_trip_delete_photo_1);
        deleteButton.setOnClickListener(ShareYourTripToWechatFragment$$Lambda$6.lambdaFactory$(this, firstPhoto, deleteButton));
        this.deletePhotoIcons.add(deleteButton);
        loadImageFromUri((AirImageView) view.findViewById(C0880R.C0882id.share_your_trip_photo_row_item_2), secondPhoto, this.photoRowWidth / 2);
        AirImageView deleteButton2 = (AirImageView) view.findViewById(C0880R.C0882id.share_your_trip_delete_photo_2);
        deleteButton2.setOnClickListener(ShareYourTripToWechatFragment$$Lambda$7.lambdaFactory$(this, secondPhoto, deleteButton2));
        this.deletePhotoIcons.add(deleteButton2);
        return view;
    }

    static /* synthetic */ void lambda$buildDoubleImageRow$6(ShareYourTripToWechatFragment shareYourTripToWechatFragment, Uri firstPhoto, AirImageView deleteButton, View v) {
        shareYourTripToWechatFragment.photoMemories.remove(firstPhoto);
        shareYourTripToWechatFragment.updatePhotoMemorySection();
        shareYourTripToWechatFragment.deletePhotoIcons.remove(deleteButton);
    }

    static /* synthetic */ void lambda$buildDoubleImageRow$7(ShareYourTripToWechatFragment shareYourTripToWechatFragment, Uri secondPhoto, AirImageView deleteButton2, View v) {
        shareYourTripToWechatFragment.photoMemories.remove(secondPhoto);
        shareYourTripToWechatFragment.updatePhotoMemorySection();
        shareYourTripToWechatFragment.deletePhotoIcons.remove(deleteButton2);
    }

    private void onShareFailed() {
        Toast.makeText(getContext(), C0880R.string.share_your_trip_share_failure_toast, 0).show();
        resetViewsForEditing();
    }

    private String getListingUrlForSharing() {
        Builder uri = Uri.parse(getString(C0880R.string.rooms_base_url, Long.valueOf(this.reservation.getListing().getId()))).buildUpon();
        uri.appendQueryParameter("s", Integer.toString(44));
        uri.appendQueryParameter("user_id", Long.toString(this.mAccountManager.getCurrentUserId()));
        uri.appendQueryParameter("ref_device_id", this.mAirbnbApi.getAndroidId());
        return uri.build().toString();
    }
}
