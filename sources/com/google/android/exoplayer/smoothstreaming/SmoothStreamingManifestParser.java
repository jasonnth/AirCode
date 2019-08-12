package com.google.android.exoplayer.smoothstreaming;

import android.util.Base64;
import android.util.Pair;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifest.ProtectionElement;
import com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifest.StreamElement;
import com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifest.TrackElement;
import com.google.android.exoplayer.upstream.UriLoadable.Parser;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class SmoothStreamingManifestParser implements Parser<SmoothStreamingManifest> {
    private final XmlPullParserFactory xmlParserFactory;

    private static abstract class ElementParser {
        private final String baseUri;
        private final List<Pair<String, Object>> normalizedAttributes = new LinkedList();
        private final ElementParser parent;
        private final String tag;

        /* access modifiers changed from: protected */
        public abstract Object build();

        public ElementParser(ElementParser parent2, String baseUri2, String tag2) {
            this.parent = parent2;
            this.baseUri = baseUri2;
            this.tag = tag2;
        }

        public final Object parse(XmlPullParser xmlParser) throws XmlPullParserException, IOException, ParserException {
            boolean foundStartTag = false;
            int skippingElementDepth = 0;
            while (true) {
                switch (xmlParser.getEventType()) {
                    case 1:
                        return null;
                    case 2:
                        String tagName = xmlParser.getName();
                        if (!this.tag.equals(tagName)) {
                            if (foundStartTag) {
                                if (skippingElementDepth <= 0) {
                                    if (!handleChildInline(tagName)) {
                                        ElementParser childElementParser = newChildParser(this, tagName, this.baseUri);
                                        if (childElementParser != null) {
                                            addChild(childElementParser.parse(xmlParser));
                                            break;
                                        } else {
                                            skippingElementDepth = 1;
                                            break;
                                        }
                                    } else {
                                        parseStartTag(xmlParser);
                                        break;
                                    }
                                } else {
                                    skippingElementDepth++;
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            foundStartTag = true;
                            parseStartTag(xmlParser);
                            break;
                        }
                    case 3:
                        if (foundStartTag) {
                            if (skippingElementDepth <= 0) {
                                String tagName2 = xmlParser.getName();
                                parseEndTag(xmlParser);
                                if (handleChildInline(tagName2)) {
                                    break;
                                } else {
                                    return build();
                                }
                            } else {
                                skippingElementDepth--;
                                break;
                            }
                        } else {
                            continue;
                        }
                    case 4:
                        if (foundStartTag && skippingElementDepth == 0) {
                            parseText(xmlParser);
                            break;
                        }
                }
                xmlParser.next();
            }
        }

        private ElementParser newChildParser(ElementParser parent2, String name, String baseUri2) {
            if ("QualityLevel".equals(name)) {
                return new TrackElementParser(parent2, baseUri2);
            }
            if ("Protection".equals(name)) {
                return new ProtectionElementParser(parent2, baseUri2);
            }
            if ("StreamIndex".equals(name)) {
                return new StreamElementParser(parent2, baseUri2);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public final void putNormalizedAttribute(String key, Object value) {
            this.normalizedAttributes.add(Pair.create(key, value));
        }

        /* access modifiers changed from: protected */
        public final Object getNormalizedAttribute(String key) {
            for (int i = 0; i < this.normalizedAttributes.size(); i++) {
                Pair<String, Object> pair = (Pair) this.normalizedAttributes.get(i);
                if (((String) pair.first).equals(key)) {
                    return pair.second;
                }
            }
            if (this.parent == null) {
                return null;
            }
            return this.parent.getNormalizedAttribute(key);
        }

        /* access modifiers changed from: protected */
        public boolean handleChildInline(String tagName) {
            return false;
        }

        /* access modifiers changed from: protected */
        public void parseStartTag(XmlPullParser xmlParser) throws ParserException {
        }

        /* access modifiers changed from: protected */
        public void parseText(XmlPullParser xmlParser) throws ParserException {
        }

        /* access modifiers changed from: protected */
        public void parseEndTag(XmlPullParser xmlParser) throws ParserException {
        }

        /* access modifiers changed from: protected */
        public void addChild(Object parsedChild) {
        }

        /* access modifiers changed from: protected */
        public final String parseRequiredString(XmlPullParser parser, String key) throws MissingFieldException {
            String value = parser.getAttributeValue(null, key);
            if (value != null) {
                return value;
            }
            throw new MissingFieldException(key);
        }

        /* access modifiers changed from: protected */
        public final int parseInt(XmlPullParser parser, String key, int defaultValue) throws ParserException {
            String value = parser.getAttributeValue(null, key);
            if (value == null) {
                return defaultValue;
            }
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new ParserException((Throwable) e);
            }
        }

        /* access modifiers changed from: protected */
        public final int parseRequiredInt(XmlPullParser parser, String key) throws ParserException {
            String value = parser.getAttributeValue(null, key);
            if (value != null) {
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    throw new ParserException((Throwable) e);
                }
            } else {
                throw new MissingFieldException(key);
            }
        }

        /* access modifiers changed from: protected */
        public final long parseLong(XmlPullParser parser, String key, long defaultValue) throws ParserException {
            String value = parser.getAttributeValue(null, key);
            if (value == null) {
                return defaultValue;
            }
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ParserException((Throwable) e);
            }
        }

        /* access modifiers changed from: protected */
        public final long parseRequiredLong(XmlPullParser parser, String key) throws ParserException {
            String value = parser.getAttributeValue(null, key);
            if (value != null) {
                try {
                    return Long.parseLong(value);
                } catch (NumberFormatException e) {
                    throw new ParserException((Throwable) e);
                }
            } else {
                throw new MissingFieldException(key);
            }
        }

        /* access modifiers changed from: protected */
        public final boolean parseBoolean(XmlPullParser parser, String key, boolean defaultValue) {
            String value = parser.getAttributeValue(null, key);
            if (value != null) {
                return Boolean.parseBoolean(value);
            }
            return defaultValue;
        }
    }

    public static class MissingFieldException extends ParserException {
        public MissingFieldException(String fieldName) {
            super("Missing required field: " + fieldName);
        }
    }

    private static class ProtectionElementParser extends ElementParser {
        private boolean inProtectionHeader;
        private byte[] initData;
        private UUID uuid;

        public ProtectionElementParser(ElementParser parent, String baseUri) {
            super(parent, baseUri, "Protection");
        }

        public boolean handleChildInline(String tag) {
            return "ProtectionHeader".equals(tag);
        }

        public void parseStartTag(XmlPullParser parser) {
            if ("ProtectionHeader".equals(parser.getName())) {
                this.inProtectionHeader = true;
                this.uuid = UUID.fromString(stripCurlyBraces(parser.getAttributeValue(null, "SystemID")));
            }
        }

        public void parseText(XmlPullParser parser) {
            if (this.inProtectionHeader) {
                this.initData = Base64.decode(parser.getText(), 0);
            }
        }

        public void parseEndTag(XmlPullParser parser) {
            if ("ProtectionHeader".equals(parser.getName())) {
                this.inProtectionHeader = false;
            }
        }

        public Object build() {
            return new ProtectionElement(this.uuid, PsshAtomUtil.buildPsshAtom(this.uuid, this.initData));
        }

        private static String stripCurlyBraces(String uuidString) {
            if (uuidString.charAt(0) == '{' && uuidString.charAt(uuidString.length() - 1) == '}') {
                return uuidString.substring(1, uuidString.length() - 1);
            }
            return uuidString;
        }
    }

    private static class SmoothStreamMediaParser extends ElementParser {
        private long duration;
        private long dvrWindowLength;
        private boolean isLive;
        private int lookAheadCount = -1;
        private int majorVersion;
        private int minorVersion;
        private ProtectionElement protectionElement = null;
        private List<StreamElement> streamElements = new LinkedList();
        private long timescale;

        public SmoothStreamMediaParser(ElementParser parent, String baseUri) {
            super(parent, baseUri, "SmoothStreamingMedia");
        }

        public void parseStartTag(XmlPullParser parser) throws ParserException {
            this.majorVersion = parseRequiredInt(parser, "MajorVersion");
            this.minorVersion = parseRequiredInt(parser, "MinorVersion");
            this.timescale = parseLong(parser, "TimeScale", 10000000);
            this.duration = parseRequiredLong(parser, "Duration");
            this.dvrWindowLength = parseLong(parser, "DVRWindowLength", 0);
            this.lookAheadCount = parseInt(parser, "LookaheadCount", -1);
            this.isLive = parseBoolean(parser, "IsLive", false);
            putNormalizedAttribute("TimeScale", Long.valueOf(this.timescale));
        }

        public void addChild(Object child) {
            if (child instanceof StreamElement) {
                this.streamElements.add((StreamElement) child);
            } else if (child instanceof ProtectionElement) {
                Assertions.checkState(this.protectionElement == null);
                this.protectionElement = (ProtectionElement) child;
            }
        }

        public Object build() {
            StreamElement[] streamElementArray = new StreamElement[this.streamElements.size()];
            this.streamElements.toArray(streamElementArray);
            return new SmoothStreamingManifest(this.majorVersion, this.minorVersion, this.timescale, this.duration, this.dvrWindowLength, this.lookAheadCount, this.isLive, this.protectionElement, streamElementArray);
        }
    }

    private static class StreamElementParser extends ElementParser {
        private final String baseUri;
        private int displayHeight;
        private int displayWidth;
        private String language;
        private long lastChunkDuration;
        private int maxHeight;
        private int maxWidth;
        private String name;
        private int qualityLevels;
        private ArrayList<Long> startTimes;
        private String subType;
        private long timescale;
        private final List<TrackElement> tracks = new LinkedList();
        private int type;
        private String url;

        public StreamElementParser(ElementParser parent, String baseUri2) {
            super(parent, baseUri2, "StreamIndex");
            this.baseUri = baseUri2;
        }

        public boolean handleChildInline(String tag) {
            return "c".equals(tag);
        }

        public void parseStartTag(XmlPullParser parser) throws ParserException {
            if ("c".equals(parser.getName())) {
                parseStreamFragmentStartTag(parser);
            } else {
                parseStreamElementStartTag(parser);
            }
        }

        private void parseStreamFragmentStartTag(XmlPullParser parser) throws ParserException {
            int chunkIndex = this.startTimes.size();
            long startTime = parseLong(parser, "t", -1);
            if (startTime == -1) {
                if (chunkIndex == 0) {
                    startTime = 0;
                } else if (this.lastChunkDuration != -1) {
                    startTime = ((Long) this.startTimes.get(chunkIndex - 1)).longValue() + this.lastChunkDuration;
                } else {
                    throw new ParserException("Unable to infer start time");
                }
            }
            int chunkIndex2 = chunkIndex + 1;
            this.startTimes.add(Long.valueOf(startTime));
            this.lastChunkDuration = parseLong(parser, "d", -1);
            long repeatCount = parseLong(parser, "r", 1);
            if (repeatCount <= 1 || this.lastChunkDuration != -1) {
                for (int i = 1; ((long) i) < repeatCount; i++) {
                    chunkIndex2++;
                    this.startTimes.add(Long.valueOf((this.lastChunkDuration * ((long) i)) + startTime));
                }
                return;
            }
            throw new ParserException("Repeated chunk with unspecified duration");
        }

        private void parseStreamElementStartTag(XmlPullParser parser) throws ParserException {
            this.type = parseType(parser);
            putNormalizedAttribute("Type", Integer.valueOf(this.type));
            if (this.type == 2) {
                this.subType = parseRequiredString(parser, "Subtype");
            } else {
                this.subType = parser.getAttributeValue(null, "Subtype");
            }
            this.name = parser.getAttributeValue(null, "Name");
            this.qualityLevels = parseInt(parser, "QualityLevels", -1);
            this.url = parseRequiredString(parser, "Url");
            this.maxWidth = parseInt(parser, "MaxWidth", -1);
            this.maxHeight = parseInt(parser, "MaxHeight", -1);
            this.displayWidth = parseInt(parser, "DisplayWidth", -1);
            this.displayHeight = parseInt(parser, "DisplayHeight", -1);
            this.language = parser.getAttributeValue(null, "Language");
            putNormalizedAttribute("Language", this.language);
            this.timescale = (long) parseInt(parser, "TimeScale", -1);
            if (this.timescale == -1) {
                this.timescale = ((Long) getNormalizedAttribute("TimeScale")).longValue();
            }
            this.startTimes = new ArrayList<>();
        }

        private int parseType(XmlPullParser parser) throws ParserException {
            String value = parser.getAttributeValue(null, "Type");
            if (value == null) {
                throw new MissingFieldException("Type");
            } else if ("audio".equalsIgnoreCase(value)) {
                return 0;
            } else {
                if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO.equalsIgnoreCase(value)) {
                    return 1;
                }
                if ("text".equalsIgnoreCase(value)) {
                    return 2;
                }
                throw new ParserException("Invalid key value[" + value + "]");
            }
        }

        public void addChild(Object child) {
            if (child instanceof TrackElement) {
                this.tracks.add((TrackElement) child);
            }
        }

        public Object build() {
            TrackElement[] trackElements = new TrackElement[this.tracks.size()];
            this.tracks.toArray(trackElements);
            return new StreamElement(this.baseUri, this.url, this.type, this.subType, this.timescale, this.name, this.qualityLevels, this.maxWidth, this.maxHeight, this.displayWidth, this.displayHeight, this.language, trackElements, this.startTimes, this.lastChunkDuration);
        }
    }

    private static class TrackElementParser extends ElementParser {
        private int bitrate;
        private int channels;
        private final List<byte[]> csd = new LinkedList();
        private int index;
        private String language;
        private int maxHeight;
        private int maxWidth;
        private String mimeType;
        private int samplingRate;

        public TrackElementParser(ElementParser parent, String baseUri) {
            super(parent, baseUri, "QualityLevel");
        }

        public void parseStartTag(XmlPullParser parser) throws ParserException {
            int type = ((Integer) getNormalizedAttribute("Type")).intValue();
            this.index = parseInt(parser, "Index", -1);
            this.bitrate = parseRequiredInt(parser, "Bitrate");
            this.language = (String) getNormalizedAttribute("Language");
            if (type == 1) {
                this.maxHeight = parseRequiredInt(parser, "MaxHeight");
                this.maxWidth = parseRequiredInt(parser, "MaxWidth");
                this.mimeType = fourCCToMimeType(parseRequiredString(parser, "FourCC"));
            } else {
                this.maxHeight = -1;
                this.maxWidth = -1;
                String fourCC = parser.getAttributeValue(null, "FourCC");
                String str = fourCC != null ? fourCCToMimeType(fourCC) : type == 0 ? "audio/mp4a-latm" : null;
                this.mimeType = str;
            }
            if (type == 0) {
                this.samplingRate = parseRequiredInt(parser, "SamplingRate");
                this.channels = parseRequiredInt(parser, "Channels");
            } else {
                this.samplingRate = -1;
                this.channels = -1;
            }
            String value = parser.getAttributeValue(null, "CodecPrivateData");
            if (value != null && value.length() > 0) {
                byte[] codecPrivateData = Util.getBytesFromHexString(value);
                byte[][] split = CodecSpecificDataUtil.splitNalUnits(codecPrivateData);
                if (split == null) {
                    this.csd.add(codecPrivateData);
                    return;
                }
                for (byte[] add : split) {
                    this.csd.add(add);
                }
            }
        }

        public Object build() {
            byte[][] csdArray = null;
            if (!this.csd.isEmpty()) {
                csdArray = new byte[this.csd.size()][];
                this.csd.toArray(csdArray);
            }
            return new TrackElement(this.index, this.bitrate, this.mimeType, csdArray, this.maxWidth, this.maxHeight, this.samplingRate, this.channels, this.language);
        }

        private static String fourCCToMimeType(String fourCC) {
            if (fourCC.equalsIgnoreCase("H264") || fourCC.equalsIgnoreCase("X264") || fourCC.equalsIgnoreCase("AVC1") || fourCC.equalsIgnoreCase("DAVC")) {
                return "video/avc";
            }
            if (fourCC.equalsIgnoreCase("AAC") || fourCC.equalsIgnoreCase("AACL") || fourCC.equalsIgnoreCase("AACH") || fourCC.equalsIgnoreCase("AACP")) {
                return "audio/mp4a-latm";
            }
            if (fourCC.equalsIgnoreCase("TTML")) {
                return "application/ttml+xml";
            }
            if (fourCC.equalsIgnoreCase("ac-3") || fourCC.equalsIgnoreCase("dac3")) {
                return "audio/ac3";
            }
            if (fourCC.equalsIgnoreCase("ec-3") || fourCC.equalsIgnoreCase("dec3")) {
                return "audio/eac3";
            }
            if (fourCC.equalsIgnoreCase("dtsc")) {
                return "audio/vnd.dts";
            }
            if (fourCC.equalsIgnoreCase("dtsh") || fourCC.equalsIgnoreCase("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (fourCC.equalsIgnoreCase("dtse")) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (fourCC.equalsIgnoreCase("opus")) {
                return "audio/opus";
            }
            return null;
        }
    }

    public SmoothStreamingManifestParser() {
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    public SmoothStreamingManifest parse(String connectionUrl, InputStream inputStream) throws IOException, ParserException {
        try {
            XmlPullParser xmlParser = this.xmlParserFactory.newPullParser();
            xmlParser.setInput(inputStream, null);
            return (SmoothStreamingManifest) new SmoothStreamMediaParser(null, connectionUrl).parse(xmlParser);
        } catch (XmlPullParserException e) {
            throw new ParserException((Throwable) e);
        }
    }
}
