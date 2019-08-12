package org.spongycastle.asn1.util;

import java.io.FileInputStream;
import java.io.InputStream;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;

public class Dump {
    public static void main(String[] args) throws Exception {
        ASN1InputStream bIn = new ASN1InputStream((InputStream) new FileInputStream(args[0]));
        while (true) {
            ASN1Primitive obj = bIn.readObject();
            if (obj != null) {
                System.out.println(ASN1Dump.dumpAsString(obj));
            } else {
                return;
            }
        }
    }
}
