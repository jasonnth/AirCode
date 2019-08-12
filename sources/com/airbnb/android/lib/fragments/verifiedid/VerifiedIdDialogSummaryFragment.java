package com.airbnb.android.lib.fragments.verifiedid;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.utils.ColorizedDrawable;

public class VerifiedIdDialogSummaryFragment extends AirDialogFragment {
    private static final String VERIFICATIONS_EXTRA = "verifications";

    public interface VerifiedIdDialogFragment {
        void showChecklist();
    }

    public static VerifiedIdDialogSummaryFragment newInstance(VerificationRequirements verifications) {
        VerifiedIdDialogSummaryFragment fragment = new VerifiedIdDialogSummaryFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable("verifications", verifications);
        fragment.setArguments(arguments);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_verified_id_dialog_summary, container, false);
        VerificationRequirements verifications = (VerificationRequirements) getArguments().getParcelable("verifications");
        ((TextView) view.findViewById(C0880R.C0882id.steps_left)).setText(getResources().getQuantityString(C0880R.plurals.x_steps_remaining, verifications.numberStepsRemaining(), new Object[]{Integer.valueOf(verifications.numberStepsRemaining())}));
        showCheckmark((TextView) view.findViewById(C0880R.C0882id.summary_photo_tv), verifications.profilePhotoComplete());
        showCheckmark((TextView) view.findViewById(C0880R.C0882id.summary_email_tv), verifications.emailVerificationComplete());
        showCheckmark((TextView) view.findViewById(C0880R.C0882id.summary_phone_tv), verifications.phoneComplete());
        showCheckmark((TextView) view.findViewById(C0880R.C0882id.summary_offline_tv), verifications.offlineVerificationComplete());
        showCheckmark((TextView) view.findViewById(C0880R.C0882id.summary_online_tv), verifications.onlineVerificationComplete());
        ((Button) view.findViewById(C0880R.C0882id.okay_button)).setOnClickListener(VerifiedIdDialogSummaryFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(1);
        return dialog;
    }

    private void showCheckmark(TextView textView, boolean show) {
        textView.setCompoundDrawablesWithIntrinsicBounds(ColorizedDrawable.forIdWithColor(getActivity(), C0880R.C0881drawable.icon_checkmark_black, show ? C0880R.color.verified_id_summary_checkmark : C0880R.color.white_zero_percent_alpha), null, null, null);
    }
}
