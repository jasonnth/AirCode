package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Region.Op;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.airbnb.android.airmapview.AirMapInterface;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.airbnb.n2.primitives.DrawOnImageView */
public class DrawOnImageView extends View implements OnTouchListener {
    private final int DEFAULT_DRAWING_COLOR = AirMapInterface.CIRCLE_BORDER_COLOR;
    private final int DEFAULT_STROKE_WIDTH = 10;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint canvasPaint;
    private Path currentPath;
    private Paint currentPathPaint;
    private float currentPathX;
    private float currentPathY;
    private boolean drawingEnabled = false;
    private float leftOffset;
    private DrawOnImageViewListener listener;
    private final ArrayList<Pair<Path, Paint>> paths = new ArrayList<>();
    private float topOffset;

    /* renamed from: com.airbnb.n2.primitives.DrawOnImageView$DrawOnImageViewListener */
    public interface DrawOnImageViewListener {
        void onPathDrawn();

        void onPathsModified(int i);
    }

    public DrawOnImageView(Context context) {
        super(context);
        init();
    }

    public DrawOnImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        this.currentPath = new Path();
        this.currentPathPaint = getPathPaint();
        this.canvasPaint = new Paint();
        this.canvasPaint.setAntiAlias(true);
        this.canvasPaint.setFilterBitmap(true);
        this.canvasPaint.setDither(true);
        this.leftOffset = 0.0f;
        this.topOffset = 0.0f;
        setOnTouchListener(this);
    }

    public void setBitmap(Bitmap bitmap2) {
        float finalRatio;
        this.paths.clear();
        int bitmapWidth = bitmap2.getWidth();
        int bitmapHeight = bitmap2.getHeight();
        float widthRatio = ((float) getWidth()) / ((float) bitmapWidth);
        float heightRatio = ((float) getHeight()) / ((float) bitmapHeight);
        if (heightRatio < widthRatio) {
            finalRatio = heightRatio;
        } else {
            finalRatio = widthRatio;
        }
        this.bitmap = Bitmap.createScaledBitmap(bitmap2, (int) (((float) bitmapWidth) * finalRatio), (int) (((float) bitmapHeight) * finalRatio), true);
        updateOffset();
        invalidate();
    }

    public void setListener(DrawOnImageViewListener listener2) {
        this.listener = listener2;
    }

    public void enableDrawing(boolean drawingEnabled2) {
        this.drawingEnabled = drawingEnabled2;
    }

    public void undoLastPath() {
        if (!this.paths.isEmpty()) {
            this.paths.remove(this.paths.size() - 1);
            invalidate();
        }
    }

    public void setDrawingColor(int drawingColor) {
        this.currentPathPaint = getPathPaint();
        this.currentPathPaint.setColor(drawingColor);
    }

    public Bitmap getEditedBitmap() {
        setDrawingCacheEnabled(true);
        Bitmap editedBitmap = Bitmap.createBitmap(getDrawingCache(), (int) this.leftOffset, (int) this.topOffset, this.bitmap.getWidth(), this.bitmap.getHeight());
        destroyDrawingCache();
        return editedBitmap;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.bitmap == null) {
            this.bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        }
        updateOffset();
        this.canvas = new Canvas(this.bitmap);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas2) {
        this.listener.onPathsModified(this.paths.size());
        canvas2.clipRect(this.leftOffset, this.topOffset, ((float) getWidth()) - this.leftOffset, ((float) getHeight()) - this.topOffset, Op.REPLACE);
        canvas2.drawBitmap(this.bitmap, this.leftOffset, this.topOffset, this.canvasPaint);
        Iterator it = this.paths.iterator();
        while (it.hasNext()) {
            Pair<Path, Paint> pair = (Pair) it.next();
            canvas2.drawPath((Path) pair.first, (Paint) pair.second);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.drawingEnabled) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                touchStart(x, y);
                invalidate();
                break;
            case 1:
                touchEnd();
                invalidate();
                break;
            case 2:
                touchMove(x, y);
                invalidate();
                break;
        }
        return true;
    }

    private void touchStart(float x, float y) {
        this.currentPath = new Path();
        this.paths.add(new Pair(this.currentPath, this.currentPathPaint));
        this.currentPath.moveTo(x, y);
        this.currentPathX = x;
        this.currentPathY = y;
    }

    private void touchMove(float x, float y) {
        this.currentPath.quadTo(this.currentPathX, this.currentPathY, (this.currentPathX + x) / 2.0f, (this.currentPathY + y) / 2.0f);
        this.currentPathX = x;
        this.currentPathY = y;
    }

    private void touchEnd() {
        this.currentPath.lineTo(this.currentPathX, this.currentPathY);
        this.canvas.drawPath(this.currentPath, this.currentPathPaint);
        this.listener.onPathDrawn();
    }

    private Paint getPathPaint() {
        Paint paint = new Paint();
        paint.setColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        paint.setStrokeWidth(10.0f);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Style.STROKE);
        paint.setStrokeJoin(Join.ROUND);
        paint.setStrokeCap(Cap.ROUND);
        return paint;
    }

    private void updateOffset() {
        this.leftOffset = (float) ((getWidth() - this.bitmap.getWidth()) / 2);
        this.topOffset = (float) ((getHeight() - this.bitmap.getHeight()) / 2);
    }
}
