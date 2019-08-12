package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.util.Arrays;

public class JPAKEParticipant {
    public static final int STATE_INITIALIZED = 0;
    public static final int STATE_KEY_CALCULATED = 50;
    public static final int STATE_ROUND_1_CREATED = 10;
    public static final int STATE_ROUND_1_VALIDATED = 20;
    public static final int STATE_ROUND_2_CREATED = 30;
    public static final int STATE_ROUND_2_VALIDATED = 40;
    public static final int STATE_ROUND_3_CREATED = 60;
    public static final int STATE_ROUND_3_VALIDATED = 70;

    /* renamed from: b */
    private BigInteger f6453b;
    private final Digest digest;

    /* renamed from: g */
    private final BigInteger f6454g;
    private BigInteger gx1;
    private BigInteger gx2;
    private BigInteger gx3;
    private BigInteger gx4;

    /* renamed from: p */
    private final BigInteger f6455p;
    private final String participantId;
    private String partnerParticipantId;
    private char[] password;

    /* renamed from: q */
    private final BigInteger f6456q;
    private final SecureRandom random;
    private int state;

    /* renamed from: x1 */
    private BigInteger f6457x1;

    /* renamed from: x2 */
    private BigInteger f6458x2;

    public JPAKEParticipant(String participantId2, char[] password2) {
        this(participantId2, password2, JPAKEPrimeOrderGroups.NIST_3072);
    }

    public JPAKEParticipant(String participantId2, char[] password2, JPAKEPrimeOrderGroup group) {
        this(participantId2, password2, group, new SHA256Digest(), new SecureRandom());
    }

    public JPAKEParticipant(String participantId2, char[] password2, JPAKEPrimeOrderGroup group, Digest digest2, SecureRandom random2) {
        JPAKEUtil.validateNotNull(participantId2, "participantId");
        JPAKEUtil.validateNotNull(password2, "password");
        JPAKEUtil.validateNotNull(group, "p");
        JPAKEUtil.validateNotNull(digest2, "digest");
        JPAKEUtil.validateNotNull(random2, "random");
        if (password2.length == 0) {
            throw new IllegalArgumentException("Password must not be empty.");
        }
        this.participantId = participantId2;
        this.password = Arrays.copyOf(password2, password2.length);
        this.f6455p = group.getP();
        this.f6456q = group.getQ();
        this.f6454g = group.getG();
        this.digest = digest2;
        this.random = random2;
        this.state = 0;
    }

    public int getState() {
        return this.state;
    }

    public JPAKERound1Payload createRound1PayloadToSend() {
        if (this.state >= 10) {
            throw new IllegalStateException("Round1 payload already created for " + this.participantId);
        }
        this.f6457x1 = JPAKEUtil.generateX1(this.f6456q, this.random);
        this.f6458x2 = JPAKEUtil.generateX2(this.f6456q, this.random);
        this.gx1 = JPAKEUtil.calculateGx(this.f6455p, this.f6454g, this.f6457x1);
        this.gx2 = JPAKEUtil.calculateGx(this.f6455p, this.f6454g, this.f6458x2);
        BigInteger[] knowledgeProofForX1 = JPAKEUtil.calculateZeroKnowledgeProof(this.f6455p, this.f6456q, this.f6454g, this.gx1, this.f6457x1, this.participantId, this.digest, this.random);
        BigInteger[] knowledgeProofForX2 = JPAKEUtil.calculateZeroKnowledgeProof(this.f6455p, this.f6456q, this.f6454g, this.gx2, this.f6458x2, this.participantId, this.digest, this.random);
        this.state = 10;
        return new JPAKERound1Payload(this.participantId, this.gx1, this.gx2, knowledgeProofForX1, knowledgeProofForX2);
    }

    public void validateRound1PayloadReceived(JPAKERound1Payload round1PayloadReceived) throws CryptoException {
        if (this.state >= 20) {
            throw new IllegalStateException("Validation already attempted for round1 payload for" + this.participantId);
        }
        this.partnerParticipantId = round1PayloadReceived.getParticipantId();
        this.gx3 = round1PayloadReceived.getGx1();
        this.gx4 = round1PayloadReceived.getGx2();
        BigInteger[] knowledgeProofForX3 = round1PayloadReceived.getKnowledgeProofForX1();
        BigInteger[] knowledgeProofForX4 = round1PayloadReceived.getKnowledgeProofForX2();
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, round1PayloadReceived.getParticipantId());
        JPAKEUtil.validateGx4(this.gx4);
        JPAKEUtil.validateZeroKnowledgeProof(this.f6455p, this.f6456q, this.f6454g, this.gx3, knowledgeProofForX3, round1PayloadReceived.getParticipantId(), this.digest);
        JPAKEUtil.validateZeroKnowledgeProof(this.f6455p, this.f6456q, this.f6454g, this.gx4, knowledgeProofForX4, round1PayloadReceived.getParticipantId(), this.digest);
        this.state = 20;
    }

    public JPAKERound2Payload createRound2PayloadToSend() {
        if (this.state >= 30) {
            throw new IllegalStateException("Round2 payload already created for " + this.participantId);
        } else if (this.state < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to creating Round2 payload for " + this.participantId);
        } else {
            BigInteger gA = JPAKEUtil.calculateGA(this.f6455p, this.gx1, this.gx3, this.gx4);
            BigInteger x2s = JPAKEUtil.calculateX2s(this.f6456q, this.f6458x2, JPAKEUtil.calculateS(this.password));
            BigInteger A = JPAKEUtil.calculateA(this.f6455p, this.f6456q, gA, x2s);
            BigInteger[] knowledgeProofForX2s = JPAKEUtil.calculateZeroKnowledgeProof(this.f6455p, this.f6456q, gA, A, x2s, this.participantId, this.digest, this.random);
            this.state = 30;
            return new JPAKERound2Payload(this.participantId, A, knowledgeProofForX2s);
        }
    }

    public void validateRound2PayloadReceived(JPAKERound2Payload round2PayloadReceived) throws CryptoException {
        if (this.state >= 40) {
            throw new IllegalStateException("Validation already attempted for round2 payload for" + this.participantId);
        } else if (this.state < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to validating Round2 payload for " + this.participantId);
        } else {
            BigInteger gB = JPAKEUtil.calculateGA(this.f6455p, this.gx3, this.gx1, this.gx2);
            this.f6453b = round2PayloadReceived.getA();
            BigInteger[] knowledgeProofForX4s = round2PayloadReceived.getKnowledgeProofForX2s();
            JPAKEUtil.validateParticipantIdsDiffer(this.participantId, round2PayloadReceived.getParticipantId());
            JPAKEUtil.validateParticipantIdsEqual(this.partnerParticipantId, round2PayloadReceived.getParticipantId());
            JPAKEUtil.validateGa(gB);
            JPAKEUtil.validateZeroKnowledgeProof(this.f6455p, this.f6456q, gB, this.f6453b, knowledgeProofForX4s, round2PayloadReceived.getParticipantId(), this.digest);
            this.state = 40;
        }
    }

    public BigInteger calculateKeyingMaterial() {
        if (this.state >= 50) {
            throw new IllegalStateException("Key already calculated for " + this.participantId);
        } else if (this.state < 40) {
            throw new IllegalStateException("Round2 payload must be validated prior to creating key for " + this.participantId);
        } else {
            BigInteger s = JPAKEUtil.calculateS(this.password);
            Arrays.fill(this.password, 0);
            this.password = null;
            BigInteger keyingMaterial = JPAKEUtil.calculateKeyingMaterial(this.f6455p, this.f6456q, this.gx4, this.f6458x2, s, this.f6453b);
            this.f6457x1 = null;
            this.f6458x2 = null;
            this.f6453b = null;
            this.state = 50;
            return keyingMaterial;
        }
    }

    public JPAKERound3Payload createRound3PayloadToSend(BigInteger keyingMaterial) {
        if (this.state >= 60) {
            throw new IllegalStateException("Round3 payload already created for " + this.participantId);
        } else if (this.state < 50) {
            throw new IllegalStateException("Keying material must be calculated prior to creating Round3 payload for " + this.participantId);
        } else {
            BigInteger macTag = JPAKEUtil.calculateMacTag(this.participantId, this.partnerParticipantId, this.gx1, this.gx2, this.gx3, this.gx4, keyingMaterial, this.digest);
            this.state = 60;
            return new JPAKERound3Payload(this.participantId, macTag);
        }
    }

    public void validateRound3PayloadReceived(JPAKERound3Payload round3PayloadReceived, BigInteger keyingMaterial) throws CryptoException {
        if (this.state >= 70) {
            throw new IllegalStateException("Validation already attempted for round3 payload for" + this.participantId);
        } else if (this.state < 50) {
            throw new IllegalStateException("Keying material must be calculated validated prior to validating Round3 payload for " + this.participantId);
        } else {
            JPAKEUtil.validateParticipantIdsDiffer(this.participantId, round3PayloadReceived.getParticipantId());
            JPAKEUtil.validateParticipantIdsEqual(this.partnerParticipantId, round3PayloadReceived.getParticipantId());
            JPAKEUtil.validateMacTag(this.participantId, this.partnerParticipantId, this.gx1, this.gx2, this.gx3, this.gx4, keyingMaterial, this.digest, round3PayloadReceived.getMacTag());
            this.gx1 = null;
            this.gx2 = null;
            this.gx3 = null;
            this.gx4 = null;
            this.state = 70;
        }
    }
}
