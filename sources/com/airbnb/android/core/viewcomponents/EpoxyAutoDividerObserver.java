package com.airbnb.android.core.viewcomponents;

import android.support.p002v7.widget.RecyclerView.AdapterDataObserver;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirModel;
import java.util.ArrayList;
import java.util.List;

public class EpoxyAutoDividerObserver extends AdapterDataObserver {
    private final AirEpoxyAdapter adapter;
    private final List<EpoxyModel<?>> models;
    private int spanCount = 1;

    public EpoxyAutoDividerObserver(AirEpoxyAdapter adapter2, List<EpoxyModel<?>> models2) {
        this.adapter = adapter2;
        this.models = models2;
    }

    public void setSpanCount(int spanCount2) {
        this.spanCount = spanCount2;
    }

    public void onChanged() {
        toggleDividers();
    }

    public void onItemRangeChanged(int positionStart, int itemCount) {
        toggleDividers();
    }

    public void onItemRangeInserted(int positionStart, int itemCount) {
        toggleDividers();
    }

    public void onItemRangeRemoved(int positionStart, int itemCount) {
        toggleDividers();
    }

    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        toggleDividers();
    }

    private void toggleDividers() {
        boolean z;
        boolean hideDivider;
        boolean z2;
        boolean firstSectionHeader = true;
        List<AirModel> visibleModels = new ArrayList<>(this.models.size());
        for (EpoxyModel<?> model : this.models) {
            if (!(model instanceof AirModel)) {
                throw new IllegalStateException("You have a non divider model in your list: " + model);
            } else if (model.isShown()) {
                visibleModels.add((AirModel) model);
            }
        }
        int modelCount = visibleModels.size();
        for (int i = 0; i < modelCount; i++) {
            AirModel viewModel = (AirModel) visibleModels.get(i);
            if (viewModel.supportsDividers()) {
                switch (viewModel.getDividerViewType()) {
                    case -1:
                    case 2:
                        throw new IllegalStateException("Should not try and auto draw dividers if dividers are unsupported by EpoxyModel");
                    case 0:
                        firstSectionHeader = false;
                        if (i != modelCount - 1) {
                            AirModel nextEpoxyModel = (AirModel) visibleModels.get(i + 1);
                            if (nextEpoxyModel.supportsDividers()) {
                                if (nextEpoxyModel.getDividerViewType() == 4) {
                                    hideDivider = true;
                                } else {
                                    hideDivider = false;
                                }
                                if (!hideDivider) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                setDivider(viewModel, i, z2);
                                break;
                            } else {
                                setDivider(viewModel, i, true);
                                break;
                            }
                        } else {
                            setDivider(viewModel, i, false);
                            break;
                        }
                    case 1:
                        if (!firstSectionHeader) {
                            z = true;
                        } else {
                            z = false;
                        }
                        setDivider(viewModel, i, z);
                        firstSectionHeader = false;
                        break;
                    case 3:
                        setDivider(viewModel, i, true);
                        firstSectionHeader = true;
                        break;
                    case 4:
                        setDivider(viewModel, i, true);
                        firstSectionHeader = true;
                        break;
                    case 5:
                        setDivider(viewModel, i, true);
                        if (i == 0) {
                            break;
                        } else {
                            AirModel previousEpoxyModel = (AirModel) visibleModels.get(i - 1);
                            if (!previousEpoxyModel.supportsDividers()) {
                                break;
                            } else {
                                setDivider(previousEpoxyModel, i, false);
                                break;
                            }
                        }
                }
            }
        }
    }

    private void setDivider(AirModel viewModel, int position, boolean showDivider) {
        boolean fullWidth;
        boolean z = true;
        if (viewModel.getSpanSize(this.spanCount, position, this.adapter.getItemCount()) == this.spanCount) {
            fullWidth = true;
        } else {
            fullWidth = false;
        }
        if (!fullWidth || !showDivider) {
            z = false;
        }
        viewModel.showDivider(z);
    }
}
