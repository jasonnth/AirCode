package com.caverock.androidsvg;

import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.xml.sax.SAXException;

public class CSSParser {
    private boolean inMediaRule = false;
    private MediaType rendererMediaType = null;

    public static class Attrib {
        public String name = null;
        public AttribOp operation;
        public String value = null;

        public Attrib(String name2, AttribOp op, String value2) {
            this.name = name2;
            this.operation = op;
            this.value = value2;
        }
    }

    private enum AttribOp {
        EXISTS,
        EQUALS,
        INCLUDES,
        DASHMATCH
    }

    private static class CSSTextScanner extends TextScanner {
        public CSSTextScanner(String input) {
            super(input.replaceAll("(?s)/\\*.*?\\*/", ""));
        }

        public String nextIdentifier() {
            int end = scanForIdentifier();
            if (end == this.position) {
                return null;
            }
            String substring = this.input.substring(this.position, end);
            this.position = end;
            return substring;
        }

        private int scanForIdentifier() {
            if (empty()) {
                return this.position;
            }
            int start = this.position;
            int lastValidPos = this.position;
            int ch = this.input.charAt(this.position);
            if (ch == 45) {
                ch = advanceChar();
            }
            if ((ch >= 65 && ch <= 90) || ((ch >= 97 && ch <= 122) || ch == 95)) {
                int ch2 = advanceChar();
                while (true) {
                    if ((ch2 < 65 || ch2 > 90) && ((ch2 < 97 || ch2 > 122) && !((ch2 >= 48 && ch2 <= 57) || ch2 == 45 || ch2 == 95))) {
                        break;
                    }
                    ch2 = advanceChar();
                }
                lastValidPos = this.position;
            }
            this.position = start;
            return lastValidPos;
        }

        public boolean nextSimpleSelector(Selector selector) throws SAXException {
            if (empty()) {
                return false;
            }
            int start = this.position;
            Combinator combinator = null;
            SimpleSelector selectorPart = null;
            if (!selector.isEmpty()) {
                if (consume('>')) {
                    combinator = Combinator.CHILD;
                    skipWhitespace();
                } else if (consume('+')) {
                    combinator = Combinator.FOLLOWS;
                    skipWhitespace();
                }
            }
            if (consume('*')) {
                selectorPart = new SimpleSelector(combinator, null);
            } else {
                String tag = nextIdentifier();
                if (tag != null) {
                    selectorPart = new SimpleSelector(combinator, tag);
                    selector.addedElement();
                }
            }
            while (true) {
                if (empty()) {
                    break;
                } else if (consume('.')) {
                    if (selectorPart == null) {
                        selectorPart = new SimpleSelector(combinator, null);
                    }
                    String value = nextIdentifier();
                    if (value == null) {
                        throw new SAXException("Invalid \".class\" selector in <style> element");
                    }
                    selectorPart.addAttrib("class", AttribOp.EQUALS, value);
                    selector.addedAttributeOrPseudo();
                } else {
                    if (consume('#')) {
                        if (selectorPart == null) {
                            selectorPart = new SimpleSelector(combinator, null);
                        }
                        String value2 = nextIdentifier();
                        if (value2 == null) {
                            throw new SAXException("Invalid \"#id\" selector in <style> element");
                        }
                        selectorPart.addAttrib("id", AttribOp.EQUALS, value2);
                        selector.addedIdAttribute();
                    }
                    if (selectorPart == null) {
                        break;
                    } else if (consume('[')) {
                        skipWhitespace();
                        String attrName = nextIdentifier();
                        String attrValue = null;
                        if (attrName == null) {
                            throw new SAXException("Invalid attribute selector in <style> element");
                        }
                        skipWhitespace();
                        AttribOp op = null;
                        if (consume('=')) {
                            op = AttribOp.EQUALS;
                        } else if (consume("~=")) {
                            op = AttribOp.INCLUDES;
                        } else if (consume("|=")) {
                            op = AttribOp.DASHMATCH;
                        }
                        if (op != null) {
                            skipWhitespace();
                            attrValue = nextAttribValue();
                            if (attrValue == null) {
                                throw new SAXException("Invalid attribute selector in <style> element");
                            }
                            skipWhitespace();
                        }
                        if (!consume(']')) {
                            throw new SAXException("Invalid attribute selector in <style> element");
                        }
                        if (op == null) {
                            op = AttribOp.EXISTS;
                        }
                        selectorPart.addAttrib(attrName, op, attrValue);
                        selector.addedAttributeOrPseudo();
                    } else if (consume(':')) {
                        int pseudoStart = this.position;
                        if (nextIdentifier() != null) {
                            if (consume('(')) {
                                skipWhitespace();
                                if (nextIdentifier() != null) {
                                    skipWhitespace();
                                    if (!consume(')')) {
                                        this.position = pseudoStart - 1;
                                    }
                                }
                            }
                            selectorPart.addPseudo(this.input.substring(pseudoStart, this.position));
                            selector.addedAttributeOrPseudo();
                        }
                    }
                }
            }
            if (selectorPart != null) {
                selector.add(selectorPart);
                return true;
            }
            this.position = start;
            return false;
        }

        private String nextAttribValue() {
            if (empty()) {
                return null;
            }
            String result = nextQuotedString();
            return result == null ? nextIdentifier() : result;
        }

        public String nextPropertyValue() {
            if (empty()) {
                return null;
            }
            int start = this.position;
            int lastValidPos = this.position;
            int ch = this.input.charAt(this.position);
            while (ch != -1 && ch != 59 && ch != 125 && ch != 33 && !isEOL(ch)) {
                if (!isWhitespace(ch)) {
                    lastValidPos = this.position + 1;
                }
                ch = advanceChar();
            }
            if (this.position > start) {
                return this.input.substring(start, lastValidPos);
            }
            this.position = start;
            return null;
        }
    }

    private enum Combinator {
        DESCENDANT,
        CHILD,
        FOLLOWS
    }

    public enum MediaType {
        all,
        aural,
        braille,
        embossed,
        handheld,
        print,
        projection,
        screen,
        tty,
        tv
    }

    public static class Rule {
        public Selector selector = null;
        public Style style = null;

        public Rule(Selector selector2, Style style2) {
            this.selector = selector2;
            this.style = style2;
        }

        public String toString() {
            return this.selector + " {}";
        }
    }

    public static class Ruleset {
        private List<Rule> rules = null;

        public void add(Rule rule) {
            if (this.rules == null) {
                this.rules = new ArrayList();
            }
            for (int i = 0; i < this.rules.size(); i++) {
                if (((Rule) this.rules.get(i)).selector.specificity > rule.selector.specificity) {
                    this.rules.add(i, rule);
                    return;
                }
            }
            this.rules.add(rule);
        }

        public void addAll(Ruleset rules2) {
            if (rules2.rules != null) {
                if (this.rules == null) {
                    this.rules = new ArrayList(rules2.rules.size());
                }
                for (Rule rule : rules2.rules) {
                    this.rules.add(rule);
                }
            }
        }

        public List<Rule> getRules() {
            return this.rules;
        }

        public boolean isEmpty() {
            return this.rules == null || this.rules.isEmpty();
        }

        public String toString() {
            if (this.rules == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (Rule rule : this.rules) {
                sb.append(rule.toString()).append(10);
            }
            return sb.toString();
        }
    }

    public static class Selector {
        public List<SimpleSelector> selector = null;
        public int specificity = 0;

        public void add(SimpleSelector part) {
            if (this.selector == null) {
                this.selector = new ArrayList();
            }
            this.selector.add(part);
        }

        public int size() {
            if (this.selector == null) {
                return 0;
            }
            return this.selector.size();
        }

        public SimpleSelector get(int i) {
            return (SimpleSelector) this.selector.get(i);
        }

        public boolean isEmpty() {
            if (this.selector == null) {
                return true;
            }
            return this.selector.isEmpty();
        }

        public void addedIdAttribute() {
            this.specificity += 10000;
        }

        public void addedAttributeOrPseudo() {
            this.specificity += 100;
        }

        public void addedElement() {
            this.specificity++;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (SimpleSelector sel : this.selector) {
                sb.append(sel).append(' ');
            }
            return sb.append('(').append(this.specificity).append(')').toString();
        }
    }

    private static class SimpleSelector {
        private static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$CSSParser$AttribOp;
        public List<Attrib> attribs = null;
        public Combinator combinator = null;
        public List<String> pseudos = null;
        public String tag = null;

        static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$CSSParser$AttribOp() {
            int[] iArr = $SWITCH_TABLE$com$caverock$androidsvg$CSSParser$AttribOp;
            if (iArr == null) {
                iArr = new int[AttribOp.values().length];
                try {
                    iArr[AttribOp.DASHMATCH.ordinal()] = 4;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[AttribOp.EQUALS.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[AttribOp.EXISTS.ordinal()] = 1;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[AttribOp.INCLUDES.ordinal()] = 3;
                } catch (NoSuchFieldError e4) {
                }
                $SWITCH_TABLE$com$caverock$androidsvg$CSSParser$AttribOp = iArr;
            }
            return iArr;
        }

        public SimpleSelector(Combinator combinator2, String tag2) {
            if (combinator2 == null) {
                combinator2 = Combinator.DESCENDANT;
            }
            this.combinator = combinator2;
            this.tag = tag2;
        }

        public void addAttrib(String attrName, AttribOp op, String attrValue) {
            if (this.attribs == null) {
                this.attribs = new ArrayList();
            }
            this.attribs.add(new Attrib(attrName, op, attrValue));
        }

        public void addPseudo(String pseudo) {
            if (this.pseudos == null) {
                this.pseudos = new ArrayList();
            }
            this.pseudos.add(pseudo);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.combinator == Combinator.CHILD) {
                sb.append("> ");
            } else if (this.combinator == Combinator.FOLLOWS) {
                sb.append("+ ");
            }
            sb.append(this.tag == null ? "*" : this.tag);
            if (this.attribs != null) {
                for (Attrib attr : this.attribs) {
                    sb.append('[').append(attr.name);
                    switch ($SWITCH_TABLE$com$caverock$androidsvg$CSSParser$AttribOp()[attr.operation.ordinal()]) {
                        case 2:
                            sb.append('=').append(attr.value);
                            break;
                        case 3:
                            sb.append("~=").append(attr.value);
                            break;
                        case 4:
                            sb.append("|=").append(attr.value);
                            break;
                    }
                    sb.append(']');
                }
            }
            if (this.pseudos != null) {
                for (String pseu : this.pseudos) {
                    sb.append(':').append(pseu);
                }
            }
            return sb.toString();
        }
    }

    public CSSParser(MediaType rendererMediaType2) {
        this.rendererMediaType = rendererMediaType2;
    }

    public Ruleset parse(String sheet) throws SAXException {
        CSSTextScanner scan = new CSSTextScanner(sheet);
        scan.skipWhitespace();
        return parseRuleset(scan);
    }

    public static boolean mediaMatches(String mediaListStr, MediaType rendererMediaType2) throws SAXException {
        CSSTextScanner scan = new CSSTextScanner(mediaListStr);
        scan.skipWhitespace();
        List<MediaType> mediaList = parseMediaList(scan);
        if (scan.empty()) {
            return mediaMatches(mediaList, rendererMediaType2);
        }
        throw new SAXException("Invalid @media type list");
    }

    private static void warn(String format, Object... args) {
        Log.w("AndroidSVG CSSParser", String.format(format, args));
    }

    private static boolean mediaMatches(List<MediaType> mediaList, MediaType rendererMediaType2) {
        for (MediaType type : mediaList) {
            if (type != MediaType.all) {
                if (type == rendererMediaType2) {
                }
            }
            return true;
        }
        return false;
    }

    private static List<MediaType> parseMediaList(CSSTextScanner scan) throws SAXException {
        ArrayList<MediaType> typeList = new ArrayList<>();
        while (!scan.empty()) {
            try {
                typeList.add(MediaType.valueOf(scan.nextToken(',')));
                if (!scan.skipCommaWhitespace()) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                throw new SAXException("Invalid @media type list");
            }
        }
        return typeList;
    }

    private void parseAtRule(Ruleset ruleset, CSSTextScanner scan) throws SAXException {
        String atKeyword = scan.nextIdentifier();
        scan.skipWhitespace();
        if (atKeyword == null) {
            throw new SAXException("Invalid '@' rule in <style> element");
        }
        if (this.inMediaRule || !atKeyword.equals(ShareConstants.WEB_DIALOG_PARAM_MEDIA)) {
            warn("Ignoring @%s rule", atKeyword);
            skipAtRule(scan);
        } else {
            List<MediaType> mediaList = parseMediaList(scan);
            if (!scan.consume('{')) {
                throw new SAXException("Invalid @media rule: missing rule set");
            }
            scan.skipWhitespace();
            if (mediaMatches(mediaList, this.rendererMediaType)) {
                this.inMediaRule = true;
                ruleset.addAll(parseRuleset(scan));
                this.inMediaRule = false;
            } else {
                parseRuleset(scan);
            }
            if (!scan.consume('}')) {
                throw new SAXException("Invalid @media rule: expected '}' at end of rule set");
            }
        }
        scan.skipWhitespace();
    }

    private void skipAtRule(CSSTextScanner scan) {
        int depth = 0;
        while (!scan.empty()) {
            int ch = scan.nextChar().intValue();
            if (ch != 59 || depth != 0) {
                if (ch == 123) {
                    depth++;
                } else if (ch == 125 && depth > 0) {
                    depth--;
                    if (depth == 0) {
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

    private Ruleset parseRuleset(CSSTextScanner scan) throws SAXException {
        Ruleset ruleset = new Ruleset();
        while (!scan.empty()) {
            if (!scan.consume("<!--") && !scan.consume("-->")) {
                if (!scan.consume('@')) {
                    if (!parseRule(ruleset, scan)) {
                        break;
                    }
                } else {
                    parseAtRule(ruleset, scan);
                }
            }
        }
        return ruleset;
    }

    private boolean parseRule(Ruleset ruleset, CSSTextScanner scan) throws SAXException {
        List<Selector> selectors = parseSelectorGroup(scan);
        if (selectors == null || selectors.isEmpty()) {
            return false;
        }
        if (!scan.consume('{')) {
            throw new SAXException("Malformed rule block in <style> element: missing '{'");
        }
        scan.skipWhitespace();
        Style ruleStyle = parseDeclarations(scan);
        scan.skipWhitespace();
        for (Selector selector : selectors) {
            ruleset.add(new Rule(selector, ruleStyle));
        }
        return true;
    }

    private List<Selector> parseSelectorGroup(CSSTextScanner scan) throws SAXException {
        if (scan.empty()) {
            return null;
        }
        ArrayList<Selector> selectorGroup = new ArrayList<>(1);
        Selector selector = new Selector();
        while (!scan.empty() && scan.nextSimpleSelector(selector)) {
            if (scan.skipCommaWhitespace()) {
                selectorGroup.add(selector);
                selector = new Selector();
            }
        }
        if (selector.isEmpty()) {
            return selectorGroup;
        }
        selectorGroup.add(selector);
        return selectorGroup;
    }

    private Style parseDeclarations(CSSTextScanner scan) throws SAXException {
        Style ruleStyle = new Style();
        do {
            String propertyName = scan.nextIdentifier();
            scan.skipWhitespace();
            if (scan.consume(':')) {
                scan.skipWhitespace();
                String propertyValue = scan.nextPropertyValue();
                if (propertyValue == null) {
                    break;
                }
                scan.skipWhitespace();
                if (scan.consume('!')) {
                    scan.skipWhitespace();
                    if (!scan.consume("important")) {
                        throw new SAXException("Malformed rule set in <style> element: found unexpected '!'");
                    }
                    scan.skipWhitespace();
                }
                scan.consume(';');
                SVGParser.processStyleProperty(ruleStyle, propertyName, propertyValue);
                scan.skipWhitespace();
                if (scan.consume('}')) {
                    return ruleStyle;
                }
            } else {
                break;
            }
        } while (!scan.empty());
        throw new SAXException("Malformed rule set in <style> element");
    }

    protected static List<String> parseClassAttribute(String val) throws SAXException {
        CSSTextScanner scan = new CSSTextScanner(val);
        List<String> classNameList = null;
        while (!scan.empty()) {
            String className = scan.nextIdentifier();
            if (className == null) {
                throw new SAXException("Invalid value for \"class\" attribute: " + val);
            }
            if (classNameList == null) {
                classNameList = new ArrayList<>();
            }
            classNameList.add(className);
            scan.skipWhitespace();
        }
        return classNameList;
    }

    protected static boolean ruleMatch(Selector selector, SvgElementBase obj) {
        List<SvgContainer> ancestors = new ArrayList<>();
        for (SvgContainer parent = obj.parent; parent != null; parent = ((SvgObject) parent).parent) {
            ancestors.add(0, parent);
        }
        int ancestorsPos = ancestors.size() - 1;
        if (selector.size() == 1) {
            return selectorMatch(selector.get(0), ancestors, ancestorsPos, obj);
        }
        return ruleMatch(selector, selector.size() - 1, ancestors, ancestorsPos, obj);
    }

    private static boolean ruleMatch(Selector selector, int selPartPos, List<SvgContainer> ancestors, int ancestorsPos, SvgElementBase obj) {
        SimpleSelector sel = selector.get(selPartPos);
        if (!selectorMatch(sel, ancestors, ancestorsPos, obj)) {
            return false;
        }
        if (sel.combinator == Combinator.DESCENDANT) {
            if (selPartPos == 0) {
                return true;
            }
            while (ancestorsPos >= 0) {
                if (ruleMatchOnAncestors(selector, selPartPos - 1, ancestors, ancestorsPos)) {
                    return true;
                }
                ancestorsPos--;
            }
            return false;
        } else if (sel.combinator == Combinator.CHILD) {
            return ruleMatchOnAncestors(selector, selPartPos - 1, ancestors, ancestorsPos);
        } else {
            int childPos = getChildPosition(ancestors, ancestorsPos, obj);
            if (childPos <= 0) {
                return false;
            }
            return ruleMatch(selector, selPartPos - 1, ancestors, ancestorsPos, (SvgElementBase) obj.parent.getChildren().get(childPos - 1));
        }
    }

    private static boolean ruleMatchOnAncestors(Selector selector, int selPartPos, List<SvgContainer> ancestors, int ancestorsPos) {
        SimpleSelector sel = selector.get(selPartPos);
        SvgElementBase obj = (SvgElementBase) ancestors.get(ancestorsPos);
        if (!selectorMatch(sel, ancestors, ancestorsPos, obj)) {
            return false;
        }
        if (sel.combinator == Combinator.DESCENDANT) {
            if (selPartPos == 0) {
                return true;
            }
            while (ancestorsPos > 0) {
                ancestorsPos--;
                if (ruleMatchOnAncestors(selector, selPartPos - 1, ancestors, ancestorsPos)) {
                    return true;
                }
            }
            return false;
        } else if (sel.combinator == Combinator.CHILD) {
            return ruleMatchOnAncestors(selector, selPartPos - 1, ancestors, ancestorsPos - 1);
        } else {
            int childPos = getChildPosition(ancestors, ancestorsPos, obj);
            if (childPos <= 0) {
                return false;
            }
            return ruleMatch(selector, selPartPos - 1, ancestors, ancestorsPos, (SvgElementBase) obj.parent.getChildren().get(childPos - 1));
        }
    }

    private static int getChildPosition(List<SvgContainer> ancestors, int ancestorsPos, SvgElementBase obj) {
        if (ancestorsPos < 0) {
            return -1;
        }
        if (ancestors.get(ancestorsPos) != obj.parent) {
            return -1;
        }
        int childPos = 0;
        for (SvgObject child : obj.parent.getChildren()) {
            if (child == obj) {
                return childPos;
            }
            childPos++;
        }
        return -1;
    }

    private static boolean selectorMatch(SimpleSelector sel, List<SvgContainer> ancestors, int ancestorsPos, SvgElementBase obj) {
        if (sel.tag != null) {
            if (sel.tag.equalsIgnoreCase("G")) {
                if (!(obj instanceof Group)) {
                    return false;
                }
            } else if (!sel.tag.equals(obj.getClass().getSimpleName().toLowerCase(Locale.US))) {
                return false;
            }
        }
        if (sel.attribs != null) {
            for (Attrib attr : sel.attribs) {
                if (attr.name == "id") {
                    if (!attr.value.equals(obj.f2964id)) {
                        return false;
                    }
                } else if (attr.name != "class" || obj.classNames == null || !obj.classNames.contains(attr.value)) {
                    return false;
                }
            }
        }
        if (sel.pseudos != null) {
            for (String pseudo : sel.pseudos) {
                if (!pseudo.equals("first-child")) {
                    return false;
                }
                if (getChildPosition(ancestors, ancestorsPos, obj) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
