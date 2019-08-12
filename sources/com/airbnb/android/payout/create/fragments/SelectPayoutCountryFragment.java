package com.airbnb.android.payout.create.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.views.CountryCodeSelectionView;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InputMarquee;

public class SelectPayoutCountryFragment extends AirFragment implements SelectionSheetOnItemClickedListener<CountryCodeItem> {
    private static final String ARG_DEFAULT_COUNTRY = "default_country";
    private CountrySelectedListener countryCodeSelectedListener;
    @BindView
    CountryCodeSelectionView countryCodeSelectionSheetPresenter;
    @BindView
    InputMarquee inputMarquee;
    private final SimpleTextWatcher onTextChangedListener = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            SelectPayoutCountryFragment.this.search(s.toString());
        }
    };
    @BindView
    AirToolbar toolbar;

    public interface CountrySelectedListener {
        void onBackPressed();

        void onCountrySelected(CountryCodeItem countryCodeItem);
    }

    public static SelectPayoutCountryFragment newInstanceWithDefaultCountry(String countryCode) {
        return (SelectPayoutCountryFragment) ((FragmentBundleBuilder) FragmentBundler.make(new SelectPayoutCountryFragment()).putString(ARG_DEFAULT_COUNTRY, countryCode)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CountrySelectedListener) {
            this.countryCodeSelectedListener = (CountrySelectedListener) context;
        } else if (getParentFragment() instanceof CountrySelectedListener) {
            this.countryCodeSelectedListener = (CountrySelectedListener) getParentFragment();
        } else {
            throw new IllegalStateException("Either activity or parent fragment should implement CountrySelectedListener");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_select_payout_country, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setNavigationOnClickListener(SelectPayoutCountryFragment$$Lambda$1.lambdaFactory$(this));
        setupInputMarquee();
        this.countryCodeSelectionSheetPresenter.setSelectionSheetOnItemClickedListener(this);
        this.countryCodeSelectionSheetPresenter.setSelectedCountryCode(getArguments().getString(ARG_DEFAULT_COUNTRY));
        return view;
    }

    private void setupInputMarquee() {
        OnEditorActionListener enterKeySearchListener = SelectPayoutCountryFragment$$Lambda$2.lambdaFactory$(this);
        this.inputMarquee.invertColors(true);
        this.inputMarquee.requestFocus();
        this.inputMarquee.setShowKeyboardOnFocus(true);
        this.inputMarquee.setHint(C7552R.string.hint_text_search_for_country);
        this.inputMarquee.setOnEditorActionListener(enterKeySearchListener);
        this.inputMarquee.addTextWatcher(this.onTextChangedListener);
        this.inputMarquee.setForSearch(true);
    }

    static /* synthetic */ boolean lambda$setupInputMarquee$1(SelectPayoutCountryFragment selectPayoutCountryFragment, TextView v, int actionId, KeyEvent event) {
        String searchQuery = v.getText().toString().trim();
        if (!KeyboardUtils.isEnterOrSearch(actionId, event)) {
            return false;
        }
        KeyboardUtils.dismissSoftKeyboard((View) v);
        selectPayoutCountryFragment.search(searchQuery);
        return true;
    }

    public void onItemClicked(CountryCodeItem item) {
        this.countryCodeSelectedListener.onCountrySelected(item);
        getFragmentManager().popBackStack();
    }

    /* access modifiers changed from: private */
    public void search(String query) {
        this.countryCodeSelectionSheetPresenter.setItemsFromSearchTerm(query.toString());
    }
}
