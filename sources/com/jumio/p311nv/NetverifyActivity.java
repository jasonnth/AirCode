package com.jumio.p311nv;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.jumio.MobileActivity;
import com.jumio.analytics.DismissType;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.analytics.UserAction;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.enums.Rotation;
import com.jumio.commons.utils.DeviceRotationManager;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.core.util.Resource;
import com.jumio.p311nv.api.calls.NVBackend;
import com.jumio.p311nv.benchmark.Benchmark;
import com.jumio.p311nv.benchmark.BenchmarkAlgorithm.DeviceCategory;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.p311nv.data.NVStrings.C4448a;
import com.jumio.p311nv.enums.NVErrorCase;
import com.jumio.p311nv.extraction.NfcController;
import com.jumio.p311nv.models.BackendModel;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.p311nv.models.LivenessModel;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.view.fragment.INetverifyActivityCallback;
import com.jumio.p311nv.view.fragment.INetverifyFragmentCallback;
import com.jumio.p311nv.view.fragment.SelectionFragment;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.models.CredentialsModel;
import java.io.File;
import java.util.ArrayList;
import jumio.p317nv.core.C4884a;
import jumio.p317nv.core.C4937q;

/* renamed from: com.jumio.nv.NetverifyActivity */
public class NetverifyActivity extends MobileActivity implements INetverifyFragmentCallback {
    public static final String MERCHANT_PRESELECTION = "com.jumio.nv.NetverifyActivity.MERCHANT_PRESELECTION";

    /* renamed from: a */
    private final Object f3233a;

    /* renamed from: b */
    private ArrayList<Fragment> f3234b;

    /* renamed from: c */
    private INetverifyActivityCallback f3235c;

    /* renamed from: d */
    private BroadcastReceiver f3236d;

    /* renamed from: e */
    private NfcController f3237e;

    /* renamed from: f */
    private DeviceRotationManager f3238f;

    /* renamed from: g */
    private Toolbar f3239g;

    /* renamed from: h */
    private TextView f3240h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public LinearLayout f3241i;

    /* renamed from: com.jumio.nv.NetverifyActivity$a */
    class C4421a implements NfcController {
        private C4421a() {
        }

        public void start(Activity activity) {
        }

        public void pause(Activity activity) {
        }

        public void stop() {
        }

        public boolean isNfcEnabled() {
            return false;
        }

        public boolean hasRootCertificate(String str) {
            return false;
        }

        public boolean hasNfcFeature() {
            return false;
        }

        public boolean consumeIntent(int i, Intent intent) {
            return false;
        }

        public void setEnabled(boolean z) {
        }

        public void setTagAccess(Object obj) {
        }
    }

    public NetverifyActivity() {
        this.f3233a = new Object();
        this.f3234b = null;
        this.f3235c = null;
        this.f3234b = new ArrayList<>();
        NVStrings.setFactory(new C4448a());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setFinishOnTouchOutside(false);
        getWindow().addFlags(8192);
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), "NetverifyActivity", "onCreate()"));
        setContentView(C4430R.layout.activity_netverify);
        this.f3238f = new DeviceRotationManager(this, Rotation.NATIVE);
        this.f3239g = (Toolbar) findViewById(C4430R.C4432id.toolbar);
        this.f3240h = (TextView) findViewById(C4430R.C4432id.toolbar_subtitle);
        this.f3240h.setTag(Boolean.valueOf(true));
        this.f3241i = (LinearLayout) findViewById(C4430R.C4432id.toolbarSubtitleContainer);
        setSupportActionBar(this.f3239g);
        getNfcController();
        if (bundle == null) {
            startFragment(new SelectionFragment(), false, 0, 0);
        } else {
            int i = bundle.getInt("FragmentBackstackCount", 0);
            for (int i2 = 0; i2 < i; i2++) {
                this.f3234b.add(getFragmentManager().getFragment(bundle, String.format("FragmentBackstack%d", new Object[]{Integer.valueOf(i2)})));
            }
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(Resource.getID(this, "abc_ic_clear_mtrl_alpha", "drawable"));
            getSupportActionBar().setShowHideAnimationEnabled(false);
        }
        handleOrientationChange(getRotationManager().isScreenPortrait() || getRotationManager().isTablet(), false);
        new Benchmark(this).startMeasurement();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), "NetverifyActivity", "onStart()"));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), "NetverifyActivity", "onResume()"));
        this.f3236d = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("com.jumio.nv.CLOSE_SDK")) {
                    NetverifyActivity.this.m1938a(intent);
                }
            }
        };
        registerReceiver(this.f3236d, C4884a.m2939a());
        getNfcController().start(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), "NetverifyActivity", "onPause()"));
        if (this.f3236d != null) {
            unregisterReceiver(this.f3236d);
            this.f3236d = null;
        }
        getNfcController().pause(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), "NetverifyActivity", "onStop()"));
    }

    public void onDestroy() {
        super.onDestroy();
        getNfcController().stop();
        JumioAnalytics.add(MobileEvents.androidLifecycle(JumioAnalytics.getSessionId(), "NetverifyActivity", "onDestroy()"));
        JumioAnalytics.shutdown(new Runnable() {
            public void run() {
                NVBackend.freeEncryption();
            }
        });
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        handleOrientationChange(configuration.orientation == 1 || getRotationManager().isTablet(), true);
    }

    public void handleOrientationChange(boolean z, boolean z2) {
        this.f3240h.getLayoutParams().height = ScreenUtil.dpToPx(this, z ? 100 : 60);
        ((LayoutParams) findViewById(C4430R.C4432id.spinnerContainer).getLayoutParams()).topMargin = ScreenUtil.dpToPx(this, z ? 116 : 76);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (int i = 0; i < this.f3234b.size(); i++) {
                getFragmentManager().putFragment(bundle, String.format("FragmentBackstack%d", new Object[]{Integer.valueOf(i)}), (Fragment) this.f3234b.get(i));
            }
        }
    }

    public void onBackPressed() {
        boolean z;
        boolean z2 = true;
        JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), null, UserAction.BACK));
        if (this.f3235c != null) {
            z = this.f3235c.onBackButtonPressed();
        } else {
            z = false;
        }
        if (!z) {
            if (this.f3234b.size() <= 1) {
                z2 = false;
            }
            if (z2) {
                closeFragment(0, C4430R.animator.fade_out);
                return;
            }
            InitiateModel initiateModel = (InitiateModel) DataAccess.load((Context) this, InitiateModel.class);
            m1938a((Intent) new C4884a(NVErrorCase.CANCEL_TYPE_USER.code(), 0, NVErrorCase.CANCEL_TYPE_USER.localizedMessage(this), initiateModel != null ? initiateModel.getJumioScanRef() : null));
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String str;
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        if (this.f3235c == null || !this.f3235c.onHomeButtonPressed()) {
            JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), null, UserAction.CLOSE));
            InitiateModel initiateModel = (InitiateModel) DataAccess.load((Context) this, InitiateModel.class);
            int code = NVErrorCase.CANCEL_TYPE_USER.code();
            String localizedMessage = NVErrorCase.CANCEL_TYPE_USER.localizedMessage(this);
            if (initiateModel != null) {
                str = initiateModel.getJumioScanRef();
            } else {
                str = null;
            }
            m1938a((Intent) new C4884a(code, 0, localizedMessage, str));
        }
        return true;
    }

    public void registerActivityCallback(INetverifyActivityCallback iNetverifyActivityCallback) {
        this.f3235c = iNetverifyActivityCallback;
    }

    public CredentialsModel getCredentials() {
        return super.getCredentialsModel();
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startFragment(android.app.Fragment r5, boolean r6, int r7, int r8) {
        /*
            r4 = this;
            java.lang.Object r1 = r4.f3233a
            monitor-enter(r1)
            boolean r0 = r4.isFinishing()     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x004c }
        L_0x000a:
            return
        L_0x000b:
            r0 = 0
            r4.f3235c = r0     // Catch:{ all -> 0x004c }
            android.app.FragmentManager r0 = r4.getFragmentManager()     // Catch:{ all -> 0x004c }
            android.app.FragmentTransaction r2 = r0.beginTransaction()     // Catch:{ all -> 0x004c }
            r2.setCustomAnimations(r7, r8)     // Catch:{ all -> 0x004c }
            java.util.ArrayList<android.app.Fragment> r0 = r4.f3234b     // Catch:{ all -> 0x004c }
            int r0 = r0.size()     // Catch:{ all -> 0x004c }
            if (r0 <= 0) goto L_0x003b
            java.util.ArrayList<android.app.Fragment> r0 = r4.f3234b     // Catch:{ all -> 0x004c }
            java.util.ArrayList<android.app.Fragment> r3 = r4.f3234b     // Catch:{ all -> 0x004c }
            int r3 = r3.size()     // Catch:{ all -> 0x004c }
            int r3 = r3 + -1
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x004c }
            android.app.Fragment r0 = (android.app.Fragment) r0     // Catch:{ all -> 0x004c }
            if (r6 == 0) goto L_0x0038
            java.util.ArrayList<android.app.Fragment> r3 = r4.f3234b     // Catch:{ all -> 0x004c }
            r3.remove(r0)     // Catch:{ all -> 0x004c }
        L_0x0038:
            r2.detach(r0)     // Catch:{ all -> 0x004c }
        L_0x003b:
            if (r5 == 0) goto L_0x0047
            int r0 = com.jumio.p311nv.C4430R.C4432id.fragment_container     // Catch:{ all -> 0x004c }
            r2.add(r0, r5)     // Catch:{ all -> 0x004c }
            java.util.ArrayList<android.app.Fragment> r0 = r4.f3234b     // Catch:{ all -> 0x004c }
            r0.add(r5)     // Catch:{ all -> 0x004c }
        L_0x0047:
            r2.commitAllowingStateLoss()     // Catch:{ IllegalStateException -> 0x004f }
        L_0x004a:
            monitor-exit(r1)     // Catch:{ all -> 0x004c }
            goto L_0x000a
        L_0x004c:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x004c }
            throw r0
        L_0x004f:
            r0 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r0)     // Catch:{ all -> 0x004c }
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.p311nv.NetverifyActivity.startFragment(android.app.Fragment, boolean, int, int):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void closeFragment(int r5, int r6) {
        /*
            r4 = this;
            java.lang.Object r1 = r4.f3233a
            monitor-enter(r1)
            r0 = 0
            r4.f3235c = r0     // Catch:{ all -> 0x0018 }
            boolean r0 = r4.isFinishing()     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x000e
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
        L_0x000d:
            return
        L_0x000e:
            java.util.ArrayList<android.app.Fragment> r0 = r4.f3234b     // Catch:{ all -> 0x0018 }
            int r0 = r0.size()     // Catch:{ all -> 0x0018 }
            if (r0 != 0) goto L_0x001b
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            goto L_0x000d
        L_0x0018:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            throw r0
        L_0x001b:
            java.util.ArrayList<android.app.Fragment> r0 = r4.f3234b     // Catch:{ all -> 0x0018 }
            java.util.ArrayList<android.app.Fragment> r2 = r4.f3234b     // Catch:{ all -> 0x0018 }
            int r2 = r2.size()     // Catch:{ all -> 0x0018 }
            int r2 = r2 + -1
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x0018 }
            android.app.Fragment r0 = (android.app.Fragment) r0     // Catch:{ all -> 0x0018 }
            android.app.FragmentManager r2 = r4.getFragmentManager()     // Catch:{ all -> 0x0018 }
            android.app.FragmentTransaction r2 = r2.beginTransaction()     // Catch:{ all -> 0x0018 }
            r2.setCustomAnimations(r5, r6)     // Catch:{ all -> 0x0018 }
            r2.remove(r0)     // Catch:{ all -> 0x0018 }
            java.util.ArrayList<android.app.Fragment> r3 = r4.f3234b     // Catch:{ all -> 0x0018 }
            r3.remove(r0)     // Catch:{ all -> 0x0018 }
            java.util.ArrayList<android.app.Fragment> r0 = r4.f3234b     // Catch:{ all -> 0x0018 }
            int r0 = r0.size()     // Catch:{ all -> 0x0018 }
            if (r0 <= 0) goto L_0x0059
            java.util.ArrayList<android.app.Fragment> r0 = r4.f3234b     // Catch:{ all -> 0x0018 }
            java.util.ArrayList<android.app.Fragment> r3 = r4.f3234b     // Catch:{ all -> 0x0018 }
            int r3 = r3.size()     // Catch:{ all -> 0x0018 }
            int r3 = r3 + -1
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0018 }
            android.app.Fragment r0 = (android.app.Fragment) r0     // Catch:{ all -> 0x0018 }
            r2.attach(r0)     // Catch:{ all -> 0x0018 }
        L_0x0059:
            r2.commitAllowingStateLoss()     // Catch:{ IllegalStateException -> 0x005e }
        L_0x005c:
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            goto L_0x000d
        L_0x005e:
            r0 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r0)     // Catch:{ all -> 0x0018 }
            goto L_0x005c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.p311nv.NetverifyActivity.closeFragment(int, int):void");
    }

    public NfcController getNfcController() {
        if (this.f3237e == null) {
            this.f3237e = C4937q.m3077a(getApplicationContext());
            if (this.f3237e == null) {
                this.f3237e = new C4421a();
            }
        }
        return this.f3237e;
    }

    public DeviceRotationManager getRotationManager() {
        return this.f3238f;
    }

    public void animateActionbar(boolean z, final boolean z2) {
        float f;
        float f2;
        float f3 = 1.0f;
        float f4 = 0.0f;
        AnimatorSet animatorSet = new AnimatorSet();
        Toolbar toolbar = this.f3239g;
        String str = "alpha";
        float[] fArr = new float[2];
        fArr[0] = z ? 0.5f : 1.0f;
        fArr[1] = z ? 1.0f : 0.5f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(toolbar, str, fArr);
        ofFloat.setDuration(150);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        Builder play = animatorSet.play(ofFloat);
        if (z2 != ((Boolean) this.f3240h.getTag()).booleanValue()) {
            this.f3240h.setTag(Boolean.valueOf(z2));
            TextView textView = this.f3240h;
            String str2 = "alpha";
            float[] fArr2 = new float[2];
            if (z2) {
                f = 0.0f;
            } else {
                f = 1.0f;
            }
            fArr2[0] = f;
            if (!z2) {
                f3 = 0.0f;
            }
            fArr2[1] = f3;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, str2, fArr2);
            ofFloat2.setDuration(150);
            ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
            LinearLayout linearLayout = this.f3241i;
            String str3 = "translationY";
            float[] fArr3 = new float[2];
            if (z2) {
                f2 = (float) (-this.f3241i.getHeight());
            } else {
                f2 = 0.0f;
            }
            fArr3[0] = f2;
            if (!z2) {
                f4 = (float) (-this.f3241i.getHeight());
            }
            fArr3[1] = f4;
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(linearLayout, str3, fArr3);
            ofFloat3.setDuration(150);
            ofFloat3.setInterpolator(new AccelerateDecelerateInterpolator());
            ofFloat3.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    if (z2) {
                        NetverifyActivity.this.f3241i.setVisibility(0);
                    }
                }

                public void onAnimationEnd(Animator animator) {
                    if (!z2) {
                        NetverifyActivity.this.f3241i.setVisibility(8);
                    }
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
            if (z2) {
                play.with(ofFloat3).before(ofFloat2);
            } else {
                play.with(ofFloat2).before(ofFloat3);
            }
        }
        animatorSet.start();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (!getNfcController().consumeIntent(i, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1938a(Intent intent) {
        synchronized (this.f3233a) {
            intent.setAction("");
            int intExtra = intent.getIntExtra("com.jumio.nv.RESULT", 0);
            intent.removeExtra("com.jumio.nv.RESULT");
            setResult(intExtra, intent);
            try {
                JumioAnalytics.add(MobileEvents.sdkLifecycle(JumioAnalytics.getSessionId(), intExtra == -1 ? DismissType.FINISHED : DismissType.CANCELLED));
            } catch (IllegalStateException e) {
            }
            DataAccess.delete((Context) this, SelectionModel.class);
            DataAccess.delete((Context) this, InitiateModel.class);
            DataAccess.delete((Context) this, BackendModel.class);
            DataAccess.delete((Context) this, LivenessModel.class);
            DataAccess.delete((Context) this, DeviceCategory.class);
            DataAccess.delete((Context) this, NVScanPartModel.class);
            DataAccess.delete((Context) this, "fallbackScanPartModel");
            DataAccess.delete((Context) this, PreviewProperties.class);
            DataAccess.delete((Context) this, BackendModel.class);
            DataAccess.delete((Context) this, DocumentDataModel.class);
            File[] listFiles = new File(getFilesDir(), "jumio").listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.isFile() && file.getName().startsWith("tmp_") && !file.delete()) {
                        file.deleteOnExit();
                    }
                }
            }
            NVBackend.cancelAllPending();
            finish();
        }
    }
}
