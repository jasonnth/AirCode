package com.google.android.exoplayer.extractor.mp4;

import android.util.Pair;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.GaplessInfo;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.crypto.tls.CipherSuite;

final class AtomParsers {

    private static final class AvcCData {
        public final List<byte[]> initializationData;
        public final int nalUnitLengthFieldLength;
        public final float pixelWidthAspectRatio;

        public AvcCData(List<byte[]> initializationData2, int nalUnitLengthFieldLength2, float pixelWidthAspectRatio2) {
            this.initializationData = initializationData2;
            this.nalUnitLengthFieldLength = nalUnitLengthFieldLength2;
            this.pixelWidthAspectRatio = pixelWidthAspectRatio2;
        }
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray stsc2, ParsableByteArray chunkOffsets2, boolean chunkOffsetsAreLongs2) {
            boolean z = true;
            this.stsc = stsc2;
            this.chunkOffsets = chunkOffsets2;
            this.chunkOffsetsAreLongs = chunkOffsetsAreLongs2;
            chunkOffsets2.setPosition(12);
            this.length = chunkOffsets2.readUnsignedIntToInt();
            stsc2.setPosition(12);
            this.remainingSamplesPerChunkChanges = stsc2.readUnsignedIntToInt();
            if (stsc2.readInt() != 1) {
                z = false;
            }
            Assertions.checkState(z, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long readUnsignedInt;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                readUnsignedInt = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                readUnsignedInt = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = readUnsignedInt;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    private static final class StsdData {
        public MediaFormat mediaFormat;
        public int nalUnitLengthFieldLength = -1;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int numberOfEntries) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[numberOfEntries];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize = this.data.readUnsignedIntToInt();
        private final int sampleCount = this.data.readUnsignedIntToInt();

        public StszSampleSizeBox(LeafAtom stszAtom) {
            this.data = stszAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            return this.fixedSampleSize == 0 ? this.data.readUnsignedIntToInt() : this.fixedSampleSize;
        }

        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize = (this.data.readUnsignedIntToInt() & 255);
        private final int sampleCount = this.data.readUnsignedIntToInt();
        private int sampleIndex;

        public Stz2SampleSizeBox(LeafAtom stz2Atom) {
            this.data = stz2Atom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            if (this.fieldSize == 8) {
                return this.data.readUnsignedByte();
            }
            if (this.fieldSize == 16) {
                return this.data.readUnsignedShort();
            }
            int i = this.sampleIndex;
            this.sampleIndex = i + 1;
            if (i % 2 != 0) {
                return this.currentByte & 15;
            }
            this.currentByte = this.data.readUnsignedByte();
            return (this.currentByte & 240) >> 4;
        }

        public boolean isFixedSampleSize() {
            return false;
        }
    }

    private static final class TkhdData {
        /* access modifiers changed from: private */
        public final long duration;
        /* access modifiers changed from: private */

        /* renamed from: id */
        public final int f3151id;
        /* access modifiers changed from: private */
        public final int rotationDegrees;

        public TkhdData(int id, long duration2, int rotationDegrees2) {
            this.f3151id = id;
            this.duration = duration2;
            this.rotationDegrees = rotationDegrees2;
        }
    }

    public static Track parseTrak(ContainerAtom trak, LeafAtom mvhd, long duration, boolean isQuickTime) {
        long durationUs;
        ContainerAtom mdia = trak.getContainerAtomOfType(Atom.TYPE_mdia);
        int trackType = parseHdlr(mdia.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (trackType != Track.TYPE_soun && trackType != Track.TYPE_vide && trackType != Track.TYPE_text && trackType != Track.TYPE_sbtl && trackType != Track.TYPE_subt) {
            return null;
        }
        TkhdData tkhdData = parseTkhd(trak.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        if (duration == -1) {
            duration = tkhdData.duration;
        }
        long movieTimescale = parseMvhd(mvhd.data);
        if (duration == -1) {
            durationUs = -1;
        } else {
            durationUs = Util.scaleLargeTimestamp(duration, 1000000, movieTimescale);
        }
        ContainerAtom stbl = mdia.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> mdhdData = parseMdhd(mdia.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData stsdData = parseStsd(stbl.getLeafAtomOfType(Atom.TYPE_stsd).data, tkhdData.f3151id, durationUs, tkhdData.rotationDegrees, (String) mdhdData.second, isQuickTime);
        Pair<long[], long[]> edtsData = parseEdts(trak.getContainerAtomOfType(Atom.TYPE_edts));
        if (stsdData.mediaFormat == null) {
            return null;
        }
        return new Track(tkhdData.f3151id, trackType, ((Long) mdhdData.first).longValue(), movieTimescale, durationUs, stsdData.mediaFormat, stsdData.trackEncryptionBoxes, stsdData.nalUnitLengthFieldLength, (long[]) edtsData.first, (long[]) edtsData.second);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.exoplayer.extractor.mp4.AtomParsers$Stz2SampleSizeBox] */
    /* JADX WARNING: type inference failed for: r61v1, types: [com.google.android.exoplayer.extractor.mp4.AtomParsers$SampleSizeBox] */
    /* JADX WARNING: type inference failed for: r0v92, types: [com.google.android.exoplayer.extractor.mp4.AtomParsers$StszSampleSizeBox] */
    /* JADX WARNING: type inference failed for: r0v93, types: [com.google.android.exoplayer.extractor.mp4.AtomParsers$Stz2SampleSizeBox] */
    /* JADX WARNING: type inference failed for: r0v94, types: [com.google.android.exoplayer.extractor.mp4.AtomParsers$StszSampleSizeBox] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v93, types: [com.google.android.exoplayer.extractor.mp4.AtomParsers$Stz2SampleSizeBox]
      assigns: [com.google.android.exoplayer.extractor.mp4.AtomParsers$Stz2SampleSizeBox, com.google.android.exoplayer.extractor.mp4.AtomParsers$StszSampleSizeBox]
      uses: [com.google.android.exoplayer.extractor.mp4.AtomParsers$Stz2SampleSizeBox, com.google.android.exoplayer.extractor.mp4.AtomParsers$SampleSizeBox, com.google.android.exoplayer.extractor.mp4.AtomParsers$StszSampleSizeBox]
      mth insns count: 428
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer.extractor.mp4.TrackSampleTable parseStbl(com.google.android.exoplayer.extractor.mp4.Track r76, com.google.android.exoplayer.extractor.mp4.Atom.ContainerAtom r77) throws com.google.android.exoplayer.ParserException {
        /*
            int r4 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_stsz
            r0 = r77
            com.google.android.exoplayer.extractor.mp4.Atom$LeafAtom r66 = r0.getLeafAtomOfType(r4)
            if (r66 == 0) goto L_0x002c
            com.google.android.exoplayer.extractor.mp4.AtomParsers$StszSampleSizeBox r61 = new com.google.android.exoplayer.extractor.mp4.AtomParsers$StszSampleSizeBox
            r0 = r61
            r1 = r66
            r0.<init>(r1)
        L_0x0013:
            int r59 = r61.getSampleCount()
            if (r59 != 0) goto L_0x0049
            com.google.android.exoplayer.extractor.mp4.TrackSampleTable r4 = new com.google.android.exoplayer.extractor.mp4.TrackSampleTable
            r12 = 0
            long[] r5 = new long[r12]
            r12 = 0
            int[] r6 = new int[r12]
            r7 = 0
            r12 = 0
            long[] r8 = new long[r12]
            r12 = 0
            int[] r9 = new int[r12]
            r4.<init>(r5, r6, r7, r8, r9)
        L_0x002b:
            return r4
        L_0x002c:
            int r4 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_stz2
            r0 = r77
            com.google.android.exoplayer.extractor.mp4.Atom$LeafAtom r68 = r0.getLeafAtomOfType(r4)
            if (r68 != 0) goto L_0x003f
            com.google.android.exoplayer.ParserException r4 = new com.google.android.exoplayer.ParserException
            java.lang.String r12 = "Track has no sample table size information"
            r4.<init>(r12)
            throw r4
        L_0x003f:
            com.google.android.exoplayer.extractor.mp4.AtomParsers$Stz2SampleSizeBox r61 = new com.google.android.exoplayer.extractor.mp4.AtomParsers$Stz2SampleSizeBox
            r0 = r61
            r1 = r68
            r0.<init>(r1)
            goto L_0x0013
        L_0x0049:
            r20 = 0
            int r4 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_stco
            r0 = r77
            com.google.android.exoplayer.extractor.mp4.Atom$LeafAtom r21 = r0.getLeafAtomOfType(r4)
            if (r21 != 0) goto L_0x005f
            r20 = 1
            int r4 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_co64
            r0 = r77
            com.google.android.exoplayer.extractor.mp4.Atom$LeafAtom r21 = r0.getLeafAtomOfType(r4)
        L_0x005f:
            r0 = r21
            com.google.android.exoplayer.util.ParsableByteArray r0 = r0.data
            r19 = r0
            int r4 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_stsc
            r0 = r77
            com.google.android.exoplayer.extractor.mp4.Atom$LeafAtom r4 = r0.getLeafAtomOfType(r4)
            com.google.android.exoplayer.util.ParsableByteArray r0 = r4.data
            r63 = r0
            int r4 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_stts
            r0 = r77
            com.google.android.exoplayer.extractor.mp4.Atom$LeafAtom r4 = r0.getLeafAtomOfType(r4)
            com.google.android.exoplayer.util.ParsableByteArray r0 = r4.data
            r67 = r0
            int r4 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_stss
            r0 = r77
            com.google.android.exoplayer.extractor.mp4.Atom$LeafAtom r65 = r0.getLeafAtomOfType(r4)
            if (r65 == 0) goto L_0x013f
            r0 = r65
            com.google.android.exoplayer.util.ParsableByteArray r0 = r0.data
            r64 = r0
        L_0x008d:
            int r4 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_ctts
            r0 = r77
            com.google.android.exoplayer.extractor.mp4.Atom$LeafAtom r27 = r0.getLeafAtomOfType(r4)
            if (r27 == 0) goto L_0x0143
            r0 = r27
            com.google.android.exoplayer.util.ParsableByteArray r0 = r0.data
            r26 = r0
        L_0x009d:
            com.google.android.exoplayer.extractor.mp4.AtomParsers$ChunkIterator r18 = new com.google.android.exoplayer.extractor.mp4.AtomParsers$ChunkIterator
            r0 = r18
            r1 = r63
            r2 = r19
            r3 = r20
            r0.<init>(r1, r2, r3)
            r4 = 12
            r0 = r67
            r0.setPosition(r4)
            int r4 = r67.readUnsignedIntToInt()
            int r57 = r4 + -1
            int r53 = r67.readUnsignedIntToInt()
            int r69 = r67.readUnsignedIntToInt()
            r54 = 0
            r58 = 0
            r72 = 0
            if (r26 == 0) goto L_0x00d2
            r4 = 12
            r0 = r26
            r0.setPosition(r4)
            int r58 = r26.readUnsignedIntToInt()
        L_0x00d2:
            r45 = -1
            r56 = 0
            if (r64 == 0) goto L_0x00eb
            r4 = 12
            r0 = r64
            r0.setPosition(r4)
            int r56 = r64.readUnsignedIntToInt()
            if (r56 <= 0) goto L_0x0147
            int r4 = r64.readUnsignedIntToInt()
            int r45 = r4 + -1
        L_0x00eb:
            boolean r4 = r61.isFixedSampleSize()
            if (r4 == 0) goto L_0x014a
            java.lang.String r4 = "audio/raw"
            r0 = r76
            com.google.android.exoplayer.MediaFormat r12 = r0.mediaFormat
            java.lang.String r12 = r12.mimeType
            boolean r4 = r4.equals(r12)
            if (r4 == 0) goto L_0x014a
            if (r57 != 0) goto L_0x014a
            if (r58 != 0) goto L_0x014a
            if (r56 != 0) goto L_0x014a
            r40 = 1
        L_0x0108:
            r7 = 0
            if (r40 != 0) goto L_0x0202
            r0 = r59
            long[] r5 = new long[r0]
            r0 = r59
            int[] r6 = new int[r0]
            r0 = r59
            long[] r8 = new long[r0]
            r0 = r59
            int[] r9 = new int[r0]
            r74 = 0
            r46 = 0
            r55 = 0
            r39 = 0
        L_0x0123:
            r0 = r39
            r1 = r59
            if (r0 >= r1) goto L_0x01af
        L_0x0129:
            if (r55 != 0) goto L_0x014d
            boolean r4 = r18.moveNext()
            com.google.android.exoplayer.util.Assertions.checkState(r4)
            r0 = r18
            long r0 = r0.offset
            r46 = r0
            r0 = r18
            int r0 = r0.numSamples
            r55 = r0
            goto L_0x0129
        L_0x013f:
            r64 = 0
            goto L_0x008d
        L_0x0143:
            r26 = 0
            goto L_0x009d
        L_0x0147:
            r64 = 0
            goto L_0x00eb
        L_0x014a:
            r40 = 0
            goto L_0x0108
        L_0x014d:
            if (r26 == 0) goto L_0x0160
        L_0x014f:
            if (r54 != 0) goto L_0x015e
            if (r58 <= 0) goto L_0x015e
            int r54 = r26.readUnsignedIntToInt()
            int r72 = r26.readInt()
            int r58 = r58 + -1
            goto L_0x014f
        L_0x015e:
            int r54 = r54 + -1
        L_0x0160:
            r5[r39] = r46
            int r4 = r61.readNextSampleSize()
            r6[r39] = r4
            r4 = r6[r39]
            if (r4 <= r7) goto L_0x016e
            r7 = r6[r39]
        L_0x016e:
            r0 = r72
            long r12 = (long) r0
            long r12 = r12 + r74
            r8[r39] = r12
            if (r64 != 0) goto L_0x01ad
            r4 = 1
        L_0x0178:
            r9[r39] = r4
            r0 = r39
            r1 = r45
            if (r0 != r1) goto L_0x018d
            r4 = 1
            r9[r39] = r4
            int r56 = r56 + -1
            if (r56 <= 0) goto L_0x018d
            int r4 = r64.readUnsignedIntToInt()
            int r45 = r4 + -1
        L_0x018d:
            r0 = r69
            long r12 = (long) r0
            long r74 = r74 + r12
            int r53 = r53 + -1
            if (r53 != 0) goto L_0x01a2
            if (r57 <= 0) goto L_0x01a2
            int r53 = r67.readUnsignedIntToInt()
            int r69 = r67.readUnsignedIntToInt()
            int r57 = r57 + -1
        L_0x01a2:
            r4 = r6[r39]
            long r12 = (long) r4
            long r46 = r46 + r12
            int r55 = r55 + -1
            int r39 = r39 + 1
            goto L_0x0123
        L_0x01ad:
            r4 = 0
            goto L_0x0178
        L_0x01af:
            if (r54 != 0) goto L_0x01c7
            r4 = 1
        L_0x01b2:
            com.google.android.exoplayer.util.Assertions.checkArgument(r4)
        L_0x01b5:
            if (r58 <= 0) goto L_0x01cb
            int r4 = r26.readUnsignedIntToInt()
            if (r4 != 0) goto L_0x01c9
            r4 = 1
        L_0x01be:
            com.google.android.exoplayer.util.Assertions.checkArgument(r4)
            r26.readInt()
            int r58 = r58 + -1
            goto L_0x01b5
        L_0x01c7:
            r4 = 0
            goto L_0x01b2
        L_0x01c9:
            r4 = 0
            goto L_0x01be
        L_0x01cb:
            if (r56 != 0) goto L_0x01fa
            r4 = 1
        L_0x01ce:
            com.google.android.exoplayer.util.Assertions.checkArgument(r4)
            if (r53 != 0) goto L_0x01fc
            r4 = 1
        L_0x01d4:
            com.google.android.exoplayer.util.Assertions.checkArgument(r4)
            if (r55 != 0) goto L_0x01fe
            r4 = 1
        L_0x01da:
            com.google.android.exoplayer.util.Assertions.checkArgument(r4)
            if (r57 != 0) goto L_0x0200
            r4 = 1
        L_0x01e0:
            com.google.android.exoplayer.util.Assertions.checkArgument(r4)
        L_0x01e3:
            r0 = r76
            long[] r4 = r0.editListDurations
            if (r4 != 0) goto L_0x0253
            r12 = 1000000(0xf4240, double:4.940656E-318)
            r0 = r76
            long r14 = r0.timescale
            com.google.android.exoplayer.util.Util.scaleLargeTimestampsInPlace(r8, r12, r14)
            com.google.android.exoplayer.extractor.mp4.TrackSampleTable r4 = new com.google.android.exoplayer.extractor.mp4.TrackSampleTable
            r4.<init>(r5, r6, r7, r8, r9)
            goto L_0x002b
        L_0x01fa:
            r4 = 0
            goto L_0x01ce
        L_0x01fc:
            r4 = 0
            goto L_0x01d4
        L_0x01fe:
            r4 = 0
            goto L_0x01da
        L_0x0200:
            r4 = 0
            goto L_0x01e0
        L_0x0202:
            r0 = r18
            int r4 = r0.length
            long[] r0 = new long[r4]
            r22 = r0
            r0 = r18
            int r4 = r0.length
            int[] r0 = new int[r4]
            r23 = r0
        L_0x0212:
            boolean r4 = r18.moveNext()
            if (r4 == 0) goto L_0x022d
            r0 = r18
            int r4 = r0.index
            r0 = r18
            long r12 = r0.offset
            r22[r4] = r12
            r0 = r18
            int r4 = r0.index
            r0 = r18
            int r12 = r0.numSamples
            r23[r4] = r12
            goto L_0x0212
        L_0x022d:
            int r35 = r61.readNextSampleSize()
            r0 = r69
            long r12 = (long) r0
            r0 = r35
            r1 = r22
            r2 = r23
            com.google.android.exoplayer.extractor.mp4.FixedSampleSizeRechunker$Results r52 = com.google.android.exoplayer.extractor.mp4.FixedSampleSizeRechunker.rechunk(r0, r1, r2, r12)
            r0 = r52
            long[] r5 = r0.offsets
            r0 = r52
            int[] r6 = r0.sizes
            r0 = r52
            int r7 = r0.maximumSize
            r0 = r52
            long[] r8 = r0.timestamps
            r0 = r52
            int[] r9 = r0.flags
            goto L_0x01e3
        L_0x0253:
            r0 = r76
            long[] r4 = r0.editListDurations
            int r4 = r4.length
            r12 = 1
            if (r4 != r12) goto L_0x0291
            r0 = r76
            long[] r4 = r0.editListDurations
            r12 = 0
            r12 = r4[r12]
            r14 = 0
            int r4 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r4 != 0) goto L_0x0291
            r39 = 0
        L_0x026a:
            int r4 = r8.length
            r0 = r39
            if (r0 >= r4) goto L_0x028a
            r12 = r8[r39]
            r0 = r76
            long[] r4 = r0.editListMediaTimes
            r14 = 0
            r14 = r4[r14]
            long r10 = r12 - r14
            r12 = 1000000(0xf4240, double:4.940656E-318)
            r0 = r76
            long r14 = r0.timescale
            long r12 = com.google.android.exoplayer.util.Util.scaleLargeTimestamp(r10, r12, r14)
            r8[r39] = r12
            int r39 = r39 + 1
            goto L_0x026a
        L_0x028a:
            com.google.android.exoplayer.extractor.mp4.TrackSampleTable r4 = new com.google.android.exoplayer.extractor.mp4.TrackSampleTable
            r4.<init>(r5, r6, r7, r8, r9)
            goto L_0x002b
        L_0x0291:
            r31 = 0
            r44 = 0
            r24 = 0
            r39 = 0
        L_0x0299:
            r0 = r76
            long[] r4 = r0.editListDurations
            int r4 = r4.length
            r0 = r39
            if (r0 >= r4) goto L_0x02e4
            r0 = r76
            long[] r4 = r0.editListMediaTimes
            r42 = r4[r39]
            r12 = -1
            int r4 = (r42 > r12 ? 1 : (r42 == r12 ? 0 : -1))
            if (r4 == 0) goto L_0x02df
            r0 = r76
            long[] r4 = r0.editListDurations
            r10 = r4[r39]
            r0 = r76
            long r12 = r0.timescale
            r0 = r76
            long r14 = r0.movieTimescale
            long r10 = com.google.android.exoplayer.util.Util.scaleLargeTimestamp(r10, r12, r14)
            r4 = 1
            r12 = 1
            r0 = r42
            int r62 = com.google.android.exoplayer.util.Util.binarySearchCeil(r8, r0, r4, r12)
            long r12 = r42 + r10
            r4 = 1
            r14 = 0
            int r34 = com.google.android.exoplayer.util.Util.binarySearchCeil(r8, r12, r4, r14)
            int r4 = r34 - r62
            int r31 = r31 + r4
            r0 = r44
            r1 = r62
            if (r0 == r1) goto L_0x02e2
            r4 = 1
        L_0x02db:
            r24 = r24 | r4
            r44 = r34
        L_0x02df:
            int r39 = r39 + 1
            goto L_0x0299
        L_0x02e2:
            r4 = 0
            goto L_0x02db
        L_0x02e4:
            r0 = r31
            r1 = r59
            if (r0 == r1) goto L_0x03ae
            r4 = 1
        L_0x02eb:
            r24 = r24 | r4
            if (r24 == 0) goto L_0x03b1
            r0 = r31
            long[] r0 = new long[r0]
            r30 = r0
        L_0x02f5:
            if (r24 == 0) goto L_0x03b5
            r0 = r31
            int[] r0 = new int[r0]
            r32 = r0
        L_0x02fd:
            if (r24 == 0) goto L_0x03b9
            r29 = 0
        L_0x0301:
            if (r24 == 0) goto L_0x03bd
            r0 = r31
            int[] r0 = new int[r0]
            r28 = r0
        L_0x0309:
            r0 = r31
            long[] r0 = new long[r0]
            r33 = r0
            r48 = 0
            r60 = 0
            r39 = 0
        L_0x0315:
            r0 = r76
            long[] r4 = r0.editListDurations
            int r4 = r4.length
            r0 = r39
            if (r0 >= r4) goto L_0x03c7
            r0 = r76
            long[] r4 = r0.editListMediaTimes
            r42 = r4[r39]
            r0 = r76
            long[] r4 = r0.editListDurations
            r10 = r4[r39]
            r12 = -1
            int r4 = (r42 > r12 ? 1 : (r42 == r12 ? 0 : -1))
            if (r4 == 0) goto L_0x03c1
            r0 = r76
            long r12 = r0.timescale
            r0 = r76
            long r14 = r0.movieTimescale
            long r12 = com.google.android.exoplayer.util.Util.scaleLargeTimestamp(r10, r12, r14)
            long r36 = r42 + r12
            r4 = 1
            r12 = 1
            r0 = r42
            int r62 = com.google.android.exoplayer.util.Util.binarySearchCeil(r8, r0, r4, r12)
            r4 = 1
            r12 = 0
            r0 = r36
            int r34 = com.google.android.exoplayer.util.Util.binarySearchCeil(r8, r0, r4, r12)
            if (r24 == 0) goto L_0x0373
            int r25 = r34 - r62
            r0 = r62
            r1 = r30
            r2 = r60
            r3 = r25
            java.lang.System.arraycopy(r5, r0, r1, r2, r3)
            r0 = r62
            r1 = r32
            r2 = r60
            r3 = r25
            java.lang.System.arraycopy(r6, r0, r1, r2, r3)
            r0 = r62
            r1 = r28
            r2 = r60
            r3 = r25
            java.lang.System.arraycopy(r9, r0, r1, r2, r3)
        L_0x0373:
            r41 = r62
        L_0x0375:
            r0 = r41
            r1 = r34
            if (r0 >= r1) goto L_0x03c1
            r14 = 1000000(0xf4240, double:4.940656E-318)
            r0 = r76
            long r0 = r0.movieTimescale
            r16 = r0
            r12 = r48
            long r50 = com.google.android.exoplayer.util.Util.scaleLargeTimestamp(r12, r14, r16)
            r12 = r8[r41]
            long r12 = r12 - r42
            r14 = 1000000(0xf4240, double:4.940656E-318)
            r0 = r76
            long r0 = r0.timescale
            r16 = r0
            long r70 = com.google.android.exoplayer.util.Util.scaleLargeTimestamp(r12, r14, r16)
            long r12 = r50 + r70
            r33[r60] = r12
            if (r24 == 0) goto L_0x03a9
            r4 = r32[r60]
            r0 = r29
            if (r4 <= r0) goto L_0x03a9
            r29 = r6[r41]
        L_0x03a9:
            int r60 = r60 + 1
            int r41 = r41 + 1
            goto L_0x0375
        L_0x03ae:
            r4 = 0
            goto L_0x02eb
        L_0x03b1:
            r30 = r5
            goto L_0x02f5
        L_0x03b5:
            r32 = r6
            goto L_0x02fd
        L_0x03b9:
            r29 = r7
            goto L_0x0301
        L_0x03bd:
            r28 = r9
            goto L_0x0309
        L_0x03c1:
            long r48 = r48 + r10
            int r39 = r39 + 1
            goto L_0x0315
        L_0x03c7:
            r38 = 0
            r39 = 0
        L_0x03cb:
            r0 = r28
            int r4 = r0.length
            r0 = r39
            if (r0 >= r4) goto L_0x03e2
            if (r38 != 0) goto L_0x03e2
            r4 = r28[r39]
            r4 = r4 & 1
            if (r4 == 0) goto L_0x03e0
            r4 = 1
        L_0x03db:
            r38 = r38 | r4
            int r39 = r39 + 1
            goto L_0x03cb
        L_0x03e0:
            r4 = 0
            goto L_0x03db
        L_0x03e2:
            if (r38 != 0) goto L_0x03ed
            com.google.android.exoplayer.ParserException r4 = new com.google.android.exoplayer.ParserException
            java.lang.String r12 = "The edited sample sequence does not contain a sync sample."
            r4.<init>(r12)
            throw r4
        L_0x03ed:
            com.google.android.exoplayer.extractor.mp4.TrackSampleTable r12 = new com.google.android.exoplayer.extractor.mp4.TrackSampleTable
            r13 = r30
            r14 = r32
            r15 = r29
            r16 = r33
            r17 = r28
            r12.<init>(r13, r14, r15, r16, r17)
            r4 = r12
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.extractor.mp4.AtomParsers.parseStbl(com.google.android.exoplayer.extractor.mp4.Track, com.google.android.exoplayer.extractor.mp4.Atom$ContainerAtom):com.google.android.exoplayer.extractor.mp4.TrackSampleTable");
    }

    public static GaplessInfo parseUdta(LeafAtom udtaAtom, boolean isQuickTime) {
        if (isQuickTime) {
            return null;
        }
        ParsableByteArray udtaData = udtaAtom.data;
        udtaData.setPosition(8);
        while (udtaData.bytesLeft() >= 8) {
            int atomSize = udtaData.readInt();
            if (udtaData.readInt() == Atom.TYPE_meta) {
                udtaData.setPosition(udtaData.getPosition() - 8);
                udtaData.setLimit(udtaData.getPosition() + atomSize);
                return parseMetaAtom(udtaData);
            }
            udtaData.skipBytes(atomSize - 8);
        }
        return null;
    }

    private static GaplessInfo parseMetaAtom(ParsableByteArray data) {
        data.skipBytes(12);
        ParsableByteArray ilst = new ParsableByteArray();
        while (data.bytesLeft() >= 8) {
            int payloadSize = data.readInt() - 8;
            if (data.readInt() == Atom.TYPE_ilst) {
                ilst.reset(data.data, data.getPosition() + payloadSize);
                ilst.setPosition(data.getPosition());
                GaplessInfo gaplessInfo = parseIlst(ilst);
                if (gaplessInfo != null) {
                    return gaplessInfo;
                }
            }
            data.skipBytes(payloadSize);
        }
        return null;
    }

    private static GaplessInfo parseIlst(ParsableByteArray ilst) {
        while (ilst.bytesLeft() > 0) {
            int endPosition = ilst.getPosition() + ilst.readInt();
            if (ilst.readInt() == Atom.TYPE_DASHES) {
                String lastCommentMean = null;
                String lastCommentName = null;
                String lastCommentData = null;
                while (ilst.getPosition() < endPosition) {
                    int length = ilst.readInt() - 12;
                    int key = ilst.readInt();
                    ilst.skipBytes(4);
                    if (key == Atom.TYPE_mean) {
                        lastCommentMean = ilst.readString(length);
                    } else if (key == Atom.TYPE_name) {
                        lastCommentName = ilst.readString(length);
                    } else if (key == Atom.TYPE_data) {
                        ilst.skipBytes(4);
                        lastCommentData = ilst.readString(length - 4);
                    } else {
                        ilst.skipBytes(length);
                    }
                }
                if (!(lastCommentName == null || lastCommentData == null || !"com.apple.iTunes".equals(lastCommentMean))) {
                    return GaplessInfo.createFromComment(lastCommentName, lastCommentData);
                }
            } else {
                ilst.setPosition(endPosition);
            }
        }
        return null;
    }

    private static long parseMvhd(ParsableByteArray mvhd) {
        int i = 8;
        mvhd.setPosition(8);
        if (Atom.parseFullAtomVersion(mvhd.readInt()) != 0) {
            i = 16;
        }
        mvhd.skipBytes(i);
        return mvhd.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray tkhd) {
        long duration;
        int rotationDegrees;
        tkhd.setPosition(8);
        int version = Atom.parseFullAtomVersion(tkhd.readInt());
        tkhd.skipBytes(version == 0 ? 8 : 16);
        int trackId = tkhd.readInt();
        tkhd.skipBytes(4);
        boolean durationUnknown = true;
        int durationPosition = tkhd.getPosition();
        int durationByteCount = version == 0 ? 4 : 8;
        int i = 0;
        while (true) {
            if (i >= durationByteCount) {
                break;
            } else if (tkhd.data[durationPosition + i] != -1) {
                durationUnknown = false;
                break;
            } else {
                i++;
            }
        }
        if (durationUnknown) {
            tkhd.skipBytes(durationByteCount);
            duration = -1;
        } else {
            duration = version == 0 ? tkhd.readUnsignedInt() : tkhd.readUnsignedLongToLong();
            if (duration == 0) {
                duration = -1;
            }
        }
        tkhd.skipBytes(16);
        int a00 = tkhd.readInt();
        int a01 = tkhd.readInt();
        tkhd.skipBytes(4);
        int a10 = tkhd.readInt();
        int a11 = tkhd.readInt();
        if (a00 == 0 && a01 == 65536 && a10 == (-65536) && a11 == 0) {
            rotationDegrees = 90;
        } else if (a00 == 0 && a01 == (-65536) && a10 == 65536 && a11 == 0) {
            rotationDegrees = MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS;
        } else if (a00 == (-65536) && a01 == 0 && a10 == 0 && a11 == (-65536)) {
            rotationDegrees = 180;
        } else {
            rotationDegrees = 0;
        }
        TkhdData tkhdData = new TkhdData(trackId, duration, rotationDegrees);
        return tkhdData;
    }

    private static int parseHdlr(ParsableByteArray hdlr) {
        hdlr.setPosition(16);
        return hdlr.readInt();
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray mdhd) {
        int i = 8;
        mdhd.setPosition(8);
        int version = Atom.parseFullAtomVersion(mdhd.readInt());
        mdhd.skipBytes(version == 0 ? 8 : 16);
        long timescale = mdhd.readUnsignedInt();
        if (version == 0) {
            i = 4;
        }
        mdhd.skipBytes(i);
        int languageCode = mdhd.readUnsignedShort();
        return Pair.create(Long.valueOf(timescale), "" + ((char) (((languageCode >> 10) & 31) + 96)) + ((char) (((languageCode >> 5) & 31) + 96)) + ((char) ((languageCode & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray stsd, int trackId, long durationUs, int rotationDegrees, String language, boolean isQuickTime) {
        stsd.setPosition(12);
        int numberOfEntries = stsd.readInt();
        StsdData out = new StsdData(numberOfEntries);
        for (int i = 0; i < numberOfEntries; i++) {
            int childStartPosition = stsd.getPosition();
            int childAtomSize = stsd.readInt();
            Assertions.checkArgument(childAtomSize > 0, "childAtomSize should be positive");
            int childAtomType = stsd.readInt();
            if (childAtomType == Atom.TYPE_avc1 || childAtomType == Atom.TYPE_avc3 || childAtomType == Atom.TYPE_encv || childAtomType == Atom.TYPE_mp4v || childAtomType == Atom.TYPE_hvc1 || childAtomType == Atom.TYPE_hev1 || childAtomType == Atom.TYPE_s263 || childAtomType == Atom.TYPE_vp08 || childAtomType == Atom.TYPE_vp09) {
                parseVideoSampleEntry(stsd, childAtomType, childStartPosition, childAtomSize, trackId, durationUs, rotationDegrees, out, i);
            } else if (childAtomType == Atom.TYPE_mp4a || childAtomType == Atom.TYPE_enca || childAtomType == Atom.TYPE_ac_3 || childAtomType == Atom.TYPE_ec_3 || childAtomType == Atom.TYPE_dtsc || childAtomType == Atom.TYPE_dtse || childAtomType == Atom.TYPE_dtsh || childAtomType == Atom.TYPE_dtsl || childAtomType == Atom.TYPE_samr || childAtomType == Atom.TYPE_sawb || childAtomType == Atom.TYPE_lpcm || childAtomType == Atom.TYPE_sowt) {
                parseAudioSampleEntry(stsd, childAtomType, childStartPosition, childAtomSize, trackId, durationUs, language, isQuickTime, out, i);
            } else if (childAtomType == Atom.TYPE_TTML) {
                out.mediaFormat = MediaFormat.createTextFormat(Integer.toString(trackId), "application/ttml+xml", -1, durationUs, language);
            } else if (childAtomType == Atom.TYPE_tx3g) {
                out.mediaFormat = MediaFormat.createTextFormat(Integer.toString(trackId), "application/x-quicktime-tx3g", -1, durationUs, language);
            } else if (childAtomType == Atom.TYPE_wvtt) {
                out.mediaFormat = MediaFormat.createTextFormat(Integer.toString(trackId), "application/x-mp4vtt", -1, durationUs, language);
            } else if (childAtomType == Atom.TYPE_stpp) {
                out.mediaFormat = MediaFormat.createTextFormat(Integer.toString(trackId), "application/ttml+xml", -1, durationUs, language, 0);
            }
            stsd.setPosition(childStartPosition + childAtomSize);
        }
        return out;
    }

    /* JADX WARNING: Removed duplicated region for block: B:65:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseVideoSampleEntry(com.google.android.exoplayer.util.ParsableByteArray r29, int r30, int r31, int r32, int r33, long r34, int r36, com.google.android.exoplayer.extractor.mp4.AtomParsers.StsdData r37, int r38) {
        /*
            int r6 = r31 + 8
            r0 = r29
            r0.setPosition(r6)
            r6 = 24
            r0 = r29
            r0.skipBytes(r6)
            int r12 = r29.readUnsignedShort()
            int r13 = r29.readUnsignedShort()
            r27 = 0
            r16 = 1065353216(0x3f800000, float:1.0)
            r6 = 50
            r0 = r29
            r0.skipBytes(r6)
            int r22 = r29.getPosition()
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_encv
            r0 = r30
            if (r0 != r6) goto L_0x003f
            r0 = r29
            r1 = r31
            r2 = r32
            r3 = r37
            r4 = r38
            parseSampleEntryEncryptionData(r0, r1, r2, r3, r4)
            r0 = r29
            r1 = r22
            r0.setPosition(r1)
        L_0x003f:
            r14 = 0
            r7 = 0
            r17 = 0
            r18 = -1
        L_0x0045:
            int r6 = r22 - r31
            r0 = r32
            if (r6 >= r0) goto L_0x0066
            r0 = r29
            r1 = r22
            r0.setPosition(r1)
            int r23 = r29.getPosition()
            int r20 = r29.readInt()
            if (r20 != 0) goto L_0x0069
            int r6 = r29.getPosition()
            int r6 = r6 - r31
            r0 = r32
            if (r6 != r0) goto L_0x0069
        L_0x0066:
            if (r7 != 0) goto L_0x0174
        L_0x0068:
            return
        L_0x0069:
            if (r20 <= 0) goto L_0x00a4
            r6 = 1
        L_0x006c:
            java.lang.String r8 = "childAtomSize should be positive"
            com.google.android.exoplayer.util.Assertions.checkArgument(r6, r8)
            int r21 = r29.readInt()
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_avcC
            r0 = r21
            if (r0 != r6) goto L_0x00a8
            if (r7 != 0) goto L_0x00a6
            r6 = 1
        L_0x007f:
            com.google.android.exoplayer.util.Assertions.checkState(r6)
            java.lang.String r7 = "video/avc"
            r0 = r29
            r1 = r23
            com.google.android.exoplayer.extractor.mp4.AtomParsers$AvcCData r19 = parseAvcCFromParent(r0, r1)
            r0 = r19
            java.util.List<byte[]> r14 = r0.initializationData
            r0 = r19
            int r6 = r0.nalUnitLengthFieldLength
            r0 = r37
            r0.nalUnitLengthFieldLength = r6
            if (r27 != 0) goto L_0x00a1
            r0 = r19
            float r0 = r0.pixelWidthAspectRatio
            r16 = r0
        L_0x00a1:
            int r22 = r22 + r20
            goto L_0x0045
        L_0x00a4:
            r6 = 0
            goto L_0x006c
        L_0x00a6:
            r6 = 0
            goto L_0x007f
        L_0x00a8:
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_hvcC
            r0 = r21
            if (r0 != r6) goto L_0x00d6
            if (r7 != 0) goto L_0x00d4
            r6 = 1
        L_0x00b1:
            com.google.android.exoplayer.util.Assertions.checkState(r6)
            java.lang.String r7 = "video/hevc"
            r0 = r29
            r1 = r23
            android.util.Pair r24 = parseHvcCFromParent(r0, r1)
            r0 = r24
            java.lang.Object r14 = r0.first
            java.util.List r14 = (java.util.List) r14
            r0 = r24
            java.lang.Object r6 = r0.second
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r0 = r37
            r0.nalUnitLengthFieldLength = r6
            goto L_0x00a1
        L_0x00d4:
            r6 = 0
            goto L_0x00b1
        L_0x00d6:
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_d263
            r0 = r21
            if (r0 != r6) goto L_0x00e8
            if (r7 != 0) goto L_0x00e6
            r6 = 1
        L_0x00df:
            com.google.android.exoplayer.util.Assertions.checkState(r6)
            java.lang.String r7 = "video/3gpp"
            goto L_0x00a1
        L_0x00e6:
            r6 = 0
            goto L_0x00df
        L_0x00e8:
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_esds
            r0 = r21
            if (r0 != r6) goto L_0x010d
            if (r7 != 0) goto L_0x010b
            r6 = 1
        L_0x00f1:
            com.google.android.exoplayer.util.Assertions.checkState(r6)
            r0 = r29
            r1 = r23
            android.util.Pair r26 = parseEsdsFromParent(r0, r1)
            r0 = r26
            java.lang.Object r7 = r0.first
            java.lang.String r7 = (java.lang.String) r7
            r0 = r26
            java.lang.Object r6 = r0.second
            java.util.List r14 = java.util.Collections.singletonList(r6)
            goto L_0x00a1
        L_0x010b:
            r6 = 0
            goto L_0x00f1
        L_0x010d:
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_pasp
            r0 = r21
            if (r0 != r6) goto L_0x011e
            r0 = r29
            r1 = r23
            float r16 = parsePaspFromParent(r0, r1)
            r27 = 1
            goto L_0x00a1
        L_0x011e:
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_vpcC
            r0 = r21
            if (r0 != r6) goto L_0x013b
            if (r7 != 0) goto L_0x0135
            r6 = 1
        L_0x0127:
            com.google.android.exoplayer.util.Assertions.checkState(r6)
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_vp08
            r0 = r30
            if (r0 != r6) goto L_0x0137
            java.lang.String r7 = "video/x-vnd.on2.vp8"
        L_0x0133:
            goto L_0x00a1
        L_0x0135:
            r6 = 0
            goto L_0x0127
        L_0x0137:
            java.lang.String r7 = "video/x-vnd.on2.vp9"
            goto L_0x0133
        L_0x013b:
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_sv3d
            r0 = r21
            if (r0 != r6) goto L_0x014d
            r0 = r29
            r1 = r23
            r2 = r20
            byte[] r17 = parseProjFromParent(r0, r1, r2)
            goto L_0x00a1
        L_0x014d:
            int r6 = com.google.android.exoplayer.extractor.mp4.Atom.TYPE_st3d
            r0 = r21
            if (r0 != r6) goto L_0x00a1
            int r28 = r29.readUnsignedByte()
            r6 = 3
            r0 = r29
            r0.skipBytes(r6)
            if (r28 != 0) goto L_0x00a1
            int r25 = r29.readUnsignedByte()
            switch(r25) {
                case 0: goto L_0x0168;
                case 1: goto L_0x016c;
                case 2: goto L_0x0170;
                default: goto L_0x0166;
            }
        L_0x0166:
            goto L_0x00a1
        L_0x0168:
            r18 = 0
            goto L_0x00a1
        L_0x016c:
            r18 = 1
            goto L_0x00a1
        L_0x0170:
            r18 = 2
            goto L_0x00a1
        L_0x0174:
            java.lang.String r6 = java.lang.Integer.toString(r33)
            r8 = -1
            r9 = -1
            r10 = r34
            r15 = r36
            com.google.android.exoplayer.MediaFormat r6 = com.google.android.exoplayer.MediaFormat.createVideoFormat(r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18)
            r0 = r37
            r0.mediaFormat = r6
            goto L_0x0068
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.extractor.mp4.AtomParsers.parseVideoSampleEntry(com.google.android.exoplayer.util.ParsableByteArray, int, int, int, int, long, int, com.google.android.exoplayer.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    private static AvcCData parseAvcCFromParent(ParsableByteArray parent, int position) {
        parent.setPosition(position + 8 + 4);
        int nalUnitLengthFieldLength = (parent.readUnsignedByte() & 3) + 1;
        if (nalUnitLengthFieldLength == 3) {
            throw new IllegalStateException();
        }
        List<byte[]> initializationData = new ArrayList<>();
        float pixelWidthAspectRatio = 1.0f;
        int numSequenceParameterSets = parent.readUnsignedByte() & 31;
        for (int j = 0; j < numSequenceParameterSets; j++) {
            initializationData.add(NalUnitUtil.parseChildNalUnit(parent));
        }
        int numPictureParameterSets = parent.readUnsignedByte();
        for (int j2 = 0; j2 < numPictureParameterSets; j2++) {
            initializationData.add(NalUnitUtil.parseChildNalUnit(parent));
        }
        if (numSequenceParameterSets > 0) {
            ParsableBitArray spsDataBitArray = new ParsableBitArray((byte[]) initializationData.get(0));
            spsDataBitArray.setPosition((nalUnitLengthFieldLength + 1) * 8);
            pixelWidthAspectRatio = NalUnitUtil.parseSpsNalUnit(spsDataBitArray).pixelWidthAspectRatio;
        }
        return new AvcCData(initializationData, nalUnitLengthFieldLength, pixelWidthAspectRatio);
    }

    private static Pair<List<byte[]>, Integer> parseHvcCFromParent(ParsableByteArray parent, int position) {
        parent.setPosition(position + 8 + 21);
        int lengthSizeMinusOne = parent.readUnsignedByte() & 3;
        int numberOfArrays = parent.readUnsignedByte();
        int csdLength = 0;
        int csdStartPosition = parent.getPosition();
        for (int i = 0; i < numberOfArrays; i++) {
            parent.skipBytes(1);
            int numberOfNalUnits = parent.readUnsignedShort();
            for (int j = 0; j < numberOfNalUnits; j++) {
                int nalUnitLength = parent.readUnsignedShort();
                csdLength += nalUnitLength + 4;
                parent.skipBytes(nalUnitLength);
            }
        }
        parent.setPosition(csdStartPosition);
        byte[] buffer = new byte[csdLength];
        int bufferPosition = 0;
        for (int i2 = 0; i2 < numberOfArrays; i2++) {
            parent.skipBytes(1);
            int numberOfNalUnits2 = parent.readUnsignedShort();
            for (int j2 = 0; j2 < numberOfNalUnits2; j2++) {
                int nalUnitLength2 = parent.readUnsignedShort();
                System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, buffer, bufferPosition, NalUnitUtil.NAL_START_CODE.length);
                int bufferPosition2 = bufferPosition + NalUnitUtil.NAL_START_CODE.length;
                System.arraycopy(parent.data, parent.getPosition(), buffer, bufferPosition2, nalUnitLength2);
                bufferPosition = bufferPosition2 + nalUnitLength2;
                parent.skipBytes(nalUnitLength2);
            }
        }
        return Pair.create(csdLength == 0 ? null : Collections.singletonList(buffer), Integer.valueOf(lengthSizeMinusOne + 1));
    }

    private static Pair<long[], long[]> parseEdts(ContainerAtom edtsAtom) {
        if (edtsAtom != null) {
            LeafAtom elst = edtsAtom.getLeafAtomOfType(Atom.TYPE_elst);
            if (elst != null) {
                ParsableByteArray elstData = elst.data;
                elstData.setPosition(8);
                int version = Atom.parseFullAtomVersion(elstData.readInt());
                int entryCount = elstData.readUnsignedIntToInt();
                long[] editListDurations = new long[entryCount];
                long[] editListMediaTimes = new long[entryCount];
                for (int i = 0; i < entryCount; i++) {
                    editListDurations[i] = version == 1 ? elstData.readUnsignedLongToLong() : elstData.readUnsignedInt();
                    editListMediaTimes[i] = version == 1 ? elstData.readLong() : (long) elstData.readInt();
                    if (elstData.readShort() != 1) {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                    elstData.skipBytes(2);
                }
                return Pair.create(editListDurations, editListMediaTimes);
            }
        }
        return Pair.create(null, null);
    }

    private static float parsePaspFromParent(ParsableByteArray parent, int position) {
        parent.setPosition(position + 8);
        return ((float) parent.readUnsignedIntToInt()) / ((float) parent.readUnsignedIntToInt());
    }

    private static void parseAudioSampleEntry(ParsableByteArray parent, int atomType, int position, int size, int trackId, long durationUs, String language, boolean isQuickTime, StsdData out, int entryIndex) {
        int sampleRate;
        int channelCount;
        List singletonList;
        int esdsAtomPosition;
        parent.setPosition(position + 8);
        int quickTimeSoundDescriptionVersion = 0;
        if (isQuickTime) {
            parent.skipBytes(8);
            quickTimeSoundDescriptionVersion = parent.readUnsignedShort();
            parent.skipBytes(6);
        } else {
            parent.skipBytes(16);
        }
        if (quickTimeSoundDescriptionVersion == 0 || quickTimeSoundDescriptionVersion == 1) {
            channelCount = parent.readUnsignedShort();
            parent.skipBytes(6);
            sampleRate = parent.readUnsignedFixedPoint1616();
            if (quickTimeSoundDescriptionVersion == 1) {
                parent.skipBytes(16);
            }
        } else if (quickTimeSoundDescriptionVersion == 2) {
            parent.skipBytes(16);
            sampleRate = (int) Math.round(parent.readDouble());
            channelCount = parent.readUnsignedIntToInt();
            parent.skipBytes(20);
        } else {
            return;
        }
        int childPosition = parent.getPosition();
        if (atomType == Atom.TYPE_enca) {
            atomType = parseSampleEntryEncryptionData(parent, position, size, out, entryIndex);
            parent.setPosition(childPosition);
        }
        String mimeType = null;
        if (atomType == Atom.TYPE_ac_3) {
            mimeType = "audio/ac3";
        } else if (atomType == Atom.TYPE_ec_3) {
            mimeType = "audio/eac3";
        } else if (atomType == Atom.TYPE_dtsc) {
            mimeType = "audio/vnd.dts";
        } else if (atomType == Atom.TYPE_dtsh || atomType == Atom.TYPE_dtsl) {
            mimeType = "audio/vnd.dts.hd";
        } else if (atomType == Atom.TYPE_dtse) {
            mimeType = "audio/vnd.dts.hd;profile=lbr";
        } else if (atomType == Atom.TYPE_samr) {
            mimeType = "audio/3gpp";
        } else if (atomType == Atom.TYPE_sawb) {
            mimeType = "audio/amr-wb";
        } else if (atomType == Atom.TYPE_lpcm || atomType == Atom.TYPE_sowt) {
            mimeType = "audio/raw";
        }
        byte[] initializationData = null;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            Assertions.checkArgument(childAtomSize > 0, "childAtomSize should be positive");
            int childAtomType = parent.readInt();
            if (childAtomType == Atom.TYPE_esds || (isQuickTime && childAtomType == Atom.TYPE_wave)) {
                if (childAtomType == Atom.TYPE_esds) {
                    esdsAtomPosition = childPosition;
                } else {
                    esdsAtomPosition = findEsdsPosition(parent, childPosition, childAtomSize);
                }
                if (esdsAtomPosition != -1) {
                    Pair<String, byte[]> mimeTypeAndInitializationData = parseEsdsFromParent(parent, esdsAtomPosition);
                    mimeType = (String) mimeTypeAndInitializationData.first;
                    initializationData = (byte[]) mimeTypeAndInitializationData.second;
                    if ("audio/mp4a-latm".equals(mimeType)) {
                        Pair<Integer, Integer> audioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(initializationData);
                        sampleRate = ((Integer) audioSpecificConfig.first).intValue();
                        channelCount = ((Integer) audioSpecificConfig.second).intValue();
                    }
                }
            } else if (childAtomType == Atom.TYPE_dac3) {
                parent.setPosition(childPosition + 8);
                out.mediaFormat = Ac3Util.parseAc3AnnexFFormat(parent, Integer.toString(trackId), durationUs, language);
            } else if (childAtomType == Atom.TYPE_dec3) {
                parent.setPosition(childPosition + 8);
                out.mediaFormat = Ac3Util.parseEAc3AnnexFFormat(parent, Integer.toString(trackId), durationUs, language);
            } else if (childAtomType == Atom.TYPE_ddts) {
                out.mediaFormat = MediaFormat.createAudioFormat(Integer.toString(trackId), mimeType, -1, -1, durationUs, channelCount, sampleRate, null, language);
            }
            childPosition += childAtomSize;
        }
        if (out.mediaFormat == null && mimeType != null) {
            int pcmEncoding = "audio/raw".equals(mimeType) ? 2 : -1;
            String num = Integer.toString(trackId);
            if (initializationData == null) {
                singletonList = null;
            } else {
                singletonList = Collections.singletonList(initializationData);
            }
            out.mediaFormat = MediaFormat.createAudioFormat(num, mimeType, -1, -1, durationUs, channelCount, sampleRate, singletonList, language, pcmEncoding);
        }
    }

    private static int findEsdsPosition(ParsableByteArray parent, int position, int size) {
        int childAtomPosition = parent.getPosition();
        while (childAtomPosition - position < size) {
            parent.setPosition(childAtomPosition);
            int childAtomSize = parent.readInt();
            Assertions.checkArgument(childAtomSize > 0, "childAtomSize should be positive");
            if (parent.readInt() == Atom.TYPE_esds) {
                return childAtomPosition;
            }
            childAtomPosition += childAtomSize;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parent, int position) {
        String mimeType;
        parent.setPosition(position + 8 + 4);
        parent.skipBytes(1);
        parseExpandableClassSize(parent);
        parent.skipBytes(2);
        int flags = parent.readUnsignedByte();
        if ((flags & 128) != 0) {
            parent.skipBytes(2);
        }
        if ((flags & 64) != 0) {
            parent.skipBytes(parent.readUnsignedShort());
        }
        if ((flags & 32) != 0) {
            parent.skipBytes(2);
        }
        parent.skipBytes(1);
        parseExpandableClassSize(parent);
        switch (parent.readUnsignedByte()) {
            case 32:
                mimeType = "video/mp4v-es";
                break;
            case 33:
                mimeType = "video/avc";
                break;
            case 35:
                mimeType = "video/hevc";
                break;
            case 64:
            case 102:
            case 103:
            case 104:
                mimeType = "audio/mp4a-latm";
                break;
            case 107:
                return Pair.create("audio/mpeg", null);
            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /*165*/:
                mimeType = "audio/ac3";
                break;
            case CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 /*166*/:
                mimeType = "audio/eac3";
                break;
            case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /*169*/:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256 /*172*/:
                return Pair.create("audio/vnd.dts", null);
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /*170*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /*171*/:
                return Pair.create("audio/vnd.dts.hd", null);
            default:
                mimeType = null;
                break;
        }
        parent.skipBytes(12);
        parent.skipBytes(1);
        int initializationDataSize = parseExpandableClassSize(parent);
        byte[] initializationData = new byte[initializationDataSize];
        parent.readBytes(initializationData, 0, initializationDataSize);
        return Pair.create(mimeType, initializationData);
    }

    private static int parseSampleEntryEncryptionData(ParsableByteArray parent, int position, int size, StsdData out, int entryIndex) {
        boolean z;
        boolean z2 = true;
        int childPosition = parent.getPosition();
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            if (childAtomSize > 0) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "childAtomSize should be positive");
            if (parent.readInt() == Atom.TYPE_sinf) {
                Pair<Integer, TrackEncryptionBox> result = parseSinfFromParent(parent, childPosition, childAtomSize);
                Integer dataFormat = (Integer) result.first;
                if (dataFormat == null) {
                    z2 = false;
                }
                Assertions.checkArgument(z2, "frma atom is mandatory");
                out.trackEncryptionBoxes[entryIndex] = (TrackEncryptionBox) result.second;
                return dataFormat.intValue();
            }
            childPosition += childAtomSize;
        }
        return 0;
    }

    private static Pair<Integer, TrackEncryptionBox> parseSinfFromParent(ParsableByteArray parent, int position, int size) {
        int childPosition = position + 8;
        TrackEncryptionBox trackEncryptionBox = null;
        Integer dataFormat = null;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            int childAtomType = parent.readInt();
            if (childAtomType == Atom.TYPE_frma) {
                dataFormat = Integer.valueOf(parent.readInt());
            } else if (childAtomType == Atom.TYPE_schm) {
                parent.skipBytes(4);
                parent.readInt();
                parent.readInt();
            } else if (childAtomType == Atom.TYPE_schi) {
                trackEncryptionBox = parseSchiFromParent(parent, childPosition, childAtomSize);
            }
            childPosition += childAtomSize;
        }
        return Pair.create(dataFormat, trackEncryptionBox);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parent, int position, int size) {
        boolean defaultIsEncrypted = true;
        int childPosition = position + 8;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            if (parent.readInt() == Atom.TYPE_tenc) {
                parent.skipBytes(6);
                if (parent.readUnsignedByte() != 1) {
                    defaultIsEncrypted = false;
                }
                int defaultInitVectorSize = parent.readUnsignedByte();
                byte[] defaultKeyId = new byte[16];
                parent.readBytes(defaultKeyId, 0, defaultKeyId.length);
                return new TrackEncryptionBox(defaultIsEncrypted, defaultInitVectorSize, defaultKeyId);
            }
            childPosition += childAtomSize;
        }
        return null;
    }

    private static byte[] parseProjFromParent(ParsableByteArray parent, int position, int size) {
        int childPosition = position + 8;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            if (parent.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parent.data, childPosition, childPosition + childAtomSize);
            }
            childPosition += childAtomSize;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray data) {
        int currentByte = data.readUnsignedByte();
        int size = currentByte & CertificateBody.profileType;
        while ((currentByte & 128) == 128) {
            currentByte = data.readUnsignedByte();
            size = (size << 7) | (currentByte & CertificateBody.profileType);
        }
        return size;
    }
}
