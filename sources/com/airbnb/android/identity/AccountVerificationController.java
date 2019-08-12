package com.airbnb.android.identity;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.User;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.List;

public interface AccountVerificationController {
    AirToolbar getAirToolbar();

    User getHost();

    IdentityJitneyLogger getIdentityJitneyLogger();

    AccountVerificationOfflineIdController getOfflineIdController();

    List<String> getSelfieFilePaths();

    User getUser();

    void initOfflineIdController(Bundle bundle, AirFragment airFragment, RequestManager requestManager, NavigationLogging navigationLogging);

    boolean isFirstStep();

    boolean isModal();

    void onLicensePhotosCaptured(String str, String str2, String str3);

    void onStepFinished(AccountVerificationStep accountVerificationStep, boolean z);

    void setSelfieFilePathsForPreview(List<String> list);

    void setState(SheetState sheetState);

    void showFragmentForStep(Fragment fragment, AccountVerificationStep accountVerificationStep);

    void showFragmentForStepPerformingPopBackStackAnimation(Fragment fragment, AccountVerificationStep accountVerificationStep);

    void showPreviousStep();
}
