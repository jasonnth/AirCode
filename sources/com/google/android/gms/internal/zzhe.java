package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.formats.AdChoicesView;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzcy.zzb;
import com.google.android.gms.internal.zzhh.zza;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@zzme
public class zzhe extends zza implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener {
    static final String[] zzHf = {"2011", "1009"};
    zzha zzGA;
    private final FrameLayout zzHg;
    Map<String, WeakReference<View>> zzHh = new HashMap();
    View zzHi;
    boolean zzHj = false;
    Point zzHk = new Point();
    Point zzHl = new Point();
    WeakReference<zzcy> zzHm = new WeakReference<>(null);
    private final Object zzrJ = new Object();
    FrameLayout zzrY;

    public zzhe(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzHg = frameLayout;
        this.zzrY = frameLayout2;
        zzw.zzdk().zza((View) this.zzHg, (OnGlobalLayoutListener) this);
        zzw.zzdk().zza((View) this.zzHg, (OnScrollChangedListener) this);
        this.zzHg.setOnTouchListener(this);
        this.zzHg.setOnClickListener(this);
        zzgd.initialize(this.zzHg.getContext());
    }

    private void zza(zzhb zzhb) {
        ViewGroup viewGroup = null;
        boolean zzfY = zzhb.zzfY();
        if (zzfY && this.zzHh != null) {
            WeakReference weakReference = (WeakReference) this.zzHh.get("1098");
            View view = weakReference != null ? (View) weakReference.get() : null;
            if (view instanceof ViewGroup) {
                viewGroup = (ViewGroup) view;
            }
        }
        boolean z = zzfY && viewGroup != null;
        this.zzHi = zza(zzhb, z);
        if (this.zzHi != null) {
            if (this.zzHh != null) {
                this.zzHh.put("1007", new WeakReference(this.zzHi));
            }
            if (z) {
                viewGroup.removeAllViews();
                viewGroup.addView(this.zzHi);
                return;
            }
            AdChoicesView zzp = zzp(zzhb.getContext());
            zzp.setLayoutParams(new LayoutParams(-1, -1));
            zzp.addView(this.zzHi);
            if (this.zzrY != null) {
                this.zzrY.addView(zzp);
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzb(zzhb zzhb) {
        synchronized (this.zzrJ) {
            final View zzgk = zzgk();
            if (!(zzgk instanceof FrameLayout)) {
                zzhb.zzgd();
            } else {
                zzhb.zza(zzgk, (zzgy) new zzgy() {
                    public void zzc(MotionEvent motionEvent) {
                        zzhe.this.onTouch(null, motionEvent);
                    }

                    public void zzfX() {
                        zzhe.this.onClick(zzgk);
                    }
                });
            }
        }
    }

    private View zzgk() {
        if (this.zzHh == null) {
            return null;
        }
        for (String str : zzHf) {
            WeakReference weakReference = (WeakReference) this.zzHh.get(str);
            if (weakReference != null) {
                return (View) weakReference.get();
            }
        }
        return null;
    }

    public void destroy() {
        synchronized (this.zzrJ) {
            if (this.zzrY != null) {
                this.zzrY.removeAllViews();
            }
            this.zzrY = null;
            this.zzHh = null;
            this.zzHi = null;
            this.zzGA = null;
            this.zzHk = null;
            this.zzHl = null;
            this.zzHm = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public int getMeasuredHeight() {
        return this.zzHg.getMeasuredHeight();
    }

    /* access modifiers changed from: 0000 */
    public int getMeasuredWidth() {
        return this.zzHg.getMeasuredWidth();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r8) {
        /*
            r7 = this;
            java.lang.Object r6 = r7.zzrJ
            monitor-enter(r6)
            com.google.android.gms.internal.zzha r0 = r7.zzGA     // Catch:{ all -> 0x0077 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r6)     // Catch:{ all -> 0x0077 }
        L_0x0008:
            return
        L_0x0009:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ all -> 0x0077 }
            r3.<init>()     // Catch:{ all -> 0x0077 }
            java.lang.String r0 = "x"
            android.graphics.Point r1 = r7.zzHk     // Catch:{ JSONException -> 0x007a }
            int r1 = r1.x     // Catch:{ JSONException -> 0x007a }
            int r1 = r7.zzC(r1)     // Catch:{ JSONException -> 0x007a }
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x007a }
            java.lang.String r0 = "y"
            android.graphics.Point r1 = r7.zzHk     // Catch:{ JSONException -> 0x007a }
            int r1 = r1.y     // Catch:{ JSONException -> 0x007a }
            int r1 = r7.zzC(r1)     // Catch:{ JSONException -> 0x007a }
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x007a }
            java.lang.String r0 = "start_x"
            android.graphics.Point r1 = r7.zzHl     // Catch:{ JSONException -> 0x007a }
            int r1 = r1.x     // Catch:{ JSONException -> 0x007a }
            int r1 = r7.zzC(r1)     // Catch:{ JSONException -> 0x007a }
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x007a }
            java.lang.String r0 = "start_y"
            android.graphics.Point r1 = r7.zzHl     // Catch:{ JSONException -> 0x007a }
            int r1 = r1.y     // Catch:{ JSONException -> 0x007a }
            int r1 = r7.zzC(r1)     // Catch:{ JSONException -> 0x007a }
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x007a }
        L_0x0046:
            android.view.View r0 = r7.zzHi     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0090
            android.view.View r0 = r7.zzHi     // Catch:{ all -> 0x0077 }
            boolean r0 = r0.equals(r8)     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0090
            com.google.android.gms.internal.zzha r0 = r7.zzGA     // Catch:{ all -> 0x0077 }
            boolean r0 = r0 instanceof com.google.android.gms.internal.zzgz     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0082
            com.google.android.gms.internal.zzha r0 = r7.zzGA     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.zzgz r0 = (com.google.android.gms.internal.zzgz) r0     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.zzha r0 = r0.zzga()     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0075
            com.google.android.gms.internal.zzha r0 = r7.zzGA     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.zzgz r0 = (com.google.android.gms.internal.zzgz) r0     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.zzha r0 = r0.zzga()     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r7.zzHh     // Catch:{ all -> 0x0077 }
            android.widget.FrameLayout r5 = r7.zzHg     // Catch:{ all -> 0x0077 }
            r1 = r8
            r0.zza(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x0077 }
        L_0x0075:
            monitor-exit(r6)     // Catch:{ all -> 0x0077 }
            goto L_0x0008
        L_0x0077:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0077 }
            throw r0
        L_0x007a:
            r0 = move-exception
            java.lang.String r0 = "Unable to get click location"
            com.google.android.gms.internal.zzpk.zzbh(r0)     // Catch:{ all -> 0x0077 }
            goto L_0x0046
        L_0x0082:
            com.google.android.gms.internal.zzha r0 = r7.zzGA     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r7.zzHh     // Catch:{ all -> 0x0077 }
            android.widget.FrameLayout r5 = r7.zzHg     // Catch:{ all -> 0x0077 }
            r1 = r8
            r0.zza(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x0077 }
            goto L_0x0075
        L_0x0090:
            com.google.android.gms.internal.zzha r0 = r7.zzGA     // Catch:{ all -> 0x0077 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r7.zzHh     // Catch:{ all -> 0x0077 }
            android.widget.FrameLayout r2 = r7.zzHg     // Catch:{ all -> 0x0077 }
            r0.zza(r8, r1, r3, r2)     // Catch:{ all -> 0x0077 }
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhe.onClick(android.view.View):void");
    }

    public void onGlobalLayout() {
        synchronized (this.zzrJ) {
            if (this.zzHj) {
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzrY == null)) {
                    this.zzrY.setLayoutParams(new LayoutParams(measuredWidth, measuredHeight));
                    this.zzHj = false;
                }
            }
            if (this.zzGA != null) {
                this.zzGA.zzd(this.zzHg, this.zzHh);
            }
        }
    }

    public void onScrollChanged() {
        synchronized (this.zzrJ) {
            if (this.zzGA != null) {
                this.zzGA.zzd(this.zzHg, this.zzHh);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.zzrJ) {
            if (this.zzGA != null) {
                Point zze = zze(motionEvent);
                this.zzHk = zze;
                if (motionEvent.getAction() == 0) {
                    this.zzHl = zze;
                }
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setLocation((float) zze.x, (float) zze.y);
                this.zzGA.zzd(obtain);
                obtain.recycle();
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public int zzC(int i) {
        return zzel.zzeT().zzc(this.zzGA.getContext(), i);
    }

    public IObjectWrapper zzU(String str) {
        Object obj = null;
        synchronized (this.zzrJ) {
            if (this.zzHh == null) {
                return null;
            }
            WeakReference weakReference = (WeakReference) this.zzHh.get(str);
            if (weakReference != null) {
                obj = (View) weakReference.get();
            }
            IObjectWrapper zzA = zzd.zzA(obj);
            return zzA;
        }
    }

    /* access modifiers changed from: 0000 */
    public View zza(zzhb zzhb, boolean z) {
        return zzhb.zza((OnClickListener) this, z);
    }

    public void zzb(IObjectWrapper iObjectWrapper, int i) {
        if (zzw.zzdl().zzjS()) {
            zzcy zzcy = (zzcy) this.zzHm.get();
            if (zzcy != null) {
                zzcy.zzdY();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzd(java.lang.String r5, com.google.android.gms.dynamic.IObjectWrapper r6) {
        /*
            r4 = this;
            java.lang.Object r0 = com.google.android.gms.dynamic.zzd.zzF(r6)
            android.view.View r0 = (android.view.View) r0
            java.lang.Object r1 = r4.zzrJ
            monitor-enter(r1)
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r4.zzHh     // Catch:{ all -> 0x0018 }
            if (r2 != 0) goto L_0x000f
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
        L_0x000e:
            return
        L_0x000f:
            if (r0 != 0) goto L_0x001b
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r0 = r4.zzHh     // Catch:{ all -> 0x0018 }
            r0.remove(r5)     // Catch:{ all -> 0x0018 }
        L_0x0016:
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            goto L_0x000e
        L_0x0018:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            throw r0
        L_0x001b:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r4.zzHh     // Catch:{ all -> 0x0018 }
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0018 }
            r3.<init>(r0)     // Catch:{ all -> 0x0018 }
            r2.put(r5, r3)     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "1098"
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0030
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            goto L_0x000e
        L_0x0030:
            r0.setOnTouchListener(r4)     // Catch:{ all -> 0x0018 }
            r2 = 1
            r0.setClickable(r2)     // Catch:{ all -> 0x0018 }
            r0.setOnClickListener(r4)     // Catch:{ all -> 0x0018 }
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhe.zzd(java.lang.String, com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* access modifiers changed from: 0000 */
    public Point zze(MotionEvent motionEvent) {
        int[] iArr = new int[2];
        this.zzHg.getLocationOnScreen(iArr);
        return new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
    }

    public void zze(IObjectWrapper iObjectWrapper) {
        synchronized (this.zzrJ) {
            zzj(null);
            Object zzF = zzd.zzF(iObjectWrapper);
            if (!(zzF instanceof zzhb)) {
                zzpk.zzbh("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            if (this.zzrY != null) {
                this.zzrY.setLayoutParams(new LayoutParams(0, 0));
                this.zzHg.requestLayout();
            }
            this.zzHj = true;
            final zzhb zzhb = (zzhb) zzF;
            if (this.zzGA != null && ((Boolean) zzgd.zzEp.get()).booleanValue()) {
                this.zzGA.zzc(this.zzHg, this.zzHh);
            }
            zzgj();
            if (!(this.zzGA instanceof zzgz) || !((zzgz) this.zzGA).zzfZ()) {
                this.zzGA = zzhb;
                if (zzhb instanceof zzgz) {
                    ((zzgz) zzhb).zzc(null);
                }
            } else {
                ((zzgz) this.zzGA).zzc(zzhb);
            }
            if (((Boolean) zzgd.zzEp.get()).booleanValue()) {
                this.zzrY.setClickable(false);
            }
            this.zzrY.removeAllViews();
            zza(zzhb);
            zzhb.zza((View) this.zzHg, this.zzHh, (OnTouchListener) this, (OnClickListener) this);
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzqw zzgb = zzhb.zzgb();
                    if (!(zzgb == null || zzhe.this.zzrY == null)) {
                        zzhe.this.zzrY.addView(zzgb.getView());
                    }
                    if (!(zzhb instanceof zzgz)) {
                        zzhe.this.zzb(zzhb);
                    }
                }
            });
            zzj(this.zzHg);
            zzgi();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzgi() {
        if (this.zzGA instanceof zzhb) {
            zzhb zzhb = (zzhb) this.zzGA;
            if (zzw.zzdl().zzjS() && zzhb != null && zzhb.getContext() != null) {
                zzcy zzcy = (zzcy) this.zzHm.get();
                if (zzcy == null) {
                    zzcy = new zzcy(this.zzHg.getContext(), this.zzHg);
                    this.zzHm = new WeakReference<>(zzcy);
                }
                zzcy.zza((zzb) zzhb.zzgg());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzgj() {
        if (this.zzGA instanceof zzhb) {
            zzhb zzhb = (zzhb) this.zzGA;
            if (zzw.zzdl().zzjS() && zzhb != null && zzhb.getContext() != null) {
                zzov zzgg = zzhb.zzgg();
                if (zzgg != null) {
                    zzgg.zzB(false);
                }
                zzcy zzcy = (zzcy) this.zzHm.get();
                if (zzcy != null && zzgg != null) {
                    zzcy.zzb(zzgg);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzj(View view) {
        if (this.zzGA != null) {
            zzha zzha = this.zzGA instanceof zzgz ? ((zzgz) this.zzGA).zzga() : this.zzGA;
            if (zzha != null) {
                zzha.zzj(view);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public AdChoicesView zzp(Context context) {
        return new AdChoicesView(context);
    }
}
