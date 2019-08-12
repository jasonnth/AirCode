package com.airbnb.android.core.utils;

import android.content.Context;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.ListingSummary;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.util.List;

public class ThreadUtils {
    public static String generateNamesString(Context context, Thread thread, User currentUser, InboxType inboxType) {
        ListingSummary listing = thread.getListing();
        boolean isBookingThread = listing != null && !ListUtils.isNullOrEmpty(listing.getHosts());
        if (!inboxType.isHostMode() || !isBookingThread) {
            return UserUtils.generateNamesString(context, getOrderedUsersWithoutCurrentUser(thread, currentUser));
        }
        return generateGuestNamesString(context, thread);
    }

    public static List<User> getOrderedUsersWithoutCurrentUser(Thread thread, User currentUser) {
        User primaryRecipient = thread.getOtherUser();
        List<User> users = Lists.newArrayList((Iterable<? extends E>) thread.getUsers());
        users.remove(currentUser);
        users.remove(primaryRecipient);
        users.add(0, primaryRecipient);
        return users;
    }

    private static String generateGuestNamesString(Context context, Thread thread) {
        List<User> hosts = thread.getListing().getHosts();
        List<User> guests = FluentIterable.from((Iterable<E>) thread.getUsers()).filter(ThreadUtils$$Lambda$1.lambdaFactory$(hosts)).toList();
        if (!ListUtils.isNullOrEmpty(guests)) {
            hosts = guests;
        }
        return UserUtils.generateNamesString(context, hosts);
    }

    static /* synthetic */ boolean lambda$generateGuestNamesString$0(List hosts, User user) {
        return !hosts.contains(user);
    }
}
