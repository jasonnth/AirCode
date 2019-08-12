package com.airbnb.p027n2.components.photorearranger;

import com.airbnb.p027n2.components.photorearranger.RearrangablePhotoRow.State;
import com.airbnb.p027n2.primitives.imaging.Image;

/* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerItem */
public class PhotoRearrangerItem {
    public final int firstItemLabelRes;

    /* renamed from: id */
    public final long f2705id;
    public final Image image;
    public final State state;

    public PhotoRearrangerItem(long id, Image image2, State state2, int firstItemLabelRes2) {
        this.f2705id = id;
        this.image = image2;
        this.state = state2;
        this.firstItemLabelRes = firstItemLabelRes2;
    }
}
