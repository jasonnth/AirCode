package com.airbnb.android.listyourspacedls.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StickyButtonSpaceEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.listing.adapters.InputAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import icepick.State;

public class SpaceTypeAdapter extends AirEpoxyAdapter implements InputAdapter {
    private final Context context;
    /* access modifiers changed from: private */
    public final Listener listener;
    @State
    PropertyType propertyType;
    private final InlineInputRowEpoxyModel_ propertyTypeModel = new InlineInputRowEpoxyModel_().titleRes(C7251R.string.lys_dls_property_type).clickListener(SpaceTypeAdapter$$Lambda$1.lambdaFactory$(this));
    private final com.airbnb.android.core.utils.RadioRowModelManager.Listener<SpaceType> radioRowListener = new com.airbnb.android.core.utils.RadioRowModelManager.Listener<SpaceType>() {
        public void onValueSelected(SpaceType spaceType) {
            SpaceTypeAdapter.this.spaceType = spaceType;
            SpaceTypeAdapter.this.listener.logRoomSelectPlaceType(spaceType);
        }

        public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
            SpaceTypeAdapter.this.notifyModelsChanged();
        }
    };
    private final RadioRowModelManager<SpaceType> radioRowModelManager = new RadioRowModelManager<>(this.radioRowListener);
    @State
    SpaceType spaceType;
    private final DocumentMarqueeEpoxyModel_ titleModel = new DocumentMarqueeEpoxyModel_().titleRes(C7251R.string.lys_dls_space_type);

    public interface Listener {
        void logRoomSelectPlaceType(SpaceType spaceType);

        void logRoomSelectPropertyType(PropertyType propertyType);
    }

    public SpaceTypeAdapter(Context context2, Listing listing, Bundle inState, Listener listener2) {
        SpaceType[] values;
        super(true);
        enableDiffing();
        this.context = context2;
        this.listener = listener2;
        if (inState == null) {
            this.spaceType = listing.getSpaceType();
            this.propertyType = PropertyType.getTypeFromKey(listing.getPropertyTypeId());
        } else {
            onRestoreInstanceState(inState);
        }
        for (SpaceType type : SpaceType.values()) {
            this.radioRowModelManager.addRow(type.titleId, type);
        }
        this.radioRowModelManager.setSelectedValue(this.spaceType);
        this.propertyTypeModel.inputRes(this.propertyType.titleId);
        addModel(this.titleModel);
        addModels(this.radioRowModelManager.getModels());
        addModel(this.propertyTypeModel);
        addModel(new StickyButtonSpaceEpoxyModel_());
    }

    public void setInputEnabled(boolean enabled) {
        this.radioRowModelManager.setRowsEnabled(enabled);
        this.propertyTypeModel.enabled(enabled);
        notifyModelsChanged();
    }

    public SpaceType getSpaceType() {
        return this.spaceType;
    }

    public PropertyType getPropertyType() {
        return this.propertyType;
    }

    /* access modifiers changed from: private */
    public void propertyTypeClicked() {
        OptionsMenuFactory.forItems(this.context, (T[]) PropertyType.values()).setTitleResTransformer(SpaceTypeAdapter$$Lambda$2.lambdaFactory$()).setListener(SpaceTypeAdapter$$Lambda$3.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ void lambda$propertyTypeClicked$2(SpaceTypeAdapter spaceTypeAdapter, PropertyType selectedPropertyType) {
        spaceTypeAdapter.propertyType = selectedPropertyType;
        spaceTypeAdapter.propertyTypeModel.inputRes(spaceTypeAdapter.propertyType.titleId);
        spaceTypeAdapter.notifyModelChanged(spaceTypeAdapter.propertyTypeModel);
        spaceTypeAdapter.listener.logRoomSelectPropertyType(spaceTypeAdapter.propertyType);
    }
}
