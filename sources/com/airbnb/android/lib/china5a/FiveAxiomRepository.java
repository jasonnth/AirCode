package com.airbnb.android.lib.china5a;

import com.airbnb.android.lib.china5a.email.EmailVerificationModel;
import com.airbnb.android.lib.china5a.phone.PhoneVerificationModel;
import com.airbnb.android.lib.china5a.photo.PhotoVerificationModel;

public interface FiveAxiomRepository {
    EmailVerificationModel getEmailModel();

    VerificationFlowModel getFlowModel();

    PhoneVerificationModel getPhoneModel();

    PhotoVerificationModel getPhotoModel();
}
