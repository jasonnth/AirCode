package com.airbnb.android.referrals;

import com.airbnb.android.core.explore.ChildScope;
import com.airbnb.android.referrals.rolodex.ContactUploadIntentService;

@ChildScope
public interface ReferralsComponent {

    public interface Builder {
        ReferralsComponent build();
    }

    void inject(ReferralsFragment referralsFragment);

    void inject(ReferralsOneClickFragment referralsOneClickFragment);

    void inject(SentReferralsFragment sentReferralsFragment);

    void inject(ContactUploadIntentService contactUploadIntentService);
}
