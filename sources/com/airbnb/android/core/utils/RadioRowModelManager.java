package com.airbnb.android.core.utils;

import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.google.common.base.Objects;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RadioRowModelManager<T> {
    private final Listener<T> listener;
    private T selectedValue;
    private boolean show = true;
    private final Map<T, ToggleActionRowEpoxyModel_> valuesMap = new LinkedHashMap();

    public interface Listener<T> {
        void onModelUpdated(ToggleActionRowEpoxyModel_ toggleActionRowEpoxyModel_);

        void onValueSelected(T t);
    }

    public RadioRowModelManager(Listener<T> listener2) {
        this.listener = listener2;
    }

    public void addRow(ToggleActionRowEpoxyModel_ model, T value) {
        Check.state(model.mo11715id() != 0);
        this.valuesMap.put(value, model);
        model.radio(true);
        model.checkedChangedlistener(RadioRowModelManager$$Lambda$1.lambdaFactory$(this, value));
    }

    public RadioRowModelManager<T> addRow(CharSequence title, T value) {
        addRow(new ToggleActionRowEpoxyModel_().title(title).radio(true), value);
        return this;
    }

    public RadioRowModelManager<T> addRow(int titleRes, T value) {
        addRow(new ToggleActionRowEpoxyModel_().titleRes(titleRes).radio(true), value);
        return this;
    }

    public void setRowsEnabled(boolean enabled) {
        for (ToggleActionRowEpoxyModel_ model : getModels()) {
            model.enabled(enabled);
            this.listener.onModelUpdated(model);
        }
    }

    public Collection<ToggleActionRowEpoxyModel_> getModels() {
        return this.valuesMap.values();
    }

    public T getSelectedValue() {
        return this.selectedValue;
    }

    public RadioRowModelManager<T> setSelectedValue(T value) {
        setSelectedValue(value, false);
        return this;
    }

    public RadioRowModelManager<T> show(boolean show2) {
        this.show = show2;
        for (ToggleActionRowEpoxyModel_ model : getModels()) {
            model.show(show2);
        }
        return this;
    }

    public boolean isShown() {
        return this.show;
    }

    /* access modifiers changed from: private */
    public void setSelectedValue(T value, boolean fromUser) {
        if (!Objects.equal(this.selectedValue, value)) {
            if (this.valuesMap.containsKey(this.selectedValue)) {
                ToggleActionRowEpoxyModel_ previouslySelectedRow = (ToggleActionRowEpoxyModel_) this.valuesMap.get(this.selectedValue);
                previouslySelectedRow.checked(false);
                this.listener.onModelUpdated(previouslySelectedRow);
            }
            this.selectedValue = value;
            ToggleActionRowEpoxyModel_ selectedModel = (ToggleActionRowEpoxyModel_) this.valuesMap.get(value);
            selectedModel.checked(true);
            this.listener.onModelUpdated(selectedModel);
            if (fromUser) {
                this.listener.onValueSelected(value);
            }
        }
    }
}
