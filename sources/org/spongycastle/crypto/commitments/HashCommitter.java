package org.spongycastle.crypto.commitments;

import java.security.SecureRandom;
import org.spongycastle.crypto.Commitment;
import org.spongycastle.crypto.Committer;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;

public class HashCommitter implements Committer {
    private final int byteLength;
    private final Digest digest;
    private final SecureRandom random;

    public HashCommitter(ExtendedDigest digest2, SecureRandom random2) {
        this.digest = digest2;
        this.byteLength = digest2.getByteLength();
        this.random = random2;
    }

    public Commitment commit(byte[] message) {
        if (message.length > this.byteLength / 2) {
            throw new DataLengthException("Message to be committed to too large for digest.");
        }
        byte[] w = new byte[(this.byteLength - message.length)];
        this.random.nextBytes(w);
        return new Commitment(w, calculateCommitment(w, message));
    }

    public boolean isRevealed(Commitment commitment, byte[] message) {
        if (message.length + commitment.getSecret().length != this.byteLength) {
            throw new DataLengthException("Message and witness secret lengths do not match.");
        }
        return Arrays.constantTimeAreEqual(commitment.getCommitment(), calculateCommitment(commitment.getSecret(), message));
    }

    private byte[] calculateCommitment(byte[] w, byte[] message) {
        byte[] commitment = new byte[this.digest.getDigestSize()];
        this.digest.update(w, 0, w.length);
        this.digest.update(message, 0, message.length);
        this.digest.doFinal(commitment, 0);
        return commitment;
    }
}
