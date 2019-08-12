package okhttp3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class CipherSuite {
    private static final ConcurrentMap<String, CipherSuite> INSTANCES = new ConcurrentHashMap();
    public static final CipherSuite TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA = m3947of("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
    public static final CipherSuite TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA = m3947of("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_CBC_SHA = m3947of("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_CBC_SHA256 = m3947of("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 = m3947of("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256);
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_CBC_SHA = m3947of("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_CBC_SHA256 = m3947of("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 = m3947of("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384);
    public static final CipherSuite TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA = m3947of("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
    public static final CipherSuite TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA = m3947of("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
    public static final CipherSuite TLS_DHE_DSS_WITH_DES_CBC_SHA = m3947of("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
    public static final CipherSuite TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA = m3947of("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
    public static final CipherSuite TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA = m3947of("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_CBC_SHA = m3947of("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_CBC_SHA256 = m3947of("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 = m3947of("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_CBC_SHA = m3947of("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_CBC_SHA256 = m3947of("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 = m3947of("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384);
    public static final CipherSuite TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA = m3947of("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
    public static final CipherSuite TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA = m3947of("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
    public static final CipherSuite TLS_DHE_RSA_WITH_DES_CBC_SHA = m3947of("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
    public static final CipherSuite TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA = m3947of("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
    public static final CipherSuite TLS_DH_anon_EXPORT_WITH_RC4_40_MD5 = m3947of("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
    public static final CipherSuite TLS_DH_anon_WITH_3DES_EDE_CBC_SHA = m3947of("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
    public static final CipherSuite TLS_DH_anon_WITH_AES_128_CBC_SHA = m3947of("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
    public static final CipherSuite TLS_DH_anon_WITH_AES_128_CBC_SHA256 = m3947of("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
    public static final CipherSuite TLS_DH_anon_WITH_AES_128_GCM_SHA256 = m3947of("TLS_DH_anon_WITH_AES_128_GCM_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256);
    public static final CipherSuite TLS_DH_anon_WITH_AES_256_CBC_SHA = m3947of("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
    public static final CipherSuite TLS_DH_anon_WITH_AES_256_CBC_SHA256 = m3947of("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
    public static final CipherSuite TLS_DH_anon_WITH_AES_256_GCM_SHA384 = m3947of("TLS_DH_anon_WITH_AES_256_GCM_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384);
    public static final CipherSuite TLS_DH_anon_WITH_DES_CBC_SHA = m3947of("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
    public static final CipherSuite TLS_DH_anon_WITH_RC4_128_MD5 = m3947of("SSL_DH_anon_WITH_RC4_128_MD5", 24);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA = m3947of("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA = m3947of("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 = m3947of("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 = m3947of("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA = m3947of("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 = m3947of("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 = m3947of("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 = m3947of("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_NULL_SHA = m3947of("TLS_ECDHE_ECDSA_WITH_NULL_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA);
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_RC4_128_SHA = m3947of("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA);
    public static final CipherSuite TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA = m3947of("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA);
    public static final CipherSuite TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA = m3947of("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA = m3947of("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA = m3947of("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 = m3947of("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 = m3947of("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA = m3947of("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 = m3947of("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 = m3947of("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 = m3947of("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_NULL_SHA = m3947of("TLS_ECDHE_RSA_WITH_NULL_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA);
    public static final CipherSuite TLS_ECDHE_RSA_WITH_RC4_128_SHA = m3947of("TLS_ECDHE_RSA_WITH_RC4_128_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA);
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA = m3947of("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA);
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA = m3947of("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA);
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 = m3947of("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256);
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 = m3947of("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256);
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA = m3947of("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA);
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 = m3947of("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384);
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 = m3947of("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384);
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_NULL_SHA = m3947of("TLS_ECDH_ECDSA_WITH_NULL_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA);
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_RC4_128_SHA = m3947of("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA);
    public static final CipherSuite TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA = m3947of("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA);
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_CBC_SHA = m3947of("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA);
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 = m3947of("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256);
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 = m3947of("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256);
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_CBC_SHA = m3947of("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA);
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 = m3947of("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384);
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 = m3947of("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384);
    public static final CipherSuite TLS_ECDH_RSA_WITH_NULL_SHA = m3947of("TLS_ECDH_RSA_WITH_NULL_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA);
    public static final CipherSuite TLS_ECDH_RSA_WITH_RC4_128_SHA = m3947of("TLS_ECDH_RSA_WITH_RC4_128_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA);
    public static final CipherSuite TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA = m3947of("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA);
    public static final CipherSuite TLS_ECDH_anon_WITH_AES_128_CBC_SHA = m3947of("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA);
    public static final CipherSuite TLS_ECDH_anon_WITH_AES_256_CBC_SHA = m3947of("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA);
    public static final CipherSuite TLS_ECDH_anon_WITH_NULL_SHA = m3947of("TLS_ECDH_anon_WITH_NULL_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA);
    public static final CipherSuite TLS_ECDH_anon_WITH_RC4_128_SHA = m3947of("TLS_ECDH_anon_WITH_RC4_128_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA);
    public static final CipherSuite TLS_EMPTY_RENEGOTIATION_INFO_SCSV = m3947of("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
    public static final CipherSuite TLS_FALLBACK_SCSV = m3947of("TLS_FALLBACK_SCSV", org.spongycastle.crypto.tls.CipherSuite.TLS_FALLBACK_SCSV);
    public static final CipherSuite TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5 = m3947of("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
    public static final CipherSuite TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA = m3947of("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
    public static final CipherSuite TLS_KRB5_EXPORT_WITH_RC4_40_MD5 = m3947of("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
    public static final CipherSuite TLS_KRB5_EXPORT_WITH_RC4_40_SHA = m3947of("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
    public static final CipherSuite TLS_KRB5_WITH_3DES_EDE_CBC_MD5 = m3947of("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
    public static final CipherSuite TLS_KRB5_WITH_3DES_EDE_CBC_SHA = m3947of("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
    public static final CipherSuite TLS_KRB5_WITH_DES_CBC_MD5 = m3947of("TLS_KRB5_WITH_DES_CBC_MD5", 34);
    public static final CipherSuite TLS_KRB5_WITH_DES_CBC_SHA = m3947of("TLS_KRB5_WITH_DES_CBC_SHA", 30);
    public static final CipherSuite TLS_KRB5_WITH_RC4_128_MD5 = m3947of("TLS_KRB5_WITH_RC4_128_MD5", 36);
    public static final CipherSuite TLS_KRB5_WITH_RC4_128_SHA = m3947of("TLS_KRB5_WITH_RC4_128_SHA", 32);
    public static final CipherSuite TLS_PSK_WITH_3DES_EDE_CBC_SHA = m3947of("TLS_PSK_WITH_3DES_EDE_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA);
    public static final CipherSuite TLS_PSK_WITH_AES_128_CBC_SHA = m3947of("TLS_PSK_WITH_AES_128_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA);
    public static final CipherSuite TLS_PSK_WITH_AES_256_CBC_SHA = m3947of("TLS_PSK_WITH_AES_256_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA);
    public static final CipherSuite TLS_PSK_WITH_RC4_128_SHA = m3947of("TLS_PSK_WITH_RC4_128_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_PSK_WITH_RC4_128_SHA);
    public static final CipherSuite TLS_RSA_EXPORT_WITH_DES40_CBC_SHA = m3947of("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
    public static final CipherSuite TLS_RSA_EXPORT_WITH_RC4_40_MD5 = m3947of("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
    public static final CipherSuite TLS_RSA_WITH_3DES_EDE_CBC_SHA = m3947of("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
    public static final CipherSuite TLS_RSA_WITH_AES_128_CBC_SHA = m3947of("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
    public static final CipherSuite TLS_RSA_WITH_AES_128_CBC_SHA256 = m3947of("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
    public static final CipherSuite TLS_RSA_WITH_AES_128_GCM_SHA256 = m3947of("TLS_RSA_WITH_AES_128_GCM_SHA256", org.spongycastle.crypto.tls.CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256);
    public static final CipherSuite TLS_RSA_WITH_AES_256_CBC_SHA = m3947of("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
    public static final CipherSuite TLS_RSA_WITH_AES_256_CBC_SHA256 = m3947of("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
    public static final CipherSuite TLS_RSA_WITH_AES_256_GCM_SHA384 = m3947of("TLS_RSA_WITH_AES_256_GCM_SHA384", org.spongycastle.crypto.tls.CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384);
    public static final CipherSuite TLS_RSA_WITH_CAMELLIA_128_CBC_SHA = m3947of("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
    public static final CipherSuite TLS_RSA_WITH_CAMELLIA_256_CBC_SHA = m3947of("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", org.spongycastle.crypto.tls.CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA);
    public static final CipherSuite TLS_RSA_WITH_DES_CBC_SHA = m3947of("SSL_RSA_WITH_DES_CBC_SHA", 9);
    public static final CipherSuite TLS_RSA_WITH_NULL_MD5 = m3947of("SSL_RSA_WITH_NULL_MD5", 1);
    public static final CipherSuite TLS_RSA_WITH_NULL_SHA = m3947of("SSL_RSA_WITH_NULL_SHA", 2);
    public static final CipherSuite TLS_RSA_WITH_NULL_SHA256 = m3947of("TLS_RSA_WITH_NULL_SHA256", 59);
    public static final CipherSuite TLS_RSA_WITH_RC4_128_MD5 = m3947of("SSL_RSA_WITH_RC4_128_MD5", 4);
    public static final CipherSuite TLS_RSA_WITH_RC4_128_SHA = m3947of("SSL_RSA_WITH_RC4_128_SHA", 5);
    public static final CipherSuite TLS_RSA_WITH_SEED_CBC_SHA = m3947of("TLS_RSA_WITH_SEED_CBC_SHA", 150);
    final String javaName;

    public static CipherSuite forJavaName(String javaName2) {
        CipherSuite result = (CipherSuite) INSTANCES.get(javaName2);
        if (result != null) {
            return result;
        }
        CipherSuite sample = new CipherSuite(javaName2);
        CipherSuite canonical = (CipherSuite) INSTANCES.putIfAbsent(javaName2, sample);
        if (canonical == null) {
            return sample;
        }
        return canonical;
    }

    private CipherSuite(String javaName2) {
        if (javaName2 == null) {
            throw new NullPointerException();
        }
        this.javaName = javaName2;
    }

    /* renamed from: of */
    private static CipherSuite m3947of(String javaName2, int value) {
        return forJavaName(javaName2);
    }

    public String javaName() {
        return this.javaName;
    }

    public String toString() {
        return this.javaName;
    }
}
