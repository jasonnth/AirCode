package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.viewcomponents.DraggableAirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.RearrangableLabeledPhotoEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import java.util.List;

class CheckInReorderStepsAdapter extends DraggableAirEpoxyAdapter {
    public CheckInReorderStepsAdapter(List<CheckInStep> steps) {
        for (int stepNum = 0; stepNum < steps.size(); stepNum++) {
            CheckInStep step = (CheckInStep) steps.get(stepNum);
            this.models.add(new RearrangableLabeledPhotoEpoxyModel_().m5374id(step.getId()).imageUrl(step.getPictureUrl()).placeHolderText(TextUtil.compressWhitespace(step.getNote())).label(Integer.toString(stepNum + 1)));
        }
    }

    /* access modifiers changed from: protected */
    public boolean onMove(int fromPosition, int toPosition) {
        boolean moved = super.onMove(fromPosition, toPosition);
        for (Integer intValue : ListUtils.range(fromPosition, toPosition)) {
            int pos = intValue.intValue();
            ((RearrangableLabeledPhotoEpoxyModel_) this.models.get(pos)).label(Integer.toString(pos + 1));
            notifyModelChanged((EpoxyModel) this.models.get(pos));
        }
        return moved;
    }

    public List<Long> currentOrdering() {
        return FluentIterable.from((Iterable<E>) this.models).transform(CheckInReorderStepsAdapter$$Lambda$1.lambdaFactory$()).toList();
    }
}
