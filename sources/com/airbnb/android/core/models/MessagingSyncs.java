package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenMessagingSyncs;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MessagingSyncs extends GenMessagingSyncs {
    public static final Creator<MessagingSyncs> CREATOR = new Creator<MessagingSyncs>() {
        public MessagingSyncs[] newArray(int size) {
            return new MessagingSyncs[size];
        }

        public MessagingSyncs createFromParcel(Parcel source) {
            MessagingSyncs object = new MessagingSyncs();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean shouldReset() {
        return !ListUtils.isEmpty((Collection<?>) this.mThreadsForPartialUpdate);
    }

    public boolean hasAnyThreadUpdates() {
        return !ListUtils.isEmpty((Collection<?>) this.mThreadsForPartialUpdate) || !ListUtils.isEmpty((Collection<?>) this.mThreadsForUpdate) || !ListUtils.isEmpty((Collection<?>) this.mThreadsForRemoval);
    }

    public List<Long> getThreadsForRemoval() {
        return ListUtils.ensureNotNull(super.getThreadsForRemoval());
    }

    public List<Thread> getThreadsForPartialUpdate() {
        return ListUtils.ensureNotNull(super.getThreadsForPartialUpdate());
    }

    public List<ThreadUpdateWithPosts> getThreadsForUpdate() {
        return ListUtils.ensureNotNull(super.getThreadsForUpdate());
    }

    public Set<Long> calculateUpdatedThreadIds() {
        return FluentIterable.from((Iterable<E>) getThreadsForPartialUpdate()).append((Iterable<? extends E>) getThreadsForUpdate()).transform(MessagingSyncs$$Lambda$1.lambdaFactory$()).append((Iterable<? extends E>) getThreadsForRemoval()).toSet();
    }
}
