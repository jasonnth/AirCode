package com.jumio.p311nv.view.fragment;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.p000v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.p000v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.p002v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.analytics.Screen;
import com.jumio.analytics.UserAction;
import com.jumio.commons.utils.ImageProvider.FlagReceivedInterface;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.commons.view.SVGImageView;
import com.jumio.core.mvp.presenter.RequiresPresenter;
import com.jumio.core.util.Resource;
import com.jumio.p311nv.C4430R;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import com.jumio.p311nv.view.interactors.SelectionView;
import com.jumio.sdk.exception.JumioError;
import com.jumio.sdk.exception.JumioError.ErrorInterface;
import com.jumio.sdk.exception.JumioException;
import com.jumio.sdk.gui.MaterialProgressBar;
import com.jumio.sdk.models.CredentialsModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import jumio.p317nv.core.C4907ah;

@RequiresPresenter(C4907ah.class)
/* renamed from: com.jumio.nv.view.fragment.SelectionFragment */
public class SelectionFragment extends NVBaseFragment<C4907ah> implements INetverifyActivityCallback, SelectionView {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public ScrollView f3502a;

    /* renamed from: b */
    private TextView f3503b;

    /* renamed from: c */
    private TextView f3504c;

    /* renamed from: d */
    private ViewGroup f3505d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public ImageView f3506e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public LinearLayout f3507f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public LinearLayout f3508g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public View f3509h;
    protected Toolbar toolbar;
    protected TextView toolbarSubtitle;
    protected LinearLayout toolbarSubtitleContainer;

    /* renamed from: com.jumio.nv.view.fragment.SelectionFragment$a */
    class C4496a implements OnClickListener {
        private C4496a() {
        }

        public void onClick(View view) {
            view.setEnabled(false);
            JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN_OPTIONS, UserAction.CHOOSE_OTHER_COUNTRY_SELECTED));
            ((INetverifyFragmentCallback) SelectionFragment.this.callback).animateActionbar(false, false);
            ((INetverifyFragmentCallback) SelectionFragment.this.callback).startFragment(new CountryListFragment(), false, C4430R.animator.slide_up, C4430R.animator.slide_down);
            view.setEnabled(true);
        }
    }

    /* renamed from: com.jumio.nv.view.fragment.SelectionFragment$b */
    static class C4497b {

        /* renamed from: a */
        DocumentType f3517a;

        /* renamed from: b */
        NVDocumentVariant f3518b;

        /* renamed from: c */
        LinearLayout f3519c;

        public C4497b(DocumentType documentType, NVDocumentVariant nVDocumentVariant) {
            this.f3517a = documentType;
            this.f3518b = nVDocumentVariant;
        }

        /* renamed from: a */
        public DocumentType mo43690a() {
            return this.f3517a;
        }

        /* renamed from: b */
        public NVDocumentVariant mo43692b() {
            return this.f3518b;
        }

        /* renamed from: a */
        public void mo43691a(LinearLayout linearLayout) {
            this.f3519c = linearLayout;
        }

        /* renamed from: c */
        public LinearLayout mo43693c() {
            return this.f3519c;
        }
    }

    /* renamed from: com.jumio.nv.view.fragment.SelectionFragment$c */
    class C4498c implements OnClickListener {
        private C4498c() {
        }

        public void onClick(View view) {
            boolean z;
            int i = 0;
            final C4497b bVar = (C4497b) view.getTag();
            if (bVar.mo43693c() != null) {
                if (bVar.mo43693c().getVisibility() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN_OPTIONS, UserAction.DOCUMENT_SELECTED, bVar.mo43690a().getId().toString()));
                }
                for (int i2 = 0; i2 < SelectionFragment.this.f3507f.getChildCount(); i2++) {
                    View childAt = SelectionFragment.this.f3507f.getChildAt(i2);
                    if (childAt.getTag() == null || !(childAt.getTag() instanceof C4497b)) {
                        childAt.setVisibility(8);
                    }
                }
                LinearLayout c = bVar.mo43693c();
                if (z) {
                    i = 8;
                }
                c.setVisibility(i);
                if (bVar.mo43693c().getVisibility() == 0) {
                    SelectionFragment.this.f3502a.post(new Runnable() {
                        public void run() {
                            SelectionFragment.this.f3502a.scrollBy(0, bVar.mo43693c().getHeight());
                        }
                    });
                    return;
                }
                return;
            }
            JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN_OPTIONS, UserAction.DOCUMENT_SELECTED, bVar.mo43690a().getId().toString()));
            ((C4907ah) SelectionFragment.this.getPresenter()).mo46837a(bVar.mo43690a(), bVar.mo43692b());
        }
    }

    /* renamed from: com.jumio.nv.view.fragment.SelectionFragment$d */
    class C4500d implements ErrorInterface {

        /* renamed from: b */
        private JumioException f3524b;

        public C4500d(JumioException jumioException) {
            this.f3524b = jumioException;
        }

        public String getCaption() {
            return NVStrings.getExternalString(SelectionFragment.this.getContext(), NVStrings.BUTTON_CANCEL);
        }

        public void getAction() {
            ((C4907ah) SelectionFragment.this.getPresenter()).mo46838a(this.f3524b);
        }

        public int getColorID() {
            return C4430R.attr.netverify_dialogNegativeButtonTextColor;
        }
    }

    /* renamed from: com.jumio.nv.view.fragment.SelectionFragment$e */
    class C4501e implements ErrorInterface {
        private C4501e() {
        }

        public String getCaption() {
            return NVStrings.getExternalString(SelectionFragment.this.getContext(), NVStrings.BUTTON_RETRY);
        }

        public void getAction() {
            ((C4907ah) SelectionFragment.this.getPresenter()).mo46836a();
        }

        public int getColorID() {
            return C4430R.attr.netverify_dialogPositiveButtonTextColor;
        }
    }

    /* renamed from: com.jumio.nv.view.fragment.SelectionFragment$f */
    class C4502f implements OnClickListener {
        private C4502f() {
        }

        public void onClick(View view) {
            C4497b bVar = (C4497b) view.getTag();
            JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN_OPTIONS, UserAction.STYLE_SELECTED, bVar.mo43692b().toString()));
            ((C4907ah) SelectionFragment.this.getPresenter()).mo46837a(bVar.mo43690a(), bVar.mo43692b());
        }
    }

    public void handleOrientationChange(boolean z, boolean z2) {
        ((LayoutParams) getActivity().findViewById(C4430R.C4432id.selection_container).getLayoutParams()).topMargin = ScreenUtil.dpToPx(getActivity(), z ? 100 : 60);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((INetverifyFragmentCallback) this.callback).registerActivityCallback(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3502a = (ScrollView) layoutInflater.inflate(C4430R.layout.fragment_selection, viewGroup, false);
        TypedValue typedValue = new TypedValue();
        Theme theme = getContext().getTheme();
        this.f3503b = (TextView) this.f3502a.findViewById(C4430R.C4432id.scan_option_country_title);
        this.f3503b.setText(NVStrings.getExternalString(viewGroup.getContext(), NVStrings.SCAN_OPTIONS_COUNTRY_TITLE));
        this.f3505d = (ViewGroup) this.f3502a.findViewById(C4430R.C4432id.scan_option_country_selection);
        SVGImageView sVGImageView = (SVGImageView) this.f3505d.findViewById(C4430R.C4432id.item_scan_option_locked);
        theme.resolveAttribute(C4430R.attr.netverify_scanOptionsItemLockedIcon, typedValue, true);
        sVGImageView.setPaintColor(typedValue.data);
        this.f3504c = (TextView) this.f3502a.findViewById(C4430R.C4432id.scan_option_document_type_title);
        this.f3504c.setText(NVStrings.getExternalString(viewGroup.getContext(), NVStrings.SCAN_OPTIONS_DOCUMENT_TYPE_TITLE));
        this.f3507f = (LinearLayout) this.f3502a.findViewById(C4430R.C4432id.scan_options_document_selection);
        this.f3506e = (ImageView) this.f3502a.findViewById(C4430R.C4432id.scan_option_country_flag);
        this.toolbar = (Toolbar) getActivity().findViewById(C4430R.C4432id.toolbar);
        this.toolbarSubtitle = (TextView) getActivity().findViewById(C4430R.C4432id.toolbar_subtitle);
        this.toolbarSubtitleContainer = (LinearLayout) getActivity().findViewById(C4430R.C4432id.toolbarSubtitleContainer);
        this.f3509h = getActivity().findViewById(C4430R.C4432id.loadingBackground);
        this.f3509h.setVisibility(0);
        int dpToPx = ScreenUtil.dpToPx(getContext(), 190);
        int dpToPx2 = ScreenUtil.dpToPx(getContext(), 6);
        theme.resolveAttribute(C4430R.attr.netverify_scanOptionsLoadingProgress, typedValue, true);
        this.f3508g = (LinearLayout) getActivity().findViewById(C4430R.C4432id.spinnerContainer);
        this.f3508g.setVisibility(0);
        this.f3508g.setContentDescription(getResources().getString(C4430R.string.jumio_accessibility_loading));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpToPx, dpToPx);
        MaterialProgressBar materialProgressBar = new MaterialProgressBar(viewGroup.getContext());
        materialProgressBar.setLayoutParams(layoutParams);
        materialProgressBar.setCircleBackgroundEnabled(false);
        materialProgressBar.setColorSchemeColors(typedValue.data);
        materialProgressBar.setProgressStokeWidth(dpToPx2);
        this.f3508g.addView(materialProgressBar);
        this.f3502a.setAlpha(0.0f);
        return this.f3502a;
    }

    public void onResume() {
        super.onResume();
        if (this.f3509h.getTranslationY() != 0.0f) {
            ((INetverifyFragmentCallback) this.callback).animateActionbar(true, true);
            this.f3509h.setVisibility(8);
        } else {
            getActivity().findViewById(C4430R.C4432id.toolbarContainer).setVisibility(4);
            getActivity().findViewById(C4430R.C4432id.fragment_container).setVisibility(4);
        }
        setActionbarTitle(NVStrings.getExternalString(getContext(), NVStrings.SCAN_OPTIONS_TITLE));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        JumioAnalytics.add(MobileEvents.pageView(JumioAnalytics.getSessionId(), Screen.SCAN_OPTIONS, null));
    }

    public void onError(Throwable th) {
        if (th instanceof JumioException) {
            JumioError.getAlertDialogBuilder(getContext(), (JumioException) th, new C4501e(), new C4500d((JumioException) th));
        }
    }

    public CredentialsModel getCredentialsModel() {
        return ((INetverifyFragmentCallback) this.callback).getCredentials();
    }

    public void onCountriesReceived(List<Country> list, Country country, boolean z) {
        this.f3503b.setVisibility(0);
        this.f3505d.setVisibility(0);
        if (country != null) {
            m2027a(country.getName(), z, country.getIsoCode());
            return;
        }
        setActionbarSubTitle(NVStrings.getExternalString(getContext(), NVStrings.SCAN_OPTIONS_SUBTITLE_TYPE));
        m2027a(NVStrings.getExternalString(getContext(), NVStrings.SCAN_OPTIONS_SELECT_COUNTRY), true, null);
        this.f3504c.setVisibility(4);
    }

    public void onDocumentTypesReceived(List<DocumentType> list, NVDocumentVariant nVDocumentVariant) {
        Collections.sort(list);
        LayoutInflater from = LayoutInflater.from(getContext());
        this.f3504c.setVisibility(0);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                DocumentType documentType = (DocumentType) list.get(i2);
                View inflate = from.inflate(C4430R.layout.item_scan_option, null);
                C4497b bVar = new C4497b(documentType, nVDocumentVariant != null ? nVDocumentVariant : NVDocumentVariant.PLASTIC);
                inflate.setTag(bVar);
                inflate.setOnClickListener(new C4498c());
                inflate.setFilterTouchesWhenObscured(true);
                TextView textView = (TextView) inflate.findViewById(C4430R.C4432id.item_scan_option_title);
                SVGImageView sVGImageView = (SVGImageView) inflate.findViewById(C4430R.C4432id.item_scan_option_icon);
                textView.setText(documentType.getId().getLocalizedName(getContext()));
                textView.setVisibility(0);
                NVDocumentType id = documentType.getId();
                Stack stack = new Stack();
                switch (id) {
                    case PASSPORT:
                        stack.add("M60,0 C26.8606452,0 0,26.8606452 0,60 C0,93.1393548 26.8606452,120 60,120 C93.1393548,120 120,93.1393548 120,60 C120,26.8606452 93.1393548,0 60,0 L60,0 Z M112.16129,58.0645161 L88.9509677,58.0645161 C88.7380645,49.4903226 87.2129032,41.2993548 84.5070968,33.8012903 C89.7329032,31.6219355 94.563871,28.7225806 98.9690323,25.2967742 C106.8,34.0877419 111.696774,45.5032258 112.16129,58.0645161 L112.16129,58.0645161 Z M57.8593548,112.149677 C51.4258065,106.792258 46.0296774,99.836129 42.0812903,91.7574194 C47.1483871,90.1741935 52.5174194,89.3070968 58.0645161,89.1290323 L58.0645161,112.157419 C57.9948387,112.157419 57.9290323,112.153548 57.8593548,112.149677 L57.8593548,112.149677 Z M62.1329032,7.85032258 C69.4335484,13.9277419 75.3909677,22.0683871 79.4206452,31.563871 C73.9006452,33.4490323 68.036129,34.5445161 61.9316129,34.7380645 L61.9316129,7.83870968 C62.0051613,7.84258065 62.0670968,7.84645161 62.1329032,7.85032258 L62.1329032,7.85032258 Z M68.5819355,8.5083871 C79.2658065,10.2851613 88.8503226,15.2748387 96.3135484,22.4980645 C92.2606452,25.6064516 87.8322581,28.2387097 83.043871,30.2322581 C79.5212903,21.8825806 74.5780645,14.5006452 68.5819355,8.5083871 L68.5819355,8.5083871 Z M58.0645161,7.83870968 L58.0645161,34.7380645 C51.96,34.5406452 46.0916129,33.4490323 40.5754839,31.563871 C44.6051613,22.0683871 50.5625806,13.9277419 57.8632258,7.85032258 C57.9329032,7.84645161 57.9948387,7.84258065 58.0645161,7.83870968 L58.0645161,7.83870968 Z M36.9522581,30.236129 C32.1716129,28.2425806 27.7393548,25.6103226 23.6864516,22.5019355 C31.1458065,15.2787097 40.7341935,10.2890323 51.4180645,8.51225806 C45.4180645,14.5006452 40.4787097,21.8825806 36.9522581,30.236129 L36.9522581,30.236129 Z M39.116129,35.1329032 C45.0851613,37.203871 51.4451613,38.4116129 58.0645161,38.6129032 L58.0645161,58.0645161 L34.92,58.0645161 C35.1290323,49.9587097 36.5651613,42.2129032 39.116129,35.1329032 L39.116129,35.1329032 Z M58.0645161,61.9354839 L58.0645161,85.2580645 C51.96,85.443871 46.0567742,86.4425806 40.4941935,88.2348387 C37.1419355,80.2529032 35.163871,71.3612903 34.92,61.9354839 L58.0645161,61.9354839 L58.0645161,61.9354839 Z M51.4180645,111.491613 C41.8451613,109.900645 33.1509677,105.731613 26.0825806,99.6774194 C29.8954839,97.0335484 34.0180645,94.796129 38.4232258,93.0967742 C41.7987097,100.095484 46.2154839,106.308387 51.4180645,111.491613 L51.4180645,111.491613 Z M61.9354839,112.16129 L61.9354839,89.1329032 C67.4787097,89.3109677 72.8516129,90.1780645 77.9187097,91.7612903 C73.9664516,99.84 68.5741935,106.796129 62.1406452,112.153548 C62.0748387,112.153548 62.0051613,112.157419 61.9354839,112.16129 L61.9354839,112.16129 Z M81.5767742,93.0967742 C85.9780645,94.796129 90.0967742,97.0374194 93.9174194,99.6774194 C86.8490323,105.727742 78.1548387,109.900645 68.5819355,111.491613 C73.7845161,106.308387 78.1974194,100.095484 81.5767742,93.0967742 L81.5767742,93.0967742 Z M79.5058065,88.2348387 C73.9393548,86.4425806 68.036129,85.443871 61.9354839,85.2580645 L61.9354839,61.9354839 L85.08,61.9354839 C84.836129,71.3612903 82.8541935,80.2529032 79.5058065,88.2348387 L79.5058065,88.2348387 Z M61.9354839,58.0645161 L61.9354839,38.6129032 C68.5509677,38.4116129 74.9109677,37.2077419 80.883871,35.1367742 C83.4309677,42.2167742 84.8670968,49.9625806 85.08,58.0683871 L61.9354839,58.0683871 L61.9354839,58.0645161 Z M21.0348387,25.2967742 C25.44,28.7225806 30.2709677,31.6219355 35.4929032,33.8012903 C32.7870968,41.2993548 31.2619355,49.4903226 31.0490323,58.0645161 L7.83870968,58.0645161 C8.30322581,45.5032258 13.2,34.0877419 21.0348387,25.2967742 L21.0348387,25.2967742 Z M7.83870968,61.9354839 L31.0490323,61.9354839 C31.2890323,71.8258065 33.356129,81.1664516 36.8670968,89.5625806 C31.9625806,91.4748387 27.3909677,94.0219355 23.1716129,97.0374194 C14.0825806,87.9909677 8.34580645,75.6232258 7.83870968,61.9354839 L7.83870968,61.9354839 Z M96.8283871,97.0335484 C92.6090323,94.0219355 88.0374194,91.4709677 83.1290323,89.5587097 C86.643871,81.1625806 88.7070968,71.8219355 88.9509677,61.9316129 L112.16129,61.9316129 C111.654194,75.6232258 105.913548,87.9909677 96.8283871,97.0335484 L96.8283871,97.0335484 Z");
                        break;
                    case DRIVER_LICENSE:
                        stack.add("M7905 11383 c-309 -66 -553 -304 -637 -623 l-23 -85 0 -1805 0 -1805 23 -85 c65 -247 224 -442 447 -550 195 -94 -115 -85 2905 -85 3021 0 2711 -9 2905 85 264 129 431 368 465 667 8 69 10 606 8 1843 l-3 1745 -23 80 c-91 323 -334 555 -648 620 -64 13 -401 15 -2711 14 -2302 -1 -2647 -3 -2708 -16z m5228 -846 c26 -15 60 -47 75 -69 23 -36 27 -51 27 -118 0 -65 -4 -82 -25 -113 -14 -21 -43 -50 -64 -65 l-39 -27 -1326 -3 c-890 -2 -1338 0 -1362 7 -19 6 -52 27 -72 47 -123 123 -68 323 99 363 23 5 568 9 1339 8 l1300 -2 48 -28z m-25 -833 c147 -61 178 -261 56 -362 -22 -18 -55 -37 -74 -42 -53 -15 -2611 -13 -2661 1 -185 56 -201 318 -24 400 38 18 96 19 1353 19 1164 0 1317 -2 1350 -16z m-3706 -833 c182 -24 329 -160 367 -339 15 -72 15 -941 0 -1014 -31 -147 -147 -275 -292 -320 -54 -16 -103 -18 -547 -18 -462 0 -491 1 -554 20 -141 44 -253 169 -285 318 -7 35 -11 211 -11 515 0 512 2 534 63 636 62 103 188 186 306 201 81 11 873 11 953 1z");
                        stack.add("M2422 9580 c-187 -49 -331 -190 -398 -390 -18 -53 -19 -142 -22 -2350 l-2 -2295 590 -1700 c354 -1021 608 -1736 634 -1790 106 -215 308 -375 551 -437 l90 -23 3210 0 3210 0 84 23 c253 70 442 222 553 444 17 34 298 832 624 1772 l594 1709 0 644 0 643 -564 0 -564 0 -17 -77 c-21 -94 -79 -223 -139 -306 -297 -416 -899 -472 -1269 -118 -129 123 -216 278 -247 438 l-13 63 -1151 2 -1151 3 -58 27 c-80 38 -161 117 -200 197 l-32 66 -3 1173 -2 1172 -1519 0 -1519 0 -4 333 c-5 363 -7 376 -71 502 -36 73 -150 189 -224 228 -113 60 -171 67 -552 66 -308 0 -354 -2 -419 -19z m1763 -2815 c90 -24 252 -107 323 -165 191 -157 311 -409 312 -656 1 -399 -269 -735 -666 -830 -102 -24 -287 -22 -389 5 -306 81 -535 310 -616 616 -30 115 -31 299 -1 412 88 330 340 565 677 632 70 14 288 5 360 -14z m6411 -4062 l-423 -1268 -3103 0 -3103 0 -423 1268 -422 1267 3948 0 3948 0 -422 -1267z");
                        break;
                    case VISA:
                        stack.add("M60,0 C26.8606452,0 0,26.8606452 0,60 C0,93.1393548 26.8606452,120 60,120 C93.1393548,120 120,93.1393548 120,60 C120,26.8606452 93.1393548,0 60,0 Z M94.9999995,44.0132049 C92.9999995,44.0132049 88.5,44.0132049 87.3692437,44.0132047 C86.6484694,40.4939206 85.690748,37.0813081 84.5070968,33.8012903 C89.7329032,31.6219355 94.563871,28.7225806 98.9690323,25.2967742 C103.754096,30.6684402 107.443607,37.0200335 109.695785,44.0141893 C106.5,44.014189 94.9999995,44.0132049 94.9999995,44.0132049 Z M57.8593548,112.149677 C51.4258065,106.792258 46.0296774,99.836129 42.0812903,91.7574194 C47.1483871,90.1741935 52.5174194,89.3070968 58.0645161,89.1290323 L58.0645161,112.157419 C57.9948387,112.157419 57.9290323,112.153548 57.8593548,112.149677 Z M62.1329032,7.85032258 C69.4335484,13.9277419 75.3909677,22.0683871 79.4206452,31.563871 C73.9006452,33.4490323 68.036129,34.5445161 61.9316129,34.7380645 L61.9316129,7.83870968 C62.0051613,7.84258065 62.0670968,7.84645161 62.1329032,7.85032258 Z M68.5819355,8.5083871 C79.2658065,10.2851613 88.8503226,15.2748387 96.3135484,22.4980645 C92.2606452,25.6064516 87.8322581,28.2387097 83.043871,30.2322581 C79.5212903,21.8825806 74.5780645,14.5006452 68.5819355,8.5083871 Z M58.0645161,7.83870968 L58.0645161,34.7380645 C51.96,34.5406452 46.0916129,33.4490323 40.5754839,31.563871 C44.6051613,22.0683871 50.5625806,13.9277419 57.8632258,7.85032258 C57.9329032,7.84645161 57.9948387,7.84258065 58.0645161,7.83870968 Z M36.9522581,30.236129 C32.1716129,28.2425806 27.7393548,25.6103226 23.6864516,22.5019355 C31.1458065,15.2787097 40.7341935,10.2890323 51.4180645,8.51225806 C45.4180645,14.5006452 40.4787097,21.8825806 36.9522581,30.236129 Z M58.0645161,38.6129032 L58.0645161,44 C52.5000005,43.9722005 42.5000001,43.9722005 36.5878978,43.9722003 C37.2457038,40.9314559 38.0908768,37.9784059 39.116129,35.1329032 C45.0851613,37.203871 51.4451613,38.4116129 58.0645161,38.6129032 Z M58.0645161,74 L58.0645161,85.2580645 C51.96,85.443871 46.0567742,86.4425806 40.4941935,88.2348387 C38.6084449,83.7447536 37.1575474,78.9668093 36.2055839,73.9678449 C38.9999999,73.9678449 53.5000003,73.9678449 58.0645161,74 Z M51.4180645,111.491613 C41.8451613,109.900645 33.1509677,105.731613 26.0825806,99.6774194 C29.8954839,97.0335484 34.0180645,94.796129 38.4232258,93.0967742 C41.7987097,100.095484 46.2154839,106.308387 51.4180645,111.491613 Z M61.9354839,112.16129 L61.9354839,89.1329032 C67.4787097,89.3109677 72.8516129,90.1780645 77.9187097,91.7612903 C73.9664516,99.84 68.5741935,106.796129 62.1406452,112.153548 C62.0748387,112.153548 62.0051613,112.157419 61.9354839,112.16129 Z M81.5767742,93.0967742 C85.9780645,94.796129 90.0967742,97.0374194 93.9174194,99.6774194 C86.8490323,105.727742 78.1548387,109.900645 68.5819355,111.491613 C73.7845161,106.308387 78.1974194,100.095484 81.5767742,93.0967742 Z M61.9354839,85.2580645 L61.9354839,74 C66.4999999,73.980732 75.9999999,73.980732 83.7907089,73.980732 C82.8380529,78.975021 81.3877589,83.7486122 79.5058065,88.2348387 C73.9393548,86.4425806 68.036129,85.443871 61.9354839,85.2580645 Z M61.9354839,44 L61.9354839,38.6129032 C68.5509677,38.4116129 74.9109677,37.2077419 80.883871,35.1367742 C81.9065656,37.9794922 82.7501573,40.929547 83.4073798,43.9671441 C79.4999999,43.9671441 66.4999999,43.9671441 61.9354839,44 Z M35.4929032,33.8012903 C34.313072,37.0707225 33.3577146,40.471895 32.6377421,43.9791348 C31.049033,43.9791339 15.9999998,43.9791339 14.9999998,43.9791339 C14.9999998,43.9791339 12,43.9399837 10.3284037,43.9399832 C12.5831036,36.9751054 16.264346,30.6494367 21.0348387,25.2967742 C25.44,28.7225806 30.2709677,31.6219355 35.4929032,33.8012903 Z M25.0000008,73.9628782 C26.0000008,73.9628782 31.5000008,73.9628782 32.2691847,73.9628778 C33.258361,79.4325404 34.8164506,84.6586649 36.8670968,89.5625806 C31.9625806,91.4748387 27.3909677,94.0219355 23.1716129,97.0374194 C16.8470199,90.7424561 12.1456093,82.8393052 9.69994748,74.0022694 C11,74.0022695 25.0000008,73.9628782 25.0000008,73.9628782 Z M83.1290323,89.5587097 C85.1775424,84.6652973 86.7329738,79.4510551 87.7225248,73.994149 C89.0000001,73.9941491 105,73.9941491 106,73.9941491 C106,73.9941491 109,73.942367 110.315459,73.942367 C107.873486,82.8040831 103.164229,90.7274015 96.8283871,97.0335484 C92.6090323,94.0219355 88.0374194,91.4709677 83.1290323,89.5587097 Z M32.5063373,65.0481283 L37.427927,47.3342246 L44.0805585,47.3342246 L35.7647691,71.6657754 L29.2479055,71.6657754 L21,47.3342246 L27.6017186,47.3342246 L32.5063373,65.0481283 Z M52.226638,71.6657754 L46.2867884,71.6657754 L46.2867884,47.3342246 L52.226638,47.3342246 L52.226638,71.6657754 Z M69.112782,65.1985294 C69.112782,64.340682 68.8044786,63.6722395 68.1878625,63.1931818 C67.5712465,62.7141242 66.4879419,62.2183626 64.9379162,61.7058824 C63.3878906,61.1934021 62.1207353,60.6976405 61.1364125,60.2185829 C57.9345347,58.6700012 56.3336198,56.5421258 56.3336198,53.834893 C56.3336198,52.4868471 56.7324343,51.2975765 57.5300752,50.2670455 C58.3277161,49.2365145 59.4562763,48.4343834 60.9157895,47.8606283 C62.3753027,47.2868732 64.0158162,47 65.8373792,47 C67.6136859,47 69.2061154,47.3119398 70.6147154,47.9358289 C72.0233153,48.5597179 73.1179338,49.4481894 73.8986037,50.6012701 C74.6792735,51.7543507 75.0696026,53.0717395 75.0696026,54.5534759 L69.129753,54.5534759 C69.129753,53.561938 68.8214496,52.7932291 68.2048335,52.2473262 C67.5882175,51.7014233 66.7538184,51.4284759 65.7016112,51.4284759 C64.6380899,51.4284759 63.7980339,51.6596456 63.1814178,52.121992 C62.5648018,52.5843383 62.2564984,53.1720107 62.2564984,53.8850267 C62.2564984,54.5089158 62.595915,55.0743067 63.2747583,55.5812166 C63.9536017,56.0881264 65.1472167,56.6117397 66.8556391,57.1520722 C68.5640615,57.6924047 69.9669834,58.2745067 71.0644468,58.8983957 C73.734564,60.4135548 75.0696026,62.5024377 75.0696026,65.165107 C75.0696026,67.2930142 74.2550028,68.9641205 72.6257787,70.1784759 C70.9965547,71.3928314 68.7620622,72 65.9222342,72 C63.9196463,72 62.106596,71.6462825 60.483029,70.9388369 C58.859462,70.2313913 57.6375623,69.2621496 56.8172932,68.0310829 C55.9970242,66.8000161 55.5868958,65.382361 55.5868958,63.7780749 L61.5606874,63.7780749 C61.5606874,65.0815573 61.9029325,66.0424434 62.5874329,66.660762 C63.2719332,67.2790806 64.3835226,67.5882353 65.9222342,67.5882353 C66.906557,67.5882353 67.6843867,67.379347 68.2557465,66.9615642 C68.8271063,66.5437813 69.112782,65.956109 69.112782,65.1985294 Z M92.1593985,67.1203209 L83.996348,67.1203209 L82.5707841,71.6657754 L76.2066595,71.6657754 L85.2691729,47.3342246 L90.8696026,47.3342246 L100,71.6657754 L93.6019334,71.6657754 L92.1593985,67.1203209 Z M85.4219119,62.5915775 L90.7338346,62.5915775 L88.0693878,54.1524064 L85.4219119,62.5915775 Z");
                        break;
                    case IDENTITY_CARD:
                        stack.add("M100.707393,62.1549296 C101.168461,59.4066889 101.408451,56.5834835 101.408451,53.7042254 C101.408451,25.7010556 78.7073951,3 50.7042254,3 C22.7010556,3 0,25.7010556 0,53.7042254 C0,80.571524 20.8967944,102.558116 47.3239437,104.297562 L47.3239437,70.6056338 C47.3239437,65.9387324 51.1077465,62.1549296 55.7746479,62.1549296 L100.707393,62.1549296 Z M47.3239437,98.95452 C35.3978007,98.1270022 24.6546897,92.9261255 17.2080947,84.3015193 C17.2080947,79.056338 41.3639733,79.056338 41.3639733,75.0533729 L41.3639733,64.3787991 C40.0296516,64.3787991 36.0266864,55.0385471 36.0266864,53.7042254 C34.7503317,53.7042254 32.0237213,41.6953299 34.6923647,41.6953299 C32.0237213,23.0148258 38.6953299,19.0118606 50.7042254,17.6775389 C65.3817589,19.0118606 70.0518903,23.0148258 68.0504077,41.6953299 C70.7190511,41.6953299 66.7350907,53.7042254 65.3817616,53.7042254 C65.3817598,54.6197672 64.1253677,59.3044913 62.9056889,62.1549296 L55.7746479,62.1549296 C51.1077465,62.1549296 47.3239437,65.9387324 47.3239437,70.6056338 L47.3239437,98.95452 Z M60.8450704,67.2253521 C56.178169,67.2253521 52.3943662,71.0091549 52.3943662,75.6760563 L52.3943662,109.478873 C52.3943662,114.145775 56.178169,117.929577 60.8450704,117.929577 L111.549296,117.929577 C116.216197,117.929577 120,114.145775 120,109.478873 L120,75.6760563 C120,71.0091549 116.216197,67.2253521 111.549296,67.2253521 L60.8450704,67.2253521 Z M77.7464789,88.3521127 C77.7464789,90.684507 75.8535211,92.5774648 73.5211268,92.5774648 L65.0704225,92.5774648 C62.7380282,92.5774648 60.8450704,90.684507 60.8450704,88.3521127 L60.8450704,79.9014085 C60.8450704,77.5690141 62.7380282,75.6760563 65.0704225,75.6760563 L73.5211268,75.6760563 C75.8535211,75.6760563 77.7464789,77.5690141 77.7464789,79.9014085 L77.7464789,88.3521127 Z M110.28169,101.028169 L84.9295775,101.028169 C83.7612676,101.028169 82.8169014,100.083803 82.8169014,98.915493 C82.8169014,97.7492958 83.7612676,96.8028169 84.9295775,96.8028169 L110.28169,96.8028169 C111.45,96.8028169 112.394366,97.7492958 112.394366,98.915493 C112.394366,100.083803 111.45,101.028169 110.28169,101.028169 Z M110.28169,109.478873 L84.9295775,109.478873 C83.7612676,109.478873 82.8169014,108.534507 82.8169014,107.366197 C82.8169014,106.197887 83.7612676,105.253521 84.9295775,105.253521 L110.28169,105.253521 C111.45,105.253521 112.394366,106.197887 112.394366,107.366197 C112.394366,108.534507 111.45,109.478873 110.28169,109.478873 Z");
                        break;
                }
                TypedValue typedValue = new TypedValue();
                getActivity().getTheme().resolveAttribute(C4430R.attr.netverify_scanOptionsItemIcon, typedValue, true);
                sVGImageView.setRotation((float) 0);
                sVGImageView.setPaintColor(typedValue.data);
                sVGImageView.setPathStringStack(stack);
                this.f3507f.addView(inflate);
                ArrayList arrayList = new ArrayList();
                if (nVDocumentVariant == null) {
                    arrayList.add(NVDocumentVariant.PLASTIC);
                    if (documentType.hasPaperVariant()) {
                        arrayList.add(NVDocumentVariant.PAPER);
                    }
                }
                if (arrayList.size() > 1) {
                    LinearLayout linearLayout = new LinearLayout(getContext());
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    linearLayout.setVisibility(8);
                    linearLayout.setOrientation(1);
                    this.f3507f.addView(linearLayout);
                    bVar.mo43691a(linearLayout);
                    int i3 = 0;
                    while (true) {
                        int i4 = i3;
                        if (i4 < arrayList.size()) {
                            NVDocumentVariant nVDocumentVariant2 = (NVDocumentVariant) arrayList.get(i4);
                            View inflate2 = from.inflate(C4430R.layout.item_scan_option_sub, null);
                            inflate2.setTag(new C4497b(documentType, nVDocumentVariant2));
                            inflate2.setOnClickListener(new C4502f());
                            inflate2.setFilterTouchesWhenObscured(true);
                            ((TextView) inflate2.findViewById(C4430R.C4432id.label)).setText(NVStrings.getExternalString(getContext(), nVDocumentVariant2 == NVDocumentVariant.PAPER ? NVStrings.DOCUMENT_VARIANT_PAPER : NVStrings.DOCUMENT_VARIANT_PLASTIC));
                            linearLayout.addView(inflate2);
                            i3 = i4 + 1;
                        }
                    }
                }
                i = i2 + 1;
            } else {
                this.f3504c.setVisibility(0);
                this.f3507f.setVisibility(0);
                if (list.size() > 0) {
                    setActionbarSubTitle(NVStrings.getExternalString(getContext(), NVStrings.SCAN_OPTIONS_SUBTITLE_TYPE));
                    return;
                }
                return;
            }
        }
    }

    public void jumpToScanFragment(boolean z) {
        int i;
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f3509h, "translationY", new float[]{0.0f, (float) (-this.f3509h.getHeight())});
            ofFloat.setStartDelay(150);
            ofFloat.setDuration(300);
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f3508g, "alpha", new float[]{1.0f, 0.0f});
            ofFloat2.setDuration(300);
            ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
            animatorSet.play(ofFloat).after(ofFloat2);
            animatorSet.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    SelectionFragment.this.f3502a.setVisibility(4);
                    SelectionFragment.this.toolbarSubtitleContainer.setVisibility(0);
                    SelectionFragment.this.getActivity().findViewById(C4430R.C4432id.toolbarContainer).setVisibility(0);
                    SelectionFragment.this.getActivity().findViewById(C4430R.C4432id.fragment_container).setVisibility(0);
                }

                public void onAnimationEnd(Animator animator) {
                    SelectionFragment.this.f3509h.setVisibility(8);
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
            animatorSet.start();
            i = 0;
        } else {
            ((INetverifyFragmentCallback) this.callback).animateActionbar(false, false);
            i = C4430R.animator.fade_out;
        }
        ((INetverifyFragmentCallback) this.callback).startFragment(new NVScanFragment(), z, 0, i);
    }

    public void hideLoading(final boolean z) {
        this.f3509h.post(new Runnable() {
            public void run() {
                Animator loadAnimator = AnimatorInflater.loadAnimator(SelectionFragment.this.getContext(), C4430R.animator.slide_up);
                loadAnimator.setTarget(SelectionFragment.this.f3502a);
                AnimatorSet animatorSet = new AnimatorSet();
                Builder play = animatorSet.play(loadAnimator);
                if (SelectionFragment.this.f3509h.getTranslationY() == 0.0f) {
                    if (z) {
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(SelectionFragment.this.f3509h, "translationY", new float[]{0.0f, (float) (-SelectionFragment.this.f3509h.getHeight())});
                        ofFloat.setStartDelay(150);
                        ofFloat.setDuration(450);
                        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
                        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(SelectionFragment.this.f3508g, "alpha", new float[]{1.0f, 0.0f});
                        ofFloat2.setDuration(300);
                        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
                        play.with(ofFloat).with(ofFloat2);
                    } else {
                        SelectionFragment.this.f3509h.setTranslationY((float) (-SelectionFragment.this.f3509h.getHeight()));
                        SelectionFragment.this.f3508g.setAlpha(0.0f);
                    }
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(SelectionFragment.this.toolbar, "alpha", new float[]{0.0f, 1.0f});
                    ofFloat3.setDuration(300);
                    ofFloat3.setInterpolator(new AccelerateDecelerateInterpolator());
                    ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(SelectionFragment.this.toolbarSubtitle, "alpha", new float[]{0.0f, 1.0f});
                    ofFloat4.setStartDelay(150);
                    ofFloat4.setDuration(300);
                    ofFloat4.setInterpolator(new AccelerateDecelerateInterpolator());
                    play.before(ofFloat3).before(ofFloat4);
                    animatorSet.addListener(new AnimatorListener() {
                        public void onAnimationStart(Animator animator) {
                            SelectionFragment.this.toolbarSubtitleContainer.setVisibility(0);
                            SelectionFragment.this.getActivity().findViewById(C4430R.C4432id.toolbarContainer).setVisibility(0);
                            SelectionFragment.this.getActivity().findViewById(C4430R.C4432id.fragment_container).setVisibility(0);
                        }

                        public void onAnimationEnd(Animator animator) {
                            SelectionFragment.this.f3509h.setVisibility(8);
                        }

                        public void onAnimationCancel(Animator animator) {
                        }

                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                }
                animatorSet.start();
            }
        });
    }

    /* renamed from: a */
    private void m2027a(String str, boolean z, String str2) {
        TextView textView = (TextView) this.f3505d.findViewById(C4430R.C4432id.item_scan_option_title);
        TextView textView2 = (TextView) this.f3505d.findViewById(C4430R.C4432id.item_scan_option_sub_title);
        textView2.setText(NVStrings.getExternalString(getContext(), NVStrings.SCAN_OPTIONS_PRESELECTED_HINT));
        SVGImageView sVGImageView = (SVGImageView) this.f3505d.findViewById(C4430R.C4432id.item_scan_option_locked);
        if (str2 != null) {
            Resource.getCountryFlag(str2, null, new FlagReceivedInterface() {
                public void onFlagReceived(Bitmap bitmap) {
                    if (SelectionFragment.this.isAdded()) {
                        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(SelectionFragment.this.getResources(), bitmap);
                        create.setCircular(true);
                        SelectionFragment.this.f3506e.setAlpha(0.0f);
                        SelectionFragment.this.f3506e.setImageDrawable(create);
                        SelectionFragment.this.f3506e.animate().alpha(1.0f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(100).start();
                    }
                }
            });
        }
        if (z) {
            this.f3505d.setOnClickListener(new C4496a());
            this.f3505d.setFilterTouchesWhenObscured(true);
            textView2.setVisibility(8);
            sVGImageView.setVisibility(8);
            textView.setText(str);
            textView.setPadding(0, 0, ScreenUtil.dpToPx(getContext(), 16), 0);
            ((RelativeLayout.LayoutParams) textView.getLayoutParams()).addRule(15, -1);
            ((RelativeLayout.LayoutParams) textView.getLayoutParams()).setMargins(0, 0, 0, 0);
            return;
        }
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(C4430R.attr.netverify_scanOptionsItemLockedIcon, typedValue, true);
        this.f3505d.setOnClickListener(null);
        this.f3505d.setFilterTouchesWhenObscured(true);
        textView2.setVisibility(0);
        sVGImageView.setPaintColor(typedValue.data);
        sVGImageView.setPathString("M24,48 C27.3137085,48 30,45.3137085 30,42 C30,38.67 27.3,36 24,36 C20.6862915,36 18,38.6862915 18,42 C18,45.3137085 20.6862915,48 24,48 L24,48 Z M42,21 C45.3137085,21 48,23.6862915 48,27 L48,57 C48,60.3137085 45.3137085,63 42,63 L6,63 C2.6862915,63 0,60.3137085 0,57 L0,27 C0,23.67 2.7,21 6,21 L9,21 L9,15 C9,6.71572875 15.7157288,1.99840144e-15 24,0 C27.9782473,3.33066907e-16 31.793556,1.58035261 34.6066017,4.39339828 C37.4196474,7.20644396 39,11.0217527 39,15 L39,21 L42,21 L42,21 Z M24,6 C19.0294373,6 15,10.0294373 15,15 L15,21 L33,21 L33,15 C33,10.0294373 28.9705627,6 24,6 L24,6 Z");
        sVGImageView.setVisibility(0);
        textView.setText(str);
        textView.setPadding(0, 0, 0, 0);
        ((RelativeLayout.LayoutParams) textView.getLayoutParams()).addRule(15, 0);
        ((RelativeLayout.LayoutParams) textView.getLayoutParams()).setMargins(0, ScreenUtil.dpToPx(getContext(), 6), 0, 0);
    }
}
