package com.airbnb.android.lib.fragments.inbox;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.MagicalTripAttachment;
import com.airbnb.android.core.models.MagicalTripAttachmentDetails;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.utils.ThreadUtils;
import com.airbnb.android.core.utils.UserUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.inbox.utils.HostThreadDisplayUtils;
import com.airbnb.android.lib.fragments.inbox.utils.MagicalTripsDisplayUtil;
import com.airbnb.android.lib.viewcomponents.viewmodels.ThreadPreviewEpoxyModel_;
import com.airbnb.android.superhero.SuperHeroMessage;
import com.airbnb.android.superhero.SuperHeroMessage.Status;
import com.airbnb.epoxy.EpoxyModel;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ThreadPreviewModelFactory {
    private static final int SUPERHERO_ID = -1111;

    public static ThreadPreviewEpoxyModel_ create(Context context, User currentUser, InboxType inboxType, Thread thread, ThreadClickListener clickListener, boolean isSwipeToToggleMessageArchiveStateEnabled) {
        ThreadPreviewEpoxyModel_ partialModel = createPartialModel(thread, clickListener, inboxType, isSwipeToToggleMessageArchiveStateEnabled);
        switch (thread.getThreadType()) {
            case PlaceBooking:
                if (inboxType.isHostMode()) {
                    return createHostModel(context, currentUser, thread, partialModel);
                }
                return createGuestModel(context, currentUser, thread, partialModel);
            case TripDirect:
                return createMagicalTripsDirectModel(context, thread, partialModel);
            case TripGroup:
                return createMagicalTripsGroupModel(context, currentUser, thread, partialModel);
            case HelpThread:
                return createHelpThreadModel(context, thread, partialModel);
            case SupportMessagingThread:
                return createSupportMessagingThreadModel(context, thread, partialModel);
            case RestaurantThread:
                return createRestaurantThreadModel(context, thread, partialModel);
            case Cohost:
                return createGuestModel(context, currentUser, thread, partialModel);
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(thread.getThreadType()));
                return createGuestModel(context, currentUser, thread, partialModel);
        }
    }

    public static void updateModel(ThreadPreviewEpoxyModel_ model, Post post) {
        model.showUnread(true).subtitleText(post.getMessage());
    }

    private static ThreadPreviewEpoxyModel_ createMagicalTripsGroupModel(Context context, User currentUser, Thread thread, ThreadPreviewEpoxyModel_ partialModel) {
        MagicalTripAttachment trip = thread.getAttachment();
        List<User> orderedUsers = MagicalTripsDisplayUtil.getOrderedUsers(thread, currentUser);
        return partialModel.imageUrls(FluentIterable.from((Iterable<E>) orderedUsers).transform(ThreadPreviewModelFactory$$Lambda$1.lambdaFactory$()).toList()).titleText(trip.getDetails().getName()).subtitleText(thread.getTextPreview()).thirdRowText(UserUtils.generateNamesString(context, orderedUsers)).fourthRowText(MagicalTripsDisplayUtil.generateEventText(context, trip));
    }

    private static ThreadPreviewEpoxyModel_ createMagicalTripsDirectModel(Context context, Thread thread, ThreadPreviewEpoxyModel_ partialModel) {
        MagicalTripAttachment trip = thread.getAttachment();
        return partialModel.imageUrls(Lists.newArrayList((E[]) new String[]{thread.getOtherUser().getPictureUrl()})).titleText(thread.getOtherUser().getName()).subtitleText(thread.getTextPreview()).thirdRowText(trip.getDetails().getName()).fourthRowText(MagicalTripsDisplayUtil.generateEventText(context, trip));
    }

    private static ThreadPreviewEpoxyModel_ createHelpThreadModel(Context context, Thread thread, ThreadPreviewEpoxyModel_ partialModel) {
        return partialModel.imageRes(C0880R.C0881drawable.help_avatar).titleText(context.getString(C0880R.string.airbnb_help)).subtitleText(thread.getTextPreview());
    }

    private static ThreadPreviewEpoxyModel_ createSupportMessagingThreadModel(Context context, Thread thread, ThreadPreviewEpoxyModel_ partialModel) {
        return partialModel.imageUrls(Collections.singletonList("https://a0.muscache.com/im/pictures/5e46728b-c6a8-4a5a-9313-ca5f508a4183.jpg")).titleText(context.getString(C0880R.string.support_messaging)).subtitleText(thread.getTextPreview());
    }

    private static ThreadPreviewEpoxyModel_ createRestaurantThreadModel(Context context, Thread thread, ThreadPreviewEpoxyModel_ partialModel) {
        MagicalTripAttachmentDetails details = thread.getAttachment().getDetails();
        return partialModel.imageUrls(Collections.singletonList(details.getImageUrl())).titleText(details.getName()).subtitleText(thread.getTextPreview());
    }

    private static ThreadPreviewEpoxyModel_ createHostModel(Context context, User currentUser, Thread thread, ThreadPreviewEpoxyModel_ partialModel) {
        return partialModel.imageUrls(Lists.newArrayList((E[]) new String[]{thread.getOtherUser().getPictureUrl()})).titleText(HostThreadDisplayUtils.calculateTitleText(context, thread, currentUser)).subtitleText(HostThreadDisplayUtils.calculatePreviewText(context, thread)).thirdRowText(HostThreadDisplayUtils.calculateReservationText(context, thread)).fourthRowText(HostThreadDisplayUtils.calculateListingText(context, thread.getListing())).actionButtonTextRes(C0880R.string.button_text_to_write_review).hideProfilePhoto(FeatureToggles.hideGuestProfilePhoto(thread.getReservationStatus())).profilePlaceholderText(thread.getOtherUser().getHiddenProfileName()).showActionButton(thread.needsReview());
    }

    private static ThreadPreviewEpoxyModel_ createGuestModel(Context context, User currentUser, Thread thread, ThreadPreviewEpoxyModel_ partialModel) {
        List<User> orderedUsers = ThreadUtils.getOrderedUsersWithoutCurrentUser(thread, currentUser);
        return partialModel.imageUrls(FluentIterable.from((Iterable<E>) orderedUsers).transform(ThreadPreviewModelFactory$$Lambda$2.lambdaFactory$()).toList()).titleText(UserUtils.generateNamesString(context, orderedUsers)).subtitleText(thread.getTextPreview(context, thread.getOtherUser().getName())).thirdRowText(calculateListingName(thread)).fourthRowText(calculateGuestReservationText(context, thread)).actionButtonTextRes(C0880R.string.button_text_to_write_review).showActionButton(thread.needsReview());
    }

    public static ThreadPreviewEpoxyModel_ createSuperHeroModel(Context context, SuperHeroMessage messageToPreview, OnClickListener clickListener) {
        return new ThreadPreviewEpoxyModel_(-1111).timeAgo(messageToPreview.starts_at()).showUnread(messageToPreview.statusEnum() == Status.TRIGGERED).imageRes(C0880R.C0881drawable.super_hero_profile).clickListener(clickListener).titleText(context.getResources().getString(C0880R.string.superhero_name)).subtitleText(messageToPreview.firstMessage());
    }

    public static boolean isSuperHeroModel(EpoxyModel<?> model) {
        return (model instanceof ThreadPreviewEpoxyModel_) && model.mo11715id() == -1111;
    }

    public static boolean doModelsMatchThreads(FluentIterable<EpoxyModel<?>> models, List<Thread> threads) {
        if (models.size() != threads.size()) {
            return false;
        }
        return models.allMatch(ThreadPreviewModelFactory$$Lambda$3.lambdaFactory$(threads.iterator()));
    }

    /* access modifiers changed from: private */
    public static boolean doesModelMatchThreadAndLastUpdated(EpoxyModel<?> model, Thread thread) {
        return (model instanceof ThreadPreviewEpoxyModel_) && thread.getId() == model.mo11715id() && thread.getLastMessageAt().equals(((ThreadPreviewEpoxyModel_) model).timeAgo());
    }

    private static ThreadPreviewEpoxyModel_ createPartialModel(final Thread thread, final ThreadClickListener clickListener, InboxType inboxType, boolean isSwipeToToggleMessageArchiveStateEnabled) {
        ThreadPreviewEpoxyModel_ model = new ThreadPreviewEpoxyModel_(thread.getId()).timeAgo(thread.getLastMessageAt()).showUnread(thread.isUnread());
        if (clickListener != null) {
            model.clickListener(ThreadPreviewModelFactory$$Lambda$4.lambdaFactory$(clickListener, thread)).longClickListener(ThreadPreviewModelFactory$$Lambda$5.lambdaFactory$(clickListener, thread)).actionButtonClickListener(ThreadPreviewModelFactory$$Lambda$6.lambdaFactory$(clickListener, thread));
        }
        if (isSwipeToToggleMessageArchiveStateEnabled) {
            model.swipeEnabled(true);
            model.swipeTextRes(inboxType.archived ? C0880R.string.unarchive : C0880R.string.archive);
            model.swipeListener(new SimpleSwipeListener() {
                public void onOpen(SwipeLayout layout) {
                    super.onOpen(layout);
                    clickListener.onSwipe(thread);
                    layout.close();
                }
            });
        }
        return model;
    }

    private static CharSequence calculateGuestReservationText(Context context, Thread thread) {
        if (!thread.hasDates() || !thread.hasListing()) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(C0880R.string.date_name_format), Locale.getDefault());
        AirDate startDate = thread.getStartDate();
        AirDate endDate = thread.getEndDate();
        String dateRange = context.getString(C0880R.string.reservation_date_range, new Object[]{startDate.formatDate((DateFormat) dateFormat), endDate.formatDate((DateFormat) dateFormat)});
        String detailString = context.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{SpannableUtils.COLORED_SUBSTRING_TOKEN, dateRange});
        ReservationStatusDisplay display = ReservationStatusDisplay.forGuest(thread);
        return SpannableUtils.makeColoredSubstring(detailString, display.getString(context), display.getColor(context));
    }

    private static String calculateListingName(Thread thread) {
        return thread.hasListing() ? thread.getListing().getName() : "";
    }
}
