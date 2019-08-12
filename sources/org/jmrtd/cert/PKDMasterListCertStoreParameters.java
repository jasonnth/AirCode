package org.jmrtd.cert;

public class PKDMasterListCertStoreParameters extends PKDCertStoreParameters {
    private static final String DEFAULT_BASE_DN = "dc=CSCAMasterList,dc=pkdDownload";

    public PKDMasterListCertStoreParameters() {
    }

    public PKDMasterListCertStoreParameters(String str) {
        this(str, DEFAULT_BASE_DN);
    }

    public PKDMasterListCertStoreParameters(String str, String str2) {
        super(str, str2);
    }

    public PKDMasterListCertStoreParameters(String str, int i) {
        this(str, i, DEFAULT_BASE_DN);
    }

    public PKDMasterListCertStoreParameters(String str, int i, String str2) {
        super(str, i, str2);
    }
}
