package com.jumio.p311nv.ocr.overlay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.TypedValue;
import android.view.View;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.p311nv.ocr.C4469R;

/* renamed from: com.jumio.nv.ocr.overlay.TemplateMatcherFrameIndicator */
public class TemplateMatcherFrameIndicator extends View {

    /* renamed from: a */
    protected boolean f3455a;

    /* renamed from: b */
    private Path f3456b = null;

    /* renamed from: c */
    private Paint f3457c = null;

    /* renamed from: d */
    private MyPointF f3458d;

    /* renamed from: e */
    private MyPointF f3459e;

    /* renamed from: f */
    private MyPointF f3460f;

    /* renamed from: g */
    private MyPointF f3461g;

    /* renamed from: h */
    private MyPointF f3462h;

    /* renamed from: i */
    private MyPointF f3463i;

    /* renamed from: j */
    private MyPointF f3464j;

    /* renamed from: k */
    private MyPointF f3465k;

    /* renamed from: l */
    private PointF f3466l;

    /* renamed from: com.jumio.nv.ocr.overlay.TemplateMatcherFrameIndicator$MyPointF */
    public class MyPointF extends PointF {
        public MyPointF(PointF pointF) {
            super(pointF.x, pointF.y);
        }

        public void setX(float f) {
            set(f, this.y);
        }

        public void setY(float f) {
            set(this.x, f);
        }
    }

    public TemplateMatcherFrameIndicator(Context context) {
        super(context);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C4469R.attr.netverify_scanOverlayTemplateValid, typedValue, true);
        int dipToPx = (int) ScreenUtil.dipToPx(context, 2.5f);
        this.f3457c = new Paint();
        this.f3457c.setColor(typedValue.data);
        this.f3457c.setStyle(Style.STROKE);
        this.f3457c.setStrokeWidth((float) dipToPx);
        this.f3457c.setDither(true);
        this.f3457c.setAntiAlias(true);
        this.f3456b = new Path();
        setBackgroundColor(0);
    }

    /* renamed from: a */
    public void mo43624a(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5) {
        this.f3466l = pointF5;
        if (pointF == null || pointF2 == null || pointF3 == null || pointF4 == null) {
            this.f3460f = null;
            this.f3458d = null;
            this.f3462h = null;
            this.f3464j = null;
            postInvalidate();
            return;
        }
        this.f3459e = new MyPointF(pointF);
        this.f3461g = new MyPointF(pointF2);
        this.f3463i = new MyPointF(pointF4);
        this.f3465k = new MyPointF(pointF3);
        if (!m2004b()) {
            this.f3460f = new MyPointF(pointF2);
            this.f3458d = new MyPointF(pointF);
            this.f3462h = new MyPointF(pointF4);
            this.f3464j = new MyPointF(pointF3);
        }
        if (!this.f3455a) {
            m2003a();
        }
        postInvalidate();
    }

    /* renamed from: a */
    private void m2003a() {
        AnimatorSet animatorSet = new AnimatorSet();
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("x", new float[]{this.f3458d.x, this.f3459e.x});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("y", new float[]{this.f3458d.y, this.f3459e.y});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.f3458d, new PropertyValuesHolder[]{ofFloat, ofFloat2});
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("x", new float[]{this.f3460f.x, this.f3461g.x});
        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat("y", new float[]{this.f3460f.y, this.f3461g.y});
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(this.f3460f, new PropertyValuesHolder[]{ofFloat3, ofFloat4});
        PropertyValuesHolder ofFloat5 = PropertyValuesHolder.ofFloat("x", new float[]{this.f3462h.x, this.f3463i.x});
        PropertyValuesHolder ofFloat6 = PropertyValuesHolder.ofFloat("y", new float[]{this.f3462h.y, this.f3463i.y});
        ObjectAnimator ofPropertyValuesHolder3 = ObjectAnimator.ofPropertyValuesHolder(this.f3462h, new PropertyValuesHolder[]{ofFloat5, ofFloat6});
        PropertyValuesHolder ofFloat7 = PropertyValuesHolder.ofFloat("x", new float[]{this.f3464j.x, this.f3465k.x});
        PropertyValuesHolder ofFloat8 = PropertyValuesHolder.ofFloat("y", new float[]{this.f3464j.y, this.f3465k.y});
        ObjectAnimator ofPropertyValuesHolder4 = ObjectAnimator.ofPropertyValuesHolder(this.f3464j, new PropertyValuesHolder[]{ofFloat7, ofFloat8});
        ofPropertyValuesHolder4.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                TemplateMatcherFrameIndicator.this.postInvalidate();
            }
        });
        animatorSet.playTogether(new Animator[]{ofPropertyValuesHolder, ofPropertyValuesHolder2, ofPropertyValuesHolder3, ofPropertyValuesHolder4});
        animatorSet.setDuration(150);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                TemplateMatcherFrameIndicator.this.f3455a = false;
            }

            public void onAnimationStart(Animator animator) {
                TemplateMatcherFrameIndicator.this.f3455a = true;
            }
        });
        animatorSet.start();
    }

    /* renamed from: b */
    private boolean m2004b() {
        return (this.f3458d == null || this.f3460f == null || this.f3462h == null || this.f3464j == null) ? false : true;
    }

    public void onDraw(Canvas canvas) {
        mo43623a(canvas);
        super.onDraw(canvas);
    }

    /* renamed from: a */
    public void mo43623a(Canvas canvas) {
        if (m2004b()) {
            this.f3456b.reset();
            this.f3456b.moveTo(this.f3458d.x * 0.98f, this.f3458d.y * 0.98f);
            this.f3456b.lineTo(this.f3460f.x * 1.02f, this.f3460f.y * 0.98f);
            this.f3456b.lineTo(this.f3462h.x * 1.02f, this.f3462h.y * 1.02f);
            this.f3456b.lineTo(this.f3464j.x * 0.98f, this.f3464j.y * 1.02f);
            this.f3456b.lineTo(this.f3458d.x * 0.98f, this.f3458d.y * 0.98f);
            this.f3456b.lineTo(this.f3460f.x * 1.02f, this.f3460f.y * 0.98f);
            canvas.drawPath(this.f3456b, this.f3457c);
        }
    }

    public void setBorderColor(int i) {
        this.f3457c.setColor(i);
    }
}
