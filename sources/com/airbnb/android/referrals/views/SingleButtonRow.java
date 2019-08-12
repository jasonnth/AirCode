package com.airbnb.android.referrals.views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.referrals.C1532R;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.ViewLibUtils;

public abstract class SingleButtonRow extends AirEpoxyModelWithHolder<Holder> {
    OnClickListener clickListener;
    String text;
    int textRes;

    static final class Holder extends AirViewHolder {
        @BindView
        AirButton button;
        @BindView
        View divider;

        Holder() {
        }

        /* access modifiers changed from: protected */
        public void showDivider(boolean showDivider) {
            ViewLibUtils.setVisibleIf(this.divider, showDivider);
        }

        public Context getContext() {
            return this.button.getContext();
        }
    }

    public final class Holder_ViewBinding implements Unbinder {
        private Holder target;

        public Holder_ViewBinding(Holder target2, View source) {
            this.target = target2;
            target2.button = (AirButton) Utils.findRequiredViewAsType(source, C1532R.C1534id.button, "field 'button'", AirButton.class);
            target2.divider = Utils.findRequiredView(source, C1532R.C1534id.divider, "field 'divider'");
        }

        public void unbind() {
            Holder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.button = null;
            target2.divider = null;
        }
    }

    public void bind(Holder viewHolder) {
        super.bind(viewHolder);
        viewHolder.button.setText(this.textRes != 0 ? viewHolder.getContext().getString(this.textRes) : this.text);
        viewHolder.button.setOnClickListener(this.clickListener);
    }

    /* access modifiers changed from: protected */
    public Holder createNewHolder() {
        return new Holder();
    }

    public int getDividerViewType() {
        return 0;
    }
}
