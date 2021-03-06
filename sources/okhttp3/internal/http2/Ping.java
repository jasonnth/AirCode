package okhttp3.internal.http2;

import java.util.concurrent.CountDownLatch;

final class Ping {
    private final CountDownLatch latch = new CountDownLatch(1);
    private long received = -1;
    private long sent = -1;

    Ping() {
    }

    /* access modifiers changed from: 0000 */
    public void send() {
        if (this.sent != -1) {
            throw new IllegalStateException();
        }
        this.sent = System.nanoTime();
    }

    /* access modifiers changed from: 0000 */
    public void receive() {
        if (this.received != -1 || this.sent == -1) {
            throw new IllegalStateException();
        }
        this.received = System.nanoTime();
        this.latch.countDown();
    }

    /* access modifiers changed from: 0000 */
    public void cancel() {
        if (this.received != -1 || this.sent == -1) {
            throw new IllegalStateException();
        }
        this.received = this.sent - 1;
        this.latch.countDown();
    }
}
