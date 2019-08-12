package com.google.android.exoplayer.extractor.webm;

import android.util.Pair;
import android.util.SparseArray;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.drm.DrmInitData.SchemeInitData;
import com.google.android.exoplayer.drm.DrmInitData.Universal;
import com.google.android.exoplayer.extractor.ChunkIndex;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.LongArray;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.cbeff.ISO781611;
import org.spongycastle.crypto.tls.CipherSuite;

public final class WebmExtractor implements Extractor {
    private static final byte[] SUBRIP_PREFIX = {49, 10, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, 58, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, 58, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, ISO7816.INS_UNBLOCK_CHV, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, ISO7816.INS_VERIFY, 45, 45, 62, ISO7816.INS_VERIFY, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, 58, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, 58, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, ISO7816.INS_UNBLOCK_CHV, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, 10};
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY, ISO7816.INS_VERIFY};
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentDrmInitData;
    private boolean sentSeekMap;
    private final ParsableByteArray subripSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    private final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        private InnerEbmlReaderOutput() {
        }

        public int getElementType(int id) {
            return WebmExtractor.this.getElementType(id);
        }

        public boolean isLevel1Element(int id) {
            return WebmExtractor.this.isLevel1Element(id);
        }

        public void startMasterElement(int id, long contentPosition, long contentSize) throws ParserException {
            WebmExtractor.this.startMasterElement(id, contentPosition, contentSize);
        }

        public void endMasterElement(int id) throws ParserException {
            WebmExtractor.this.endMasterElement(id);
        }

        public void integerElement(int id, long value) throws ParserException {
            WebmExtractor.this.integerElement(id, value);
        }

        public void floatElement(int id, double value) throws ParserException {
            WebmExtractor.this.floatElement(id, value);
        }

        public void stringElement(int id, String value) throws ParserException {
            WebmExtractor.this.stringElement(id, value);
        }

        public void binaryElement(int id, int contentsSize, ExtractorInput input) throws IOException, InterruptedException {
            WebmExtractor.this.binaryElement(id, contentsSize, input);
        }
    }

    private static final class Track {
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public byte[] encryptionKeyId;
        public boolean hasContentEncryption;
        public int height;
        /* access modifiers changed from: private */
        public String language;
        public int nalUnitLengthFieldLength;
        public int number;
        public TrackOutput output;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int type;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.language = "eng";
        }

        public void initializeOutput(ExtractorOutput output2, int trackId, long durationUs) throws ParserException {
            String mimeType;
            MediaFormat format;
            int maxInputSize = -1;
            int pcmEncoding = -1;
            List<byte[]> initializationData = null;
            String str = this.codecId;
            char c = 65535;
            switch (str.hashCode()) {
                case -2095576542:
                    if (str.equals("V_MPEG4/ISO/AP")) {
                        c = 5;
                        break;
                    }
                    break;
                case -2095575984:
                    if (str.equals("V_MPEG4/ISO/SP")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1985379776:
                    if (str.equals("A_MS/ACM")) {
                        c = 20;
                        break;
                    }
                    break;
                case -1784763192:
                    if (str.equals("A_TRUEHD")) {
                        c = 15;
                        break;
                    }
                    break;
                case -1730367663:
                    if (str.equals("A_VORBIS")) {
                        c = 9;
                        break;
                    }
                    break;
                case -1482641357:
                    if (str.equals("A_MPEG/L3")) {
                        c = 12;
                        break;
                    }
                    break;
                case -1373388978:
                    if (str.equals("V_MS/VFW/FOURCC")) {
                        c = 8;
                        break;
                    }
                    break;
                case -538363189:
                    if (str.equals("V_MPEG4/ISO/ASP")) {
                        c = 4;
                        break;
                    }
                    break;
                case -538363109:
                    if (str.equals("V_MPEG4/ISO/AVC")) {
                        c = 6;
                        break;
                    }
                    break;
                case -425012669:
                    if (str.equals("S_VOBSUB")) {
                        c = 23;
                        break;
                    }
                    break;
                case -356037306:
                    if (str.equals("A_DTS/LOSSLESS")) {
                        c = 18;
                        break;
                    }
                    break;
                case 62923557:
                    if (str.equals("A_AAC")) {
                        c = 11;
                        break;
                    }
                    break;
                case 62923603:
                    if (str.equals("A_AC3")) {
                        c = 13;
                        break;
                    }
                    break;
                case 62927045:
                    if (str.equals("A_DTS")) {
                        c = 16;
                        break;
                    }
                    break;
                case 82338133:
                    if (str.equals("V_VP8")) {
                        c = 0;
                        break;
                    }
                    break;
                case 82338134:
                    if (str.equals("V_VP9")) {
                        c = 1;
                        break;
                    }
                    break;
                case 99146302:
                    if (str.equals("S_HDMV/PGS")) {
                        c = 24;
                        break;
                    }
                    break;
                case 542569478:
                    if (str.equals("A_DTS/EXPRESS")) {
                        c = 17;
                        break;
                    }
                    break;
                case 725957860:
                    if (str.equals("A_PCM/INT/LIT")) {
                        c = 21;
                        break;
                    }
                    break;
                case 855502857:
                    if (str.equals("V_MPEGH/ISO/HEVC")) {
                        c = 7;
                        break;
                    }
                    break;
                case 1422270023:
                    if (str.equals("S_TEXT/UTF8")) {
                        c = 22;
                        break;
                    }
                    break;
                case 1809237540:
                    if (str.equals("V_MPEG2")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1950749482:
                    if (str.equals("A_EAC3")) {
                        c = 14;
                        break;
                    }
                    break;
                case 1950789798:
                    if (str.equals("A_FLAC")) {
                        c = 19;
                        break;
                    }
                    break;
                case 1951062397:
                    if (str.equals("A_OPUS")) {
                        c = 10;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    mimeType = "video/x-vnd.on2.vp8";
                    break;
                case 1:
                    mimeType = "video/x-vnd.on2.vp9";
                    break;
                case 2:
                    mimeType = "video/mpeg2";
                    break;
                case 3:
                case 4:
                case 5:
                    mimeType = "video/mp4v-es";
                    if (this.codecPrivate != null) {
                        initializationData = Collections.singletonList(this.codecPrivate);
                        break;
                    } else {
                        initializationData = null;
                        break;
                    }
                case 6:
                    mimeType = "video/avc";
                    Pair<List<byte[]>, Integer> h264Data = parseAvcCodecPrivate(new ParsableByteArray(this.codecPrivate));
                    initializationData = (List) h264Data.first;
                    this.nalUnitLengthFieldLength = ((Integer) h264Data.second).intValue();
                    break;
                case 7:
                    mimeType = "video/hevc";
                    Pair<List<byte[]>, Integer> hevcData = parseHevcCodecPrivate(new ParsableByteArray(this.codecPrivate));
                    initializationData = (List) hevcData.first;
                    this.nalUnitLengthFieldLength = ((Integer) hevcData.second).intValue();
                    break;
                case 8:
                    mimeType = "video/wvc1";
                    initializationData = parseFourCcVc1Private(new ParsableByteArray(this.codecPrivate));
                    break;
                case 9:
                    mimeType = "audio/vorbis";
                    maxInputSize = 8192;
                    initializationData = parseVorbisCodecPrivate(this.codecPrivate);
                    break;
                case 10:
                    mimeType = "audio/opus";
                    maxInputSize = 5760;
                    initializationData = new ArrayList<>(3);
                    initializationData.add(this.codecPrivate);
                    initializationData.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.codecDelayNs).array());
                    initializationData.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.seekPreRollNs).array());
                    break;
                case 11:
                    mimeType = "audio/mp4a-latm";
                    initializationData = Collections.singletonList(this.codecPrivate);
                    break;
                case 12:
                    mimeType = "audio/mpeg";
                    maxInputSize = 4096;
                    break;
                case 13:
                    mimeType = "audio/ac3";
                    break;
                case 14:
                    mimeType = "audio/eac3";
                    break;
                case 15:
                    mimeType = "audio/true-hd";
                    break;
                case 16:
                case 17:
                    mimeType = "audio/vnd.dts";
                    break;
                case 18:
                    mimeType = "audio/vnd.dts.hd";
                    break;
                case 19:
                    mimeType = "audio/x-flac";
                    initializationData = Collections.singletonList(this.codecPrivate);
                    break;
                case 20:
                    mimeType = "audio/raw";
                    if (!parseMsAcmCodecPrivate(new ParsableByteArray(this.codecPrivate))) {
                        throw new ParserException("Non-PCM MS/ACM is unsupported");
                    }
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        throw new ParserException("Unsupported PCM bit depth: " + this.audioBitDepth);
                    }
                    break;
                case 21:
                    mimeType = "audio/raw";
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        throw new ParserException("Unsupported PCM bit depth: " + this.audioBitDepth);
                    }
                    break;
                case 22:
                    mimeType = "application/x-subrip";
                    break;
                case 23:
                    mimeType = "application/vobsub";
                    initializationData = Collections.singletonList(this.codecPrivate);
                    break;
                case 24:
                    mimeType = "application/pgs";
                    break;
                default:
                    throw new ParserException("Unrecognized codec identifier.");
            }
            if (MimeTypes.isAudio(mimeType)) {
                format = MediaFormat.createAudioFormat(Integer.toString(trackId), mimeType, -1, maxInputSize, durationUs, this.channelCount, this.sampleRate, initializationData, this.language, pcmEncoding);
            } else if (MimeTypes.isVideo(mimeType)) {
                if (this.displayUnit == 0) {
                    this.displayWidth = this.displayWidth == -1 ? this.width : this.displayWidth;
                    this.displayHeight = this.displayHeight == -1 ? this.height : this.displayHeight;
                }
                float pixelWidthHeightRatio = -1.0f;
                if (!(this.displayWidth == -1 || this.displayHeight == -1)) {
                    pixelWidthHeightRatio = ((float) (this.height * this.displayWidth)) / ((float) (this.width * this.displayHeight));
                }
                format = MediaFormat.createVideoFormat(Integer.toString(trackId), mimeType, -1, maxInputSize, durationUs, this.width, this.height, initializationData, -1, pixelWidthHeightRatio);
            } else if ("application/x-subrip".equals(mimeType)) {
                format = MediaFormat.createTextFormat(Integer.toString(trackId), mimeType, -1, durationUs, this.language);
            } else if ("application/vobsub".equals(mimeType) || "application/pgs".equals(mimeType)) {
                format = MediaFormat.createImageFormat(Integer.toString(trackId), mimeType, -1, durationUs, initializationData, this.language);
            } else {
                throw new ParserException("Unexpected MIME type.");
            }
            this.output = output2.track(this.number);
            this.output.format(format);
        }

        private static List<byte[]> parseFourCcVc1Private(ParsableByteArray buffer) throws ParserException {
            try {
                buffer.skipBytes(16);
                long compression = buffer.readLittleEndianUnsignedInt();
                if (compression != 826496599) {
                    throw new ParserException("Unsupported FourCC compression type: " + compression);
                }
                int startOffset = buffer.getPosition() + 20;
                byte[] bufferData = buffer.data;
                for (int offset = startOffset; offset < bufferData.length - 4; offset++) {
                    if (bufferData[offset] == 0 && bufferData[offset + 1] == 0 && bufferData[offset + 2] == 1 && bufferData[offset + 3] == 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bufferData, offset, bufferData.length));
                    }
                }
                throw new ParserException("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParserException("Error parsing FourCC VC1 codec private");
            }
        }

        private static Pair<List<byte[]>, Integer> parseAvcCodecPrivate(ParsableByteArray buffer) throws ParserException {
            try {
                buffer.setPosition(4);
                int nalUnitLengthFieldLength2 = (buffer.readUnsignedByte() & 3) + 1;
                if (nalUnitLengthFieldLength2 == 3) {
                    throw new ParserException();
                }
                List<byte[]> initializationData = new ArrayList<>();
                int numSequenceParameterSets = buffer.readUnsignedByte() & 31;
                for (int i = 0; i < numSequenceParameterSets; i++) {
                    initializationData.add(NalUnitUtil.parseChildNalUnit(buffer));
                }
                int numPictureParameterSets = buffer.readUnsignedByte();
                for (int j = 0; j < numPictureParameterSets; j++) {
                    initializationData.add(NalUnitUtil.parseChildNalUnit(buffer));
                }
                return Pair.create(initializationData, Integer.valueOf(nalUnitLengthFieldLength2));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParserException("Error parsing AVC codec private");
            }
        }

        private static Pair<List<byte[]>, Integer> parseHevcCodecPrivate(ParsableByteArray parent) throws ParserException {
            try {
                parent.setPosition(21);
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
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParserException("Error parsing HEVC codec private");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] codecPrivate2) throws ParserException {
            try {
                if (codecPrivate2[0] != 2) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int vorbisInfoLength = 0;
                int offset = 1;
                while (codecPrivate2[offset] == -1) {
                    vorbisInfoLength += 255;
                    offset++;
                }
                int vorbisInfoLength2 = vorbisInfoLength + codecPrivate2[offset];
                int vorbisSkipLength = 0;
                int offset2 = offset + 1;
                while (codecPrivate2[offset2] == -1) {
                    vorbisSkipLength += 255;
                    offset2++;
                }
                int offset3 = offset2 + 1;
                int vorbisSkipLength2 = vorbisSkipLength + codecPrivate2[offset2];
                if (codecPrivate2[offset3] != 1) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] vorbisInfo = new byte[vorbisInfoLength2];
                System.arraycopy(codecPrivate2, offset3, vorbisInfo, 0, vorbisInfoLength2);
                int offset4 = offset3 + vorbisInfoLength2;
                if (codecPrivate2[offset4] != 3) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int offset5 = offset4 + vorbisSkipLength2;
                if (codecPrivate2[offset5] != 5) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] vorbisBooks = new byte[(codecPrivate2.length - offset5)];
                System.arraycopy(codecPrivate2, offset5, vorbisBooks, 0, codecPrivate2.length - offset5);
                List<byte[]> initializationData = new ArrayList<>(2);
                initializationData.add(vorbisInfo);
                initializationData.add(vorbisBooks);
                return initializationData;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray buffer) throws ParserException {
            try {
                int formatTag = buffer.readLittleEndianUnsignedShort();
                if (formatTag == 1) {
                    return true;
                }
                if (formatTag != 65534) {
                    return false;
                }
                buffer.setPosition(24);
                if (buffer.readLong() == WebmExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && buffer.readLong() == WebmExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                    return true;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }

    public WebmExtractor() {
        this(new DefaultEbmlReader());
    }

    WebmExtractor(EbmlReader reader2) {
        this.segmentContentPosition = -1;
        this.segmentContentSize = -1;
        this.timecodeScale = -1;
        this.durationTimecode = -1;
        this.durationUs = -1;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = -1;
        this.reader = reader2;
        this.reader.init(new InnerEbmlReaderOutput());
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subripSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
        return new Sniffer().sniff(input);
    }

    public void init(ExtractorOutput output) {
        this.extractorOutput = output;
    }

    public void seek() {
        this.clusterTimecodeUs = -1;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
    }

    public void release() {
    }

    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException, InterruptedException {
        this.sampleRead = false;
        boolean continueReading = true;
        while (continueReading && !this.sampleRead) {
            continueReading = this.reader.read(input);
            if (continueReading && maybeSeekForCues(seekPosition, input.getPosition())) {
                return 1;
            }
        }
        if (!continueReading) {
            return -1;
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int getElementType(int id) {
        switch (id) {
            case ISO781611.CREATION_DATE_AND_TIME_TAG /*131*/:
            case CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA /*155*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /*159*/:
            case CipherSuite.TLS_PSK_WITH_NULL_SHA256 /*176*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /*179*/:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*186*/:
            case JfifUtil.MARKER_RST7 /*215*/:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21680:
            case 21682:
            case 21690:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case 17026:
            case 2274716:
                return 3;
            case 160:
            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256 /*174*/:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384 /*183*/:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /*187*/:
            case 224:
            case JfifUtil.MARKER_APP1 /*225*/:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 25152:
            case 28032:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /*161*/:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /*163*/:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
                return 4;
            case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /*181*/:
            case 17545:
                return 5;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isLevel1Element(int id) {
        return id == 357149030 || id == 524531317 || id == 475249515 || id == 374648427;
    }

    /* access modifiers changed from: 0000 */
    public void startMasterElement(int id, long contentPosition, long contentSize) throws ParserException {
        switch (id) {
            case 160:
                this.sampleSeenReferenceBlock = false;
                return;
            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256 /*174*/:
                this.currentTrack = new Track();
                return;
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /*187*/:
                this.seenClusterPositionForCurrentCuePoint = false;
                return;
            case 19899:
                this.seekEntryId = -1;
                this.seekEntryPosition = -1;
                return;
            case 20533:
                this.currentTrack.hasContentEncryption = true;
                return;
            case 408125543:
                if (this.segmentContentPosition == -1 || this.segmentContentPosition == contentPosition) {
                    this.segmentContentPosition = contentPosition;
                    this.segmentContentSize = contentSize;
                    return;
                }
                throw new ParserException("Multiple Segment elements not supported");
            case 475249515:
                this.cueTimesUs = new LongArray();
                this.cueClusterPositions = new LongArray();
                return;
            case 524531317:
                if (this.sentSeekMap) {
                    return;
                }
                if (this.cuesContentPosition != -1) {
                    this.seekForCues = true;
                    return;
                }
                this.extractorOutput.seekMap(SeekMap.UNSEEKABLE);
                this.sentSeekMap = true;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void endMasterElement(int id) throws ParserException {
        switch (id) {
            case 160:
                if (this.blockState == 2) {
                    if (!this.sampleSeenReferenceBlock) {
                        this.blockFlags |= 1;
                    }
                    commitSampleToOutput((Track) this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
                    this.blockState = 0;
                    return;
                }
                return;
            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256 /*174*/:
                if (this.tracks.get(this.currentTrack.number) == null && isCodecSupported(this.currentTrack.codecId)) {
                    this.currentTrack.initializeOutput(this.extractorOutput, this.currentTrack.number, this.durationUs);
                    this.tracks.put(this.currentTrack.number, this.currentTrack);
                }
                this.currentTrack = null;
                return;
            case 19899:
                if (this.seekEntryId == -1 || this.seekEntryPosition == -1) {
                    throw new ParserException("Mandatory element SeekID or SeekPosition not found");
                } else if (this.seekEntryId == 475249515) {
                    this.cuesContentPosition = this.seekEntryPosition;
                    return;
                } else {
                    return;
                }
            case 25152:
                if (!this.currentTrack.hasContentEncryption) {
                    return;
                }
                if (this.currentTrack.encryptionKeyId == null) {
                    throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                } else if (!this.sentDrmInitData) {
                    this.extractorOutput.drmInitData(new Universal(new SchemeInitData("video/webm", this.currentTrack.encryptionKeyId)));
                    this.sentDrmInitData = true;
                    return;
                } else {
                    return;
                }
            case 28032:
                if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                    throw new ParserException("Combining encryption and compression is not supported");
                }
                return;
            case 357149030:
                if (this.timecodeScale == -1) {
                    this.timecodeScale = 1000000;
                }
                if (this.durationTimecode != -1) {
                    this.durationUs = scaleTimecodeToUs(this.durationTimecode);
                    return;
                }
                return;
            case 374648427:
                if (this.tracks.size() == 0) {
                    throw new ParserException("No valid tracks were found");
                }
                this.extractorOutput.endTracks();
                return;
            case 475249515:
                if (!this.sentSeekMap) {
                    this.extractorOutput.seekMap(buildSeekMap());
                    this.sentSeekMap = true;
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void integerElement(int id, long value) throws ParserException {
        switch (id) {
            case ISO781611.CREATION_DATE_AND_TIME_TAG /*131*/:
                this.currentTrack.type = (int) value;
                return;
            case CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA /*155*/:
                this.blockDurationUs = scaleTimecodeToUs(value);
                return;
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /*159*/:
                this.currentTrack.channelCount = (int) value;
                return;
            case CipherSuite.TLS_PSK_WITH_NULL_SHA256 /*176*/:
                this.currentTrack.width = (int) value;
                return;
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /*179*/:
                this.cueTimesUs.add(scaleTimecodeToUs(value));
                return;
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*186*/:
                this.currentTrack.height = (int) value;
                return;
            case JfifUtil.MARKER_RST7 /*215*/:
                this.currentTrack.number = (int) value;
                return;
            case 231:
                this.clusterTimecodeUs = scaleTimecodeToUs(value);
                return;
            case 241:
                if (!this.seenClusterPositionForCurrentCuePoint) {
                    this.cueClusterPositions.add(value);
                    this.seenClusterPositionForCurrentCuePoint = true;
                    return;
                }
                return;
            case 251:
                this.sampleSeenReferenceBlock = true;
                return;
            case 16980:
                if (value != 3) {
                    throw new ParserException("ContentCompAlgo " + value + " not supported");
                }
                return;
            case 17029:
                if (value < 1 || value > 2) {
                    throw new ParserException("DocTypeReadVersion " + value + " not supported");
                }
                return;
            case 17143:
                if (value != 1) {
                    throw new ParserException("EBMLReadVersion " + value + " not supported");
                }
                return;
            case 18401:
                if (value != 5) {
                    throw new ParserException("ContentEncAlgo " + value + " not supported");
                }
                return;
            case 18408:
                if (value != 1) {
                    throw new ParserException("AESSettingsCipherMode " + value + " not supported");
                }
                return;
            case 20529:
                if (value != 0) {
                    throw new ParserException("ContentEncodingOrder " + value + " not supported");
                }
                return;
            case 20530:
                if (value != 1) {
                    throw new ParserException("ContentEncodingScope " + value + " not supported");
                }
                return;
            case 21420:
                this.seekEntryPosition = this.segmentContentPosition + value;
                return;
            case 21680:
                this.currentTrack.displayWidth = (int) value;
                return;
            case 21682:
                this.currentTrack.displayUnit = (int) value;
                return;
            case 21690:
                this.currentTrack.displayHeight = (int) value;
                return;
            case 22186:
                this.currentTrack.codecDelayNs = value;
                return;
            case 22203:
                this.currentTrack.seekPreRollNs = value;
                return;
            case 25188:
                this.currentTrack.audioBitDepth = (int) value;
                return;
            case 2352003:
                this.currentTrack.defaultSampleDurationNs = (int) value;
                return;
            case 2807729:
                this.timecodeScale = value;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void floatElement(int id, double value) {
        switch (id) {
            case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /*181*/:
                this.currentTrack.sampleRate = (int) value;
                return;
            case 17545:
                this.durationTimecode = (long) value;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void stringElement(int id, String value) throws ParserException {
        switch (id) {
            case 134:
                this.currentTrack.codecId = value;
                return;
            case 17026:
                if (!"webm".equals(value) && !"matroska".equals(value)) {
                    throw new ParserException("DocType " + value + " not supported");
                }
                return;
            case 2274716:
                this.currentTrack.language = value;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void binaryElement(int id, int contentSize, ExtractorInput input) throws IOException, InterruptedException {
        int byteValue;
        int i;
        int i2;
        switch (id) {
            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /*161*/:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /*163*/:
                if (this.blockState == 0) {
                    this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(input, false, true, 8);
                    this.blockTrackNumberLength = this.varintReader.getLastLength();
                    this.blockDurationUs = -1;
                    this.blockState = 1;
                    this.scratch.reset();
                }
                Track track = (Track) this.tracks.get(this.blockTrackNumber);
                if (track == null) {
                    input.skipFully(contentSize - this.blockTrackNumberLength);
                    this.blockState = 0;
                    return;
                }
                if (this.blockState == 1) {
                    readScratch(input, 3);
                    int lacing = (this.scratch.data[2] & 6) >> 1;
                    if (lacing == 0) {
                        this.blockLacingSampleCount = 1;
                        this.blockLacingSampleSizes = ensureArrayCapacity(this.blockLacingSampleSizes, 1);
                        this.blockLacingSampleSizes[0] = (contentSize - this.blockTrackNumberLength) - 3;
                    } else if (id != 163) {
                        throw new ParserException("Lacing only supported in SimpleBlocks.");
                    } else {
                        readScratch(input, 4);
                        this.blockLacingSampleCount = (this.scratch.data[3] & 255) + 1;
                        this.blockLacingSampleSizes = ensureArrayCapacity(this.blockLacingSampleSizes, this.blockLacingSampleCount);
                        if (lacing == 2) {
                            Arrays.fill(this.blockLacingSampleSizes, 0, this.blockLacingSampleCount, ((contentSize - this.blockTrackNumberLength) - 4) / this.blockLacingSampleCount);
                        } else if (lacing == 1) {
                            int totalSamplesSize = 0;
                            int headerSize = 4;
                            for (int sampleIndex = 0; sampleIndex < this.blockLacingSampleCount - 1; sampleIndex++) {
                                this.blockLacingSampleSizes[sampleIndex] = 0;
                                do {
                                    headerSize++;
                                    readScratch(input, headerSize);
                                    byteValue = this.scratch.data[headerSize - 1] & 255;
                                    int[] iArr = this.blockLacingSampleSizes;
                                    iArr[sampleIndex] = iArr[sampleIndex] + byteValue;
                                } while (byteValue == 255);
                                totalSamplesSize += this.blockLacingSampleSizes[sampleIndex];
                            }
                            this.blockLacingSampleSizes[this.blockLacingSampleCount - 1] = ((contentSize - this.blockTrackNumberLength) - headerSize) - totalSamplesSize;
                        } else if (lacing == 3) {
                            int totalSamplesSize2 = 0;
                            int headerSize2 = 4;
                            for (int sampleIndex2 = 0; sampleIndex2 < this.blockLacingSampleCount - 1; sampleIndex2++) {
                                this.blockLacingSampleSizes[sampleIndex2] = 0;
                                headerSize2++;
                                readScratch(input, headerSize2);
                                if (this.scratch.data[headerSize2 - 1] == 0) {
                                    throw new ParserException("No valid varint length mask found");
                                }
                                long readValue = 0;
                                int i3 = 0;
                                while (true) {
                                    if (i3 < 8) {
                                        int lengthMask = 1 << (7 - i3);
                                        if ((this.scratch.data[headerSize2 - 1] & lengthMask) != 0) {
                                            int readPosition = headerSize2 - 1;
                                            headerSize2 += i3;
                                            readScratch(input, headerSize2);
                                            readValue = (long) (this.scratch.data[readPosition] & 255 & (lengthMask ^ -1));
                                            for (int readPosition2 = readPosition + 1; readPosition2 < headerSize2; readPosition2++) {
                                                readValue = (readValue << 8) | ((long) (this.scratch.data[readPosition2] & 255));
                                            }
                                            if (sampleIndex2 > 0) {
                                                readValue -= (1 << ((i3 * 7) + 6)) - 1;
                                            }
                                        } else {
                                            i3++;
                                        }
                                    }
                                }
                                if (readValue < -2147483648L || readValue > 2147483647L) {
                                    throw new ParserException("EBML lacing sample size out of range.");
                                }
                                int intReadValue = (int) readValue;
                                int[] iArr2 = this.blockLacingSampleSizes;
                                if (sampleIndex2 != 0) {
                                    intReadValue += this.blockLacingSampleSizes[sampleIndex2 - 1];
                                }
                                iArr2[sampleIndex2] = intReadValue;
                                totalSamplesSize2 += this.blockLacingSampleSizes[sampleIndex2];
                            }
                            this.blockLacingSampleSizes[this.blockLacingSampleCount - 1] = ((contentSize - this.blockTrackNumberLength) - headerSize2) - totalSamplesSize2;
                        } else {
                            throw new ParserException("Unexpected lacing value: " + lacing);
                        }
                    }
                    this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((long) ((this.scratch.data[0] << 8) | (this.scratch.data[1] & 255)));
                    boolean isInvisible = (this.scratch.data[2] & 8) == 8;
                    if (track.type == 2 || (id == 163 && (this.scratch.data[2] & ISOFileInfo.DATA_BYTES1) == 128)) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (isInvisible) {
                        i2 = 134217728;
                    } else {
                        i2 = 0;
                    }
                    this.blockFlags = i2 | i;
                    this.blockState = 2;
                    this.blockLacingSampleIndex = 0;
                }
                if (id == 163) {
                    while (this.blockLacingSampleIndex < this.blockLacingSampleCount) {
                        writeSampleData(input, track, this.blockLacingSampleSizes[this.blockLacingSampleIndex]);
                        commitSampleToOutput(track, this.blockTimeUs + ((long) ((this.blockLacingSampleIndex * track.defaultSampleDurationNs) / 1000)));
                        this.blockLacingSampleIndex++;
                    }
                    this.blockState = 0;
                    return;
                }
                writeSampleData(input, track, this.blockLacingSampleSizes[0]);
                return;
            case 16981:
                this.currentTrack.sampleStrippedBytes = new byte[contentSize];
                input.readFully(this.currentTrack.sampleStrippedBytes, 0, contentSize);
                return;
            case 18402:
                this.currentTrack.encryptionKeyId = new byte[contentSize];
                input.readFully(this.currentTrack.encryptionKeyId, 0, contentSize);
                return;
            case 21419:
                Arrays.fill(this.seekEntryIdBytes.data, 0);
                input.readFully(this.seekEntryIdBytes.data, 4 - contentSize, contentSize);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
                return;
            case 25506:
                this.currentTrack.codecPrivate = new byte[contentSize];
                input.readFully(this.currentTrack.codecPrivate, 0, contentSize);
                return;
            default:
                throw new ParserException("Unexpected id: " + id);
        }
    }

    private void commitSampleToOutput(Track track, long timeUs) {
        if ("S_TEXT/UTF8".equals(track.codecId)) {
            writeSubripSample(track);
        }
        track.output.sampleMetadata(timeUs, this.blockFlags, this.sampleBytesWritten, 0, track.encryptionKeyId);
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput input, int requiredLength) throws IOException, InterruptedException {
        if (this.scratch.limit() < requiredLength) {
            if (this.scratch.capacity() < requiredLength) {
                this.scratch.reset(Arrays.copyOf(this.scratch.data, Math.max(this.scratch.data.length * 2, requiredLength)), this.scratch.limit());
            }
            input.readFully(this.scratch.data, this.scratch.limit(), requiredLength - this.scratch.limit());
            this.scratch.setLimit(requiredLength);
        }
    }

    private void writeSampleData(ExtractorInput input, Track track, int size) throws IOException, InterruptedException {
        if ("S_TEXT/UTF8".equals(track.codecId)) {
            int sizeWithPrefix = SUBRIP_PREFIX.length + size;
            if (this.subripSample.capacity() < sizeWithPrefix) {
                this.subripSample.data = Arrays.copyOf(SUBRIP_PREFIX, sizeWithPrefix + size);
            }
            input.readFully(this.subripSample.data, SUBRIP_PREFIX.length, size);
            this.subripSample.setPosition(0);
            this.subripSample.setLimit(sizeWithPrefix);
            return;
        }
        TrackOutput output = track.output;
        if (!this.sampleEncodingHandled) {
            if (track.hasContentEncryption) {
                this.blockFlags &= -3;
                if (!this.sampleSignalByteRead) {
                    input.readFully(this.scratch.data, 0, 1);
                    this.sampleBytesRead++;
                    if ((this.scratch.data[0] & ISOFileInfo.DATA_BYTES1) == 128) {
                        throw new ParserException("Extension bit is set in signal byte");
                    }
                    this.sampleSignalByte = this.scratch.data[0];
                    this.sampleSignalByteRead = true;
                }
                if ((this.sampleSignalByte & 1) == 1) {
                    boolean hasSubsampleEncryption = (this.sampleSignalByte & 2) == 2;
                    this.blockFlags |= 2;
                    if (!this.sampleInitializationVectorRead) {
                        input.readFully(this.encryptionInitializationVector.data, 0, 8);
                        this.sampleBytesRead += 8;
                        this.sampleInitializationVectorRead = true;
                        this.scratch.data[0] = (byte) ((hasSubsampleEncryption ? 128 : 0) | 8);
                        this.scratch.setPosition(0);
                        output.sampleData(this.scratch, 1);
                        this.sampleBytesWritten++;
                        this.encryptionInitializationVector.setPosition(0);
                        output.sampleData(this.encryptionInitializationVector, 8);
                        this.sampleBytesWritten += 8;
                    }
                    if (hasSubsampleEncryption) {
                        if (!this.samplePartitionCountRead) {
                            input.readFully(this.scratch.data, 0, 1);
                            this.sampleBytesRead++;
                            this.scratch.setPosition(0);
                            this.samplePartitionCount = this.scratch.readUnsignedByte();
                            this.samplePartitionCountRead = true;
                        }
                        int samplePartitionDataSize = this.samplePartitionCount * 4;
                        if (this.scratch.limit() < samplePartitionDataSize) {
                            this.scratch.reset(new byte[samplePartitionDataSize], samplePartitionDataSize);
                        }
                        input.readFully(this.scratch.data, 0, samplePartitionDataSize);
                        this.sampleBytesRead += samplePartitionDataSize;
                        this.scratch.setPosition(0);
                        this.scratch.setLimit(samplePartitionDataSize);
                        short subsampleCount = (short) ((this.samplePartitionCount / 2) + 1);
                        int subsampleDataSize = (subsampleCount * 6) + 2;
                        if (this.encryptionSubsampleDataBuffer == null || this.encryptionSubsampleDataBuffer.capacity() < subsampleDataSize) {
                            this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(subsampleDataSize);
                        }
                        this.encryptionSubsampleDataBuffer.position(0);
                        this.encryptionSubsampleDataBuffer.putShort(subsampleCount);
                        int partitionOffset = 0;
                        for (int i = 0; i < this.samplePartitionCount; i++) {
                            int previousPartitionOffset = partitionOffset;
                            partitionOffset = this.scratch.readUnsignedIntToInt();
                            if (i % 2 == 0) {
                                this.encryptionSubsampleDataBuffer.putShort((short) (partitionOffset - previousPartitionOffset));
                            } else {
                                this.encryptionSubsampleDataBuffer.putInt(partitionOffset - previousPartitionOffset);
                            }
                        }
                        int finalPartitionSize = (size - this.sampleBytesRead) - partitionOffset;
                        if (this.samplePartitionCount % 2 == 1) {
                            this.encryptionSubsampleDataBuffer.putInt(finalPartitionSize);
                        } else {
                            this.encryptionSubsampleDataBuffer.putShort((short) finalPartitionSize);
                            this.encryptionSubsampleDataBuffer.putInt(0);
                        }
                        this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), subsampleDataSize);
                        output.sampleData(this.encryptionSubsampleData, subsampleDataSize);
                        this.sampleBytesWritten += subsampleDataSize;
                    }
                }
            } else if (track.sampleStrippedBytes != null) {
                this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
            }
            this.sampleEncodingHandled = true;
        }
        int size2 = size + this.sampleStrippedBytes.limit();
        if ("V_MPEG4/ISO/AVC".equals(track.codecId) || "V_MPEGH/ISO/HEVC".equals(track.codecId)) {
            byte[] nalLengthData = this.nalLength.data;
            nalLengthData[0] = 0;
            nalLengthData[1] = 0;
            nalLengthData[2] = 0;
            int nalUnitLengthFieldLength = track.nalUnitLengthFieldLength;
            int nalUnitLengthFieldLengthDiff = 4 - track.nalUnitLengthFieldLength;
            while (this.sampleBytesRead < size2) {
                if (this.sampleCurrentNalBytesRemaining == 0) {
                    readToTarget(input, nalLengthData, nalUnitLengthFieldLengthDiff, nalUnitLengthFieldLength);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    output.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                } else {
                    this.sampleCurrentNalBytesRemaining -= readToOutput(input, output, this.sampleCurrentNalBytesRemaining);
                }
            }
        } else {
            while (this.sampleBytesRead < size2) {
                readToOutput(input, output, size2 - this.sampleBytesRead);
            }
        }
        if ("A_VORBIS".equals(track.codecId)) {
            this.vorbisNumPageSamples.setPosition(0);
            output.sampleData(this.vorbisNumPageSamples, 4);
            this.sampleBytesWritten += 4;
        }
    }

    private void writeSubripSample(Track track) {
        setSubripSampleEndTimecode(this.subripSample.data, this.blockDurationUs);
        track.output.sampleData(this.subripSample, this.subripSample.limit());
        this.sampleBytesWritten += this.subripSample.limit();
    }

    private static void setSubripSampleEndTimecode(byte[] subripSampleData, long timeUs) {
        byte[] timeCodeData;
        if (timeUs == -1) {
            timeCodeData = SUBRIP_TIMECODE_EMPTY;
        } else {
            int hours = (int) (timeUs / 3600000000L);
            long timeUs2 = timeUs - (((long) hours) * 3600000000L);
            int minutes = (int) (timeUs2 / 60000000);
            long timeUs3 = timeUs2 - ((long) (60000000 * minutes));
            int seconds = (int) (timeUs3 / 1000000);
            timeCodeData = String.format(Locale.US, "%02d:%02d:%02d,%03d", new Object[]{Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds), Integer.valueOf((int) ((timeUs3 - ((long) (1000000 * seconds))) / 1000))}).getBytes();
        }
        System.arraycopy(timeCodeData, 0, subripSampleData, 19, 12);
    }

    private void readToTarget(ExtractorInput input, byte[] target, int offset, int length) throws IOException, InterruptedException {
        int pendingStrippedBytes = Math.min(length, this.sampleStrippedBytes.bytesLeft());
        input.readFully(target, offset + pendingStrippedBytes, length - pendingStrippedBytes);
        if (pendingStrippedBytes > 0) {
            this.sampleStrippedBytes.readBytes(target, offset, pendingStrippedBytes);
        }
        this.sampleBytesRead += length;
    }

    private int readToOutput(ExtractorInput input, TrackOutput output, int length) throws IOException, InterruptedException {
        int bytesRead;
        int strippedBytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (strippedBytesLeft > 0) {
            bytesRead = Math.min(length, strippedBytesLeft);
            output.sampleData(this.sampleStrippedBytes, bytesRead);
        } else {
            bytesRead = output.sampleData(input, length, false);
        }
        this.sampleBytesRead += bytesRead;
        this.sampleBytesWritten += bytesRead;
        return bytesRead;
    }

    private SeekMap buildSeekMap() {
        if (this.segmentContentPosition == -1 || this.durationUs == -1 || this.cueTimesUs == null || this.cueTimesUs.size() == 0 || this.cueClusterPositions == null || this.cueClusterPositions.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return SeekMap.UNSEEKABLE;
        }
        int cuePointsSize = this.cueTimesUs.size();
        int[] sizes = new int[cuePointsSize];
        long[] offsets = new long[cuePointsSize];
        long[] durationsUs = new long[cuePointsSize];
        long[] timesUs = new long[cuePointsSize];
        for (int i = 0; i < cuePointsSize; i++) {
            timesUs[i] = this.cueTimesUs.get(i);
            offsets[i] = this.segmentContentPosition + this.cueClusterPositions.get(i);
        }
        for (int i2 = 0; i2 < cuePointsSize - 1; i2++) {
            sizes[i2] = (int) (offsets[i2 + 1] - offsets[i2]);
            durationsUs[i2] = timesUs[i2 + 1] - timesUs[i2];
        }
        sizes[cuePointsSize - 1] = (int) ((this.segmentContentPosition + this.segmentContentSize) - offsets[cuePointsSize - 1]);
        durationsUs[cuePointsSize - 1] = this.durationUs - timesUs[cuePointsSize - 1];
        this.cueTimesUs = null;
        this.cueClusterPositions = null;
        return new ChunkIndex(sizes, offsets, durationsUs, timesUs);
    }

    private boolean maybeSeekForCues(PositionHolder seekPosition, long currentPosition) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = currentPosition;
            seekPosition.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        } else if (!this.sentSeekMap || this.seekPositionAfterBuildingCues == -1) {
            return false;
        } else {
            seekPosition.position = this.seekPositionAfterBuildingCues;
            this.seekPositionAfterBuildingCues = -1;
            return true;
        }
    }

    private long scaleTimecodeToUs(long unscaledTimecode) throws ParserException {
        if (this.timecodeScale == -1) {
            throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
        }
        return Util.scaleLargeTimestamp(unscaledTimecode, this.timecodeScale, 1000);
    }

    private static boolean isCodecSupported(String codecId) {
        if ("V_VP8".equals(codecId) || "V_VP9".equals(codecId) || "V_MPEG2".equals(codecId) || "V_MPEG4/ISO/SP".equals(codecId) || "V_MPEG4/ISO/ASP".equals(codecId) || "V_MPEG4/ISO/AP".equals(codecId) || "V_MPEG4/ISO/AVC".equals(codecId) || "V_MPEGH/ISO/HEVC".equals(codecId) || "V_MS/VFW/FOURCC".equals(codecId) || "A_OPUS".equals(codecId) || "A_VORBIS".equals(codecId) || "A_AAC".equals(codecId) || "A_MPEG/L3".equals(codecId) || "A_AC3".equals(codecId) || "A_EAC3".equals(codecId) || "A_TRUEHD".equals(codecId) || "A_DTS".equals(codecId) || "A_DTS/EXPRESS".equals(codecId) || "A_DTS/LOSSLESS".equals(codecId) || "A_FLAC".equals(codecId) || "A_MS/ACM".equals(codecId) || "A_PCM/INT/LIT".equals(codecId) || "S_TEXT/UTF8".equals(codecId) || "S_VOBSUB".equals(codecId) || "S_HDMV/PGS".equals(codecId)) {
            return true;
        }
        return false;
    }

    private static int[] ensureArrayCapacity(int[] array, int length) {
        if (array == null) {
            return new int[length];
        }
        return array.length < length ? new int[Math.max(array.length * 2, length)] : array;
    }
}
