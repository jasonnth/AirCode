package com.airbnb.android.core.views.svg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.caverock.androidsvg.PreserveAspectRatio;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import java.util.ArrayList;
import java.util.List;

class SvgData {
    private final int height;
    private final List<SvgPath> paths;
    private final int width;

    private SvgData(List<SvgPath> paths2, int documentWidth, int documentHeight) {
        this.paths = paths2;
        this.width = documentWidth;
        this.height = documentHeight;
    }

    /* access modifiers changed from: 0000 */
    public List<SvgPath> getPaths() {
        return this.paths;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    static SvgData fromResource(Context context, int svgResource) throws SVGParseException {
        final List<SvgPath> paths2 = new ArrayList<>();
        SVG svg = SVG.getFromResource(context, svgResource);
        svg.setDocumentPreserveAspectRatio(PreserveAspectRatio.UNSCALED);
        RectF viewBox = svg.getDocumentViewBox();
        final int svgWidth = (int) (viewBox != null ? viewBox.width() : svg.getDocumentWidth());
        final int svgHeight = (int) (viewBox != null ? viewBox.height() : svg.getDocumentHeight());
        svg.renderToCanvas(new Canvas() {
            public int getWidth() {
                return svgWidth;
            }

            public int getHeight() {
                return svgHeight;
            }

            public void drawPath(Path path, Paint paint) {
                paths2.add(new SvgPath(path));
            }
        });
        return new SvgData(paths2, svgWidth, svgHeight);
    }
}
