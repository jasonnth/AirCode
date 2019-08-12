package com.airbnb.android.lib.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import com.airbnb.android.core.events.CurrencyChangedEvent;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.models.Currency;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.CurrencyAdapter;
import java.util.ArrayList;
import java.util.List;

public class CurrencySelectorDialogFragment extends ZenDialog {
    private static final String ARG_SELECTED = "selected";
    private static final String KEY_CURRENCIES = "currencies";
    private List<Currency> mCurrences;
    private OnCurrencySelectedListener mListener;

    public interface OnCurrencySelectedListener {
        void onCurrencySelected(Currency currency);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCurrences = getArguments().getParcelableArrayList(KEY_CURRENCIES);
    }

    public static CurrencySelectorDialogFragment newInstance(int selectedIndex, List<Currency> currencies) {
        Bundle args = new Bundle();
        args.putInt(ARG_SELECTED, selectedIndex);
        args.putParcelableArrayList(KEY_CURRENCIES, new ArrayList(currencies));
        return (CurrencySelectorDialogFragment) new ZenBuilder(new CurrencySelectorDialogFragment()).withTitle(C0880R.string.select_currency).withListView(args).create();
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        return new CurrencyAdapter(getActivity(), 0, 0, this.mCurrences, getArguments().getInt(ARG_SELECTED));
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return CurrencySelectorDialogFragment$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$getItemClickListener$0(CurrencySelectorDialogFragment currencySelectorDialogFragment, AdapterView parent, View view, int position, long id) {
        currencySelectorDialogFragment.dismiss();
        Currency c = (Currency) currencySelectorDialogFragment.mCurrences.get(position);
        if (currencySelectorDialogFragment.mListener != null) {
            currencySelectorDialogFragment.mListener.onCurrencySelected(c);
        }
        currencySelectorDialogFragment.mBus.post(new CurrencyChangedEvent(c));
        Fragment f = currencySelectorDialogFragment.getTargetFragment();
        if (f == null || !OnCurrencySelectedListener.class.isInstance(f)) {
            Activity activity = currencySelectorDialogFragment.getActivity();
            if (activity != null && (activity instanceof OnCurrencySelectedListener)) {
                ((OnCurrencySelectedListener) activity).onCurrencySelected(c);
                return;
            }
            return;
        }
        ((OnCurrencySelectedListener) f).onCurrencySelected(c);
    }

    public void setOnCurrencySelectedDialogListener(OnCurrencySelectedListener listener) {
        this.mListener = listener;
    }
}
