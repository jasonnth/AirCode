package okhttp3;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.connection.StreamAllocation.StreamAllocationReference;
import okhttp3.internal.platform.Platform;

public final class ConnectionPool {
    static final /* synthetic */ boolean $assertionsDisabled = (!ConnectionPool.class.desiredAssertionStatus());
    private static final Executor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
    private final Runnable cleanupRunnable;
    boolean cleanupRunning;
    private final Deque<RealConnection> connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    final RouteDatabase routeDatabase;

    public ConnectionPool() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public ConnectionPool(int maxIdleConnections2, long keepAliveDuration, TimeUnit timeUnit) {
        this.cleanupRunnable = new Runnable() {
            public void run() {
                while (true) {
                    long waitNanos = ConnectionPool.this.cleanup(System.nanoTime());
                    if (waitNanos != -1) {
                        if (waitNanos > 0) {
                            long waitMillis = waitNanos / 1000000;
                            long waitNanos2 = waitNanos - (waitMillis * 1000000);
                            synchronized (ConnectionPool.this) {
                                try {
                                    ConnectionPool.this.wait(waitMillis, (int) waitNanos2);
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        };
        this.connections = new ArrayDeque();
        this.routeDatabase = new RouteDatabase();
        this.maxIdleConnections = maxIdleConnections2;
        this.keepAliveDurationNs = timeUnit.toNanos(keepAliveDuration);
        if (keepAliveDuration <= 0) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + keepAliveDuration);
        }
    }

    /* access modifiers changed from: 0000 */
    public RealConnection get(C5164Address address, StreamAllocation streamAllocation) {
        if ($assertionsDisabled || Thread.holdsLock(this)) {
            for (RealConnection connection : this.connections) {
                if (connection.isEligible(address)) {
                    streamAllocation.acquire(connection);
                    return connection;
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public Socket deduplicate(C5164Address address, StreamAllocation streamAllocation) {
        if ($assertionsDisabled || Thread.holdsLock(this)) {
            for (RealConnection connection : this.connections) {
                if (connection.isEligible(address) && connection.isMultiplexed() && connection != streamAllocation.connection()) {
                    return streamAllocation.releaseAndAcquire(connection);
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public void put(RealConnection connection) {
        if ($assertionsDisabled || Thread.holdsLock(this)) {
            if (!this.cleanupRunning) {
                this.cleanupRunning = true;
                executor.execute(this.cleanupRunnable);
            }
            this.connections.add(connection);
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public boolean connectionBecameIdle(RealConnection connection) {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (connection.noNewStreams || this.maxIdleConnections == 0) {
            this.connections.remove(connection);
            return true;
        } else {
            notifyAll();
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public long cleanup(long now) {
        int inUseConnectionCount = 0;
        int idleConnectionCount = 0;
        RealConnection longestIdleConnection = null;
        long longestIdleDurationNs = Long.MIN_VALUE;
        synchronized (this) {
            for (RealConnection connection : this.connections) {
                if (pruneAndGetAllocationCount(connection, now) > 0) {
                    inUseConnectionCount++;
                } else {
                    idleConnectionCount++;
                    long idleDurationNs = now - connection.idleAtNanos;
                    if (idleDurationNs > longestIdleDurationNs) {
                        longestIdleDurationNs = idleDurationNs;
                        longestIdleConnection = connection;
                    }
                }
            }
            if (longestIdleDurationNs >= this.keepAliveDurationNs || idleConnectionCount > this.maxIdleConnections) {
                this.connections.remove(longestIdleConnection);
                Util.closeQuietly(longestIdleConnection.socket());
                return 0;
            } else if (idleConnectionCount > 0) {
                long j = this.keepAliveDurationNs - longestIdleDurationNs;
                return j;
            } else if (inUseConnectionCount > 0) {
                long j2 = this.keepAliveDurationNs;
                return j2;
            } else {
                this.cleanupRunning = false;
                return -1;
            }
        }
    }

    private int pruneAndGetAllocationCount(RealConnection connection, long now) {
        List<Reference<StreamAllocation>> references = connection.allocations;
        int i = 0;
        while (i < references.size()) {
            Reference<StreamAllocation> reference = (Reference) references.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                StreamAllocationReference streamAllocRef = (StreamAllocationReference) reference;
                Platform.get().logCloseableLeak("A connection to " + connection.route().address().url() + " was leaked. Did you forget to close a response body?", streamAllocRef.callStackTrace);
                references.remove(i);
                connection.noNewStreams = true;
                if (references.isEmpty()) {
                    connection.idleAtNanos = now - this.keepAliveDurationNs;
                    return 0;
                }
            }
        }
        return references.size();
    }
}
