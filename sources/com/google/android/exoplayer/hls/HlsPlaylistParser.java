package com.google.android.exoplayer.hls;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.hls.HlsMediaPlaylist.Segment;
import com.google.android.exoplayer.upstream.UriLoadable.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public final class HlsPlaylistParser implements Parser<HlsPlaylist> {
    private static final Pattern BANDWIDTH_ATTR_REGEX = Pattern.compile("BANDWIDTH=(\\d+)\\b");
    private static final Pattern BYTERANGE_REGEX = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern CODECS_ATTR_REGEX = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern INSTREAM_ID_ATTR_REGEX = Pattern.compile("INSTREAM-ID=\"(.+?)\"");
    private static final Pattern IV_ATTR_REGEX = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern LANGUAGE_ATTR_REGEX = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern MEDIA_DURATION_REGEX = Pattern.compile("#EXTINF:([\\d.]+)\\b");
    private static final Pattern MEDIA_SEQUENCE_REGEX = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern METHOD_ATTR_REGEX = Pattern.compile("METHOD=(NONE|AES-128)");
    private static final Pattern NAME_ATTR_REGEX = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern RESOLUTION_ATTR_REGEX = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern TARGET_DURATION_REGEX = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern TYPE_ATTR_REGEX = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern URI_ATTR_REGEX = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern VERSION_REGEX = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");

    private static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> extraLines2, BufferedReader reader2) {
            this.extraLines = extraLines2;
            this.reader = reader2;
        }

        public boolean hasNext() throws IOException {
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = (String) this.extraLines.poll();
                return true;
            }
            do {
                String readLine = this.reader.readLine();
                this.next = readLine;
                if (readLine == null) {
                    return false;
                }
                this.next = this.next.trim();
            } while (this.next.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (!hasNext()) {
                return null;
            }
            String result = this.next;
            this.next = null;
            return result;
        }
    }

    public HlsPlaylist parse(String connectionUrl, InputStream inputStream) throws IOException, ParserException {
        String line;
        HlsPlaylist parseMasterPlaylist;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Queue<String> extraLines = new LinkedList<>();
        while (true) {
            try {
                String line2 = reader.readLine();
                if (line2 != null) {
                    line = line2.trim();
                    if (!line.isEmpty()) {
                        if (line.startsWith("#EXT-X-STREAM-INF")) {
                            extraLines.add(line);
                            parseMasterPlaylist = parseMasterPlaylist(new LineIterator(extraLines, reader), connectionUrl);
                            reader.close();
                            break;
                        } else if (line.startsWith("#EXT-X-TARGETDURATION") || line.startsWith("#EXT-X-MEDIA-SEQUENCE") || line.startsWith("#EXTINF") || line.startsWith("#EXT-X-KEY") || line.startsWith("#EXT-X-BYTERANGE") || line.equals("#EXT-X-DISCONTINUITY") || line.equals("#EXT-X-DISCONTINUITY-SEQUENCE") || line.equals("#EXT-X-ENDLIST")) {
                            extraLines.add(line);
                            parseMasterPlaylist = parseMediaPlaylist(new LineIterator(extraLines, reader), connectionUrl);
                        } else {
                            extraLines.add(line);
                        }
                    }
                } else {
                    reader.close();
                    throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                }
            } finally {
                reader.close();
            }
        }
        extraLines.add(line);
        parseMasterPlaylist = parseMediaPlaylist(new LineIterator(extraLines, reader), connectionUrl);
        return parseMasterPlaylist;
    }

    private static HlsMasterPlaylist parseMasterPlaylist(LineIterator iterator, String baseUri) throws IOException {
        String name;
        ArrayList<Variant> variants = new ArrayList<>();
        ArrayList<Variant> audios = new ArrayList<>();
        ArrayList<Variant> subtitles = new ArrayList<>();
        int bitrate = 0;
        String codecs = null;
        int width = -1;
        int height = -1;
        String muxedAudioLanguage = null;
        String muxedCaptionLanguage = null;
        boolean expectingStreamInfUrl = false;
        String name2 = null;
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.startsWith("#EXT-X-MEDIA")) {
                String type = HlsParserUtil.parseStringAttr(line, TYPE_ATTR_REGEX, "TYPE");
                if ("CLOSED-CAPTIONS".equals(type)) {
                    if ("CC1".equals(HlsParserUtil.parseStringAttr(line, INSTREAM_ID_ATTR_REGEX, "INSTREAM-ID"))) {
                        muxedCaptionLanguage = HlsParserUtil.parseOptionalStringAttr(line, LANGUAGE_ATTR_REGEX);
                    }
                } else if ("SUBTITLES".equals(type)) {
                    String subtitleName = HlsParserUtil.parseStringAttr(line, NAME_ATTR_REGEX, "NAME");
                    String uri = HlsParserUtil.parseStringAttr(line, URI_ATTR_REGEX, "URI");
                    String language = HlsParserUtil.parseOptionalStringAttr(line, LANGUAGE_ATTR_REGEX);
                    subtitles.add(new Variant(uri, new Format(subtitleName, "application/x-mpegURL", -1, -1, -1.0f, -1, -1, -1, language, codecs)));
                } else if ("AUDIO".equals(type)) {
                    String language2 = HlsParserUtil.parseOptionalStringAttr(line, LANGUAGE_ATTR_REGEX);
                    String uri2 = HlsParserUtil.parseOptionalStringAttr(line, URI_ATTR_REGEX);
                    if (uri2 != null) {
                        Format format = new Format(HlsParserUtil.parseStringAttr(line, NAME_ATTR_REGEX, "NAME"), "application/x-mpegURL", -1, -1, -1.0f, -1, -1, -1, language2, codecs);
                        audios.add(new Variant(uri2, format));
                    } else {
                        muxedAudioLanguage = language2;
                    }
                }
            } else {
                if (line.startsWith("#EXT-X-STREAM-INF")) {
                    bitrate = HlsParserUtil.parseIntAttr(line, BANDWIDTH_ATTR_REGEX, "BANDWIDTH");
                    codecs = HlsParserUtil.parseOptionalStringAttr(line, CODECS_ATTR_REGEX);
                    String name3 = HlsParserUtil.parseOptionalStringAttr(line, NAME_ATTR_REGEX);
                    String resolutionString = HlsParserUtil.parseOptionalStringAttr(line, RESOLUTION_ATTR_REGEX);
                    if (resolutionString != null) {
                        String[] widthAndHeight = resolutionString.split("x");
                        width = Integer.parseInt(widthAndHeight[0]);
                        if (width <= 0) {
                            width = -1;
                        }
                        height = Integer.parseInt(widthAndHeight[1]);
                        if (height <= 0) {
                            height = -1;
                        }
                    } else {
                        width = -1;
                        height = -1;
                    }
                    expectingStreamInfUrl = true;
                    name2 = name3;
                } else {
                    if (!line.startsWith("#") && expectingStreamInfUrl) {
                        if (name2 == null) {
                            name = Integer.toString(variants.size());
                        } else {
                            name = name2;
                        }
                        Format format2 = new Format(name, "application/x-mpegURL", width, height, -1.0f, -1, -1, bitrate, null, codecs);
                        variants.add(new Variant(line, format2));
                        bitrate = 0;
                        codecs = null;
                        width = -1;
                        height = -1;
                        expectingStreamInfUrl = false;
                        name2 = null;
                    }
                }
            }
        }
        return new HlsMasterPlaylist(baseUri, variants, audios, subtitles, muxedAudioLanguage, muxedCaptionLanguage);
    }

    private static HlsMediaPlaylist parseMediaPlaylist(LineIterator iterator, String baseUri) throws IOException {
        String segmentEncryptionIV;
        int mediaSequence = 0;
        int targetDurationSecs = 0;
        int version = 1;
        boolean live = true;
        ArrayList arrayList = new ArrayList();
        double segmentDurationSecs = 0.0d;
        int discontinuitySequenceNumber = 0;
        long segmentStartTimeUs = 0;
        long segmentByterangeOffset = 0;
        long segmentByterangeLength = -1;
        int segmentMediaSequence = 0;
        boolean isEncrypted = false;
        String encryptionKeyUri = null;
        String encryptionIV = null;
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.startsWith("#EXT-X-TARGETDURATION")) {
                targetDurationSecs = HlsParserUtil.parseIntAttr(line, TARGET_DURATION_REGEX, "#EXT-X-TARGETDURATION");
            } else if (line.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                mediaSequence = HlsParserUtil.parseIntAttr(line, MEDIA_SEQUENCE_REGEX, "#EXT-X-MEDIA-SEQUENCE");
                segmentMediaSequence = mediaSequence;
            } else if (line.startsWith("#EXT-X-VERSION")) {
                version = HlsParserUtil.parseIntAttr(line, VERSION_REGEX, "#EXT-X-VERSION");
            } else if (line.startsWith("#EXTINF")) {
                segmentDurationSecs = HlsParserUtil.parseDoubleAttr(line, MEDIA_DURATION_REGEX, "#EXTINF");
            } else if (line.startsWith("#EXT-X-KEY")) {
                isEncrypted = "AES-128".equals(HlsParserUtil.parseStringAttr(line, METHOD_ATTR_REGEX, "METHOD"));
                if (isEncrypted) {
                    encryptionKeyUri = HlsParserUtil.parseStringAttr(line, URI_ATTR_REGEX, "URI");
                    encryptionIV = HlsParserUtil.parseOptionalStringAttr(line, IV_ATTR_REGEX);
                } else {
                    encryptionKeyUri = null;
                    encryptionIV = null;
                }
            } else if (line.startsWith("#EXT-X-BYTERANGE")) {
                String[] splitByteRange = HlsParserUtil.parseStringAttr(line, BYTERANGE_REGEX, "#EXT-X-BYTERANGE").split("@");
                segmentByterangeLength = Long.parseLong(splitByteRange[0]);
                if (splitByteRange.length > 1) {
                    segmentByterangeOffset = Long.parseLong(splitByteRange[1]);
                }
            } else if (line.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                discontinuitySequenceNumber = Integer.parseInt(line.substring(line.indexOf(58) + 1));
            } else if (line.equals("#EXT-X-DISCONTINUITY")) {
                discontinuitySequenceNumber++;
            } else if (!line.startsWith("#")) {
                if (!isEncrypted) {
                    segmentEncryptionIV = null;
                } else if (encryptionIV != null) {
                    segmentEncryptionIV = encryptionIV;
                } else {
                    segmentEncryptionIV = Integer.toHexString(segmentMediaSequence);
                }
                segmentMediaSequence++;
                if (segmentByterangeLength == -1) {
                    segmentByterangeOffset = 0;
                }
                arrayList.add(new Segment(line, segmentDurationSecs, discontinuitySequenceNumber, segmentStartTimeUs, isEncrypted, encryptionKeyUri, segmentEncryptionIV, segmentByterangeOffset, segmentByterangeLength));
                segmentStartTimeUs += (long) (1000000.0d * segmentDurationSecs);
                segmentDurationSecs = 0.0d;
                if (segmentByterangeLength != -1) {
                    segmentByterangeOffset += segmentByterangeLength;
                }
                segmentByterangeLength = -1;
            } else if (line.equals("#EXT-X-ENDLIST")) {
                live = false;
            }
        }
        return new HlsMediaPlaylist(baseUri, mediaSequence, targetDurationSecs, version, live, Collections.unmodifiableList(arrayList));
    }
}
