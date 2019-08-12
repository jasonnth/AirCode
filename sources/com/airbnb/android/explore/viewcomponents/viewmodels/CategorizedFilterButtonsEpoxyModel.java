package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.p027n2.components.CategorizedFilterButtons;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class CategorizedFilterButtonsEpoxyModel extends AirEpoxyModel<CategorizedFilterButtons> {
    FilterSuggestionItem item1;
    OnClickListener item1ClickListener;
    FilterSuggestionItem item2;
    OnClickListener item2ClickListener;
    FilterSuggestionItem item3;
    OnClickListener item3ClickListener;
    String title;

    public void bind(CategorizedFilterButtons filterButtons) {
        super.bind(filterButtons);
        filterButtons.setTitleText(this.title);
        boolean selected = false;
        if (this.item1 != null) {
            filterButtons.setButton1Text(this.item1.getDisplayText());
            filterButtons.setButton1ClickListener(this.item1ClickListener);
            filterButtons.setMode(1);
            filterButtons.setButton1Selected(this.item1.isSelected());
            selected = false | this.item1.isSelected();
        }
        if (this.item2 != null) {
            filterButtons.setButton2Text(this.item2.getDisplayText());
            filterButtons.setButton2ClickListener(this.item2ClickListener);
            filterButtons.setMode(2);
            filterButtons.setButton2Selected(this.item2.isSelected());
            selected |= this.item2.isSelected();
        }
        if (this.item3 != null) {
            filterButtons.setButton3Text(this.item3.getDisplayText());
            filterButtons.setButton3ClickListener(this.item3ClickListener);
            filterButtons.setMode(3);
            filterButtons.setButton3Selected(this.item3.isSelected());
            selected |= this.item3.isSelected();
        }
        filterButtons.setBackground(selected);
    }

    public void unbind(CategorizedFilterButtons filterButtons) {
        super.unbind(filterButtons);
        filterButtons.setButton1ClickListener(null);
        filterButtons.setButton2ClickListener(null);
        filterButtons.setButton3ClickListener(null);
    }
}
