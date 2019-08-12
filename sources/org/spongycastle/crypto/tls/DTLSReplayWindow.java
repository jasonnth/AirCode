package org.spongycastle.crypto.tls;

class DTLSReplayWindow {
    private static final long VALID_SEQ_MASK = 281474976710655L;
    private static final long WINDOW_SIZE = 64;
    private long bitmap = 0;
    private long latestConfirmedSeq = -1;

    DTLSReplayWindow() {
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldDiscard(long seq) {
        if ((VALID_SEQ_MASK & seq) != seq) {
            return true;
        }
        if (seq <= this.latestConfirmedSeq) {
            long diff = this.latestConfirmedSeq - seq;
            if (diff >= 64 || (this.bitmap & (1 << ((int) diff))) != 0) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void reportAuthenticated(long seq) {
        if ((VALID_SEQ_MASK & seq) != seq) {
            throw new IllegalArgumentException("'seq' out of range");
        } else if (seq <= this.latestConfirmedSeq) {
            long diff = this.latestConfirmedSeq - seq;
            if (diff < 64) {
                this.bitmap |= 1 << ((int) diff);
            }
        } else {
            long diff2 = seq - this.latestConfirmedSeq;
            if (diff2 >= 64) {
                this.bitmap = 1;
            } else {
                this.bitmap <<= (int) diff2;
                this.bitmap |= 1;
            }
            this.latestConfirmedSeq = seq;
        }
    }

    /* access modifiers changed from: 0000 */
    public void reset() {
        this.latestConfirmedSeq = -1;
        this.bitmap = 0;
    }
}
