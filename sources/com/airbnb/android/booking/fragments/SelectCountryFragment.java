package com.airbnb.android.booking.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.BookingGraph;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.views.CountryCodeSelectionView;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.google.common.base.Optional;
import java.util.Locale;

public class SelectCountryFragment extends AirFragment {
    private static final String ARG_COUNTRY = "arg_country";
    private static final String ARG_FLOW = "arg_flow";
    private static final String ARG_TITLE_RES = "arg_title_res";
    public static final String EXTRA_RESULT_COUNTRY_CODE = "extra_result_country_code";
    private CountrySelectorListener countrySelectorListener;
    private Flow flow;
    QuickPayJitneyLogger quickPayJitneyLogger;
    @BindView
    CountryCodeSelectionView selectionSheetPresenter;

    public interface CountrySelectorListener {
        void onSelectCountry(String str);
    }

    public enum Flow {
        Booking,
        AddPayout,
        QuickPay
    }

    public static SelectCountryFragment forBooking(int titleRes, String countryCode) {
        return (SelectCountryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SelectCountryFragment()).putInt(ARG_FLOW, Flow.Booking.ordinal())).putInt(ARG_TITLE_RES, titleRes)).putString(ARG_COUNTRY, countryCode)).build();
    }

    public static SelectCountryFragment forAddPayout(int titleRes, String countryCode) {
        return (SelectCountryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SelectCountryFragment()).putInt(ARG_FLOW, Flow.AddPayout.ordinal())).putInt(ARG_TITLE_RES, titleRes)).putString(ARG_COUNTRY, countryCode)).build();
    }

    public static SelectCountryFragment forQuickPay(int titleRes, String countryCode) {
        return (SelectCountryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SelectCountryFragment()).putInt(ARG_FLOW, Flow.QuickPay.ordinal())).putInt(ARG_TITLE_RES, titleRes)).putString(ARG_COUNTRY, countryCode)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BookingGraph) CoreApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_guest_select_country, container, false);
        bindViews(view);
        this.flow = Flow.values()[getArguments().getInt(ARG_FLOW)];
        this.selectionSheetPresenter.setTitle(getArguments().getInt(ARG_TITLE_RES));
        setCountry();
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LegacyAddPaymentMethodActivity) {
            this.countrySelectorListener = ((LegacyAddPaymentMethodActivity) context).getCountrySelectorListener();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onContinueClicked() {
        String countryCode = this.selectionSheetPresenter.getSelectedCountryCode();
        if (countryCode != null) {
            if (isAddPaymentFlowDisabledForCountry(countryCode)) {
                String displayCountry = new Locale("", countryCode).getDisplayCountry();
                ErrorUtils.showErrorUsingSnackbar(getView(), getString(C0704R.string.quick_pay_add_payment_blocked_country_text, displayCountry));
                return;
            }
            switch (this.flow) {
                case Booking:
                case QuickPay:
                    LegacyAddPaymentMethodActivity addPaymentMethodActivity = (LegacyAddPaymentMethodActivity) getActivity();
                    if (this.flow == Flow.Booking) {
                        KonaBookingAnalytics.trackClick("payment_options", "payment_country", addPaymentMethodActivity.getAnalyticsData().mo9946kv("country_code", countryCode));
                    } else {
                        this.quickPayJitneyLogger.paymentCountry(countryCode);
                    }
                    Check.notNull(this.countrySelectorListener);
                    this.countrySelectorListener.onSelectCountry(countryCode);
                    return;
                case AddPayout:
                    Intent resultData = new Intent();
                    resultData.putExtra(EXTRA_RESULT_COUNTRY_CODE, countryCode);
                    getActivity().setResult(-1, resultData);
                    getActivity().finish();
                    return;
                default:
                    throw new IllegalStateException("SelectCountryFragment called without specifying a flow");
            }
        }
    }

    private void setCountry() {
        this.selectionSheetPresenter.setSelectedCountryCode((String) Optional.fromNullable(getArguments().getString(ARG_COUNTRY)).mo41059or(this.mAccountManager.getCurrentUser().getDefaultCountryOfResidence()));
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.AddPaymentCountry;
    }

    private boolean isAddPaymentFlowDisabledForCountry(String countryCode) {
        return countryCode.equals(AirbnbConstants.COUNTRY_CODE_BRAZIL) || countryCode.equals(AirbnbConstants.COUNTRY_CODE_INDIA);
    }
}
