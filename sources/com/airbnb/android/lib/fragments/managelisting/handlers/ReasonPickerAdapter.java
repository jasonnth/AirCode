package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.models.IconWithTitles;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment.InputReason;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Joiner;
import java.util.List;

public class ReasonPickerAdapter extends AirEpoxyAdapter {
    private static final int SUBTITLE_MAX_LINE_NUM = 5;
    private static final int TITLE_MAX_LINE_NUM = 3;
    protected final ReasonPickerCallback callback;
    protected boolean isModal;
    private final ReservationCancellationInfo reservationCancellationInfo;

    public interface ReasonPickerCallback {
        void onCancelReservationClicked(ReservationCancellationReason reservationCancellationReason, boolean z);

        void onHostMessageUpdate(InputReason inputReason, String str);

        void onKeepReservationClicked();

        void onReasonClicked(ReservationCancellationReason reservationCancellationReason, boolean z);

        void onShowModal(ReservationCancellationReason reservationCancellationReason);

        void onViewNondiscriminationPolicyClicked();
    }

    public ReasonPickerAdapter(ReasonPickerCallback callback2, ReservationCancellationInfo reservationCancellationInfo2, boolean isModal2) {
        super(true);
        this.callback = callback2;
        this.reservationCancellationInfo = reservationCancellationInfo2;
        this.isModal = isModal2;
    }

    public ReasonPickerAdapter(ReasonPickerCallback callback2, ReservationCancellationInfo reservationCancellationInfo2) {
        this(callback2, reservationCancellationInfo2, false);
    }

    /* access modifiers changed from: protected */
    public void addTitleRes(int titleInt) {
        addTitleRes(titleInt, 0);
    }

    /* access modifiers changed from: protected */
    public void addTitleRes(int titleInt, int captionInt) {
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(titleInt).captionRes(captionInt));
    }

    /* access modifiers changed from: protected */
    public void addTitle(int titleRes) {
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(titleRes));
    }

    /* access modifiers changed from: protected */
    public void addTitle(CharSequence title) {
        addModel(new DocumentMarqueeEpoxyModel_().titleText(title));
    }

    /* access modifiers changed from: protected */
    public void addReasons(List<ReservationCancellationReason> reasons) {
        if (reasons == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Invalid Reservation Cancellation reasons" + reasons));
        }
        for (ReservationCancellationReason reason : reasons) {
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{new StandardRowEpoxyModel_().title((CharSequence) reason.getReasonTitleStr(this.reservationCancellationInfo)).titleMaxLine(2).subtitle(reason.getReasonSubTitleStrId()).clickListener(ReasonPickerAdapter$$Lambda$1.lambdaFactory$(this, reason)).rowDrawableRes(C0880R.C0881drawable.n2_icon_chevron_right_babu)});
        }
    }

    /* access modifiers changed from: protected */
    public void addStandardRow(int titleRes, int titleMaxLineNum) {
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new StandardRowEpoxyModel_().title(titleRes).titleMaxLine(titleMaxLineNum)});
    }

    /* access modifiers changed from: protected */
    public void addStandardRow(String title, String subtitle) {
        addStandardRow(title, subtitle, null);
    }

    /* access modifiers changed from: protected */
    public void addStandardRow(IconWithTitles iconWithTitles) {
        addStandardRow(iconWithTitles.getTitle(), Joiner.m1896on("\n").join((Iterable<?>) iconWithTitles.getSubtitles()), iconWithTitles.getValue());
    }

    /* access modifiers changed from: protected */
    public void addStandardRow(String title, String subtitle, String infoText) {
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new StandardRowEpoxyModel_().title((CharSequence) title).titleMaxLine(3).subtitle((CharSequence) subtitle).subTitleMaxLine(5).infoText((CharSequence) infoText)});
    }

    /* access modifiers changed from: protected */
    public void addViewNondiscriminationPolicyLink() {
        addModel(new LinkActionRowEpoxyModel_().textRes(C0880R.string.reservation_cancellation_view_policy).clickListener(ReasonPickerAdapter$$Lambda$2.lambdaFactory$(this)));
    }

    /* access modifiers changed from: protected */
    public void addKeepReservationLink() {
        addModel(new LinkActionRowEpoxyModel_().textRes(C0880R.string.keep_reservation).clickListener(ReasonPickerAdapter$$Lambda$3.lambdaFactory$(this)));
    }
}
