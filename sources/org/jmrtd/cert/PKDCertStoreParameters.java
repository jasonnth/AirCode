package org.jmrtd.cert;

import java.security.cert.CertStoreParameters;

public class PKDCertStoreParameters implements Cloneable, CertStoreParameters {
    private static final String DEFAULT_BASE_DN = "dc=data,dc=pkdDownload";
    private static final int DEFAULT_PORT = 389;
    private static final String DEFAULT_SERVER_NAME = "localhost";
    private String baseDN;
    private int port;
    private String serverName;

    public PKDCertStoreParameters() {
        this(DEFAULT_SERVER_NAME, DEFAULT_PORT, DEFAULT_BASE_DN);
    }

    public PKDCertStoreParameters(String str) {
        this(str, DEFAULT_PORT, DEFAULT_BASE_DN);
    }

    public PKDCertStoreParameters(String str, String str2) {
        this(str, DEFAULT_PORT, str2);
    }

    public PKDCertStoreParameters(String str, int i) {
        this(str, i, DEFAULT_BASE_DN);
    }

    public PKDCertStoreParameters(String str, int i, String str2) {
        this.serverName = str;
        this.port = i;
        this.baseDN = str2;
    }

    public String getServerName() {
        return this.serverName;
    }

    public int getPort() {
        return this.port;
    }

    public String getBaseDN() {
        return this.baseDN;
    }

    public Object clone() {
        return new PKDCertStoreParameters(this.serverName, this.port, this.baseDN);
    }

    public String toString() {
        return "PKDCertStoreParameters [" + this.serverName + ":" + this.port + "/" + this.baseDN + "]";
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        PKDCertStoreParameters pKDCertStoreParameters = (PKDCertStoreParameters) obj;
        if (!pKDCertStoreParameters.serverName.equals(this.serverName) || pKDCertStoreParameters.port != this.port || !pKDCertStoreParameters.baseDN.equals(this.baseDN)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((this.serverName.hashCode() + this.port + this.baseDN.hashCode()) * 2) + 303;
    }
}
