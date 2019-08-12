package com.airbnb.android.mythbusters;

import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.primitives.AirButton;

public abstract class TrueFalseButtonRowEpoxyModel extends AirEpoxyModelWithHolder<Holder> {
    OnClickListener falseClickListener;
    OnClickListener trueClickListener;

    static final class Holder extends AirViewHolder {
        @BindView
        AirButton falseButton;
        @BindView
        AirButton trueButton;

        Holder() {
        }
    }

    public final class Holder_ViewBinding implements Unbinder {
        private Holder target;

        public Holder_ViewBinding(Holder target2, View source) {
            this.target = target2;
            target2.trueButton = (AirButton) Utils.findRequiredViewAsType(source, C7485R.C7487id.true_button, "field 'trueButton'", AirButton.class);
            target2.falseButton = (AirButton) Utils.findRequiredViewAsType(source, C7485R.C7487id.false_button, "field 'falseButton'", AirButton.class);
        }

        public void unbind() {
            Holder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.trueButton = null;
            target2.falseButton = null;
        }
    }

    public void bind(Holder viewHolder) {
        super.bind(viewHolder);
        viewHolder.trueButton.setOnClickListener(this.trueClickListener);
        viewHolder.falseButton.setOnClickListener(this.falseClickListener);
    }
}
