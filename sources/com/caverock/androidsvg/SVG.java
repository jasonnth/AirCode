package com.caverock.androidsvg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.caverock.androidsvg.CSSParser.Rule;
import com.caverock.androidsvg.CSSParser.Ruleset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.xml.sax.SAXException;

public class SVG {
    /* access modifiers changed from: private */
    public static final List<SvgObject> EMPTY_CHILD_LIST = new ArrayList(0);
    private Ruleset cssRules = new Ruleset();
    private String desc = "";
    private SVGExternalFileResolver fileResolver = null;
    private float renderDPI = 96.0f;
    private Svg rootElement = null;
    private String title = "";

    protected static class Box implements Cloneable {
        public float height;
        public float minX;
        public float minY;
        public float width;

        public Box(float minX2, float minY2, float width2, float height2) {
            this.minX = minX2;
            this.minY = minY2;
            this.width = width2;
            this.height = height2;
        }

        public static Box fromLimits(float minX2, float minY2, float maxX, float maxY) {
            return new Box(minX2, minY2, maxX - minX2, maxY - minY2);
        }

        public RectF toRectF() {
            return new RectF(this.minX, this.minY, maxX(), maxY());
        }

        public float maxX() {
            return this.minX + this.width;
        }

        public float maxY() {
            return this.minY + this.height;
        }

        public void union(Box other) {
            if (other.minX < this.minX) {
                this.minX = other.minX;
            }
            if (other.minY < this.minY) {
                this.minY = other.minY;
            }
            if (other.maxX() > maxX()) {
                this.width = other.maxX() - this.minX;
            }
            if (other.maxY() > maxY()) {
                this.height = other.maxY() - this.minY;
            }
        }

        public String toString() {
            return "[" + this.minX + " " + this.minY + " " + this.width + " " + this.height + "]";
        }
    }

    protected static class CSSClipRect {
        public Length bottom;
        public Length left;
        public Length right;
        public Length top;

        public CSSClipRect(Length top2, Length right2, Length bottom2, Length left2) {
            this.top = top2;
            this.right = right2;
            this.bottom = bottom2;
            this.left = left2;
        }
    }

    protected static class Circle extends GraphicsElement {

        /* renamed from: cx */
        public Length f2940cx;

        /* renamed from: cy */
        public Length f2941cy;

        /* renamed from: r */
        public Length f2942r;

        protected Circle() {
        }
    }

    protected static class ClipPath extends Group implements NotDirectlyRendered {
        public Boolean clipPathUnitsAreUser;

        protected ClipPath() {
        }
    }

    protected static class Colour extends SvgPaint {
        public static final Colour BLACK = new Colour(0);
        public int colour;

        public Colour(int val) {
            this.colour = val;
        }

        public String toString() {
            return String.format("#%06x", new Object[]{Integer.valueOf(this.colour)});
        }
    }

    protected static class CurrentColor extends SvgPaint {
        private static CurrentColor instance = new CurrentColor();

        private CurrentColor() {
        }

        public static CurrentColor getInstance() {
            return instance;
        }
    }

    protected static class Defs extends Group implements NotDirectlyRendered {
        protected Defs() {
        }
    }

    protected static class Ellipse extends GraphicsElement {

        /* renamed from: cx */
        public Length f2943cx;

        /* renamed from: cy */
        public Length f2944cy;

        /* renamed from: rx */
        public Length f2945rx;

        /* renamed from: ry */
        public Length f2946ry;

        protected Ellipse() {
        }
    }

    protected static class GradientElement extends SvgElementBase implements SvgContainer {
        public List<SvgObject> children = new ArrayList();
        public Matrix gradientTransform;
        public Boolean gradientUnitsAreUser;
        public String href;
        public GradientSpread spreadMethod;

        protected GradientElement() {
        }

        public List<SvgObject> getChildren() {
            return this.children;
        }

        public void addChild(SvgObject elem) throws SAXException {
            if (elem instanceof Stop) {
                this.children.add(elem);
                return;
            }
            throw new SAXException("Gradient elements cannot contain " + elem + " elements.");
        }
    }

    protected enum GradientSpread {
        pad,
        reflect,
        repeat
    }

    protected static abstract class GraphicsElement extends SvgConditionalElement implements HasTransform {
        public Matrix transform;

        protected GraphicsElement() {
        }

        public void setTransform(Matrix transform2) {
            this.transform = transform2;
        }
    }

    protected static class Group extends SvgConditionalContainer implements HasTransform {
        public Matrix transform;

        protected Group() {
        }

        public void setTransform(Matrix transform2) {
            this.transform = transform2;
        }
    }

    protected interface HasTransform {
        void setTransform(Matrix matrix);
    }

    protected static class Image extends SvgPreserveAspectRatioContainer implements HasTransform {
        public Length height;
        public String href;
        public Matrix transform;
        public Length width;

        /* renamed from: x */
        public Length f2947x;

        /* renamed from: y */
        public Length f2948y;

        protected Image() {
        }

        public void setTransform(Matrix transform2) {
            this.transform = transform2;
        }
    }

    protected static class Length implements Cloneable {
        private static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVG$Unit;
        Unit unit = Unit.px;
        float value = 0.0f;

        static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVG$Unit() {
            int[] iArr = $SWITCH_TABLE$com$caverock$androidsvg$SVG$Unit;
            if (iArr == null) {
                iArr = new int[Unit.values().length];
                try {
                    iArr[Unit.cm.ordinal()] = 5;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[Unit.em.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[Unit.ex.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[Unit.in.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                try {
                    iArr[Unit.mm.ordinal()] = 6;
                } catch (NoSuchFieldError e5) {
                }
                try {
                    iArr[Unit.pc.ordinal()] = 8;
                } catch (NoSuchFieldError e6) {
                }
                try {
                    iArr[Unit.percent.ordinal()] = 9;
                } catch (NoSuchFieldError e7) {
                }
                try {
                    iArr[Unit.pt.ordinal()] = 7;
                } catch (NoSuchFieldError e8) {
                }
                try {
                    iArr[Unit.px.ordinal()] = 1;
                } catch (NoSuchFieldError e9) {
                }
                $SWITCH_TABLE$com$caverock$androidsvg$SVG$Unit = iArr;
            }
            return iArr;
        }

        public Length(float value2, Unit unit2) {
            this.value = value2;
            this.unit = unit2;
        }

        public Length(float value2) {
            this.value = value2;
            this.unit = Unit.px;
        }

        public float floatValue() {
            return this.value;
        }

        public float floatValueX(SVGAndroidRenderer renderer) {
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVG$Unit()[this.unit.ordinal()]) {
                case 1:
                    return this.value;
                case 2:
                    return this.value * renderer.getCurrentFontSize();
                case 3:
                    return this.value * renderer.getCurrentFontXHeight();
                case 4:
                    return this.value * renderer.getDPI();
                case 5:
                    return (this.value * renderer.getDPI()) / 2.54f;
                case 6:
                    return (this.value * renderer.getDPI()) / 25.4f;
                case 7:
                    return (this.value * renderer.getDPI()) / 72.0f;
                case 8:
                    return (this.value * renderer.getDPI()) / 6.0f;
                case 9:
                    Box viewPortUser = renderer.getCurrentViewPortInUserUnits();
                    if (viewPortUser == null) {
                        return this.value;
                    }
                    return (this.value * viewPortUser.width) / 100.0f;
                default:
                    return this.value;
            }
        }

        public float floatValueY(SVGAndroidRenderer renderer) {
            if (this.unit != Unit.percent) {
                return floatValueX(renderer);
            }
            Box viewPortUser = renderer.getCurrentViewPortInUserUnits();
            if (viewPortUser == null) {
                return this.value;
            }
            return (this.value * viewPortUser.height) / 100.0f;
        }

        public float floatValue(SVGAndroidRenderer renderer) {
            if (this.unit != Unit.percent) {
                return floatValueX(renderer);
            }
            Box viewPortUser = renderer.getCurrentViewPortInUserUnits();
            if (viewPortUser == null) {
                return this.value;
            }
            float w = viewPortUser.width;
            float h = viewPortUser.height;
            if (w == h) {
                return (this.value * w) / 100.0f;
            }
            return (this.value * ((float) (Math.sqrt((double) ((w * w) + (h * h))) / 1.414213562373095d))) / 100.0f;
        }

        public float floatValue(SVGAndroidRenderer renderer, float max) {
            if (this.unit == Unit.percent) {
                return (this.value * max) / 100.0f;
            }
            return floatValueX(renderer);
        }

        public float floatValue(float dpi) {
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVG$Unit()[this.unit.ordinal()]) {
                case 1:
                    return this.value;
                case 4:
                    return this.value * dpi;
                case 5:
                    return (this.value * dpi) / 2.54f;
                case 6:
                    return (this.value * dpi) / 25.4f;
                case 7:
                    return (this.value * dpi) / 72.0f;
                case 8:
                    return (this.value * dpi) / 6.0f;
                default:
                    return this.value;
            }
        }

        public boolean isZero() {
            return this.value == 0.0f;
        }

        public boolean isNegative() {
            return this.value < 0.0f;
        }

        public String toString() {
            return new StringBuilder(String.valueOf(String.valueOf(this.value))).append(this.unit).toString();
        }
    }

    protected static class Line extends GraphicsElement {

        /* renamed from: x1 */
        public Length f2949x1;

        /* renamed from: x2 */
        public Length f2950x2;

        /* renamed from: y1 */
        public Length f2951y1;

        /* renamed from: y2 */
        public Length f2952y2;

        protected Line() {
        }
    }

    protected static class Marker extends SvgViewBoxContainer implements NotDirectlyRendered {
        public Length markerHeight;
        public boolean markerUnitsAreUser;
        public Length markerWidth;
        public Float orient;
        public Length refX;
        public Length refY;

        protected Marker() {
        }
    }

    protected static class Mask extends SvgConditionalContainer implements NotDirectlyRendered {
        public Length height;
        public Boolean maskContentUnitsAreUser;
        public Boolean maskUnitsAreUser;
        public Length width;

        /* renamed from: x */
        public Length f2953x;

        /* renamed from: y */
        public Length f2954y;

        protected Mask() {
        }
    }

    protected interface NotDirectlyRendered {
    }

    protected static class PaintReference extends SvgPaint {
        public SvgPaint fallback;
        public String href;

        public PaintReference(String href2, SvgPaint fallback2) {
            this.href = href2;
            this.fallback = fallback2;
        }

        public String toString() {
            return this.href + " " + this.fallback;
        }
    }

    protected static class Path extends GraphicsElement {

        /* renamed from: d */
        public PathDefinition f2955d;
        public Float pathLength;

        protected Path() {
        }
    }

    protected static class PathDefinition implements PathInterface {
        private List<Byte> commands;
        private List<Float> coords;

        public PathDefinition() {
            this.commands = null;
            this.coords = null;
            this.commands = new ArrayList();
            this.coords = new ArrayList();
        }

        public boolean isEmpty() {
            return this.commands.isEmpty();
        }

        public void moveTo(float x, float y) {
            this.commands.add(Byte.valueOf(0));
            this.coords.add(Float.valueOf(x));
            this.coords.add(Float.valueOf(y));
        }

        public void lineTo(float x, float y) {
            this.commands.add(Byte.valueOf(1));
            this.coords.add(Float.valueOf(x));
            this.coords.add(Float.valueOf(y));
        }

        public void cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
            this.commands.add(Byte.valueOf(2));
            this.coords.add(Float.valueOf(x1));
            this.coords.add(Float.valueOf(y1));
            this.coords.add(Float.valueOf(x2));
            this.coords.add(Float.valueOf(y2));
            this.coords.add(Float.valueOf(x3));
            this.coords.add(Float.valueOf(y3));
        }

        public void quadTo(float x1, float y1, float x2, float y2) {
            this.commands.add(Byte.valueOf(3));
            this.coords.add(Float.valueOf(x1));
            this.coords.add(Float.valueOf(y1));
            this.coords.add(Float.valueOf(x2));
            this.coords.add(Float.valueOf(y2));
        }

        public void arcTo(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) {
            int i;
            int i2 = 0;
            if (largeArcFlag) {
                i = 2;
            } else {
                i = 0;
            }
            int i3 = i | 4;
            if (sweepFlag) {
                i2 = 1;
            }
            this.commands.add(Byte.valueOf((byte) (i3 | i2)));
            this.coords.add(Float.valueOf(rx));
            this.coords.add(Float.valueOf(ry));
            this.coords.add(Float.valueOf(xAxisRotation));
            this.coords.add(Float.valueOf(x));
            this.coords.add(Float.valueOf(y));
        }

        public void close() {
            this.commands.add(Byte.valueOf(8));
        }

        public void enumeratePath(PathInterface handler) {
            boolean largeArcFlag;
            boolean sweepFlag;
            Iterator<Float> coordsIter = this.coords.iterator();
            for (Byte byteValue : this.commands) {
                byte command = byteValue.byteValue();
                switch (command) {
                    case 0:
                        handler.moveTo(((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue());
                        break;
                    case 1:
                        handler.lineTo(((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue());
                        break;
                    case 2:
                        handler.cubicTo(((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue());
                        break;
                    case 3:
                        handler.quadTo(((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue());
                        break;
                    case 8:
                        handler.close();
                        break;
                    default:
                        if ((command & 2) != 0) {
                            largeArcFlag = true;
                        } else {
                            largeArcFlag = false;
                        }
                        if ((command & 1) != 0) {
                            sweepFlag = true;
                        } else {
                            sweepFlag = false;
                        }
                        handler.arcTo(((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue(), largeArcFlag, sweepFlag, ((Float) coordsIter.next()).floatValue(), ((Float) coordsIter.next()).floatValue());
                        break;
                }
            }
        }
    }

    protected interface PathInterface {
        void arcTo(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5);

        void close();

        void cubicTo(float f, float f2, float f3, float f4, float f5, float f6);

        void lineTo(float f, float f2);

        void moveTo(float f, float f2);

        void quadTo(float f, float f2, float f3, float f4);
    }

    protected static class Pattern extends SvgViewBoxContainer implements NotDirectlyRendered {
        public Length height;
        public String href;
        public Boolean patternContentUnitsAreUser;
        public Matrix patternTransform;
        public Boolean patternUnitsAreUser;
        public Length width;

        /* renamed from: x */
        public Length f2956x;

        /* renamed from: y */
        public Length f2957y;

        protected Pattern() {
        }
    }

    protected static class PolyLine extends GraphicsElement {
        public float[] points;

        protected PolyLine() {
        }
    }

    protected static class Polygon extends PolyLine {
        protected Polygon() {
        }
    }

    protected static class Rect extends GraphicsElement {
        public Length height;

        /* renamed from: rx */
        public Length f2958rx;

        /* renamed from: ry */
        public Length f2959ry;
        public Length width;

        /* renamed from: x */
        public Length f2960x;

        /* renamed from: y */
        public Length f2961y;

        protected Rect() {
        }
    }

    protected static class SolidColor extends SvgElementBase implements SvgContainer {
        protected SolidColor() {
        }

        public List<SvgObject> getChildren() {
            return SVG.EMPTY_CHILD_LIST;
        }

        public void addChild(SvgObject elem) throws SAXException {
        }
    }

    protected static class Stop extends SvgElementBase implements SvgContainer {
        public Float offset;

        protected Stop() {
        }

        public List<SvgObject> getChildren() {
            return SVG.EMPTY_CHILD_LIST;
        }

        public void addChild(SvgObject elem) throws SAXException {
        }
    }

    protected static class Style implements Cloneable {
        public CSSClipRect clip;
        public String clipPath;
        public FillRule clipRule;
        public Colour color;
        public TextDirection direction;
        public Boolean display;
        public SvgPaint fill;
        public Float fillOpacity;
        public FillRule fillRule;
        public List<String> fontFamily;
        public Length fontSize;
        public FontStyle fontStyle;
        public Integer fontWeight;
        public String markerEnd;
        public String markerMid;
        public String markerStart;
        public String mask;
        public Float opacity;
        public Boolean overflow;
        public SvgPaint solidColor;
        public Float solidOpacity;
        public long specifiedFlags = 0;
        public SvgPaint stopColor;
        public Float stopOpacity;
        public SvgPaint stroke;
        public Length[] strokeDashArray;
        public Length strokeDashOffset;
        public LineCaps strokeLineCap;
        public LineJoin strokeLineJoin;
        public Float strokeMiterLimit;
        public Float strokeOpacity;
        public Length strokeWidth;
        public TextAnchor textAnchor;
        public TextDecoration textDecoration;
        public VectorEffect vectorEffect;
        public SvgPaint viewportFill;
        public Float viewportFillOpacity;
        public Boolean visibility;

        public enum FillRule {
            NonZero,
            EvenOdd
        }

        public enum FontStyle {
            Normal,
            Italic,
            Oblique
        }

        public enum LineCaps {
            Butt,
            Round,
            Square
        }

        public enum LineJoin {
            Miter,
            Round,
            Bevel
        }

        public enum TextAnchor {
            Start,
            Middle,
            End
        }

        public enum TextDecoration {
            None,
            Underline,
            Overline,
            LineThrough,
            Blink
        }

        public enum TextDirection {
            LTR,
            RTL
        }

        public enum VectorEffect {
            None,
            NonScalingStroke
        }

        protected Style() {
        }

        public static Style getDefaultStyle() {
            Style def = new Style();
            def.specifiedFlags = -1;
            def.fill = Colour.BLACK;
            def.fillRule = FillRule.NonZero;
            def.fillOpacity = Float.valueOf(1.0f);
            def.stroke = null;
            def.strokeOpacity = Float.valueOf(1.0f);
            def.strokeWidth = new Length(1.0f);
            def.strokeLineCap = LineCaps.Butt;
            def.strokeLineJoin = LineJoin.Miter;
            def.strokeMiterLimit = Float.valueOf(4.0f);
            def.strokeDashArray = null;
            def.strokeDashOffset = new Length(0.0f);
            def.opacity = Float.valueOf(1.0f);
            def.color = Colour.BLACK;
            def.fontFamily = null;
            def.fontSize = new Length(12.0f, Unit.pt);
            def.fontWeight = Integer.valueOf(400);
            def.fontStyle = FontStyle.Normal;
            def.textDecoration = TextDecoration.None;
            def.direction = TextDirection.LTR;
            def.textAnchor = TextAnchor.Start;
            def.overflow = Boolean.valueOf(true);
            def.clip = null;
            def.markerStart = null;
            def.markerMid = null;
            def.markerEnd = null;
            def.display = Boolean.TRUE;
            def.visibility = Boolean.TRUE;
            def.stopColor = Colour.BLACK;
            def.stopOpacity = Float.valueOf(1.0f);
            def.clipPath = null;
            def.clipRule = FillRule.NonZero;
            def.mask = null;
            def.solidColor = null;
            def.solidOpacity = Float.valueOf(1.0f);
            def.viewportFill = null;
            def.viewportFillOpacity = Float.valueOf(1.0f);
            def.vectorEffect = VectorEffect.None;
            return def;
        }

        public void resetNonInheritingProperties(boolean isRootSVG) {
            this.display = Boolean.TRUE;
            this.overflow = isRootSVG ? Boolean.TRUE : Boolean.FALSE;
            this.clip = null;
            this.clipPath = null;
            this.opacity = Float.valueOf(1.0f);
            this.stopColor = Colour.BLACK;
            this.stopOpacity = Float.valueOf(1.0f);
            this.mask = null;
            this.solidColor = null;
            this.solidOpacity = Float.valueOf(1.0f);
            this.viewportFill = null;
            this.viewportFillOpacity = Float.valueOf(1.0f);
            this.vectorEffect = VectorEffect.None;
        }

        /* access modifiers changed from: protected */
        public Object clone() {
            try {
                Style obj = (Style) super.clone();
                if (this.strokeDashArray != null) {
                    obj.strokeDashArray = (Length[]) this.strokeDashArray.clone();
                }
                return obj;
            } catch (CloneNotSupportedException e) {
                throw new InternalError(e.toString());
            }
        }
    }

    protected static class Svg extends SvgViewBoxContainer {
        public Length height;
        public String version;
        public Length width;

        /* renamed from: x */
        public Length f2962x;

        /* renamed from: y */
        public Length f2963y;

        protected Svg() {
        }
    }

    protected interface SvgConditional {
        String getRequiredExtensions();

        Set<String> getRequiredFeatures();

        Set<String> getRequiredFonts();

        Set<String> getRequiredFormats();

        Set<String> getSystemLanguage();

        void setRequiredExtensions(String str);

        void setRequiredFeatures(Set<String> set);

        void setRequiredFonts(Set<String> set);

        void setRequiredFormats(Set<String> set);

        void setSystemLanguage(Set<String> set);
    }

    protected static class SvgConditionalContainer extends SvgElement implements SvgConditional, SvgContainer {
        public List<SvgObject> children = new ArrayList();
        public String requiredExtensions = null;
        public Set<String> requiredFeatures = null;
        public Set<String> requiredFonts = null;
        public Set<String> requiredFormats = null;
        public Set<String> systemLanguage = null;

        protected SvgConditionalContainer() {
        }

        public List<SvgObject> getChildren() {
            return this.children;
        }

        public void addChild(SvgObject elem) throws SAXException {
            this.children.add(elem);
        }

        public void setRequiredFeatures(Set<String> features) {
            this.requiredFeatures = features;
        }

        public Set<String> getRequiredFeatures() {
            return this.requiredFeatures;
        }

        public void setRequiredExtensions(String extensions) {
            this.requiredExtensions = extensions;
        }

        public String getRequiredExtensions() {
            return this.requiredExtensions;
        }

        public void setSystemLanguage(Set<String> languages) {
            this.systemLanguage = languages;
        }

        public Set<String> getSystemLanguage() {
            return null;
        }

        public void setRequiredFormats(Set<String> mimeTypes) {
            this.requiredFormats = mimeTypes;
        }

        public Set<String> getRequiredFormats() {
            return this.requiredFormats;
        }

        public void setRequiredFonts(Set<String> fontNames) {
            this.requiredFonts = fontNames;
        }

        public Set<String> getRequiredFonts() {
            return this.requiredFonts;
        }
    }

    protected static class SvgConditionalElement extends SvgElement implements SvgConditional {
        public String requiredExtensions = null;
        public Set<String> requiredFeatures = null;
        public Set<String> requiredFonts = null;
        public Set<String> requiredFormats = null;
        public Set<String> systemLanguage = null;

        protected SvgConditionalElement() {
        }

        public void setRequiredFeatures(Set<String> features) {
            this.requiredFeatures = features;
        }

        public Set<String> getRequiredFeatures() {
            return this.requiredFeatures;
        }

        public void setRequiredExtensions(String extensions) {
            this.requiredExtensions = extensions;
        }

        public String getRequiredExtensions() {
            return this.requiredExtensions;
        }

        public void setSystemLanguage(Set<String> languages) {
            this.systemLanguage = languages;
        }

        public Set<String> getSystemLanguage() {
            return this.systemLanguage;
        }

        public void setRequiredFormats(Set<String> mimeTypes) {
            this.requiredFormats = mimeTypes;
        }

        public Set<String> getRequiredFormats() {
            return this.requiredFormats;
        }

        public void setRequiredFonts(Set<String> fontNames) {
            this.requiredFonts = fontNames;
        }

        public Set<String> getRequiredFonts() {
            return this.requiredFonts;
        }
    }

    protected interface SvgContainer {
        void addChild(SvgObject svgObject) throws SAXException;

        List<SvgObject> getChildren();
    }

    protected static class SvgElement extends SvgElementBase {
        public Box boundingBox = null;

        protected SvgElement() {
        }
    }

    protected static class SvgElementBase extends SvgObject {
        public Style baseStyle = null;
        public List<String> classNames = null;

        /* renamed from: id */
        public String f2964id = null;
        public Boolean spacePreserve = null;
        public Style style = null;

        protected SvgElementBase() {
        }
    }

    protected static class SvgLinearGradient extends GradientElement {

        /* renamed from: x1 */
        public Length f2965x1;

        /* renamed from: x2 */
        public Length f2966x2;

        /* renamed from: y1 */
        public Length f2967y1;

        /* renamed from: y2 */
        public Length f2968y2;

        protected SvgLinearGradient() {
        }
    }

    protected static class SvgObject {
        public SVG document;
        public SvgContainer parent;

        protected SvgObject() {
        }

        public String toString() {
            return getClass().getSimpleName();
        }
    }

    protected static abstract class SvgPaint implements Cloneable {
        protected SvgPaint() {
        }
    }

    protected static class SvgPreserveAspectRatioContainer extends SvgConditionalContainer {
        public PreserveAspectRatio preserveAspectRatio = null;

        protected SvgPreserveAspectRatioContainer() {
        }
    }

    protected static class SvgRadialGradient extends GradientElement {

        /* renamed from: cx */
        public Length f2969cx;

        /* renamed from: cy */
        public Length f2970cy;

        /* renamed from: fx */
        public Length f2971fx;

        /* renamed from: fy */
        public Length f2972fy;

        /* renamed from: r */
        public Length f2973r;

        protected SvgRadialGradient() {
        }
    }

    protected static class SvgViewBoxContainer extends SvgPreserveAspectRatioContainer {
        public Box viewBox;

        protected SvgViewBoxContainer() {
        }
    }

    protected static class Switch extends Group {
        protected Switch() {
        }
    }

    protected static class Symbol extends SvgViewBoxContainer implements NotDirectlyRendered {
        protected Symbol() {
        }
    }

    protected static class TRef extends TextContainer implements TextChild {
        public String href;
        private TextRoot textRoot;

        protected TRef() {
        }

        public void setTextRoot(TextRoot obj) {
            this.textRoot = obj;
        }

        public TextRoot getTextRoot() {
            return this.textRoot;
        }
    }

    protected static class TSpan extends TextPositionedContainer implements TextChild {
        private TextRoot textRoot;

        protected TSpan() {
        }

        public void setTextRoot(TextRoot obj) {
            this.textRoot = obj;
        }

        public TextRoot getTextRoot() {
            return this.textRoot;
        }
    }

    protected static class Text extends TextPositionedContainer implements HasTransform, TextRoot {
        public Matrix transform;

        protected Text() {
        }

        public void setTransform(Matrix transform2) {
            this.transform = transform2;
        }
    }

    protected interface TextChild {
        TextRoot getTextRoot();
    }

    protected static class TextContainer extends SvgConditionalContainer {
        protected TextContainer() {
        }

        public void addChild(SvgObject elem) throws SAXException {
            if (elem instanceof TextChild) {
                this.children.add(elem);
                return;
            }
            throw new SAXException("Text content elements cannot contain " + elem + " elements.");
        }
    }

    protected static class TextPath extends TextContainer implements TextChild {
        public String href;
        public Length startOffset;
        private TextRoot textRoot;

        protected TextPath() {
        }

        public void setTextRoot(TextRoot obj) {
            this.textRoot = obj;
        }

        public TextRoot getTextRoot() {
            return this.textRoot;
        }
    }

    protected static class TextPositionedContainer extends TextContainer {

        /* renamed from: dx */
        public List<Length> f2974dx;

        /* renamed from: dy */
        public List<Length> f2975dy;

        /* renamed from: x */
        public List<Length> f2976x;

        /* renamed from: y */
        public List<Length> f2977y;

        protected TextPositionedContainer() {
        }
    }

    protected interface TextRoot {
    }

    protected static class TextSequence extends SvgObject implements TextChild {
        public String text;
        private TextRoot textRoot;

        public TextSequence(String text2) {
            this.text = text2;
        }

        public String toString() {
            return new StringBuilder(String.valueOf(getClass().getSimpleName())).append(" '").append(this.text).append("'").toString();
        }

        public TextRoot getTextRoot() {
            return this.textRoot;
        }
    }

    protected enum Unit {
        px,
        em,
        ex,
        in,
        cm,
        mm,
        pt,
        pc,
        percent
    }

    protected static class Use extends Group {
        public Length height;
        public String href;
        public Length width;

        /* renamed from: x */
        public Length f2986x;

        /* renamed from: y */
        public Length f2987y;

        protected Use() {
        }
    }

    protected static class View extends SvgViewBoxContainer implements NotDirectlyRendered {
        protected View() {
        }
    }

    protected SVG() {
    }

    public static SVG getFromResource(Context context, int resourceId) throws SVGParseException {
        return getFromResource(context.getResources(), resourceId);
    }

    public static SVG getFromResource(Resources resources, int resourceId) throws SVGParseException {
        return new SVGParser().parse(resources.openRawResource(resourceId));
    }

    public void renderToCanvas(Canvas canvas) {
        renderToCanvas(canvas, null);
    }

    public void renderToCanvas(Canvas canvas, RectF viewPort) {
        Box svgViewPort;
        if (viewPort != null) {
            svgViewPort = Box.fromLimits(viewPort.left, viewPort.top, viewPort.right, viewPort.bottom);
        } else {
            svgViewPort = new Box(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
        }
        new SVGAndroidRenderer(canvas, svgViewPort, this.renderDPI).renderDocument(this, null, null, true);
    }

    public float getDocumentWidth() {
        if (this.rootElement != null) {
            return getDocumentDimensions(this.renderDPI).width;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public float getDocumentHeight() {
        if (this.rootElement != null) {
            return getDocumentDimensions(this.renderDPI).height;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public RectF getDocumentViewBox() {
        if (this.rootElement == null) {
            throw new IllegalArgumentException("SVG document is empty");
        } else if (this.rootElement.viewBox == null) {
            return null;
        } else {
            return this.rootElement.viewBox.toRectF();
        }
    }

    public void setDocumentPreserveAspectRatio(PreserveAspectRatio preserveAspectRatio) {
        if (this.rootElement == null) {
            throw new IllegalArgumentException("SVG document is empty");
        }
        this.rootElement.preserveAspectRatio = preserveAspectRatio;
    }

    /* access modifiers changed from: protected */
    public Svg getRootElement() {
        return this.rootElement;
    }

    /* access modifiers changed from: protected */
    public void setRootElement(Svg rootElement2) {
        this.rootElement = rootElement2;
    }

    /* access modifiers changed from: protected */
    public SvgObject resolveIRI(String iri) {
        if (iri != null && iri.length() > 1 && iri.startsWith("#")) {
            return getElementById(iri.substring(1));
        }
        return null;
    }

    private Box getDocumentDimensions(float dpi) {
        float hOut;
        Length w = this.rootElement.width;
        Length h = this.rootElement.height;
        if (w == null || w.isZero() || w.unit == Unit.percent || w.unit == Unit.em || w.unit == Unit.ex) {
            return new Box(-1.0f, -1.0f, -1.0f, -1.0f);
        }
        float wOut = w.floatValue(dpi);
        if (h != null) {
            if (h.isZero() || h.unit == Unit.percent || h.unit == Unit.em || h.unit == Unit.ex) {
                return new Box(-1.0f, -1.0f, -1.0f, -1.0f);
            }
            hOut = h.floatValue(dpi);
        } else if (this.rootElement.viewBox != null) {
            hOut = (this.rootElement.viewBox.height * wOut) / this.rootElement.viewBox.width;
        } else {
            hOut = wOut;
        }
        return new Box(0.0f, 0.0f, wOut, hOut);
    }

    /* access modifiers changed from: protected */
    public void addCSSRules(Ruleset ruleset) {
        this.cssRules.addAll(ruleset);
    }

    /* access modifiers changed from: protected */
    public List<Rule> getCSSRules() {
        return this.cssRules.getRules();
    }

    /* access modifiers changed from: protected */
    public boolean hasCSSRules() {
        return !this.cssRules.isEmpty();
    }

    /* access modifiers changed from: protected */
    public void setTitle(String title2) {
        this.title = title2;
    }

    /* access modifiers changed from: protected */
    public void setDesc(String desc2) {
        this.desc = desc2;
    }

    /* access modifiers changed from: protected */
    public SVGExternalFileResolver getFileResolver() {
        return this.fileResolver;
    }

    /* access modifiers changed from: protected */
    public SvgObject getElementById(String id) {
        if (id.equals(this.rootElement.f2964id)) {
            return this.rootElement;
        }
        return getElementById(this.rootElement, id);
    }

    private SvgElementBase getElementById(SvgContainer obj, String id) {
        SvgElementBase elem = (SvgElementBase) obj;
        if (id.equals(elem.f2964id)) {
            return elem;
        }
        for (SvgObject child : obj.getChildren()) {
            if (child instanceof SvgElementBase) {
                SvgElementBase childElem = (SvgElementBase) child;
                if (id.equals(childElem.f2964id)) {
                    return childElem;
                }
                if (child instanceof SvgContainer) {
                    SvgElementBase found = getElementById((SvgContainer) child, id);
                    if (found != null) {
                        return found;
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }
}
