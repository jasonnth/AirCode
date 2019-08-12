package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.models.SupportPhoneNumber;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;

public class SupportNumbersAdapter extends AirEpoxyAdapter {
    private final Context context;
    private final StandardRowEpoxyModel_ defaultNumberRow;
    private final SimpleTextRowEpoxyModel loadingExplanationRow;
    private final LoadingRowEpoxyModel loadingRow = new LoadingRowEpoxyModel_();

    public SupportNumbersAdapter(Context context2) {
        this.context = context2;
        enableDiffing();
        this.defaultNumberRow = createCallRow(context2.getString(C0880R.string.customer_support), context2.getString(C0880R.string.support_phone_number));
        this.loadingExplanationRow = new SimpleTextRowEpoxyModel_().small().text(context2.getString(C0880R.string.customer_support_loading_local_numbers)).showDivider(false);
    }

    public void showDefaultNumberAndLoading() {
        this.models.clear();
        notifyModelsChanged();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.defaultNumberRow, this.loadingRow, this.loadingExplanationRow});
    }

    public void showSupportNumbers(ArrayList<SupportPhoneNumber> numbers) {
        this.models.clear();
        notifyModelsChanged();
        addModels((Collection<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) numbers).filter(SupportNumbersAdapter$$Lambda$1.lambdaFactory$()).transform(SupportNumbersAdapter$$Lambda$2.lambdaFactory$(this)).toList());
    }

    static /* synthetic */ boolean lambda$showSupportNumbers$0(SupportPhoneNumber item) {
        return !TextUtils.isEmpty(item.getNumber());
    }

    public void showFallbackNumber() {
        this.models.clear();
        notifyModelsChanged();
        addModel(this.defaultNumberRow);
    }

    /* access modifiers changed from: private */
    public StandardRowEpoxyModel_ createCallRow(String title, String phoneNumber) {
        return new StandardRowEpoxyModel_().title((CharSequence) title).subtitle((CharSequence) phoneNumber).actionText(C0880R.string.call).clickListener(SupportNumbersAdapter$$Lambda$3.lambdaFactory$(this, phoneNumber)).longClickListener(SupportNumbersAdapter$$Lambda$4.lambdaFactory$(this, phoneNumber));
    }
}
