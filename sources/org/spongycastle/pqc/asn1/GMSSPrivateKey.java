package org.spongycastle.pqc.asn1;

import java.math.BigInteger;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.pqc.crypto.gmss.GMSSLeaf;
import org.spongycastle.pqc.crypto.gmss.GMSSParameters;
import org.spongycastle.pqc.crypto.gmss.GMSSRootCalc;
import org.spongycastle.pqc.crypto.gmss.GMSSRootSig;
import org.spongycastle.pqc.crypto.gmss.Treehash;

public class GMSSPrivateKey extends ASN1Object {
    private ASN1Primitive primitive;

    private GMSSPrivateKey(ASN1Sequence mtsPrivateKey) {
        ASN1Sequence indexPart = (ASN1Sequence) mtsPrivateKey.getObjectAt(0);
        int[] index = new int[indexPart.size()];
        for (int i = 0; i < indexPart.size(); i++) {
            index[i] = checkBigIntegerInIntRange(indexPart.getObjectAt(i));
        }
        ASN1Sequence curSeedsPart = (ASN1Sequence) mtsPrivateKey.getObjectAt(1);
        byte[][] curSeeds = new byte[curSeedsPart.size()][];
        for (int i2 = 0; i2 < curSeeds.length; i2++) {
            curSeeds[i2] = ((DEROctetString) curSeedsPart.getObjectAt(i2)).getOctets();
        }
        ASN1Sequence nextNextSeedsPart = (ASN1Sequence) mtsPrivateKey.getObjectAt(2);
        byte[][] nextNextSeeds = new byte[nextNextSeedsPart.size()][];
        for (int i3 = 0; i3 < nextNextSeeds.length; i3++) {
            nextNextSeeds[i3] = ((DEROctetString) nextNextSeedsPart.getObjectAt(i3)).getOctets();
        }
        ASN1Sequence curAuthPart0 = (ASN1Sequence) mtsPrivateKey.getObjectAt(3);
        byte[][][] curAuth = new byte[curAuthPart0.size()][][];
        for (int i4 = 0; i4 < curAuth.length; i4++) {
            ASN1Sequence curAuthPart1 = (ASN1Sequence) curAuthPart0.getObjectAt(i4);
            curAuth[i4] = new byte[curAuthPart1.size()][];
            for (int j = 0; j < curAuth[i4].length; j++) {
                curAuth[i4][j] = ((DEROctetString) curAuthPart1.getObjectAt(j)).getOctets();
            }
        }
        ASN1Sequence nextAuthPart0 = (ASN1Sequence) mtsPrivateKey.getObjectAt(4);
        byte[][][] nextAuth = new byte[nextAuthPart0.size()][][];
        for (int i5 = 0; i5 < nextAuth.length; i5++) {
            ASN1Sequence nextAuthPart1 = (ASN1Sequence) nextAuthPart0.getObjectAt(i5);
            nextAuth[i5] = new byte[nextAuthPart1.size()][];
            for (int j2 = 0; j2 < nextAuth[i5].length; j2++) {
                nextAuth[i5][j2] = ((DEROctetString) nextAuthPart1.getObjectAt(j2)).getOctets();
            }
        }
        Treehash[][] treehashArr = new Treehash[((ASN1Sequence) mtsPrivateKey.getObjectAt(5)).size()][];
    }

    public GMSSPrivateKey(int[] index, byte[][] currentSeed, byte[][] nextNextSeed, byte[][][] currentAuthPath, byte[][][] nextAuthPath, Treehash[][] currentTreehash, Treehash[][] nextTreehash, Vector[] currentStack, Vector[] nextStack, Vector[][] currentRetain, Vector[][] nextRetain, byte[][][] keep, GMSSLeaf[] nextNextLeaf, GMSSLeaf[] upperLeaf, GMSSLeaf[] upperTreehashLeaf, int[] minTreehash, byte[][] nextRoot, GMSSRootCalc[] nextNextRoot, byte[][] currentRootSig, GMSSRootSig[] nextRootSig, GMSSParameters gmssParameterset, AlgorithmIdentifier digestAlg) {
        this.primitive = encode(index, currentSeed, nextNextSeed, currentAuthPath, nextAuthPath, keep, currentTreehash, nextTreehash, currentStack, nextStack, currentRetain, nextRetain, nextNextLeaf, upperLeaf, upperTreehashLeaf, minTreehash, nextRoot, nextNextRoot, currentRootSig, nextRootSig, gmssParameterset, new AlgorithmIdentifier[]{digestAlg});
    }

    private ASN1Primitive encode(int[] index, byte[][] currentSeeds, byte[][] nextNextSeeds, byte[][][] currentAuthPaths, byte[][][] nextAuthPaths, byte[][][] keep, Treehash[][] currentTreehash, Treehash[][] nextTreehash, Vector[] currentStack, Vector[] nextStack, Vector[][] currentRetain, Vector[][] nextRetain, GMSSLeaf[] nextNextLeaf, GMSSLeaf[] upperLeaf, GMSSLeaf[] upperTreehashLeaf, int[] minTreehash, byte[][] nextRoot, GMSSRootCalc[] nextNextRoot, byte[][] currentRootSig, GMSSRootSig[] nextRootSig, GMSSParameters gmssParameterset, AlgorithmIdentifier[] algorithms) {
        ASN1EncodableVector result = new ASN1EncodableVector();
        ASN1EncodableVector indexPart = new ASN1EncodableVector();
        for (int i = 0; i < index.length; i++) {
            indexPart.add(new ASN1Integer((long) index[i]));
        }
        DERSequence dERSequence = new DERSequence(indexPart);
        result.add(dERSequence);
        ASN1EncodableVector curSeedsPart = new ASN1EncodableVector();
        for (byte[] dEROctetString : currentSeeds) {
            curSeedsPart.add(new DEROctetString(dEROctetString));
        }
        DERSequence dERSequence2 = new DERSequence(curSeedsPart);
        result.add(dERSequence2);
        ASN1EncodableVector nextNextSeedsPart = new ASN1EncodableVector();
        for (byte[] dEROctetString2 : nextNextSeeds) {
            nextNextSeedsPart.add(new DEROctetString(dEROctetString2));
        }
        DERSequence dERSequence3 = new DERSequence(nextNextSeedsPart);
        result.add(dERSequence3);
        ASN1EncodableVector curAuthPart0 = new ASN1EncodableVector();
        ASN1EncodableVector curAuthPart1 = new ASN1EncodableVector();
        for (int i2 = 0; i2 < currentAuthPaths.length; i2++) {
            for (byte[] dEROctetString3 : currentAuthPaths[i2]) {
                curAuthPart0.add(new DEROctetString(dEROctetString3));
            }
            DERSequence dERSequence4 = new DERSequence(curAuthPart0);
            curAuthPart1.add(dERSequence4);
            curAuthPart0 = new ASN1EncodableVector();
        }
        DERSequence dERSequence5 = new DERSequence(curAuthPart1);
        result.add(dERSequence5);
        ASN1EncodableVector nextAuthPart0 = new ASN1EncodableVector();
        ASN1EncodableVector nextAuthPart1 = new ASN1EncodableVector();
        for (int i3 = 0; i3 < nextAuthPaths.length; i3++) {
            for (byte[] dEROctetString4 : nextAuthPaths[i3]) {
                nextAuthPart0.add(new DEROctetString(dEROctetString4));
            }
            DERSequence dERSequence6 = new DERSequence(nextAuthPart0);
            nextAuthPart1.add(dERSequence6);
            nextAuthPart0 = new ASN1EncodableVector();
        }
        DERSequence dERSequence7 = new DERSequence(nextAuthPart1);
        result.add(dERSequence7);
        ASN1EncodableVector seqOfTreehash0 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfTreehash1 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfStat = new ASN1EncodableVector();
        ASN1EncodableVector seqOfByte = new ASN1EncodableVector();
        ASN1EncodableVector seqOfInt = new ASN1EncodableVector();
        for (int i4 = 0; i4 < currentTreehash.length; i4++) {
            for (int j = 0; j < currentTreehash[i4].length; j++) {
                seqOfStat.add(new DERSequence((ASN1Encodable) algorithms[0]));
                int tailLength = currentTreehash[i4][j].getStatInt()[1];
                seqOfByte.add(new DEROctetString(currentTreehash[i4][j].getStatByte()[0]));
                seqOfByte.add(new DEROctetString(currentTreehash[i4][j].getStatByte()[1]));
                seqOfByte.add(new DEROctetString(currentTreehash[i4][j].getStatByte()[2]));
                for (int k = 0; k < tailLength; k++) {
                    seqOfByte.add(new DEROctetString(currentTreehash[i4][j].getStatByte()[k + 3]));
                }
                DERSequence dERSequence8 = new DERSequence(seqOfByte);
                seqOfStat.add(dERSequence8);
                seqOfByte = new ASN1EncodableVector();
                seqOfInt.add(new ASN1Integer((long) currentTreehash[i4][j].getStatInt()[0]));
                seqOfInt.add(new ASN1Integer((long) tailLength));
                seqOfInt.add(new ASN1Integer((long) currentTreehash[i4][j].getStatInt()[2]));
                seqOfInt.add(new ASN1Integer((long) currentTreehash[i4][j].getStatInt()[3]));
                seqOfInt.add(new ASN1Integer((long) currentTreehash[i4][j].getStatInt()[4]));
                seqOfInt.add(new ASN1Integer((long) currentTreehash[i4][j].getStatInt()[5]));
                for (int k2 = 0; k2 < tailLength; k2++) {
                    seqOfInt.add(new ASN1Integer((long) currentTreehash[i4][j].getStatInt()[k2 + 6]));
                }
                DERSequence dERSequence9 = new DERSequence(seqOfInt);
                seqOfStat.add(dERSequence9);
                seqOfInt = new ASN1EncodableVector();
                DERSequence dERSequence10 = new DERSequence(seqOfStat);
                seqOfTreehash1.add(dERSequence10);
                seqOfStat = new ASN1EncodableVector();
            }
            DERSequence dERSequence11 = new DERSequence(seqOfTreehash1);
            seqOfTreehash0.add(dERSequence11);
            seqOfTreehash1 = new ASN1EncodableVector();
        }
        DERSequence dERSequence12 = new DERSequence(seqOfTreehash0);
        result.add(dERSequence12);
        ASN1EncodableVector seqOfTreehash02 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfTreehash12 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfStat2 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfByte2 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfInt2 = new ASN1EncodableVector();
        for (int i5 = 0; i5 < nextTreehash.length; i5++) {
            for (int j2 = 0; j2 < nextTreehash[i5].length; j2++) {
                seqOfStat2.add(new DERSequence((ASN1Encodable) algorithms[0]));
                int tailLength2 = nextTreehash[i5][j2].getStatInt()[1];
                seqOfByte2.add(new DEROctetString(nextTreehash[i5][j2].getStatByte()[0]));
                seqOfByte2.add(new DEROctetString(nextTreehash[i5][j2].getStatByte()[1]));
                seqOfByte2.add(new DEROctetString(nextTreehash[i5][j2].getStatByte()[2]));
                for (int k3 = 0; k3 < tailLength2; k3++) {
                    seqOfByte2.add(new DEROctetString(nextTreehash[i5][j2].getStatByte()[k3 + 3]));
                }
                DERSequence dERSequence13 = new DERSequence(seqOfByte2);
                seqOfStat2.add(dERSequence13);
                seqOfByte2 = new ASN1EncodableVector();
                seqOfInt2.add(new ASN1Integer((long) nextTreehash[i5][j2].getStatInt()[0]));
                seqOfInt2.add(new ASN1Integer((long) tailLength2));
                seqOfInt2.add(new ASN1Integer((long) nextTreehash[i5][j2].getStatInt()[2]));
                seqOfInt2.add(new ASN1Integer((long) nextTreehash[i5][j2].getStatInt()[3]));
                seqOfInt2.add(new ASN1Integer((long) nextTreehash[i5][j2].getStatInt()[4]));
                seqOfInt2.add(new ASN1Integer((long) nextTreehash[i5][j2].getStatInt()[5]));
                for (int k4 = 0; k4 < tailLength2; k4++) {
                    seqOfInt2.add(new ASN1Integer((long) nextTreehash[i5][j2].getStatInt()[k4 + 6]));
                }
                DERSequence dERSequence14 = new DERSequence(seqOfInt2);
                seqOfStat2.add(dERSequence14);
                seqOfInt2 = new ASN1EncodableVector();
                DERSequence dERSequence15 = new DERSequence(seqOfStat2);
                seqOfTreehash12.add(dERSequence15);
                seqOfStat2 = new ASN1EncodableVector();
            }
            DERSequence dERSequence16 = new DERSequence(seqOfTreehash12);
            seqOfTreehash02.add(new DERSequence((ASN1Encodable) dERSequence16));
            seqOfTreehash12 = new ASN1EncodableVector();
        }
        DERSequence dERSequence17 = new DERSequence(seqOfTreehash02);
        result.add(dERSequence17);
        ASN1EncodableVector keepPart0 = new ASN1EncodableVector();
        ASN1EncodableVector keepPart1 = new ASN1EncodableVector();
        for (int i6 = 0; i6 < keep.length; i6++) {
            for (byte[] dEROctetString5 : keep[i6]) {
                keepPart0.add(new DEROctetString(dEROctetString5));
            }
            DERSequence dERSequence18 = new DERSequence(keepPart0);
            keepPart1.add(dERSequence18);
            keepPart0 = new ASN1EncodableVector();
        }
        DERSequence dERSequence19 = new DERSequence(keepPart1);
        result.add(dERSequence19);
        ASN1EncodableVector curStackPart0 = new ASN1EncodableVector();
        ASN1EncodableVector curStackPart1 = new ASN1EncodableVector();
        for (int i7 = 0; i7 < currentStack.length; i7++) {
            for (int j3 = 0; j3 < currentStack[i7].size(); j3++) {
                DEROctetString dEROctetString6 = new DEROctetString((byte[]) currentStack[i7].elementAt(j3));
                curStackPart0.add(dEROctetString6);
            }
            DERSequence dERSequence20 = new DERSequence(curStackPart0);
            curStackPart1.add(dERSequence20);
            curStackPart0 = new ASN1EncodableVector();
        }
        DERSequence dERSequence21 = new DERSequence(curStackPart1);
        result.add(dERSequence21);
        ASN1EncodableVector nextStackPart0 = new ASN1EncodableVector();
        ASN1EncodableVector nextStackPart1 = new ASN1EncodableVector();
        for (int i8 = 0; i8 < nextStack.length; i8++) {
            for (int j4 = 0; j4 < nextStack[i8].size(); j4++) {
                DEROctetString dEROctetString7 = new DEROctetString((byte[]) nextStack[i8].elementAt(j4));
                nextStackPart0.add(dEROctetString7);
            }
            DERSequence dERSequence22 = new DERSequence(nextStackPart0);
            nextStackPart1.add(dERSequence22);
            nextStackPart0 = new ASN1EncodableVector();
        }
        DERSequence dERSequence23 = new DERSequence(nextStackPart1);
        result.add(dERSequence23);
        ASN1EncodableVector currentRetainPart0 = new ASN1EncodableVector();
        ASN1EncodableVector currentRetainPart1 = new ASN1EncodableVector();
        ASN1EncodableVector currentRetainPart2 = new ASN1EncodableVector();
        for (int i9 = 0; i9 < currentRetain.length; i9++) {
            for (int j5 = 0; j5 < currentRetain[i9].length; j5++) {
                for (int k5 = 0; k5 < currentRetain[i9][j5].size(); k5++) {
                    DEROctetString dEROctetString8 = new DEROctetString((byte[]) currentRetain[i9][j5].elementAt(k5));
                    currentRetainPart0.add(dEROctetString8);
                }
                DERSequence dERSequence24 = new DERSequence(currentRetainPart0);
                currentRetainPart1.add(dERSequence24);
                currentRetainPart0 = new ASN1EncodableVector();
            }
            DERSequence dERSequence25 = new DERSequence(currentRetainPart1);
            currentRetainPart2.add(dERSequence25);
            currentRetainPart1 = new ASN1EncodableVector();
        }
        DERSequence dERSequence26 = new DERSequence(currentRetainPart2);
        result.add(dERSequence26);
        ASN1EncodableVector nextRetainPart0 = new ASN1EncodableVector();
        ASN1EncodableVector nextRetainPart1 = new ASN1EncodableVector();
        ASN1EncodableVector nextRetainPart2 = new ASN1EncodableVector();
        for (int i10 = 0; i10 < nextRetain.length; i10++) {
            for (int j6 = 0; j6 < nextRetain[i10].length; j6++) {
                for (int k6 = 0; k6 < nextRetain[i10][j6].size(); k6++) {
                    DEROctetString dEROctetString9 = new DEROctetString((byte[]) nextRetain[i10][j6].elementAt(k6));
                    nextRetainPart0.add(dEROctetString9);
                }
                DERSequence dERSequence27 = new DERSequence(nextRetainPart0);
                nextRetainPart1.add(dERSequence27);
                nextRetainPart0 = new ASN1EncodableVector();
            }
            DERSequence dERSequence28 = new DERSequence(nextRetainPart1);
            nextRetainPart2.add(dERSequence28);
            nextRetainPart1 = new ASN1EncodableVector();
        }
        DERSequence dERSequence29 = new DERSequence(nextRetainPart2);
        result.add(dERSequence29);
        ASN1EncodableVector seqOfLeaf = new ASN1EncodableVector();
        ASN1EncodableVector seqOfStat3 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfByte3 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfInt3 = new ASN1EncodableVector();
        for (int i11 = 0; i11 < nextNextLeaf.length; i11++) {
            seqOfStat3.add(new DERSequence((ASN1Encodable) algorithms[0]));
            byte[][] tempByte = nextNextLeaf[i11].getStatByte();
            seqOfByte3.add(new DEROctetString(tempByte[0]));
            seqOfByte3.add(new DEROctetString(tempByte[1]));
            seqOfByte3.add(new DEROctetString(tempByte[2]));
            seqOfByte3.add(new DEROctetString(tempByte[3]));
            DERSequence dERSequence30 = new DERSequence(seqOfByte3);
            seqOfStat3.add(dERSequence30);
            seqOfByte3 = new ASN1EncodableVector();
            int[] tempInt = nextNextLeaf[i11].getStatInt();
            seqOfInt3.add(new ASN1Integer((long) tempInt[0]));
            seqOfInt3.add(new ASN1Integer((long) tempInt[1]));
            seqOfInt3.add(new ASN1Integer((long) tempInt[2]));
            seqOfInt3.add(new ASN1Integer((long) tempInt[3]));
            DERSequence dERSequence31 = new DERSequence(seqOfInt3);
            seqOfStat3.add(dERSequence31);
            seqOfInt3 = new ASN1EncodableVector();
            DERSequence dERSequence32 = new DERSequence(seqOfStat3);
            seqOfLeaf.add(dERSequence32);
            seqOfStat3 = new ASN1EncodableVector();
        }
        DERSequence dERSequence33 = new DERSequence(seqOfLeaf);
        result.add(dERSequence33);
        ASN1EncodableVector seqOfUpperLeaf = new ASN1EncodableVector();
        ASN1EncodableVector seqOfStat4 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfByte4 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfInt4 = new ASN1EncodableVector();
        for (int i12 = 0; i12 < upperLeaf.length; i12++) {
            seqOfStat4.add(new DERSequence((ASN1Encodable) algorithms[0]));
            byte[][] tempByte2 = upperLeaf[i12].getStatByte();
            seqOfByte4.add(new DEROctetString(tempByte2[0]));
            seqOfByte4.add(new DEROctetString(tempByte2[1]));
            seqOfByte4.add(new DEROctetString(tempByte2[2]));
            seqOfByte4.add(new DEROctetString(tempByte2[3]));
            DERSequence dERSequence34 = new DERSequence(seqOfByte4);
            seqOfStat4.add(dERSequence34);
            seqOfByte4 = new ASN1EncodableVector();
            int[] tempInt2 = upperLeaf[i12].getStatInt();
            seqOfInt4.add(new ASN1Integer((long) tempInt2[0]));
            seqOfInt4.add(new ASN1Integer((long) tempInt2[1]));
            seqOfInt4.add(new ASN1Integer((long) tempInt2[2]));
            seqOfInt4.add(new ASN1Integer((long) tempInt2[3]));
            DERSequence dERSequence35 = new DERSequence(seqOfInt4);
            seqOfStat4.add(dERSequence35);
            seqOfInt4 = new ASN1EncodableVector();
            DERSequence dERSequence36 = new DERSequence(seqOfStat4);
            seqOfUpperLeaf.add(dERSequence36);
            seqOfStat4 = new ASN1EncodableVector();
        }
        DERSequence dERSequence37 = new DERSequence(seqOfUpperLeaf);
        result.add(dERSequence37);
        ASN1EncodableVector seqOfUpperTreehashLeaf = new ASN1EncodableVector();
        ASN1EncodableVector seqOfStat5 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfByte5 = new ASN1EncodableVector();
        ASN1EncodableVector seqOfInt5 = new ASN1EncodableVector();
        for (int i13 = 0; i13 < upperTreehashLeaf.length; i13++) {
            seqOfStat5.add(new DERSequence((ASN1Encodable) algorithms[0]));
            byte[][] tempByte3 = upperTreehashLeaf[i13].getStatByte();
            seqOfByte5.add(new DEROctetString(tempByte3[0]));
            seqOfByte5.add(new DEROctetString(tempByte3[1]));
            seqOfByte5.add(new DEROctetString(tempByte3[2]));
            seqOfByte5.add(new DEROctetString(tempByte3[3]));
            DERSequence dERSequence38 = new DERSequence(seqOfByte5);
            seqOfStat5.add(dERSequence38);
            seqOfByte5 = new ASN1EncodableVector();
            int[] tempInt3 = upperTreehashLeaf[i13].getStatInt();
            seqOfInt5.add(new ASN1Integer((long) tempInt3[0]));
            seqOfInt5.add(new ASN1Integer((long) tempInt3[1]));
            seqOfInt5.add(new ASN1Integer((long) tempInt3[2]));
            seqOfInt5.add(new ASN1Integer((long) tempInt3[3]));
            DERSequence dERSequence39 = new DERSequence(seqOfInt5);
            seqOfStat5.add(dERSequence39);
            seqOfInt5 = new ASN1EncodableVector();
            DERSequence dERSequence40 = new DERSequence(seqOfStat5);
            seqOfUpperTreehashLeaf.add(dERSequence40);
            seqOfStat5 = new ASN1EncodableVector();
        }
        DERSequence dERSequence41 = new DERSequence(seqOfUpperTreehashLeaf);
        result.add(dERSequence41);
        ASN1EncodableVector minTreehashPart = new ASN1EncodableVector();
        for (int i14 = 0; i14 < minTreehash.length; i14++) {
            minTreehashPart.add(new ASN1Integer((long) minTreehash[i14]));
        }
        DERSequence dERSequence42 = new DERSequence(minTreehashPart);
        result.add(dERSequence42);
        ASN1EncodableVector nextRootPart = new ASN1EncodableVector();
        for (byte[] dEROctetString10 : nextRoot) {
            nextRootPart.add(new DEROctetString(dEROctetString10));
        }
        DERSequence dERSequence43 = new DERSequence(nextRootPart);
        result.add(dERSequence43);
        ASN1EncodableVector seqOfnextNextRoot = new ASN1EncodableVector();
        ASN1EncodableVector seqOfnnRStats = new ASN1EncodableVector();
        new ASN1EncodableVector();
        ASN1EncodableVector seqOfnnRBytes = new ASN1EncodableVector();
        ASN1EncodableVector seqOfnnRInts = new ASN1EncodableVector();
        ASN1EncodableVector seqOfnnRTreehash = new ASN1EncodableVector();
        ASN1EncodableVector seqOfnnRRetain = new ASN1EncodableVector();
        for (int i15 = 0; i15 < nextNextRoot.length; i15++) {
            seqOfnnRStats.add(new DERSequence((ASN1Encodable) algorithms[0]));
            new ASN1EncodableVector();
            int heightOfTree = nextNextRoot[i15].getStatInt()[0];
            int tailLength3 = nextNextRoot[i15].getStatInt()[7];
            seqOfnnRBytes.add(new DEROctetString(nextNextRoot[i15].getStatByte()[0]));
            for (int j7 = 0; j7 < heightOfTree; j7++) {
                seqOfnnRBytes.add(new DEROctetString(nextNextRoot[i15].getStatByte()[j7 + 1]));
            }
            for (int j8 = 0; j8 < tailLength3; j8++) {
                seqOfnnRBytes.add(new DEROctetString(nextNextRoot[i15].getStatByte()[heightOfTree + 1 + j8]));
            }
            DERSequence dERSequence44 = new DERSequence(seqOfnnRBytes);
            seqOfnnRStats.add(dERSequence44);
            seqOfnnRBytes = new ASN1EncodableVector();
            seqOfnnRInts.add(new ASN1Integer((long) heightOfTree));
            seqOfnnRInts.add(new ASN1Integer((long) nextNextRoot[i15].getStatInt()[1]));
            seqOfnnRInts.add(new ASN1Integer((long) nextNextRoot[i15].getStatInt()[2]));
            seqOfnnRInts.add(new ASN1Integer((long) nextNextRoot[i15].getStatInt()[3]));
            seqOfnnRInts.add(new ASN1Integer((long) nextNextRoot[i15].getStatInt()[4]));
            seqOfnnRInts.add(new ASN1Integer((long) nextNextRoot[i15].getStatInt()[5]));
            seqOfnnRInts.add(new ASN1Integer((long) nextNextRoot[i15].getStatInt()[6]));
            seqOfnnRInts.add(new ASN1Integer((long) tailLength3));
            for (int j9 = 0; j9 < heightOfTree; j9++) {
                seqOfnnRInts.add(new ASN1Integer((long) nextNextRoot[i15].getStatInt()[j9 + 8]));
            }
            for (int j10 = 0; j10 < tailLength3; j10++) {
                seqOfnnRInts.add(new ASN1Integer((long) nextNextRoot[i15].getStatInt()[heightOfTree + 8 + j10]));
            }
            DERSequence dERSequence45 = new DERSequence(seqOfnnRInts);
            seqOfnnRStats.add(dERSequence45);
            seqOfnnRInts = new ASN1EncodableVector();
            ASN1EncodableVector seqOfStat6 = new ASN1EncodableVector();
            ASN1EncodableVector seqOfByte6 = new ASN1EncodableVector();
            ASN1EncodableVector seqOfInt6 = new ASN1EncodableVector();
            if (nextNextRoot[i15].getTreehash() != null) {
                for (int j11 = 0; j11 < nextNextRoot[i15].getTreehash().length; j11++) {
                    seqOfStat6.add(new DERSequence((ASN1Encodable) algorithms[0]));
                    int tailLength4 = nextNextRoot[i15].getTreehash()[j11].getStatInt()[1];
                    seqOfByte6.add(new DEROctetString(nextNextRoot[i15].getTreehash()[j11].getStatByte()[0]));
                    seqOfByte6.add(new DEROctetString(nextNextRoot[i15].getTreehash()[j11].getStatByte()[1]));
                    seqOfByte6.add(new DEROctetString(nextNextRoot[i15].getTreehash()[j11].getStatByte()[2]));
                    for (int k7 = 0; k7 < tailLength4; k7++) {
                        seqOfByte6.add(new DEROctetString(nextNextRoot[i15].getTreehash()[j11].getStatByte()[k7 + 3]));
                    }
                    DERSequence dERSequence46 = new DERSequence(seqOfByte6);
                    seqOfStat6.add(dERSequence46);
                    seqOfByte6 = new ASN1EncodableVector();
                    seqOfInt6.add(new ASN1Integer((long) nextNextRoot[i15].getTreehash()[j11].getStatInt()[0]));
                    seqOfInt6.add(new ASN1Integer((long) tailLength4));
                    seqOfInt6.add(new ASN1Integer((long) nextNextRoot[i15].getTreehash()[j11].getStatInt()[2]));
                    seqOfInt6.add(new ASN1Integer((long) nextNextRoot[i15].getTreehash()[j11].getStatInt()[3]));
                    seqOfInt6.add(new ASN1Integer((long) nextNextRoot[i15].getTreehash()[j11].getStatInt()[4]));
                    seqOfInt6.add(new ASN1Integer((long) nextNextRoot[i15].getTreehash()[j11].getStatInt()[5]));
                    for (int k8 = 0; k8 < tailLength4; k8++) {
                        seqOfInt6.add(new ASN1Integer((long) nextNextRoot[i15].getTreehash()[j11].getStatInt()[k8 + 6]));
                    }
                    DERSequence dERSequence47 = new DERSequence(seqOfInt6);
                    seqOfStat6.add(dERSequence47);
                    seqOfInt6 = new ASN1EncodableVector();
                    DERSequence dERSequence48 = new DERSequence(seqOfStat6);
                    seqOfnnRTreehash.add(dERSequence48);
                    seqOfStat6 = new ASN1EncodableVector();
                }
            }
            DERSequence dERSequence49 = new DERSequence(seqOfnnRTreehash);
            seqOfnnRStats.add(dERSequence49);
            seqOfnnRTreehash = new ASN1EncodableVector();
            ASN1EncodableVector currentRetainPart02 = new ASN1EncodableVector();
            if (nextNextRoot[i15].getRetain() != null) {
                for (int j12 = 0; j12 < nextNextRoot[i15].getRetain().length; j12++) {
                    for (int k9 = 0; k9 < nextNextRoot[i15].getRetain()[j12].size(); k9++) {
                        DEROctetString dEROctetString11 = new DEROctetString((byte[]) nextNextRoot[i15].getRetain()[j12].elementAt(k9));
                        currentRetainPart02.add(dEROctetString11);
                    }
                    DERSequence dERSequence50 = new DERSequence(currentRetainPart02);
                    seqOfnnRRetain.add(dERSequence50);
                    currentRetainPart02 = new ASN1EncodableVector();
                }
            }
            DERSequence dERSequence51 = new DERSequence(seqOfnnRRetain);
            seqOfnnRStats.add(dERSequence51);
            seqOfnnRRetain = new ASN1EncodableVector();
            DERSequence dERSequence52 = new DERSequence(seqOfnnRStats);
            seqOfnextNextRoot.add(dERSequence52);
            seqOfnnRStats = new ASN1EncodableVector();
        }
        DERSequence dERSequence53 = new DERSequence(seqOfnextNextRoot);
        result.add(dERSequence53);
        ASN1EncodableVector curRootSigPart = new ASN1EncodableVector();
        for (byte[] dEROctetString12 : currentRootSig) {
            curRootSigPart.add(new DEROctetString(dEROctetString12));
        }
        DERSequence dERSequence54 = new DERSequence(curRootSigPart);
        result.add(dERSequence54);
        ASN1EncodableVector seqOfnextRootSigs = new ASN1EncodableVector();
        ASN1EncodableVector seqOfnRSStats = new ASN1EncodableVector();
        new ASN1EncodableVector();
        ASN1EncodableVector seqOfnRSBytes = new ASN1EncodableVector();
        ASN1EncodableVector seqOfnRSInts = new ASN1EncodableVector();
        for (int i16 = 0; i16 < nextRootSig.length; i16++) {
            seqOfnRSStats.add(new DERSequence((ASN1Encodable) algorithms[0]));
            new ASN1EncodableVector();
            seqOfnRSBytes.add(new DEROctetString(nextRootSig[i16].getStatByte()[0]));
            seqOfnRSBytes.add(new DEROctetString(nextRootSig[i16].getStatByte()[1]));
            seqOfnRSBytes.add(new DEROctetString(nextRootSig[i16].getStatByte()[2]));
            seqOfnRSBytes.add(new DEROctetString(nextRootSig[i16].getStatByte()[3]));
            seqOfnRSBytes.add(new DEROctetString(nextRootSig[i16].getStatByte()[4]));
            DERSequence dERSequence55 = new DERSequence(seqOfnRSBytes);
            seqOfnRSStats.add(dERSequence55);
            seqOfnRSBytes = new ASN1EncodableVector();
            seqOfnRSInts.add(new ASN1Integer((long) nextRootSig[i16].getStatInt()[0]));
            seqOfnRSInts.add(new ASN1Integer((long) nextRootSig[i16].getStatInt()[1]));
            seqOfnRSInts.add(new ASN1Integer((long) nextRootSig[i16].getStatInt()[2]));
            seqOfnRSInts.add(new ASN1Integer((long) nextRootSig[i16].getStatInt()[3]));
            seqOfnRSInts.add(new ASN1Integer((long) nextRootSig[i16].getStatInt()[4]));
            seqOfnRSInts.add(new ASN1Integer((long) nextRootSig[i16].getStatInt()[5]));
            seqOfnRSInts.add(new ASN1Integer((long) nextRootSig[i16].getStatInt()[6]));
            seqOfnRSInts.add(new ASN1Integer((long) nextRootSig[i16].getStatInt()[7]));
            seqOfnRSInts.add(new ASN1Integer((long) nextRootSig[i16].getStatInt()[8]));
            DERSequence dERSequence56 = new DERSequence(seqOfnRSInts);
            seqOfnRSStats.add(dERSequence56);
            seqOfnRSInts = new ASN1EncodableVector();
            DERSequence dERSequence57 = new DERSequence(seqOfnRSStats);
            seqOfnextRootSigs.add(dERSequence57);
            seqOfnRSStats = new ASN1EncodableVector();
        }
        DERSequence dERSequence58 = new DERSequence(seqOfnextRootSigs);
        result.add(dERSequence58);
        ASN1EncodableVector parSetPart0 = new ASN1EncodableVector();
        ASN1EncodableVector parSetPart1 = new ASN1EncodableVector();
        ASN1EncodableVector parSetPart2 = new ASN1EncodableVector();
        ASN1EncodableVector parSetPart3 = new ASN1EncodableVector();
        for (int i17 = 0; i17 < gmssParameterset.getHeightOfTrees().length; i17++) {
            parSetPart1.add(new ASN1Integer((long) gmssParameterset.getHeightOfTrees()[i17]));
            parSetPart2.add(new ASN1Integer((long) gmssParameterset.getWinternitzParameter()[i17]));
            parSetPart3.add(new ASN1Integer((long) gmssParameterset.getK()[i17]));
        }
        parSetPart0.add(new ASN1Integer((long) gmssParameterset.getNumOfLayers()));
        DERSequence dERSequence59 = new DERSequence(parSetPart1);
        parSetPart0.add(dERSequence59);
        DERSequence dERSequence60 = new DERSequence(parSetPart2);
        parSetPart0.add(dERSequence60);
        DERSequence dERSequence61 = new DERSequence(parSetPart3);
        parSetPart0.add(dERSequence61);
        DERSequence dERSequence62 = new DERSequence(parSetPart0);
        result.add(dERSequence62);
        ASN1EncodableVector namesPart = new ASN1EncodableVector();
        for (int i18 = 0; i18 < algorithms.length; i18++) {
            namesPart.add(algorithms[i18]);
        }
        DERSequence dERSequence63 = new DERSequence(namesPart);
        result.add(dERSequence63);
        DERSequence dERSequence64 = new DERSequence(result);
        return dERSequence64;
    }

    private static int checkBigIntegerInIntRange(ASN1Encodable a) {
        BigInteger b = ((ASN1Integer) a).getValue();
        if (b.compareTo(BigInteger.valueOf(2147483647L)) <= 0 && b.compareTo(BigInteger.valueOf(-2147483648L)) >= 0) {
            return b.intValue();
        }
        throw new IllegalArgumentException("BigInteger not in Range: " + b.toString());
    }

    public ASN1Primitive toASN1Primitive() {
        return this.primitive;
    }
}
