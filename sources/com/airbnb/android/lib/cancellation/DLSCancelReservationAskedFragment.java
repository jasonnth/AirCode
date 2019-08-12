package com.airbnb.android.lib.cancellation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;

public class DLSCancelReservationAskedFragment extends DLSCancelReservationBaseFragment {
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
        this.marquee.setTitle(C0880R.string.cancel_detail_asked_title);
        this.textRow.setText(C0880R.string.cancel_detail_asked_text);
        this.btn.setText(C0880R.string.cancel_detail_asked_button);
        setToolbar(this.toolbar);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void changeReservation() {
        CancellationAnalytics.trackElementClick(CancellationAnalytics.VALUE_PAGE_ASKED, this.cancellationData, CancellationAnalytics.VALUE_ELEMENT_ASK_HOST_TO_CANCEL_BUTTON, null);
        startActivity(ThreadFragmentIntents.newIntent(getActivity(), (long) this.cancelActivity.reservation.getThreadId(), InboxType.Guest));
        this.cancelActivity.finish();
    }
}
