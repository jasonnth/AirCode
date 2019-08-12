package org.spongycastle.crypto.params;

import net.p318sf.scuba.smartcards.ISO7816;

public class DESParameters extends KeyParameter {
    public static final int DES_KEY_LENGTH = 8;
    private static byte[] DES_weak_keys = {1, 1, 1, 1, 1, 1, 1, 1, 31, 31, 31, 31, 14, 14, 14, 14, ISO7816.INS_CREATE_FILE, ISO7816.INS_CREATE_FILE, ISO7816.INS_CREATE_FILE, ISO7816.INS_CREATE_FILE, -15, -15, -15, -15, -2, -2, -2, -2, -2, -2, -2, -2, 1, -2, 1, -2, 1, -2, 1, -2, 31, ISO7816.INS_CREATE_FILE, 31, ISO7816.INS_CREATE_FILE, 14, -15, 14, -15, 1, ISO7816.INS_CREATE_FILE, 1, ISO7816.INS_CREATE_FILE, 1, -15, 1, -15, 31, -2, 31, -2, 14, -2, 14, -2, 1, 31, 1, 31, 1, 14, 1, 14, ISO7816.INS_CREATE_FILE, -2, ISO7816.INS_CREATE_FILE, -2, -15, -2, -15, -2, -2, 1, -2, 1, -2, 1, -2, 1, ISO7816.INS_CREATE_FILE, 31, ISO7816.INS_CREATE_FILE, 31, -15, 14, -15, 14, ISO7816.INS_CREATE_FILE, 1, ISO7816.INS_CREATE_FILE, 1, -15, 1, -15, 1, -2, 31, -2, 31, -2, 14, -2, 14, 31, 1, 31, 1, 14, 1, 14, 1, -2, ISO7816.INS_CREATE_FILE, -2, ISO7816.INS_CREATE_FILE, -2, -15, -2, -15};
    private static final int N_DES_WEAK_KEYS = 16;

    public DESParameters(byte[] key) {
        super(key);
        if (isWeakKey(key, 0)) {
            throw new IllegalArgumentException("attempt to create weak DES key");
        }
    }

    public static boolean isWeakKey(byte[] key, int offset) {
        if (key.length - offset < 8) {
            throw new IllegalArgumentException("key material too short.");
        }
        int i = 0;
        while (i < 16) {
            int j = 0;
            while (j < 8) {
                if (key[j + offset] != DES_weak_keys[(i * 8) + j]) {
                    i++;
                } else {
                    j++;
                }
            }
            return true;
        }
        return false;
    }

    public static void setOddParity(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            bytes[i] = (byte) ((b & 254) | (((((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6)) ^ (b >> 7)) ^ 1) & 1));
        }
    }
}
