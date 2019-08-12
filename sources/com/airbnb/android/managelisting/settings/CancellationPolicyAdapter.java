package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.core.models.LocalizedCancellationPolicy;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.utils.RadioRowModelManager.Listener;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.List;

public class CancellationPolicyAdapter extends AirEpoxyAdapter {
    private final List<LocalizedCancellationPolicy> availableCancellationPolicies;
    private final Listener<String> cancellationPolicyListener = new Listener<String>() {
        public void onValueSelected(String value) {
            CancellationPolicyAdapter.this.selectedCancellationPolicyName = value;
        }

        public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
            CancellationPolicyAdapter.this.notifyModelChanged(model);
        }
    };
    private final RadioRowModelManager<String> cancellationPolicyRadioManager;
    private final DocumentMarqueeEpoxyModel_ documentMarquee = new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.cancellation_policy).captionRes(C7368R.string.cancellation_policy_description);
    private final String existingCancellationPolicyKey;
    @State
    String selectedCancellationPolicyName;

    public CancellationPolicyAdapter(String cancellationPolicyKey, List<LocalizedCancellationPolicy> availableCancellationPolicies2, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        onRestoreInstanceState(savedInstanceState);
        this.existingCancellationPolicyKey = cancellationPolicyKey;
        this.availableCancellationPolicies = availableCancellationPolicies2;
        this.cancellationPolicyRadioManager = new RadioRowModelManager<>(this.cancellationPolicyListener);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.documentMarquee, new LoadingRowEpoxyModel_()});
        if (this.availableCancellationPolicies != null) {
            addCancellationPolicyModels();
        }
    }

    public String getCancellationPolicyName() {
        return this.selectedCancellationPolicyName;
    }

    public boolean hasChanged(String originalCancellationPolicy) {
        return !Objects.equal(this.selectedCancellationPolicyName, originalCancellationPolicy);
    }

    public void setInputEnabled(boolean enabled) {
        this.cancellationPolicyRadioManager.setRowsEnabled(enabled);
    }

    public void addCancellationPolicyModels() {
        removeAllAfterModel(this.documentMarquee);
        for (LocalizedCancellationPolicy cancellationPolicy : this.availableCancellationPolicies) {
            this.cancellationPolicyRadioManager.addRow(buildCancellationPolicyModel(cancellationPolicy), cancellationPolicy.getPolicyId());
        }
        if (this.selectedCancellationPolicyName != null) {
            this.cancellationPolicyRadioManager.setSelectedValue(this.selectedCancellationPolicyName);
        } else {
            Optional<String> listingCancellationPolicyName = FluentIterable.from((Iterable<E>) this.availableCancellationPolicies).transform(CancellationPolicyAdapter$$Lambda$1.lambdaFactory$()).firstMatch(CancellationPolicyAdapter$$Lambda$2.lambdaFactory$(this));
            if (listingCancellationPolicyName.isPresent()) {
                this.selectedCancellationPolicyName = (String) listingCancellationPolicyName.get();
                this.cancellationPolicyRadioManager.setSelectedValue(this.selectedCancellationPolicyName);
            }
        }
        addModels(this.cancellationPolicyRadioManager.getModels());
        notifyModelsChanged();
    }

    static /* synthetic */ boolean lambda$addCancellationPolicyModels$0(CancellationPolicyAdapter cancellationPolicyAdapter, String id) {
        return id != null && id.equals(cancellationPolicyAdapter.existingCancellationPolicyKey);
    }

    private ToggleActionRowEpoxyModel_ buildCancellationPolicyModel(LocalizedCancellationPolicy cancellationPolicy) {
        return new ToggleActionRowEpoxyModel_().title(cancellationPolicy.getLocalizedTitle()).subtitle(cancellationPolicy.getLocalizedDescription());
    }
}
