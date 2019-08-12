package com.airbnb.epoxy;

import android.support.p002v7.widget.RecyclerView.AdapterDataObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class DiffHelper {
    /* access modifiers changed from: private */
    public final BaseEpoxyAdapter adapter;
    /* access modifiers changed from: private */
    public ArrayList<ModelState> currentStateList = new ArrayList<>();
    /* access modifiers changed from: private */
    public Map<Long, ModelState> currentStateMap = new HashMap();
    private final boolean immutableModels;
    private final DifferModelListObserver modelListObserver = new DifferModelListObserver();
    /* access modifiers changed from: private */
    public boolean notifiedOfStructuralChanges;
    private final AdapterDataObserver observer = new AdapterDataObserver() {
        public void onChanged() {
            throw new UnsupportedOperationException("Diffing is enabled. You should use notifyModelsChanged instead of notifyDataSetChanged");
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            for (int i = positionStart; i < positionStart + itemCount; i++) {
                ((ModelState) DiffHelper.this.currentStateList.get(i)).hashCode = ((EpoxyModel) DiffHelper.this.adapter.getCurrentModels().get(i)).hashCode();
            }
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            if (itemCount != 0) {
                DiffHelper.this.notifiedOfStructuralChanges = true;
                if (itemCount == 1 || positionStart == DiffHelper.this.currentStateList.size()) {
                    for (int i = positionStart; i < positionStart + itemCount; i++) {
                        DiffHelper.this.currentStateList.add(i, DiffHelper.this.createStateForPosition(i));
                    }
                } else {
                    List<ModelState> newModels = new ArrayList<>(itemCount);
                    for (int i2 = positionStart; i2 < positionStart + itemCount; i2++) {
                        newModels.add(DiffHelper.this.createStateForPosition(i2));
                    }
                    DiffHelper.this.currentStateList.addAll(positionStart, newModels);
                }
                int size = DiffHelper.this.currentStateList.size();
                for (int i3 = positionStart + itemCount; i3 < size; i3++) {
                    ModelState modelState = (ModelState) DiffHelper.this.currentStateList.get(i3);
                    modelState.position += itemCount;
                }
            }
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if (itemCount != 0) {
                DiffHelper.this.notifiedOfStructuralChanges = true;
                List<ModelState> modelsToRemove = DiffHelper.this.currentStateList.subList(positionStart, positionStart + itemCount);
                for (ModelState model : modelsToRemove) {
                    DiffHelper.this.currentStateMap.remove(Long.valueOf(model.f2683id));
                }
                modelsToRemove.clear();
                int size = DiffHelper.this.currentStateList.size();
                for (int i = positionStart; i < size; i++) {
                    ModelState modelState = (ModelState) DiffHelper.this.currentStateList.get(i);
                    modelState.position -= itemCount;
                }
            }
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            if (fromPosition != toPosition) {
                if (itemCount != 1) {
                    throw new IllegalArgumentException("Moving more than 1 item at a time is not supported. Number of items moved: " + itemCount);
                }
                DiffHelper.this.notifiedOfStructuralChanges = true;
                ModelState model = (ModelState) DiffHelper.this.currentStateList.remove(fromPosition);
                model.position = toPosition;
                DiffHelper.this.currentStateList.add(toPosition, model);
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        ModelState modelState = (ModelState) DiffHelper.this.currentStateList.get(i);
                        modelState.position--;
                    }
                    return;
                }
                for (int i2 = toPosition + 1; i2 <= fromPosition; i2++) {
                    ModelState modelState2 = (ModelState) DiffHelper.this.currentStateList.get(i2);
                    modelState2.position++;
                }
            }
        }
    };
    private ArrayList<ModelState> oldStateList = new ArrayList<>();
    private Map<Long, ModelState> oldStateMap = new HashMap();
    private final boolean usingModelListObserver;

    DiffHelper(BaseEpoxyAdapter adapter2, boolean immutableModels2) {
        this.adapter = adapter2;
        this.immutableModels = immutableModels2;
        adapter2.registerAdapterDataObserver(this.observer);
        this.usingModelListObserver = adapter2 instanceof EpoxyAdapter;
        if (this.usingModelListObserver) {
            ((ModelList) adapter2.getCurrentModels()).setObserver(this.modelListObserver);
        }
    }

    /* access modifiers changed from: 0000 */
    public void notifyModelChanges() {
        UpdateOpHelper updateOpHelper = new UpdateOpHelper();
        if (this.usingModelListObserver && this.modelListObserver.hasNoChanges()) {
            updateHashes(updateOpHelper);
        } else if (this.notifiedOfStructuralChanges || (!this.modelListObserver.hasOnlyInsertions() && !this.modelListObserver.hasOnlyRemovals())) {
            buildDiff(updateOpHelper);
        } else {
            notifyChanges(this.modelListObserver);
            updateHashes(updateOpHelper);
        }
        this.adapter.unregisterAdapterDataObserver(this.observer);
        notifyChanges(updateOpHelper);
        this.adapter.registerAdapterDataObserver(this.observer);
        this.modelListObserver.reset();
        this.notifiedOfStructuralChanges = false;
    }

    private void updateHashes(UpdateOpHelper updateOpHelper) {
        int modelCount = this.adapter.getCurrentModels().size();
        if (modelCount != this.currentStateList.size()) {
            throw new IllegalStateException("State list does not match current models");
        }
        for (int i = 0; i < modelCount; i++) {
            ModelState state = (ModelState) this.currentStateList.get(i);
            int newHash = ((EpoxyModel) this.adapter.getCurrentModels().get(i)).hashCode();
            if (state.hashCode != newHash) {
                updateOpHelper.update(i, state.model);
                state.hashCode = newHash;
            }
        }
    }

    private void notifyChanges(UpdateOpHelper opHelper) {
        for (UpdateOp op : opHelper.opList) {
            switch (op.type) {
                case 0:
                    this.adapter.notifyItemRangeInserted(op.positionStart, op.itemCount);
                    break;
                case 1:
                    this.adapter.notifyItemRangeRemoved(op.positionStart, op.itemCount);
                    break;
                case 2:
                    if (this.immutableModels && op.payloads != null) {
                        this.adapter.notifyItemRangeChanged(op.positionStart, op.itemCount, new DiffPayload(op.payloads));
                        break;
                    } else {
                        this.adapter.notifyItemRangeChanged(op.positionStart, op.itemCount);
                        break;
                    }
                    break;
                case 3:
                    this.adapter.notifyItemMoved(op.positionStart, op.itemCount);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type: " + op.type);
            }
        }
    }

    private UpdateOpHelper buildDiff(UpdateOpHelper updateOpHelper) {
        prepareStateForDiff();
        collectRemovals(updateOpHelper);
        if (this.oldStateList.size() - updateOpHelper.getNumRemovals() != this.currentStateList.size()) {
            collectInsertions(updateOpHelper);
        }
        collectMoves(updateOpHelper);
        collectChanges(updateOpHelper);
        return updateOpHelper;
    }

    private void prepareStateForDiff() {
        this.oldStateList.clear();
        this.oldStateMap.clear();
        ArrayList<ModelState> tempList = this.oldStateList;
        this.oldStateList = this.currentStateList;
        this.currentStateList = tempList;
        Map<Long, ModelState> tempMap = this.oldStateMap;
        this.oldStateMap = this.currentStateMap;
        this.currentStateMap = tempMap;
        Iterator it = this.oldStateList.iterator();
        while (it.hasNext()) {
            ((ModelState) it.next()).pair = null;
        }
        int modelCount = this.adapter.getCurrentModels().size();
        this.currentStateList.ensureCapacity(modelCount);
        for (int i = 0; i < modelCount; i++) {
            this.currentStateList.add(createStateForPosition(i));
        }
    }

    /* access modifiers changed from: private */
    public ModelState createStateForPosition(int position) {
        EpoxyModel<?> model = (EpoxyModel) this.adapter.getCurrentModels().get(position);
        model.addedToAdapter = true;
        ModelState state = ModelState.build(model, position, this.immutableModels);
        ModelState previousValue = (ModelState) this.currentStateMap.put(Long.valueOf(state.f2683id), state);
        if (previousValue == null) {
            return state;
        }
        int previousPosition = previousValue.position;
        throw new IllegalStateException("Two models have the same ID. ID's must be unique! Model at position " + position + ": " + model + " Model at position " + previousPosition + ": " + ((EpoxyModel) this.adapter.getCurrentModels().get(previousPosition)));
    }

    private void collectRemovals(UpdateOpHelper helper) {
        Iterator it = this.oldStateList.iterator();
        while (it.hasNext()) {
            ModelState state = (ModelState) it.next();
            state.position -= helper.getNumRemovals();
            state.pair = (ModelState) this.currentStateMap.get(Long.valueOf(state.f2683id));
            if (state.pair != null) {
                state.pair.pair = state;
            } else {
                helper.remove(state.position);
            }
        }
    }

    private void collectInsertions(UpdateOpHelper helper) {
        Iterator<ModelState> oldItemIterator = this.oldStateList.iterator();
        Iterator it = this.currentStateList.iterator();
        while (it.hasNext()) {
            ModelState itemToInsert = (ModelState) it.next();
            if (itemToInsert.pair != null) {
                ModelState nextOldItem = getNextItemWithPair(oldItemIterator);
                if (nextOldItem != null) {
                    nextOldItem.position += helper.getNumInsertions();
                }
            } else {
                helper.add(itemToInsert.position);
            }
        }
    }

    private void collectChanges(UpdateOpHelper helper) {
        boolean modelChanged;
        Iterator it = this.currentStateList.iterator();
        while (it.hasNext()) {
            ModelState newItem = (ModelState) it.next();
            ModelState previousItem = newItem.pair;
            if (previousItem != null) {
                if (this.immutableModels) {
                    if (previousItem.model.isDebugValidationEnabled()) {
                        previousItem.model.validateStateHasNotChangedSinceAdded("Model was changed before it could be diffed.", previousItem.position);
                    }
                    modelChanged = !previousItem.model.equals(newItem.model);
                } else {
                    modelChanged = previousItem.hashCode != newItem.hashCode;
                }
                if (modelChanged) {
                    helper.update(newItem.position, previousItem.model);
                }
            }
        }
    }

    private void collectMoves(UpdateOpHelper helper) {
        Iterator<ModelState> oldItemIterator = this.oldStateList.iterator();
        ModelState nextOldItem = null;
        Iterator it = this.currentStateList.iterator();
        while (it.hasNext()) {
            ModelState newItem = (ModelState) it.next();
            if (newItem.pair == null) {
                if (!helper.moves.isEmpty()) {
                    newItem.pairWithSelf();
                }
            }
            if (nextOldItem == null) {
                nextOldItem = getNextItemWithPair(oldItemIterator);
                if (nextOldItem == null) {
                    nextOldItem = newItem.pair;
                }
            }
            while (true) {
                if (nextOldItem == null) {
                    break;
                }
                updateItemPosition(newItem.pair, helper.moves);
                updateItemPosition(nextOldItem, helper.moves);
                if (newItem.f2683id != nextOldItem.f2683id || newItem.position != nextOldItem.position) {
                    int newItemDistance = newItem.pair.position - newItem.position;
                    int oldItemDistance = nextOldItem.pair.position - nextOldItem.position;
                    if (newItemDistance != 0 || oldItemDistance != 0) {
                        if (oldItemDistance <= newItemDistance) {
                            helper.move(newItem.pair.position, newItem.position);
                            newItem.pair.position = newItem.position;
                            newItem.pair.lastMoveOp = helper.getNumMoves();
                            break;
                        }
                        helper.move(nextOldItem.position, nextOldItem.pair.position);
                        nextOldItem.position = nextOldItem.pair.position;
                        nextOldItem.lastMoveOp = helper.getNumMoves();
                        nextOldItem = getNextItemWithPair(oldItemIterator);
                    } else {
                        nextOldItem = null;
                        break;
                    }
                } else {
                    nextOldItem = null;
                    break;
                }
            }
        }
    }

    private void updateItemPosition(ModelState item, List<UpdateOp> moveOps) {
        int size = moveOps.size();
        for (int i = item.lastMoveOp; i < size; i++) {
            UpdateOp moveOp = (UpdateOp) moveOps.get(i);
            int fromPosition = moveOp.positionStart;
            int toPosition = moveOp.itemCount;
            if (item.position > fromPosition && item.position <= toPosition) {
                item.position--;
            } else if (item.position < fromPosition && item.position >= toPosition) {
                item.position++;
            }
        }
        item.lastMoveOp = size;
    }

    private ModelState getNextItemWithPair(Iterator<ModelState> iterator) {
        ModelState nextItem = null;
        while (nextItem == null && iterator.hasNext()) {
            nextItem = (ModelState) iterator.next();
            if (nextItem.pair == null) {
                nextItem = null;
            }
        }
        return nextItem;
    }
}
