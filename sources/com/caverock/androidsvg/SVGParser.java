package com.caverock.androidsvg;

import android.graphics.Matrix;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.core.notifications.PushNotificationConstants;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;
import com.caverock.androidsvg.CSSParser.MediaType;
import com.caverock.androidsvg.PreserveAspectRatio.Alignment;
import com.caverock.androidsvg.PreserveAspectRatio.Scale;
import com.caverock.androidsvg.SVG.Style.FillRule;
import com.caverock.androidsvg.SVG.Style.FontStyle;
import com.caverock.androidsvg.SVG.Style.LineCaps;
import com.caverock.androidsvg.SVG.Style.LineJoin;
import com.caverock.androidsvg.SVG.Style.TextAnchor;
import com.caverock.androidsvg.SVG.Style.TextDecoration;
import com.caverock.androidsvg.SVG.Style.TextDirection;
import com.caverock.androidsvg.SVG.Style.VectorEffect;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.soloader.MinElf;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.crypto.tls.CipherSuite;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;

public class SVGParser extends DefaultHandler2 {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr;
    private static HashMap<String, Alignment> aspectRatioKeywords = new HashMap<>();
    private static HashMap<String, Integer> colourKeywords = new HashMap<>();
    private static HashMap<String, Length> fontSizeKeywords = new HashMap<>(9);
    private static HashMap<String, FontStyle> fontStyleKeywords = new HashMap<>(3);
    private static HashMap<String, Integer> fontWeightKeywords = new HashMap<>(13);
    protected static HashSet<String> supportedFeatures = new HashSet<>();
    private SvgContainer currentElement = null;
    private int ignoreDepth;
    private boolean ignoring = false;
    private boolean inMetadataElement = false;
    private boolean inStyleElement = false;
    private StringBuilder metadataElementContents = null;
    private String metadataTag = null;
    private StringBuilder styleElementContents = null;
    private HashSet<String> supportedFormats = null;
    private SVG svgDocument = null;

    private enum SVGAttr {
        CLASS,
        clip,
        clip_path,
        clipPathUnits,
        clip_rule,
        color,
        cx,
        cy,
        direction,
        dx,
        dy,
        fx,
        fy,
        d,
        display,
        fill,
        fill_rule,
        fill_opacity,
        font,
        font_family,
        font_size,
        font_weight,
        font_style,
        gradientTransform,
        gradientUnits,
        height,
        href,
        id,
        marker,
        marker_start,
        marker_mid,
        marker_end,
        markerHeight,
        markerUnits,
        markerWidth,
        mask,
        maskContentUnits,
        maskUnits,
        media,
        offset,
        opacity,
        orient,
        overflow,
        pathLength,
        patternContentUnits,
        patternTransform,
        patternUnits,
        points,
        preserveAspectRatio,
        r,
        refX,
        refY,
        requiredFeatures,
        requiredExtensions,
        requiredFormats,
        requiredFonts,
        rx,
        ry,
        solid_color,
        solid_opacity,
        spreadMethod,
        startOffset,
        stop_color,
        stop_opacity,
        stroke,
        stroke_dasharray,
        stroke_dashoffset,
        stroke_linecap,
        stroke_linejoin,
        stroke_miterlimit,
        stroke_opacity,
        stroke_width,
        style,
        systemLanguage,
        text_anchor,
        text_decoration,
        transform,
        type,
        vector_effect,
        version,
        viewBox,
        width,
        x,
        y,
        x1,
        y1,
        x2,
        y2,
        viewport_fill,
        viewport_fill_opacity,
        visibility,
        UNSUPPORTED;

        public static SVGAttr fromString(String str) {
            if (str.equals("class")) {
                return CLASS;
            }
            if (str.indexOf(95) != -1) {
                return UNSUPPORTED;
            }
            try {
                return valueOf(str.replace('-', '_'));
            } catch (IllegalArgumentException e) {
                return UNSUPPORTED;
            }
        }
    }

    protected static class TextScanner {
        protected String input;
        protected int position = 0;

        public TextScanner(String input2) {
            this.input = input2.trim();
        }

        public boolean empty() {
            return this.position == this.input.length();
        }

        /* access modifiers changed from: protected */
        public boolean isWhitespace(int c) {
            return c == 32 || c == 10 || c == 13 || c == 9;
        }

        public void skipWhitespace() {
            while (this.position < this.input.length() && isWhitespace(this.input.charAt(this.position))) {
                this.position++;
            }
        }

        /* access modifiers changed from: protected */
        public boolean isEOL(int c) {
            return c == 10 || c == 13;
        }

        public boolean skipCommaWhitespace() {
            skipWhitespace();
            if (this.position == this.input.length() || this.input.charAt(this.position) != ',') {
                return false;
            }
            this.position++;
            skipWhitespace();
            return true;
        }

        public Float nextFloat() {
            int floatEnd = scanForFloat();
            if (floatEnd == this.position) {
                return null;
            }
            Float valueOf = Float.valueOf(Float.parseFloat(this.input.substring(this.position, floatEnd)));
            this.position = floatEnd;
            return valueOf;
        }

        public Float possibleNextFloat() {
            int start = this.position;
            skipCommaWhitespace();
            Float result = nextFloat();
            if (result != null) {
                return result;
            }
            this.position = start;
            return null;
        }

        public Float checkedNextFloat(Object lastRead) {
            if (lastRead == null) {
                return null;
            }
            skipCommaWhitespace();
            return nextFloat();
        }

        public Integer nextChar() {
            if (this.position == this.input.length()) {
                return null;
            }
            String str = this.input;
            int i = this.position;
            this.position = i + 1;
            return Integer.valueOf(str.charAt(i));
        }

        public Length nextLength() {
            Float scalar = nextFloat();
            if (scalar == null) {
                return null;
            }
            Unit unit = nextUnit();
            if (unit == null) {
                return new Length(scalar.floatValue(), Unit.px);
            }
            return new Length(scalar.floatValue(), unit);
        }

        public Boolean nextFlag() {
            if (this.position == this.input.length()) {
                return null;
            }
            char ch = this.input.charAt(this.position);
            if (ch != '0' && ch != '1') {
                return null;
            }
            this.position++;
            return Boolean.valueOf(ch == '1');
        }

        public Boolean checkedNextFlag(Object lastRead) {
            if (lastRead == null) {
                return null;
            }
            skipCommaWhitespace();
            return nextFlag();
        }

        public boolean consume(char ch) {
            boolean found = this.position < this.input.length() && this.input.charAt(this.position) == ch;
            if (found) {
                this.position++;
            }
            return found;
        }

        public boolean consume(String str) {
            int len = str.length();
            boolean found = this.position <= this.input.length() - len && this.input.substring(this.position, this.position + len).equals(str);
            if (found) {
                this.position += len;
            }
            return found;
        }

        /* access modifiers changed from: protected */
        public int advanceChar() {
            if (this.position == this.input.length()) {
                return -1;
            }
            this.position++;
            if (this.position < this.input.length()) {
                return this.input.charAt(this.position);
            }
            return -1;
        }

        public String nextToken() {
            return nextToken(' ');
        }

        public String nextToken(char terminator) {
            if (empty()) {
                return null;
            }
            int ch = this.input.charAt(this.position);
            if (isWhitespace(ch) || ch == terminator) {
                return null;
            }
            int start = this.position;
            int ch2 = advanceChar();
            while (ch2 != -1 && ch2 != terminator && !isWhitespace(ch2)) {
                ch2 = advanceChar();
            }
            return this.input.substring(start, this.position);
        }

        public String nextFunction() {
            if (empty()) {
                return null;
            }
            int start = this.position;
            int ch = this.input.charAt(this.position);
            while (true) {
                if ((ch < 97 || ch > 122) && (ch < 65 || ch > 90)) {
                    int end = this.position;
                } else {
                    ch = advanceChar();
                }
            }
            int end2 = this.position;
            while (isWhitespace(ch)) {
                ch = advanceChar();
            }
            if (ch == 40) {
                this.position++;
                return this.input.substring(start, end2);
            }
            this.position = start;
            return null;
        }

        private int scanForFloat() {
            int ch;
            if (empty()) {
                return this.position;
            }
            int lastValidPos = this.position;
            int start = this.position;
            int ch2 = this.input.charAt(this.position);
            if (ch2 == 45 || ch2 == 43) {
                ch2 = advanceChar();
            }
            if (Character.isDigit(ch)) {
                lastValidPos = this.position + 1;
                ch = advanceChar();
                while (Character.isDigit(ch)) {
                    lastValidPos = this.position + 1;
                    ch = advanceChar();
                }
            }
            if (ch == 46) {
                int lastValidPos2 = this.position + 1;
                int ch3 = advanceChar();
                while (Character.isDigit(ch)) {
                    lastValidPos2 = this.position + 1;
                    ch3 = advanceChar();
                }
            }
            if (ch == 101 || ch == 69) {
                int ch4 = advanceChar();
                if (ch4 == 45 || ch4 == 43) {
                    ch4 = advanceChar();
                }
                if (Character.isDigit(ch4)) {
                    int lastValidPos3 = this.position + 1;
                    int ch5 = advanceChar();
                    while (Character.isDigit(ch5)) {
                        lastValidPos3 = this.position + 1;
                        ch5 = advanceChar();
                    }
                }
            }
            this.position = start;
            return lastValidPos;
        }

        public String ahead() {
            int start = this.position;
            while (!empty() && !isWhitespace(this.input.charAt(this.position))) {
                this.position++;
            }
            String str = this.input.substring(start, this.position);
            this.position = start;
            return str;
        }

        public Unit nextUnit() {
            if (empty()) {
                return null;
            }
            if (this.input.charAt(this.position) == 37) {
                this.position++;
                return Unit.percent;
            } else if (this.position > this.input.length() - 2) {
                return null;
            } else {
                try {
                    Unit valueOf = Unit.valueOf(this.input.substring(this.position, this.position + 2).toLowerCase(Locale.US));
                    this.position += 2;
                    return valueOf;
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }
        }

        public boolean hasLetter() {
            if (this.position == this.input.length()) {
                return false;
            }
            char ch = this.input.charAt(this.position);
            if ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z')) {
                return false;
            }
            return true;
        }

        public String nextQuotedString() {
            if (empty()) {
                return null;
            }
            int start = this.position;
            int charAt = this.input.charAt(this.position);
            int endQuote = charAt;
            if (charAt != '\'' && charAt != '\"') {
                return null;
            }
            int ch = advanceChar();
            while (ch != -1 && ch != endQuote) {
                ch = advanceChar();
            }
            if (ch == -1) {
                this.position = start;
                return null;
            }
            this.position++;
            return this.input.substring(start + 1, this.position - 1);
        }

        public String restOfText() {
            if (empty()) {
                return null;
            }
            int start = this.position;
            this.position = this.input.length();
            return this.input.substring(start);
        }
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr() {
        int[] iArr = $SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr;
        if (iArr == null) {
            iArr = new int[SVGAttr.values().length];
            try {
                iArr[SVGAttr.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SVGAttr.UNSUPPORTED.ordinal()] = 92;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SVGAttr.clip.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[SVGAttr.clipPathUnits.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[SVGAttr.clip_path.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[SVGAttr.clip_rule.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[SVGAttr.color.ordinal()] = 6;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[SVGAttr.cx.ordinal()] = 7;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[SVGAttr.cy.ordinal()] = 8;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[SVGAttr.d.ordinal()] = 14;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[SVGAttr.direction.ordinal()] = 9;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[SVGAttr.display.ordinal()] = 15;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[SVGAttr.dx.ordinal()] = 10;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[SVGAttr.dy.ordinal()] = 11;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[SVGAttr.fill.ordinal()] = 16;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[SVGAttr.fill_opacity.ordinal()] = 18;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[SVGAttr.fill_rule.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[SVGAttr.font.ordinal()] = 19;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[SVGAttr.font_family.ordinal()] = 20;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[SVGAttr.font_size.ordinal()] = 21;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[SVGAttr.font_style.ordinal()] = 23;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[SVGAttr.font_weight.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[SVGAttr.fx.ordinal()] = 12;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[SVGAttr.fy.ordinal()] = 13;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[SVGAttr.gradientTransform.ordinal()] = 24;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[SVGAttr.gradientUnits.ordinal()] = 25;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[SVGAttr.height.ordinal()] = 26;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[SVGAttr.href.ordinal()] = 27;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[SVGAttr.id.ordinal()] = 28;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[SVGAttr.marker.ordinal()] = 29;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[SVGAttr.markerHeight.ordinal()] = 33;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[SVGAttr.markerUnits.ordinal()] = 34;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[SVGAttr.markerWidth.ordinal()] = 35;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[SVGAttr.marker_end.ordinal()] = 32;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[SVGAttr.marker_mid.ordinal()] = 31;
            } catch (NoSuchFieldError e35) {
            }
            try {
                iArr[SVGAttr.marker_start.ordinal()] = 30;
            } catch (NoSuchFieldError e36) {
            }
            try {
                iArr[SVGAttr.mask.ordinal()] = 36;
            } catch (NoSuchFieldError e37) {
            }
            try {
                iArr[SVGAttr.maskContentUnits.ordinal()] = 37;
            } catch (NoSuchFieldError e38) {
            }
            try {
                iArr[SVGAttr.maskUnits.ordinal()] = 38;
            } catch (NoSuchFieldError e39) {
            }
            try {
                iArr[SVGAttr.media.ordinal()] = 39;
            } catch (NoSuchFieldError e40) {
            }
            try {
                iArr[SVGAttr.offset.ordinal()] = 40;
            } catch (NoSuchFieldError e41) {
            }
            try {
                iArr[SVGAttr.opacity.ordinal()] = 41;
            } catch (NoSuchFieldError e42) {
            }
            try {
                iArr[SVGAttr.orient.ordinal()] = 42;
            } catch (NoSuchFieldError e43) {
            }
            try {
                iArr[SVGAttr.overflow.ordinal()] = 43;
            } catch (NoSuchFieldError e44) {
            }
            try {
                iArr[SVGAttr.pathLength.ordinal()] = 44;
            } catch (NoSuchFieldError e45) {
            }
            try {
                iArr[SVGAttr.patternContentUnits.ordinal()] = 45;
            } catch (NoSuchFieldError e46) {
            }
            try {
                iArr[SVGAttr.patternTransform.ordinal()] = 46;
            } catch (NoSuchFieldError e47) {
            }
            try {
                iArr[SVGAttr.patternUnits.ordinal()] = 47;
            } catch (NoSuchFieldError e48) {
            }
            try {
                iArr[SVGAttr.points.ordinal()] = 48;
            } catch (NoSuchFieldError e49) {
            }
            try {
                iArr[SVGAttr.preserveAspectRatio.ordinal()] = 49;
            } catch (NoSuchFieldError e50) {
            }
            try {
                iArr[SVGAttr.r.ordinal()] = 50;
            } catch (NoSuchFieldError e51) {
            }
            try {
                iArr[SVGAttr.refX.ordinal()] = 51;
            } catch (NoSuchFieldError e52) {
            }
            try {
                iArr[SVGAttr.refY.ordinal()] = 52;
            } catch (NoSuchFieldError e53) {
            }
            try {
                iArr[SVGAttr.requiredExtensions.ordinal()] = 54;
            } catch (NoSuchFieldError e54) {
            }
            try {
                iArr[SVGAttr.requiredFeatures.ordinal()] = 53;
            } catch (NoSuchFieldError e55) {
            }
            try {
                iArr[SVGAttr.requiredFonts.ordinal()] = 56;
            } catch (NoSuchFieldError e56) {
            }
            try {
                iArr[SVGAttr.requiredFormats.ordinal()] = 55;
            } catch (NoSuchFieldError e57) {
            }
            try {
                iArr[SVGAttr.rx.ordinal()] = 57;
            } catch (NoSuchFieldError e58) {
            }
            try {
                iArr[SVGAttr.ry.ordinal()] = 58;
            } catch (NoSuchFieldError e59) {
            }
            try {
                iArr[SVGAttr.solid_color.ordinal()] = 59;
            } catch (NoSuchFieldError e60) {
            }
            try {
                iArr[SVGAttr.solid_opacity.ordinal()] = 60;
            } catch (NoSuchFieldError e61) {
            }
            try {
                iArr[SVGAttr.spreadMethod.ordinal()] = 61;
            } catch (NoSuchFieldError e62) {
            }
            try {
                iArr[SVGAttr.startOffset.ordinal()] = 62;
            } catch (NoSuchFieldError e63) {
            }
            try {
                iArr[SVGAttr.stop_color.ordinal()] = 63;
            } catch (NoSuchFieldError e64) {
            }
            try {
                iArr[SVGAttr.stop_opacity.ordinal()] = 64;
            } catch (NoSuchFieldError e65) {
            }
            try {
                iArr[SVGAttr.stroke.ordinal()] = 65;
            } catch (NoSuchFieldError e66) {
            }
            try {
                iArr[SVGAttr.stroke_dasharray.ordinal()] = 66;
            } catch (NoSuchFieldError e67) {
            }
            try {
                iArr[SVGAttr.stroke_dashoffset.ordinal()] = 67;
            } catch (NoSuchFieldError e68) {
            }
            try {
                iArr[SVGAttr.stroke_linecap.ordinal()] = 68;
            } catch (NoSuchFieldError e69) {
            }
            try {
                iArr[SVGAttr.stroke_linejoin.ordinal()] = 69;
            } catch (NoSuchFieldError e70) {
            }
            try {
                iArr[SVGAttr.stroke_miterlimit.ordinal()] = 70;
            } catch (NoSuchFieldError e71) {
            }
            try {
                iArr[SVGAttr.stroke_opacity.ordinal()] = 71;
            } catch (NoSuchFieldError e72) {
            }
            try {
                iArr[SVGAttr.stroke_width.ordinal()] = 72;
            } catch (NoSuchFieldError e73) {
            }
            try {
                iArr[SVGAttr.style.ordinal()] = 73;
            } catch (NoSuchFieldError e74) {
            }
            try {
                iArr[SVGAttr.systemLanguage.ordinal()] = 74;
            } catch (NoSuchFieldError e75) {
            }
            try {
                iArr[SVGAttr.text_anchor.ordinal()] = 75;
            } catch (NoSuchFieldError e76) {
            }
            try {
                iArr[SVGAttr.text_decoration.ordinal()] = 76;
            } catch (NoSuchFieldError e77) {
            }
            try {
                iArr[SVGAttr.transform.ordinal()] = 77;
            } catch (NoSuchFieldError e78) {
            }
            try {
                iArr[SVGAttr.type.ordinal()] = 78;
            } catch (NoSuchFieldError e79) {
            }
            try {
                iArr[SVGAttr.vector_effect.ordinal()] = 79;
            } catch (NoSuchFieldError e80) {
            }
            try {
                iArr[SVGAttr.version.ordinal()] = 80;
            } catch (NoSuchFieldError e81) {
            }
            try {
                iArr[SVGAttr.viewBox.ordinal()] = 81;
            } catch (NoSuchFieldError e82) {
            }
            try {
                iArr[SVGAttr.viewport_fill.ordinal()] = 89;
            } catch (NoSuchFieldError e83) {
            }
            try {
                iArr[SVGAttr.viewport_fill_opacity.ordinal()] = 90;
            } catch (NoSuchFieldError e84) {
            }
            try {
                iArr[SVGAttr.visibility.ordinal()] = 91;
            } catch (NoSuchFieldError e85) {
            }
            try {
                iArr[SVGAttr.width.ordinal()] = 82;
            } catch (NoSuchFieldError e86) {
            }
            try {
                iArr[SVGAttr.x.ordinal()] = 83;
            } catch (NoSuchFieldError e87) {
            }
            try {
                iArr[SVGAttr.x1.ordinal()] = 85;
            } catch (NoSuchFieldError e88) {
            }
            try {
                iArr[SVGAttr.x2.ordinal()] = 87;
            } catch (NoSuchFieldError e89) {
            }
            try {
                iArr[SVGAttr.y.ordinal()] = 84;
            } catch (NoSuchFieldError e90) {
            }
            try {
                iArr[SVGAttr.y1.ordinal()] = 86;
            } catch (NoSuchFieldError e91) {
            }
            try {
                iArr[SVGAttr.y2.ordinal()] = 88;
            } catch (NoSuchFieldError e92) {
            }
            $SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr = iArr;
        }
        return iArr;
    }

    static {
        colourKeywords.put("aliceblue", Integer.valueOf(15792383));
        colourKeywords.put("antiquewhite", Integer.valueOf(16444375));
        colourKeywords.put("aqua", Integer.valueOf(MinElf.PN_XNUM));
        colourKeywords.put("aquamarine", Integer.valueOf(8388564));
        colourKeywords.put("azure", Integer.valueOf(15794175));
        colourKeywords.put("beige", Integer.valueOf(16119260));
        colourKeywords.put("bisque", Integer.valueOf(16770244));
        colourKeywords.put("black", Integer.valueOf(0));
        colourKeywords.put("blanchedalmond", Integer.valueOf(16772045));
        colourKeywords.put("blue", Integer.valueOf(255));
        colourKeywords.put("blueviolet", Integer.valueOf(9055202));
        colourKeywords.put("brown", Integer.valueOf(10824234));
        colourKeywords.put("burlywood", Integer.valueOf(14596231));
        colourKeywords.put("cadetblue", Integer.valueOf(6266528));
        colourKeywords.put("chartreuse", Integer.valueOf(8388352));
        colourKeywords.put("chocolate", Integer.valueOf(13789470));
        colourKeywords.put("coral", Integer.valueOf(16744272));
        colourKeywords.put("cornflowerblue", Integer.valueOf(6591981));
        colourKeywords.put("cornsilk", Integer.valueOf(16775388));
        colourKeywords.put("crimson", Integer.valueOf(14423100));
        colourKeywords.put("cyan", Integer.valueOf(MinElf.PN_XNUM));
        colourKeywords.put("darkblue", Integer.valueOf(CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA));
        colourKeywords.put("darkcyan", Integer.valueOf(35723));
        colourKeywords.put("darkgoldenrod", Integer.valueOf(12092939));
        colourKeywords.put("darkgray", Integer.valueOf(11119017));
        colourKeywords.put("darkgreen", Integer.valueOf(25600));
        colourKeywords.put("darkgrey", Integer.valueOf(11119017));
        colourKeywords.put("darkkhaki", Integer.valueOf(12433259));
        colourKeywords.put("darkmagenta", Integer.valueOf(9109643));
        colourKeywords.put("darkolivegreen", Integer.valueOf(5597999));
        colourKeywords.put("darkorange", Integer.valueOf(16747520));
        colourKeywords.put("darkorchid", Integer.valueOf(10040012));
        colourKeywords.put("darkred", Integer.valueOf(9109504));
        colourKeywords.put("darksalmon", Integer.valueOf(15308410));
        colourKeywords.put("darkseagreen", Integer.valueOf(9419919));
        colourKeywords.put("darkslateblue", Integer.valueOf(4734347));
        colourKeywords.put("darkslategray", Integer.valueOf(3100495));
        colourKeywords.put("darkslategrey", Integer.valueOf(3100495));
        colourKeywords.put("darkturquoise", Integer.valueOf(52945));
        colourKeywords.put("darkviolet", Integer.valueOf(9699539));
        colourKeywords.put("deeppink", Integer.valueOf(16716947));
        colourKeywords.put("deepskyblue", Integer.valueOf(49151));
        colourKeywords.put("dimgray", Integer.valueOf(6908265));
        colourKeywords.put("dimgrey", Integer.valueOf(6908265));
        colourKeywords.put("dodgerblue", Integer.valueOf(2003199));
        colourKeywords.put("firebrick", Integer.valueOf(11674146));
        colourKeywords.put("floralwhite", Integer.valueOf(16775920));
        colourKeywords.put("forestgreen", Integer.valueOf(2263842));
        colourKeywords.put("fuchsia", Integer.valueOf(16711935));
        colourKeywords.put("gainsboro", Integer.valueOf(14474460));
        colourKeywords.put("ghostwhite", Integer.valueOf(16316671));
        colourKeywords.put("gold", Integer.valueOf(16766720));
        colourKeywords.put("goldenrod", Integer.valueOf(14329120));
        colourKeywords.put("gray", Integer.valueOf(8421504));
        colourKeywords.put("green", Integer.valueOf(32768));
        colourKeywords.put("greenyellow", Integer.valueOf(11403055));
        colourKeywords.put("grey", Integer.valueOf(8421504));
        colourKeywords.put("honeydew", Integer.valueOf(15794160));
        colourKeywords.put("hotpink", Integer.valueOf(16738740));
        colourKeywords.put("indianred", Integer.valueOf(13458524));
        colourKeywords.put("indigo", Integer.valueOf(4915330));
        colourKeywords.put("ivory", Integer.valueOf(16777200));
        colourKeywords.put("khaki", Integer.valueOf(15787660));
        colourKeywords.put("lavender", Integer.valueOf(15132410));
        colourKeywords.put("lavenderblush", Integer.valueOf(16773365));
        colourKeywords.put("lawngreen", Integer.valueOf(8190976));
        colourKeywords.put("lemonchiffon", Integer.valueOf(16775885));
        colourKeywords.put("lightblue", Integer.valueOf(11393254));
        colourKeywords.put("lightcoral", Integer.valueOf(15761536));
        colourKeywords.put("lightcyan", Integer.valueOf(14745599));
        colourKeywords.put("lightgoldenrodyellow", Integer.valueOf(16448210));
        colourKeywords.put("lightgray", Integer.valueOf(13882323));
        colourKeywords.put("lightgreen", Integer.valueOf(9498256));
        colourKeywords.put("lightgrey", Integer.valueOf(13882323));
        colourKeywords.put("lightpink", Integer.valueOf(16758465));
        colourKeywords.put("lightsalmon", Integer.valueOf(16752762));
        colourKeywords.put("lightseagreen", Integer.valueOf(2142890));
        colourKeywords.put("lightskyblue", Integer.valueOf(8900346));
        colourKeywords.put("lightslategray", Integer.valueOf(7833753));
        colourKeywords.put("lightslategrey", Integer.valueOf(7833753));
        colourKeywords.put("lightsteelblue", Integer.valueOf(11584734));
        colourKeywords.put("lightyellow", Integer.valueOf(16777184));
        colourKeywords.put("lime", Integer.valueOf(ExploreBaseRangeSeekBar.ACTION_POINTER_INDEX_MASK));
        colourKeywords.put("limegreen", Integer.valueOf(3329330));
        colourKeywords.put("linen", Integer.valueOf(16445670));
        colourKeywords.put("magenta", Integer.valueOf(16711935));
        colourKeywords.put("maroon", Integer.valueOf(8388608));
        colourKeywords.put("mediumaquamarine", Integer.valueOf(6737322));
        colourKeywords.put("mediumblue", Integer.valueOf(205));
        colourKeywords.put("mediumorchid", Integer.valueOf(12211667));
        colourKeywords.put("mediumpurple", Integer.valueOf(9662683));
        colourKeywords.put("mediumseagreen", Integer.valueOf(3978097));
        colourKeywords.put("mediumslateblue", Integer.valueOf(8087790));
        colourKeywords.put("mediumspringgreen", Integer.valueOf(64154));
        colourKeywords.put("mediumturquoise", Integer.valueOf(4772300));
        colourKeywords.put("mediumvioletred", Integer.valueOf(13047173));
        colourKeywords.put("midnightblue", Integer.valueOf(1644912));
        colourKeywords.put("mintcream", Integer.valueOf(16121850));
        colourKeywords.put("mistyrose", Integer.valueOf(16770273));
        colourKeywords.put("moccasin", Integer.valueOf(16770229));
        colourKeywords.put("navajowhite", Integer.valueOf(16768685));
        colourKeywords.put("navy", Integer.valueOf(128));
        colourKeywords.put("oldlace", Integer.valueOf(16643558));
        colourKeywords.put("olive", Integer.valueOf(8421376));
        colourKeywords.put("olivedrab", Integer.valueOf(7048739));
        colourKeywords.put("orange", Integer.valueOf(16753920));
        colourKeywords.put("orangered", Integer.valueOf(16729344));
        colourKeywords.put("orchid", Integer.valueOf(14315734));
        colourKeywords.put("palegoldenrod", Integer.valueOf(15657130));
        colourKeywords.put("palegreen", Integer.valueOf(10025880));
        colourKeywords.put("paleturquoise", Integer.valueOf(11529966));
        colourKeywords.put("palevioletred", Integer.valueOf(14381203));
        colourKeywords.put("papayawhip", Integer.valueOf(16773077));
        colourKeywords.put("peachpuff", Integer.valueOf(16767673));
        colourKeywords.put("peru", Integer.valueOf(13468991));
        colourKeywords.put("pink", Integer.valueOf(16761035));
        colourKeywords.put("plum", Integer.valueOf(14524637));
        colourKeywords.put("powderblue", Integer.valueOf(11591910));
        colourKeywords.put("purple", Integer.valueOf(8388736));
        colourKeywords.put("red", Integer.valueOf(16711680));
        colourKeywords.put("rosybrown", Integer.valueOf(12357519));
        colourKeywords.put("royalblue", Integer.valueOf(4286945));
        colourKeywords.put("saddlebrown", Integer.valueOf(9127187));
        colourKeywords.put("salmon", Integer.valueOf(16416882));
        colourKeywords.put("sandybrown", Integer.valueOf(16032864));
        colourKeywords.put("seagreen", Integer.valueOf(3050327));
        colourKeywords.put("seashell", Integer.valueOf(16774638));
        colourKeywords.put("sienna", Integer.valueOf(10506797));
        colourKeywords.put("silver", Integer.valueOf(12632256));
        colourKeywords.put("skyblue", Integer.valueOf(8900331));
        colourKeywords.put("slateblue", Integer.valueOf(6970061));
        colourKeywords.put("slategray", Integer.valueOf(7372944));
        colourKeywords.put("slategrey", Integer.valueOf(7372944));
        colourKeywords.put("snow", Integer.valueOf(16775930));
        colourKeywords.put("springgreen", Integer.valueOf(65407));
        colourKeywords.put("steelblue", Integer.valueOf(4620980));
        colourKeywords.put("tan", Integer.valueOf(13808780));
        colourKeywords.put("teal", Integer.valueOf(32896));
        colourKeywords.put("thistle", Integer.valueOf(14204888));
        colourKeywords.put("tomato", Integer.valueOf(16737095));
        colourKeywords.put("turquoise", Integer.valueOf(4251856));
        colourKeywords.put("violet", Integer.valueOf(15631086));
        colourKeywords.put("wheat", Integer.valueOf(16113331));
        colourKeywords.put("white", Integer.valueOf(16777215));
        colourKeywords.put("whitesmoke", Integer.valueOf(16119285));
        colourKeywords.put("yellow", Integer.valueOf(16776960));
        colourKeywords.put("yellowgreen", Integer.valueOf(10145074));
        fontSizeKeywords.put("xx-small", new Length(0.694f, Unit.pt));
        fontSizeKeywords.put("x-small", new Length(0.833f, Unit.pt));
        fontSizeKeywords.put("small", new Length(10.0f, Unit.pt));
        fontSizeKeywords.put("medium", new Length(12.0f, Unit.pt));
        fontSizeKeywords.put("large", new Length(14.4f, Unit.pt));
        fontSizeKeywords.put("x-large", new Length(17.3f, Unit.pt));
        fontSizeKeywords.put("xx-large", new Length(20.7f, Unit.pt));
        fontSizeKeywords.put("smaller", new Length(83.33f, Unit.percent));
        fontSizeKeywords.put("larger", new Length(120.0f, Unit.percent));
        fontWeightKeywords.put("normal", Integer.valueOf(400));
        fontWeightKeywords.put("bold", Integer.valueOf(700));
        fontWeightKeywords.put("bolder", Integer.valueOf(1));
        fontWeightKeywords.put("lighter", Integer.valueOf(-1));
        fontWeightKeywords.put("100", Integer.valueOf(100));
        fontWeightKeywords.put("200", Integer.valueOf(200));
        fontWeightKeywords.put("300", Integer.valueOf(300));
        fontWeightKeywords.put("400", Integer.valueOf(400));
        fontWeightKeywords.put("500", Integer.valueOf(500));
        fontWeightKeywords.put("600", Integer.valueOf(600));
        fontWeightKeywords.put("700", Integer.valueOf(700));
        fontWeightKeywords.put("800", Integer.valueOf(800));
        fontWeightKeywords.put("900", Integer.valueOf(900));
        fontStyleKeywords.put("normal", FontStyle.Normal);
        fontStyleKeywords.put("italic", FontStyle.Italic);
        fontStyleKeywords.put("oblique", FontStyle.Oblique);
        aspectRatioKeywords.put("none", Alignment.None);
        aspectRatioKeywords.put("xMinYMin", Alignment.XMinYMin);
        aspectRatioKeywords.put("xMidYMin", Alignment.XMidYMin);
        aspectRatioKeywords.put("xMaxYMin", Alignment.XMaxYMin);
        aspectRatioKeywords.put("xMinYMid", Alignment.XMinYMid);
        aspectRatioKeywords.put("xMidYMid", Alignment.XMidYMid);
        aspectRatioKeywords.put("xMaxYMid", Alignment.XMaxYMid);
        aspectRatioKeywords.put("xMinYMax", Alignment.XMinYMax);
        aspectRatioKeywords.put("xMidYMax", Alignment.XMidYMax);
        aspectRatioKeywords.put("xMaxYMax", Alignment.XMaxYMax);
        supportedFeatures.add("Structure");
        supportedFeatures.add("BasicStructure");
        supportedFeatures.add("ConditionalProcessing");
        supportedFeatures.add("Image");
        supportedFeatures.add("Style");
        supportedFeatures.add("ViewportAttribute");
        supportedFeatures.add("Shape");
        supportedFeatures.add("BasicText");
        supportedFeatures.add("PaintAttribute");
        supportedFeatures.add("BasicPaintAttribute");
        supportedFeatures.add("OpacityAttribute");
        supportedFeatures.add("BasicGraphicsAttribute");
        supportedFeatures.add("Marker");
        supportedFeatures.add("Gradient");
        supportedFeatures.add("Pattern");
        supportedFeatures.add("Clip");
        supportedFeatures.add("BasicClip");
        supportedFeatures.add("Mask");
        supportedFeatures.add("View");
    }

    /* access modifiers changed from: protected */
    public SVG parse(InputStream is) throws SVGParseException {
        InputStream is2;
        InputStream is3;
        if (!is.markSupported()) {
            is2 = new BufferedInputStream(is);
        } else {
            is2 = is;
        }
        try {
            is2.mark(3);
            int firstTwoBytes = is2.read() + (is2.read() << 8);
            is2.reset();
            if (firstTwoBytes == 35615) {
                is3 = new GZIPInputStream(is2);
            } else {
                is3 = is2;
            }
        } catch (IOException e) {
            is3 = is2;
        }
        try {
            XMLReader xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xr.setContentHandler(this);
            xr.setProperty("http://xml.org/sax/properties/lexical-handler", this);
            xr.parse(new InputSource(is3));
            try {
                is3.close();
            } catch (IOException e2) {
                Log.e("SVGParser", "Exception thrown closing input stream");
            }
            return this.svgDocument;
        } catch (IOException e3) {
            throw new SVGParseException("File error", e3);
        } catch (ParserConfigurationException e4) {
            throw new SVGParseException("XML Parser problem", e4);
        } catch (SAXException e5) {
            throw new SVGParseException("SVG parse error: " + e5.getMessage(), e5);
        } catch (Throwable th) {
            try {
                is3.close();
            } catch (IOException e6) {
                Log.e("SVGParser", "Exception thrown closing input stream");
            }
            throw th;
        }
    }

    public void startDocument() throws SAXException {
        super.startDocument();
        this.svgDocument = new SVG();
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (this.ignoring) {
            this.ignoreDepth++;
        } else if (!"http://www.w3.org/2000/svg".equals(uri) && !"".equals(uri)) {
        } else {
            if (localName.equals("svg")) {
                svg(attributes);
            } else if (localName.equals("g")) {
                m1744g(attributes);
            } else if (localName.equals("defs")) {
                defs(attributes);
            } else if (localName.equals("use")) {
                use(attributes);
            } else if (localName.equals("path")) {
                path(attributes);
            } else if (localName.equals("rect")) {
                rect(attributes);
            } else if (localName.equals("circle")) {
                circle(attributes);
            } else if (localName.equals("ellipse")) {
                ellipse(attributes);
            } else if (localName.equals("line")) {
                line(attributes);
            } else if (localName.equals("polyline")) {
                polyline(attributes);
            } else if (localName.equals("polygon")) {
                polygon(attributes);
            } else if (localName.equals("text")) {
                text(attributes);
            } else if (localName.equals("tspan")) {
                tspan(attributes);
            } else if (localName.equals("tref")) {
                tref(attributes);
            } else if (localName.equals("switch")) {
                zwitch(attributes);
            } else if (localName.equals("symbol")) {
                symbol(attributes);
            } else if (localName.equals("marker")) {
                marker(attributes);
            } else if (localName.equals("linearGradient")) {
                linearGradient(attributes);
            } else if (localName.equals("radialGradient")) {
                radialGradient(attributes);
            } else if (localName.equals("stop")) {
                stop(attributes);
            } else if (localName.equals("a")) {
                m1744g(attributes);
            } else if (localName.equals("title") || localName.equals("desc")) {
                this.inMetadataElement = true;
                this.metadataTag = localName;
            } else if (localName.equals("clipPath")) {
                clipPath(attributes);
            } else if (localName.equals("textPath")) {
                textPath(attributes);
            } else if (localName.equals("pattern")) {
                pattern(attributes);
            } else if (localName.equals(ContentFrameworkAnalytics.IMAGE)) {
                image(attributes);
            } else if (localName.equals("view")) {
                view(attributes);
            } else if (localName.equals("mask")) {
                mask(attributes);
            } else if (localName.equals(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE)) {
                style(attributes);
            } else if (localName.equals("solidColor")) {
                solidColor(attributes);
            } else {
                this.ignoring = true;
                this.ignoreDepth = 1;
            }
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (!this.ignoring) {
            if (this.inMetadataElement) {
                if (this.metadataElementContents == null) {
                    this.metadataElementContents = new StringBuilder(length);
                }
                this.metadataElementContents.append(ch, start, length);
            } else if (this.inStyleElement) {
                if (this.styleElementContents == null) {
                    this.styleElementContents = new StringBuilder(length);
                }
                this.styleElementContents.append(ch, start, length);
            } else if (this.currentElement instanceof TextContainer) {
                SvgConditionalContainer parent = (SvgConditionalContainer) this.currentElement;
                int numOlderSiblings = parent.children.size();
                SvgObject previousSibling = numOlderSiblings == 0 ? null : (SvgObject) parent.children.get(numOlderSiblings - 1);
                if (previousSibling instanceof TextSequence) {
                    TextSequence textSequence = (TextSequence) previousSibling;
                    textSequence.text += new String(ch, start, length);
                    return;
                }
                ((SvgConditionalContainer) this.currentElement).addChild(new TextSequence(new String(ch, start, length)));
            }
        }
    }

    public void comment(char[] ch, int start, int length) throws SAXException {
        if (!this.ignoring && this.inStyleElement) {
            if (this.styleElementContents == null) {
                this.styleElementContents = new StringBuilder(length);
            }
            this.styleElementContents.append(ch, start, length);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (this.ignoring) {
            int i = this.ignoreDepth - 1;
            this.ignoreDepth = i;
            if (i == 0) {
                this.ignoring = false;
                return;
            }
        }
        if (!"http://www.w3.org/2000/svg".equals(uri) && !"".equals(uri)) {
            return;
        }
        if (localName.equals("title") || localName.equals("desc")) {
            this.inMetadataElement = false;
            if (this.metadataTag.equals("title")) {
                this.svgDocument.setTitle(this.metadataElementContents.toString());
            } else if (this.metadataTag.equals("desc")) {
                this.svgDocument.setDesc(this.metadataElementContents.toString());
            }
            this.metadataElementContents.setLength(0);
        } else if (localName.equals(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE) && this.styleElementContents != null) {
            this.inStyleElement = false;
            parseCSSStyleSheet(this.styleElementContents.toString());
            this.styleElementContents.setLength(0);
        } else if (localName.equals("svg") || localName.equals("defs") || localName.equals("g") || localName.equals("use") || localName.equals(ContentFrameworkAnalytics.IMAGE) || localName.equals("text") || localName.equals("tspan") || localName.equals("switch") || localName.equals("symbol") || localName.equals("marker") || localName.equals("linearGradient") || localName.equals("radialGradient") || localName.equals("stop") || localName.equals("clipPath") || localName.equals("textPath") || localName.equals("pattern") || localName.equals("view") || localName.equals("mask") || localName.equals("solidColor")) {
            this.currentElement = ((SvgObject) this.currentElement).parent;
        }
    }

    public void endDocument() throws SAXException {
        super.endDocument();
    }

    private void debug(String format, Object... args) {
    }

    private void svg(Attributes attributes) throws SAXException {
        debug("<svg>", new Object[0]);
        Svg obj = new Svg();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesViewBox(obj, attributes);
        parseAttributesSVG(obj, attributes);
        if (this.currentElement == null) {
            this.svgDocument.setRootElement(obj);
        } else {
            this.currentElement.addChild(obj);
        }
        this.currentElement = obj;
    }

    private void parseAttributesSVG(Svg obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 26:
                    obj.height = parseLength(val);
                    if (!obj.height.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <svg> element. height cannot be negative");
                    }
                case 80:
                    obj.version = val;
                    break;
                case 82:
                    obj.width = parseLength(val);
                    if (!obj.width.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <svg> element. width cannot be negative");
                    }
                case 83:
                    obj.f2962x = parseLength(val);
                    break;
                case 84:
                    obj.f2963y = parseLength(val);
                    break;
            }
        }
    }

    /* renamed from: g */
    private void m1744g(Attributes attributes) throws SAXException {
        debug("<g>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Group obj = new Group();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void defs(Attributes attributes) throws SAXException {
        debug("<defs>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Defs obj = new Defs();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void use(Attributes attributes) throws SAXException {
        debug("<use>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Use obj = new Use();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesUse(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesUse(Use obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 26:
                    obj.height = parseLength(val);
                    if (!obj.height.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <use> element. height cannot be negative");
                    }
                case 27:
                    if (!"http://www.w3.org/1999/xlink".equals(attributes.getURI(i))) {
                        break;
                    } else {
                        obj.href = val;
                        break;
                    }
                case 82:
                    obj.width = parseLength(val);
                    if (!obj.width.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <use> element. width cannot be negative");
                    }
                case 83:
                    obj.f2986x = parseLength(val);
                    break;
                case 84:
                    obj.f2987y = parseLength(val);
                    break;
            }
        }
    }

    private void image(Attributes attributes) throws SAXException {
        debug("<image>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Image obj = new Image();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesImage(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesImage(Image obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 26:
                    obj.height = parseLength(val);
                    if (!obj.height.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <use> element. height cannot be negative");
                    }
                case 27:
                    if (!"http://www.w3.org/1999/xlink".equals(attributes.getURI(i))) {
                        break;
                    } else {
                        obj.href = val;
                        break;
                    }
                case 49:
                    parsePreserveAspectRatio(obj, val);
                    break;
                case 82:
                    obj.width = parseLength(val);
                    if (!obj.width.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <use> element. width cannot be negative");
                    }
                case 83:
                    obj.f2947x = parseLength(val);
                    break;
                case 84:
                    obj.f2948y = parseLength(val);
                    break;
            }
        }
    }

    private void path(Attributes attributes) throws SAXException {
        debug("<path>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Path obj = new Path();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesPath(obj, attributes);
        this.currentElement.addChild(obj);
    }

    private void parseAttributesPath(Path obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 14:
                    obj.f2955d = parsePath(val);
                    break;
                case 44:
                    obj.pathLength = Float.valueOf(parseFloat(val));
                    if (obj.pathLength.floatValue() >= 0.0f) {
                        break;
                    } else {
                        throw new SAXException("Invalid <path> element. pathLength cannot be negative");
                    }
            }
        }
    }

    private void rect(Attributes attributes) throws SAXException {
        debug("<rect>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Rect obj = new Rect();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesRect(obj, attributes);
        this.currentElement.addChild(obj);
    }

    private void parseAttributesRect(Rect obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 26:
                    obj.height = parseLength(val);
                    if (!obj.height.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <rect> element. height cannot be negative");
                    }
                case 57:
                    obj.f2958rx = parseLength(val);
                    if (!obj.f2958rx.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <rect> element. rx cannot be negative");
                    }
                case 58:
                    obj.f2959ry = parseLength(val);
                    if (!obj.f2959ry.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <rect> element. ry cannot be negative");
                    }
                case 82:
                    obj.width = parseLength(val);
                    if (!obj.width.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <rect> element. width cannot be negative");
                    }
                case 83:
                    obj.f2960x = parseLength(val);
                    break;
                case 84:
                    obj.f2961y = parseLength(val);
                    break;
            }
        }
    }

    private void circle(Attributes attributes) throws SAXException {
        debug("<circle>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Circle obj = new Circle();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesCircle(obj, attributes);
        this.currentElement.addChild(obj);
    }

    private void parseAttributesCircle(Circle obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 7:
                    obj.f2940cx = parseLength(val);
                    break;
                case 8:
                    obj.f2941cy = parseLength(val);
                    break;
                case 50:
                    obj.f2942r = parseLength(val);
                    if (!obj.f2942r.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <circle> element. r cannot be negative");
                    }
            }
        }
    }

    private void ellipse(Attributes attributes) throws SAXException {
        debug("<ellipse>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Ellipse obj = new Ellipse();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesEllipse(obj, attributes);
        this.currentElement.addChild(obj);
    }

    private void parseAttributesEllipse(Ellipse obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 7:
                    obj.f2943cx = parseLength(val);
                    break;
                case 8:
                    obj.f2944cy = parseLength(val);
                    break;
                case 57:
                    obj.f2945rx = parseLength(val);
                    if (!obj.f2945rx.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <ellipse> element. rx cannot be negative");
                    }
                case 58:
                    obj.f2946ry = parseLength(val);
                    if (!obj.f2946ry.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <ellipse> element. ry cannot be negative");
                    }
            }
        }
    }

    private void line(Attributes attributes) throws SAXException {
        debug("<line>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Line obj = new Line();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesLine(obj, attributes);
        this.currentElement.addChild(obj);
    }

    private void parseAttributesLine(Line obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 85:
                    obj.f2949x1 = parseLength(val);
                    break;
                case 86:
                    obj.f2951y1 = parseLength(val);
                    break;
                case 87:
                    obj.f2950x2 = parseLength(val);
                    break;
                case 88:
                    obj.f2952y2 = parseLength(val);
                    break;
            }
        }
    }

    private void polyline(Attributes attributes) throws SAXException {
        debug("<polyline>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        PolyLine obj = new PolyLine();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesPolyLine(obj, attributes, "polyline");
        this.currentElement.addChild(obj);
    }

    private void parseAttributesPolyLine(PolyLine obj, Attributes attributes, String tag) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            if (SVGAttr.fromString(attributes.getLocalName(i)) == SVGAttr.points) {
                TextScanner scan = new TextScanner(attributes.getValue(i));
                List<Float> points = new ArrayList<>();
                scan.skipWhitespace();
                while (!scan.empty()) {
                    Float x = scan.nextFloat();
                    if (x == null) {
                        throw new SAXException("Invalid <" + tag + "> points attribute. Non-coordinate content found in list.");
                    }
                    scan.skipCommaWhitespace();
                    Float y = scan.nextFloat();
                    if (y == null) {
                        throw new SAXException("Invalid <" + tag + "> points attribute. There should be an even number of coordinates.");
                    }
                    scan.skipCommaWhitespace();
                    points.add(x);
                    points.add(y);
                }
                obj.points = new float[points.size()];
                int j = 0;
                for (Float f : points) {
                    int j2 = j + 1;
                    obj.points[j] = f.floatValue();
                    j = j2;
                }
            }
        }
    }

    private void polygon(Attributes attributes) throws SAXException {
        debug("<polygon>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Polygon obj = new Polygon();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesPolyLine(obj, attributes, "polygon");
        this.currentElement.addChild(obj);
    }

    private void text(Attributes attributes) throws SAXException {
        debug("<text>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Text obj = new Text();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesTextPosition(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesTextPosition(TextPositionedContainer obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 10:
                    obj.f2974dx = parseLengthList(val);
                    break;
                case 11:
                    obj.f2975dy = parseLengthList(val);
                    break;
                case 83:
                    obj.f2976x = parseLengthList(val);
                    break;
                case 84:
                    obj.f2977y = parseLengthList(val);
                    break;
            }
        }
    }

    private void tspan(Attributes attributes) throws SAXException {
        debug("<tspan>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else if (!(this.currentElement instanceof TextContainer)) {
            throw new SAXException("Invalid document. <tspan> elements are only valid inside <text> or other <tspan> elements.");
        } else {
            TSpan obj = new TSpan();
            obj.document = this.svgDocument;
            obj.parent = this.currentElement;
            parseAttributesCore(obj, attributes);
            parseAttributesStyle(obj, attributes);
            parseAttributesConditional(obj, attributes);
            parseAttributesTextPosition(obj, attributes);
            this.currentElement.addChild(obj);
            this.currentElement = obj;
            if (obj.parent instanceof TextRoot) {
                obj.setTextRoot((TextRoot) obj.parent);
            } else {
                obj.setTextRoot(((TextChild) obj.parent).getTextRoot());
            }
        }
    }

    private void tref(Attributes attributes) throws SAXException {
        debug("<tref>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else if (!(this.currentElement instanceof TextContainer)) {
            throw new SAXException("Invalid document. <tref> elements are only valid inside <text> or <tspan> elements.");
        } else {
            TRef obj = new TRef();
            obj.document = this.svgDocument;
            obj.parent = this.currentElement;
            parseAttributesCore(obj, attributes);
            parseAttributesStyle(obj, attributes);
            parseAttributesConditional(obj, attributes);
            parseAttributesTRef(obj, attributes);
            this.currentElement.addChild(obj);
            if (obj.parent instanceof TextRoot) {
                obj.setTextRoot((TextRoot) obj.parent);
            } else {
                obj.setTextRoot(((TextChild) obj.parent).getTextRoot());
            }
        }
    }

    private void parseAttributesTRef(TRef obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 27:
                    if (!"http://www.w3.org/1999/xlink".equals(attributes.getURI(i))) {
                        break;
                    } else {
                        obj.href = val;
                        break;
                    }
            }
        }
    }

    private void zwitch(Attributes attributes) throws SAXException {
        debug("<switch>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Switch obj = new Switch();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesConditional(SvgConditional obj, Attributes attributes) throws SAXException {
        Set<String> fontSet;
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 53:
                    obj.setRequiredFeatures(parseRequiredFeatures(val));
                    break;
                case 54:
                    obj.setRequiredExtensions(val);
                    break;
                case 55:
                    obj.setRequiredFormats(parseRequiredFormats(val));
                    break;
                case 56:
                    List<String> fonts = parseFontFamily(val);
                    if (fonts != null) {
                        fontSet = new HashSet<>(fonts);
                    } else {
                        fontSet = new HashSet<>(0);
                    }
                    obj.setRequiredFonts(fontSet);
                    break;
                case 74:
                    obj.setSystemLanguage(parseSystemLanguage(val));
                    break;
            }
        }
    }

    private void symbol(Attributes attributes) throws SAXException {
        debug("<symbol>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Symbol obj = new Symbol();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesViewBox(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void marker(Attributes attributes) throws SAXException {
        debug("<marker>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Marker obj = new Marker();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesViewBox(obj, attributes);
        parseAttributesMarker(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesMarker(Marker obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 33:
                    obj.markerHeight = parseLength(val);
                    if (!obj.markerHeight.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <marker> element. markerHeight cannot be negative");
                    }
                case 34:
                    if ("strokeWidth".equals(val)) {
                        obj.markerUnitsAreUser = false;
                        break;
                    } else if ("userSpaceOnUse".equals(val)) {
                        obj.markerUnitsAreUser = true;
                        break;
                    } else {
                        throw new SAXException("Invalid value for attribute markerUnits");
                    }
                case 35:
                    obj.markerWidth = parseLength(val);
                    if (!obj.markerWidth.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <marker> element. markerWidth cannot be negative");
                    }
                case 42:
                    if (!"auto".equals(val)) {
                        obj.orient = Float.valueOf(parseFloat(val));
                        break;
                    } else {
                        obj.orient = Float.valueOf(Float.NaN);
                        break;
                    }
                case 51:
                    obj.refX = parseLength(val);
                    break;
                case 52:
                    obj.refY = parseLength(val);
                    break;
            }
        }
    }

    private void linearGradient(Attributes attributes) throws SAXException {
        debug("<linearGradiant>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        SvgLinearGradient obj = new SvgLinearGradient();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesGradient(obj, attributes);
        parseAttributesLinearGradient(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesGradient(GradientElement obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 24:
                    obj.gradientTransform = parseTransformList(val);
                    break;
                case 25:
                    if ("objectBoundingBox".equals(val)) {
                        obj.gradientUnitsAreUser = Boolean.valueOf(false);
                        break;
                    } else if ("userSpaceOnUse".equals(val)) {
                        obj.gradientUnitsAreUser = Boolean.valueOf(true);
                        break;
                    } else {
                        throw new SAXException("Invalid value for attribute gradientUnits");
                    }
                case 27:
                    if (!"http://www.w3.org/1999/xlink".equals(attributes.getURI(i))) {
                        break;
                    } else {
                        obj.href = val;
                        break;
                    }
                case 61:
                    try {
                        obj.spreadMethod = GradientSpread.valueOf(val);
                        break;
                    } catch (IllegalArgumentException e) {
                        throw new SAXException("Invalid spreadMethod attribute. \"" + val + "\" is not a valid value.");
                    }
            }
        }
    }

    private void parseAttributesLinearGradient(SvgLinearGradient obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 85:
                    obj.f2965x1 = parseLength(val);
                    break;
                case 86:
                    obj.f2967y1 = parseLength(val);
                    break;
                case 87:
                    obj.f2966x2 = parseLength(val);
                    break;
                case 88:
                    obj.f2968y2 = parseLength(val);
                    break;
            }
        }
    }

    private void radialGradient(Attributes attributes) throws SAXException {
        debug("<radialGradient>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        SvgRadialGradient obj = new SvgRadialGradient();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesGradient(obj, attributes);
        parseAttributesRadialGradient(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesRadialGradient(SvgRadialGradient obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 7:
                    obj.f2969cx = parseLength(val);
                    break;
                case 8:
                    obj.f2970cy = parseLength(val);
                    break;
                case 12:
                    obj.f2971fx = parseLength(val);
                    break;
                case 13:
                    obj.f2972fy = parseLength(val);
                    break;
                case 50:
                    obj.f2973r = parseLength(val);
                    if (!obj.f2973r.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <radialGradient> element. r cannot be negative");
                    }
            }
        }
    }

    private void stop(Attributes attributes) throws SAXException {
        debug("<stop>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else if (!(this.currentElement instanceof GradientElement)) {
            throw new SAXException("Invalid document. <stop> elements are only valid inside <linearGradiant> or <radialGradient> elements.");
        } else {
            Stop obj = new Stop();
            obj.document = this.svgDocument;
            obj.parent = this.currentElement;
            parseAttributesCore(obj, attributes);
            parseAttributesStyle(obj, attributes);
            parseAttributesStop(obj, attributes);
            this.currentElement.addChild(obj);
            this.currentElement = obj;
        }
    }

    private void parseAttributesStop(Stop obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 40:
                    obj.offset = parseGradiantOffset(val);
                    break;
            }
        }
    }

    private Float parseGradiantOffset(String val) throws SAXException {
        if (val.length() == 0) {
            throw new SAXException("Invalid offset value in <stop> (empty string)");
        }
        int end = val.length();
        boolean isPercent = false;
        if (val.charAt(val.length() - 1) == '%') {
            end--;
            isPercent = true;
        }
        try {
            float scalar = Float.parseFloat(val.substring(0, end));
            if (isPercent) {
                scalar /= 100.0f;
            }
            if (scalar < 0.0f) {
                scalar = 0.0f;
            } else if (scalar > 100.0f) {
                scalar = 100.0f;
            }
            return Float.valueOf(scalar);
        } catch (NumberFormatException e) {
            throw new SAXException("Invalid offset value in <stop>: " + val, e);
        }
    }

    private void solidColor(Attributes attributes) throws SAXException {
        debug("<solidColor>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        SolidColor obj = new SolidColor();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void clipPath(Attributes attributes) throws SAXException {
        debug("<clipPath>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        ClipPath obj = new ClipPath();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesTransform(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesClipPath(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesClipPath(ClipPath obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 4:
                    if ("objectBoundingBox".equals(val)) {
                        obj.clipPathUnitsAreUser = Boolean.valueOf(false);
                        break;
                    } else if ("userSpaceOnUse".equals(val)) {
                        obj.clipPathUnitsAreUser = Boolean.valueOf(true);
                        break;
                    } else {
                        throw new SAXException("Invalid value for attribute clipPathUnits");
                    }
            }
        }
    }

    private void textPath(Attributes attributes) throws SAXException {
        debug("<textPath>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        TextPath obj = new TextPath();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesTextPath(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
        if (obj.parent instanceof TextRoot) {
            obj.setTextRoot((TextRoot) obj.parent);
        } else {
            obj.setTextRoot(((TextChild) obj.parent).getTextRoot());
        }
    }

    private void parseAttributesTextPath(TextPath obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 27:
                    if (!"http://www.w3.org/1999/xlink".equals(attributes.getURI(i))) {
                        break;
                    } else {
                        obj.href = val;
                        break;
                    }
                case 62:
                    obj.startOffset = parseLength(val);
                    break;
            }
        }
    }

    private void pattern(Attributes attributes) throws SAXException {
        debug("<pattern>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Pattern obj = new Pattern();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesViewBox(obj, attributes);
        parseAttributesPattern(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesPattern(Pattern obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 26:
                    obj.height = parseLength(val);
                    if (!obj.height.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <pattern> element. height cannot be negative");
                    }
                case 27:
                    if (!"http://www.w3.org/1999/xlink".equals(attributes.getURI(i))) {
                        break;
                    } else {
                        obj.href = val;
                        break;
                    }
                case 45:
                    if ("objectBoundingBox".equals(val)) {
                        obj.patternContentUnitsAreUser = Boolean.valueOf(false);
                        break;
                    } else if ("userSpaceOnUse".equals(val)) {
                        obj.patternContentUnitsAreUser = Boolean.valueOf(true);
                        break;
                    } else {
                        throw new SAXException("Invalid value for attribute patternContentUnits");
                    }
                case 46:
                    obj.patternTransform = parseTransformList(val);
                    break;
                case 47:
                    if ("objectBoundingBox".equals(val)) {
                        obj.patternUnitsAreUser = Boolean.valueOf(false);
                        break;
                    } else if ("userSpaceOnUse".equals(val)) {
                        obj.patternUnitsAreUser = Boolean.valueOf(true);
                        break;
                    } else {
                        throw new SAXException("Invalid value for attribute patternUnits");
                    }
                case 82:
                    obj.width = parseLength(val);
                    if (!obj.width.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <pattern> element. width cannot be negative");
                    }
                case 83:
                    obj.f2956x = parseLength(val);
                    break;
                case 84:
                    obj.f2957y = parseLength(val);
                    break;
            }
        }
    }

    private void view(Attributes attributes) throws SAXException {
        debug("<view>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        View obj = new View();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesViewBox(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void mask(Attributes attributes) throws SAXException {
        debug("<mask>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        Mask obj = new Mask();
        obj.document = this.svgDocument;
        obj.parent = this.currentElement;
        parseAttributesCore(obj, attributes);
        parseAttributesStyle(obj, attributes);
        parseAttributesConditional(obj, attributes);
        parseAttributesMask(obj, attributes);
        this.currentElement.addChild(obj);
        this.currentElement = obj;
    }

    private void parseAttributesMask(Mask obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 26:
                    obj.height = parseLength(val);
                    if (!obj.height.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <mask> element. height cannot be negative");
                    }
                case 37:
                    if ("objectBoundingBox".equals(val)) {
                        obj.maskContentUnitsAreUser = Boolean.valueOf(false);
                        break;
                    } else if ("userSpaceOnUse".equals(val)) {
                        obj.maskContentUnitsAreUser = Boolean.valueOf(true);
                        break;
                    } else {
                        throw new SAXException("Invalid value for attribute maskContentUnits");
                    }
                case 38:
                    if ("objectBoundingBox".equals(val)) {
                        obj.maskUnitsAreUser = Boolean.valueOf(false);
                        break;
                    } else if ("userSpaceOnUse".equals(val)) {
                        obj.maskUnitsAreUser = Boolean.valueOf(true);
                        break;
                    } else {
                        throw new SAXException("Invalid value for attribute maskUnits");
                    }
                case 82:
                    obj.width = parseLength(val);
                    if (!obj.width.isNegative()) {
                        break;
                    } else {
                        throw new SAXException("Invalid <mask> element. width cannot be negative");
                    }
                case 83:
                    obj.f2953x = parseLength(val);
                    break;
                case 84:
                    obj.f2954y = parseLength(val);
                    break;
            }
        }
    }

    private void parseAttributesCore(SvgElementBase obj, Attributes attributes) throws SAXException {
        int i = 0;
        while (i < attributes.getLength()) {
            String qname = attributes.getQName(i);
            if (qname.equals("id") || qname.equals("xml:id")) {
                obj.f2964id = attributes.getValue(i).trim();
                return;
            } else if (qname.equals("xml:space")) {
                String val = attributes.getValue(i).trim();
                if ("default".equals(val)) {
                    obj.spacePreserve = Boolean.FALSE;
                    return;
                } else if ("preserve".equals(val)) {
                    obj.spacePreserve = Boolean.TRUE;
                    return;
                } else {
                    throw new SAXException("Invalid value for \"xml:space\" attribute: " + val);
                }
            } else {
                i++;
            }
        }
    }

    private void parseAttributesStyle(SvgElementBase obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            if (val.length() != 0) {
                switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                    case 1:
                        obj.classNames = CSSParser.parseClassAttribute(val);
                        break;
                    case 73:
                        parseStyle(obj, val);
                        break;
                    default:
                        if (obj.baseStyle == null) {
                            obj.baseStyle = new Style();
                        }
                        processStyleProperty(obj.baseStyle, attributes.getLocalName(i), attributes.getValue(i).trim());
                        break;
                }
            }
        }
    }

    private static void parseStyle(SvgElementBase obj, String style) throws SAXException {
        TextScanner scan = new TextScanner(style.replaceAll("/\\*.*?\\*/", ""));
        while (true) {
            String propertyName = scan.nextToken(':');
            scan.skipWhitespace();
            if (scan.consume(':')) {
                scan.skipWhitespace();
                String propertyValue = scan.nextToken(';');
                if (propertyValue != null) {
                    scan.skipWhitespace();
                    if (scan.empty() || scan.consume(';')) {
                        if (obj.style == null) {
                            obj.style = new Style();
                        }
                        processStyleProperty(obj.style, propertyName, propertyValue);
                        scan.skipWhitespace();
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    protected static void processStyleProperty(Style style, String localName, String val) throws SAXException {
        if (val.length() != 0 && !val.equals("inherit")) {
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(localName).ordinal()]) {
                case 2:
                    style.clip = parseClip(val);
                    style.specifiedFlags |= 1048576;
                    return;
                case 3:
                    style.clipPath = parseFunctionalIRI(val, localName);
                    style.specifiedFlags |= 268435456;
                    return;
                case 5:
                    style.clipRule = parseFillRule(val);
                    style.specifiedFlags |= 536870912;
                    return;
                case 6:
                    style.color = parseColour(val);
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    return;
                case 9:
                    style.direction = parseTextDirection(val);
                    style.specifiedFlags |= 68719476736L;
                    return;
                case 15:
                    if (val.indexOf(EACTags.DYNAMIC_AUTHENTIFICATION_TEMPLATE) >= 0 || "|inline|block|list-item|run-in|compact|marker|table|inline-table|table-row-group|table-header-group|table-footer-group|table-row|table-column-group|table-column|table-cell|table-caption|none|".indexOf(new StringBuilder(String.valueOf('|')).append(val).append('|').toString()) == -1) {
                        throw new SAXException("Invalid value for \"display\" attribute: " + val);
                    }
                    style.display = Boolean.valueOf(!val.equals("none"));
                    style.specifiedFlags |= 16777216;
                    return;
                case 16:
                    style.fill = parsePaintSpecifier(val, "fill");
                    style.specifiedFlags |= 1;
                    return;
                case 17:
                    style.fillRule = parseFillRule(val);
                    style.specifiedFlags |= 2;
                    return;
                case 18:
                    style.fillOpacity = Float.valueOf(parseOpacity(val));
                    style.specifiedFlags |= 4;
                    return;
                case 19:
                    parseFont(style, val);
                    return;
                case 20:
                    style.fontFamily = parseFontFamily(val);
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    return;
                case 21:
                    style.fontSize = parseFontSize(val);
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    return;
                case 22:
                    style.fontWeight = parseFontWeight(val);
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    return;
                case 23:
                    style.fontStyle = parseFontStyle(val);
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    return;
                case 29:
                    style.markerStart = parseFunctionalIRI(val, localName);
                    style.markerMid = style.markerStart;
                    style.markerEnd = style.markerStart;
                    style.specifiedFlags |= 14680064;
                    return;
                case 30:
                    style.markerStart = parseFunctionalIRI(val, localName);
                    style.specifiedFlags |= 2097152;
                    return;
                case 31:
                    style.markerMid = parseFunctionalIRI(val, localName);
                    style.specifiedFlags |= 4194304;
                    return;
                case 32:
                    style.markerEnd = parseFunctionalIRI(val, localName);
                    style.specifiedFlags |= 8388608;
                    return;
                case 36:
                    style.mask = parseFunctionalIRI(val, localName);
                    style.specifiedFlags |= 1073741824;
                    return;
                case 41:
                    style.opacity = Float.valueOf(parseOpacity(val));
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    return;
                case 43:
                    style.overflow = parseOverflow(val);
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    return;
                case 59:
                    if (val.equals("currentColor")) {
                        style.solidColor = CurrentColor.getInstance();
                    } else {
                        style.solidColor = parseColour(val);
                    }
                    style.specifiedFlags |= 2147483648L;
                    return;
                case 60:
                    style.solidOpacity = Float.valueOf(parseOpacity(val));
                    style.specifiedFlags |= 4294967296L;
                    return;
                case 63:
                    if (val.equals("currentColor")) {
                        style.stopColor = CurrentColor.getInstance();
                    } else {
                        style.stopColor = parseColour(val);
                    }
                    style.specifiedFlags |= 67108864;
                    return;
                case 64:
                    style.stopOpacity = Float.valueOf(parseOpacity(val));
                    style.specifiedFlags |= 134217728;
                    return;
                case 65:
                    style.stroke = parsePaintSpecifier(val, "stroke");
                    style.specifiedFlags |= 8;
                    return;
                case 66:
                    if ("none".equals(val)) {
                        style.strokeDashArray = null;
                    } else {
                        style.strokeDashArray = parseStrokeDashArray(val);
                    }
                    style.specifiedFlags |= 512;
                    return;
                case 67:
                    style.strokeDashOffset = parseLength(val);
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    return;
                case 68:
                    style.strokeLineCap = parseStrokeLineCap(val);
                    style.specifiedFlags |= 64;
                    return;
                case 69:
                    style.strokeLineJoin = parseStrokeLineJoin(val);
                    style.specifiedFlags |= 128;
                    return;
                case 70:
                    style.strokeMiterLimit = Float.valueOf(parseFloat(val));
                    style.specifiedFlags |= 256;
                    return;
                case 71:
                    style.strokeOpacity = Float.valueOf(parseOpacity(val));
                    style.specifiedFlags |= 16;
                    return;
                case 72:
                    style.strokeWidth = parseLength(val);
                    style.specifiedFlags |= 32;
                    return;
                case 75:
                    style.textAnchor = parseTextAnchor(val);
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    return;
                case 76:
                    style.textDecoration = parseTextDecoration(val);
                    style.specifiedFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    return;
                case 79:
                    style.vectorEffect = parseVectorEffect(val);
                    style.specifiedFlags |= 34359738368L;
                    return;
                case 89:
                    if (val.equals("currentColor")) {
                        style.viewportFill = CurrentColor.getInstance();
                    } else {
                        style.viewportFill = parseColour(val);
                    }
                    style.specifiedFlags |= 8589934592L;
                    return;
                case 90:
                    style.viewportFillOpacity = Float.valueOf(parseOpacity(val));
                    style.specifiedFlags |= 17179869184L;
                    return;
                case 91:
                    if (val.indexOf(EACTags.DYNAMIC_AUTHENTIFICATION_TEMPLATE) >= 0 || "|visible|hidden|collapse|".indexOf(new StringBuilder(String.valueOf('|')).append(val).append('|').toString()) == -1) {
                        throw new SAXException("Invalid value for \"visibility\" attribute: " + val);
                    }
                    style.visibility = Boolean.valueOf(val.equals(InternalLogger.EVENT_PARAM_VIEW_STATE_VISIBLE));
                    style.specifiedFlags |= 33554432;
                    return;
                default:
                    return;
            }
        }
    }

    private void parseAttributesViewBox(SvgViewBoxContainer obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 49:
                    parsePreserveAspectRatio(obj, val);
                    break;
                case 81:
                    obj.viewBox = parseViewBox(val);
                    break;
            }
        }
    }

    private void parseAttributesTransform(HasTransform obj, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            if (SVGAttr.fromString(attributes.getLocalName(i)) == SVGAttr.transform) {
                obj.setTransform(parseTransformList(attributes.getValue(i)));
            }
        }
    }

    private Matrix parseTransformList(String val) throws SAXException {
        Matrix matrix = new Matrix();
        TextScanner textScanner = new TextScanner(val);
        textScanner.skipWhitespace();
        while (!textScanner.empty()) {
            String cmd = textScanner.nextFunction();
            if (cmd != null) {
                if (cmd.equals("matrix")) {
                    textScanner.skipWhitespace();
                    Float a = textScanner.nextFloat();
                    textScanner.skipCommaWhitespace();
                    Float b = textScanner.nextFloat();
                    textScanner.skipCommaWhitespace();
                    Float c = textScanner.nextFloat();
                    textScanner.skipCommaWhitespace();
                    Float d = textScanner.nextFloat();
                    textScanner.skipCommaWhitespace();
                    Float e = textScanner.nextFloat();
                    textScanner.skipCommaWhitespace();
                    Float f = textScanner.nextFloat();
                    textScanner.skipWhitespace();
                    if (f == null || !textScanner.consume(')')) {
                        throw new SAXException("Invalid transform list: " + val);
                    }
                    Matrix m = new Matrix();
                    m.setValues(new float[]{a.floatValue(), c.floatValue(), e.floatValue(), b.floatValue(), d.floatValue(), f.floatValue(), 0.0f, 0.0f, 1.0f});
                    matrix.preConcat(m);
                } else if (cmd.equals("translate")) {
                    textScanner.skipWhitespace();
                    Float tx = textScanner.nextFloat();
                    Float ty = textScanner.possibleNextFloat();
                    textScanner.skipWhitespace();
                    if (tx == null || !textScanner.consume(')')) {
                        throw new SAXException("Invalid transform list: " + val);
                    } else if (ty == null) {
                        matrix.preTranslate(tx.floatValue(), 0.0f);
                    } else {
                        matrix.preTranslate(tx.floatValue(), ty.floatValue());
                    }
                } else if (cmd.equals("scale")) {
                    textScanner.skipWhitespace();
                    Float sx = textScanner.nextFloat();
                    Float sy = textScanner.possibleNextFloat();
                    textScanner.skipWhitespace();
                    if (sx == null || !textScanner.consume(')')) {
                        throw new SAXException("Invalid transform list: " + val);
                    } else if (sy == null) {
                        matrix.preScale(sx.floatValue(), sx.floatValue());
                    } else {
                        matrix.preScale(sx.floatValue(), sy.floatValue());
                    }
                } else if (cmd.equals("rotate")) {
                    textScanner.skipWhitespace();
                    Float ang = textScanner.nextFloat();
                    Float cx = textScanner.possibleNextFloat();
                    Float cy = textScanner.possibleNextFloat();
                    textScanner.skipWhitespace();
                    if (ang == null || !textScanner.consume(')')) {
                        throw new SAXException("Invalid transform list: " + val);
                    } else if (cx == null) {
                        matrix.preRotate(ang.floatValue());
                    } else if (cy != null) {
                        matrix.preRotate(ang.floatValue(), cx.floatValue(), cy.floatValue());
                    } else {
                        throw new SAXException("Invalid transform list: " + val);
                    }
                } else if (cmd.equals("skewX")) {
                    textScanner.skipWhitespace();
                    Float ang2 = textScanner.nextFloat();
                    textScanner.skipWhitespace();
                    if (ang2 == null || !textScanner.consume(')')) {
                        throw new SAXException("Invalid transform list: " + val);
                    }
                    matrix.preSkew((float) Math.tan(Math.toRadians((double) ang2.floatValue())), 0.0f);
                } else if (cmd.equals("skewY")) {
                    textScanner.skipWhitespace();
                    Float ang3 = textScanner.nextFloat();
                    textScanner.skipWhitespace();
                    if (ang3 == null || !textScanner.consume(')')) {
                        throw new SAXException("Invalid transform list: " + val);
                    }
                    matrix.preSkew(0.0f, (float) Math.tan(Math.toRadians((double) ang3.floatValue())));
                } else if (cmd != null) {
                    throw new SAXException("Invalid transform list fn: " + cmd + ")");
                }
                if (textScanner.empty()) {
                    break;
                }
                textScanner.skipCommaWhitespace();
            } else {
                throw new SAXException("Bad transform function encountered in transform list: " + val);
            }
        }
        return matrix;
    }

    protected static Length parseLength(String val) throws SAXException {
        if (val.length() == 0) {
            throw new SAXException("Invalid length value (empty string)");
        }
        int end = val.length();
        Unit unit = Unit.px;
        char lastChar = val.charAt(end - 1);
        if (lastChar == '%') {
            end--;
            unit = Unit.percent;
        } else if (end > 2 && Character.isLetter(lastChar) && Character.isLetter(val.charAt(end - 2))) {
            end -= 2;
            try {
                unit = Unit.valueOf(val.substring(end).toLowerCase(Locale.US));
            } catch (IllegalArgumentException e) {
                throw new SAXException("Invalid length unit specifier: " + val);
            }
        }
        try {
            return new Length(Float.parseFloat(val.substring(0, end)), unit);
        } catch (NumberFormatException e2) {
            throw new SAXException("Invalid length value: " + val, e2);
        }
    }

    private static List<Length> parseLengthList(String val) throws SAXException {
        if (val.length() == 0) {
            throw new SAXException("Invalid length list (empty string)");
        }
        List<Length> coords = new ArrayList<>(1);
        TextScanner scan = new TextScanner(val);
        scan.skipWhitespace();
        while (!scan.empty()) {
            Float scalar = scan.nextFloat();
            if (scalar == null) {
                throw new SAXException("Invalid length list value: " + scan.ahead());
            }
            Unit unit = scan.nextUnit();
            if (unit == null) {
                unit = Unit.px;
            }
            coords.add(new Length(scalar.floatValue(), unit));
            scan.skipCommaWhitespace();
        }
        return coords;
    }

    private static float parseFloat(String val) throws SAXException {
        if (val.length() == 0) {
            throw new SAXException("Invalid float value (empty string)");
        }
        try {
            return Float.parseFloat(val);
        } catch (NumberFormatException e) {
            throw new SAXException("Invalid float value: " + val, e);
        }
    }

    private static float parseOpacity(String val) throws SAXException {
        float o = parseFloat(val);
        if (o < 0.0f) {
            return 0.0f;
        }
        if (o > 1.0f) {
            return 1.0f;
        }
        return o;
    }

    private static Box parseViewBox(String val) throws SAXException {
        TextScanner scan = new TextScanner(val);
        scan.skipWhitespace();
        Float minX = scan.nextFloat();
        scan.skipCommaWhitespace();
        Float minY = scan.nextFloat();
        scan.skipCommaWhitespace();
        Float width = scan.nextFloat();
        scan.skipCommaWhitespace();
        Float height = scan.nextFloat();
        if (minX == null || minY == null || width == null || height == null) {
            throw new SAXException("Invalid viewBox definition - should have four numbers");
        } else if (width.floatValue() < 0.0f) {
            throw new SAXException("Invalid viewBox. width cannot be negative");
        } else if (height.floatValue() >= 0.0f) {
            return new Box(minX.floatValue(), minY.floatValue(), width.floatValue(), height.floatValue());
        } else {
            throw new SAXException("Invalid viewBox. height cannot be negative");
        }
    }

    private static void parsePreserveAspectRatio(SvgPreserveAspectRatioContainer obj, String val) throws SAXException {
        TextScanner scan = new TextScanner(val);
        scan.skipWhitespace();
        Scale scale = null;
        String word = scan.nextToken();
        if ("defer".equals(word)) {
            scan.skipWhitespace();
            word = scan.nextToken();
        }
        Alignment align = (Alignment) aspectRatioKeywords.get(word);
        scan.skipWhitespace();
        if (!scan.empty()) {
            String meetOrSlice = scan.nextToken();
            if (meetOrSlice.equals("meet")) {
                scale = Scale.Meet;
            } else if (meetOrSlice.equals("slice")) {
                scale = Scale.Slice;
            } else {
                throw new SAXException("Invalid preserveAspectRatio definition: " + val);
            }
        }
        obj.preserveAspectRatio = new PreserveAspectRatio(align, scale);
    }

    private static SvgPaint parsePaintSpecifier(String val, String attrName) throws SAXException {
        if (!val.startsWith("url(")) {
            return parseColourSpecifer(val);
        }
        int closeBracket = val.indexOf(")");
        if (closeBracket == -1) {
            throw new SAXException("Bad " + attrName + " attribute. Unterminated url() reference");
        }
        String href = val.substring(4, closeBracket).trim();
        SvgPaint fallback = null;
        String val2 = val.substring(closeBracket + 1).trim();
        if (val2.length() > 0) {
            fallback = parseColourSpecifer(val2);
        }
        return new PaintReference(href, fallback);
    }

    private static SvgPaint parseColourSpecifer(String val) throws SAXException {
        if (val.equals("none")) {
            return null;
        }
        if (val.equals("currentColor")) {
            return CurrentColor.getInstance();
        }
        return parseColour(val);
    }

    private static Colour parseColour(String val) throws SAXException {
        if (val.charAt(0) == '#') {
            try {
                if (val.length() == 7) {
                    return new Colour(Integer.parseInt(val.substring(1), 16));
                }
                if (val.length() == 4) {
                    int threehex = Integer.parseInt(val.substring(1), 16);
                    int h1 = threehex & 3840;
                    int h2 = threehex & 240;
                    int h3 = threehex & 15;
                    return new Colour((h1 << 16) | (h1 << 12) | (h2 << 8) | (h2 << 4) | (h3 << 4) | h3);
                }
                throw new SAXException("Bad hex colour value: " + val);
            } catch (NumberFormatException e) {
                throw new SAXException("Bad colour value: " + val);
            }
        } else if (!val.toLowerCase(Locale.US).startsWith("rgb(")) {
            return parseColourKeyword(val);
        } else {
            TextScanner scan = new TextScanner(val.substring(4));
            scan.skipWhitespace();
            int red = parseColourComponent(scan);
            scan.skipCommaWhitespace();
            int green = parseColourComponent(scan);
            scan.skipCommaWhitespace();
            int blue = parseColourComponent(scan);
            scan.skipWhitespace();
            if (scan.consume(')')) {
                return new Colour((red << 16) | (green << 8) | blue);
            }
            throw new SAXException("Bad rgb() colour value: " + val);
        }
    }

    private static int parseColourComponent(TextScanner scan) throws SAXException {
        float comp = scan.nextFloat().floatValue();
        if (scan.consume('%')) {
            comp = (256.0f * comp) / 100.0f;
        }
        if (comp < 0.0f) {
            return 0;
        }
        if (comp > 255.0f) {
            return 255;
        }
        return (int) comp;
    }

    private static Colour parseColourKeyword(String name) throws SAXException {
        Integer col = (Integer) colourKeywords.get(name.toLowerCase(Locale.US));
        if (col != null) {
            return new Colour(col.intValue());
        }
        throw new SAXException("Invalid colour keyword: " + name);
    }

    private static void parseFont(Style style, String val) throws SAXException {
        String item;
        FontStyle fontStyle;
        Integer fontWeight = null;
        FontStyle fontStyle2 = null;
        String fontVariant = null;
        if ("|caption|icon|menu|message-box|small-caption|status-bar|".indexOf(new StringBuilder(String.valueOf('|')).append(val).append('|').toString()) == -1) {
            TextScanner scan = new TextScanner(val);
            while (true) {
                item = scan.nextToken('/');
                scan.skipWhitespace();
                if (item != null) {
                    if (fontWeight == null || fontStyle2 == null) {
                        if (!item.equals("normal")) {
                            if (fontWeight == null) {
                                fontWeight = (Integer) fontWeightKeywords.get(item);
                                if (fontWeight != null) {
                                    continue;
                                }
                            }
                            if (fontStyle2 == null) {
                                fontStyle2 = (FontStyle) fontStyleKeywords.get(item);
                                if (fontStyle2 != null) {
                                    continue;
                                }
                            }
                            if (fontVariant != null || !item.equals("small-caps")) {
                                break;
                            }
                            fontVariant = item;
                        }
                    } else {
                        break;
                    }
                } else {
                    throw new SAXException("Invalid font style attribute: missing font size and family");
                }
            }
            Length fontSize = parseFontSize(item);
            if (scan.consume('/')) {
                scan.skipWhitespace();
                String item2 = scan.nextToken();
                if (item2 == null) {
                    throw new SAXException("Invalid font style attribute: missing line-height");
                }
                parseLength(item2);
                scan.skipWhitespace();
            }
            style.fontFamily = parseFontFamily(scan.restOfText());
            style.fontSize = fontSize;
            style.fontWeight = Integer.valueOf(fontWeight == null ? 400 : fontWeight.intValue());
            if (fontStyle2 == null) {
                fontStyle = FontStyle.Normal;
            } else {
                fontStyle = fontStyle2;
            }
            style.fontStyle = fontStyle;
            style.specifiedFlags |= 122880;
        }
    }

    private static List<String> parseFontFamily(String val) throws SAXException {
        List<String> fonts = null;
        TextScanner scan = new TextScanner(val);
        do {
            String item = scan.nextQuotedString();
            if (item == null) {
                item = scan.nextToken(',');
            }
            if (item == null) {
                break;
            }
            if (fonts == null) {
                fonts = new ArrayList<>();
            }
            fonts.add(item);
            scan.skipCommaWhitespace();
        } while (!scan.empty());
        return fonts;
    }

    private static Length parseFontSize(String val) throws SAXException {
        Length size = (Length) fontSizeKeywords.get(val);
        if (size == null) {
            return parseLength(val);
        }
        return size;
    }

    private static Integer parseFontWeight(String val) throws SAXException {
        Integer wt = (Integer) fontWeightKeywords.get(val);
        if (wt != null) {
            return wt;
        }
        throw new SAXException("Invalid font-weight property: " + val);
    }

    private static FontStyle parseFontStyle(String val) throws SAXException {
        FontStyle fs = (FontStyle) fontStyleKeywords.get(val);
        if (fs != null) {
            return fs;
        }
        throw new SAXException("Invalid font-style property: " + val);
    }

    private static TextDecoration parseTextDecoration(String val) throws SAXException {
        if ("none".equals(val)) {
            return TextDecoration.None;
        }
        if ("underline".equals(val)) {
            return TextDecoration.Underline;
        }
        if ("overline".equals(val)) {
            return TextDecoration.Overline;
        }
        if ("line-through".equals(val)) {
            return TextDecoration.LineThrough;
        }
        if ("blink".equals(val)) {
            return TextDecoration.Blink;
        }
        throw new SAXException("Invalid text-decoration property: " + val);
    }

    private static TextDirection parseTextDirection(String val) throws SAXException {
        if ("ltr".equals(val)) {
            return TextDirection.LTR;
        }
        if ("rtl".equals(val)) {
            return TextDirection.RTL;
        }
        throw new SAXException("Invalid direction property: " + val);
    }

    private static FillRule parseFillRule(String val) throws SAXException {
        if ("nonzero".equals(val)) {
            return FillRule.NonZero;
        }
        if ("evenodd".equals(val)) {
            return FillRule.EvenOdd;
        }
        throw new SAXException("Invalid fill-rule property: " + val);
    }

    private static LineCaps parseStrokeLineCap(String val) throws SAXException {
        if ("butt".equals(val)) {
            return LineCaps.Butt;
        }
        if ("round".equals(val)) {
            return LineCaps.Round;
        }
        if ("square".equals(val)) {
            return LineCaps.Square;
        }
        throw new SAXException("Invalid stroke-linecap property: " + val);
    }

    private static LineJoin parseStrokeLineJoin(String val) throws SAXException {
        if ("miter".equals(val)) {
            return LineJoin.Miter;
        }
        if ("round".equals(val)) {
            return LineJoin.Round;
        }
        if ("bevel".equals(val)) {
            return LineJoin.Bevel;
        }
        throw new SAXException("Invalid stroke-linejoin property: " + val);
    }

    private static Length[] parseStrokeDashArray(String val) throws SAXException {
        TextScanner scan = new TextScanner(val);
        scan.skipWhitespace();
        if (scan.empty()) {
            return null;
        }
        Length dash = scan.nextLength();
        if (dash == null) {
            return null;
        }
        if (dash.isNegative()) {
            throw new SAXException("Invalid stroke-dasharray. Dash segemnts cannot be negative: " + val);
        }
        float sum = dash.floatValue();
        List<Length> dashes = new ArrayList<>();
        dashes.add(dash);
        while (!scan.empty()) {
            scan.skipCommaWhitespace();
            Length dash2 = scan.nextLength();
            if (dash2 == null) {
                throw new SAXException("Invalid stroke-dasharray. Non-Length content found: " + val);
            } else if (dash2.isNegative()) {
                throw new SAXException("Invalid stroke-dasharray. Dash segemnts cannot be negative: " + val);
            } else {
                dashes.add(dash2);
                sum += dash2.floatValue();
            }
        }
        if (sum != 0.0f) {
            return (Length[]) dashes.toArray(new Length[dashes.size()]);
        }
        return null;
    }

    private static TextAnchor parseTextAnchor(String val) throws SAXException {
        if ("start".equals(val)) {
            return TextAnchor.Start;
        }
        if ("middle".equals(val)) {
            return TextAnchor.Middle;
        }
        if ("end".equals(val)) {
            return TextAnchor.End;
        }
        throw new SAXException("Invalid text-anchor property: " + val);
    }

    private static Boolean parseOverflow(String val) throws SAXException {
        if (InternalLogger.EVENT_PARAM_VIEW_STATE_VISIBLE.equals(val) || "auto".equals(val)) {
            return Boolean.TRUE;
        }
        if (PushNotificationConstants.EXTRA_IS_HIDDEN.equals(val) || "scroll".equals(val)) {
            return Boolean.FALSE;
        }
        throw new SAXException("Invalid toverflow property: " + val);
    }

    private static CSSClipRect parseClip(String val) throws SAXException {
        if ("auto".equals(val)) {
            return null;
        }
        if (!val.toLowerCase(Locale.US).startsWith("rect(")) {
            throw new SAXException("Invalid clip attribute shape. Only rect() is supported.");
        }
        TextScanner scan = new TextScanner(val.substring(5));
        scan.skipWhitespace();
        Length top = parseLengthOrAuto(scan);
        scan.skipCommaWhitespace();
        Length right = parseLengthOrAuto(scan);
        scan.skipCommaWhitespace();
        Length bottom = parseLengthOrAuto(scan);
        scan.skipCommaWhitespace();
        Length left = parseLengthOrAuto(scan);
        scan.skipWhitespace();
        if (scan.consume(')')) {
            return new CSSClipRect(top, right, bottom, left);
        }
        throw new SAXException("Bad rect() clip definition: " + val);
    }

    private static Length parseLengthOrAuto(TextScanner scan) {
        if (scan.consume("auto")) {
            return new Length(0.0f);
        }
        return scan.nextLength();
    }

    private static VectorEffect parseVectorEffect(String val) throws SAXException {
        if ("none".equals(val)) {
            return VectorEffect.None;
        }
        if ("non-scaling-stroke".equals(val)) {
            return VectorEffect.NonScalingStroke;
        }
        throw new SAXException("Invalid vector-effect property: " + val);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x001d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.caverock.androidsvg.SVG.PathDefinition parsePath(java.lang.String r29) throws org.xml.sax.SAXException {
        /*
            com.caverock.androidsvg.SVGParser$TextScanner r20 = new com.caverock.androidsvg.SVGParser$TextScanner
            r0 = r20
            r1 = r29
            r0.<init>(r1)
            r17 = 63
            r10 = 0
            r11 = 0
            r15 = 0
            r16 = 0
            r13 = 0
            r14 = 0
            com.caverock.androidsvg.SVG$PathDefinition r2 = new com.caverock.androidsvg.SVG$PathDefinition
            r2.<init>()
            boolean r3 = r20.empty()
            if (r3 == 0) goto L_0x001e
        L_0x001d:
            return r2
        L_0x001e:
            java.lang.Integer r3 = r20.nextChar()
            int r17 = r3.intValue()
            r3 = 77
            r0 = r17
            if (r0 == r3) goto L_0x0032
            r3 = 109(0x6d, float:1.53E-43)
            r0 = r17
            if (r0 != r3) goto L_0x001d
        L_0x0032:
            r20.skipWhitespace()
            switch(r17) {
                case 65: goto L_0x0039;
                case 67: goto L_0x0184;
                case 72: goto L_0x02ef;
                case 76: goto L_0x0126;
                case 77: goto L_0x009c;
                case 81: goto L_0x0379;
                case 83: goto L_0x023d;
                case 84: goto L_0x0408;
                case 86: goto L_0x0334;
                case 90: goto L_0x02e4;
                case 97: goto L_0x0039;
                case 99: goto L_0x0184;
                case 104: goto L_0x02ef;
                case 108: goto L_0x0126;
                case 109: goto L_0x009c;
                case 113: goto L_0x0379;
                case 115: goto L_0x023d;
                case 116: goto L_0x0408;
                case 118: goto L_0x0334;
                case 122: goto L_0x02e4;
                default: goto L_0x0038;
            }
        L_0x0038:
            goto L_0x001d
        L_0x0039:
            java.lang.Float r18 = r20.nextFloat()
            r0 = r20
            r1 = r18
            java.lang.Float r19 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r19
            java.lang.Float r25 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r25
            java.lang.Boolean r12 = r0.checkedNextFlag(r1)
            r0 = r20
            java.lang.Boolean r21 = r0.checkedNextFlag(r12)
            java.lang.Float r22 = r20.checkedNextFloat(r21)
            r0 = r20
            r1 = r22
            java.lang.Float r26 = r0.checkedNextFloat(r1)
            if (r26 == 0) goto L_0x007b
            float r3 = r18.floatValue()
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 < 0) goto L_0x007b
            float r3 = r19.floatValue()
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 >= 0) goto L_0x0485
        L_0x007b:
            java.lang.String r3 = "SVGParser"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Bad path coords for "
            r4.<init>(r5)
            r0 = r17
            char r5 = (char) r0
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " path segment"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            goto L_0x001d
        L_0x009c:
            java.lang.Float r22 = r20.nextFloat()
            r0 = r20
            r1 = r22
            java.lang.Float r26 = r0.checkedNextFloat(r1)
            if (r26 != 0) goto L_0x00cc
            java.lang.String r3 = "SVGParser"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Bad path coords for "
            r4.<init>(r5)
            r0 = r17
            char r5 = (char) r0
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " path segment"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            goto L_0x001d
        L_0x00cc:
            r3 = 109(0x6d, float:1.53E-43)
            r0 = r17
            if (r0 != r3) goto L_0x00ea
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x00ea
            float r3 = r22.floatValue()
            float r3 = r3 + r10
            java.lang.Float r22 = java.lang.Float.valueOf(r3)
            float r3 = r26.floatValue()
            float r3 = r3 + r11
            java.lang.Float r26 = java.lang.Float.valueOf(r3)
        L_0x00ea:
            float r3 = r22.floatValue()
            float r4 = r26.floatValue()
            r2.moveTo(r3, r4)
            float r13 = r22.floatValue()
            r15 = r13
            r10 = r13
            float r14 = r26.floatValue()
            r16 = r14
            r11 = r14
            r3 = 109(0x6d, float:1.53E-43)
            r0 = r17
            if (r0 != r3) goto L_0x0123
            r17 = 108(0x6c, float:1.51E-43)
        L_0x010a:
            r20.skipCommaWhitespace()
            boolean r3 = r20.empty()
            if (r3 != 0) goto L_0x001d
            boolean r3 = r20.hasLetter()
            if (r3 == 0) goto L_0x0032
            java.lang.Integer r3 = r20.nextChar()
            int r17 = r3.intValue()
            goto L_0x0032
        L_0x0123:
            r17 = 76
            goto L_0x010a
        L_0x0126:
            java.lang.Float r22 = r20.nextFloat()
            r0 = r20
            r1 = r22
            java.lang.Float r26 = r0.checkedNextFloat(r1)
            if (r26 != 0) goto L_0x0156
            java.lang.String r3 = "SVGParser"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Bad path coords for "
            r4.<init>(r5)
            r0 = r17
            char r5 = (char) r0
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " path segment"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            goto L_0x001d
        L_0x0156:
            r3 = 108(0x6c, float:1.51E-43)
            r0 = r17
            if (r0 != r3) goto L_0x016e
            float r3 = r22.floatValue()
            float r3 = r3 + r10
            java.lang.Float r22 = java.lang.Float.valueOf(r3)
            float r3 = r26.floatValue()
            float r3 = r3 + r11
            java.lang.Float r26 = java.lang.Float.valueOf(r3)
        L_0x016e:
            float r3 = r22.floatValue()
            float r4 = r26.floatValue()
            r2.lineTo(r3, r4)
            float r13 = r22.floatValue()
            r10 = r13
            float r14 = r26.floatValue()
            r11 = r14
            goto L_0x010a
        L_0x0184:
            java.lang.Float r23 = r20.nextFloat()
            r0 = r20
            r1 = r23
            java.lang.Float r27 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r27
            java.lang.Float r24 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r24
            java.lang.Float r28 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r28
            java.lang.Float r22 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r22
            java.lang.Float r26 = r0.checkedNextFloat(r1)
            if (r26 != 0) goto L_0x01d4
            java.lang.String r3 = "SVGParser"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Bad path coords for "
            r4.<init>(r5)
            r0 = r17
            char r5 = (char) r0
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " path segment"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            goto L_0x001d
        L_0x01d4:
            r3 = 99
            r0 = r17
            if (r0 != r3) goto L_0x0210
            float r3 = r22.floatValue()
            float r3 = r3 + r10
            java.lang.Float r22 = java.lang.Float.valueOf(r3)
            float r3 = r26.floatValue()
            float r3 = r3 + r11
            java.lang.Float r26 = java.lang.Float.valueOf(r3)
            float r3 = r23.floatValue()
            float r3 = r3 + r10
            java.lang.Float r23 = java.lang.Float.valueOf(r3)
            float r3 = r27.floatValue()
            float r3 = r3 + r11
            java.lang.Float r27 = java.lang.Float.valueOf(r3)
            float r3 = r24.floatValue()
            float r3 = r3 + r10
            java.lang.Float r24 = java.lang.Float.valueOf(r3)
            float r3 = r28.floatValue()
            float r3 = r3 + r11
            java.lang.Float r28 = java.lang.Float.valueOf(r3)
        L_0x0210:
            float r3 = r23.floatValue()
            float r4 = r27.floatValue()
            float r5 = r24.floatValue()
            float r6 = r28.floatValue()
            float r7 = r22.floatValue()
            float r8 = r26.floatValue()
            r2.cubicTo(r3, r4, r5, r6, r7, r8)
            float r13 = r24.floatValue()
            float r14 = r28.floatValue()
            float r10 = r22.floatValue()
            float r11 = r26.floatValue()
            goto L_0x010a
        L_0x023d:
            r3 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 * r10
            float r3 = r3 - r13
            java.lang.Float r23 = java.lang.Float.valueOf(r3)
            r3 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 * r11
            float r3 = r3 - r14
            java.lang.Float r27 = java.lang.Float.valueOf(r3)
            java.lang.Float r24 = r20.nextFloat()
            r0 = r20
            r1 = r24
            java.lang.Float r28 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r28
            java.lang.Float r22 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r22
            java.lang.Float r26 = r0.checkedNextFloat(r1)
            if (r26 != 0) goto L_0x028d
            java.lang.String r3 = "SVGParser"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Bad path coords for "
            r4.<init>(r5)
            r0 = r17
            char r5 = (char) r0
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " path segment"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            goto L_0x001d
        L_0x028d:
            r3 = 115(0x73, float:1.61E-43)
            r0 = r17
            if (r0 != r3) goto L_0x02b7
            float r3 = r22.floatValue()
            float r3 = r3 + r10
            java.lang.Float r22 = java.lang.Float.valueOf(r3)
            float r3 = r26.floatValue()
            float r3 = r3 + r11
            java.lang.Float r26 = java.lang.Float.valueOf(r3)
            float r3 = r24.floatValue()
            float r3 = r3 + r10
            java.lang.Float r24 = java.lang.Float.valueOf(r3)
            float r3 = r28.floatValue()
            float r3 = r3 + r11
            java.lang.Float r28 = java.lang.Float.valueOf(r3)
        L_0x02b7:
            float r3 = r23.floatValue()
            float r4 = r27.floatValue()
            float r5 = r24.floatValue()
            float r6 = r28.floatValue()
            float r7 = r22.floatValue()
            float r8 = r26.floatValue()
            r2.cubicTo(r3, r4, r5, r6, r7, r8)
            float r13 = r24.floatValue()
            float r14 = r28.floatValue()
            float r10 = r22.floatValue()
            float r11 = r26.floatValue()
            goto L_0x010a
        L_0x02e4:
            r2.close()
            r13 = r15
            r10 = r15
            r14 = r16
            r11 = r16
            goto L_0x010a
        L_0x02ef:
            java.lang.Float r22 = r20.nextFloat()
            if (r22 != 0) goto L_0x0317
            java.lang.String r3 = "SVGParser"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Bad path coords for "
            r4.<init>(r5)
            r0 = r17
            char r5 = (char) r0
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " path segment"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            goto L_0x001d
        L_0x0317:
            r3 = 104(0x68, float:1.46E-43)
            r0 = r17
            if (r0 != r3) goto L_0x0326
            float r3 = r22.floatValue()
            float r3 = r3 + r10
            java.lang.Float r22 = java.lang.Float.valueOf(r3)
        L_0x0326:
            float r3 = r22.floatValue()
            r2.lineTo(r3, r11)
            float r13 = r22.floatValue()
            r10 = r13
            goto L_0x010a
        L_0x0334:
            java.lang.Float r26 = r20.nextFloat()
            if (r26 != 0) goto L_0x035c
            java.lang.String r3 = "SVGParser"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Bad path coords for "
            r4.<init>(r5)
            r0 = r17
            char r5 = (char) r0
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " path segment"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            goto L_0x001d
        L_0x035c:
            r3 = 118(0x76, float:1.65E-43)
            r0 = r17
            if (r0 != r3) goto L_0x036b
            float r3 = r26.floatValue()
            float r3 = r3 + r11
            java.lang.Float r26 = java.lang.Float.valueOf(r3)
        L_0x036b:
            float r3 = r26.floatValue()
            r2.lineTo(r10, r3)
            float r14 = r26.floatValue()
            r11 = r14
            goto L_0x010a
        L_0x0379:
            java.lang.Float r23 = r20.nextFloat()
            r0 = r20
            r1 = r23
            java.lang.Float r27 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r27
            java.lang.Float r22 = r0.checkedNextFloat(r1)
            r0 = r20
            r1 = r22
            java.lang.Float r26 = r0.checkedNextFloat(r1)
            if (r26 != 0) goto L_0x03b9
            java.lang.String r3 = "SVGParser"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Bad path coords for "
            r4.<init>(r5)
            r0 = r17
            char r5 = (char) r0
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " path segment"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            goto L_0x001d
        L_0x03b9:
            r3 = 113(0x71, float:1.58E-43)
            r0 = r17
            if (r0 != r3) goto L_0x03e3
            float r3 = r22.floatValue()
            float r3 = r3 + r10
            java.lang.Float r22 = java.lang.Float.valueOf(r3)
            float r3 = r26.floatValue()
            float r3 = r3 + r11
            java.lang.Float r26 = java.lang.Float.valueOf(r3)
            float r3 = r23.floatValue()
            float r3 = r3 + r10
            java.lang.Float r23 = java.lang.Float.valueOf(r3)
            float r3 = r27.floatValue()
            float r3 = r3 + r11
            java.lang.Float r27 = java.lang.Float.valueOf(r3)
        L_0x03e3:
            float r3 = r23.floatValue()
            float r4 = r27.floatValue()
            float r5 = r22.floatValue()
            float r6 = r26.floatValue()
            r2.quadTo(r3, r4, r5, r6)
            float r13 = r23.floatValue()
            float r14 = r27.floatValue()
            float r10 = r22.floatValue()
            float r11 = r26.floatValue()
            goto L_0x010a
        L_0x0408:
            r3 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 * r10
            float r3 = r3 - r13
            java.lang.Float r23 = java.lang.Float.valueOf(r3)
            r3 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 * r11
            float r3 = r3 - r14
            java.lang.Float r27 = java.lang.Float.valueOf(r3)
            java.lang.Float r22 = r20.nextFloat()
            r0 = r20
            r1 = r22
            java.lang.Float r26 = r0.checkedNextFloat(r1)
            if (r26 != 0) goto L_0x0448
            java.lang.String r3 = "SVGParser"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Bad path coords for "
            r4.<init>(r5)
            r0 = r17
            char r5 = (char) r0
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " path segment"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            goto L_0x001d
        L_0x0448:
            r3 = 116(0x74, float:1.63E-43)
            r0 = r17
            if (r0 != r3) goto L_0x0460
            float r3 = r22.floatValue()
            float r3 = r3 + r10
            java.lang.Float r22 = java.lang.Float.valueOf(r3)
            float r3 = r26.floatValue()
            float r3 = r3 + r11
            java.lang.Float r26 = java.lang.Float.valueOf(r3)
        L_0x0460:
            float r3 = r23.floatValue()
            float r4 = r27.floatValue()
            float r5 = r22.floatValue()
            float r6 = r26.floatValue()
            r2.quadTo(r3, r4, r5, r6)
            float r13 = r23.floatValue()
            float r14 = r27.floatValue()
            float r10 = r22.floatValue()
            float r11 = r26.floatValue()
            goto L_0x010a
        L_0x0485:
            r3 = 97
            r0 = r17
            if (r0 != r3) goto L_0x049d
            float r3 = r22.floatValue()
            float r3 = r3 + r10
            java.lang.Float r22 = java.lang.Float.valueOf(r3)
            float r3 = r26.floatValue()
            float r3 = r3 + r11
            java.lang.Float r26 = java.lang.Float.valueOf(r3)
        L_0x049d:
            float r3 = r18.floatValue()
            float r4 = r19.floatValue()
            float r5 = r25.floatValue()
            boolean r6 = r12.booleanValue()
            boolean r7 = r21.booleanValue()
            float r8 = r22.floatValue()
            float r9 = r26.floatValue()
            r2.arcTo(r3, r4, r5, r6, r7, r8, r9)
            float r13 = r22.floatValue()
            r10 = r13
            float r14 = r26.floatValue()
            r11 = r14
            goto L_0x010a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SVGParser.parsePath(java.lang.String):com.caverock.androidsvg.SVG$PathDefinition");
    }

    private static Set<String> parseRequiredFeatures(String val) throws SAXException {
        TextScanner scan = new TextScanner(val);
        HashSet<String> result = new HashSet<>();
        while (!scan.empty()) {
            String feature = scan.nextToken();
            if (feature.startsWith("http://www.w3.org/TR/SVG11/feature#")) {
                result.add(feature.substring("http://www.w3.org/TR/SVG11/feature#".length()));
            } else {
                result.add("UNSUPPORTED");
            }
            scan.skipWhitespace();
        }
        return result;
    }

    private static Set<String> parseSystemLanguage(String val) throws SAXException {
        TextScanner scan = new TextScanner(val);
        HashSet<String> result = new HashSet<>();
        while (!scan.empty()) {
            String language = scan.nextToken();
            int hyphenPos = language.indexOf(45);
            if (hyphenPos != -1) {
                language = language.substring(0, hyphenPos);
            }
            result.add(new Locale(language, "", "").getLanguage());
            scan.skipWhitespace();
        }
        return result;
    }

    private static Set<String> parseRequiredFormats(String val) throws SAXException {
        TextScanner scan = new TextScanner(val);
        HashSet<String> result = new HashSet<>();
        while (!scan.empty()) {
            result.add(scan.nextToken());
            scan.skipWhitespace();
        }
        return result;
    }

    private static String parseFunctionalIRI(String val, String attrName) throws SAXException {
        if (val.equals("none")) {
            return null;
        }
        if (val.startsWith("url(") && val.endsWith(")")) {
            return val.substring(4, val.length() - 1).trim();
        }
        throw new SAXException("Bad " + attrName + " attribute. Expected \"none\" or \"url()\" format");
    }

    private void style(Attributes attributes) throws SAXException {
        debug("<style>", new Object[0]);
        if (this.currentElement == null) {
            throw new SAXException("Invalid document. Root element must be <svg>");
        }
        boolean isTextCSS = true;
        String media = "all";
        for (int i = 0; i < attributes.getLength(); i++) {
            String val = attributes.getValue(i).trim();
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVGParser$SVGAttr()[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 39:
                    media = val;
                    break;
                case 78:
                    isTextCSS = val.equals("text/css");
                    break;
            }
        }
        if (!isTextCSS || !CSSParser.mediaMatches(media, MediaType.screen)) {
            this.ignoring = true;
            this.ignoreDepth = 1;
            return;
        }
        this.inStyleElement = true;
    }

    private void parseCSSStyleSheet(String sheet) throws SAXException {
        this.svgDocument.addCSSRules(new CSSParser(MediaType.screen).parse(sheet));
    }
}
