package org.spongycastle.jcajce.provider.asymmetric.p330ec;

import java.security.spec.ECGenParameterSpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;

/* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.ECUtils */
class ECUtils {
    ECUtils() {
    }

    static X9ECParameters getDomainParametersFromGenSpec(ECGenParameterSpec genSpec) {
        return getDomainParametersFromName(genSpec.getName());
    }

    static X9ECParameters getDomainParametersFromName(String curveName) {
        try {
            if (curveName.charAt(0) >= '0' && curveName.charAt(0) <= '2') {
                return ECUtil.getNamedCurveByOid(new ASN1ObjectIdentifier(curveName));
            }
            if (curveName.indexOf(32) > 0) {
                return ECUtil.getNamedCurveByName(curveName.substring(curveName.indexOf(32) + 1));
            }
            return ECUtil.getNamedCurveByName(curveName);
        } catch (IllegalArgumentException e) {
            return ECUtil.getNamedCurveByName(curveName);
        }
    }
}
