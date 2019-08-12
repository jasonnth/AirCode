package com.airbnb.android.core.erf;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.bugsnag.MetaDataWrapper;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.erf.Erf.Callbacks;
import com.airbnb.erf.ErfException;
import com.airbnb.erf.Experiment;
import com.bugsnag.android.MetaData;

public final class ErfCallbacks implements Callbacks {
    private static final String ERF_KILL_SWITCH_FORMAT = "erf-android-%s-%s";
    private final AirbnbAccountManager accountManager;
    private final ErfAnalytics erfAnalytics;
    private final ExperimentsProvider experimentsProvider;

    public ErfCallbacks(AirbnbAccountManager accountManager2, ErfAnalytics erfAnalytics2, ExperimentsProvider experimentsProvider2) {
        this.accountManager = accountManager2;
        this.erfAnalytics = erfAnalytics2;
        this.experimentsProvider = experimentsProvider2;
    }

    public void onExperimentDelivered(String name, String treatment) {
        logExperiment(name, treatment);
        BugsnagWrapper.addDeliveredExperimentMetadata(name, treatment);
    }

    public void onExperimentDeliveryFailed(ErfException e) {
        MetaDataWrapper metaDataWrapper = new MetaDataWrapper();
        metaDataWrapper.setGroupingHash(String.valueOf(e.getMessage().hashCode()));
        BugsnagWrapper.notify((Throwable) e, (MetaData) metaDataWrapper);
    }

    public void onExperimentValidation(Experiment experiment) {
        if (isKillSwitched(experiment.getName())) {
            throw new ErfException("Failed to deliver experiment " + experiment.getName() + ". Experiment has been kill switched.");
        } else if (experiment.isSubjectUser() && !this.accountManager.isCurrentUserAuthorized()) {
            throw new ErfException("Failed to deliver experiment " + experiment.getName() + ". Experiment subject is 'User' but you aren't logged in.");
        }
    }

    private boolean isKillSwitched(String experimentName) {
        return Trebuchet.launch(Trebuchet.KEY_KILL_SWITCH, getKillSwitchNameForErfExperiment(experimentName), false);
    }

    private static String getKillSwitchNameForErfExperiment(String experimentName) {
        return String.format(ERF_KILL_SWITCH_FORMAT, new Object[]{BuildHelper.versionName(), experimentName});
    }

    private void logExperiment(String experimentName, String treatmentName) {
        this.erfAnalytics.logExperiment(this.experimentsProvider.getExperiment(experimentName), treatmentName);
    }
}
