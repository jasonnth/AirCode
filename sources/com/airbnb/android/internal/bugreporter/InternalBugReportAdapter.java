package com.airbnb.android.internal.bugreporter;

import android.os.Bundle;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RearrangablePhotoRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.internal.C6574R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class InternalBugReportAdapter extends AirEpoxyAdapter {
    /* access modifiers changed from: 0000 */
    @State
    public CharSequence details = "";
    /* access modifiers changed from: 0000 */
    @State
    public boolean includeUserInfo = true;
    @State
    ArrayList<String> photos = new ArrayList<>();
    /* access modifiers changed from: 0000 */
    @State
    public CharSequence recipient = "";
    private final ToolbarSpacerEpoxyModel_ spacerModel = new ToolbarSpacerEpoxyModel_();
    /* access modifiers changed from: 0000 */
    @State
    public CharSequence subject = "";

    interface Listener {
        void pickPhoto();
    }

    InternalBugReportAdapter(Listener listener, List<String> logFiles, String previousRecipient, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        if (savedInstanceState == null) {
            this.recipient = previousRecipient;
        }
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new DocumentMarqueeEpoxyModel_().titleRes(C6574R.string.internal_bug_report_fragment_title), new SwitchRowEpoxyModel_().titleRes(C6574R.string.internal_bug_report_option_include_user_info).style(SwitchStyle.Filled).checked(this.includeUserInfo).checkedChangeListener(InternalBugReportAdapter$$Lambda$1.lambdaFactory$(this)), new InlineInputRowEpoxyModel_().titleRes(C6574R.string.internal_bug_report_subject).input(this.subject).inputChangedListener(InternalBugReportAdapter$$Lambda$4.lambdaFactory$(this)), new InlineInputRowEpoxyModel_().titleRes(C6574R.string.internal_bug_report_details).input(this.details).inputChangedListener(InternalBugReportAdapter$$Lambda$5.lambdaFactory$(this)), new InlineInputRowEpoxyModel_().titleRes(C6574R.string.internal_bug_report_recipient).hintRes(C6574R.string.internal_bug_report_recipient_hint).input(this.recipient).inputChangedListener(InternalBugReportAdapter$$Lambda$6.lambdaFactory$(this)), new StandardRowEpoxyModel_().titleRes(C6574R.string.internal_bug_report_logs).subtitle((CharSequence) Joiner.m1896on("\n").join((Iterable<?>) FluentIterable.from((Iterable<E>) logFiles).transform(InternalBugReportAdapter$$Lambda$7.lambdaFactory$()))), new LinkActionRowEpoxyModel_().textRes(C6574R.string.internal_bug_report_add_photo).clickListener(InternalBugReportAdapter$$Lambda$8.lambdaFactory$(listener))});
        addModels((Collection<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) this.photos).transform(InternalBugReportAdapter$$Lambda$9.lambdaFactory$()).toList());
        addModel(this.spacerModel);
    }

    public void addPhoto(String path) {
        this.photos.add(path);
        insertModelBefore(createPhotoModel(path), this.spacerModel);
    }

    public ImmutableList<String> getPhotos() {
        return ImmutableList.copyOf((Collection<? extends E>) this.photos);
    }

    public String getSubject() {
        return this.subject.toString();
    }

    public String getDetails() {
        return this.details.toString();
    }

    public String getRecipient() {
        return this.recipient.toString();
    }

    /* access modifiers changed from: private */
    public static EpoxyModel<?> createPhotoModel(String path) {
        return new RearrangablePhotoRowEpoxyModel_().image(new SimpleImage(path));
    }
}
