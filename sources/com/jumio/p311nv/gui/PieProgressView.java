package com.jumio.p311nv.gui;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;
import com.jumio.commons.utils.ScreenUtil;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureApiConstants;

/* renamed from: com.jumio.nv.gui.PieProgressView */
public class PieProgressView extends View {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public boolean f3364a = false;

    /* renamed from: b */
    private Paint f3365b = new Paint(1);

    /* renamed from: c */
    private Paint f3366c;

    /* renamed from: d */
    private Paint f3367d;

    /* renamed from: e */
    private float f3368e;

    /* renamed from: f */
    private C4454a f3369f;

    /* renamed from: g */
    private RectF f3370g;

    /* renamed from: h */
    private Rect f3371h;

    /* renamed from: i */
    private float f3372i = 0.0f;

    /* renamed from: j */
    private final float f3373j = -90.0f;

    /* renamed from: k */
    private boolean f3374k = false;

    /* renamed from: l */
    private String f3375l = "";

    /* renamed from: com.jumio.nv.gui.PieProgressView$a */
    public enum C4454a {
        CLOCKWISE,
        COUNTERCLOCKWISE
    }

    public PieProgressView(Context context) {
        super(context);
        this.f3365b.setStyle(Style.STROKE);
        this.f3365b.setTypeface(Typeface.create("sans-serif-light", 2));
        this.f3366c = new Paint(1);
        this.f3366c.setStyle(Style.STROKE);
        this.f3366c.setStrokeJoin(Join.ROUND);
        this.f3366c.setStrokeCap(Cap.ROUND);
        this.f3367d = new Paint(1);
        this.f3367d.setStyle(Style.FILL);
        this.f3367d.setStrokeJoin(Join.ROUND);
        this.f3367d.setStrokeCap(Cap.ROUND);
        this.f3369f = C4454a.CLOCKWISE;
        setBorderWidth(3);
        this.f3371h = new Rect();
    }

    public void updatePosition(PointF pointF) {
        if (!this.f3364a) {
            m1977a(pointF);
        }
    }

    /* renamed from: a */
    private void m1977a(PointF pointF) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("X", new float[]{getX(), pointF.x - (((float) getWidth()) / 2.0f)}), PropertyValuesHolder.ofFloat("Y", new float[]{getY(), pointF.y - (((float) getHeight()) / 2.0f)})});
        ofPropertyValuesHolder.setDuration(150);
        ofPropertyValuesHolder.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PieProgressView.this.postInvalidate();
            }
        });
        ofPropertyValuesHolder.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                PieProgressView.this.f3364a = true;
            }

            public void onAnimationEnd(Animator animator) {
                PieProgressView.this.f3364a = false;
            }

            public void onAnimationCancel(Animator animator) {
                PieProgressView.this.f3364a = false;
            }

            public void onAnimationRepeat(Animator animator) {
                PieProgressView.this.f3364a = true;
            }
        });
        ofPropertyValuesHolder.start();
    }

    public void setDirection(C4454a aVar) {
        this.f3369f = aVar;
        invalidate();
    }

    public void setInvertFill(boolean z) {
        this.f3374k = z;
        invalidate();
    }

    public void setBorderWidth(int i) {
        this.f3368e = (float) ScreenUtil.dpToPx(getContext(), i);
        this.f3366c.setStrokeWidth(this.f3368e);
        invalidate();
    }

    public void setBorderColor(int i) {
        this.f3366c.setColor(i);
        invalidate();
    }

    public void setPieColor(int i) {
        this.f3367d.setColor(i);
        invalidate();
    }

    public void setTextColor(int i) {
        this.f3365b.setColor(i);
        invalidate();
    }

    public void setProgress(int i) {
        float f = (360.0f * ((float) i)) / 100.0f;
        if (Math.ceil((double) f) != Math.ceil((double) this.f3372i)) {
            invalidate();
        }
        this.f3372i = f;
    }

    public void setProgress(int i, String str) {
        this.f3375l = str;
        setProgress(i);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f3370g = new RectF(this.f3368e, this.f3368e, ((float) i) - this.f3368e, ((float) i2) - this.f3368e);
        this.f3365b.setTextSize(((float) i2) / 2.3f);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f3370g != null) {
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), null, 31);
            canvas.drawCircle(this.f3370g.centerX(), this.f3370g.centerY(), this.f3370g.width() / 2.0f, this.f3366c);
            int i = this.f3374k ? FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT : 0;
            if (this.f3369f.equals(C4454a.CLOCKWISE)) {
                canvas.drawArc(this.f3370g, -90.0f, this.f3372i - ((float) i), true, this.f3367d);
            } else {
                canvas.drawArc(this.f3370g, -90.0f, ((float) i) - this.f3372i, true, this.f3367d);
            }
            canvas.restoreToCount(saveLayer);
            this.f3365b.getTextBounds(this.f3375l, 0, this.f3375l.length(), this.f3371h);
            canvas.drawText(this.f3375l, this.f3370g.centerX() - (this.f3365b.measureText(this.f3375l) / 2.0f), this.f3370g.centerY() + (((float) this.f3371h.height()) / 2.0f), this.f3365b);
        }
    }
}
