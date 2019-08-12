package org.ejbca.cvc;

import java.util.HashMap;

public class AlgorithmUtil {
    private static HashMap<String, OIDField> algorithmMap = new HashMap<>();
    private static HashMap<String, String> conversionMap = new HashMap<>();

    static {
        algorithmMap.put("SHA1WITHRSA", CVCObjectIdentifiers.id_TA_RSA_v1_5_SHA_1);
        algorithmMap.put("SHA256WITHRSA", CVCObjectIdentifiers.id_TA_RSA_v1_5_SHA_256);
        algorithmMap.put("SHA1WITHRSAANDMGF1", CVCObjectIdentifiers.id_TA_RSA_PSS_SHA_1);
        algorithmMap.put("SHA256WITHRSAANDMGF1", CVCObjectIdentifiers.id_TA_RSA_PSS_SHA_256);
        algorithmMap.put("SHA1WITHECDSA", CVCObjectIdentifiers.id_TA_ECDSA_SHA_1);
        algorithmMap.put("SHA224WITHECDSA", CVCObjectIdentifiers.id_TA_ECDSA_SHA_224);
        algorithmMap.put("SHA256WITHECDSA", CVCObjectIdentifiers.id_TA_ECDSA_SHA_256);
        conversionMap.put("SHA1WITHECDSA", "SHA1WITHECDSA");
        conversionMap.put("SHA224WITHECDSA", "SHA224WITHECDSA");
        conversionMap.put("SHA256WITHECDSA", "SHA256WITHECDSA");
    }

    public static OIDField getOIDField(String str) {
        OIDField oIDField = (OIDField) algorithmMap.get(convertAlgorithmNameToCVC(str));
        if (oIDField != null) {
            return oIDField;
        }
        throw new IllegalArgumentException("Unsupported algorithmName: " + str);
    }

    public static String convertAlgorithmNameToCVC(String str) {
        String str2 = (String) conversionMap.get(str.toUpperCase());
        return str2 != null ? str2 : str.toUpperCase();
    }

    public static String getAlgorithmName(OIDField oIDField) {
        for (String str : algorithmMap.keySet()) {
            if (((OIDField) algorithmMap.get(str)).getValue().equals(oIDField.getValue())) {
                return str;
            }
        }
        throw new IllegalArgumentException("Unknown OIDField: " + oIDField.getValue());
    }
}
