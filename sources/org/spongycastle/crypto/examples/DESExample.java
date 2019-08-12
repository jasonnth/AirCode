package org.spongycastle.crypto.examples;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.generators.DESedeKeyGenerator;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.encoders.Hex;

public class DESExample {
    private PaddedBufferedBlockCipher cipher = null;
    private boolean encrypt = true;

    /* renamed from: in */
    private BufferedInputStream f6723in = null;
    private byte[] key = null;
    private BufferedOutputStream out = null;

    public static void main(String[] args) {
        boolean encrypt2 = true;
        if (args.length < 2) {
            System.err.println("Usage: java " + new DESExample().getClass().getName() + " infile outfile [keyfile]");
            System.exit(1);
        }
        String keyfile = "deskey.dat";
        String infile = args[0];
        String outfile = args[1];
        if (args.length > 2) {
            encrypt2 = false;
            keyfile = args[2];
        }
        new DESExample(infile, outfile, keyfile, encrypt2).process();
    }

    public DESExample() {
    }

    public DESExample(String infile, String outfile, String keyfile, boolean encrypt2) {
        this.encrypt = encrypt2;
        try {
            this.f6723in = new BufferedInputStream(new FileInputStream(infile));
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found [" + infile + "]");
            System.exit(1);
        }
        try {
            this.out = new BufferedOutputStream(new FileOutputStream(outfile));
        } catch (IOException e2) {
            System.err.println("Output file not created [" + outfile + "]");
            System.exit(1);
        }
        if (encrypt2) {
            SecureRandom sr = null;
            try {
                SecureRandom sr2 = new SecureRandom();
                try {
                    sr2.setSeed("www.bouncycastle.org".getBytes());
                    sr = sr2;
                } catch (Exception e3) {
                    sr = sr2;
                    try {
                        System.err.println("Hmmm, no SHA1PRNG, you need the Sun implementation");
                        System.exit(1);
                        KeyGenerationParameters kgp = new KeyGenerationParameters(sr, 192);
                        DESedeKeyGenerator kg = new DESedeKeyGenerator();
                        kg.init(kgp);
                        this.key = kg.generateKey();
                        BufferedOutputStream keystream = new BufferedOutputStream(new FileOutputStream(keyfile));
                        byte[] keyhex = Hex.encode(this.key);
                        keystream.write(keyhex, 0, keyhex.length);
                        keystream.flush();
                        keystream.close();
                        return;
                    } catch (IOException e4) {
                    }
                } catch (IOException e5) {
                    SecureRandom secureRandom = sr2;
                    System.err.println("Could not decryption create key file [" + keyfile + "]");
                    System.exit(1);
                    return;
                }
            } catch (Exception e6) {
                System.err.println("Hmmm, no SHA1PRNG, you need the Sun implementation");
                System.exit(1);
                KeyGenerationParameters kgp2 = new KeyGenerationParameters(sr, 192);
                DESedeKeyGenerator kg2 = new DESedeKeyGenerator();
                kg2.init(kgp2);
                this.key = kg2.generateKey();
                BufferedOutputStream keystream2 = new BufferedOutputStream(new FileOutputStream(keyfile));
                byte[] keyhex2 = Hex.encode(this.key);
                keystream2.write(keyhex2, 0, keyhex2.length);
                keystream2.flush();
                keystream2.close();
                return;
            }
            KeyGenerationParameters kgp22 = new KeyGenerationParameters(sr, 192);
            DESedeKeyGenerator kg22 = new DESedeKeyGenerator();
            kg22.init(kgp22);
            this.key = kg22.generateKey();
            BufferedOutputStream keystream22 = new BufferedOutputStream(new FileOutputStream(keyfile));
            byte[] keyhex22 = Hex.encode(this.key);
            keystream22.write(keyhex22, 0, keyhex22.length);
            keystream22.flush();
            keystream22.close();
            return;
        }
        try {
            BufferedInputStream keystream3 = new BufferedInputStream(new FileInputStream(keyfile));
            int len = keystream3.available();
            byte[] keyhex3 = new byte[len];
            keystream3.read(keyhex3, 0, len);
            this.key = Hex.decode(keyhex3);
        } catch (IOException e7) {
            System.err.println("Decryption key file not found, or not valid [" + keyfile + "]");
            System.exit(1);
        }
    }

    private void process() {
        this.cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()));
        if (this.encrypt) {
            performEncrypt(this.key);
        } else {
            performDecrypt(this.key);
        }
        try {
            this.f6723in.close();
            this.out.flush();
            this.out.close();
        } catch (IOException e) {
        }
    }

    private void performEncrypt(byte[] key2) {
        this.cipher.init(true, new KeyParameter(key2));
        byte[] inblock = new byte[47];
        byte[] outblock = new byte[this.cipher.getOutputSize(47)];
        while (true) {
            try {
                int inL = this.f6723in.read(inblock, 0, 47);
                if (inL > 0) {
                    int outL = this.cipher.processBytes(inblock, 0, inL, outblock, 0);
                    if (outL > 0) {
                        byte[] rv = Hex.encode(outblock, 0, outL);
                        this.out.write(rv, 0, rv.length);
                        this.out.write(10);
                    }
                } else {
                    try {
                        break;
                    } catch (CryptoException e) {
                        return;
                    }
                }
            } catch (IOException ioeread) {
                ioeread.printStackTrace();
                return;
            }
        }
        int outL2 = this.cipher.doFinal(outblock, 0);
        if (outL2 > 0) {
            byte[] rv2 = Hex.encode(outblock, 0, outL2);
            this.out.write(rv2, 0, rv2.length);
            this.out.write(10);
        }
    }

    private void performDecrypt(byte[] key2) {
        this.cipher.init(false, new KeyParameter(key2));
        BufferedReader br = new BufferedReader(new InputStreamReader(this.f6723in));
        byte[] outblock = null;
        while (true) {
            try {
                String rv = br.readLine();
                if (rv != null) {
                    byte[] inblock = Hex.decode(rv);
                    outblock = new byte[this.cipher.getOutputSize(inblock.length)];
                    int outL = this.cipher.processBytes(inblock, 0, inblock.length, outblock, 0);
                    if (outL > 0) {
                        this.out.write(outblock, 0, outL);
                    }
                } else {
                    try {
                        break;
                    } catch (CryptoException e) {
                        return;
                    }
                }
            } catch (IOException ioeread) {
                ioeread.printStackTrace();
                return;
            }
        }
        int outL2 = this.cipher.doFinal(outblock, 0);
        if (outL2 > 0) {
            this.out.write(outblock, 0, outL2);
        }
    }
}
