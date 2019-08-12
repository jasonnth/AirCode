package org.spongycastle.crypto.tls;

import java.security.SecureRandom;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.prng.DigestRandomGenerator;
import org.spongycastle.crypto.prng.RandomGenerator;
import org.spongycastle.util.Times;

abstract class AbstractTlsContext implements TlsContext {
    private static long counter = Times.nanoTime();
    private ProtocolVersion clientVersion = null;
    private RandomGenerator nonceRandom;
    private SecureRandom secureRandom;
    private SecurityParameters securityParameters;
    private ProtocolVersion serverVersion = null;
    private TlsSession session = null;
    private Object userObject = null;

    private static synchronized long nextCounterValue() {
        long j;
        synchronized (AbstractTlsContext.class) {
            j = counter + 1;
            counter = j;
        }
        return j;
    }

    AbstractTlsContext(SecureRandom secureRandom2, SecurityParameters securityParameters2) {
        Digest d = TlsUtils.createHash(4);
        byte[] seed = new byte[d.getDigestSize()];
        secureRandom2.nextBytes(seed);
        this.nonceRandom = new DigestRandomGenerator(d);
        this.nonceRandom.addSeedMaterial(nextCounterValue());
        this.nonceRandom.addSeedMaterial(Times.nanoTime());
        this.nonceRandom.addSeedMaterial(seed);
        this.secureRandom = secureRandom2;
        this.securityParameters = securityParameters2;
    }

    public RandomGenerator getNonceRandomGenerator() {
        return this.nonceRandom;
    }

    public SecureRandom getSecureRandom() {
        return this.secureRandom;
    }

    public SecurityParameters getSecurityParameters() {
        return this.securityParameters;
    }

    public ProtocolVersion getClientVersion() {
        return this.clientVersion;
    }

    /* access modifiers changed from: 0000 */
    public void setClientVersion(ProtocolVersion clientVersion2) {
        this.clientVersion = clientVersion2;
    }

    public ProtocolVersion getServerVersion() {
        return this.serverVersion;
    }

    /* access modifiers changed from: 0000 */
    public void setServerVersion(ProtocolVersion serverVersion2) {
        this.serverVersion = serverVersion2;
    }

    public TlsSession getResumableSession() {
        return this.session;
    }

    /* access modifiers changed from: 0000 */
    public void setResumableSession(TlsSession session2) {
        this.session = session2;
    }

    public Object getUserObject() {
        return this.userObject;
    }

    public void setUserObject(Object userObject2) {
        this.userObject = userObject2;
    }

    public byte[] exportKeyingMaterial(String asciiLabel, byte[] context_value, int length) {
        if (context_value == null || TlsUtils.isValidUint16(context_value.length)) {
            SecurityParameters sp = getSecurityParameters();
            byte[] cr = sp.getClientRandom();
            byte[] sr = sp.getServerRandom();
            int seedLength = cr.length + sr.length;
            if (context_value != null) {
                seedLength += context_value.length + 2;
            }
            byte[] seed = new byte[seedLength];
            System.arraycopy(cr, 0, seed, 0, cr.length);
            int seedPos = 0 + cr.length;
            System.arraycopy(sr, 0, seed, seedPos, sr.length);
            int seedPos2 = seedPos + sr.length;
            if (context_value != null) {
                TlsUtils.writeUint16(context_value.length, seed, seedPos2);
                int seedPos3 = seedPos2 + 2;
                System.arraycopy(context_value, 0, seed, seedPos3, context_value.length);
                seedPos2 = seedPos3 + context_value.length;
            }
            if (seedPos2 == seedLength) {
                return TlsUtils.PRF(this, sp.getMasterSecret(), asciiLabel, seed, length);
            }
            throw new IllegalStateException("error in calculation of seed for export");
        }
        throw new IllegalArgumentException("'context_value' must have length less than 2^16 (or be null)");
    }
}
