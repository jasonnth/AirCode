package com.airbnb.android.lib.cancellation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.TextInputSheetFragment;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;

public class DLSCancelReservationOtherFragment extends DLSCancelReservationBaseFragment {
    @BindView
    AirButton btn;
    @BindView
    DocumentMarquee marquee;
    @BindView
    SimpleTextRow textRow;
    @BindView
    AirToolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_dls_cancel_reservation_info, container, false);
        bindViews(view);
        this.marquee.setTitle(C0880R.string.cancel_detail_other_title);
        this.textRow.setText(C0880R.string.cancel_detail_other_text);
        this.btn.setText(C0880R.string.cancel_detail_other_button);
        setToolbar(this.toolbar);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void changeReservation() {
        CancellationAnalytics.trackPage(CancellationAnalytics.VALUE_PAGE_OTHER_INPUT, this.cancellationData);
        startActivityForResult(TextInputSheetFragment.newIntent(getContext(), "", getString(C0880R.string.cancel_detail_other_prompt), getString(C0880R.string.continue_text)), TextInputSheetFragment.REQUEST_CODE_TEXT_INPUT_SHEET);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 811) {
            this.cancellationData = this.cancellationData.toBuilder().otherReason(data.getStringExtra("result_extra_input")).build();
            this.cancelActivity.onStepFinished(CancelReservationStep.Other, this.cancellationData);
        }
    }
}
