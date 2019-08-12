package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.User;
import java.util.ArrayList;

public class AccountVerificationActivityIntents {
    public static final String EXTRA_HOST = "extra_host";
    public static final String EXTRA_IS_MODAL = "extra_is_modal";
    public static final String EXTRA_MOVE_TO_LAST_STEP = "extra_move_to_last_step";
    public static final String EXTRA_PHONE_VERIFICATION_CODE = "extra_phone_verification_code";
    public static final String EXTRA_REQUIRED_VERIFICATION_STEPS = "extra_required_verification_steps";
    public static final String EXTRA_SELFIE_PHOTOS_FILE_PATH = "extra_selfie_photos_file_path";
    public static final String EXTRA_VERIFICATION_FLOW = "extra_verification_flow";
    public static final String EXTRA_VERIFICATION_USER = "extra_verification_user";

    public static Intent newIntentForSteps(Context context, ArrayList<? extends Parcelable> steps, VerificationFlow verificationFlow, User host, User verificationUser, String phoneVerificationCode, boolean moveToLastStep, boolean isModal, ArrayList<String> selfiePhotoFilePaths) {
        return new Intent(context, Activities.accountVerification()).putExtra("extra_host", host).putExtra(EXTRA_VERIFICATION_USER, verificationUser).putExtra(EXTRA_REQUIRED_VERIFICATION_STEPS, steps).putExtra("extra_verification_flow", verificationFlow).putExtra(EXTRA_PHONE_VERIFICATION_CODE, phoneVerificationCode).putExtra(EXTRA_SELFIE_PHOTOS_FILE_PATH, selfiePhotoFilePaths).putExtra(EXTRA_IS_MODAL, isModal).putExtra(EXTRA_MOVE_TO_LAST_STEP, moveToLastStep);
    }
}
