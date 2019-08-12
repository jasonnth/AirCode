package com.jumio.commons.obfuscate;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class StringObfuscater {
    public static String format(byte[] in, long value) {
        byte[] out = new byte[in.length];
        new Random(value).nextBytes(out);
        for (int i = 0; i < in.length; i++) {
            out[i] = (byte) (out[i] ^ in[i]);
        }
        try {
            return new String(out, "UTF8");
        } catch (UnsupportedEncodingException e) {
            return new String(out);
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (args.length != 1) {
            System.out.println("Usage: StringFormatting in");
            return;
        }
        byte[] in = args[0].getBytes("UTF8");
        byte[] out = new byte[in.length];
        long value = SecureRandom.getInstance("SHA1PRNG").nextLong();
        new Random(value).nextBytes(out);
        for (int i = 0; i < in.length; i++) {
            out[i] = (byte) (out[i] ^ in[i]);
        }
        System.out.print("{ ");
        boolean first = true;
        for (byte b : out) {
            if (first) {
                first = false;
            } else {
                System.out.print(", ");
            }
            System.out.print(b);
        }
        System.out.println(" }, " + value + "l");
    }
}
