package com.jumio.p311nv.view.fragment;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.res.Resources.Theme;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MetaInfo;
import com.jumio.analytics.MobileEvents;
import com.jumio.analytics.Screen;
import com.jumio.analytics.UserAction;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.commons.view.CompatibilityLayer;
import com.jumio.commons.view.SVGImageView;
import com.jumio.core.mvp.presenter.RequiresPresenter;
import com.jumio.p311nv.C4430R;
import com.jumio.p311nv.api.calls.NVBackend;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.p311nv.view.interactors.UploadView;
import com.jumio.sdk.exception.JumioErrorCase;
import com.jumio.sdk.exception.JumioException;
import com.jumio.sdk.gui.CircleView;
import com.jumio.sdk.gui.MaterialProgressBar;
import com.jumio.sdk.models.CredentialsModel;
import jumio.p317nv.core.C4914ai;

@RequiresPresenter(C4914ai.class)
/* renamed from: com.jumio.nv.view.fragment.UploadFragment */
public class UploadFragment extends NVBaseFragment<C4914ai> implements INetverifyActivityCallback, UploadView {

    /* renamed from: a */
    private ViewGroup f3527a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public SVGImageView f3528b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public CircleView f3529c;

    /* renamed from: d */
    private MaterialProgressBar f3530d;

    /* renamed from: e */
    private FrameLayout f3531e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public TextView f3532f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public TextView f3533g;

    /* renamed from: h */
    private View f3534h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public View f3535i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public Button f3536j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public JumioException f3537k;

    /* renamed from: l */
    private C4508c f3538l;

    /* renamed from: com.jumio.nv.view.fragment.UploadFragment$a */
    class C4506a implements OnClickListener {

        /* renamed from: b */
        private JumioException f3544b;

        public C4506a(JumioException jumioException) {
            this.f3544b = jumioException;
        }

        public void onClick(View view) {
            ((C4914ai) UploadFragment.this.getPresenter()).mo46847a(this.f3544b);
        }
    }

    /* renamed from: com.jumio.nv.view.fragment.UploadFragment$b */
    class C4507b implements OnClickListener {
        private C4507b() {
        }

        public void onClick(View view) {
            view.setEnabled(false);
            if (UploadFragment.this.f3537k != null) {
                JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.ERROR, UserAction.RETRY, String.valueOf(UploadFragment.this.f3537k.getErrorCase().code())));
                UploadFragment.this.f3537k = null;
            }
            ((C4914ai) UploadFragment.this.getPresenter()).mo46848b();
            UploadFragment.this.m2040a(C4508c.PROGRESS);
            view.setEnabled(true);
        }
    }

    /* renamed from: com.jumio.nv.view.fragment.UploadFragment$c */
    enum C4508c {
        PROGRESS,
        SUCCESS,
        ERROR
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((INetverifyFragmentCallback) this.callback).registerActivityCallback(this);
        if (bundle != null) {
            this.f3537k = (JumioException) bundle.getSerializable("JumioException");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putSerializable("JumioException", this.f3537k);
        }
    }

    public boolean onBackButtonPressed() {
        if (this.f3538l == C4508c.SUCCESS) {
            return true;
        }
        if (this.f3537k == null || this.f3537k.getErrorCase().retry()) {
            NVBackend.cancelAllPending();
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(C4430R.attr.colorPrimaryDark, typedValue, true);
            this.f3535i.setTranslationY((float) (-this.f3535i.getHeight()));
            showActionbar(true);
            setStatusBarColor(typedValue.data);
            return false;
        }
        ((C4914ai) getPresenter()).mo46847a(this.f3537k);
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3531e = new FrameLayout(getContext());
        this.f3531e.setLayoutParams(new LayoutParams(-1, -1));
        showActionbar(false);
        getActivity().findViewById(C4430R.C4432id.fragment_container).bringToFront();
        return this.f3531e;
    }

    public void handleOrientationChange(boolean z, boolean z2) {
        Drawable drawable;
        LayoutInflater from = LayoutInflater.from(getContext());
        if (z2) {
            this.f3531e.removeAllViews();
        }
        this.f3527a = (ViewGroup) from.inflate(z ? C4430R.layout.fragment_upload_portrait : C4430R.layout.fragment_upload_landscape, null);
        this.f3533g = (TextView) this.f3527a.findViewById(C4430R.C4432id.upload_title);
        this.f3533g.setText(Html.fromHtml(NVStrings.getExternalString(getContext(), NVStrings.UPLOAD_PROGRESS)));
        this.f3532f = (TextView) this.f3527a.findViewById(C4430R.C4432id.upload_description);
        this.f3532f.setText(NVStrings.getExternalString(getContext(), NVStrings.UPLOAD_PROGRESS_DESCRIPTION));
        this.f3534h = this.f3527a.findViewById(C4430R.C4432id.upload_separator);
        this.f3534h.setVisibility(0);
        this.f3535i = getActivity().findViewById(C4430R.C4432id.loadingBackground);
        this.f3535i.setContentDescription(this.f3533g.getText() + "\n" + this.f3532f.getText());
        int dpToPx = ScreenUtil.dpToPx(getContext(), 190);
        int dpToPx2 = ScreenUtil.dpToPx(getContext(), 40);
        int dpToPx3 = ScreenUtil.dpToPx(getContext(), 6);
        RelativeLayout relativeLayout = (RelativeLayout) this.f3527a.findViewById(C4430R.C4432id.doctype_container);
        this.f3530d = new MaterialProgressBar(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpToPx, dpToPx);
        layoutParams.addRule(13);
        this.f3530d.setLayoutParams(layoutParams);
        this.f3530d.setPadding(0, 0, 0, 0);
        this.f3530d.setCircleBackgroundEnabled(false);
        this.f3530d.setProgressStokeWidth(dpToPx3);
        relativeLayout.addView(this.f3530d);
        this.f3529c = new CircleView(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dpToPx, dpToPx);
        layoutParams2.addRule(13);
        this.f3529c.setLayoutParams(layoutParams2);
        this.f3529c.setPadding(0, 0, 0, 0);
        this.f3529c.setVisibility(4);
        relativeLayout.addView(this.f3529c);
        this.f3528b = new SVGImageView(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(dpToPx, dpToPx);
        layoutParams3.addRule(13);
        this.f3528b.setLayoutParams(layoutParams3);
        this.f3528b.setPadding(dpToPx2, dpToPx2, dpToPx2, dpToPx2);
        this.f3528b.setPathString("M520 743 l0 -458 -208 208 -207 207 -53 -53 -52 -52 300 -300 300 -300 300 300 300 300 -52 52 -53 53 -207 -207 -208 -208 0 458 0 457 -80 0 -80 0 0 -457z");
        relativeLayout.addView(this.f3528b);
        this.f3536j = (Button) this.f3527a.findViewById(C4430R.C4432id.upload_retry);
        this.f3527a.setVisibility(0);
        if (!z2) {
            this.f3538l = C4508c.PROGRESS;
            this.f3530d.setAlpha(0.0f);
            this.f3527a.setAlpha(0.0f);
            this.f3535i.setVisibility(0);
            m2043b(this.f3538l);
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(C4430R.attr.netverify_submissionProgressSuccessBackground, typedValue, true);
            this.f3535i.setBackgroundColor(typedValue.data);
        } else {
            if (this.f3538l.equals(C4508c.PROGRESS)) {
                this.f3529c.setVisibility(4);
                this.f3528b.setPathString("M520 743 l0 -458 -208 208 -207 207 -53 -53 -52 -52 300 -300 300 -300 300 300 300 300 -52 52 -53 53 -207 -207 -208 -208 0 458 0 457 -80 0 -80 0 0 -457z");
                this.f3528b.requestLayout();
                this.f3533g.setText(Html.fromHtml(NVStrings.getExternalString(getContext(), NVStrings.UPLOAD_PROGRESS)));
                this.f3532f.setText(NVStrings.getExternalString(getContext(), NVStrings.UPLOAD_PROGRESS_DESCRIPTION));
                this.f3536j.setVisibility(8);
            } else if (this.f3538l.equals(C4508c.SUCCESS)) {
                this.f3529c.setScaleX(1.0f);
                this.f3529c.setScaleY(1.0f);
                this.f3529c.setVisibility(0);
                this.f3528b.setPathString("M226.749912,330.55 L166.199912,270 L146.016579,290.183333 L226.749912,370.916667 L399.749912,197.916667 L379.566579,177.733333 L226.749912,330.55 Z");
                this.f3528b.requestLayout();
                this.f3533g.setText(Html.fromHtml(NVStrings.getExternalString(getContext(), NVStrings.UPLOAD_SUCCESSFUL)));
                this.f3532f.setText("");
            } else if (this.f3538l.equals(C4508c.ERROR)) {
                this.f3529c.setScaleX(1.0f);
                this.f3529c.setScaleY(1.0f);
                this.f3529c.setVisibility(0);
                this.f3528b.setPathString("M370.916667,190.266667 L350.733333,170.083333 L270,250.816667 L189.266667,170.083333 L169.083333,190.266667 L249.816667,271 L169.083333,351.733333 L189.266667,371.916667 L270,291.183333 L350.733333,371.916667 L370.916667,351.733333 L290.183333,271 L370.916667,190.266667 Z");
                this.f3528b.requestLayout();
                this.f3533g.setText(Html.fromHtml(NVStrings.getExternalString(getContext(), NVStrings.UPLOAD_ERROR)));
                this.f3532f.setText(NVStrings.getExternalString(getContext(), NVStrings.UPLOAD_ERROR_DESCRIPTION));
                this.f3536j.setText(NVStrings.getExternalString(getContext(), this.f3537k.getErrorCase().retry() ? NVStrings.BUTTON_RETRY : NVStrings.BUTTON_CANCEL));
                this.f3536j.setOnClickListener(this.f3537k.getErrorCase().retry() ? new C4507b() : new C4506a(this.f3537k));
                Button button = this.f3536j;
                if (this.f3537k.getErrorCase().retry()) {
                    drawable = CompatibilityLayer.getDrawable(getResources(), C4430R.C4431drawable.upload_retry);
                } else {
                    drawable = null;
                }
                button.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                this.f3536j.setAlpha(1.0f);
                this.f3536j.setVisibility(0);
            }
            m2043b(this.f3538l);
        }
        this.f3531e.addView(this.f3527a);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        JumioAnalytics.add(MobileEvents.pageView(JumioAnalytics.getSessionId(), Screen.SUBMISSION, null));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f3535i, "translationY", new float[]{(float) (-this.f3535i.getHeight()), 0.0f});
        ofFloat.setDuration(300);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.start();
        Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), C4430R.animator.slide_up);
        loadAnimator.setTarget(this.f3527a);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f3530d, "alpha", new float[]{0.0f, 1.0f});
        ofFloat2.setDuration(300);
        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(loadAnimator).before(ofFloat2);
        animatorSet.start();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void uploadComplete() {
        m2040a(C4508c.SUCCESS);
    }

    public CredentialsModel getCredentialsModel() {
        return ((INetverifyFragmentCallback) this.callback).getCredentials();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2040a(final C4508c cVar) {
        if (!cVar.equals(this.f3538l)) {
            this.f3538l = cVar;
            TypedValue typedValue = new TypedValue();
            TypedValue typedValue2 = new TypedValue();
            Theme theme = getContext().getTheme();
            theme.resolveAttribute(C4430R.attr.netverify_submissionProgressSuccessBackground, typedValue, true);
            theme.resolveAttribute(C4430R.attr.netverify_submissionErrorBackground, typedValue2, true);
            AnimatorSet animatorSet = new AnimatorSet();
            AnimatorSet animatorSet2 = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f3528b, "scaleX", new float[]{1.0f, 0.1f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f3528b, "scaleY", new float[]{1.0f, 0.1f});
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f3533g, "alpha", new float[]{1.0f, 0.2f});
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f3532f, "alpha", new float[]{1.0f, 0.2f});
            animatorSet2.setDuration(150);
            animatorSet2.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    if (cVar.equals(C4508c.PROGRESS)) {
                        UploadFragment.this.f3529c.setVisibility(4);
                        UploadFragment.this.f3528b.setPathString("M520 743 l0 -458 -208 208 -207 207 -53 -53 -52 -52 300 -300 300 -300 300 300 300 300 -52 52 -53 53 -207 -207 -208 -208 0 458 0 457 -80 0 -80 0 0 -457z");
                        UploadFragment.this.f3528b.requestLayout();
                        UploadFragment.this.f3533g.setText(Html.fromHtml(NVStrings.getExternalString(UploadFragment.this.getContext(), NVStrings.UPLOAD_PROGRESS)));
                        UploadFragment.this.f3532f.setText(NVStrings.getExternalString(UploadFragment.this.getContext(), NVStrings.UPLOAD_PROGRESS_DESCRIPTION));
                        UploadFragment.this.f3536j.setVisibility(8);
                    } else if (cVar.equals(C4508c.SUCCESS)) {
                        UploadFragment.this.f3529c.setScaleX(0.1f);
                        UploadFragment.this.f3529c.setScaleY(0.1f);
                        UploadFragment.this.f3529c.setVisibility(0);
                        UploadFragment.this.f3528b.setPathString("M226.749912,330.55 L166.199912,270 L146.016579,290.183333 L226.749912,370.916667 L399.749912,197.916667 L379.566579,177.733333 L226.749912,330.55 Z");
                        UploadFragment.this.f3528b.requestLayout();
                        UploadFragment.this.f3533g.setText(Html.fromHtml(NVStrings.getExternalString(UploadFragment.this.getContext(), NVStrings.UPLOAD_SUCCESSFUL)));
                        UploadFragment.this.f3532f.setText("");
                    } else if (cVar.equals(C4508c.ERROR)) {
                        UploadFragment.this.f3529c.setScaleX(0.1f);
                        UploadFragment.this.f3529c.setScaleY(0.1f);
                        UploadFragment.this.f3529c.setVisibility(0);
                        UploadFragment.this.f3528b.setPathString("M370.916667,190.266667 L350.733333,170.083333 L270,250.816667 L189.266667,170.083333 L169.083333,190.266667 L249.816667,271 L169.083333,351.733333 L189.266667,371.916667 L270,291.183333 L350.733333,371.916667 L370.916667,351.733333 L290.183333,271 L370.916667,190.266667 Z");
                        UploadFragment.this.f3528b.requestLayout();
                        UploadFragment.this.f3533g.setText(Html.fromHtml(NVStrings.getExternalString(UploadFragment.this.getContext(), NVStrings.UPLOAD_ERROR)));
                        UploadFragment.this.f3532f.setText(NVStrings.getExternalString(UploadFragment.this.getContext(), NVStrings.UPLOAD_ERROR_DESCRIPTION));
                        UploadFragment.this.f3536j.setAlpha(0.0f);
                        UploadFragment.this.f3536j.setVisibility(0);
                    }
                    UploadFragment.this.m2043b(cVar);
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
            Builder with = animatorSet2.play(ofFloat).with(ofFloat2).with(ofFloat3).with(ofFloat4);
            AnimatorSet animatorSet3 = new AnimatorSet();
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.f3528b, "scaleX", new float[]{0.1f, 1.0f});
            ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.f3528b, "scaleY", new float[]{0.1f, 1.0f});
            ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this.f3533g, "alpha", new float[]{0.2f, 1.0f});
            ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(this.f3532f, "alpha", new float[]{0.2f, 1.0f});
            animatorSet3.setDuration(150);
            Builder with2 = animatorSet3.play(ofFloat5).with(ofFloat6).with(ofFloat7).with(ofFloat8);
            Builder before = animatorSet.play(animatorSet2).before(animatorSet3);
            if (cVar.equals(C4508c.PROGRESS)) {
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{typedValue2.data, typedValue.data});
                ofInt.setDuration(300);
                ofInt.setEvaluator(new ArgbEvaluator());
                ofInt.setInterpolator(new DecelerateInterpolator());
                ofInt.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        UploadFragment.this.f3535i.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                before.with(ofInt);
                ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(this.f3528b, "rotation", new float[]{90.0f, 0.0f});
                ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(this.f3529c, "scaleX", new float[]{1.0f, 0.1f});
                with.with(ofFloat10).with(ObjectAnimator.ofFloat(this.f3529c, "scaleY", new float[]{1.0f, 0.1f})).with(ofFloat9);
                ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(this.f3530d, "alpha", new float[]{0.0f, 1.0f});
                ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(this.f3530d, "scaleX", new float[]{0.1f, 1.0f});
                ObjectAnimator ofFloat13 = ObjectAnimator.ofFloat(this.f3530d, "scaleY", new float[]{0.1f, 1.0f});
                Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), C4430R.animator.slide_down);
                loadAnimator.setTarget(this.f3536j);
                with2.with(ofFloat11).with(ofFloat12).with(ofFloat13).with(loadAnimator);
            } else {
                ObjectAnimator ofFloat14 = ObjectAnimator.ofFloat(this.f3530d, "alpha", new float[]{1.0f, 0.0f});
                ObjectAnimator ofFloat15 = ObjectAnimator.ofFloat(this.f3530d, "scaleX", new float[]{1.0f, 0.1f});
                with.with(ofFloat14).with(ofFloat15).with(ObjectAnimator.ofFloat(this.f3530d, "scaleY", new float[]{1.0f, 0.1f}));
                ObjectAnimator ofFloat16 = ObjectAnimator.ofFloat(this.f3529c, "scaleX", new float[]{0.1f, 1.0f});
                with2.with(ofFloat16).with(ObjectAnimator.ofFloat(this.f3529c, "scaleY", new float[]{0.1f, 1.0f}));
                if (cVar.equals(C4508c.ERROR)) {
                    ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{typedValue.data, typedValue2.data});
                    ofInt2.setDuration(300);
                    ofInt2.setEvaluator(new ArgbEvaluator());
                    ofInt2.setInterpolator(new DecelerateInterpolator());
                    ofInt2.addUpdateListener(new AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            UploadFragment.this.f3535i.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                        }
                    });
                    ObjectAnimator ofFloat17 = ObjectAnimator.ofFloat(this.f3528b, "rotation", new float[]{0.0f, 90.0f});
                    before.with(ofInt2);
                    Animator loadAnimator2 = AnimatorInflater.loadAnimator(getContext(), C4430R.animator.slide_up);
                    loadAnimator2.setTarget(this.f3536j);
                    with2.with(loadAnimator2).with(ofFloat17);
                }
            }
            animatorSet.start();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2043b(C4508c cVar) {
        int i;
        boolean z = cVar == C4508c.ERROR;
        TypedValue typedValue = new TypedValue();
        Theme theme = getContext().getTheme();
        theme.resolveAttribute(z ? C4430R.attr.netverify_submissionErrorBackground : C4430R.attr.netverify_submissionProgressSuccessBackground, typedValue, true);
        int i2 = typedValue.data;
        theme.resolveAttribute(z ? C4430R.attr.netverify_submissionErrorImage : C4430R.attr.netverify_submissionProgressSuccessImage, typedValue, true);
        int i3 = typedValue.data;
        this.f3529c.setFillColor(i3);
        SVGImageView sVGImageView = this.f3528b;
        if (cVar == C4508c.PROGRESS) {
            i = i3;
        } else {
            i = i2;
        }
        sVGImageView.setPaintColor(i);
        this.f3530d.setColorSchemeColors(i3);
        theme.resolveAttribute(z ? C4430R.attr.netverify_submissionErrorTitle : C4430R.attr.netverify_submissionProgressSuccessTitle, typedValue, true);
        this.f3533g.setTextColor(typedValue.data);
        theme.resolveAttribute(z ? C4430R.attr.netverify_submissionErrorDescription : C4430R.attr.netverify_submissionProgressSuccessDescription, typedValue, true);
        this.f3532f.setTextColor(typedValue.data);
        theme.resolveAttribute(z ? C4430R.attr.netverify_submissionErrorSeperator : C4430R.attr.netverify_submissionProgressSuccessSeperator, typedValue, true);
        this.f3534h.setBackgroundColor(typedValue.data);
        theme.resolveAttribute(z ? C4430R.attr.netverify_submissionErrorStatusBar : C4430R.attr.colorPrimaryDark, typedValue, true);
        setStatusBarColor(typedValue.data);
        if (z && this.f3536j.getCompoundDrawables()[0] != null) {
            theme.resolveAttribute(C4430R.attr.netverify_submissionErrorDescription, typedValue, true);
            this.f3536j.getCompoundDrawables()[0].setColorFilter(typedValue.data, Mode.SRC_IN);
        }
    }

    public void onError(Throwable th) {
        Drawable drawable;
        if (!this.f3538l.equals(C4508c.ERROR) && (th instanceof JumioException)) {
            this.f3537k = (JumioException) th;
            JumioErrorCase errorCase = this.f3537k.getErrorCase();
            this.f3536j.setText(NVStrings.getExternalString(getContext(), errorCase.retry() ? NVStrings.BUTTON_RETRY : NVStrings.BUTTON_CANCEL));
            this.f3536j.setOnClickListener(errorCase.retry() ? new C4507b() : new C4506a(this.f3537k));
            Button button = this.f3536j;
            if (errorCase.retry()) {
                drawable = CompatibilityLayer.getDrawable(getResources(), C4430R.C4431drawable.upload_retry);
            } else {
                drawable = null;
            }
            button.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            m2040a(C4508c.ERROR);
            MetaInfo metaInfo = new MetaInfo();
            metaInfo.put("errorCode", Integer.valueOf(this.f3537k.getErrorCase().code()));
            metaInfo.put("detailedErrorCode", Integer.valueOf(this.f3537k.getDetailedErrorCase()));
            metaInfo.put("errorMessage", th.getMessage());
            metaInfo.put("retryPossible", Boolean.valueOf(true));
            JumioAnalytics.add(MobileEvents.pageView(JumioAnalytics.getSessionId(), Screen.ERROR, metaInfo));
        }
    }
}
