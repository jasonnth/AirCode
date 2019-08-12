package com.airbnb.android.sharing.p033ui;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;

/* renamed from: com.airbnb.android.sharing.ui.ShareMethodEpoxyModel */
public abstract class ShareMethodEpoxyModel extends AirEpoxyModelWithHolder<Holder> {
    OnClickListener clickListener;
    Drawable icon;
    String name;

    /* renamed from: com.airbnb.android.sharing.ui.ShareMethodEpoxyModel$Holder */
    static final class Holder extends AirViewHolder {
        @BindView
        ImageView icon;
        @BindView
        TextView name;
        @BindView
        View root;

        Holder() {
        }
    }

    /* renamed from: com.airbnb.android.sharing.ui.ShareMethodEpoxyModel$Holder_ViewBinding */
    public final class Holder_ViewBinding implements Unbinder {
        private Holder target;

        public Holder_ViewBinding(Holder target2, View source) {
            this.target = target2;
            target2.root = Utils.findRequiredView(source, C0921R.C0923id.root, "field 'root'");
            target2.icon = (ImageView) Utils.findRequiredViewAsType(source, C0921R.C0923id.icon, "field 'icon'", ImageView.class);
            target2.name = (TextView) Utils.findRequiredViewAsType(source, C0921R.C0923id.name, "field 'name'", TextView.class);
        }

        public void unbind() {
            Holder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.root = null;
            target2.icon = null;
            target2.name = null;
        }
    }

    public void bind(Holder holder) {
        super.bind(holder);
        holder.icon.setImageDrawable(this.icon);
        holder.name.setText(this.name);
        holder.root.setOnClickListener(this.clickListener);
    }

    public int getDividerViewType() {
        return 0;
    }
}
