package com.caverock.androidsvg;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PathMeasure;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import android.util.Log;
import com.caverock.androidsvg.CSSParser.Rule;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;

public class SVGAndroidRenderer {

    /* renamed from: $SWITCH_TABLE$com$caverock$androidsvg$PreserveAspectRatio$Alignment */
    private static /* synthetic */ int[] f2988xb84d42c9;
    private static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$FillRule;
    private static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineCaps;
    private static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineJoin;
    private Stack<Bitmap> bitmapStack;
    /* access modifiers changed from: private */
    public Canvas canvas;
    private Stack<Canvas> canvasStack;
    private Box canvasViewPort;
    private boolean directRenderingMode;
    private SVG document;
    private float dpi;
    private Stack<Matrix> matrixStack;
    private Stack<SvgContainer> parentStack;
    /* access modifiers changed from: private */
    public RendererState state;
    private Stack<RendererState> stateStack;

    private class MarkerPositionCalculator implements PathInterface {
        private boolean closepathReAdjustPending;
        private MarkerVector lastPos = null;
        private List<MarkerVector> markers = new ArrayList();
        private boolean normalCubic = true;
        private boolean startArc = false;
        private float startX;
        private float startY;
        private int subpathStartIndex = -1;

        public MarkerPositionCalculator(PathDefinition pathDef) {
            pathDef.enumeratePath(this);
            if (this.closepathReAdjustPending) {
                this.lastPos.add((MarkerVector) this.markers.get(this.subpathStartIndex));
                this.markers.set(this.subpathStartIndex, this.lastPos);
                this.closepathReAdjustPending = false;
            }
            if (this.lastPos != null) {
                this.markers.add(this.lastPos);
            }
        }

        public List<MarkerVector> getMarkers() {
            return this.markers;
        }

        public void moveTo(float x, float y) {
            if (this.closepathReAdjustPending) {
                this.lastPos.add((MarkerVector) this.markers.get(this.subpathStartIndex));
                this.markers.set(this.subpathStartIndex, this.lastPos);
                this.closepathReAdjustPending = false;
            }
            if (this.lastPos != null) {
                this.markers.add(this.lastPos);
            }
            this.startX = x;
            this.startY = y;
            this.lastPos = new MarkerVector(x, y, 0.0f, 0.0f);
            this.subpathStartIndex = this.markers.size();
        }

        public void lineTo(float x, float y) {
            this.lastPos.add(x, y);
            this.markers.add(this.lastPos);
            this.lastPos = new MarkerVector(x, y, x - this.lastPos.f2991x, y - this.lastPos.f2992y);
            this.closepathReAdjustPending = false;
        }

        public void cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
            if (this.normalCubic || this.startArc) {
                this.lastPos.add(x1, y1);
                this.markers.add(this.lastPos);
                this.startArc = false;
            }
            this.lastPos = new MarkerVector(x3, y3, x3 - x2, y3 - y2);
            this.closepathReAdjustPending = false;
        }

        public void quadTo(float x1, float y1, float x2, float y2) {
            this.lastPos.add(x1, y1);
            this.markers.add(this.lastPos);
            this.lastPos = new MarkerVector(x2, y2, x2 - x1, y2 - y1);
            this.closepathReAdjustPending = false;
        }

        public void arcTo(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) {
            this.startArc = true;
            this.normalCubic = false;
            SVGAndroidRenderer.arcTo(this.lastPos.f2991x, this.lastPos.f2992y, rx, ry, xAxisRotation, largeArcFlag, sweepFlag, x, y, this);
            this.normalCubic = true;
            this.closepathReAdjustPending = false;
        }

        public void close() {
            this.markers.add(this.lastPos);
            lineTo(this.startX, this.startY);
            this.closepathReAdjustPending = true;
        }
    }

    private class MarkerVector {

        /* renamed from: dx */
        public float f2989dx = 0.0f;

        /* renamed from: dy */
        public float f2990dy = 0.0f;

        /* renamed from: x */
        public float f2991x;

        /* renamed from: y */
        public float f2992y;

        public MarkerVector(float x, float y, float dx, float dy) {
            this.f2991x = x;
            this.f2992y = y;
            double len = Math.sqrt((double) ((dx * dx) + (dy * dy)));
            if (len != 0.0d) {
                this.f2989dx = (float) (((double) dx) / len);
                this.f2990dy = (float) (((double) dy) / len);
            }
        }

        public void add(float x, float y) {
            float dx = x - this.f2991x;
            float dy = y - this.f2992y;
            double len = Math.sqrt((double) ((dx * dx) + (dy * dy)));
            if (len != 0.0d) {
                this.f2989dx += (float) (((double) dx) / len);
                this.f2990dy += (float) (((double) dy) / len);
            }
        }

        public void add(MarkerVector v2) {
            this.f2989dx += v2.f2989dx;
            this.f2990dy += v2.f2990dy;
        }

        public String toString() {
            return "(" + this.f2991x + "," + this.f2992y + " " + this.f2989dx + "," + this.f2990dy + ")";
        }
    }

    private class PathConverter implements PathInterface {
        float lastX;
        float lastY;
        Path path = new Path();

        public PathConverter(PathDefinition pathDef) {
            pathDef.enumeratePath(this);
        }

        public Path getPath() {
            return this.path;
        }

        public void moveTo(float x, float y) {
            this.path.moveTo(x, y);
            this.lastX = x;
            this.lastY = y;
        }

        public void lineTo(float x, float y) {
            this.path.lineTo(x, y);
            this.lastX = x;
            this.lastY = y;
        }

        public void cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
            this.path.cubicTo(x1, y1, x2, y2, x3, y3);
            this.lastX = x3;
            this.lastY = y3;
        }

        public void quadTo(float x1, float y1, float x2, float y2) {
            this.path.quadTo(x1, y1, x2, y2);
            this.lastX = x2;
            this.lastY = y2;
        }

        public void arcTo(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) {
            SVGAndroidRenderer.arcTo(this.lastX, this.lastY, rx, ry, xAxisRotation, largeArcFlag, sweepFlag, x, y, this);
            this.lastX = x;
            this.lastY = y;
        }

        public void close() {
            this.path.close();
        }
    }

    private class PathTextDrawer extends PlainTextDrawer {
        private Path path;

        public PathTextDrawer(Path path2, float x, float y) {
            super(x, y);
            this.path = path2;
        }

        public void processText(String text) {
            if (SVGAndroidRenderer.this.visible()) {
                if (SVGAndroidRenderer.this.state.hasFill) {
                    SVGAndroidRenderer.this.canvas.drawTextOnPath(text, this.path, this.f2993x, this.f2994y, SVGAndroidRenderer.this.state.fillPaint);
                }
                if (SVGAndroidRenderer.this.state.hasStroke) {
                    SVGAndroidRenderer.this.canvas.drawTextOnPath(text, this.path, this.f2993x, this.f2994y, SVGAndroidRenderer.this.state.strokePaint);
                }
            }
            this.f2993x += SVGAndroidRenderer.this.state.fillPaint.measureText(text);
        }
    }

    private class PlainTextDrawer extends TextProcessor {

        /* renamed from: x */
        public float f2993x;

        /* renamed from: y */
        public float f2994y;

        public PlainTextDrawer(float x, float y) {
            super(SVGAndroidRenderer.this, null);
            this.f2993x = x;
            this.f2994y = y;
        }

        public void processText(String text) {
            SVGAndroidRenderer.debug("TextSequence render", new Object[0]);
            if (SVGAndroidRenderer.this.visible()) {
                if (SVGAndroidRenderer.this.state.hasFill) {
                    SVGAndroidRenderer.this.canvas.drawText(text, this.f2993x, this.f2994y, SVGAndroidRenderer.this.state.fillPaint);
                }
                if (SVGAndroidRenderer.this.state.hasStroke) {
                    SVGAndroidRenderer.this.canvas.drawText(text, this.f2993x, this.f2994y, SVGAndroidRenderer.this.state.strokePaint);
                }
            }
            this.f2993x += SVGAndroidRenderer.this.state.fillPaint.measureText(text);
        }
    }

    private class PlainTextToPath extends TextProcessor {
        public Path textAsPath;

        /* renamed from: x */
        public float f2995x;

        /* renamed from: y */
        public float f2996y;

        public PlainTextToPath(float x, float y, Path textAsPath2) {
            super(SVGAndroidRenderer.this, null);
            this.f2995x = x;
            this.f2996y = y;
            this.textAsPath = textAsPath2;
        }

        public boolean doTextContainer(TextContainer obj) {
            if (!(obj instanceof TextPath)) {
                return true;
            }
            SVGAndroidRenderer.warn("Using <textPath> elements in a clip path is not supported.", new Object[0]);
            return false;
        }

        public void processText(String text) {
            if (SVGAndroidRenderer.this.visible()) {
                Path spanPath = new Path();
                SVGAndroidRenderer.this.state.fillPaint.getTextPath(text, 0, text.length(), this.f2995x, this.f2996y, spanPath);
                this.textAsPath.addPath(spanPath);
            }
            this.f2995x += SVGAndroidRenderer.this.state.fillPaint.measureText(text);
        }
    }

    private class RendererState implements Cloneable {
        public boolean directRendering;
        public Paint fillPaint = new Paint();
        public boolean hasFill;
        public boolean hasStroke;
        public boolean spacePreserve;
        public Paint strokePaint;
        public Style style;
        public Box viewBox;
        public Box viewPort;

        public RendererState() {
            this.fillPaint.setFlags(385);
            this.fillPaint.setStyle(Style.FILL);
            this.fillPaint.setTypeface(Typeface.DEFAULT);
            this.strokePaint = new Paint();
            this.strokePaint.setFlags(385);
            this.strokePaint.setStyle(Style.STROKE);
            this.strokePaint.setTypeface(Typeface.DEFAULT);
            this.style = Style.getDefaultStyle();
        }

        /* access modifiers changed from: protected */
        public Object clone() {
            try {
                RendererState obj = (RendererState) super.clone();
                obj.style = (Style) this.style.clone();
                obj.fillPaint = new Paint(this.fillPaint);
                obj.strokePaint = new Paint(this.strokePaint);
                return obj;
            } catch (CloneNotSupportedException e) {
                throw new InternalError(e.toString());
            }
        }
    }

    private class TextBoundsCalculator extends TextProcessor {
        RectF bbox = new RectF();

        /* renamed from: x */
        float f2997x;

        /* renamed from: y */
        float f2998y;

        public TextBoundsCalculator(float x, float y) {
            super(SVGAndroidRenderer.this, null);
            this.f2997x = x;
            this.f2998y = y;
        }

        public boolean doTextContainer(TextContainer obj) {
            if (!(obj instanceof TextPath)) {
                return true;
            }
            TextPath tpath = (TextPath) obj;
            SvgObject ref = obj.document.resolveIRI(tpath.href);
            if (ref == null) {
                SVGAndroidRenderer.error("TextPath path reference '%s' not found", tpath.href);
                return false;
            }
            Path pathObj = (Path) ref;
            Path path = new PathConverter(pathObj.f2955d).getPath();
            if (pathObj.transform != null) {
                path.transform(pathObj.transform);
            }
            RectF pathBounds = new RectF();
            path.computeBounds(pathBounds, true);
            this.bbox.union(pathBounds);
            return false;
        }

        public void processText(String text) {
            if (SVGAndroidRenderer.this.visible()) {
                Rect rect = new Rect();
                SVGAndroidRenderer.this.state.fillPaint.getTextBounds(text, 0, text.length(), rect);
                RectF textbounds = new RectF(rect);
                textbounds.offset(this.f2997x, this.f2998y);
                this.bbox.union(textbounds);
            }
            this.f2997x += SVGAndroidRenderer.this.state.fillPaint.measureText(text);
        }
    }

    private abstract class TextProcessor {
        public abstract void processText(String str);

        private TextProcessor() {
        }

        /* synthetic */ TextProcessor(SVGAndroidRenderer sVGAndroidRenderer, TextProcessor textProcessor) {
            this();
        }

        public boolean doTextContainer(TextContainer obj) {
            return true;
        }
    }

    private class TextWidthCalculator extends TextProcessor {

        /* renamed from: x */
        public float f2999x;

        private TextWidthCalculator() {
            super(SVGAndroidRenderer.this, null);
            this.f2999x = 0.0f;
        }

        /* synthetic */ TextWidthCalculator(SVGAndroidRenderer sVGAndroidRenderer, TextWidthCalculator textWidthCalculator) {
            this();
        }

        public void processText(String text) {
            this.f2999x += SVGAndroidRenderer.this.state.fillPaint.measureText(text);
        }
    }

    /* renamed from: $SWITCH_TABLE$com$caverock$androidsvg$PreserveAspectRatio$Alignment */
    static /* synthetic */ int[] m1743xb84d42c9() {
        int[] iArr = f2988xb84d42c9;
        if (iArr == null) {
            iArr = new int[Alignment.values().length];
            try {
                iArr[Alignment.None.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Alignment.XMaxYMax.ordinal()] = 10;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Alignment.XMaxYMid.ordinal()] = 7;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Alignment.XMaxYMin.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Alignment.XMidYMax.ordinal()] = 9;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[Alignment.XMidYMid.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[Alignment.XMidYMin.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[Alignment.XMinYMax.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[Alignment.XMinYMid.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[Alignment.XMinYMin.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            f2988xb84d42c9 = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$FillRule() {
        int[] iArr = $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$FillRule;
        if (iArr == null) {
            iArr = new int[FillRule.values().length];
            try {
                iArr[FillRule.EvenOdd.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FillRule.NonZero.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$FillRule = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineCaps() {
        int[] iArr = $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineCaps;
        if (iArr == null) {
            iArr = new int[LineCaps.values().length];
            try {
                iArr[LineCaps.Butt.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LineCaps.Round.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LineCaps.Square.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineCaps = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineJoin() {
        int[] iArr = $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineJoin;
        if (iArr == null) {
            iArr = new int[LineJoin.values().length];
            try {
                iArr[LineJoin.Bevel.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LineJoin.Miter.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LineJoin.Round.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineJoin = iArr;
        }
        return iArr;
    }

    private void resetState() {
        this.state = new RendererState();
        this.stateStack = new Stack<>();
        updateStyle(this.state, Style.getDefaultStyle());
        this.state.viewPort = this.canvasViewPort;
        this.state.spacePreserve = false;
        this.state.directRendering = this.directRenderingMode;
        this.stateStack.push((RendererState) this.state.clone());
        this.canvasStack = new Stack<>();
        this.bitmapStack = new Stack<>();
        this.matrixStack = new Stack<>();
        this.parentStack = new Stack<>();
    }

    protected SVGAndroidRenderer(Canvas canvas2, Box viewPort, float defaultDPI) {
        this.canvas = canvas2;
        this.dpi = defaultDPI;
        this.canvasViewPort = viewPort;
    }

    /* access modifiers changed from: protected */
    public float getDPI() {
        return this.dpi;
    }

    /* access modifiers changed from: protected */
    public float getCurrentFontSize() {
        return this.state.fillPaint.getTextSize();
    }

    /* access modifiers changed from: protected */
    public float getCurrentFontXHeight() {
        return this.state.fillPaint.getTextSize() / 2.0f;
    }

    /* access modifiers changed from: protected */
    public Box getCurrentViewPortInUserUnits() {
        if (this.state.viewBox != null) {
            return this.state.viewBox;
        }
        return this.state.viewPort;
    }

    /* access modifiers changed from: protected */
    public void renderDocument(SVG document2, Box viewBox, PreserveAspectRatio positioning, boolean directRenderingMode2) {
        this.document = document2;
        this.directRenderingMode = directRenderingMode2;
        Svg rootObj = document2.getRootElement();
        if (rootObj == null) {
            warn("Nothing to render. Document is empty.", new Object[0]);
            return;
        }
        resetState();
        checkXMLSpaceAttribute(rootObj);
        render(rootObj, rootObj.width, rootObj.height, viewBox != null ? viewBox : rootObj.viewBox, positioning != null ? positioning : rootObj.preserveAspectRatio);
    }

    private void render(SvgObject obj) {
        if (!(obj instanceof NotDirectlyRendered)) {
            statePush();
            checkXMLSpaceAttribute(obj);
            if (obj instanceof Svg) {
                render((Svg) obj);
            } else if (obj instanceof Use) {
                render((Use) obj);
            } else if (obj instanceof Switch) {
                render((Switch) obj);
            } else if (obj instanceof Group) {
                render((Group) obj);
            } else if (obj instanceof Image) {
                render((Image) obj);
            } else if (obj instanceof Path) {
                render((Path) obj);
            } else if (obj instanceof Rect) {
                render((Rect) obj);
            } else if (obj instanceof Circle) {
                render((Circle) obj);
            } else if (obj instanceof Ellipse) {
                render((Ellipse) obj);
            } else if (obj instanceof Line) {
                render((Line) obj);
            } else if (obj instanceof Polygon) {
                render((Polygon) obj);
            } else if (obj instanceof PolyLine) {
                render((PolyLine) obj);
            } else if (obj instanceof Text) {
                render((Text) obj);
            }
            statePop();
        }
    }

    private void renderChildren(SvgContainer obj, boolean isContainer) {
        if (isContainer) {
            parentPush(obj);
        }
        for (SvgObject child : obj.getChildren()) {
            render(child);
        }
        if (isContainer) {
            parentPop();
        }
    }

    private void statePush() {
        this.canvas.save();
        this.stateStack.push(this.state);
        this.state = (RendererState) this.state.clone();
    }

    private void statePop() {
        this.canvas.restore();
        this.state = (RendererState) this.stateStack.pop();
    }

    private void parentPush(SvgContainer obj) {
        this.parentStack.push(obj);
        this.matrixStack.push(this.canvas.getMatrix());
    }

    private void parentPop() {
        this.parentStack.pop();
        this.matrixStack.pop();
    }

    private void updateStyleForElement(RendererState state2, SvgElementBase obj) {
        state2.style.resetNonInheritingProperties(obj.parent == null);
        if (obj.baseStyle != null) {
            updateStyle(state2, obj.baseStyle);
        }
        if (this.document.hasCSSRules()) {
            for (Rule rule : this.document.getCSSRules()) {
                if (CSSParser.ruleMatch(rule.selector, obj)) {
                    updateStyle(state2, rule.style);
                }
            }
        }
        if (obj.style != null) {
            updateStyle(state2, obj.style);
        }
    }

    private void checkXMLSpaceAttribute(SvgObject obj) {
        if (obj instanceof SvgElementBase) {
            SvgElementBase bobj = (SvgElementBase) obj;
            if (bobj.spacePreserve != null) {
                this.state.spacePreserve = bobj.spacePreserve.booleanValue();
            }
        }
    }

    private void doFilledPath(SvgElement obj, Path path) {
        if (this.state.style.fill instanceof PaintReference) {
            SvgObject ref = this.document.resolveIRI(((PaintReference) this.state.style.fill).href);
            if (ref instanceof Pattern) {
                fillWithPattern(obj, path, (Pattern) ref);
                return;
            }
        }
        this.canvas.drawPath(path, this.state.fillPaint);
    }

    private void doStroke(Path path) {
        if (this.state.style.vectorEffect == VectorEffect.NonScalingStroke) {
            Matrix currentMatrix = this.canvas.getMatrix();
            Path transformedPath = new Path();
            path.transform(currentMatrix, transformedPath);
            this.canvas.setMatrix(new Matrix());
            Shader shader = this.state.strokePaint.getShader();
            Matrix currentShaderMatrix = new Matrix();
            if (shader != null) {
                shader.getLocalMatrix(currentShaderMatrix);
                Matrix newShaderMatrix = new Matrix(currentShaderMatrix);
                newShaderMatrix.postConcat(currentMatrix);
                shader.setLocalMatrix(newShaderMatrix);
            }
            this.canvas.drawPath(transformedPath, this.state.strokePaint);
            this.canvas.setMatrix(currentMatrix);
            if (shader != null) {
                shader.setLocalMatrix(currentShaderMatrix);
                return;
            }
            return;
        }
        this.canvas.drawPath(path, this.state.strokePaint);
    }

    /* access modifiers changed from: private */
    public static void warn(String format, Object... args) {
        Log.w("SVGAndroidRenderer", String.format(format, args));
    }

    /* access modifiers changed from: private */
    public static void error(String format, Object... args) {
        Log.e("SVGAndroidRenderer", String.format(format, args));
    }

    /* access modifiers changed from: private */
    public static void debug(String format, Object... args) {
    }

    private void render(Svg obj) {
        render(obj, obj.width, obj.height);
    }

    private void render(Svg obj, Length width, Length height) {
        render(obj, width, height, obj.viewBox, obj.preserveAspectRatio);
    }

    private void render(Svg obj, Length width, Length height, Box viewBox, PreserveAspectRatio positioning) {
        debug("Svg render", new Object[0]);
        if (width != null && width.isZero()) {
            return;
        }
        if (height == null || !height.isZero()) {
            if (positioning == null) {
                positioning = obj.preserveAspectRatio != null ? obj.preserveAspectRatio : PreserveAspectRatio.LETTERBOX;
            }
            updateStyleForElement(this.state, obj);
            if (display()) {
                float _x = 0.0f;
                float _y = 0.0f;
                if (obj.parent != null) {
                    if (obj.f2962x != null) {
                        _x = obj.f2962x.floatValueX(this);
                    } else {
                        _x = 0.0f;
                    }
                    if (obj.f2963y != null) {
                        _y = obj.f2963y.floatValueY(this);
                    } else {
                        _y = 0.0f;
                    }
                }
                Box viewPortUser = getCurrentViewPortInUserUnits();
                this.state.viewPort = new Box(_x, _y, width != null ? width.floatValueX(this) : viewPortUser.width, height != null ? height.floatValueY(this) : viewPortUser.height);
                if (!this.state.style.overflow.booleanValue()) {
                    setClipRect(this.state.viewPort.minX, this.state.viewPort.minY, this.state.viewPort.width, this.state.viewPort.height);
                }
                checkForClipPath(obj, this.state.viewPort);
                if (viewBox != null) {
                    this.canvas.concat(calculateViewBoxTransform(this.state.viewPort, viewBox, positioning));
                    this.state.viewBox = obj.viewBox;
                } else {
                    this.canvas.translate(_x, _y);
                }
                boolean compositing = pushLayer();
                viewportFill();
                renderChildren(obj, true);
                if (compositing) {
                    popLayer(obj);
                }
                updateParentBoundingBox(obj);
            }
        }
    }

    private void render(Group obj) {
        debug("Group render", new Object[0]);
        updateStyleForElement(this.state, obj);
        if (display()) {
            if (obj.transform != null) {
                this.canvas.concat(obj.transform);
            }
            checkForClipPath(obj);
            boolean compositing = pushLayer();
            renderChildren(obj, true);
            if (compositing) {
                popLayer(obj);
            }
            updateParentBoundingBox(obj);
        }
    }

    private void updateParentBoundingBox(SvgElement obj) {
        if (obj.parent != null && obj.boundingBox != null) {
            Matrix m = new Matrix();
            if (((Matrix) this.matrixStack.peek()).invert(m)) {
                float[] pts = {obj.boundingBox.minX, obj.boundingBox.minY, obj.boundingBox.maxX(), obj.boundingBox.minY, obj.boundingBox.maxX(), obj.boundingBox.maxY(), obj.boundingBox.minX, obj.boundingBox.maxY()};
                m.preConcat(this.canvas.getMatrix());
                m.mapPoints(pts);
                RectF rect = new RectF(pts[0], pts[1], pts[0], pts[1]);
                for (int i = 2; i <= 6; i += 2) {
                    if (pts[i] < rect.left) {
                        rect.left = pts[i];
                    }
                    if (pts[i] > rect.right) {
                        rect.right = pts[i];
                    }
                    if (pts[i + 1] < rect.top) {
                        rect.top = pts[i + 1];
                    }
                    if (pts[i + 1] > rect.bottom) {
                        rect.bottom = pts[i + 1];
                    }
                }
                SvgElement parent = (SvgElement) this.parentStack.peek();
                if (parent.boundingBox == null) {
                    parent.boundingBox = Box.fromLimits(rect.left, rect.top, rect.right, rect.bottom);
                } else {
                    parent.boundingBox.union(Box.fromLimits(rect.left, rect.top, rect.right, rect.bottom));
                }
            }
        }
    }

    private boolean pushLayer() {
        if (!requiresCompositing()) {
            return false;
        }
        this.canvas.saveLayerAlpha(null, clamp255(this.state.style.opacity.floatValue()), 4);
        this.stateStack.push(this.state);
        this.state = (RendererState) this.state.clone();
        if (this.state.style.mask != null && this.state.directRendering) {
            SvgObject ref = this.document.resolveIRI(this.state.style.mask);
            if (ref == null || !(ref instanceof Mask)) {
                error("Mask reference '%s' not found", this.state.style.mask);
                this.state.style.mask = null;
                return true;
            }
            this.canvasStack.push(this.canvas);
            duplicateCanvas();
        }
        return true;
    }

    private void popLayer(SvgElement obj) {
        if (this.state.style.mask != null && this.state.directRendering) {
            SvgObject ref = this.document.resolveIRI(this.state.style.mask);
            duplicateCanvas();
            renderMask((Mask) ref, obj);
            Bitmap maskedContent = processMaskBitmaps();
            this.canvas = (Canvas) this.canvasStack.pop();
            this.canvas.save();
            this.canvas.setMatrix(new Matrix());
            this.canvas.drawBitmap(maskedContent, 0.0f, 0.0f, this.state.fillPaint);
            maskedContent.recycle();
            this.canvas.restore();
        }
        statePop();
    }

    private boolean requiresCompositing() {
        if (this.state.style.mask != null && !this.state.directRendering) {
            warn("Masks are not supported when using getPicture()", new Object[0]);
        }
        if (this.state.style.opacity.floatValue() < 1.0f || (this.state.style.mask != null && this.state.directRendering)) {
            return true;
        }
        return false;
    }

    private void duplicateCanvas() {
        try {
            Bitmap newBM = Bitmap.createBitmap(this.canvas.getWidth(), this.canvas.getHeight(), Config.ARGB_8888);
            this.bitmapStack.push(newBM);
            Canvas newCanvas = new Canvas(newBM);
            newCanvas.setMatrix(this.canvas.getMatrix());
            this.canvas = newCanvas;
        } catch (OutOfMemoryError e) {
            error("Not enough memory to create temporary bitmaps for mask processing", new Object[0]);
            throw e;
        }
    }

    private Bitmap processMaskBitmaps() {
        Bitmap mask = (Bitmap) this.bitmapStack.pop();
        Bitmap maskedContent = (Bitmap) this.bitmapStack.pop();
        int w = mask.getWidth();
        int h = mask.getHeight();
        int[] maskBuf = new int[w];
        int[] maskedContentBuf = new int[w];
        for (int y = 0; y < h; y++) {
            mask.getPixels(maskBuf, 0, w, 0, y, w, 1);
            maskedContent.getPixels(maskedContentBuf, 0, w, 0, y, w, 1);
            for (int x = 0; x < w; x++) {
                int px = maskBuf[x];
                int b = px & 255;
                int g = (px >> 8) & 255;
                int r = (px >> 16) & 255;
                int a = (px >> 24) & 255;
                if (a == 0) {
                    maskedContentBuf[x] = 0;
                } else {
                    int maskAlpha = ((((r * 6963) + (g * 23442)) + (b * 2362)) * a) / 8355840;
                    int content = maskedContentBuf[x];
                    maskedContentBuf[x] = (16777215 & content) | (((((content >> 24) & 255) * maskAlpha) / 255) << 24);
                }
            }
            maskedContent.setPixels(maskedContentBuf, 0, w, 0, y, w, 1);
        }
        mask.recycle();
        return maskedContent;
    }

    private void render(Switch obj) {
        debug("Switch render", new Object[0]);
        updateStyleForElement(this.state, obj);
        if (display()) {
            if (obj.transform != null) {
                this.canvas.concat(obj.transform);
            }
            checkForClipPath(obj);
            boolean compositing = pushLayer();
            renderSwitchChild(obj);
            if (compositing) {
                popLayer(obj);
            }
            updateParentBoundingBox(obj);
        }
    }

    private void renderSwitchChild(Switch obj) {
        String deviceLanguage = Locale.getDefault().getLanguage();
        SVGExternalFileResolver fileResolver = this.document.getFileResolver();
        for (SvgObject child : obj.getChildren()) {
            if (child instanceof SvgConditional) {
                SvgConditional condObj = (SvgConditional) child;
                if (condObj.getRequiredExtensions() == null) {
                    Set<String> syslang = condObj.getSystemLanguage();
                    if (syslang == null || (!syslang.isEmpty() && syslang.contains(deviceLanguage))) {
                        Set<String> reqfeat = condObj.getRequiredFeatures();
                        if (reqfeat == null || (!reqfeat.isEmpty() && SVGParser.supportedFeatures.containsAll(reqfeat))) {
                            Set<String> reqfmts = condObj.getRequiredFormats();
                            if (reqfmts != null) {
                                if (!reqfmts.isEmpty() && fileResolver != null) {
                                    Iterator it = reqfmts.iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            if (!fileResolver.isFormatSupported((String) it.next())) {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                            Set<String> reqfonts = condObj.getRequiredFonts();
                            if (reqfonts != null) {
                                if (!reqfonts.isEmpty() && fileResolver != null) {
                                    for (String fontName : reqfonts) {
                                        if (fileResolver.resolveFont(fontName, this.state.style.fontWeight.intValue(), String.valueOf(this.state.style.fontStyle)) == null) {
                                        }
                                    }
                                }
                            }
                            render(child);
                            return;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
    }

    private void render(Use obj) {
        float _x;
        float _y;
        debug("Use render", new Object[0]);
        if (obj.width != null && obj.width.isZero()) {
            return;
        }
        if (obj.height == null || !obj.height.isZero()) {
            updateStyleForElement(this.state, obj);
            if (display()) {
                SvgObject ref = obj.document.resolveIRI(obj.href);
                if (ref == null) {
                    error("Use reference '%s' not found", obj.href);
                    return;
                }
                if (obj.transform != null) {
                    this.canvas.concat(obj.transform);
                }
                Matrix m = new Matrix();
                if (obj.f2986x != null) {
                    _x = obj.f2986x.floatValueX(this);
                } else {
                    _x = 0.0f;
                }
                if (obj.f2987y != null) {
                    _y = obj.f2987y.floatValueY(this);
                } else {
                    _y = 0.0f;
                }
                m.preTranslate(_x, _y);
                this.canvas.concat(m);
                checkForClipPath(obj);
                boolean compositing = pushLayer();
                parentPush(obj);
                if (ref instanceof Svg) {
                    statePush();
                    Svg svgElem = (Svg) ref;
                    render(svgElem, obj.width != null ? obj.width : svgElem.width, obj.height != null ? obj.height : svgElem.height);
                    statePop();
                } else if (ref instanceof Symbol) {
                    Length _w = obj.width != null ? obj.width : new Length(100.0f, Unit.percent);
                    Length _h = obj.height != null ? obj.height : new Length(100.0f, Unit.percent);
                    statePush();
                    render((Symbol) ref, _w, _h);
                    statePop();
                } else {
                    render(ref);
                }
                parentPop();
                if (compositing) {
                    popLayer(obj);
                }
                updateParentBoundingBox(obj);
            }
        }
    }

    private void render(Path obj) {
        debug("Path render", new Object[0]);
        updateStyleForElement(this.state, obj);
        if (!display() || !visible()) {
            return;
        }
        if (this.state.hasStroke || this.state.hasFill) {
            if (obj.transform != null) {
                this.canvas.concat(obj.transform);
            }
            Path path = new PathConverter(obj.f2955d).getPath();
            if (obj.boundingBox == null) {
                obj.boundingBox = calculatePathBounds(path);
            }
            updateParentBoundingBox(obj);
            checkForGradiantsAndPatterns(obj);
            checkForClipPath(obj);
            boolean compositing = pushLayer();
            if (this.state.hasFill) {
                path.setFillType(getFillTypeFromState());
                doFilledPath(obj, path);
            }
            if (this.state.hasStroke) {
                doStroke(path);
            }
            renderMarkers(obj);
            if (compositing) {
                popLayer(obj);
            }
        }
    }

    private Box calculatePathBounds(Path path) {
        RectF pathBounds = new RectF();
        path.computeBounds(pathBounds, true);
        return new Box(pathBounds.left, pathBounds.top, pathBounds.width(), pathBounds.height());
    }

    private void render(Rect obj) {
        debug("Rect render", new Object[0]);
        if (obj.width != null && obj.height != null && !obj.width.isZero() && !obj.height.isZero()) {
            updateStyleForElement(this.state, obj);
            if (display() && visible()) {
                if (obj.transform != null) {
                    this.canvas.concat(obj.transform);
                }
                Path path = makePathAndBoundingBox(obj);
                updateParentBoundingBox(obj);
                checkForGradiantsAndPatterns(obj);
                checkForClipPath(obj);
                boolean compositing = pushLayer();
                if (this.state.hasFill) {
                    doFilledPath(obj, path);
                }
                if (this.state.hasStroke) {
                    doStroke(path);
                }
                if (compositing) {
                    popLayer(obj);
                }
            }
        }
    }

    private void render(Circle obj) {
        debug("Circle render", new Object[0]);
        if (obj.f2942r != null && !obj.f2942r.isZero()) {
            updateStyleForElement(this.state, obj);
            if (display() && visible()) {
                if (obj.transform != null) {
                    this.canvas.concat(obj.transform);
                }
                Path path = makePathAndBoundingBox(obj);
                updateParentBoundingBox(obj);
                checkForGradiantsAndPatterns(obj);
                checkForClipPath(obj);
                boolean compositing = pushLayer();
                if (this.state.hasFill) {
                    doFilledPath(obj, path);
                }
                if (this.state.hasStroke) {
                    doStroke(path);
                }
                if (compositing) {
                    popLayer(obj);
                }
            }
        }
    }

    private void render(Ellipse obj) {
        debug("Ellipse render", new Object[0]);
        if (obj.f2945rx != null && obj.f2946ry != null && !obj.f2945rx.isZero() && !obj.f2946ry.isZero()) {
            updateStyleForElement(this.state, obj);
            if (display() && visible()) {
                if (obj.transform != null) {
                    this.canvas.concat(obj.transform);
                }
                Path path = makePathAndBoundingBox(obj);
                updateParentBoundingBox(obj);
                checkForGradiantsAndPatterns(obj);
                checkForClipPath(obj);
                boolean compositing = pushLayer();
                if (this.state.hasFill) {
                    doFilledPath(obj, path);
                }
                if (this.state.hasStroke) {
                    doStroke(path);
                }
                if (compositing) {
                    popLayer(obj);
                }
            }
        }
    }

    private void render(Line obj) {
        debug("Line render", new Object[0]);
        updateStyleForElement(this.state, obj);
        if (display() && visible() && this.state.hasStroke) {
            if (obj.transform != null) {
                this.canvas.concat(obj.transform);
            }
            Path path = makePathAndBoundingBox(obj);
            updateParentBoundingBox(obj);
            checkForGradiantsAndPatterns(obj);
            checkForClipPath(obj);
            boolean compositing = pushLayer();
            doStroke(path);
            renderMarkers(obj);
            if (compositing) {
                popLayer(obj);
            }
        }
    }

    private List<MarkerVector> calculateMarkerPositions(Line obj) {
        float _x1;
        float _y1;
        float _x2;
        float _y2;
        if (obj.f2949x1 != null) {
            _x1 = obj.f2949x1.floatValueX(this);
        } else {
            _x1 = 0.0f;
        }
        if (obj.f2951y1 != null) {
            _y1 = obj.f2951y1.floatValueY(this);
        } else {
            _y1 = 0.0f;
        }
        if (obj.f2950x2 != null) {
            _x2 = obj.f2950x2.floatValueX(this);
        } else {
            _x2 = 0.0f;
        }
        if (obj.f2952y2 != null) {
            _y2 = obj.f2952y2.floatValueY(this);
        } else {
            _y2 = 0.0f;
        }
        List<MarkerVector> markers = new ArrayList<>(2);
        markers.add(new MarkerVector(_x1, _y1, _x2 - _x1, _y2 - _y1));
        markers.add(new MarkerVector(_x2, _y2, _x2 - _x1, _y2 - _y1));
        return markers;
    }

    private void render(PolyLine obj) {
        debug("PolyLine render", new Object[0]);
        updateStyleForElement(this.state, obj);
        if (!display() || !visible()) {
            return;
        }
        if (this.state.hasStroke || this.state.hasFill) {
            if (obj.transform != null) {
                this.canvas.concat(obj.transform);
            }
            if (obj.points.length >= 2) {
                Path path = makePathAndBoundingBox(obj);
                updateParentBoundingBox(obj);
                checkForGradiantsAndPatterns(obj);
                checkForClipPath(obj);
                boolean compositing = pushLayer();
                if (this.state.hasFill) {
                    doFilledPath(obj, path);
                }
                if (this.state.hasStroke) {
                    doStroke(path);
                }
                renderMarkers(obj);
                if (compositing) {
                    popLayer(obj);
                }
            }
        }
    }

    private List<MarkerVector> calculateMarkerPositions(PolyLine obj) {
        int numPoints = obj.points.length;
        if (numPoints < 2) {
            return null;
        }
        List<MarkerVector> markers = new ArrayList<>();
        MarkerVector lastPos = new MarkerVector(obj.points[0], obj.points[1], 0.0f, 0.0f);
        float x = 0.0f;
        float y = 0.0f;
        for (int i = 2; i < numPoints; i += 2) {
            x = obj.points[i];
            y = obj.points[i + 1];
            lastPos.add(x, y);
            markers.add(lastPos);
            lastPos = new MarkerVector(x, y, x - lastPos.f2991x, y - lastPos.f2992y);
        }
        if (!(obj instanceof Polygon)) {
            markers.add(lastPos);
            return markers;
        } else if (x == obj.points[0] || y == obj.points[1]) {
            return markers;
        } else {
            float x2 = obj.points[0];
            float y2 = obj.points[1];
            lastPos.add(x2, y2);
            markers.add(lastPos);
            MarkerVector newPos = new MarkerVector(x2, y2, x2 - lastPos.f2991x, y2 - lastPos.f2992y);
            newPos.add((MarkerVector) markers.get(0));
            markers.add(newPos);
            markers.set(0, newPos);
            return markers;
        }
    }

    private void render(Polygon obj) {
        debug("Polygon render", new Object[0]);
        updateStyleForElement(this.state, obj);
        if (!display() || !visible()) {
            return;
        }
        if (this.state.hasStroke || this.state.hasFill) {
            if (obj.transform != null) {
                this.canvas.concat(obj.transform);
            }
            if (obj.points.length >= 2) {
                Path path = makePathAndBoundingBox((PolyLine) obj);
                updateParentBoundingBox(obj);
                checkForGradiantsAndPatterns(obj);
                checkForClipPath(obj);
                boolean compositing = pushLayer();
                if (this.state.hasFill) {
                    doFilledPath(obj, path);
                }
                if (this.state.hasStroke) {
                    doStroke(path);
                }
                renderMarkers(obj);
                if (compositing) {
                    popLayer(obj);
                }
            }
        }
    }

    private void render(Text obj) {
        debug("Text render", new Object[0]);
        updateStyleForElement(this.state, obj);
        if (display()) {
            if (obj.transform != null) {
                this.canvas.concat(obj.transform);
            }
            float x = (obj.f2976x == null || obj.f2976x.size() == 0) ? 0.0f : ((Length) obj.f2976x.get(0)).floatValueX(this);
            float y = (obj.f2977y == null || obj.f2977y.size() == 0) ? 0.0f : ((Length) obj.f2977y.get(0)).floatValueY(this);
            float dx = (obj.f2974dx == null || obj.f2974dx.size() == 0) ? 0.0f : ((Length) obj.f2974dx.get(0)).floatValueX(this);
            float dy = (obj.f2975dy == null || obj.f2975dy.size() == 0) ? 0.0f : ((Length) obj.f2975dy.get(0)).floatValueY(this);
            TextAnchor anchor = getAnchorPosition();
            if (anchor != TextAnchor.Start) {
                float textWidth = calculateTextWidth(obj);
                if (anchor == TextAnchor.Middle) {
                    x -= textWidth / 2.0f;
                } else {
                    x -= textWidth;
                }
            }
            if (obj.boundingBox == null) {
                TextBoundsCalculator proc = new TextBoundsCalculator(x, y);
                enumerateTextSpans(obj, proc);
                obj.boundingBox = new Box(proc.bbox.left, proc.bbox.top, proc.bbox.width(), proc.bbox.height());
            }
            updateParentBoundingBox(obj);
            checkForGradiantsAndPatterns(obj);
            checkForClipPath(obj);
            boolean compositing = pushLayer();
            enumerateTextSpans(obj, new PlainTextDrawer(x + dx, y + dy));
            if (compositing) {
                popLayer(obj);
            }
        }
    }

    private TextAnchor getAnchorPosition() {
        if (this.state.style.direction == TextDirection.LTR || this.state.style.textAnchor == TextAnchor.Middle) {
            return this.state.style.textAnchor;
        }
        return this.state.style.textAnchor == TextAnchor.Start ? TextAnchor.End : TextAnchor.Start;
    }

    private void enumerateTextSpans(TextContainer obj, TextProcessor textprocessor) {
        if (display()) {
            Iterator<SvgObject> iter = obj.children.iterator();
            boolean isFirstChild = true;
            while (iter.hasNext()) {
                SvgObject child = (SvgObject) iter.next();
                if (child instanceof TextSequence) {
                    textprocessor.processText(textXMLSpaceTransform(((TextSequence) child).text, isFirstChild, !iter.hasNext()));
                } else {
                    processTextChild(child, textprocessor);
                }
                isFirstChild = false;
            }
        }
    }

    private void processTextChild(SvgObject obj, TextProcessor textprocessor) {
        if (textprocessor.doTextContainer((TextContainer) obj)) {
            if (obj instanceof TextPath) {
                statePush();
                renderTextPath((TextPath) obj);
                statePop();
            } else if (obj instanceof TSpan) {
                debug("TSpan render", new Object[0]);
                statePush();
                TSpan tspan = (TSpan) obj;
                updateStyleForElement(this.state, tspan);
                if (display()) {
                    float x = 0.0f;
                    float y = 0.0f;
                    float dx = 0.0f;
                    float dy = 0.0f;
                    if (textprocessor instanceof PlainTextDrawer) {
                        x = (tspan.f2976x == null || tspan.f2976x.size() == 0) ? ((PlainTextDrawer) textprocessor).f2993x : ((Length) tspan.f2976x.get(0)).floatValueX(this);
                        y = (tspan.f2977y == null || tspan.f2977y.size() == 0) ? ((PlainTextDrawer) textprocessor).f2994y : ((Length) tspan.f2977y.get(0)).floatValueY(this);
                        dx = (tspan.f2974dx == null || tspan.f2974dx.size() == 0) ? 0.0f : ((Length) tspan.f2974dx.get(0)).floatValueX(this);
                        if (tspan.f2975dy == null || tspan.f2975dy.size() == 0) {
                            dy = 0.0f;
                        } else {
                            dy = ((Length) tspan.f2975dy.get(0)).floatValueY(this);
                        }
                    }
                    checkForGradiantsAndPatterns((SvgElement) tspan.getTextRoot());
                    if (textprocessor instanceof PlainTextDrawer) {
                        ((PlainTextDrawer) textprocessor).f2993x = x + dx;
                        ((PlainTextDrawer) textprocessor).f2994y = y + dy;
                    }
                    boolean compositing = pushLayer();
                    enumerateTextSpans(tspan, textprocessor);
                    if (compositing) {
                        popLayer(tspan);
                    }
                }
                statePop();
            } else if (obj instanceof TRef) {
                statePush();
                TRef tref = (TRef) obj;
                updateStyleForElement(this.state, tref);
                if (display()) {
                    checkForGradiantsAndPatterns((SvgElement) tref.getTextRoot());
                    SvgObject ref = obj.document.resolveIRI(tref.href);
                    if (ref == null || !(ref instanceof TextContainer)) {
                        error("Tref reference '%s' not found", tref.href);
                    } else {
                        StringBuilder str = new StringBuilder();
                        extractRawText((TextContainer) ref, str);
                        if (str.length() > 0) {
                            textprocessor.processText(str.toString());
                        }
                    }
                }
                statePop();
            }
        }
    }

    private void renderTextPath(TextPath obj) {
        float startOffset;
        debug("TextPath render", new Object[0]);
        updateStyleForElement(this.state, obj);
        if (display() && visible()) {
            SvgObject ref = obj.document.resolveIRI(obj.href);
            if (ref == null) {
                error("TextPath reference '%s' not found", obj.href);
                return;
            }
            Path pathObj = (Path) ref;
            Path path = new PathConverter(pathObj.f2955d).getPath();
            if (pathObj.transform != null) {
                path.transform(pathObj.transform);
            }
            PathMeasure measure = new PathMeasure(path, false);
            if (obj.startOffset != null) {
                startOffset = obj.startOffset.floatValue(this, measure.getLength());
            } else {
                startOffset = 0.0f;
            }
            TextAnchor anchor = getAnchorPosition();
            if (anchor != TextAnchor.Start) {
                float textWidth = calculateTextWidth(obj);
                if (anchor == TextAnchor.Middle) {
                    startOffset -= textWidth / 2.0f;
                } else {
                    startOffset -= textWidth;
                }
            }
            checkForGradiantsAndPatterns((SvgElement) obj.getTextRoot());
            boolean compositing = pushLayer();
            enumerateTextSpans(obj, new PathTextDrawer(path, startOffset, 0.0f));
            if (compositing) {
                popLayer(obj);
            }
        }
    }

    private float calculateTextWidth(TextContainer parentTextObj) {
        TextWidthCalculator proc = new TextWidthCalculator(this, null);
        enumerateTextSpans(parentTextObj, proc);
        return proc.f2999x;
    }

    private void extractRawText(TextContainer parent, StringBuilder str) {
        Iterator<SvgObject> iter = parent.children.iterator();
        boolean isFirstChild = true;
        while (iter.hasNext()) {
            SvgObject child = (SvgObject) iter.next();
            if (child instanceof TextContainer) {
                extractRawText((TextContainer) child, str);
            } else if (child instanceof TextSequence) {
                str.append(textXMLSpaceTransform(((TextSequence) child).text, isFirstChild, !iter.hasNext()));
            }
            isFirstChild = false;
        }
    }

    private String textXMLSpaceTransform(String text, boolean isFirstChild, boolean isLastChild) {
        if (this.state.spacePreserve) {
            return text.replaceAll("[\\n\\t]", " ");
        }
        String text2 = text.replaceAll("\\n", "").replaceAll("\\t", " ");
        if (isFirstChild) {
            text2 = text2.replaceAll("^\\s+", "");
        }
        if (isLastChild) {
            text2 = text2.replaceAll("\\s+$", "");
        }
        return text2.replaceAll("\\s{2,}", " ");
    }

    private void render(Symbol obj, Length width, Length height) {
        debug("Symbol render", new Object[0]);
        if (width != null && width.isZero()) {
            return;
        }
        if (height == null || !height.isZero()) {
            PreserveAspectRatio positioning = obj.preserveAspectRatio != null ? obj.preserveAspectRatio : PreserveAspectRatio.LETTERBOX;
            updateStyleForElement(this.state, obj);
            this.state.viewPort = new Box(0.0f, 0.0f, width != null ? width.floatValueX(this) : this.state.viewPort.width, height != null ? height.floatValueX(this) : this.state.viewPort.height);
            if (!this.state.style.overflow.booleanValue()) {
                setClipRect(this.state.viewPort.minX, this.state.viewPort.minY, this.state.viewPort.width, this.state.viewPort.height);
            }
            if (obj.viewBox != null) {
                this.canvas.concat(calculateViewBoxTransform(this.state.viewPort, obj.viewBox, positioning));
                this.state.viewBox = obj.viewBox;
            }
            boolean compositing = pushLayer();
            renderChildren(obj, true);
            if (compositing) {
                popLayer(obj);
            }
            updateParentBoundingBox(obj);
        }
    }

    private void render(Image obj) {
        float _x;
        float _y;
        debug("Image render", new Object[0]);
        if (obj.width != null && !obj.width.isZero() && obj.height != null && !obj.height.isZero() && obj.href != null) {
            PreserveAspectRatio positioning = obj.preserveAspectRatio != null ? obj.preserveAspectRatio : PreserveAspectRatio.LETTERBOX;
            Bitmap image = checkForImageDataURL(obj.href);
            if (image == null) {
                SVGExternalFileResolver fileResolver = this.document.getFileResolver();
                if (fileResolver != null) {
                    image = fileResolver.resolveImage(obj.href);
                } else {
                    return;
                }
            }
            if (image == null) {
                error("Could not locate image '%s'", obj.href);
                return;
            }
            updateStyleForElement(this.state, obj);
            if (display() && visible()) {
                if (obj.transform != null) {
                    this.canvas.concat(obj.transform);
                }
                if (obj.f2947x != null) {
                    _x = obj.f2947x.floatValueX(this);
                } else {
                    _x = 0.0f;
                }
                if (obj.f2948y != null) {
                    _y = obj.f2948y.floatValueY(this);
                } else {
                    _y = 0.0f;
                }
                this.state.viewPort = new Box(_x, _y, obj.width.floatValueX(this), obj.height.floatValueX(this));
                if (!this.state.style.overflow.booleanValue()) {
                    setClipRect(this.state.viewPort.minX, this.state.viewPort.minY, this.state.viewPort.width, this.state.viewPort.height);
                }
                obj.boundingBox = new Box(0.0f, 0.0f, (float) image.getWidth(), (float) image.getHeight());
                this.canvas.concat(calculateViewBoxTransform(this.state.viewPort, obj.boundingBox, positioning));
                updateParentBoundingBox(obj);
                checkForClipPath(obj);
                boolean compositing = pushLayer();
                viewportFill();
                this.canvas.drawBitmap(image, 0.0f, 0.0f, this.state.fillPaint);
                if (compositing) {
                    popLayer(obj);
                }
            }
        }
    }

    private Bitmap checkForImageDataURL(String url) {
        if (!url.startsWith("data:") || url.length() < 14) {
            return null;
        }
        int comma = url.indexOf(44);
        if (comma == -1 || comma < 12 || !";base64".equals(url.substring(comma - 7, comma))) {
            return null;
        }
        byte[] imageData = Base64.decode(url.substring(comma + 1), 0);
        return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
    }

    private boolean display() {
        if (this.state.style.display != null) {
            return this.state.style.display.booleanValue();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean visible() {
        if (this.state.style.visibility != null) {
            return this.state.style.visibility.booleanValue();
        }
        return true;
    }

    private Matrix calculateViewBoxTransform(Box viewPort, Box viewBox, PreserveAspectRatio positioning) {
        Matrix m = new Matrix();
        if (!(positioning == null || positioning.getAlignment() == null)) {
            float xScale = viewPort.width / viewBox.width;
            float yScale = viewPort.height / viewBox.height;
            float xOffset = -viewBox.minX;
            float yOffset = -viewBox.minY;
            if (positioning.equals(PreserveAspectRatio.STRETCH)) {
                m.preTranslate(viewPort.minX, viewPort.minY);
                m.preScale(xScale, yScale);
                m.preTranslate(xOffset, yOffset);
            } else {
                float scale = positioning.getScale() == Scale.Slice ? Math.max(xScale, yScale) : Math.min(xScale, yScale);
                float imageW = viewPort.width / scale;
                float imageH = viewPort.height / scale;
                switch (m1743xb84d42c9()[positioning.getAlignment().ordinal()]) {
                    case 3:
                    case 6:
                    case 9:
                        xOffset -= (viewBox.width - imageW) / 2.0f;
                        break;
                    case 4:
                    case 7:
                    case 10:
                        xOffset -= viewBox.width - imageW;
                        break;
                }
                switch (m1743xb84d42c9()[positioning.getAlignment().ordinal()]) {
                    case 5:
                    case 6:
                    case 7:
                        yOffset -= (viewBox.height - imageH) / 2.0f;
                        break;
                    case 8:
                    case 9:
                    case 10:
                        yOffset -= viewBox.height - imageH;
                        break;
                }
                m.preTranslate(viewPort.minX, viewPort.minY);
                m.preScale(scale, scale);
                m.preTranslate(xOffset, yOffset);
            }
        }
        return m;
    }

    private boolean isSpecified(Style style, long flag) {
        return (style.specifiedFlags & flag) != 0;
    }

    private void updateStyle(RendererState state2, Style style) {
        if (isSpecified(style, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)) {
            state2.style.color = style.color;
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH)) {
            state2.style.opacity = style.opacity;
        }
        if (isSpecified(style, 1)) {
            state2.style.fill = style.fill;
            state2.hasFill = style.fill != null;
        }
        if (isSpecified(style, 4)) {
            state2.style.fillOpacity = style.fillOpacity;
        }
        if (isSpecified(style, 6149)) {
            setPaintColour(state2, true, state2.style.fill);
        }
        if (isSpecified(style, 2)) {
            state2.style.fillRule = style.fillRule;
        }
        if (isSpecified(style, 8)) {
            state2.style.stroke = style.stroke;
            state2.hasStroke = style.stroke != null;
        }
        if (isSpecified(style, 16)) {
            state2.style.strokeOpacity = style.strokeOpacity;
        }
        if (isSpecified(style, 6168)) {
            setPaintColour(state2, false, state2.style.stroke);
        }
        if (isSpecified(style, 34359738368L)) {
            state2.style.vectorEffect = style.vectorEffect;
        }
        if (isSpecified(style, 32)) {
            state2.style.strokeWidth = style.strokeWidth;
            state2.strokePaint.setStrokeWidth(state2.style.strokeWidth.floatValue(this));
        }
        if (isSpecified(style, 64)) {
            state2.style.strokeLineCap = style.strokeLineCap;
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineCaps()[style.strokeLineCap.ordinal()]) {
                case 1:
                    state2.strokePaint.setStrokeCap(Cap.BUTT);
                    break;
                case 2:
                    state2.strokePaint.setStrokeCap(Cap.ROUND);
                    break;
                case 3:
                    state2.strokePaint.setStrokeCap(Cap.SQUARE);
                    break;
            }
        }
        if (isSpecified(style, 128)) {
            state2.style.strokeLineJoin = style.strokeLineJoin;
            switch ($SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$LineJoin()[style.strokeLineJoin.ordinal()]) {
                case 1:
                    state2.strokePaint.setStrokeJoin(Join.MITER);
                    break;
                case 2:
                    state2.strokePaint.setStrokeJoin(Join.ROUND);
                    break;
                case 3:
                    state2.strokePaint.setStrokeJoin(Join.BEVEL);
                    break;
            }
        }
        if (isSpecified(style, 256)) {
            state2.style.strokeMiterLimit = style.strokeMiterLimit;
            state2.strokePaint.setStrokeMiter(style.strokeMiterLimit.floatValue());
        }
        if (isSpecified(style, 512)) {
            state2.style.strokeDashArray = style.strokeDashArray;
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) {
            state2.style.strokeDashOffset = style.strokeDashOffset;
        }
        if (isSpecified(style, 1536)) {
            if (state2.style.strokeDashArray == null) {
                state2.strokePaint.setPathEffect(null);
            } else {
                float intervalSum = 0.0f;
                int n = state2.style.strokeDashArray.length;
                int arrayLen = n % 2 == 0 ? n : n * 2;
                float[] intervals = new float[arrayLen];
                for (int i = 0; i < arrayLen; i++) {
                    intervals[i] = state2.style.strokeDashArray[i % n].floatValue(this);
                    intervalSum += intervals[i];
                }
                if (intervalSum == 0.0f) {
                    state2.strokePaint.setPathEffect(null);
                } else {
                    float offset = state2.style.strokeDashOffset.floatValue(this);
                    if (offset < 0.0f) {
                        offset = intervalSum + (offset % intervalSum);
                    }
                    state2.strokePaint.setPathEffect(new DashPathEffect(intervals, offset));
                }
            }
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_PREPARE)) {
            float currentFontSize = getCurrentFontSize();
            state2.style.fontSize = style.fontSize;
            state2.fillPaint.setTextSize(style.fontSize.floatValue(this, currentFontSize));
            state2.strokePaint.setTextSize(style.fontSize.floatValue(this, currentFontSize));
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_PLAY_FROM_URI)) {
            state2.style.fontFamily = style.fontFamily;
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
            if (style.fontWeight.intValue() == -1 && state2.style.fontWeight.intValue() > 100) {
                Style style2 = state2.style;
                style2.fontWeight = Integer.valueOf(style2.fontWeight.intValue() - 100);
            } else if (style.fontWeight.intValue() != 1 || state2.style.fontWeight.intValue() >= 900) {
                state2.style.fontWeight = style.fontWeight;
            } else {
                Style style3 = state2.style;
                style3.fontWeight = Integer.valueOf(style3.fontWeight.intValue() + 100);
            }
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
            state2.style.fontStyle = style.fontStyle;
        }
        if (isSpecified(style, 106496)) {
            Typeface font = null;
            if (!(state2.style.fontFamily == null || this.document == null)) {
                SVGExternalFileResolver fileResolver = this.document.getFileResolver();
                for (String fontName : state2.style.fontFamily) {
                    font = checkGenericFont(fontName, state2.style.fontWeight, state2.style.fontStyle);
                    if (font == null && fileResolver != null) {
                        font = fileResolver.resolveFont(fontName, state2.style.fontWeight.intValue(), String.valueOf(state2.style.fontStyle));
                        continue;
                    }
                    if (font != null) {
                    }
                }
            }
            if (font == null) {
                font = checkGenericFont("sans-serif", state2.style.fontWeight, state2.style.fontStyle);
            }
            state2.fillPaint.setTypeface(font);
            state2.strokePaint.setTypeface(font);
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_PREPARE_FROM_URI)) {
            state2.style.textDecoration = style.textDecoration;
            state2.fillPaint.setStrikeThruText(style.textDecoration == TextDecoration.LineThrough);
            state2.fillPaint.setUnderlineText(style.textDecoration == TextDecoration.Underline);
            if (VERSION.SDK_INT >= 17) {
                state2.strokePaint.setStrikeThruText(style.textDecoration == TextDecoration.LineThrough);
                state2.strokePaint.setUnderlineText(style.textDecoration == TextDecoration.Underline);
            }
        }
        if (isSpecified(style, 68719476736L)) {
            state2.style.direction = style.direction;
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_SET_REPEAT_MODE)) {
            state2.style.textAnchor = style.textAnchor;
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED)) {
            state2.style.overflow = style.overflow;
        }
        if (isSpecified(style, 2097152)) {
            state2.style.markerStart = style.markerStart;
        }
        if (isSpecified(style, 4194304)) {
            state2.style.markerMid = style.markerMid;
        }
        if (isSpecified(style, 8388608)) {
            state2.style.markerEnd = style.markerEnd;
        }
        if (isSpecified(style, 16777216)) {
            state2.style.display = style.display;
        }
        if (isSpecified(style, 33554432)) {
            state2.style.visibility = style.visibility;
        }
        if (isSpecified(style, 1048576)) {
            state2.style.clip = style.clip;
        }
        if (isSpecified(style, 268435456)) {
            state2.style.clipPath = style.clipPath;
        }
        if (isSpecified(style, 536870912)) {
            state2.style.clipRule = style.clipRule;
        }
        if (isSpecified(style, 1073741824)) {
            state2.style.mask = style.mask;
        }
        if (isSpecified(style, 67108864)) {
            state2.style.stopColor = style.stopColor;
        }
        if (isSpecified(style, 134217728)) {
            state2.style.stopOpacity = style.stopOpacity;
        }
        if (isSpecified(style, 8589934592L)) {
            state2.style.viewportFill = style.viewportFill;
        }
        if (isSpecified(style, 17179869184L)) {
            state2.style.viewportFillOpacity = style.viewportFillOpacity;
        }
    }

    private void setPaintColour(RendererState state2, boolean isFill, SvgPaint paint) {
        int col;
        float paintOpacity = (isFill ? state2.style.fillOpacity : state2.style.strokeOpacity).floatValue();
        if (paint instanceof Colour) {
            col = ((Colour) paint).colour;
        } else if (paint instanceof CurrentColor) {
            col = state2.style.color.colour;
        } else {
            return;
        }
        int col2 = col | (clamp255(paintOpacity) << 24);
        if (isFill) {
            state2.fillPaint.setColor(col2);
        } else {
            state2.strokePaint.setColor(col2);
        }
    }

    private Typeface checkGenericFont(String fontName, Integer fontWeight, FontStyle fontStyle) {
        boolean italic;
        if (fontStyle == FontStyle.Italic) {
            italic = true;
        } else {
            italic = false;
        }
        int typefaceStyle = fontWeight.intValue() > 500 ? italic ? 3 : 1 : italic ? 2 : 0;
        if (fontName.equals("serif")) {
            return Typeface.create(Typeface.SERIF, typefaceStyle);
        }
        if (fontName.equals("sans-serif")) {
            return Typeface.create(Typeface.SANS_SERIF, typefaceStyle);
        }
        if (fontName.equals("monospace")) {
            return Typeface.create(Typeface.MONOSPACE, typefaceStyle);
        }
        if (fontName.equals("cursive")) {
            return Typeface.create(Typeface.SANS_SERIF, typefaceStyle);
        }
        if (fontName.equals("fantasy")) {
            return Typeface.create(Typeface.SANS_SERIF, typefaceStyle);
        }
        return null;
    }

    private int clamp255(float val) {
        int i = (int) (256.0f * val);
        if (i < 0) {
            return 0;
        }
        if (i > 255) {
            return 255;
        }
        return i;
    }

    private FillType getFillTypeFromState() {
        if (this.state.style.fillRule == null) {
            return FillType.WINDING;
        }
        switch ($SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$FillRule()[this.state.style.fillRule.ordinal()]) {
            case 2:
                return FillType.EVEN_ODD;
            default:
                return FillType.WINDING;
        }
    }

    private void setClipRect(float minX, float minY, float width, float height) {
        float left = minX;
        float top = minY;
        float right = minX + width;
        float bottom = minY + height;
        if (this.state.style.clip != null) {
            left += this.state.style.clip.left.floatValueX(this);
            top += this.state.style.clip.top.floatValueY(this);
            right -= this.state.style.clip.right.floatValueX(this);
            bottom -= this.state.style.clip.bottom.floatValueY(this);
        }
        this.canvas.clipRect(left, top, right, bottom);
    }

    private void viewportFill() {
        int col;
        if (this.state.style.viewportFill instanceof Colour) {
            col = ((Colour) this.state.style.viewportFill).colour;
        } else if (this.state.style.viewportFill instanceof CurrentColor) {
            col = this.state.style.color.colour;
        } else {
            return;
        }
        if (this.state.style.viewportFillOpacity != null) {
            col |= clamp255(this.state.style.viewportFillOpacity.floatValue()) << 24;
        }
        this.canvas.drawColor(col);
    }

    /* access modifiers changed from: private */
    public static void arcTo(float lastX, float lastY, float rx, float ry, float angle, boolean largeArcFlag, boolean sweepFlag, float x, float y, PathInterface pather) {
        if (lastX != x || lastY != y) {
            if (rx == 0.0f || ry == 0.0f) {
                pather.lineTo(x, y);
                return;
            }
            float rx2 = Math.abs(rx);
            float ry2 = Math.abs(ry);
            float angleRad = (float) Math.toRadians(((double) angle) % 360.0d);
            double cosAngle = Math.cos((double) angleRad);
            double sinAngle = Math.sin((double) angleRad);
            double dx2 = ((double) (lastX - x)) / 2.0d;
            double dy2 = ((double) (lastY - y)) / 2.0d;
            double x1 = (cosAngle * dx2) + (sinAngle * dy2);
            double y1 = ((-sinAngle) * dx2) + (cosAngle * dy2);
            double rx_sq = (double) (rx2 * rx2);
            double ry_sq = (double) (ry2 * ry2);
            double x1_sq = x1 * x1;
            double y1_sq = y1 * y1;
            double radiiCheck = (x1_sq / rx_sq) + (y1_sq / ry_sq);
            if (radiiCheck > 1.0d) {
                rx2 *= (float) Math.sqrt(radiiCheck);
                ry2 *= (float) Math.sqrt(radiiCheck);
                rx_sq = (double) (rx2 * rx2);
                ry_sq = (double) (ry2 * ry2);
            }
            double sign = (double) (largeArcFlag == sweepFlag ? -1 : 1);
            double sq = (((rx_sq * ry_sq) - (rx_sq * y1_sq)) - (ry_sq * x1_sq)) / ((rx_sq * y1_sq) + (ry_sq * x1_sq));
            if (sq < 0.0d) {
                sq = 0.0d;
            }
            double coef = sign * Math.sqrt(sq);
            double cx1 = coef * ((((double) rx2) * y1) / ((double) ry2));
            double cy1 = coef * (-((((double) ry2) * x1) / ((double) rx2)));
            double cx = (((double) (lastX + x)) / 2.0d) + ((cosAngle * cx1) - (sinAngle * cy1));
            double cy = (((double) (lastY + y)) / 2.0d) + (sinAngle * cx1) + (cosAngle * cy1);
            double ux = (x1 - cx1) / ((double) rx2);
            double uy = (y1 - cy1) / ((double) ry2);
            double vx = ((-x1) - cx1) / ((double) rx2);
            double vy = ((-y1) - cy1) / ((double) ry2);
            double angleStart = Math.toDegrees(Math.acos(ux / Math.sqrt((ux * ux) + (uy * uy))) * (uy < 0.0d ? -1.0d : 1.0d));
            double angleExtent = Math.toDegrees(Math.acos(((ux * vx) + (uy * vy)) / Math.sqrt(((ux * ux) + (uy * uy)) * ((vx * vx) + (vy * vy)))) * ((ux * vy) - (uy * vx) < 0.0d ? -1.0d : 1.0d));
            if (!sweepFlag && angleExtent > 0.0d) {
                angleExtent -= 360.0d;
            } else if (sweepFlag && angleExtent < 0.0d) {
                angleExtent += 360.0d;
            }
            float[] bezierPoints = arcToBeziers(angleStart % 360.0d, angleExtent % 360.0d);
            Matrix m = new Matrix();
            m.postScale(rx2, ry2);
            m.postRotate(angle);
            m.postTranslate((float) cx, (float) cy);
            m.mapPoints(bezierPoints);
            bezierPoints[bezierPoints.length - 2] = x;
            bezierPoints[bezierPoints.length - 1] = y;
            for (int i = 0; i < bezierPoints.length; i += 6) {
                pather.cubicTo(bezierPoints[i], bezierPoints[i + 1], bezierPoints[i + 2], bezierPoints[i + 3], bezierPoints[i + 4], bezierPoints[i + 5]);
            }
        }
    }

    private static float[] arcToBeziers(double angleStart, double angleExtent) {
        int numSegments = (int) Math.ceil(Math.abs(angleExtent) / 90.0d);
        double angleStart2 = Math.toRadians(angleStart);
        float angleIncrement = (float) (Math.toRadians(angleExtent) / ((double) numSegments));
        double controlLength = (1.3333333333333333d * Math.sin(((double) angleIncrement) / 2.0d)) / (1.0d + Math.cos(((double) angleIncrement) / 2.0d));
        float[] coords = new float[(numSegments * 6)];
        int pos = 0;
        for (int i = 0; i < numSegments; i++) {
            double angle = angleStart2 + ((double) (((float) i) * angleIncrement));
            double dx = Math.cos(angle);
            double dy = Math.sin(angle);
            int pos2 = pos + 1;
            coords[pos] = (float) (dx - (controlLength * dy));
            int pos3 = pos2 + 1;
            coords[pos2] = (float) ((controlLength * dx) + dy);
            double angle2 = angle + ((double) angleIncrement);
            double dx2 = Math.cos(angle2);
            double dy2 = Math.sin(angle2);
            int pos4 = pos3 + 1;
            coords[pos3] = (float) ((controlLength * dy2) + dx2);
            int pos5 = pos4 + 1;
            coords[pos4] = (float) (dy2 - (controlLength * dx2));
            int pos6 = pos5 + 1;
            coords[pos5] = (float) dx2;
            pos = pos6 + 1;
            coords[pos6] = (float) dy2;
        }
        return coords;
    }

    private void renderMarkers(GraphicsElement obj) {
        List<MarkerVector> markers;
        if (this.state.style.markerStart != null || this.state.style.markerMid != null || this.state.style.markerEnd != null) {
            Marker _markerStart = null;
            Marker _markerMid = null;
            Marker _markerEnd = null;
            if (this.state.style.markerStart != null) {
                SvgObject ref = obj.document.resolveIRI(this.state.style.markerStart);
                if (ref != null) {
                    _markerStart = (Marker) ref;
                } else {
                    error("Marker reference '%s' not found", this.state.style.markerStart);
                }
            }
            if (this.state.style.markerMid != null) {
                SvgObject ref2 = obj.document.resolveIRI(this.state.style.markerMid);
                if (ref2 != null) {
                    _markerMid = (Marker) ref2;
                } else {
                    error("Marker reference '%s' not found", this.state.style.markerMid);
                }
            }
            if (this.state.style.markerEnd != null) {
                SvgObject ref3 = obj.document.resolveIRI(this.state.style.markerEnd);
                if (ref3 != null) {
                    _markerEnd = (Marker) ref3;
                } else {
                    error("Marker reference '%s' not found", this.state.style.markerEnd);
                }
            }
            if (obj instanceof Path) {
                markers = new MarkerPositionCalculator(((Path) obj).f2955d).getMarkers();
            } else if (obj instanceof Line) {
                markers = calculateMarkerPositions((Line) obj);
            } else {
                markers = calculateMarkerPositions((PolyLine) obj);
            }
            if (markers != null) {
                int markerCount = markers.size();
                if (markerCount != 0) {
                    Style style = this.state.style;
                    Style style2 = this.state.style;
                    this.state.style.markerEnd = null;
                    style2.markerMid = null;
                    style.markerStart = null;
                    if (_markerStart != null) {
                        renderMarker(_markerStart, (MarkerVector) markers.get(0));
                    }
                    if (_markerMid != null) {
                        for (int i = 1; i < markerCount - 1; i++) {
                            renderMarker(_markerMid, (MarkerVector) markers.get(i));
                        }
                    }
                    if (_markerEnd != null) {
                        renderMarker(_markerEnd, (MarkerVector) markers.get(markerCount - 1));
                    }
                }
            }
        }
    }

    private void renderMarker(Marker marker, MarkerVector pos) {
        float unitsScale;
        float aspectScale;
        float angle = 0.0f;
        statePush();
        if (marker.orient != null) {
            if (!Float.isNaN(marker.orient.floatValue())) {
                angle = marker.orient.floatValue();
            } else if (!(pos.f2989dx == 0.0f && pos.f2990dy == 0.0f)) {
                angle = (float) Math.toDegrees(Math.atan2((double) pos.f2990dy, (double) pos.f2989dx));
            }
        }
        if (marker.markerUnitsAreUser) {
            unitsScale = 1.0f;
        } else {
            unitsScale = this.state.style.strokeWidth.floatValue(this.dpi);
        }
        this.state = findInheritFromAncestorState(marker);
        Matrix m = new Matrix();
        m.preTranslate(pos.f2991x, pos.f2992y);
        m.preRotate(angle);
        m.preScale(unitsScale, unitsScale);
        float _refX = marker.refX != null ? marker.refX.floatValueX(this) : 0.0f;
        float _refY = marker.refY != null ? marker.refY.floatValueY(this) : 0.0f;
        float _markerWidth = marker.markerWidth != null ? marker.markerWidth.floatValueX(this) : 3.0f;
        float _markerHeight = marker.markerHeight != null ? marker.markerHeight.floatValueY(this) : 3.0f;
        if (marker.viewBox != null) {
            float xScale = _markerWidth / marker.viewBox.width;
            float yScale = _markerHeight / marker.viewBox.height;
            PreserveAspectRatio positioning = marker.preserveAspectRatio != null ? marker.preserveAspectRatio : PreserveAspectRatio.LETTERBOX;
            if (!positioning.equals(PreserveAspectRatio.STRETCH)) {
                if (positioning.getScale() == Scale.Slice) {
                    aspectScale = Math.max(xScale, yScale);
                } else {
                    aspectScale = Math.min(xScale, yScale);
                }
                yScale = aspectScale;
                xScale = aspectScale;
            }
            m.preTranslate((-_refX) * xScale, (-_refY) * yScale);
            this.canvas.concat(m);
            float imageW = marker.viewBox.width * xScale;
            float imageH = marker.viewBox.height * yScale;
            float xOffset = 0.0f;
            float yOffset = 0.0f;
            switch (m1743xb84d42c9()[positioning.getAlignment().ordinal()]) {
                case 3:
                case 6:
                case 9:
                    xOffset = 0.0f - ((_markerWidth - imageW) / 2.0f);
                    break;
                case 4:
                case 7:
                case 10:
                    xOffset = 0.0f - (_markerWidth - imageW);
                    break;
            }
            switch (m1743xb84d42c9()[positioning.getAlignment().ordinal()]) {
                case 5:
                case 6:
                case 7:
                    yOffset = 0.0f - ((_markerHeight - imageH) / 2.0f);
                    break;
                case 8:
                case 9:
                case 10:
                    yOffset = 0.0f - (_markerHeight - imageH);
                    break;
            }
            if (!this.state.style.overflow.booleanValue()) {
                setClipRect(xOffset, yOffset, _markerWidth, _markerHeight);
            }
            m.reset();
            m.preScale(xScale, yScale);
            this.canvas.concat(m);
        } else {
            m.preTranslate(-_refX, -_refY);
            this.canvas.concat(m);
            if (!this.state.style.overflow.booleanValue()) {
                setClipRect(0.0f, 0.0f, _markerWidth, _markerHeight);
            }
        }
        boolean compositing = pushLayer();
        renderChildren(marker, false);
        if (compositing) {
            popLayer(marker);
        }
        statePop();
    }

    private RendererState findInheritFromAncestorState(SvgObject obj) {
        RendererState newState = new RendererState();
        updateStyle(newState, Style.getDefaultStyle());
        return findInheritFromAncestorState(obj, newState);
    }

    private RendererState findInheritFromAncestorState(SvgObject obj, RendererState newState) {
        List<SvgElementBase> ancestors = new ArrayList<>();
        while (true) {
            if (obj instanceof SvgElementBase) {
                ancestors.add(0, (SvgElementBase) obj);
            }
            if (obj.parent == null) {
                break;
            }
            obj = (SvgObject) obj.parent;
        }
        for (SvgElementBase ancestor : ancestors) {
            updateStyleForElement(newState, ancestor);
        }
        newState.viewBox = this.document.getRootElement().viewBox;
        if (newState.viewBox == null) {
            newState.viewBox = this.canvasViewPort;
        }
        newState.viewPort = this.canvasViewPort;
        newState.directRendering = this.state.directRendering;
        return newState;
    }

    private void checkForGradiantsAndPatterns(SvgElement obj) {
        if (this.state.style.fill instanceof PaintReference) {
            decodePaintReference(true, obj.boundingBox, (PaintReference) this.state.style.fill);
        }
        if (this.state.style.stroke instanceof PaintReference) {
            decodePaintReference(false, obj.boundingBox, (PaintReference) this.state.style.stroke);
        }
    }

    private void decodePaintReference(boolean isFill, Box boundingBox, PaintReference paintref) {
        SvgObject ref = this.document.resolveIRI(paintref.href);
        if (ref == null) {
            String str = "%s reference '%s' not found";
            Object[] objArr = new Object[2];
            objArr[0] = isFill ? "Fill" : "Stroke";
            objArr[1] = paintref.href;
            error(str, objArr);
            if (paintref.fallback != null) {
                setPaintColour(this.state, isFill, paintref.fallback);
            } else if (isFill) {
                this.state.hasFill = false;
            } else {
                this.state.hasStroke = false;
            }
        } else {
            if (ref instanceof SvgLinearGradient) {
                makeLinearGradiant(isFill, boundingBox, (SvgLinearGradient) ref);
            }
            if (ref instanceof SvgRadialGradient) {
                makeRadialGradiant(isFill, boundingBox, (SvgRadialGradient) ref);
            }
            if (ref instanceof SolidColor) {
                setSolidColor(isFill, (SolidColor) ref);
            }
        }
    }

    private void makeLinearGradiant(boolean isFill, Box boundingBox, SvgLinearGradient gradient) {
        float _x1;
        float _y1;
        float _x2;
        float _y2;
        if (gradient.href != null) {
            fillInChainedGradientFields((GradientElement) gradient, gradient.href);
        }
        boolean userUnits = gradient.gradientUnitsAreUser != null && gradient.gradientUnitsAreUser.booleanValue();
        Paint paint = isFill ? this.state.fillPaint : this.state.strokePaint;
        if (userUnits) {
            Box viewPortUser = getCurrentViewPortInUserUnits();
            _x1 = gradient.f2965x1 != null ? gradient.f2965x1.floatValueX(this) : 0.0f;
            _y1 = gradient.f2967y1 != null ? gradient.f2967y1.floatValueY(this) : 0.0f;
            _x2 = gradient.f2966x2 != null ? gradient.f2966x2.floatValueX(this) : viewPortUser.width;
            _y2 = gradient.f2968y2 != null ? gradient.f2968y2.floatValueY(this) : 0.0f;
        } else {
            _x1 = gradient.f2965x1 != null ? gradient.f2965x1.floatValue(this, 1.0f) : 0.0f;
            _y1 = gradient.f2967y1 != null ? gradient.f2967y1.floatValue(this, 1.0f) : 0.0f;
            _x2 = gradient.f2966x2 != null ? gradient.f2966x2.floatValue(this, 1.0f) : 1.0f;
            _y2 = gradient.f2968y2 != null ? gradient.f2968y2.floatValue(this, 1.0f) : 0.0f;
        }
        statePush();
        this.state = findInheritFromAncestorState(gradient);
        Matrix m = new Matrix();
        if (!userUnits) {
            m.preTranslate(boundingBox.minX, boundingBox.minY);
            m.preScale(boundingBox.width, boundingBox.height);
        }
        if (gradient.gradientTransform != null) {
            m.preConcat(gradient.gradientTransform);
        }
        int numStops = gradient.children.size();
        if (numStops == 0) {
            statePop();
            if (isFill) {
                this.state.hasFill = false;
            } else {
                this.state.hasStroke = false;
            }
        } else {
            int[] colours = new int[numStops];
            float[] positions = new float[numStops];
            int i = 0;
            float lastOffset = -1.0f;
            for (SvgObject child : gradient.children) {
                Stop stop = (Stop) child;
                if (i == 0 || stop.offset.floatValue() >= lastOffset) {
                    positions[i] = stop.offset.floatValue();
                    lastOffset = stop.offset.floatValue();
                } else {
                    positions[i] = lastOffset;
                }
                statePush();
                updateStyleForElement(this.state, stop);
                Colour col = (Colour) this.state.style.stopColor;
                if (col == null) {
                    col = Colour.BLACK;
                }
                colours[i] = (clamp255(this.state.style.stopOpacity.floatValue()) << 24) | col.colour;
                i++;
                statePop();
            }
            if ((_x1 == _x2 && _y1 == _y2) || numStops == 1) {
                statePop();
                paint.setColor(colours[numStops - 1]);
                return;
            }
            TileMode tileMode = TileMode.CLAMP;
            if (gradient.spreadMethod != null) {
                if (gradient.spreadMethod == GradientSpread.reflect) {
                    tileMode = TileMode.MIRROR;
                } else {
                    if (gradient.spreadMethod == GradientSpread.repeat) {
                        tileMode = TileMode.REPEAT;
                    }
                }
            }
            statePop();
            LinearGradient gr = new LinearGradient(_x1, _y1, _x2, _y2, colours, positions, tileMode);
            gr.setLocalMatrix(m);
            paint.setShader(gr);
        }
    }

    private void makeRadialGradiant(boolean isFill, Box boundingBox, SvgRadialGradient gradient) {
        float _cx;
        float _cy;
        float _r;
        if (gradient.href != null) {
            fillInChainedGradientFields((GradientElement) gradient, gradient.href);
        }
        boolean userUnits = gradient.gradientUnitsAreUser != null && gradient.gradientUnitsAreUser.booleanValue();
        Paint paint = isFill ? this.state.fillPaint : this.state.strokePaint;
        if (userUnits) {
            Length fiftyPercent = new Length(50.0f, Unit.percent);
            _cx = gradient.f2969cx != null ? gradient.f2969cx.floatValueX(this) : fiftyPercent.floatValueX(this);
            _cy = gradient.f2970cy != null ? gradient.f2970cy.floatValueY(this) : fiftyPercent.floatValueY(this);
            _r = gradient.f2973r != null ? gradient.f2973r.floatValue(this) : fiftyPercent.floatValue(this);
        } else {
            _cx = gradient.f2969cx != null ? gradient.f2969cx.floatValue(this, 1.0f) : 0.5f;
            _cy = gradient.f2970cy != null ? gradient.f2970cy.floatValue(this, 1.0f) : 0.5f;
            _r = gradient.f2973r != null ? gradient.f2973r.floatValue(this, 1.0f) : 0.5f;
        }
        statePush();
        this.state = findInheritFromAncestorState(gradient);
        Matrix m = new Matrix();
        if (!userUnits) {
            m.preTranslate(boundingBox.minX, boundingBox.minY);
            m.preScale(boundingBox.width, boundingBox.height);
        }
        if (gradient.gradientTransform != null) {
            m.preConcat(gradient.gradientTransform);
        }
        int numStops = gradient.children.size();
        if (numStops == 0) {
            statePop();
            if (isFill) {
                this.state.hasFill = false;
            } else {
                this.state.hasStroke = false;
            }
        } else {
            int[] colours = new int[numStops];
            float[] positions = new float[numStops];
            int i = 0;
            float lastOffset = -1.0f;
            for (SvgObject child : gradient.children) {
                Stop stop = (Stop) child;
                if (i == 0 || stop.offset.floatValue() >= lastOffset) {
                    positions[i] = stop.offset.floatValue();
                    lastOffset = stop.offset.floatValue();
                } else {
                    positions[i] = lastOffset;
                }
                statePush();
                updateStyleForElement(this.state, stop);
                Colour col = (Colour) this.state.style.stopColor;
                if (col == null) {
                    col = Colour.BLACK;
                }
                colours[i] = (clamp255(this.state.style.stopOpacity.floatValue()) << 24) | col.colour;
                i++;
                statePop();
            }
            if (_r == 0.0f || numStops == 1) {
                statePop();
                paint.setColor(colours[numStops - 1]);
                return;
            }
            TileMode tileMode = TileMode.CLAMP;
            if (gradient.spreadMethod != null) {
                if (gradient.spreadMethod == GradientSpread.reflect) {
                    tileMode = TileMode.MIRROR;
                } else {
                    if (gradient.spreadMethod == GradientSpread.repeat) {
                        tileMode = TileMode.REPEAT;
                    }
                }
            }
            statePop();
            RadialGradient gr = new RadialGradient(_cx, _cy, _r, colours, positions, tileMode);
            gr.setLocalMatrix(m);
            paint.setShader(gr);
        }
    }

    private void fillInChainedGradientFields(GradientElement gradient, String href) {
        SvgObject ref = gradient.document.resolveIRI(href);
        if (ref == null) {
            warn("Gradient reference '%s' not found", href);
        } else if (!(ref instanceof GradientElement)) {
            error("Gradient href attributes must point to other gradient elements", new Object[0]);
        } else if (ref == gradient) {
            error("Circular reference in gradient href attribute '%s'", href);
        } else {
            GradientElement grRef = (GradientElement) ref;
            if (gradient.gradientUnitsAreUser == null) {
                gradient.gradientUnitsAreUser = grRef.gradientUnitsAreUser;
            }
            if (gradient.gradientTransform == null) {
                gradient.gradientTransform = grRef.gradientTransform;
            }
            if (gradient.spreadMethod == null) {
                gradient.spreadMethod = grRef.spreadMethod;
            }
            if (gradient.children.isEmpty()) {
                gradient.children = grRef.children;
            }
            try {
                if (gradient instanceof SvgLinearGradient) {
                    fillInChainedGradientFields((SvgLinearGradient) gradient, (SvgLinearGradient) ref);
                } else {
                    fillInChainedGradientFields((SvgRadialGradient) gradient, (SvgRadialGradient) ref);
                }
            } catch (ClassCastException e) {
            }
            if (grRef.href != null) {
                fillInChainedGradientFields(gradient, grRef.href);
            }
        }
    }

    private void fillInChainedGradientFields(SvgLinearGradient gradient, SvgLinearGradient grRef) {
        if (gradient.f2965x1 == null) {
            gradient.f2965x1 = grRef.f2965x1;
        }
        if (gradient.f2967y1 == null) {
            gradient.f2967y1 = grRef.f2967y1;
        }
        if (gradient.f2966x2 == null) {
            gradient.f2966x2 = grRef.f2966x2;
        }
        if (gradient.f2968y2 == null) {
            gradient.f2968y2 = grRef.f2968y2;
        }
    }

    private void fillInChainedGradientFields(SvgRadialGradient gradient, SvgRadialGradient grRef) {
        if (gradient.f2969cx == null) {
            gradient.f2969cx = grRef.f2969cx;
        }
        if (gradient.f2970cy == null) {
            gradient.f2970cy = grRef.f2970cy;
        }
        if (gradient.f2973r == null) {
            gradient.f2973r = grRef.f2973r;
        }
        if (gradient.f2971fx == null) {
            gradient.f2971fx = grRef.f2971fx;
        }
        if (gradient.f2972fy == null) {
            gradient.f2972fy = grRef.f2972fy;
        }
    }

    private void setSolidColor(boolean isFill, SolidColor ref) {
        boolean z = true;
        if (isFill) {
            if (isSpecified(ref.baseStyle, 2147483648L)) {
                this.state.style.fill = ref.baseStyle.solidColor;
                RendererState rendererState = this.state;
                if (ref.baseStyle.solidColor == null) {
                    z = false;
                }
                rendererState.hasFill = z;
            }
            if (isSpecified(ref.baseStyle, 4294967296L)) {
                this.state.style.fillOpacity = ref.baseStyle.solidOpacity;
            }
            if (isSpecified(ref.baseStyle, 6442450944L)) {
                setPaintColour(this.state, isFill, this.state.style.fill);
                return;
            }
            return;
        }
        if (isSpecified(ref.baseStyle, 2147483648L)) {
            this.state.style.stroke = ref.baseStyle.solidColor;
            RendererState rendererState2 = this.state;
            if (ref.baseStyle.solidColor == null) {
                z = false;
            }
            rendererState2.hasStroke = z;
        }
        if (isSpecified(ref.baseStyle, 4294967296L)) {
            this.state.style.strokeOpacity = ref.baseStyle.solidOpacity;
        }
        if (isSpecified(ref.baseStyle, 6442450944L)) {
            setPaintColour(this.state, isFill, this.state.style.stroke);
        }
    }

    private void checkForClipPath(SvgElement obj) {
        checkForClipPath(obj, obj.boundingBox);
    }

    private void checkForClipPath(SvgElement obj, Box boundingBox) {
        boolean userUnits;
        if (this.state.style.clipPath != null) {
            SvgObject ref = obj.document.resolveIRI(this.state.style.clipPath);
            if (ref == null) {
                error("ClipPath reference '%s' not found", this.state.style.clipPath);
                return;
            }
            ClipPath clipPath = (ClipPath) ref;
            if (clipPath.children.isEmpty()) {
                this.canvas.clipRect(0, 0, 0, 0);
                return;
            }
            if (clipPath.clipPathUnitsAreUser == null || clipPath.clipPathUnitsAreUser.booleanValue()) {
                userUnits = true;
            } else {
                userUnits = false;
            }
            if (!(obj instanceof Group) || userUnits) {
                clipStatePush();
                if (!userUnits) {
                    Matrix m = new Matrix();
                    m.preTranslate(boundingBox.minX, boundingBox.minY);
                    m.preScale(boundingBox.width, boundingBox.height);
                    this.canvas.concat(m);
                }
                if (clipPath.transform != null) {
                    this.canvas.concat(clipPath.transform);
                }
                this.state = findInheritFromAncestorState(clipPath);
                checkForClipPath(clipPath);
                Path combinedPath = new Path();
                for (SvgObject child : clipPath.children) {
                    addObjectToClip(child, true, combinedPath, new Matrix());
                }
                this.canvas.clipPath(combinedPath);
                clipStatePop();
                return;
            }
            warn("<clipPath clipPathUnits=\"objectBoundingBox\"> is not supported when referenced from container elements (like %s)", obj.getClass().getSimpleName());
        }
    }

    private void addObjectToClip(SvgObject obj, boolean allowUse, Path combinedPath, Matrix combinedPathMatrix) {
        if (display()) {
            clipStatePush();
            if (obj instanceof Use) {
                if (allowUse) {
                    addObjectToClip((Use) obj, combinedPath, combinedPathMatrix);
                } else {
                    error("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
                }
            } else if (obj instanceof Path) {
                addObjectToClip((Path) obj, combinedPath, combinedPathMatrix);
            } else if (obj instanceof Text) {
                addObjectToClip((Text) obj, combinedPath, combinedPathMatrix);
            } else if (obj instanceof GraphicsElement) {
                addObjectToClip((GraphicsElement) obj, combinedPath, combinedPathMatrix);
            } else {
                error("Invalid %s element found in clipPath definition", obj.getClass().getSimpleName());
            }
            clipStatePop();
        }
    }

    private void clipStatePush() {
        this.canvas.save(1);
        this.stateStack.push(this.state);
        this.state = (RendererState) this.state.clone();
    }

    private void clipStatePop() {
        this.canvas.restore();
        this.state = (RendererState) this.stateStack.pop();
    }

    private FillType getClipRuleFromState() {
        if (this.state.style.clipRule == null) {
            return FillType.WINDING;
        }
        switch ($SWITCH_TABLE$com$caverock$androidsvg$SVG$Style$FillRule()[this.state.style.clipRule.ordinal()]) {
            case 2:
                return FillType.EVEN_ODD;
            default:
                return FillType.WINDING;
        }
    }

    private void addObjectToClip(Path obj, Path combinedPath, Matrix combinedPathMatrix) {
        updateStyleForElement(this.state, obj);
        if (display() && visible()) {
            if (obj.transform != null) {
                combinedPathMatrix.preConcat(obj.transform);
            }
            Path path = new PathConverter(obj.f2955d).getPath();
            if (obj.boundingBox == null) {
                obj.boundingBox = calculatePathBounds(path);
            }
            checkForClipPath(obj);
            combinedPath.setFillType(getClipRuleFromState());
            combinedPath.addPath(path, combinedPathMatrix);
        }
    }

    private void addObjectToClip(GraphicsElement obj, Path combinedPath, Matrix combinedPathMatrix) {
        Path path;
        updateStyleForElement(this.state, obj);
        if (display() && visible()) {
            if (obj.transform != null) {
                combinedPathMatrix.preConcat(obj.transform);
            }
            if (obj instanceof Rect) {
                path = makePathAndBoundingBox((Rect) obj);
            } else if (obj instanceof Circle) {
                path = makePathAndBoundingBox((Circle) obj);
            } else if (obj instanceof Ellipse) {
                path = makePathAndBoundingBox((Ellipse) obj);
            } else if (obj instanceof PolyLine) {
                path = makePathAndBoundingBox((PolyLine) obj);
            } else {
                return;
            }
            checkForClipPath(obj);
            combinedPath.setFillType(path.getFillType());
            combinedPath.addPath(path, combinedPathMatrix);
        }
    }

    private void addObjectToClip(Use obj, Path combinedPath, Matrix combinedPathMatrix) {
        updateStyleForElement(this.state, obj);
        if (display() && visible()) {
            if (obj.transform != null) {
                combinedPathMatrix.preConcat(obj.transform);
            }
            SvgObject ref = obj.document.resolveIRI(obj.href);
            if (ref == null) {
                error("Use reference '%s' not found", obj.href);
                return;
            }
            checkForClipPath(obj);
            addObjectToClip(ref, false, combinedPath, combinedPathMatrix);
        }
    }

    private void addObjectToClip(Text obj, Path combinedPath, Matrix combinedPathMatrix) {
        updateStyleForElement(this.state, obj);
        if (display()) {
            if (obj.transform != null) {
                combinedPathMatrix.preConcat(obj.transform);
            }
            float x = (obj.f2976x == null || obj.f2976x.size() == 0) ? 0.0f : ((Length) obj.f2976x.get(0)).floatValueX(this);
            float y = (obj.f2977y == null || obj.f2977y.size() == 0) ? 0.0f : ((Length) obj.f2977y.get(0)).floatValueY(this);
            float dx = (obj.f2974dx == null || obj.f2974dx.size() == 0) ? 0.0f : ((Length) obj.f2974dx.get(0)).floatValueX(this);
            float dy = (obj.f2975dy == null || obj.f2975dy.size() == 0) ? 0.0f : ((Length) obj.f2975dy.get(0)).floatValueY(this);
            if (this.state.style.textAnchor != TextAnchor.Start) {
                float textWidth = calculateTextWidth(obj);
                if (this.state.style.textAnchor == TextAnchor.Middle) {
                    x -= textWidth / 2.0f;
                } else {
                    x -= textWidth;
                }
            }
            if (obj.boundingBox == null) {
                TextBoundsCalculator proc = new TextBoundsCalculator(x, y);
                enumerateTextSpans(obj, proc);
                obj.boundingBox = new Box(proc.bbox.left, proc.bbox.top, proc.bbox.width(), proc.bbox.height());
            }
            checkForClipPath(obj);
            Path textAsPath = new Path();
            enumerateTextSpans(obj, new PlainTextToPath(x + dx, y + dy, textAsPath));
            combinedPath.setFillType(getClipRuleFromState());
            combinedPath.addPath(textAsPath, combinedPathMatrix);
        }
    }

    private Path makePathAndBoundingBox(Line obj) {
        float x1 = obj.f2949x1 == null ? 0.0f : obj.f2949x1.floatValueX(this);
        float y1 = obj.f2951y1 == null ? 0.0f : obj.f2951y1.floatValueY(this);
        float x2 = obj.f2950x2 == null ? 0.0f : obj.f2950x2.floatValueX(this);
        float y2 = obj.f2952y2 == null ? 0.0f : obj.f2952y2.floatValueY(this);
        if (obj.boundingBox == null) {
            obj.boundingBox = new Box(Math.min(x1, y1), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
        }
        Path p = new Path();
        p.moveTo(x1, y1);
        p.lineTo(x2, y2);
        return p;
    }

    private Path makePathAndBoundingBox(Rect obj) {
        float rx;
        float ry;
        if (obj.f2958rx == null && obj.f2959ry == null) {
            rx = 0.0f;
            ry = 0.0f;
        } else if (obj.f2958rx == null) {
            ry = obj.f2959ry.floatValueY(this);
            rx = ry;
        } else if (obj.f2959ry == null) {
            ry = obj.f2958rx.floatValueX(this);
            rx = ry;
        } else {
            rx = obj.f2958rx.floatValueX(this);
            ry = obj.f2959ry.floatValueY(this);
        }
        float rx2 = Math.min(rx, obj.width.floatValueX(this) / 2.0f);
        float ry2 = Math.min(ry, obj.height.floatValueY(this) / 2.0f);
        float x = obj.f2960x != null ? obj.f2960x.floatValueX(this) : 0.0f;
        float y = obj.f2961y != null ? obj.f2961y.floatValueY(this) : 0.0f;
        float w = obj.width.floatValueX(this);
        float h = obj.height.floatValueY(this);
        if (obj.boundingBox == null) {
            obj.boundingBox = new Box(x, y, w, h);
        }
        float right = x + w;
        float bottom = y + h;
        Path p = new Path();
        if (rx2 == 0.0f || ry2 == 0.0f) {
            p.moveTo(x, y);
            p.lineTo(right, y);
            p.lineTo(right, bottom);
            p.lineTo(x, bottom);
            p.lineTo(x, y);
        } else {
            float cpx = rx2 * 0.5522848f;
            float cpy = ry2 * 0.5522848f;
            p.moveTo(x, y + ry2);
            p.cubicTo(x, (y + ry2) - cpy, (x + rx2) - cpx, y, x + rx2, y);
            p.lineTo(right - rx2, y);
            p.cubicTo((right - rx2) + cpx, y, right, (y + ry2) - cpy, right, y + ry2);
            p.lineTo(right, bottom - ry2);
            p.cubicTo(right, (bottom - ry2) + cpy, (right - rx2) + cpx, bottom, right - rx2, bottom);
            p.lineTo(x + rx2, bottom);
            p.cubicTo((x + rx2) - cpx, bottom, x, (bottom - ry2) + cpy, x, bottom - ry2);
            p.lineTo(x, y + ry2);
        }
        p.close();
        return p;
    }

    private Path makePathAndBoundingBox(Circle obj) {
        float cx = obj.f2940cx != null ? obj.f2940cx.floatValueX(this) : 0.0f;
        float cy = obj.f2941cy != null ? obj.f2941cy.floatValueY(this) : 0.0f;
        float r = obj.f2942r.floatValue(this);
        float left = cx - r;
        float top = cy - r;
        float right = cx + r;
        float bottom = cy + r;
        if (obj.boundingBox == null) {
            obj.boundingBox = new Box(left, top, 2.0f * r, 2.0f * r);
        }
        float cp = r * 0.5522848f;
        Path p = new Path();
        p.moveTo(cx, top);
        p.cubicTo(cx + cp, top, right, cy - cp, right, cy);
        p.cubicTo(right, cy + cp, cx + cp, bottom, cx, bottom);
        p.cubicTo(cx - cp, bottom, left, cy + cp, left, cy);
        p.cubicTo(left, cy - cp, cx - cp, top, cx, top);
        p.close();
        return p;
    }

    private Path makePathAndBoundingBox(Ellipse obj) {
        float cx = obj.f2943cx != null ? obj.f2943cx.floatValueX(this) : 0.0f;
        float cy = obj.f2944cy != null ? obj.f2944cy.floatValueY(this) : 0.0f;
        float rx = obj.f2945rx.floatValueX(this);
        float ry = obj.f2946ry.floatValueY(this);
        float left = cx - rx;
        float top = cy - ry;
        float right = cx + rx;
        float bottom = cy + ry;
        if (obj.boundingBox == null) {
            obj.boundingBox = new Box(left, top, 2.0f * rx, 2.0f * ry);
        }
        float cpx = rx * 0.5522848f;
        float cpy = ry * 0.5522848f;
        Path p = new Path();
        p.moveTo(cx, top);
        p.cubicTo(cx + cpx, top, right, cy - cpy, right, cy);
        p.cubicTo(right, cy + cpy, cx + cpx, bottom, cx, bottom);
        p.cubicTo(cx - cpx, bottom, left, cy + cpy, left, cy);
        p.cubicTo(left, cy - cpy, cx - cpx, top, cx, top);
        p.close();
        return p;
    }

    private Path makePathAndBoundingBox(PolyLine obj) {
        Path path = new Path();
        path.moveTo(obj.points[0], obj.points[1]);
        for (int i = 2; i < obj.points.length; i += 2) {
            path.lineTo(obj.points[i], obj.points[i + 1]);
        }
        if (obj instanceof Polygon) {
            path.close();
        }
        if (obj.boundingBox == null) {
            obj.boundingBox = calculatePathBounds(path);
        }
        path.setFillType(getClipRuleFromState());
        return path;
    }

    private void fillWithPattern(SvgElement obj, Path path, Pattern pattern) {
        float x;
        float y;
        float w;
        float h;
        boolean patternUnitsAreUser = pattern.patternUnitsAreUser != null && pattern.patternUnitsAreUser.booleanValue();
        if (pattern.href != null) {
            fillInChainedPatternFields(pattern, pattern.href);
        }
        if (patternUnitsAreUser) {
            x = pattern.f2956x != null ? pattern.f2956x.floatValueX(this) : 0.0f;
            y = pattern.f2957y != null ? pattern.f2957y.floatValueY(this) : 0.0f;
            w = pattern.width != null ? pattern.width.floatValueX(this) : 0.0f;
            if (pattern.height != null) {
                h = pattern.height.floatValueY(this);
            } else {
                h = 0.0f;
            }
        } else {
            x = obj.boundingBox.minX + (obj.boundingBox.width * (pattern.f2956x != null ? pattern.f2956x.floatValue(this, 1.0f) : 0.0f));
            y = obj.boundingBox.minY + (obj.boundingBox.height * (pattern.f2957y != null ? pattern.f2957y.floatValue(this, 1.0f) : 0.0f));
            w = (pattern.width != null ? pattern.width.floatValue(this, 1.0f) : 0.0f) * obj.boundingBox.width;
            h = (pattern.height != null ? pattern.height.floatValue(this, 1.0f) : 0.0f) * obj.boundingBox.height;
        }
        if (w != 0.0f && h != 0.0f) {
            PreserveAspectRatio positioning = pattern.preserveAspectRatio != null ? pattern.preserveAspectRatio : PreserveAspectRatio.LETTERBOX;
            statePush();
            this.canvas.clipPath(path);
            RendererState baseState = new RendererState();
            updateStyle(baseState, Style.getDefaultStyle());
            baseState.style.overflow = Boolean.valueOf(false);
            this.state = findInheritFromAncestorState(pattern, baseState);
            Box patternArea = obj.boundingBox;
            if (pattern.patternTransform != null) {
                this.canvas.concat(pattern.patternTransform);
                Matrix inverse = new Matrix();
                if (pattern.patternTransform.invert(inverse)) {
                    float[] pts = {obj.boundingBox.minX, obj.boundingBox.minY, obj.boundingBox.maxX(), obj.boundingBox.minY, obj.boundingBox.maxX(), obj.boundingBox.maxY(), obj.boundingBox.minX, obj.boundingBox.maxY()};
                    inverse.mapPoints(pts);
                    RectF rectF = new RectF(pts[0], pts[1], pts[0], pts[1]);
                    for (int i = 2; i <= 6; i += 2) {
                        if (pts[i] < rectF.left) {
                            rectF.left = pts[i];
                        }
                        if (pts[i] > rectF.right) {
                            rectF.right = pts[i];
                        }
                        if (pts[i + 1] < rectF.top) {
                            rectF.top = pts[i + 1];
                        }
                        if (pts[i + 1] > rectF.bottom) {
                            rectF.bottom = pts[i + 1];
                        }
                    }
                    patternArea = new Box(rectF.left, rectF.top, rectF.right - rectF.left, rectF.bottom - rectF.top);
                }
            }
            float originX = x + (((float) Math.floor((double) ((patternArea.minX - x) / w))) * w);
            float originY = y + (((float) Math.floor((double) ((patternArea.minY - y) / h))) * h);
            float right = patternArea.maxX();
            float bottom = patternArea.maxY();
            Box box = new Box(0.0f, 0.0f, w, h);
            for (float stepY = originY; stepY < bottom; stepY += h) {
                for (float stepX = originX; stepX < right; stepX += w) {
                    box.minX = stepX;
                    box.minY = stepY;
                    statePush();
                    if (!this.state.style.overflow.booleanValue()) {
                        setClipRect(box.minX, box.minY, box.width, box.height);
                    }
                    if (pattern.viewBox != null) {
                        this.canvas.concat(calculateViewBoxTransform(box, pattern.viewBox, positioning));
                    } else {
                        boolean patternContentUnitsAreUser = pattern.patternContentUnitsAreUser == null || pattern.patternContentUnitsAreUser.booleanValue();
                        this.canvas.translate(stepX, stepY);
                        if (!patternContentUnitsAreUser) {
                            this.canvas.scale(obj.boundingBox.width, obj.boundingBox.height);
                        }
                    }
                    boolean compositing = pushLayer();
                    for (SvgObject child : pattern.children) {
                        render(child);
                    }
                    if (compositing) {
                        popLayer(pattern);
                    }
                    statePop();
                }
            }
            statePop();
        }
    }

    private void fillInChainedPatternFields(Pattern pattern, String href) {
        SvgObject ref = pattern.document.resolveIRI(href);
        if (ref == null) {
            warn("Pattern reference '%s' not found", href);
        } else if (!(ref instanceof Pattern)) {
            error("Pattern href attributes must point to other pattern elements", new Object[0]);
        } else if (ref == pattern) {
            error("Circular reference in pattern href attribute '%s'", href);
        } else {
            Pattern pRef = (Pattern) ref;
            if (pattern.patternUnitsAreUser == null) {
                pattern.patternUnitsAreUser = pRef.patternUnitsAreUser;
            }
            if (pattern.patternContentUnitsAreUser == null) {
                pattern.patternContentUnitsAreUser = pRef.patternContentUnitsAreUser;
            }
            if (pattern.patternTransform == null) {
                pattern.patternTransform = pRef.patternTransform;
            }
            if (pattern.f2956x == null) {
                pattern.f2956x = pRef.f2956x;
            }
            if (pattern.f2957y == null) {
                pattern.f2957y = pRef.f2957y;
            }
            if (pattern.width == null) {
                pattern.width = pRef.width;
            }
            if (pattern.height == null) {
                pattern.height = pRef.height;
            }
            if (pattern.children.isEmpty()) {
                pattern.children = pRef.children;
            }
            if (pattern.viewBox == null) {
                pattern.viewBox = pRef.viewBox;
            }
            if (pattern.preserveAspectRatio == null) {
                pattern.preserveAspectRatio = pRef.preserveAspectRatio;
            }
            if (pRef.href != null) {
                fillInChainedPatternFields(pattern, pRef.href);
            }
        }
    }

    private void renderMask(Mask mask, SvgElement obj) {
        float w;
        float h;
        debug("Mask render", new Object[0]);
        if (mask.maskUnitsAreUser != null && mask.maskUnitsAreUser.booleanValue()) {
            w = mask.width != null ? mask.width.floatValueX(this) : obj.boundingBox.width;
            h = mask.height != null ? mask.height.floatValueY(this) : obj.boundingBox.height;
            if (mask.f2953x != null) {
                float floatValueX = mask.f2953x.floatValueX(this);
            } else {
                float f = (float) (((double) obj.boundingBox.minX) - (0.1d * ((double) obj.boundingBox.width)));
            }
            if (mask.f2954y != null) {
                float floatValueY = mask.f2954y.floatValueY(this);
            } else {
                float f2 = (float) (((double) obj.boundingBox.minY) - (0.1d * ((double) obj.boundingBox.height)));
            }
        } else {
            float x = mask.f2953x != null ? mask.f2953x.floatValue(this, 1.0f) : -0.1f;
            float x2 = obj.boundingBox.minX + (obj.boundingBox.width * x);
            float y = obj.boundingBox.minY + (obj.boundingBox.height * (mask.f2954y != null ? mask.f2954y.floatValue(this, 1.0f) : -0.1f));
            w = (mask.width != null ? mask.width.floatValue(this, 1.0f) : 1.2f) * obj.boundingBox.width;
            h = (mask.height != null ? mask.height.floatValue(this, 1.0f) : 1.2f) * obj.boundingBox.height;
        }
        if (w != 0.0f && h != 0.0f) {
            statePush();
            this.state = findInheritFromAncestorState(mask);
            this.state.style.opacity = Float.valueOf(1.0f);
            if (!(mask.maskContentUnitsAreUser == null || mask.maskContentUnitsAreUser.booleanValue())) {
                this.canvas.translate(obj.boundingBox.minX, obj.boundingBox.minY);
                this.canvas.scale(obj.boundingBox.width, obj.boundingBox.height);
            }
            renderChildren(mask, false);
            statePop();
        }
    }
}
