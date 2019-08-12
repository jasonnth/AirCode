package jumio.p317nv.nfc;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputFilter.AllCaps;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MetaInfo;
import com.jumio.analytics.MobileEvents;
import com.jumio.analytics.Screen;
import com.jumio.analytics.UserAction;
import com.jumio.commons.log.Log;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.commons.view.SVGImageView;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.presenter.RequiresPresenter;
import com.jumio.core.overlay.Overlay;
import com.jumio.nfc.C4414R;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.p311nv.enums.EPassportStatus;
import com.jumio.p311nv.extraction.NfcController;
import com.jumio.p311nv.nfc.core.communication.TagAccessSpec;
import com.jumio.p311nv.view.fragment.INetverifyActivityCallback;
import com.jumio.p311nv.view.fragment.INetverifyFragmentCallback;
import com.jumio.p311nv.view.fragment.NVBaseFragment;
import com.jumio.p311nv.view.fragment.NVScanFragment;
import com.jumio.p311nv.view.fragment.UploadFragment;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.gui.CircleView;
import com.jumio.sdk.models.CredentialsModel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import net.p318sf.scuba.smartcards.CardServiceException;
import p003at.grabner.circleprogress.CircleProgressView;
import p003at.grabner.circleprogress.TextMode;

@RequiresPresenter(C5120v.class)
/* renamed from: jumio.nv.nfc.w */
/* compiled from: NfcFragment */
public class C5126w extends NVBaseFragment<C5120v> implements OnGlobalLayoutListener, Overlay, INetverifyActivityCallback, C5119u {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public boolean f5672a = false;

    /* renamed from: b */
    private CircleProgressView f5673b;

    /* renamed from: c */
    private RelativeLayout f5674c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public ViewFlipper f5675d;

    /* renamed from: e */
    private ImageView f5676e;

    /* renamed from: f */
    private ImageView f5677f;

    /* renamed from: g */
    private TextView f5678g;

    /* renamed from: h */
    private TextView f5679h;

    /* renamed from: i */
    private TextView f5680i;

    /* renamed from: j */
    private RelativeLayout f5681j;

    /* renamed from: k */
    private int f5682k;

    /* renamed from: l */
    private Animator f5683l;

    /* renamed from: m */
    private Point f5684m;

    /* renamed from: n */
    private Point f5685n;

    /* renamed from: o */
    private float[] f5686o;

    /* renamed from: p */
    private Bitmap f5687p;

    /* renamed from: q */
    private Bitmap f5688q;

    /* renamed from: jumio.nv.nfc.w$a */
    /* compiled from: NfcFragment */
    class C5133a implements InputFilter {
        private C5133a() {
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            StringBuilder sb = new StringBuilder();
            for (int i5 = i; i5 < i2; i5++) {
                char charAt = charSequence.charAt(i5);
                if (Character.isLetterOrDigit(charAt)) {
                    sb.append(charAt);
                }
            }
            if (sb.length() == i2 - i) {
                return null;
            }
            return sb.toString();
        }
    }

    /* renamed from: jumio.nv.nfc.w$b */
    /* compiled from: NfcFragment */
    public class C5134b implements OnClickListener {

        /* renamed from: b */
        private Dialog f5701b;

        public C5134b(Dialog dialog) {
            this.f5701b = dialog;
        }

        public void onClick(View view) {
            this.f5701b.dismiss();
            C5126w.this.f5672a = false;
            ((C5120v) C5126w.this.getPresenter()).mo47229a(EPassportStatus.DENIED);
        }
    }

    /* renamed from: jumio.nv.nfc.w$c */
    /* compiled from: NfcFragment */
    public class C5135c implements OnClickListener {

        /* renamed from: b */
        private final EditText f5703b;

        /* renamed from: c */
        private final EditText f5704c;

        /* renamed from: d */
        private final EditText f5705d;

        /* renamed from: e */
        private Dialog f5706e;

        public C5135c(EditText editText, EditText editText2, EditText editText3, Dialog dialog) {
            this.f5703b = editText;
            this.f5704c = editText2;
            this.f5705d = editText3;
            this.f5706e = dialog;
        }

        public void onClick(View view) {
            if (this.f5703b.getText().length() > 0 && this.f5704c.getText().length() > 0 && this.f5705d.getText().length() > 0) {
                ((C5120v) C5126w.this.getPresenter()).mo47228a().setDateOfBirth(m3842a(this.f5704c.getText().toString()));
                ((C5120v) C5126w.this.getPresenter()).mo47228a().setPassportNumber(this.f5703b.getText().toString());
                ((C5120v) C5126w.this.getPresenter()).mo47228a().setDateOfExpiry(m3842a(this.f5705d.getText().toString()));
                ((C5120v) C5126w.this.getPresenter()).mo47228a().setShouldDownloadFaceimage(false);
                this.f5706e.dismiss();
                C5126w.this.f5672a = false;
                ((C5120v) C5126w.this.getPresenter()).mo47232a(true);
                C5126w.this.f5675d.setDisplayedChild(0);
                C5126w.this.m3834g();
            }
        }

        /* renamed from: a */
        private Date m3842a(String str) {
            try {
                return DateFormat.getDateFormat(C5126w.this.getView().getContext()).parse(str);
            } catch (ParseException e) {
                return new Date();
            }
        }
    }

    /* renamed from: jumio.nv.nfc.w$d */
    /* compiled from: NfcFragment */
    class C5136d implements OnClickListener {

        /* renamed from: b */
        private final Calendar f5708b = Calendar.getInstance();

        /* renamed from: c */
        private OnDateSetListener f5709c;

        public C5136d(OnDateSetListener onDateSetListener, Date date) {
            this.f5709c = onDateSetListener;
            if (date != null) {
                this.f5708b.setTime(date);
            }
        }

        public void onClick(View view) {
            new DatePickerDialog(C5126w.this.getActivity(), this.f5709c, this.f5708b.get(1), this.f5708b.get(2), this.f5708b.get(5)).show();
        }
    }

    /* renamed from: jumio.nv.nfc.w$e */
    /* compiled from: NfcFragment */
    class C5137e implements TextWatcher {

        /* renamed from: b */
        private TextView f5711b;

        /* renamed from: c */
        private TextView f5712c;

        /* renamed from: d */
        private View f5713d;

        public C5137e(TextView textView, TextView textView2, View view) {
            this.f5711b = textView;
            this.f5712c = textView2;
            this.f5713d = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            String charSequence2 = charSequence.toString();
            try {
                java.text.DateFormat dateFormat = DateFormat.getDateFormat(C5126w.this.getActivity().getApplicationContext());
                dateFormat.setLenient(false);
                dateFormat.parse(charSequence2);
                this.f5711b.setError(null);
                if (this.f5712c != null) {
                    this.f5712c.setVisibility(0);
                }
                if (this.f5713d != null) {
                    this.f5713d.setEnabled(true);
                    this.f5713d.setAlpha(1.0f);
                }
            } catch (ParseException e) {
                C5100f.m3696b("NfcFragment", "error parsing date " + charSequence2);
                this.f5711b.setError("Invalid date");
                if (this.f5712c != null) {
                    this.f5712c.setVisibility(8);
                }
                if (this.f5713d != null) {
                    this.f5713d.setEnabled(false);
                    this.f5713d.setAlpha(0.5f);
                }
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: jumio.nv.nfc.w$f */
    /* compiled from: NfcFragment */
    class C5138f implements TextWatcher {

        /* renamed from: b */
        private final TextView f5715b;

        /* renamed from: c */
        private final TextView f5716c;

        public C5138f(TextView textView, TextView textView2) {
            this.f5716c = textView;
            this.f5715b = textView2;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            float measureText = this.f5715b.getPaint().measureText(this.f5715b.getText().toString());
            float measureText2 = this.f5716c.getPaint().measureText(this.f5716c.getText().toString());
            int width = this.f5716c.getWidth();
            if (width > 0) {
                if (measureText2 * 1.2f >= ((float) width) - measureText) {
                    this.f5715b.setVisibility(4);
                } else {
                    this.f5715b.setVisibility(0);
                }
            }
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: a */
    public static C5126w m3828a(Bundle bundle) {
        C5126w wVar = new C5126w();
        wVar.setArguments(bundle);
        return wVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            TagAccessSpec tagAccessSpec = new TagAccessSpec();
            tagAccessSpec.setPassportNumber(arguments.getString("ppnumber", ""));
            tagAccessSpec.setDateOfBirth(new Date(arguments.getLong("dob", 0)));
            tagAccessSpec.setDateOfExpiry(new Date(arguments.getLong("doe", 0)));
            tagAccessSpec.setCountryIso3(arguments.getString("country", ""));
            String string = arguments.getString("scanReference", "");
            ((C5120v) getPresenter()).mo47230a(tagAccessSpec);
            ((C5120v) getPresenter()).mo47231a(string);
            C5100f.m3693a("MRZ_" + string);
        }
        setActionbarTitle(NVStrings.getExternalString(getActivity(), NVStrings.NFC_TITLE));
        ((INetverifyFragmentCallback) this.callback).registerActivityCallback(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f5674c = new RelativeLayout(getContext());
        this.f5674c.setLayoutParams(new LayoutParams(-1, -1));
        this.f5675d = new ViewFlipper(getContext());
        this.f5675d.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f5675d.setInAnimation(AnimationUtils.loadAnimation(getContext(), 17432578));
        this.f5675d.setOutAnimation(AnimationUtils.loadAnimation(getContext(), 17432579));
        this.f5674c.addView(this.f5675d);
        return this.f5674c;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f5674c.getViewTreeObserver().addOnGlobalLayoutListener(this);
        m3834g();
    }

    public void onResume() {
        super.onResume();
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f5675d.getChildAt(0).findViewById(C4414R.C4416id.help_background), "alpha", new float[]{0.0f, 1.0f});
        ofFloat.setDuration(300);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f5675d.getChildAt(0).findViewById(C4414R.C4416id.overall_container), "alpha", new float[]{0.0f, 1.0f});
        ofFloat2.setStartDelay(200);
        ofFloat2.setDuration(500);
        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.f5676e != null) {
            this.f5676e.setImageBitmap(null);
        }
        if (this.f5677f != null) {
            this.f5677f.setImageBitmap(null);
        }
        if (this.f5688q != null) {
            this.f5688q.recycle();
            this.f5688q = null;
        }
        if (this.f5687p != null) {
            this.f5687p.recycle();
            this.f5687p = null;
        }
        System.gc();
    }

    public void handleOrientationChange(boolean z, boolean z2) {
        int i = 0;
        if (z2 && this.f5675d != null) {
            i = this.f5675d.getDisplayedChild();
            this.f5675d.removeAllViews();
        }
        int i2 = i;
        LayoutInflater from = LayoutInflater.from(getContext());
        RelativeLayout relativeLayout = (RelativeLayout) from.inflate(z ? C4414R.layout.helpview_portrait : C4414R.layout.helpview_land, null);
        int dpToPx = ScreenUtil.dpToPx(getContext(), 45);
        relativeLayout.findViewById(C4414R.C4416id.overall_container).setPadding(dpToPx, 0, dpToPx, 0);
        relativeLayout.findViewById(C4414R.C4416id.helpview_progress_info).setVisibility(4);
        relativeLayout.findViewById(C4414R.C4416id.helpview_progress_bar).setVisibility(4);
        ((TextView) relativeLayout.findViewById(C4414R.C4416id.helpview_doctype_title)).setText(Html.fromHtml(NVStrings.getExternalString(getContext(), NVStrings.NFC_HEADER_START)));
        String externalString = NVStrings.getExternalString(getContext(), NVStrings.NFC_DESCRIPTION_START);
        ((TextView) relativeLayout.findViewById(C4414R.C4416id.helpview_scan_instructions)).setText(m3825a(externalString, "${icon}", C4414R.C4415drawable.epassport_white));
        ((TextView) relativeLayout.findViewById(C4414R.C4416id.helpview_tap_to_continue)).setText("");
        TypedValue typedValue = new TypedValue();
        Theme theme = getActivity().getTheme();
        theme.resolveAttribute(C4414R.attr.netverify_helpFallback, typedValue, true);
        int i3 = typedValue.data;
        Button button = (Button) relativeLayout.findViewById(C4414R.C4416id.helpview_fallback_button);
        button.setText(NVStrings.getExternalString(getContext(), NVStrings.NFC_BUTTON_NO_EPASSPORT));
        button.getBackground().setColorFilter(i3, Mode.MULTIPLY);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN, UserAction.NFC_NO_EPASSPORT));
                ((C5120v) C5126w.this.getPresenter()).mo47229a(EPassportStatus.NOT_PERFORMED);
            }
        });
        int dpToPx2 = ScreenUtil.dpToPx(getContext(), 190);
        int dpToPx3 = ScreenUtil.dpToPx(getContext(), 60);
        boolean equals = ((C5120v) getPresenter()).mo47228a().getCountryIso3().toLowerCase().equals("usa");
        LinearLayout linearLayout = (LinearLayout) relativeLayout.findViewById(C4414R.C4416id.doctype_container);
        linearLayout.setContentDescription(externalString);
        RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
        relativeLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, dpToPx3 + dpToPx2));
        if (this.f5688q == null) {
            this.f5688q = BitmapFactory.decodeResource(getResources(), equals ? C4414R.C4415drawable.passport_usa : C4414R.C4415drawable.passport_row);
        }
        if (this.f5687p == null) {
            this.f5687p = BitmapFactory.decodeResource(getResources(), equals ? C4414R.C4415drawable.phone_usa : C4414R.C4415drawable.phone_row);
        }
        this.f5676e = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, dpToPx2);
        layoutParams.addRule(12);
        this.f5676e.setLayoutParams(layoutParams);
        this.f5676e.setAdjustViewBounds(true);
        this.f5676e.setImageBitmap(this.f5688q);
        this.f5676e.setId(C4414R.C4416id.passport_imageview);
        relativeLayout2.addView(this.f5676e);
        this.f5677f = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, dpToPx2);
        layoutParams2.addRule(5, C4414R.C4416id.passport_imageview);
        layoutParams2.addRule(6, C4414R.C4416id.passport_imageview);
        layoutParams2.addRule(7, C4414R.C4416id.passport_imageview);
        layoutParams2.addRule(8, C4414R.C4416id.passport_imageview);
        this.f5677f.setLayoutParams(layoutParams2);
        this.f5677f.setAdjustViewBounds(true);
        this.f5677f.setImageBitmap(this.f5687p);
        this.f5677f.setId(C4414R.C4416id.passport_phone_imageview);
        relativeLayout2.addView(this.f5677f);
        linearLayout.addView(relativeLayout2);
        if (!z2) {
            relativeLayout.findViewById(C4414R.C4416id.help_background).setAlpha(0.0f);
            relativeLayout.findViewById(C4414R.C4416id.overall_container).setAlpha(0.0f);
        }
        this.f5675d.addView(relativeLayout);
        RelativeLayout relativeLayout3 = (RelativeLayout) from.inflate(z ? C4414R.layout.helpview_portrait : C4414R.layout.helpview_land, null);
        relativeLayout3.findViewById(C4414R.C4416id.helpview_progress_info).setVisibility(4);
        relativeLayout3.findViewById(C4414R.C4416id.helpview_fallback_button).setVisibility(4);
        relativeLayout3.findViewById(C4414R.C4416id.helpview_progress_bar).setVisibility(8);
        this.f5678g = (TextView) relativeLayout3.findViewById(C4414R.C4416id.helpview_doctype_title);
        this.f5678g.setText(Html.fromHtml(NVStrings.getExternalString(getContext(), NVStrings.NFC_HEADER_DOWNLOAD)));
        this.f5679h = (TextView) relativeLayout3.findViewById(C4414R.C4416id.helpview_scan_instructions);
        this.f5679h.setText(NVStrings.getExternalString(getContext(), NVStrings.NFC_DESCRIPTION_DOWNLOAD));
        this.f5680i = (TextView) relativeLayout3.findViewById(C4414R.C4416id.helpview_tap_to_continue);
        this.f5680i.setText(NVStrings.getExternalString(getContext(), NVStrings.HELPVIEW_TAP_TO_CONTINUE));
        this.f5680i.setVisibility(4);
        TypedValue typedValue2 = new TypedValue();
        theme.resolveAttribute(C4414R.attr.netverify_helpTitle, typedValue2, true);
        int i4 = typedValue2.data;
        TypedValue typedValue3 = new TypedValue();
        theme.resolveAttribute(C4414R.attr.netverify_helpBackground, typedValue3, true);
        int i5 = typedValue3.data;
        LinearLayout linearLayout2 = (LinearLayout) relativeLayout3.findViewById(C4414R.C4416id.doctype_container);
        int dpToPx4 = ScreenUtil.dpToPx(getContext(), 40);
        int dpToPx5 = ScreenUtil.dpToPx(getContext(), 6);
        int spToPx = (int) ScreenUtil.spToPx(getContext(), 45.0f);
        this.f5673b = new CircleProgressView(getContext());
        this.f5673b.setLayoutParams(new RelativeLayout.LayoutParams(dpToPx2, dpToPx2));
        this.f5673b.setBarColor(i4);
        this.f5673b.setBarWidth(dpToPx5);
        this.f5673b.setBlockCount(1);
        this.f5673b.setContourSize(0.0f);
        this.f5673b.setMaxValue(7.0f);
        this.f5673b.setRimColor(0);
        this.f5673b.setRimWidth(dpToPx5);
        this.f5673b.setTextColor(i4);
        this.f5673b.setTextSize(spToPx);
        this.f5673b.setUnit("%");
        this.f5673b.setUnitColor(i4);
        this.f5673b.setUnitSize(spToPx);
        this.f5673b.setValue(0.0f);
        this.f5673b.setShowUnit(true);
        this.f5673b.setTextMode(TextMode.PERCENT);
        linearLayout2.addView(this.f5673b);
        this.f5681j = new RelativeLayout(getContext());
        this.f5681j.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        CircleView circleView = new CircleView(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(dpToPx2, dpToPx2);
        layoutParams3.addRule(14);
        circleView.setLayoutParams(layoutParams3);
        circleView.setPadding(0, 0, 0, 0);
        circleView.setFillColor(i4);
        this.f5681j.addView(circleView);
        SVGImageView sVGImageView = new SVGImageView(getContext());
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(dpToPx2, dpToPx2);
        layoutParams4.addRule(14);
        sVGImageView.setLayoutParams(layoutParams4);
        sVGImageView.setPadding(dpToPx4, dpToPx4, dpToPx4, dpToPx4);
        sVGImageView.setPaintColor(i5);
        sVGImageView.setPathString("M226.749912,330.55 L166.199912,270 L146.016579,290.183333 L226.749912,370.916667 L399.749912,197.916667 L379.566579,177.733333 L226.749912,330.55 Z");
        this.f5681j.addView(sVGImageView);
        this.f5681j.setVisibility(8);
        linearLayout2.addView(this.f5681j);
        this.f5675d.addView(relativeLayout3);
        if (z2 && i2 != 0) {
            this.f5675d.setDisplayedChild(i2);
        }
    }

    public void onError(Throwable th) {
    }

    /* renamed from: a */
    public CredentialsModel mo47221a() {
        return ((INetverifyFragmentCallback) this.callback).getCredentials();
    }

    /* renamed from: b */
    public NfcController mo47225b() {
        return ((INetverifyFragmentCallback) this.callback).getNfcController();
    }

    /* renamed from: a */
    public void mo47224a(boolean z) {
        ((C5120v) getPresenter()).mo47232a(false);
        ((INetverifyFragmentCallback) this.callback).startFragment(z ? new UploadFragment() : new NVScanFragment(), true, 0, C4414R.animator.fade_out);
    }

    public void calculate(DocumentScanMode documentScanMode, int i, int i2) {
    }

    public void addViews(ViewGroup viewGroup) {
    }

    public void prepareDraw(ScanSide scanSide, boolean z, boolean z2) {
    }

    public void doDraw(Canvas canvas) {
    }

    public void update(ExtractionUpdate extractionUpdate) {
    }

    public void setVisible(int i) {
    }

    public Rect getOverlayBounds() {
        return new Rect();
    }

    public void onGlobalLayout() {
        if (this.f5684m == null) {
            this.f5684m = new Point();
            this.f5684m.x = (int) (this.f5676e.getX() + (((float) this.f5676e.getWidth()) / 2.0f));
            this.f5684m.y = (int) (this.f5676e.getY() + (((float) this.f5676e.getHeight()) / 2.0f));
        }
        if (this.f5685n == null) {
            this.f5685n = new Point();
            this.f5685n.x = (int) (this.f5677f.getX() + (((float) this.f5677f.getWidth()) / 2.0f));
            this.f5685n.y = (int) (this.f5677f.getY() + (((float) this.f5677f.getHeight()) / 2.0f));
        }
        if (this.f5682k == 0) {
            this.f5682k = ScreenUtil.dpToPx(getActivity(), 50);
        }
        if (this.f5686o == null) {
            this.f5686o = new float[]{this.f5677f.getY() - (((float) this.f5682k) * 0.9f), this.f5677f.getY()};
        }
        if (this.f5683l != null) {
            this.f5683l.cancel();
        }
        if (this.f5682k > 0) {
            this.f5683l = m3824a((View) this.f5677f, this.f5686o);
            this.f5683l.start();
        }
    }

    /* renamed from: a */
    private SpannableString m3825a(String str, String str2, int i) {
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return new SpannableString(str);
        }
        int spToPx = (int) ScreenUtil.spToPx(getContext(), 14.0f);
        float f = ((float) spToPx) * 1.8091873f;
        SpannableString spannableString = new SpannableString(str);
        Drawable drawable = getResources().getDrawable(i);
        if (drawable == null) {
            return spannableString;
        }
        drawable.setBounds(0, 0, (int) f, spToPx);
        spannableString.setSpan(new ImageSpan(drawable, 1), indexOf, str2.length() + indexOf, 33);
        return spannableString;
    }

    /* renamed from: a */
    private Animator m3824a(View view, float... fArr) {
        view.clearAnimation();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "y", fArr);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f});
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.addListener(new AnimatorListener() {

            /* renamed from: a */
            public boolean f5690a;

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.f5690a) {
                    animatorSet.setStartDelay(2000);
                    animatorSet.start();
                }
            }

            public void onAnimationCancel(Animator animator) {
                this.f5690a = true;
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        return animatorSet;
    }

    /* renamed from: c */
    public void mo47226c() {
        if (this.f5675d.getDisplayedChild() == 0) {
            this.f5675d.showNext();
        }
        Log.m1909d("NfcFragment", "access view class " + hashCode());
        this.f5681j.setVisibility(8);
        this.f5673b.setVisibility(0);
        this.f5673b.invalidate();
        this.f5678g.setText(Html.fromHtml(NVStrings.getExternalString(getContext(), NVStrings.NFC_HEADER_DOWNLOAD)));
        this.f5679h.setText(NVStrings.getExternalString(getContext(), NVStrings.NFC_DESCRIPTION_DOWNLOAD));
    }

    /* renamed from: a */
    public void mo47222a(int i) {
        this.f5673b.setValueAnimated((float) i);
    }

    /* renamed from: d */
    public void mo47227d() {
        this.f5673b.setVisibility(8);
        this.f5681j.setVisibility(0);
        this.f5678g.setText(Html.fromHtml(NVStrings.getExternalString(getContext(), NVStrings.NFC_HEADER_FINISH)));
        this.f5679h.setText("");
    }

    /* renamed from: a */
    public void mo47223a(C5112o oVar) {
        Dialog dialog;
        String str;
        C5100f.m3694a("NfcFragment", "showing dialog");
        ((C5120v) getPresenter()).mo47232a(false);
        Dialog dialog2 = null;
        if (oVar.mo47202a() != C5113p.BAC_CHECK || !(oVar.mo47207b().mo47214a() instanceof CardServiceException)) {
            Builder e = m3832e();
            if (e != null) {
                dialog2 = e.create();
            }
            dialog = dialog2;
            str = "NFC reading error";
        } else {
            dialog = m3833f();
            str = "NFC BAC authentication failed";
        }
        if (dialog != null) {
            dialog.setCancelable(false);
            dialog.show();
        }
        MetaInfo metaInfo = new MetaInfo();
        metaInfo.put("errorMessage", str);
        metaInfo.put("retryPossible", Boolean.valueOf(true));
        JumioAnalytics.add(MobileEvents.pageView(JumioAnalytics.getSessionId(), Screen.ERROR, metaInfo));
    }

    /* renamed from: e */
    private Builder m3832e() {
        if (this.f5672a) {
            return null;
        }
        this.f5672a = true;
        Activity activity = getActivity();
        return new Builder(activity, 5).setTitle(NVStrings.getExternalString(activity, NVStrings.NFC_GENERAL_ERROR_DIALOG_TITLE)).setMessage(NVStrings.getExternalString(activity, NVStrings.NFC_GENERAL_ERROR_DIALOG_TEXT)).setPositiveButton(NVStrings.getExternalString(activity, NVStrings.BUTTON_RETRY), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                C5126w.this.m3834g();
                C5126w.this.f5672a = false;
                C5126w.this.f5675d.showPrevious();
                ((C5120v) C5126w.this.getPresenter()).mo47232a(true);
                dialogInterface.dismiss();
            }
        }).setNegativeButton(NVStrings.getExternalString(activity, NVStrings.BUTTON_CANCEL), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                C5126w.this.f5672a = false;
                dialogInterface.dismiss();
                ((C5120v) C5126w.this.getPresenter()).mo47229a(EPassportStatus.DENIED);
            }
        }).setIcon(17301543);
    }

    /* renamed from: f */
    private Dialog m3833f() {
        Dialog dialog;
        if (this.f5672a) {
            return null;
        }
        this.f5672a = true;
        if (VERSION.SDK_INT >= 21) {
            dialog = new Dialog(getActivity(), 16974394);
        } else {
            dialog = new Dialog(getActivity());
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle(NVStrings.getExternalString(getActivity(), NVStrings.NFC_BAC_DIALOG_TITLE));
        dialog.setContentView(C4414R.layout.bac_entry_dialog);
        EditText editText = (EditText) dialog.findViewById(C4414R.C4416id.passportNumberTextfield);
        final EditText editText2 = (EditText) dialog.findViewById(C4414R.C4416id.dobEntryField);
        final EditText editText3 = (EditText) dialog.findViewById(C4414R.C4416id.doeEntryField);
        Activity activity = getActivity();
        TextView textView = (TextView) dialog.findViewById(C4414R.C4416id.ppNumberHint);
        TextView textView2 = (TextView) dialog.findViewById(C4414R.C4416id.hintDob);
        TextView textView3 = (TextView) dialog.findViewById(C4414R.C4416id.hintDoe);
        textView.setText(NVStrings.getExternalString(activity, NVStrings.NFC_BAC_DIALOG_PASSPORT_NUMBER));
        textView2.setText(NVStrings.getExternalString(activity, NVStrings.NFC_BAC_DIALOG_DATE_OF_BIRTH));
        textView3.setText(NVStrings.getExternalString(activity, NVStrings.NFC_BAC_DIALOG_DATE_OF_EXPIRY));
        editText.addTextChangedListener(new C5138f(editText, textView));
        ArrayList arrayList = new ArrayList(Arrays.asList(editText.getFilters()));
        arrayList.add(0, new AllCaps());
        arrayList.add(1, new C5133a());
        editText.setFilters((InputFilter[]) arrayList.toArray(new InputFilter[arrayList.size()]));
        editText2.addTextChangedListener(new C5138f(editText2, textView2));
        View findViewById = dialog.findViewById(C4414R.C4416id.bacEntryOkButton);
        editText2.addTextChangedListener(new C5137e(editText2, textView2, findViewById));
        editText2.setKeyListener(null);
        TagAccessSpec a = ((C5120v) getPresenter()).mo47228a();
        editText2.setOnClickListener(new C5136d(new OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Calendar instance = Calendar.getInstance();
                instance.set(1, i);
                instance.set(2, i2);
                instance.set(5, i3);
                editText2.setText(C5126w.this.m3826a(instance.getTime()));
            }
        }, a.getDateOfBirth()));
        editText3.addTextChangedListener(new C5138f(editText3, textView3));
        editText3.addTextChangedListener(new C5137e(editText3, textView3, findViewById));
        editText3.setKeyListener(null);
        editText3.setOnClickListener(new C5136d(new OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Calendar instance = Calendar.getInstance();
                instance.set(1, i);
                instance.set(2, i2);
                instance.set(5, i3);
                editText3.setText(C5126w.this.m3826a(instance.getTime()));
            }
        }, a.getDateOfExpiry()));
        editText.setText(a.getPassportNumber());
        editText2.setText(m3826a(a.getDateOfBirth()));
        editText3.setText(m3826a(a.getDateOfExpiry()));
        TextView textView4 = (TextView) dialog.findViewById(16908310);
        if (textView4 != null) {
            textView4.setSingleLine(false);
        }
        ((TextView) dialog.findViewById(C4414R.C4416id.content_description)).setText(NVStrings.getExternalString(getActivity(), NVStrings.NFC_BAC_DIALOG_TEXT));
        ((Button) findViewById).setText(NVStrings.getExternalString(getActivity(), NVStrings.BUTTON_RETRY));
        findViewById.setOnClickListener(new C5135c(editText, editText2, editText3, dialog));
        dialog.findViewById(C4414R.C4416id.bacEntryCancelButton).setOnClickListener(new C5134b(dialog));
        return dialog;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m3826a(Date date) {
        return DateFormat.getDateFormat(getActivity().getApplicationContext()).format(date);
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public void m3834g() {
        MetaInfo metaInfo = new MetaInfo();
        metaInfo.put("type", "NFC");
        JumioAnalytics.add(MobileEvents.pageView(JumioAnalytics.getSessionId(), Screen.SCAN, metaInfo));
    }
}
