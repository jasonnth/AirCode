package com.airbnb.android.core.viewcomponents.models;

import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.ButtonBar;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;
import java.util.List;

public abstract class ButtonBarEpoxyModel extends AirEpoxyModel<ButtonBar> {
    private final List<ButtonBarButton> buttons = new ArrayList();
    int icon1;
    int icon2;
    int icon3;
    int icon4;
    int label1;
    int label2;
    int label3;
    int label4;
    OnClickListener listener1;
    OnClickListener listener2;
    OnClickListener listener3;
    OnClickListener listener4;
    int numberOfButtons;

    private class ButtonBarButton {
        public final int icon;
        public final int label;
        public final OnClickListener listener;

        public ButtonBarButton(int label2, int icon2, OnClickListener listener2) {
            this.label = label2;
            this.icon = icon2;
            this.listener = listener2;
        }
    }

    public void bind(ButtonBar view) {
        super.bind(view);
        if (this.buttons.size() > 0) {
            this.numberOfButtons = this.buttons.size();
            if (this.numberOfButtons > 0) {
                this.label1 = ((ButtonBarButton) this.buttons.get(0)).label;
                this.icon1 = ((ButtonBarButton) this.buttons.get(0)).icon;
                this.listener1 = ((ButtonBarButton) this.buttons.get(0)).listener;
            }
            if (this.numberOfButtons > 1) {
                this.label2 = ((ButtonBarButton) this.buttons.get(1)).label;
                this.icon2 = ((ButtonBarButton) this.buttons.get(1)).icon;
                this.listener2 = ((ButtonBarButton) this.buttons.get(1)).listener;
            }
            if (this.numberOfButtons > 2) {
                this.label3 = ((ButtonBarButton) this.buttons.get(2)).label;
                this.icon3 = ((ButtonBarButton) this.buttons.get(2)).icon;
                this.listener3 = ((ButtonBarButton) this.buttons.get(2)).listener;
            }
            if (this.numberOfButtons > 3) {
                this.label4 = ((ButtonBarButton) this.buttons.get(3)).label;
                this.icon4 = ((ButtonBarButton) this.buttons.get(3)).icon;
                this.listener4 = ((ButtonBarButton) this.buttons.get(3)).listener;
            }
        }
        if (this.numberOfButtons > 0) {
            view.setNumberOfButtons(this.numberOfButtons);
        }
        if (this.label1 != 0) {
            view.setLabel1(this.label1);
        } else {
            view.setLabel1((CharSequence) null);
        }
        if (this.label2 != 0) {
            view.setLabel2(this.label2);
        } else {
            view.setLabel2((CharSequence) null);
        }
        if (this.label3 != 0) {
            view.setLabel3(this.label3);
        } else {
            view.setLabel3((CharSequence) null);
        }
        if (this.label4 != 0) {
            view.setLabel4(this.label4);
        } else {
            view.setLabel4((CharSequence) null);
        }
        if (this.icon1 != 0) {
            view.setIcon1(this.icon1);
        } else {
            view.setIcon1((Drawable) null);
        }
        if (this.icon2 != 0) {
            view.setIcon2(this.icon2);
        } else {
            view.setIcon2((Drawable) null);
        }
        if (this.icon3 != 0) {
            view.setIcon3(this.icon3);
        } else {
            view.setIcon3((Drawable) null);
        }
        if (this.icon4 != 0) {
            view.setIcon4(this.icon4);
        } else {
            view.setIcon4((Drawable) null);
        }
        view.setOnClickListener1(this.listener1);
        view.setOnClickListener2(this.listener2);
        view.setOnClickListener3(this.listener3);
        view.setOnClickListener4(this.listener4);
    }

    public void unbind(ButtonBar view) {
        super.unbind(view);
        view.setOnClickListener1(null);
        view.setOnClickListener2(null);
        view.setOnClickListener3(null);
        view.setOnClickListener4(null);
    }

    public ButtonBarEpoxyModel addButton(int label, int icon, OnClickListener listener) {
        this.buttons.add(new ButtonBarButton(label, icon, listener));
        return this;
    }

    public ButtonBarEpoxyModel clearButtons() {
        this.buttons.clear();
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }
}
