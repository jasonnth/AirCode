package com.airbnb.android.pickwishlist;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.primitives.AirButton;

public class CreateWishListActivity_ViewBinding implements Unbinder {
    private CreateWishListActivity target;
    private View view2131755297;

    public CreateWishListActivity_ViewBinding(CreateWishListActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public CreateWishListActivity_ViewBinding(final CreateWishListActivity target2, View source) {
        this.target = target2;
        target2.nameInput = (InlineInputRow) Utils.findRequiredViewAsType(source, C7614R.C7616id.wish_list_name_input, "field 'nameInput'", InlineInputRow.class);
        target2.publicToggle = (ToggleActionRow) Utils.findRequiredViewAsType(source, C7614R.C7616id.public_toggle, "field 'publicToggle'", ToggleActionRow.class);
        target2.privateToggle = (ToggleActionRow) Utils.findRequiredViewAsType(source, C7614R.C7616id.private_toggle, "field 'privateToggle'", ToggleActionRow.class);
        View view = Utils.findRequiredView(source, C7614R.C7616id.create_wish_list_button, "field 'createButton' and method 'onCreateClicked'");
        target2.createButton = (AirButton) Utils.castView(view, C7614R.C7616id.create_wish_list_button, "field 'createButton'", AirButton.class);
        this.view2131755297 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCreateClicked();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7614R.C7616id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C7614R.C7616id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C7614R.C7616id.marquee, "field 'marquee'", DocumentMarquee.class);
    }

    public void unbind() {
        CreateWishListActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.nameInput = null;
        target2.publicToggle = null;
        target2.privateToggle = null;
        target2.createButton = null;
        target2.toolbar = null;
        target2.scrollView = null;
        target2.marquee = null;
        this.view2131755297.setOnClickListener(null);
        this.view2131755297 = null;
    }
}
