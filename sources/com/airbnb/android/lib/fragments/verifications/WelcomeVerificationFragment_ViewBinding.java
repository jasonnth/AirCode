package com.airbnb.android.lib.fragments.verifications;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class WelcomeVerificationFragment_ViewBinding implements Unbinder {
    private WelcomeVerificationFragment target;
    private View view2131756904;

    public WelcomeVerificationFragment_ViewBinding(final WelcomeVerificationFragment target2, View source) {
        this.target = target2;
        target2.userWelcomeHeader = (TextView) Utils.findOptionalViewAsType(source, C0880R.C0882id.welcome_user_header, "field 'userWelcomeHeader'", TextView.class);
        target2.hostHaloImageView = (HaloImageView) Utils.findOptionalViewAsType(source, C0880R.C0882id.halo_host, "field 'hostHaloImageView'", HaloImageView.class);
        target2.guestHaloImageView = (HaloImageView) Utils.findOptionalViewAsType(source, C0880R.C0882id.halo_guest, "field 'guestHaloImageView'", HaloImageView.class);
        target2.stepsRemainingTextView = (TextView) Utils.findOptionalViewAsType(source, C0880R.C0882id.txt_steps_remaining, "field 'stepsRemainingTextView'", TextView.class);
        target2.bookingHeaderTextView = (TextView) Utils.findOptionalViewAsType(source, C0880R.C0882id.txt_welcome_booking_header, "field 'bookingHeaderTextView'", TextView.class);
        target2.contentContainer = source.findViewById(C0880R.C0882id.container_content);
        target2.backgroundImageView = (ImageView) Utils.findOptionalViewAsType(source, C0880R.C0882id.img_background, "field 'backgroundImageView'", ImageView.class);
        target2.gradientImage = (ImageView) Utils.findOptionalViewAsType(source, C0880R.C0882id.img_gradient, "field 'gradientImage'", ImageView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn_welcome, "method 'doneWithWelcome'");
        this.view2131756904 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.doneWithWelcome();
            }
        });
    }

    public void unbind() {
        WelcomeVerificationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.userWelcomeHeader = null;
        target2.hostHaloImageView = null;
        target2.guestHaloImageView = null;
        target2.stepsRemainingTextView = null;
        target2.bookingHeaderTextView = null;
        target2.contentContainer = null;
        target2.backgroundImageView = null;
        target2.gradientImage = null;
        this.view2131756904.setOnClickListener(null);
        this.view2131756904 = null;
    }
}
