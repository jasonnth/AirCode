package jumio.p317nv.core;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.TypedValue;
import android.view.View;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.facebook.react.uimanager.ViewProps;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.p311nv.C4430R;

/* renamed from: jumio.nv.core.ad */
/* compiled from: LivenessGuidanceView */
public class C4890ad extends View {

    /* renamed from: a */
    private AnimatorSet f4717a;

    /* renamed from: b */
    private AnimatorSet f4718b;

    /* renamed from: c */
    private Paint f4719c;

    /* renamed from: d */
    private Paint f4720d;

    /* renamed from: e */
    private RectF f4721e;

    /* renamed from: f */
    private boolean f4722f = true;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Paint f4723g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public int f4724h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public int f4725i;

    public C4890ad(Context context) {
        super(context);
        int dpToPx = ScreenUtil.dpToPx(context, 3);
        TypedValue typedValue = new TypedValue();
        Theme theme = context.getTheme();
        theme.resolveAttribute(C4430R.attr.netverify_scanOverlayLiveness, typedValue, true);
        int i = typedValue.data;
        theme.resolveAttribute(C4430R.attr.netverify_scanOverlayLivenessLocked, typedValue, true);
        int i2 = typedValue.data;
        this.f4719c = new Paint();
        this.f4719c.setStyle(Style.STROKE);
        this.f4719c.setStrokeWidth((float) dpToPx);
        this.f4719c.setColor(i);
        this.f4719c.setStrokeCap(Cap.ROUND);
        this.f4719c.setAntiAlias(true);
        this.f4720d = new Paint();
        this.f4720d.setStyle(Style.STROKE);
        this.f4720d.setStrokeWidth((float) dpToPx);
        this.f4720d.setColor(i2);
        this.f4720d.setStrokeCap(Cap.ROUND);
        this.f4720d.setAntiAlias(true);
        this.f4717a = m2954a(30, 15, 30, 60, i, i2, 300);
        this.f4718b = m2954a(15, 30, 60, 30, i2, i, 200);
        this.f4723g = this.f4719c;
        this.f4724h = 0;
        this.f4725i = 0;
        this.f4721e = new RectF();
        mo46806a(false);
    }

    /* renamed from: a */
    public void mo46806a(boolean z) {
        if (this.f4722f != z) {
            this.f4722f = z;
            if (z) {
                if (this.f4718b.isRunning()) {
                    this.f4718b.cancel();
                }
                this.f4717a.start();
                return;
            }
            if (this.f4717a.isRunning()) {
                this.f4717a.cancel();
            }
            this.f4718b.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        float f;
        float f2 = 0.0f;
        super.onMeasure(i, i2);
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        if (((float) measuredWidth) / ((float) measuredHeight) < 0.7616f) {
            f = ((float) (measuredHeight - Math.round(((float) measuredWidth) / 0.7616f))) / 2.0f;
        } else {
            f2 = ((float) (measuredWidth - Math.round(((float) measuredHeight) * 0.7616f))) / 2.0f;
            f = 0.0f;
        }
        this.f4721e.left = ((float) (getPaddingLeft() + 0)) + f2;
        this.f4721e.top = ((float) getPaddingTop()) + f;
        this.f4721e.right = ((float) (getMeasuredWidth() - getPaddingRight())) - f2;
        this.f4721e.bottom = ((float) (getMeasuredHeight() - getPaddingBottom())) - f;
    }

    public void onDraw(Canvas canvas) {
        canvas.drawArc(this.f4721e, (float) this.f4724h, (float) this.f4725i, false, this.f4723g);
        canvas.drawArc(this.f4721e, (float) (this.f4724h + 90), (float) this.f4725i, false, this.f4723g);
        canvas.drawArc(this.f4721e, (float) (this.f4724h + 180), (float) this.f4725i, false, this.f4723g);
        canvas.drawArc(this.f4721e, (float) (this.f4724h + MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS), (float) this.f4725i, false, this.f4723g);
        super.onDraw(canvas);
    }

    /* renamed from: a */
    private AnimatorSet m2954a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[]{PropertyValuesHolder.ofInt("startAngle", new int[]{i, i2})});
        ofPropertyValuesHolder.setDuration((long) i7);
        ofPropertyValuesHolder.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                C4890ad.this.f4724h = ((Integer) valueAnimator.getAnimatedValue("startAngle")).intValue();
                C4890ad.this.invalidate();
            }
        });
        ValueAnimator ofPropertyValuesHolder2 = ValueAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[]{PropertyValuesHolder.ofInt("sweepAngle", new int[]{i3, i4})});
        ofPropertyValuesHolder2.setDuration((long) i7);
        ofPropertyValuesHolder2.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                C4890ad.this.f4725i = ((Integer) valueAnimator.getAnimatedValue("sweepAngle")).intValue();
                C4890ad.this.invalidate();
            }
        });
        ValueAnimator ofPropertyValuesHolder3 = ValueAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[]{PropertyValuesHolder.ofInt(ViewProps.COLOR, new int[]{i5, i6})});
        ofPropertyValuesHolder3.setEvaluator(new ArgbEvaluator());
        ofPropertyValuesHolder3.setDuration((long) i7);
        ofPropertyValuesHolder3.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                C4890ad.this.f4723g.setColor(((Integer) valueAnimator.getAnimatedValue(ViewProps.COLOR)).intValue());
                C4890ad.this.invalidate();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofPropertyValuesHolder3, ofPropertyValuesHolder, ofPropertyValuesHolder2});
        return animatorSet;
    }
}
