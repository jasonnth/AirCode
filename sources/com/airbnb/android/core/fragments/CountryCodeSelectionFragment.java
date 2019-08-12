package com.airbnb.android.core.fragments;

import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.views.CountryCodeSelectionView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;
import com.airbnb.p027n2.collections.BaseSelectionView.Style;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class CountryCodeSelectionFragment extends AirFragment implements SelectionSheetOnItemClickedListener<CountryCodeItem> {
    private static final String ARG_STYLE = "style";
    private CountrySelectedListener countryCodeSelectedListener;
    @BindView
    CountryCodeSelectionView countryCodeSelectionSheetPresenter;
    @BindView
    JellyfishView jellyfishView;

    public enum CountryCodeSelectionStyle {
        BABU(R.color.n2_default_sheet_background, Style.BABU, true),
        WHITE(C0716R.color.white, Style.WHITE, false);
        
        final int backgroundColorRes;
        final boolean hasJellyFish;
        final Style selectionViewStyle;

        private CountryCodeSelectionStyle(int backgroundColorRes2, Style selectionViewStyle2, boolean hasJellyFish2) {
            this.backgroundColorRes = backgroundColorRes2;
            this.selectionViewStyle = selectionViewStyle2;
            this.hasJellyFish = hasJellyFish2;
        }
    }

    public interface CountrySelectedListener {
        void onCountrySelected(CountryCodeItem countryCodeItem);
    }

    public static CountryCodeSelectionFragment newInstance() {
        return newInstance(CountryCodeSelectionStyle.BABU);
    }

    public static CountryCodeSelectionFragment newInstance(CountryCodeSelectionStyle style) {
        return (CountryCodeSelectionFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CountryCodeSelectionFragment()).putSerializable("style", style)).build();
    }

    public void onItemClicked(CountryCodeItem item) {
        if (this.countryCodeSelectedListener != null) {
            this.countryCodeSelectedListener.onCountrySelected(item);
        } else if (getParentFragment() instanceof CountrySelectedListener) {
            ((CountrySelectedListener) getParentFragment()).onCountrySelected(item);
        }
        finish();
    }

    public void setOnCountryCodeSelectedListener(CountrySelectedListener countryCodeSelectedListener2) {
        this.countryCodeSelectedListener = countryCodeSelectedListener2;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_country_code_selection, container, false);
        bindViews(view);
        this.countryCodeSelectionSheetPresenter.setSelectionSheetOnItemClickedListener(this);
        setCountryCodeSelectionStyle(view, (CountryCodeSelectionStyle) getArguments().getSerializable("style"));
        return view;
    }

    private void setCountryCodeSelectionStyle(View view, CountryCodeSelectionStyle style) {
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        this.countryCodeSelectionSheetPresenter.setStyle(style.selectionViewStyle);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
    }

    public void onDestroyView() {
        this.countryCodeSelectionSheetPresenter.setSelectionSheetOnItemClickedListener(null);
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        KeyboardUtils.dismissSoftKeyboard(getView());
    }

    private void finish() {
        getFragmentManager().popBackStack();
    }
}
