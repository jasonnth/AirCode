package com.airbnb.android.core.utils;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.erf.ErfExperiment;
import com.airbnb.android.core.erf.experiments.AlipayNonBindingFlowExperiment;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.LanguageUtils;
import com.airbnb.erf.Experiments;
import com.alipay.sdk.app.AuthTask;
import java.util.Locale;

public final class ChinaUtils {
    private ChinaUtils() {
    }

    public static boolean isUserCountrySetToChina(User user) {
        return AirbnbConstants.COUNTRY_CODE_CHINA.equals(user.getCountry());
    }

    public static boolean isUserInChinaOrPrefersChineseLanguage() {
        return AppLaunchUtils.isUserInChina() || LocationUtil.isUserPreferredCountryChina() || LanguageUtils.isUserPreferredLanguageChinese();
    }

    public static boolean isUserUsingSimplifiedChinese() {
        return LocationUtil.isUserPreferredCountryChina() && AirbnbConstants.LANGUAGE_CODE_CHINESE.equalsIgnoreCase(Locale.getDefault().getLanguage());
    }

    public static boolean shouldShowSplashScreenForCBL() {
        return isUserUsingSimplifiedChinese();
    }

    public static boolean isWechatTripSharingEnabled(Context context) {
        if (!Trebuchet.launch("killswitch_wechat_trip_share", false) && LanguageUtils.isUserPreferredLanguageChinese() && ExternalAppUtils.isWechatInstalled(context) && Experiments.enableShareTripToWechat()) {
            return true;
        }
        return false;
    }

    public static boolean isAlipayInstalled(Context context) {
        return new AuthTask(context).isExistAlipay();
    }

    public static boolean enableExploreBookButtonOptimization(Context context) {
        return !MiscUtils.isSmallScreen(context) && isUserUsingSimplifiedChinese() && Experiments.isContactHostButtonVisible();
    }

    public static boolean enableOfflinePaymentEducation() {
        return isUserInChinaOrPrefersChineseLanguage() && Experiments.enableOfflinePaymentEducation();
    }

    public static boolean enableNew5AFlow() {
        return isUserInChinaOrPrefersChineseLanguage() && Experiments.enableNew5AFlow();
    }

    public static boolean enableSearchPlaceholderOptimization() {
        return isUserUsingSimplifiedChinese() && Experiments.enableSearchPlaceholderOptimization();
    }

    public static boolean enableVerificationCodeAutofill() {
        return BuildHelper.isChina() && Experiments.enableVerificationCodeAutofill();
    }

    public static boolean useHomeCardChina() {
        return !MiscUtils.isSmallScreen(CoreApplication.appContext()) && !MiscUtils.isTabletScreen(CoreApplication.appContext()) && isUserUsingSimplifiedChinese() && Experiments.useHomeCardChina();
    }

    public static boolean useSearchPageChina() {
        return isUserUsingSimplifiedChinese() && Experiments.useSearchPageChina();
    }

    public static boolean enableAlipayNonBindingFlow(Context context, boolean deliverExperiment) {
        boolean isUserQualified;
        boolean isInTreatmentGroup;
        if (!isAlipayInstalled(context) || !isUserInChinaOrPrefersChineseLanguage()) {
            isUserQualified = false;
        } else {
            isUserQualified = true;
        }
        if (deliverExperiment) {
            return isUserQualified && Experiments.enableAlipayNonBindingFlow();
        }
        ErfExperiment experiment = CoreApplication.instance().component().experimentsProvider().getExperiment(AlipayNonBindingFlowExperiment.EXPERIMENT_NAME);
        if (experiment != null) {
            isInTreatmentGroup = TextUtils.equals(experiment.getAssignedTreatment(), "treatment");
        } else {
            isInTreatmentGroup = false;
        }
        if (!isUserQualified || (!BuildHelper.isDebugFeaturesEnabled() && !isInTreatmentGroup)) {
            return false;
        }
        return true;
    }
}
