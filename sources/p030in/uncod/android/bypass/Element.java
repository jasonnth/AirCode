package p030in.uncod.android.bypass;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import java.util.HashMap;
import java.util.Map;

/* renamed from: in.uncod.android.bypass.Element */
public class Element {
    public static final int F_LIST_ORDERED = 1;
    Map<String, String> attributes = new HashMap();
    Element[] children;
    int nestLevel = 0;
    Element parent;
    String text;
    Type type;

    /* renamed from: in.uncod.android.bypass.Element$Type */
    public enum Type {
        BLOCK_CODE(0),
        BLOCK_QUOTE(1),
        BLOCK_HTML(2),
        HEADER(3),
        HRULE(4),
        LIST(5),
        LIST_ITEM(6),
        PARAGRAPH(7),
        TABLE(8),
        TABLE_CELL(9),
        TABLE_ROW(10),
        AUTOLINK(267),
        CODE_SPAN(268),
        DOUBLE_EMPHASIS(269),
        EMPHASIS(MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS),
        IMAGE(271),
        LINEBREAK(HelpCenterArticle.SET_RESERVATION_REQUIREMENTS),
        LINK(273),
        RAW_HTML_TAG(TiffUtil.TIFF_TAG_ORIENTATION),
        TRIPLE_EMPHASIS(275),
        TEXT(276),
        STRIKETHROUGH(277);
        
        private static final Type[] TypeValues = null;
        /* access modifiers changed from: private */
        public final int value;

        static {
            TypeValues = values();
        }

        private Type(int value2) {
            this.value = value2;
        }

        public static Type fromInteger(int x) {
            Type[] arr$;
            for (Type type : TypeValues) {
                if (type.value == x) {
                    return type;
                }
            }
            return null;
        }
    }

    public Element(String text2, int type2) {
        this.text = text2;
        this.type = Type.fromInteger(type2);
    }

    public void setParent(Element element) {
        this.parent = element;
    }

    public void setChildren(Element[] children2) {
        this.children = children2;
    }

    public void addAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

    public String getAttribute(String name) {
        return (String) this.attributes.get(name);
    }

    public Element getParent() {
        return this.parent;
    }

    public String getText() {
        return this.text;
    }

    public int size() {
        if (this.children != null) {
            return this.children.length;
        }
        return 0;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isBlockElement() {
        return (this.type.value & 256) == 0;
    }

    public boolean isSpanElement() {
        return (this.type.value & 256) == 256;
    }
}
