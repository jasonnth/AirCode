package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.generated.GenThread;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Thread extends GenThread {
    public static final Creator<Thread> CREATOR = new Creator<Thread>() {
        public Thread[] newArray(int size) {
            return new Thread[size];
        }

        public Thread createFromParcel(Parcel source) {
            Thread object = new Thread();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean hasSpecialOffer() {
        return getSpecialOffer() != null;
    }

    public void addPost(Post post, boolean outgoing) {
        addPost(post, outgoing, null);
    }

    public void addPost(Post post, boolean outgoing, Long offlineId) {
        if (this.mPosts == null) {
            this.mPosts = Lists.newArrayList();
        }
        ListUtils.removeAllWhere(this.mPosts, Thread$$Lambda$1.lambdaFactory$(post, offlineId));
        this.mPosts.add(post);
        updatePostOrdering();
        this.mTextPreview = post.getMessage();
        setUnread(!outgoing);
    }

    static /* synthetic */ boolean lambda$addPost$0(Post post, Long offlineId, Post p) {
        return p.getId() == post.getId() || (offlineId != null && p.getId() == offlineId.longValue());
    }

    public void mergeOlderPosts(List<Post> posts) {
        if (this.mPosts == null) {
            this.mPosts = posts;
            return;
        }
        Set<Long> postIds = FluentIterable.from((Iterable<E>) this.mPosts).transform(Thread$$Lambda$2.lambdaFactory$()).toSet();
        for (Post post : posts) {
            if (!postIds.contains(Long.valueOf(post.getId()))) {
                this.mPosts.add(post);
            }
        }
        updatePostOrdering();
    }

    private void updatePostOrdering() {
        Collections.sort(this.mPosts, Thread$$Lambda$3.lambdaFactory$());
    }

    public ReservationStatus getReservationStatus() {
        return Reservation.calculateCorrectedReservationState(this.mReservationStatus, getInquiryReservation() != null && getInquiryReservation().isGuestPendingIdentityVerification());
    }

    public boolean hasDates() {
        return (getStartDate() == null || getEndDate() == null) ? false : true;
    }

    public boolean hasListing() {
        return getListing() != null;
    }

    public boolean hasActiveInquiry() {
        return getActiveInquiry() != null;
    }

    public int getNights() {
        Check.state(hasDates());
        return getStartDate().getDaysUntil(getEndDate());
    }

    public String getReservationConfirmationCode() {
        if (getInquiryReservation() != null) {
            return getInquiryReservation().getConfirmationCode();
        }
        return null;
    }

    @JsonProperty("status")
    public void setStatus(String statusString) {
        this.mReservationStatus = ReservationStatus.findStatus(statusString);
    }

    @JsonProperty("business_purpose")
    public void setThreadType(String conversationTypeKey) {
        super.setThreadType(ThreadType.fromKey(conversationTypeKey));
    }

    public ThreadType getThreadType() {
        if (this.mThreadType == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new NullPointerException("Thread type is null, using fallback"));
            this.mThreadType = ThreadType.Unknown;
        }
        return super.getThreadType();
    }

    public boolean needsReview() {
        return getReservationStatus() == ReservationStatus.Accepted && isPendingReview() && daysLeftToReview() >= 0;
    }

    public int daysLeftToReview() {
        return AirDate.today().getDaysUntil(getEndDate().plusDays(14));
    }

    public String getTextPreview(Context context, String guestName) {
        if (!TextUtils.isEmpty(this.mTextPreview)) {
            return this.mTextPreview;
        }
        return context.getResources().getString(C0716R.string.conversation_with_someone, new Object[]{guestName});
    }
}
