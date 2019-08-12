package com.airbnb.android.core.views.svg;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;
import com.airbnb.android.core.C0716R;
import com.caverock.androidsvg.SVGParseException;
import java.util.ArrayList;
import java.util.List;

public class SvgView extends View {
    private float offset;
    private final Paint paint;
    private final ArrayList<Path> pathPool;
    private float percentShown;
    private final List<Path> renderPaths;
    private SvgData svg;

    public SvgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SvgView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.paint = new Paint(1);
        this.renderPaths = new ArrayList();
        this.pathPool = new ArrayList<>();
        this.offset = 0.0f;
        this.percentShown = 1.0f;
        this.paint.setStyle(Style.STROKE);
        TypedArray a = context.obtainStyledAttributes(attrs, C0716R.styleable.SvgView, defStyle, 0);
        this.paint.setStrokeWidth((float) a.getDimensionPixelSize(C0716R.styleable.SvgView_strokeWidth, getResources().getDimensionPixelSize(C0716R.dimen.svg_view_stroke_width)));
        this.paint.setColor(a.getColor(C0716R.styleable.SvgView_strokeColor, getResources().getColor(C0716R.color.c_rausch)));
        this.percentShown = a.getFloat(C0716R.styleable.SvgView_percentShown, this.percentShown);
        this.offset = a.getFloat(C0716R.styleable.SvgView_offset, this.offset);
        int svgRes = a.getResourceId(C0716R.styleable.SvgView_svg, -1);
        if (svgRes != -1) {
            setSvgResource(svgRes);
        }
        a.recycle();
    }

    public void setOffset(float offset2) {
        this.offset = offset2;
        updateRenderPaths();
    }

    public void setPercentShown(float percentShown2) {
        this.percentShown = percentShown2;
        updateRenderPaths();
    }

    public void setSvgResource(final int svgResource) {
        this.svg = null;
        final Context context = getContext();
        new AsyncTask<Void, Void, SvgData>() {
            /* access modifiers changed from: protected */
            public SvgData doInBackground(Void... params) {
                try {
                    return SvgData.fromResource(context, svgResource);
                } catch (SVGParseException e) {
                    return null;
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(SvgData svgData) {
                if (svgData == null) {
                    throw new IllegalArgumentException("Could not parse svg resource");
                }
                SvgView.this.setSvgData(svgData);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void setSvgData(SvgData svg2) {
        this.svg = svg2;
        updateRenderPaths();
        requestLayout();
    }

    private void updateRenderPaths() {
        clearRenderPaths();
        invalidate();
        if (this.svg != null) {
            int pathCount = this.svg.getPaths().size();
            for (int i = 0; i < pathCount; i++) {
                SvgPath svgPath = (SvgPath) this.svg.getPaths().get(i);
                float length = svgPath.length;
                float start = length * this.offset;
                float end = start + (this.percentShown * length);
                renderPath(svgPath, start, end);
                if (end > length) {
                    renderPath(svgPath, 0.0f, end - length);
                }
            }
        }
    }

    private void clearRenderPaths() {
        this.pathPool.ensureCapacity(this.pathPool.size() + this.renderPaths.size());
        for (Path renderPath : this.renderPaths) {
            this.pathPool.add(renderPath);
        }
        this.renderPaths.clear();
    }

    private void renderPath(SvgPath svgPath, float start, float end) {
        Path renderPath = getBlankRenderPath();
        svgPath.measure.getSegment(start, end, renderPath, true);
        renderPath.rLineTo(0.0f, 0.0f);
        this.renderPaths.add(renderPath);
    }

    private Path getBlankRenderPath() {
        if (this.pathPool.isEmpty()) {
            return new Path();
        }
        Path path = (Path) this.pathPool.remove(this.pathPool.size() - 1);
        path.reset();
        return path;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.svg == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int strokeWidth = (int) this.paint.getStrokeWidth();
        setMeasuredDimension(this.svg.getWidth() + getPaddingLeft() + getPaddingRight() + strokeWidth, this.svg.getHeight() + getPaddingTop() + getPaddingBottom() + strokeWidth);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
        for (Path path : this.renderPaths) {
            canvas.drawPath(path, this.paint);
        }
        canvas.restore();
    }

    public float getOffset() {
        return this.offset;
    }
}
