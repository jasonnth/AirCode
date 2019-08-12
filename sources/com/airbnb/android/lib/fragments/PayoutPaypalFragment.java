package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.airbnb.android.lib.C0880R;

public class PayoutPaypalFragment extends Fragment {
    public static PayoutPaypalFragment newInstance() {
        return new PayoutPaypalFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0880R.layout.fragment_payout_paypal, container, false);
        Button noButton = (Button) v.findViewById(C0880R.C0882id.no_button);
        ((Button) v.findViewById(C0880R.C0882id.yes_button)).setOnClickListener(PayoutPaypalFragment$$Lambda$1.lambdaFactory$(this));
        noButton.setOnClickListener(PayoutPaypalFragment$$Lambda$2.lambdaFactory$(this));
        return v;
    }
}
