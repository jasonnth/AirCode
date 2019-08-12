package org.spongycastle.jce.provider;

import org.spongycastle.asn1.x509.ReasonFlags;

class ReasonsMask {
    static final ReasonsMask allReasons = new ReasonsMask(33023);
    private int _reasons;

    ReasonsMask(ReasonFlags reasons) {
        this._reasons = reasons.intValue();
    }

    private ReasonsMask(int reasons) {
        this._reasons = reasons;
    }

    ReasonsMask() {
        this(0);
    }

    /* access modifiers changed from: 0000 */
    public void addReasons(ReasonsMask mask) {
        this._reasons |= mask.getReasons();
    }

    /* access modifiers changed from: 0000 */
    public boolean isAllReasons() {
        return this._reasons == allReasons._reasons;
    }

    /* access modifiers changed from: 0000 */
    public ReasonsMask intersect(ReasonsMask mask) {
        ReasonsMask _mask = new ReasonsMask();
        _mask.addReasons(new ReasonsMask(this._reasons & mask.getReasons()));
        return _mask;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasNewReasons(ReasonsMask mask) {
        return (this._reasons | (mask.getReasons() ^ this._reasons)) != 0;
    }

    /* access modifiers changed from: 0000 */
    public int getReasons() {
        return this._reasons;
    }
}
