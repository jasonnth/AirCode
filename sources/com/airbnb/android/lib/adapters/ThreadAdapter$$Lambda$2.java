package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.RiskEducationFragment;

final /* synthetic */ class ThreadAdapter$$Lambda$2 implements OnClickListener {
    private final ThreadAdapter arg$1;

    private ThreadAdapter$$Lambda$2(ThreadAdapter threadAdapter) {
        this.arg$1 = threadAdapter;
    }

    public static OnClickListener lambdaFactory$(ThreadAdapter threadAdapter) {
        return new ThreadAdapter$$Lambda$2(threadAdapter);
    }

    public void onClick(View view) {
        this.arg$1.context.startActivity(RiskEducationFragment.newIntent(this.arg$1.context, this.arg$1.context.getString(C0880R.string.risk_education_offline_payment_title), this.arg$1.context.getString(C0880R.string.risk_education_offline_payment_content)));
    }
}
