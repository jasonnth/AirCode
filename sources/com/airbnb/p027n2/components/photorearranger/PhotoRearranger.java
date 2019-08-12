package com.airbnb.p027n2.components.photorearranger;

import android.support.p002v7.widget.GridLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.helper.ItemTouchHelper;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerController.Mode;

/* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearranger */
public class PhotoRearranger {
    public static PhotoRearrangerController init(RecyclerView recyclerView, PhotoRearrangerAdapter<?> adapter, Mode initialMode) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        RearrangingCallback itemTouchCallback = new RearrangingCallback(adapter);
        new ItemTouchHelper(itemTouchCallback).attachToRecyclerView(recyclerView);
        return new PhotoRearrangerController(recyclerView, itemTouchCallback, initialMode);
    }
}
