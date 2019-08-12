package com.airbnb.android.lib.adapters.settings;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class AboutEpoxyController extends AirEpoxyController {
    StandardRowEpoxyModel_ howItWorksRow;
    private Listener listener;
    StandardRowEpoxyModel_ nonDiscriminationPolicyRow;
    StandardRowEpoxyModel_ paymentTermsOfServiceRow;
    StandardRowEpoxyModel_ privacyPolicyRow;
    ToolbarSpacerEpoxyModel_ spacerRow;
    StandardRowEpoxyModel_ termsOfServiceRow;
    StandardRowEpoxyModel_ versionRow;
    StandardRowEpoxyModel_ whyHostRow;

    public interface Listener {
        void onHowItWorksClicked();

        void onNonDiscrimationPolicyClicked();

        void onPaymentTermsOfServiceClicked();

        void onPrivacyPolicyClicked();

        void onTermsOfServiceClicked();

        void onWhyHostClicked();
    }

    public AboutEpoxyController(Listener listener2) {
        this.listener = listener2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        setupSpacerRow();
        setupHowItWorksRow();
        setupWhyHostRow();
        setupTermsOfServiceRow();
        setupNonDiscriminationPolicyRow();
        setupPaymentTermsOfServiceRow();
        setupPrivacyPolicyRow();
        setupVersionRow();
    }

    private void setupSpacerRow() {
        this.spacerRow.addTo(this);
    }

    private void setupHowItWorksRow() {
        this.howItWorksRow.title(C0880R.string.how_it_works).clickListener(AboutEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
    }

    private void setupWhyHostRow() {
        this.whyHostRow.title(C0880R.string.options_menu_how_to_host).clickListener(AboutEpoxyController$$Lambda$2.lambdaFactory$(this)).addTo(this);
    }

    private void setupTermsOfServiceRow() {
        this.termsOfServiceRow.title(C0880R.string.terms_of_service).clickListener(AboutEpoxyController$$Lambda$3.lambdaFactory$(this)).addTo(this);
    }

    private void setupNonDiscriminationPolicyRow() {
        this.nonDiscriminationPolicyRow.title(C0880R.string.about_screen_anti_discrimination).clickListener(AboutEpoxyController$$Lambda$4.lambdaFactory$(this)).addTo(this);
    }

    private void setupPaymentTermsOfServiceRow() {
        this.paymentTermsOfServiceRow.title(C0880R.string.payments_terms_of_service).clickListener(AboutEpoxyController$$Lambda$5.lambdaFactory$(this)).addTo(this);
    }

    private void setupPrivacyPolicyRow() {
        this.privacyPolicyRow.title(C0880R.string.privacy_policy).clickListener(AboutEpoxyController$$Lambda$6.lambdaFactory$(this)).addTo(this);
    }

    private void setupVersionRow() {
        if (BuildHelper.isChina()) {
            this.versionRow.longClickListener(AboutEpoxyController$$Lambda$7.lambdaFactory$());
        }
        this.versionRow.title(C0880R.string.settings_build_version).actionText((CharSequence) BuildHelper.versionName() + " / " + BuildHelper.versionCode()).addTo(this);
    }
}
