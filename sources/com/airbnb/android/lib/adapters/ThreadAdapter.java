package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.ThreadType;
import com.airbnb.android.core.models.TranslatedMessage;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.presenters.GuestDetailsPresenter;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.InlineCautionEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.InlineCautionEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineContextEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.InlineContextEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MessageItemEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TranslationExpoyModel;
import com.airbnb.android.core.viewcomponents.models.TranslationExpoyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.viewcomponents.viewmodels.MessageImageEpoxyModel_;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.messaging.MessageImage.MessageImageOnLoadedListener;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadAdapter extends AirEpoxyAdapter {
    AirbnbAccountManager accountManager;
    private final SimpleDateFormat checkInOutDateFormat;
    private final Context context;
    CurrencyFormatter currencyHelper;
    DebugSettings debugSettings;
    private final InboxType inboxType;
    private boolean isShowingTranslatedMessages = false;
    private final MessageImageOnLoadedListener messageImageOnLoadedListener;
    private TranslationExpoyModel messageTranslationModel;
    private final OnCreateContextMenuListener onCreateContextMenuListener;
    private final OnMessageItemClickListener onMessageItemClickListener;
    private final OnTranslateMessageClickListener onTranslateMessageClickListener;
    SharedPrefsHelper sharedPrefsHelper;
    private Thread thread;
    private Map<Long, User> threadParticipantMapping;

    public interface OnMessageItemClickListener {
        void onImageAttachmentClicked(Post post);

        void onMessageItemClicked(Post post);
    }

    public interface OnTranslateMessageClickListener {
        void onShowOriginalClicked();

        void onTranslateClicked();
    }

    public ThreadAdapter(Context context2, InboxType inboxType2, OnMessageItemClickListener onMessageItemClickListener2, MessageImageOnLoadedListener messageImageOnLoadedListener2, OnTranslateMessageClickListener onTranslateMessageClickListener2, OnCreateContextMenuListener onCreateContextMenuListener2) {
        ((AirbnbGraph) AirbnbApplication.instance(context2).component()).inject(this);
        this.context = context2;
        this.inboxType = inboxType2;
        this.onMessageItemClickListener = onMessageItemClickListener2;
        this.messageImageOnLoadedListener = messageImageOnLoadedListener2;
        this.onTranslateMessageClickListener = onTranslateMessageClickListener2;
        this.onCreateContextMenuListener = onCreateContextMenuListener2;
        this.checkInOutDateFormat = new SimpleDateFormat(context2.getString(C0880R.string.date_name_format));
    }

    public void setData(Thread thread2) {
        this.thread = thread2;
        this.threadParticipantMapping = FluentIterable.from((Iterable<E>) thread2.getUsers()).uniqueIndex(ThreadAdapter$$Lambda$1.lambdaFactory$());
        setPosts(thread2.getPosts());
    }

    public void setPosts(List<Post> posts) {
        if (this.thread.getOtherUser() == null) {
            throw new IllegalStateException("setData() must be called before setPosts()");
        }
        this.models.clear();
        addTranslationButtonIfNeeded();
        long offlineTransactionCautionInsertedId = this.sharedPrefsHelper.getOfflineTransactionCautionInsertedId(this.thread.getId());
        if (offlineTransactionCautionInsertedId == -1 && !posts.isEmpty()) {
            offlineTransactionCautionInsertedId = ((Post) posts.get(0)).getId();
        }
        for (int i = 0; i < posts.size(); i++) {
            Post currentPost = (Post) posts.get(i);
            if (currentPost.getId() == offlineTransactionCautionInsertedId) {
                InlineCautionEpoxyModel inlineCautionEpoxyModel = getInlineCautionIfNeeded();
                if (inlineCautionEpoxyModel != null && ChinaUtils.enableOfflinePaymentEducation()) {
                    this.models.add(inlineCautionEpoxyModel);
                    this.sharedPrefsHelper.setOfflineTransactionCautionInsertedId(this.thread.getId(), offlineTransactionCautionInsertedId);
                }
            }
            boolean showProfileHalo = !currentPost.didSendFail();
            if (i > 0) {
                Post nextPost = (Post) posts.get(i - 1);
                showProfileHalo &= currentPost.getUserId() != nextPost.getUserId() || nextPost.didSendFail() || (!this.models.isEmpty() && (this.models.get(this.models.size() + -1) instanceof InlineContextEpoxyModel));
            }
            AirEpoxyModel<?> messageItemEpoxyModel = getMessageItemIfNeeded(currentPost, showProfileHalo);
            if (messageItemEpoxyModel != null) {
                this.models.add(messageItemEpoxyModel);
            }
            InlineContextEpoxyModel inlineContextEpoxyModel = getInlineContextIfNeeded(currentPost);
            if (inlineContextEpoxyModel != null) {
                this.models.add(inlineContextEpoxyModel);
            }
        }
        if (this.thread.getThreadType() == ThreadType.TripGroup && this.inboxType != InboxType.ExperienceHost) {
            this.models.add(new InlineContextEpoxyModel_().status(this.context.getString(C0880R.string.magical_trip_thread_greeting, new Object[]{this.thread.getAttachment().getDetails().getName(), this.thread.getOtherUser().getName()})).m4860id((CharSequence) "thread_header"));
        }
        notifyDataSetChanged();
    }

    public int getModelPosition(long postId) {
        for (int i = 0; i < this.models.size(); i++) {
            if (((EpoxyModel) this.models.get(i)).mo11715id() == postId) {
                return i;
            }
        }
        return -1;
    }

    private InlineCautionEpoxyModel getInlineCautionIfNeeded() {
        if (this.inboxType.isGuestMode()) {
            return new InlineCautionEpoxyModel_().contentText(this.context.getString(C0880R.string.thread_inline_caution_offline_payment_content)).actionText(this.context.getString(C0880R.string.thread_inline_caution_offline_payment_action)).actionListener(ThreadAdapter$$Lambda$2.lambdaFactory$(this));
        }
        return null;
    }

    private InlineContextEpoxyModel getInlineContextIfNeeded(Post currentPost) {
        if (!TextUtils.isEmpty(currentPost.getInlineStatusText())) {
            return getHostInlineContextIfNeeded(currentPost);
        }
        ReservationStatus status = currentPost.getStatus();
        if (currentPost.hasSpecialOffer()) {
            return getSpecialOfferOrPreApprovalInlineContext(currentPost);
        }
        if (currentPost.hasDates() && (status == ReservationStatus.Inquiry || status == null)) {
            return getInquiryInlineContext(currentPost);
        }
        if (status == ReservationStatus.Denied) {
            return getDeclinedInquiryInlineContext(currentPost);
        }
        if (status == null || status == ReservationStatus.Unknown) {
            return null;
        }
        return getOtherInlineContext(currentPost);
    }

    private InlineContextEpoxyModel getHostInlineContextIfNeeded(Post currentPost) {
        String statusDetailsText;
        String formattedNumGuests = GuestDetailsPresenter.formatGuestsString(this.context, currentPost.getGuestDetails(), currentPost.getNumberOfGuests());
        if (currentPost.hasCompleteDates()) {
            statusDetailsText = getFormattedStatusDetails(formattedNumGuests, currentPost.getCheckinDate().formatDate((DateFormat) this.checkInOutDateFormat), currentPost.getCheckoutDate().formatDate((DateFormat) this.checkInOutDateFormat), null);
        } else {
            statusDetailsText = formattedNumGuests;
        }
        return getInlineContext(currentPost, currentPost.getInlineStatusText(), statusDetailsText);
    }

    private AirEpoxyModel<?> getMessageItemIfNeeded(Post currentPost, boolean showProfileHalo) {
        String message = currentPost.getMessage();
        if (isImagePost(currentPost)) {
            return getMessageImage(currentPost, showProfileHalo);
        }
        if (!TextUtils.isEmpty(message)) {
            return getMessageItem(currentPost, showProfileHalo);
        }
        if (!Strings.isNullOrEmpty(currentPost.getAttachmentType())) {
            return getFallbackMessageItem(currentPost, showProfileHalo);
        }
        return null;
    }

    private boolean isImagePost(Post post) {
        return (post.getAttachmentImages() == null || post.getAttachmentImages().size() == 0) ? false : true;
    }

    private InlineContextEpoxyModel getSpecialOfferOrPreApprovalInlineContext(Post currentPost) {
        SpecialOffer specialOffer = currentPost.getSpecialOffer();
        String price = this.currencyHelper.formatNativeCurrency((double) specialOffer.getPriceNative(), true);
        int numGuests = specialOffer.getNumberOfGuests();
        String formattedNumGuests = numGuests > 0 ? this.context.getResources().getQuantityString(C0880R.plurals.x_guests, numGuests, new Object[]{Integer.valueOf(numGuests)}) : "";
        AirDate checkInDate = specialOffer.getStartDate();
        return getInlineContext(currentPost, this.context.getString(specialOffer.isPreApproval() ? C0880R.string.ro_status_pre_approval : C0880R.string.ro_status_special_offer), getFormattedStatusDetails(formattedNumGuests, checkInDate.formatDate((DateFormat) this.checkInOutDateFormat), checkInDate.plusDays(specialOffer.getNights()).formatDate((DateFormat) this.checkInOutDateFormat), price));
    }

    private InlineContextEpoxyModel getInquiryInlineContext(Post currentPost) {
        String statusText;
        String formattedCheckInDate = currentPost.getCheckinDate().formatDate((DateFormat) this.checkInOutDateFormat);
        String formattedCheckOutDate = currentPost.getCheckoutDate().formatDate((DateFormat) this.checkInOutDateFormat);
        String formattedNumGuests = GuestDetailsPresenter.formatGuestsString(this.context, currentPost.getGuestDetails(), currentPost.getNumberOfGuests());
        if (this.thread.hasListing()) {
            statusText = this.context.getString(C0880R.string.ro_status_inquiry_for_listing, new Object[]{this.thread.getListing().getName()});
        } else {
            statusText = this.context.getString(C0880R.string.ro_status_inquiry);
        }
        return getInlineContext(currentPost, statusText, getFormattedStatusDetails(formattedNumGuests, formattedCheckInDate, formattedCheckOutDate, null));
    }

    private InlineContextEpoxyModel getDeclinedInquiryInlineContext(Post currentPost) {
        return getInlineContext(currentPost, this.context.getString(C0880R.string.ro_status_declined_inquiry), "");
    }

    private InlineContextEpoxyModel getOtherInlineContext(Post currentPost) {
        return getInlineContext(currentPost, ReservationStatusDisplay.forStatus(currentPost.getStatus()).getString(this.context), "");
    }

    private String getFormattedStatusDetails(String formattedNumGuests, String formattedCheckInDate, String formattedCheckOutDate, String price) {
        Resources resources = this.context.getResources();
        int i = C0880R.string.ro_status_details_num_guests_checkin_checkout_total_price;
        Object[] objArr = new Object[4];
        objArr[0] = formattedNumGuests + (!TextUtils.isEmpty(formattedNumGuests) ? ", " : "");
        objArr[1] = formattedCheckInDate;
        objArr[2] = formattedCheckOutDate;
        objArr[3] = price != null ? ", " + price : "";
        return resources.getString(i, objArr);
    }

    private String getPostStatus(Post currentPost) {
        String elapsedTime;
        StringBuilder sb = new StringBuilder();
        if (currentPost.didSendFail()) {
            sb.append(this.context.getString(C0880R.string.ro_message_failed));
        } else {
            User postUser = getPostUser(currentPost);
            if (currentPost.isSendInProgress()) {
                elapsedTime = this.context.getResources().getString(C0880R.string.sending);
            } else {
                elapsedTime = currentPost.getCreatedAt().getElapsedTime(this.context);
            }
            sb.append(elapsedTime);
            if (currentPost.isSentFromMobile()) {
                sb.append(this.context.getString(C0880R.string.ro_message_from_mobile));
            }
            if (!isSelf(currentPost) && this.threadParticipantMapping.size() > 2) {
                sb.append(this.context.getString(C0880R.string.bullet_with_space));
                sb.append(postUser.getName());
                if (currentPost.isFirstPostOfTheSender() && this.thread.getListing().isUserCohost(postUser.getId())) {
                    User primaryHost = this.thread.getListing().getPrimaryHost();
                    sb.append(this.context.getString(C0880R.string.attributes_separator));
                    sb.append(this.context.getString(C0880R.string.ro_message_cohost_introduction, new Object[]{primaryHost.getName()}));
                }
            }
        }
        return sb.toString();
    }

    private User getPostUser(Post post) {
        if (isSelf(post)) {
            return (User) Check.notNull(this.accountManager.getCurrentUser());
        }
        if (this.threadParticipantMapping.containsKey(Long.valueOf(post.getUserId()))) {
            return (User) this.threadParticipantMapping.get(Long.valueOf(post.getUserId()));
        }
        return this.thread.getOtherUser();
    }

    private boolean isSelf(Post post) {
        return post.getUserId() == this.accountManager.getCurrentUserId();
    }

    private InlineContextEpoxyModel getInlineContext(Post currentPost, CharSequence statusText, CharSequence statusDetailsText) {
        return new InlineContextEpoxyModel_().status(statusText).statusDetails(statusDetailsText).m4858id(-currentPost.getId());
    }

    private AirEpoxyModel<?> getMessageItem(Post currentPost, boolean showProfileHalo) {
        return setUpMessageItem(currentPost, showProfileHalo).messageText(currentPost.getMessage());
    }

    private AirEpoxyModel<?> getMessageImage(Post currentPost, boolean showProfileHalo) {
        User user;
        MessageImageEpoxyModel_ messageModel = new MessageImageEpoxyModel_();
        if (isSelf(currentPost)) {
            user = this.accountManager.getCurrentUser();
            if (showProfileHalo) {
                messageModel.profileImageClickListener(ThreadAdapter$$Lambda$3.lambdaFactory$(this));
            }
            messageModel.sender();
        } else {
            user = getPostUser(currentPost);
            if (showProfileHalo) {
                messageModel.profileImageClickListener(ThreadAdapter$$Lambda$4.lambdaFactory$(this, user));
            }
            messageModel.receiver();
        }
        if (FeatureToggles.hideGuestProfilePhoto(this.thread.getReservationStatus())) {
            messageModel.profilePlaceholderText(user.getHiddenProfileName()).hideProfilePhoto(true);
        } else {
            messageModel.profileImageUrl(user.getPictureUrl());
        }
        if (currentPost.shouldShowFlagged()) {
            messageModel.showReportLink(true).reported(true).reportTextClickListener(ThreadAdapter$$Lambda$5.lambdaFactory$(this, messageModel));
        } else {
            messageModel.showReportLink(false);
        }
        messageModel.sendFailed(currentPost.didSendFail()).statusText(getPostStatus(currentPost)).attachmentImages(currentPost.getAttachmentImages()).imageAttachmentClickListener(ThreadAdapter$$Lambda$6.lambdaFactory$(this, currentPost)).imageAttachmentLongClickListener(ThreadAdapter$$Lambda$7.lambdaFactory$()).onImageLoadedListener(this.messageImageOnLoadedListener).contextMenuListener(this.onCreateContextMenuListener).post(currentPost).m6179id(currentPost.getId());
        return messageModel;
    }

    static /* synthetic */ void lambda$getMessageImage$3(ThreadAdapter threadAdapter, MessageImageEpoxyModel_ messageModel, View view) {
        messageModel.reported(!messageModel.reported());
        threadAdapter.notifyModelChanged(messageModel);
    }

    private AirEpoxyModel<?> getFallbackMessageItem(Post currentPost, boolean showProfileHalo) {
        return setUpMessageItem(currentPost, showProfileHalo).messageText(this.context.getString(C0880R.string.messaging_content_not_supported_text) + " " + currentPost.getAttachmentFallbackUrl());
    }

    private MessageItemEpoxyModel_ setUpMessageItem(Post currentPost, boolean showProfileHalo) {
        User user;
        MessageItemEpoxyModel_ messageModel = new MessageItemEpoxyModel_();
        if (isSelf(currentPost)) {
            user = this.accountManager.getCurrentUser();
            if (showProfileHalo) {
                messageModel.profileImageClickListener(ThreadAdapter$$Lambda$8.lambdaFactory$(this)).senderWithTail();
            } else {
                messageModel.senderNoTail();
            }
        } else {
            user = getPostUser(currentPost);
            if (showProfileHalo) {
                messageModel.receiverWithTail();
                if (!this.thread.getThreadType().isRestaurantThread()) {
                    messageModel.profileImageClickListener(ThreadAdapter$$Lambda$9.lambdaFactory$(this, user));
                }
            } else {
                messageModel.receiverNoTail();
            }
        }
        if (FeatureToggles.hideGuestProfilePhoto(this.thread.getReservationStatus())) {
            messageModel.profilePlaceholderText(user.getHiddenProfileName()).hideProfilePhoto(true);
        } else if (this.thread.getThreadType().isRestaurantThread()) {
            messageModel.profileImageUrl(this.thread.getAttachment().getDetails().getImageUrl());
        } else {
            messageModel.profileImageUrl(user.getPictureUrl());
        }
        if (currentPost.didSendFail()) {
            messageModel.sendFailed(true).clickListener(ThreadAdapter$$Lambda$10.lambdaFactory$(this, currentPost));
        }
        if (currentPost.shouldShowFlagged()) {
            messageModel.showReportLink(true).reported(true).reportTextClickListener(ThreadAdapter$$Lambda$11.lambdaFactory$(this, messageModel));
        } else {
            messageModel.showReportLink(false);
        }
        messageModel.statusText(getPostStatus(currentPost)).withLinks(true).longClickListener(ThreadAdapter$$Lambda$12.lambdaFactory$()).contextMenuListener(this.onCreateContextMenuListener).post(currentPost).m5146id(currentPost.getId());
        return messageModel;
    }

    static /* synthetic */ void lambda$setUpMessageItem$9(ThreadAdapter threadAdapter, MessageItemEpoxyModel_ messageModel, View view) {
        messageModel.reported(!messageModel.reported());
        threadAdapter.notifyModelChanged(messageModel);
    }

    public void setTranslationLoadingState(boolean shouldShow) {
        this.messageTranslationModel.setIsLoading(shouldShow);
        notifyModelChanged(this.messageTranslationModel);
    }

    public String getLastMessageTimeStamp() {
        if (this.thread != null) {
            return this.thread.getLastMessageAt().getIsoDateStringUTC();
        }
        return null;
    }

    public void onTranslationFailed() {
        this.messageTranslationModel.status(SpannableUtils.makeColoredSubstring(this.context.getString(C0880R.string.message_translation_unable_to_translate, new Object[]{SpannableUtils.COLORED_SUBSTRING_TOKEN}), this.context.getString(C0880R.string.message_translation_unable_to_translate_retry), ContextCompat.getColor(this.context, C0716R.color.c_arches)));
        this.messageTranslationModel.setIsLoading(false);
        notifyModelChanged(this.messageTranslationModel);
    }

    public void setTranslatedMessages(List<TranslatedMessage> translatedMessageList) {
        Map<Long, String> map = new HashMap<>();
        for (TranslatedMessage message : translatedMessageList) {
            map.put(Long.valueOf(message.getId()), message.getTranslatedMessage());
        }
        updateModelsMessages(map);
        this.messageTranslationModel.toggleTranslationView(this.context.getString(C0880R.string.message_translation_show_original_thread), true, false);
        notifyModelChanged(this.messageTranslationModel);
        this.isShowingTranslatedMessages = true;
    }

    public void showOriginalMessages() {
        HashMap<Long, String> map = new HashMap<>();
        for (Post p : this.thread.getPosts()) {
            map.put(Long.valueOf(p.getId()), p.getMessage());
        }
        updateModelsMessages(map);
        this.messageTranslationModel.toggleTranslationView(getDefaultTranslationButtonStatusMessage(), false, false);
        notifyModelChanged(this.messageTranslationModel);
        this.isShowingTranslatedMessages = false;
    }

    private String getDefaultTranslationButtonStatusMessage() {
        try {
            return this.context.getString(C0880R.string.message_translation_translate_thread_to, new Object[]{LocaleUtil.getDeviceDisplayLanguage(this.context)});
        } catch (RuntimeException e) {
            BugsnagWrapper.throwOrNotify(e);
            return this.context.getString(C0880R.string.message_translation_translate_thread);
        }
    }

    private void addTranslationButtonIfNeeded() {
        if (this.thread.isShouldTranslate()) {
            this.messageTranslationModel = new TranslationExpoyModel_().toggleTranslationView(getDefaultTranslationButtonStatusMessage(), false, false).clickListener(ThreadAdapter$$Lambda$13.lambdaFactory$(this)).m5724id((CharSequence) "message_translations_model");
            this.models.add(this.messageTranslationModel);
        }
    }

    static /* synthetic */ void lambda$addTranslationButtonIfNeeded$11(ThreadAdapter threadAdapter, View v) {
        if (threadAdapter.isShowingTranslatedMessages) {
            threadAdapter.onTranslateMessageClickListener.onShowOriginalClicked();
        } else {
            threadAdapter.onTranslateMessageClickListener.onTranslateClicked();
        }
    }

    private void updateModelsMessages(Map<Long, String> map) {
        for (int i = 0; i < this.models.size(); i++) {
            EpoxyModel currentModel = (EpoxyModel) this.models.get(i);
            if (currentModel instanceof MessageItemEpoxyModel_) {
                MessageItemEpoxyModel_ currentMessage = (MessageItemEpoxyModel_) currentModel;
                if (map.containsKey(Long.valueOf(currentMessage.mo11715id()))) {
                    currentMessage.messageText((CharSequence) map.get(Long.valueOf(currentMessage.mo11715id())));
                }
                notifyModelChanged(currentMessage);
            }
        }
    }
}
