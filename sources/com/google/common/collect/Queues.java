package com.google.common.collect;

import java.util.Queue;

public final class Queues {
    public static <E> Queue<E> synchronizedQueue(Queue<E> queue) {
        return Synchronized.queue(queue, null);
    }
}
