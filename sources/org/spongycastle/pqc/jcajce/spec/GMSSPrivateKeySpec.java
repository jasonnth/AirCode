package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;
import java.util.Vector;
import org.spongycastle.pqc.crypto.gmss.GMSSLeaf;
import org.spongycastle.pqc.crypto.gmss.GMSSParameters;
import org.spongycastle.pqc.crypto.gmss.GMSSRootCalc;
import org.spongycastle.pqc.crypto.gmss.GMSSRootSig;
import org.spongycastle.pqc.crypto.gmss.Treehash;
import org.spongycastle.util.Arrays;

public class GMSSPrivateKeySpec implements KeySpec {
    private byte[][][] currentAuthPath;
    private Vector[][] currentRetain;
    private byte[][] currentRootSig;
    private byte[][] currentSeed;
    private Vector[] currentStack;
    private Treehash[][] currentTreehash;
    private GMSSParameters gmssPS;
    private int[] index;
    private byte[][][] keep;
    private int[] minTreehash;
    private byte[][][] nextAuthPath;
    private GMSSLeaf[] nextNextLeaf;
    private GMSSRootCalc[] nextNextRoot;
    private byte[][] nextNextSeed;
    private Vector[][] nextRetain;
    private byte[][] nextRoot;
    private GMSSRootSig[] nextRootSig;
    private Vector[] nextStack;
    private Treehash[][] nextTreehash;
    private GMSSLeaf[] upperLeaf;
    private GMSSLeaf[] upperTreehashLeaf;

    public GMSSPrivateKeySpec(int[] index2, byte[][] currentSeed2, byte[][] nextNextSeed2, byte[][][] currentAuthPath2, byte[][][] nextAuthPath2, Treehash[][] currentTreehash2, Treehash[][] nextTreehash2, Vector[] currentStack2, Vector[] nextStack2, Vector[][] currentRetain2, Vector[][] nextRetain2, byte[][][] keep2, GMSSLeaf[] nextNextLeaf2, GMSSLeaf[] upperLeaf2, GMSSLeaf[] upperTreehashLeaf2, int[] minTreehash2, byte[][] nextRoot2, GMSSRootCalc[] nextNextRoot2, byte[][] currentRootSig2, GMSSRootSig[] nextRootSig2, GMSSParameters gmssParameterset) {
        this.index = index2;
        this.currentSeed = currentSeed2;
        this.nextNextSeed = nextNextSeed2;
        this.currentAuthPath = currentAuthPath2;
        this.nextAuthPath = nextAuthPath2;
        this.currentTreehash = currentTreehash2;
        this.nextTreehash = nextTreehash2;
        this.currentStack = currentStack2;
        this.nextStack = nextStack2;
        this.currentRetain = currentRetain2;
        this.nextRetain = nextRetain2;
        this.keep = keep2;
        this.nextNextLeaf = nextNextLeaf2;
        this.upperLeaf = upperLeaf2;
        this.upperTreehashLeaf = upperTreehashLeaf2;
        this.minTreehash = minTreehash2;
        this.nextRoot = nextRoot2;
        this.nextNextRoot = nextNextRoot2;
        this.currentRootSig = currentRootSig2;
        this.nextRootSig = nextRootSig2;
        this.gmssPS = gmssParameterset;
    }

    public int[] getIndex() {
        return Arrays.clone(this.index);
    }

    public byte[][] getCurrentSeed() {
        return clone(this.currentSeed);
    }

    public byte[][] getNextNextSeed() {
        return clone(this.nextNextSeed);
    }

    public byte[][][] getCurrentAuthPath() {
        return clone(this.currentAuthPath);
    }

    public byte[][][] getNextAuthPath() {
        return clone(this.nextAuthPath);
    }

    public Treehash[][] getCurrentTreehash() {
        return clone(this.currentTreehash);
    }

    public Treehash[][] getNextTreehash() {
        return clone(this.nextTreehash);
    }

    public byte[][][] getKeep() {
        return clone(this.keep);
    }

    public Vector[] getCurrentStack() {
        return clone(this.currentStack);
    }

    public Vector[] getNextStack() {
        return clone(this.nextStack);
    }

    public Vector[][] getCurrentRetain() {
        return clone(this.currentRetain);
    }

    public Vector[][] getNextRetain() {
        return clone(this.nextRetain);
    }

    public GMSSLeaf[] getNextNextLeaf() {
        return clone(this.nextNextLeaf);
    }

    public GMSSLeaf[] getUpperLeaf() {
        return clone(this.upperLeaf);
    }

    public GMSSLeaf[] getUpperTreehashLeaf() {
        return clone(this.upperTreehashLeaf);
    }

    public int[] getMinTreehash() {
        return Arrays.clone(this.minTreehash);
    }

    public GMSSRootSig[] getNextRootSig() {
        return clone(this.nextRootSig);
    }

    public GMSSParameters getGmssPS() {
        return this.gmssPS;
    }

    public byte[][] getNextRoot() {
        return clone(this.nextRoot);
    }

    public GMSSRootCalc[] getNextNextRoot() {
        return clone(this.nextNextRoot);
    }

    public byte[][] getCurrentRootSig() {
        return clone(this.currentRootSig);
    }

    private static GMSSLeaf[] clone(GMSSLeaf[] data) {
        if (data == null) {
            return null;
        }
        GMSSLeaf[] copy = new GMSSLeaf[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        return copy;
    }

    private static GMSSRootCalc[] clone(GMSSRootCalc[] data) {
        if (data == null) {
            return null;
        }
        GMSSRootCalc[] copy = new GMSSRootCalc[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        return copy;
    }

    private static GMSSRootSig[] clone(GMSSRootSig[] data) {
        if (data == null) {
            return null;
        }
        GMSSRootSig[] copy = new GMSSRootSig[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        return copy;
    }

    private static byte[][] clone(byte[][] data) {
        if (data == null) {
            return null;
        }
        byte[][] copy = new byte[data.length][];
        for (int i = 0; i != data.length; i++) {
            copy[i] = Arrays.clone(data[i]);
        }
        return copy;
    }

    private static byte[][][] clone(byte[][][] data) {
        if (data == null) {
            return null;
        }
        byte[][][] copy = new byte[data.length][][];
        for (int i = 0; i != data.length; i++) {
            copy[i] = clone(data[i]);
        }
        return copy;
    }

    private static Treehash[] clone(Treehash[] data) {
        if (data == null) {
            return null;
        }
        Treehash[] copy = new Treehash[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        return copy;
    }

    private static Treehash[][] clone(Treehash[][] data) {
        if (data == null) {
            return null;
        }
        Treehash[][] copy = new Treehash[data.length][];
        for (int i = 0; i != data.length; i++) {
            copy[i] = clone(data[i]);
        }
        return copy;
    }

    private static Vector[] clone(Vector[] data) {
        if (data == null) {
            return null;
        }
        Vector[] copy = new Vector[data.length];
        for (int i = 0; i != data.length; i++) {
            copy[i] = new Vector(data[i]);
        }
        return copy;
    }

    private static Vector[][] clone(Vector[][] data) {
        if (data == null) {
            return null;
        }
        Vector[][] copy = new Vector[data.length][];
        for (int i = 0; i != data.length; i++) {
            copy[i] = clone(data[i]);
        }
        return copy;
    }
}
