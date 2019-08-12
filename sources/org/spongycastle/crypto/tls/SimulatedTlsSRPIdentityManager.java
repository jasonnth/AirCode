package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.agreement.srp.SRP6VerifierGenerator;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.SRP6GroupParameters;
import org.spongycastle.util.Strings;

public class SimulatedTlsSRPIdentityManager implements TlsSRPIdentityManager {
    private static final byte[] PREFIX_PASSWORD = Strings.toByteArray("password");
    private static final byte[] PREFIX_SALT = Strings.toByteArray("salt");
    protected SRP6GroupParameters group;
    protected Mac mac;
    protected SRP6VerifierGenerator verifierGenerator;

    public static SimulatedTlsSRPIdentityManager getRFC5054Default(SRP6GroupParameters group2, byte[] seedKey) {
        SRP6VerifierGenerator verifierGenerator2 = new SRP6VerifierGenerator();
        verifierGenerator2.init(group2, TlsUtils.createHash(2));
        HMac mac2 = new HMac(TlsUtils.createHash(2));
        mac2.init(new KeyParameter(seedKey));
        return new SimulatedTlsSRPIdentityManager(group2, verifierGenerator2, mac2);
    }

    public SimulatedTlsSRPIdentityManager(SRP6GroupParameters group2, SRP6VerifierGenerator verifierGenerator2, Mac mac2) {
        this.group = group2;
        this.verifierGenerator = verifierGenerator2;
        this.mac = mac2;
    }

    public TlsSRPLoginParameters getLoginParameters(byte[] identity) {
        this.mac.update(PREFIX_SALT, 0, PREFIX_SALT.length);
        this.mac.update(identity, 0, identity.length);
        byte[] salt = new byte[this.mac.getMacSize()];
        this.mac.doFinal(salt, 0);
        this.mac.update(PREFIX_PASSWORD, 0, PREFIX_PASSWORD.length);
        this.mac.update(identity, 0, identity.length);
        byte[] password = new byte[this.mac.getMacSize()];
        this.mac.doFinal(password, 0);
        return new TlsSRPLoginParameters(this.group, this.verifierGenerator.generateVerifier(salt, identity, password), salt);
    }
}
