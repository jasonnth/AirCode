package com.airbnb.android.registration;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.registration.models.AccountLoginData;

final /* synthetic */ class CreateSocialAccountFragment$$Lambda$3 implements OnClickListener {
    private final CreateSocialAccountFragment arg$1;
    private final AccountLoginData arg$2;

    private CreateSocialAccountFragment$$Lambda$3(CreateSocialAccountFragment createSocialAccountFragment, AccountLoginData accountLoginData) {
        this.arg$1 = createSocialAccountFragment;
        this.arg$2 = accountLoginData;
    }

    public static OnClickListener lambdaFactory$(CreateSocialAccountFragment createSocialAccountFragment, AccountLoginData accountLoginData) {
        return new CreateSocialAccountFragment$$Lambda$3(createSocialAccountFragment, accountLoginData);
    }

    public void onClick(View view) {
        CreateSocialAccountFragment.lambda$handleNetworkError$2(this.arg$1, this.arg$2, view);
    }
}
