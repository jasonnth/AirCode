package com.airbnb.p027n2.utils;

import android.support.p000v4.util.ArrayMap;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.airbnb.n2.utils.RadioRowManager */
public class RadioRowManager<T> implements OnCheckedChangeListener {
    private final RadioRowManagerSelectionListener<T> listener;
    private ToggleActionRow selectedRow;
    private final Map<ToggleActionRow, T> valuesMap = new ArrayMap();

    public RadioRowManager(RadioRowManagerSelectionListener<T> listener2) {
        this.listener = listener2;
    }

    public void addToggleActionRow(ToggleActionRow toggleActionRow, T selectedValue) {
        toggleActionRow.radio(true);
        toggleActionRow.setOnCheckedChangeListener(this);
        this.valuesMap.put(toggleActionRow, selectedValue);
    }

    public void setCurrentValue(T value) {
        for (Entry<ToggleActionRow, T> entry : this.valuesMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                onCheckedChanged((ToggleActionRow) entry.getKey(), true);
                return;
            }
        }
    }

    public T getCurrentValue() {
        return this.valuesMap.get(this.selectedRow);
    }

    public void onCheckedChanged(ToggleActionRow toggleActionRow, boolean checked) {
        if (!toggleActionRow.equals(this.selectedRow)) {
            if (this.selectedRow != null) {
                this.selectedRow.setChecked(false);
            }
            toggleActionRow.setChecked(true);
            this.selectedRow = toggleActionRow;
            this.listener.onRadioRowSelection(this.valuesMap.get(this.selectedRow));
        }
    }
}
