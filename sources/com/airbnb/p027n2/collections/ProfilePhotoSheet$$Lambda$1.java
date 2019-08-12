package com.airbnb.p027n2.collections;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.collections.ProfilePhotoSheet$$Lambda$1 */
final /* synthetic */ class ProfilePhotoSheet$$Lambda$1 implements OnClickListener {
    private final ProfilePhotoSheet arg$1;

    private ProfilePhotoSheet$$Lambda$1(ProfilePhotoSheet profilePhotoSheet) {
        this.arg$1 = profilePhotoSheet;
    }

    public static OnClickListener lambdaFactory$(ProfilePhotoSheet profilePhotoSheet) {
        return new ProfilePhotoSheet$$Lambda$1(profilePhotoSheet);
    }

    public void onClick(View view) {
        ProfilePhotoSheet.lambda$bindViews$0(this.arg$1, view);
    }
}
