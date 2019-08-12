package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostingContractFragment;
import com.airbnb.android.core.models.CohostingContract;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingContractFragment$$Icepick<T extends CohostingContractFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7805H = new Helper("com.airbnb.android.cohosting.fragments.CohostingContractFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.contract = (CohostingContract) f7805H.getParcelable(state, "contract");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7805H.putParcelable(state, "contract", target.contract);
    }
}
