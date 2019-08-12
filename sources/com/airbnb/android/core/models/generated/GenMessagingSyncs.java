package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.ThreadUpdateWithPosts;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenMessagingSyncs implements Parcelable {
    @JsonProperty("current_sequence_id")
    protected long mCurrentSequenceId;
    @JsonProperty("threads_for_partial_update")
    protected List<Thread> mThreadsForPartialUpdate;
    @JsonProperty("threads_for_removal")
    protected List<Long> mThreadsForRemoval;
    @JsonProperty("threads_for_update")
    protected List<ThreadUpdateWithPosts> mThreadsForUpdate;
    @JsonProperty("unread_count")
    protected int mUnreadCount;

    protected GenMessagingSyncs(List<Long> threadsForRemoval, List<Thread> threadsForPartialUpdate, List<ThreadUpdateWithPosts> threadsForUpdate, int unreadCount, long currentSequenceId) {
        this();
        this.mThreadsForRemoval = threadsForRemoval;
        this.mThreadsForPartialUpdate = threadsForPartialUpdate;
        this.mThreadsForUpdate = threadsForUpdate;
        this.mUnreadCount = unreadCount;
        this.mCurrentSequenceId = currentSequenceId;
    }

    protected GenMessagingSyncs() {
    }

    public List<Long> getThreadsForRemoval() {
        return this.mThreadsForRemoval;
    }

    @JsonProperty("threads_for_removal")
    public void setThreadsForRemoval(List<Long> value) {
        this.mThreadsForRemoval = value;
    }

    public List<Thread> getThreadsForPartialUpdate() {
        return this.mThreadsForPartialUpdate;
    }

    @JsonProperty("threads_for_partial_update")
    public void setThreadsForPartialUpdate(List<Thread> value) {
        this.mThreadsForPartialUpdate = value;
    }

    public List<ThreadUpdateWithPosts> getThreadsForUpdate() {
        return this.mThreadsForUpdate;
    }

    @JsonProperty("threads_for_update")
    public void setThreadsForUpdate(List<ThreadUpdateWithPosts> value) {
        this.mThreadsForUpdate = value;
    }

    public int getUnreadCount() {
        return this.mUnreadCount;
    }

    @JsonProperty("unread_count")
    public void setUnreadCount(int value) {
        this.mUnreadCount = value;
    }

    public long getCurrentSequenceId() {
        return this.mCurrentSequenceId;
    }

    @JsonProperty("current_sequence_id")
    public void setCurrentSequenceId(long value) {
        this.mCurrentSequenceId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mThreadsForRemoval);
        parcel.writeTypedList(this.mThreadsForPartialUpdate);
        parcel.writeTypedList(this.mThreadsForUpdate);
        parcel.writeInt(this.mUnreadCount);
        parcel.writeLong(this.mCurrentSequenceId);
    }

    public void readFromParcel(Parcel source) {
        this.mThreadsForRemoval = (List) source.readValue(null);
        this.mThreadsForPartialUpdate = source.createTypedArrayList(Thread.CREATOR);
        this.mThreadsForUpdate = source.createTypedArrayList(ThreadUpdateWithPosts.CREATOR);
        this.mUnreadCount = source.readInt();
        this.mCurrentSequenceId = source.readLong();
    }
}
