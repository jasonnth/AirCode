package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class JPAKERound2Payload {

    /* renamed from: a */
    private final BigInteger f6462a;
    private final BigInteger[] knowledgeProofForX2s;
    private final String participantId;

    public JPAKERound2Payload(String participantId2, BigInteger a, BigInteger[] knowledgeProofForX2s2) {
        JPAKEUtil.validateNotNull(participantId2, "participantId");
        JPAKEUtil.validateNotNull(a, "a");
        JPAKEUtil.validateNotNull(knowledgeProofForX2s2, "knowledgeProofForX2s");
        this.participantId = participantId2;
        this.f6462a = a;
        this.knowledgeProofForX2s = Arrays.copyOf(knowledgeProofForX2s2, knowledgeProofForX2s2.length);
    }

    public String getParticipantId() {
        return this.participantId;
    }

    public BigInteger getA() {
        return this.f6462a;
    }

    public BigInteger[] getKnowledgeProofForX2s() {
        return Arrays.copyOf(this.knowledgeProofForX2s, this.knowledgeProofForX2s.length);
    }
}
