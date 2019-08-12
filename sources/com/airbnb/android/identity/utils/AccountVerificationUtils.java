package com.airbnb.android.identity.utils;

import android.content.Context;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.CameraHelper;
import com.airbnb.android.core.utils.Check;
import com.jumio.p311nv.NetverifySDK;
import java.util.ArrayList;

public final class AccountVerificationUtils {
    private AccountVerificationUtils() {
    }

    public static boolean isPhotoCapturePossible(Context context, ArrayList<AccountVerificationStep> requiredSteps) {
        Check.notNull(context, "Context can't be null");
        Check.notNull(requiredSteps, "Required steps can't be null");
        boolean meetsRequirements = true;
        if (requiredSteps.contains(AccountVerificationStep.OfflineId)) {
            meetsRequirements = !BuildHelper.isFuture() ? NetverifySDK.isSupportedPlatform() && CameraHelper.isCameraAvailable(context) : CameraHelper.isBackCameraAvailable(context);
        }
        if (!requiredSteps.contains(AccountVerificationStep.Selfie)) {
            return meetsRequirements;
        }
        if (!meetsRequirements || !CameraHelper.isCameraAvailable(context)) {
            return false;
        }
        return true;
    }
}
