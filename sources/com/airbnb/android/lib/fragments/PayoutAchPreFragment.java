package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;

public class PayoutAchPreFragment extends Fragment {

    public enum AchAccountType {
        CHECKING("Checking"),
        SAVINGS("Savings");
        
        public final String apiName;

        private AchAccountType(String apiName2) {
            this.apiName = apiName2;
        }
    }

    public static PayoutAchPreFragment newInstance() {
        return new PayoutAchPreFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0880R.layout.fragment_payout_ach_pre, container, false);
        Button checking = (Button) v.findViewById(C0880R.C0882id.checking);
        Button savings = (Button) v.findViewById(C0880R.C0882id.savings);
        OnClickListener listener = PayoutAchPreFragment$$Lambda$1.lambdaFactory$(this);
        checking.setOnClickListener(listener);
        savings.setOnClickListener(listener);
        return v;
    }

    static /* synthetic */ void lambda$onCreateView$0(PayoutAchPreFragment payoutAchPreFragment, View view) {
        int viewId = view.getId();
        if (viewId == C0880R.C0882id.checking) {
            ((LegacyAddPayoutActivity) payoutAchPreFragment.getActivity()).showFragment(PayoutAchFragment.newInstance(AchAccountType.CHECKING.apiName));
        } else if (viewId == C0880R.C0882id.savings) {
            ((LegacyAddPayoutActivity) payoutAchPreFragment.getActivity()).showFragment(PayoutAchFragment.newInstance(AchAccountType.SAVINGS.apiName));
        } else {
            throw new RuntimeException("unexpcted id, should be checking or savings");
        }
    }
}
