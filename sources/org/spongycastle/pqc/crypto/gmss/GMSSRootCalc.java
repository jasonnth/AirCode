package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.encoders.Hex;

public class GMSSRootCalc {
    private byte[][] AuthPath;

    /* renamed from: K */
    private int f7085K;
    private GMSSDigestProvider digestProvider;
    private int heightOfNextSeed;
    private Vector heightOfNodes;
    private int heightOfTree;
    private int[] index;
    private int indexForNextSeed;
    private boolean isFinished;
    private boolean isInitialized;
    private int mdLength;
    private Digest messDigestTree;
    private Vector[] retain;
    private byte[] root;
    private Vector tailStack;
    private Treehash[] treehash;

    public GMSSRootCalc(Digest digest, byte[][] statByte, int[] statInt, Treehash[] treeH, Vector[] ret) {
        this.messDigestTree = this.digestProvider.get();
        this.digestProvider = this.digestProvider;
        this.heightOfTree = statInt[0];
        this.mdLength = statInt[1];
        this.f7085K = statInt[2];
        this.indexForNextSeed = statInt[3];
        this.heightOfNextSeed = statInt[4];
        if (statInt[5] == 1) {
            this.isFinished = true;
        } else {
            this.isFinished = false;
        }
        if (statInt[6] == 1) {
            this.isInitialized = true;
        } else {
            this.isInitialized = false;
        }
        int tailLength = statInt[7];
        this.index = new int[this.heightOfTree];
        for (int i = 0; i < this.heightOfTree; i++) {
            this.index[i] = statInt[i + 8];
        }
        this.heightOfNodes = new Vector();
        for (int i2 = 0; i2 < tailLength; i2++) {
            this.heightOfNodes.addElement(Integers.valueOf(statInt[this.heightOfTree + 8 + i2]));
        }
        this.root = statByte[0];
        this.AuthPath = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.heightOfTree, this.mdLength});
        for (int i3 = 0; i3 < this.heightOfTree; i3++) {
            this.AuthPath[i3] = statByte[i3 + 1];
        }
        this.tailStack = new Vector();
        for (int i4 = 0; i4 < tailLength; i4++) {
            this.tailStack.addElement(statByte[this.heightOfTree + 1 + i4]);
        }
        this.treehash = GMSSUtils.clone(treeH);
        this.retain = GMSSUtils.clone(ret);
    }

    public GMSSRootCalc(int heightOfTree2, int K, GMSSDigestProvider digestProvider2) {
        this.heightOfTree = heightOfTree2;
        this.digestProvider = digestProvider2;
        this.messDigestTree = digestProvider2.get();
        this.mdLength = this.messDigestTree.getDigestSize();
        this.f7085K = K;
        this.index = new int[heightOfTree2];
        this.AuthPath = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{heightOfTree2, this.mdLength});
        this.root = new byte[this.mdLength];
        this.retain = new Vector[(this.f7085K - 1)];
        for (int i = 0; i < K - 1; i++) {
            this.retain[i] = new Vector();
        }
    }

    public void initialize(Vector sharedStack) {
        this.treehash = new Treehash[(this.heightOfTree - this.f7085K)];
        for (int i = 0; i < this.heightOfTree - this.f7085K; i++) {
            this.treehash[i] = new Treehash(sharedStack, i, this.digestProvider.get());
        }
        this.index = new int[this.heightOfTree];
        this.AuthPath = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.heightOfTree, this.mdLength});
        this.root = new byte[this.mdLength];
        this.tailStack = new Vector();
        this.heightOfNodes = new Vector();
        this.isInitialized = true;
        this.isFinished = false;
        for (int i2 = 0; i2 < this.heightOfTree; i2++) {
            this.index[i2] = -1;
        }
        this.retain = new Vector[(this.f7085K - 1)];
        for (int i3 = 0; i3 < this.f7085K - 1; i3++) {
            this.retain[i3] = new Vector();
        }
        this.indexForNextSeed = 3;
        this.heightOfNextSeed = 0;
    }

    public void update(byte[] seed, byte[] leaf) {
        if (this.heightOfNextSeed < this.heightOfTree - this.f7085K && this.indexForNextSeed - 2 == this.index[0]) {
            initializeTreehashSeed(seed, this.heightOfNextSeed);
            this.heightOfNextSeed++;
            this.indexForNextSeed *= 2;
        }
        update(leaf);
    }

    public void update(byte[] leaf) {
        if (this.isFinished) {
            System.out.print("Too much updates for Tree!!");
        } else if (!this.isInitialized) {
            System.err.println("GMSSRootCalc not initialized!");
        } else {
            int[] iArr = this.index;
            iArr[0] = iArr[0] + 1;
            if (this.index[0] == 1) {
                System.arraycopy(leaf, 0, this.AuthPath[0], 0, this.mdLength);
            } else if (this.index[0] == 3 && this.heightOfTree > this.f7085K) {
                this.treehash[0].setFirstNode(leaf);
            }
            if ((this.index[0] - 3) % 2 == 0 && this.index[0] >= 3 && this.heightOfTree == this.f7085K) {
                this.retain[0].insertElementAt(leaf, 0);
            }
            if (this.index[0] == 0) {
                this.tailStack.addElement(leaf);
                this.heightOfNodes.addElement(Integers.valueOf(0));
                return;
            }
            byte[] help = new byte[this.mdLength];
            byte[] toBeHashed = new byte[(this.mdLength << 1)];
            System.arraycopy(leaf, 0, help, 0, this.mdLength);
            int helpHeight = 0;
            while (this.tailStack.size() > 0 && helpHeight == ((Integer) this.heightOfNodes.lastElement()).intValue()) {
                System.arraycopy(this.tailStack.lastElement(), 0, toBeHashed, 0, this.mdLength);
                this.tailStack.removeElementAt(this.tailStack.size() - 1);
                this.heightOfNodes.removeElementAt(this.heightOfNodes.size() - 1);
                System.arraycopy(help, 0, toBeHashed, this.mdLength, this.mdLength);
                this.messDigestTree.update(toBeHashed, 0, toBeHashed.length);
                help = new byte[this.messDigestTree.getDigestSize()];
                this.messDigestTree.doFinal(help, 0);
                helpHeight++;
                if (helpHeight < this.heightOfTree) {
                    int[] iArr2 = this.index;
                    iArr2[helpHeight] = iArr2[helpHeight] + 1;
                    if (this.index[helpHeight] == 1) {
                        System.arraycopy(help, 0, this.AuthPath[helpHeight], 0, this.mdLength);
                    }
                    if (helpHeight >= this.heightOfTree - this.f7085K) {
                        if (helpHeight == 0) {
                            System.out.println("M���P");
                        }
                        if ((this.index[helpHeight] - 3) % 2 == 0 && this.index[helpHeight] >= 3) {
                            this.retain[helpHeight - (this.heightOfTree - this.f7085K)].insertElementAt(help, 0);
                        }
                    } else if (this.index[helpHeight] == 3) {
                        this.treehash[helpHeight].setFirstNode(help);
                    }
                }
            }
            this.tailStack.addElement(help);
            this.heightOfNodes.addElement(Integers.valueOf(helpHeight));
            if (helpHeight == this.heightOfTree) {
                this.isFinished = true;
                this.isInitialized = false;
                this.root = (byte[]) this.tailStack.lastElement();
            }
        }
    }

    public void initializeTreehashSeed(byte[] seed, int index2) {
        this.treehash[index2].initializeSeed(seed);
    }

    public boolean wasInitialized() {
        return this.isInitialized;
    }

    public boolean wasFinished() {
        return this.isFinished;
    }

    public byte[][] getAuthPath() {
        return GMSSUtils.clone(this.AuthPath);
    }

    public Treehash[] getTreehash() {
        return GMSSUtils.clone(this.treehash);
    }

    public Vector[] getRetain() {
        return GMSSUtils.clone(this.retain);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.root);
    }

    public Vector getStack() {
        Vector copy = new Vector();
        Enumeration en = this.tailStack.elements();
        while (en.hasMoreElements()) {
            copy.addElement(en.nextElement());
        }
        return copy;
    }

    public byte[][] getStatByte() {
        int tailLength;
        if (this.tailStack == null) {
            tailLength = 0;
        } else {
            tailLength = this.tailStack.size();
        }
        byte[][] statByte = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.heightOfTree + 1 + tailLength, 64});
        statByte[0] = this.root;
        for (int i = 0; i < this.heightOfTree; i++) {
            statByte[i + 1] = this.AuthPath[i];
        }
        for (int i2 = 0; i2 < tailLength; i2++) {
            statByte[this.heightOfTree + 1 + i2] = (byte[]) this.tailStack.elementAt(i2);
        }
        return statByte;
    }

    public int[] getStatInt() {
        int tailLength;
        if (this.tailStack == null) {
            tailLength = 0;
        } else {
            tailLength = this.tailStack.size();
        }
        int[] statInt = new int[(this.heightOfTree + 8 + tailLength)];
        statInt[0] = this.heightOfTree;
        statInt[1] = this.mdLength;
        statInt[2] = this.f7085K;
        statInt[3] = this.indexForNextSeed;
        statInt[4] = this.heightOfNextSeed;
        if (this.isFinished) {
            statInt[5] = 1;
        } else {
            statInt[5] = 0;
        }
        if (this.isInitialized) {
            statInt[6] = 1;
        } else {
            statInt[6] = 0;
        }
        statInt[7] = tailLength;
        for (int i = 0; i < this.heightOfTree; i++) {
            statInt[i + 8] = this.index[i];
        }
        for (int i2 = 0; i2 < tailLength; i2++) {
            statInt[this.heightOfTree + 8 + i2] = ((Integer) this.heightOfNodes.elementAt(i2)).intValue();
        }
        return statInt;
    }

    public String toString() {
        int tailLength;
        String out = "";
        if (this.tailStack == null) {
            tailLength = 0;
        } else {
            tailLength = this.tailStack.size();
        }
        for (int i = 0; i < this.heightOfTree + 8 + tailLength; i++) {
            out = out + getStatInt()[i] + " ";
        }
        for (int i2 = 0; i2 < this.heightOfTree + 1 + tailLength; i2++) {
            out = out + new String(Hex.encode(getStatByte()[i2])) + " ";
        }
        return out + "  " + this.digestProvider.get().getDigestSize();
    }
}
