package com.jumio.p311nv.view.fragment;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.p002v7.widget.C0289SearchView;
import android.support.p002v7.widget.C0289SearchView.OnQueryTextListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.analytics.Screen;
import com.jumio.analytics.UserAction;
import com.jumio.commons.view.CompatibilityLayer;
import com.jumio.core.mvp.presenter.RequiresPresenter;
import com.jumio.p311nv.C4430R;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.p311nv.view.interactors.CountryListView;
import jumio.p317nv.core.C4898af;
import jumio.p317nv.core.C4942t;
import jumio.p317nv.core.C4942t.C4943a;

@RequiresPresenter(C4898af.class)
/* renamed from: com.jumio.nv.view.fragment.CountryListFragment */
public class CountryListFragment extends NVBaseFragment<C4898af> implements OnQueryTextListener, OnItemClickListener, INetverifyActivityCallback, CountryListView {

    /* renamed from: a */
    private ListView f3470a;

    /* renamed from: b */
    private TextView f3471b;

    /* renamed from: c */
    private C4942t f3472c;

    /* renamed from: d */
    private MenuItem f3473d;

    /* renamed from: e */
    private C0289SearchView f3474e;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((INetverifyFragmentCallback) this.callback).registerActivityCallback(this);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4430R.layout.fragment_countrylist, viewGroup, false);
        this.f3471b = (TextView) inflate.findViewById(16908292);
        this.f3470a = (ListView) inflate.findViewById(C4430R.C4432id.countryListView);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        ((INetverifyFragmentCallback) this.callback).animateActionbar(true, false);
        setActionbarTitle(NVStrings.getExternalString(getActivity(), NVStrings.COUNTRYLIST_TITLE));
    }

    public void handleOrientationChange(boolean z, boolean z2) {
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f3472c = new C4942t(((C4898af) getPresenter()).mo46812a());
        this.f3471b.setText(NVStrings.getExternalString(getActivity(), NVStrings.COUNTRYLIST_EMPTY));
        this.f3470a.setEmptyView(this.f3471b);
        this.f3470a.setAdapter(this.f3472c);
        this.f3470a.setOnItemClickListener(this);
        this.f3470a.setFastScrollEnabled(true);
        this.f3470a.setFilterTouchesWhenObscured(true);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        JumioAnalytics.add(MobileEvents.pageView(JumioAnalytics.getSessionId(), Screen.COUNTRY_LIST, null));
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        this.f3474e = new C0289SearchView(getActivity());
        this.f3474e.setFilterTouchesWhenObscured(true);
        this.f3474e.setOnQueryTextListener(this);
        this.f3474e.setOnSearchClickListener(new OnClickListener() {
            public void onClick(View view) {
                JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.COUNTRY_LIST, UserAction.SEARCH_STARTED));
            }
        });
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(C4430R.attr.colorControlNormal, typedValue, true);
        Drawable drawable = CompatibilityLayer.getDrawable(getResources(), C4430R.C4431drawable.abc_ic_search_api_mtrl_alpha);
        drawable.setColorFilter(typedValue.data, Mode.SRC_ATOP);
        this.f3473d = menu.add(0, 1, 1, NVStrings.getExternalString(getActivity(), NVStrings.SEARCH));
        this.f3473d.setIcon(drawable);
        this.f3473d.setActionView(this.f3474e);
        this.f3473d.setShowAsAction(9);
    }

    public boolean onQueryTextSubmit(String str) {
        if (this.f3472c != null) {
            this.f3472c.mo46888a(str);
        }
        return false;
    }

    public boolean onQueryTextChange(String str) {
        if (this.f3472c != null) {
            this.f3472c.mo46888a(str);
        }
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C4943a aVar = (C4943a) view.getTag();
        JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.COUNTRY_LIST, UserAction.COUNTRY_SELECTED, aVar.f4835a.getIsoCode()));
        ((C4898af) getPresenter()).mo46813a(aVar.f4835a);
        m2010a();
        ((INetverifyFragmentCallback) this.callback).closeFragment(C4430R.animator.slide_up, C4430R.animator.slide_down);
    }

    public boolean onBackButtonPressed() {
        if (this.f3473d == null || !this.f3473d.isActionViewExpanded()) {
            ((INetverifyFragmentCallback) this.callback).closeFragment(C4430R.animator.slide_up, C4430R.animator.slide_down);
        } else {
            this.f3473d.collapseActionView();
            this.f3474e.setQuery("", false);
        }
        return true;
    }

    public boolean onHomeButtonPressed() {
        return false;
    }

    /* renamed from: a */
    private void m2010a() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public void onError(Throwable th) {
    }
}
