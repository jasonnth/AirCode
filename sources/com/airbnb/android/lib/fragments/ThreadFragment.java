package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.cohosting.fragments.CohostingListingPickerFragment;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.analytics.MessagingAnalytics;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.events.MessageReceivedEvent;
import com.airbnb.android.core.events.MessageSendEvent;
import com.airbnb.android.core.events.MessageSyncEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.intents.BookingActivityIntents;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.intents.GuestRecoveryActivityIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ThreadBlockActivityIntents;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.messaging.MessagingRequestFactory.SendSource;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingSummary;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Post.SendState;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.ReservationSummary;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.ThreadType;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityOrderStatus;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CohostedListingsRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.MessageTranslationRequest;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.requests.ThreadRequest;
import com.airbnb.android.core.requests.UpdateUserBlockRequest;
import com.airbnb.android.core.requests.UpdateUserFlagRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.CohostedListingsResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.MessageTranslationResponse;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.responses.ThreadResponse;
import com.airbnb.android.core.responses.UserBlockResponse;
import com.airbnb.android.core.responses.UserFlagResponse;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.CohostingDeepLink;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.MagicalTripsThreadUtil;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.utils.ThreadUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.DLSReservationObjectActivity;
import com.airbnb.android.lib.adapters.ThreadAdapter;
import com.airbnb.android.lib.adapters.ThreadAdapter.OnMessageItemClickListener;
import com.airbnb.android.lib.adapters.ThreadAdapter.OnTranslateMessageClickListener;
import com.airbnb.android.lib.fragments.inbox.saved_messages.MessageImageFullScreenFragment;
import com.airbnb.android.lib.fragments.inbox.saved_messages.SavedMessageSelectedListener;
import com.airbnb.android.lib.fragments.inbox.saved_messages.SavedMessagesFragment;
import com.airbnb.android.lib.fragments.inbox.threads.ThreadActionButtonController;
import com.airbnb.android.lib.fragments.inbox.threads.ThreadActionButtonController.Listener;
import com.airbnb.android.lib.fragments.inbox.utils.CohostingDisplayUtil;
import com.airbnb.android.lib.fragments.inbox.utils.MagicalTripsDisplayUtil;
import com.airbnb.android.lib.host.HostReservationIntentFactory;
import com.airbnb.android.lib.paidamenities.activities.GuestPendingAmenityActivity;
import com.airbnb.android.lib.paidamenities.activities.HostPendingAmenityActivity;
import com.airbnb.android.lib.reviews.activities.WriteReviewActivity;
import com.airbnb.android.lib.utils.ThemeUtils;
import com.airbnb.android.lib.views.messages.MessageThreadInputView;
import com.airbnb.android.photomarkupeditor.fragments.PhotoMarkupEditorFragment;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.thread.fragments.ThreadNotSentFragment;
import com.airbnb.android.thread.fragments.ThreadUnblockDialogFragment;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.Message.p021v1.ActionType;
import com.airbnb.jitney.event.logging.TranslationButtonTextType.p024v1.C0975TranslationButtonTextType;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.StatusBanner;
import com.airbnb.p027n2.components.ThreadBottomActionButton;
import com.airbnb.p027n2.components.onboarding_overlay.OnboardingOverlayViewController;
import com.airbnb.p027n2.primitives.messaging.MessageImage.MessageImageOnLoadedListener;
import com.airbnb.p027n2.primitives.messaging.MessageInputListener;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.squareup.otto.Subscribe;
import icepick.State;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import p032rx.Observer;

public class ThreadFragment extends AirFragment implements OnCreateContextMenuListener, OnMessageItemClickListener, OnTranslateMessageClickListener, MessageImageOnLoadedListener {
    private static final int CHOOSE_OR_TAKE_PHOTO = 144;
    private static final int ONBOARDING_OVERLAY_SHOW_ON_SEEN_TIMES_PHONE = 2;
    private static final int ONBOARDING_OVERLAY_SHOW_ON_SEEN_TIMES_TABLET = 4;
    private static final int RC_FETCH_IDENTITY = 142;
    private static final int REQUEST_BLOCKED_MESSAGE_NOT_SENT = 148;
    private static final int REQUEST_BLOCK_THREAD = 149;
    private static final int REQUEST_CODE_FLAG_CONTENT = 145;
    private static final int REQUEST_CODE_IMAGE_PREVIEW = 146;
    private static final int REQUEST_CODE_SAVED_MESSAGES = 143;
    private static final int REQUEST_UNBLOCK_THREAD = 147;
    private static final String TAG = ThreadFragment.class.getSimpleName();
    AirbnbAccountManager accountManager;
    @BindView
    PrimaryButton actionButton;
    private ThreadActionButtonController actionButtonController;
    private ThreadAdapter adapter;
    final RequestListener<Object> archiveListener = new C0699RL().onResponse(ThreadFragment$$Lambda$19.lambdaFactory$(this)).onError(ThreadFragment$$Lambda$20.lambdaFactory$(this)).build();
    private MenuItem archiveMenuItem;
    @BindView
    StatusBanner banner;
    public final NonResubscribableRequestListener<AirBatchResponse> batchCohostedListingListener = new C0699RL().onResponse(ThreadFragment$$Lambda$1.lambdaFactory$(this)).onError(ThreadFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    BusinessTravelAccountManager businessTravelAccountManager;
    private final Listener callToActionListener = new Listener() {
        public void viewIdentityVerification() {
            ThreadFragment.this.actionButton.setLoading();
            ReservationRequest.forConfirmationCode(ThreadFragment.this.thread.getInquiryReservation().getConfirmationCode(), ThreadFragment.this.inboxType.isHostMode() ? Format.Host : Format.Guest).withListener((Observer) ThreadFragment.this.reservationsRequestListener).execute(ThreadFragment.this.requestManager);
        }

        public void viewListingDetails() {
            ThreadFragment.this.reloadOnResume = true;
            ThreadFragment.this.startActivity(P3ActivityIntents.withListingId(ThreadFragment.this.getActivity(), ThreadFragment.this.thread.getListing().getId(), "message_thread"));
        }

        public void continueBooking() {
            ThreadFragment.this.actionButton.setLoading();
            ThreadFragment.this.fetchIdentityController.startFetchingIdentityVerificationState(ThreadFragment.this.mAccountManager.getCurrentUserId());
        }

        public void writeReview() {
            ThreadFragment.this.getContext().startActivity(WriteReviewActivity.newIntent(ThreadFragment.this.getContext(), ThreadFragment.this.thread.getReviewId()));
        }

        public void viewReservation() {
            ThreadFragment.this.jitneyLogger.threadDetailsClicked(ThreadFragment.this.inboxType, ThreadFragment.this.thread, ActionType.AcceptOrDecline);
            ThreadFragment.this.startROActivity();
        }

        public void viewAlterationRequest() {
            ThreadFragment.this.actionButton.setLoading();
            ReservationRequest.forConfirmationCode(ThreadFragment.this.thread.getReservationConfirmationCode(), ThreadFragment.this.inboxType.isHostMode() ? Format.Host : Format.Guest).withListener((Observer) ThreadFragment.this.showAlterationsRequestListener).execute(NetworkUtil.singleFireExecutor());
        }
    };
    /* access modifiers changed from: private */
    public FetchIdentityController fetchIdentityController;
    private final FetchIdentityController.Listener fetchIdentityControllerListener = new FetchIdentityController.Listener() {
        public void onVerificationsFetchComplete(ArrayList<AccountVerification> incompleteVerifications) {
            ThreadFragment.this.actionButton.setNormal();
            if (incompleteVerifications.isEmpty()) {
                ThreadFragment.this.goToBooking();
                return;
            }
            ThreadFragment.this.reloadOnResume = true;
            ThreadFragment.this.startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(ThreadFragment.this.getContext(), AccountVerificationStartFragmentArguments.builder().verificationFlow(VerificationFlow.Booking).incompleteVerifications(incompleteVerifications).host(ThreadFragment.this.thread.getOtherUser()).verificationUser(ThreadFragment.this.fetchIdentityController.getVerificationUser()).listingId(ThreadFragment.this.thread.getListing().getId()).build()), 142);
        }

        public void onVerificationsFetchError(NetworkException e) {
            ThreadFragment.this.actionButton.setNormal();
            NetworkUtil.tryShowErrorWithSnackbar(ThreadFragment.this.getView(), e);
        }
    };
    @State
    boolean forceReloadOnResume;
    @BindView
    View fullLoader;
    @BindView
    PrimaryButton hostRespondExtraServiceButton;
    @State
    InboxType inboxType;
    @BindView
    MessageThreadInputView input;
    private final MessageInputListener inputListener = new MessageInputListener() {
        public void onSendButtonClicked() {
            String message = ThreadFragment.this.input.getInputText();
            String imagePath = ThreadFragment.this.input.getImagePath();
            if (!TextUtils.isEmpty(message.trim())) {
                sendText(message.trim());
            } else if (!TextUtils.isEmpty(imagePath)) {
                sendImage(imagePath);
            }
        }

        public void onCombinationCameraIconClicked() {
            captureImage(0);
        }

        public void onPhotoIconClicked() {
            ThreadFragment.this.jitneyLogger.takePhotoClicked(ThreadFragment.this.inboxType, ThreadFragment.this.thread);
            ThreadFragment.this.jitneyLogger.logInputViewButtonClicked(MessagingJitneyLogger.PHOTO_ICON_LOGGING_TAG, ThreadFragment.this.threadId);
            captureImage(2);
        }

        public void onCameraIconClicked() {
            ThreadFragment.this.jitneyLogger.takePhotoClicked(ThreadFragment.this.inboxType, ThreadFragment.this.thread);
            ThreadFragment.this.jitneyLogger.logInputViewButtonClicked(MessagingJitneyLogger.CAMERA_ICON_LOGGING_TAG, ThreadFragment.this.threadId);
            captureImage(1);
        }

        public void onTextIconClicked(View view) {
            MiscUtils.requestFocusAndOpenKeyboard(ThreadFragment.this.getContext(), view);
        }

        public void onSavedMessageIconClicked() {
            ThreadFragment.this.jitneyLogger.logInputViewButtonClicked(MessagingJitneyLogger.SAVED_MESSAGES_LOGGING_TAG, ThreadFragment.this.threadId);
            ListingSummary listing = ThreadFragment.this.thread.getListing();
            if (listing != null) {
                ThreadFragment.this.startActivityForResult(ModalActivity.intentForFragment(ThreadFragment.this.getContext(), SavedMessagesFragment.newInstance(listing.getId(), ThreadFragment.this.threadId)), 143);
            }
        }

        private void captureImage(int imageSource) {
            ThreadFragment.this.startActivityForResult(AirPhotoPicker.builder().setSource(imageSource).targetOutputDimensions(2048, 2048).create(ThreadFragment.this.getContext()), 144);
        }

        private void sendText(String message) {
            ThreadFragment.this.appendSentMessage(ThreadFragment.this.messagingRequestFactory.sendMessage(ThreadFragment.this.inboxType, ThreadFragment.this.threadId, message, SendSource.Thread));
            ThreadFragment.this.input.clearInputText();
        }

        private void sendImage(String imagePath) {
            ThreadFragment.this.appendSentMessage(ThreadFragment.this.messagingRequestFactory.sendImageMessage(ThreadFragment.this.inboxType, ThreadFragment.this.threadId, imagePath, SendSource.Thread));
            ThreadFragment.this.input.clearImagePreview();
        }
    };
    @BindView
    View inputLoader;
    MessagingJitneyLogger jitneyLogger;
    private final OnGlobalLayoutListener keyboardListener = ThreadFragment$$Lambda$16.lambdaFactory$(this);
    @BindView
    LinearLayout layout;
    final RequestListener<ThreadResponse> loadThreadRequestListener = new C0699RL().onResponse(ThreadFragment$$Lambda$8.lambdaFactory$(this)).onError(ThreadFragment$$Lambda$9.lambdaFactory$(this)).onComplete(ThreadFragment$$Lambda$10.lambdaFactory$(this)).build();
    final RequestListener<MessageTranslationResponse> messageTranslationResponseRequestListener = new C0699RL().onResponse(ThreadFragment$$Lambda$17.lambdaFactory$(this)).onError(ThreadFragment$$Lambda$18.lambdaFactory$(this)).build();
    protected MessagingRequestFactory messagingRequestFactory;
    @State
    Post postToUnflag;
    @BindView
    RecyclerView recyclerView;
    @State
    boolean reloadOnResume;
    private final NonResubscribableRequestListener<ThreadResponse> reloadThreadRequestListener = new C0699RL().onResponse(ThreadFragment$$Lambda$13.lambdaFactory$(this)).onError(ThreadFragment$$Lambda$14.lambdaFactory$()).onComplete(ThreadFragment$$Lambda$15.lambdaFactory$(this)).buildWithoutResubscription();
    private MenuItem reportMenuItem;
    @State
    Reservation reservation;
    final RequestListener<ReservationResponse> reservationsRequestListener = new C0699RL().onResponse(ThreadFragment$$Lambda$6.lambdaFactory$(this)).onError(ThreadFragment$$Lambda$7.lambdaFactory$(this)).build();
    @State
    Long scrollToPostId;
    /* access modifiers changed from: private */
    public final NonResubscribableRequestListener<ReservationResponse> showAlterationsRequestListener = new C0699RL().onResponse(ThreadFragment$$Lambda$3.lambdaFactory$(this)).onError(ThreadFragment$$Lambda$4.lambdaFactory$(this)).onComplete(ThreadFragment$$Lambda$5.lambdaFactory$(this)).buildWithoutResubscription();
    @State
    ROLaunchSource source;
    private final NonResubscribableRequestListener<ListingResponse> startBookingActivityListingListner = new NonResubscribableRequestListener<ListingResponse>() {
        public void onResponse(ListingResponse data) {
            Intent intent;
            ThreadFragment.this.reloadOnResume = true;
            ReservationStatus status = ThreadFragment.this.thread.getReservationStatus();
            if (!ThreadFragment.this.thread.hasSpecialOffer() || !(status == ReservationStatus.SpecialOffer || status == ReservationStatus.Preapproved)) {
                intent = BookingActivityIntents.intentForBooking(ThreadFragment.this.getActivity(), ThreadFragment.this.thread, data.listing);
            } else {
                intent = BookingActivityIntents.intentForSpecialOffer(ThreadFragment.this.getActivity(), ThreadFragment.this.thread, data.listing);
            }
            ThreadFragment.this.startActivity(intent);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            NetworkUtil.tryShowErrorWithSnackbar(ThreadFragment.this.getView(), e);
        }

        public void onRequestCompleted(boolean successful) {
            ThreadFragment.this.actionButton.setNormal();
        }
    };
    @State
    Thread thread;
    @BindView
    ThreadBottomActionButton threadBottomActionButton;
    @State
    long threadId;
    private MenuItem unarchiveMenuItem;
    private MenuItem unblockMenuItem;
    final RequestListener<UserBlockResponse> updateUserBlockListener = new C0699RL().onResponse(ThreadFragment$$Lambda$21.lambdaFactory$(this)).onError(ThreadFragment$$Lambda$22.lambdaFactory$(this)).build();
    final RequestListener<UserFlagResponse> userFlagListener = new C0699RL().onResponse(ThreadFragment$$Lambda$11.lambdaFactory$(this)).onError(ThreadFragment$$Lambda$12.lambdaFactory$(this)).build();

    public void onTranslateClicked() {
        this.jitneyLogger.logTranslateThreadClicked(this.inboxType, this.thread, C0975TranslationButtonTextType.Translate);
        fetchTranslations();
    }

    public void onShowOriginalClicked() {
        this.jitneyLogger.logTranslateThreadClicked(this.inboxType, this.thread, C0975TranslationButtonTextType.ShowOriginal);
        this.adapter.showOriginalMessages();
    }

    static /* synthetic */ void lambda$new$4(ThreadFragment threadFragment, ReservationResponse response) {
        threadFragment.reservation = response.reservation;
        threadFragment.reloadOnResume = true;
        threadFragment.actionButton.setNormal();
        threadFragment.startActivity(VerifiedIdActivityIntents.intentForVerifiedId(threadFragment.getActivity(), null, threadFragment.reservation));
    }

    static /* synthetic */ void lambda$new$5(ThreadFragment threadFragment, AirRequestNetworkException e) {
        threadFragment.actionButton.setNormal();
        NetworkUtil.tryShowErrorWithSnackbar(threadFragment.getView(), e);
    }

    static /* synthetic */ void lambda$new$6(ThreadFragment threadFragment, ThreadResponse response) {
        threadFragment.thread = response.thread;
        if (threadFragment.thread.isUnread()) {
            threadFragment.thread.setUnread(false);
            threadFragment.messagingRequestFactory.markThreadRead(threadFragment.inboxType, threadFragment.thread);
        }
        threadFragment.setupUI();
        threadFragment.fullLoader.setVisibility(8);
        threadFragment.setupOnboardingOverlayForSavedMessagesIcon();
        PerformanceLoggerTimeline.logTimeToInteraction(Event.HOST_MESSAGE_THREAD);
        MessagingAnalytics.trackThreadView(threadFragment.thread, threadFragment.getReservationStatusText(), threadFragment.inboxType);
    }

    static /* synthetic */ void lambda$new$13(ThreadFragment threadFragment, ThreadResponse response) {
        threadFragment.thread = response.thread;
        threadFragment.setupUI();
    }

    static /* synthetic */ void lambda$new$14(AirRequestNetworkException error) {
    }

    static /* synthetic */ void lambda$new$17(ThreadFragment threadFragment, AirRequestNetworkException error) {
        threadFragment.jitneyLogger.logTranslationFailure(threadFragment.inboxType, threadFragment.thread, String.valueOf(NetworkUtil.getNetworkErrorLoggingData(error)));
        threadFragment.adapter.onTranslationFailed();
    }

    static /* synthetic */ void lambda$new$18(ThreadFragment threadFragment, Object data) {
        boolean z;
        Thread thread2 = threadFragment.thread;
        if (!threadFragment.thread.isArchived()) {
            z = true;
        } else {
            z = false;
        }
        thread2.setArchived(z);
        threadFragment.setUpMenuUI();
        threadFragment.toggleArchiveConfirmationSnackBar(threadFragment.thread.isArchived() ? C0880R.string.archive_confirmation : C0880R.string.unarchive_confirmation, 0, 2);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        PerformanceLoggerTimeline.start(Event.HOST_MESSAGE_THREAD);
    }

    static /* synthetic */ void lambda$new$21(ThreadFragment threadFragment, UserBlockResponse data) {
        threadFragment.thread.setBlock(data.userBlock);
        threadFragment.setUpMenuUI();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ThemeUtils.inflaterForPhonePadding(inflater).inflate(C0880R.layout.fragment_thread, container, false);
        bindViews(view);
        this.mBus.register(this);
        if (savedInstanceState == null) {
            this.threadId = getArguments().getLong("thread_id", 0);
            this.inboxType = (InboxType) Check.notNull(getArguments().getSerializable(ThreadFragmentIntents.ARG_INBOX_TYPE));
            this.scrollToPostId = Long.valueOf(getArguments().getLong("post_id"));
            this.source = (ROLaunchSource) getArguments().getSerializable("launch_source");
            initialFetch();
        }
        this.adapter = new ThreadAdapter(getActivity(), this.inboxType, this, this, this, this);
        this.recyclerView.setAdapter(this.adapter);
        ((LinearLayoutManager) this.recyclerView.getLayoutManager()).setReverseLayout(true);
        this.businessTravelAccountManager.fetchBusinessTravelEmployeeInfo();
        this.fetchIdentityController = new FetchIdentityController((RequestManager) this.requestManager, this.fetchIdentityControllerListener, VerificationFlow.Booking, savedInstanceState);
        this.actionButtonController = new ThreadActionButtonController(this.actionButton, this.callToActionListener);
        setupUI();
        view.getViewTreeObserver().addOnGlobalLayoutListener(this.keyboardListener);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C0880R.C0883menu.thread, menu);
        this.archiveMenuItem = menu.findItem(C0880R.C0882id.archive).setVisible(false);
        this.unarchiveMenuItem = menu.findItem(C0880R.C0882id.unarchive).setVisible(false);
        this.reportMenuItem = menu.findItem(C0880R.C0882id.report).setVisible(false);
        this.unblockMenuItem = menu.findItem(C0880R.C0882id.unblock).setVisible(false);
        setUpMenuUI();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == this.reportMenuItem) {
            startActivityForResult(ThreadBlockActivityIntents.newIntent(getContext(), this.thread), 149);
            return true;
        } else if (item == this.unblockMenuItem) {
            showUnblockDialog();
            return true;
        } else if (item != this.archiveMenuItem && item != this.unarchiveMenuItem) {
            return super.onOptionsItemSelected(item);
        } else {
            toggleArchived();
            return true;
        }
    }

    private void showUnblockDialog() {
        ThreadUnblockDialogFragment dialog = new ThreadUnblockDialogFragment();
        dialog.setTargetFragment(this, 147);
        dialog.setRecipientName(this.thread.getOtherUser().getName());
        dialog.show(getFragmentManager(), (String) null);
    }

    /* access modifiers changed from: private */
    public void handleUnblock() {
        UpdateUserBlockRequest.create(this.thread.getBlock().getId(), false).withListener((Observer) this.updateUserBlockListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void unflagPost() {
        new UpdateUserFlagRequest(FlagContent.Post, this.postToUnflag.getId(), this.accountManager.getCurrentUserId(), true).withListener((Observer) this.userFlagListener).execute(this.requestManager);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Post post = (Post) v.getTag();
        if (post.getAttachmentImages() == null || post.getAttachmentImages().size() == 0) {
            menu.add(C0880R.string.copy_message_option_text).setOnMenuItemClickListener(ThreadFragment$$Lambda$23.lambdaFactory$(this, v));
        }
        if (post.shouldShowFlagged()) {
            menu.add(C0880R.string.undo_report_message_option_text).setOnMenuItemClickListener(ThreadFragment$$Lambda$24.lambdaFactory$(this, post));
        } else {
            menu.add(C0880R.string.report_message_option_text).setOnMenuItemClickListener(ThreadFragment$$Lambda$25.lambdaFactory$(this, post));
        }
    }

    static /* synthetic */ boolean lambda$onCreateContextMenu$25(ThreadFragment threadFragment, Post post, MenuItem menuItem) {
        threadFragment.postToUnflag = post;
        threadFragment.unflagPost();
        return true;
    }

    static /* synthetic */ boolean lambda$onCreateContextMenu$26(ThreadFragment threadFragment, Post post, MenuItem menuItem) {
        threadFragment.getActivity().startActivityForResult(ReactNativeIntents.intentForFlagContent(threadFragment.getContext(), post.getId(), post.getUserFlag() == null ? null : Long.valueOf(post.getUserFlag().getId()), FlagContent.Post), 145);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 149) {
            this.thread = (Thread) data.getParcelableExtra(ThreadBlockActivityIntents.ARG_THREAD);
            setUpMenuUI();
            return;
        }
        if (resultCode == -1) {
            switch (requestCode) {
                case 142:
                    goToBooking();
                    break;
                case 143:
                    String savedMessages = data.getStringExtra(SavedMessageSelectedListener.CHOSEN_SAVED_MESSAGE);
                    String existingText = this.input.getInputText();
                    MessageThreadInputView messageThreadInputView = this.input;
                    if (!TextUtils.isEmpty(existingText)) {
                        savedMessages = existingText + " " + savedMessages;
                    }
                    messageThreadInputView.setInputText(savedMessages);
                    break;
                case 144:
                    String filePath = data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH);
                    if (this.inboxType.isHostMode() && FeatureToggles.showImageAnnotationsInMessageThread()) {
                        startActivityForResult(PhotoMarkupEditorFragment.intentForMessageThread(getContext(), filePath), 146);
                        break;
                    } else {
                        this.input.setImagePreview(filePath);
                        break;
                    }
                    break;
                case 145:
                    forceFullThreadReload();
                    break;
                case 146:
                    this.input.setImagePreview(data.getStringExtra(PhotoMarkupEditorFragment.EXTRA_EDITED_IMAGE_PATH));
                    break;
                case 147:
                    handleUnblock();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.fetchIdentityController != null) {
            this.fetchIdentityController.onSaveInstanceState(outState);
        }
    }

    public void onDestroyView() {
        getView().getViewTreeObserver().removeOnGlobalLayoutListener(this.keyboardListener);
        this.mBus.unregister(this);
        super.onDestroyView();
    }

    public void onPause() {
        KeyboardUtils.dismissSoftKeyboard((View) this.recyclerView);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (this.source == ROLaunchSource.ExtraService || this.forceReloadOnResume) {
            forceThreadReload();
        } else if (this.thread == null) {
        } else {
            if (this.reloadOnResume || this.inboxType.useMessagingSync()) {
                this.reloadOnResume = false;
                reloadThread();
            }
        }
    }

    private void setupUI() {
        if (this.thread != null) {
            this.adapter.setData(this.thread);
            setupBanner();
            this.input.setupMessageInput(getContext(), this.inputListener, this.inboxType, this.thread);
            setupGuestResponseButton();
            this.layout.animate().alpha(1.0f).setDuration(100);
            setActionBarTitle(getTitlebarText(this.thread));
            setupBottomActionButton();
            setUpMenuUI();
            return;
        }
        setActionBarTitle(null);
    }

    private void setUpMenuUI() {
        boolean isBlockActive;
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (this.reportMenuItem != null && this.thread != null) {
            boolean featureEnabled = Trebuchet.launch(TrebuchetKeys.THREAD_BLOCK);
            if (this.thread.getBlock() == null || !this.thread.getBlock().isActive()) {
                isBlockActive = false;
            } else {
                isBlockActive = true;
            }
            MenuItem menuItem = this.reportMenuItem;
            if (!featureEnabled || !this.thread.isBlockableThreadType() || isBlockActive) {
                z = false;
            } else {
                z = true;
            }
            menuItem.setVisible(z);
            MenuItem menuItem2 = this.unblockMenuItem;
            if (!featureEnabled || !isBlockActive || this.thread.getBlock().getBlockedUserId() != this.thread.getOtherUser().getId()) {
                z2 = false;
            } else {
                z2 = true;
            }
            menuItem2.setVisible(z2);
            MenuItem menuItem3 = this.archiveMenuItem;
            if (this.thread.isArchived()) {
                z3 = false;
            }
            menuItem3.setVisible(z3);
            this.unarchiveMenuItem.setVisible(this.thread.isArchived());
        }
    }

    /* access modifiers changed from: private */
    public void onThreadLoadingComplete() {
        this.fullLoader.setVisibility(8);
        this.inputLoader.setVisibility(8);
        this.input.setVisibility(0);
        int modelIndex = this.adapter.getModelPosition(this.scrollToPostId.longValue());
        if (modelIndex >= 0) {
            this.recyclerView.smoothScrollToPosition(modelIndex);
        }
    }

    private String getTitlebarText(Thread thread2) {
        if (thread2.getThreadType() == ThreadType.TripGroup || thread2.getThreadType().isRestaurantThread()) {
            return thread2.getAttachment().getDetails().getName();
        }
        return ThreadUtils.generateNamesString(getContext(), thread2, this.accountManager.getCurrentUser(), this.inboxType);
    }

    private void setupOnboardingOverlayForSavedMessagesIcon() {
        ImageView savedMessageIcon = this.input.getSavedMessageIcon();
        if (this.inboxType.isHostMode() && savedMessageIcon != null) {
            OnboardingOverlayViewController.show(getActivity(), savedMessageIcon, getString(C0880R.string.onboarding_title_for_saved_messages_icon), getString(C0880R.string.onboarding_dismiss_button), "saved_message_icon", isTabletScreen() ? 4 : 2);
        }
    }

    private void setActionBarTitle(String title) {
        if (!isTabletScreen()) {
            getActionBar().setTitle((CharSequence) title);
        }
    }

    /* access modifiers changed from: private */
    public void setupGuestResponseButton() {
        boolean isKeyboardShowing = getView() != null && KeyboardUtils.isKeyboardUp(getAppCompatActivity(), getView());
        boolean isReloading = this.requestManager.hasRequests(this.reloadThreadRequestListener);
        if (this.thread == null || isKeyboardShowing || isReloading) {
            this.actionButtonController.hideRespondButton();
        } else if (this.inboxType.isHostMode()) {
            this.actionButtonController.setupHostRespondButton(this.thread, isMyProfile(this.thread));
        } else {
            this.actionButtonController.setupGuestRespondButton(this.thread);
        }
    }

    private void setupBanner() {
        String text = getStatusBarText(this.thread);
        this.banner.setVisibility(text == null ? 8 : 0);
        this.banner.setLeftStatus(text);
    }

    private String getStatusBarText(Thread thread2) {
        if (thread2.getThreadType() == ThreadType.TripGroup) {
            return MagicalTripsDisplayUtil.generateNamesString(getContext(), thread2, this.accountManager.getCurrentUser());
        }
        if (!thread2.getThreadType().isCohostingThread() || !DebugSettings.LAUNCH_COHOSTING_LISTING_PICKER_FROM_THREADS.isEnabled()) {
            return getReservationStatusText();
        }
        return CohostingDisplayUtil.generateNamesString(getContext(), thread2);
    }

    private String getReservationStatusText() {
        ReservationStatusDisplay statusDisplay;
        switch (this.thread.getThreadType()) {
            case TripDirect:
            case TripGroup:
                return getString(MagicalTripsDisplayUtil.getStatusString(this.thread.getAttachment().getStatus()));
            case PlaceBooking:
                if (!this.thread.hasDates()) {
                    return null;
                }
                if (this.inboxType.isHostMode()) {
                    statusDisplay = ReservationStatusDisplay.forHost(this.thread);
                } else {
                    statusDisplay = ReservationStatusDisplay.forGuest(this.thread);
                }
                return statusDisplay.getString(getContext());
            default:
                return null;
        }
    }

    private void setupBottomActionButton() {
        if (this.thread.getReservationStatus() == ReservationStatus.Accepted) {
            ReservationSummary inquiryReservation = this.thread.getInquiryReservation();
            ListingSummary inquiryListing = this.thread.getListing();
            if (this.inboxType.isHostMode()) {
                if (inquiryReservation.hasPaidAmenityOrderWithStatus(EnumSet.of(PaidAmenityOrderStatus.Pending))) {
                    this.hostRespondExtraServiceButton.setVisibility(0);
                    this.hostRespondExtraServiceButton.setOnClickListener(ThreadFragment$$Lambda$26.lambdaFactory$(this));
                    this.hostRespondExtraServiceButton.setText(C0880R.string.host_respond_paid_amenities_request_button_text);
                    return;
                }
                this.hostRespondExtraServiceButton.setVisibility(8);
            } else if (shouldConfigureGuestBottomActionButton(inquiryListing, inquiryReservation)) {
                this.threadBottomActionButton.setVisibility(0);
                if (inquiryReservation.hasPaidAmenityOrderWithStatus(EnumSet.of(PaidAmenityOrderStatus.Accepted, PaidAmenityOrderStatus.Pending))) {
                    configureGuestBottomActionButton(getString(C0880R.string.guest_check_requested_paid_amenities_button_text), getString(C0880R.string.guest_extra_service_highlight_button_text), GuestPendingAmenityActivity.intentForThread(getContext(), this.thread));
                    return;
                }
                configureGuestBottomActionButton(getString(C0880R.string.guest_check_out_host_available_paid_amenities_button_text, this.thread.getOtherUser().getName()), getString(C0880R.string.guest_extra_service_highlight_button_text), PaidAmenityIntents.purchaseAmenitiesWithThread(getContext(), this.thread));
            }
        }
    }

    static /* synthetic */ void lambda$setupBottomActionButton$27(ThreadFragment threadFragment, View v) {
        threadFragment.forceReloadOnResume = true;
        threadFragment.startActivity(HostPendingAmenityActivity.intentForThread(threadFragment.getContext(), threadFragment.thread));
    }

    private boolean shouldConfigureGuestBottomActionButton(ListingSummary inquiryListing, ReservationSummary inquiryReservation) {
        if (!Trebuchet.launch(TrebuchetKeys.PAID_AMENITIES_GUEST, false)) {
            return false;
        }
        if (inquiryListing.hasPaidAmenities() || inquiryReservation.hasPaidAmenityOrders()) {
            return true;
        }
        return false;
    }

    private void configureGuestBottomActionButton(String fullText, String highlightText, Intent intent) {
        if (fullText.contains(highlightText)) {
            ViewUtils.setTextAndColorSubstring(this.threadBottomActionButton.getTextView(), fullText, highlightText, getResources().getColor(C0880R.color.c_babu));
        } else {
            this.threadBottomActionButton.setText((CharSequence) SpannableUtils.makeColoredString(fullText, getResources().getColor(C0880R.color.c_babu)));
        }
        this.threadBottomActionButton.setOnClickListener(ThreadFragment$$Lambda$27.lambdaFactory$(this, intent));
    }

    static /* synthetic */ void lambda$configureGuestBottomActionButton$28(ThreadFragment threadFragment, Intent intent, View v) {
        threadFragment.forceReloadOnResume = true;
        threadFragment.startActivity(intent);
    }

    private boolean hasValidReservation(Thread thread2) {
        boolean hasReservation;
        if (thread2.getReservationStatus() == null || thread2.getReservationStatus() == ReservationStatus.Unknown) {
            return false;
        }
        if (thread2.getReservationConfirmationCode() != null) {
            hasReservation = true;
        } else {
            hasReservation = false;
        }
        boolean hasInquiry = thread2.hasListing();
        if (hasReservation || hasInquiry) {
            return true;
        }
        return false;
    }

    @OnClick
    public void statusBannerClicked() {
        this.jitneyLogger.threadDetailsClicked(this.inboxType, this.thread, ActionType.DetailsButton);
        if (this.thread.getThreadType().isTripThread()) {
            startActivityForMagicalTripDetails();
        } else if (this.thread.getThreadType().isCohostingThread()) {
            handleCohostingDeepLinking();
        } else {
            startROActivity();
        }
    }

    /* access modifiers changed from: private */
    public void startROActivity() {
        Intent intent;
        this.reloadOnResume = true;
        if (this.source == ROLaunchSource.HostRO) {
            getActivity().finish();
            return;
        }
        if (this.thread.hasSpecialOffer()) {
            if (this.inboxType.isHostMode()) {
                intent = HostReservationIntentFactory.forThreadId(getContext(), this.thread.getId(), ROLaunchSource.MessageThread);
            } else {
                intent = DLSReservationObjectActivity.intentForThread(getContext(), this.thread.getId());
            }
        } else if (this.thread.hasPastReservationsHost()) {
            intent = ReservationPickerFragment.createIntent(getContext(), this.thread.getId(), this.thread.getReservationConfirmationCode());
        } else if (!TextUtils.isEmpty(this.thread.getReservationConfirmationCode())) {
            if (this.inboxType.isHostMode()) {
                intent = HostReservationIntentFactory.forConfirmationCode(getContext(), this.thread.getReservationConfirmationCode(), ROLaunchSource.MessageThread);
            } else {
                intent = getIntentForGuestReservation();
            }
        } else if (this.inboxType.isHostMode()) {
            intent = HostReservationIntentFactory.forThreadId(getContext(), this.thread.getId(), ROLaunchSource.MessageThread);
        } else {
            intent = DLSReservationObjectActivity.intentForThread(getContext(), this.thread.getId());
        }
        startActivity(intent);
    }

    private Intent getIntentForGuestReservation() {
        boolean isCanceledByHost = false;
        if (this.thread.getInquiryReservation() != null && this.thread.getReservationStatus() == ReservationStatus.Cancelled && this.thread.getInquiryReservation().isCanceledByHost()) {
            isCanceledByHost = true;
        }
        if ((this.thread.getReservationStatus() == ReservationStatus.Denied || isCanceledByHost) && FeatureToggles.enableRejectionRecovery()) {
            return GuestRecoveryActivityIntents.intentForConfirmationCode(getContext(), this.thread.getReservationConfirmationCode(), this.thread.getReservationStatus());
        }
        return ReactNativeIntents.intentForItineraryCheckinCard(getContext(), this.thread.getReservationConfirmationCode());
    }

    private void startActivityForMagicalTripDetails() {
        startActivity(MagicalTripsThreadUtil.getIntentForDetails(getContext(), this.thread, this.inboxType.isGuestMode()));
    }

    /* access modifiers changed from: private */
    public void appendSentMessage(Post post) {
        this.thread.addPost(post, true);
        this.adapter.setPosts(this.thread.getPosts());
        scrollToMostRecentMessage();
    }

    /* access modifiers changed from: private */
    public void initialFetch() {
        this.fullLoader.setVisibility(0);
        this.inputLoader.setVisibility(0);
        this.input.setVisibility(8);
        this.messagingRequestFactory.createThreadRequest(this.threadId, this.inboxType).withListener((Observer) this.loadThreadRequestListener).doubleResponse().execute(this.requestManager);
    }

    private void reloadThread() {
        if (!this.requestManager.hasRequest((BaseRequestListener<T>) this.reloadThreadRequestListener, ThreadRequest.class)) {
            this.messagingRequestFactory.createThreadRequest(this.threadId, this.inboxType).withListener((Observer) this.reloadThreadRequestListener).execute(this.requestManager);
        }
    }

    private void forceThreadReload() {
        this.messagingRequestFactory.createThreadRequest(this.threadId, this.inboxType).withListener((Observer) this.reloadThreadRequestListener).skipCache().execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void forceFullThreadReload() {
        this.messagingRequestFactory.createFullThreadRequest(this.threadId, this.inboxType).withListener((Observer) this.reloadThreadRequestListener).skipCache().execute(this.requestManager);
    }

    public void onMessageItemClicked(Post post) {
        if (post.didSendFail()) {
            this.messagingRequestFactory.sendPost(this.inboxType, this.threadId, post, SendSource.Thread);
        }
    }

    public void onImageAttachmentClicked(Post post) {
        if (post.didSendFail()) {
            this.messagingRequestFactory.sendPost(this.inboxType, this.threadId, post, SendSource.Thread);
            return;
        }
        this.jitneyLogger.logImageTap(this.threadId, post.getId());
        startActivity(MessageImageFullScreenFragment.newIntent(getActivity(), post.getImageAttachmentUrl()));
    }

    public void onImageLoaded(long postId, boolean fetch_success, long image_size, long seconds_to_load) {
        this.jitneyLogger.logImageFetch(this.threadId, postId, fetch_success, image_size, seconds_to_load);
    }

    private void fetchTranslations() {
        this.adapter.setTranslationLoadingState(true);
        new MessageTranslationRequest(LocaleUtil.getCurrentDeviceLocale(getContext()).getLanguage(), this.thread.getId(), this.adapter.getLastMessageTimeStamp()).withListener((Observer) this.messageTranslationResponseRequestListener).execute(this.requestManager);
    }

    @Subscribe
    public void onMessageReceived(MessageReceivedEvent event) {
        if (this.accountManager.getCurrentUserId() != event.post.getUserId()) {
            addPost(event.threadId, event.post, false, null);
        }
    }

    @Subscribe
    public void onMessageSync(MessageSyncEvent event) {
        if (event.appliesToThread(this.threadId)) {
            reloadThread();
        }
    }

    @Subscribe
    public void onMessageSend(MessageSendEvent event) {
        addPost(event.threadId, event.message, true, Long.valueOf(event.offlinePostId));
        if (event.message.getSendState() == SendState.Failed && this.thread.getBlock() != null && this.thread.getBlock().isActive()) {
            ThreadNotSentFragment dialog = new ThreadNotSentFragment();
            dialog.setTargetFragment(this, 148);
            dialog.show(getFragmentManager(), (String) null);
        }
    }

    private void addPost(long threadId2, Post post, boolean outgoing, Long offlinePostId) {
        if (this.thread != null && this.thread.getId() == threadId2) {
            this.thread.addPost(post, outgoing, offlinePostId);
            this.adapter.setPosts(this.thread.getPosts());
            if (!outgoing) {
                this.messagingRequestFactory.markThreadRead(this.inboxType, this.thread);
            }
        } else if (threadId2 == this.threadId) {
            Log.w(TAG, "Skipping an " + (outgoing ? "send" : "received") + " message event that matches this thread because thread is not loaded yet");
        }
    }

    private void scrollToMostRecentMessage() {
        this.recyclerView.smoothScrollToPosition(0);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.MessageThread;
    }

    public Strap getNavigationTrackingParams() {
        return this.inboxType.addLoggingParams(super.getNavigationTrackingParams()).mo11638kv("message_thread_id", this.threadId);
    }

    /* access modifiers changed from: private */
    public void goToBooking() {
        this.actionButton.setLoading();
        ListingRequest.forListingId(this.thread.getListing().getId()).withListener((Observer) this.startBookingActivityListingListner).execute(this.requestManager);
    }

    private boolean isMyProfile(Thread thread2) {
        User currentUser = this.mAccountManager.getCurrentUser();
        return currentUser != null && currentUser.getId() == thread2.getOtherUser().getId();
    }

    private void handleCohostingDeepLinking() {
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        long currentUserId = this.mAccountManager.getCurrentUserId();
        long otherUserId = this.thread.getOtherUser().getId();
        CohostedListingsRequest requestAsOwner = CohostedListingsRequest.forUsers(currentUserId, otherUserId);
        CohostedListingsRequest requestAsCohost = CohostedListingsRequest.forUsers(otherUserId, currentUserId);
        requests.add(requestAsOwner);
        requests.add(requestAsCohost);
        new AirBatchRequest(requests, this.batchCohostedListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void onFetchCohostedListingsSuccess(AirBatchResponse batchResponse) {
        this.reloadOnResume = true;
        FluentIterable<Listing> listings = batchResponse.filterResponses(CohostedListingsResponse.class).transformAndConcat(ThreadFragment$$Lambda$28.lambdaFactory$());
        Check.state(!listings.isEmpty());
        if (listings.size() == 1) {
            startActivityForCohostingWithSingleListing(CohostingDisplayUtil.getListingId(this.thread), this.thread.getOtherUser().getId());
        } else {
            startActivityForCohostingWithMultipleListings(Lists.newArrayList((Iterable<? extends E>) listings));
        }
    }

    private void startActivityForCohostingWithSingleListing(long listingId, long otherUserId) {
        startActivity(CohostingIntents.intentForListingManagerDeepLink(getContext(), listingId, otherUserId, CohostingDeepLink.ListingManager));
    }

    private void startActivityForCohostingWithMultipleListings(ArrayList<Listing> listings) {
        startActivity(ModalActivity.intentForFragment(getContext(), CohostingListingPickerFragment.create(listings, this.thread.getOtherUser().getFirstName(), this.thread.getOtherUser().getId())));
    }

    /* access modifiers changed from: private */
    public void toggleArchived() {
        this.requestManager.execute(this.messagingRequestFactory.archiveThread(this.inboxType, this.thread, !this.thread.isArchived()).withListener((Observer) this.archiveListener));
    }

    private void toggleArchiveConfirmationSnackBar(int titleRes, int orientation, int theme) {
        new SnackbarWrapper().view(getView()).title(titleRes, false).orientation(orientation).duration(-1).buildAndShow(theme);
    }
}
