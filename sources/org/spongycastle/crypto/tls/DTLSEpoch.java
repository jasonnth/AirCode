package org.spongycastle.crypto.tls;

class DTLSEpoch {
    private final TlsCipher cipher;
    private final int epoch;
    private final DTLSReplayWindow replayWindow = new DTLSReplayWindow();
    private long sequenceNumber = 0;

    DTLSEpoch(int epoch2, TlsCipher cipher2) {
        if (epoch2 < 0) {
            throw new IllegalArgumentException("'epoch' must be >= 0");
        } else if (cipher2 == null) {
            throw new IllegalArgumentException("'cipher' cannot be null");
        } else {
            this.epoch = epoch2;
            this.cipher = cipher2;
        }
    }

    /* access modifiers changed from: 0000 */
    public long allocateSequenceNumber() {
        long j = this.sequenceNumber;
        this.sequenceNumber = 1 + j;
        return j;
    }

    /* access modifiers changed from: 0000 */
    public TlsCipher getCipher() {
        return this.cipher;
    }

    /* access modifiers changed from: 0000 */
    public int getEpoch() {
        return this.epoch;
    }

    /* access modifiers changed from: 0000 */
    public DTLSReplayWindow getReplayWindow() {
        return this.replayWindow;
    }

    /* access modifiers changed from: 0000 */
    public long getSequenceNumber() {
        return this.sequenceNumber;
    }
}
