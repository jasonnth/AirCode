package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.ListingSummary;
import com.airbnb.android.core.models.MagicalTripAttachment;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.ReservationSummary;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.ThreadType;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.UserBlock;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenThread implements Parcelable {
    @JsonProperty("active_inquiry")
    protected Post mActiveInquiry;
    @JsonProperty("archived")
    protected boolean mArchived;
    @JsonProperty("attachment")
    protected MagicalTripAttachment mAttachment;
    @JsonProperty("block")
    protected UserBlock mBlock;
    @JsonProperty("blockable")
    protected boolean mBlockable;
    @JsonProperty("blockable_thread_type")
    protected boolean mBlockableThreadType;
    @JsonProperty("inquiry_checkout_date")
    protected AirDate mEndDate;
    @JsonProperty("expires_at")
    protected AirDateTime mExpiresAt;
    @JsonProperty("has_past_reservations_host")
    protected boolean mHasPastReservationsHost;
    @JsonProperty("has_pending_alteration_request")
    protected boolean mHasPendingAlterationRequest;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("inquiry_reservation")
    protected ReservationSummary mInquiryReservation;
    @JsonProperty("user_thread_updated_at")
    protected AirDateTime mLastMessageAt;
    @JsonProperty("inquiry_listing")
    protected ListingSummary mListing;
    @JsonProperty("inquiry_number_of_guests")
    protected int mNumberOfGuests;
    @JsonProperty("other_user")
    protected User mOtherUser;
    @JsonProperty("should_leave_review_reminder")
    protected boolean mPendingReview;
    @JsonProperty("posts")
    protected List<Post> mPosts;
    @JsonProperty("requires_response")
    protected boolean mRequiresResponse;
    @JsonProperty("status")
    protected ReservationStatus mReservationStatus;
    @JsonProperty("review_id")
    protected long mReviewId;
    @JsonProperty("should_translate")
    protected boolean mShouldTranslate;
    @JsonProperty("inquiry_special_offer")
    protected SpecialOffer mSpecialOffer;
    @JsonProperty("inquiry_checkin_date")
    protected AirDate mStartDate;
    @JsonProperty("text_preview")
    protected String mTextPreview;
    @JsonProperty("business_purpose")
    protected ThreadType mThreadType;
    @JsonProperty("unread")
    protected boolean mUnread;
    @JsonProperty("users")
    protected List<User> mUsers;

    protected GenThread(AirDate startDate, AirDate endDate, AirDateTime expiresAt, AirDateTime lastMessageAt, List<Post> posts, List<User> users, ListingSummary listing, MagicalTripAttachment attachment, Post activeInquiry, ReservationStatus reservationStatus, ReservationSummary inquiryReservation, SpecialOffer specialOffer, String textPreview, ThreadType threadType, User otherUser, UserBlock block, boolean unread, boolean archived, boolean blockable, boolean blockableThreadType, boolean pendingReview, boolean hasPastReservationsHost, boolean hasPendingAlterationRequest, boolean requiresResponse, boolean shouldTranslate, int numberOfGuests, long id, long reviewId) {
        this();
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mExpiresAt = expiresAt;
        this.mLastMessageAt = lastMessageAt;
        this.mPosts = posts;
        this.mUsers = users;
        this.mListing = listing;
        this.mAttachment = attachment;
        this.mActiveInquiry = activeInquiry;
        this.mReservationStatus = reservationStatus;
        this.mInquiryReservation = inquiryReservation;
        this.mSpecialOffer = specialOffer;
        this.mTextPreview = textPreview;
        this.mThreadType = threadType;
        this.mOtherUser = otherUser;
        this.mBlock = block;
        this.mUnread = unread;
        this.mArchived = archived;
        this.mBlockable = blockable;
        this.mBlockableThreadType = blockableThreadType;
        this.mPendingReview = pendingReview;
        this.mHasPastReservationsHost = hasPastReservationsHost;
        this.mHasPendingAlterationRequest = hasPendingAlterationRequest;
        this.mRequiresResponse = requiresResponse;
        this.mShouldTranslate = shouldTranslate;
        this.mNumberOfGuests = numberOfGuests;
        this.mId = id;
        this.mReviewId = reviewId;
    }

    protected GenThread() {
    }

    public AirDate getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("inquiry_checkin_date")
    public void setStartDate(AirDate value) {
        this.mStartDate = value;
    }

    public AirDate getEndDate() {
        return this.mEndDate;
    }

    @JsonProperty("inquiry_checkout_date")
    public void setEndDate(AirDate value) {
        this.mEndDate = value;
    }

    public AirDateTime getExpiresAt() {
        return this.mExpiresAt;
    }

    @JsonProperty("expires_at")
    public void setExpiresAt(AirDateTime value) {
        this.mExpiresAt = value;
    }

    public AirDateTime getLastMessageAt() {
        return this.mLastMessageAt;
    }

    @JsonProperty("user_thread_updated_at")
    public void setLastMessageAt(AirDateTime value) {
        this.mLastMessageAt = value;
    }

    public List<Post> getPosts() {
        return this.mPosts;
    }

    @JsonProperty("posts")
    public void setPosts(List<Post> value) {
        this.mPosts = value;
    }

    public List<User> getUsers() {
        return this.mUsers;
    }

    @JsonProperty("users")
    public void setUsers(List<User> value) {
        this.mUsers = value;
    }

    public ListingSummary getListing() {
        return this.mListing;
    }

    @JsonProperty("inquiry_listing")
    public void setListing(ListingSummary value) {
        this.mListing = value;
    }

    public MagicalTripAttachment getAttachment() {
        return this.mAttachment;
    }

    @JsonProperty("attachment")
    public void setAttachment(MagicalTripAttachment value) {
        this.mAttachment = value;
    }

    public Post getActiveInquiry() {
        return this.mActiveInquiry;
    }

    @JsonProperty("active_inquiry")
    public void setActiveInquiry(Post value) {
        this.mActiveInquiry = value;
    }

    public ReservationStatus getReservationStatus() {
        return this.mReservationStatus;
    }

    public ReservationSummary getInquiryReservation() {
        return this.mInquiryReservation;
    }

    @JsonProperty("inquiry_reservation")
    public void setInquiryReservation(ReservationSummary value) {
        this.mInquiryReservation = value;
    }

    public SpecialOffer getSpecialOffer() {
        return this.mSpecialOffer;
    }

    @JsonProperty("inquiry_special_offer")
    public void setSpecialOffer(SpecialOffer value) {
        this.mSpecialOffer = value;
    }

    public String getTextPreview() {
        return this.mTextPreview;
    }

    @JsonProperty("text_preview")
    public void setTextPreview(String value) {
        this.mTextPreview = value;
    }

    public ThreadType getThreadType() {
        return this.mThreadType;
    }

    @JsonProperty("business_purpose")
    public void setThreadType(ThreadType value) {
        this.mThreadType = value;
    }

    public User getOtherUser() {
        return this.mOtherUser;
    }

    @JsonProperty("other_user")
    public void setOtherUser(User value) {
        this.mOtherUser = value;
    }

    public UserBlock getBlock() {
        return this.mBlock;
    }

    @JsonProperty("block")
    public void setBlock(UserBlock value) {
        this.mBlock = value;
    }

    public boolean isUnread() {
        return this.mUnread;
    }

    @JsonProperty("unread")
    public void setUnread(boolean value) {
        this.mUnread = value;
    }

    public boolean isArchived() {
        return this.mArchived;
    }

    @JsonProperty("archived")
    public void setArchived(boolean value) {
        this.mArchived = value;
    }

    public boolean isBlockable() {
        return this.mBlockable;
    }

    @JsonProperty("blockable")
    public void setBlockable(boolean value) {
        this.mBlockable = value;
    }

    public boolean isBlockableThreadType() {
        return this.mBlockableThreadType;
    }

    @JsonProperty("blockable_thread_type")
    public void setBlockableThreadType(boolean value) {
        this.mBlockableThreadType = value;
    }

    public boolean isPendingReview() {
        return this.mPendingReview;
    }

    @JsonProperty("should_leave_review_reminder")
    public void setPendingReview(boolean value) {
        this.mPendingReview = value;
    }

    public boolean hasPastReservationsHost() {
        return this.mHasPastReservationsHost;
    }

    @JsonProperty("has_past_reservations_host")
    public void setHasPastReservationsHost(boolean value) {
        this.mHasPastReservationsHost = value;
    }

    public boolean hasPendingAlterationRequest() {
        return this.mHasPendingAlterationRequest;
    }

    @JsonProperty("has_pending_alteration_request")
    public void setHasPendingAlterationRequest(boolean value) {
        this.mHasPendingAlterationRequest = value;
    }

    public boolean isRequiresResponse() {
        return this.mRequiresResponse;
    }

    @JsonProperty("requires_response")
    public void setRequiresResponse(boolean value) {
        this.mRequiresResponse = value;
    }

    public boolean isShouldTranslate() {
        return this.mShouldTranslate;
    }

    @JsonProperty("should_translate")
    public void setShouldTranslate(boolean value) {
        this.mShouldTranslate = value;
    }

    public int getNumberOfGuests() {
        return this.mNumberOfGuests;
    }

    @JsonProperty("inquiry_number_of_guests")
    public void setNumberOfGuests(int value) {
        this.mNumberOfGuests = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getReviewId() {
        return this.mReviewId;
    }

    @JsonProperty("review_id")
    public void setReviewId(long value) {
        this.mReviewId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mEndDate, 0);
        parcel.writeParcelable(this.mExpiresAt, 0);
        parcel.writeParcelable(this.mLastMessageAt, 0);
        parcel.writeTypedList(this.mPosts);
        parcel.writeTypedList(this.mUsers);
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeParcelable(this.mAttachment, 0);
        parcel.writeParcelable(this.mActiveInquiry, 0);
        parcel.writeParcelable(this.mReservationStatus, 0);
        parcel.writeParcelable(this.mInquiryReservation, 0);
        parcel.writeParcelable(this.mSpecialOffer, 0);
        parcel.writeString(this.mTextPreview);
        parcel.writeParcelable(this.mThreadType, 0);
        parcel.writeParcelable(this.mOtherUser, 0);
        parcel.writeParcelable(this.mBlock, 0);
        parcel.writeBooleanArray(new boolean[]{this.mUnread, this.mArchived, this.mBlockable, this.mBlockableThreadType, this.mPendingReview, this.mHasPastReservationsHost, this.mHasPendingAlterationRequest, this.mRequiresResponse, this.mShouldTranslate});
        parcel.writeInt(this.mNumberOfGuests);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mReviewId);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mEndDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mExpiresAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mLastMessageAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mPosts = source.createTypedArrayList(Post.CREATOR);
        this.mUsers = source.createTypedArrayList(User.CREATOR);
        this.mListing = (ListingSummary) source.readParcelable(ListingSummary.class.getClassLoader());
        this.mAttachment = (MagicalTripAttachment) source.readParcelable(MagicalTripAttachment.class.getClassLoader());
        this.mActiveInquiry = (Post) source.readParcelable(Post.class.getClassLoader());
        this.mReservationStatus = (ReservationStatus) source.readParcelable(ReservationStatus.class.getClassLoader());
        this.mInquiryReservation = (ReservationSummary) source.readParcelable(ReservationSummary.class.getClassLoader());
        this.mSpecialOffer = (SpecialOffer) source.readParcelable(SpecialOffer.class.getClassLoader());
        this.mTextPreview = source.readString();
        this.mThreadType = (ThreadType) source.readParcelable(ThreadType.class.getClassLoader());
        this.mOtherUser = (User) source.readParcelable(User.class.getClassLoader());
        this.mBlock = (UserBlock) source.readParcelable(UserBlock.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mUnread = bools[0];
        this.mArchived = bools[1];
        this.mBlockable = bools[2];
        this.mBlockableThreadType = bools[3];
        this.mPendingReview = bools[4];
        this.mHasPastReservationsHost = bools[5];
        this.mHasPendingAlterationRequest = bools[6];
        this.mRequiresResponse = bools[7];
        this.mShouldTranslate = bools[8];
        this.mNumberOfGuests = source.readInt();
        this.mId = source.readLong();
        this.mReviewId = source.readLong();
    }
}
